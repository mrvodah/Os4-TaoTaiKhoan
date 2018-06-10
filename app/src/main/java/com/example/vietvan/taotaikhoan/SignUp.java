package com.example.vietvan.taotaikhoan;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUp extends AppCompatActivity {

    @BindView(R.id.tv_su)
    TextView tvSu;
    @BindView(R.id.edt_su_Username)
    MaterialEditText edtSuUsername;
    @BindView(R.id.edt_su_Password)
    MaterialEditText edtSuPassword;
    @BindView(R.id.edt_su_Passtuoch)
    MaterialEditText edtSuPasstuoch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "NABILA.TTF");
        tvSu.setTypeface(typeface);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!TouchScreen.password.equals("")) {
            edtSuPasstuoch.setText(TouchScreen.password);
        }
    }

    @OnClick({R.id.si_btnpwTouch, R.id.si_btnSignIn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.si_btnpwTouch:
                Common.is = 1;
                startActivity(new Intent(SignUp.this, TouchScreen.class));
                break;
            case R.id.si_btnSignIn:

                if (edtSuUsername.getText().toString().equals("") ||
                        edtSuPassword.getText().toString().equals("") ||
                        edtSuPasstuoch.getText().toString().equals("")) {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {

                    if (new DatabaseManager(SignUp.this).isExists(edtSuUsername.getText().toString())) {
                        Toast.makeText(this, "Account has already exists!", Toast.LENGTH_SHORT).show();
                    } else {
                        new DatabaseManager(SignUp.this).add(edtSuUsername.getText().toString(), edtSuPassword.getText().toString(), edtSuPasstuoch.getText().toString());
                        Toast.makeText(this, "Create Account successfull~", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }

                break;
        }
    }
}
