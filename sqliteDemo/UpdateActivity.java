package com.example.dell.sqllightdemo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dell.sqllightdemo.Database.DBHandler;
import com.example.dell.sqllightdemo.R;

public class UpdateActivity extends AppCompatActivity {
    Button btupdate,btndelete;
    EditText etName;
    DBHandler dbHandler = new DBHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        etName = (EditText)findViewById(R.id.etName);
        btupdate = (Button)findViewById(R.id.btupdate);
        btndelete = (Button)findViewById(R.id.btdelete);

        Intent i = getIntent();
        final Appinfo updateappinfo = i.getParcelableExtra("data");
        etName.setText(updateappinfo.getAPP_NAME());
        btupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Appinfo newinfo = new Appinfo(updateappinfo.getID(),etName.getText()+"");
                dbHandler.updateApp(newinfo);
                Intent i1 = new Intent(UpdateActivity.this,AppDisplayActivity.class);
                startActivity(i1);
                finish();
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Appinfo newinfo = new Appinfo(updateappinfo.getID(),etName.getText()+"");
                dbHandler.deleteApp(newinfo);
                Intent i1 = new Intent(UpdateActivity.this,AppDisplayActivity.class);
                startActivity(i1);
                finish();
            }
        });

    }
}
