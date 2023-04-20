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
public class ServerProtokoll {

    private BenutzerListe benutzer;
    private ChatServer chatServer;

    ServerProtokoll(BenutzerListe pUserListe, ChatServer pChatServer) {
        benutzer = pUserListe;
        chatServer = pChatServer;
    }

    public String anmelden(String pClientIP, int pClientPort, String pMessage) {
        String[] data = pMessage.split(":", 3);
        System.out.println(data[1]);
        if (data[1].trim().equals("")) {

            return "leerer Benutzername nicht erlaubt";
        }
        if (data.length == 3) {
            if (data[2].trim().equals("rieb")) {
                if (benutzer.getBenutzerByName(data[1]) != null) {
                    return data[1] + " Benutzername schon vorhanden";
                }
                benutzer.addBenutzer(new Benutzer(data[1], pClientIP, pClientPort));
                return data[1] + " wurde erfolgreich angemeldet";
            }
        }
        return "falsches Passwort";
    }

    public void senden(String pClientName, String pMessage) {
        String[] data = pMessage.split(":", 2);
        if (data.length == 2) {
            chatServer.sendToAll(pClientName + ": " + data[1]);
        }
    }

    public void fluestern(Benutzer pBenutzer, String pMessage) {
        String[] data = pMessage.split(":", 3);
        if (data.length == 3) {
            Benutzer k = benutzer.getBenutzerByName(data[1]);
            if (k != null) {
                chatServer.send(pBenutzer.getClientIP(), pBenutzer.getClientPort(), "Nachricht an " + k.getClientName() + " :" + data[2]);
                chatServer.send(k.getClientIP(), k.getClientPort(), "Nachricht von " + pBenutzer.getClientName() + " :" + data[2]);
            } else {
                chatServer.send(pBenutzer.getClientIP(), pBenutzer.getClientPort(), "Benutzer: " + data[1] + " nicht gefunden");
            }
        }
    }

    public void abmelden(Benutzer pBenutzer, String pMessage) {
        benutzer.entferneBenutzer(pBenutzer);
        chatServer.send(pBenutzer.getClientIP(), pBenutzer.getClientPort(), "Abgemeldet");
    }

    public void trennen(Benutzer pBenutzer) {
        chatServer.send(pBenutzer.getClientIP(), pBenutzer.getClientPort(), "Trenne Verbindung");
        chatServer.closeConnection(pBenutzer.getClientIP(), pBenutzer.getClientPort());
    }

    public void liste(Benutzer pBenutzer) {
        chatServer.send(pBenutzer.getClientIP(), pBenutzer.getClientPort(), benutzer.alleBenutzer());
    }
}
