// Derek Tang
// pd # 09
// HW## 34?
// 2013-04-22

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayBST {
  
  private static Comparable[] _tree;
  private static int _size;
  
  //constructor w/ default _tree size of 10
  public ArrayBST() { 
    _tree = new Comparable[10];
    _size = 10;
  }
  
  // Return String representation of _tree
  // as level-order traversal
  // eg
  // "1 null 2 null null null 6"
  public String toString() { 
    String yo = "";
    for(int i = 0; i < _size; i++){
      yo += _tree[i] + " ";
    }
    return yo;
  }
  
  
  // add a value to the tree by "walking it down"
  // to the proper location and inserting it
  public void add( Comparable addVal ) { 
    if (_tree[0] == null)
      _tree[0] = addVal;
    else{
      int i = 1;
      while(addVal != null){
        if(i > _size)
          resize();
        if(_tree[i - 1] == null){
          _tree[i - 1] = addVal;
          addVal = null;
          break;
        }
        if(_tree[i - 1].compareTo(addVal) > 0)
          i *= 2;
        else 
          i = 2 * i + 1;
      }
    }
  }
  
  
  // resize() will be called when the value to be added lies
  // beyond the range of the underlying array
  // ( a la resize() for SuperArray/ArrayList... )
  public void resize() { 
    int newsize;
    if(_size == 10)
      newsize = 15;
    else
      newsize = _size * 2 + 1;
    Comparable[] tmp = new Comparable[newsize];
    for(int i = 0; i < _size; i++){
      tmp[i] = _tree[i];
    }
    _tree = tmp;
    _size = newsize;
  }
  
  
  //return the number of meaningful slots in the underlying array
  // ( number of nodes in the tree )
  public int size() {
    if(isEmpty())
      return 0;
    int counter = 0;
    for(int i = 0; i < _size; i++){
      if(_tree[i] != null)
        counter++;
    }
    return counter;
  }
  
  
  // return whether or not the tree is empty
  public boolean isEmpty() { 
    return _tree[0] == null;
  }
//*****************  PHASE II *******************
  
  //N: a helper fxn for each traversal could be useful...
  
  //return a String representation of a pre-order traversal
  public String preOrdTrav() { //W L R
    return preOrdHelp(0);
  }
  
  public static String preOrdHelp(int ind){
    String yo = "";
    yo += _tree[ind] + " ";
    if(2 * ind + 2 <= _size * 2 - 1){
      if(2 * ind + 1 <= _size && _tree[2 * ind + 1] != null)
        yo += preOrdHelp(2 * ind + 1);
      if(2 * ind + 2 <= _size && _tree[2 * ind + 2] != null)
        yo += preOrdHelp(2 * ind + 2);
    }
    return yo;
  }
  
  //return a String representation of an in-order traversal
  public String inOrdTrav() { 
    return inOrdHelp(0);
  }
  
  public static String inOrdHelp(int ind){
    String yo = "";
    if(2 * ind + 2 <= _size * 2 - 1){
      if(2 * ind + 1 <= _size && _tree[2 * ind + 1] != null)
        yo += inOrdHelp(2 * ind + 1);
      yo += _tree[ind] + " ";
      if(2 * ind + 2 <= _size && _tree[2 * ind + 2] != null)
        yo += inOrdHelp(2 * ind + 2) ;
    }
    
    return yo;
  }
  
  //return a String representation of a post-order traversal
  public String postOrdTrav() { 
    return postOrdHelp(0);
  }
  
  public static String postOrdHelp(int ind){
    String yo = "";
    if(2 * ind + 2 <= _size * 2 - 1){
      if(2 * ind + 1 <= _size && _tree[2 * ind + 1] != null)
        yo += postOrdHelp(2 * ind + 1);
      if(2 * ind + 2 <= _size && _tree[2 * ind + 2] != null)
        yo += postOrdHelp(2 * ind + 2);
      yo += _tree[ind] + " ";
    }
    //System.out.println(_tree[ind]);
    
    return yo;
  }
  
  // return the index in _tree array where query q is found
  public int find( Comparable q ) { 
    int i = 0;
    while(i < _size || _tree[i].compareTo(q) != 0 ||  _tree[i] != null){
      if(_tree[i] == null)
        break;
      if(_tree[i].compareTo(q) > 0)
        i = 2 * i + 1;
      else if(_tree[i].compareTo(q) < 0)
        i = 2 * i + 2;
      else
        break;
    }
    if(i > _size || _tree[i] == null)
      return -1;
    else
      return i;
  }
  
  //***********************************************
  
  //***************** PHASE III *******************
  
  //HINT (again): how could a helper for height, numLeaves be useful?
  
  //return height of tree (length of longest leaf-to-root path)
  public int height() { 
    if(isEmpty()) return 0;
    else if(_size == 10){
      if(_tree[1] == null && _tree[2] == null) return 1;
      else if(_tree[3] == null && _tree[4] == null && _tree[5] == null && _tree[6] == null) return 2;
      else return 3;
    }
    else{
      for(int n = 0; ; n++){
        if (Math.pow(2,n) - 1 > _size) return n - 1;
      }
    }
  }
  
  
  //return number of leaves in tree
  public int numLeaves() { 
    return numHelper(0);
  }
  
  public int numHelper(int i){
    if (i >= _size || _tree[i] == null) return 0;
    else if (2 * i + 2 > _size && _tree != null) return 1;
    else if (_tree[2 * i + 1] == null && _tree[2 * i + 2] == null) return 1;
    else
      return numHelper(2 * i + 1) + numHelper(2*i + 2);
  }
  
  //return index in _tree array where least node is stored
    public int indexOfMin() { return indexOfSubtreeMin(0); }

    //return index of min value in subtree w root subRoot
    public static int indexOfSubtreeMin( int subRoot ) { 
 //only necessary to look down LEFT subtree...
 int minPos = subRoot;
 for( int i = subRoot; i < _tree.length; ) {
     if ( _tree[i] == null )
  break;
     else {
  minPos = i;
  i = 2*i+1;
     }
 }
 return minPos;
    }

    //return index in _tree array where greatest node is stored
    public int indexOfMax() { return indexOfSubtreeMax(0); }

    //return index of min value in subtree w root subRoot
    public static int indexOfSubtreeMax( int subRoot ) { 
 //only necessary to look down RIGHT subtree...
 int maxPos = subRoot;
 for( int i = subRoot; i < _tree.length; ) {
     if ( _tree[i] == null )
  break;
     else {
  maxPos = i;
  i = 2*i+2;
     }
 }
 return maxPos;
    }
  
  
  //***********************************************
  
//***************** PHASE IV ********************
  
  public boolean isALeaf(int index){
    if (2 * index + 2 < _size && (_tree[2 * index + 1] != null || _tree[2 * index + 2] != null))
      return false;
    else
      return true;
  }
  
  public static void swap(int ind1, int ind2){
    Comparable tmp = _tree[ind1];
    _tree[ind1] = _tree[ind2];
    _tree[ind2] = tmp;
  }
  
  
  public boolean remove( Comparable q ) { 
    if(find(q) == -1)
      return false;
      return removelper(find(q));
    //return false; //placeholder return to pass compiler
  }
//right most of left subtree or left most of right subtree
  public boolean removelper(int i){
    if(isALeaf(i)){
      _tree[i] = null;
    }
    if(2 * i + 2 < _size && _tree[2 * i + 1] != null){
      swap(i, indexOfSubtreeMax(2 * i + 1));
      removelper(indexOfSubtreeMax(2 * i + 1));
    }
    else if(2 * i + 2 < _size && _tree[2 * i + 2] != null){
      swap(i, indexOfSubtreeMin(2 * i + 2));
      removelper(indexOfSubtreeMin(2 * i + 2));
    }
    return true;
  }
            
      //***********************************************
  
  // ************* MyIterator implementation ***************
    // *******************************************************
  private class ArrBSTIterator implements Iterator{

 //Remember this guy?
 private boolean _okToRemove;

 //Q: wha wha whaaAAHHH??? . . .
 private Queue desmond;

 //Equiv to dummy node's cargo
 private Comparable _value;

 //We need this for 1 reason. In what situation would it be
 //reeeeally nice to have a pointer/alias to this tree?
        private ArrayBST _kraken;

 public ArrBSTIterator( ArrayBST bst ) { 
_okToRemove = false;
desmond = new LLQueue<Comparable>();
_kraken = bst;
fillErUp(0);
System.out.println(desmond);
_value = null;
}

    private void fillErUp( int pos ) { 
     //Q: What must be done here?
     // ... flags set?
     //     collections filled?
     //     pointers assigned?
        if(2 * pos + 2 < _size){
 fillErUp( 2 * pos + 1 );
 if(_tree[pos] != null) 
   desmond.enqueue(_tree[pos]);
 fillErUp(2 * pos + 2 );
      }
 }

 public boolean hasNext() { 
     //H: take advantage of your auxiliary structure...
return !desmond.isEmpty();
 }

 public Comparable next() { 
     //Q: What must be done here?
     // ... flags flipped?
     //     collection actions?
     //     pointers assigned?
if(desmond.isEmpty())
  throw new NoSuchElementException();
_okToRemove = true;
return _value = (Comparable)desmond.dequeue();
 }
 
 public void remove() { 
     //H: Use yer magicks
if(!_okToRemove)
throw new IllegalStateException("must call next() before");
if(_kraken != null){
  _okToRemove = false;
  _kraken.remove(_value);
  _value = (Comparable)desmond.front();
}
 }//end class ArrBSTIterator
  }
    public Iterator iterator(){ 
 //H: think: SIMPLE       ...THIS should be e-z...
      return new ArrBSTIterator(this);
  }
    // *******************************************************
    // *******************************************************
      
      public static void main( String[] args ) {
        ArrayBST LutherBurger = new ArrayBST();
        
        System.out.println("Is LutherBurger empty?: " + LutherBurger.isEmpty() + "\n");
        System.out.println("LutherBurger: \n" + LutherBurger + "\n");
        LutherBurger.add(157);
        System.out.println("LutherBurger after adding 157: \n" + LutherBurger + "\n");
        LutherBurger.add(777);
        System.out.println("LutherBurger after adding 777: \n" + LutherBurger + "\n");
        LutherBurger.add(2);
        System.out.println("LutherBurger after adding 2: \n" + LutherBurger + "\n");
        LutherBurger.add(621);
        System.out.println("LutherBurger after adding 621: \n" + LutherBurger + "\n");
        LutherBurger.add(11);
        System.out.println("LutherBurger after adding 11: \n" + LutherBurger + "\n");
        LutherBurger.add(58);
        System.out.println("LutherBurger after adding 58: \n" + LutherBurger + "\n");
        LutherBurger.add(81);
        System.out.println("LutherBurger after adding 81: \n" + LutherBurger + "\n");
        LutherBurger.add(300);
        System.out.println("LutherBurger after adding 300: \n" + LutherBurger + "\n");
        LutherBurger.add(76);
        System.out.println("LutherBurger after adding 76: \n" + LutherBurger + "\n");
        LutherBurger.add(966);
        System.out.println("LutherBurger after adding 58: \n" + LutherBurger + "\n");
        
        System.out.println("Number of Nodes on the tree: " + LutherBurger.size());
        System.out.println("Is LutherBurger empty?: " + LutherBurger.isEmpty());
        System.out.println("Pre- Order Traversal of LutherBurger: " + LutherBurger.preOrdTrav());
        System.out.println("In- Order Traversal of LutherBurger: " + LutherBurger.inOrdTrav());
        System.out.println("Post- Order Traversal of LutherBurger: " + LutherBurger.postOrdTrav());
        System.out.println("Index of '58' in LutherBurger: " + LutherBurger.find(58));
        System.out.println("Index of '81' in LutherBurger: " + LutherBurger.find(81));
        System.out.println("Index of '555555' in LutherBurger: " + LutherBurger.find(555555));
        
        System.out.println("Height(Thickness/How much meat) of LutherBurger: " + LutherBurger.height());
        System.out.println("Number of leaves on LutherBurger: " + LutherBurger.numLeaves());
        System.out.println("Index of the Minimum value in LutherBurger: " + LutherBurger.indexOfMin());
        System.out.println("Index of the Maximum value in LutherBurger: " + LutherBurger.indexOfMax() + "\n");
        
        System.out.println("Is index 6 a leaf?: " + LutherBurger.isALeaf(6));
        System.out.println("Removing '157' from LutherBurger: " + LutherBurger.remove(157));
        System.out.println("LutherBurger after remvoing 157: \n" + LutherBurger + "\n");
        System.out.println("In- Order Traversal of LutherBurger: " + LutherBurger.inOrdTrav());
        
        Iterator<Comparable> itr = LutherBurger.iterator();
        System.out.println(itr.hasNext()); 
        System.out.println(itr.next());
        System.out.println(itr.next());
        itr.remove();
        //itr.remove();
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        //System.out.println(itr.next());
    }
        
//tester functions...
        
      
      
    }//end class ArrayBST