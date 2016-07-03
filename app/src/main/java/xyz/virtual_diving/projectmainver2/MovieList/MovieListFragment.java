package xyz.virtual_diving.projectmainver2.MovieList;

/**
 * Created by b1014100 on 2016/06/06.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import java.util.Collections;
import java.util.List;

import xyz.virtual_diving.projectmainver2.MovieList.compareato.IdComparator;
import xyz.virtual_diving.projectmainver2.MovieList.compareato.ViewCountComparator;
import xyz.virtual_diving.projectmainver2.R;
import xyz.virtual_diving.projectmainver2.panframe.SimplePlayerActivity;

public class MovieListFragment extends Fragment {
    private ListView lv;
    private List<ListItem> list0 = MovieListActivity.list0;
    private List<ListItem> list1 = MovieListActivity.list1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movielist_fragment, null);
        ImageArrayAdapter adapter;
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        int page = bundle.getInt("page", 0);
        lv = (ListView) view.findViewById(R.id.MovieList_listview);

        //0ページ目ならID順にソート
        //1ページ目ならVIEWOCNt順にソート
        if (page == 0) {
            adapter = new ImageArrayAdapter(getActivity(), R.layout.movielist_listitem, list0);
            lv.setAdapter(adapter);
        }
        if (page == 1) {
            Collections.sort(list1, new ViewCountComparator());
            adapter = new ImageArrayAdapter(getActivity(), R.layout.movielist_listitem, list1);
            lv.setAdapter(adapter);
        }


        /*クリックリスナーの設置*/
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String msg = position + "番目のアイテムがクリックされました";
                Intent intent = new Intent(getActivity(), SimplePlayerActivity.class);
                //Toast.makeText(getActivity(), getMovieURLbyId(list1, position), Toast.LENGTH_LONG).show();
                //Toast.makeText(getActivity(), getMovieURLbyId(list0, position), Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
        return view;
    }

    /*
*convert MovieURL from  position
*/
    public String getMovieURLbyId(List<ListItem> list, int position) {
        for (int n = 0; n < list.size(); n++) {
            if (list.get(n).getId() == list.get(position).getId()) return list.get(n).getMovieURL();
        }
        return null;
    }

    /*
     *get Listitem contents by log
     */
    public static void AllLog(ListItem item) {
        Log.d("results", "id=" + item.getId());
        Log.d("results", "Titletext=" + item.getTitleText());
        Log.d("results", "Contentstext=" + item.getContentsText());
        Log.d("results", "viewcount=" + item.getViewCount());
        Log.d("results", "iamgeid=" + item.getImageId());
        Log.d("results", "MovieURL=" + item.getMovieURL());
    }

}
