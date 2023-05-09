import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DBController implements ActionListener{

    private DBView view;
    private DBModel model;

    public DBController(DBModel model, DBView view){
        this.view = view;
        this.model = model;

        view.add.addActionListener(this);
        view.search.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(view.add)){
            try {
                model.add(view.metropolisField.getText(), view.continentField.getText(), view.populationField.getText());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        if(e.getSource().equals(view.search)){
            boolean largerPopulation = view.populationBox.getSelectedItem().equals("Population larger than");
            boolean exatMatch = view.matchBox.getSelectedItem().equals("Exact match");
            String metropolises = view.metropolisField.getText();
            String continent = view.continentField.getText();
            String population = view.populationField.getText();

            try {
                model.search(metropolises, continent , population, !largerPopulation, exatMatch);
            } catch (SQLException ex) {
                System.out.println("something is wrong");
            }
        }
    }
}