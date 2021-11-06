/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFrameUtil;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Julio
 */
public class JTableUtil {
    
    //crea table model y lo inicializa con sus nombres columnas
    public static DefaultTableModel inicializeTableModel(String ...name_columns){
      //DefaultTableModel table=new DefaultTableModel(new String[]{"COLA"},0);//opcional table
      DefaultTableModel table=new DefaultTableModel(name_columns,0);//opcional table
      return table;
    }
        public static void inicializeTableModel(JTable jtable,String ...name_columns){
      //DefaultTableModel table=new DefaultTableModel(new String[]{"COLA"},0);//opcional table
      DefaultTableModel table=new DefaultTableModel(name_columns,0);//opcional table
      jtable.setModel(table);
      return ;
    }
    
        
    //asigno ResultSet datos SQl a una Tabla
    public static void ResultSetToTable(ResultSet resultset,JTable jtable){
       
        try {
            //ResultSet r=NPersona.BuscarDatos(cn);
            //obtengo nombres de columnas
            ResultSetMetaData rsmd = resultset.getMetaData();
            String []columns=new String[rsmd.getColumnCount()];
            System.out.println("num columns: "+rsmd.getColumnCount());
            //primer bucle es para obtener nombre de columnas
            for(int i=0;i<columns.length;i++)
                columns[i]=rsmd.getColumnName(i+1);//+1 ya que maneja indices desde 1 no 0
            
            DefaultTableModel tablemodel=inicializeTableModel(columns);//inicializo primera linea, columnas 
            //obtengo datos, solo puedo obtener de una linea, uso el next para avanzar linea, devuelve null o false cuando ya no hay
           
            //obtengo todos los da tos
            String []lineData=new String[columns.length];
            while(resultset.next()){
                for(int i=0;i<columns.length;i++)
                    lineData[i]=resultset.getString(columns[i]);
                //agrego datos a mi tabla
                tablemodel.addRow(lineData);
            }
            jtable.setModel(tablemodel);
            
            /* } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"Error al cargar la tabla datos.\n"+ex.getMessage());
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        } catch (SQLException ex) {
            Logger.getLogger(JTableUtil.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error al cargar la tabla datos.\n"+ex.getMessage());
        }
      
    }
    //Obtengo data, segun numero de linea y nombre de columna //retorna null si el index de la linea es -1 o no lo pilla en la tabla
    public static String getData(JTable jtable,int rowIndex,String nameColumn){
        try{
        //obtengo cedula de tabla, tambien puedo obtener todos los datos pero mejor y mas seguro es hacer la consulta directo en la bd
        String data=jtable.getValueAt(rowIndex,//indice linea
                jtable.getColumn(nameColumn).getModelIndex())//Nombre columna
                .toString();
        return data;
        }catch(ArrayIndexOutOfBoundsException ex){
            //Este catch es para cuando se refresca la tabla y marca este error
            System.out.println("Tabla vacia");
            return null;
        }

    }
}
