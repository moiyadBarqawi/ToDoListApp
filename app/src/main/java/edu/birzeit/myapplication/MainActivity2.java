package edu.birzeit.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    EditText task ;
    EditText actor;
    EditText time ;
    Button back ;
    Button save;
    Button clear;
    private boolean flag;
    public static final String GSONARRAY = "GSON";

    private Gson gsonArray;
    public  String FLAG = "FLAG";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private ArrayList<In_List> list_item =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setupSharedPrefs();
        checkPrefs();
        task = findViewById(R.id.edittask);
        actor=findViewById(R.id.editactor);
        time=findViewById(R.id.editTime);
        save=findViewById(R.id.add);
        clear=findViewById(R.id.clearall);
        back=findViewById(R.id.backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tas= task.getText().toString();
                String act= actor.getText().toString();
                String tim=(time.getText().toString());
                In_List iteamadded= new In_List(tas,act,tim);
                list_item.add(iteamadded);
                Gson gson = new Gson();
                String json = gson.toJson(list_item);

                editor.putString("GSON", json);
                editor.apply();
                if(!flag){
                   editor.putBoolean("FLAG", true);
                    editor.apply();
                }

            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearAllData();
            }
        });

    }
    private void setupSharedPrefs() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
    }

    private void checkPrefs() {
        flag = preferences.getBoolean(FLAG, false);

        if (flag) {
            String g = preferences.getString(GSONARRAY, "");
            gsonArray = new Gson();//used to convert from string to class
            Type listType = new TypeToken<ArrayList<In_List>>(){}.getType();
            list_item = gsonArray.fromJson(g, listType);

        }
    }
    private void ClearAllData(){
        task.setText("");
         actor.setText("");
        time.setText(""); ;
    }
}