public class Objet2 extends Objet {
   private String nom;
   
   public Objet2(String nom) {
       this.nom =  nom;
   }

   public String nom() { 
       return nom;
   }

   public int hash() {
       int retHash = 5381;
       for(int i = 0; i < nom.length(); i ++) {
	   retHash = (retHash * 33) ^ nom.charAt(i) ;
       }
       return retHash;
   }
    public static void main(String[] args) {
	//Test 1: Hashing the string "coucou"
	Objet t1 = new Objet2("coucou");
	System.out.println(t1.hash());
    }
}
