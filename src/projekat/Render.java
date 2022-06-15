package projekat;

public class Render {
	public String Materijali;
	public String Kamere;
	public Render(String materijali, String kamere, String objekti, String naziv) {
		super();
		Materijali = materijali;
		Kamere = kamere;
		Objekti = objekti;
		Naziv = naziv;
	}
	public String Objekti;
	public String Naziv;
	@Override
	public String toString() {
		return Naziv+" "+Materijali+" "+Kamere+" "+Objekti;
	}


}
