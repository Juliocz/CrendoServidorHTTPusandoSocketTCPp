/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crendoservidorhttpusandosockettcp;

import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Julio
 */
public class MyButtonSimpleInfo extends JButton{
    String msg;
    @Override
    public void doClick() {
        super.doClick(); //To change body of generated methods, choose Tools | Templates.
        JOptionPane.showMessageDialog(this,msg);
    }

    public MyButtonSimpleInfo() {
    }

    public MyButtonSimpleInfo(String string) {
        super(string);
    }
    public MyButtonSimpleInfo(String string_text,String message_click) {
        super(string_text);
    }
}
