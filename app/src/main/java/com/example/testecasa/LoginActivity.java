package com.example.testecasa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText txtNome, txtSenha;
    Button logar;
    BdBarbeiro db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        txtNome = findViewById(R.id.txtNomeLogin);
        txtSenha = findViewById(R.id.txtSenhaLogin);
        logar = findViewById(R.id.btnLogar);

        db = new BdBarbeiro(this);

        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome, senha;

                nome = txtNome.getText().toString();
                senha = txtSenha.getText().toString();

                //Chamando metodo "ChecarConta", se ele voltar verdadeiro o login e feito, se não ele manda uma mensagem
                boolean checkConta = db.ChecarConta(nome, senha);

                if(checkConta==true){

                    Toast.makeText(getApplicationContext(), "Entrando...", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivity(intent);

                }else{

                    Toast.makeText(getApplicationContext(), "Errouuuu...", Toast.LENGTH_LONG).show();

                }
            }
        });


    }


}
