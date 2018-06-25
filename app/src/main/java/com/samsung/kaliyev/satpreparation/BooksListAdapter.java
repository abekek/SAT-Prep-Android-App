package com.samsung.kaliyev.satpreparation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class BooksListAdapter extends RecyclerView.Adapter<BooksListAdapter.ViewHolder> {
    public List<Posts> postsList;
    public Context context;
    private int lastPosition = -1;

    public BooksListAdapter(Context context, List<Posts> postsList){

        this.postsList = postsList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nameText.setText(postsList.get(position).getName());
        holder.typeText.setText(postsList.get(position).getType());

        final String url = postsList.get(position).url;
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
                Intent listIntent = new Intent(Intent.ACTION_VIEW);
                listIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                listIntent.setData(Uri.parse(url));
                context.startActivity(listIntent);
            }
        });

        setAnimation(holder.itemView, position);
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public TextView nameText;
        public TextView typeText;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            nameText = (TextView) mView.findViewById(R.id.nameText);
            typeText = (TextView) mView.findViewById(R.id.typeText);
        }
    }
}
