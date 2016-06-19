package com.example.implementingpaging;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyPageAdapter extends PagerAdapter {

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int resId = 0;
        switch (position) {
            case 0:
                resId = R.layout.page1;
                break;
            case 1:
                resId = R.layout.page2;
                break;
            case 2:
                resId = R.layout.page3;
                break;
        }

        View view = inflater.inflate(resId, null);
        ((ViewPager) container).addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
