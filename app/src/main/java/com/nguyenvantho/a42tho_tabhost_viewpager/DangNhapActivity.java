package com.nguyenvantho.a42tho_tabhost_viewpager;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.nguyenvantho.a42tho_tabhost_viewpager.Adapter.ViewpagerAdapterDangNhap;


public class DangNhapActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbarDangNhap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap_layout);

        tabLayout= this.<TabLayout>findViewById(R.id.tabdangnhap);
        viewPager= this.<ViewPager>findViewById(R.id.viewpagerdangnhap);
        toolbarDangNhap= this.<Toolbar>findViewById(R.id.toolbarDangNhap);

        setSupportActionBar(toolbarDangNhap);

        ViewpagerAdapterDangNhap viewpagerAdapterDangNhap= new ViewpagerAdapterDangNhap(getSupportFragmentManager());
        viewPager.setAdapter(viewpagerAdapterDangNhap);
        viewpagerAdapterDangNhap.notifyDataSetChanged();

        tabLayout.setupWithViewPager(viewPager);
    }
}
