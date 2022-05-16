package Pis_hue1_;

import java.util.ArrayList;

public interface Game {

    void move(int number);

    boolean gameOver();

    boolean checkWinner();

    void randomMove();

    ArrayList<Integer> moveList();
}
