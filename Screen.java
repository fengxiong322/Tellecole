import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;

public class Screen{
	BufferedImage image;
	ArrayList<Polygon> buttons;
	int[] nextScreen;//Holds what the button should do
	public Screen(BufferedImage image, String file){
		this.image = image;
		buttons = new ArrayList<Polygon>();
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			int counter = 0;
			String temp;
			while(true){
				temp = br.readLine();
				String[] stringTockens = temp.split(" ");
				int[] tockens = new int[stringTockens.length];
				for(int i = 0; i < stringTockens.length; i++)
					tockens[i] = Integer.parseInt(stringTockens[i]);
				if(tockens.length==1)
					break;
				int[] x = Arrays.copyOfRange(tockens, 0, tockens.length/2);
				int[] y = Arrays.copyOfRange(tockens, tockens.length/2, tockens.length);
				buttons.add(new Polygon(x, y, x.length));
				counter++;
			}
			nextScreen = new int[counter];
			for(int i = 0; i < counter; i++){
				nextScreen[i] = Integer.parseInt(temp);
				temp = br.readLine();
			}
		}catch(IOException e){e.printStackTrace();}
	}

	public void draw(Graphics g, int height){
		g.drawImage(image, 0, height, null);
	}

	public int findNextScreen(int x, int y){
		for(int i = 0; i < buttons.size(); i++){
			if(buttons.get(i).contains(x, y))
				return nextScreen[i];
		}
		return -1;
	}
}