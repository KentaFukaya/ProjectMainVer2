package xyz.virtual_diving.projectmainver2.Image;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import xyz.virtual_diving.projectmainver2.R;

/**
 * Created by b1014157 on 2016/06/15.
 */
public class Adapter extends PagerAdapter {
    LayoutInflater _inflater = null;

    List<String> comment;
    List<Bitmap> ImageList;

    private TextView commentView;
    private ImageView charView;

    public Adapter(Context context, List<String> comment, List<Bitmap> ImageList)
    {
        super();
        _inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.comment = comment;
        this.ImageList = ImageList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        LinearLayout layout = (LinearLayout)_inflater.inflate(R.layout.image_card, null);

        // position のコメントを textview にセット
        commentView = (TextView)layout.findViewById(R.id.word);
        commentView.setText(comment.get(position));
        // position の画像を imageview にセット
        charView = (ImageView)layout.findViewById(R.id.img);
        charView.setImageBitmap(ImageList.get(position));

        container.addView(layout);

        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public int getCount()
    {
        return ImageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj)
    {
        return view.equals(obj);
    }
}
