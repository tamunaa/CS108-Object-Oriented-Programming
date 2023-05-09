import org.apache.commons.dbcp2.BasicDataSource;
import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.ArrayList;


public class DBModel extends AbstractTableModel {

    String[] colNames = {"Metropolis", "Continent", "Population" };

    private int rowCount = 15;
    private int colCount = 3;

    private ResultSet it;

    private ArrayList<String> arr = new ArrayList<>();

    private metropolisDAO dao;
    private void processInfo() throws SQLException {
        arr.clear();
        int counter = rowCount * colCount;

        while (it.next()){
            if (counter < 0)break;
            String val0 = it.getString(1);
            String val1 = it.getString(2);
            String val2 = it.getString(3);

            arr.add(val0);
            arr.add(val1);
            arr.add(val2);
            counter-=3;
        }
    }

    public DBModel(BasicDataSource dataSource) throws SQLException {
        dao = new metropolisDAO(dataSource);
        dao.selectAll();
        it = dao.getCurrentResultSet();

        fireTableDataChanged();
    }

    public void add(String metropolis, String continent, String population) throws SQLException {
        it = dao.getCurrentResultSet();
        dao.add(metropolis, continent, population);
        search(metropolis, continent, population, true, true);
    }


    public void search(String metropolis, String continent, String population,
                       boolean lessOrEqual, boolean exactMatch) throws SQLException {

        dao.search(metropolis, continent, population, lessOrEqual, exactMatch);
        it = dao.getCurrentResultSet();
        processInfo();
        fireTableDataChanged();
    }


    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return colCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (colCount * rowIndex + columnIndex >= arr.size())return null;
        Object result = arr.get(colCount * rowIndex + columnIndex);
        return result;
    }


    @Override
    public String getColumnName(int colInd){
        return colNames[colInd];
    }
}
