package com.example.shoppingui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.HorizontalViewHolder> {
    Context context;
    List<String> chairTypesList;

    public HorizontalAdapter(Context context, List<String> chairTypesList) {
        this.context = context;
        this.chairTypesList = chairTypesList;
    }

    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(context).inflate(R.layout.horizontal_recycler_view,viewGroup,false);
        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder horizontalViewHolder, int position) {
        horizontalViewHolder.button.setText(chairTypesList.get(position));
    }

    @Override
    public int getItemCount() {
        return chairTypesList.size();
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder{
        Button button;

        public HorizontalViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.horiButton);
        }
    }
}
