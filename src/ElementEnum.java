/**
 * 
 */

/**
 * @author Sergey
 *
 */
public enum ElementEnum {
	DEVICE("Device"),
	COMPONENTS("Components"),
	COMPONENT("Component"),
	TYPE("Type"),
	PRICE("price"),
	POWER_CONSUMPTION("powerconsumption"),
	POWER_CONSUMPTION_VALUE_NAME("power"),
	DEVICE_NAME("devicename"),
	NAME("name"),
	ORIGIN("origin"),
	CRITICAL("critical"),
	PERIPHERAL("peripheral"),
	COOLER("cooler"),
	COMPONENTS_GROUP("componentsgroup"),
	PORTS("Ports"),
	PORT("port"),
	COM("COM"),
	USB("USB"),
	LPT("LPT");
	
	
	private String value;
	
	private ElementEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	static public ElementEnum getElementEnum(String value) {
		for (ElementEnum ee : ElementEnum.values()) {
			if (ee.getValue().equals(value)) {
				return ee;
			}
		}
		throw new RuntimeException("Unknown type");
	}
	
	static String getElementEnumValue(ElementEnum elementEnum) {
		for (ElementEnum ee : ElementEnum.values()) {
			if (ee.equals(elementEnum)) {
				return ee.value;
			}
		}
		throw new RuntimeException("Unknown type");
	}
}
