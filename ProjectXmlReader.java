import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;


public class ProjectXmlReader {
    public static ArrayList<Project> readFromFile(String fileName) {
        ArrayList<Project> projects = new ArrayList<Project>();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            InputStream stream = new FileInputStream(fileName);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(stream);
            Project project = null;
            int elementType;
            String tagName;
            while ( reader.hasNext() ) {
                elementType = reader.next();
                if (elementType == XMLStreamReader.START_ELEMENT) {
                    tagName = reader.getLocalName();
                    if ( tagName.compareTo("project") == 0 ) {
                        project = new Project();
                        String id = reader.getAttributeValue(null, "id");
                        project.setId( Integer.parseInt(id) );
                    } else if ( tagName.compareTo("customer") == 0 ) {
                        project.setCustomer( reader.getElementText() );
                    } else if ( tagName.compareTo("name") == 0 ) {
                        project.setName( reader.getElementText() );
                    } else if ( tagName.compareTo("start") == 0 ) {
                        try {
                            project.setStart( format.parse( reader.getElementText() ) );
                        } catch (ParseException e) {
                            System.out.println("ERROR: invalid date");
                        }
                    } else if ( tagName.compareTo("plan-finish") == 0 ) {
                        try {
                            project.setPlanFinish( format.parse( reader.getElementText() ) );
                        } catch (ParseException e) {
                            System.out.println("ERROR: invalid date");
                        }
                    } else if ( tagName.compareTo("finish") == 0 ) {
                        try {
                            project.setFinish( format.parse( reader.getElementText() ) );
                        } catch (ParseException e) {
                            System.out.println("ERROR: invalid date");
                        }
                    } else if ( tagName.compareTo("manager") == 0 ) {
                        project.setManager( reader.getElementText() );
                    } else if ( tagName.compareTo("is-completed") == 0 ) {
                        project.setIsCompleted( Boolean.parseBoolean( reader.getElementText() ) );
                    }
                } else if (elementType == XMLStreamReader.END_ELEMENT) {
                    tagName = reader.getLocalName();
                    if ( tagName.compareTo("project") == 0 ) {
                        projects.add(project);
                    }
                }
            }
            reader.close();
        } catch (FileNotFoundException | XMLStreamException e) {
            System.out.println("ERROR: unable to read file");
        }
        return projects;
    }
}
