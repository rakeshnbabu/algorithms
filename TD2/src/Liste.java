import java.util.NoSuchElementException;

public class Liste {


    private Cellule tete;
    public Liste() {
	tete = null;
    }
    public Liste ajouteTete(Objet val) {
	Cellule newCell = new Cellule(val);
	newCell.setSuivant(tete);
	tete = newCell;
	return this;
    }
    public Liste supprimeTete() {
	if(tete == null) throw new java.util.NoSuchElementException();
	tete = tete.getSuivant();
	return this;
    }
    public boolean contient(Objet o) {
	String targetNom = o.nom();
	Cellule currCellule = this.tete;
	while(currCellule != null) {
	    if(currCellule.getContent().nom().equals(targetNom)) return true;
	    currCellule = currCellule.getSuivant();
	}
	return false;
    }
    public int longueur() {
	Cellule currCellule = this.tete;
	int counter = 0;
	while(currCellule != null) {
	    counter += 1;
	    currCellule = currCellule.getSuivant();
	}	
	return counter;
    }
    public static void main(String[] args) {
	Liste l = new Liste();
	l.ajouteTete(new Objet1("toto"));
	System.out.println(l.contient(new Objet1("toto")));
    }

}
