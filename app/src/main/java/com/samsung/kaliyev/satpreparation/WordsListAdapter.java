package com.samsung.kaliyev.satpreparation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.List;

public class WordsListAdapter extends RecyclerView.Adapter<WordsListAdapter.ViewHolder> {
    public List<Words> wordsList;
    public Context context;
    private int lastPosition = -1;

    public WordsListAdapter(Context context, List<Words> wordsList){

        this.wordsList = wordsList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.nameText.setText(wordsList.get(position).getName());
        holder.typeText.setText(wordsList.get(position).getType());

        final String name = wordsList.get(position).name;
        final String type = wordsList.get(position).type;
        final String description = wordsList.get(position).description;
        final String translation = wordsList.get(position).translation;
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listIntent = new Intent(context, WordInfo.class);
                listIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                listIntent.putExtra("name", name);
                listIntent.putExtra("type", type);
                listIntent.putExtra("description", description);
                listIntent.putExtra("translation", translation);
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
        return wordsList.size();
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

