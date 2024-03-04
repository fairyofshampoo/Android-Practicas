package com.ikariscraft.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextUtils;
import android.util.Patterns;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    EditText editTextName, editTextBirthDate, editTextPhone, editTextEmail, editTextPassword, editTextRepeatPassword;
    Button btnRegister, btnCleanForm;
    TextView lblMissingName, lblMissingBirthDate, lblMissingGender, lblMissingEmail, lblMissingPassword, lblMissingRepeatPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName = findViewById(R.id.editTextName);
        editTextBirthDate = findViewById(R.id.editTextBirthDate);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextRepeatPassword = findViewById(R.id.editTextRepeatPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnCleanForm = findViewById(R.id.btnCleanForm);
        lblMissingName = findViewById(R.id.lblMissingName);
        lblMissingBirthDate = findViewById(R.id.lblMissingBirthday);
        lblMissingEmail = findViewById(R.id.lblMissingEmail);
        lblMissingPassword = findViewById(R.id.lblMissingPassword);
        lblMissingRepeatPassword = findViewById(R.id.lblMissingRepeatPassword);

        btnRegister.setOnClickListener(v -> {
            boolean isNameValid = validateName();
            boolean isBirthDateValid = validateBirthDate();
            boolean isEmailValid = validateEmail();
            boolean isPasswordsValid = validatePasswords();

            if (!isNameValid || !isBirthDateValid || !isEmailValid || !isPasswordsValid) {
                showToast(getString(R.string.missing_fields));
            } else {
                showToast(getString(R.string.registration_successful));
                cleanFields();
            }
        });


        btnCleanForm.setOnClickListener(v->{
            cleanFields();
            showToast(getString(R.string.btn_clean_form_clicked));
        });
    }


    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private boolean validateName() {
        if (TextUtils.isEmpty(editTextName.getText())) {
            lblMissingName.setVisibility(View.VISIBLE);
            return false;
        } else {
            lblMissingName.setVisibility(View.GONE);
        }
        return true;
    }

    private boolean validateBirthDate() {
        String birthDateString = editTextBirthDate.getText().toString();

        if (TextUtils.isEmpty(birthDateString)) {
            lblMissingBirthDate.setVisibility(View.VISIBLE);
            return false;
        } else {

            lblMissingBirthDate.setVisibility(View.GONE);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        try {
            Date birthDate = dateFormat.parse(birthDateString);
            return true;
        } catch (ParseException parseException) {
            lblMissingBirthDate.setText(getString(R.string.invalid_birth_date));
            lblMissingBirthDate.setVisibility(View.VISIBLE);
            return false;
        }
    }

    private boolean validateEmail() {
        if (TextUtils.isEmpty(editTextEmail.getText())) {
            lblMissingEmail.setVisibility(View.VISIBLE);
            return false;
        } else {
            lblMissingEmail.setVisibility(View.GONE);
        }

        if (!isValidEmail(editTextEmail.getText().toString())) {
            lblMissingEmail.setVisibility(View.VISIBLE);
            return false;
        } else {
            lblMissingEmail.setVisibility(View.GONE);
        }
        return true;
    }

    private boolean validatePasswords() {
        if (TextUtils.isEmpty(editTextPassword.getText()) || TextUtils.isEmpty(editTextRepeatPassword.getText())) {
            lblMissingPassword.setVisibility(View.VISIBLE);
            return false;
        } else {
            lblMissingPassword.setVisibility(View.GONE);
        }

        if (!TextUtils.equals(editTextPassword.getText(), editTextRepeatPassword.getText())) {
            lblMissingRepeatPassword.setVisibility(View.VISIBLE);
            return false;
        } else {
            lblMissingRepeatPassword.setVisibility(View.GONE);
        }
        return true;
    }

    private void cleanFields(){
        editTextName.setText("");
        editTextBirthDate.setText("");
        editTextPhone.setText("");
        editTextEmail.setText("");
        editTextPassword.setText("");
        editTextRepeatPassword.setText("");
    }

}