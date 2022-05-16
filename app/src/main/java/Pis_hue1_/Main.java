package Pis_hue1_;

import processing.core.PApplet;


public class Main extends PApplet {

    public static void main(String[] args) {
        PApplet.runSketch(new String[]{""}, new Main());

    }

    GameEngine game = new GameEngine();
    int width = 1000;
    int height = 800;
    int begin;
    int duration = 5;
    int timer;
    int milli;

    public void settings() {
        size(width, height);
    }

    public void setup() {
        begin = millis();

    }

    public void draw() {
        fill(200);
        rect(937, 25, 50, 30);
        fill(0);

        if (!game.getIsGameOver()) {
            if (game.getTimer() > 0) {
                timer = millis();
                milli = (timer - begin) / 1000;
                game.setTimer(duration - milli);
                text(game.getTimer(), 950, 50);
            } else {
                begin = millis();
                game.setTimer(5);
                //System.out.println("timer:" + game.getTimer());
                game.randomMove();
            }
        }

        if (game.getIsGameOver()) {
            background(0);
            textAlign(CENTER);
            fill(255);
            text("Player " + game.getCurrent_player() + " HAS WON!!!", width / 2, height / 2); // wenn 1 gewonnen hat hat 2 gewonnen und umgekehrt
            text("Press R for new GAME", width / 2, 550);
            text("Press E for EXIT the GAME", width / 2, 580);

        } else if (game.getMoveCounter() == 42) {
            background(0);
            textAlign(CENTER);
            fill(255);
            text(" IS DRAW", width / 2, height / 2);
            text("Press R for new GAME", width / 2, 550);
            text("Press E for EXIT the GAME", width / 2, 580);


        } else {
            display();
            fill(255);
            rect(90, 20, 820, 50);
            for (int i = 1; i <= 7; i++) {
                fill(200);
                rect((119 * i), 30, 60, 30);
                fill(0);
                textSize(25);
                textAlign(LEFT);
                text("click", (119 * i), 53);
            }


        }
        if (key == 'e' || key == 'E') {
            exit();
        }


    }

    public void display() {

        for (int i = 0; i < game.getBoard().length; i++) {
            for (int j = 0; j < game.getBoard()[i].length; j++) {
                if (game.getBoard()[i][j] == 0) {
                    fill(255);
                    ellipse((j * 120) + 140, 740 - (120 * i), 100, 100);
                } else if (game.getBoard()[i][j] == 1) {
                    fill(255, 255, 0);
                    ellipse((j * 120) + 140, 740 - (120 * i), 100, 100);
                } else if (game.getBoard()[i][j] == 2) {
                    fill(0, 0, 255);
                    ellipse((j * 120) + 140, 740 - (120 * i), 100, 100);
                }
            }
        }


    }

    public void mousePressed() {
        //println("X : " + mouseX + "Y: " + mouseY);
        if (mouseX >= 120 && mouseX <= 180 && mouseY >= 0 && mouseY <= 80) {
            game.move(0);

        } else if (mouseX >= 240 && mouseX <= 320 & mouseY >= 0 && mouseY <= 80) {
            game.move(1);


        } else if (mouseX >= 355 && mouseX <= 415 && mouseY >= 0 && mouseY <= 80) {
            game.move(2);

        } else if (mouseX >= 475 && mouseX <= 535 && mouseY >= 0 && mouseY <= 80) {
            game.move(3);


        } else if (mouseX >= 590 && mouseX <= 650 && mouseY >= 0 && mouseY <= 80) {
            game.move(4);


        } else if (mouseX >= 710 && mouseX <= 775 && mouseY >= 0 && mouseY <= 80) {
            game.move(5);


        } else if (mouseX >= 830 && mouseX <= 890 && mouseY >= 0 && mouseY <= 80) {
            game.move(6);
        }
        begin = millis();
        game.setTimer(10);
        println(game.toString());
    }

    public void keyPressed() {
        if (key == 'r' || key == 'R') {
            newGame();
        }
    }

    public void newGame() {
        fill(200);
        background(200);
        game = new GameEngine();
        begin = millis();


    }

}

