package com.sianware.surgtech;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

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

    String[] imageRes;
    InputStream imagesFiles = null;
    final ArrayList<HashMap<String,Drawable>> listItems = new ArrayList<HashMap<String,Drawable>>();
    // final ArrayList<String> directoryNames = new ArrayList<String>(); //Nothing for now, eventually hold the description for each image
    final static String IMAGE_RESOURCE = "ir";
    SimpleAdapter imageAdapter;

    public ImageFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,

            Bundle savedInstanceState)
    {
        Bundle bundle = this.getArguments();
        String sectionDirectory = bundle.getString(MainActivity.SELECTED_REVIEW_SECTION);


        View view = inflater.inflate(R.layout.review_images, container, false);
        final Context context = inflater.getContext();

        //References item_section file and is used as a container for our section names based of directories.
        imageAdapter = new SimpleAdapter(context, listItems, R.layout.instru_files,
                new String[]{IMAGE_RESOURCE}, new int[]{R.id.imageView});

        AssetManager assetManager = context.getAssets();
        //Establishes string to be referenced for our files.
        imageRes = null;
        try {
            imageRes = assetManager.list("sections/" + sectionDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < imageRes.length; i++)
        {
            Log.d("imageRes",imageRes[i]);
        }

        for(int i = 0; i < imageRes.length; i++)
        {
            HashMap<String,Drawable> item = new HashMap<String,Drawable>();
            Drawable d = null;
            Log.w("####Full Path####",context.getFilesDir() + "/sections/" + sectionDirectory + "/" + imageRes[i]);
            //Drawable d = Drawable.createFromPath(context.getAssets()+ "/sections/" + sectionDirectory + "/" + imageRes);
            try {
                d = Drawable.createFromStream(context.getAssets().open(context.getFilesDir() + "/sections/" + sectionDirectory + "/" + imageRes[i]), null);
            } catch(IOException e){
                e.printStackTrace();
            }

//            String sectionName = "";
//            try {
//                sectionName += streamToString(context.getAssets().open("sections/" + dirName + "/manifest.txt"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            item.put(IMAGE_RESOURCE, d);
            listItems.add(item);
            //directoryNames.add(dirName);
        }

        setListAdapter(imageAdapter);
        imageAdapter.notifyDataSetChanged();


//
//        //References item_section file and is used as a container for our section names based of directories.
//        imageAdapter = new SimpleAdapter(context, imageItems, R.layout.instru_files,
//                new String[]{SECTION_NAME}, new int[]{R.id.imageView});
//
//        //points to the images in the folders
//
//        AssetManager assetImages = context.getAssets();
//
//
//        for(int i = 0; i < assetDirs.length; i++)
//        {
//
//            HashMap<String,ImageView> item = new HashMap<String,ImageView>();
//            ImageView images = new ImageView(context);
//            images.setId(i);
//
//            String dirName = assetDirs[i];
//
//            //Grab images from folder
//            try{
//            String[] imageFiles = assetImages.list("sections" + "/" + dirName);
//            imagesFiles = context.getAssets().open("sections" + "/" + dirName + imageFiles[i]);
//            Bitmap bit = BitmapFactory.decodeStream(imagesFiles);
//            images.setImageBitmap(bit);
//            Log.d("###############", imageFiles[i]);
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            item.put(imagesFiles.toString(), images);
//            imageItems.add(item);
//            directoryNames.add(dirName);
//
//        }
//        setListAdapter(imageAdapter);
//        imageAdapter.notifyDataSetChanged();

        return view;
    }

//    private static String streamToString(InputStream stream)
//    {
//        Scanner s = new Scanner(stream).useDelimiter("\\A");
//        String toReturn = s.hasNext() ? s.next() : "";
//        toReturn = toReturn.replace("\n","").replace("\n","");
//        return toReturn;
//    }

}






