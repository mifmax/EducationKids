package home.maximv.responder;

import home.maximv.educationkids.R;
import home.maximv.utils.WikiRequest;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import android.app.Activity;
import android.widget.Toast;

public class Responder extends Activity{
    
    public String getResponse(String quest){
        boolean isQuestion=false;
        String newString="";
        String answer="";
        String[] q = quest.split(" ,");
        String[] a = getResources().getStringArray(R.array.questions);
        for (int i = 0; i < q.length; i++) {
            for (String questions : a) {
                if (questions.equalsIgnoreCase(q[i])){
                    isQuestion=true;
                    q[i]="";
                    break;
                }
            }
        }
        if (isQuestion){
            for (String questions : q) {
                newString=newString+questions+" ";   
            }
        }
        //if () есть в базе данных
        //else идем на wiki
        try {
            WikiRequest wikiRequest = new WikiRequest(); 
           answer =  wikiRequest.sendWikiRequest("newString");
           Toast.makeText(this,answer , Toast.LENGTH_LONG).show();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return answer;

    }    
}
