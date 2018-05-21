package com.example.milka404.game_2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

import static java.lang.Integer.parseInt;


public class  MainActivity extends AppCompatActivity {

    Button[][] ButtonArray = new Button[4][4];

    boolean isMoveRight = false, isMoveLeft = false, isMoveUp = false, isMoveDown = false;

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

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                ButtonArray[i][j].setText(" ");

        create_new_cell();
        create_new_cell();
    }

    int create_new_cell() {

        int i, j;
        Random rnd = new Random(System.currentTimeMillis());

        while (true)
        {
            i = rnd.nextInt(4 );
            j = rnd.nextInt(4 );

            if (ButtonArray[i][j].getText() != " ")
            {
                continue;
            }

            ButtonArray[i][j].setText("2");
            break;
        }

        return 0;
    }

    private void moving_cells() {

        int dir, flag;

        if (Math.abs(x1 - x2) > Math.abs(y1 - y2)) {
            if (x2 - x1 > 0)
                dir = 1; //right
            else dir = 2; //left
        }
        else {
            if (y2 - y1 > 0)
                dir = 3;
            else dir = 4;
        }
        flag = 0;
        switch (dir) {
            case 1:
                for (int i = 0; i < 4; i++)
                {
                    for (int j = 2; j >= 0; j--)
                    {
                        if (ButtonArray[i][j].getText().toString() == " ")
                        {
                            //Toast.makeText(getApplicationContext(), "ignore [" + i + "][" + j + "]", Toast.LENGTH_SHORT).show();
                            continue;
                        }
                        for (int z = j; z < 3; z++) {
                            if (ButtonArray[i][z].getText().toString() != ButtonArray[i][z + 1].getText().toString())
                            {
                                ButtonArray[i][z + 1].setText(ButtonArray[i][z].getText().toString());
                                ButtonArray[i][z].setText(" ");
                                flag = 1;

                                isMoveRight = true;

                                isMoveDown = false;
                                isMoveUp = false;
                                isMoveLeft = false;
                            }
                            else
                            {
                                ButtonArray[i][z + 1].setText(String.valueOf(parseInt(ButtonArray[i][z + 1].getText().toString()) * 2));
                                ButtonArray[i][z].setText(" ");
                                flag = 1;

                                isMoveRight = true;

                                isMoveDown = false;
                                isMoveUp = false;
                                isMoveLeft = false;
                                break;
                            }

                        }
                    }
                }
                if (flag == 1 && isMoveRight)
                    create_new_cell();
                break;
            case 2: break;
            case 3: break;
            case 4: break;
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
                        moving_cells();
                       // create_new_cell(ButtonArray);
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

