package com.example.android.fastimc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by brunoferreira on 3/17/16.
 */
public class Order extends AppCompatActivity {

    private TextView Textv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Textv = (TextView)findViewById(R.id.result);
        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            String j =(String) b.get("IMC_RESULT");
//            Textv.setText(j);
            displayMessage(j);
        }

    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.result);
        priceTextView.setText(message);
    }

}
