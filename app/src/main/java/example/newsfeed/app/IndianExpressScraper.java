package example.newsfeed.app;

import android.util.Log;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IndianExpressScraper extends Scraper {

    @Override
    public void getNetworkClient() {
        this.client = NetworkClient.getClient();
    }

    @Override
    public void getPage() {
        Request request = new Request.Builder().url("http://indianexpress.com/").build();
        Response response = client.request(request);
        try {
            InputStreamReader isr = new InputStreamReader(response.body().byteStream());
            BufferedReader reader = new BufferedReader(isr);
            String input;
            while((input = reader.readLine())!= null){
                Log.d("CONTENT ", input);
            }
        } catch (IOException e) {
            Log.d("EX","exception in getpage");
            e.printStackTrace();
        }

    }

}

