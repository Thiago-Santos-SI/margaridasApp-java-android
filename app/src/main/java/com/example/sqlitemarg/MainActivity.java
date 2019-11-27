package com.example.sqlitemarg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    EditText editPreco, editNome, editUnidadeMedida, editId;
    Button btnLimpar, btnAdicionar, btnDeletar;
    ListView listViewMateriais;

    BancoDados db = new BancoDados(this);

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editPreco = (EditText) findViewById(R.id.editPreco);
        editNome = (EditText) findViewById(R.id.editNome);
        editUnidadeMedida = (EditText) findViewById(R.id.editUnidadeMedida);
        editId = (EditText) findViewById(R.id.editId);

        btnLimpar = (Button) findViewById(R.id.btnLimpar);
        btnAdicionar = (Button) findViewById(R.id.btnAdicionar);
        btnDeletar = (Button) findViewById(R.id.btnDeletar);

        listViewMateriais = (ListView) findViewById(R.id.listViewMateriais);
        listarMateriais();


        /* INSERT
        //db.addMaterial(new Materiais("Pano",2,2,1));
        //Toast.makeText(MainActivity.this, "salvo com sucesso", Toast.LENGTH_LONG).show();
        */


        /*  DELETE
        Materiais materiais = new Materiais();
        materiais.setId(3);
        db.DeleteMaterial(materiais);
        Toast.makeText(MainActivity.this, "apagado com sucesso", Toast.LENGTH_LONG).show();
        */

        /*  SELECT
        Materiais materiais = db.selecionarMaterial(3);

        Log.d("Material selecionado", "id: " + materiais.getId() + " Nome: " + materiais.getNome()
                + " preço: " + materiais.getPreco() + " Unidade de Medida: " + materiais.getUnidade_medida());
                */


        /* UPDATE
        Materiais materiais = new Materiais();
        materiais.setId(4);
        materiais.setNome("thiago");///
        materiais.setPreco(2);
        materiais.setUnidade_medida(2);

        db.atualizarMaterial(materiais);

        Toast.makeText(MainActivity.this, "Atualizado com sucesso", Toast.LENGTH_LONG).show();

         */
    } //MINUTO 8:31
    public void listarMateriais(){

    }
}