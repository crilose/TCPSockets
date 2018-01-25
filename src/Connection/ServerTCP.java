/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristiano
 */
public class ServerTCP {

//Il socket per la connessione
Socket connessione = null;
//Il socket per i dati
ServerSocket sSocket = null;
//nome o IP del server
String serverIp = "localhost";
//porta del server in ascolto
int porta = 2000;
DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
Date data = new Date();
    
    
//Creiamo i due stream che ci servono
DataInputStream dataIn;
DataOutputStream dataOut;
    
//Creiamo lo scanner per l'input
Scanner input = new Scanner(System.in);

    public void connessione()
    {
        try{
                // il server si mette in ascolto sulla porta voluta
                sSocket = new ServerSocket(porta);
                System.out.println("In attesa di connessioni!");
                //si è stabilita la connessione
                connessione = sSocket.accept();
                System.out.println("Connessione stabilita!");
                dataIn = new DataInputStream(connessione.getInputStream());
                dataOut = new DataOutputStream(connessione.getOutputStream());
            }
               catch(IOException e){
                   System.err.println("Errore di I/O!");
            }
    }
    
    public void comunica()
    {
        try {
                if(dataIn.readUTF().contentEquals("Voglio la data"))
                {
                    comunicaData();
                }
                else
                {
                    System.out.println("Messaggio del client: " + dataIn.readUTF());
                }
            } catch (IOException ex) {
                System.err.println(ex);
            }
    }
    
    public void comunicaData()
    {

            try {
                dataOut.writeUTF(dateFormat.format(data));
                dataOut.flush();
            } catch (IOException ex) {
                System.err.println(ex);
            }
    
    }
    
    public void chiudiConnessione()
    {
        if (sSocket!=null) try {
            sSocket.close();
        } catch (IOException ex) {
            System.err.println("Errore nella chiusura della connessione!");
        }
        System.out.println("Connessione chiusa!");
    }
}
