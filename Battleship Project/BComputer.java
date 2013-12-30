//Team Mouse & Tail
//Jian Huang and Derek Tang
//Period 8/9
//Battleship Project
//1-22-2013

public class BComputer extends BPlayer {

    private int[] _ships;
  
    public BComputer() {
	super();
    }
    
    public void setBoard( BBoard mine ) {
	System.out.println("adding Destroyer..." );
	addShip( mine, BBoard.DESTROYER, "Destroyer" );
	System.out.println("adding Cruiser..." );
	addShip( mine, BBoard.CRUISER, "Cruiser" );
	System.out.println("adding Submarine..." );
	addShip( mine, BBoard.SUBMARINE, "Submarine" );
	System.out.println("adding Battleship..." );
	addShip( mine, BBoard.BATTLESHIP, "Battleship" );
	System.out.println("adding Carrier..." );
	addShip( mine, BBoard.CARRIER, "Aircraft carrier" );
    }
 
    public void addShip( BBoard mine, int type, String t ) {
	//randomly chooses coordinates and orientation
	int x = ((int)(Math.random() * 10));
	int y = ((int)(Math.random() * 10));
	int o = ((int)(Math.random() * 2));
	System.out.print("Enter coordinates for "+t+":");
	if(!mine.addShip(x,y,o,type)){
	    addShip(mine, type, "");}
	System.out.println();
	System.out.println(mine);
    }

    public void makeMove( BBoard other, BPlayer opponent ) {
	//randomly chooses coordinates and orientation
	int row = ((int)(Math.random() * 10));
	int col = ((int)(Math.random() * 10));
	int result = other.mark(row,col);
	if(result == -99){//checks for the re-entering of a previous coordinate
	    makeMove(other, opponent);}
	if(result > -1){
	    opponent.recordMark(result);
	}
    }
}//end class Computer
	    
