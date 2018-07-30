package com.example.clientapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class AndFixActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_and_fix);
    }


    public void cal(View view) {
        TextView textView = findViewById(R.id.fix_result);
        Caculutor caculutor = new Caculutor();
        int result = caculutor.cal();
        textView.setText("" + result);


    }

    public void fix(View view) {
//        File file = new File("a.txt");
        File file = new File(this.getApplicationContext().getFilesDir(), "out.dex");
        TextView textView = findViewById(R.id.fix_result);
        if (file.exists()) {
            textView.setText(file.getAbsolutePath());
        } else {
            textView.setText("文件不存在");
        }
        DexManager dexManager = new DexManager(this.getApplicationContext());
        dexManager.loadDex(file);



    }
}
