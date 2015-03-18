package example.newsfeed.app;

import android.util.Log;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class NetworkClient {
    private OkHttpClient client = new OkHttpClient();
    private static NetworkClient nwclient = null;

    private NetworkClient(){
    }

    public static NetworkClient getClient(){
        if(nwclient == null )
            nwclient = new NetworkClient();
        return nwclient;
    }

    public Response request(Request request){
        try {
            Response response = client.newCall(request).execute();
            return response;
        } catch (IOException e) {
            Log.d("EX","Exception in request network client");
            e.printStackTrace();
        }
        return null;
    }

}

