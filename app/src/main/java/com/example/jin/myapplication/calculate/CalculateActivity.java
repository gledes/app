package com.example.jin.myapplication.calculate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jin.myapplication.R;

public class CalculateActivity extends AppCompatActivity {


    private EditText pokerText1;

    private EditText pokerText2;

    private EditText pokerText3;

    private EditText pokerText4;

    private TextView show_result;

    String operator[] = {"-", "+", "/", "*"};

    int arr[] = new int[10];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        pokerText1 = findViewById(R.id.poker_text1);
        pokerText2 = findViewById(R.id.poker_text2);
        pokerText3 = findViewById(R.id.poker_text3);
        pokerText4 = findViewById(R.id.poker_text4);
        show_result = findViewById(R.id.show_result);

    }

    private float oper(int f, float m, float n) {
        if (f == 3) {
            return (m * n);
        }
        if (f == 2) {
            return (m / n);
        }
        if (f == 1) {
            return m + n;
        }
        if (f == 0) {
            return (m - n);
        }
        return 0;
    }

    private void tb(int i1, int i2, int i3, int i4) {
        arr[1] = i1;
        arr[2] = i2;
        arr[4] = i3;
        arr[8] = i4;
    }

    public String cacl(int i1, int i2, int i3, int i4) {
        tb(i1, i2, i3, i4);
        StringBuilder sb = new StringBuilder();
        float m = 0f;
        for (i1 = 1; i1 <= 8; i1 *= 2) {
            for (i2 = 1; i2 <= 8; i2 *= 2) {
                for (i3 = 1; i3 <= 8; i3 *= 2) {
                    for (i4 = 1; i4 <= 8; i4 *= 2) {

                        if ((i1 | i2 | i3 | i4) != 0xf) {
                            continue;
                        }

                        for (int f1 = 0; f1 <= 3; f1++) {
                            for (int f2 = 0; f2 <= 3; f2++) {
                                for (int f3 = 0; f3 <= 3; f3++) {
                                    m = oper(f3, oper(f2, oper(f1, arr[i1], arr[i2]), arr[i3]), arr[i4]);
                                    if (Math.abs(m - 24) < 1e-5) {
                                        String str = "(("+arr[i1]+operator[f1]+arr[i2]+")"+operator[f2]+arr[i3]+")"+operator[f3]+arr[i4]+"\n";
                                        sb.append(str);
                                    }

                                    m = oper(f1, arr[i1], oper(f3, oper(f2, arr[i2], arr[i3]), arr[i4]));
                                    if (Math.abs(m - 24) < 1e-5) {
                                        String str = arr[i1]+operator[f1]+"(("+arr[i2]+operator[f2]+arr[i3]+")"+operator[f3]+arr[i4]+")\n";
                                        sb.append(str);
                                    }

                                    m = oper(f3, oper(f1, arr[i1], oper(f2, arr[i2], arr[i3])), arr[i4]);
                                    if (Math.abs(m - 24) < 1e-5) {
                                        String str = "("+arr[i1]+operator[f1]+"("+arr[i2]+operator[f2]+arr[i3]+"))"+operator[f3]+arr[i4]+"\n";
                                        sb.append(str);
                                    }

                                    m = oper(f1, arr[i1], oper(f2, arr[i2], oper(f3, arr[i3], arr[i4])));
                                    if (Math.abs(m - 24) < 1e-5) {
                                        String str = arr[i1]+operator[f1]+"("+arr[i2]+operator[f2]+"("+arr[i3]+operator[f3]+arr[i4]+"))\n";
                                        sb.append(str);
                                    }

                                    m = oper(f2, oper(f1, arr[i1], arr[i2]), oper(f3, arr[i3], arr[i4]));
                                    if (Math.abs(m - 24) < 1e-5) {
                                        String str = "("+arr[i1]+operator[f1]+arr[i2]+")"+operator[f2]+"("+arr[i3]+operator[f3]+arr[i4]+")\n";
                                        sb.append(str);
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }

        return sb.toString();
    }

    public void calc(View view) {
        try {
            int text1 = Integer.parseInt(pokerText1.getText().toString());
            int text2 = Integer.parseInt(pokerText2.getText().toString());
            int text3 = Integer.parseInt(pokerText3.getText().toString());
            int text4 = Integer.parseInt(pokerText4.getText().toString());
            String result = cacl(text1, text2, text3, text4);
            show_result.setText(result);
        } catch (Exception e) {
            Toast.makeText(this,"请输入数字", Toast.LENGTH_SHORT).show();
        }
    }
}
