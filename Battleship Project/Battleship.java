//Team Mouse & Tail
//Jian Huang and Derek Tang
//Period 8/9
//Battleship Project
//1-22-2013

import cs1.Keyboard;

public class Battleship {

    private BBoard player1Board;
    private BBoard player2Board;
    
    private BPlayer player1;
    private BPlayer player2;

    public Battleship() {	
	player1Board = new BBoard();
	player2Board = new BBoard();
	player1 = new BHuman();
	player2 = new BHuman();
    }
    public Battleship(String players) {	
	player1Board = new BBoard();
	player2Board = new BBoard();
	if(players.equals("NoPlayers")||players.equals("Player2")){
	    player1 = new BComputer();
	    System.out.println("Player1 is a computer.");}
	else
	    player1 = new BHuman();
	if(players.equals("NoPlayers")||players.equals("Player1")){
	    player2 = new BComputer();
	    System.out.println("Player2 is a computer.");}
	else
	    player2 = new BHuman();
    }
    public void setUp() {
	System.out.println("~~~~~~ B A T T L E S H I P ~~~~~~");
	System.out.println("Brought to you by Team Mouse & Tail\n");	
	System.out.println("Prepare your ships for battle!\n\n");

	player1.setBoard( player1Board );
	System.out.println("Your final arrangement:");
	System.out.println( player1Board );

       	player2.setBoard( player2Board );
	System.out.println("Your final arrangement:");
	System.out.println( player2Board );

    }

    public void makeMove( int p ) {	
	if(p == 1){
	    player1.makeMove(player2Board,player2);}
	if(p == 2){
	    player2.makeMove(player1Board,player1);}
    }

    public boolean isOver() {
	return (player1.hasLost()||player2.hasLost());
    }

    public int winner() {
	if ( player1.hasLost() )
	    return 2;
	else
	    return 1;
    }
    
    public void showBoards() {
	System.out.println("Player 1's current board:\n" + player1Board.hiddenBoard());
	System.out.println("Player 2's current board:\n" + player2Board.hiddenBoard());
    }
    
    //for testing
    public void printShips() {
	player1.printShips();
	player2.printShips();
    }

    public static void main( String[] args ) {
	String temp ="";
	System.out.println("Enter the type of Players (Case-sensitive)");
	System.out.println("Player1 - to go first against a computer");
	System.out.println("Player2 - to go second against a computer");
	System.out.println("NoPlayers - to watch tons of codes fly off the screen and an AI brawl!");
	System.out.println("If anything else is inputed, a normal player vs player game will start.");
	temp = Keyboard.readString();
	
	Battleship game = new Battleship(temp);

	game.setUp();

	int moves = 0;

	while ( !game.isOver() && moves < 100) {

	    game.showBoards();

	    game.makeMove(1);
	    game.makeMove(2);

	    moves++;

	}
	game.showBoards();
	System.out.println("Player " + game.winner() + " Won in "+moves+" moves!");
    }

}//end class Battleship
