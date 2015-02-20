package kz.aituaraibol.insdu.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import kz.aituaraibol.insdu.R;

public class Contacts extends ActionBarActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        String text1 = getApplicationContext().getResources().getString(R.string.text_info4);
        String text2 = getApplicationContext().getResources().getString(R.string.text_info5);;
        String text3 = getApplicationContext().getResources().getString(R.string.text_info6);;
        String text4 = getApplicationContext().getResources().getString(R.string.text_info7);;

        TextView tv1 = (TextView) findViewById(R.id.tv1);
        TextView tv2 = (TextView) findViewById(R.id.tv2);
        TextView tv3 = (TextView) findViewById(R.id.tv3);
        TextView tv4 = (TextView) findViewById(R.id.tv4);

        tv1.setText(text1);
        tv2.setText(text2);
        tv3.setText(text3);
        tv4.setText(text4);
    }
}
