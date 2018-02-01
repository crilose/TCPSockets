/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Cristiano
 */
public class ClientTCP {
    
    //Il socket per la connessione
    Socket connessione = null;
    //nome o IP del server
    String serverIp = "localhost";
    //porta del server in ascolto
    int porta = 2000;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date data = new Date();
    
    //Creiamo i due stream che ci servono
    DataInputStream dataIn;
    DataOutputStream dataOut;
    BufferedReader file;
    PrintWriter fileOut;
    
    //Creiamo lo scanner per l'input
    Scanner input = new Scanner(System.in);
    
    public void connessione()
    {
        try{
            connessione = new Socket(serverIp, porta);
            System.out.println("Connessione aperta");
            file = new BufferedReader(new FileReader("testfile.txt"));
            fileOut = new PrintWriter(connessione.getOutputStream());
        }
        catch(ConnectException e){
            System.err.println("Server non disponibile!");
        }
        catch(UnknownHostException e1){
            System.err.println("Errore DNS!");
        }

        catch(IOException e2){//
            System.err.println(e2);
            e2.printStackTrace();
        }
    }
    
    public void comunica()
    {
        String riga;
        try {  
                while ((riga = file.readLine()) != null)
                {
                    fileOut.println(riga);
                    fileOut.flush();
                }
   
            } catch (IOException ex) {
                System.err.println(ex);
            }
    }
    
 
    
    public void chiudiConnessione()
    {
        try {
            if (connessione!=null)
                {
                    connessione.close();
                    System.out.println("Connessione chiusa dal server!");
                }
            }
            catch(IOException e){
                System.err.println("Errore nella chiusura della connessione!");
            }
    }
    
}
