package com.example.milka404.game_2048;

import android.widget.Button;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;
import com.example.milka404.game_2048.MainActivity;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    static int choose_moving_cells(int x1, int x2, int y1, int y2) {

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
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
        }
        return -1;
    }

    static int color_change(int i, int j, String[][] ButtonArray) {
        if (ButtonArray[i][j].equals(" ")) {
            return 0xffcdc1b5;
        }
        if (ButtonArray[i][j].equals("2")) {
            return 0xff72ad92;
        }
        if (ButtonArray[i][j].equals("4")) {
            return 0xffa4a54a;
        }
        if (ButtonArray[i][j].equals("8")) {
            return 0xff608bd1;
        }
        if (ButtonArray[i][j].equals("16")) {
            return 0xffa52940;
        }
        if (ButtonArray[i][j].equals("32")) {
            return 0xff96c95a;
        }
        if (ButtonArray[i][j].equals("64")) {
            return 0xff51e269;
        }
        if (ButtonArray[i][j].equals("128")) {
            return 0xff7a50ce;
        }
        if (ButtonArray[i][j].equals("256")) {
            return 0xff351d66;
        }
        if (ButtonArray[i][j].equals("512")) {
            return 0xffc148e2;
        }
        if (ButtonArray[i][j].equals("1024")) {
            return 0xff00ff87;
        }
        if (ButtonArray[i][j].equals("2048")) {
            return 0xff00edff;
        }
        return -1;
    }

    @Test
    public void movementRight_isCorrect() {
        int x1 = 10, x2 = 50;
        int y1 = 50, y2 = 45;

        assertEquals(1, choose_moving_cells(x1, x2, y1, y2));
    }

    @Test
    public void movementDown_isNotCorrect() {
        int x1 = 10, x2 = 50;
        int y1 = 50, y2 = 250;

        assertThat(2, is(not(choose_moving_cells(x1, x2, y1, y2)))
        );
    }

    @Test
    public void painting_isCorrect() {
        String[][] ButtonArray = new String[4][4];
        int i = 2, j = 1;
        
        ButtonArray[2][1] = "128";

        assertThat(0xff7a50ce, is(color_change(i, j, ButtonArray)));
    }

    @Test
    public  void  painting_isNotCorrect() {
        String[][] ButtonArray = new String[4][4];
        int i = 2, j = 1;

        ButtonArray[2][1] = " ";

        assertThat(0xffc148e2, is(not(color_change(i, j, ButtonArray))));
    }

}