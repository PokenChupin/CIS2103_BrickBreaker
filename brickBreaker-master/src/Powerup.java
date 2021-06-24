
public class Powerup extends Hittable implements Comparable<Powerup>{

	public final double vY;
	
	public Powerup(int x, int y, int w, int h, double vY, String filename) {
		super(x, y, w, h, filename);
		//set cosntant dowards speed for powerup
		this.vY = vY;
	}
	
	//check if powerup is still on screen
	public boolean isGone(int height) {
            return (this.y > height); 
	}
	
	//get velcotity downards
	public double getvY() {
            return vY;
	}

	public void fall() {
            this.y += this.vY; 
	}

	@Override
	public int compareTo(Powerup o) {
            // TODO Auto-generated method stub
            return 0;
	}
}
