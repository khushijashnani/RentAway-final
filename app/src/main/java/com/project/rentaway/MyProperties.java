package com.project.rentaway;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class MyProperties extends Fragment implements MyAdapter.Listener{

    private static final String TAG = "MyProperties";
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Property> propertyList = new ArrayList<>();
    private ArrayList<HashMap<String,String>> userArrayList = new ArrayList<>();
    private DatabaseReference databaseReference,databaseReference1;
    private ProgressDialog progressDialog;

    public MyProperties() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.home, container, false);
        recyclerView = view.findViewById(R.id.rec_view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new MyAdapter(propertyList,this);
        recyclerView.setAdapter(myAdapter);


        progressDialog= new ProgressDialog(this.getActivity());
        progressDialog.setMessage("Loading Items..");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(user.getDisplayName()).child("Property");
        progressDialog.show();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

        return view;
    }

    private void showData(DataSnapshot dataSnapshot){
        propertyList.clear();
        for (DataSnapshot d: dataSnapshot.getChildren())
        {
            Property p = d.getValue(Property.class);
            Log.d(TAG, "showData: on"+d.getRef());
            final String apart =p.getApartmentName();
            Log.d(TAG, "showData: apart"+apart);
            propertyList.add(p);
            databaseReference1 = FirebaseDatabase.getInstance().getReference("Users");
            databaseReference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot d2 : dataSnapshot.getChildren())
                    {
                        DataSnapshot x = d2.child("Property");
                        for(DataSnapshot y :x.getChildren())
                        {
                            String name = y.child("apartmentName").getValue(String.class);
                            if(name.equals(apart))
                            {
                                Log.d(TAG, "onDataChange: 1");
                                String phone = d2.child("phone").getValue(String.class);
                                String email = d2.child("email").getValue(String.class);
                                String username = d2.child("userName").getValue(String.class);
                                HashMap<String,String> map = new HashMap<>();
                                Log.d(TAG, "onDataChange: 2");
                                map.put("phone",phone);
                                map.put("email",email);
                                map.put("userName",username);
                                Log.d(TAG, "onDataChange: 3");
                                userArrayList.add(map);
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        myAdapter.notifyDataSetChanged();
        progressDialog.dismiss();
        //toastMessage("bhk:"+propertyList.get(0).getBhk()+" add:"+propertyList.get(0).getAddress()+" Rent:"+propertyList.get(0).getRent());
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    private void toastMessage(String message){
        Toast.makeText(MyProperties.this.getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClicked(int position) {
        Log.d(TAG, "onClicked: clicked..");
        Intent intent = new Intent(this.getActivity(),PropertyDetails.class);
        Property current = propertyList.get(position);
        intent.putExtra("fragment","MyProperties");
        intent.putExtra("url",current.getImageUri());
        intent.putExtra("Location",current.getLocation());
        intent.putExtra("Address",current.getAddress());
        intent.putExtra("no_of_floors",current.getNoOfFloors());
        intent.putExtra("Security",current.getSecurityAmount());
        intent.putExtra("Carpet",current.getCarpetArea());
        intent.putExtra("Balcony",current.getBalconies());
        intent.putExtra("Furnishing",current.getFurnishing());
        intent.putExtra("Floor",current.getFloorNo());
        intent.putExtra("Rent",current.getRent());
        intent.putExtra("BHK",current.getBHK());
        HashMap<String,String> map = userArrayList.get(position);
        intent.putExtra("Phone",(String)map.get("phone"));
        intent.putExtra("userName",(String)map.get("userName"));
        intent.putExtra("Email",(String)map.get("email"));
        startActivity(intent);
    }
}


