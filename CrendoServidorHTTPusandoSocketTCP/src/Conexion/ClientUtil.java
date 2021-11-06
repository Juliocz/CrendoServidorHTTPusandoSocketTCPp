/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import ReadWriterUtil.InputStreamUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julio
 */
public class ClientUtil {
    
    //metodo que hace peticion get como si fuera chrome
    //host localhost or http://localhost  get: /hola
    //example http://localhost/hola =GetBuffWithChrome("localhost","/hola");
    public static byte[] GetBuffWithChrome(String host,String get){
        try {
            Socket socket=new Socket(host,80);
            //socket.setSendBufferSize(0);
            String buff=getDefaultHeaderChrome();
            if(!get.isEmpty())buff=buff.replaceFirst("GET /","GET "+get);
            return sendByte(buff.getBytes(), socket);
        } catch (IOException ex) {
            Logger.getLogger(ClientUtil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    
    //envia datos al sockets
    private static byte[] sendByte(byte[]buff,Socket socket) throws IOException{
       // buff=Server.resizeByteArray(buff,500);
        //socket.setSendBufferSize(buff.length);
        
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 // Obtener la secuencia de salida, es decir, los datos que escribimos en el servidor
        //BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
 
        socket.getOutputStream().write(buff);
        socket.getOutputStream().flush();
        //bufferedWriter.write("GET /v3/weather/weatherInfo?city=%E9%95%BF%E6%B2%99&key=13cb58f5884f9749287abbead9c658f2 HTTP/1.1\r\n");
        //bufferedWriter.write("Host: restapi.amap.com\r\n\r\n");
        
        //bufferedWriter.flush();
                 // Usa un hilo para leer la respuesta del servidor , mejor usare libreria http para peticiones
        
               //para hacer peticion es mas dificil, ya que el buffer nunca queda nulo, el socket queda abierto
               //se pone a leer para siempre
        new Thread() {
            @Override
            public void run() {
                while (true ) {
                    String line = null;
                    try {
                        while ((line = bufferedReader.readLine()) != null) {
                            System.out.println("recv : " + line);
                        }
                        
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
 
        /*bufferedWriter.write("GET /v3/weather/weatherInfo?city=%E9%95%BF%E6%B2%99&key=13cb58f5884f9749287abbead9c658f2 HTTP/1.1\r\n");
        bufferedWriter.write("Host: restapi.amap.com\r\n\r\n");
        bufferedWriter.flush();*/

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        /*socket.getOutputStream().write(buff);//envia buffer bytes, buffer completo sin enviar cabezera ni header ni nada
        
        byte[]respuesta=null;
        
        new Thread() {
            @Override
            public void run() {

        while(respuesta==null || respuesta.length==0){
            try {
                byte[]respuesta=InputStreamUtil.readAllBytes(socket.getInputStream(),false);
            } catch (IOException ex) {
                Logger.getLogger(ClientUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("client buffer: "+respuesta.length);
       }}.start();
        
        //System.out.println("client buffer: "+respuesta.length);
        //socket.getOutputStream().close();
        //socket.getInputStream().close();
        //socket.close();
        socket.getOutputStream().flush();*/
        return "leyendo".getBytes();
        
    }
    
    public static String getDefaultHeaderChrome(){
        return "GET / HTTP/1.1\n" +
"Host: localhost\n" +
"Connection: keep-alive\n" +
"sec-ch-ua: \"Google Chrome\";v=\"95\", \"Chromium\";v=\"95\", \";Not A Brand\";v=\"99\"\n" +
"sec-ch-ua-mobile: ?0\n" +
"sec-ch-ua-platform: \"Windows\"\n" +
"Upgrade-Insecure-Requests: 1\n" +
"User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Safari/537.36\n" +
"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\n" +
"Sec-Fetch-Site: none\n" +
"Sec-Fetch-Mode: navigate\n" +
"Sec-Fetch-User: ?1\n" +
"Sec-Fetch-Dest: document\n" +
"Accept-Encoding: gzip, deflate, br\n" +
"Accept-Language: es-ES,es;q=0.9\n\n";
    }
}
