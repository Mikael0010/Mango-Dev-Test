package com.mmede.MangoDevTest;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {

  Item findByTitle(String title);
}