package util;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public Integer duyurulmaYilBul(){
		String duyurulma = doc.select("a:contains(Announced)").first().parent().nextElementSibling().text();
		String pattern = "([0-9]{4})";
		Pattern r = Pattern.compile(pattern);
	    Matcher m = r.matcher(duyurulma);
	    if (m.find()) {
	    	return Integer.parseInt(m.group(0));
	    }else{
	    	return 0;
	    }
	}
	
	public String duyurulmaAyBul(){
		String duyurulma = doc.select("a:contains(Announced)").first().parent().nextElementSibling().text();
		String pattern = "([A-Za-z]+)";
		Pattern r = Pattern.compile(pattern);
	    Matcher m = r.matcher(duyurulma);
	    if (m.find()) {
	    	return m.group(0);
	    }else{
	    	return "";
	    }
	}	
	
}
