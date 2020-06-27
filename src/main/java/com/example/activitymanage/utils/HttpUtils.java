package com.example.activitymanage.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HttpUtils {
    public static String doGet(String url, Map<String, String> param) {

        CloseableHttpResponse response=null;
        CloseableHttpClient client=HttpClients.createDefault();
        String res="";
        try {
            URIBuilder builder=new URIBuilder(url);

            Set<String> keys=param.keySet();
            Iterator<String> it=keys.iterator();
            while(it.hasNext()) {
                String key=it.next();
                builder.addParameter(key, param.get(key));
            }

            URI uri=builder.build();
            HttpGet get=new HttpGet(uri);

            response=client.execute(get);

            if(response.getStatusLine().getStatusCode()==200) {
                res=EntityUtils.toString(response.getEntity(),"UTF-8");
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(response!=null) {
                    response.close();
                }
                client.close();
            }catch(IOException e) {
                e.printStackTrace();
            }

        }

        return res;
    }
}