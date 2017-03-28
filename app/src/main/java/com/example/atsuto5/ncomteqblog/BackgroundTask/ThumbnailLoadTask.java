package com.example.atsuto5.ncomteqblog.BackgroundTask;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ListView;

import com.example.atsuto5.ncomteqblog.ItemBeans;
import com.example.atsuto5.ncomteqblog.RssAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Atsuto5 on 2017/03/12.
 */
public class ThumbnailLoadTask extends AsyncTask <String, Void, ArrayList<ItemBeans>> {

    private Document doc = null;
    private final String TAG = "ThumbnailLoadTask";
    private ListView mRssListView;
    private RssAdapter mRssAdapter;
    private Activity mActivity;
    private ProgressDialog mLoadingDialog;
    private ArrayList<ItemBeans> mItemList;

    public ThumbnailLoadTask(ListView listView, RssAdapter rssAdapter, Activity activity) {
        this.mRssListView = listView;
        this.mRssAdapter = rssAdapter;
        this.mActivity = activity;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        //アダプターをリセットする。
        mRssAdapter.clear();
        mLoadingDialog = new ProgressDialog(mActivity);
        mLoadingDialog.setMessage("ロード中です...");
        mLoadingDialog.setCancelable(false);
        mLoadingDialog.show();


    }

    @Override
    protected ArrayList<ItemBeans> doInBackground(String... params) {

            try {

                doc = Jsoup.connect(params[0]).get();

            } catch (IOException e) {
                e.printStackTrace();
            }

        mItemList = new ArrayList<>();

        //タイトル取得
        Elements titleElements = doc.select(".group-header  [property=dc:title] a");
//            for (int i = 0; i < titleElements.size(); i++ ){
//                ItemBeans item = new ItemBeans();
//                mItemList.add(item);
//                mItemList.get(i).setTitle(titleElements.text());
//                Log.i(TAG, "doInBackground:タイトル取得 " + titleElements.text());
//            }

            for (Element element : titleElements) {
                ItemBeans item = new ItemBeans();
                item.setTitle(element.text());
                mItemList.add(item);
                Log.i(TAG, "doInBackground:タイトル取得 " + element.text());
            }

        //説明文取得
        Elements subTitleElements = doc.select(".group-header .field__items [property=content:encoded]");

        int i = 0;
        for (Element element : subTitleElements) {
                mItemList.get(i).setSubTitle(element.text());
                i++;
                Log.i(TAG, "doInBackground:説明文取得 " + element.text());
        }
//            for (i = 0; i < subTitleElements.size(); i++ ){
//                mItemList.get(i).setSubTitle(subTitleElements.text());
//                subTitleElements.next();
//                Log.i(TAG, "doInBackground:説明文取得 " + subTitleElements.text());
//            }

        //リンクURL取得
        Elements linkElements = doc.select(".group-header  [property=dc:title] a");
        i = 0;
        for (Element element : linkElements) {
            mItemList.get(i).setLinkURL(element.attr("href"));
            i++;
            Log.i(TAG, "doInBackground:説明文取得 " + element.attr("href"));
        }

//        for (i = 0; i < linkElements.size(); i++ ){
//            mItemList.get(i).setLinkURL(linkElements.attr("href"));
//            Log.i(TAG, "doInBackground:リンクURL " + linkElements.attr("href"));
//        }

        //画像URL取得
        Elements imageURLElements = doc.select(".group-header img");
        i = 0;
        for (Element element : imageURLElements) {
            mItemList.get(i).setThumbNailUrl(element.attr("src"));
            i++;
            Log.i(TAG, "doInBackground:説明文取得 " + element.attr("src"));
        }

//        for (i = 0; i < linkElements.size(); i++ ){
//            mItemList.get(i).setThumbNailUrl(imageURLElements.attr("src"));
//            Log.i(TAG, "doInBackground:画像URL " + imageURLElements.attr("src"));
//        }

        return mItemList;
    }

    @Override
    protected void onPostExecute(ArrayList<ItemBeans> itemList) {
        super.onPostExecute(itemList);

        BitmapSetTask bitmapSetTask = new BitmapSetTask(mRssListView, mRssAdapter, mActivity, mLoadingDialog);
        bitmapSetTask.execute(itemList);

    }
}
