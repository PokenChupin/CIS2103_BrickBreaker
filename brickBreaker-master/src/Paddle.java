
public class Paddle extends Hittable{

	private boolean big, small; 
	
	public Paddle(int x, int y, int w, int h, String filename) {
		super(x, y, w, h, filename);
		// TODO Auto-generated constructor stub
	}
        
        public void movePaddle(boolean right, boolean left, double gameWidth){
            if(right==true && this.x < gameWidth - this.width){
                this.x+= 4;
            }
            if(left==true && this.x > 0){
                this.x-=4;
            }
        }
	
	//increase the width of the paddle
	public void grow() {
		if (!big) {
			this.width *= 2;
			if (small) {
				small = false; 
				big = false;
			}
			else {
				small = false;
				big = true;
			}
		}
	}
	//decrease the paddle size
	public void shrink() {
		if(!small) {
			this.width *= 0.5;
			if (big) {
				small = false; 
				big = false;
			}
			else {
				small = true;
				big = false;
			}
		}
	}
	//decrease the size of the paddle
	//is the block big or small?
	public boolean isBig() {
		return big;
	}
	public boolean isSmall() {
		return small;
	}
}
