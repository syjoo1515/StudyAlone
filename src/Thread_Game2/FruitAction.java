package Thread_Game2;


import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class FruitAction extends JFrame{
	private int x=0,y=0;
	private Image img;

	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public FruitAction() {
		super();
	}
	
	public FruitAction(int x, int y, Image img) {
		super();
		this.x=x;
		this.y=y;
		this.img=img;
		
		
	}

}