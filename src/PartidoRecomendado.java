import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * Created by edward on 10/05/17.
 */
public class PartidoRecomendado {

    ArrayList<Partido> partidos;
    ArrayList<User> usuarios;
    ArrayList<String> interseccion;

    public PartidoRecomendado(ArrayList<Partido> partidos, ArrayList<User> usuarios) {
        this.partidos=partidos;
        this.usuarios=usuarios;
        interseccion= new ArrayList<String>();

    }

    public void partidosAceptables(){

        User uno=usuarios.get(0);
        User dos=usuarios.get(1);
        User tres= usuarios.get(2);

        Collection<String> interna= getIntersection(uno.getEquipos(), dos.getEquipos(), tres.getEquipos());
        interseccion.addAll(interna);



        for (int i = 0; i <interseccion.size() ; i++) {
            System.out.println("Equipos en común: "+interseccion.get(i));
        }


        //AQUI SACO LOS IDEALES

        for (int i = 0; i <partidos.size() ; i++) {
            Partido p= partidos.get(i);

            for (int j = 0; j <interseccion.size() ; j++) {
                if (interseccion.get(j).equals(p.getEquipoUno())| interseccion.get(j).equals(p.getEquipoDos()) ){

                    System.out.println("partido aceptable: "+p.getEquipoUno()+" "+p.getEquipoDos());

                }
            }

        }


    }

    public void partidosIdeales(){

        //yo aqui debo guardar todos los partidos de los usuarios y mirar si en algún partido dos de esos se encuentran dos equipos que se enfrenten :v

        ArrayList<ArrayList<String>> totalEquipos= new ArrayList<>();
        String[] insd= new String[800];

        for (int i = 0; i <usuarios.size() ; i++) {

            User u= usuarios.get(i);
            totalEquipos.add(u.getEquipos());

        }




    }


        //RETAIN
    public static <T> Collection<T> getIntersection(Collection<T>... sets) {
        Collection<T> firstSet;
        if (sets == null || sets.length == 0 || (firstSet = sets[0]) == null)
            return Collections.<T> emptySet();
        Collection<T> intersection = new HashSet(firstSet);
        for (Collection c : sets) {
            if (c == null)
                return Collections.<T> emptySet();
            intersection.retainAll(c);
        }
        return intersection;
    }




}
