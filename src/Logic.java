import jxl.*;
import jxl.read.biff.BiffException;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by edward on 10/05/17.
 */

public class Logic {

    private PApplet app;
    private Workbook workbook;
    private Sheet[] mySheets;
    //private String inputFile = "data/partidos.xls";
    private String inputFile = "data/PartidosNew.xls";
    private int numberSheet=0;

    private Cell myCell;// este se encarga de tomar el dato de la celda

    private LabelCell myLabel;
    private NumberCell myNumber;

    private String equipoUno="";
    private String equipoDos="";
    private String  ciudad="";
    private String  fecha="";
    private String  hora="";
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


    public Logic(PApplet app) {
        this.app=app;
        partidos= new ArrayList<Partido>();
        usuarios= new ArrayList<User>();
        maleta= new Maleta(app);
        back= app.loadImage("data/fondo.jpg");
        manager= new ManagerComunicacion(app);
        new Thread(manager).start();

        initUsers();

        try {
            readData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        recomendacion= new PartidoRecomendado(partidos,usuarios);
        recomendacion.partidosAceptables();
        recomendacion.partidosIdeales();

    }

    public void initUsers(){

        usuarios.add(new User("COL", "MEX","CHL","ESP"));
        usuarios.add(new User("COL", "URY","CHL","ESP"));
        usuarios.add(new User("COL", "GRC","KOR","ESP"));

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

                    if (i==0&&j>0) {

                        equipoUno= myLabel.getString();
                    }

                    if (i==1&&j>0){
                        equipoDos=myLabel.getString();
                    }

                    if (i==2&&j>0){
                        ciudad= myLabel.getString();
                    }

                    if (i==3&&j>0){
                        fecha= myLabel.getString();

                    }

                    if(i==4&&j>0) {
                        hora= myLabel.getString();

                    }

                    if (i==6&&j>0){
                        grupo= myLabel.getString();

                    }

                }

                if (myCell.getType()==CellType.NUMBER){

                    myNumber=(NumberCell) myCell;

                    if (i==5&&j>0) {
                        costo = (int) myNumber.getValue();
                    }

                }

            }

            px = (app.width/3)-30 + app.cos(app.radians(angle))*(radius);
            py = app.height/2 + app.sin(app.radians(angle))*(radius);

            rotationAngle= j*(app.TWO_PI/49);


            p= new Partido(equipoUno,equipoDos,ciudad,fecha,hora,costo,grupo, new PVector(px,py), j,rotationAngle, app);
            partidos.add(p);

            angle= (float) (j*7.5);

            //boletas.add(new Boleta(new PVector (px,py), i,rotationAngle));

        }


        for (int j = 0; j <partidos.size() ; j++) {
            Partido p= partidos.get(j);
            System.out.println(p.equipoUno+"vs"+p.equipoDos+":"+ciudad+":"+fecha+":"+hora+":"+costo+":"+grupo);
        }

       System.out.println(mySheets[numberSheet].getRows());

    }

    public void pintar(){

        //System.out.println(partidos.size());
        app.image(back, 0,0, app.width, app.height);

        maleta.pintar();

        for (int i = 0; i <partidos.size() ; i++) {
            Partido p= partidos.get(i);
            p.pintar();
        }
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



}
