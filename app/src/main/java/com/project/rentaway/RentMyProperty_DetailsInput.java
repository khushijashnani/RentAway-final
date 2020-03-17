package com.project.rentaway;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
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

import java.util.Objects;

import static android.R.layout.select_dialog_singlechoice;

public class RentMyProperty_DetailsInput extends Fragment {

    private TextView text_view1,text_view2,text_view3,text_view4,text_view5,text_view6;
    private EditText edit_text1,edit_text2,edit_text3;
    private ListView list_view1,list_view2,list_view3;
    private Button next;
    private String bhk,balcony,furnish,apartment_name,Address,Location;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            apartment_name= getArguments().getString("apartmentName");
            Address=getArguments().getString("Address");
            Location=getArguments().getString("Location");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.rent_my_property_details_input,null);

        edit_text1=(EditText) view.findViewById(R.id.floors_input);
        edit_text2=(EditText) view.findViewById(R.id.floors_no_input);
        edit_text3=(EditText) view.findViewById(R.id.area);

        bhk=furnish=balcony="";

        list_view1=(ListView) view.findViewById(R.id.list_view_1);
        String[] bedrooms={"1 BHK","2 BHK","3 BHK"};
        final ArrayAdapter<String> adapter_1=new ArrayAdapter<String>(getActivity(),
                R.layout.list_view_layout,bedrooms);

        list_view2=(ListView) view.findViewById(R.id.list_view_2);
        String[] balconies={"1","2","3"};
        final ArrayAdapter<String> adapter_2=new ArrayAdapter<String>( getActivity(),
                R.layout.list_view_layout,balconies);

        list_view3=(ListView) view.findViewById(R.id.list_view_3);
        String[] furnishing={"Fully furnished","Semi furnished","Unfurnished"};
        final ArrayAdapter<String> adapter_3=new ArrayAdapter<String>( getActivity(),
                R.layout.list_view_layout,furnishing);


        list_view1.setAdapter(adapter_1);
        list_view2.setAdapter(adapter_2);
        list_view3.setAdapter(adapter_3);

        Utility.setListViewHeightBasedOnChildren(list_view1);
        Utility.setListViewHeightBasedOnChildren(list_view2);
        Utility.setListViewHeightBasedOnChildren(list_view3);


        list_view1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                view.setSelected(true);
                bhk=(String)adapter_1.getItem(i);

                //Toast.makeText(getContext(), value+" selected", Toast.LENGTH_SHORT).show();
            }
        });


        list_view2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                view.setSelected(true);
                balcony=(String)adapter_2.getItem(i);

                // Toast.makeText(getContext(), value+" selected", Toast.LENGTH_SHORT).show();
            }
        });

        list_view3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                view.setSelected(true);
                furnish=(String)adapter_3.getItem(i);

                //Toast.makeText(getContext(), value+" selected", Toast.LENGTH_SHORT).show();
            }
        });

        next=(Button)view.findViewById(R.id.button_next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nextFragment();
            }
        });


        return view;
    }

    private void nextFragment() {

        String area=edit_text3.getText().toString().trim();
        String floors=edit_text1.getText().toString().trim();
        String floorNo=edit_text2.getText().toString().trim();

        if(TextUtils.isEmpty(area)) {
            toastMessage("Enter the carpet area");
            return;
        }
        if(TextUtils.isEmpty(floors)) {
            toastMessage("Enter total number of floors");
            return;
        }
        if(TextUtils.isEmpty(floorNo)) {
            toastMessage("Enter the floor number");
            return;
        }
        if(TextUtils.isEmpty(bhk)) {
            toastMessage("Select the no. of bedroooms");
            return;
        }
        if(TextUtils.isEmpty(furnish)) {
            toastMessage("Select the type of furnishing");
            return;
        }
        if(TextUtils.isEmpty(balcony)) {
            toastMessage("Select the no.of balconies");
            return;
        }




        RentPriceDetails fragmentNew = new RentPriceDetails();

        Bundle arguments=new Bundle();
        arguments.putString("apartmentName",apartment_name);
        arguments.putString("Address",Address);
        arguments.putString("Location",Location);
        arguments.putString("BHK",bhk);
        arguments.putString("balconies",balcony);
        arguments.putString("carpetArea",area);
        arguments.putString("noOfFloors",floors);
        arguments.putString("floorNo",floorNo);
        arguments.putString("Furnishing",furnish);



        fragmentNew.setArguments(arguments);


        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right
                , R.anim.enter_from_right, R.anim.exit_to_right);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.screen, fragmentNew, "Next Fragment").commit();



    }

    public void toastMessage(String str)
    {
        Toast.makeText(getContext(),str, Toast.LENGTH_LONG).show();
    }


}
