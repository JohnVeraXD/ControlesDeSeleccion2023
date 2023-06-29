package com.example.controlesdeseleccin2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.controlesdeseleccin2023.Adaptadores.AdaptadorUsuarios;
import com.example.controlesdeseleccin2023.Modelos.Noticias;
import com.example.controlesdeseleccin2023.Modelos.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebService.WebService;
import WebService.Asynchtask;

public class MainActivity4ListaUsuarios extends AppCompatActivity implements Asynchtask {


    ListView lstOpciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity4_lista_usuarios);

        //Data
        lstOpciones = (ListView) findViewById(R.id.listUsuario);
        View header = getLayoutInflater().inflate(R.layout.ly_cabecerausuarios, null);
        lstOpciones.addHeaderView(header);
        Map<String, String> datos = new HashMap<String, String>();

        WebService ws= new WebService(
                "https://reqres.in/api/users",
                datos, MainActivity4ListaUsuarios.this, MainActivity4ListaUsuarios.this);
        ws.execute("GET");


    }

    @Override
    public void processFinish(String result) throws JSONException {

        ArrayList<Usuario> lstUsuarios = new ArrayList<Usuario> ();

        JSONObject JSONlista = new JSONObject(result);
        JSONArray JSONlistaUsuarios= JSONlista.getJSONArray("data");
        lstUsuarios = Usuario.JsonObjectsBuild(JSONlistaUsuarios);
        AdaptadorUsuarios adapatorUsuario = new AdaptadorUsuarios(MainActivity4ListaUsuarios.this, lstUsuarios);
        lstOpciones.setAdapter(adapatorUsuario);
    }
}