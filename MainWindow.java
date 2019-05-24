import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.*;

public class MainWindow extends JFrame implements MouseMotionListener, MouseListener{
  BufferedImage[] screens;
  int currentScreen;
  int currentHeight;

  public MainWindow(){
    super("Tellecole Demo: BETA TESTING");
    setSize(500, 1000);
    screens = new BufferedImage[5];
    currentScreen = 0;
    currentHeight = 0;
    try{
      screens[0] = ImageIO.read(new File(""));//Waiting for pictures
    }catch(IOException e){
      System.out.println("A Fatal Error Has Occured. Images failed to load");
      e.printStackTrace();
    }
    addMouseListener(this);
    addMouseMotionListener(this);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    repaint();
  }

  public void paint(Graphics g){
    g.setColor(new Color(0, 0, 0));
    g.fillRect(0, 0, getWidth(), getHeight());
    g.drawImage(screens[currentScreen], currentHeight, 0, 500, 10000, null);
  }

  public void mousePressed(MouseEvent me){
    int x = me.getX();
    int y = me.getY();  
  }

  public void mouseClicked(MouseEvent me){}

  public void mouseReleased(MouseEvent me){}

  public void mouseEntered(MouseEvent me){}

  public void mouseExited(MouseEvent me){}

  public void mouseMoved(MouseEvent me){}
  
  public void mouseDragged(MouseEvent me){
    int y = me.getY();
  }

  public static void main(String[]args){
    new MainWindow();
  }
  
}
