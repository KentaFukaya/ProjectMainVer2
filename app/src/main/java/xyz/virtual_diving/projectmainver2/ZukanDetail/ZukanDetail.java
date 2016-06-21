package xyz.virtual_diving.projectmainver2.ZukanDetail;

/**
 * Created by b1014100 on 2016/06/13.
 */
public class ZukanDetail {
    private int Id;
    private String FishName;
    private String Abstract;
    private String contents[];
    private int ImageUrl;

    //ゲッター
    public  int getId(){return  Id;}
    public String getFishName(){return FishName;}
    public String getAbstract(){return Abstract; }
    public String[] getContents(){return contents;}
    public int getImageUrl(){return ImageUrl;}

    //セッター
    public void setId(int id){this.Id = id;}
    public void setFishName(String fishName){this.FishName = fishName;}
    public void setAbstract(String Abstract){
        this.Abstract = Abstract;
    }
    public void setContents(String[] contents){
        this.contents = contents;
    }
    public void setImageUrl(int imageUrl){
        this.ImageUrl = imageUrl;
    }

}
