package com.nguyenvantho.a42tho_tabhost_viewpager.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.nguyenvantho.a42tho_tabhost_viewpager.Fragment.FragmentDangKi;
import com.nguyenvantho.a42tho_tabhost_viewpager.Fragment.FragmentDangNhap;


public class ViewpagerAdapterDangNhap extends FragmentPagerAdapter {
    public ViewpagerAdapterDangNhap(@NonNull FragmentManager fm) {
        super(fm);
    }

    public ViewpagerAdapterDangNhap(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
            {
                FragmentDangNhap fragmentDangNhap= new FragmentDangNhap();
                return fragmentDangNhap;
            }
            case 1:
            {
                FragmentDangKi fragmentDangKi= new FragmentDangKi();
                return fragmentDangKi;
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
            {
                return "Đăng Nhập";
            }
            case 1:
            {

                return "Đăng Ký";
            }
        }
        return null;
    }
}
