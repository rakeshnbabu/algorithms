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
       for(int i = 0; i < nom.length(); i ++) {
	   retHash *= 31;
	   retHash += nom.charAt(i);
       }
       return retHash;
   }
    public static void main(String[] args) {
	//Test 1: Hashing the string "coucou"
	Objet t1 = new Objet1("coucou");
	System.out.println(t1.hash());
    }
}
