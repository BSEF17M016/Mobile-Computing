package com.example.assignment2;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Object> viewItems=new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private  RecyclerView.LayoutManager layoutManager;
    public DownloadManager downloadManager;
    private  static  final  String TAG="MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView=(RecyclerView)findViewById(R.id.my_recycler_view);
        //  mRecyclerView.setHasFixedSize(false);
        layoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        addItemsFromJSON();
        mAdapter=new RecyclerAdapter(this,viewItems,this,downloadManager);
        mRecyclerView.setAdapter(mAdapter);

    }


    private void addItemsFromJSON() {
        try {
            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject itemObject = jsonArray.getJSONObject(i);
                String title = itemObject.getString("title");
                String author = itemObject.getString("author");
                String authorUrl = itemObject.getString("authorUrl");
                String level = itemObject.getString("level");
                String info = itemObject.getString("info");
                String url = itemObject.getString("url");
                String cover=itemObject.getString("cover");
                Issues issues = new Issues(title, author,authorUrl,level,info,url,cover);
                viewItems.add(issues);


            }
        } catch (JSONException | IOException e) {
            Log.d(TAG, "addItemsFromJSON ", e);
        }
    }

    private String readJSONDataFromFile() throws IOException
    {
        InputStream inputStream=null;
        StringBuilder builder=new StringBuilder();
        try{
            String jsonString=null;
            inputStream=getResources().openRawResource(R.raw.issues);
            BufferedReader bufferedReader=new BufferedReader(
                    new InputStreamReader(inputStream,"UTF-8"));
            while((jsonString=bufferedReader.readLine())!=null)
            {
                builder.append(jsonString);
            }
        }finally {
            if(inputStream!=null)
            {
                inputStream.close();
            }
            return new String(builder);

        }

    }
}

