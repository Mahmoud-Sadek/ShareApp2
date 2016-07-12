package com.example.shareapp2;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.example.shareapp2.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	EditText message;
	Button Load;
	TextView status;
	String packageName="com.example.shareapp1";	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        message=(EditText) findViewById(R.id.EdTxt);
        status=(TextView) findViewById(R.id.Txt);
        Load=(Button) findViewById(R.id.btn);    
        Load.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				PackageManager packageManager=getPackageManager();
				try {
					ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
					String fullPath=(applicationInfo.dataDir+"/files/test.txt");
					readFile(fullPath);
				} catch (NameNotFoundException e) {
					// TODO Auto-generated catch block
					status.setTextColor(Color.RED);
					status.setText(e.toString());
				}catch (Exception e) {
					status.setTextColor(Color.RED);
					status.setText(e.toString());
				}
							
			}
		});
    }
    
	
    public void readFile(String FullPath){
		FileInputStream fileInputStream=null;
		try {
			fileInputStream=new FileInputStream(new File(FullPath));
			int read=-1;
			StringBuffer buffer=new StringBuffer();
			while ((read=fileInputStream.read())!=-1) {
				buffer.append((char)read);
			}
		//	L.s(this,""+buffer);
			message.setText(buffer);
			status.setTextColor(Color.GREEN);
			status.setText(buffer+"\n was read successfuly from \n "+FullPath);
			
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			status.setTextColor(Color.RED);
			status.setText(e.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			status.setTextColor(Color.RED);
			status.setText(e.toString());
		}finally{
			if(fileInputStream!=null);
			try {
				fileInputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				status.setTextColor(Color.RED);
				status.setText(e.toString());
			}
			
		}
		
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
