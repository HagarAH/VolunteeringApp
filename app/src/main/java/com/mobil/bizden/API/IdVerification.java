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

public class IdVerification {

    private OkHttpClient client = new OkHttpClient();
    private MediaType mediaType = MediaType.parse("application/soap+xml; charset=utf-8");
    private Request request;

    public IdVerification(String fName, String lName, String birthYear, String TC) {
        String requestBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\r\n    <soap12:Body>\r\n        <TCKimlikNoDogrula xmlns=\"http://tckimlik.nvi.gov.tr/WS\">\r\n            <TCKimlikNo>" + TC + "</TCKimlikNo>\r\n            <Ad>" + fName + "</Ad>\r\n            <Soyad>" + lName + "</Soyad>\r\n            <DogumYili>" + birthYear + "</DogumYili>\r\n        </TCKimlikNoDogrula>\r\n    </soap12:Body>\r\n</soap12:Envelope>";

        RequestBody body = RequestBody.create(mediaType, requestBody);

        request = new Request.Builder()
                .url("https://tckimlik.nvi.gov.tr/Service/KPSPublic.asmx")
                .method("POST", body)
                .addHeader("Content-Type", "application/soap+xml; charset=utf-8")
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
            NodeList nodeList = doc.getElementsByTagName("TCKimlikNoDogrulaResult");
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
