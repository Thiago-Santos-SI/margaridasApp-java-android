package com.example.sqlitemarg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class telaCalculoActivity extends AppCompatActivity {


    Button btnVoltar;
    ListView spinner2;

    BancoDados db = new BancoDados(this);

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    EditText txtCustoTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calcular);

        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        txtCustoTotal = (EditText) findViewById(R.id.txtCustoTotal);


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent e = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(e);
            }
        });

        spinner2 = (ListView) findViewById(R.id.spinner2);
        spinnerMateriais();


        spinner2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String conteudo = (String) spinner2.getItemAtPosition(i);
                String id = conteudo.substring(0, conteudo.indexOf("-"));
                id = id.trim();

                Materiais materiais = db.selecionarMaterial(Integer.parseInt(id));

                txtCustoTotal.setText(String.valueOf( materiais.getPreco()));

            }


        });


    }
    public void spinnerMateriais(){

        List<Materiais> materiais = db.listaTodosMateriais();

        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(telaCalculoActivity.this, android.R.layout.simple_list_item_1, arrayList);

        spinner2.setAdapter(adapter);

        for(Materiais m : materiais){
            // teste de listar todos ->   Log.d("Lista", "\nID: " + m.getId() + " Nome: " + m.getNome());
            arrayList.add(m.getId() + " - " + m.getNome()+ " | " +" "+ "Preço: " + m.getPreco() + " | " + " Unidade: " + m.getUnidade_medida());
            adapter.notifyDataSetChanged();

        }
    }

}
