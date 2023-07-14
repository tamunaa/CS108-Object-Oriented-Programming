package DataBase;
import Objects.*;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DB {
    public static List<Product> getProductList(ConnectionManager manager) throws SQLException {
        List<Product> res = new ArrayList<Product>();
        Connection conn = manager.getConnection();

        String command = "SELECT * FROM products";
        PreparedStatement statement = conn.prepareStatement(command);

        ResultSet rs = statement.executeQuery();

        while (rs.next()){
            String[] arr = new String[4];
            for (int i = 1; i <= 4; i++){
                arr[i-1] = rs.getString(i);
            }
            res.add(new Product(arr[0], arr[1], arr[2], Double.parseDouble(arr[3])));
        }

        return res;
    }
}
