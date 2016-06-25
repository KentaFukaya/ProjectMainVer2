package xyz.virtual_diving.projectmainver2.Image;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by b1014169 on 2016/06/26.
 */
public class BitmapAdapter extends ArrayAdapter<Bitmap> {
    private int resourceId;

    public BitmapAdapter(Context context, int resource, List<Bitmap> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resourceId, null);
        }

        ImageView view = (ImageView) convertView;
        // 画像をグリッド表示するサイズを指定
        view.setLayoutParams(new GridView.LayoutParams(340, 340));
        view.setImageBitmap(getItem(position));

        return view;
    }
}
