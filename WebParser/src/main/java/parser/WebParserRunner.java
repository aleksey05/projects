package parser;


import java.util.Set;

import converterXml.Converter;
import model.Offer;
import model.Product;

public class WebParserRunner {
	
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
	    
		parser.collectAllPagesLinks("https://www.aboutyou.de");
		parser.selctPagesWithRequiredProducts();
		products = parser.getSelectedProductsList();
		
		new Converter().convertToXML(new Offer(products));
		
		tracker.setEndTime();
		tracker.printInfo(products.size());
	}

}
