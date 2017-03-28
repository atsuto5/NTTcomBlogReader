package com.example.atsuto5.ncomteqblog.BackgroundTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.atsuto5.ncomteqblog.ItemBeans;
import com.example.atsuto5.ncomteqblog.R;
import com.example.atsuto5.ncomteqblog.RssAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Atsuto5 on 2017/03/12.
 */
public class BitmapSetTask extends AsyncTask <ArrayList<ItemBeans>, Void, ArrayList<ItemBeans>> {

    private Bitmap bit = null;
    private final String TAG = "BitmapSetTask";
    private ListView mRssListView;
    private RssAdapter mRssAdapter;
    private Activity mActivity;
    private ProgressDialog mLoadingDialog;
    private static final String URL_KEY = "URL";
    private static final String PACKAGE_NAME = "com.example.atsuto5.ncomteqblog";
    private static final String WebViewActivity_NAME = "com.example.atsuto5.ncomteqblog.WebViewActivity";

    public BitmapSetTask(ListView listView, RssAdapter rssAdapter, Activity activity, ProgressDialog progressDialog) {
        this.mRssListView = listView;
        this.mRssAdapter = rssAdapter;
        this.mActivity = activity;
        this.mLoadingDialog = progressDialog;
    }



    @Override
    protected ArrayList<ItemBeans> doInBackground(ArrayList<ItemBeans>... params) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;

        ArrayList<ItemBeans> itemList = params[0];

        for(int i = 0; itemList.size()>i;i++) {
            ItemBeans item = (ItemBeans) itemList.get(i);

            try {
                URL url = new URL(item.getThumbNailUrl());
                Log.i(TAG, "doInBackground: " + item.getThumbNailUrl());
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                InputStream in = con.getInputStream();
                bit = BitmapFactory.decodeStream(in, null, options);
                in.close();

                item.setThumbNail(bit);
                Log.i(TAG, "doInBackground: Complete");
                //itemList.set(i,item);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return itemList;
    }

    @Override
    protected void onPostExecute(ArrayList<ItemBeans> itemList) {
        super.onPostExecute(itemList);

        for(int i = 0; i < itemList.size();i++){
            mRssAdapter.add((ItemBeans) itemList.get(i));
        }
        mRssListView.setAdapter(mRssAdapter);
        mLoadingDialog.dismiss();

    }

    private Bitmap getPreResizedImage(byte[] imageData, int viewHeight, int viewWidth){

        // Optionsインスタンスを取得
        BitmapFactory.Options options = new BitmapFactory.Options();

        // Bitmapを生成せずにサイズを取得する
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(imageData, 0, imageData.length, options);

        if(viewHeight == 0 || viewWidth == 0) {
            // 等倍（リサイズしない）
            options.inSampleSize = 1;
            }else{
            // 設定するImageViewのサイズにあわせてリサイズ
            options.inSampleSize = Math.max(options.outHeight / viewHeight, options.outWidth / viewWidth);
            }
        // 実際にBitmapを生成する
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(imageData, 0, imageData.length, options);
    }
}
