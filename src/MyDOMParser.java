import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 */

/**
 * @author Sergey
 *
 */
public class MyDOMParser {
	
	private Device device;
	
	public void parse(String xmlFile) {
		
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = builderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
				
		Document document = null;
		try {
			document = builder.parse(new FileInputStream(xmlFile));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		visitLevels(document, 0);
		
	}
	
	/**
	 * @param document
	 * @param iget
	 */
	private void visitLevels(Node node, int level) {
		NodeList nodes = node.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node childNode = nodes.item(i);
			processChildNode(childNode);
			visitLevels(childNode, level + 1);
		}
		
	}

	/**
	 * @param childNode
	 * @param i
	 */
	private void processChildNode(Node node) {
		if (node instanceof Element) {
			Element e = (Element) node;
			initObject(e); // init objects
		}
	}

	/**
	 * @param e
	 */
	private void initObject(Element e) {
		String nodeName = e.getNodeName();
		ElementEnum thisElement = ElementEnum.getElementEnum(nodeName);

		switch (thisElement) {
		case DEVICE:
			initDevice(e);
			break;
		case DEVICE_NAME:
			initDeviceName(e);
			break;
		case COMPONENTS:
			break;
		case COMPONENT:
			initComponent(e);
			break;
		case NAME:
			initName(e);
			break;
		case ORIGIN:
			initOrigin(e);
			break;
		case PRICE:
			initPrice(e);
			break;
		case TYPE:
			break;
		case PERIPHERAL:
			initPeripheral(e);
			break;
		case POWER_CONSUMPTION:
			initPowerConcumption(e);
			break;
		case COOLER:
			initCooler(e);
			break;
		case COMPONENTS_GROUP:
			initComponentsGroup(e);
			break;
		case PORTS:
			break;
		case PORT:
			initPort(e);
			break;
		case CRITICAL:
			initCritical(e);
			break;
		default:
			break;
		}
	}

	/**
	 * @param e
	 */
	private void initCritical(Element e) {
		Component component = takeLastComponent();
		String str = e.getTextContent();
		boolean critical = false;
		switch (str) {
		case "Yes" :
			critical = ComponentType.YES;
			break;
		case "No" :
			critical = ComponentType.NO;
			break;
		}
		component.getType().setCritical(critical);		
	}

	/**
	 * @param e
	 */
	private void initPort(Element e) {
		Component component = takeLastComponent();
		String str = e.getTextContent();
		switch (str) {
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

	/**
	 * @param e
	 */
	private void initComponentsGroup(Element e) {
		Component component = takeLastComponent();
		String componentsGroup = e.getTextContent();
		component.getType().setComponentsGroup(componentsGroup);		
	}

	/**
	 * @param e
	 */
	private void initCooler(Element e) {
		Component component = takeLastComponent();
		String str = e.getTextContent();
		boolean cooler = false;
		switch (str) {
		case "Yes" :
			cooler = ComponentType.YES;
			break;
		case "No" :
			cooler = ComponentType.NO;
			break;
		}
		component.getType().setCooler(cooler);
	}

	/**
	 * @param e
	 */
	private void initPowerConcumption(Element e) {
		Component component = takeLastComponent();
		int powerConsumption = Integer.valueOf(e.getTextContent());
		String str = ElementEnum.getElementEnumValue(ElementEnum.POWER_CONSUMPTION_VALUE_NAME);
		String powerConsumptionValueName = e.getAttribute(str);
		component.getType().setPowerConsumption(powerConsumption);
		component.getType().setPowerConsumptionValueName(powerConsumptionValueName);
	}

	/**
	 * @param e
	 */
	private void initPeripheral(Element e) {
		Component component = takeLastComponent();
		String str = e.getTextContent();
		boolean peripheral = false;
		switch (str) {
		case "Yes" :
			peripheral = ComponentType.YES;
			break;
		case "No" :
			peripheral = ComponentType.NO;
			break;
		}
		component.getType().setPeripheral(peripheral);
	}

	/**
	 * @param e
	 */
	private void initPrice(Element e) {
		Component component = takeLastComponent();				
		double price = Double.valueOf(e.getTextContent());
		String currency = e.getAttribute("currency");
		component.setPrice(price);
		component.setCurrency(currency);
	}

	/**
	 * @param e
	 */
	private void initOrigin(Element e) {
		Component component = takeLastComponent();				
		String name = e.getTextContent();		
		component.setOrigin(name);
		
	}

	/**
	 * @param e
	 */
	private void initName(Element e) {
		Component component = takeLastComponent();				
		String name = e.getTextContent();		
		component.setName(name);
	}

	/**
	 * @param e
	 */
	private void initComponent(Element e) {
		Component component = new Component();
		String attribute = e.getAttribute("id");
		component.setId(attribute);
		device.getComponents().add(component);
	}

	/**
	 * @param e
	 */
	private void initDeviceName(Element e) {
		String deviceName = e.getTextContent();		
		device.setDeviceName(deviceName);
	}

	/**
	 * @param e
	 */
	private void initDevice(Element e) {
    	device = new Device();    
    	String attribute = e.getAttribute("deviceId");    
    	device.setDeviceId(attribute);		
	}
	
	private Component takeLastComponent() {
		int lastIndex = device.getComponents().size() - 1;
		return device.getComponents().get(lastIndex);
	}

	public Device getDevice() {
		return device;
	}
}
