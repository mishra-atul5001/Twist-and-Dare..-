package com.example.mishr.twistanddare;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView rotate_image;
    Button rotate_btn;
    int angle;
    Random r;
    boolean reset = false;
    private static long back_pressed;
    private String[] my_String_TRUTH,my_String_DARE;
    private String list_TRUTH,list_DARE;
    public static final Random rgenerator = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Resources resources = getResources();
        my_String_TRUTH=resources.getStringArray(R.array.my_Array_TRUTH);
        my_String_DARE = resources.getStringArray(R.array.my_Array_DARE);
        list_TRUTH = my_String_TRUTH[rgenerator.nextInt(my_String_TRUTH.length)];
        list_DARE = my_String_DARE[rgenerator.nextInt(my_String_DARE.length)];
        r= new Random();
        rotate_btn = findViewById(R.id.rotate_button);
        rotate_image = findViewById(R.id.iv_bottle);

        rotate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reset){
                     angle = angle%360;
                    RotateAnimation rotateAnimation = new RotateAnimation(angle,360,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
                    rotateAnimation.setFillAfter(true);
                    rotateAnimation.setDuration(3600);
                    rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                    rotate_image.startAnimation(rotateAnimation);
                    reset = false;
                    rotate_btn.setText("Twist it..!!");
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(120);

                }
                else {
                    angle = r.nextInt(3600)+360;
                    RotateAnimation rotateAnimation = new RotateAnimation(0,angle,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
                    rotateAnimation.setFillAfter(true);
                    rotateAnimation.setDuration(3600);
                    rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                    rotate_image.startAnimation(rotateAnimation);
                    reset = true;
                    rotate_btn.setText("Reset..!!");
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(1520);


                }



            }
        });
       



    }
    @Override
    public void onBackPressed(){
       
        if (back_pressed + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
        }
        else{
            Toast.makeText(getBaseContext(), "Press once again to exit", Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();
        }
    }

    public void Show_Dialog(View view) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Reveal the TRUTH..!!");
        builder1.setCancelable(false);
        builder1.setMessage(my_String_TRUTH[rgenerator.nextInt(my_String_TRUTH.length)]);
        builder1.setPositiveButton("Done..!!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "You have those GUTS..!!", Toast.LENGTH_SHORT).show();
               
            }
        })                                                                        ;

       builder1.setNegativeButton("Failed..!!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Come on bro..!! Be fearless..!!", Toast.LENGTH_SHORT).show();

            }
        })                               ;
        builder1.create().show();

    }

    public void Show_Dialog_DARE(View view) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("DARE is here..!!");
        builder1.setCancelable(false);
        builder1.setMessage(my_String_DARE[rgenerator.nextInt(my_String_DARE.length)]);
        builder1.setPositiveButton("Done..!!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Grown up Boy..!!", Toast.LENGTH_SHORT).show();

            }
        })                                                                        ;

        builder1.setNegativeButton("Failed..!!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Momma's Boy..!!", Toast.LENGTH_SHORT).show();

            }
        })                               ;
        builder1.create().show();
    }
}
