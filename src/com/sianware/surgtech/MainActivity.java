package com.sianware.surgtech;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends FragmentActivity {
	
	//Variable for viewPager
	ViewPager viewPager;
	HashMap<String, TabInfo> tabInfoMap = new HashMap<String, TabInfo>();
	
	//Listener for User input
	final OnPageChangeListener pageChangeListener = new OnPageChangeListener()
	{

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageSelected(int index)
		{
			//tabHost.setCurrentTab(index);
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
		
		viewPager = (ViewPager)findViewById(R.id.pager);
		viewPager.setAdapter(pagerAdapter);
		viewPager.setOffscreenPageLimit(2);
		viewPager.setCurrentItem(0);
		
		viewPager.setOnPageChangeListener(pageChangeListener);

        final ArrayList<String> list = new ArrayList<String>();


        final ListView listview = (ListView)findViewById(R.id.listView1);
        String[] values = new String[]
                {
                        "Cardiovascular Instr.",
                        "Clamping Instr.",
                        "Dressings",
                        "Endo Instr.",
                        "ENT Instr.",
                        "Forceps and Graspers",
                        "Needles",
                        "Neuro Instr.",
                        "OB/GYN Instr.",
                        "Ortho 1",
                        "Ortho 2",
                        "Ortho 3",
                        "Ortho 4",
                        "Plastics Instr.",
                        "Retractors",
                        "Staplers",
                        "Supplemental Instr.",
                        "Supplies",
                        "Urology Instr.",
                };


        //Populate List View
        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);



        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }


		}

    //Sets up array functions
    private class StableArrayAdapter extends ArrayAdapter<String>
    {
        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }
        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }


    }





        @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
