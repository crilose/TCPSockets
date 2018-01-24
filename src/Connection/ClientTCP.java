/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
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
    
    //Creiamo i due stream che ci servono
    DataInputStream dataIn;
    DataOutputStream dataOut;
    
    //Creiamo lo scanner per l'input
    Scanner input = new Scanner(System.in);
    
    public void connessione()
    {
        try{
            connessione = new Socket(serverIp, porta);
            System.out.println("Connessione aperta");
            dataIn = new DataInputStream(connessione.getInputStream());
            dataOut = new DataOutputStream(connessione.getOutputStream());
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
        try {
                System.out.println("Inserisci il tuo messaggio: ");
                String msg = input.nextLine();
                dataOut.writeUTF(msg);
                dataOut.flush();
                
                System.out.println("Messaggio del server: " + dataIn.readUTF());
                
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
