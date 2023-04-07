package com.example.dddemo;

import android.content.res.AssetManager;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.dddemo.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding1;
    private ListView listView;
    private ArrayList<String> holdingList;
    private ArrayAdapter<String> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
     Scanner scannAH = new Scanner(System.in);

     final int LENGTH = 99999;




     String holdingString = " " ;

     String [] yugiohName = new String[LENGTH];String assetName = "Yugioh.txt";
        super.onCreate(savedInstanceState);
         binding1 = ActivityMainBinding.inflate(getLayoutInflater());
         setContentView(binding1.getRoot());






        holdingString = LoadData(assetName);
        Yugioh [] yugioh12 = new Yugioh[holdingString.length()];
        for(int tt = 0 ; tt < 100 ; ++tt){
            yugioh12[tt] = new Yugioh();

        }
        int po = loadYugioh(yugioh12,holdingString);
        listView = findViewById(R.id.listView);
        holdingList = new ArrayList<String>();
        for(int az = 0; az< 20; ++az) {

                    holdingList.add(yugioh12[az].getName());

        }

        itemsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, holdingList);
     listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);

                    switch (position) {
                        case :
                            //
                            break;
                    }
                Toast.makeText(getApplicationContext(),
                        "This a toast message",
                        Toast.LENGTH_LONG).show();
            }
        });

    }
@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static int loadYugioh
            ( Yugioh[] yugioh,String file )
    {
        Scanner keyboard = new Scanner( System.in );
        int nYugiohCards = 0;
        Scanner inFile = new Scanner( file );
        do
        {
            yugioh[nYugiohCards] = new Yugioh();

            yugioh[nYugiohCards].name = inFile.next();


            yugioh[nYugiohCards].type = inFile.next();

            yugioh[nYugiohCards].attackPower = inFile.nextInt();




            yugioh[nYugiohCards].defensePower = inFile.nextInt();




            ++nYugiohCards;
        }while ( yugioh[ nYugiohCards -1].defensePower != 0 );
        --nYugiohCards;

        return nYugiohCards;
    }



    public String LoadData(String inFile) {
        String tContents = "";
        Scanner file = new Scanner( inFile );


        try {
            InputStream stream = getAssets().open(inFile);

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            tContents = new String(buffer);
        }
        catch (IOException e) {
            // Handle exceptions here
        }

        return tContents;

    }



}