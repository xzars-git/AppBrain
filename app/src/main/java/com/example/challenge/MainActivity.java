package com.example.challenge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton, button1, button2, button3, button4, restart;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    int truthBlock;
    int point = 0;
    int listPertanyaan = 0;
    TextView resultView, pointView,sumView;
    TextView time;
    RelativeLayout gameRelativeLayout;

    public void  playAgain(View view){

        point = 0;
        listPertanyaan = 0;

        time.setText("30s");
        pointView.setText("0/0");
        resultView.setText("");
        restart.setVisibility(View.INVISIBLE);

        generate();

        new CountDownTimer(10000, 1000){

            @Override
            public void onTick(long timer) {
                time.setText(String.valueOf(timer/1000+ "s"));
            }

            @Override
            public void onFinish() {

                restart.setVisibility(View.VISIBLE);
                time.setText("0s");
                resultView.setText("Skor Anda: "+ Integer.toString(point)+ "/"+ Integer.toString(listPertanyaan));

            }
        }.start();

    }

    public void generate(){

        Random random = new Random();

        int a = random.nextInt(26);
        int b = random.nextInt(26);

        sumView.setText(Integer.toString(a) + " + "+ Integer.toString(b));

        truthBlock = random.nextInt(4);

        answer.clear();

        int wrongAnswer;

        for (int i=0; i<4; i++){

            if (i == truthBlock){

                answer.add(a + b);
            }else {
                wrongAnswer = random.nextInt(51);

                while (wrongAnswer == a + b){

                    wrongAnswer = random.nextInt(51);
                }

                answer.add(wrongAnswer);

            }
        }

        button1.setText(Integer.toString(answer.get(0)));
        button2.setText(Integer.toString(answer.get(1)));
        button3.setText(Integer.toString(answer.get(2)));
        button4.setText(Integer.toString(answer.get(3)));

    }

    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);

        playAgain(findViewById(R.id.playAgain));
    }
    public void chooseAnswer(View view){

        if (view.getTag().toString().equals(Integer.toString(truthBlock))){

            point++;
            resultView.setText("Benar!");
        }else {
            resultView.setText("Salah!");
        }

        listPertanyaan++;
        pointView.setText(Integer.toString(point)+"/"+ Integer.toString(listPertanyaan));
        generate();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        sumView = findViewById(R.id.sumView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        resultView = findViewById(R.id.resultView);
        pointView = findViewById(R.id.pointView);
        time = findViewById(R.id.time);
        restart = findViewById(R.id.playAgain);
        gameRelativeLayout = findViewById(R.id.frameGame);

    }
}