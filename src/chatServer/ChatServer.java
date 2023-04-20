package chatServer;

import abiturklassen.netzklassen.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ChatServer extends Server {

    final String ENDE = "*bye*";

    private BenutzerListe benutzerList=new BenutzerListe();
    private ServerProtokoll protokoll;
    
    public ChatServer() {
      super(2000);
      protokoll=new ServerProtokoll(benutzerList,this);
    }

    public void processNewConnection(String pClientIP, int pClientPort) {
     //Verbindungsaufbau Begrueßung
        this.send(pClientIP, pClientPort, "Willkommen " + pClientIP + " auf Port "
        + pClientPort + " bei unserem chat!");
     
    }

    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
     //Anmeldung
        if(pMessage.startsWith("Anmelden")){
         this.send(pClientIP, pClientPort, protokoll.anmelden(pClientIP, pClientPort, pMessage));
         
         
      }
      //Fluestern
        else if(pMessage.startsWith("Fluestern")){
           Benutzer benutzer = benutzerList.istAngemeldet(pClientIP, pClientPort);
          if(benutzer==null){
              this.send(pClientIP, pClientPort, "Nicht angemeldet");
          }else{
              protokoll.fluestern(benutzer, pMessage);
          }

      } 
      //Trennen
        else if(pMessage.startsWith("Trennen")){
         
            Benutzer benutzer = benutzerList.entferneBenutzer(benutzerList.istAngemeldet(pClientIP, pClientPort));
            protokoll.trennen(benutzer);
      }
      //Sende
       else if(pMessage.startsWith("Sende")){
          Benutzer benutzer = benutzerList.istAngemeldet(pClientIP, pClientPort);
          if(benutzer==null){
              this.send(pClientIP, pClientPort, "Nicht angemeldet");
          }else{
              protokoll.senden(benutzer.getClientName(), pMessage);           
          }
      
      }
       //List
       else if(pMessage.startsWith("List")){
           Benutzer benutzer = benutzerList.istAngemeldet(pClientIP, pClientPort);
          if(benutzer==null){
              this.send(pClientIP, pClientPort, "Nicht angemeldet");
          }else{
               protokoll.liste(benutzer);           
               
          }

      } else if(pMessage.startsWith("Abmelden")){
         
            Benutzer benutzer = benutzerList.entferneBenutzer(benutzerList.istAngemeldet(pClientIP, pClientPort));
              protokoll.abmelden(benutzer, pMessage);
            
            
      }
       //unbekannte Anweisung
      else{
         
         this.send(pClientIP, pClientPort, "Unbekannte Anweisung - Geben Sie einen korrekte Anweisung an");
      
      }
    }
    
    public void processClosedConnection(String pClientIP, int pClientPort) {
        //Verbindung getrennt
      this.send(pClientIP, pClientPort, pClientIP + " " + pClientPort +
      " Danke fuer die Teilnahme an unserem Chat.");
     
      this.sendToAll(pClientIP + " " + pClientPort + " hat sich abgemeldet.");
      this.send(pClientIP, pClientPort, ENDE);
    }

    
}
