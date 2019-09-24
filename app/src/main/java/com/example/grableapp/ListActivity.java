package com.example.grableapp;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
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
    Button finish_btn;
    TextView text_view;

    private ListView lvProduct;
    private ProductListAdapter adapter;
    private List<Product> mProductList;
    private int number_of_products = 0;
    private double total = 0.0;


    public void addItemToList(String name, double price, String description)
    {
//        if (mProductList.isEmpty()) {
//            mProductList.add(new Product(1, name, price, description));
//        }
//        else{
//            int id = mProductList.get(mProductList.size()).getId();
//            mProductList.add(new Product(id+1, name, price, description));
//        }
        //nu merge varianta de mai sus
        //trebuie adaugat id-ul ultimului element din lista + 1

        mProductList.add(new Product(1, name, price, description));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        add_btn = findViewById(R.id.list_btn);
        finish_btn = findViewById(R.id.finish_shopping);
        text_view = findViewById(R.id.textviewlist);



        lvProduct = (ListView)findViewById(R.id.listview_produsct);

        mProductList = new ArrayList<>();


        //Add items to list
        //Se poate din database
/*
        mProductList.add(new Product(1, "Kefir", 12, "Covalact, 20% grasime"));
        mProductList.add(new Product(2, "Tastatura Asus", 300, "Tastatura Gaming Asus Cerberus"));
        mProductList.add(new Product(3, "iPhone5s", 1000, "Apple iPhone5s 16GB"));
        mProductList.add(new Product(4, "Paine VelPitar", 3.5, "Paine VelPitar Alba feliata"));
        mProductList.add(new Product(5, "iPhone6s", 1000, "Apple iPhone6s 16GB"));
        mProductList.add(new Product(6, "iPhone7", 1500, "Apple iPhone7 16GB"));
        mProductList.add(new Product(7, "iPhone7Plus", 2000, "Apple iPhone7Plus 16GB"));
        mProductList.add(new Product(8, "iPhone8", 2500, "Apple iPhone8 16GB"));
        mProductList.add(new Product(9, "iPhoneX", 4000, "Apple iPhoneX 16GB"));
        mProductList.add(new Product(10, "iPhone11", 6000, "Apple iPhone11 16GB"));
        addItemToList("Carne", 20.5, "Piept de pui");
*/

        ///more to add


        ///init adapter
        adapter = new ProductListAdapter(getApplicationContext(), mProductList);
        lvProduct.setAdapter(adapter);

//        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                //ceva
//                //functie de delete item
//                Toast.makeText(getApplicationContext(), "Clicked product id =" + view.getTag(), Toast.LENGTH_LONG).show();
//            }
//        });


        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, ScannerActivity.class);
                startActivity(intent);
                //mProductList.add(new Product(1, "Kefir", 12, "Covalact, 20% grasime"));
                number_of_products++;
                text_view.setAlpha(0f);
                adapter.notifyDataSetChanged();
            }
        });

        finish_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Toast.makeText(ListActivity.this, "Mai mult cod", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Confirm finish shopping?");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialog.Builder build2 = new AlertDialog.Builder(ListActivity.this);
                        build2.setTitle("Choose payment method");
                        build2.setPositiveButton("Credit card", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                for (Product item : mProductList) {
                                    total += item.getPrice();
                                    Intent intent = new Intent(ListActivity.this, CardPaymentActivity.class);
                                    startActivity(intent);
                                }
                                String totall = total + "";
                                Toast.makeText(ListActivity.this, "Mai mult cod", Toast.LENGTH_SHORT).show();
                            }
                        });
                        build2.setNegativeButton("Generate QR code", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                for (Product item : mProductList) {
                                    total += item.getPrice();
                                }
                                String totall = total + "";
                                Intent intent = new Intent(ListActivity.this, QRActivity.class);
                                intent.putExtra("key", totall);
                                startActivity(intent);

                            }
                        });
                        build2.show();
                    }
                });
                    builder.show();
            }
        });

    }

}

