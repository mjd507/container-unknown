package com.jiandong.dockercompose;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@SpringBootApplication
public class DockerComposeApplication {

	static void main(String[] args) {
		SpringApplication.run(DockerComposeApplication.class, args);
	}

}

@Document("item")
record Item(@Id String id, String name, int quantity, String category) {

}

@RestController
@RequestMapping("/item")
class ItemController {

	private final ItemRepository itemRepository;

	ItemController(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<Item> save(@RequestBody Item item) {
		return ResponseEntity.ok(itemRepository.save(item));
	}

}

@Repository
interface ItemRepository extends CrudRepository<Item, String> {

}