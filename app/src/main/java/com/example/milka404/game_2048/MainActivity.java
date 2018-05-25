package com.example.milka404.game_2048;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

import static java.lang.Integer.parseInt;


public class  MainActivity extends AppCompatActivity {

    SharedPreferences settings;// = getSharedPreferences("Preferences", MODE_PRIVATE);

    Button[][] ButtonArray = new Button[4][4];
    int color = 0xffcdc1b5, score = 0, record = 0;

    String rec = "Record", scr = "Score", set = "Set";

    boolean isMoveRight = false, isMoveLeft = false, isMoveUp = false, isMoveDown = false;

    private Button myLayout;
    private Button Record;
    private Button Score;

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

    void record() {
        if (score > record)
            record = score;
        Record.setText(String.valueOf(record));
    }

    int create_new_cell() {

        int i, j;
        Random rnd = new Random(System.currentTimeMillis());

        while (true) {
            i = rnd.nextInt(4 );
            j = rnd.nextInt(4 );

            if (ButtonArray[i][j].getText() != " ") {
                continue;
            }

            ButtonArray[i][j].setText("2");
            color_change(i, j);
            break;
        }

        return 0;
    }

    void win(CharSequence text, float alpha) {
        myLayout.setAlpha(alpha);
        myLayout.setText(text);
        myLayout.setTextSize(45);
    }

    int color_change(int i, int j) {
        if (ButtonArray[i][j].getText().toString().equals(" ")) {
            ButtonArray[i][j].setBackgroundColor(color);
            return 0xffcdc1b5;
        }
        if (ButtonArray[i][j].getText().toString().equals("2")) {
            ButtonArray[i][j].setBackgroundColor(0xff72ad92);
            return 0xff72ad92;
        }
        if (ButtonArray[i][j].getText().toString().equals("4")) {
            ButtonArray[i][j].setBackgroundColor(0xffa4a54a);
            return 0xffa4a54a;
        }
        if (ButtonArray[i][j].getText().toString().equals("8")) {
            ButtonArray[i][j].setBackgroundColor(0xff608bd1);
            return 0xff608bd1;
        }
        if (ButtonArray[i][j].getText().toString().equals("16")) {
            ButtonArray[i][j].setBackgroundColor(0xffa52940);
            return 0xffa52940;
        }
        if (ButtonArray[i][j].getText().toString().equals("32")) {
            ButtonArray[i][j].setBackgroundColor(0xff96c95a);
            return 0xff96c95a;
        }
        if (ButtonArray[i][j].getText().toString().equals("64")) {
            ButtonArray[i][j].setBackgroundColor(0xff51e269);
            return 0xff51e269;
        }
        if (ButtonArray[i][j].getText().toString().equals("128")) {
            ButtonArray[i][j].setBackgroundColor(0xff7a50ce);
            return 0xff7a50ce;
        }
        if (ButtonArray[i][j].getText().toString().equals("256")) {
            ButtonArray[i][j].setBackgroundColor(0xff351d66);
            return 0xff351d66;
        }
        if (ButtonArray[i][j].getText().toString().equals("512")) {
            ButtonArray[i][j].setBackgroundColor(0xffc148e2);
            return 0xffc148e2;
        }
        if (ButtonArray[i][j].getText().toString().equals("1024")) {
            ButtonArray[i][j].setBackgroundColor(0xff00ff87);
            return 0xff00ff87;
        }
        if (ButtonArray[i][j].getText().toString().equals("2048")) {
            ButtonArray[i][j].setBackgroundColor(0xff00edff);
            win( "Вы выиграли!", (float) 0.5);
            return 0xff00edff;
        }
        return -1;
    }

    private int choose_moving_cells() {

        int dir, flag;

        if (Math.abs(x1 - x2) > Math.abs(y1 - y2)) {
            if (x2 - x1 > 0)
                dir = 1; //right
            else dir = 2; //left
        }
        else {
            if (y2 - y1 > 0)
                dir = 3; //down
            else dir = 4; //up
        }
        flag = 0;
        switch (dir) {
            case 1:
                moving_right(flag);
                return 1;
            case 2:
                moving_left(flag);
                return 2;
            case 3:
                moving_down(flag);
                return 3;
            case 4:
                moving_up(flag);
                return 4;
        }
        return -1;
    }

    void moving_right(int flag) {
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j >= 0; j--) {
                if (ButtonArray[i][j].getText().toString().equals(" ")) {
                    //Toast.makeText(getApplicationContext(), "ignore [" + i + "][" + j + "]", Toast.LENGTH_SHORT).show();
                    continue;
                }
                for (int z = j; z < 3; z++) {
                    if (!ButtonArray[i][z].getText().toString().equals(ButtonArray[i][z + 1].getText().toString())) {
                        if (!ButtonArray[i][z + 1].getText().toString().equals(" "))
                            continue;
                        ButtonArray[i][z + 1].setText(ButtonArray[i][z].getText().toString());
                        color_change(i, z + 1);
                        ButtonArray[i][z].setText(" ");
                        color_change(i, z);
                        flag = 1;

                        isMoveRight = true;

                        isMoveDown = false;
                        isMoveUp = false;
                        isMoveLeft = false;
                    }
                    else {
                        score = score + parseInt(ButtonArray[i][z + 1].getText().toString());
                        ButtonArray[i][z + 1].setText(String.valueOf(parseInt(ButtonArray[i][z + 1].getText().toString()) * 2));
                        Score.setText(String.valueOf(score));
                        //Toast.makeText(getApplicationContext(), "score = " + score, Toast.LENGTH_SHORT).show();
                        color_change(i, z + 1);
                        ButtonArray[i][z].setText(" ");
                        color_change(i, z);
                        record();
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
    }

    void moving_left(int flag) {
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 3; j++) {
                if (ButtonArray[i][j].getText().toString().equals(" ")) {
                    //Toast.makeText(getApplicationContext(), "ignore [" + i + "][" + j + "]", Toast.LENGTH_SHORT).show();
                    continue;
                }
                for (int z = j; z > 0; z--) {
                    if (!ButtonArray[i][z].getText().toString().equals(ButtonArray[i][z - 1].getText().toString())) {
                        if (!ButtonArray[i][z - 1].getText().toString().equals(" "))
                            continue;
                        ButtonArray[i][z - 1].setText(ButtonArray[i][z].getText().toString());
                        color_change(i, z - 1);
                        ButtonArray[i][z].setText(" ");
                        color_change(i, z);
                        flag = 1;

                        isMoveLeft = true;

                        isMoveDown = false;
                        isMoveUp = false;
                        isMoveRight = false;
                    }
                    else {
                        score = score + parseInt(ButtonArray[i][z - 1].getText().toString());
                        ButtonArray[i][z - 1].setText(String.valueOf(parseInt(ButtonArray[i][z - 1].getText().toString()) * 2));
                        Score.setText(String.valueOf(score));
                        color_change(i, z - 1);
                        ButtonArray[i][z].setText(" ");
                        color_change(i, z);
                        record();
                        flag = 1;

                        isMoveLeft = true;

                        isMoveDown = false;
                        isMoveUp = false;
                        isMoveRight = false;
                        break;
                    }

                }
            }
        }
        if (flag == 1 && isMoveLeft)
            create_new_cell();
    }

    void moving_down(int flag) {
        for (int j = 0; j < 4; j++) {
            for (int i = 2; i >= 0; i--) {
                if (ButtonArray[i][j].getText().toString().equals(" ")) {
                    //Toast.makeText(getApplicationContext(), "ignore [" + i + "][" + j + "]", Toast.LENGTH_SHORT).show();
                    continue;
                }
                for (int z = i; z < 3; z++) {
                    if (!ButtonArray[z][j].getText().toString().equals(ButtonArray[z + 1][j].getText().toString())) {
                        if (!ButtonArray[z + 1][j].getText().toString().equals(" "))
                            continue;
                        ButtonArray[z + 1][j].setText(ButtonArray[z][j].getText().toString());
                        color_change(z + 1, j);
                        ButtonArray[z][j].setText(" ");
                        color_change(z, j);
                        flag = 1;

                        isMoveDown = true;

                        isMoveRight = false;
                        isMoveUp = false;
                        isMoveLeft = false;
                    }
                    else {
                        score = score + parseInt(ButtonArray[z + 1][j].getText().toString());
                        ButtonArray[z + 1][j].setText(String.valueOf(parseInt(ButtonArray[z + 1][j].getText().toString()) * 2));
                        Score.setText(String.valueOf(score));
                        color_change(z + 1, j);
                        ButtonArray[z][j].setText(" ");
                        color_change(z, j);
                        record();
                        flag = 1;

                        isMoveDown = true;

                        isMoveRight = false;
                        isMoveUp = false;
                        isMoveLeft = false;
                        break;
                    }

                }
            }
        }
        if (flag == 1 && isMoveDown)
            create_new_cell();
    }

    void moving_up(int flag) {
        for (int j = 0; j < 4; j++) {
            for (int i = 1; i <= 3; i++) {
                if (ButtonArray[i][j].getText().toString().equals(" ")) {
                    //Toast.makeText(getApplicationContext(), "ignore [" + i + "][" + j + "]", Toast.LENGTH_SHORT).show();
                    continue;
                }
                for (int z = i; z > 0; z--) {
                    if (!ButtonArray[z][j].getText().toString().equals(ButtonArray[z - 1][j].getText().toString())) {
                        if (!ButtonArray[z - 1][j].getText().toString().equals(" "))
                            continue;
                        ButtonArray[z - 1][j].setText(ButtonArray[z][j].getText().toString());
                        color_change(z - 1, j);
                        ButtonArray[z][j].setText(" ");
                        color_change(z, j);
                        flag = 1;

                        isMoveUp = true;

                        isMoveRight = false;
                        isMoveDown = false;
                        isMoveLeft = false;
                    }
                    else {
                        score = score + parseInt(ButtonArray[z - 1][j].getText().toString());
                        ButtonArray[z - 1][j].setText(String.valueOf(parseInt(ButtonArray[z - 1][j].getText().toString()) * 2));
                        Score.setText(String.valueOf(score));
                        color_change(z - 1, j);
                        ButtonArray[z][j].setText(" ");
                        color_change(z, j);
                        record();
                        flag = 1;

                        isMoveUp = true;

                        isMoveRight = false;
                        isMoveDown = false;
                        isMoveLeft = false;
                        break;
                    }

                }
            }
        }
        if (flag == 1 && isMoveUp)
            create_new_cell();
    }

    public void Restart(View view) {
        for (int i = 0; i <= 3; i++)
            for (int j = 0; j <= 3; j++) {
                ButtonArray[i][j].setText(" ");
                color_change(i, j);
            }
        score = 0;
        Score.setText(String.valueOf(score));
        win(" ", (float) 0.0);
        create_new_cell();
        create_new_cell();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);//-----
        setContentView(R.layout.activity_main);
        settings = getSharedPreferences("Preferences", MODE_PRIVATE);
        ArrayInit(ButtonArray);
        myLayout = findViewById(R.id.myLayout);
        Score = findViewById(R.id.Score);
        Record = findViewById(R.id.Record);
        //-----------------------------
        record = settings.getInt(rec,record);
        score = settings.getInt(scr ,score);
        Record.setText(String.valueOf(record));
        Score.setText(String.valueOf(score));

        String str = "";
        str = settings.getString(set, str);

        int k = 0;
        String[] arr = str.split(",");
        if (arr.length == 16)
            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++) {
                    ButtonArray[i][j].setText(arr[k]);
                    color_change(i, j);
                    k++;
                }


        //-----------------------------
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
                        choose_moving_cells();
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.putInt(rec, record);
        prefEditor.putInt(scr, score);

        String str = "";

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                //list.add(ButtonArray[i][j].getText().toString());
                if (i == 3 && j == 3)
                    str = str.concat(ButtonArray[i][j].getText().toString());
                else
                    str = str.concat(ButtonArray[i][j].getText().toString() + ",");

        //Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
        prefEditor.putString(set, str);

        prefEditor.apply();

        /*outState.putInt(rec, record);
        outState.putInt(scr, score);*/
        //Toast.makeText(getApplicationContext(), "Меня потрогали dybp", Toast.LENGTH_SHORT).show();
        super.onSaveInstanceState(outState);
    }
}

