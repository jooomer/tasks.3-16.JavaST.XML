import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * 
 */

/**
 * @author Sergey
 *
 */
public class MyStAXParser {
	
	private Device device;
	private Component component;
	private String content;
	
	public void parse(String xmlFile) {
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = null;
		InputStream is = null;

		try {
			is = new FileInputStream(new File(xmlFile));
			reader = factory.createXMLStreamReader(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
		try {
			while (reader.hasNext()) {
				int event = reader.next();
				initObjects(event, reader);
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param event
	 */
	private void initObjects(int event, XMLStreamReader reader) {
		switch (event) {
		case XMLStreamConstants.START_DOCUMENT :
			break;
		case XMLStreamConstants.START_ELEMENT :
			initElement(reader);
			break;
		case XMLStreamConstants.CHARACTERS :
			takeContent(reader);
			break;
		case XMLStreamConstants.END_ELEMENT :
			initContent(reader);
			break;
		case XMLStreamConstants.END_DOCUMENT :
			break;
			
		}
		
	}
	
	/**
	 * @param reader
	 */
	private void takeContent(XMLStreamReader reader) {
		content = reader.getText().trim();
	}


	/**
	 * @param reader
	 */
	private void initElement(XMLStreamReader reader) {
		String str = reader.getLocalName();
		ElementEnum thisElement = ElementEnum.getElementEnum(str);
		
		switch (thisElement) {
		case DEVICE:
			initElementDevice(reader);
			break;
		case COMPONENT:
			initElementComponent(reader);
			break;
		case PRICE:
			initElementPrice(reader);
			break;
		case POWER_CONSUMPTION:
			initElementPowerConcumption(reader);
			break;
		default:
			break;
		}
	}

	/**
	 * @param reader
	 */
	private void initElementComponent(XMLStreamReader reader) {
		component = new Component();
		component.setId(reader.getAttributeValue(0));
		device.getComponents().add(component);
	}

	/**
	 * 
	 */
	private void initElementDevice(XMLStreamReader reader) {
		device = new Device();
		device.setDeviceId(reader.getAttributeValue(0));
	}	

	/**
	 * 
	 */
	private void initElementPrice(XMLStreamReader reader) {
		component.setCurrency(reader.getAttributeValue(0));
	}
	
	/**
	 * @param reader
	 */
	private void initElementPowerConcumption(XMLStreamReader reader) {
		component.getType().setPowerConsumptionValueName(reader.getAttributeValue(0));
	}

	/**
	 * @param reader
	 */
	private void initContent(XMLStreamReader reader) {
		String str = reader.getLocalName();
		ElementEnum thisElement = ElementEnum.getElementEnum(str);

		switch (thisElement) {
		case DEVICE_NAME:
			initContentDeviceName();
			break;
		case NAME:
			initContentName();
			break;
		case ORIGIN:
			initContentOrigin();
			break;
		case PRICE:
			initContentPrice();
			break;
		case PERIPHERAL:
			initContetntPeripheral();
			break;
		case POWER_CONSUMPTION:
			initContentPowerConsumption();
			break;
		case COOLER:
			initContetntCooler();
			break;
		case COMPONENTS_GROUP:
			initContentComponentsGroup();
			break;
		case PORT:
			initContentPort();
			break;
		case CRITICAL:
			initContetntCritical();
			break;
		default:
			break;
		}

	}

	/**
	 * 
	 */
	private void initContetntCritical() {
		switch (content) {
		case "Yes" :
			component.setCritical(ComponentType.YES);
			break;
		case "No" :
			component.setCritical(ComponentType.NO);
			break;
		}
	}

	/**
	 * 
	 */
	private void initContentComponentsGroup() {
		component.getType().setComponentsGroup(content);		
	}

	/**
	 * 
	 */
	private void initContetntCooler() {
		switch (content) {
		case "Yes" :
			component.getType().setCooler(ComponentType.YES);
			break;
		case "No" :
			component.getType().setCooler(ComponentType.NO);
			break;
		}
	}

	/**
	 * 
	 */
	private void initContentPowerConsumption() {
		component.getType().setPowerConsumption(Integer.valueOf(content));		
	}

	/**
	 * 
	 */
	private void initContetntPeripheral() {
		switch (content) {
		case "Yes" :
			component.getType().setPeripheral(ComponentType.YES);
			break;
		case "No" :
			component.getType().setPeripheral(ComponentType.NO);
			break;
		}		
	}

	/**
	 * 
	 */
	private void initContentPrice() {
		component.setPrice(Double.valueOf(content));
	}

	/**
	 * 
	 */
	private void initContentOrigin() {
		component.setOrigin(content);
	}

	/**
	 * @param reader
	 */
	private void initContentName() {
		component.setName(content);
	}

	/**
	 * @param thisElement
	 * @param reader
	 */
	private void initContentDeviceName() {
		device.setDeviceName(content);
	}

	/**
	 * 
	 */
	private void initContentPort() {
		ElementEnum elementEnum = ElementEnum.getElementEnum(content);
			
		switch (elementEnum) {
		case COM :
			component.getType().addPort(ComponentType.Port.COM);
			break;
		case USB :
			component.getType().addPort(ComponentType.Port.USB);
			break;
		case LPT :
			component.getType().addPort(ComponentType.Port.LPT);
			break;
		default:
			break;
		}
	}

		
	public Device getDevice() {
		return device;
	}
	

}
