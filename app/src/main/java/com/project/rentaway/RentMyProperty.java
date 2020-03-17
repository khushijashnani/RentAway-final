package com.project.rentaway;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RentMyProperty extends Fragment {

    private TextView text_view_location,text_view_address,text_view_apartment;
    private Button next_1;
    EditText address_input,location_input,apartment_input;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.rent_my_property,null);

        text_view_address=(TextView) view.findViewById(R.id.address);
        text_view_location=(TextView) view.findViewById(R.id.location);
        text_view_apartment=(TextView) view.findViewById((R.id.apartment)) ;
        next_1=(Button) view.findViewById(R.id.button_next1);
        address_input=(EditText) view.findViewById(R.id.address_input) ;
        location_input=(EditText) view.findViewById(R.id.location_input) ;
        apartment_input=(EditText)view.findViewById(R.id.apartment_input);


        next_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nextFragment();
            }
        });

        return view;
    }

    private void nextFragment() {

        String location=location_input.getText().toString().trim();
        String address=address_input.getText().toString().trim();
        String apartment_name=apartment_input.getText().toString().trim();


        if (TextUtils.isEmpty(location)) {
            Toast.makeText(getContext(), "Please enter the location", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(getContext(), "Please enter the address", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(apartment_name)) {
            Toast.makeText(getContext(), "Please enter the apartment name", Toast.LENGTH_SHORT).show();
            return;
        }




        RentMyProperty_DetailsInput fragmentNew = new RentMyProperty_DetailsInput();
        Bundle arguments=new Bundle();
        arguments.putString("apartmentName",apartment_name);
        arguments.putString("Address",address);
        arguments.putString("Location",location);
        fragmentNew.setArguments(arguments);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right
                , R.anim.enter_from_right, R.anim.exit_to_right);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.screen, fragmentNew, "Next Fragment").commit();


    }


}



