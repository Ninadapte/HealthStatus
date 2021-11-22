package com.project.healthstatus;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class summery_adapter extends RecyclerView.Adapter<summery_adapter.ExampleViewHolder>  {

    ArrayList<summery_item> items;
    ItemSelected s;
    Context context;
    public interface ItemSelected
    {
        void selected(int position);
        void completed(int position);
    }

    public summery_adapter(ArrayList<summery_item> items, Context context)
    {
        this.items = items;
        this.context = context;

    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder
    {

        public TextView name ;
        public View view;
        public TextView points;
        public RelativeLayout relative;
        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_text);
            view = itemView.findViewById(R.id.item_view);
            points = itemView.findViewById(R.id.item_points);
            relative = itemView.findViewById(R.id.rel);

        }
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.summery_item,parent , false);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setRepeatCount(0);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        v.startAnimation(alphaAnimation);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        summery_item currentItem = items.get(position);

        holder.name.setText(currentItem.getname());

        holder.view.setBackgroundColor(Color.parseColor(currentItem.getColor()));

        holder.points.setText(currentItem.getPoints());
        holder.relative.setBackgroundColor(Color.parseColor(currentItem.getBackground_color()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
