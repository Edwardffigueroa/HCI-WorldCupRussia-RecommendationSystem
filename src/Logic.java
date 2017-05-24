import jxl.*;
import jxl.read.biff.BiffException;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * Created by edward on 10/05/17.
 */

public class Logic implements Observer{

    private static int NUMERO_JUGADORES=2;
    private  int pantallas=1;
    //private int mapeo=200;
    private PApplet app;
    private Workbook workbook;
    private Sheet[] mySheets;

    //private String inputFile = "data/partidos.xls";
    private String inputFile = "data/PartidosNew.xls";
    private int numberSheet=0;
    private Cell myCell;// este se encarga de tomar el dato de la celda

    private LabelCell myLabel;
    private NumberCell myNumber;
    private DateCell myDate;

    private String equipoUno="";
    private String equipoDos="";
    private String  ciudad="";
    private String  fecha="";
    private String  horas="";
    private int  costo=0;
    private String  grupo="";

    private Partido p;
    private Partido partido=null;
    private Maleta maleta;
    private ArrayList<Partido> partidos;
    private ArrayList<User> usuarios;
    private PartidoRecomendado recomendacion;
    private ManagerComunicacion manager;

    //variables para la pintada
    float rotationAngle;
    float px, py;
    float angle=353;
    //float radius = 320;
    float radius = 320;

    private PImage back;
    private PImage start;
    private PImage bag;
    private  PImage finish;

    private String code="";


    public Logic(PApplet app) {
        this.app=app;
        partidos= new ArrayList<Partido>();
        usuarios= new ArrayList<User>();
        maleta= new Maleta(app, this);
        back= app.loadImage("data/fondo.jpg");
        start=app.loadImage("data/pantallaUno.jpg");
        bag= app.loadImage("data/partidosMaleta.png");
        finish=app.loadImage("data/pantallaFinal.jpg");
        cod();
        manager= new ManagerComunicacion(app, code);
        manager.addObserver(this);
        new Thread(manager).start();

        initUsers();

        try {
            readData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*recomendacion= new PartidoRecomendado(partidos,usuarios);
        recomendacion.partidosAceptables();
        recomendacion.partidosIdeales();*/

    }

    public void initUsers(){

    //    usuarios.add(new User("asdas","COL", "MEX","CHL","ESP"));
  //      usuarios.add(new User("asdasd","COL", "URY","CHL","ESP"));
//        usuarios.add(new User("asdasd","COL", "GRC","KOR","ESP"));

    }

    public void readData() throws Exception{

        try {

            workbook = Workbook.getWorkbook(new File(inputFile));
            mySheets = workbook.getSheets();

        } catch (IOException e) {
            System.out.println("no se encontro nada");
        } catch (BiffException e) {
            e.printStackTrace();
            System.out.println("no se encontro nada");

        }

        for (int j=0; j<mySheets[numberSheet].getRows();j++){
            for (int i = 0; i <mySheets[numberSheet].getColumns() ; i++) {

                myCell= mySheets[numberSheet].getCell(i, j);

                if (myCell.getType()==CellType.LABEL){

                    myLabel=(LabelCell) myCell;

                    if (i==0&&j>-1) {

                        equipoUno= myLabel.getString();
                    }

                    if (i==1&&j>-1){
                        equipoDos=myLabel.getString();
                    }

                    if (i==2&&j>-1){
                        ciudad= myLabel.getString();
                    }

                    if (i==3&&j>-1){
                        fecha= myLabel.getString();


                    }

                    /*if(i==4&&j>-1) {
                        horas= myLabel.getString();


                    }*/

                    if (i==6&&j>-1){
                        grupo= myLabel.getString();

                    }

                }

                if (myCell.getType()==CellType.NUMBER){

                    myNumber=(NumberCell) myCell;

                    if (i==5&&j>-1) {
                        costo = (int) myNumber.getValue();
                    }

                }

                if (myCell.getType()==CellType.DATE){
                    myDate=(DateCell) myCell;
                    if(i==4&&j>-1) {
                        horas= myDate.getContents();
                    }
                }

            }

            px = (app.width/3)-30 + app.cos(app.radians(angle))*(radius);
            py = app.height/2 + app.sin(app.radians(angle))*(radius);

            rotationAngle= j*(app.TWO_PI/49);

            p= new Partido(equipoUno,equipoDos,ciudad,fecha,horas,costo,grupo, new PVector(px,py), j,rotationAngle, app);
            partidos.add(p);

            angle= (float) (j*7.5);

            //boletas.add(new Boleta(new PVector (px,py), i,rotationAngle));

        }


        /*for (int k = 0; k <partidos.size() ; k++) {
            Partido p= partidos.get(k);
            System.out.println(p.getEquipoUno()+"vs"+p.getEquipoDos()+":"+p.getCiudad()+":"+p.getFecha()+":"+p.getHora()+":"+p.getCosto()+":"+p.getGrupo());
        }*/

       System.out.println(mySheets[numberSheet].getRows());

    }

    public void pintar(){

        app.image(back, 0,0, app.width, app.height);

        switch (pantallas){
            case 0:

                app.image(start,0,0,app.width, app.height);
                app.textSize(24);
                app.text(manager.getCode(),900,500);

                app.text(usuarios.size()+"/"+NUMERO_JUGADORES,900,600);

                break;
            case 1:
                app.image(back, 0,0, app.width, app.height);

                maleta.pintar();

                for (int i = 0; i <partidos.size() ; i++) {
                    Partido p= partidos.get(i);
                    p.pintar();
                }
                break;

            case 2:

                app.image(bag, 0,0, app.width, app.height);

                break;
            case 3:
                app.image(finish, 0,0, app.width,app.height);
                maleta.showDataOnFinish();

                break;

        }

        //System.out.println(partidos.size());

    }

    public void coger(){

        for(int i=0;i<partidos.size();i++){
            Partido pa= partidos.get(i);
            if(pa.agarrar()&&partido==null){
                partido=pa;
            }
        }
    }


    public void pressed(){
        coger();

        //ZONA SENSIBLE PARA VER PARTIDOS
        if (app.dist(maleta.getPos().x, maleta.getPos().y,app.mouseX, app.mouseY)<125&&pantallas==1){
            pantallas=2;
        }

        //ZONA SENSIBLE PARA SALIR DE LA OPCION DE VER LOS PARTIDOS
        if (app.mouseX>481&&app.mouseX<705&&app.mouseY>757&&app.mouseY<804&&pantallas==2){
            pantallas=1;
        }
        //ZONA SENSIBLE PARA PASAR A LA PANTALLA FINAL
        if (app.mouseX>1315&&app.mouseX<1643&&app.mouseY>938&&app.mouseY<1010&&pantallas==1){
            pantallas=3;
        }
        //ZONA SENSIBLE PARA REGRESAR
        if (app.mouseX>88&&app.mouseX<316&&app.mouseY>831&&app.mouseY<1047&&pantallas==3){
            pantallas=1;
        }

    }


    public void dragged(){

        if(partido!=null){
            partido.arrastrar(app.mouseX, app.mouseY);
        }
    }

    public void released(){
        for(int i=0; i<partidos.size(); i++){
            Partido pa= partidos.get(i);

            if(maleta.insertar(pa.getPos().x,pa.getPos().y)){
                maleta.recibir(pa);
                partidos.remove(pa);
            }
        }

        partido=null;

    }


    @Override
    public void update(Observable o, Object arg) {

        if (arg instanceof User){
            usuarios.add((User) arg);
            System.out.println(usuarios.size());
            System.out.println(((User) arg).getNombreUsuario()+":"+((User) arg).getEquipoUno()+":"+((User) arg).getEquipoDos()+":"+((User) arg).getEquipoTres()+":"+((User) arg).getEquipoCuatro());

        }

        if(usuarios.size()==NUMERO_JUGADORES){

            pantallas=1;

        recomendacion= new PartidoRecomendado(partidos,usuarios);
        recomendacion.partidosAceptables();
        recomendacion.partidosIdeales();

        }
    }


    public void cod(){

        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        code = sb.toString();
        System.out.println("Código: " + code);
    }

    public String getCode() {
        return code;
    }

    public static int getNumeroJugadores() {
        return NUMERO_JUGADORES;
    }
}
