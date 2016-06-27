package xyz.virtual_diving.projectmainver2.Image;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import xyz.virtual_diving.projectmainver2.R;

/**
 * Created by b1014157 on 2016/06/15.
 */
public class ImageSubActivity extends Activity {
    private ViewPager viewPager;
    List<String> comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_sub);

        this.comment = CommentGenerator.createCommentData(getApplication());
        Intent intent = getIntent();
        //gridviewページのページ番号を取得
        int pageNumber = intent.getIntExtra("PageNumber", 0);
        ArrayList<Bitmap> list = new ArrayList<>(ImageActivity.getList());

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        PagerAdapter pagerAdapter = new Adapter(this, comment, list);
        viewPager.setOnPageChangeListener(new PageChangeListener());
        viewPager.setAdapter(pagerAdapter);
        //最初に表示するページの設定
        viewPager.setCurrentItem(pageNumber);

        View.OnClickListener returnButtonListener
                = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageActivity.page = viewPager.getCurrentItem();
                Log.d("aaaaaaaa", "onClick:"+ ImageActivity.page);
                finish();
            }
        };
        findViewById(R.id.returnButton).setOnClickListener(returnButtonListener);
    }

    class PageChangeListener extends ViewPager.SimpleOnPageChangeListener {
        @Override
        public void onPageSelected(int position) {
            // Page change Operation!
        }
    }
}