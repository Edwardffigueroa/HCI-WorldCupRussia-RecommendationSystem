import java.util.ArrayList;

/**
 * Created by edward on 10/05/17.
 */
public class User {

    private ArrayList<java.lang.String> equipos;
    String equipoUno;
    String equipoDos;
    String equipoTres;
    String equipoCuatro;

    public User(String equipoUno, String equipoDos, String equipoTres, String equipoCuatro) {
        this.equipoUno=equipoUno;
        this.equipoDos=equipoDos;
        this.equipoTres=equipoTres;
        this.equipoCuatro=equipoCuatro;

        equipos= new ArrayList<>();


        equipos.add(equipoUno);
        equipos.add(equipoDos);
        equipos.add(equipoTres);
        equipos.add(equipoCuatro);

    }

    public ArrayList<String> getEquipos() {
        return equipos;
    }

    public String getEquiposIndividuales(){

            return equipoUno+":"+equipoDos+":"+equipoTres+":"+equipoCuatro;

    }
}
