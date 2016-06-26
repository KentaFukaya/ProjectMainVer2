package xyz.virtual_diving.projectmainver2.Image;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import com.nifty.cloud.mb.core.FindCallback;
import com.nifty.cloud.mb.core.NCMB;
import com.nifty.cloud.mb.core.NCMBException;
import com.nifty.cloud.mb.core.NCMBObject;
import com.nifty.cloud.mb.core.NCMBQuery;

/**
 * Created by b1014157 on 2016/06/22.
 */
public class CommentGenerator {
    private static ArrayList<String> comment = new ArrayList<String>();

    public static ArrayList<String> createCommentData(Application app)
    {
        //mBaaS からコメントを取得
        NCMB.initialize(app, "27c2662e293b45e56ef0fcc76c8d95e4b6c982797bd6cb69630e306acdf2b42d", "3126fe3cd100209a5ff79ea8398b6caae05604d90eda2ef68ed161542bf33b25");
        NCMBQuery<NCMBObject> query = new NCMBQuery<>("ScreenShot");
        query.findInBackground(new FindCallback<NCMBObject>() {
            @Override
            public void done(List<NCMBObject> results, NCMBException e) {
                for(NCMBObject result: results){
                    comment.add(result.getString("Comment"));
                }
            }
        });

        return comment;
    }
}
