package xyz.virtual_diving.projectmainver2.MovieList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import xyz.virtual_diving.projectmainver2.R;

/**
 * Created by b1014100 on 2016/06/02.
 */
public class ImageArrayAdapter extends ArrayAdapter<ListItem> {

    private int resouceId;
    private List<ListItem> items;
    private LayoutInflater inflater;
    /*コンストラクタ*/

    public ImageArrayAdapter(Context context, int resouceId, List<ListItem> items){
        super(context,resouceId,items);
        this.resouceId = resouceId;
        this.items = items;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    /*スクロールしたとき用のやつ*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view;
        if(convertView  != null) {
            view = convertView;
        }else{
            view = this.inflater.inflate(this.resouceId, null);
        }

        ListItem item = this.items.get(position);

        //タイトルをセット
        TextView appTitleText = (TextView) view.findViewById(R.id.MovieList_titletext);
        appTitleText.setText(item.getTitleText());
        //本文をセット
        TextView appContentsText = (TextView) view.findViewById(R.id.MovieList_contentstext);
        appContentsText.setText(item.getContentsText());
        //画像をセット
        ImageView appInfoImage = (ImageView) view.findViewById(R.id.MovieList_itemimage);
        //appInfoImage.setImageResource(R.drawable.zukanlist_sakana0+item.getImageId());
        appInfoImage.setImageResource(R.drawable.zukanlist_sakana0);

        //再生数のセット
        TextView appViewCountText = (TextView) view.findViewById(R.id.MovieList_viewconut);
        appViewCountText.setText("再生数:"+item.getViewCount());

        return view;
    }


}

