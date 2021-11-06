/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julio
 */
public abstract class Server extends Thread{
    int port=80;//puerto
    ServerSocket server;//sockect server
    ArrayList<Socket>clientsSockets=new ArrayList<>();//aqui se guarda todos los sockets conectados
    public Server() {}
    public Server(int port) {
        this.port=port;
    }
    public boolean startServer(){
           try {
            server = new ServerSocket(port);//Se crea el socket para el servidor en puerto 1234
            reportInfo("Servidor iniciado en puerto: "+port, false);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            //cuando ocurre un error al crear el servidor
            reportInfo("No se pudo iniciar servidor en"+port+"\n"+ex.getMessage(), true);
            return false;
        }
    }
    //inicio servidor
     @Override
    public void run(){
                while(true){
                    //aqui solo debo esperar clientes, ya despues otra clase se encargara de leer el socket cada momento
                try {                                   
                    reportInfo("Esperando clientes...", false);
                    Socket n_client=server.accept();//nuevo cliente
                    OnNewSocketClientConnection(n_client);//anuncio que hay nueva conecion
                    clientsSockets.add(n_client);//agrego a la lista sockets clientes
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    reportInfo("Ocurrio un error en la espera de clientes, se cancela\n"+ex.getMessage(), true);
                    return;
                }
            }
    }
    public void closeServer(){try {
        for(Socket s:clientsSockets)s.close();//cancelo todos los sockets
        server.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    public abstract void OnNewSocketClientConnection(Socket socket);
    public abstract void reportInfo(String msg_info,boolean exception);
}
