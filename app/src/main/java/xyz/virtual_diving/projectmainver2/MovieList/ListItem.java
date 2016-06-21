package xyz.virtual_diving.projectmainver2.MovieList;

/**
 * Created by b1014100 on 2016/06/02.
 */
public class ListItem {

    private int id;//通し番号
    private String titleText; //タイトル
    private String contentsText; //本文
    private int imageId; //画像のアドレス
    private int viewCount;//再生数
    private String movieURL;

    /*ゲッター*/
    public int getId(){
        return id;
    }

    public String getTitleText(){
        return titleText;
    }

    public String getContentsText(){
        return contentsText;
    }

    public int getImageId(){
        return imageId;
    }

    public int getViewCount(){
        return viewCount;
    }

    public String getMovieURL(){return movieURL;}


    /*セッター*/
    public void setListId(int id){
        this.id = id;
    }

    public void setTitleText(String titleText){
        this.titleText = titleText;
    }

    public void setContentsText(String contentsText){
        this.contentsText = contentsText;
    }

    public void setImageId(int imageId){
        this.imageId = imageId;
    }

    public void setViewCount(int viewCount){this.viewCount = viewCount;}

    public void setMovieURL(String MovieURL) {this.movieURL = MovieURL;}

}
