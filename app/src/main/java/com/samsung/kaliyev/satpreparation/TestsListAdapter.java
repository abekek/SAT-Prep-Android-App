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

public class TestsListAdapter extends RecyclerView.Adapter<TestsListAdapter.ViewHolder> {
    public List<Tests> testsList;
    public Context context;
    private int lastPosition = -1;

    public TestsListAdapter(Context context, List<Tests> testsList){

        this.testsList = testsList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nameText.setText(testsList.get(position).getName());
        holder.typeText.setText(testsList.get(position).getType());

        final String name = testsList.get(position).name;
        final String type = testsList.get(position).type;
        final String test_url = testsList.get(position).test_url;
        final String essay_url = testsList.get(position).essay_url;
        final String answers_url = testsList.get(position).answ_url;
        final String explanations_url = testsList.get(position).exp_url;
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listIntent = new Intent(context, Download.class);
                listIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                listIntent.putExtra("name", name);
                listIntent.putExtra("type", type);
                listIntent.putExtra("test_url", test_url);
                listIntent.putExtra("essay_url", essay_url);
                listIntent.putExtra("answ_url", answers_url);
                listIntent.putExtra("exp_url", explanations_url);
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
        return testsList.size();
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
