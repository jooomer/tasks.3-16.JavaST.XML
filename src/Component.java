/**
 * 
 */

import java.util.*;

/**
 * @author Sergey
 *
 */

class ComponentType {
	private boolean peripheral;
	private int powerConsumption;
	private String powerConsumptionValueName;
	private boolean cooler;
	private String componentsGroup;
	private List<Port> ports;
	private boolean critical;
	public static final boolean YES = true;
	public static final boolean NO = false;
	
	public enum Port {
		COM, USB, LPT;
	}
	
	public void addPort(Port port) {
		if (ports == null) {
			ports = new ArrayList<>();
		}
		ports.add(port);
	}
	
	public Port takeLastPort() {
		return ports.get(ports.size() - 1);
	}
	
	/**
	 * @return the peripheral
	 */
	public boolean isPeripheral() {
		return peripheral;
	}
	/**
	 * @param peripheral the peripheral to set
	 */
	public void setPeripheral(boolean peripheral) {
		this.peripheral = peripheral;
	}
	/**
	 * @return the powerConsumption
	 */
	public int getPowerConsumption() {
		return powerConsumption;
	}
	/**
	 * @param powerConsumption the powerConsumption to set
	 */
	public void setPowerConsumption(int powerConsumption) {
		this.powerConsumption = powerConsumption;
	}
	/**
	 * @return the cooler
	 */
	public boolean isCooler() {
		return cooler;
	}
	/**
	 * @param cooler the cooler to set
	 */
	public void setCooler(boolean cooler) {
		this.cooler = cooler;
	}
	/**
	 * @return the componentsGroup
	 */
	public String getComponentsGroup() {
		return componentsGroup;
	}
	/**
	 * @param componentsGroup the componentsGroup to set
	 */
	public void setComponentsGroup(String componentsGroup) {
		this.componentsGroup = componentsGroup;
	}
	/**
	 * @return the port
	 */
	public List<Port> getPorts() {
		return ports;
	}
	/**
	 * @param port the port to set
	 */
	public void setPorts(List<Port> ports) {
		this.ports = ports;
	}

	/**
	 * @return the critical
	 */
	public boolean isCritical() {
		return critical;
	}

	/**
	 * @param critical the critical to set
	 */
	public void setCritical(boolean critical) {
		this.critical = critical;
	}

	/**
	 * @return the powerValueName
	 */
	public String getPowerConsumptionValueName() {
		return powerConsumptionValueName;
	}

	/**
	 * @param powerValueName the powerValueName to set
	 */
	public void setPowerConsumptionValueName(String powerValueName) {
		this.powerConsumptionValueName = powerValueName;
	}
}


public class Component {
	private String name;
	private String id;
	private String origin;
	private double price;
	private String currency;
	private boolean critical;
	private ComponentType type = new ComponentType();
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}
	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @return the critical
	 */
	public boolean isCritical() {
		return critical;
	}
	/**
	 * @param critical the critical to set
	 */
	public void setCritical(boolean critical) {
		this.critical = critical;
	}
	/**
	 * @return the type
	 */
	public ComponentType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(ComponentType type) {
		this.type = type;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
