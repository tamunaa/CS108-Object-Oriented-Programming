import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.*;

public class metropolisDAO {
    private BasicDataSource dataSource;
    private Connection connection;
    private ResultSet it;

    public metropolisDAO(BasicDataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        this.connection = dataSource.getConnection();
    }

    public void add(String metropolis, String continent, String population) throws SQLException {
        if (metropolis.length() == 0 || continent.length() == 0 || population.length() == 0)return;

        Connection connection = dataSource.getConnection();
        String insertionCommand = "insert into metropolises VALUES(?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(insertionCommand);

        statement.setString(1, metropolis);
        statement.setString(2, continent);
        statement.setLong(3, Long.parseLong(population));

        statement.executeUpdate();
        search(metropolis, continent, population, true, true);
    }

    public void selectAll() throws SQLException {
        Statement executable = connection.createStatement();
        it = executable.executeQuery("SELECT * FROM metropolises");
    }
    public void search(String metropolis, String continent, String population,
                       boolean lessOrEqual, boolean exactMatch) throws SQLException {
        Statement executable = connection.createStatement();

        boolean flag = false;

        if (metropolis.isEmpty() && continent.isEmpty() && population.isEmpty()){
            it = executable.executeQuery("SELECT * FROM metropolises");
        }

        String exec = "select * from metropolises\n";

        if (metropolis.length() != 0){
            //where ...
            // and
            if (flag){
                exec += "and ";
            }else {
                exec += "where \n";
                flag = true;
            }

            if (exactMatch) {
                exec += "metropolises.metropolis = \"" + metropolis + "\"\n";
            }else {
                exec += "metropolises.metropolis like \"" + metropolis + "%\"\n";
            }
        }

        if (continent.length() != 0){
            if (flag){
                exec += "and ";
            }else {
                exec += "where \n";
                flag = true;
            }
            if (exactMatch){
                exec += "metropolises.continent = \"" + continent + "\"\n";
            }else {
                exec += "metropolises.continent like \"" + continent + "%\"\n";
            }
        }

        if (population.length() != 0){
            if (flag){
                exec += "and ";
            }else {
                exec += "where \n";
                flag = true;
            }
            if (lessOrEqual){
                exec += "metropolises.population <= " + population + "\n";
            }else {
                exec += "metropolises.population > " + population + "\n";
            }
        }

        exec += ';';

        it = executable.executeQuery(exec);
    }

    public ResultSet getCurrentResultSet(){
        return it;
    }

    public ResultSet getResuletSet() throws SQLException {
        Statement executable = connection.createStatement();
        ResultSet result = executable.executeQuery("SELECT * FROM metropolises");
        return result;
    }
}
