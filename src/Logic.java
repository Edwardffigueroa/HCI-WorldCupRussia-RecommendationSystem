import jxl.*;
import jxl.read.biff.BiffException;
import processing.core.PApplet;

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
    private String inputFile = "data/partidos.xls";
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
    private ArrayList<Partido> partidos;
    private ArrayList<User> usuarios;
    private PartidoRecomendado recomendacion;


    public Logic(PApplet app) {
        this.app=app;
        partidos= new ArrayList<Partido>();
        usuarios= new ArrayList<User>();

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

             p= new Partido(equipoUno,equipoDos,ciudad,fecha,hora,costo,grupo);
            partidos.add(p);

        }

        for (int j = 0; j <partidos.size() ; j++) {
            Partido p= partidos.get(j);
        }

       //System.out.println(partidos.size());

    }




}
