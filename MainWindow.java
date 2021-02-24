import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JScrollPane;


public class MainWindow extends Frame implements ActionListener, ItemListener, WindowListener {
    private ArrayList<Customer> customers;
    private ArrayList<Project> projects;
    private Button bttAddLine;
    private Button bttRemoveLine;
    private Button bttSaveChanges;
    private Choice chTables;
    private CustomerTableModel cstTableModel;
    private JTable table;
    private JScrollPane scrollPane;
    private Panel panel;
    private ProjectTableModel prjTableModel;


    public MainWindow() {
        setTitle("Laboratory 6");
        setLocation(100, 100);
        setSize(800, 600);
        chTables = new Choice();
        chTables.add("Projects");
        chTables.add("Customers");
        add(chTables, BorderLayout.NORTH);
        projects = ProjectXmlReader.readFromFile("projects.xml");
        customers = CustomerXmlReader.readFromFile("customers.xml");
        prjTableModel = new ProjectTableModel(projects);
        cstTableModel = new CustomerTableModel(customers);
        table = new JTable(prjTableModel);
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        panel = new Panel();
        bttAddLine = new Button("Add line");
        bttRemoveLine = new Button("Remove line");
        bttSaveChanges = new Button("Save changes");
        panel.add(bttAddLine);
        panel.add(bttRemoveLine);
        panel.add(bttSaveChanges);
        add(panel, BorderLayout.SOUTH);
        chTables.addItemListener(this);
        bttAddLine.addActionListener(this);
        bttRemoveLine.addActionListener(this);
        bttSaveChanges.addActionListener(this);
        addWindowListener(this);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent ae) {
        if ( ae.getSource() == bttAddLine ) {
            if ( chTables.getSelectedItem().compareTo("Projects") == 0 ) {
                projects.add( new Project() );
                refreshProjectsTable();
            } else if ( chTables.getSelectedItem().compareTo("Customers") == 0 ) {
                customers.add( new Customer() );
                refreshCustomersTable();
            }
        } else if ( ae.getSource() == bttRemoveLine ) {
            if ( chTables.getSelectedItem().compareTo("Projects") == 0 ) {
                for ( int i : table.getSelectedRows() ) {
                    projects.remove(i);
                }
                refreshProjectsTable();
            } else if ( chTables.getSelectedItem().compareTo("Customers") == 0 ) {
                for ( int i : table.getSelectedRows() ) {
                    customers.remove(i);
                }
                refreshCustomersTable();
            }
        } else if ( ae.getSource() == bttSaveChanges ) {
            if ( chTables.getSelectedItem().compareTo("Projects") == 0 ) {
                ProjectXmlWriter.writeToFile("projects.xml", projects);
            } else if ( chTables.getSelectedItem().compareTo("Customers") == 0 ) {
                CustomerXmlWriter.writeToFile("customers.xml", customers);
            }
        }
    }


    public void itemStateChanged(ItemEvent e) {
        remove(scrollPane);
        if ( ( (String)e.getItem() ).compareTo("Projects") == 0 ) {
            table = new JTable(prjTableModel);
        } else if ( ( (String)e.getItem() ).compareTo("Customers") == 0 ) {
            table = new JTable(cstTableModel);
        }
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        validate();
    }


    public void refreshCustomersTable() {
        remove(scrollPane);
        cstTableModel = new CustomerTableModel(customers);
        table = new JTable(cstTableModel);
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        validate();
    }


    public void refreshProjectsTable() {
        remove(scrollPane);
        prjTableModel = new ProjectTableModel(projects);
        table = new JTable(prjTableModel);
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        validate();
    }


    public void windowActivated(WindowEvent we) {}


    public void windowClosed(WindowEvent we) {}


    public void windowClosing(WindowEvent we) {
        dispose();
    }


    public void windowDeactivated(WindowEvent we) {}


    public void windowDeiconified(WindowEvent we) {}


    public void windowIconified(WindowEvent we) {}


    public void windowOpened(WindowEvent we) {}
}
