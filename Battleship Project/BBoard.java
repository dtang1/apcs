//Team Mouse & Tail
//Jian Huang and Derek Tang
//Period 8/9
//Battleship Project
//1-22-2013

public class BBoard {

    //constants for ship types
    public static final int DESTROYER = 0;
    public static final int SUBMARINE = 1;
    public static final int CRUISER = 2;
    public static final int BATTLESHIP = 3;
    public static final int CARRIER = 4;
    public static final int EMPTY = -1;
    public static final int HIT = -2;
    public static final int MISS = -3;

    //constants for ship lengths
    public static final int DESTROYER_LENGTH = 2;
    public static final int SUBMARINE_LENGTH = 3;
    public static final int CRUISER_LENGTH = 3;
    public static final int BATTLESHIP_LENGTH = 4;
    public static final int CARRIER_LENGTH = 5;

    //constants for orientation
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    //constants for board size, visual effect
    public static final int SIZE = 10;
    public static final char SPACER = '~';

    //2D array to represent ship grid
    private int[][] _grid;

    //default constructor
    //postcondition: initializes empty ship grid
    public BBoard() {
	_grid = new int[SIZE][SIZE];
        for(int row=0 ; row < SIZE ; row++ )
            for(int column=0 ; column < SIZE ; column++ )
                _grid[row][column]=-1;
    }
    public boolean addShip( int r, int c, int orientation, int type ) {
	if (type < DESTROYER || type > CARRIER){
	    return false;
	}
	if(r<0 || c < 0 || r >= SIZE || c >= SIZE){
	    return false;
	}
	else if(type == DESTROYER)
        return addShipHelper(r,c,orientation,DESTROYER,DESTROYER_LENGTH);
	else if(type == SUBMARINE)
	    return addShipHelper(r,c,orientation,SUBMARINE,SUBMARINE_LENGTH);
        else if(type == CRUISER)
	    return addShipHelper(r,c,orientation,CRUISER,CRUISER_LENGTH);
	else if(type == BATTLESHIP)
	    return addShipHelper(r,c,orientation,BATTLESHIP,BATTLESHIP_LENGTH);
	else
	    return addShipHelper(r,c,orientation,CARRIER,CARRIER_LENGTH);
    }
    
    public boolean addShipHelper ( int r, int c, int o, int type, int length ) {
	if (o == HORIZONTAL){
	    if (c + length > _grid[r].length){
		System.out.println("ERROR! Placement Over Edge");
		return false;
	    }
	else{
	    for (int i = 0; i < length; i++){
		if (_grid[r][c + i] != EMPTY){
		    System.out.println("ERROR! Placement Over Another Ship: "+ type);
		    return false;
		}}}}     
	if (o == VERTICAL){
	    if (r + length > _grid.length){
		System.out.println("ERROR! Placement Over Edge");
		return false;
	    }
	    else{
		for (int i = 0; i < length; i++){
		  if (_grid[r+i][c] != EMPTY){
		      System.out.println("ERROR! Placement Over Another Ship: "+ type);
		      return false;
		  }}}}
        for (int i = 0; i < length; i++){
	    if (o == HORIZONTAL)
		_grid[r][c + i] = type;
	    else
		_grid[r + i][c] = type;
        }
	return true;
	
    }
    
    public String toString() {
	String s = "";
	int num = 0;
	char letter = 'A';
	
	for ( int r = 0; r < _grid.length + 1; r++) {
	    for ( int c = 0; c < _grid.length + 1; c++) {
		if ( r == 0 && c != 0 ) {
		    s+= letter + " ";
		    letter++;
  }
		else if ( c == 0 ) {
		    if ( r != 0 ) {
			s+= num + " ";
			num++;
		    }            
		    else
			s+= "  ";
		}        
		else if ( _grid[r-1][c-1] == EMPTY )
		    s+= SPACER + " ";
		else if ( _grid[r-1][c-1] == DESTROYER )
		    s+= 'D' + " ";
		else if ( _grid[r-1][c-1] == SUBMARINE )
		    s+= 'S' + " ";
		else if ( _grid[r-1][c-1] == CRUISER )
		    s+= 'C' + " ";
		else if ( _grid[r-1][c-1] == BATTLESHIP )
		    s+= 'B' + " ";
		else if ( _grid[r-1][c-1] == CARRIER )
		    s+= 'A' + " ";
		else if ( _grid[r-1][c-1] == HIT )
		    s+= 'X' + " ";
		else if ( _grid[r-1][c-1] == MISS )
		    s+= 'M' + " ";
	    }
	    s+= "\n";
	}
	return s;
    }
    //think of hiddenBoard as something like an accessor method --
    // it returns a String representing the board hidden from a player
    public String hiddenBoard() {
	String s = "";
	int num = 0;
	char letter = 'A';
	
	for ( int r = 0; r < _grid.length + 1; r++) {
	    for ( int c = 0; c < _grid.length + 1; c++) {
		if ( r == 0 && c != 0 ) {
		    s+= letter + " ";
		    letter++;
		}
		else if ( c == 0 ) {
		    if ( r != 0 ) {
			s+= num + " ";
			num++;
		    }      
		    else
			s+= "  ";
		}
		else if ( _grid[r-1][c-1] == HIT )
		    s+= 'X' + " ";
		else if ( _grid[r-1][c-1] == MISS )
		    s+= 'M' + " ";
		else{s+= SPACER + " ";}
	    }
	    s+= "\n";
	}
	return s;
    }
    
    //mark() updates a cell in the grid to reflect a hit or miss
    public int mark( int r, int c ) {
	if (r<10 && r>=0 && c<10 && c>=0){
	    if(_grid[r][c] == EMPTY){	
		_grid[r][c] = MISS;
		return _grid[r][c];
	    }
	    else if(_grid[r][c]==MISS){
		return -99;}//for the low chance of repeating a coordinate...
	    else if(_grid[r][c]==HIT){
		return -99;}//for the low chance of repeating a coordinate...
	    else{int temp =_grid[r][c];
		_grid[r][c]= HIT;
		return temp;}//used for recording ship damage
	}
	else{
	    System.out.println("Invalid coordinates for attack!");
	    return -99;//allows for re-entering of coordinates
	}
    }
    
    
    //main fxn for testing
    public static void main( String[] args ) {
	BBoard b = new BBoard();
	System.out.println("\nGrid before ship placement:");
	System.out.println(b);
	b.addShip( 0, 0, HORIZONTAL, DESTROYER );
	b.addShip( 2, 7, HORIZONTAL, CRUISER );
	b.addShip( 3, 4, VERTICAL, SUBMARINE );
	b.addShip( 6, 2, VERTICAL, BATTLESHIP );
	System.out.println(b);
	b.addShip( 1, 9, VERTICAL, CARRIER );
	b.mark(1,1);
	b.mark(0,1);
	System.out.println("Grid after ship placement:");
	System.out.println( b );
	System.out.println(b.hiddenBoard());
    }
    
}//end class BBoard
