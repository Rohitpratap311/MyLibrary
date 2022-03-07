package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    private static final String TAG = "BookActivity";
    private TextView bookName, authorName, description, pageNumber;
    private ImageView bookImage;

    private Button btnCurrReading, btnWantToRead, btnAlreadyRead;
    private Book incommingBook;
    private Util util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
       // overridePendingTransition(R.anim.in,R.anim.out);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initWidgets();

        Intent intent = getIntent();
        int id = intent.getIntExtra("bookID", 0);

        util = new Util();
        ArrayList<Book> books = util.getAllBooks();

        for (Book b : books) {
            if (b.getId() == id) {

                incommingBook = b;
                bookName.setText(b.getName());
                authorName.setText(b.getAuthor());
                description.setText(b.getDescription());
                pageNumber.setText("Pages " + b.getPages());
                Glide.with(this).asBitmap()
                        .load(b.getImageUrl())
                        .into(bookImage);
            }
        }

        btnCurrReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCurrReadingTapped();
            }
        });


        btnWantToRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnWantToReadTapped();
            }
        });
//TODO ERROR
        btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAlreadyReadTapped();
            }
        });


    }

    private void initWidgets() {
        bookName = (TextView) findViewById(R.id.bookName);
        authorName = (TextView) findViewById(R.id.authorName);
        description = (TextView) findViewById(R.id.bookDescription);
        pageNumber = (TextView) findViewById(R.id.bookPages);
        bookImage = (ImageView) findViewById(R.id.bookImage);

        btnCurrReading = (Button) findViewById(R.id.btnCurrentlyReading);
        btnWantToRead = (Button) findViewById(R.id.btnWantToRead);
        btnAlreadyRead = (Button) findViewById(R.id.btnAlreadyRead);
    }

    private void btnWantToReadTapped() {
        Log.d(TAG, "btnWantToReadTapped: Started");

        ArrayList<Book> wantToReadBooks = util.getWantToReadBooks();


        if (wantToReadBooks.contains(incommingBook)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You ALready Added This This Book To Your Want To Read Books");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.create().show();
        } else {
            ArrayList<Book> alreadyReadBooks = util.getAlreadyReadBooks();
            if (alreadyReadBooks.contains(incommingBook)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("YOU Already Read This Book");
                builder.setTitle("Error");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
            } else {
                ArrayList<Book> currentlyReadingBooks = util.getCurrentlyReadingBooks();
                if (currentlyReadingBooks.contains(incommingBook)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("You Are  Already Reading This Book");
                    builder.setTitle("Error");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.create().show();
                } else {
                    util.addWantToReadBook(incommingBook);
                    Toast.makeText(this, "The Book " + incommingBook.getName() + " Added To Your Want To Read Shelf", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    public void btnAlreadyReadTapped() {
        Log.d(TAG, "btnAlreadyReadTapped: Started");

        ArrayList<Book> alreadyReadBooks = util.getAlreadyReadBooks();

        if (alreadyReadBooks.contains(incommingBook)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You ALready Added This This Book To Your Already Read Books");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.create().show();
        } else {

            ArrayList<Book> currentlyReadingBokks = util.getCurrentlyReadingBooks();
            if (currentlyReadingBokks.contains(incommingBook)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("HAve You Finished This Book");
                builder.setTitle("Error");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        util.removeCurrentlyReadingBook(incommingBook);
                        util.addAlreadyReadBook(incommingBook);
                        Toast.makeText(BookActivity.this, "The Book " + incommingBook.getName() + " Added To Your Already Read Shelf", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
            } else {
                util.addAlreadyReadBook(incommingBook);
                Toast.makeText(this, "The Book " + incommingBook.getName() + " Added To Your Already Read Shelf", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void btnCurrReadingTapped() {
        Log.d(TAG, "btnCurrReadingTapped: started");

        ArrayList<Book> currentlyReadingBooks = util.getCurrentlyReadingBooks();

        if (currentlyReadingBooks.contains(incommingBook)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You ALready Added This This Book To Your Currently Reading Books");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.create().show();
        } else {

            ArrayList<Book> wantToReadBooks = util.getWantToReadBooks();
            if (wantToReadBooks.contains(incommingBook)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are You Going To Start Reading This Book");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        util.removeWantToReadBook(incommingBook);
                        util.addCurrentlyReadingBook(incommingBook);
                        Toast.makeText(BookActivity.this, "The Book " + incommingBook.getName() + " Added To Your Currently Reading Shelf", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
            } else {

                ArrayList<Book> alreadyReadBooks = util.getAlreadyReadBooks();
                if (alreadyReadBooks.contains(incommingBook)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Do you Want To Read it Again");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // util.removeWantToReadBook(incommingBook);
                            util.addCurrentlyReadingBook(incommingBook);
                            Toast.makeText(BookActivity.this, "The Book " + incommingBook.getName() + " Added To Your Currently Reading Shelf", Toast.LENGTH_SHORT).show();

                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.create().show();
                } else {
                    util.addCurrentlyReadingBook(incommingBook);
                    Toast.makeText(this, "The Book " + incommingBook.getName() + " Added To Your Currently Reading Shelf", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ((item.getItemId())) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void finish() {
        super.finish();
       // overridePendingTransition(R.anim.close_in,R.anim.close_out);
    }
}
