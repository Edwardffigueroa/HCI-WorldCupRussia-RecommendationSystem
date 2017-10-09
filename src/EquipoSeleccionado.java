import processing.core.PApplet;

/**
 * Created by edward on 8/10/17.
 */
public class EquipoSeleccionado {

    private String equipo;
    private PApplet app;
    private int posX, posY;


    public EquipoSeleccionado(PApplet app, String nombre) {
        this.app=app;
        this.equipo=nombre;

    }

    public void setPos(int posX, int posY){
        this.posX=posX;
        this.posY=posY;

    }

    public void pintar(){


        app.fill(0);

        app.rect(posX, posY, 80, 15);
        app.fill(255);
        app.text(equipo, posX, posY+15);

    }


    public boolean seleccionado(){

        if (app.mouseX>posX&&app.mouseX<posX+80&&app.mouseY>posY&&app.mouseY<posY+15){
            return true;
        }
        return false;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }



}
