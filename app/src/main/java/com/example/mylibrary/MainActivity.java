package com.example.mylibrary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button btnSeeAll, btnCurrentlyReading, btnWantToRead, btnAlreadyRead, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //overridePendingTransition(R.anim.in,R.anim.out);
        initWidgets();
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        btnSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
                startActivity(intent);
            }
        });
        btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlreadyReadActivity.class);
                startActivity(intent);

            }
        });
        btnWantToRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WantToActivity.class);
                startActivity(intent);
            }
        });
        btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CurrentlyReadingActivity.class);
                startActivity(intent);
            }
        });
btnAbout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        aboutTapped();
    }
});

    }
    private void aboutTapped(){
        Log.d(TAG, "aboutTapped: Started");
        AlertDialog.Builder builder=new AlertDialog.Builder(this)
                .setTitle("About My Library")
                .setMessage("Build By Rohit Pratap Singh\n" +
                        "If You like My Work \n" +
                        "Then You Can Text Me At **********\n" +
                        "or mail me at " +
                        "aba@xyz.com"+
                "www.google.com").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                           Intent intent=new Intent(MainActivity.this,  MyWebViewActivity.class);
                           intent.putExtra("url","https://www.google.com/");
                           startActivity(intent);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.create().show();
    }

    private void initWidgets() {
        btnSeeAll = (Button) findViewById(R.id.btnAllBooks);
        btnCurrentlyReading = (Button) findViewById(R.id.btnCurrent);
        btnWantToRead = (Button) findViewById(R.id.btnWantToRead);
        btnAlreadyRead = (Button) findViewById(R.id.btnAlready);
        btnAbout = (Button) findViewById(R.id.btnAbout);

    }
    @Override
    public void finish() {
        super.finish();
        //overridePendingTransition(R.anim.close_in,R.anim.close_out);
    }

}
