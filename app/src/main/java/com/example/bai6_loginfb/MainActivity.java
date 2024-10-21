package com.example.bai6_loginfb;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    CheckBox checkBox;
    EditText editTextEmail;
    EditText editTextPassword;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        checkBox = findViewById(R.id.checkBox);
        editTextEmail= findViewById(R.id.edtEmail);
        editTextPassword = findViewById(R.id.edtPass);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    saveInfo();
                }
            }
        });
        getInf();
    }


    private void saveInfo(){
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (checkBox.isChecked()) {
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();


            editor.putString("Email", email);
            editor.putString("Password", password);
            editor.putBoolean("RememberMe", true);
            editor.apply();
        } else {

            editor.clear();
            editor.apply();
        }

    }

    private  void getInf(){

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        boolean isRemembered = sharedPreferences.getBoolean("RememberMe", false);

        if (isRemembered) {
            String savedEmail = sharedPreferences.getString("Email", "");
            String savedPassword = sharedPreferences.getString("Password", "");

            editTextEmail.setText(savedEmail);
            editTextPassword.setText(savedPassword);
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }

    }


}
