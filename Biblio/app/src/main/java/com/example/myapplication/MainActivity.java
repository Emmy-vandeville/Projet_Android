package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Myadapter.BookListener {
    RecyclerView recyclerView;
    FloatingActionButton ajoutButton;
    DatabaseReference ref;
    ArrayList<Book> myListData;
    SearchView searchView;
    Myadapter.BookListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ref = FirebaseDatabase.getInstance("https://projetandroid-df4f6-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Biblio").child("Livres");
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);
        ajoutButton=findViewById(R.id.floatingActionButton2);
        ajoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
                startActivity(intent);
            }
        });
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // The user object has basic properties such as display name, email, etc.
            String displayName = user.getDisplayName();
            getSupportActionBar().setTitle(displayName);

        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(ref != null){
            listener = this;
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        myListData = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren() ){
                            if(ds.getValue(Book.class).getId_compte().equals(user.getUid())){
                                myListData.add(ds.getValue(Book.class));
                            }

                        }
                        Myadapter adapterClass = new Myadapter(getApplicationContext(), myListData, listener);
                        recyclerView.setAdapter(adapterClass);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(MainActivity.this, databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
        if(searchView != null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }
    }
    private void search(String str){
        ArrayList<Book> myList = new ArrayList<>();
        for (Book object : this.myListData){
            if(object.getAuteur().toLowerCase().contains(str.toLowerCase()) || object.getNomLivre().toLowerCase().contains(str.toLowerCase())){
                myList.add(object);
            }
        }
        Myadapter adapterClass = new Myadapter(getApplicationContext(), myList, this);
        recyclerView.setAdapter(adapterClass);
    }


    @Override
    public void  onItemClicked(Book book) {
        Intent intent = new Intent(this, BookDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("book", book);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_other:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
            case R.id.menu_profil:
                startActivity(new Intent(getApplicationContext(), UpdateProfil.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

