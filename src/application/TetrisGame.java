package application;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

// https://www.youtube.com/watch?v=KjEaD0KyL0w&ab_channel=PlayJava
// https://www.youtube.com/watch?v=KD7wHKN22DQ&list=PLHwsL1JI79K2Kw5wf1KOOCn5KIXZ9qybv&ab_channel=Coding_Place

public class TetrisGame extends Application {

    public static final int MOVE = 25;
    public static final int SIZE = 25;
    public static int xMAX = SIZE * 12;
    public static int yMAX = SIZE * 24;
    public static int[][] MESH = new int[xMAX / SIZE][yMAX / SIZE];
    public static Pane groupe = new Pane();
    public static Shape object;
    public static Scene scene = new Scene(groupe, xMAX + 150, yMAX);
    public static int scoreNumber = 0;
    public static int top = 0;
    public static boolean game = true;
    public static Shape nextBlock = Controller.makeRect();
    public static int levelNumber = 0;

    // start the game anc create a screen
    public static void main(String[] args) {
        System.out.println("main");
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        for (int[] a : MESH) {
            Arrays.fill(a, 0);
            System.out.println("start");

        }


        Line line = new Line(xMAX, 0, xMAX, yMAX);

        Text score = new Text("Score: ");
        score.setStyle("-fx-font: 15 arials;");
        score.setX(xMAX + 5);
        score.setY(40);
        score.setFill(Color.GREEN);

        Text level = new Text("Level: ");
        level.setStyle("-fx-font: 15 arials;");
        level.setX(xMAX + 5);
        level.setY(80);
        level.setFill(Color.RED);

        groupe.getChildren().addAll(score, level, line);

        Shape a = nextBlock;

        groupe.getChildren().addAll(a.a, a.b, a.c, a.d);

        moveOnKeyPressed(a);
        object = a;
        nextBlock = Controller.makeRect();

        primaryStage.setScene(scene);
        primaryStage.setTitle(" TETRIS ");
        primaryStage.show();
        System.out.println("tetris");

        Timer fall = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {

                    if (object.a.getY() == 0 || object.b.getY() == 0 || object.c.getY() == 0 || object.d.getY() == 0)
                        // ||
                        top++;
                    else
                        top = 0;

                    if (top == 2) {
                        // game over when you hit the top

                        Text gameOver = new Text("GAME OVER!!");
                        gameOver.setStyle("-fx-font: 70 arial");
                        gameOver.setX(10);
                        gameOver.setY(250);

                        groupe.getChildren().add(gameOver);

                        game = false;
                    }

                    if (top == 15) {
                        System.exit(0);
                    }

                    if (game) {

                        MoveDown(object);
                        score.setText("Score: " + Integer.toString(scoreNumber));
                        level.setText("Level: " + Integer.toString(levelNumber));
                    }

                });
            }
        };
        // speed 330
        fall.schedule(task, 0, 330);

    }

    private void moveOnKeyPressed(Shape shape) {

        scene.setOnKeyPressed(keyEvent -> {
            // setting the key bindings to up,left,right and down.
            switch (keyEvent.getCode()) {

                case RIGHT:
                    Controller.MoveRight(shape);
                    break;

                case DOWN:
                    MoveDown(shape);
                    break;

                case LEFT:
                    Controller.MoveLeft(shape);
                    break;

                case UP:
                    MoveTurn(shape);
                    break;

            }

        });

    }

    public void MoveTurn(Shape shape) {

        int f = shape.shape;
        Rectangle a = shape.a;
        Rectangle b = shape.b;
        Rectangle c = shape.c;
        Rectangle d = shape.d;

        switch (shape.getName()) {
            case "j":
                if (f == 1 && checkBool(a, 1, -1) && checkBool(c, -1, -1) && checkBool(d, -2, -2)) {

                    MoveRight(shape.a);
                    MoveDown(shape.a);

                    MoveDown(shape.c);
                    MoveLeft(shape.c);

                    MoveDown(shape.d);
                    MoveDown(shape.d);
                    MoveLeft(shape.d);
                    MoveLeft(shape.d);
                    shape.changeForm();
                    break;
                }
                if (f == 2 && checkBool(a, -1, -1) && checkBool(c, -1, 1) && checkBool(d, -2, 2)) {

                    MoveDown(shape.a);
                    MoveLeft(shape.a);

                    MoveLeft(shape.c);
                    MoveUp(shape.c);

                    MoveLeft(shape.d);
                    MoveLeft(shape.d);
                    MoveUp(shape.d);
                    MoveUp(shape.d);
                    shape.changeForm();
                    break;
                }
                if (f == 3 && checkBool(a, -1, 1) && checkBool(c, 1, 1) && checkBool(d, 2, 2)) {

                    MoveLeft(shape.a);
                    MoveUp(shape.a);

                    MoveUp(shape.c);
                    MoveRight(shape.c);

                    MoveUp(shape.d);
                    MoveUp(shape.d);
                    MoveRight(shape.d);
                    MoveRight(shape.d);
                    shape.changeForm();
                    break;
                }
                if (f == 4 && checkBool(a, 1, 1) && checkBool(c, 1, -1) && checkBool(d, 2, -2)) {

                    MoveUp(shape.a);
                    MoveRight(shape.a);

                    MoveRight(shape.c);
                    MoveDown(shape.c);

                    MoveRight(shape.d);
                    MoveRight(shape.d);
                    MoveDown(shape.d);
                    MoveDown(shape.d);
                    shape.changeForm();
                    break;
                }
            case "l":
                if (f == 1 && checkBool(a, 1, -1) && checkBool(c, 1, 1) && checkBool(b, 2, 2)) {
                    MoveRight(shape.a);
                    MoveDown(shape.a);

                    MoveUp(shape.b);
                    MoveUp(shape.b);
                    MoveRight(shape.b);
                    MoveRight(shape.b);

                    MoveUp(shape.c);
                    MoveRight(shape.c);

                    shape.changeForm();
                    break;
                }
                if (f == 2 && checkBool(a, -1, -1) && checkBool(b, 2, -2) && checkBool(c, 1, -1)) {
                    MoveDown(shape.a);
                    MoveLeft(shape.a);

                    MoveRight(shape.b);
                    MoveRight(shape.b);
                    MoveDown(shape.b);
                    MoveDown(shape.b);

                    MoveRight(shape.c);
                    MoveDown(shape.c);
                    shape.changeForm();
                    break;
                }
                if (f == 3 && checkBool(a, -1, 1) && checkBool(c, -1, -1) && checkBool(b, -2, -2)) {
                    MoveLeft(shape.a);
                    MoveUp(shape.a);

                    MoveDown(shape.c);
                    MoveLeft(shape.c);

                    MoveDown(shape.b);
                    MoveDown(shape.b);
                    MoveLeft(shape.b);
                    MoveLeft(shape.b);

                    shape.changeForm();
                    break;
                }
                if (f == 4 && checkBool(a, 1, 1) && checkBool(b, -2, 2) && checkBool(c, -1, 1)) {
                    MoveUp(shape.a);
                    MoveRight(shape.a);

                    MoveLeft(shape.b);
                    MoveLeft(shape.b);
                    MoveUp(shape.b);
                    MoveUp(shape.b);

                    MoveLeft(shape.c);
                    MoveUp(shape.c);
                    shape.changeForm();
                    break;
                }

                break;
            case "o":
                // same shape every time
                break;
            case "s":
                if (f == 1 && checkBool(a, -1, -1) && checkBool(c, -1, 1) && checkBool(d, 0, 2)) {
                    MoveDown(shape.a);
                    MoveLeft(shape.a);

                    MoveLeft(shape.c);
                    MoveUp(shape.c);

                    MoveUp(shape.d);
                    MoveUp(shape.d);
                    shape.changeForm();
                    break;
                }
                if (f == 2 && checkBool(a, 1, 1) && checkBool(c, 1, -1) && checkBool(d, 0, -2)) {
                    MoveUp(shape.a);
                    MoveRight(shape.a);

                    MoveRight(shape.c);
                    MoveDown(shape.c);

                    MoveDown(shape.d);
                    MoveDown(shape.d);
                    shape.changeForm();
                    break;
                }
                if (f == 3 && checkBool(a, -1, -1) && checkBool(c, -1, 1) && checkBool(d, 0, 2)) {
                    MoveDown(shape.a);
                    MoveLeft(shape.a);

                    MoveLeft(shape.c);
                    MoveUp(shape.c);

                    MoveUp(shape.d);
                    MoveUp(shape.d);
                    shape.changeForm();
                    break;
                }
                if (f == 4 && checkBool(a, 1, 1) && checkBool(c, 1, -1) && checkBool(d, 0, -2)) {
                    MoveUp(shape.a);
                    MoveRight(shape.a);

                    MoveRight(shape.c);
                    MoveDown(shape.c);

                    MoveDown(shape.d);
                    MoveDown(shape.d);
                    shape.changeForm();
                    break;
                }
                break;
            case "t":
                if (f == 1 && checkBool(a, 1, 1) && checkBool(d, -1, -1) && checkBool(c, -1, 1)) {
                    MoveUp(shape.a);
                    MoveRight(shape.a);

                    MoveDown(shape.d);
                    MoveLeft(shape.d);

                    MoveLeft(shape.c);
                    MoveUp(shape.c);
                    shape.changeForm();
                    break;
                }
                if (f == 2 && checkBool(a, 1, -1) && checkBool(d, -1, 1) && checkBool(c, 1, 1)) {
                    MoveRight(shape.a);
                    MoveDown(shape.a);

                    MoveLeft(shape.d);
                    MoveUp(shape.d);

                    MoveUp(shape.c);
                    MoveRight(shape.c);
                    shape.changeForm();
                    break;
                }
                if (f == 3 && checkBool(a, -1, -1) && checkBool(d, 1, 1) && checkBool(c, 1, -1)) {
                    MoveDown(shape.a);
                    MoveLeft(shape.a);

                    MoveUp(shape.d);
                    MoveRight(shape.d);

                    MoveRight(shape.c);
                    MoveDown(shape.c);
                    shape.changeForm();
                    break;
                }
                if (f == 4 && checkBool(a, -1, 1) && checkBool(d, 1, -1) && checkBool(c, -1, -1)) {
                    MoveLeft(shape.a);
                    MoveUp(shape.a);

                    MoveRight(shape.d);
                    MoveDown(shape.d);

                    MoveDown(shape.c);
                    MoveLeft(shape.c);

                    shape.changeForm();
                    break;
                }
                break;
            case "z":
                if (f == 1 && checkBool(b, 1, 1) && checkBool(c, -1, 1) && checkBool(d, -2, 0)) {
                    MoveUp(shape.b);
                    MoveRight(shape.b);

                    MoveLeft(shape.c);
                    MoveUp(shape.c);

                    MoveLeft(shape.d);
                    MoveLeft(shape.d);
                    shape.changeForm();
                    break;
                }
                if (f == 2 && checkBool(b, -1, -1) && checkBool(c, 1, -1) && checkBool(d, 2, 0)) {
                    MoveDown(shape.b);
                    MoveLeft(shape.b);

                    MoveRight(shape.c);
                    MoveDown(shape.c);

                    MoveRight(shape.d);
                    MoveRight(shape.d);
                    shape.changeForm();
                    break;
                }
                if (f == 3 && checkBool(b, 1, 1) && checkBool(c, -1, 1) && checkBool(d, -2, 0)) {
                    MoveUp(shape.b);
                    MoveRight(shape.b);

                    MoveLeft(shape.c);
                    MoveUp(shape.c);

                    MoveLeft(shape.d);
                    MoveLeft(shape.d);
                    shape.changeForm();
                    break;
                }
                if (f == 4 && checkBool(b, -1, -1) && checkBool(c, 1, -1) && checkBool(d, 2, 0)) {
                    MoveDown(shape.b);
                    MoveLeft(shape.b);

                    MoveRight(shape.c);
                    MoveDown(shape.c);

                    MoveRight(shape.d);
                    MoveRight(shape.d);
                    shape.changeForm();
                    break;
                }
                break;
            case "i":

                if (f == 1 && checkBool(a, 2, 2) && checkBool(b, 1, 1) && checkBool(d, -1, -1)) {
                    MoveUp(shape.a);
                    MoveUp(shape.a);
                    MoveRight(shape.a);
                    MoveRight(shape.a);

                    MoveUp(shape.b);
                    MoveRight(shape.b);

                    MoveDown(shape.d);
                    MoveLeft(shape.d);
                    shape.changeForm();
                    break;
                }
                if (f == 2 && checkBool(a, -2, -2) && checkBool(b, -1, -1) && checkBool(d, 1, 1)) {
                    MoveDown(shape.a);
                    MoveDown(shape.a);
                    MoveLeft(shape.a);
                    MoveLeft(shape.a);

                    MoveDown(shape.b);
                    MoveLeft(shape.b);

                    MoveUp(shape.d);
                    MoveRight(shape.d);
                    shape.changeForm();
                    break;
                }
                if (f == 3 && checkBool(a, 2, 2) && checkBool(b, 1, 1) && checkBool(d, -1, -1)) {
                    MoveUp(shape.a);
                    MoveUp(shape.a);
                    MoveRight(shape.a);
                    MoveRight(shape.a);

                    MoveUp(shape.b);
                    MoveRight(shape.b);

                    MoveDown(shape.d);
                    MoveLeft(shape.d);
                    shape.changeForm();
                    break;
                }
                if (f == 4 && checkBool(a, -2, -2) && checkBool(b, -1, -1) && checkBool(d, 1, 1)) {
                    MoveDown(shape.a);
                    MoveDown(shape.a);
                    MoveLeft(shape.a);
                    MoveLeft(shape.a);

                    MoveDown(shape.b);
                    MoveLeft(shape.b);

                    MoveUp(shape.d);
                    MoveRight(shape.d);
                    shape.changeForm();
                    break;
                }
                break;
        }
    }


    private void RemoveRows(Pane pane) {

        ArrayList<Node> rects = new ArrayList<Node>();
        ArrayList<Integer> lines = new ArrayList<Integer>();
        ArrayList<Node> newrects = new ArrayList<Node>();
        int full = 0;
        for (int i = 0; i < MESH[0].length; i++) {
            for (int j = 0; j < MESH.length; j++) {
                if (MESH[j][i] == 1)
                    full++;
            }
            if (full == MESH.length)
                lines.add(i + lines.size());
            full = 0;
        }
        if (lines.size() > 0)
            do {
                for (Node node : pane.getChildren()) {
                    if (node instanceof Rectangle)
                        rects.add(node);
                }
                scoreNumber += 50;
                levelNumber++;

                for (Node node : rects) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() == lines.get(0) * SIZE) {
                        MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
                        pane.getChildren().remove(node);
                    } else
                        newrects.add(node);
                }

                for (Node node : newrects) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() < lines.get(0) * SIZE) {
                        MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
                        a.setY(a.getY() + SIZE);
                    }
                }
                lines.remove(0);
                rects.clear();
                newrects.clear();
                for (Node node : pane.getChildren()) {
                    if (node instanceof Rectangle)
                        rects.add(node);
                }
                for (Node node : rects) {
                    Rectangle a = (Rectangle) node;
                    try {
                        MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
                rects.clear();
            } while (lines.size() > 0);
    }

    private void MoveDown(Rectangle rect) {
        if (rect.getY() + MOVE < yMAX)
            rect.setY(rect.getY() + MOVE);

    }

    private void MoveRight(Rectangle rect) {
        if (rect.getX() + MOVE <= xMAX - SIZE)
            rect.setX(rect.getX() + MOVE);
    }

    private void MoveLeft(Rectangle rect) {
        if (rect.getX() - MOVE >= 0)
            rect.setX(rect.getX() - MOVE);
    }

    private void MoveUp(Rectangle rect) {
        if (rect.getY() - MOVE > 0)
            rect.setY(rect.getY() - MOVE);
    }

    private void MoveDown(Shape shape) {
        if (shape.a.getY() == yMAX - SIZE || shape.b.getY() == yMAX - SIZE || shape.c.getY() == yMAX - SIZE
                || shape.d.getY() == yMAX - SIZE || moveA(shape) || moveB(shape) || moveC(shape) || moveD(shape)) {
            MESH[(int) shape.a.getX() / SIZE][(int) shape.a.getY() / SIZE] = 1;
            MESH[(int) shape.b.getX() / SIZE][(int) shape.b.getY() / SIZE] = 1;
            MESH[(int) shape.c.getX() / SIZE][(int) shape.c.getY() / SIZE] = 1;
            MESH[(int) shape.d.getX() / SIZE][(int) shape.d.getY() / SIZE] = 1;
            RemoveRows(groupe);

            Shape a = nextBlock;
            nextBlock = Controller.makeRect();
            object = a;
            groupe.getChildren().addAll(a.a, a.b, a.c, a.d);
            moveOnKeyPressed(a);
        }

        if (shape.a.getY() + MOVE < yMAX && shape.b.getY() + MOVE < yMAX && shape.c.getY() + MOVE < yMAX
                && shape.d.getY() + MOVE < yMAX) {
            int moveA = MESH[(int) shape.a.getX() / SIZE][((int) shape.a.getY() / SIZE) + 1];
            int moveB = MESH[(int) shape.b.getX() / SIZE][((int) shape.b.getY() / SIZE) + 1];
            int moveC = MESH[(int) shape.c.getX() / SIZE][((int) shape.c.getY() / SIZE) + 1];
            int moveD = MESH[(int) shape.d.getX() / SIZE][((int) shape.d.getY() / SIZE) + 1];
            if (moveA == 0 && moveA == moveB && moveB == moveC && moveC == moveD) {
                shape.a.setY(shape.a.getY() + MOVE);
                shape.b.setY(shape.b.getY() + MOVE);
                shape.c.setY(shape.c.getY() + MOVE);
                shape.d.setY(shape.d.getY() + MOVE);
            }
        }
    }

    private boolean moveA(Shape shape) {
        return (MESH[(int) shape.a.getX() / SIZE][((int) shape.a.getY() / SIZE) + 1] == 1);
    }

    private boolean moveB(Shape shape) {
        return (MESH[(int) shape.b.getX() / SIZE][((int) shape.b.getY() / SIZE) + 1] == 1);
    }

    private boolean moveC(Shape shape) {
        return (MESH[(int) shape.c.getX() / SIZE][((int) shape.c.getY() / SIZE) + 1] == 1);
    }

    private boolean moveD(Shape shape) {
        return (MESH[(int) shape.d.getX() / SIZE][((int) shape.d.getY() / SIZE) + 1] == 1);
    }

    private boolean checkBool(Rectangle rectangle, int x, int y) {

        boolean ybool = false;
        boolean xbool = false;

        if (x >= 0)
            xbool = rectangle.getX() + x * MOVE <= xMAX - SIZE;
        if (x < 0)
            xbool = rectangle.getX() + x * MOVE >= 0;
        if (y >= 0)
            ybool = rectangle.getY() + y * MOVE > 0;
        if (y < 0)
            ybool = rectangle.getY() + y * MOVE < yMAX;

        return xbool && ybool && MESH[((int) rectangle.getX() / SIZE) + x][((int) rectangle.getY() / SIZE - y)] == 0;


    }

}


