package org.wangqing.microservices.guestbook.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "students", collectionResourceRel = "students")
@Repository
public interface StudentDataRestRepository extends PagingAndSortingRepository<Student, Long>{

}
