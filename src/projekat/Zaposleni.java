package projekat;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Zaposleni {
	public String Ime;
	public String Prezime;
	public String	Jmbg;
	public LocalDate DatumRodjenja;
	public String Email;
	public String RadnoMesto;
	public Adresa AdresaStan;
	public List<Softver> Softveri=new ArrayList<>();
	public Zaposleni(String ime, String prezime, String jmbg, LocalDate datumRodjenja, String email, Adresa adresaStan, String radnoMesto) {
		super();
		Ime = ime;
		Prezime = prezime;
		Jmbg = jmbg;
		DatumRodjenja = datumRodjenja;
		Email = email;
		AdresaStan = adresaStan;
		RadnoMesto = radnoMesto;
	}
	public void dodajSoftver(Softver s) {
		Softveri.add(s);
	}
	@Override
	public String toString() {
		
		 StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(DatumRodjenja.getDayOfMonth());
		stringBuilder.append(".");
		stringBuilder.append(DatumRodjenja.getMonth());
		stringBuilder.append(".");
		stringBuilder.append(DatumRodjenja.getYear());
		stringBuilder.append(".");
		String dat=stringBuilder.toString();
		return  Ime+" " +  Prezime +" " + Jmbg+" " +  dat
				+" "+  Email + " " + RadnoMesto + " " + AdresaStan.toString();
	}
	

}
