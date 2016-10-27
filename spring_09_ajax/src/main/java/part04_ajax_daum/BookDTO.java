package part04_ajax_daum;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="item")
public class BookDTO {
	private String title,link,author, image;
	private int price;
	public BookDTO() {
	}
	public String getTitle() {
		return title;
	}
	@XmlElement(name="title")
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	@XmlElement(name="link")
	public void setLink(String link) {
		this.link = link;
	}
	public String getAuthor() { 
		return author;
	}
	@XmlElement(name="author")
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getImage() {
		return image;
	}
	@XmlElement(name="cover_s_url")
	public void setImage(String image) {
		this.image = image;
	}
	public int getPrice() {
		return price;
	}
	@XmlElement(name="sale_price")
	public void setPrice(int price) {
		this.price = price;
	}
	
}
