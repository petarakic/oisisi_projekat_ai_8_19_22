package projekat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        
        this.add(toolbar,BorderLayout.NORTH);
        
	}
	
	public static void main(String[] args) {
		new MainWindow();

	}}