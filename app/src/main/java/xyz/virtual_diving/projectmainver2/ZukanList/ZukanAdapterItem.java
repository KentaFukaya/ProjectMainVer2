package xyz.virtual_diving.projectmainver2.ZukanList;

/**
 * Created by b1014248 on 2016/06/17.
 */
public class ZukanAdapterItem {
    public int Id;
    public int icon;
    public String title;
    public String de;
    public String co;

    //セッター
    public void setId(int id){
        this.Id = id;
    }

    public void setIcon(int icon){
        this.icon = icon;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDe(String de){
        this.de = de;
    }

    public void setCo(String co){
        this.co = co;
    }

    //ゲッター
    public int getId(){return this.Id;
    }
    public int getIcon(){
        return this.icon;
    }

    public String getT(){
        return title;
    }
}
