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


    private ActivityMainBinding a;
    private ListView l;
    private ArrayList<String> h;
    private ArrayAdapter<String> i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
     Scanner s = new Scanner(System.in);

     final int L = 99999;

     String hS = " " ;

     String [] yN = new String[L];String aN = "Yugioh.txt";
        super.onCreate(savedInstanceState);
         a = ActivityMainBinding.inflate(getLayoutInflater());
         setContentView(a.getRoot());

        hS = LoadData(aN);
        Yugioh [] y = new Yugioh[hS.length()];
        for(int t = 0 ; t < 100 ; ++t){
            y[t] = new Yugioh();

        }
        int p = loadYugioh(y,hS);
        l = findViewById(R.id.listView);
        h = new ArrayList<String>();
        for(int az = 0; az< 20; ++az) {

                    h.add(y[az].getName());

        }

        i = new ArrayAdapter(this, android.R.layout.simple_list_item_1, h);
     l.setAdapter(i);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menu_main, m);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem i) {
        int d = i.getItemId();

        if (d == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(i);
    }

    public static int loadYugioh
            ( Yugioh[] y,String f )
    {
        Scanner k = new Scanner( System.in );
        int nY = 0;
        Scanner in = new Scanner( f );
        do
        {
            y[nY] = new Yugioh();

            y[nY].name = in.next();


            y[nY].type = in.next();

            y[nY].attackPower = in.nextInt();




            y[nY].defensePower = in.nextInt();




            ++nY;
        }while ( y[ nY -1].defensePower != 0 );
        --nY;

        return nY;
    }



    public String LoadData(String in) {
        String c = "";
        Scanner f = new Scanner( in );


        try {
            InputStream s = getAssets().open(in);

            int l = s.available();
            byte[] b = new byte[l];
            s.read(b);
            s.close();
            c = new String(b);
        }
        catch (IOException e) {
            // Handle exceptions here
        }

        return c;

    }



}
