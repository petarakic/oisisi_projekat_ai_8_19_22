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
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class MainWindow extends JFrame {
	JMenuBar  menu=new JMenuBar();
	JToolBar toolbar=new JToolBar();
	JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JTabbedPane tabs = new JTabbedPane();
	JComponent zap = new JPanel();
	JComponent sof = new JPanel();
	JDialog dijalog,deleteDijalog,dijalogS;
	Boolean editB=false;
	Boolean editS=false;
	
	List<Zaposleni> zaposl=new ArrayList<Zaposleni>();
	List<Softver> software=new ArrayList<Softver>();
	
	 DefaultListModel model = new DefaultListModel();
     JList jl = new JList(model);
     DefaultListModel model2 = new DefaultListModel();
     JList<Softver> jl2 = new JList(model2);
	
	
	JTextField ime=new JTextField(20);
	JTextField prezime=new JTextField(20);
	JTextField jmbg=new JTextField(13);
	JTextField mejl=new JTextField(50);
	DateFormat df = new SimpleDateFormat("dd.mm.yyyy.");
	JFormattedTextField txtDate = new JFormattedTextField(df);
	//adresa
	JTextField ulica=new JTextField(20);
	JTextField broj=new JTextField(20);
	JTextField grad=new JTextField(20);
	//softver
	JTextField nazivsof=new JTextField(20);
	JTextField fajl=new JTextField(20);
	JTextField alat=new JTextField(20);
	//cetkica
	JTextField nazivcet=new JTextField(20);
	JTextField namena=new JTextField(20);
	

	Color bojaCet;
	//render
	JTextField materijali=new JTextField(20);
	JTextField kamere=new JTextField(20);
	JTextField objekti=new JTextField(20);
	JTextField nazivren=new JTextField(20);

	//radnoMesto
	 JLabel rad= new JLabel("Radno Mesto");
     String radnaMesta[]= { "modelator","ilustrator","animator", "riger"};
     JList mestaR=new JList(radnaMesta);
      

	public MainWindow() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		pack();
		setSize(screenSize.width*3/4,screenSize.height*3/4);
		
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
		setVisible(true);
	}
	
	public void makeMeni() {
		JMenu fajl, edit,help,open;  
	    JMenuItem newitem, exititem, edititem, deleteitem,aboutitem,zaposleniitem,softveritem;  
	    
	    fajl=new JMenu("File");
	    newitem=new JMenuItem("New");
	    newitem.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
	    open=new JMenu("Open");
    	zaposleniitem=new JMenuItem("Zaposleni");
    	softveritem=new JMenuItem("Softver");
    	open.add(zaposleniitem);
    	open.add(softveritem);
    	exititem=new JMenuItem("Exit");
	    exititem.setAccelerator(KeyStroke.getKeyStroke('X', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

	    fajl.add(newitem);
    	fajl.add(open);
    	fajl.add(exititem);
    	menu.add(fajl);
    	  
    	edit=new JMenu("Edit");
    	edititem=new JMenuItem("Edit");
   	  	deleteitem=new JMenuItem("Delete");
   	  	edit.add(edititem);
   	  	edit.add(deleteitem);
   	  	menu.add(edit);
   	  	
   	  	help=new JMenu("Help");
   	  	aboutitem=new JMenuItem("About");
   	  	help.add(aboutitem);
   	  	menu.add(help);
   	  	
    	this.setJMenuBar(menu);
    	aboutitem.addActionListener(new 	ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JDialog autor=new JDialog();
				JTextField teks=new JTextField();
				teks.setText("Jelena Stanic AI 8/2019 --- Srdjana Milićević AI 19/2019 --- Petar Rakić AI 22/2019");
				autor.add(teks);
				autor.setSize(500, 500);
				autor.setVisible(true);
				
			}
    	});
	}
	
	public void newEntities() {
		dijalog=new JDialog();
		dijalog.setLayout(new BorderLayout());
		dijalog.setModal(true);
		dijalog.setSize(600,800);
		JLabel im=new JLabel("Ime");
		JLabel prez=new JLabel("Prezime");
		JLabel jmb=new JLabel("JMBG");
		JLabel mej=new JLabel("Email");
		JLabel Datum=new JLabel("Datum");
		JLabel ul=new JLabel("Ulica");
		JLabel br=new JLabel("Broj");
		JLabel gr=new JLabel("Grad");
		JLabel so=new JLabel("Naziv softvera");
		JLabel faj=new JLabel("Fajl Format");
		JLabel al=new JLabel("Alat");
		JLabel nazcet=new JLabel("Naziv cetkice");
		JLabel col=new JLabel("Boja");
		JLabel nam=new JLabel("Namena");
		JLabel mat=new JLabel("Materijali");
		JLabel kam=new JLabel("Kamere");
		JLabel objk=new JLabel("Objekti");
		JLabel nazivr=new JLabel("Naziv rendera");
		
		JPanel bigpan=new JPanel(new GridLayout(1,2));
		JPanel zappan=new JPanel(new GridLayout(9,1));
		JPanel sofpan=new JPanel(new GridLayout(10,1));

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
			    		greska.showMessageDialog(dijalog,"Greska: Naziv softvera mora biti jedinstven", "Error Message",
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
			    for(Zaposleni z:zaposl) {
			    	double d=Double.parseDouble(z.Jmbg);
			    	if(currentVal.equals(z.Jmbg) && !editB) {
			    		JOptionPane.showMessageDialog(dijalog,"Greska: JMBG mora biti jedinstven", "Error Message",
						          JOptionPane.ERROR_MESSAGE);
			    		dijalog.dispose();
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
		JButton okej=new JButton("OK");
		JButton cancel=new JButton("CANCEL");
		
		JPanel buttpanS=new JPanel();
		JPanel buttpan=new JPanel();

		dijalogS=new JDialog();
		dijalogS.setLayout(new BorderLayout());
		dijalogS.setModal(true);
		dijalogS.setSize(600,600);
		JPanel bigpanS=new JPanel(new GridLayout(1,2));
		bigpanS.add(sofpan);
		dijalogS.add(bigpanS);
		JButton okejS=new JButton("OK");
		JButton cancelS=new JButton("CANCEL");
		
		buttpan.add(okej);
		buttpan.add(cancel);
		dijalog.add(buttpan,BorderLayout.SOUTH);
		buttpanS.add(okejS);
		buttpanS.add(cancelS);
		dijalogS.add(buttpanS,BorderLayout.SOUTH);
		
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
				bojaCet=JColorChooser.showDialog(dijalogS,"Izaberi boju cetkice",Color.RED); 
				dijalogS.setVisible(true);
				
				
			}
    	});
        
        this.add(toolbar,BorderLayout.NORTH);
        
	}
	
	public static void main(String[] args) {
		new MainWindow();

	}}