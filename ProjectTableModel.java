import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class ProjectTableModel implements TableModel {
    private ArrayList<Project> projects;
    private SimpleDateFormat format;


    public ProjectTableModel(ArrayList<Project> projects) {
        this.projects = projects;
        format = new SimpleDateFormat("dd.MM.yyyy");
    }


    @Override
    public int getColumnCount() {
        return 8;
    }


    @Override
    public String getColumnName(int index) {
        switch(index) {
            case 0: return "ID";
            case 1: return "Customer";
            case 2: return "Name";
            case 3: return "Start";
            case 4: return "Plan finish";
            case 5: return "Finish";
            case 6: return "Manager";
            case 7: return "Is completed";
        }
        return null;
    }


    @Override
    public Class<?> getColumnClass(int index) {
        switch(index) {
            case 0: return Integer.class;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6: return String.class;
            case 7: return Boolean.class;
        }
        return null;
    }


    @Override
    public int getRowCount() {
        return projects.size();
    }


    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        switch(colIndex) {
            case 0: return projects.get(rowIndex).getId();
            case 1: return projects.get(rowIndex).getCustomer();
            case 2: return projects.get(rowIndex).getName();
            case 3:
                if (projects.get(rowIndex).getStart() != null) {
                    return format.format( projects.get(rowIndex).getStart() );
                } else {
                    return "unknown";
                }
            case 4:
                if (projects.get(rowIndex).getPlanFinish() != null) {
                    return format.format( projects.get(rowIndex).getPlanFinish() );
                } else {
                    return "unknown";
                }
            case 5:
                if (projects.get(rowIndex).getFinish() != null) {
                    return format.format( projects.get(rowIndex).getFinish() );
                } else {
                    return "unknown";
                }
            case 6: return projects.get(rowIndex).getManager();
            case 7: return projects.get(rowIndex).getIsCompleted();
        }
        return null;
    }


    @Override
    public void setValueAt(Object value, int rowIndex, int colIndex) {
        if (colIndex == 0) {
            projects.get(rowIndex).setId( (Integer)value );
        } else if (colIndex == 1) {
            projects.get(rowIndex).setCustomer( (String)value );
        } else if (colIndex == 2) {
            projects.get(rowIndex).setName( (String)value );
        } else if (colIndex == 3) {
            try {
                projects.get(rowIndex).setStart( format.parse( (String)value ) );
            } catch (ParseException e) {
                System.out.println("ERROR: invalid date");
            }
        } else if (colIndex == 4) {
            try {
                projects.get(rowIndex).setPlanFinish( format.parse( (String)value ) );
            } catch (ParseException e) {
                System.out.println("ERROR: invalid date");
            }
        } else if (colIndex == 5) {
            try {
                projects.get(rowIndex).setFinish( format.parse( (String)value ) );
            } catch (ParseException e) {
                System.out.println("ERROR: invalid date");
            }
        } else if (colIndex == 6) {
            projects.get(rowIndex).setManager( (String)value );
        } else if (colIndex == 7) {
            projects.get(rowIndex).setIsCompleted( Boolean.parseBoolean( (String)value ) );
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
            case 5:
            case 6:
            case 7: return true;
        }
        return false;
    }


    @Override
    public void addTableModelListener(TableModelListener listener) {}


    @Override
    public void removeTableModelListener(TableModelListener listener) {}


    public ArrayList<Project> getProjects(int[] indexes) {
        ArrayList<Project> result = new ArrayList<Project>();
        for(int index : indexes) {
            result.add( projects.get(index) );
        }
        return result;
    }
}
