package com.nguyenvantho.a42tho_tabhost_viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;

import com.nguyenvantho.a42tho_tabhost_viewpager.Adapter.ViewPagerAdapter;
import com.nguyenvantho.a42tho_tabhost_viewpager.Fragment.Fragment1;
import com.nguyenvantho.a42tho_tabhost_viewpager.Fragment.Fragment3;

public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {
    ViewPager viewPager;
    TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);
        ViewPagerAdapter adapter= new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabHost= this.<TabHost>findViewById(R.id.tabhost);
        tabHost.setup();
        TabHost.TabSpec tab1= tabHost.newTabSpec("Điều Khiển");
        tab1.setIndicator("Điều Khiển");
        tab1.setContent(new FakeContentTab(this));

        TabHost.TabSpec tab2= tabHost.newTabSpec("Biểu Đồ");
        tab2.setIndicator("Biểu Đồ");
        tab2.setContent(new FakeContentTab(this));



        tabHost.addTab(tab1);
        tabHost.addTab(tab2);

        tabHost.setOnTabChangedListener(this::onTabChanged);
        viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onTabChanged(String tabId) {
        int vitriTab= tabHost.getCurrentTab();
        viewPager.setCurrentItem(vitriTab);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tabHost.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public class FakeContentTab implements TabHost.TabContentFactory{
        Context context;
        public FakeContentTab(Context context)
        {
            this.context= context;
        }
        @Override
        public View createTabContent(String tag) {
            View view= new View(context);
            view.setMinimumHeight(0);
            view.setMinimumWidth(0);
            return view;
        }
    }
}