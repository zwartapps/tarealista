package com.zwartapps.tarealista;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Declaramos las variables a usar
    private ListView lista;
    private ArrayList<String> nombres;
    private ArrayAdapter<String> adapter;

    //metodo onCreate para iniciar las vistas y poblar la lista con el arrayadapter
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Creamos el arraylist y ponemos los nombres iniciales
        //Definimos el adapter para poner los datos en la listview lista
        nombres = new ArrayList<>();
        nombres.add("Pepe");
        nombres.add("Juanito");
        nombres.add("Maria de la O");

        lista = findViewById(R.id.listanombres);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nombres);

        lista.setAdapter(adapter);
        lista.setTextFilterEnabled(true);
        registerForContextMenu(lista);
    }

    //metodo para crear el menu (por defecto en esta plantilla
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //metodo para añadir nombres, nos saldrá un dialogo de mensaje para introducir el nombre
    public void addNames() {

        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);

        final EditText nombre = view.findViewById(R.id.et_nombre);
        Button buttonAdd = view.findViewById(R.id.b_add);
        Button buttonCancel = view.findViewById(R.id.b_cancelar);

        buttonAdd.setOnClickListener(new View.OnClickListener() {

            //Creamos un String temporal para añadirlo a la lista y para
            // concatenarlo con el mensaje Toast que hemos añadido ese nombre
            @Override
            public void onClick(View v) {
                String temp = nombre.getText().toString();
                nombres.add(temp);
                Toast.makeText(getApplicationContext(), "Añadido " + temp, Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                dialogBuilder.dismiss();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder.setView(view);
        dialogBuilder.show();
    }

    //metodo para indicar que accion hace el menu al selecionar la opcion "Nuevo"
    //si pulsamos Nuevo, se ejecuta el metodo addNames()
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.nuevo) {
            addNames();
        }
        return super.onOptionsItemSelected(item);
    }

    //Creamos el metodo para el menu contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);
    }

    //Creamos String temporal para eliminar el elemento y indicarlo con el toast que fue eliminado
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.Delete:
                String temp = (nombres.get((int) info.id));
                nombres.remove(temp);
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Borrado " + temp, Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
