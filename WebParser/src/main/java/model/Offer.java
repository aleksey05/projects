package model;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "offers")
public class Offer {

	private Set<Product> list;

	public Offer() {
	}

	public Offer(Set<Product> products) {
		list = products;
	}

	@XmlElement(name = "offer")
	public Set<Product> getList() {
		return list;
	}

	public void setList(Set<Product> list) {
		this.list = list;
	}

}
