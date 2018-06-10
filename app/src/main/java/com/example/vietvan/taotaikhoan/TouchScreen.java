package com.example.vietvan.taotaikhoan;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TouchScreen extends AppCompatActivity {

    private static final String TAG = "TAG";
    @BindView(R.id.tv_touch)
    TextView tvTouch;
    @BindView(R.id.tv_time)
    TextView tvTime;
    int time = 5, stic = 3;
    public int posx = 0, posy = 0;
    public static String password = "";
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_screen);
        ButterKnife.bind(this);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "NABILA.TTF");
        tvTouch.setTypeface(typeface);

        if(Common.is == 2){
            user = new DatabaseManager(this).access(getIntent().getStringExtra("username"));

            if(user == null){
                finish();
            }

        }

        stic = 3;
        reset();

    }

    public void reset(){
        time = 5;
        posx = posy = 0;
        password = "";

        load();

    }

    public void load(){
        tvTime.setText(time + "");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(time == 5){
                    posx = x;
                    posy = y;
                }
                else{
                    if(posy < y){
                        password += "B";
                    }
                    else if(posy == y){

                    }
                    else{
                        password += "T";
                    }

                    if(posx < x){
                        password += "R";
                    }
                    else if(posx == x){

                    }
                    else{
                        password += "L";
                    }
                }
                posx = x; posy = y;
                time--;
                load();
                if(time == 0){

                    if(Common.is == 1){

                        finish();

                    }
                    else{

                        if(new DatabaseManager(TouchScreen.this).checkPassTouch(user.getUsername(), password)){

                            Toast.makeText(this, "Sign In Successfull~", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(TouchScreen.this, Home.class);
                            intent.putExtra("user", user);
                            startActivity(intent);
                            finish();

                        }
                        else{

                            if(stic == 1){

                                Toast.makeText(this, "PassTouch has failed 3 times. Go to Sign In with Default Password!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(TouchScreen.this, LogIn.class);
                                intent.putExtra("username", user.getUsername());
                                startActivity(intent);
                                finish();

                            }
                            else{

                                Toast.makeText(this, "Passtouch has failed " + (3 - stic + 1) + " times", Toast.LENGTH_SHORT).show();
                                reset();

                            }

                            stic--;

                        }

                    }

                }
                break;
        }
        return false;
    }


}
