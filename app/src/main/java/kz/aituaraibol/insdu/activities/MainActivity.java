package kz.aituaraibol.insdu.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import kz.aituaraibol.insdu.R;
import kz.aituaraibol.insdu.adapters.GridViewAdapter;
import kz.aituaraibol.insdu.utils.Item;


public class MainActivity extends ActionBarActivity {

    private GridView gridView;
    private ArrayList<Item> gridArray = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String gridTitles[] = this.getResources().getStringArray(R.array.grid_items);
        Bitmap gridIcons[] = new Bitmap[] {
                BitmapFactory.decodeResource(this.getResources(), R.drawable.home),
                BitmapFactory.decodeResource(this.getResources(), R.drawable.gps_icon),
                BitmapFactory.decodeResource(this.getResources(), R.drawable.atm),
                BitmapFactory.decodeResource(this.getResources(), R.drawable.stories_icon),
                BitmapFactory.decodeResource(this.getResources(), R.drawable.priznavawki),
                BitmapFactory.decodeResource(this.getResources(), R.drawable.sdupublic),
                BitmapFactory.decodeResource(this.getResources(), R.drawable.contacts),
                BitmapFactory.decodeResource(this.getResources(), R.drawable.home),
                BitmapFactory.decodeResource(this.getResources(), R.drawable.info_icon)
                };

        for(int i = 0; i < 9; i++) {
            gridArray.add(new Item(gridIcons[i], gridTitles[i]));

        }

        gridView = (GridView) findViewById(R.id.gridView);

        GridViewAdapter adapter = new GridViewAdapter(getApplicationContext(), R.layout.row_grid, gridArray);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(MainActivity.this, About.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, Map.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, About.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, SDUstories.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, Priznavashki.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(MainActivity.this, SDUpublic.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(MainActivity.this, Contacts.class);
                        startActivity(intent);
                        break;
                    case 7:
                        intent = new Intent(MainActivity.this, About.class);
                        startActivity(intent);
                        break;
                    case 8:
                        intent = new Intent(MainActivity.this, Info.class);
                        startActivity(intent);
                        break;
                }
            }
        });



    }
}
