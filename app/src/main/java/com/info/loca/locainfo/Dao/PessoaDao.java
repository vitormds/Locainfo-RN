package com.info.loca.locainfo.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.info.loca.locainfo.Model.Pessoa;

import java.util.ArrayList;

public class PessoaDao extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "DBLocainfo.db";
    private static final int VERSION = 1;
    private static final  String TABELA = "pessoa";
    private static final  String ID = "id";
    private static final String USUARIO = "usuario";
    private static final String SENHA = "senha";
    private static final String NOME = "nome";
    private static final String EMAIL = "email";
    private static final String CEP = "cep";
    private static final String RUA = "rua";
    private static final String BAIRRO = "bairro";
    private static final String CIDADE = "cidade";
    private static final String ESTADO = "estado";
    private static final String NUMERO = "numero";


    public PessoaDao(Context context) {
        super(context, NOME_BANCO, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
       String sql = "CREATE TABLE IF NOT EXISTS "+TABELA+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                " "+USUARIO+" TEXT, "+
                " "+SENHA+" TEXT, "+
                " "+NOME+" TEXT, "+
                " "+EMAIL+" TEXT, "+
                " "+CEP+" TEXT, "+
                " "+RUA+" TEXT, "+
                " "+BAIRRO+" TEXT, "+
                " "+CIDADE+" TEXT, "+
                " "+ESTADO+" TEXT, "+
                " "+NUMERO+" TEXT);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public long salvarPessoa(Pessoa p){
        ContentValues vl = new ContentValues();
        long retornoDB;
        vl.put(USUARIO, p.getUsuario());
        vl.put(SENHA, p.getSenha());
        vl.put(NOME, p.getNome());
        vl.put(EMAIL, p.getEmail());
        vl.put(CEP, p.getCep());
        vl.put(RUA, p.getRua());
        vl.put(BAIRRO, p.getBairro());
        vl.put(CIDADE,p.getCidade());
        vl.put(ESTADO, p.getEstado());
        vl.put(NUMERO, p.getNumero());

        retornoDB = getWritableDatabase().insert(TABELA,null,vl);
        return retornoDB;
    }

    public ArrayList<Pessoa> selectAllPessoas(String lg, String sh){


     String sql = "select * from "+ TABELA+ " where " + USUARIO + " = '" + lg + "'" + " AND " + SENHA + " = '" + sh + "'" ;

       // String sql = "select * from "+ TABELA;
                Cursor cursor = getReadableDatabase().rawQuery(sql,null);
        ArrayList<Pessoa> listapessoa = new ArrayList<Pessoa>();
        while(cursor.moveToNext()){
          Pessoa p = new Pessoa();
          p.setId(cursor.getInt(0));
          p.setUsuario(cursor.getString(1));
          p.setSenha(cursor.getString(2));
          p.setNome(cursor.getString(3));
          p.setEmail(cursor.getString(4));
          p.setCep(cursor.getString(5));
          p.setRua(cursor.getString(6));
          p.setBairro(cursor.getString(7));
          p.setCidade(cursor.getString(8));
          p.setEstado(cursor.getString(9));
          p.setNumero(cursor.getString(10));
          listapessoa.add(p);
        }


        return listapessoa;
    }
}
