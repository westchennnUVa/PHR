package forumutil;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by WestChen on 16/3/16.
 */
public class HttpUtil2 {
    private HttpClient httpClient;

    public HttpUtil2(){
        httpClient = new DefaultHttpClient();
    }

    public String get(String url) throws IOException {
        HttpGet get = new HttpGet(url);
        HttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == 200){
            return EntityUtils.toString(response.getEntity(), "utf-8");
        }else
            return null;
    }

    public String post(String url,List<NameValuePair> temp){
        HttpPost post = new HttpPost(url);
        //List<NameValuePair> paprams = new ArrayList<NameValuePair>();
        //paprams.add(new BasicNameValuePair("userName","wind"));
        //paprams.add(new BasicNameValuePair("password","123456"));
        try {
            post.setEntity(new UrlEncodedFormEntity(temp, HTTP.UTF_8));
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200){
                return EntityUtils.toString(response.getEntity());
            }else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
