/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ConnectException;

/**
 *
 * @author Monica Ciuchetti
 */
public class ClientConnessioneTCP {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        ClientTCP client = new ClientTCP();
        client.connessione();
        client.comunica();
       // client.chiediData();
        client.chiudiConnessione();
}
}
