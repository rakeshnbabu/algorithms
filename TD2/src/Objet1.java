public class Objet1 extends Objet {
   private String nom;
   
   public Objet1(String nom) {
       this.nom =  nom;
   }

   public String nom() { 
       return nom;
   }

   public int hash() {
       int retHash = 0;
       for(int i = nom.length() - 1; i >= 0; i --) {
            retHash = 31 * (retHash + nom.charAt(i));
       }
       return retHash;
   }
}
