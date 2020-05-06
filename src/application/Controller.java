package application;

import javafx.scene.shape.Rectangle;

// https://www.youtube.com/watch?v=KjEaD0KyL0w&ab_channel=PlayJava
// https://www.youtube.com/watch?v=KD7wHKN22DQ&list=PLHwsL1JI79K2Kw5wf1KOOCn5KIXZ9qybv&ab_channel=Coding_Place

public class Controller {

    // From the Tetris calss
    public static final int MOVE = TetrisGame.MOVE;
    public static final int SIZE = TetrisGame.SIZE;
    public static int xMAX = TetrisGame.xMAX;
    public static int [][] MESH = TetrisGame.MESH;

    // logic for moving the blocks left and right

    public static void MoveRight(Shape shape){

        if (shape.a.getX() + MOVE <= xMAX - SIZE && shape.b.getX() + MOVE <= xMAX - SIZE
            && shape.c.getX() + MOVE <= xMAX - SIZE && shape.d.getX() + MOVE <= xMAX - SIZE){

            int moveA = MESH[((int) shape.a.getX()/SIZE+1)][((int) shape.a.getY()/SIZE)];
            int moveB = MESH[((int) shape.b.getX()/SIZE+1)][((int) shape.b.getY()/SIZE)];
            int moveC = MESH[((int) shape.c.getX()/SIZE+1)][((int) shape.c.getY()/SIZE)];
            int moveD = MESH[((int) shape.d.getX()/SIZE+1)][((int) shape.d.getY()/SIZE)];

            if (moveA == 0 && moveA == moveB && moveB == moveC && moveC == moveD){
                shape.a.setX(shape.a.getX() + MOVE);
                shape.b.setX(shape.b.getX() + MOVE);
                shape.c.setX(shape.c.getX() + MOVE);
                shape.d.setX(shape.d.getX() + MOVE);
            }
        }
    }


    public static void MoveLeft(Shape shape){

        if (shape.a.getX() - MOVE >= 0 && shape.b.getX() - MOVE >= 0
                && shape.c.getX() - MOVE >= 0 && shape.d.getX() - MOVE >= 0){

            int moveA = MESH[((int) shape.a.getX()/SIZE-1)][((int) shape.a.getY()/SIZE)];
            int moveB = MESH[((int) shape.b.getX()/SIZE-1)][((int) shape.b.getY()/SIZE)];
            int moveC = MESH[((int) shape.c.getX()/SIZE-1)][((int) shape.c.getY()/SIZE)];
            int moveD = MESH[((int) shape.d.getX()/SIZE-1)][((int) shape.d.getY()/SIZE)];

            if (moveA == 0 && moveA == moveB && moveB == moveC && moveC == moveD){
                shape.a.setX(shape.a.getX() - MOVE);
                shape.b.setX(shape.b.getX() - MOVE);
                shape.c.setX(shape.c.getX() - MOVE);
                shape.d.setX(shape.d.getX() - MOVE);
            }
        }
    }

    // create the block within the blocks (Inception)

    public static Shape makeRect(){

        //random color for the inception

        int block = (int) (Math.random() * 100);
        String name;
        // if we only use SIZE then the entire block is just one block, now one block consist of 4 blocks.

        Rectangle
                a = new Rectangle(SIZE-1, SIZE-1),
                b = new Rectangle(SIZE-1, SIZE-1),
                c = new Rectangle(SIZE-1, SIZE-1),
                d = new Rectangle(SIZE-1, SIZE-1);
        if(block < 15){
            // making the different shapes
            a.setX(xMAX / 2 - SIZE);
            b.setX(xMAX / 2 - SIZE);

            b.setY(SIZE);
            c.setX(xMAX / 2);
            c.setY(SIZE);

            d.setX(xMAX / 2 + SIZE);
            d.setY(SIZE);
            name = "j";
        }else if(block < 30){
            a.setX(xMAX / 2 + SIZE);

            b.setX(xMAX / 2 - SIZE);
            b.setY(SIZE);

            c.setX(xMAX / 2);
            c.setY(SIZE);

            d.setX(xMAX / 2 + SIZE);
            d.setY(SIZE);
            name = "l";

        }else if(block < 45) {
            a.setX(xMAX / 2 - SIZE);

            b.setX(xMAX / 2);

            c.setX(xMAX / 2 - SIZE);
            c.setY(SIZE);

            d.setX(xMAX / 2);
            d.setY(SIZE);
            name = "o";
        }else if(block < 60) {
            a.setX(xMAX / 2 + SIZE);

            b.setX(xMAX / 2);

            c.setX(xMAX / 2);
            c.setY(SIZE);

            d.setX(xMAX / 2 - SIZE);
            d.setY(SIZE);
            name = "s";
        }else if(block < 75) {
            a.setX(xMAX / 2 - SIZE);

            b.setX(xMAX / 2);

            c.setX(xMAX / 2);
            c.setY(SIZE);

            d.setX(xMAX / 2 + SIZE);
            name = "t";
        }else if(block < 90 ) {
            a.setX(xMAX / 2 + SIZE);

            b.setX(xMAX / 2 );

            c.setX(xMAX / 2 + SIZE);
            c.setY(SIZE);

            d.setX(xMAX / 2 + SIZE + SIZE);
            d.setY(SIZE);
            name = "z";
        }else {
            a.setX(xMAX / 2 - SIZE - SIZE);

            b.setX(xMAX / 2 - SIZE);

            c.setX(xMAX / 2);

            d.setX(xMAX / 2 + SIZE);
            name = "i";
        }
        return new Shape(a,b,c,d,name);
    }









}
