//Team Mouse & Tail
//Jian Huang and Derek Tang
//Period 8/9
//Battleship Project
//1-22-2013
import cs1.Keyboard;

public class BHuman extends BPlayer {
    private int[] _ships;
    public BHuman() {
        super();
    }

    public void setBoard( BBoard mine ) { 
	System.out.println("Input coordinates with column letter + row. Ex: A4");
	addShip(mine,0,"Destroyer");
        addShip(mine,1,"Submarine");
        addShip(mine,2,"Cruiser");
        addShip(mine,3,"Battleship");
        addShip(mine,4,"Carrier");
    }

    public void addShip( BBoard mine, int type, String t ) {
	int y = -1; int x = -1; int o = -1;
	//variables are intiated outside to allow for a special intial value
	while(y == -1 || x == -1){
	    try{
		System.out.print("Enter coordinates for "+t+":");
		String temp = Keyboard.readString();
		//the following pieces of code uses the properties of char. all char have a corresponding value (alphabatized for letters) so you can compare the char to find out if it belongs.
		//String method charAt(int) found in Java API: used as a assesor method to return a single char of a string
		if(((temp.charAt(0)>='a'&&temp.charAt(0)<='j')||(temp.charAt(0)>='A'&&temp.charAt(0)<='J'))&&temp.charAt(1)>='0'&&temp.charAt(1)<='9'){
		    //double if statements here for char's uppercase and lowercase. Once again, the corresponding value of the char is substracted from first char to find its position.
		    if(temp.charAt(0)>='a'&&temp.charAt(0)<='j'){
			y = temp.charAt(0)-'a';}
		    else if(temp.charAt(0)>='A'&&temp.charAt(0)<='J'){
			y= temp.charAt(0)-'A';}
		    x= Integer.parseInt(temp.substring(1,2));
		}
		else{int fail = 5 / 0;}//forces an error that can be catched
	    }	
	    catch(Exception e){
		System.out.println("\tError: Enter in format (letter)(#). Example: B5");
	    }
	}
	while(o == -1){//repeats the loop until h or v is entered
	    try{
		System.out.print("Enter h(HORIZONTAL) or v(VERTICAL) for "+t+":");
		char orientation = Keyboard.readString().charAt(0);
		//uses readString to avoid repeated errors with a string
		if(orientation=='h'||orientation=='H'){o = 0;}
		else if(orientation=='v'||orientation=='V'){o = 1;}
		else{ int fail = 5 / 0;}//forces an error that can be catched
	    }
	    catch(Exception e){
		System.out.println("\tError: Incorrect input. Enter h or v only.");
	    }
	}
	if(mine.addShip(x,y,o,type)==false){//original run
	    addShip(mine, type, t);}//recursion to deal with errors
	System.out.println(mine);
    }
	
    
    public void makeMove( BBoard other, BPlayer opponent ) {
	//copy of addShip exception loop
	int x= -1; int y= -1;
	while(y == -1 || x == -1){
	    try{
		System.out.print("Enter coordinates to attack:");
		String temp = Keyboard.readString();
		if(((temp.charAt(0)>='a'&&temp.charAt(0)<='j')||(temp.charAt(0)>='A'&&temp.charAt(0)<='J'))&&temp.charAt(1)>='0'&&temp.charAt(1)<='9'){
		    if(temp.charAt(0)>='a'&&temp.charAt(0)<='j'){
			y = temp.charAt(0)-'a';}
		    else if(temp.charAt(0)>='A'&&temp.charAt(0)<='J'){
			y= temp.charAt(0)-'A';}
		    x= Integer.parseInt(temp.substring(1,2));}
		else{int fail = 5 / 0;}//forces an error that can be catched
	    }
	    catch(Exception e){
		System.out.println("\tError: Enter in format (letter)(#). Example: B5");
	    }
	}
	int result = other.mark(x,y);
	//splits the read String with first character becoming an int and the second becoming a char
	//method .charAt(int) found in Java API
	if(result == -99){//deals with repeated coordinates
	    System.out.println("SIR. You already entered that coordinate or it is Out-of-Bound. Try Again");
	    makeMove(other, opponent);
	}
	if(result > -1){
	    opponent.recordMark(result);}
    }
    
}//end class Human
    
