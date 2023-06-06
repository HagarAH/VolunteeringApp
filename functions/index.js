const admin = require("firebase-admin");
const functions = require("firebase-functions");

admin.initializeApp();

exports.autoAccept = functions.firestore.document(`requests/{did}`)
    .onCreate(async (snapshot, context) => {
      const request = snapshot.data();
      const db = admin.firestore();
      console.log(request);
      const gInfoDoc = await db.collection("gatheringAreasInfo")
          .doc(request.aid).get();
      const gInfo = gInfoDoc.data();
      const areaDoc = await db.collection("gatheringAreas")
          .doc(request.aid).get();
      const areaData = areaDoc.data();

      // Check if gathering area has space
      if (gInfo.occupancyRate >= areaData.capacity) {
        return null;
      }

      const code = generateRandomString(6);
      console.log(`Generated code: ${code}`);

      const entryCodeData = {
        did: context.params.did,
        aid: request.aid,
        uid: request.uid,
        status: true,
        createdDate: admin.firestore.FieldValue.serverTimestamp(),
        validUntil: new Date(Date.now() +20*60*60*1000),
        code: code,
      };

      try {
        const docRef = await db.collection("entryCodes").add(entryCodeData);
        console.log("Document written with ID: ", docRef.did);
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
