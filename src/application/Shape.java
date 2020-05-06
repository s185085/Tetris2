package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

// https://www.youtube.com/watch?v=KjEaD0KyL0w&ab_channel=PlayJava
// https://www.youtube.com/watch?v=KD7wHKN22DQ&list=PLHwsL1JI79K2Kw5wf1KOOCn5KIXZ9qybv&ab_channel=Coding_Place

public class Shape {

    Rectangle a;
    Rectangle b;
    Rectangle c;
    Rectangle d;
    Color color;
    private String name;
    public int shape = 1;

    public Shape(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public Shape(Rectangle a, Rectangle b, Rectangle c, Rectangle d, String name) {

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.name = name;


        // color of the blocks
        switch (name) {

            case "j":
                color = Color.GREENYELLOW;
                break;
            case "l":
                color = Color.GREEN;
                break;
            case "o":
                color = Color.MEDIUMVIOLETRED;
                break;
            case "s":
                color = Color.LIGHTGOLDENRODYELLOW;
                break;
            case "t":
                color = Color.CORNFLOWERBLUE;
                break;
            case "z":
                color = Color.HOTPINK;
                break;
            case "i":
                color = Color.SADDLEBROWN;
                break;

        }

        this.a.setFill(color);
        this.b.setFill(color);
        this.c.setFill(color);
        this.d.setFill(color);
    }

    public String getName() {

        return name;
    }


    public void changeForm() {
        if ( shape != 4) {
            shape++;
        } else {
            shape = 1;
        }
    }

}


