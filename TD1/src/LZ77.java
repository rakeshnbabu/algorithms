// Ces squelettes sont à compléter et sont là uniquement pour prévenir des
// erreurs de compilation.
class Element {
  Element (Occurrence e, int s) {}
}

class Occurrence {
    public int taille;
    public int retour;
    Occurrence (int retour, int taille) {
	this.taille = taille;
	this.retour = retour;
    }

    /**
     * @return the taille
     */
    public int getTaille() {
	return taille;
    }
    
    /**
     * @param taille the taille to set
     */
    public void setTaille(int taille) {
	this.taille = taille;
    }
    
    /**
     * @return the retour
     */
    public int getRetour() {
	return retour;
    }
    
    /**
     * @param retour the retour to set
     */
    public void setRetour(int retour) {
	this.retour = retour;
    }

    public boolean plusGrandQue(Occurrence autre) {
	if(taille > autre.getTaille())
	    return true;
	else if(taille < autre.getTaille())
	    return false;
	else {
	    if(retour > autre.getRetour())
		return true;
	    else
		return false;
	}
    } 
    public String toString() {
	return "<Occurence(" + retour + "," + taille + ")>";
    }
}

public class LZ77 {
    public static Occurrence plusLongueOccurrence(int[] t, int positionCourante, int tailleFenetre) {
	if(t == null)
	    return null;
        Occurrence plusLongue = new Occurrence(0,0);
	for(int i = (positionCourante > tailleFenetre) ? positionCourante - tailleFenetre : 0; i < positionCourante; i ++) {
	    Occurrence currOcc = new Occurrence(positionCourante - i, 0);
	    boolean matches = true;
	    for(int j = 0; j < t.length - positionCourante && j < tailleFenetre && matches; j ++) {
		if(t[j] == t[positionCourante + j]) { 
		    currOcc.setTaille(j+1);  // taille est j + 1 parce que j est indexé à 0.
		} else
		    matches = false;
	    }
	    if(currOcc.plusGrandQue(plusLongue))
	       plusLongue = currOcc;
	}
	return plusLongue;
    }

    public static void main(String[] args) {
	//Tests for LZ77/PlusLongueOccurrence()
	System.out.println("Tests for LZ77/PlusLongueOccurrence()");

	System.out.println("Test1:\t []");
	System.out.println("\t Expected: <Occurrence(0,0)>");
	int[] test1 = new int[0];
	System.out.println(plusLongueOccurrence(test1, 0, 0)); 
	
	System.out.println("Test2:\t null");
	System.out.println("\t Expected: null");
	int[] test2 = null;
	System.out.println(plusLongueOccurrence(test2, 0, 0));

	System.out.println("Test3:\t ([1,0,1,1,0,1], 3, 100)");
	System.out.println("\t Expected: <Occurrence(3,3)>");
	int[] test3 = {1,0,1,1,0,1};
	System.out.println(plusLongueOccurrence(test3, 3, 100));
	

	System.out.println

    }
}

