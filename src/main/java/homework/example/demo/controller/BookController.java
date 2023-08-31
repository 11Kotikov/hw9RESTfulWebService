package homework.example.demo.controller;

import homework.example.demo.exception.ResourceNotFoundException;
import homework.example.demo.model.Book;
import homework.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    // build create Book REST API
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // build get books by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Book> getBookById(@PathVariable  long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book does not exist with id:" + id));
        return ResponseEntity.ok(book);
    }

    // build update book REST API
    @PutMapping("{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id,@RequestBody Book bookDetails) {
        Book updateBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book does not exist with id: " + id));

        updateBook.setId(bookDetails.getId());
        updateBook.setTitle(bookDetails.getTitle());
        updateBook.setAuthor(bookDetails.getAuthor());

        bookRepository.save(updateBook);

        return ResponseEntity.ok(updateBook);
    }

    // build delete book REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable long id){

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book does not exist with id: " + id));

        bookRepository.delete(book);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
