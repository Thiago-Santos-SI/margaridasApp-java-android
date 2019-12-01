package com.example.sqlitemarg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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


        values.put(COLUNA_NOME, materiais.getNome());
        values.put(COLUNA_PRECO, materiais.getPreco());
        values.put(COLUNA_UNIDADE, materiais.getUnidade_medida());

        db.insert(TABELA_MATERIAIS,null, values);
        db.close();
    }

    void DeleteMaterial(Materiais materiais){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_MATERIAIS, COLUNA_ID + " = ?", new String[] { String.valueOf(materiais.getId())});
        db.close();

    }

    Materiais selecionarMaterial(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_MATERIAIS, new String[] {COLUNA_ID, COLUNA_NOME,
                COLUNA_PRECO, COLUNA_UNIDADE}, COLUNA_ID + "= ?", new String[]
                {String.valueOf(id)}, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Materiais materiais = new Materiais(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Float.parseFloat(cursor.getString(2)),Float.parseFloat(cursor.getString(3)));


        return materiais;
    }


    void atualizarMaterial(Materiais materiais){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();


        values.put(COLUNA_NOME, materiais.getNome());
        values.put(COLUNA_PRECO, materiais.getPreco());
        values.put(COLUNA_UNIDADE, materiais.getUnidade_medida());

        db.update(TABELA_MATERIAIS, values, COLUNA_ID + " =? ",
                new String[]{String.valueOf(materiais.getId())});
    }

    public List<Materiais> listaTodosMateriais(){
        List<Materiais> listaMateriais = new ArrayList<Materiais>();

        String query = "SELECT * FROM " + TABELA_MATERIAIS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()){
            do {
                Materiais materiais = new Materiais();
                materiais.setId(Integer.parseInt(c.getString(0)));
                materiais.setNome(c.getString(1));
                materiais.setUnidade_medida(Float.parseFloat(c.getString(2)));
                materiais.setPreco(Float.parseFloat(c.getString(3)));

                listaMateriais.add(materiais);
            }while (c.moveToNext());
        }
        return listaMateriais;
    }


}
