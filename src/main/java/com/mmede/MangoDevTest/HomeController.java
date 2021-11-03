package com.mmede.MangoDevTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}
	@Autowired
	private ItemRepository itemRepository;

	@PostMapping("/items")
 	public ResponseEntity<Item> addItem(@RequestBody Item item) {
		return new ResponseEntity<Item>(itemRepository.save(item), HttpStatus.OK);
	 }

}