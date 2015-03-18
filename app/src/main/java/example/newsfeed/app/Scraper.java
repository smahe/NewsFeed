package example.newsfeed.app;

public abstract class Scraper {
    private String url;
    protected NetworkClient client;

    public abstract void getNetworkClient();

    public abstract void getPage();

    public void setUrl(String newspaper){
        url = newspaper;
    }

    public String getUrl(String newspaper){
        return url;
    }

}
