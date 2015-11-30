package com.example.viewpagerpractice;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnClickListener, OnPageChangeListener {

	private View first, second, third;
	
	private ViewPager viewPager;
	
	private MyViewPagerAdapter adapter;
	
	private List<View> list = new ArrayList<View>();
	
	private ImageView points[];
	
	private int currentIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        LayoutInflater inflater = getLayoutInflater();
        first = inflater.inflate(R.layout.first_image_item, null);
        second = inflater.inflate(R.layout.second_image_item, null);
        third = inflater.inflate(R.layout.third_image_item, null);
        
        list.add(first);
        list.add(second);
        list.add(third);
        
        adapter =  new MyViewPagerAdapter(list);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
        
        initPoints();
    }


    private void initPoints() {
    	LinearLayout linearLayout = (LinearLayout) findViewById(R.id.liner_layout);
    	points = new ImageView[list.size()];
    	for(int i = 0; i < list.size(); i++) {
    		points[i] = (ImageView) linearLayout.getChildAt(i);
    		points[i].setEnabled(true);
    		points[i].setOnClickListener(this);
    		points[i].setTag(i);
    	}
    	currentIndex = 0;
    	points[currentIndex].setEnabled(false);
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		int position = (Integer) v.getTag();
		setCurView(position);
		setCurDot(position);
	}


	private void setCurView(int position) {
		if(position < 0 || position >= list.size()){
			return;
		}
		viewPager.setCurrentItem(position);
	}


	private void setCurDot(int position) {
		if(position < 0 || position > list.size() - 1 || currentIndex == position) {
			return;
		}
		points[position].setEnabled(false);
		points[currentIndex].setEnabled(true);
		currentIndex = position;
	}


	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPageSelected(int position) {
		setCurDot(position);
	}
    
}
