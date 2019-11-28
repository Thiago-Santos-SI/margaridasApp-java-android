package com.example.sqlitemarg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

        listViewMateriais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String conteudo = (String) listViewMateriais.getItemAtPosition(i);
                //Toast.makeText(MainActivity.this, "Select: " + conteudo, Toast.LENGTH_LONG).show();
                String id = conteudo.substring(0, conteudo.indexOf("-"));
                Materiais materiais = db.selecionarMaterial(Integer.parseInt(id));

                editId.setText(materiais.getId());
                editNome.setText(materiais.getNome());

                //ERRORS
                editUnidadeMedida.setText(String.valueOf(materiais.getUnidade_medida()));
                editPreco.setText(String.valueOf(materiais.getPreco()));




            }
        });


        //INSERT - teste
        /*
        db.addMaterial(new Materiais(1,"pano",2,1));
        Toast.makeText(MainActivity.this, "salvo com sucesso", Toast.LENGTH_LONG).show();

        db.addMaterial(new Materiais(2,"linha",2,3));
        Toast.makeText(MainActivity.this, "salvo com sucesso", Toast.LENGTH_LONG).show();

        db.addMaterial(new Materiais(3,"tecido",2,2));
        Toast.makeText(MainActivity.this, "salvo com sucesso", Toast.LENGTH_LONG).show();

        db.addMaterial(new Materiais(4,"tecido de sla oq",2,1));
        Toast.makeText(MainActivity.this, "salvo com sucesso", Toast.LENGTH_LONG).show();
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

        List<Materiais> materiais = db.listaTodosMateriais();

        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList);

        listViewMateriais.setAdapter(adapter);

        for(Materiais m : materiais){
            // teste de listar todos ->   Log.d("Lista", "\nID: " + m.getId() + " Nome: " + m.getNome());
            arrayList.add(m.getId() + " - " + m.getNome() +" "+ "Preço: " + m.getPreco() + " Unidade: " + m.getUnidade_medida());
            adapter.notifyDataSetChanged();

        }
    }
}