package com.example.sqlitemarg;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BancoDados extends SQLiteOpenHelper {


    //--------------------BANCO MATERIAIS---------------------------------------//

    private static final int VERSION_DATABASE = 1;
    private static final String BANCO_MATERIAIS = "bd_materiais";

    private static final String TABELA_MATERIAIS = "bd_materiais";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_PRECO = "preco";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_UNIDADE = "unidade_medida";


    public BancoDados(@Nullable Context context) {
        super(context, BANCO_MATERIAIS, null, VERSION_DATABASE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String QUERY_COLUNA = "CREATE TABLE " + TABELA_MATERIAIS + "("
                + COLUNA_ID + " INTEGER PRIMARY KEY, " + COLUNA_NOME + " TEXT, "
                + COLUNA_PRECO + " INTEGER, " + COLUNA_UNIDADE + " INTEGER) " ;

    db.execSQL(QUERY_COLUNA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //vazio
    }


    //CRUD

    void addMaterial(Materiais materiais){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_ID, materiais.getId());
        values.put(COLUNA_NOME, materiais.getNome());
        values.put(COLUNA_PRECO, materiais.getPreco());
        values.put(COLUNA_UNIDADE, materiais.getUnidade_medida());

        db.insert(TABELA_MATERIAIS,null, values);
        db.close();
    }


}
