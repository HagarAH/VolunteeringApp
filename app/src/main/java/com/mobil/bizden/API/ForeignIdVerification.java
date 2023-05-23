package com.mobil.bizden.API;

import android.os.AsyncTask;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ForeignIdVerification {

    private OkHttpClient client = new OkHttpClient();
    private MediaType mediaType = MediaType.parse("application/soap+xml; charset=utf-8");
    private Request request;

    public ForeignIdVerification(String fName, String lName, int birthYear,int birthMonth,int birthDay, long TC) {
        String requestBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\r\n  <soap12:Body>\r\n    <YabanciKimlikNoDogrula xmlns=\"http://tckimlik.nvi.gov.tr/WS\">\r\n      <KimlikNo>"+TC+ "</KimlikNo>\r\n      <Ad>"+fName+"</Ad>\r\n      <Soyad>"+ lName+"</Soyad>\r\n      <DogumGun>"+birthDay+"</DogumGun>\r\n      <DogumAy>"+birthMonth+"</DogumAy>\r\n      <DogumYil>"+birthMonth+"</DogumYil>\r\n    </YabanciKimlikNoDogrula>\r\n  </soap12:Body>\r\n</soap12:Envelope>";

        RequestBody body = RequestBody.create(mediaType, requestBody);

        request = new Request.Builder()
                .url("https://tckimlik.nvi.gov.tr/Service/KPSPublicYabanciDogrula.asmx")
                .method("POST", body)
                .addHeader("Content-Type", "application/soap+xml; charset=utf-8")
                .addHeader("Cookie", "TS0193588c=01e4b304429e3ec9cf29c5128a4f29295c6f14fb3f4e3f08f937209d2e2f86813c08cbfaa909f93e9648798b2f1714d05ed7515913")
                .build();
    }

    public void performVerification(final VerificationListener listener) {
        AsyncTask<Void, Void, String> verificationTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String responseBody = response.body().string();
                        return parseVerificationResult(responseBody);
                    } else {
                        return null;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String verificationResult) {
                if (verificationResult != null) {
                    listener.onVerificationCompleted(verificationResult);
                } else {
                    listener.onVerificationFailed();
                }
            }
        };

        verificationTask.execute();
    }

    private String parseVerificationResult(String responseBody) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            InputStream inputStream = new ByteArrayInputStream(responseBody.getBytes("UTF-8"));
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("YabanciKimlikNoDogrulaResult");
            if (nodeList.getLength() > 0) {
                Element element = (Element) nodeList.item(0);
                return element.getTextContent();
            }
        } catch (ParserConfigurationException | IOException | org.xml.sax.SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    public interface VerificationListener {
        void onVerificationCompleted(String verificationResult);

        void onVerificationFailed();
    }
}
