package chatServer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import abiturklassen.netzklassen.*;

/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 13.06.2006
  * @author
  */
public class CServer extends JFrame {
  // Anfang Variablen

  // Anfang Attribute
  private JButton jButton1 = new JButton();
  private JLabel jLabel1 = new JLabel();
  private ChatServer chatServer=new ChatServer();
  // Ende Attribute

  // Ende Variablen

  public CServer(String title) {
    // Frame-Initialisierung
    super(title);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) { System.exit(0); }
    });
    int frameWidth = 181;
    int frameHeight = 180;
    setSize(frameWidth, frameHeight);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten

    jButton1.setBounds(40, 80, 75, 25);
    jButton1.setText("Stop");
    cp.add(jButton1);
    jButton1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });

    jLabel1.setBounds(32, 24, 99, 24);
    jLabel1.setText("Chat-Server");
    jLabel1.setFont (new Font("MS Sans Serif", Font.PLAIN, 17));
    cp.add(jLabel1);
    // Ende Komponenten

    setResizable(false);
    setVisible(true);
  }

  // Anfang Methoden

  // Anfang Ereignisprozeduren
  public void jButton1ActionPerformed(ActionEvent evt) {
    chatServer.close();
    System.exit(0);
  }

  // Ende Ereignisprozeduren

  public static void main(String[] args) {
    new CServer("ChatServer");
  }
  // Ende Methoden
}

