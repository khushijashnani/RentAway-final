package com.project.rentaway;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Favourites extends Fragment implements MyAdapter.Listener{

    private ArrayList<HashMap<String,String>> userArrayList = new ArrayList<>();
    private static final String TAG = "Favourites";
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Property> propertyList = new ArrayList<>();
    private DatabaseReference databaseReference;


    public Favourites() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);

        recyclerView = view.findViewById(R.id.rec_view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter(propertyList,this);
        recyclerView.setAdapter(myAdapter);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(user.getDisplayName()).child("favs");
        //progressDialog.show();
        Log.d(TAG, "onCreateView: favourites");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //progressDialog.dismiss();
            }
        });
        return view;
    }

    private void toastMessage(String message){
        Toast.makeText(Favourites.this.getActivity(),message,Toast.LENGTH_SHORT).show();
    }
    private void showData(DataSnapshot dataSnapshot) {
        propertyList.clear();
        Log.d(TAG, "showData: datarefernce is on favs"+dataSnapshot.getChildrenCount());
        for (DataSnapshot d: dataSnapshot.getChildren())
        {
            Log.d(TAG, "showData: inside favs");
                final String name = (String) d.getKey();
                DatabaseReference d2 = FirebaseDatabase.getInstance().getReference("Users");
                d2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot da: dataSnapshot.getChildren())
                        {
                            DataSnapshot d2 = da.child("Property");
                            for(DataSnapshot ds : d2.getChildren())
                            {
                                Property p = ds.getValue(Property.class);
                                if(p.getApartmentName().equals(name) && p.getApartmentName()!=null)
                                {
                                    propertyList.add(p);
                                    Log.d(TAG, "onDataChange: 1");
                                    String phone = da.child("phone").getValue(String.class);
                                    String email = da.child("email").getValue(String.class);
                                    String username = da.child("userName").getValue(String.class);
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
                        myAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
    }

    @Override
    public void onClicked(int position) {
        Log.d(TAG, "onClicked: clicked..");
        Intent intent = new Intent(this.getActivity(),PropertyDetails.class);
        Property current = propertyList.get(position);
        intent.putExtra("fragment","Favourites");
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
