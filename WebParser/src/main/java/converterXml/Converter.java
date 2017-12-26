package converterXml;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import model.Offer;

public class Converter {

	JAXBContext contextObj;

	public void convertToXML(Offer offers) {

		try {
			contextObj = JAXBContext.newInstance(Offer.class);
			Marshaller marshallerObj = contextObj.createMarshaller();
			marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshallerObj.marshal(offers, new FileOutputStream("Product1.xml"));
		} catch (JAXBException | FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
