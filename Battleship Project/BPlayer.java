//Team Mouse & Tail
//Jian Huang and Derek Tang
//Period 8/9
//Battleship Project
//1-22-2013

public abstract class BPlayer {

    //instance vars
    private int[] _ships; //array to keep track of ships

    public BPlayer() {
	_ships = new int[5];
	_ships[BBoard.DESTROYER]=BBoard.DESTROYER_LENGTH;
	_ships[BBoard.SUBMARINE]=BBoard.SUBMARINE_LENGTH;
	_ships[BBoard.CRUISER]=BBoard.CRUISER_LENGTH;
	_ships[BBoard.BATTLESHIP]=BBoard.BATTLESHIP_LENGTH;
	_ships[BBoard.CARRIER]=BBoard.CARRIER_LENGTH;
	//default constructor that sets the health of corresponding ship type
    }

     public boolean hasLost() {
   	boolean dead = true;
	for(int max =0;max<_ships.length;max++){
	    if(_ships[max] != 0)
		{dead = false;//checks if any ships have health left
		    break;}}
		    return dead;
     }

    public boolean recordMark( int type ) {
	if ( type >= 0 && _ships[type] > 0 ) {
	    _ships[type]--;
	    if ( _ships[type] == 0 ) {
		//Expanded code to print the name of the ship
		String shipname = "";
		if(type == 0){shipname = "Destroyer";}
		else if(type == 1){shipname = "Submarine";}
		else if(type == 2){shipname = "Cruiser";}
		else if(type == 3){shipname = "Battleship";}
		else if(type == 4){shipname = "Carrier";}
		System.out.println("You sunk my: " + shipname);
		return true;
	    }
	}
	return false;
    }
    // ABSTRACT METHODS -- must be implemented by subclasses
    public abstract void addShip( BBoard mine, int type, String t );
    public abstract void makeMove( BBoard other, BPlayer opponent );
    public abstract void setBoard( BBoard mine );
    
    //print fxn for testing
    public void printShips() {
	for( int i = 0; i < _ships.length; i++ )
	    System.out.println( i + ": " + _ships[i]);
    }
	
    
}//end class BPlayer
