package com.example.shoppingui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.VerticalViewHolder> {
    Context context;
    List<Chairs> chairsList;

    public VerticalAdapter(Context context, List<Chairs> chairsList) {
        this.context = context;
        this.chairsList = chairsList;
    }

    @NonNull
    @Override
    public VerticalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vertical_recycler_view,viewGroup,false);
        return new VerticalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VerticalViewHolder verticalViewHolder, final int position) {
        Chairs items = chairsList.get(position);

        verticalViewHolder.ratingBar.setTag(position);
        verticalViewHolder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Chairs chairsUpdate = chairsList.get((int)ratingBar.getTag());
               chairsUpdate.setRating(rating);
               chairsList.set((int)ratingBar.getTag(),chairsUpdate);
            }
        });

        verticalViewHolder.favourite.setTag(position);
        verticalViewHolder.favourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Chairs chairsUpdate = chairsList.get((int)buttonView.getTag());
                chairsUpdate.setFavourite(isChecked);
                chairsList.set((int)buttonView.getTag(),chairsUpdate);
            }
        });

        verticalViewHolder.mainText.setText(items.getName());
        verticalViewHolder.subText.setText(items.getCollYear());
        verticalViewHolder.ratingBar.setRating(items.getRating());
        verticalViewHolder.favourite.setChecked(items.isFavourite());
        verticalViewHolder.imageView.setImageResource(items.getImage());
        verticalViewHolder.price.setText("$"+items.getPrice());

        verticalViewHolder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(context instanceof IMethodCaller){
                    ((IMethodCaller)context).onClickNotify();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return chairsList.size();
    }

    public class VerticalViewHolder extends RecyclerView.ViewHolder{
        TextView mainText, subText, price, notification;
        RatingBar ratingBar;
        ToggleButton favourite;
        ImageView imageView;
        Button addToCart;

        public VerticalViewHolder(@NonNull View itemView) {
            super(itemView);

            mainText = itemView.findViewById(R.id.mainText);
            notification = itemView.findViewById(R.id.notification);
            subText = itemView.findViewById(R.id.subText);
            price = itemView.findViewById(R.id.price);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            favourite = itemView.findViewById(R.id.favourite);
            imageView = itemView.findViewById(R.id.imageView);
            addToCart = itemView.findViewById(R.id.addToCart);
        }
    }

    public interface IMethodCaller{
        void onClickNotify();
    }
}
