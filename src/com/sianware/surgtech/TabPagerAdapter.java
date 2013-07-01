package com.sianware.surgtech;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter{
	public final ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	public final ArrayList<CharSequence> tabTitles = new ArrayList<CharSequence>();
	
	public TabPagerAdapter(FragmentManager fragmentManager) 
	{
		super(fragmentManager);
	} 
	
	@Override
	public int getCount()
	{
		return fragments.size();
	}

	@Override
	public Fragment getItem(int index)
	{
		return fragments.get(index);
	}
	
	@Override
	public CharSequence getPageTitle(int index)
	{
		return tabTitles.get(index);		
	}
	
	public void addFragment(Fragment fragment, CharSequence tabTitle)
	{
		fragments.add(fragment);
		tabTitles.add(tabTitle);
		notifyDataSetChanged();
	}
}
