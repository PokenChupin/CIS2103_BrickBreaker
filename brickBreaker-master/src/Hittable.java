import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//Basic class for all blocks, balls, powerups, and paddles in the game
public abstract class Hittable extends Rectangle{
	
	private Image blockPic; 
	//constructor takes in x/y coordinate, height/width, and image file name
	public Hittable(int x, int y, int w, int h, String filename) {
		//assign block fields
		this.x = x;
		this.y = y;
		
		this.width = w;
		this.height = h;
		
		//get image for block
		try {
			setBlockPic(ImageIO.read(new File("files/"+ filename)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
        
        public Hittable(int x, int y, int w, int h) {
		//assign block fields
		this.x = x;
		this.y = y;
		
		this.width = w;
		this.height = h;
		
		//get image for block
		try {
			setBlockPic(ImageIO.read(new File("files/"+ "blue.png")));
		} catch (IOException e) {
			
		}
	}
        
        public void hits(){
            
        }
	
	//draw the object
	public void draw(Graphics g, Component c) {
		g.drawImage(blockPic, x, y, width-3, height-3, c);
	}
	
	//getters and setters for image
	public Image getBlockPic() {
		return blockPic;
	}
	public void setBlockPic(Image blockPic) {
		this.blockPic = blockPic;
	}
	
	
	
}
