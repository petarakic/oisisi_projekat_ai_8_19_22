package projekat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class MainWindow extends JFrame {
	JMenuBar  menu = new JMenuBar();
	JToolBar toolbar = new JToolBar();
	JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JTabbedPane tabs = new JTabbedPane();
	JComponent zap = new JPanel();
	JComponent sof = new JPanel();
	JDialog dijalog,deleteDijalog,dijalogS;
	Boolean editB = false;
	Boolean editS = false;
	
	List<Zaposleni> zaposl = new ArrayList<Zaposleni>();
	List<Softver> software = new ArrayList<Softver>();
	
	 DefaultListModel model = new DefaultListModel();
     JList jl = new JList(model);
     DefaultListModel model2 = new DefaultListModel();
     JList<Softver> jl2 = new JList(model2);
	
	
	JTextField ime = new JTextField(20);
	JTextField prezime = new JTextField(20);
	JTextField jmbg = new JTextField(13);
	JTextField mejl = new JTextField(50);
	DateFormat df = new SimpleDateFormat("dd.mm.yyyy.");
	JFormattedTextField txtDate = new JFormattedTextField(df);
	//adresa
	JTextField ulica = new JTextField(20);
	JTextField broj = new JTextField(20);
	JTextField grad = new JTextField(20);
	//softver
	JTextField nazivsof = new JTextField(20);
	JTextField fajl = new JTextField(20);
	JTextField alat = new JTextField(20);
	//cetkica
	JTextField nazivcet = new JTextField(20);
	JTextField namena = new JTextField(20);
	

	Color bojaCet;
	//render
	JTextField materijali = new JTextField(20);
	JTextField kamere = new JTextField(20);
	JTextField objekti = new JTextField(20);
	JTextField nazivren = new JTextField(20);

	//radnoMesto
	 JLabel rad = new JLabel("Radno Mesto");
     String radnaMesta[] = { "modelator", "ilustrator", "animator", "riger"};
     JList mestaR = new JList(radnaMesta);
      

	public MainWindow() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		pack();
		setSize(screenSize.width * 3 / 4, screenSize.height * 3 / 4);
		
		jl2.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                      boolean isSelected, boolean cellHasFocus) {
                 Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                 if (value instanceof Softver) {
                      Softver s = (Softver) value;
                      setText(s.toString());
                      setBackground(s.Cetkice.Boja);
                 } 
                 return c;
            }
       });
	
		makeMeni();
		makeToolbar();
		makeStatusBar();
		makeTabs();
		setVisible(true);
	}
	
	public void makeStatusBar() {
	    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		LocalDateTime date = LocalDateTime.now();   
		String strDate = dateFormat.format(date); 
		JLabel datum = new JLabel("Današnji datum je " + strDate);
		statusBar.add(datum);
		this.add(statusBar,BorderLayout.SOUTH);
	}
	
	public void makeTabs() {
		zap.add(jl);
	  	tabs.addTab("Zaposleni", zap);
	  	sof.add(jl2);
	  	tabs.addTab("Softver", sof);
	  	this.add(tabs);
	}
	
	public void makeMeni() {
		JMenu fajl, edit,help,open;  
	    JMenuItem newitem, exititem, edititem, deleteitem,aboutitem,zaposleniitem,softveritem;  
	    
	    fajl = new JMenu("File");
	    newitem = new JMenuItem("New");
	    newitem.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
	    open = new JMenu("Open");
    	zaposleniitem = new JMenuItem("Zaposleni");
    	softveritem = new JMenuItem("Softver");
    	open.add(zaposleniitem);
    	open.add(softveritem);
    	exititem = new JMenuItem("Exit");
	    exititem.setAccelerator(KeyStroke.getKeyStroke('X', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

	    fajl.add(newitem);
    	fajl.add(open);
    	fajl.add(exititem);
    	menu.add(fajl);
    	  
    	edit = new JMenu("Edit");
    	edititem = new JMenuItem("Edit");
   	  	deleteitem = new JMenuItem("Delete");
   	  	edit.add(edititem);
   	  	edit.add(deleteitem);
   	  	menu.add(edit);
   	  	
   	  	help = new JMenu("Help");
   	  	aboutitem = new JMenuItem("About");
   	  	help.add(aboutitem);
   	  	menu.add(help);
   	  	
    	this.setJMenuBar(menu);
    	
    	aboutitem.addActionListener(new 	ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog autor = new JDialog();
				JTextField teks = new JTextField();
				teks.setText("Jelena Stanic AI 8/2019 --- Srdjana Milićević AI 19/2019 --- Petar Rakić AI 22/2019");
				autor.add(teks);
				autor.setSize(500, 500);
				autor.setVisible(true);
			}
    	});
    	
    	edititem.addActionListener(new 	ActionListener() {
	    
			@Override
			public void actionPerformed(ActionEvent e) {
				
				newEntities();
				
				if (jl.getSelectedValue() != null) {
					editB = true;
					Zaposleni zaposl = (Zaposleni) jl.getSelectedValue();
					ime.setText(zaposl.Ime);
					prezime.setText(zaposl.Prezime);
					mejl.setText(zaposl.Email);
					jmbg.setText(zaposl.Jmbg);
				
					DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy.");	
					String strDate = dateFormat.format(zaposl.DatumRodjenja); 	   
					txtDate.setText(strDate);
					String m=zaposl.RadnoMesto;
					
					for (int i = 0; i < 4; i++) {
						if(radnaMesta[i].equals(m)) {
							mestaR.setSelectedIndex(i);
						}
					}
					
					broj.setText(zaposl.AdresaStan.Broj);
					grad.setText(zaposl.AdresaStan.Grad);
					ulica.setText(zaposl.AdresaStan.Ulica);
					dijalog.setVisible(true);
				}
				
				if(jl2.getSelectedValue()!=null) {
					editS=true;
					Softver s = (Softver)jl2.getSelectedValue();
					nazivsof.setText(s.Naziv);
					fajl.setText(s.FajlFormat);
					alat.setText(s.Alati);
					nazivcet.setText(s.Cetkice.Naziv);
					namena.setText(s.Cetkice.Namena);
					bojaCet=JColorChooser.showDialog(dijalogS, "Izaberi boju cetkice", s.Cetkice.Boja); 
					
					materijali.setText(s.Renderi.Materijali);
					kamere.setText(s.Renderi.Kamere);
					objekti.setText(s.Renderi.Objekti);
					nazivren.setText(s.Renderi.Naziv);
					dijalogS.setVisible(true);
				}
			}
		});
	
		zaposleniitem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabs.setSelectedComponent(zap);
			}
		});
		
		softveritem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabs.setSelectedComponent(sof);
			}
		});
		
		newitem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newEntities();
				dijalog.setVisible(true);
				bojaCet = JColorChooser.showDialog(dijalogS, "Izaberi boju cetkice", Color.RED); 
				dijalogS.setVisible(true);
			}
		});
		
		exititem.addActionListener(new 	ActionListener() {    
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		deleteitem.addActionListener(new 	ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteDialog();
				deleteDijalog.setVisible(true);
			}
		});
	}
	
	public void deleteDialog() {
		JPanel bigpan=new JPanel();
		JPanel buttpan=new JPanel();
		
		deleteDijalog=new JDialog();
		deleteDijalog.setLayout(new BorderLayout());
		deleteDijalog.setModal(true);
		deleteDijalog.setSize(600,600);
		deleteDijalog.add(bigpan);

		JLabel obrisi=new JLabel("Klikom na OK nastavite brisanje");
		JButton okej=new JButton("OK");
		JButton cancel=new JButton("CANCEL");
		
		bigpan.add(obrisi);
		buttpan.add(okej);
		buttpan.add(cancel);
		deleteDijalog.add(buttpan,BorderLayout.SOUTH);
		
		okej.addActionListener(new 	ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				obrisiE();
				deleteDijalog.dispose();		
			}
		});
		
		cancel.addActionListener(new ActionListener() {
   	    
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteDijalog.dispose();
			}
		});
	}
	
	public void obrisiE() {
		if (jl.getSelectedValue() != null) {
			Zaposleni zaposlen = (Zaposleni) jl.getSelectedValue();
			zaposl.remove(zaposlen);
			model.removeElement(zaposlen);
			
		}
		if (jl2.getSelectedValue() != null) {
			Softver s = (Softver) jl2.getSelectedValue();
			software.remove(s);
			model2.removeElement(s);
		}
	}
	
	public void newEntities() {
		dijalog = new JDialog();
		dijalog.setLayout(new BorderLayout());
		dijalog.setModal(true);
		dijalog.setSize(600,800);
		JLabel im = new JLabel("Ime");
		JLabel prez = new JLabel("Prezime");
		JLabel jmb = new JLabel("JMBG");
		JLabel mej = new JLabel("Email");
		JLabel Datum = new JLabel("Datum");
		JLabel ul = new JLabel("Ulica");
		JLabel br = new JLabel("Broj");
		JLabel gr = new JLabel("Grad");
		JLabel so = new JLabel("Naziv softvera");
		JLabel faj = new JLabel("Fajl Format");
		JLabel al = new JLabel("Alat");
		JLabel nazcet = new JLabel("Naziv cetkice");
		JLabel col = new JLabel("Boja");
		JLabel nam = new JLabel("Namena");
		JLabel mat = new JLabel("Materijali");
		JLabel kam = new JLabel("Kamere");
		JLabel objk = new JLabel("Objekti");
		JLabel nazivr = new JLabel("Naziv rendera");
		
		JPanel bigpan = new JPanel(new GridLayout(1,2));
		JPanel zappan = new JPanel(new GridLayout(9,1));
		JPanel sofpan = new JPanel(new GridLayout(10,1));

		nazivsof.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void focusLost(FocusEvent e) {
				JOptionPane greska=new JOptionPane();
				String currentVal = nazivsof.getText();
				
			    for(Softver s:software) {
			    	if(currentVal.equals(s.Naziv) && !editS) {
			    		JOptionPane.showMessageDialog(dijalog,"Greska: Naziv softvera mora biti jedinstven", "Error Message",
						          JOptionPane.ERROR_MESSAGE);
			    		dijalogS.dispose();
			    	}
			    }
			    greska.setVisible(false);	
			}
		});
		
		sofpan.add(so);
		sofpan.add(nazivsof);
		sofpan.add(faj);
		sofpan.add(fajl);
		sofpan.add(al);
		sofpan.add(alat);
		sofpan.add(nazcet);
		sofpan.add(nazivcet);
		sofpan.add(nam);
		sofpan.add(namena);
		sofpan.add(nazivr);
		sofpan.add(nazivren);
		sofpan.add(mat);
		sofpan.add(materijali);
		sofpan.add(kam);
		sofpan.add(kamere);
		sofpan.add(objk);
		sofpan.add(objekti);
		
		
		jmbg.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				JOptionPane greska=new JOptionPane();
				
				String currentVal = jmbg.getText();
			    for (Zaposleni z : zaposl) {
			    	if (currentVal.trim().equals(z.Jmbg)) {
			    		JOptionPane.showMessageDialog(dijalog,"Greska: JMBG mora biti jedinstven", "Error Message",
						          JOptionPane.ERROR_MESSAGE);
			    		dijalog.dispose();
			    		return;
			    	}
			    }
			    greska.setVisible(false);
			}
		});

		zappan.add(im);
		zappan.add(ime);
		zappan.add(prez);
		zappan.add(prezime);
		zappan.add(jmb);
		zappan.add(jmbg);
		zappan.add(Datum);
		zappan.add(txtDate);
		zappan.add(mej);
		zappan.add(mejl);
		zappan.add(rad);
		zappan.add(mestaR);
		zappan.add(ul);
		zappan.add(ulica);
		zappan.add(br);
		zappan.add(broj);
		zappan.add(gr);
		zappan.add(grad);
		
		bigpan.add(zappan);
		dijalog.add(bigpan);
		
		JButton okej = new JButton("OK");
		JButton cancel = new JButton("CANCEL");
		
		JPanel buttpanS = new JPanel();
		JPanel buttpan = new JPanel();

		dijalogS = new JDialog();
		dijalogS.setLayout(new BorderLayout());
		dijalogS.setModal(true);
		dijalogS.setSize(600,600);
		JPanel bigpanS = new JPanel(new GridLayout(1,2));
		bigpanS.add(sofpan);
		dijalogS.add(bigpanS);
		JButton okejS = new JButton("OK");
		JButton cancelS = new JButton("CANCEL");
		
		buttpan.add(okej);
		buttpan.add(cancel);
		dijalog.add(buttpan,BorderLayout.SOUTH);
		buttpanS.add(okejS);
		buttpanS.add(cancelS);
		dijalogS.add(buttpanS,BorderLayout.SOUTH);
		
		okej.addActionListener(new 	ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dijalog.setVisible(true);
				addZaposleni();
				ime.setText(" ");
				prezime.setText(" ");
				jmbg.setText(" ");
				mejl.setText(" ");
				txtDate.setText(" ");
				ulica.setText(" ");
				broj.setText(" ");
				grad.setText(" ");
				mestaR.clearSelection();
				tabs.setSelectedComponent(zap);
				dijalog.dispose();		
			}
		});

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ime.setText(" ");
				prezime.setText(" ");
				jmbg.setText(" ");
				mejl.setText(" ");
				txtDate.setText(" ");
				ulica.setText(" ");
				broj.setText(" ");
				grad.setText(" ");
				mestaR.clearSelection();
				dijalog.dispose();
			}
		});
		
		okejS.addActionListener(new ActionListener() {    
			@Override
			public void actionPerformed(ActionEvent e) {
				dijalogS.setVisible(true);
				if(!editS)addSoftver();
				else editSoftver((Softver)jl2.getSelectedValue());
				nazivsof.setText(" ");
				fajl.setText(" ");
				alat.setText(" ");
				nazivcet.setText(" ");
				namena.setText(" ");
				materijali.setText(" ");
				kamere.setText(" ");
				objekti.setText(" ");
				nazivren.setText(" ");
				tabs.setSelectedComponent(sof);
				dijalogS.dispose();
			}
		});
		
		cancelS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dijalogS.setVisible(true);
				nazivsof.setText(" ");
				fajl.setText(" ");
				alat.setText(" ");
				nazivcet.setText(" ");
				namena.setText(" ");
				materijali.setText(" ");
				kamere.setText(" ");
				objekti.setText(" ");
				nazivren.setText(" ");
				dijalogS.dispose();
			}
		});
	}

	public void addZaposleni() {
		Adresa a = new Adresa(broj.getText(),grad.getText(),ulica.getText());
		String ss = txtDate.getText();
		int year = Integer.parseInt(ss.substring(6, 10));
		int month = Integer.parseInt(ss.substring(3, 5));
		int dan = Integer.parseInt(ss.substring(0,2));
		LocalDate dat = LocalDate.of(year, month,dan);
		String jmbgg = jmbg.getText();
		String ime1 = ime.getText();
		String prez1 = prezime.getText();
		String mail = mejl.getText();

		for (Zaposleni z : zaposl) {
			if (jmbg.getText().equals(z.Jmbg)) {
	    		System.out.println("JMBG mora biti isti jedinstveni !");
	    		JOptionPane.showMessageDialog(dijalog,"Greska: JMBG mora biti jedinstven", "Error Message",
				          JOptionPane.ERROR_MESSAGE);
	    		return;
			}
		}
		
		if (!editB) {
				String mes = (String) mestaR.getSelectedValue();
				Zaposleni zap = new Zaposleni(ime1,prez1,jmbgg,dat,mail,a,mes);
				zaposl.add(zap);
				model.addElement(zap);
		} else {
			for (Zaposleni z : zaposl) {
				if (z.Jmbg.equals(jmbgg)) {
					z.Ime = ime1;
					z.Prezime = prez1;
					z.Email = mail;
					z.AdresaStan = a;
					z.DatumRodjenja = dat;
					z.Jmbg = jmbgg;
					String mes = (String) mestaR.getSelectedValue();
					z.RadnoMesto = mes;
					zaposl.set(zaposl.indexOf(z), z);
					model.setElementAt(z,model.indexOf(z));
					jl.clearSelection();		
				}
			}
			editB=false;
		}
	}
	
	public void addSoftver() {
		Cetkica c = new Cetkica(nazivcet.getText(), namena.getText(), bojaCet);
		Render r = new Render(materijali.getText(), kamere.getText(), objekti.getText(), nazivren.getText());
		
		String nazS = nazivsof.getText();
		String ff = fajl.getText();
		String alati = alat.getText();
		
		Softver sf = new Softver(nazS, c, ff, alati, r);
		

	    for (Softver s : software) {
	    	System.out.println(s.Naziv);
	    	System.out.println(nazS);
	    	if (nazS.trim().equals(s.getNaziv())) {
	    		System.out.println("Sovtveri imaju isti naziv !");
	    		JOptionPane.showMessageDialog(dijalog,"Greska: naziv softveramora biti jedinstven", "Error Message",
				          JOptionPane.ERROR_MESSAGE);
	    		return;
	    	}
	    }
		
		software.add(sf);
		model2.addElement(sf);
	}
	
	public void editSoftver(Softver s) {
		int indeks=software.indexOf(s);
		int modelind=model2.indexOf(s);
		
		Cetkica c = new Cetkica(nazivcet.getText(), namena.getText(), bojaCet);
		Render r = new Render(materijali.getText(), kamere.getText(), objekti.getText(), nazivren.getText());
		
		String nazS = nazivsof.getText();
		String ff = fajl.getText();
		String alati = alat.getText();
		
		s.Naziv=nazS;
		s.Cetkice = c;
		s.Renderi = r;
		s.FajlFormat = ff;
		s.Alati = alati;
		s.Cetkice.Boja = bojaCet;
		
		software.set(indeks, s);
		model2.setElementAt(s,modelind);
		editS = false;
		jl2.clearSelection();
	}
	
	public void makeToolbar() {
		JButton addButton = new JButton(new ImageIcon(
                this.getClass().getResource("/images/addd12.png")));
        JButton editButton = new JButton(new ImageIcon(
                this.getClass().getResource("/images/ed2.png")));
        JButton deleteButton = new JButton(new ImageIcon(
                this.getClass().getResource("/images/delete.png")));
        
        toolbar.add(addButton);
        toolbar.add(editButton);
        toolbar.add(deleteButton);
        
        addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newEntities();
				dijalog.setVisible(true);
				bojaCet=JColorChooser.showDialog(dijalogS, "Izaberi boju cetkice", Color.RED); 
				dijalogS.setVisible(true);
			}
    	});
        
      deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteDialog();
				deleteDijalog.setVisible(true);
			}
		});
	    
	    editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newEntities();
				if (jl.getSelectedValue() != null) {
					editB = true;
					Zaposleni zaposl = (Zaposleni) jl.getSelectedValue();
					ime.setText(zaposl.Ime);
					prezime.setText(zaposl.Prezime);
					mejl.setText(zaposl.Email);
					jmbg.setText(zaposl.Jmbg);
				
					DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy.");	
					String strDate = dateFormat.format(zaposl.DatumRodjenja); 	   
					txtDate.setText(strDate);
					String m = zaposl.RadnoMesto;
					
					for (int i = 0; i < 4; i++) {
						if (radnaMesta[i].equals(m)) {
							mestaR.setSelectedIndex(i);
						}
					}
					
					broj.setText(zaposl.AdresaStan.Broj);
					grad.setText(zaposl.AdresaStan.Grad);
					ulica.setText(zaposl.AdresaStan.Ulica);
					dijalog.setVisible(true);
				}
				if (jl2.getSelectedValue() != null) {
					editS = true;
					Softver s = (Softver) jl2.getSelectedValue();
					nazivsof.setText(s.Naziv);
					fajl.setText(s.FajlFormat);
					alat.setText(s.Alati);
					nazivcet.setText(s.Cetkice.Naziv);
					namena.setText(s.Cetkice.Namena);
					bojaCet = JColorChooser.showDialog(dijalogS,"Izaberi boju cetkice",s.Cetkice.Boja); 
					
					materijali.setText(s.Renderi.Materijali);
					kamere.setText(s.Renderi.Kamere);
					objekti.setText(s.Renderi.Objekti);
					nazivren.setText(s.Renderi.Naziv);
					dijalogS.setVisible(true);
				}		
			}
		});
        this.add(toolbar,BorderLayout.NORTH);
	}
	
	public static void main(String[] args) {
		new MainWindow();
	}}