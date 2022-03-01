package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class BookDetailsActivity extends AppCompatActivity {

    Book book;
    TextView nomAuteur, nomLivre, idLivre, statutLivre;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        book = (Book) bundle.getSerializable("book");

        setContentView(R.layout.activity_book_details);
        nomAuteur = findViewById(R.id.book_author_txt);
        nomLivre = findViewById(R.id.book_name_txt);
        idLivre = findViewById(R.id.book_id_txt);
        statutLivre = findViewById(R.id.books_statut_txt);
        imageView = findViewById(R.id.book_image);

        nomAuteur.setText("By " + book.getAuteur());
        nomLivre.setText(book.getNomLivre());
        idLivre.setText("Livre Id: " + book.getIdLivre());
        if (book.isStatut()) {
            statutLivre.setText("preté, il est de retour le " + book.getDate());
        } else {
            statutLivre.setText("Disponible");
        }

        Glide.with(getApplicationContext())
                .load(book.getImgCouverture())
                .into(imageView);


    }
}
