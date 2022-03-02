package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BookDetailsActivity extends AppCompatActivity {

    Book book;
    TextView nomAuteur, nomLivre;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // The user object has basic properties such as display name, email, etc.
            String displayName = user.getDisplayName();
            getSupportActionBar().setTitle(displayName);

        }
        Bundle bundle = intent.getExtras();
        book = (Book) bundle.getSerializable("book");

        setContentView(R.layout.activity_book_details);
        nomAuteur = findViewById(R.id.book_author_txt);
        nomLivre = findViewById(R.id.book_name_txt);
        //idLivre = findViewById(R.id.book_id_txt);
        imageView = findViewById(R.id.book_image);

        nomAuteur.setText("By " + book.getAuteur());
        nomLivre.setText(book.getNomLivre());
        //idLivre.setText("Livre Id: " + book.getIdLivre());


        Glide.with(getApplicationContext())
                .load(book.getImgCouverture())
                .into(imageView);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_book_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        return super.onOptionsItemSelected(item);
    }
}
