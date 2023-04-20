/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatServer;

import java.util.ArrayList;

/**
 *
 * @author Felix
 */
public class BenutzerListe {
    
    private ArrayList<Benutzer> benutzerList=new ArrayList<Benutzer>();
 
    public BenutzerListe(){
       
    }
    
    public void addBenutzer(Benutzer pBenutzer){
        
            benutzerList.add(pBenutzer);
            
    }
    
    public Benutzer istAngemeldet(String pClientIP, int pClientPort){
        Benutzer ret = null;
        
        for( Benutzer k: benutzerList ){
            if(k.getClientIP().equals(pClientIP) && k.getClientPort() == pClientPort){
                ret = k;
            }
        }     
        return ret;
    }

    public Benutzer getBenutzerByName(String pClientName) {
        Benutzer ret = null;  
        for( Benutzer k: benutzerList ){
            if(k.getClientName().equals(pClientName)){
                ret = k;
            }
        }   
        return ret;
    }
    
    public Benutzer entferneBenutzer(Benutzer pBenutzer) {
        benutzerList.remove(pBenutzer);
        return pBenutzer;
    }
    
    public String alleBenutzer(){
        String alleBenutzer="";
        for(Benutzer k:benutzerList){
            alleBenutzer = alleBenutzer +" ; "+k.getClientName(); 
        }
        return alleBenutzer;
    }
    
}
