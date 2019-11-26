package com.example.sqlitemarg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText editPreco, editNome, editUnidadeMedida;
    Button btnLimpar, btnAdicionar, btnDeletar;
    ListView listViewMateriais;

    BancoDados db = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editPreco = (EditText)findViewById(R.id.editPreco);
        editNome = (EditText)findViewById(R.id.editNome);
        editUnidadeMedida = (EditText)findViewById(R.id.editUnidadeMedida);

        btnLimpar = (Button) findViewById(R.id.btnLimpar);
        btnAdicionar = (Button) findViewById(R.id.btnAdicionar);
        btnDeletar = (Button) findViewById(R.id.btnDeletar);

        listViewMateriais = (ListView) findViewById(R.id.listViewMateriais);


        //TEXTE//

        db.addMaterial(new Materiais("Pano",2,2));
        Toast.makeText(MainActivity.this, "salvo com sucesso", Toast.LENGTH_LONG).show();

    }
}
