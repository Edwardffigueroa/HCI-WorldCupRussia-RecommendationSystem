import processing.core.PApplet;

import java.util.Random;

/**
 * Created by edward on 10/05/17.
 */
public class Main extends PApplet {

    char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();

    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    String code;


    Logic log;

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    @Override
    public void settings()
    {
        //size(1280,800);
        fullScreen();
    }

    @Override
    public void setup() {

        log= new Logic(this);
        for (int i = 0; i < 6; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }

        code = sb.toString();
     //   System.out.println(code);
        //smooth();
        //noCursor();
    }

    @Override
    public void draw() {
        background(80);
        log.pintar();

    }

    @Override
    public void mousePressed() {
        log.pressed();
        System.out.println(mouseX+","+mouseY);


        if(mouseX>1612&&mouseX<1879&&mouseY>912&&mouseY<1048&&log.getPantallas()==3){

           setup();
        }
    }

    @Override
    public void mouseDragged() {
        log.dragged();
    }

    @Override
    public void mouseReleased() {
        log.released();
    }
}
