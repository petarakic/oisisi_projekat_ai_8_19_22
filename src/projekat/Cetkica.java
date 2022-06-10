package projekat;

import java.awt.Color;

public class Cetkica {
	public String Naziv;
	public String Namena;
	public Color Boja;
	public Cetkica(String naziv, String namena, Color boja) {
		super();
		Naziv = naziv;
		Namena = namena;
		Boja = boja;
	}
	@Override
	public String toString() {
		return Naziv+" "+Namena;
	}


}
