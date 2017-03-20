package com.example.atsuto5.ncomteqblog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Atsuto5 on 2017/02/11.
 */
public class RssAdapter extends ArrayAdapter<ItemBeans> {
    private LayoutInflater mInflater;
    private ArrayList<ItemBeans> itemList = new ArrayList<>();
    private String TAG = "RssAdapter";
    private static final String URL_KEY = "URL";
    private static final String PACKAGE_NAME = "com.example.atsuto5.yahoo_rss_reader";
    private static final String WebViewActivity_NAME = "com.example.atsuto5.yahoo_rss_reader.WebViewActivity";
    private Context mContext;

    static class ViewHolder{
        TextView titleText;
        Button urlButton;
        ImageView thumbNailView;
    }


    public RssAdapter(Context context, int id) {
        super(context, id);
        mContext = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


    public View getView(final int position, View view, final ViewGroup parent){

        final ViewHolder holder;

        if(view == null) {
            view = mInflater.inflate(R.layout.rss_beans, null);
            //ViewHolderを作成
            holder = new ViewHolder();
            holder.titleText = (TextView) view.findViewById(R.id.titleTextView);
            holder.urlButton = (Button) view.findViewById(R.id.urlButton);
            holder.thumbNailView = (ImageView) view.findViewById(R.id.thumbNailView);
            //holder.userLikeView = (ImageView) view.findViewById(R.id.userLikeView);
            view.setTag(holder);
        }else {
            holder = (ViewHolder)view.getTag();
        }

            final ItemBeans item = this.getItem(position);

            if(item != null){

                holder.titleText.setText(item.getTitle());
                holder.urlButton.setText(item.getUrl());
                holder.urlButton.setOnClickListener(new View.OnClickListener()  {
                    //URLをタップしたときWebViewActivityに遷移する
                    public void onClick(View v) {

                        Intent webViewIntent = new Intent();
                        webViewIntent.setClassName(PACKAGE_NAME,WebViewActivity_NAME);
                        webViewIntent.putExtra(URL_KEY, item.getUrl());
                        Log.i(TAG, "onClick: " + item.getUrl());
                        mContext.startActivity(webViewIntent);

                    }
                });

                holder.thumbNailView.setImageBitmap(item.getThumNail());
            }
        return view;
        }

}
