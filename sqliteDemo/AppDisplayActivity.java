package com.example.dell.sqllightdemo.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dell.sqllightdemo.Adapter.AppAdapter;
import com.example.dell.sqllightdemo.Database.DBHandler;
import com.example.dell.sqllightdemo.R;

import java.util.ArrayList;
import java.util.List;

public class AppDisplayActivity extends BaseActivity implements AppAdapter.Clickable{
    Button btnAdd,btnDelete,btnUpdate;
    DBHandler dbHandler = new DBHandler(this);
    RecyclerView rvAppInfoDisplay;
    public ArrayList<Appinfo> appinfo = new ArrayList<>();
    AppAdapter appAdapter;
    public int i = 1;
    public static int count = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_display);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        rvAppInfoDisplay = (RecyclerView) findViewById(R.id.rvAppInfoDisplay);

        if(count==1) {
            appdata();
            count++;
        }
        appinfo =  dbHandler.getAllApplication();
        appAdapter = new AppAdapter(appinfo,getApplicationContext(),this);
        // RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        rvAppInfoDisplay.setLayoutManager(layoutManager);
        rvAppInfoDisplay.setItemAnimator(new DefaultItemAnimator());
        rvAppInfoDisplay.setAdapter(appAdapter);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppDisplayActivity.this,AddActivity.class);
                intent.putExtra("id",i);
                startActivity(intent);
            }
        });



    }

    private void appdata() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pkgAppsList = getPackageManager().queryIntentActivities( mainIntent, 0);

        Appinfo newInfo = null;

        for (ResolveInfo ri : pkgAppsList) {
            Log.i("app"+i,ri.activityInfo.packageName);
            if (ri.activityInfo != null) {
                Resources res = null;
                try {
                    res = getPackageManager().getResourcesForApplication(ri.activityInfo.applicationInfo);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                if (ri.activityInfo.labelRes != 0) {
                    newInfo = new Appinfo(i,res.getString(
                            ri.activityInfo.labelRes));


                } else {
                    newInfo = new Appinfo(i,ri.activityInfo.applicationInfo.loadLabel(
                            getPackageManager()).toString());
                    Log.i("app"+i,newInfo.toString());
                }
                dbHandler.addApp(newInfo);

            }

            i++;
        }


    }

    @Override
    public void Click(int position) {
        Appinfo appinfoClick = appinfo.get(position);
        Toast.makeText(getApplicationContext(), appinfoClick.getAPP_NAME() + " is selected!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(AppDisplayActivity.this,UpdateActivity.class);
        i.putExtra("data",appinfoClick);
        startActivity(i);
    }
}
