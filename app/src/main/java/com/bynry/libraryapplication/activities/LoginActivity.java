package com.bynry.libraryapplication.activities;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bynry.libraryapplication.R;
import com.bynry.libraryapplication.db.DatabaseManager;
import com.bynry.libraryapplication.utilities.AppConstants;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtUSerName, edtPassword;
    private Button btnLogin;
    private TextView txtForgetPassword, txtSignUp, txtUserNameLabel, txtPasswordLabel;
    public String userName, password;
    private int j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUSerName = findViewById(R.id.edt_user_name);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        txtForgetPassword = findViewById(R.id.txt_forget_password);
        txtSignUp = findViewById(R.id.txt_sign_up);
        txtUserNameLabel = findViewById(R.id.txt_user_name_label);
        txtPasswordLabel = findViewById(R.id.txt_password_label);

        btnLogin.setOnClickListener(this);
        txtSignUp.setOnClickListener(this);
        txtForgetPassword.setOnClickListener(this);

        j = 0;
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogin){
            if (!edtUSerName.getText().toString().trim().isEmpty()){
                if (!edtPassword.getText().toString().trim().isEmpty()) {
                    ArrayList<LoginActivity> data = new ArrayList<>();
                    data = DatabaseManager.getData();
                    for (int i = 0; i < data.size(); i++) {
                        if (edtUSerName.getText().toString().trim().equals(data.get(i).userName)) {
                            if (edtPassword.getText().toString().trim().equals(data.get(i).password)) {
                                Toast.makeText(this, R.string.you_have_logged_in_successfully, Toast.LENGTH_SHORT).show();
                                j = 1;
                                Intent intent = new Intent(this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                    if (j == 0)
                        Toast.makeText(this, R.string.please_enter_correct_username_and_password, Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(this, R.string.enter_password, Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, R.string.enter_user_name, Toast.LENGTH_SHORT).show();
        }
        else
            if (view == txtForgetPassword){
                final Dialog dialog = new Dialog(getApplicationContext());
                dialog.setContentView(R.layout.dialog);
                dialog.setTitle("");
               /* TextView txtUserName = dialog.findViewById(R.id.txt_user_name);
                EditText edtUserName = dialog.findViewById(R.id.edt_enter_user_name);*/
                Button btnOk = dialog.findViewById(R.id.btn_ok);

                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
        }
        else
            if (view == txtSignUp){
                        Intent intent = new Intent(this, SignUpActivity.class);
                        startActivity(intent);
                        finish();
            }
    }
}
