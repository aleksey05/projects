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
//		links.add("https://www.aboutyou.de/kinder?page=2");
//		links.add("https://www.aboutyou.de/kinder?page=3");
//		links.add("https://www.aboutyou.de/kinder?page=4");
//		links.add("https://www.aboutyou.de/kinder?page=5");
//		links.add("https://www.aboutyou.de/kinder?page=6");
//		links.add("https://www.aboutyou.de/kinder?page=7");
//		links.add("https://www.aboutyou.de/kinder?page=8");
//		links.add("https://www.aboutyou.de/kinder?page=9");
//		links.add("https://www.aboutyou.de/kinder?page=10");
//		links.add("https://www.aboutyou.de/kinder?page=11");
//		links.add("https://www.aboutyou.de/kinder?page=12");
//		links.add("https://www.aboutyou.de/kinder?page=13");
//		links.add("https://www.aboutyou.de/kinder?page=14");
//		links.add("https://www.aboutyou.de/kinder?page=15");
//		links.add("https://www.aboutyou.de/kinder?page=16");

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
				System.out.print(URL);
				System.out.println(" ---->   " + links.size());
				links.add(URL);
				try {
					Document document = Jsoup.connect(URL).get();
					tracker.countCalls();
					Elements linksOnPage = document.select("a[href]");
					for (Element link : linksOnPage) {
						collectAllPagesLinks(link.absUrl("href"));
					}
				} catch (IOException e) {
					System.err.println("For '" + URL + "': " + e.getMessage());
				}
			}
		}

	}

int track = 0;
	public void selctPagesWithRequiredProducts() {

		for (String page : links) {
			System.out.println("Exploring page number " + track++);
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
		System.out.println("in fill products");

		Elements brands = doc.select(".brand_ke66rm");
		Elements productNames = doc.select(".name_1iurgbx");
		Elements productLinks = doc.select("div.categoryTileWrapper_e296pg > a.anchor_wgmchy");

		Elements productPrices = doc.select("span.price_1543wg1-o_O-highlight_1t1mqn4"); 
		
		
		List<String> productLinksList = concatURLS(productLinks);
		System.out.println("BRAND_SIZE  " + brands.size());
		System.out.println("BRAND_NAMES  " + productNames.size());
		System.out.println("PRODUCT_LINKS " + productLinksList.size());

		for (int i = 0; i < brands.size(); i++) {
			String brand = brands.get(i).text().toLowerCase();
			String modelName = productNames.get(i).text().toLowerCase();
			if (checkForCoincidence(keyword, brand, modelName)) {
				addProduct(brands.get(i).text(), productNames.get(i).text(), productLinksList.get(i)); // +parameter
																										// ProductPricre
			}
		}
	}

	int pn = 1;

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
			System.out.println(product!=null);
			selectedProductsList.add(product);
			System.out.println("Product added " + pn++);
		}
	}

	private boolean checkForCoincidence(String keyword, String brand, String productName) {
		if (brand.contains(keyword) || productName.contains(keyword)) {
			System.out.println("Page  " + track + " has required products ----> ");
		}
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
