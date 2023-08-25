package com.example.harrypotter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.harrypotter.databinding.ActivityLoginBinding;


public class Login extends AppCompatActivity {
    private EditText correo,pass;
    private ActivityLoginBinding binding;
    private SharedPreferences sharedPreferences;
    public static String SESSION_PREFERENCE ="SESSION_PREFERENCE";
    public static String SESSION_ACTIVATED ="SESSION_ACTIVATED";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences(SESSION_PREFERENCE,MODE_PRIVATE);


        binding.txtCorreo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean ok=isCredentialValidate(s.toString(),binding.txtPass.getText().toString());
                binding.btnLogin.setEnabled(ok);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.txtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean ok=isCredentialValidate(binding.txtCorreo.getText().toString(),s.toString());
                binding.btnLogin.setEnabled(ok);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private boolean isCredentialValidate(String email,String password){
        boolean isEmailok=!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches();
        boolean passwordok=password.length()>=7;
        return isEmailok && passwordok;
    }




    public void Verificar2(View view) {
        String email = binding.txtCorreo.getText().toString();
        String password = binding.txtPass.getText().toString();


        if (email.equals("ejemploe@idat.com.pe") && password.equals("Peru123")) {

            sharedPreferences.edit().putBoolean(SESSION_ACTIVATED,true).apply();
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else
            Toast.makeText(this, "correo o Contraseña incorrecto", Toast.LENGTH_SHORT).show();


    }




}