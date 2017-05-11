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
    public void settings() {
        size(200,200);
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

    }

    @Override
    public void draw() {

    }


}
