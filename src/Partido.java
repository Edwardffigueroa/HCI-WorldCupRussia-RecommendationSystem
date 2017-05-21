import processing.core.PApplet;
import processing.core.PVector;

/**
 * Created by edward on 10/05/17.
 */
public class Partido {

    private PApplet app;
    String equipoUno;
    String equipoDos;
    String ciudad;
    String fecha;
    String hora;
    int costo;
    String grupo;

    //GRAPHICS
    PVector pos;

    float ancho;
    float alto;
    int id;
    float rotationAngle;

    private boolean info=false;
    private boolean aceptable=false;
    private boolean ideal=false;

    public Partido(String equipoUno, String equipoDos, String ciudad, String fecha, String hora, int costo, String grupo, PVector pos, int id, float rotationAngle, PApplet app) {

        this.equipoUno=equipoUno;
        this.equipoDos=equipoDos;
        this.ciudad=ciudad;
        this.fecha=fecha;
        this.hora=hora;
        this.costo=costo;
        this.grupo=grupo;
            //graphics
        this.pos=pos;
        this.id=id;
        this.rotationAngle=rotationAngle;
        this.app=app;


        ancho=80;
        alto=30;

    }

    public void pintar(){
        app.noFill();

        app.pushMatrix();

        app.translate(pos.x, pos.y);
        app.rotate(rotationAngle);
        app.fill(0);

        if (aceptable){
            app.fill(0,0,255);
        }
        if (ideal){
            app.fill(0,255,0);
        }
        app.rect(0,0,ancho, alto);
        app.fill(255);
        app.text(equipoUno+"vs"+equipoDos,0,0+(alto/2));
        app.popMatrix();

        // ellipseMode(CORNER);
        app.ellipse(pos.x,pos.y,alto,alto);

   /*if(mouseX>pos.x&&mouseX<pos.x+ancho&&mouseY>pos.y&&mouseY<pos.y+alto){
     fill(0);
     rect(pos.x, pos.y, ancho, alto);
    }*/

    }


    public void arrastrar(float posX, float posY){
        pos.x= posX;
        pos.y= posY;
    }

    public boolean agarrar(){
        if(app.dist(app.mouseX, app.mouseY, pos.x, pos.y)<25){
            info=true;
            return true;
        }else{
            info= false;
            return false;
        }
    }

    public void partidoAceptable(){
        aceptable=true;
    }

    public void partidoIdeal(){
        ideal=true;

    }

    public PVector getPos(){
        return pos;
    }


    public String getEquipoUno() {
        return equipoUno;
    }

    public String getEquipoDos() {
        return equipoDos;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public int getCosto() {
        return costo;
    }

    public String getGrupo() {
        return grupo;
    }




}
