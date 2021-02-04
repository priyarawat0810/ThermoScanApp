package com.example.thermoscanapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    private List<UploadImage> uploadImageList=new ArrayList<>();
    private ProgressBar mProgressCircle;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private RecyclerView recyclerView;
    EditText searchEditText;
    ImageView searchIcon, searchIconSec, imgBack;
    ConstraintLayout searchLayout;
    private GalleryImageAdapter galleryImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.WHITE);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.black_overlay2), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        searchIcon = findViewById(R.id.search_icon);
        searchLayout = findViewById(R.id.searchLayout);
        searchEditText = findViewById(R.id.searchEditText);
        searchIconSec = findViewById(R.id.search_icon_sec);
        imgBack = findViewById(R.id.img_back);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchLayout.setVisibility(View.GONE);
            }
        });

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchLayout.setVisibility(View.VISIBLE);
            }
        });


        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.RecyclerView);
        mProgressCircle = findViewById(R.id.progress_circle);

        GridLayoutManager layoutManager =new GridLayoutManager(this,3);
        layoutManager.setOrientation(recyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);



        firebaseFirestore.collection("Users")
                .document(firebaseAuth.getCurrentUser().getUid()).collection("UserData")
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                                uploadImageList.add(new UploadImage(documentSnapshot.get("imageUrl").toString(),documentSnapshot.get("name").toString(),documentSnapshot.get("details").toString(),documentSnapshot.get("timestamp").toString()));
                            }

                            GalleryImageAdapter imageAdapter =new GalleryImageAdapter(uploadImageList);
                            recyclerView.setAdapter(imageAdapter);
                            imageAdapter.notifyDataSetChanged();
                            mProgressCircle.setVisibility(View.INVISIBLE);

                        }else{
                            String error= task.getException().getMessage();
                            Toast.makeText(GalleryActivity.this, error, Toast.LENGTH_SHORT).show();
                            mProgressCircle.setVisibility(View.INVISIBLE);

                        }
                    }
                });

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                searchUsers(charSequence.toString().toLowerCase());
                filter(charSequence.toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        });

        searchIconSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                searchUsers(searchEditText.getText().toString().trim());
            }
        });
    }

    void filter(String text){
        List<UploadImage> temp = new ArrayList();
        for(UploadImage d: uploadImageList){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(d.getName().toLowerCase().contains(text)){
                temp.add(d);
            }
        }
        galleryImageAdapter = new GalleryImageAdapter(temp);
        recyclerView.setAdapter(galleryImageAdapter);
        //update recyclerview
        galleryImageAdapter.updateList(temp);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id == android.R.id.home) {
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}