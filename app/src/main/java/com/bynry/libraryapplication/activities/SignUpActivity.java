package com.bynry.libraryapplication.activities;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bynry.libraryapplication.R;
import com.bynry.libraryapplication.db.DatabaseManager;
import com.bynry.libraryapplication.utilities.AppConstants;
import com.bynry.libraryapplication.utilities.AppPreferences;

import java.io.Serializable;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, Serializable{

    private Context context;
    private EditText edtUserName, edtPassword, edtConfirmPassword, edtContactNo, edtEmail, edtAddress;
    private TextView txtUserNameLabel, txtPasswordLabel, txtConfirmPasswordLabel, txtContactNoLabel, txtEmailLabel, txtAddressLabel, txtLogin;
    private RadioButton radioMale, radioFemale;
    private Button btnSignUp;
    public String userName, password, contactNo, email, address, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        context = this;

        edtUserName = findViewById(R.id.edt_user_name);
        edtPassword = findViewById(R.id.edt_password);
        edtConfirmPassword = findViewById(R.id.edt_confirm_password);
        edtContactNo = findViewById(R.id.edt_contact_no);
        edtEmail = findViewById(R.id.edt_email);
        edtAddress = findViewById(R.id.edt_address);

        txtUserNameLabel = findViewById(R.id.txt_user_name_label);
        txtPasswordLabel = findViewById(R.id.txt_password_label);
        txtConfirmPasswordLabel = findViewById(R.id.txt_confirm_password_label);
        txtContactNoLabel = findViewById(R.id.txt_contact_no_label);
        txtEmailLabel = findViewById(R.id.txt_email_label);
        txtAddressLabel = findViewById(R.id.txt_address_label);
        txtLogin = findViewById(R.id.txt_login);

        radioMale = findViewById(R.id.radio_male);
        radioFemale = findViewById(R.id.radio_female);

        btnSignUp = findViewById(R.id.btn_sign_up);

        btnSignUp.setOnClickListener(this);
        txtLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == btnSignUp){
                if (!edtUserName.getText().toString().trim().isEmpty()) {
                    txtUserNameLabel.setVisibility(View.GONE);
                    if (!edtPassword.getText().toString().trim().isEmpty()) {
                        txtPasswordLabel.setVisibility(View.GONE);
                        if (!edtConfirmPassword.getText().toString().trim().isEmpty()) {
                            txtConfirmPasswordLabel.setVisibility(View.GONE);
                            if (!edtContactNo.getText().toString().trim().isEmpty()) {
                                txtContactNoLabel.setVisibility(View.GONE);
                                if (!edtEmail.getText().toString().trim().isEmpty()) {
                                    txtEmailLabel.setVisibility(View.GONE);
                                    if (radioMale.isChecked() || radioFemale.isChecked()) {
                                        if (!edtAddress.getText().toString().trim().isEmpty()) {
                                            txtPasswordLabel.setVisibility(View.GONE);
                                            if (edtPassword.getText().toString().trim().equals(edtConfirmPassword.getText().toString().trim())) {
                                                SignUpActivity data = new SignUpActivity();
                                                data.userName = edtUserName.getText().toString().trim();
                                                data.password = edtPassword.getText().toString().trim();
                                                data.contactNo = edtContactNo.getText().toString().trim();
                                                data.email = edtEmail.getText().toString().trim();
                                                if (radioMale.isChecked())
                                                    data.gender = radioMale.getText().toString().trim();
                                                else
                                                    data.gender = radioFemale.getText().toString().trim();
                                                data.address = edtAddress.getText().toString().trim();
                                                DatabaseManager.saveRegistrationDetails(context, data);
                                                edtUserName.setText("");
                                                edtPassword.setText("");
                                                edtConfirmPassword.setText("");
                                                edtContactNo.setText("");
                                                edtEmail.setText("");
                                                edtAddress.setText("");
                                                radioMale.setChecked(false);
                                                radioFemale.setChecked(false);
                                                Toast.makeText(this, R.string.you_have_registered_successfully, Toast.LENGTH_SHORT).show();
                                            } else
                                                Toast.makeText(this, R.string.password_and_confirm_password_does_not_match, Toast.LENGTH_SHORT).show();
                                        } else
                                            txtAddressLabel.setVisibility(View.VISIBLE);
                                    } else
                                        Toast.makeText(this, R.string.select_gender, Toast.LENGTH_SHORT).show();
                                } else
                                    txtEmailLabel.setVisibility(View.VISIBLE);
                            } else
                                txtContactNoLabel.setVisibility(View.VISIBLE);
                        } else
                            txtConfirmPasswordLabel.setVisibility(View.VISIBLE);
                    } else
                        txtPasswordLabel.setVisibility(View.VISIBLE);
                } else
                    txtUserNameLabel.setVisibility(View.VISIBLE);
        }
        else {
            if (view == txtLogin) {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}