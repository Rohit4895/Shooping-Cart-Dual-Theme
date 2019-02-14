package com.example.shoppingui;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements VerticalAdapter.IMethodCaller {
    RecyclerView horiRecyclerView, verRecyclerView;
    LinearLayoutManager linearLayoutManager;
    HorizontalAdapter horizontalAdapter;
    VerticalAdapter verticalAdapter;
    Button addToCart;
    TextView textView;
    int count=0;
    ImageView cart;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utils.onActivityCreateSetTheme(this);

        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.notification);


        Chairs chair1 = new Chairs("White Chair","Collection 2018",
                82.75,R.drawable.chair1,0,false);
        Chairs chair2 = new Chairs("Black Chair","Collection 2019",
                92.35,R.drawable.chair2,0,false);
        Chairs chair3 = new Chairs("Brown Chair","Collection 2015",
                24.31,R.drawable.chair3,0,false);
        Chairs chair4 = new Chairs("Yellow Chair","Collection 2010",
                90.14,R.drawable.chair4,0,false);
        Chairs chair5 = new Chairs("Gray Chair","Collection 2012",
                47.45,R.drawable.chair5,0,false);
        Chairs chair6 = new Chairs("Wooden Chair","Collection 2016",
                04.55,R.drawable.chair6,0,false);
        Chairs chair7 = new Chairs("Plastic Chair","Collection 2017",
                82.66,R.drawable.chair7,0,false);
        Chairs chair8 = new Chairs("Fiber Chair","Collection 2018",
                31.21,R.drawable.chair8,0,false);

        List<Chairs> list = new ArrayList<Chairs>();
        list.add(chair1);
        list.add(chair2);
        list.add(chair3);
        list.add(chair4);
        list.add(chair5);
        list.add(chair6);
        list.add(chair7);
        list.add(chair8);

        List<String> list1 = new ArrayList<String>();
        list1.add("Dinning Room");
        list1.add("Living Room");
        list1.add("Garden");
        list1.add("Bed Room");
        list1.add("Farm House");

        horiRecyclerView = findViewById(R.id.horiRecyclerView);
        verRecyclerView = findViewById(R.id.verRecyclerView);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        horiRecyclerView.setLayoutManager(linearLayoutManager);
        HorizontalAdapter horizontalAdapter = new HorizontalAdapter(this,list1);
        horiRecyclerView.setAdapter(horizontalAdapter);

        verRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        VerticalAdapter verticalAdapter = new VerticalAdapter(this,list);
        verRecyclerView.setAdapter(verticalAdapter);

        cart = findViewById(R.id.cartIcon);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = (MainActivity.this).getPreferences(Context.MODE_PRIVATE);
                int status = sharedPreferences.getInt("status",0);
                Log.d("statusRohit",status+"");
                if (status == 1){
                    Utils.changeTheme(MainActivity.this, Utils.THEME_DEFAULT);
                }else if (status == 2){
                    Utils.changeTheme(MainActivity.this, Utils.THEME_BLACK);
                }




            }
        });

    }

    @Override
    public void onClickNotify() {
        count++;
        textView.setText(String.valueOf(count));
    }
}
