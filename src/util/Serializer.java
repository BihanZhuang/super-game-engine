package util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Serializer {

    private XStream mySerializer;
    
    public Serializer() {
        mySerializer = new XStream(new DomDriver());
    }
    
    public String toXML(Object obj) {
        return mySerializer.toXML(obj);
    }
    
    public <T> T fromXML(String xml, Class<T> clazz) {
        try {
            return clazz.cast(mySerializer.fromXML(xml));
        } catch(ClassCastException e) {
            throw new VoogaException("Wrong format for %s", clazz.toString());
        }
    }
    
}
