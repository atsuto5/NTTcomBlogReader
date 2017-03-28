package com.example.atsuto5.ncomteqblog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.atsuto5.ncomteqblog.BackgroundTask.ThumbnailLoadTask;

/**
 * Created by Atsuto5 on 2017/03/20.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_PARAM = "page";
    private String mParam;
    private OnFragmentInteractionListener mListener;
    public static WebView webView;
    private RssAdapter mRssAdapter;
    private ListView mRssList;
    private MainActivity mMainActivity;
    private static final String URL_KEY = "URL";
    private static final String PACKAGE_NAME = "com.example.atsuto5.ncomteqblog";
    private static final String WebViewActivity_NAME = "com.example.atsuto5.ncomteqblog.WebViewActivity";
    private final String TAG = "OnItemSelected";


    // コンストラクタ
    public PlaceholderFragment() {
    }

    public static PlaceholderFragment newInstance(int page) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int page = getArguments().getInt(ARG_PARAM, 0);
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mMainActivity = (MainActivity) getActivity();
        mRssAdapter = new RssAdapter(mMainActivity, R.layout.rss_beans);
        mRssList = (ListView) view.findViewById(R.id.Rss_ListView);

        mRssList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                ItemBeans item = (ItemBeans) listView.getItemAtPosition(position);
                Intent webViewIntent = new Intent();
                webViewIntent.setClassName(PACKAGE_NAME,WebViewActivity_NAME);
                webViewIntent.putExtra(URL_KEY, "https://developer.ntt.com" + item.getLinkURL());
                Log.i(TAG, "onClick: " + item.getLinkURL());
                getActivity().startActivity(webViewIntent);
            }
        });


        ThumbnailLoadTask thumbnailLoadTask = new ThumbnailLoadTask(mRssList, mRssAdapter, mMainActivity);

        switch (page) {
            case 1 :
                thumbnailLoadTask.execute("https://developer.ntt.com/ja/blog/cat01");
                break;

            case 2 :
                thumbnailLoadTask.execute("https://developer.ntt.com/ja/blog/cat01?page=1");
                break;

            case 3 :
                thumbnailLoadTask.execute("https://developer.ntt.com/ja/blog/cat01?page=2");
                break;

            case 4 :
                thumbnailLoadTask.execute("https://developer.ntt.com/ja/blog/cat01?page=3");
                break;

            case 5 :
                thumbnailLoadTask.execute("https://developer.ntt.com/ja/blog/cat01?page=4");
                break;

            case 6 :
                thumbnailLoadTask.execute("https://developer.ntt.com/ja/blog/cat01?page=5");
                break;
        }
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
