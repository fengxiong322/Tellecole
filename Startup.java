import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;

public class Startup extends JPanel{
	ExitListener el;

	public Startup(int x, int y, ExitListener el){
		setSize(x, y);
		this.el = el;
	}

	public void paint(Graphics g){
		
	}
}