/* Team Bagels
 Brains: Nisreen Abdelaziz, Shaan Sheikh, Derek Tang 
 
 RUNTIME ANALYSIS:
 Best:  O(nlogn), when already sorted, b/c even if the array is sorted, the sort will still need to break the array down into smaller parts like a merge sort.
 Worst: O(n^2), when the pivot is always the lowest object in the array, b/c the code will have to run through the array to find the pivot and swap it with the front most object. Then this is reapeated again and again.
        For example: The worst case for the array {1,2,3,4,5,6,7} would be the array {2,7,4,1,3,5,6}
 Avg:   O(nlogn), when the objects appear randomly, b/c the code will have to break the array into smaller parts to sort it, kinda like merge sort.
 
 PIVOT CHOICE:
 The middle of the array is the smartest way to pick a pivot, because...
 The Queen Across the Sea says so.
 We dont think there is a "smartest way" to pick a pivot. 
 
  */


//class QuickSort
//implements QuickSort algorithm to sort an array of Comparables
public class QuickSort {
  
  // ~~~~~~~~~~~~~~~~ HELPER FXNS ~~~~~~~~~~~~~~~~~~
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  public static void swap( int x, int y, Comparable[] o ) {
    Comparable tmp = o[x];
    o[x] = o[y];
    o[y] = tmp;
  }
  
  public static void printArr( Object[] a ) {
    for ( Object o : a )
      System.out.print( o + " " );
    System.out.println();
  }
  
  public static void shuffle( Comparable[] d ) {
    Comparable tmp;
    int swapPos;
    for( int i = 0; i < d.length; i++ ) {
      tmp = d[i];
      swapPos = i + (int)( (d.length - i) * Math.random() );
      swap( i, swapPos, d );
    }
  }
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  
  
  public static void sort( Comparable[] d ) { 
    sortHelp( 0, d.length-1, d );
  }
  
  public static void sortHelp( int lo, int hi, Comparable[] d ) { 
    
    if ( lo >= hi )
      return;
    
    int tmpLo = lo;
    int tmpHi = hi;
    Comparable pivot = d[(lo + hi) / 2];
    
    System.out.print("\n*** sortNHelp called w/ ");
    System.out.print("tmpLo:" + tmpLo);
    System.out.print(", tmpHi:" + tmpHi);
    System.out.print(", pivot:" + pivot + " ***\n");
    
    while( tmpLo < tmpHi ) {
      System.out.println("in while...");
      while( d[tmpLo].compareTo(pivot) < 0 ) {
        System.out.println("tmpLo: " + tmpLo);
        tmpLo++;
      }
      while( d[tmpHi].compareTo(pivot) > 0) {
        System.out.println("tmpHi: " + tmpHi);
        tmpHi--;
      }
      swap( tmpLo, tmpHi, d );
      System.out.println("swapped d[" + tmpLo + "] with d[" + tmpHi + "]:");
      printArr(d);
      System.out.println("tmpLo: " + tmpLo + "\ttmpHi: " + tmpHi);
      if (d[tmpLo] == d[tmpHi] && tmpLo != tmpHi)
      {tmpLo++;}
    }
    
    d[tmpLo] = pivot;
    
    sortHelp( lo, tmpLo-1, d );
    sortHelp( tmpLo+1, hi, d );
    
  }//end partNHelp
  
  public static void quickSort(Comparable[] n){
    sort(n);
    printArr(n);
  }
  
  
  
  public static void main( String[] args ) {
    
    // TESTING QUICKSORT...
    // NOTE: UNCOMMENT ONE SECTION AT A TIME
    
    /*
     // ...using array of n distinct vals
     Comparable[] arrN = new Comparable[10];
     for( int i = 0; i < arrN.length; i++ )
     arrN[i] = i;
     
     System.out.println("\narrN init'd to: " );
     printArr(arrN);
     
     shuffle(arrN);
     System.out.println("arrN post-shuffle: " );
     printArr(arrN);
     */
    
    // ************ INTERESTING TEST CASES ************
    //distinct vals
    //Comparable [] arrN = {7,5,0,3,4,1,6,2};
    
    //one case of duplicates
    //Comparable [] arrN = {7,5,0,3,4,1,1,2};
    
    //another case of duplicates
    //Comparable [] arrN = {7,6,0,3,4,1,6,2};
    
    //a case of double duplicates, in problematic places
    //Comparable [] arrN = {8,0,2,3,8,4,7,0,2,9};
    
    // System.out.println("\narrN init'd to: " );
    //printArr(arrN);
    //
    // ************************************************
    
    
    //random array, likely to have duplicates
    Comparable[] arrN = new Comparable[8];
    for( int i = 0; i < arrN.length; i++ )
    arrN[i] = (int)(8*Math.random());
    
    System.out.println("\narrN init'd to: " );
    printArr(arrN);
    
    quickSort( arrN );
    System.out.println("arrN after quickSort: " );
    printArr(arrN);
    
  }//end main
  
}//end class QuickSort