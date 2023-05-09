import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/hw3");
        dataSource.setUsername("root");
        dataSource.setPassword("password");


        DBModel dataBaseModel = new DBModel(dataSource);
        DBView dataBaseView= new DBView(dataBaseModel);
        DBController dataBaseController = new DBController(dataBaseModel, dataBaseView);
    }
}
