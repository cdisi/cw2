package util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Parser {
	
	private Document doc;
	
	public Parser(String url){  
		try {
			doc = Jsoup.connect(url)
				.data("query", "Java")
				  .userAgent("Mozilla")
				  .cookie("auth", "token")
				  .timeout(3000)
				  .post();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 
	public String CihazAdiBul(){
		return doc.select("h1.specs-phone-name-title").first().text();
	}
}
