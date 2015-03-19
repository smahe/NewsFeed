package example.newsfeed.app;

import android.util.Log;
import android.webkit.JavascriptInterface;

public class ScraperInterface{
    private static final String logtag = ScraperInterface.class.getName();

    @JavascriptInterface
    public void News(String s){
            Log.d(logtag,"");
    }

}
