
public class Ball extends Hittable{
	
	//create fields for velocity
	double vX;
	double vY;
	
	public Ball(int x, int y, int w, int h, String filename) {
		super(x, y, w, h, filename);
		
		//set initial velocity for new ball
		this.vX = 0; 
		this.vY = 1;
	}
        
        
	public void moveBall(double xcur, double ycur, double xprev, double yprev, double xdprev, double ydprev, double gameWidth){
            if(this.x >= (gameWidth - this.width) || this.x <= 0) {
		xcur = this.getvX();
		ycur = this.getvY();
		if (Math.abs(this.getvX()) < 1) {
                    this.setvX(Math.copySign(1, this.getvX() * -1));
		}
		else {
                    this.setvX(this.getvX() * -1);
		}
		if(Math.abs(xcur) == Math.abs(xprev) && Math.abs(xprev) == Math.abs(xdprev)) {
                    this.x += (this.x < 20)? 5: -5;
		}
		xprev = xcur;
		xdprev = xprev;
		if(Math.abs(ycur) == Math.abs(yprev) && Math.abs(yprev) == Math.abs(ydprev)) {
                    this.y += 5; 
		}
		yprev = xcur;
		ydprev = xprev;
            }
            
            if(this.y < 0) {
		this.setvY(this.getvY() * -1);
            }
            
            move();
            
        }
        
        public void bouncePaddle(double paddleX, double paddleWidth){
            if(this.getvY() < 3) {
                this.setvY(3);
            }
            this.setvY(this.getvY() * -1);
            double xDif = (double) (paddleX + (paddleWidth/2) - (this.x + (this.width/2)));
                if (xDif > 0) {
                    this.setvX((Math.abs(xDif))/(paddleWidth/2) * -2.0);	
		}
		else {
                    this.setvX((((paddleWidth/2) -xDif))/(paddleWidth/2) * 2.0);	
            }
        }
	
	//return whether or not the ball is moving
	public boolean isMoving() {
		return (vX != 0 && vY !=0);
	}
        
        
        
	//move the ball
	public void move() {
		this.x += this.vX;
		this.y += this.vY;
	}
	
	//getters and setters for two velcoity components
	public double getvX() {
		return vX;
	}

	public void setvX(double vX) {
		this.vX = vX;
	}

	public double getvY() {
		return vY;
	}

	public void setvY(double vY) {
		this.vY = vY;
	}
	

}
