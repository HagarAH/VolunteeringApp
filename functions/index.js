const admin = require("firebase-admin");
const functions = require("firebase-functions");

admin.initializeApp();

exports.autoAccept = functions.firestore.document("requests/{requestId}")
    .onCreate(async (snapshot, context) => {
      if (!snapshot.exists || !snapshot.has( "aid") ||
          !snapshot.has( "did") || !snapshot.has( "uid") ||
          !snapshot.has( "volunteerStartTime") ||
          !snapshot.has( "volunteerEndTime")) {
        console.error("Invalid request data");
        return null;
      }

      const request = snapshot.data();
      const {aid, did} = request;

      const db = admin.firestore();
      const gInfoDoc = await db.collection("gatheringAreasInfo").doc(aid).get();
      const gInfo = gInfoDoc.data();
      const areaDoc = await db.collection("gatheringAreas").doc(aid).get();
      const areaData = areaDoc.data();

      // Check if gathering area has space
      if (gInfo.occupancyRate >= areaData.capacity) {
        const docSnapshot = await db.collection("requests").doc(did).get();
        if (docSnapshot.exists) {
          await db.collection("requests").doc(did).update({rejection: true});
        }
        return null;
      }

      // Check if user"s volunteer time matches area requirements
      const {timeRequirements} = areaData;
      let matchesRequirements = false;
      for (const requirement of timeRequirements) {
        if (requirement.start === request.volunteerStartTime &&
           requirement.end === request.volunteerEndTime &&
           requirement.requiredVolunteers <= areaData.capacity) {
          matchesRequirements = true;
          break;
        }
      }

      if (!matchesRequirements) {
        // update request status to rejected
        const docSnapshot = await db.collection("requests").doc(did).get();
        if (docSnapshot.exists) {
          await db.collection("requests").doc(did).update({status: "rejected"});
        }
        return null;
      }

      const code = generateRandomString(6);
      console.log(`Generated code: ${code}`);

      const entryCodeData = {
        id: context.params.requestId,
        did: did,
        aid: request.aid,
        uid: request.uid,
        status: true,
        createdDate: admin.firestore.FieldValue.serverTimestamp(),
        validUntil: new Date(Date.now() +20*60*60*1000),
        code: code,
      };
      const docSnapshot = await db.collection("requests").doc(did).get();
      if (docSnapshot.exists) {
        await db.collection("requests").doc(did).update({acceptance: true});
      }
      try {
        const docRef = await db.collection("entryCodes").add(entryCodeData);
        console.log("Document written with ID: ", docRef.id);
      } catch (error) {
        console.error("Error adding document: ", error);
      }

      return null;
    });
/**
 * Description of the function.
 * @param {type} length - Description of the parameter.
 * @return {type} - Description of the return value.
 */
function generateRandomString(length) {
  let result = "";
  const characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrs0123456789";
  for (let i = 0; i < length; i++) {
    result += characters.charAt(Math.floor(Math.random() * characters.length));
  }
  return result;
}
