
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import processing.core.PApplet;

public class ManagerComunicacion implements Observer, Runnable {

    private final int PORT = 5000;
    private ServerSocket socketServidor;
    private boolean conectado;
    private ArrayList<Comunicacion> clientes = new ArrayList<>();
    private int id;
    PApplet app;
    private String code="";

    public ManagerComunicacion(PApplet app) {
        this.app=app;

        try {
            socketServidor = new ServerSocket(PORT);
            conectado = true;
            System.out.println("[Servidor]: Atendiendo en " + InetAddress.getLocalHost().getHostAddress().toString()
                    + ":" + this.PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        cod();
    }

    @Override
    public void run() {
        while (conectado) {
            try {

                // Esperar a que un cliente se conecte
                Socket s = socketServidor.accept();

                Comunicacion com = new Comunicacion(s, clientes.size());

                // Agregar el gestor como observador
                com.addObserver(this);

                // Comenzar el hilo de ejecuciÃ³n del contrlador
                new Thread(com).start();

                // Agregar a la colecciÃ³n de clientes
                clientes.add(com);
                System.out.println("[Servidor] Tenemos: " + clientes.size() + " clientes");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void update(Observable o, Object msn) {

        Comunicacion controladorCliente = (Comunicacion) o;

        if (msn instanceof String) {

            String mensaje = (String) msn;
            System.out.println(mensaje);

            if (mensaje.equalsIgnoreCase("cliente desconectado")) {
                clientes.remove(controladorCliente);
                System.out.println("[Servidor] Tenemos: " + clientes.size() + " clientes");
            }

            if (mensaje.contains("codigo")){
                System.out.println("Welcome to the jungle");
                String[] partes = mensaje.split(":");

                if (partes[2].contains(code)){

                    System.out.println("CÓDIGO ACEPTADO");
                    controladorCliente.enviarMensaje("CÓDIGO ACEPTADO");


                }

            }

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

    public void pintar(){
        app.textSize(30);
        app.fill(0);
        app.text("Ingrese en su celular el código: " + code, app.width/2, app.height/2);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
