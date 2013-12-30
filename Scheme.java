//TEAM BAGELS
//Team Member Nisreen Abdelaziz, Team Member Shaan Sheikh, Team Member Derek Tang
//APCS2 pd #9
//HW14
//2013-03-04

public class Scheme{
  
 /*
  ALGORITHM OUTLINE:
  My algorithm takes the non-")" strings and puts them into a stack. It places an operator, stored in a second stack, in between integers. Whenever it runs through the string of inputs and encounters a close parenthesis, it does all the operations until it reaches an open paren. When it runs through the entire array of inputs, it completes all of the operations. 
  
  STACK OF CHOICE: LLStack by Derek b/c it feels more stacky than an arraylist. ALStack is kind of the dumbing down of an arraylist when LLStacks have nodes that can only access the node before it.
*/
  public static boolean isNumber(String s){
    try{ Integer.parseInt(s);
      return true;
    }
    catch(NumberFormatException e)
    {return false;}
  }
  
  public static boolean isOp(String s){
    if(s == "+" || s == "-" || s == "/" || s == "+")
      return true;
    else
      return false;
  }
  
  public static String operation(String a, String op, String b){
    int c = Integer.parseInt(b);
    //System.out.println(c);
    int d = Integer.parseInt(a);
     //System.out.println(d);
    int e = 0;
    if(op.compareTo("+") == 0)
      e = c + d;
    else if(op.compareTo("-") == 0)
      e = c - d;
    else if(op.compareTo("*") == 0)
      e = c * d;
    else
      e = c / d;
    
    return e + "";
  }
  
  public static void main(String[] args){
    Stack<String> _stack = new ALStack<String>();
    Stack<String> op = new LLStack<String>();
    
    String input = "( + 4 ( * 2 5 3 ) 25 )";
    
    String [] line = input.split( "\\s+" );
    for(int i = 0; i < line.length; i++){
      if (line[i].compareTo("(") == 0){
        _stack.push(line[i]);
        //System.out.println(_stack.toString() + "in (");
      }
      else if (isNumber(line[i])){
        _stack.push(line[i]);
        _stack.push(op.peek());
        //System.out.println(_stack.toString() + "in num");
      }
      else if (line[i].compareTo(")") != 0){
        op.push(line[i]);
        //System.out.println(op + "in op");
      }
      else{
        //System.out.println("boo");
        //System.out.println(_stack.toString() + "in )");
        _stack.pop();
        String prev = _stack.pop();
        //System.out.println(prev);
        while(_stack.peek().compareTo("(") != 0 ){
          _stack.push(prev);
          //System.out.println(_stack.toString());
          _stack.push(operation(_stack.pop(), _stack.pop(), _stack.pop()));
          //System.out.println(_stack.toString());
          prev = _stack.pop();
          //System.out.println(_stack.toString());
        }
          _stack.pop();
        _stack.push(prev);
        
        if (i != line.length - 1){
            op.pop();
        _stack.push(op.peek());
        }
      }
    }
    System.out.println(_stack.toString());
  }
}