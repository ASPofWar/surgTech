package com.sianware.surgtech;

import android.app.Fragment;
import android.os.Bundle;


public class TabInfo 
{
	String tag;
	Class<?> tabClass;
	Bundle args;
	Fragment fragment;
	
	TabInfo(String tab, Class<?> tabClass, Bundle args)
	{
		this.tag = tag;
		this.tabClass = tabClass;
		this.args = args;
	}

}
