package parser;


import java.util.Set;

import converterXml.Converter;
import model.Offer;
import model.Product;
//Currently parser traverses only over the pages of "kinder" department, because of memory overflows in heap while attempting to collect links from all the 3 departments.(including men, women).  
public class WebParserRunner {
	private final String BASE_URL = "https://www.aboutyou.de";
	private Set<Product> products;
	private String searchDepartment = "/kinder";
	private Parser parser;
	private Tracker tracker;
	
	public WebParserRunner(String searchWord){
		parser = new Parser(searchWord, searchDepartment);
		tracker = new Tracker();
		
	}
	public void parse() {
		tracker.setStartTime();
	    
		parser.collectAllPagesLinks(BASE_URL);
		parser.selctPagesWithRequiredProducts();
		products = parser.getSelectedProductsList();
		
		new Converter().convertToXML(new Offer(products));
		
		tracker.setEndTime();
		tracker.printInfo(products.size());
	}

}
