package kz.aituaraibol.insdu.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import kz.aituaraibol.insdu.R;

public class About extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        String text1 = getApplicationContext().getResources().getString(R.string.text_info1);
        String text2 = getApplicationContext().getResources().getString(R.string.text_info2);
        String text3 = getApplicationContext().getResources().getString(R.string.text_info3);

        TextView tv1 = (TextView) findViewById(R.id.TextInfo1);
        TextView tv2 = (TextView) findViewById(R.id.TextInfo2);
        TextView tv3 = (TextView) findViewById(R.id.TextInfo3);

        tv1.setText(text1);
        tv2.setText(text2);
        tv3.setText(text3);
    }
}
