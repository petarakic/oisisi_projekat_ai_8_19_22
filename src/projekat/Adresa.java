package projekat;

public class Adresa {
	public String Broj;
	public String Grad;
	public String Ulica;
	public Adresa(String broj, String grad, String ulica) {
		super();
		Broj = broj;
		Grad = grad;
		Ulica = ulica;
	}
	@Override
	public String toString() {
		StringBuilder s=new StringBuilder();
		s.append(Ulica).append(" ").append(Broj).append(" ").append(Grad);
		return s.toString();
	}

}
