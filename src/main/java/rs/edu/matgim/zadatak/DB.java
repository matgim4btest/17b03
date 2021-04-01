package rs.edu.matgim.zadatak;

import java.sql.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
public class DB {

    String connectionString = "jdbc:sqlite:src\\main\\java\\Banka.db";//ovde treba da se podesi da je path do baze sacuvane na mom kompu

    public void printKomitent() {
        try ( Connection conn = DriverManager.getConnection(connectionString);
                Statement s = conn.createStatement()) {

            ResultSet rs = s.executeQuery("SELECT * FROM Komitent");
            while (rs.next()) {
                int IdKom = rs.getInt("IdKom");
                String Naziv = rs.getString("Naziv");
                String Adresa = rs.getString("Adresa");

                System.out.println(String.format("%d\t%s\t%s", IdKom, Naziv, Adresa));
            }

        } catch (SQLException ex) {
            System.out.println("Greska prilikom povezivanja na bazu");
            System.out.println(ex);
        }}
    public float Uplate(int IdFil,int IdKom) throws Exception
    {
     try {Connection coni=DriverManager.getConnection(ConnStr);
     
    PreparedStatement st1 = coni.prepareStatement("UPDATE Racun SET Stanje=Stanje+? WHERE idrac=?");
     PreparedStatement prepstatement = coni.prepareStatement("SELECT * FROM Racun R,Komitent K WHERE R.Stanje<-R.DozvMinus AND R.IdFil=? AND R.IdKom=?");
    ResultSet rs=prepstatement.executeQuery();
    int suma=0;
     while(rs.next())
        
    {
    int br=rs.getInt("IdRac");
   int gold=rs.getInt("Stanje");
   int dozmin=rs.getInt("DozvMinus");
   int dodatak=-gold-dozmin;
   suma=suma-gold-dozmin;
   coni.setAutoCommit(false);
     st1.setInt(1,dodatak );
     st1.setInt(2,br);
     st1.execute();
     if(true) throw new Exception("Greska");
     coni.commit();//komitujemo sve prethodno apisane stejtmente kao jednu celinu 
    coni.setAutoCommit(true);
    }
    System.out.println(suma);
   coni.close();
   st1.close();
   
    }
      catch (SQLException ex) {
            System.out.println("Greska prilikom povezivanja na bazu");
            System.out.println(ex);
        }
    
    
    }
    
   public void printActiveRacun() {
   try ( Connection conn = DriverManager.getConnection(connectionString);  
           Statement s = conn.createStatement()) {

            ResultSet rs = s.executeQuery("SELECT * FROM Racun WHERE Status='A'");
            while (rs.next()) {
                int Idrac = rs.getInt("IdRac");
              int Stanje = rs.getInt("Stanje");
                 System.out.println(Idrac+"\t"+"\t"+Stanje+"\t");

            }

        } catch (SQLException ex) {
            System.out.println("Greska prilikom povezivanja na bazu");
            System.out.println(ex);
        }
   
   
   }
    

}
