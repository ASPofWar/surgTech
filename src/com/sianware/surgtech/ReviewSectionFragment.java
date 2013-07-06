package com.sianware.surgtech;


import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class ReviewSectionFragment extends ListFragment
{
    MainActivity activity = null;

    SimpleAdapter listAdapter;
    final ArrayList<HashMap<String,String>> listItems = new ArrayList<HashMap<String,String>>();
    final ArrayList<String> directoryNames = new ArrayList<String>();
    final static String SECTION_NAME = "sn";
    String[] assetDirs;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
	{
        View view = inflater.inflate(R.layout.review_list, container, false);
        final Context context = inflater.getContext();

        //References item_section file and is used as a container for our section names based of directories.
        listAdapter = new SimpleAdapter(context, listItems, R.layout.item_section,
                new String[]{SECTION_NAME}, new int[]{R.id.sectionName});

        AssetManager assetManager = context.getAssets();
        //Establishes string to be referenced for our files.
        assetDirs = null;
        try {
            assetDirs = assetManager.list("sections");
        } catch (IOException e) {
            e.printStackTrace();
        }


        for(int i = 0; i < assetDirs.length; i++)
        {
            HashMap<String,String> item = new HashMap<String,String>();
            String dirName = assetDirs[i];

            String sectionName = "";
            try {
                sectionName += streamToString(context.getAssets().open("sections/" + dirName + "/manifest.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.d("###FILENAME###", sectionName);

            item.put(SECTION_NAME, sectionName);
            listItems.add(item);
            directoryNames.add(dirName);
        }

        setListAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
        return view;

    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id)
    {
        if(activity == null) return;
        activity.GotoImageFragment(directoryNames.get(position));
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        this.activity = (MainActivity)activity;
    }

    private static String streamToString(InputStream stream)
    {
        Scanner s = new Scanner(stream).useDelimiter("\\A");
        String toReturn = s.hasNext() ? s.next() : "";
        toReturn = toReturn.replace("\n","").replace("\n","");
        return toReturn;
    }
}
