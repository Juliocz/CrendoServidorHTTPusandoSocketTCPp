/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReadWriterUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julio
 */
public class InputStreamUtil {
    public static byte[] readAllBytes(InputStream inputStream,boolean close){
        try {
            byte[]buff=new byte[inputStream.available()];
            inputStream.read(buff);
            //System.out.println("bufferMetodo:\n"+new String(buff));
            if(close)inputStream.close();
            return buff;
        } catch (IOException ex) {
            Logger.getLogger(InputStreamUtil.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
    }
    public static byte[] readAllBytes(int sizeRead,InputStream inputStream,boolean close){
        try {
            byte[]buff=new byte[sizeRead];
            inputStream.read(buff);
            //System.out.println("bufferMetodo:\n"+new String(buff));
            if(close)inputStream.close();
            return buff;
        } catch (IOException ex) {
            Logger.getLogger(InputStreamUtil.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
    }
}
