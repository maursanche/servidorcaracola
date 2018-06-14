/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;


import java.io.IOException;
import java.net.ServerSocket;
import java.util.Calendar;
import java.util.Date;



/**
 *
 * @author am
 */
public class servidor_caracola {
    public static final String SIQ_VERSION = "0.1";
    public static final int    SIQ_SOLICITUD_STATUS = 1;
    public static final int    SIQ_SOLICITUD_PAR_IMPAR = 2;
    public static final int    SIQ_PUERTO_SOCKET=8080;
    
         
    public void  inicia_servicio_socket(){
      Date fecha_actual = Calendar.getInstance().getTime();
      
       System.out.println("-----------------------------------------------");
       System.out.println("Santiago " +fecha_actual.toString()+"\n");
       System.out.println("Servidor Caracola version " + SIQ_VERSION );
       System.out.println("Inicio del servicio Socket en puerto " +SIQ_PUERTO_SOCKET);
      
        
        ServerSocket   siq_servidor; 
        siq_servidor=null;
         
        try {
                siq_servidor= new ServerSocket(SIQ_PUERTO_SOCKET);
        } catch (IOException ex) {
                System.out.println("Fallo el inicio del servidor.");            
        }
  
        if(siq_servidor==null){
            return;
        }   
         
        while(true){
            try {
                new Thread(new procesa_solicitudes_via_socket_de_caracola(siq_servidor.accept())).start();
            } catch (IOException ex) {
                    // Logger.getLogger(servidor_socket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }//final del while... que espera multiples conexiones.   
        
        
        
      // System.out.println("-----------------------------------------------");
    }//-----------------------------------------------------
    
        
    
 
    public static void main(String[] args){
        
        servidor_caracola nueva_instancia = new servidor_caracola();                      
        nueva_instancia.inicia_servicio_socket();
              
    }
    
     
}


