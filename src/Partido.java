import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
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
    PVector pose;
    PVector posDestino;

    float vel;
    private float z = 0; // create variable for noise z

    float ancho;
    float alto;
    int id;
    float rotationAngle;

    private boolean info=false;
    private boolean aceptable=false;
    private boolean ideal=false;

    private PImage boleta;
    private PImage boletaIdeal;
    private PImage boletaAceptable;



    public Partido(String equipoUno, String equipoDos, String ciudad, String fecha, String hora, int costo, String grupo, PVector pos, int id, float rotationAngle, PApplet app) {

        this.equipoUno=equipoUno;
        this.equipoDos=equipoDos;
        this.ciudad=ciudad;
        this.fecha=fecha;
        this.hora=hora;
        this.costo=costo;
        this.grupo=grupo;
            //graphics
       // this.pos=pos;
        this.id=id;
        this.rotationAngle=rotationAngle;
        this.app=app;

        this.pose=pos;
    //this.posDestino=pos;
    //   pose= new PVector((app.width/3)-30,app.height/2 );
      //  pose= new PVector(0,0 );

        boleta= app.loadImage("data/tiqueteGris.png");
        boletaIdeal= app.loadImage("data/tiqueteAmarillo.png");
        boletaAceptable= app.loadImage("data/tiqueteAzul.png");

        ancho=80;
        alto=30;

        //vel= (float) 15.29;
        vel= (float) 20.25;

    }

    public void pintar(){
        app.noFill();

        app.pushMatrix();

        app.translate(pose.x, pose.y);
        app.rotate(rotationAngle);
        app.fill(0);

        app.imageMode(app.CENTER);
        app.image(boleta, 0,0, 94-8, 47-8);

        if (aceptable){
            app.image(boletaAceptable, 0,0);
        }

        if (ideal){
            app.image(boletaIdeal, 0,0);
        }

        app.imageMode(app.CORNER);


        app.fill(0);
        app.textSize(10);
//        app.textMode(PConstants.CENTER);

        app.text(equipoUno+" vs "+equipoDos,-38,0);

        app.popMatrix();

            //prueba area sensible
        app.noFill();
        app.ellipse(pose.x, pose.y, alto,alto);


//----------------------------------------INFORMACIÓN DEL PARTIDO
        if(app.dist(app.mouseX, app.mouseY, pose.x, pose.y)<alto/2){ //MOSTRAR INFORMACIÓN

            app.pushMatrix();
            app.translate(pose.x, pose.y);
            app.rotate(rotationAngle);

        app.fill(0);
        app.rectMode(app.CORNER);
        app.rect(-120, 0, ancho, 70);
        app.fill(255);
        app.text(equipoUno+"vs"+equipoDos, -100,15);
        app.text("Ciudad: "+ciudad, -120,25);
        app.text("Fecha: "+fecha, -120,35);
        app.text("Hora: "+hora, -120,45);
        app.text("Costo: "+costo, -120,55);
        app.text("Grupo: "+grupo, -120,65);

        app.popMatrix();

    }


      //  animacion();
        //z = (float) (z + 0.02);
    }


    public void animacion(){

        if(pose.x<=posDestino.x){
            pose.x+=vel*0.50;
        }

        if(pose.x>=posDestino.x){
            pose.x-=vel*0.50;
        }

        if(pose.y<=posDestino.y){
            pose.y+=vel*0.50;
        }
        if(pose.y>=posDestino.y){
            pose.y-=vel*0.50;
        }


    }


    public void arrastrar(float posX, float posY){
        pose.x= posX;
        pose.y= posY;
    }

    public boolean agarrar(){
        if(app.dist(app.mouseX, app.mouseY, pose.x, pose.y)<alto/2){
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
        return pose;
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
