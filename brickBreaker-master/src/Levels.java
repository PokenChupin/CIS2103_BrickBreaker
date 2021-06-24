import java.util.LinkedList;
import java.util.List;

public class Levels {
	List<Block[][]> levels = new LinkedList<>();
	int width, height;
	//create blocks in terms of array index
	private Block create(int i, int j, int hits) {
            return new Block(j*width, i*height, width, height, hits);
	}
	
	public Levels(int width, int height) {
		//convert screen size to block dimensions
		this.width = width/10;
		this.height = height/24; 
		
                Block[][] levelOne = new Block[10][10];
		//add blocks... this will be a diamond patter
		levelOne[0][4] = create(0, 4, 1);
		levelOne[0][5] = create(0, 5, 1);
		levelOne[9][4] = create(9, 4, 1);
		levelOne[9][5] = create(9, 5, 1);
		
		for (int i = 3; i< 7; i++) {
			levelOne[1][i] = create(1, i, 1);
			levelOne[8][i] = create(8, i, 1);
		}
		for (int i = 2; i< 8; i++) {
			levelOne[2][i] = create(2, i, 1);
			levelOne[7][i] = create(7, i, 1);
		}
		for (int i = 1; i< 9; i++) {
			levelOne[3][i] = create(3, i, 1);
			levelOne[6][i] = create(6, i, 1);
		}
		for (int i = 0; i< 10; i++) {
			levelOne[4][i] = create(4, i, 1);
			levelOne[5][i] = create(5, i, 1);
		}
		levels.add(levelOne);
		
		//-------------------LEVEL TWO------------
		//create level one matrix
		Block[][] levelTwo = new Block[10][10];
		//add blocks... this will be a diamond patter
		for (int i = 0; i < 10; i++) {
			levelTwo[0][i] = create(0, i, 1);
			levelTwo[2][i] = create(2, i, 1);
			levelTwo[4][i] = create(4, i, 1);
			levelTwo[6][i] = create(6, i, 1);
			levelTwo[8][i] = create(8, i, 1);
		}
		
		for (int i = 0; i < 10; i+=2) {
			levelTwo[1][i] = create(1, i, 2);
			levelTwo[3][i] = create(3, i+1, 2);
			levelTwo[5][i] = create(5, i, 2);
			levelTwo[7][i] = create(7, i+1, 2);
		}	
		levels.add(levelTwo);
		
		//----------------LEVEL THREE------------------
		
		//create level one matrix
				Block[][] levelThree = new Block[10][10];
				//add blocks... this will be a diamond patter
				for (int i = 0; i < 10; i++) {
					levelThree[0][i] = create(0, i, 1);
					levelThree[9][i] = create(9, i, 1);
				}
				for (int i = 0; i < 4; i++) {
					levelThree[1][i] = create(1, i, 1);
					levelThree[1][9-i] = create(1, 9-i, 1);
					levelThree[8][i] = create(8, i, 1);
					levelThree[8][9-i] = create(8, 9-i, 1);
				}
				for (int i = 0; i < 3; i++) {
					levelThree[2][i] = create(2, i, 1);
					levelThree[2][9-i] = create(2, 9-i, 1);
					levelThree[7][i] = create(7, i, 1);
					levelThree[7][9-i] = create(7, 9-i, 1);
				}
				for (int i = 0; i < 2; i++) {
					levelThree[3][i] = create(3, i, 1);
					levelThree[3][9-i] = create(3, 9-i, 1);
					levelThree[6][i] = create(6, i, 1);
					levelThree[6][9-i] = create(6, 9-i, 1);
				}
				
				for (int i = 0; i < 1; i++) {
					levelThree[4][i] = create(4, i, 1);
					levelThree[4][9-i] = create(4, 9-i, 1);
					levelThree[5][i] = create(5, i, 1);
					levelThree[5][9-i] = create(5, 9-i, 1);
				}
				
				levelThree[4][4] = create (4, 4, 2);
				levelThree[4][5] = create (4, 5, 2);
				levelThree[5][4] = create (5, 4, 2);
				levelThree[5][5] = create (5, 5, 2);
				
				levelThree[0][0] = create (0, 0, 1);
				levelThree[0][9] = create (0, 9, 1);
				levelThree[9][0] = create (9, 0, 1);
				levelThree[9][9] = create (9, 9, 1);
				levels.add(levelThree);
				
				//---------------------LEVEL FOUR-------------------
				
				Block[][] levelFour = new Block[10][10];
				//add blocks... this will be a diamond patter
				for (int i = 0; i < 10; i+=2) {
					for (int j = 0; j < 10; j ++) {
						levelFour[j][i] = create(j, i, 1);
					}
					levelFour[0][i+1] = create(0, i+1, 2);
					levelFour[4][i+1] = create(4, i+1, 2);
					levelFour[5][i+1] = create(5, i+1, 2);
					levelFour[9][i+1] = create(9, i+1, 2);
				}
				
				levels.add(levelFour);
				
				//---------------------LEVEL FIVE-------------------
				
				Block[][] levelFive = new Block[10][10];
				//add blocks... this will be a diamond patter
				for (int i = 0; i < 10; i++) {
					for(int j = 0; j < 5; j++) {
						levelFive[i][j] = create(i, j, 1);
						levelFive[i][9-j] = create(i, 9-j, 1);
					}
					levelFive[i][i] = create(i, i, 2);
					levelFive[i][9-i] = create(i, 9-i, 2);
				}
				for (int i = 1; i <9; i++) {
					levelFive[0][i] = create(0, i, 1);
					levelFive[9][i] = create(9, i, 1);
				}
				for (int i = 2; i <8; i++) {
					levelFive[1][i] = create(1, i, 1);
					levelFive[8][i] = create(8, i, 1);
				}
				for (int i = 3; i <7; i++) {
					levelFive[2][i] = create(2, i, 1);
					levelFive[7][i] = create(7, i, 1);
				}
				for (int i = 4; i <6; i++) {
					levelFive[3][i] = create(3, i, 1);
					levelFive[6][i] = create(6, i, 1);
				}

				levels.add(levelFive);
				
		//---------------------LEVEL SIX-------------------
				
				Block[][] levelSix = new Block[10][10];
				//add blocks... this will be a diamond patter
				for (int i = 1; i < 9; i++) {
					levelSix[0][i] = create(0, i, 1);
					levelSix[9][i] = create(9, i, 1);
					levelSix[i][0] = create(i, 0, 1);
					levelSix[i][9] = create(i, 9, 1);
					for(int j = 1; j < 8; j++) {
						double rand = Math.random();
						if (rand <= 0.5) {
							levelSix[j][i] = create(j, i, 2);
						}
					}
				}
				levelSix[0][0] = create(0, 0, 2);
				levelSix[0][9] = create(0, 9, 2);
				levelSix[9][0] = create(9, 0, 2);
				levelSix[9][9] = create(9, 9, 2);

				levels.add(levelSix);		
		
			//---------------------LEVEL SEVEN-------------------
				
				Block[][] levelSeven = new Block[10][10];
				//add blocks... this will be a diamond patter
				for (int i = 0; i < 10; i++) {
					levelSeven[0][i] = create(0, i, 1);
					levelSeven[1][i] = create(1, i, 1);
					levelSeven[2][i] = create(2, i, 1);
					levelSeven[3][i] = create(3, i, 1);
					levelSeven[4][i] = create(4, i, 1);
					levelSeven[9][i] = create(9, i, 1);
					levelSeven[8][i] = create(8, i, 1);
					levelSeven[7][i] = create(7, i, 1);
					levelSeven[6][i] = create(6, i, 1);
					levelSeven[5][i] = create(5, i, 1);
				}

				levelSeven[0][7] = null;
				levelSeven[1][7] = null;
				levelSeven[1][6] = null;
				levelSeven[2][6] = null;
				levelSeven[2][5] = null;
				levelSeven[3][5] = null;
				levelSeven[3][4] = null;
				levelSeven[4][4] = null;
				levelSeven[4][3] = null;
				levelSeven[5][3] = null;
				levelSeven[5][2] = null;
				levelSeven[6][2] = null;
				levelSeven[6][3] = null;
				levelSeven[7][3] = null;
				levelSeven[7][4] = null;
				levelSeven[8][4] = null;
				levelSeven[8][5] = null;
				levelSeven[9][5] = create(9, 5, 2);
				levelSeven[9][4] = create(9, 4, 2);
				
				levels.add(levelSeven);	
				
				//---------------------LEVEL EIGHT-------------------
				
				Block[][] levelEight = new Block[10][10];
				//add blocks... this will be a diamond patter
				for (int i = 0; i < 10; i++) {
					levelEight[0][i] = create(0, i, 2);
					levelEight[9][i] = create(9, i, 2);
					levelEight[i][0] = create(i, 0, 2);
					levelEight[i][9] = create(i, 9, 2);
				}
				for (int i = 1; i < 9; i++) {
					levelEight[1][i] = create(1, i, 1);
					levelEight[8][i] = create(8, i, 1);
					levelEight[i][1] = create(i, 1, 1);
					levelEight[i][8] = create(i, 8, 1);
				}
				for (int i = 2; i < 8; i++) {
					levelEight[2][i] = create(2, i, 1);
					levelEight[7][i] = create(7, i, 1);
					levelEight[i][2] = create(i, 2, 1);
					levelEight[i][7] = create(i, 7, 1);
				}
				
				levelEight[4][4] = create(4, 4, 2);
				levelEight[4][5] = create(4, 5, 2);
				levelEight[5][4] = create(5, 4, 2);
				levelEight[5][5] = create(5, 5, 2);
					
				levels.add(levelEight);	
        }
	
	public Block[][] getLevel(int i) {
		return levels.get(i);
	}
	
	public int howManyLevels() {
		return levels.size();
	}

}
