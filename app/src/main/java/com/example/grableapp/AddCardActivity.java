package com.example.grableapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
import com.craftman.cardform.OnPayBtnClickListner;


public class AddCardActivity extends AppCompatActivity {

    private CardForm cardForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        cardForm = (CardForm)findViewById(R.id.credit_card);
        TextView textDes = (TextView)findViewById(R.id.payment_amount);
        Button btnpay = (Button)findViewById(R.id.btn_pay);
        textDes.setText("$7541");
        btnpay.setText(String.format("Payer name is:", textDes.getText()));
        cardForm.setPayBtnClickListner(new OnPayBtnClickListner() {
            @Override
            public void onClick(Card card) {
                Toast.makeText(AddCardActivity.this, "Name:" + card.getName() +
                        "Last 4 digits" + card.getLast4(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
