import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class modifservices extends JFrame implements  ActionListener
{
     Container con=null;
    JLabel l1 = new JLabel("Code service");
    JTextField t1 = new JTextField(10);
    JLabel l2 = new JLabel("Libelle");
    JComboBox cob = new JComboBox();
    JButton b1 = new JButton("Modifier");
    JButton b2 = new JButton("Annuler");
    
   
    public modifservices()
    {
        con=getContentPane();
        setTitle("Modifier service");
        con.setLayout(new FlowLayout());
        setSize(new Dimension(900,400));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        con.add(l2);
        con.add(cob);
        
        con.add(l1);
        con.add(t1);
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
            String req="select * from service";
            ResultSet res=s1.executeQuery(req);
            while(res.next())
        {
                cob.addItem(res.getString("libelle"));
        }
        }
            
        catch(ClassNotFoundException e11)
        {
            JOptionPane.showMessageDialog(null,"error: chargement de pilote");
        }
        catch(SQLException e2)
        {
            JOptionPane.showMessageDialog(null, "error:erreur de connexion ou de requete ");
        }
         cob.setSelectedIndex(-1);
         t1.setText("");
 
    }
    
    public void actionPerformed(ActionEvent e1)
    {
        
       if(e1.getSource()==b2)
       {
           menu r =new menu();
           r.setVisible(true);
           dispose();
       }
        else if(e1.getSource()==cob){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection co = null ;
                co=DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava","root","");     
                Statement s1 = co.createStatement();
                String req1 ="select * from service where libelle = '"+cob.getSelectedItem()+"' ";
                ResultSet res1 = s1.executeQuery(req1);
                
                while(res1.next()){
                    
                   t1.setText(res1.getString("code_service"));
                }

                  
                
            }catch(ClassNotFoundException ee1){
               JOptionPane.showMessageDialog(null,"err:chargement de pilote");
                
            }catch(SQLException  ee2){
                 JOptionPane.showMessageDialog(null,"err: erreur de connection ou requette");
            }
          }
          
          else if(e1.getSource()==b1)
          {
             try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection co = null ;
                co=DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava","root","");     
                Statement s = co.createStatement();
                String req ="update service set code_service="+t1.getText()+" where libelle='"+cob.getSelectedItem()+"' ";
                int res = s.executeUpdate(req);
                JOptionPane.showMessageDialog(null,"Modification avec succées !!!");
                cob.setSelectedIndex(-1);
                t1.setText("");
                
            }catch(ClassNotFoundException ee1){
               JOptionPane.showMessageDialog(null,"err:chargement de pilote");
                
            }catch(SQLException  ee2){
                 JOptionPane.showMessageDialog(null,"err: erreur de connection ou requette");
            }
                   
        }    
        
    }
    public static void main(String[]arg)
    {
        new modifservices().setVisible(true);
    }
}

