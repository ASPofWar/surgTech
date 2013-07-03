package com.sianware.surgtech;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;

import java.util.HashMap;


public class MainActivity extends FragmentActivity {
	
	//Variable for viewPager
	SomewhatSwipeableViewPager viewPager;
	HashMap<String, TabInfo> tabInfoMap = new HashMap<String, TabInfo>();
	
	//Listener for User input
	final OnPageChangeListener pageChangeListener = new OnPageChangeListener()
	{

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int index)
		{
		}
		
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        //Create Fragment for reviewList
		Fragment reviewFragment = new ReviewSectionFragment();
		Fragment quizFragment = new QuizFragment();
		
		
		TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
		pagerAdapter.addFragment(reviewFragment, "Review");
		pagerAdapter.addFragment(quizFragment, "Quizs");
		
		viewPager = (SomewhatSwipeableViewPager)findViewById(R.id.pager);
		viewPager.setAdapter(pagerAdapter);
		viewPager.setOffscreenPageLimit(2);
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(pageChangeListener);



    }





        @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
