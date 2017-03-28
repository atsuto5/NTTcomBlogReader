package com.example.atsuto5.ncomteqblog;

import android.graphics.Bitmap;

/**
 * Created by Atsuto5 on 2017/02/11.
 */
public class ItemBeans {
    private String mTitle;
    private String mSubTitle;
    private String mThumbNailUrl;
    private Bitmap mThumbNail;
    private String mLinkURL;


    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setSubTitle(String subTitle) {
        this.mSubTitle = subTitle;
    }

    public void setThumbNailUrl(String thumbNailUrl) {
        this.mThumbNailUrl = thumbNailUrl;
    }

    public void setThumbNail(Bitmap thumbNail){
        this.mThumbNail = thumbNail;
    }

    public void setLinkURL(String linkURL) {
        this.mLinkURL = linkURL;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getSubTitle() {
        return this.mSubTitle;
        }

    public String getThumbNailUrl() {
        return this.mThumbNailUrl;
    }

    public Bitmap getThumNail(){
        return this.mThumbNail;
    }

    public String getLinkURL() {
        return this.mLinkURL;
    }

}
