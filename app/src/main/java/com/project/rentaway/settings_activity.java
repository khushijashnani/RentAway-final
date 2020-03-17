package com.project.rentaway;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class settings_activity extends AppCompatActivity {

    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
       // toastMessage(bundle.getString("position"));
        switch (bundle.getInt("position")){
            case 4:
                setContentView(R.layout.terms_conditions);
                ScrollView scrollView1= findViewById(R.id.scroll1);
                TextView textView1 = findViewById(R.id.terms);
                TextView textView2 = findViewById(R.id.conditions);

                break;
            case 5:
                setContentView(R.layout.privacy_policy);
                ScrollView scrollView2= findViewById(R.id.scroll2);
                TextView textView3 = findViewById(R.id.pri);
                TextView textView4 = findViewById(R.id.policy);
                break;
            case 6:
                setContentView(R.layout.about_us);
                ScrollView scrollView= findViewById(R.id.scroll3);
                TextView textView5 = findViewById(R.id.aboutus);
                TextView textView6 = findViewById(R.id.us);
                break;

        }
    }
    private void toastMessage(String message){
        Toast.makeText(settings_activity.this,message,Toast.LENGTH_SHORT).show();
    }
}
