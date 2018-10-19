package com.firstapp.quyen.test;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{
    ImageButton imgbtnPlay;
    ImageButton imgbtnReset;
    CheckBox chkAnimal1;
    CheckBox chkAnimal2;
    CheckBox chkAnimal3;
    SeekBar seekBarAnimal1;
    SeekBar seekBarAnimal2;
    SeekBar seekBarAnimal3;
    Random random;
    private int valueAnimal1;
    private int valueAnimal2;
    private int valueAnimal3;
    CountDownTimer countdowntimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgbtnPlay = findViewById(R.id.imagebuttonPlay);
        imgbtnReset = findViewById(R.id.imagebuttonReset);
        chkAnimal1 = findViewById(R.id.chkAnimal_1);
        chkAnimal2 = findViewById(R.id.chkAnimal_2);
        chkAnimal3 = findViewById(R.id.chkAnimal_3);
        seekBarAnimal1 = findViewById(R.id.seekBarAnimal_1);
        seekBarAnimal2 = findViewById(R.id.seekBarAnimal_2);
        seekBarAnimal3 = findViewById(R.id.seekBarAnimal_3);
        countdowntimer = new CountDownTimer(60000,500) {
            @Override
            public void onTick(long millisUntilFinished) {
                valueAnimal1+=random.nextInt(9)+1;
                valueAnimal2+=random.nextInt(9)+2;
                valueAnimal3+=random.nextInt(9)+3;

                seekBarAnimal1.setProgress(valueAnimal1);
                seekBarAnimal2.setProgress(valueAnimal2);
                seekBarAnimal3.setProgress(valueAnimal3);

                if(valueAnimal1 >= seekBarAnimal1.getMax() ) {
                    String str = chkAnimal1.isChecked() ? "Dog winned and your choice is right" : "Dog winned and your choice is wrong";
                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                    countdowntimer.cancel();
                }
                else if(valueAnimal2 >= seekBarAnimal1.getMax())
                {
                    String str = chkAnimal2.isChecked() ? "Rabbit winned and your choice is right" : "Rabbit winned and your choice is wrong";
                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                    countdowntimer.cancel();
                }
                else if(valueAnimal3 >= seekBarAnimal1.getMax())
                {
                    String str = chkAnimal3.isChecked() ? "Turtle winned and your choice is right" : "Turtle winned and your choice is wrong";
                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                    countdowntimer.cancel();
                }
            }

            @Override
            public void onFinish() {

            }
        };
        imgbtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!chkAnimal1.isChecked() && !chkAnimal2.isChecked() && !chkAnimal3.isChecked())
                {
                    Toast.makeText(MainActivity.this, "Checkbox has not been checked", Toast.LENGTH_SHORT).show();
                }
                else{
                    random = new Random();
                    imgbtnPlay.setVisibility(View.INVISIBLE);
                    imgbtnPlay.setEnabled(false);
                    imgbtnReset.setVisibility(View.VISIBLE);
                    imgbtnReset.setEnabled(true);
                    countdowntimer.start();


                }
            }
        });

        imgbtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueAnimal1 = 0;
                valueAnimal2 = 0;
                valueAnimal3 = 0;
                seekBarAnimal1.setProgress(valueAnimal1);
                seekBarAnimal2.setProgress(valueAnimal2);
                seekBarAnimal3.setProgress(valueAnimal3);
                imgbtnPlay.setEnabled(true);
                imgbtnPlay.setVisibility(View.VISIBLE);
                imgbtnReset.setEnabled(false);
                imgbtnReset.setVisibility(View.INVISIBLE);
                countdowntimer.cancel();
            }
        });

        chkAnimal1.setOnCheckedChangeListener(this);
        chkAnimal2.setOnCheckedChangeListener(this);
        chkAnimal3.setOnCheckedChangeListener(this);


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.chkAnimal_1:
                if(isChecked) {
                    Log.d("animalOne", "ddd");
                    chkAnimal2.setChecked(false);
                    chkAnimal3.setChecked(false);
                }
                break;
            case R.id.chkAnimal_2:
                if(isChecked) {
                    Log.d("animalTwo", "ddd");
                    chkAnimal1.setChecked(false);
                    chkAnimal3.setChecked(false);
                }
                break;
            case R.id.chkAnimal_3:
                if(isChecked) {
                    Log.d("animalThree", "dddd");
                    chkAnimal1.setChecked(false);
                    chkAnimal2.setChecked(false);
                }
                break;
        }
    }
}
