package com.example.aabcs_trading;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Context context;
    ArrayList<ArticleData> articles;



    public MyAdapter ( Context context, ArrayList<ArticleData> articles){
        this.context = context;
        this.articles = articles;
    }
    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("ArticleViewList", "OnCreateViewHolder");
        View articleHolder = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false);
        return new ViewHolder(articleHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("ArticleViewList", "OnBindViewHolder" + position);

        ArticleData art = articles.get(position);
        String url = art.getURL();
        holder.textView1.setText(art.name);
        holder.textView2.setText(art.Author);
        holder.textView3.setText(art.URL);


        holder.textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout ArticleDesign;
        TextView textView1, textView2, textView3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ArticleDesign = itemView.findViewById(R.id.BaseArticle);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            textView3.setMovementMethod(LinkMovementMethod.getInstance());
        }

    }
}
