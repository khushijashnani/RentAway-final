package com.project.rentaway;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class RentPriceDetails extends Fragment {

    private ImageView image;
    private EditText rent_amount,security_amount;
    private Button select,finish;
    private TextView  select_image,upload_image;
    FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    private Uri uri;
    private String apartmentName,imageUri,Address;
    private String Location,BHK,balconies,carpetArea,noOfFloors,floorNo,Furnishing;
    private String rentAmount,securityAmount;
    final ProgressDialog progressDialog = new ProgressDialog(this.getActivity());


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            apartmentName= getArguments().getString("apartmentName");
            Address= getArguments().getString("Address");
            Location= getArguments().getString("Location");
            BHK= getArguments().getString("BHK");
            balconies= getArguments().getString("balconies");
            carpetArea= getArguments().getString("carpetArea");
            noOfFloors= getArguments().getString("noOfFloors");
            floorNo= getArguments().getString("floorNo");
            Furnishing= getArguments().getString("Furnishing");


        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.rent_price_details,null);

        image=(ImageView) view.findViewById(R.id.image);
        select=(Button) view.findViewById(R.id.select_image);
        rent_amount=(EditText) view.findViewById(R.id.rent_amount);
        security_amount=(EditText) view.findViewById(R.id.security_amount);
        finish=(Button) view.findViewById(R.id.finish);
        // upload=(Button) view.findVie

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage();
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishUpload();
            }
        });

        return view;

    }

    private void finishUpload() {


        rentAmount=rent_amount.getText().toString().trim();
        securityAmount = security_amount.getText().toString().trim();

        StorageReference storageReference= FirebaseStorage.getInstance().getReference().child("Uploads").
                child(uri.getLastPathSegment());



        if(TextUtils.isEmpty(rentAmount)) {
            Toast.makeText(getContext(), "Enter the expected rent", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(securityAmount)) {
            Toast.makeText(getContext(), "Enter the security amount", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("Rent", "Reached here");

        progressDialog.setMessage("Storing data..");
        progressDialog.show();
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.d("Rent", "Reached once more 23");
               // progressDialog.dismiss();
                Task<Uri> uriTask= taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete());
                Toast.makeText(getContext(), "Hello Bye", Toast.LENGTH_SHORT).show();
                Uri uriImage=uriTask.getResult();
                Log.d("Rent", "Reached once more");
                imageUri=uriImage.toString();


                finishFinal();




                Toast.makeText(getContext(), "All details stored", Toast.LENGTH_LONG).show();

                //changeFragment();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(),e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                Log.e("Rent", e.getMessage().toString());
                Log.e("Rent", uri.toString());
            }
        });



    }





    private void finishFinal()
    {
        currentUser= FirebaseAuth.getInstance().getCurrentUser();
        String userName = currentUser.getDisplayName();

        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Users/"+ userName
                +"/Property/"+apartmentName);

        Log.d("Rent",apartmentName);
        Log.d("Rent",imageUri);

        String s=apartmentName;

        Property p=new Property(imageUri, Location, balconies, noOfFloors,floorNo, carpetArea, Furnishing,s,rentAmount,securityAmount,BHK,Address);


        databaseReference.setValue(p);
        progressDialog.dismiss();

    }




    private void pickImage() {

        Intent pickPhoto=new Intent(Intent.ACTION_PICK);
        pickPhoto.setType("image/*");
        startActivityForResult(pickPhoto,100);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK && requestCode==100) {

            uri=data.getData();
            image.setImageURI(uri);
            Log.d("Rent",uri.toString());
        }
    }
}





