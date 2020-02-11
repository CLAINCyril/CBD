import DAO.Connexion;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Connexion conn = new Connexion();
        conn.connect();
        conn.close();
    }
}
