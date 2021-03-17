package br.com.bookclient.book;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	@Query("FROM Book l " +  "WHERE LOWER(l.title) like %:searchTerm%")
	Page<Book> findAll(@Param("searchTerm") String searchTerm, Pageable pageable);
	
	@Query("FROM Book l " +  "WHERE l.livro_db = :livro_db")
	List<Book> findByUUID(@Param("livro_db") UUID livro_db);
	
}
