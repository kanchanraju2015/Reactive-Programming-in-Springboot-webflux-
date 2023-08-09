package com.briz.mongoflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
// reactive web and reactive mongodb only in dependency webflux and reactor automatically.
@RestController
public class BookController
{
@Autowired
BookRepository brepo;// note netty server is running on 8080 check log file 
@RequestMapping("/test")
public String test()
{
	return "this is only mongodb test";
}
@RequestMapping("/save")
public Mono<mybooks> save()
{
	mybooks m=new mybooks();
	m.setAuthorName("harry potter");
	m.setBookName("fiction history");
	m.setId(451);
	brepo.save(m).subscribe(); // must subscribe otherwise will not work
	Mono<mybooks> mono=Mono.just(m);
	return mono;
}
@RequestMapping(value="/all",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
Flux<mybooks> alldata()
{
	return brepo.findAll();
}
@RequestMapping(value="/{id}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
Mono<mybooks> byid(@PathVariable int id)
{
	return brepo.findById(id);
}
@RequestMapping(value="/author/{authorName}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
Flux<mybooks> byauthor(@PathVariable String authorName)
{
	return brepo.findByauthorName(authorName);
}
@RequestMapping("/del/{id}")
public Mono<String> del(@PathVariable int id)
{
	brepo.deleteById(id).subscribe(); // must subscribe otherwises not work
	Mono<String> mono=Mono.just("data deleted");	
	return mono;
}
@RequestMapping("/by/{bookName}")
public Flux<mybooks> bybook(@PathVariable String bookName)
{
	 return brepo.findByBookName(bookName);	
	
}
}
