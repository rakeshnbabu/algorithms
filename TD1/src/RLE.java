import java.util.Arrays;

public class RLE {
    public static int longueurRLE(int[] t) {
	if(t == null) {
	    // Ne fais rien
	    // J'aimerais lancer une exception, mais je ne peux pas diviner la syntaxe.
	    return 0;
	}
	else if(t.length == 0) 
	    return 0;
	else {
	    int currBit = -1;
	    int numChanges = 0;
	    for(int i = 0; i < t.length; i ++) {
		if(currBit != t[i]) {
		    currBit = t[i];
		    numChanges += 1;
		} //end if
	    } //end for
	    return 2 * numChanges;
	} // end else
    } // end longueurRLE

    public static int[] RLE(int[] t) {
	if(t == null)
	    return null;
	int[] retArray = new int[longueurRLE(t)];
	if(retArray.length == 0) 
	    return new int[0];
	int currBit = -1;
	int runLength = 0;
	int retI = 0;
	for(int i = 0; i < t.length; i ++) {
	    if(currBit == -1){
		currBit = t[i];
		runLength += 1;
	    } else if(currBit != t[i]) {
		retArray[retI] = currBit;
		retArray[retI+1] = runLength;
		currBit = t[i];
		runLength = 1;
		retI += 2;
	    } else {
		runLength += 1;
	    } //end if
	} //end for
	retArray[retI] = currBit;
	retArray[retI+1] = runLength;
	return retArray;
    }
    
    public static int longueurRLEInverse(int[] t) {
	if(t == null) {
	    // Ne fais rien
	    // J'aimerais lancer une exception, mais je ne peux pas diviner la syntaxe.
	    return 0;
	}
	else if(t.length == 0) 
	    return 0;
	else {
	    int sum = 0;
	    for(int i = 1; i < t.length; i += 2) {
		sum += t[i];
	    } // fin for
	    return sum;
	} // fin else
    } // fin longueurRLEInverse

    public static int[] RLEInverse(int[] t) {
	if(t == null) 
	    return null;
	else if(t.length == 0) 
	    return new int[0];
	else {
	    int[] retArray = new int[longueurRLEInverse(t)];
	    int tmpLoc = 0;
	    int currBit = -1;
	    for(int i = 0; i < t.length; i += 2) {
		currBit = t[i];
		for(int j = tmpLoc; j < tmpLoc + t[i+1]; j ++) {
		    retArray[j] = currBit;
		} // fin for/j	
		tmpLoc += t[i+1];
	    } // fin for/i
	    return retArray;
	} // fin else
    } // fin RLEInverse

    public static void main(String[] args) {
	//Tests for longueurRLE
	if(args[0].equals("1")){	
	    System.out.println("Test 1:\t array of length 0");
	    System.out.println("\t Expected: 0");
	    int[] test1 = new int[0]; 
	    System.out.println(longueurRLE(test1));
	    
	    System.out.println("Test 2:\t null array");
	    System.out.println("\t Expected: Error");
	    int[] test2 = null;
	    System.out.println(longueurRLE(test2));
	    
	    System.out.println("Test 3:\t array [1]");
	    System.out.println("\t Expected: 2");
	    int[] test3 = {1};
	    System.out.println(longueurRLE(test3));
	    
	    System.out.println("Test 4:\t array [0,1,1,0,0,0,0]");
	    System.out.println("\t Expected: 6");
	    int[] test4 = {0,1,1,0,0,0,0};
	    System.out.println(longueurRLE(test4));
	}
	
	//Tests for RLE
	else if(args[0].equals("2")) {
	    System.out.println("Test 1:\t array of length 0");
	    System.out.println("\t Expected: []");
	    int[] test1 = new int[0];
	    System.out.println(Arrays.toString(RLE(test1)));
	    
	    System.out.println("Test 2:\t null array");
	    System.out.println("\t Expected: null");
	    int[] test2 = null;
	    System.out.println(Arrays.toString(RLE(test2)));
	    
	    System.out.println("Test 3:\t array [1]");
	    System.out.println("\t Expected: [1,1]");
	    int[] test3 = {1};
	    System.out.println(Arrays.toString(RLE(test3)));
	    
	    System.out.println("Test 4:\t array [0,1,1,0,0,0,0]");
	    System.out.println("\t Expected: [0,1,1,2,0,4]");
	    int[] test4 = {0,1,1,0,0,0,0};
	    System.out.println(Arrays.toString(RLE(test4)));
	}
	
	//Tests for longueurRLEInverse
	else if(args[0].equals("3")) {
	    System.out.println("Test 1:\t array of length 0");
	    System.out.println("\t Expected: 0");
	    int[] test1 = new int[0]; 
	    System.out.println(longueurRLEInverse(test1));
	    
	    System.out.println("Test 2:\t null array");
	    System.out.println("\t Expected: null");
	    int[] test2 = null;
	    System.out.println(longueurRLEInverse(test2));
	    
	    System.out.println("Test 3:\t array [1,1]");
	    System.out.println("\t Expected: 1");
	    int[] test3 = {1,1};
	    System.out.println(longueurRLEInverse(test3));
	    
	    System.out.println("Test 4:\t array [0,1,1,2,0,4]");
	    System.out.println("\t Expected: 7");
	    int[] test4 = {0,1,1,2,0,4};
	    System.out.println(longueurRLEInverse(test4));
	}
	// Tests for RLEInverse
	else if(args[0].equals("4")) {
	    System.out.println("Test 1:\t array of length 0");
	    System.out.println("\t Expected: []");
	    int[] test1 = new int[0]; 
	    System.out.println(Arrays.toString(RLEInverse(test1)));
	    
	    System.out.println("Test 2:\t null array");
	    System.out.println("\t Expected: null");
	    int[] test2 = null;
	    System.out.println(Arrays.toString(RLEInverse(test2)));
	    
	    System.out.println("Test 3:\t array [1,1]");
	    System.out.println("\t Expected: [1]");
	    int[] test3 = {1,1};
	    System.out.println(Arrays.toString(RLEInverse(test3)));
	    
	    System.out.println("Test 4:\t array [0,1,1,2,0,4]");
	    System.out.println("\t Expected: [0,1,1,0,0,0,0]");
	    int[] test4 = {0,1,1,2,0,4};
	    System.out.println(Arrays.toString(RLEInverse(test4)));
	}
    }
}
