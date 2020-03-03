package com.example.testecasa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtnome, txtsenha, txtchecarSenha;
    Button cadastrar, login;
    BdBarbeiro db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instanciando o banco de dados
        db = new BdBarbeiro(this);

        txtnome = findViewById(R.id.txtNome);
        txtsenha = findViewById(R.id.txtSenha);
        txtchecarSenha = findViewById(R.id.txtSenhaChecar);

        login = findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        cadastrar = findViewById(R.id.btnCadastrar);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome, senha, checarSenha;

                nome = txtnome.getText().toString();
                senha = txtsenha.getText().toString();
                checarSenha = txtchecarSenha.getText().toString();

                if(nome.equals("") || senha.equals("") || checarSenha.equals("")){

                    Toast.makeText(getApplicationContext(), "Insira valores", Toast.LENGTH_SHORT).show();

                }else{
                    //chamando metodos do Helper, metodo "Validar", para ver se existe algum dado igual ao inserido
                    //caso não tenha o metodo inserir entra em ação
                    if(senha.equals(checarSenha)){
                    boolean validar = db.ValidarConta(nome);

                    if(validar == true){

                        boolean inserir = db.inserir(nome, senha);
                        if (inserir == true){

                            Toast.makeText(getApplicationContext(), "Registro feito com sucesso", Toast.LENGTH_SHORT).show();
                        }}else {

                            Toast.makeText(getApplicationContext(), "E-mail ja existe", Toast.LENGTH_SHORT).show();
                        }
                    }else{

                        Toast.makeText(getApplicationContext(), "Senha não confere", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }
}
