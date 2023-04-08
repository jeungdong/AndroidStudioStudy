package com.example.exam2;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.MyViewHolder> {



    private List<Items> itemsList;

    Context mContext;



    WordListAdapter(Context cxt, List<Items> mItemList){

        mContext = cxt;

        this.itemsList = mItemList;

    }



    @Override

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_row,parent,false);

        return new MyViewHolder(view);

    }



    @Override

    public void onBindViewHolder(MyViewHolder holder, int position) {



        final Items item = itemsList.get(position);



        // Drawable d = mContext.getDrawable(item.getImageID());
        Drawable d = mContext.getDrawable(item.getImageID());
        holder.img.setImageDrawable(d);

        holder.name.setText(item.getTitle()); //



    }



    @Override

    public int getItemCount() {

        return itemsList.size();

    }



    class MyViewHolder extends RecyclerView.ViewHolder

            implements View.OnClickListener {



        public TextView name;

        public ImageView img;


        public MyViewHolder(View itemView) {

            super(itemView);

            name = itemView.findViewById(R.id.Name); //텍스트전체

            img = itemView.findViewById(R.id.image); //이미지뷰

            //itemView.setOnClickListener(this); 아이템 전체에 대한 리스너

            name.setOnClickListener(this);

            img.setOnClickListener(this);


        }



        @Override

        public void onClick(View view) {

            int mPosition = getLayoutPosition();
            switch ( view.getId() ) {
                case R.id.Name: // ss = "name";
                    // search method 1
                    Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                    intent.putExtra(SearchManager.QUERY, itemsList.get(mPosition).getTitle()); // query contains search string
                    // search method 2
                    try {
                        String eQuery = URLEncoder.encode(itemsList.get(mPosition).getTitle(), "UTF-8");
                        Uri uri = Uri.parse("http://www.google.com/search?q=" + eQuery);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    mContext.startActivity(intent);
                    break;

                case R.id.image: //) ss = "image ";
                    Intent sintent=new Intent(mContext,ImageActivity.class);
                    sintent.putExtra("image", itemsList.get(mPosition).getImageID2());
                    sintent.putExtra("mydata", "if you need more");

                    mContext.startActivity(sintent);
                    break;
            }


        }

    }

}
