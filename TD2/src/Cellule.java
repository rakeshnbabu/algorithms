public class Cellule {
    private Objet content;
    private Cellule suivant;

    public Cellule() {
	content = null;
	suivant = null;
    }
    public Cellule(Objet val) {
	content = val;
	suivant = null;
    }

	/**
	 * @return the content
	 */
	public Objet getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(Objet content) {
		this.content = content;
	}

	/**
	 * @return the suivant
	 */
	public Cellule getSuivant() {
		return suivant;
	}

	/**
	 * @param suivant the suivant to set
	 */
	public void setSuivant(Cellule suivant) {
		this.suivant = suivant;
	}
}
