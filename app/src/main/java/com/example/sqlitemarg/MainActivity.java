package com.example.sqlitemarg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
    Button btnLimpar, btnAdicionar, btnDeletar, btnTelaCalculo, btnVoltar;
    ListView listViewMateriais;

    BancoDados db = new BancoDados(this);

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTelaCalculo = (Button) findViewById(R.id.btnTelaCalculo);
        btnTelaCalculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), telaCalculoActivity.class);
                startActivity(i);
            }
        });


        editPreco = (EditText) findViewById(R.id.editPreco);
        editNome = (EditText) findViewById(R.id.editNome);
        editUnidadeMedida = (EditText) findViewById(R.id.editUnidadeMedida);
        editId = (EditText) findViewById(R.id.editId);

        btnLimpar = (Button) findViewById(R.id.btnLimpar);
        btnAdicionar = (Button) findViewById(R.id.btnAdicionar);
        btnDeletar = (Button) findViewById(R.id.btnDeletar);

        imm = (InputMethodManager) this.getSystemService(Service.INPUT_METHOD_SERVICE);

        listViewMateriais = (ListView) findViewById(R.id.listViewMateriais);
        listarMateriais();

        listViewMateriais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String conteudo = (String) listViewMateriais.getItemAtPosition(i);
                //Toast.makeText(MainActivity.this, "Select: " + conteudo, Toast.LENGTH_LONG).show();
                String id = conteudo.substring(0, conteudo.indexOf("-"));
                id = id.trim();
                Materiais materiais = db.selecionarMaterial(Integer.parseInt(id));

                //LEMBRAR SEMPRE DE CONVERTER
                editId.setText(String.valueOf(materiais.getId()));
                editNome.setText(materiais.getNome());
                editUnidadeMedida.setText(String.valueOf(materiais.getUnidade_medida()));
                editPreco.setText(String.valueOf(materiais.getPreco()));
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limparCampos();
            }
        });

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = editId.getText().toString();
                //? editId.setText(String.valueOf(id));
                String nome = editNome.getText().toString();
                String unidade = editUnidadeMedida.getText().toString();
                String preco = editPreco.getText().toString();

                if(nome.isEmpty()){
                    editNome.setError("campo obrigatorio!");
                } else if(id.isEmpty()){
                    db.addMaterial(new Materiais(nome, Float.parseFloat(unidade), Float.parseFloat(preco)));

                    Toast.makeText(MainActivity.this, "adicionado com sucesso", Toast.LENGTH_LONG).show();

                    limparCampos();
                    listarMateriais();
                } else {
                    db.atualizarMaterial(new Materiais(Integer.parseInt(id), nome, Float.parseFloat(unidade), Float.parseFloat(preco)));

                    Toast.makeText(MainActivity.this, "Material atualizado com sucesso", Toast.LENGTH_LONG).show();

                    limparCampos();
                    listarMateriais();
                    esconderTeclado();
                }
            }
        });

        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = editId.getText().toString();

                if (id.isEmpty()){
                    Toast.makeText(MainActivity.this, "Nenhum material esta selecionado", Toast.LENGTH_LONG).show();
                } else {
                    Materiais materiais = new Materiais();
                    materiais.setId(Integer.parseInt(id));
                    db.DeleteMaterial(materiais);

                    Toast.makeText(MainActivity.this, "Material deletado com sucesso", Toast.LENGTH_LONG).show();

                    limparCampos();
                    listarMateriais();
                    esconderTeclado();

                }

            }
        });

        //------------------------------------------------------------------------------------------

        //INSERT - teste
        /*
        db.addMaterial(new Materiais("tijolo",2,1));
        Toast.makeText(MainActivity.this, "salvo com sucesso", Toast.LENGTH_LONG).show();

        db.addMaterial(new Materiais("linha",2,3));
        Toast.makeText(MainActivity.this, "salvo com sucesso", Toast.LENGTH_LONG).show();

        db.addMaterial(new Materiais("tecido",2,2));
        Toast.makeText(MainActivity.this, "salvo com sucesso", Toast.LENGTH_LONG).show();

        db.addMaterial(new Materiais("tostelino",2,1));
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
    }

    void esconderTeclado(){
        imm.hideSoftInputFromWindow(editNome.getWindowToken(), 0);
    }

    void limparCampos(){
        editId.setText("");
        editNome.setText("");
        editUnidadeMedida.setText("");
        editPreco.setText("");

        editNome.requestFocus();
    }

    public void listarMateriais(){

        List<Materiais> materiais = db.listaTodosMateriais();

        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList);

        listViewMateriais.setAdapter(adapter);

        for(Materiais m : materiais){
            // teste de listar todos ->   Log.d("Lista", "\nID: " + m.getId() + " Nome: " + m.getNome());
            arrayList.add(m.getId() + " - " + m.getNome()+ " | " +" "+ "Preço: " + m.getPreco() + " | " + " Unidade: " + m.getUnidade_medida());
            adapter.notifyDataSetChanged();

        }
    }
}