import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;


public class CustomerXmlWriter {
    public static void writeToFile(String fileName, ArrayList<Customer> customers) {
        try {
            OutputStream stream = new FileOutputStream(fileName);
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = factory.createXMLStreamWriter(stream);
            writer.writeStartElement("?xml version=\"1.0\" encoding=\"UTF-8\"?");
            writer.writeCharacters("\n");
            writer.writeStartElement("!DOCTYPE customers SYSTEM \"customers.dtd\"");
            writer.writeCharacters("\n");
            writer.writeStartElement("customers");
            writer.writeCharacters("\n");
            for (int i = 0; i != customers.size(); i++) {
                writer.writeCharacters("\t");
                writer.writeStartElement("customer");
                writer.writeAttribute( "id", customers.get(i).getId().toString() );
                writer.writeCharacters("\n");
                writer.writeCharacters("\t\t");
                writer.writeStartElement("name");
                writer.writeCharacters( customers.get(i).getName() );
                writer.writeEndElement();
                writer.writeCharacters("\n");
                writer.writeCharacters("\t\t");
                writer.writeStartElement("juridical-adress");
                writer.writeCharacters( customers.get(i).getJuridicalAdress() );
                writer.writeEndElement();
                writer.writeCharacters("\n");
                writer.writeCharacters("\t\t");
                writer.writeStartElement("projects");
                writer.writeCharacters( customers.get(i).getProjects().toString() );
                writer.writeEndElement();
                writer.writeCharacters("\n");
                writer.writeCharacters("\t\t");
                writer.writeStartElement("completed-projects");
                writer.writeCharacters( customers.get(i).getCompletedProjects().toString() );
                writer.writeEndElement();
                writer.writeCharacters("\n");
                writer.writeCharacters("\t");
                writer.writeEndElement();
                writer.writeCharacters("\n");
            }
            writer.writeEndElement();
            writer.writeCharacters("\n");
            writer.writeCharacters("\n");
            writer.flush();
            writer.close();
        } catch (FileNotFoundException | XMLStreamException e) {
            System.out.println("ERROR: unable to write file");
        }
    }
}
