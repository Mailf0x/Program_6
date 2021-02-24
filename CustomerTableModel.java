import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class CustomerTableModel implements TableModel {
    private ArrayList<Customer> customers;


    public CustomerTableModel(ArrayList<Customer> customers) {
        this.customers = customers;
    }


    @Override
    public int getColumnCount() {
        return 5;
    }


    @Override
    public String getColumnName(int index) {
        switch(index) {
            case 0: return "ID";
            case 1: return "Name";
            case 2: return "Juridical adress";
            case 3: return "Projects";
            case 4: return "Completed projects";
        }
        return null;
    }


    @Override
    public Class<?> getColumnClass(int index) {
        switch(index) {
            case 0: return Integer.class;
            case 1:
            case 2: return String.class;
            case 3:
            case 4: return Integer.class;
        }
        return null;
    }


    @Override
    public int getRowCount() {
        return customers.size();
    }


    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        switch(colIndex) {
            case 0: return customers.get(rowIndex).getId();
            case 1: return customers.get(rowIndex).getName();
            case 2: return customers.get(rowIndex).getJuridicalAdress();
            case 3: return customers.get(rowIndex).getProjects();
            case 4: return customers.get(rowIndex).getCompletedProjects();
        }
        return null;
    }


    @Override
    public void setValueAt(Object value, int rowIndex, int colIndex) {
        if (colIndex == 0) {
            customers.get(rowIndex).setId( (Integer)value );
        } else if (colIndex == 1) {
            customers.get(rowIndex).setName( (String)value );
        } else if (colIndex == 2) {
            customers.get(rowIndex).setJuridicalAdress( (String)value );
        } else if (colIndex == 3) {
            customers.get(rowIndex).setProjects( (Integer)value );
        } else if (colIndex == 4) {
            customers.get(rowIndex).setCompletedProjects( (Integer)value );
        }
    }


    @Override
    public boolean isCellEditable(int rowIndex, int colIndex) {
        switch(colIndex) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: return true;
        }
        return false;
    }


    @Override
    public void addTableModelListener(TableModelListener listener) {}


    @Override
    public void removeTableModelListener(TableModelListener listener) {}


    public ArrayList<Customer> getCustomers(int[] indexes) {
        ArrayList<Customer> result = new ArrayList<Customer>();
        for(int index : indexes) {
            result.add( customers.get(index) );
        }
        return result;
    }
}
