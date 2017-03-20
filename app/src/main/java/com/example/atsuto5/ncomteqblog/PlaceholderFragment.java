package com.example.atsuto5.ncomteqblog;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

/**
 * Created by Atsuto5 on 2017/03/20.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_PARAM = "page";
    private String mParam;
    private OnFragmentInteractionListener mListener;
    public static WebView webView;

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
        webView = (WebView)view.findViewById(R.id.textView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                return false;
            }
        });

        switch (page) {
            case 1 :
                webView.loadUrl("https://developer.ntt.com/ja/blog/cat01");
                break;

            case 2 :
                webView.loadUrl("https://developer.ntt.com/ja/blog/cat01?page=1");
                break;

            case 3 :
                webView.loadUrl("https://developer.ntt.com/ja/blog/cat01?page=2");
                break;

            case 4 :
                webView.loadUrl("https://developer.ntt.com/ja/blog/cat01?page=3");
                break;

            case 5 :
                webView.loadUrl("https://developer.ntt.com/ja/blog/cat01?page=4");
                break;

            case 6 :
                webView.loadUrl("https://developer.ntt.com/ja/blog/cat01?page=5");
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

    public WebView getWebView () {
        return webView;
    }
}
