import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class menu extends JFrame implements ActionListener
{
    Container co=null;
    JMenuBar men= new JMenuBar();
    JMenu m1= new JMenu ("Services");
    JMenu m2= new JMenu ("Employes");
    JMenu m3= new JMenu ("Quitter");
    
    JMenuItem a= new JMenuItem("Ajout");
    JMenuItem m= new JMenuItem("Modification");
    JMenuItem s= new JMenuItem("Suppression");
    JMenuItem aj= new JMenuItem("Ajout");
    JMenuItem mo= new JMenuItem("Modification");
    JMenuItem su= new JMenuItem("Suppression");
    JMenuItem f= new JMenuItem("fin de programme");
    
    public menu()
    {
        co=getContentPane();
        setTitle("MENU GENERAL");
        co.setLayout(new FlowLayout());
       setSize(new Dimension(200,130));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setJMenuBar(men);
        men.add(m1);
        m1.add(aj);
        m1.add(mo);
        m1.add(su);
        
        men.add(m2);
        m2.add(a);
        m2.add(m);
        m2.add(s);
            
        men.add(m3);
        m3.add(f);
        
        a.addActionListener(this);
        m.addActionListener(this);
        s.addActionListener(this);
        aj.addActionListener(this);
        mo.addActionListener(this);
        su.addActionListener(this);
        f.addActionListener(this);
    }
    public void actionPerformed(ActionEvent ee)
    {
        if (ee.getSource()== f)
    {
       JOptionPane.showMessageDialog(null,"bye");
        System.exit(0);
    }
    
    else if (ee.getSource()== a)
    {
        new Ajoutemployes().setVisible(true);
        dispose();
    }
    else if (ee.getSource()== aj)
    {
        new Ajoutservices().setVisible(true);
        dispose();
    }
    else if (ee.getSource()==m)
    {
        new modifemployes().setVisible(true);
        dispose();
    }
    else if (ee.getSource()==mo)
    {
        new modifservices().setVisible(true);
    }
    else if(ee.getSource()==s)
    {
        new SuppEmploye().setVisible(true);
    }
         else if(ee.getSource()==su)
    {
        new SuppService().setVisible(true);
    }
    }
    public static void main(String[]arg)
    {
        new menu().setVisible(true);
    }
    
}
