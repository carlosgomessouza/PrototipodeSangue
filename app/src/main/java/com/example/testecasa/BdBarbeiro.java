package com.example.testecasa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//Classe do helper, com extends do SQL, voltando como parametro apenas Context, nomenado o database e colocando o numero
//colocando o numero da vesão
public class BdBarbeiro extends SQLiteOpenHelper {
    public BdBarbeiro(@Nullable Context context) {
        super(context, "DBarbearia.sql", null, 1);
    }

    //onCreate éo metodo pra criar a tabela.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table clientes(nome text primary key, senha text, checarSenha)");
    }

    //onUpgrade deleta a tabela antiga e substitui para uma nova, mexendo na versão
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists clientes");

    }

    //Classe "inserir", para colocar os valores na tabela, com ContentValues
    //put inseri os valores, sendo o primeiro valor o nome da coluca e o segundo a string onde sera inserida os valores
    public boolean inserir (String nome, String senha){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put("nome", nome);
        valores.put("senha", senha);

//inserindo, com o comando db.insert, colocando o nome da tabela primeiro, colocando como padrão que não pode ficar vazio
        // e no final o nome da variavel do contentValues
        long inserido = db.insert("clientes", null, valores);
        if(inserido==1){

            return false;
        }else {

            return true;
        }


    }


    //Na hora da validação da conta esse metodo vai usar como base de procura a primary key, se tiver alguma conta
    //com a mesma primary key, ele não cria
    //cursor serve para ele andar pelo banco
    public boolean ValidarConta(String nome){

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from clientes where nome = ?", new String[]{nome});

        if(cursor.getCount()>0){

            return false;
        }else {

            return true;
        }

    }

    //Metodo para fazer login, nesse metodo ele vai andar pelo banco, ver se os dados existem no banco de dados
    //se esxistir, ele tem permissão para entrar na conta
    public boolean ChecarConta(String nome, String senha){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from clientes where nome = ? and senha =?", new String[]{nome, senha});

        if(cursor.getCount()>0){

            return true;
        }else{

            return  false;
        }


    }

}
