package example.newsfeed.app;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private static final String LOGTAG = MainActivity.class.getName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String[] data = new String[1];
        final RecyclerView recyclerView;
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.scrollToPosition(0);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        new AsyncTask<String,Void,String>(){
            @Override
            protected String doInBackground(String... params) {
                try {
                    NetworkClient client = NetworkClient.getClient();
                    //Hitting localhost
                    //old ip 192.168.56.1
                    Request request = new Request.Builder().url("http://192.168.1.2:8080/com.serverNewsFeed/national/thehindu")
                            .build();
                    Response response = client.request(request);
                    InputStream is = response.body().byteStream();
                    Log.d(LOGTAG, "++++++++++" + response.message());
                    byte[] buffer = new byte[512];
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    is.read();
                    int numRead = 0;
                    while ((numRead = is.read(buffer)) != -1) {
                        baos.write(buffer, 0, numRead);
                    }
                    data[0] = new String(baos.toByteArray());
                    baos.close();
                    is.close();
                    Log.d(LOGTAG, "=====================" + data[0]);
                } catch (Exception e) {
                    Log.d(LOGTAG, "EXCEPTION IN DO IN BACKGROUND");
                    e.printStackTrace();
                }
                Log.d(LOGTAG,"berfore return");
                return data[0];
            }

            @Override
            protected void onPostExecute(String result) {
                Log.d(LOGTAG, "========inside post execute async=========="+result);
                    final List<NewsData> viewdata;// = new ArrayList<NewsData>();
                    viewdata = parseResponse(result);
                    //dispCustomRecyclerView(viewdata);
                    CustomAdapter adapter = new CustomAdapter(viewdata);
                    recyclerView.setAdapter(adapter);

                //    super.onPostExecute(result);
            }
        }.execute();
        //if(data == null){
     //   Log.d(LOGTAG, "====================DATA IS NULL =============");
        //}else
    }

    public List<NewsData> parseResponse(String str){
        List<NewsData> data = new ArrayList<NewsData>();
        try {
            Log.d(LOGTAG,"creating json");
            //json syntax missed adding it to make str as jsonobject
            str = "{"+str;
            JSONObject jsobj = new JSONObject(str.substring(str.indexOf("{"),str.lastIndexOf("}") + 1));
            JSONArray jsarray = jsobj.getJSONArray("article");
            for(int i = 0;i<jsarray.length();i++){
                JSONObject js = jsarray.getJSONObject(i);
                Log.d(LOGTAG,"value of "+js.optString("url")+" "+js.optString("content"));
                data.add(new NewsData(js.optString("url"),js.optString("content")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
