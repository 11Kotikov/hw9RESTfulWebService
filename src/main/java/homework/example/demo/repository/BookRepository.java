package homework.example.demo.repository;

import homework.example.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    // тут все crud методы с базой данных
}


