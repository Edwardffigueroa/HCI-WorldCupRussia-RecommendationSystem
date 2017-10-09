import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by edward on 8/10/17.
 */
public class Celular extends Observable {


    private String nombreUser="";
    private ArrayList<EquipoSeleccionado> equipos;
    private PApplet app;
    int x, y;

    private int posXInputUno=250;
    private int posYInputUno=250;

    private int posXInputDos=250;
    private int posYInputDos=300;

    private int posXInputTres=250;
    private int posYInputTres=350;

    private int posXInputCuatro=250;
    private int posYInputCuatro=400;

    private int posXbuttonsend=200;
    private int posYbuttonsend=450;



    private boolean activarListaUno=false;
    private boolean activarListaDos=false;
    private boolean activarListaTres=false;
    private boolean activarListaCuatro=false;

    private boolean sendUno=false;
    private boolean sendDos=false;
    private boolean sendTres=false;
    private boolean sendCuatro=false;

    private boolean enviado=false;

    private String equiposUno="";
    private String equiposDos="";
    private String equipostres="";
    private String equiposCuatro="";

    public Celular(PApplet app,String user , int x, int y) {
        this.x=x;
        this.y=y;

        posXInputUno=x+150;
        posYInputUno=y+50;

        posXInputDos=x+150;
        posYInputDos=y+100;

        posXInputTres=x+150;
        posYInputTres=y+150;

        posXInputCuatro=x+150;
        posYInputCuatro=y+200;

        posXbuttonsend=x+0;
        posYbuttonsend=y+250;


this.app=app;
        this.nombreUser=user;
        equipos= new ArrayList<EquipoSeleccionado>();
        initTeam();

    }

    public void initTeam(){
        equipos.add(new EquipoSeleccionado(app,"BRA"));
        equipos.add(new EquipoSeleccionado(app,"CRO"));
        equipos.add(new EquipoSeleccionado(app,"MEX"));
        equipos.add(new EquipoSeleccionado(app,"CMR"));
        equipos.add(new EquipoSeleccionado(app,"ESP"));
        equipos.add(new EquipoSeleccionado(app,"NLD"));
        equipos.add(new EquipoSeleccionado(app,"CHL"));
        equipos.add(new EquipoSeleccionado(app,"AUS"));
        equipos.add(new EquipoSeleccionado(app,"COL"));
        equipos.add(new EquipoSeleccionado(app,"GRC"));
        equipos.add(new EquipoSeleccionado(app,"URY"));
        equipos.add(new EquipoSeleccionado(app,"CRI"));
        equipos.add(new EquipoSeleccionado(app,"CIV"));
        equipos.add(new EquipoSeleccionado(app,"JPN"));
        equipos.add(new EquipoSeleccionado(app,"ING"));
        equipos.add(new EquipoSeleccionado(app,"ITA"));
        equipos.add(new EquipoSeleccionado(app,"SUI"));
        equipos.add(new EquipoSeleccionado(app,"ECU"));
        equipos.add(new EquipoSeleccionado(app,"FRA"));
        equipos.add(new EquipoSeleccionado(app,"HND"));
        equipos.add(new EquipoSeleccionado(app,"ARG"));
        equipos.add(new EquipoSeleccionado(app,"BIH"));
        equipos.add(new EquipoSeleccionado(app,"IRN"));
        equipos.add(new EquipoSeleccionado(app,"NGA"));
        equipos.add(new EquipoSeleccionado(app,"DEU"));
        equipos.add(new EquipoSeleccionado(app,"PRT"));
        equipos.add(new EquipoSeleccionado(app,"GHA"));
        equipos.add(new EquipoSeleccionado(app,"USA"));
        equipos.add(new EquipoSeleccionado(app,"BEL"));
        equipos.add(new EquipoSeleccionado(app,"AGL"));
        equipos.add(new EquipoSeleccionado(app,"RUS"));
        equipos.add(new EquipoSeleccionado(app,"KOR"));

/*

        <item>Brazil -BRA</item>
        <item>Croacia -CRO</item>
        <item>Mexico -MEX</item>
        <item>Camerun -CMR</item>
        <item>España -ESP</item>
        <item>Holanda -NLD</item>
        <item>Chile -CHL</item>
        <item>Australia -AUS</item>
        <item>Colombia -COL</item>
        <item>Grecia -GRC</item>
        <item>Uruguay -URY</item>
        <item>Costa Rica -CRI</item>
        <item>Costa de Marfil -CIV</item>
        <item>Japon -JPN</item>
        <item>Inglaterra -ING</item>
        <item>Italia -ITA</item>
        <item>Suiza -SUI</item>
        <item>Ecuador -ECU</item>
        <item>Francia -FRA</item>
        <item>Honduras -HND</item>
        <item>Argentina -ARG</item>
        <item>Bosnia -BIH</item>
        <item>Iran -IRN</item>
        <item>Nigeria -NGA</item>
        <item>Alemania -DEU</item>
        <item>Portugal -PRT</item>
        <item>Ghana -GHA</item>
        <item>Estados Unidos -USA</item>
        <item>Belgica -BEL</item>
        <item>Argelia -AGL</item>
        <item>Rusia -RUS</item>
        <item>Korea -KOR</item>
*/
    }


    public void pintar(){



        app.fill(255);
        app.rect(x,y,200,300);


        //boton de enviar
        app.fill(0);
        app.rect(posXbuttonsend,posYbuttonsend,200,50);
        app.fill(255);
        app.text("ENVIAR",posXbuttonsend+100, posYbuttonsend+25);
        //circulos de partidos
        app.fill(0);
        app.ellipse(posXInputUno,posYInputUno,40,40);
        app.fill(255);
        app.text(equiposUno,posXInputUno,posYInputUno);

        app.fill(0);
        app.ellipse(posXInputDos,posYInputDos,40,40);
        app.fill(255);
        app.text(equiposDos,posXInputDos,posYInputDos);

        app.fill(0);
        app.ellipse(posXInputTres,posYInputTres,40,40);
        app.fill(255);
        app.text(equipostres,posXInputTres,posYInputTres);


        app.fill(0);
        app.ellipse(posXInputCuatro,posYInputCuatro,40,40);
        app.fill(255);
        app.text(equiposCuatro,posXInputCuatro,posYInputCuatro);


        for (int i=0; i< equipos.size();i++){

            if(activarListaUno){

                equipos.get(i).setPos(posXInputUno+25, posYInputUno +(i*20));
                equipos.get(i).pintar();

                }

            if(activarListaDos){

                equipos.get(i).setPos(posXInputDos+25, posYInputDos +(i*20));
                equipos.get(i).pintar();

            }
            if(activarListaTres){

                equipos.get(i).setPos(posXInputTres+25, posYInputTres +(i*20));
                equipos.get(i).pintar();

            }
            if(activarListaCuatro){

                equipos.get(i).setPos(posXInputCuatro+25, posYInputCuatro +(i*20));
                equipos.get(i).pintar();

            }
        }

        if(enviado){
            app.fill(0, 98);
            app.rect(x,y,200,300);
            app.fill(255);
            app.text("wait", x+100, y+100);
        }



    }

    public void seleccionar(){

        if (app.dist(posXInputUno,posYInputUno,app.mouseX, app.mouseY)<20){

            activarListaUno=!activarListaUno;
            activarListaDos=false;
            activarListaTres=false;
            activarListaCuatro=false;

        }
        if (app.dist(posXInputDos,posYInputDos,app.mouseX, app.mouseY)<20){

            activarListaDos=!activarListaDos;

            activarListaUno=false;
            activarListaTres=false;
            activarListaCuatro=false;
        }
        if (app.dist(posXInputTres,posYInputTres,app.mouseX, app.mouseY)<20){

            activarListaTres=!activarListaTres;

            activarListaDos=false;
            activarListaUno=false;
            activarListaCuatro=false;

        }
        if (app.dist(posXInputCuatro,posYInputCuatro,app.mouseX, app.mouseY)<20){

            activarListaCuatro=!activarListaCuatro;
            activarListaUno=false;
            activarListaDos=false;
            activarListaTres=false;

        }

        for (int i=0; i< equipos.size();i++){
            if (activarListaUno && equipos.get(i).seleccionado()) {
                System.out.println(equipos.get(i).getEquipo());
                equiposUno=equipos.get(i).getEquipo();
                activarListaUno=false;
                sendUno=true;
            }

            if (activarListaDos && equipos.get(i).seleccionado()) {
                System.out.println(equipos.get(i).getEquipo());
                equiposDos=equipos.get(i).getEquipo();
                activarListaDos=false;
                sendDos=true;
            }

            if (activarListaTres && equipos.get(i).seleccionado()) {
                System.out.println(equipos.get(i).getEquipo());
                equipostres=equipos.get(i).getEquipo();

                activarListaTres=false;
                sendTres=true;
            }

            if (activarListaCuatro && equipos.get(i).seleccionado()) {
                System.out.println(equipos.get(i).getEquipo());
                equiposCuatro=equipos.get(i).getEquipo();
                activarListaCuatro=false;
                sendCuatro=true;
            }
        }

        if (app.mouseX>posXbuttonsend && app.mouseX<posXbuttonsend+200 &&app.mouseY>posYbuttonsend&&app.mouseY<posYbuttonsend+50){

             if(sendUno&&sendDos&&sendTres&&sendCuatro) {

                 User u = new User(nombreUser, equiposUno, equiposDos, equipostres, equiposCuatro);
                 setChanged();
                 notifyObservers(u);
                 enviado=true;
             }
        }

      /*  User u= new User(nombreUser, equiposUno,equiposDos, equiposTres, equiposCuatro);
        setChanged();
        notifyObservers(u);*/

    }


}
