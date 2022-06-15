package projekat;

public class Softver {
	
	public String Naziv;
	public Cetkica Cetkice;
	public Softver(String naziv, Cetkica cetkice, String fajlFormat, String alati, Render renderi) {
		super();
		Naziv = naziv;
		Cetkice = cetkice;
		FajlFormat = fajlFormat;
		Alati = alati;
		Renderi = renderi;
	}
	public String FajlFormat;
	public String Alati;
	public Render Renderi;
	@Override
	public String toString() {
		return Naziv +" "+ FajlFormat+" " + Alati+" "+Cetkice.toString()+" " +Renderi.toString();
	}


}
