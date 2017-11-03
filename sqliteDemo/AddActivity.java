package com.example.dell.sqllightdemo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dell.sqllightdemo.Database.DBHandler;
import com.example.dell.sqllightdemo.R;

public class AddActivity extends AppCompatActivity {
    Button btAdd;
    EditText etName;
    DBHandler dbHandler = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        etName = (EditText)findViewById(R.id.etName);
        btAdd = (Button)findViewById(R.id.btAdd);
        Intent i = getIntent();
        final int[] id = {i.getIntExtra("id", 0)};

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Appinfo newinfo = new Appinfo(++id[0],etName.getText()+"");
                dbHandler.addApp(newinfo);
                Intent i1 = new Intent(AddActivity.this,AppDisplayActivity.class);
                startActivity(i1);
            }
        });
    }
}
