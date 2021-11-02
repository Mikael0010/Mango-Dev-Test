package com.mmede.MangoDevTest;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity 
public class Item{

    private @Id String title;
	private String description;

	private Item() {}

	public Item(String title, String description) {
		this.title = title;
		this.description = description;
	}
   
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	@Override   //This is probably useless but I'm keeping it in just in case.
	public String toString() {
		return "Item{" +
			", title='" + title + '\'' +
			", description='" + description + '\'' +
			'}';
	}
}