package example.newsfeed.app;

public class NewsData {
    private String title;
    private String url;

    public NewsData(String title, String url){
        this.title = title;
        this.url = url;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
       return title;
    }

    public void setUrl(String title){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }

}
