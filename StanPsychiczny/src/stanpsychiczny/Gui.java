
package stanpsychiczny;

import MedServer.PacjenciClass;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;
import MedServer.WynikiClass;
import javax.swing.JTextArea;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 *
 * @author Pantera
 */
public class Gui extends JFrame implements ActionListener {
    private JFrame log,glowne,szP,badanief,regis ;
    private JFrame oprogram = new JFrame("O Programie");
    private JPasswordField passwd;
    private JButton ok,anuluj,rok,badanie,szedytuj,next,prev,ocen;
    private JTextField login,snazwisko,simie,spesel;
    private JLabel sLl,sLh,llekarz,ll,llds,limie,lnazwisko,lpesel,lsnazwisko,
            lsimie,lspesel,lmiasto,lulica,lnrdom,lkod,lnrtel,szn,szi,szp,szm,szu,
            sznrd,szk,sztel,inf,status,szstatus,info,inff;
    private JMenuBar menuBar;
    private JMenu plik,pomoc,edycja;
    private JMenuItem koniec,oprog,kopiuj,wklej,wytnij,nowyp,noweb;
    private JTable tabela;
    private JScrollPane scrol;
    private DefaultTableModel model;
    private JPanel TabPanel,LekPanel,Akcja,reg,Szukaj,szcz,ob,bad;
    private JButton dodaj,przep,usun,edytuj,szzapisz,szanuluj,szbadanie;
    private JToolBar tbar;
    private JFormattedTextField rpesel,rimie,rnazwisko,rmiasto,rulica,rnrdom,
            rkod,rnrtel,szpesel,szimie,sznazwisko,szmiasto,szulica,sznrdom,
            szkod,sznrtel;
    private Border kolor;
    private TitledBorder tytul1,tytul2,tytul3,tytul4,tytul5,tytul6,tytul7,tytul8;
    private TableRowSorter<DefaultTableModel> sorter;
    private int index,inext;
    private JTabbedPane zakladki;
    private boolean flaga=false,flaga2=false;
    private ArrayList<PacjenciClass> sz;
    private ButtonGroup p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p13,p15,p16,p17,p18,p19,p20,p23;
    private JRadioButton p11,p12,p21,p22,p31,p32,p41,p42,p51,p52,p61,p62,p71,p72,
            p81,p82,p91,p92,p101,p102,p131,p132,p151,p152,p161,p162,p171,p172,p181,
            p182,p191,p192,p201,p202,p231,p232;
    private JCheckBox p111,p112,p113,p121,p122,p123,p124,p125,p141,p142,p143;
    private Font f = new Font ("Monospaced", Font.BOLD | Font.ITALIC, 14 );
    private Font f1 = new Font ("serif", Font.ITALIC | Font.BOLD, 12);
    private ChartPanel wykres;
    
    public void oLogowania() 
    {
        log = new JFrame("Logowanie");
        log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        log.setSize(380,200);
        log.setLayout(null);
        log.setResizable(false);
        log.setVisible(true);
        log.setLocation(500, 200);
      
        
        ImageIcon l = new ImageIcon("icons/login.png");
        
        ll = new JLabel(l);
        ll.setBounds(240,20, 128, 128);
        log.add(ll);
        
        llds = new JLabel("Logowanie do systemu badań");
        llds.setBounds(100, 10, 200, 20);
        log.add(llds);
        
        sLl = new JLabel("Login:");
        sLl.setBounds(20, 50, 90, 20);
        log.add(sLl);
        
        sLh = new JLabel("Password:");
        sLh.setBounds(20, 80, 90, 20);
        log.add(sLh);
        
        login = new JTextField();
        login.setBounds(85, 50, 130, 20);
        log.add(login);
        
        passwd = new JPasswordField();
        passwd.setBounds(85, 80, 130, 20);
        log.add(passwd);
        passwd.addActionListener(this);
        
        ok = new JButton("OK");
        ok.setBounds(60, 120, 70, 20);
        log.add(ok);
        ok.addActionListener(this);
        
        anuluj = new JButton("Exit");
        anuluj.setBounds(160, 120, 70, 20);
        log.add(anuluj);
        anuluj.addActionListener(this);
        
      log.revalidate();
      log.repaint();
        
    }//oLogowania
    
    public void oGlowne()
    {
        glowne = new JFrame("Program");
        glowne.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        glowne.setVisible(true);
        glowne.setSize(600,620);
        glowne.setLayout(null);
        glowne.setResizable(false);
        
        //menu bar
        menuBar = new JMenuBar();
        plik = new JMenu("Plik");
        glowne.setJMenuBar(menuBar);
        menuBar.add(plik);       
        
        ImageIcon icon1 = new ImageIcon("icons/Closet.gif");
        ImageIcon add = new ImageIcon("icons/add1.png");
        ImageIcon exammin = new ImageIcon("icons/exammin.png");
        ImageIcon exam = new ImageIcon("icons/exam.png");
        ImageIcon addmin = new ImageIcon("icons/add1min.png");
        ImageIcon cutmin = new ImageIcon("icons/cutmin.png");
        ImageIcon pastemin = new ImageIcon("icons/pastemin.png");
        ImageIcon copymin = new ImageIcon("icons/copymin.png");
        ImageIcon aboutmin = new ImageIcon("icons/aboutmin.png");
        ImageIcon remove = new ImageIcon("icons/remove.png");
        ImageIcon edit = new ImageIcon("icons/edit.png");
 
        nowyp = new JMenuItem("Nowy Pacjent",addmin);  
        plik.add(nowyp);
        nowyp.addActionListener(this);
        
        noweb = new JMenuItem("Nowe Badanie",exammin);
        plik.add(noweb);
        noweb.addActionListener(this);
        
        koniec = new JMenuItem("Exit",icon1);
        plik.addSeparator();
        plik.add(koniec);
        koniec.addActionListener(this);       
        
        edycja = new JMenu("Edytuj");
        glowne.setJMenuBar(menuBar);
        menuBar.add(edycja);
        
        wytnij = new JMenuItem("Wytnij",cutmin);
        wytnij.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
        edycja.add(wytnij);
        wytnij.addActionListener(this);
        
        kopiuj = new JMenuItem("Kopiuj",copymin);
        kopiuj.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
        edycja.add(kopiuj);
        kopiuj.addActionListener(this);
        
        wklej = new JMenuItem("Wklej",pastemin);
        wklej.setAccelerator(KeyStroke.getKeyStroke("ctrl V"));
        edycja.add(wklej);
        wklej.addActionListener(this);
        
        pomoc = new JMenu("Pomoc");
        glowne.setJMenuBar(menuBar);
        menuBar.add(pomoc);
        
        oprog = new JMenuItem("O programie",aboutmin);
        pomoc.add(oprog);
        oprog.addActionListener(this);
        
        //toolbar
        tbar = new JToolBar();
        tbar.setBounds(0, 0, 140, 40);
        glowne.add(tbar);
        
        
        //border komponetow
        kolor = BorderFactory.createLineBorder(Color.DARK_GRAY);
        tytul1 = BorderFactory.createTitledBorder(kolor, "Lista Pacjentów");
        tytul1.setTitleFont(f1);
        tytul1.setTitleJustification(TitledBorder.CENTER);
        tytul2 = BorderFactory.createTitledBorder(kolor, "Lekarz");
        tytul2.setTitleFont(f1);
        tytul2.setTitleJustification(TitledBorder.CENTER);
        tytul3 = BorderFactory.createTitledBorder(kolor, "Akcja");
        tytul3.setTitleFont(f1);
        tytul3.setTitleJustification(TitledBorder.CENTER);
        tytul4 = BorderFactory.createTitledBorder(kolor, "Szukaj");
        tytul4.setTitleFont(f1);
        tytul4.setTitleJustification(TitledBorder.CENTER);
        
        //tabela pacientow
        TabPanel = new JPanel();
        TabPanel.setBounds(5, 50, 430, 450);
        TabPanel.setBorder(tytul1);
        glowne.add(TabPanel);       
        
        tabela = new JTable(); 
        
        tabela.setToolTipText("Kliknij dwukrotnie aby zobaczyć szczeegóły pacjenta");
        scrol = new JScrollPane(tabela);
        scrol.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrol.setPreferredSize(new Dimension(426, 426));
        TabPanel.add(scrol);
        
        tabela.addMouseListener(new MouseAdapter(){
            @Override
        public void mouseClicked(MouseEvent e){
            if (e.getClickCount() == 2){try {
                    szczegolyP();
                    index = getSelectedRow();
                    aktSzczg(index);
                    genWykres();
                } catch (ParseException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }
}}});
               
        //lekarz
        LekPanel = new JPanel();
        LekPanel.setBounds(440, 50, 150, 100);
        LekPanel.setBorder(tytul2);
        LekPanel.setFont(f1);
        glowne.add(LekPanel);
        llekarz = new JLabel();
        llekarz.setFont(f);
        llekarz.setPreferredSize(new Dimension(140, 20));
        LekPanel.add(llekarz);
        
        //info
        info = new JLabel("Podpowiedż:");
        info.setBounds(440, 260, 150, 30);
        glowne.add(info);
        JTextArea t = new JTextArea(" Aby zobaczysz szczegóły\n"
                                + " pacjenta należy kliknąć\n"
                                + " dwukrotnie w wiersz\n"
                                + "z danymi pacjenat");
        t.setFont(f1);
        t.setOpaque(false);
        t.setBounds(450, 290, 130, 80);
       
        t.setEditable(false);
        glowne.add(t);
        //akcja
        Akcja = new JPanel();
        Akcja.setBounds(440, 160, 150, 100);
        Akcja.setBorder(tytul3);
        glowne.add(Akcja);
        
        dodaj = new JButton(add);
        dodaj.setPreferredSize(new Dimension(30,30));
        dodaj.setLayout(new FlowLayout());
        dodaj.setToolTipText("dodaj nowego pacjenta");
        dodaj.addActionListener(this);
        tbar.add(dodaj);
        
        usun = new JButton(remove);
        usun.setPreferredSize(new Dimension(30,30));
        usun.setLayout(new FlowLayout());
        usun.setToolTipText("usuń pacjenta");
        usun.addActionListener(this);
        tbar.add(usun);
        
        edytuj = new JButton(edit);
        edytuj.setPreferredSize(new Dimension(30,30));
        edytuj.setLayout(new FlowLayout());
        edytuj.setToolTipText("usuń pacjenta");
        edytuj.addActionListener(this);
        tbar.add(edytuj);
        
        JSeparator s = new JSeparator(JSeparator.VERTICAL);
        s.setPreferredSize(new Dimension(2,30));
        s.setLayout(new FlowLayout(FlowLayout.LEFT));
        tbar.add(s);
        
        badanie = new JButton(exam);
        badanie.setLayout(new FlowLayout(FlowLayout.LEFT));
        badanie.setPreferredSize(new Dimension(30,30));
        badanie.setToolTipText("wykonaj badanie");
        badanie.addActionListener(this);
        tbar.add(badanie);
            
        przep = new JButton("Badanie");
        przep.setLayout(new FlowLayout());
        przep.setPreferredSize(new Dimension(120,30));
        przep.addActionListener(this);
        Akcja.add(przep);        
        
        //szukaj
        Szukaj = new JPanel();
        Szukaj.setBounds(5, 510, 585, 55);
        Szukaj.setBorder(tytul4);
        glowne.add(Szukaj);
        
        lsnazwisko = new JLabel("Nazwisko:");
        lsnazwisko.setLayout(new FlowLayout());
        Szukaj.add(lsnazwisko);
        
        snazwisko = new JTextField();
        snazwisko.setLayout(new FlowLayout());
        snazwisko.setPreferredSize(new Dimension(130,20));
        snazwisko.getDocument().addDocumentListener(
                new DocumentListener() {
                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        newFilter(snazwisko,0);
                    }
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        newFilter(snazwisko,0);
                    }
                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        newFilter(snazwisko,0);
                    }
                });
        Szukaj.add(snazwisko);
        
        lsimie = new JLabel("Imie:");
        lsimie.setLayout(new FlowLayout());
        Szukaj.add(lsimie);
        
        simie = new JTextField();
        simie.setLayout(new FlowLayout());
        simie.setPreferredSize(new Dimension(130,20));
        simie.getDocument().addDocumentListener(
                new DocumentListener() {
                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        newFilter(simie,1);
                    }
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        newFilter(simie,1);
                    }
                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        newFilter(simie,1);
                    }
                });
        Szukaj.add(simie);
        
        lspesel = new JLabel("Pesel:");
        lspesel.setLayout(new FlowLayout());
        Szukaj.add(lspesel);
        
        spesel = new JTextField();
        spesel.setLayout(new FlowLayout());
        spesel.setPreferredSize(new Dimension(130,20));
        spesel.getDocument().addDocumentListener(
                new DocumentListener() {
                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        newFilter(spesel,2);
                    }
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        newFilter(spesel,2);
                    }
                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        newFilter(spesel,2);
                    }
                });
        Szukaj.add(spesel);
}//oGlowne
    
private void skrajnosc()
    {
        int nn =getSelectedRow();
        if(nn==0)
        {
            prev.setEnabled(false);
        }
        if(nn==inext-1)
        {
            next.setEnabled(false);
        }
    }

    public void szczegolyP() throws ParseException
    {
        szP = new JFrame("Szczegóły pacjenta");
        szP.setVisible(true);
        szP.setSize(700,500);
        szP.setLayout(null);
        szP.setResizable(false);
        szP.setLocation(300, 150);

        glowne.setEnabled(false);
        
        szP.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                glowne.setEnabled(true);
            }
      });
              
        ImageIcon inextt = new ImageIcon("icons/next.png");
        ImageIcon iprevv = new ImageIcon("icons/prev.png");
        
        szcz = new JPanel();
        tytul6 = BorderFactory.createTitledBorder(kolor, "Szczegóły pacjenta");
        tytul6.setTitleJustification(TitledBorder.CENTER);
        tytul6.setTitleFont(f1);
        szcz.setLayout(null);
        szcz.setBorder(tytul6);
        szcz.setBounds(10, 10, 675, 450);
        szP.add(szcz);
        
        ob = new JPanel();
        tytul7 = BorderFactory.createTitledBorder(kolor, "Wykres");
        tytul7.setTitleJustification(TitledBorder.CENTER);
        tytul7.setTitleFont(f1);
        ob.setBorder(tytul7);
        ob.setLayout(new BorderLayout());
        ob.setBounds(270,15,390,420);
        szcz.add(ob);
        
        szstatus = new JLabel("Status: ");
        szstatus.setBounds(10, 420, 40, 20);
        szcz.add(szstatus);
        
        status = new JLabel("Badanie wykonane");
        status.setBounds(50, 420, 100, 20);
        status.setForeground(Color.green);
        szcz.add(status);
        
        szn=new JLabel("Nazwisko:");
        szn.setBounds(10, 20, 70, 20);
        szcz.add(szn);
        szi=new JLabel("Imie:");
        szi.setBounds(10, 50, 70, 20);
        szcz.add(szi);
        szp=new JLabel("Pesel:");
        szp.setBounds(10, 80, 70, 20);
        szcz.add(szp);
        szm=new JLabel("Miejscowość:");
        szm.setBounds(10, 110, 70, 20);
        szcz.add(szm);
        szu=new JLabel("Ulica:");
        szu.setBounds(10, 140, 70, 20);
        szcz.add(szu);
        sznrd=new JLabel("nr domu:");
        sznrd.setBounds(10, 170, 70, 20);
        szcz.add(sznrd);
        szk=new JLabel("Kod poczt.:");
        szk.setBounds(10, 200, 70, 20);
        szcz.add(szk);
        sztel=new JLabel("nr kom:");
        sztel.setBounds(10, 230, 70, 20);
        szcz.add(sztel);
        
        MaskFormatter n = new MaskFormatter("***************");
        n.setInvalidCharacters("0123456789?/|!@#$%^&*()_+=-[]{}|\"\'<>:;,.~`\\");
        MaskFormatter n1 = new MaskFormatter("***");
        n1.setInvalidCharacters("ABCabcdefghijklmnoprstuwxyzvq?/|!@#$%^&*()_+=-[]{}|\"\'<>:;,.`~\\");
        
        sznazwisko = new JFormattedTextField(n);
        sznazwisko.setBounds(80, 20, 180, 20);
        sznazwisko.setEditable(false);
        szcz.add(sznazwisko);
        
        szimie = new JFormattedTextField(n);
        szimie.setBounds(80, 50, 180, 20);
        szimie.setEditable(false);
        szcz.add(szimie);
        
        szpesel = new JFormattedTextField(new MaskFormatter("###########"));
        szpesel.setBounds(80, 80, 180, 20);
        szpesel.setEditable(false);
        szcz.add(szpesel);
        
        szmiasto = new JFormattedTextField();
        szmiasto.setBounds(80, 110, 180, 20);
        szmiasto.setEditable(false);
        szcz.add(szmiasto);
        
        szulica=new JFormattedTextField();
        szulica.setBounds(80, 140, 180, 20);
        szulica.setEditable(false);
        szcz.add(szulica);
        
        sznrdom=new JFormattedTextField(n1);
        sznrdom.setBounds(80, 170, 180, 20); 
        sznrdom.setEditable(false);
        szcz.add(sznrdom);
        
        szkod=new JFormattedTextField(new MaskFormatter("##-###"));
        szkod.setBounds(80, 200, 180, 20); 
        szkod.setEditable(false);
        szcz.add(szkod);
        
        sznrtel=new JFormattedTextField(new MaskFormatter("###-###-###"));
        sznrtel.setBounds(80, 230, 180, 20);
        sznrtel.setEditable(false);
        szcz.add(sznrtel);
        
        szedytuj=new JButton("Edytuj");
        szedytuj.setBounds(10, 265, 80, 20);
        szedytuj.addActionListener(this);
        szcz.add(szedytuj);
        
        szbadanie=new JButton("Badanie");
        szbadanie.setBounds(10, 295, 80, 20);
        szbadanie.addActionListener(this);
        szcz.add(szbadanie);
        
        prev=new JButton(iprevv);
        prev.setBounds(210, 265, 24, 24);
        prev.addActionListener(this);
        szcz.add(prev);
        
        
        next=new JButton(inextt);
        next.setBounds(235, 265, 24, 24);
        next.addActionListener(this);
        szcz.add(next);
        
        szzapisz=new JButton("zapisz");
        szzapisz.setBounds(10, 265, 80, 20);
        szzapisz.setVisible(false);
        szzapisz.addActionListener(this);
        szcz.add(szzapisz);
        
        szanuluj=new JButton("anuluj");
        szanuluj.setBounds(100, 265, 80, 20); 
        szanuluj.setVisible(false);
        szanuluj.addActionListener(this);
        szcz.add(szanuluj);
        
       skrajnosc();
       
       flaga2=true;
        
}//szczegolyP
    
    
    
    private void falseEdit()
    {
        sznazwisko.setEditable(false);
        szimie.setEditable(false);
        szmiasto.setEditable(false);
        szulica.setEditable(false);
        sznrdom.setEditable(false);
        szkod.setEditable(false);
        sznrtel.setEditable(false);
        szzapisz.setVisible(false);
        szanuluj.setVisible(false);
        szedytuj.setVisible(true);
        next.setEnabled(true);
        prev.setEnabled(true);
        szbadanie.setEnabled(true);
    }
    
    private void trueEdit()
    {
        sznazwisko.setEditable(true);
        szimie.setEditable(true); 
        szmiasto.setEditable(true);
        szulica.setEditable(true);
        sznrdom.setEditable(true);
        szkod.setEditable(true);
        sznrtel.setEditable(true);
        szzapisz.setVisible(true);
        szanuluj.setVisible(true);
        szedytuj.setVisible(false);
        next.setEnabled(false);
        prev.setEnabled(false);
        szbadanie.setEnabled(false);
    }
    
private void next()
{ 
      if(index==(inext-2))
      {
       next.setEnabled(false);
      }
      prev.setEnabled(true);
      index++;

      tabela.setRowSelectionInterval(0, index);
      tabela.removeRowSelectionInterval(0, index-1);

      aktSzczg(index);
      genWykres();    
}
    
    private void prev()
    { 
      if(index==1)
      {
        prev.setEnabled(false);
      }
      next.setEnabled(true);
      index--;
      if(index!=0){
      tabela.setRowSelectionInterval(0, index);
      tabela.removeRowSelectionInterval(0, index-1);
      }
      else
      {
       tabela.setRowSelectionInterval(0, 0);  
      }
      aktSzczg(index);    
      genWykres();
    }
    
    private void oprog()
    {       
        oprogram.setVisible(true);
        oprogram.setSize(250,200);
        oprogram.setResizable(false);
        oprogram.setLayout(null); 
        oprogram.setLocation(500, 200);
        
        TextArea t = new TextArea();
        t.setBounds(0,0,260,200);
        t.setEditable(false);
        oprogram.add(t);
        String opis =" Program oceniający stan psychiczny pacjenta\n"
                + " na podstawie przeprowadzonego testu\n" 
                + "              \n\n    Twórcy:\n"
                + "                 Piotr Chmieliński\n"
                + "                 Łukasz Byjoś\n"
                + "                 Paweł Lenart\n\n"
                + "                 Wersja programu: v1.1";
        t.append(opis);
}//oprogramie
    
public void rejestracja() throws ParseException
{   
        regis= new JFrame();
        regis.setSize(300,350);
        regis.setResizable(false);
        regis.setLayout(null); 
        regis.setLocation(500, 200);
        regis.setVisible(true);
        
        glowne.setEnabled(false);
        regis.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                glowne.setEnabled(true);
            }
        });
        
        reg = new JPanel();
        tytul5 = BorderFactory.createTitledBorder(kolor, "Rejestracja pacjenta");
        tytul5.setTitleJustification(TitledBorder.CENTER);
        tytul5.setTitleFont(f1);
        reg.setLayout(null);
        reg.setBorder(tytul5);
        reg.setBounds(10, 10, 275, 300);
        regis.add(reg);
        
        lnazwisko=new JLabel("Nazwisko:");
        lnazwisko.setBounds(10, 20, 70, 20);
        reg.add(lnazwisko);
        limie=new JLabel("Imie:");
        limie.setBounds(10, 50, 70, 20);
        reg.add(limie);
        lpesel=new JLabel("Pesel:");
        lpesel.setBounds(10, 80, 70, 20);
        reg.add(lpesel);
        lmiasto=new JLabel("Miejscowość:");
        lmiasto.setBounds(10, 110, 70, 20);
        reg.add(lmiasto);
        lulica=new JLabel("Ulica:");
        lulica.setBounds(10, 140, 70, 20);
        reg.add(lulica);
        lnrdom=new JLabel("nr domu:");
        lnrdom.setBounds(10, 170, 70, 20);
        reg.add(lnrdom);
        lkod=new JLabel("Kod poczt.:");
        lkod.setBounds(10, 200, 70, 20);
        reg.add(lkod);
        lnrtel=new JLabel("nr kom:");
        lnrtel.setBounds(10, 230, 70, 20);
        reg.add(lnrtel);
        
        
        MaskFormatter n = new MaskFormatter("***************");
        n.setInvalidCharacters("0123456789?/|!@#$%^&*()_+=-[]{}|\"\'<>:;,.`~\\");
        MaskFormatter n1 = new MaskFormatter("***");
        n1.setInvalidCharacters("ABCabcdefghijklmnoprstuwxyzvq?/|!@#$%^&*()_+=-[]{}|\"\'<>:;,.`~\\");
        rnazwisko = new JFormattedTextField(n);
        rnazwisko.setBounds(80, 20, 180, 20);
        reg.add(rnazwisko);
        rimie = new JFormattedTextField(n);
        rimie.setBounds(80, 50, 180, 20);
        reg.add(rimie);
        rpesel = new JFormattedTextField(new MaskFormatter("###########"));
        rpesel.setBounds(80, 80, 180, 20);
        reg.add(rpesel);
        
        rmiasto = new JFormattedTextField();
        rmiasto.setBounds(80, 110, 180, 20);
        reg.add(rmiasto);
        
        rulica=new JFormattedTextField();
        rulica.setBounds(80, 140, 180, 20);
        reg.add(rulica);       
       
        rnrdom=new JFormattedTextField(n1);
        rnrdom.setBounds(80, 170, 180, 20);
        reg.add(rnrdom);
        
        rkod=new JFormattedTextField(new MaskFormatter("##-###"));
        rkod.setBounds(80, 200, 180, 20);
        reg.add(rkod);
        
        rnrtel=new JFormattedTextField(new MaskFormatter("###-###-###"));
        rnrtel.setBounds(80, 230, 180, 20);
        reg.add(rnrtel);
        
        rok=new JButton("Zarejestruj");
        rok.setBounds(90, 270, 100, 20);
        rok.addActionListener(this);
        reg.add(rok);      
 
}//rejest

private void badanie()
{
                 badanief= new JFrame();
                 badanief.setSize(700,560);
                 badanief.setResizable(false);
                 badanief.setLayout(null); 
                 badanief.setLocation(200, 100);
                 badanief.setVisible(true); 
                 
                 glowne.setEnabled(false);
          
        badanief.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                glowne.setEnabled(true);
         
            }
        });
                 
                 bad = new JPanel();
                 tytul8 = BorderFactory.createTitledBorder(kolor, "Badanie pacjenta");
                 tytul8.setTitleJustification(TitledBorder.CENTER);
                 tytul8.setTitleFont(f1);
                 bad.setLayout(null);
                 bad.setBorder(tytul8);
                 bad.setBounds(10, 10, 675, 466);
                 badanief.add(bad);
                 
                 Font fp = new Font ("Arial", Font.ITALIC | Font.BOLD, 14);
                 Font fp1 = new Font ("Arial", Font.ITALIC , 12);
                 
                 inf = new JLabel();
                 inf.setBounds(10, 20, 650, 30);
                 bad.add(inf);
                 
                 JPanel panelz1 = new JPanel();
                 panelz1.setLayout(null);
                 panelz1.setBounds(0,0,705,529);
      
                 JPanel panelz2 = new JPanel();
                 panelz2.setLayout(null);
                 panelz2.setBounds(0,0,705,529);
                 JPanel panelz3 = new JPanel();
                 panelz3.setLayout(null);
                 panelz3.setBounds(0,0,705,529);
                 JPanel panelz4 = new JPanel();
                 panelz4.setLayout(null);
                 panelz4.setBounds(0,0,705,529);
                 JPanel panelz5 = new JPanel();
                 panelz5.setLayout(null);
                 panelz5.setBounds(0,0,705,529);
             
                 
                 JLabel naglowek11 = new JLabel("Orientacja w czasie:");
                 naglowek11.setBounds(10,10,200,30);
                 naglowek11.setFont(fp);
                 panelz1.add(naglowek11);
                 
                 JLabel naglowek12 = new JLabel("Orientacja w miejscu:");
                 naglowek12.setBounds(10, 190, 200, 30);
                 naglowek12.setFont(fp);
                 panelz1.add(naglowek12);
                 
                           
                 JLabel pyt11 = new JLabel("Jaki jest teraz rok ?");
                 pyt11.setBounds(50,40,200,30);
                 pyt11.setFont(fp1);
                 panelz1.add(pyt11);
                 
                 p1= new ButtonGroup();
                 p11 = new JRadioButton("true",true);
                 p11.setBounds(400, 45, 50, 20);
                 p1.add(p11);
                 p12 = new JRadioButton("false",false);
                 p12.setBounds(450, 45, 50, 20);
                 p1.add(p12);
                 panelz1.add(p11);
                 panelz1.add(p12);
                 
                 JLabel pyt12 = new JLabel("Jaka jest teraz pora roku ?");
                 pyt12.setBounds(50,70,200,30);
                 pyt12.setFont(fp1);
                 panelz1.add(pyt12);
                 
                 p2= new ButtonGroup();
                 p21 = new JRadioButton("true");
                 p21.setBounds(400, 75, 50, 20);
                 p2.add(p21); 
                 p22 = new JRadioButton("false");
                 p22.setBounds(450, 75, 50, 20);
                 p2.add(p22);
                 
                 panelz1.add(p21);
                 panelz1.add(p22);
                 
                 JLabel pyt13 = new JLabel("Jaki jest teraz miesiac ?");
                 pyt13.setBounds(50,100,200,30);
                 pyt13.setFont(fp1);
                 panelz1.add(pyt13);
                 
                 p3= new ButtonGroup();
                 p31=new JRadioButton("true");
                 p31.setBounds(400, 105, 50, 20);
                 p3.add(p31);
                 p32=new JRadioButton("false");
                 p32.setBounds(450, 105, 50, 20);
                 p3.add(p32);
                 
                 panelz1.add(p31);
                 panelz1.add(p32);
                 
                 JLabel pyt14 = new JLabel("Jaka jest dzisiejsza data ?");
                 pyt14.setBounds(50,130,200,30);
                 pyt14.setFont(fp1);
                 panelz1.add(pyt14);
                 
                 p4= new ButtonGroup();
                 p41 = new JRadioButton("true");
                 p41.setBounds(400, 135, 50, 20);
                 p4.add(p41);
                 p42 = new JRadioButton("false");
                 p42.setBounds(450, 135, 50, 20);
                 p4.add(p42);
                 panelz1.add(p41);
                 panelz1.add(p42);
                 
                 JLabel pyt15 = new JLabel("jaki jest dzisiaj dzień tygodnia ?");
                 pyt15.setBounds(50,160,200,30);
                 pyt15.setFont(fp1);
                 panelz1.add(pyt15);
                 
                 p5= new ButtonGroup();
                 p51=new JRadioButton("true");
                 p51.setBounds(400, 165, 50, 20);
                 p5.add(p51);
                 p52=new JRadioButton("false");
                 p52.setBounds(450, 165, 50, 20);
                 p5.add(p52);
                 panelz1.add(p51);
                 panelz1.add(p52);
                 
                 JLabel pyt16 = new JLabel("W jaki kraju się znajdujemy ?");
                 pyt16.setBounds(50,220,200,30);
                 pyt16.setFont(fp1);
                 panelz1.add(pyt16);
                 
                 p6= new ButtonGroup();
                 p61 =new JRadioButton("true");
                 p61.setBounds(400, 225, 50, 20);
                 p6.add(p61);
                 p62 =new JRadioButton("false");
                 p62.setBounds(450, 225, 50, 20);
                 p6.add(p62);
                 panelz1.add(p61);
                 panelz1.add(p62);
                 
                 JLabel pyt17 = new JLabel("W jaki wojedzództwie sie znajdujemy ?");
                 pyt17.setBounds(50,250,220,30);
                 pyt17.setFont(fp1);
                 panelz1.add(pyt17);
                 
                 p7= new ButtonGroup();
                 p71=new JRadioButton("true");
                 p71.setBounds(400, 255, 50, 20);
                 p7.add(p71);
                 p72=new JRadioButton("false");
                 p72.setBounds(450, 255, 50, 20);
                 p7.add(p72);
                 panelz1.add(p71);
                 panelz1.add(p72);
                 
                 JLabel pyt18 = new JLabel("W jakim miescie sie znajdujemy ?");
                 pyt18.setBounds(50,280,200,30);
                 pyt18.setFont(fp1);
                 panelz1.add(pyt18);
                 
                 p8= new ButtonGroup();
                 p81 = new JRadioButton("true");
                 p81.setBounds(400, 285, 50, 20);
                 p8.add(p81);
                 p82 = new JRadioButton("false");
                 p82.setBounds(450, 285, 50, 20);
                 p8.add(p82);
                 panelz1.add(p81);
                 panelz1.add(p82);
                 
                 JLabel pyt19 = new JLabel("Jak nazwya sie miejsce w którym sie znajdujemy ?");
                 pyt19.setBounds(50,310,290,30);
                 pyt19.setFont(fp1);
                 panelz1.add(pyt19);
                 
                 p9= new ButtonGroup();
                 p91=new JRadioButton("true");
                 p91.setBounds(400, 315, 50, 20);
                 p9.add(p91);
                 p92=new JRadioButton("false");
                 p92.setBounds(450, 315, 50, 20);
                 p9.add(p92);
                 panelz1.add(p91);
                 panelz1.add(p92);
                 
                 JLabel pyt110 = new JLabel("Na którym pietrze jesteśmy teraz ?");
                 pyt110.setBounds(50,340,200,30);
                 pyt110.setFont(fp1);
                 panelz1.add(pyt110);
                 
                 p10= new ButtonGroup();
                 p101=new JRadioButton("true");
                 p101.setBounds(400, 345, 50, 20);
                 p10.add(p101);
                 p102=new JRadioButton("false");
                 p102.setBounds(450, 345, 50, 20);
                 p10.add(p102);
                 panelz1.add(p101);
                 panelz1.add(p102);
                 //pytanie 1
                 
                 JLabel naglowek21 = new JLabel("Zapamietywanie:");
                 naglowek21.setBounds(10,10,200,30);
                 naglowek21.setFont(fp);
                 panelz2.add(naglowek21);
                 
                 JLabel pyt21 = new JLabel("Wymienie teraz trzy słowa. Kiedy skonczę, poproszę, aby je Pan/Pani powtórzył(a)");
                 JLabel pyt22 = new JLabel("Poniższe słowa wypowiadamy wolno i wyraźnie, proszę je zapamiętać ");
                 pyt21.setBounds(50,40,700,30);
                 pyt21.setFont(fp1);
                 panelz2.add(pyt21);
                 pyt22.setBounds(50,55,700,30);
                 pyt22.setFont(fp1);
                 panelz2.add(pyt22);
                 
                 JLabel odpo = new JLabel("Poprawanie powtórzone: ");
                 odpo.setBounds(50,100,700,30);
                 odpo.setFont(fp1);
                 panelz2.add(odpo);
                 
                 p111 = new JCheckBox("Byk");
                 p111.setBounds(100, 130, 50, 20);

                 p112 = new JCheckBox("Mur");
                 p112.setBounds(150, 130, 50, 20);
                 
                 p113 = new JCheckBox("Las");
                 p113.setBounds(200, 130, 50, 20);

                 panelz2.add(p111);
                 panelz2.add(p112);
                 panelz2.add(p113);
                 //pytanie 2
                 
                 JLabel naglowek31 = new JLabel("Uwaga i Liczenie: ");
                 naglowek31.setBounds(10,170,200,30);
                 naglowek31.setFont(fp);
                 panelz2.add(naglowek31);
                 
                 JLabel pyt31 = new JLabel("Prosze odejmować kolejno od 100 po 7 w 5 krokach");
                 pyt31.setBounds(50,200,700,30);
                 pyt31.setFont(fp1);
                 panelz2.add(pyt31);
                 
                 JLabel o1 = new JLabel("Poprawanie zliczone: ");
                 o1.setBounds(50,240,700,30);
                 o1.setFont(fp1);
                 panelz2.add(o1);
                 
                 p121 = new JCheckBox("Krok 1");
                 p121.setBounds(100, 270, 60, 20);

                 p122 = new JCheckBox("Krok 2");
                 p122.setBounds(170, 270, 60, 20);
                 
                 p123 = new JCheckBox("Krok 3");
                 p123.setBounds(240, 270, 60, 20);
                 
                 p124 = new JCheckBox("Krok 4");
                 p124.setBounds(310, 270, 60, 20);

                 p125 = new JCheckBox("Krok 5");
                 p125.setBounds(380, 270, 60, 20);
                 
                 panelz2.add(p121);
                 panelz2.add(p122);
                 panelz2.add(p123);
                 panelz2.add(p124);
                 panelz2.add(p125);
                 //pytanie3
                 
                 JLabel naglowek41 = new JLabel("Przypominanie");
                 naglowek41.setBounds(10,10,200,30);
                 naglowek41.setFont(fp);
                 panelz4.add(naglowek41);
                 
                 JLabel pyt41 = new JLabel("Proszę wymienić trzy słowa, które Pan(i) miał(a) wcześniej zapamiętać");
                 pyt41.setBounds(50,40,700,30);
                 pyt41.setFont(fp1);
                 panelz4.add(pyt41);
                 
                 JLabel odpo1 = new JLabel("Poprawanie powtórzone: ");
                 odpo1.setBounds(50,80,700,30);
                 odpo1.setFont(fp1);
                 panelz4.add(odpo1);
                 
                 p141 = new JCheckBox("Byk");
                 p141.setBounds(100, 110, 50, 20);

                 p142 = new JCheckBox("Mur");
                 p142.setBounds(150, 110, 50, 20);
                 
                 p143 = new JCheckBox("Las");
                 p143.setBounds(200, 110, 50, 20);

                 panelz4.add(p141);
                 panelz4.add(p142);
                 panelz4.add(p143);
                 //pytanie 4
                 
                 JLabel naglowek51 = new JLabel("Nazywanie");
                 naglowek51.setBounds(10,10,200,30);
                 naglowek51.setFont(fp);
                 panelz5.add(naglowek51);
                 
                 JLabel pyt51 = new JLabel("Prosimy o nazwanie dwóch przedmiotów, które kolejno pokazujemy badanemu (ołówek,zegarek)");
                 pyt51.setBounds(50,40,700,30);
                 pyt51.setFont(fp1);
                 panelz5.add(pyt51);
                 
                 JLabel podpyt1 = new JLabel("Jak nazywa sie przeedmiot nr 1");
                 podpyt1.setBounds(100,70,700,30);
                 podpyt1.setFont(fp1);
                 panelz5.add(podpyt1);
                 
                 JLabel podpyt2 = new JLabel("Jak nazywa sie przeedmiot nr 2");
                 podpyt2.setBounds(100,100,700,30);
                 podpyt2.setFont(fp1);
                 panelz5.add(podpyt2);
                 
                 p13= new ButtonGroup();
                 p131 = new JRadioButton("true");
                 p131.setBounds(400, 75, 50, 20);
                 p13.add(p131); 
                 p132 = new JRadioButton("false");
                 p132.setBounds(450, 75, 50, 20);
                 p13.add(p132);
                 
                 panelz5.add(p131);
                 panelz5.add(p132);
                 
                 p15= new ButtonGroup();
                 p151 = new JRadioButton("true");
                 p151.setBounds(400, 105, 50, 20);
                 p15.add(p151); 
                 p152 = new JRadioButton("false");
                 p152.setBounds(450, 105, 50, 20);
                 p15.add(p152);
                 
                 panelz5.add(p151);
                 panelz5.add(p152);
                 ///////////////////
                 
                 JLabel naglowek52 = new JLabel("Powtarzanie");
                 naglowek52.setBounds(10,130,200,30);
                 naglowek52.setFont(fp);
                 panelz5.add(naglowek52);
                 
                 JLabel pyt52 = new JLabel("Prosze dosłownie powtórzyć nastepujące zdanie:");
                 pyt52.setBounds(50,160,700,30);
                 pyt52.setFont(fp1);
                 panelz5.add(pyt52);
                 
                 JLabel podpyt3 = new JLabel("Ani tak, ani nie, ani ale");
                 podpyt3.setBounds(100,190,700,30);
                 podpyt3.setFont(fp1);
                 panelz5.add(podpyt3);
                 
                 p16= new ButtonGroup();
                 p161 = new JRadioButton("true");
                 p161.setBounds(400, 195, 50, 20);
                 p16.add(p161); 
                 p162 = new JRadioButton("false");
                 p162.setBounds(450, 195, 50, 20);
                 p16.add(p162);
                 
                 panelz5.add(p161);
                 panelz5.add(p162);
                 ///////////////////
                 
                 JLabel naglowek53 = new JLabel("Wykonywanie poleceń");
                 naglowek53.setBounds(10,220,200,30);
                 naglowek53.setFont(fp);
                 panelz5.add(naglowek53);
                 
                 JLabel pyt53 = new JLabel("a) Proszę posłuchac treści całego polecenia a następnie wykonać to polecenie.");
                 pyt53.setBounds(50,250,700,30);
                 pyt53.setFont(fp1);
                 panelz5.add(pyt53);
                 
                 JLabel podpyt4 = new JLabel("Prosze wziąc kartkę do prawej/lewej ręki");
                 podpyt4.setBounds(100,280,700,30);
                 podpyt4.setFont(fp1);
                 panelz5.add(podpyt4);
                 
                 JLabel podpyt5 = new JLabel("złozyć ja oburacz na połowę");
                 podpyt5.setBounds(100,310,700,30);
                 podpyt5.setFont(fp1);
                 panelz5.add(podpyt5);
                 
                 JLabel podpyt6 = new JLabel("i położyć ja na kolana");
                 podpyt6.setBounds(100,340,700,30);
                 podpyt6.setFont(fp1);
                 panelz5.add(podpyt6);
                 
                 p17= new ButtonGroup();
                 p171 = new JRadioButton("true");
                 p171.setBounds(400, 285, 50, 20);
                 p17.add(p171); 
                 p172 = new JRadioButton("false");
                 p172.setBounds(450, 285, 50, 20);
                 p17.add(p172);
                 
                 panelz5.add(p171);
                 panelz5.add(p172);
                 
                 p18= new ButtonGroup();
                 p181 = new JRadioButton("true");
                 p181.setBounds(400, 315, 50, 20);
                 p18.add(p181); 
                 p182 = new JRadioButton("false");
                 p182.setBounds(450, 315, 50, 20);
                 p18.add(p182);
                 
                 panelz5.add(p181);
                 panelz5.add(p182);
                 
                 p19= new ButtonGroup();
                 p191 = new JRadioButton("true");
                 p191.setBounds(400, 345, 50, 20);
                 p19.add(p191); 
                 p192 = new JRadioButton("false");
                 p192.setBounds(450, 345, 50, 20);
                 p19.add(p192);
                 
                 panelz5.add(p191);
                 panelz5.add(p192);
                 
                 JLabel pyt54 = new JLabel("b) Pokazujemy badanemu tekst polecenia zamieszczony na okładce: \"proszę zamknąć oczy\".");
                 pyt54.setBounds(50,10,700,30);
                 pyt54.setFont(fp1);
                 panelz3.add(pyt54);
                 
                 JLabel podpyt7 = new JLabel("Prosze przeczytac to poleceenie i je wykonać");
                 podpyt7.setBounds(100,40,700,30);
                 podpyt7.setFont(fp1);
                 panelz3.add(podpyt7);
                 
                 p20= new ButtonGroup();
                 p201 = new JRadioButton("true");
                 p201.setBounds(400, 45, 50, 20);
                 p20.add(p201); 
                 p202 = new JRadioButton("false");
                 p202.setBounds(450, 45, 50, 20);
                 p20.add(p202);
                 
                 panelz3.add(p201);
                 panelz3.add(p202);
                 
                 JLabel naglowek54 = new JLabel("Pisanie");
                 naglowek54.setBounds(10,70,200,30);
                 naglowek54.setFont(fp);
                 panelz3.add(naglowek54);
                 
                 JLabel pyt55 = new JLabel("Dajemy osobie badanej czystą kartkę papieru i prosimy o napisanie dowolnego zdania");
                 pyt55.setBounds(50,100,700,30);
                 pyt55.setFont(fp1);
                 panelz3.add(pyt55);
                 ///////////////////
                 
                 JLabel podpyt8 = new JLabel("Prosze napisać na tej kartce dowolne zdanie");
                 podpyt8.setBounds(100,130,700,30);
                 podpyt8.setFont(fp1);
                 panelz3.add(podpyt8);
                 
                 p23= new ButtonGroup();
                 p231 = new JRadioButton("true");
                 p231.setBounds(400, 135, 50, 20);
                 p23.add(p231); 
                 p232 = new JRadioButton("false");
                 p232.setBounds(450, 135, 50, 20);
                 p23.add(p232);
                 
                 panelz3.add(p231);
                 panelz3.add(p232);
                             
                 JPanel pytania = new JPanel();
                 pytania.setLayout(null);
                 pytania.setBounds(3, 60, 669, 460);
                 bad.add(pytania);
                 
                 zakladki = new JTabbedPane();
                 zakladki.setSize(705, 405);
                 zakladki.add(panelz1,"ORIENTACJA W CZA. I MIE.");
                 zakladki.add(panelz2,"ZAPAMIETYWANIE I LICZENIE");
                 zakladki.add(panelz4,"PRZYPOMNIENIE");
                 zakladki.add(panelz5,"FUNKCJE JĘZYKOWE");
                 zakladki.add(panelz3,"FUNKCJE J. C.D i PISANIE");
                 pytania.add(zakladki);  
                 
                 ocen = new JButton("Ocen Badanie");
                 ocen.setBounds(10,481,100,40);
                 ocen.addActionListener(this);
                 badanief.add(ocen);
                
}//badanie

private int punkty()
{
   int wynik=0;  
  if(p11.isSelected()==true){wynik++;}
  if(p21.isSelected()==true){wynik++;}
  if(p31.isSelected()==true){wynik++;}
  if(p41.isSelected()==true){wynik++;}
  if(p51.isSelected()==true){wynik++;}
  if(p61.isSelected()==true){wynik++;}
  if(p71.isSelected()==true){wynik++;}
  if(p81.isSelected()==true){wynik++;}
  if(p91.isSelected()==true){wynik++;}
  if(p101.isSelected()==true){wynik++;}
  if(p111.isSelected()==true){wynik++;}
  if(p112.isSelected()==true){wynik++;}
  if(p113.isSelected()==true){wynik++;}
  if(p121.isSelected()==true){wynik++;}
  if(p122.isSelected()==true){wynik++;}
  if(p123.isSelected()==true){wynik++;}
  if(p124.isSelected()==true){wynik++;}
  if(p125.isSelected()==true){wynik++;}
  if(p141.isSelected()==true){wynik++;}
  if(p142.isSelected()==true){wynik++;}
  if(p143.isSelected()==true){wynik++;}
  if(p131.isSelected()==true){wynik++;}
  if(p151.isSelected()==true){wynik++;}
  if(p161.isSelected()==true){wynik++;}
  if(p171.isSelected()==true){wynik++;}
  if(p181.isSelected()==true){wynik++;}
  if(p191.isSelected()==true){wynik++;}
  if(p201.isSelected()==true){wynik++;}
  if(p231.isSelected()==true){wynik++;}
  
  return wynik;
}

private String diagnoza(int punkty)
{

    if(punkty>=0 && punkty <9)
    {
        return "otępienie głębokie";
    }
    else if(punkty>=9 && punkty <19)
    {
        return "otępienie umiarkowane";
    }
    else if(punkty >=19 && punkty<23)
    {
        return "otępnienie łagodne";
    }
    else if(punkty >=23 && punkty <27)
    {
        return "łagodne zaburzenie funkcji poznawczych";
    }
    else if(punkty >=27 && punkty <29)
    {
        return "stan normalny";
    }
    return null;
}


private void genWykres()
{
   TcpCon con = new TcpCon();
   String p =getSelect().toString();
   int iloscwyn = con.conWyn(p);

   if(iloscwyn>0)
   {
     status.setText("Badanie wykonane");
     status.setForeground(new Color(26,168,33));
     wykres();
   }
   else if(iloscwyn==0)
   {   
     status.setText("Brak badania");
     status.setForeground(Color.red);
           ob.removeAll();
           ob.repaint();
   }
}

public void wykres()
{
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    TcpCon con = new TcpCon();
    String p =getSelect().toString();
    for(WynikiClass i :con.getWyniki(p))
             { 
                 dataset.setValue(i.getWynik(), "Profit", i.getData());         
             }
    
		JFreeChart chart = ChartFactory.createBarChart("Wykres przeprowadzonych badań",
			"Data", "Punkty", dataset, PlotOrientation.VERTICAL,
			false, true, false);		
 
		wykres=new ChartPanel(chart);
		wykres.setVisible(true);
                ob.add(wykres,BorderLayout.CENTER);
                ob.validate();
    
}
    
private int getSelectedRow()
{
        return tabela.getSelectedRow();
}

private Object getSelect()//pobranie peselu
{
        int row = tabela.getSelectedRow();
        Object select = model.getValueAt(row, 2);    
        return select;
}//getSelect
     
public void aktualizacjaTabeli()
{      
            Object[] col = {"Nazwisko","Imie","Pesel"};
            TcpCon con = new TcpCon();
            
             model = new DefaultTableModel(col,0){
                 @Override
                 public boolean isCellEditable(int row, int column) {
                     return false;
                 }};
             
             for(PacjenciClass i :con.getPacjenci())
             {
               Object[] row = new Object[3];
               row[0]=i.getNazwisko().toUpperCase();
               row[1]=i.getImie().toUpperCase();
               row[2]=i.getPeselCaly();
               model.addRow(row);  
               flaga=true;
             }
             
             tabela.setModel(model); 
             sorter = new TableRowSorter<>(model);
             tabela.setRowSorter(sorter);
             
}//aktual.tabli
    
public void aktSzczg(int index)
{
     sznazwisko.setText(sz.get(index).getNazwisko().toString());
     szimie.setText(sz.get(index).getImie().toString());
     szpesel.setText(sz.get(index).getPeselCaly().toString());
     szmiasto.setText(sz.get(index).getMiasto().toString());
     szulica.setText(sz.get(index).getUlica().toString());
     sznrdom.setText(sz.get(index).getNr_dom().toString());
     szkod.setText(sz.get(index).getKod_pocztowy().toString());
     sznrtel.setText(sz.get(index).getTel().toString());
     
}//szczegoly
 
    private void newFilter(JTextField t, int i) {
      
        RowFilter<DefaultTableModel, Object> rf ;
        try {
            rf = RowFilter.regexFilter(t.getText().toUpperCase(), i);
            
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
}//nowy filtr
    
    private int sprPola()
    {
       if(rnazwisko.getText().trim().length()==0)
       {
         rnazwisko.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.red));  
       }
       else if(rnazwisko.getText().trim().length() >0)
       {
        rnazwisko.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.LIGHT_GRAY));  
       }
       if(rimie.getText().trim().length()==0)
       {
        rimie.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.red));   
       }
       else if(rimie.getText().trim().length()>0)
       {
        rimie.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.LIGHT_GRAY));     
       }
        if(rpesel.getText().trim().length()==0)
       {
        rpesel.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.red));              
       }
       else if(rpesel.getText().trim().length()>0)
       {
        rpesel.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.LIGHT_GRAY));
       }
        if(rkod.getText().trim().length()==0)
       {
        rkod.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.red));              
       }
       else if(rkod.getText().trim().length()>0)
       {
        rkod.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.LIGHT_GRAY));
       }
        if(rmiasto.getText().trim().length()==0)
       {
        rmiasto.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.red));              
       }
       else if(rmiasto.getText().trim().length()>0)
       {
        rmiasto.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.LIGHT_GRAY));
       }
       if(rnazwisko.getText().trim().length() >0 && rimie.getText().trim().length()>0 && rpesel.getText().trim().length()>0 &&
          rkod.getText().trim().length()>0 && rmiasto.getText().trim().length()>0)
       {
           return 1;
       }
       else {
            return 0;
        }
}

private void start()
{
    TcpCon con = new TcpCon();
    if((login.getText().trim().length()==0)||passwd.getText().trim().length()==0)
    {
        login.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.red));
        passwd.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.red)); 
    }
    else
    {
        if(con.login(login.getText(), passwd.getText())!=1)
        {
            String msg="Bład logowania";
            Powiadomienia.w_msg(msg, null); 
        }
        else
        {
            log.dispose(); 
            oGlowne();
            aktualizacjaTabeli();
            if(flaga==true)
            {
            tabela.setRowSelectionInterval(0, 0);    
            }
            llekarz.setText("dr."+con.getLekarz(login.getText()));
            inext=(int)con.countPacjent(); 
            sz = new ArrayList<PacjenciClass>();
            sz=con.getPacjenci();
            
        }
     } 
}

private void startAdd()
{
    TcpCon con = new TcpCon();
    if(sprPola()==0)
    {
                 
    }
    else
    {
        String msg="Na pewno dodac pacjenta ?";
        String tytul="Dodaj";
        int odp =Powiadomienia.chose_msg(msg, tytul,null);
        
        if(odp==JOptionPane.YES_OPTION)
        {
         int d= con.addPacjent(rimie.getText(),rnazwisko.getText(),
         rpesel.getText(),rmiasto.getText(),rulica.getText(),
         rnrdom.getText(),rnrtel.getText(),rkod.getText());
         if(d==2)
         {
           String msg1="Pacjent juz istnieje";
           Powiadomienia.w_msg(msg1, null);
         }
         else if(d==0)
         {
           rpesel.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.red));
           rpesel.setToolTipText("Nieprawidłowy pesel,musi być prawdziwy");
         }
         else
         {
             Powiadomienia.info_msg("Pomyslnie dodano pacjenta", null);   
             regis.dispose();
             aktualizacjaTabeli();
             inext=(int)con.countPacjent();
             sz=con.getPacjenci();
             tabela.setRowSelectionInterval(0, 0);
             glowne.setEnabled(true);
         }
              
         }
    }     
}

private void startWynik()
{
    TcpCon con = new TcpCon();
    int wyn=punkty();
    boolean oo = con.addWynik(getSelect(), login.getText(), wyn);
    if(oo==true)
    {
        badanief.dispose();
        String msg ="Wynik badania: "+punkty()+"\n Diagnoza: "+diagnoza(punkty())
                +"\nwykres badan mozna zobaczyc w szczegółach pacjenta";
        Powiadomienia.info_msg(msg, null);
    }
    else
    {
        Powiadomienia.chose_msg("Wystąpił błąd", "Błąd badania", null);
    }
}

private void startUsun()
{
  TcpCon con = new TcpCon();
  String msgg="Czy na pewno chcesz usunąć pacjenta\n o nr pesel: "+getSelect();
  String tytull="USUŃ";
  int spr;
  if(Powiadomienia.chose_msg(msgg, tytull, null)==JOptionPane.YES_OPTION) 
  {
      if(con.deletePacjent(getSelect())==1)
      {
          String powia="Pomyślnie usunięto pacjenta";
          Powiadomienia.info_msg(powia, null);
          aktualizacjaTabeli();
          inext=(int)con.countPacjent();
          sz=con.getPacjenci();
          spr=(int)con.countPacjent();
          if(spr!=0)
          {
              tabela.setRowSelectionInterval(0, 0);
          }
          
      }
      else
      {
          String po="Wystąpił Bład przy usuwaniu";
          Powiadomienia.w_msg(po, null);
      }
  }
}

private void startUpdate()
{
   TcpCon con = new TcpCon(); 
   String msg="Na pewno chcesz wprowadzic zmiany ?";
   String tytul="Zmiana";
        int odp =Powiadomienia.chose_msg(msg, tytul,null);
        
        if(odp==JOptionPane.YES_OPTION)
        {
         int d= con.updatePacjent(szimie.getText(),sznazwisko.getText(),
         szpesel.getText(),szmiasto.getText(),szulica.getText(),
         sznrdom.getText(),sznrtel.getText(),szkod.getText());
        
         if(d==0)
         {
             Powiadomienia.w_msg("Wystąpił Bład", null);
         }
         else
         {
             Powiadomienia.info_msg("Pomyslnie zaktualizowano dane", null);   
             aktualizacjaTabeli();
             sz=con.getPacjenci();
             tabela.setRowSelectionInterval(0, 0);
             aktSzczg(index);
             falseEdit();
         }
    }
}

    @Override
    public void actionPerformed(ActionEvent e) {
         Object w = e.getSource();
         TcpCon con = new TcpCon();
         
         if(w==anuluj)
         {
             con.closeSocket();
             log.dispose();   
         }
         else if(w==ok || w==passwd)
         {  
           start(); 
         }
         else if(w==koniec)
         { 
             con.closeSocket();
             glowne.dispose();
         }
         else if(w==oprog)
         {
             if(oprogram.isActive()==false)
             {
                oprog();  
             }
         }
         else if(w==dodaj || w==nowyp)
         {
            try {
                rejestracja();
            } catch (ParseException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
         else if(w==rok)
         {      
           startAdd();  
         }
         else if(w==usun)
             {
               startUsun();
             }
         else if(w==szedytuj)
         {
             trueEdit();     
         }
         else if(w==szanuluj)
         {
             falseEdit();
             index = getSelectedRow();
             skrajnosc();
             aktSzczg(index);
         }
         else if(w==next)
         {            
             next();
         }
         else if(w==prev)
         {
             prev();  
         }
         else if(w==edytuj)
         {
            try {
                szczegolyP();
                index = getSelectedRow();
                aktSzczg(index);
                genWykres();
            } catch (ParseException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
         else if(w==przep || w==badanie || w==noweb || w==szbadanie)
         {   
                String n=sz.get(getSelectedRow()).getNazwisko().trim().toString();
                String i=sz.get(getSelectedRow()).getImie().trim().toString();
                badanie();
                inf.setText("Pacjent: ["+n+" "+i+"] \n Lekarz: ["+llekarz.getText()+"]");
         }
         else if(w==ocen)
         {
            startWynik();
            if(flaga2==true)
            {
            aktSzczg(index);
            genWykres();
            skrajnosc();
            }
            glowne.setEnabled(true);
         }
         else if(w==szzapisz)
         {
             startUpdate();
         }
    }//action 
       
}//Gui