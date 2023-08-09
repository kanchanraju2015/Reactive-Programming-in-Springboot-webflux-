package com.briz.mongoflux;



import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BookRepository extends ReactiveMongoRepository<mybooks,Integer>
{

	Mono<mybooks> findById(int id);
	Flux<mybooks> findByauthorName(String authorName);
	@Query("{ 'bookName': ?0 }")
	Flux<mybooks> findByBookName(final String name);
}
