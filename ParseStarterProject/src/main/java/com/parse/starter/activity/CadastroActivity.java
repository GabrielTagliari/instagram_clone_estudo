package com.parse.starter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.starter.R;
import com.parse.starter.utils.ParseErros;

public class CadastroActivity extends AppCompatActivity {

    private EditText mTextoUsuario;
    private EditText mTextoEmail;
    private EditText mTextoSenha;
    private Button mBotaoCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        mTextoUsuario = (EditText) findViewById(R.id.et_usuario);
        mTextoEmail = (EditText) findViewById(R.id.et_email);
        mTextoSenha = (EditText) findViewById(R.id.et_senha);

    }

    public void cadastrarUsuario(View view) {

        ParseUser usuario = new ParseUser();
        usuario.setUsername(mTextoUsuario.getText().toString());
        usuario.setEmail(mTextoEmail.getText().toString());
        usuario.setPassword(mTextoSenha.getText().toString());

        usuario.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {

                if (e == null){

                    abrirTelaLogin();

                    Toast.makeText(CadastroActivity.this,
                            "Sucesso ao salvar usuário", Toast.LENGTH_SHORT).show();
                } else {
                    ParseErros parseErros = new ParseErros();
                    String erro = parseErros.getErro(e.getCode());
                    Toast.makeText(CadastroActivity.this, erro, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void abrirTelaLogin() {
        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
