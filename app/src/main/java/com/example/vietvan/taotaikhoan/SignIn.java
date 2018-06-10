package com.example.vietvan.taotaikhoan;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignIn extends AppCompatActivity {

    @BindView(R.id.tv_si)
    TextView tvSi;
    @BindView(R.id.edt_si_Username)
    MaterialEditText edtSiUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "NABILA.TTF");
        tvSi.setTypeface(typeface);

    }

    @OnClick(R.id.si_btnSignIn)
    public void onViewClicked() {
        
        boolean is = new DatabaseManager(SignIn.this).isExists(edtSiUsername.getText().toString());
        if(is){
            Common.is = 2;
            Intent intent = new Intent(SignIn.this, TouchScreen.class);
            intent.putExtra("username", edtSiUsername.getText().toString());
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "User doesn't exists!", Toast.LENGTH_SHORT).show();
        }
        
    }
}
