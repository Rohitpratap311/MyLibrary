package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {
    private static final String TAG = "AllBooksActivity";
    // private ListView listView;
    private RecyclerView booksRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
     //   overridePendingTransition(R.anim.in,R.anim.out);

        Log.d(TAG, "onCreate :started ");

        booksRecView = (RecyclerView) findViewById(R.id.booksRecyclerView);

        BooksRecViewAdapter adapter = new BooksRecViewAdapter(this);
        booksRecView.setAdapter(adapter);

        booksRecView.setLayoutManager(new GridLayoutManager(this,2));

        Util util=new Util();
        ArrayList<Book> books=new ArrayList<>();
        books=util.getAllBooks();
        adapter.setBooks(books);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ((item.getItemId())){
            case android.R.id.home:
                super.onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
       // overridePendingTransition(R.anim.close_in,R.anim.close_out);
    }
}
