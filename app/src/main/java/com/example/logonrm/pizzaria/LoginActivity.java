package com.example.logonrm.pizzaria;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.username)
    TextInputLayout username;

    @BindView(R.id.password)
    TextInputLayout password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        username.getEditText().addTextChangedListener((new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                username.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        }));

        password.getEditText().addTextChangedListener((new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                password.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        }));
    }

    @OnClick(R.id.btConectar)
    public void conectar(){

        Boolean controller = true;

        if (validaUsuario() == false){
            controller = false;
        }
        if (validaSenha() == false){
            controller = false;
        }

        if (controller == true){
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("USUARIO", username.getEditText().getText().toString());
            i.putExtra("SENHA", password.getEditText().getText().toString());

            startActivity(i);

            //chamaMain
            //Toast.makeText(this, "vai", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validaUsuario() {
        if (username.getEditText().getText().toString().isEmpty()){
            username.setError("Login não informado");
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }

    private boolean validaSenha() {
        if (password.getEditText().getText().toString().isEmpty()){
            password.setError("Senha não informado");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }
}