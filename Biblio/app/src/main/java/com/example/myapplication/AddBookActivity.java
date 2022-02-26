package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddBookActivity extends AppCompatActivity { ;

    EditText nomAuteur, nomLivre, idLivre, dateLive, imageURL;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        nomAuteur = findViewById(R.id.book_name_ed);
        nomLivre = findViewById(R.id.book_name_ed);
        idLivre = findViewById(R.id.book_id_ed);
        dateLive = findViewById(R.id.books_date_ed);
        imageURL = findViewById(R.id.books_image_url_ed);
        addButton = findViewById(R.id.add_book_btn);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewBook(nomLivre.getText().toString(), nomAuteur.getText().toString(), idLivre.getText().toString(), dateLive.getText().toString(), imageURL.getText().toString());
            }
        });
    }

    private void createNewBook(String nomLivre, String nomAuteur, String idLivre, String dateLive, String imageURL) {
        //envoyer
        Toast.makeText(this, "Book Added", Toast.LENGTH_SHORT).show();
    }
}



