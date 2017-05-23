import java.util.ArrayList;

/**
 * Created by edward on 10/05/17.
 */
public class User {

    private ArrayList<java.lang.String> equipos;
    String nombreUsuario;
    String equipoUno;
    String equipoDos;
    String equipoTres;
    String equipoCuatro;

    public User(String nombreUsuario,String equipoUno, String equipoDos, String equipoTres, String equipoCuatro) {
        this.nombreUsuario=nombreUsuario;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getEquipoUno() {
        return equipoUno;
    }

    public void setEquipoUno(String equipoUno) {
        this.equipoUno = equipoUno;
    }

    public String getEquipoDos() {
        return equipoDos;
    }

    public void setEquipoDos(String equipoDos) {
        this.equipoDos = equipoDos;
    }

    public String getEquipoTres() {
        return equipoTres;
    }

    public void setEquipoTres(String equipoTres) {
        this.equipoTres = equipoTres;
    }

    public String getEquipoCuatro() {
        return equipoCuatro;
    }

    public void setEquipoCuatro(String equipoCuatro) {
        this.equipoCuatro = equipoCuatro;
    }


}
