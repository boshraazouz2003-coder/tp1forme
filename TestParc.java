import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
class ParcManager {
    private String url = "jdbc:mysql://localhost:3306/db_parc";
    private String user = "root";
    private String password = "";
    private Connection cnn;
    private Statement st;

    public void connecter() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Chargement du pilote 
        cnn = DriverManager.getConnection(url, user, password); // Établissement de la connexion 
        st = cnn.createStatement();
    }

    // Insertion d'un véhicule (UPDATE) 
    public void ajouterVoiture(String matricule, String marque, double prix, boolean clim) throws Exception {
        connecter();
        String sql = "INSERT INTO Table_Vehicules VALUES ('" + matricule + "', '" + marque + "', " + prix + ", " + (clim ? 1 : 0) + ")";
        st.executeUpdate(sql);
        System.out.println("Véhicule ajouté avec succès.");
        fermer();
    }

    // Lecture du parc (SELECT) 
    public void listerParc() throws Exception {
        connecter();
        ResultSet res = st.executeQuery("SELECT * FROM Table_Vehicules");
        System.out.println("MATRICULE \t MARQUE \t PRIX");
        while (res.next()) {
            System.out.println(res.getString(1) + "\t" + res.getString(2) + "\t" + res.getDouble(3));
        }
        fermer();
    }
     public void calculerMoyennePrix() throws Exception {
        connecter();
        ResultSet res = st.executeQuery("SELECT AVG(prixBase) FROM Table_Vehicules");

        if (res.next()) {
            double moyenne = res.getDouble(1);
            System.out.println("Moyenne des prix des véhicules : " + moyenne);
        }

        fermer();
    }

public void filtreMarque(String marque) throws Exception {
    connecter();

    String sql = "SELECT * FROM Table_Vehicules WHERE marque = '" + marque + "'";
    ResultSet res = st.executeQuery(sql);

    System.out.println("MATRICULE \t MARQUE \t PRIX");
    while (res.next()) {
        System.out.println(
            res.getString(1) + "\t" +
            res.getString(2) + "\t" +
            res.getDouble(3)
        );
    }

    fermer();
}
private void fermer() throws Exception {
        if (st != null) st.close();
        if (cnn != null) cnn.close(); // Libération des ressources
    }
}
public class TestParc {
   public static void main(String[] args) {
       ParcManager gestionnaire = new ParcManager();
       try {
           System.out.println("--- Test du Parc Automobile ---");
           // Ajout d'un nouveau véhicule
           gestionnaire.ajouterVoiture("901199TN99", "BMW", 150000, true);
          
           // Affichage de la liste
           gestionnaire.listerParc();
           gestionnaire.calculerMoyennePrix();
           gestionnaire.filtreMarque("BMW");
       } catch (Exception e) {
           System.out.println("Erreur : " + e.getMessage());
       }
   }
}
