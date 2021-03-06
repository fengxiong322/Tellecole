import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class MainWindow extends JFrame implements MouseMotionListener, MouseListener, ActionListener{
  Screen[] screens;
  int currentScreen;
  int currentHeight;
  int y;
  int clickPoint;
  int sizeX;
  int sizeY;
  boolean setup;
  float alpha = 0f;
  Timer timer;

  public MainWindow(){
    super("Tellecole Demo: BETA TESTING");
    setSize(500, 700);
    screens = new Screen[6];
    currentScreen = 0;
    currentHeight = 0;
    clickPoint = 0;
    try{
      screens[0] = new Screen(ImageIO.read(new File("home.png")), "home.txt");
      screens[1] = new Screen(ImageIO.read(new File("logo.jpg")), "logo.txt");
      screens[2] = new Screen(ImageIO.read(new File("announcements.png")), "announcements.txt");
      screens[3] = new Screen(ImageIO.read(new File("teacher.png")), "teacher.txt");
      screens[4] = new Screen(ImageIO.read(new File("FAQ.png")), "FAQ.txt");
      screens[5] = new Screen(ImageIO.read(new File("SignUp.jpg")), "SignUp.txt");
    }catch(IOException e){
      System.out.println("A Fatal Error Has Occured. Images failed to load");
      e.printStackTrace();
    }
    setup = true;
    timer = new Timer(20, this);
    timer.setInitialDelay(30);
    timer.start();
    addMouseListener(this);
    addMouseMotionListener(this);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    repaint();
  }

  public void paint(Graphics g){
    if(setup)
      setup(g);
    else
      screens[currentScreen].draw(g, currentHeight);
  }

  public void setup(Graphics g){
      Graphics2D g2d = (Graphics2D) g.create();
      g2d.setComposite(AlphaComposite.SrcOver.derive(alpha));
      screens[1].draw(g2d, 0);
  }

  public void actionPerformed(ActionEvent evt) {
    alpha = alpha + 0.01f;
    if(alpha > 1f){
      timer.stop();
      setup = false;
      repaint();
    }else{
      timer.restart();
      repaint();
    }
  }

  public void mousePressed(MouseEvent me){
    y = me.getY();
    clickPoint = currentHeight;
  }

  public void mouseClicked(MouseEvent me){
    System.out.println(me.getX() + " " + me.getY());
    int next = screens[currentScreen].findNextScreen(me.getX(), me.getY()-currentHeight);
    if(next != -1){
      currentScreen = next;
      currentHeight = 0;
    }
    repaint();
  }

  public void mouseReleased(MouseEvent me){}

  public void mouseEntered(MouseEvent me){}

  public void mouseExited(MouseEvent me){}

  public void mouseMoved(MouseEvent me){}
  
  public void mouseDragged(MouseEvent me){
    if(clickPoint+me.getY()-y<=30 && clickPoint+me.getY()-y+700 >= screens[currentScreen].height())
      currentHeight = clickPoint+me.getY()-y;
    repaint();
  }

  public static void main(String[]args){
    new MainWindow();
  }
  
}
