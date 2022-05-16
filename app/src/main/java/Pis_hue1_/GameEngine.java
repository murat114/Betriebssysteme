package Pis_hue1_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class GameEngine implements Game {

    private int[][] board;
    private int current_player;
    private int player_1;
    private int player_2;
    private int moveCounter;
    private boolean isGameOver;
    private int timer;

    GameEngine() {
        this.board = new int[6][7];
        this.player_1 = 1;
        this.player_2 = 2;
        this.current_player = player_1;
        this.moveCounter = 0;
        this.isGameOver = false;
        this.timer = 5;
    }

    public int[][] getBoard() {
        return board;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public int getCurrent_player() {
        current_player = (moveCounter & 1) == 0 ? player_1 : player_2;
        return current_player;
    }

    public void setBoard(int[][] array) {
        this.board = array;
    }


    public boolean getIsGameOver() {
        return isGameOver;
    }

    public int getMoveCounter() {
        return moveCounter;
    }

    public int getBoardIndex(int index1, int index2) {
        return this.board[index1][index2];
    }

    public void move(int number) {

        ArrayList<Integer> moveList = moveList();
        if (!moveList.contains(number)) {
            System.out.println("Du kannst da keine Steine hinsetzen");
            return;
        }


        AtomicInteger counter = new AtomicInteger(); //dafür da um einen variablen der final ist zu verändern, um mein forEach zu breaken
        Arrays.stream(board).forEach(x -> { //kopiere jedes element aus meiner Liste /forEach
            if (x[number] == 0 && counter.get() == 0) {
                x[number] = getCurrent_player();// ich schaue hier nach ob x[number] == 0 ist, wenn ja kann ich ein Stein setzen
                counter.incrementAndGet(); // erhöhe mein counter um 1, counter ist um zu breaken da, die methode ist da um ein stein zu setzten

            }
        });

        if (gameOver()) {
            System.out.println("Player " + getCurrent_player() + " hat gewonnen");
            isGameOver = true;
            return;
        }
        if (moveCounter == 42) {
            System.out.println("Draw");
        }
        setTimer(10);
        moveCounter++;

    }

    public boolean gameOver() {
        return checkWinner();
    }

    public boolean checkWinner() {
        current_player = getCurrent_player();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    continue; // überspringe das Feld
                }
                if (j + 3 < board[0].length && board[i][j] == current_player && board[i][j + 1] == current_player && board[i][j + 2] == current_player && board[i][j + 3] == current_player) {
                    return true; // check die Felder rechts ab, ab da wo ich den stein gesetzt habe
                }
                if (i + 3 < board.length && board[i][j] == current_player && board[i + 1][j] == current_player && board[i + 2][j] == current_player && board[i + 3][j] == current_player) {
                    return true; // check die felder oben ab, ab da wo ich den stein gesetzt habe
                }
                if (j + 3 < board[0].length && i + 3 < board.length && board[i][j] == current_player && board[i + 1][j + 1] == current_player && board[i + 2][j + 2] == current_player && board[i + 3][j + 3] == current_player) {
                    return true; //check die felder oben rechts ab, ab da wo ich den stein gesetzt habe
                }
                if (j - 3 >= 0 && i + 3 < board.length && board[i][j] == current_player && board[i + 1][j - 1] == current_player && board[i + 2][j - 2] == current_player && board[i + 3][j - 3] == current_player) {
                    return true;
                }
            }
        }
        return false;
    }

    public void randomMove() {
        ArrayList<Integer> list = moveList();

        Random rd = new Random(); // erzeugen random Objekt
        int num = list.get(rd.nextInt(list.size())); // Wir wählen irgendeinen random wert zu unserer Liste
        move(num);// rufen die Methode move auf und mache einen random move

    }

    public ArrayList<Integer> moveList() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (board[j][i] == 0) {
                    list.add(i);
                    break;
                }
            }
        }
        return list;
    }


    public String toString() {
        String s = " ";
        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = 0; j < 7; j++) {
                s += board[i][j];
                s += " ";
            }
            s += " \n ";
        }
        return s;
    }
}