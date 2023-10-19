package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    //Nome do usuario mysql
    private static final String USERNAME = "root";
    //Senha do usuario mysql
    private static final String PASSWORD = "admin";
    //Caminho do banco de dados
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";

    //Conexao MySQL
    public static Connection createConnectionToMySQL() throws Exception {
        //Faz com que a classe seja carregada pela JVM
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Cria a conexao com o banco de dados
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        return connection;
    }

    public static void main(String[] args) throws Exception{
        //Recuperar uma conexao com o banco de dados
        Connection con = createConnectionToMySQL();

        //Testar se a conexao e nula
        if(con != null) {
            System.out.println("Conexao obtida com sucesso!");
            con.close();
        }
    }
}
