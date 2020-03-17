package com.project.rentaway;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PropertyDetails extends AppCompatActivity {
    private String fragment;
    private ArrayList<String> a1 =new ArrayList<>();
    private ArrayList<String> a2 =new ArrayList<>();
    private Property p;
    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressDialog progressDialog;
    private static final String TAG = "PropertyDetails";
    private ImageView imageView,caller;
    private TextView rent,rooms,add,t,owner,phone,call;
    private LinearLayoutCompat linear1,linear2,linear3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_details);
        linear1=findViewById(R.id.l1);
        linear2=findViewById(R.id.l2);
        linear3=findViewById(R.id.l3);
        owner = findViewById(R.id.owner);
        phone = findViewById(R.id.phone);
        call = findViewById(R.id.call);
        caller = findViewById(R.id.caller);
        imageView = findViewById(R.id.property_image);
        t = findViewById(R.id.textView1);
        rent = findViewById(R.id.rent);
        rooms = findViewById(R.id.BHK);
        add =findViewById(R.id.address_and_location);
        a1.clear();
        a2.clear();
        a1.add("Security Amount");
        a1.add("Carpet Area");
        a1.add("Address");
        a1.add("No. of Floors");
        a1.add("Floor ");
        a1.add("Furnishing ");
        a1.add("No. of Balconies");
        final Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            fragment=(String)bundle.get("fragment");
            Picasso.get().load((String)bundle.get("url")).fit().into(imageView);
            owner.setText((String)bundle.get("userName"));
            phone.setText((String)bundle.get("Phone"));
            rent.setText((String)bundle.get("Rent"));
            rooms.setText((String)bundle.get("BHK"));
            add.setText((String)bundle.get("Location"));
            a2.add((String)bundle.get("Security"));
            a2.add((String)bundle.get("Carpet"));
            a2.add((String)bundle.get("Address"));
            a2.add((String)bundle.get("no_of_floors"));
            a2.add((String)bundle.get("Floor"));
            a2.add((String)bundle.get("Furnishing"));
            a2.add((String)bundle.get("Balcony"));
        }

        //linear1.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        //recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView = findViewById(R.id.Rec_View);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        adapter = new adapter_attribute(a1,a2);
        recyclerView.setAdapter(adapter);
        progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Loading Items..");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(user.getDisplayName());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(fragment.equals("Home")) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Added to Favourites.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    databaseReference.child("favs").child((String) bundle.get("apartmentName")).setValue((String) bundle.get("Location"));

                }
            });
        }
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",(String)bundle.get("Phone"), null)));
            }
        });

        caller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", (String)bundle.get("Phone"), null)));
            }
        });


    }

    private void toastMessage(String message){
        Toast.makeText(PropertyDetails.this,message,Toast.LENGTH_SHORT).show();
    }
}
