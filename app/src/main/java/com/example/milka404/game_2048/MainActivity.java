package com.example.milka404.game_2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;


public class  MainActivity extends AppCompatActivity {

    Button[][] ButtonArray = new Button[4][4];

    private LinearLayout myLayout = null;

    private float x1, x2;
    private float y1, y2;

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

        create_new_cell(ButtonArray);
        create_new_cell(ButtonArray);
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

    private void moving_cells() {

        int dir;

        if (Math.abs(x1 - x2) > Math.abs(y1 - y2)) {
            if (x2 - x1 > 0)
                dir = 1;
            else dir = 2;
        }
        else {
            if (y2 - y1 > 0)
                dir = 3;
            else dir = 4;
        }
        
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayInit(ButtonArray);
        myLayout = (LinearLayout) findViewById(R.id.myLayout);

        myLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажатие
                        x1 = event.getX();
                        y1 = event.getY();
                        break;
                    case MotionEvent.ACTION_UP: // отпускание
                        x2 = event.getX();
                        y2 = event.getY();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        Toast.makeText(getApplicationContext(), "Меня потрогалиО_О", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
}

