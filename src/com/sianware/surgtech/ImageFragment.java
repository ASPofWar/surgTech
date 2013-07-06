package com.sianware.surgtech;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Aaron on 7/3/13.
 */
public class ImageFragment extends ListFragment
{

    public String currentDir;
    String[] assetDirs;
    InputStream imagesFiles = null;
    final ArrayList<HashMap<String,ImageView>> imageItems = new ArrayList<HashMap<String,ImageView>>();
    final ArrayList<String> directoryNames = new ArrayList<String>();
    final static String SECTION_NAME = "sn";
    SimpleAdapter imageAdapter;



    public ImageFragment(String dirName)
    {
        currentDir = dirName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.review_images, container, false);
        final Context context = inflater.getContext();



        //References item_section file and is used as a container for our section names based of directories.
        imageAdapter = new SimpleAdapter(context, imageItems, R.layout.instru_files,
                new String[]{SECTION_NAME}, new int[]{R.id.imageView});

        //points to the images in the folders

        AssetManager assetImages = context.getAssets();


        for(int i = 0; i < assetDirs.length; i++)
        {

            HashMap<String,ImageView> item = new HashMap<String,ImageView>();
            ImageView images = new ImageView(context);
            images.setId(i);

            String dirName = assetDirs[i];

            //Grab images from folder
            try{
            String[] imageFiles = assetImages.list("sections" + "/" + dirName);
            imagesFiles = context.getAssets().open("sections" + "/" + dirName + imageFiles[i]);
            Bitmap bit = BitmapFactory.decodeStream(imagesFiles);
            images.setImageBitmap(bit);
            Log.d("###############", imageFiles[i]);


            } catch (IOException e) {
                e.printStackTrace();
            }
            item.put(imagesFiles.toString(), images);
            imageItems.add(item);
            directoryNames.add(dirName);

        }
        setListAdapter(imageAdapter);
        imageAdapter.notifyDataSetChanged();

        return view;
    }
}






