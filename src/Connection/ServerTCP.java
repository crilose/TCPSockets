/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
BufferedReader output;
    
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
                output = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
                
            }
               catch(IOException e){
                   System.err.println("Errore di I/O!");
            }
    }
    
    public void comunica()
    {
        String riga;
        System.out.println("Leggo il file: ");
        try {
                while((riga = output.readLine())!=null)
                {
                    System.out.println(riga);
                }
        }
            catch (IOException ex) {
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
