import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Block extends Hittable{
	
	//how many hits the block has
	private int hits;
	
	public Block(int x, int y, int w, int h, int hits) {
		//call super constructor but also take in an int, hits
		super(x, y, w, h);                            
		this.hits = hits;          
                setColor();
	}

	
	//if a block gets hit
	public void hit() {
            if (getHits() > 0) {
                this.hits --;
                setColor();                    
            }
	}
        
        private void setColor(){
            String filename = "blue.png";

            switch(this.hits){
                case 1:
                    filename = "blue.png";
                    break;
                case 2:
                    filename = "yellow.png";
                    break;
                case 3:
                    filename = "red.png";
                    break;  
            }

            try {
                setBlockPic(ImageIO.read(new File("files/"+ filename)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	
	//see how many hits a block has left
	public int getHits() {
		return hits;
	}
	
	//return a boolean of whether or not the block is destroyed
	public boolean isDestroyed() {
		return (hits < 1); 
	}
}
