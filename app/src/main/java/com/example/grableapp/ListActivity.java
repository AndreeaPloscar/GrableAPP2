package com.example.grableapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ListActivity extends AppCompatActivity  {

    ArrayList<String> arrayList = new ArrayList<>();


    ArrayAdapter<String> arrayAdapter;

    Map<String, String> shoppingList = new HashMap<String, String>();

    Button add_btn;

    private ListView lvProduct;
    private ProductListAdapter adapter;
    private List<Product> mProductList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        add_btn = findViewById(R.id.list_btn);

        lvProduct = (ListView)findViewById(R.id.listview_produsct);

        mProductList = new ArrayList<>();
        //Add items to list
        //Se poate din database

        mProductList.add(new Product(1, "iPhone4", 200, "Apple iPhone4 16GB"));
        mProductList.add(new Product(2, "iPhone5", 400, "Apple iPhone5 16GB"));
        mProductList.add(new Product(3, "iPhone5s", 500, "Apple iPhone5s 16GB"));
        mProductList.add(new Product(4, "iPhone6", 700, "Apple iPhone6 16GB"));
        mProductList.add(new Product(5, "iPhone6s", 1000, "Apple iPhone6s 16GB"));
        mProductList.add(new Product(6, "iPhone7", 1500, "Apple iPhone7 16GB"));
        mProductList.add(new Product(7, "iPhone7Plus", 2000, "Apple iPhone7Plus 16GB"));
        mProductList.add(new Product(8, "iPhone8", 2500, "Apple iPhone8 16GB"));
        mProductList.add(new Product(9, "iPhoneX", 4000, "Apple iPhoneX 16GB"));
        mProductList.add(new Product(10, "iPhone11", 6000, "Apple iPhone11 16GB"));


        ///more to add


        ///init adapter
        adapter = new ProductListAdapter(getApplicationContext(), mProductList);
        lvProduct.setAdapter(adapter);

        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //ceva
                //functie de delete item
                Toast.makeText(getApplicationContext(), "Clicked product id =" + view.getTag(), Toast.LENGTH_LONG).show();
            }
        });


        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, ScannerActivity.class);
                startActivity(intent);
            }
        });


    }

}
