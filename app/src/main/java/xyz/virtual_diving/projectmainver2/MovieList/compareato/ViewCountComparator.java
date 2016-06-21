package xyz.virtual_diving.projectmainver2.MovieList.compareato;



import java.util.Comparator;

import xyz.virtual_diving.projectmainver2.MovieList.ListItem;

/**
 * Created by b1014100 on 2016/06/07.
 */
public class ViewCountComparator implements Comparator<ListItem> {
    //比較メソッド（データクラスを比較して-1, 0, 1を返すように記述する）
    public int compare(ListItem a,ListItem b) {
        int id1 = a.getViewCount();
        int id2 = b.getViewCount();

        //こうすると社員番号の昇順でソートされる
        if (id1 > id2) {
            return 1;
        }else if(id1 == id2){
            return 0;
    }else {
            return -1;

        }
    }
}
