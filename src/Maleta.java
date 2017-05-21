import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

/**
 * Created by edward on 19/05/17.
 */
public class Maleta {

    private PApplet app;
    private PVector pos;
    private ArrayList <Partido> partidos;


    Maleta(PApplet app){
        this.app=app;

        pos= new PVector((app.width/3)-30,app.height/2);
        partidos= new ArrayList<Partido>();

    }

    public void pintar(){

        app.fill(0);
        app.ellipse(pos.x, pos.y, 150,150);
        app.textSize(15);
        app.fill(255);
        app.text(partidos.size(), pos.x, pos.y-50);
    }

    public void recibir(Partido p){
        partidos.add(p);
    }

    public boolean insertar(float posx, float posy){
        if(app.dist(posx, posy, pos.x, pos.y)<75){
            return true;
        }else{
            return false;
        }

    }

}
