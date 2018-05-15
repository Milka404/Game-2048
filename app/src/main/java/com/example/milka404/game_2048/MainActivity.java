package com.example.milka404.game_2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.Random;


public class  MainActivity extends AppCompatActivity {

    Button[][] ButtonArray = new Button[4][4];

    private void ArrayInit(Button[][] ButtonArray) {

        ButtonArray[0][0] = (Button) findViewById(R.id.Button00);
        ButtonArray[0][1] = (Button) findViewById(R.id.Button01);
        ButtonArray[0][2] = (Button) findViewById(R.id.Button02);
        ButtonArray[0][3] = (Button) findViewById(R.id.Button03);
        ButtonArray[1][0] = (Button) findViewById(R.id.Button10);
        ButtonArray[1][1] = (Button) findViewById(R.id.Button11);
        ButtonArray[1][2] = (Button) findViewById(R.id.Button12);
        ButtonArray[1][3] = (Button) findViewById(R.id.Button13);
        ButtonArray[2][0] = (Button) findViewById(R.id.Button20);
        ButtonArray[2][1] = (Button) findViewById(R.id.Button21);
        ButtonArray[2][2] = (Button) findViewById(R.id.Button22);
        ButtonArray[2][3] = (Button) findViewById(R.id.Button23);
        ButtonArray[3][0] = (Button) findViewById(R.id.Button30);
        ButtonArray[3][1] = (Button) findViewById(R.id.Button31);
        ButtonArray[3][2] = (Button) findViewById(R.id.Button32);
        ButtonArray[3][3] = (Button) findViewById(R.id.Button33);
    }

    static int create_new_cell(Button[][] ButtonArray) {

        int i, j;
        Random rnd = new Random(System.currentTimeMillis());

        while (true)
        {
            i = rnd.nextInt(4 );
            j = rnd.nextInt(4 );

            if (ButtonArray[i][j].getText() != "")
            {
                continue;
            }

            ButtonArray[i][j].setText("2");
            break;
        }

        return 0;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayInit(ButtonArray);
        create_new_cell(ButtonArray);
    }
}

