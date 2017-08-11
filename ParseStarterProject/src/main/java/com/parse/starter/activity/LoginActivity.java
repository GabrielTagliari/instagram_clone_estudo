package com.parse.starter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.starter.R;

public class LoginActivity extends AppCompatActivity {

    private EditText mTextoUsuario;
    private EditText mTextoSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mTextoUsuario = (EditText) findViewById(R.id.et_usuario);
        mTextoSenha = (EditText) findViewById(R.id.et_senha);

        //ParseUser.logOut();

        verificarUsuarioLogado();

    }

    private void verificarUsuarioLogado() {
        if (ParseUser.getCurrentUser() != null) {
            abrirTelaPrincipal();
        }
    }

    public void abrirTelaCadastro(View view) {
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
    }

    public void abrirTelaPrincipal() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void logar(View view) {

        String textoUsuario = mTextoUsuario.getText().toString();
        String textoSenha = mTextoSenha.getText().toString();

        ParseUser.logInInBackground(textoUsuario, textoSenha, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e == null) {
                    Toast.makeText(LoginActivity.this,
                            "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                    abrirTelaPrincipal();
                } else {
                    Toast.makeText(LoginActivity.this,
                            "Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
