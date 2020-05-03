package com.messed.chatappatg.View.Acitivites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.messed.chatappatg.R;

public class LoginPageActivity extends AppCompatActivity {
    Button button;
    EditText e1,e2;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    static final String TAG="LoginPageActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        e1=findViewById(R.id.edt1);
        e2=findViewById(R.id.edt2);
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        //-----userlogedin------------------------//
        if(firebaseAuth.getCurrentUser()!=null)
        {
            Log.e(TAG, "onCreate: "+"user already login" );
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getSupportActionBar().hide();
        button=findViewById(R.id.btn1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

    }
    public void userLogin()
    {
        String email=e1.getText().toString().trim();
        String password=e2.getText().toString().trim();
        if(email.isEmpty())
        {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.isEmpty())
        {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Logining");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.cancel();
                if(task.isSuccessful())
                {
                    finish();
                    Intent intent=new Intent(getBaseContext(),MainActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(LoginPageActivity.this, "Can't Connect Please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
