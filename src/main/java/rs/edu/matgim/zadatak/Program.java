package rs.edu.matgim.zadatak;
import java.util.Scanner;
public class Program {

    public static void main(String[] args) {

        DB db = new DB();
        db.printKomitent();
        Scanner sc= new Scanner(System.in);
        int br=sc.nextInt();
        int br1=sc.nextInt();
        db.Uplate(br, br1);
    }
}
