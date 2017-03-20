package com.example.atsuto5.ncomteqblog;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,
        PlaceholderFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        // xmlからTabLayoutの取得
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        // xmlからViewPagerを取得
        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        // ページタイトル配列
        //final String[] pageTitle = {"HOME", "EVENT", "SETTING"};

        // 表示Pageに必要な項目を設定
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return PlaceholderFragment.newInstance(position + 1);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                //return pageTitle[position];
                return "Page" + " - " + (position + 1) + " - " ;
            }

            @Override
            public int getCount() {
                return 6;
            }
        };

        // ViewPagerにアダプターをセット
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);

        // ViewPagerをTabLayoutにセット
        tabLayout.setupWithViewPager(viewPager);
    }

//    //ActionBarにメニューをセットする。
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    //メニューの動作を定義する。
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    //バックキー押下時、WebView内でページバックする
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //戻るページがある場合
        if (event.getAction() == KeyEvent.ACTION_DOWN
                && keyCode == KeyEvent.KEYCODE_BACK
                && PlaceholderFragment.webView.canGoBack() == true) {
            PlaceholderFragment.webView.goBack();
            return true;
        }
        //トップページの場合
        else {
//            finish();
          return true;
        }
    }
}
