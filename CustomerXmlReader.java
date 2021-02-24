import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;


public class CustomerXmlReader {
    public static ArrayList<Customer> readFromFile(String fileName) {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        try {
            InputStream stream = new FileInputStream(fileName);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(stream);
            Customer customer = null;
            int elementType;
            String tagName;
            while ( reader.hasNext() ) {
                elementType = reader.next();
                if (elementType == XMLStreamReader.START_ELEMENT) {
                    tagName = reader.getLocalName();
                    if ( tagName.compareTo("customer") == 0 ) {
                        customer = new Customer();
                        String id = reader.getAttributeValue(null, "id");
                        customer.setId( Integer.parseInt(id) );
                    } else if ( tagName.compareTo("name") == 0 ) {
                        customer.setName( reader.getElementText() );
                    } else if ( tagName.compareTo("juridical-adress") == 0 ) {
                        customer.setJuridicalAdress( reader.getElementText() );
                    } else if ( tagName.compareTo("projects") == 0 ) {
                        customer.setProjects( Integer.parseInt( reader.getElementText() ) );
                    } else if ( tagName.compareTo("completed-projects") == 0 ) {
                        customer.setCompletedProjects( Integer.parseInt( reader.getElementText() ) );
                    }
                } else if (elementType == XMLStreamReader.END_ELEMENT) {
                    tagName = reader.getLocalName();
                    if ( tagName.compareTo("customer") == 0 ) {
                        customers.add(customer);
                    }
                }
            }
            reader.close();
        } catch (FileNotFoundException | XMLStreamException e) {
            System.out.println("ERROR: unable to read file");
        }
        return customers;
    }
}
