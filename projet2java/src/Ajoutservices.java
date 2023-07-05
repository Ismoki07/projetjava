import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ajoutservices extends JFrame implements ActionListener {
    Container con=null;
    JLabel l1 = new JLabel("Code service");
    JTextField t1 = new JTextField(5);
    JLabel l2 = new JLabel("libelle");
    JTextField t2 = new JTextField(15);
    JButton b1 = new JButton("Insertion");
    JButton b2 = new JButton("Retour");

    
    public Ajoutservices()
    {
        con=getContentPane();
        setTitle("Ajout service");
        con.setLayout(new FlowLayout());
        setSize(new Dimension(600,200));
        setDefaultCloseOperation(EXIT_ON_CLOSE);     
        setLocationRelativeTo(null);


        con.add(l1);
        con.add(t1);
        con.add(l2);
        con.add(t2);
        con.add(b1);
        con.add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);

        
    }
    
    
    
    public void actionPerformed(ActionEvent ee)
    {
        if (ee.getSource()==b2)
        {
            menu r= new menu();
            r.setVisible(true);
            dispose();
        }
        else{
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection co=null;
            co=DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava","root","");
            Statement s1 = co.createStatement();
            Statement s2 =co.createStatement();
            String req2="insert into service values("+t1.getText()+",'"+t2.getText()+"')";
            String req="select * from service where code_service="+t1.getText()+"";
            ResultSet res=s1.executeQuery(req);           
            int existe=0;
            while(res.next())
            {
                existe=1;
            }
            if (existe==1)
            {
                JOptionPane.showMessageDialog(null,"Service Existe Déjà!");
            }
            {
             int ress= s2.executeUpdate(req2);
             JOptionPane.showMessageDialog(null,"Insertion Service Réussie");
            t1.setText("");
            t2.setText("");
            }
            
        }
        catch(ClassNotFoundException e1)
        {
            JOptionPane.showMessageDialog(null,"err de pilote");
        }
      catch(SQLException e2)
    {
        JOptionPane.showMessageDialog(null,"err de requete ou connexion");
        
    }
        }
    
}
    
    public static void main(String[] arg)
    {
        new Ajoutservices().setVisible(true);
    }
    
}

    

