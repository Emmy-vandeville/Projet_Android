package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements Myadapter.BookListener {
    RecyclerView recyclerView;
    FloatingActionButton ajoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ajoutButton=(FloatingActionButton)findViewById(R.id.floatingActionButton2);
        ajoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
                startActivity(intent);
            }
        });

        Book[] myListData = getListBook();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        Myadapter adapter = new Myadapter(getApplicationContext(), myListData, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
    }

    private Book[] getListBook() {
        //get data from firebase
        return new Book[]{
                new Book(0, "livre 1", "Auteur 1", "http://via.placeholder.com/300.png", true, "date"),
                new Book(1, "livre 2", "Auteur 1", "http://via.placeholder.com/300.png", false, ""),
                new Book(2, "livre 3", "Auteur 1", "http://via.placeholder.com/300.png", true, "date"),
                new Book(3, "livre 4", "Auteur 1", "http://via.placeholder.com/300.png", true, "date"),
                new Book(4, "livre 5", "Auteur 1", "http://via.placeholder.com/300.png", true, "date"),
                new Book(5, "livre 6", "Auteur 1", "http://via.placeholder.com/300.png", true, "date"),
                new Book(5, "livre 6", "Auteur 1", "http://via.placeholder.com/300.png", true, "date"),
                new Book(5, "livre 6", "Auteur 1", "http://via.placeholder.com/300.png", true, "date"),
                new Book(5, "livre 6", "Auteur 1", "http://via.placeholder.com/300.png", true, "date"),
                new Book(5, "livre 6", "Auteur 1", "http://via.placeholder.com/300.png", true, "date"),
                new Book(5, "livre 6", "Auteur 1", "http://via.placeholder.com/300.png", true, "date"),
                new Book(5, "livre 6", "Auteur 1", "https://us.123rf.com/450wm/dp3010/dp30101401/dp3010140100016/24917468-couverture-de-livre-en-cuir-rouge.jpg?ver=6", true, "date"),
        };
    }


    @Override
    public void  onItemClicked(Book book) {
        Intent intent = new Intent(this, BookDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("book", book);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

