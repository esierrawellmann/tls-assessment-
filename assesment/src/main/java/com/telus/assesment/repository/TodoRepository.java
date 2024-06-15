package com.telus.assesment.repository;

import com.telus.assesment.model.Todo;
import org.springframework.data.repository.ListCrudRepository;

//TODO make it paginable and sortable :v extending to Paginable and Sortable
public interface TodoRepository extends ListCrudRepository<Todo,Integer> {
}
