package com.daovietgiao.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edtUsername, edtPassword;
    CheckBox cbRemember;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        edtUsername.setText(sharedPreferences.getString("taikhoan",""));
        edtPassword.setText(sharedPreferences.getString("matkhau",""));
        cbRemember.setChecked(sharedPreferences.getBoolean("checked",false));

        AnhXa();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if(username.equals("daovietgiao")&&password.equals("123456")){
                    Toast.makeText(MainActivity.this,"Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    if(cbRemember.isChecked()){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("taikhoan", username);
                        editor.putString("matkhau", password);
                        editor.putBoolean("checked", true);
                        editor.commit();
                    }else{
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("taikhoan");
                        editor.remove("matkhau");
                        editor.remove("checked");
                        editor.commit();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"Lỗi đăng nhập", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void AnhXa(){
        btnLogin = (Button)findViewById(R.id.buttonLogin);
        edtUsername = (EditText)findViewById(R.id.editTextUsername);
        edtUsername = (EditText)findViewById(R.id.editTextPassword);
        cbRemember = (CheckBox) findViewById(R.id.checkBoxRemember);
    }
}