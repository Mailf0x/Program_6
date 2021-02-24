import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;


public class ProjectXmlWriter {
    public static void writeToFile(String fileName, ArrayList<Project> projects) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            OutputStream stream = new FileOutputStream(fileName);
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = factory.createXMLStreamWriter(stream);
            writer.writeStartElement("?xml version=\"1.0\" encoding=\"UTF-8\"?");
            writer.writeCharacters("\n");
            writer.writeStartElement("!DOCTYPE projects SYSTEM \"projects.dtd\"");
            writer.writeCharacters("\n");
            writer.writeStartElement("projects");
            writer.writeCharacters("\n");
            for (int i = 0; i != projects.size(); i++) {
                writer.writeCharacters("\t");
                writer.writeStartElement("project");
                writer.writeAttribute( "id", projects.get(i).getId().toString() );
                writer.writeCharacters("\n");
                writer.writeCharacters("\t\t");
                writer.writeStartElement("customer");
                writer.writeCharacters( projects.get(i).getCustomer() );
                writer.writeEndElement();
                writer.writeCharacters("\n");
                writer.writeCharacters("\t\t");
                writer.writeStartElement("name");
                writer.writeCharacters( projects.get(i).getName() );
                writer.writeEndElement();
                writer.writeCharacters("\n");
                writer.writeCharacters("\t\t");
                writer.writeStartElement("start");
                writer.writeCharacters( format.format( projects.get(i).getStart() ) );
                writer.writeEndElement();
                writer.writeCharacters("\n");
                writer.writeCharacters("\t\t");
                writer.writeStartElement("plan-finish");
                writer.writeCharacters( format.format( projects.get(i).getPlanFinish() ) );
                writer.writeEndElement();
                writer.writeCharacters("\n");
                writer.writeCharacters("\t\t");
                writer.writeStartElement("finish");
                writer.writeCharacters( format.format( projects.get(i).getFinish() ) );
                writer.writeEndElement();
                writer.writeCharacters("\n");
                writer.writeCharacters("\t\t");
                writer.writeStartElement("manager");
                writer.writeCharacters( projects.get(i).getManager() );
                writer.writeEndElement();
                writer.writeCharacters("\n");
                writer.writeCharacters("\t\t");
                writer.writeStartElement("is-completed");
                writer.writeCharacters( projects.get(i).getIsCompleted().toString() );
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
