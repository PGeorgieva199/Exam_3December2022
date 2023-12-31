package softuni.exam.util;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface XmlParse {
    @SuppressWarnings("unchecked")
    <T> T fromFile(String filePath, Class<T> tClass) throws JAXBException, FileNotFoundException, JAXBException, FileNotFoundException;
}
