import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by edward on 19/05/17.
 */
public class Maleta {

    private PApplet app;
    private PVector pos;
    private ArrayList <Partido> partidos;

    private int dias=0;
    private int numeroPartidos=0;
    private int numeroVuelos=0;
    private int dineroPartidos=0;
    private int dineroVuelos=0;
    private int total=0;

    //procesos internos
    int dia=0;
    int mes=0;
    int ano=0;

    int diaMenor=0;
    int diaMayor=0;

    private TreeSet<Integer> noDias= new TreeSet<Integer>();
    private TreeSet<String> noVuelos= new TreeSet<String>();
    private Logic log;

    Maleta(PApplet app, Logic log){

        this.app=app;
        this.log=log;

        pos= new PVector((app.width/3)-30,app.height/2);
        partidos= new ArrayList<Partido>();

    }

    public void pintar(){

       // app.fill(0);
     //   app.ellipse(pos.x, pos.y, 250,250);


        showData();

    }

    public void recibir(Partido p){

        partidos.add(p);
        dineroPartidos+=p.getCosto();

        String[] fecha =p.getFecha().split("/");
        dia= Integer.parseInt(fecha[0]);

        System.out.println(dia);
        noDias.add(dia);
        noVuelos.add(p.getCiudad());

    }

    public boolean insertar(float posx, float posy){

        if(app.dist(posx, posy, pos.x, pos.y)<125){
            return true;
        }else{
            return false;
        }
    }

    public void showData() {

        for (int i:noDias) {

            diaMenor= Integer.min(diaMayor,i);
            diaMayor=Integer.max(diaMenor,i);

        }

            numeroPartidos=partidos.size();
            dias=noDias.size();
            numeroVuelos=noVuelos.size();
            dineroVuelos=numeroVuelos*150000;
            total=dineroVuelos+dineroPartidos;

            app.textSize(15);
            app.fill(255);
            app.text(numeroPartidos+" Partidos", 874,479);
            //dinero en partidos
            app.textSize(15);
            app.fill(255);
            app.text("$ "+dineroPartidos, 1000,479);
            //numero de dias (debería restar el dia mayor y el día menor)
            app.textSize(15);
            app.fill(255);
            app.text(dias+" Días", 869,419);
            //lapso de tiempo
            app.textSize(15);
            app.fill(255);
            app.text(diaMenor+"-"+diaMayor, 1000,419);
        //numero de vuelos
        app.textSize(15);
        app.fill(255);
        app.text(numeroVuelos+" Vuelos", 870,537);
        //precio de vuelos
        app.textSize(15);
        app.fill(255);
        app.text("$ "+dineroVuelos, 1000,537);

        //precio total
        app.textSize(15);
        app.fill(255);
        app.text("Total: $ "+total, 874,612);

        //}
    }

    public void showDataOnFinish(){
//lapso de tiempo

        app.textSize(15);
        app.fill(0);
        app.text(diaMenor+"-"+diaMayor, 330,312);

        //code

        app.textSize(15);
        app.fill(0);
        app.text(log.getCode(),747,312);

        //numero de jugadores
        app.textSize(15);
        app.fill(0);
        app.text(log.getNumeroJugadores(),311,444);

        //precio total de la compra

        app.textSize(15);
        app.fill(0);
        app.text(total,688,441);


        //PARTIDOS A LOS QUE SE IRAN

        for (int i = 0; i <partidos.size() ; i++) {
            Partido p= partidos.get(i);

        app.textSize(15);
        app.fill(0);
        app.text(p.getEquipoUno() +" vs "+p.getEquipoDos(),947,312+(i*20));

        }

    }

    public void removePartido(){

    }

    public PVector getPos() {
        return pos;
    }
}
