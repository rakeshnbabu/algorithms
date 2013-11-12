import java.util.Arrays;

// Ces squelettes sont à compléter et sont là uniquement pour prévenir des
// erreurs de compilation.
class Element {
    public Occurrence e;
    public int s;
    Element (Occurrence e, int s) {
	this.e = e;
	this.s = s;
    }
    public Occurrence getE() {
	return e;
    }
    public void setE(Occurrence e) {
	this.e = e;
    }
    public int getTaille() {
	return e.getTaille();
    }
    public int getRetour() {
	return e.getRetour();
    }
    public int getS() {
	return s;
    }
    public void setS(int s) {
	this.s = s;
    }
    public String toString() {
	return "(" + e.retour + "," + e.taille + ")" + s;
    }
}

class Occurrence {
    public int taille;
    public int retour;
    Occurrence (int retour, int taille) {
	this.taille = taille;
	this.retour = retour;
    }
    public int getTaille() {
	return taille;
    }
    public void setTaille(int taille) {
	this.taille = taille;
    }
    public int getRetour() {
	return retour;
    }
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
	int startPos = positionCourante - tailleFenetre;
	if(startPos < 0)
	    startPos = 0;
	for(int i = startPos; i < positionCourante; i ++) {
	    Occurrence currOcc = new Occurrence(positionCourante - i, 0);
	    boolean matches = true;
	    for(int j = 0; j + i < positionCourante && positionCourante + j < t.length && matches; j ++) {
		if(t[i + j] == t[positionCourante + j]) { 
		    currOcc.setTaille(j+1);  // taille est j + 1 parce que j est indexé à 0.
		} else {
		    matches = false;
		}	
	    }
	    if(currOcc.plusGrandQue(plusLongue) && currOcc.taille > 0)
	       plusLongue = currOcc;
	}
	return plusLongue;
    }

    public static int LZ77Longueur(int[] t, int tailleFenetre) {
	if(t == null)
	    return 0;
	int tableauTaille = 0;
	int positionCourante = 0;
	while(positionCourante < t.length) {
	    //On doit trouver la longueur du plus longue occurrence.
	    //On ajoute un à ce nombre parce qu'on doit sauter le bit suivant.
	    positionCourante += plusLongueOccurrence(t, positionCourante, tailleFenetre).getTaille() + 1;
	    tableauTaille += 1;
	}
	return tableauTaille;
    }
    public static Element[] LZ77(int[] tab, int tailleFenetre) {
	if(tab == null)
	    return null;
	int[] t = tab; //Je ne sais pas pourquoi ils ont changé le nom du tableau; ceci est pour la clarité.
	Element[] retTableau = new Element[LZ77Longueur(t,tailleFenetre)];
	int tableauPosition = 0;
	int positionCourante = 0;
	while(positionCourante < t.length) {
	    //on trouve la plus longue occurrence. On fait un Element avec un position suivant temporaire 
	    //de -1 parce qu'il est un peu difficile de calculer l'indice de la position suivante.
	    retTableau[tableauPosition] = new Element(plusLongueOccurrence(t, 
									      positionCourante, 
									      tailleFenetre),-1); 
	    //on calcule la prochaîne positionCourante.
	    positionCourante += retTableau[tableauPosition].getTaille() + 1;
	    //on remplace la value de S avec le correct bit.
	    retTableau[tableauPosition].setS(t[positionCourante -1]); 
	    //enfin, on peut incrementer la position du tableau.
	    tableauPosition += 1;
	}
	return retTableau;
    }
    
    public static void afficheEncode(Element[] tab) {
	String retString  = "";
	for(Element i : tab) {
	    retString += i;
	}
	System.out.println(retString);
    }
    
    public static int LZ77InverseLongueur(Element[] t) {
	int retLongueur = 0;
	for(Element i : t) {
	    retLongueur += 1 + i.getTaille();
	}
	return retLongueur;
    }
    
    public static int[] LZ77Inverse(Element[] t) {
	if(t == null)
	    return null;

	int[] retTableau = new int[LZ77InverseLongueur(t)];
	int curPos = 0;
	for(Element i : t) {
	    int retPos = curPos - i.getRetour();
	    for(int j = 0; j < i.getTaille(); j ++) {
		retTableau[curPos] = retTableau[retPos];
		curPos ++;
		retPos ++;
	    }
	    retTableau[curPos] = i.getS();
	    curPos ++;
	}
	return retTableau;
    }
    public static void afficheDecode(int[] t) {
	String retString = "";
	for(int i : t) {
	    retString += i + " ";
	}
	System.out.println(retString);
    }

    public static void main(String[] args) {
	/*
	//Tests for LZ77/PlusLongueOccurrence()
	//1. handle the empty list
	System.out.println("Tests for LZ77/PlusLongueOccurrence()");

	System.out.println("Test1:\t []");
	System.out.println("\t Expected: <Occurrence(0,0)>");
	int[] test1 = new int[0];
	System.out.println(plusLongueOccurrence(test1, 0, 0)); 
	
	//2. handle the null list
	System.out.println("Test2:\t null");
	System.out.println("\t Expected: null");
	int[] test2 = null;
	System.out.println(plusLongueOccurrence(test2, 0, 0));

	//3. nominal test of function
	System.out.println("Test3:\t ([1,0,1,1,0,1], 3, 100)");
	System.out.println("\t Expected: <Occurrence(3,3)>");
	int[] test3 = {1,0,1,1,0,1};
	System.out.println(plusLongueOccurrence(test3, 3, 100));

	//4. Selects the larger occurrence, which is the first
	System.out.println("Test4:\t ([1,0,1,1,0,0,1,0,1,1,1,0,1,0,1,1,0],12,100)");
	System.out.println("\t Expected: <Occurrence(12,5)>");
	int[] test4 = {1,0,1,1,0,0,1,0,1,1,1,0,1,0,1,1,0};
	System.out.println(plusLongueOccurrence(test4, 12,100));
	
	//5. selects the larger occurrence, which is the second
	System.out.println("Test5:\t ([1,0,1,1,0,0,1,0,1,1,1,0,1,0,1,1,1],12,100)");
	System.out.println("\t Expected: <Ocurrence(6,5)>");
	int[] test5 = {1,0,1,1,0,0,1,0,1,1,1,0,1,0,1,1,1};
	System.out.println(plusLongueOccurrence(test5, 12,100));
	
	//6. selects the earlier occurrence, when two are the same size.
	System.out.println("Test6:\t ([1,0,1,1,0,0,1,0,1,1,0,0,1,0,1,1,0,0],12,100)");
	System.out.println("\t Expected: <Occurrence(12,6)>");
	int[] test6 ={1,0,1,1,0,0,1,0,1,1,0,0,1,0,1,1,0,0};
	System.out.println(plusLongueOccurrence(test6, 12,100));
	
	//7. Case when (t.length - positioncourante) > fenetre
	System.out.println("Test7:\t ([1,0,1,1,0,0,1,0,1,1,0,0,1,0,1,1,0,0],3,100)");
	System.out.println("\t Expected: <Occurrence(3,2)>");
	int[] test7 ={1,0,1,1,0,0,1,0,1,1,0,0,1,0,1,1,0,0};
	System.out.println(plusLongueOccurrence(test7, 3,100));
	*/

	/*
	//Tests for LZ77Longueur()
	//1. Test for null table
	System.out.println("Test1:\t (null, 100)");
	System.out.println("\t Expected: 0");
	int[] test1 = null;
	System.out.println(LZ77Longueur(test1, 100));

	//2. Test for table of length 0
	System.out.println("Test2:\t ([], 100)");
	System.out.println("\t Expected: 0");
	int[] test2 = new int[0];
	System.out.println(LZ77Longueur(test2, 100));
	
	//3. Test for nominal case
	System.out.println("Test3:\t ([1,0,1,1,0,0,1,0,1,0,0,0,1,2], 100)");
	System.out.println("\t Expected: 6");
	int[] test3 = {1,0,1,1,0,0,1,0,1,0,0,0,1,2};
	System.out.println(LZ77Longueur(test3, 100));
	*/
	/*
	//Tests for LZ77
	//1. Test for null input
	System.out.println("Test1:\t (null, 100)");
	System.out.println("\t Expected: null");
	int[] test1 = null;
	System.out.println(Arrays.toString(LZ77(test1, 100)));

	//2. Test for empty input
	System.out.println("Test2:\t ([], 100)");
	System.out.println("\t Expected: []");
	int[] test2 = new int[0];
	System.out.println(Arrays.toString(LZ77(test2, 100)));
	
	//3. Test for nominal case
	System.out.println("Test3:\t ([1,0,1,1,0,0,1,0,1,0,0,0,1,2], 100)");
	System.out.println("\t Expected: (0,0)1(0,0)0(2,1)1(3,1)0(6,3)0(6,3)2");
	int[] test3 = {1,0,1,1,0,0,1,0,1,0,0,0,1,2};
	afficheEncode(LZ77(test3, 100));
	*/

	//Tests for LZ77Inverse()
	//1. Test for null input
	System.out.println("Test1:\t (null)");
	System.out.println("\t Expected: null");
	Element[] test1 = null;
	System.out.println(LZ77Inverse(test1));

	//2. Test for empty input
	System.out.println("Test2:\t ([])");
	System.out.println("\t Expected: [empty string]");
	Element[] test2 = new Element[0];
	afficheDecode(LZ77Inverse(test2));
	
	//3. Test nominal case
	System.out.println("Test3:\t (0,0)1(0,0)0(2,1)1(3,1)0(6,3)0(6,3)2");
	System.out.println("\t Expected: [1,0,1,1,0,0,1,0,1,0,0,0,1,2]");
	int[] test3a = {1,0,1,1,0,0,1,0,1,0,0,0,1,2};
	Element[] test3 = LZ77(test3a, 100);
	afficheDecode(LZ77Inverse(test3));


    }
}


