package edu.birzeit.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView list;
    private ArrayAdapter<String> adapter;
    private Button addtolist;
    private boolean flag;
    public static final String GSONARRAY = "GSON";

    private Gson gsonArray;
    public  String FLAG = "FLAG";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
//    String[] arrayadapter = {"go to the doctor ", "Study", "News", "Helping nabours "};
    private ArrayList<In_List> list_item =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupSharedPrefs();
        checkPrefs();
        list = findViewById(R.id.list);
        String[] lists =  convert(list_item);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,lists);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index = position;
                TextView textView = ((TextView) view);
                //Toast.makeText(getApplicationContext(),":       "+textView.getPaintFlags(),Toast.LENGTH_LONG).show();

                if (textView.getPaintFlags() != 16) {
                    //   textView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    textView.setPaintFlags(16);//77 under line
                } else if (textView.getPaintFlags() == 16) {
                    //textView.setPaintFlags(textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    textView.setPaintFlags(77);
                }

            }
        });
        addtolist = findViewById(R.id.addtolist);
        addtolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String json = gson.toJson(list_item);

                editor.putString("GSON", json);
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
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
    private String[] convert(ArrayList<In_List> listss){
        String[] list = new String[listss.size()];
        for (int i = 0; i < list.length;i++) {
            list[i] = listss.get(i).toString();
        }
        return list;
    }
}