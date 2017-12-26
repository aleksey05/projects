package parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.Product;

public class Parser {
	private Tracker tracker;
	private final String BASE_URL = "https://www.aboutyou.de";
	private String keyword;
	private Set<String> links;
	private Set<Product> selectedProductsList;
	private Document doc;
	private String searchType;


	public Parser(String keyword, String searchType) {
		this.searchType = searchType;
		this.keyword = keyword;
		tracker = new Tracker();
		selectedProductsList = new HashSet<>();
		links = new HashSet<String>();
	}

	public Set<String> getLinks() {
		return links;
	}

	public Set<Product> getSelectedProductsList() {
		return selectedProductsList;
	}

	public void collectAllPagesLinks(String URL) {
		if (URL.equals(BASE_URL) | URL.contains(BASE_URL + searchType)) {
			if (!links.contains(URL)) {
				links.add(URL);
				try {
					Document document = Jsoup.connect(URL).get();
					tracker.countCalls();
					Elements linksOnPage = document.select("a[href]");
					for (Element link : linksOnPage) {
						collectAllPagesLinks(link.absUrl("href"));
					}
				} catch (IOException e) {
					System.out.println(URL + "': " + e.getMessage());
				}
			}
		}

	}

	public void selctPagesWithRequiredProducts() {

		for (String page : links) {
			try {
				doc = Jsoup.connect(page).get();
				tracker.countCalls();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (doc.select("*:containsOwn(" + keyword + ")").size() >= 1) {
				fillProducts(doc);
			}
		}
	}

	private void fillProducts(Document doc) {

		Elements brands = doc.select(".brand_ke66rm");
		Elements productNames = doc.select(".name_1iurgbx");
		Elements productLinks = doc.select("div.categoryTileWrapper_e296pg > a.anchor_wgmchy");

	//	Elements productPrices = doc.select("span.price_1543wg1-o_O-highlight_1t1mqn4"); Prices are inclosed in different tags over the shop. Currently not included into searched objects. 
		
		
		List<String> productLinksList = concatURLS(productLinks);

		for (int i = 0; i < brands.size(); i++) {
			String brand = brands.get(i).text().toLowerCase();
			String modelName = productNames.get(i).text().toLowerCase();
			if (checkForCoincidence(keyword, brand, modelName)) {
				addProduct(brands.get(i).text(), productNames.get(i).text(), productLinksList.get(i)); 
																										     
			}
		}
	}


	private void addProduct(String brand, String modelName, String productLink) {
		Product product = new Product();
		List<String> descriptionList = new ArrayList<>();
		Document productPage = null;

		try {
			productPage = Jsoup.connect(productLink).get();
			tracker.countCalls();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (productPage != null) {

			Elements productDescriptionElements = productPage.select("ul.orderedList_1q5u47i div li");
			for (Element el : productDescriptionElements) {
				descriptionList.add(el.text());
			}
			Elements productIdElement = productPage.select("*:containsOwn(Artikel-Nr)");
			String productId = productIdElement.text().substring(productIdElement.text().indexOf(":") + 1,
					productIdElement.text().length());
			product.setModelName(modelName);
			product.setBrand(brand);
			product.setDescription(descriptionList);
			product.setProductId(productId);
			
			selectedProductsList.add(product);
		}
	}

	private boolean checkForCoincidence(String keyword, String brand, String productName) {
		return brand.contains(keyword) || productName.contains(keyword);
	}

	private List<String> concatURLS(Elements elementsProductLinksList) {
		List<String> concatedList = new ArrayList<>();
		Set<String> linksSet = new LinkedHashSet<>();

		for (Element el : elementsProductLinksList) {
			linksSet.add(el.attr("href"));
		}
		for (String link : linksSet) {
			concatedList.add(BASE_URL.concat(link));
		}
		return concatedList;

	}

}
