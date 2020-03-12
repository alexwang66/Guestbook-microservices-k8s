package org.wangqing.microservices.guestbook.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.wangqing.microservices.guestbook.entity.Notebook;

import java.util.List;

@Repository
public interface NotebookRepository extends CrudRepository<Notebook, Long> {
    
    List<Notebook> findByName(String name);
    
}
