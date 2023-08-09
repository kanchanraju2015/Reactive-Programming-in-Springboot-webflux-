package com.briz.mongoflux;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class mybooks   // note- mybooks will be the name of document inside mongodb 
{
@Id
int id;
String authorName;
String bookName;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getAuthorName() {
	return authorName;
}
public void setAuthorName(String authorName) {
	this.authorName = authorName;
}
public String getBookName() {
	return bookName;
}
public void setBookName(String bookName) {
	this.bookName = bookName;
}

}
