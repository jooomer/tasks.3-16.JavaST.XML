import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 */

/**
 * @author Sergey
 *
 */
public class MySAXParser {
	
	private ElementEnum thisElement;
	private Attributes thisAttributes;
	
	private Device device;
	private Component component;
	private ComponentType type;
	
		
	public void parse(String xmlFile) {
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		try {
			
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {

				/* (non-Javadoc)
				 * @see org.xml.sax.helpers.DefaultHandler#startDocument()
				 */
				@Override
				public void startDocument() throws SAXException {
					System.out.println("Start parse XML...");
				}

				/* (non-Javadoc)
				 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
				 */
				@Override
				public void endDocument() throws SAXException {
					System.out.println("End parse XML...");
				}

				/* (non-Javadoc)
				 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
				 */
				// startElement(String namespaceURI, String localName, String qName, Attributes atts)
				@Override
				public void startElement(String namespaceURI, String localName, String qName,
						Attributes atts) throws SAXException {
					
					thisElement = ElementEnum.getElementEnum(qName);
					thisAttributes = atts;
					
					initElement(thisElement, thisAttributes);
					
				}
				
				/* (non-Javadoc)
				 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
				 */
				@Override
				public void endElement(String namespaceURI, String localName, String qName)
						throws SAXException {
					thisElement = null;
				}

				/* (non-Javadoc)
				 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
				 */
				@Override
				public void characters(char[] ch, int start, int length)
						throws SAXException {

					String characters = new String(ch, start, length);
					
					initElementData(characters);
					
				}

			};
			
			saxParser.parse(xmlFile, handler);

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void initElement(ElementEnum thisElement, Attributes thisAttributes) {
		switch (thisElement) {
		case DEVICE :
			initDevice(thisAttributes);
			break;
		case COMPONENT :
			initComponent(thisAttributes);
			break;
		case TYPE :
			initType();
			break;
		case PRICE :
			initPrice(thisAttributes);
			break;
		case POWER_CONSUMPTION :
			initPowerConsumption(thisAttributes);
			break;
		default:
			break;
		}
		
		if (thisAttributes != null) {
			
		}

	}
	
	private void initDevice(Attributes thisAttributes) {
		this.device = new Device();
		this.device.setDeviceId(thisAttributes.getValue(0));
	}
	
	private void initDeviceData(ElementEnum thisElement, String characters) {
		switch (thisElement) {
		case DEVICE_NAME :
			device.setDeviceName(characters);
			break;
		default:
			break;
		}
	}
	
	private void initComponent(Attributes thisAttributes) {
		Component component = new Component();
		if (thisAttributes != null) {
			for (int i = 0; i < thisAttributes.getLength(); i++) {
				String value = thisAttributes.getValue(i);
				component.setId(value);
			}
		}
		device.getComponents().add(component);
		this.component = component;
	}
	
	private void initElementData(String characters) {
		if (thisElement != null) {
			switch (thisElement) {
			case DEVICE_NAME:
				initDeviceData(thisElement, characters);
				break;
			case NAME:
			case ORIGIN:
			case PRICE:
			case CRITICAL:
				initComponentData(thisElement, characters);
				break;
			case PERIPHERAL:
			case POWER_CONSUMPTION:
			case COOLER:
			case COMPONENTS_GROUP:
			case PORTS:
			case PORT:
				initTypeData(thisElement, characters);
				break;
			default:
				break;
			}
		}
	}

	private void initComponentData(ElementEnum thisElement, String characters) {
		switch (thisElement) {
		case NAME :
			component.setName(characters);
			break;
		case ORIGIN :
			component.setOrigin(characters);
			break;
		case PRICE :
			component.setPrice(Double.valueOf(characters));
			break;
		case CRITICAL :
			boolean critical = ComponentType.NO;
			if (characters.equals("Yes")) {
				critical = ComponentType.YES;
			} 
			component.setCritical(critical);
			break;
		default:
			break;
		}

	}
	
	private void initType() {
		ComponentType type = new ComponentType();
		component.setType(type);
		this.type = type;
	}
	
	private void initTypeData(ElementEnum thisElement, String characters) {
		switch (thisElement) {
		case PERIPHERAL :			
			boolean peripheral = ComponentType.NO;
			if (characters.equals("Yes")) {
				peripheral = ComponentType.YES;
			} 
			component.getType().setPeripheral(peripheral);
			break;
		case POWER_CONSUMPTION :			
			component.getType().setPowerConsumption(Integer.valueOf(characters));
			break;
		case COOLER :			
			boolean cooler = ComponentType.NO;
			if (characters.equals("Yes")) {
				cooler = ComponentType.YES;
			} 
			component.getType().setCooler(cooler);
			break;
		case COMPONENTS_GROUP :			
			component.getType().setComponentsGroup(characters);
			break;
		case PORT :
			initPorts(thisElement, characters);
			break;
		default:
			break;
		}
	}
	
	private void initPrice(Attributes thisAttributes) {
		component.setCurrency(thisAttributes.getValue(0));		
	}
	
	private void initPorts(ElementEnum thisElement, String characters) {
		switch (characters) {
		case "COM" :
			component.getType().addPort(ComponentType.Port.COM);
			break;
		case "USB" :
			component.getType().addPort(ComponentType.Port.USB);
			break;
		case "LPT" :
			component.getType().addPort(ComponentType.Port.LPT);
			break;
		}
	}
	
	private void initPowerConsumption(Attributes thisAttributes) {
		component.getType().setPowerConsumptionValueName(thisAttributes.getValue(0));
	}
	
	/**
	 * @return the computer
	 */
	public Device getDevice() {
		return device;
	}
	
}
