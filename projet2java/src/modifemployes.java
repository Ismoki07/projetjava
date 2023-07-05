
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
public class modifemployes extends JFrame implements ActionListener 
{
    Container con;
    JLabel l1= new JLabel("Code Service");
    JTextField t1= new JTextField(5);
    JLabel l2= new JLabel("Matricule");
    JLabel l3=new JLabel("Nom");
    JTextField t3= new JTextField(15);
    JLabel l4= new JLabel("Salaire");
    JTextField t4= new JTextField(10);
    JComboBox cob=new JComboBox();
    
    JButton b1=new JButton("Modifer");
    JButton b2=new JButton("Annuler");
    
    public modifemployes()
    { 
        con=getContentPane();
        setTitle("Modification employes");
        con.setLayout(new FlowLayout());
        setSize(new Dimension(900,400));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        con.add(l2);
        con.add(cob);
        con.add(l1);
        con.add(t1);
        con.add(l3);
        con.add(t3);
        con.add(l4);
        con.add(t4);
        con.add(b1);
        con.add(b2);
        
        
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        cob.addActionListener(this);
        
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection co=null;
            co=DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava","root","");
            Statement s1 = co.createStatement();
            String req="select * from employe ";
            ResultSet res=s1.executeQuery(req);
            while(res.next())
            {
                cob.addItem(res.getInt("matricule"));
                
            }
        }
        catch(ClassNotFoundException e11)
        {
            JOptionPane.showMessageDialog(null,"error: chargement de pilote");
        }
        catch(SQLException e1)
        {
            JOptionPane.showMessageDialog(null, "error:erreur de connexion ou de requete ");
        }
        cob.setSelectedIndex(-1);
          t1.setText("");
          t3.setText("");
          t4.setText("");
           
       }
    public void actionPerformed(ActionEvent e1)
    {
        if (e1.getSource()==b2)
        {
            menu r = new menu();
            r.setVisible(true);
            dispose();
        }
                 
        
        else if  (e1.getSource()==b1)
        {
                    
         try
            { Class.forName("com.mysql.cj.jdbc.Driver");
                Connection co;
                co=DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava","root",""); 
                Statement s1 =co.createStatement();
                String req1="update employe set nom='"+t3.getText()+"',salaire="+t4.getText()+",code_service="+t1.getText()+" where matricule="+cob.getSelectedItem()+" ";
                int res=s1.executeUpdate(req1);
                JOptionPane.showMessageDialog(null,"Modificaton réussie ");  
                
              
            
            }
            catch(ClassNotFoundException e11)
            {
            JOptionPane.showMessageDialog(null,"err : chargement pilote");    
            }
            catch(SQLException e2)
            {
              JOptionPane.showMessageDialog(null,"erreur de conn ou requete");  
            }
            cob.setSelectedIndex(-1);
          t1.setText("");
          t3.setText("");
          t4.setText("");
         
            
        }
  
   
        else
        {
            //pour le choix de com
            
            
            try
            {
             //chargement pilote
                Class.forName("com.mysql.cj.jdbc.Driver");
                //url
                Connection co;
                co=DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava","root","");
                //espace memoire piur execution de la requete
                Statement s1 = co.createStatement();
                //declaration de la requete
                String req="select * from  employe  where matricule="+cob.getSelectedItem();
                //execution de la requete
    
                ResultSet res=s1.executeQuery(req);
                
                
                
                while(res.next())
                {
                    t3.setText(res.getString("nom"));
                    t4.setText(res.getString("salaire"));
                    t1.setText(res.getString("code_service"));
                }
                

            }
            catch(ClassNotFoundException e11)
            {
            JOptionPane.showMessageDialog(null,"err : chargement pilote");    
            }
            catch(SQLException e2)
            {
              JOptionPane.showMessageDialog(null,"erreur de conn ou requete");  
            }
            
   }        
            
        
    }   
    public static void main(String[]arg)
    {
        new modifemployes().setVisible(true);
    }
}
