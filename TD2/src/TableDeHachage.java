public class TableDeHachage {

    Liste[] table;

    public TableDeHachage(int n) {
	table = new Liste[n];
	for(int i = 0; i < n; i ++) {
	    table[i] = new Liste();
	}
    }
    
    public void ajoute(Objet o) {
	int index = o.hash() % table.length;
	if(index < 0) index += table.length;
	table[index] = table[index].ajouteTete(o);
    }
    
    public boolean contient(Objet o) {
	int index = o.hash() % table.length;
	if(index < 0) index += table.length;
	return table[index].contient(o);
    }
    
    public int[] remplissageMax() {
	int maxIndex = -1;
	int maxLongueur = -1;
	for(int i = 0; i < table.length; i ++) {
	    int iLongueur = table[i].longueur();
	    if(iLongueur > maxLongueur) {
		maxLongueur = iLongueur;
		maxIndex = i;
	    }
	}
	int[] retArray = {maxIndex, maxLongueur};
	return retArray;
    }
    
    public static void main(String[] args) {
	System.out.println(-15 % 6);
    }
}
