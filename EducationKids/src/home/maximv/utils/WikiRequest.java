package home.maximv.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class WikiRequest {

    public String sendWikiRequest(String search) throws URISyntaxException, ParserConfigurationException,
            SAXException, IOException {
        HttpResponse response = null;
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet();
        request.setURI(new URI("http://ru.wikipedia.org/w/api.php?action=opensearch&search=" + URLEncoder.encode(search, "UTF-8")
                + "&limit=1&format=xml"));
       
        response = client.execute(request);
        HttpEntity entity = response.getEntity();
        InputStream is = entity.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;

        while ((line = reader.readLine()) != null) {
            sb.append((line + "\n"));
        }
        is.close();

        InputSource ins = new InputSource(new StringReader(sb.toString()));

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(ins);
        String answer = doc.getElementsByTagName("Description").item(0).getTextContent();
        answer = answer.split("—")[1].trim();
        return answer;
    }
}
