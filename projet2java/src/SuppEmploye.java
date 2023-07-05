import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class SuppEmploye extends JFrame implements ActionListener{
    Container con=null;
    JComboBox cob= new JComboBox();
    JLabel l1 = new JLabel("Matricule");
    
    JButton b1=new JButton("Supprimer");
    JButton b2 = new JButton("Retour");
    
public SuppEmploye(){
        con=getContentPane();
        setTitle("Suppression");
        con.setLayout(new FlowLayout());
        setSize(new Dimension(600,200));
        setDefaultCloseOperation(EXIT_ON_CLOSE);     
        setLocationRelativeTo(null);
        
        con.add(l1);
        con.add(cob);
        con.add(b1);
        con.add(b2);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
    try
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection co;
        co=DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava","root","");
        Statement s1 = co.createStatement();
        String req = "select * from employe";
        ResultSet res= s1.executeQuery(req);
        
        while(res.next())
        {
            cob.addItem(res.getInt("matricule"));
        }
    }
    catch(ClassNotFoundException e1)
    {
        JOptionPane.showMessageDialog(null,"err: chargement de pilote");
    }
    catch(SQLException e2)
    {
        JOptionPane.showMessageDialog(null,"err: connection ou requete");

    }
    cob.setSelectedIndex(-1);
         
}
public void actionPerformed(ActionEvent e3)
{
    if(e3.getSource()==b2)
    {
        menu r = new menu();
        r.setVisible(true);
        dispose();
    }
    else if(e3.getSource()==b1)
    {
         try
           {
                
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection co;
                co=DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava","root",""); 
                Statement s1 =co.createStatement();
                String req1="delete from employe where matricule="+cob.getSelectedItem() ;
                int res=s1.executeUpdate(req1);
                JOptionPane.showMessageDialog(null,"Supprimer  avec succées "); 
                cob.setSelectedIndex(-1);

           }
         
     
          
            catch(ClassNotFoundException e11)
            {
            JOptionPane.showMessageDialog(null,"err : chargement pilote");    
            }
            catch(SQLException e4)
            {
              JOptionPane.showMessageDialog(null,"erreur de connection ou rquette");  
            }    
    }}
     public static void main(String[]  args){
        
        new SuppEmploye().setVisible(true);
    }
}

    

