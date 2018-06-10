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

public class LogIn extends AppCompatActivity {

    @BindView(R.id.tv_lg)
    TextView tvLg;
    @BindView(R.id.edt_lg_Username)
    MaterialEditText edtLgUsername;
    @BindView(R.id.edt_lg_Password)
    MaterialEditText edtLgPassword;
    int time = 3;

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "NABILA.TTF");
        tvLg.setTypeface(typeface);

        time = 3;

        username = getIntent().getStringExtra("username");

        if(username != null){

            edtLgUsername.setText(username);

        }

    }

    @OnClick(R.id.lg_btnSignIn)
    public void onViewClicked() {

        if(edtLgUsername.getText().toString().equals("") ||
                edtLgPassword.getText().toString().equals("")){
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
        else{
            User user = new DatabaseManager(LogIn.this).getUser(edtLgUsername.getText().toString(), edtLgPassword.getText().toString());

            if(user == null){

                if(time == 1){

                    Toast.makeText(this, "You haved wrong Username or Password 3 times. Go to Home!", Toast.LENGTH_SHORT).show();
                    finish();

                }
                else{

                    Toast.makeText(this, "Wrong Username or Password " + (3 - time + 1) + " times", Toast.LENGTH_SHORT).show();

                }

                time--;

            }
            else{

                Intent intent = new Intent(LogIn.this, Home.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();

            }
        }

    }
}
