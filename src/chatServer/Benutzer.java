/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatServer;

/**
 *
 * @author Felix
 */
public class Benutzer {
   private String clientName;
   private String clientIP;
   private int clientPort;
    
    public Benutzer(String pClientName, String pClientIP, int pClientPort){
        clientName= pClientName;
        clientIP=pClientIP;
        clientPort=pClientPort;
    }

    /**
     * @return the ClientName
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * @param ClientName the ClientName to set
     */
    public void setClientName(String pClientName) {
        this.clientName = pClientName;
    }

    /**
     * @return the ClientIP
     */
    public String getClientIP() {
        return clientIP;
    }

    /**
     * @param ClientIP the ClientIP to set
     */
    public void setClientIP(String pClientIP) {
        this.clientIP = pClientIP;
    }

    /**
     * @return the ClientPort
     */
    public int getClientPort() {
        return clientPort;
    }

    /**
     * @param ClientPort the ClientPort to set
     */
    public void setClientPort(int pClientPort) {
        this.clientPort = pClientPort;
    }
}
