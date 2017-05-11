/**
 * Created by edward on 10/05/17.
 */
public class Partido {

    String equipoUno;
    String equipoDos;
    String ciudad;
    String fecha;
    String hora;
    int costo;
    String grupo;

    public Partido(String equipoUno, String equipoDos, String ciudad, String fecha, String hora, int costo, String grupo) {

        this.equipoUno=equipoUno;
        this.equipoDos=equipoDos;
        this.ciudad=ciudad;
        this.fecha=fecha;
        this.hora=hora;
        this.costo=costo;

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
