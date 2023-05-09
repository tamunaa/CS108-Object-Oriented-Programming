
import junit.framework.TestCase;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Test;

import java.security.PublicKey;
import java.sql.*;

public class DBTest extends TestCase {
    private metropolisDAO dataBase;
    private Connection connection;

    public void setUp() throws SQLException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/mock_scheme");
        dataSource.setUsername("root");
        dataSource.setPassword("password");

        dataBase = new metropolisDAO(dataSource);
        connection = dataSource.getConnection();
        connection.setAutoCommit(false);
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback();
        connection.close();
    }



    public void testSize() throws SQLException {
        Statement executable = connection.createStatement();
        String exec = "DELETE FROM metropolises;\n";

        executable.executeUpdate(exec);

        String str = "SELECT * FROM metropolises\n";
        ResultSet st = executable.executeQuery(str);

        int len = 0;
        while (st.next()){
            len++;
        }

        assert (len == 0);
    }


    @Test
    public void testAddMetropolis() throws SQLException {
        String metropolis = "Test Metropolis";
        String continent = "Test Continent";
        String population = "1000000";

        dataBase.add(metropolis, continent, population);

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM metropolises WHERE metropolis = ?");
        statement.setString(1, metropolis);
        ResultSet resultSet = statement.executeQuery();

        assertTrue(resultSet.next());
        assertEquals(continent, resultSet.getString("continent"));
        assertEquals(population, resultSet.getString("population"));
    }

    @Test
    public void testGetMetropolis() throws SQLException {
        String metropolis = "London";
        String continent = "Europe";
        String population = "8308369";

        dataBase.add(metropolis, continent, population);
        dataBase.search(metropolis, continent, population, true, true);
        ResultSet it = dataBase.getCurrentResultSet();

        assert (it.next());

        assertEquals(metropolis, it.getObject(1));
        assertEquals(continent, it.getObject(2));
        assertEquals(population, it.getObject(3).toString());

        dataBase.search(metropolis, continent, population, false, true);
        it = dataBase.getCurrentResultSet();
        assert (!it.next());
    }

    public void testSearch1() throws SQLException {
        String metropolis = "London";
        String continent = "Europe";
        String population = "8308369";

        dataBase.search(metropolis, continent, population, false, true);
        ResultSet it = dataBase.getCurrentResultSet();
        assert (!it.next());
    }

    public void testSearch2() throws SQLException {
        String metropolis = "London";
        String continent = "Europe";
        String population = "8308369";

        dataBase.search(metropolis, continent, population, false, false);
        ResultSet it = dataBase.getCurrentResultSet();
        assert (!it.next());
    }

    public void testSearch3() throws SQLException {
        String metropolis = "nothing";

        dataBase.search(metropolis, "", "", false, true);
        ResultSet it = dataBase.getCurrentResultSet();
        assert (!it.next());
    }

    public void testSearch4() throws SQLException {
        String metropolis = "nothing";

        dataBase.search(metropolis, "", "", false, false);
        ResultSet it = dataBase.getCurrentResultSet();
        assert (!it.next());
    }

    public void testSearch5() throws SQLException {
        dataBase.search("", "", "", true, false);
        ResultSet it = dataBase.getCurrentResultSet();
        assert (it.next());
    }

    public void testSearch6() throws SQLException {
        ResultSet it = dataBase.getResuletSet();
        assert (it.next());
    }

    public void testSearch7() throws SQLException {
        dataBase.selectAll();
        ResultSet it = dataBase.getCurrentResultSet();
        assert (it.next());
    }

}
