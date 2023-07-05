import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ajoutemployes extends JFrame implements ActionListener 
{
    Container co;
    JLabel l1= new JLabel("Code Service");
    JComboBox cob=new JComboBox();
    
    JLabel l2= new JLabel("Matricule");
    JTextField t2= new JTextField(5);
    
    JLabel l3=new JLabel("Nom");
    JTextField t3= new JTextField(15);
    
    JLabel l4= new JLabel("Salaire");
    JTextField t4= new JTextField(10);
    
    JButton b1= new JButton("Ajouter");
    JButton b2= new JButton("Retour");
    
    public Ajoutemployes()
    {
        co=getContentPane();
        setTitle("Ajout");
        
        co.setBackground(Color.white);
        setSize(new Dimension(800,400));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        co.setLayout(new FlowLayout());
        co.add(l1);
        co.add(cob);
        co.add(l2);
        co.add(t2);
        co.add(l3);
        co.add(t3);
        co.add(l4);
        co.add(t4);
        co.add(b1);
        co.add(b2);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        cob.addActionListener(this);
        
         try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection co=null;
            co=DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava","root","");
            Statement s1 = co.createStatement();
            String req="select * from service ";
            ResultSet res=s1.executeQuery(req);
            while(res.next())
            {
                cob.addItem(res.getInt("code_service"));
            }
        }
        catch(ClassNotFoundException e11)
            {
            JOptionPane.showMessageDialog(null,"err : chargement pilote");    
            }
        catch(SQLException e2)
            {
              JOptionPane.showMessageDialog(null,"erreur de connect ou requete");  
            }
         cob.setSelectedIndex(-1);
          t2.setText("");
          t3.setText("");
          t4.setText("");
       
    }
    public void actionPerformed(ActionEvent e1)
    {
         if (e1.getSource()==b2)
        {
            menu m= new menu();
            m.setVisible(true);
            dispose();
        }
         else if (e1.getSource()==b1){
           
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con;
            
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava","root","");
            Statement s1=con.createStatement();
            Statement s2=con.createStatement();
            String req2="select * from employe where matricule="+t2.getText()+"";

            String req1 ="insert into employe(matricule,nom,salaire,code_service) values("+t2.getText()+",'"+t3.getText()+"',"+t4.getText()+","+cob.getSelectedItem()+")";
            ResultSet res=s1.executeQuery(req2);
            
            int existe=0;
            while(res.next())
            {
                existe=1;
            }
            if (existe==1)
            {
                JOptionPane.showMessageDialog(null,"matricule existe deja!");
            }
            
            else
            {
            int ress=s1.executeUpdate(req1);
            JOptionPane.showMessageDialog(null,"insertion reussie");
            }
            
            cob.setSelectedIndex(-1);
            t2.setText("");
            t3.setText("");
            t4.setText("");
            
        }
        catch(ClassNotFoundException e11)
            {
            JOptionPane.showMessageDialog(null,"err : chargement pilote");    
            }
        catch(SQLException e2)
            {
              JOptionPane.showMessageDialog(null,"erreur de connexion ou requete");  
            }
        
        }}
            

            
  public static void main(String[] arg)
    {
        new Ajoutemployes().setVisible(true);

}
}