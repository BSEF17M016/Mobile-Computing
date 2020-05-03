package com.example.assignment2;

import android.app.Activity;
import  android.app.DownloadManager;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Picture;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.example.allahwasayaapp.Issues;
import com.squareup.picasso.Picasso;

import android.widget.TextView;


import androidx.annotation.NonNull;
import  androidx.recyclerview.widget.RecyclerView;


import java.util.List;




public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE=1;
    private final Context context;

    private String baseUrl="https://raw.githubusercontent.com/revolunet/PythonBooks/master/";
    // private String[] images={"img/cover_picking_python_version_manifesto.gif","img/cover_functional_programming_in_python.gif","img/cover_picking_python_version_manifesto.gif"};

    RequestOptions option;

    private final List<Object> listRecyclerItem;
    private Activity mActivity;
    private   DownloadManager downloadManager;


    public RecyclerAdapter(Context context, List<Object> listRecyclerItem,Activity activity,DownloadManager downloadManager) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
        this.mActivity=activity;
        this.downloadManager=downloadManager;
    }
    private static   class ItemViewHolder extends   RecyclerView.ViewHolder{
        private TextView title;
        private TextView author;
        private TextView authorUrl;
        private TextView level;
        private TextView info;
        private  TextView url;
        private ImageView imageView;
        private  Button downloadBtn;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.title);
            author=(TextView)itemView.findViewById(R.id.author);
            authorUrl=(TextView)itemView.findViewById(R.id.authorUrl);
            level=(TextView)itemView.findViewById(R.id.level);
            info=(TextView)itemView.findViewById(R.id.info);
            url=(TextView)itemView.findViewById(R.id.url);
            imageView=(ImageView)itemView.findViewById(R.id.cover);


        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch(i)
        {
            case TYPE:
            default:
                View layoutView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_row_item,viewGroup,false);
                return new ItemViewHolder(layoutView);
        }

    }

    @SuppressLint("ResourceType")
    @Override
    public  void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,int i){


        int viewType=getItemViewType(i);
        switch (viewType)
        {
            case TYPE:
            default:
                final ItemViewHolder itemViewHolder=(RecyclerAdapter.ItemViewHolder) viewHolder;
                Issues issues =(Issues) listRecyclerItem.get(i);

                itemViewHolder.title.setText(issues.getTitle());
                itemViewHolder.author.setText(issues.getAuthor());
                itemViewHolder.authorUrl.setText(issues.getAuthorUrl());
                itemViewHolder.level.setText(issues.getLevel());
                itemViewHolder.info.setText(issues.getInfo()+"------");
                itemViewHolder.url.setText(issues.getUrl());
                String coverString=issues.getCover();
                String threeLettersOfCover=coverString.substring(0,3);
                String baseUrlForLoad;
                if(threeLettersOfCover.equals("img"))
                {
                    baseUrlForLoad=baseUrl;
                }
                else
                {
                    baseUrlForLoad="";
                }




                Picasso.get().load(baseUrlForLoad+issues.getCover()).into(itemViewHolder.imageView);


        }

    }
    @Override
    public  int getItemCount(){
        return listRecyclerItem.size();
    }


}