package com.example.vietvan.taotaikhoan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.hoang8f.widget.FButton;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    @BindView(R.id.btnSignUp)
    FButton btnSignUp;
    @BindView(R.id.btnSignIn)
    FButton btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btnSignUp, R.id.btnSignIn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSignUp:
                startActivity(new Intent(MainActivity.this, SignUp.class));
                break;
            case R.id.btnSignIn:
                startActivity(new Intent(MainActivity.this, SignIn.class));
                break;
        }
    }
}
