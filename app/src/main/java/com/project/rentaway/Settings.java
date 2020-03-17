//package com.project.rentaway;
//
//import android.app.ProgressDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AlertDialog;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.DividerItemDecoration;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.ArrayList;
//
//import static androidx.constraintlayout.widget.Constraints.TAG;
//
//public class Settings extends Fragment implements setting_adapter.Listener{
//
//    private FirebaseAuth firebaseAuth;
//    private FirebaseUser currentUser;
//    private static final String TAG = "Settings";
//    private RecyclerView recyclerView;
//    private RecyclerView.Adapter adapter;
//    private RecyclerView.LayoutManager layoutManager;
//   final ProgressDialog progressDialog = new ProgressDialog(this.getActivity());
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.settings,null);
//        ArrayList<Model> list = new ArrayList<>();
//
//        firebaseAuth=FirebaseAuth.getInstance();
//        currentUser= FirebaseAuth.getInstance().getCurrentUser();
//
//        Model m = new Model("Personalize",1);
//        Log.d(TAG, "onCreateView: first ");
//        Model m1 = new Model("Change Password",0);
//        Log.d(TAG, "onCreateView: second ");
//        Model m2 = new Model("Clear Favourites",0);
//        Log.d(TAG, "onCreateView: third ");
//        Model m3 = new Model("Information & Support",1);
//        Log.d(TAG, "onCreateView: fourth ");
//        Model m4 = new Model("Terms and Conditions",0);
//        Log.d(TAG, "onCreateView: fifth ");
//        Model n = new Model("Privacy Policy",0);
//        Model m5 = new Model("About Us",0);
//        Log.d(TAG, "onCreateView: sixth ");
//        list.add(m);
//        list.add(m1);
//        list.add(m2);
//        list.add(m3);
//        list.add(m4);
//        list.add(n);
//        list.add(m5);
//        recyclerView = view.findViewById(R.id.settings_recycler_view);
//        layoutManager = new LinearLayoutManager(getActivity());
//        adapter = new setting_adapter(list,this);
//        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setHasFixedSize(true);
//        Log.d(TAG, "onCreateView: before adapter");
//        recyclerView.setAdapter(adapter);
//        Log.d(TAG, "onCreateView: after adapter");
//        return view;
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        view.findViewById(R.id.settings);
//    }
//
//    @Override
//    public void onClicked(int position) {
//
//        //toastMessage(Integer.toString(position));
//
//
//        if(position==1) {
//            customDialogPass();
//            Log.d("Settings","change password selected");
//        }
//
//        else if(position==2)
//            customDialogFav();
//
//        else {
//            Intent intent = new Intent(Settings.this.getActivity(), settings_activity.class);
//            intent.putExtra("position", position);
//            Log.d("Settings", "item selected");
//            startActivity(intent);
//        }
//    }
//
//    private void toastMessage(String message){
//        Toast.makeText(Settings.this.getActivity(),message,Toast.LENGTH_LONG).show();
//    }
//
//
//    public void customDialogPass() {
//
//        final AlertDialog alert;
//        final AlertDialog.Builder builderSingle;
//        builderSingle = new AlertDialog.Builder(getActivity());
//
//        //builderSingle.setIcon(R.drawable.ic_notification);
//
//        LayoutInflater inflater = requireActivity().getLayoutInflater();
//        View dialogView=inflater.inflate(R.layout.change_pass_dialog,null);
//        builderSingle.setView(dialogView);
//        alert=builderSingle.create();
//
//
//        final EditText email_add=(EditText) dialogView.findViewById(R.id.email);
//        Button cancel=(Button) dialogView.findViewById(R.id.btn_cancel);
//        Button reset_pass=(Button) dialogView.findViewById(R.id.reset_pass);
//
//
//
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                alert.dismiss();
//            }
//        });
//
//        reset_pass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                progressDialog.setMessage("Sending password reset email");
//                final String emailAdd = email_add.getText().toString();
//
//                if(email_add!=null) {
//                    firebaseAuth.sendPasswordResetEmail(emailAdd).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if(task.isSuccessful()){
//                                progressDialog.dismiss();
//                                toastMessage("Password sent to your email");
//                            }
//                            else
//                            {
//                                toastMessage(task.getException().getMessage());
//                            }
//                        }
//                    });
//
//                }
//
//                else{
//                    toastMessage("Please enter your email address");
//                }
//            }
//        });
//
//        alert.show();
//
//    }
//
//
//    public void customDialogFav()
//    {
//
//        final AlertDialog alert2;
//        final AlertDialog.Builder builderSingle2;
//        builderSingle2 = new AlertDialog.Builder(getActivity());
//
//        LayoutInflater inflater = requireActivity().getLayoutInflater();
//        View dialogView=inflater.inflate(R.layout.clear_favs_dialog,null);
//        builderSingle2.setView(dialogView);
//        alert2=builderSingle2.create();
//
//        Button cancel2=(Button) dialogView.findViewById(R.id.btn2_cancel);
//        Button okay=(Button) dialogView.findViewById(R.id.ok);
//
//        cancel2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                alert2.dismiss();
//            }
//        });
//
//        okay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                progressDialog.setMessage("Deleting favourites");
//                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Users/"+
//                        currentUser.getDisplayName());
//                databaseReference.child("favs").removeValue();
//                progressDialog.dismiss();
//                toastMessage("Favourites cleared");
//
//
//            }
//        });
//
//        alert2.show();
//
//    }
//
//
//
//}
//
//
//
//
package com.project.rentaway;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Settings extends Fragment implements setting_adapter.Listener{

    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    private static final String TAG = "Settings";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings,null);
        ArrayList<Model> list = new ArrayList<>();

        firebaseAuth=FirebaseAuth.getInstance();
        currentUser= FirebaseAuth.getInstance().getCurrentUser();

        Model m = new Model("Personalize",1);
        Log.d(TAG, "onCreateView: first ");
        Model m1 = new Model("Change Password",0);
        Log.d(TAG, "onCreateView: second ");
        Model m2 = new Model("Clear Favourites",0);
        Log.d(TAG, "onCreateView: third ");
        Model m3 = new Model("Information & Support",1);
        Log.d(TAG, "onCreateView: fourth ");
        Model m4 = new Model("Terms and Conditions",0);
        Log.d(TAG, "onCreateView: fifth ");
        Model n = new Model("Privacy Policy",0);
        Model m5 = new Model("About Us",0);
        Log.d(TAG, "onCreateView: sixth ");
        list.add(m);
        list.add(m1);
        list.add(m2);
        list.add(m3);
        list.add(m4);
        list.add(n);
        list.add(m5);
        recyclerView = view.findViewById(R.id.settings_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new setting_adapter(list,this);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        Log.d(TAG, "onCreateView: before adapter");
        recyclerView.setAdapter(adapter);
        Log.d(TAG, "onCreateView: after adapter");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.settings);
    }

    @Override
    public void onClicked(int position) {

        //toastMessage(Integer.toString(position));


        if(position==1) {
            customDialogPass();
            Log.d("Settings","change password selected");
        }

        else if(position==2)
            customDialogFav();

        else {
            Intent intent = new Intent(Settings.this.getActivity(), settings_activity.class);
            intent.putExtra("position", position);
            Log.d("Settings", "item selected");
            startActivity(intent);
        }
    }

    private void toastMessage(String message){
        Toast.makeText(Settings.this.getActivity(),message,Toast.LENGTH_LONG).show();
    }


    public void customDialogPass() {

        final AlertDialog alert;
        final AlertDialog.Builder builderSingle;
        builderSingle = new AlertDialog.Builder(getActivity());

        //builderSingle.setIcon(R.drawable.ic_notification);

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView=inflater.inflate(R.layout.change_pass_dialog,null);
        builderSingle.setView(dialogView);
        alert=builderSingle.create();


        final EditText email_add=(EditText) dialogView.findViewById(R.id.email);
        Button cancel=(Button) dialogView.findViewById(R.id.btn_cancel);
        Button reset_pass=(Button) dialogView.findViewById(R.id.reset_pass);



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });

        reset_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String emailAdd=email_add.getText().toString();

                if(email_add!=null) {
                    firebaseAuth.sendPasswordResetEmail(emailAdd).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){
                                toastMessage("Password sent to your email");
                                alert.dismiss();
                            }

                            else
                            {
                                toastMessage(task.getException().getMessage());
                            }

                        }
                    });

                }

                else{
                    toastMessage("Please enter your email address");
                }
            }
        });

        alert.show();

    }


    public void customDialogFav()
    {

        final AlertDialog alert2;
        final AlertDialog.Builder builderSingle2;
        builderSingle2 = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView=inflater.inflate(R.layout.clear_favs_dialog,null);
        builderSingle2.setView(dialogView);
        alert2=builderSingle2.create();

        Button cancel2=(Button) dialogView.findViewById(R.id.btn2_cancel);
        Button okay=(Button) dialogView.findViewById(R.id.ok);

        cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert2.dismiss();
            }
        });

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Users/"+
                        currentUser.getDisplayName());
                databaseReference.child("favs").removeValue();
                toastMessage("Favourites cleared");
                alert2.dismiss();

            }
        });

        alert2.show();

    }



}




