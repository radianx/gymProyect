package gimnasio.controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Family
 */
import java.io.File;
import java.net.URL;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDatos{

    private Process proceso;
    private ProcessBuilder constructor;

    private final String host = "localhost";
    private final String puerto = "5432";
    private final String usuario = "postgres";
    private final String clave = "postgres";
    private final String bd = "gimnasio";
    private final String formato = "custom";

    public boolean BD_backup(String path) {
        boolean hecho = false;
        try {
            File pgdump = new File("C:/Program Files/PostgreSQL/11/bin\\pg_dump.exe");
            if (pgdump.exists()) {
                if (!path.equalsIgnoreCase("")) {
                    constructor = new ProcessBuilder("C:/Program Files/PostgreSQL/11/bin\\pg_dump.exe", "--verbose", "--format", formato, "-f", path);
                } else {
                    constructor = new ProcessBuilder("C:/Program Files/PostgreSQL/11/bin\\pg_dump.exe", "--verbose", "--inserts", "--column-inserts", "-f", path);
                    System.out.println("ERROR");
                }
                constructor.environment().put("PGHOST", host);
                constructor.environment().put("PGPORT", puerto);
                constructor.environment().put("PGUSER", usuario);
                constructor.environment().put("PGPASSWORD", clave);
                constructor.environment().put("PGDATABASE", bd);
                constructor.redirectErrorStream(true);
                proceso = constructor.start();
                escribirProcess(proceso.toString());
                System.out.println("terminado backup " + path);
                hecho = true;
            } else {
                if (!path.equalsIgnoreCase("")) {
                    constructor = new ProcessBuilder("/opt/PostgreSQL/11/bin/pg_dump", "--verbose", "--format", formato, "-f", path);
                } else {
                    constructor = new ProcessBuilder("/opt/PostgreSQL/11/bin/pg_dump", "--verbose", "--inserts", "--column-inserts", "-f", path);
                    System.out.println("ERROR");
                }
                constructor.environment().put("PGHOST", host);
                constructor.environment().put("PGPORT", puerto);
                constructor.environment().put("PGUSER", usuario);
                constructor.environment().put("PGPASSWORD", clave);
                constructor.environment().put("PGDATABASE", bd);
                constructor.redirectErrorStream(true);
                proceso = constructor.start();
                escribirProcess(proceso.toString());
                System.out.println("terminado backup " + path);
                hecho = true;
            }
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage() + "Error de backup");
            hecho = false;
        }
        return hecho;
    }

    public boolean restaurarBackup(String path) {
        boolean hecho = false;
        try {
            File pgrestore = new File("C:/Program Files/PostgreSQL/11/bin\\pg_restore.exe");
            URL f = getClass().getClassLoader().getResource(path);
            System.out.println(f);
            if (pgrestore.exists()) {
                constructor = new ProcessBuilder("C:/Program Files/PostgreSQL/11/bin\\pg_restore.exe", "-i", "-h", host, "-p", puerto, "-U", usuario, "-d", bd, "-v", path);
                constructor.environment().put("PGPASSWORD", clave);
                constructor.redirectErrorStream(true);
                proceso = constructor.start();
                escribirProcess(proceso.toString() +" pgRestore existe");
                hecho = true;
            } else {
                constructor = new ProcessBuilder("/opt/PostgreSQL/11/bin/pg_restore", "-i", "-h", host, "-p", puerto, "-U", usuario, "-d", bd, "-v", path);
                constructor.environment().put("PGPASSWORD", clave);
                constructor.redirectErrorStream(true);
                proceso = constructor.start();
                escribirProcess(proceso.toString() + "pgRestore no existe ");
                hecho = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            hecho = false;
        }
        return hecho;
    }
    
    public void restaurarDataBase() throws SQLException{
        Connection c = DriverManager.getConnection("jdbc:postgresql://"+host+":"+puerto+"/", usuario, clave);
        Statement statement = c.createStatement(); 
        statement.execute("DROP DATABASE gimnasio");
        statement.execute("CREATE DATABASE gimnasio");
        restaurarBackup("/backup/gimnasio.backup");
    }
    
    private void escribirProcess(String proceso) {
        System.err.println(proceso);
    }
    
    
    
}
