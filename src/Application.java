/**
 * 
 */

/**
 * @author Sergey
 *
 */
public class Application {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String xmlFile = "Computer.xml"; // path and name of file
		String xsdFile = "Computer.xsd";
				
//		FileUtilities.readFile(xmlFile);
//		FileUtilities.printFile();
	
		
		XsdSchemaSaxValidator.mainValidator(xsdFile, xmlFile);

		System.out.println();
		System.out.println("#########################################  SAX parser  ##########################################");
		MySAXParser mySAXParsr = new MySAXParser();
		mySAXParsr.parse(xmlFile);
		mySAXParsr.getDevice().printDevice();

		System.out.println();
		System.out.println("#########################################  DOM parser  ##########################################");
		MyDOMParser myDOMParser = new MyDOMParser();
		myDOMParser.parse(xmlFile);
		myDOMParser.getDevice().printDevice();

		System.out.println();
		System.out.println("#########################################  StAX parser  ##########################################");
		MyStAXParser myStAXParser = new MyStAXParser();
		myStAXParser.parse(xmlFile);
		myStAXParser.getDevice().printDevice();

	}

}
