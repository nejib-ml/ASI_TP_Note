package fr.univtours.polytech.gestionbiblioejb.utils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import fr.univtours.polytech.gestionbiblioejb.business.AuthorService;
import fr.univtours.polytech.gestionbiblioejb.business.BookService;
import fr.univtours.polytech.gestionbiblioejb.business.GenreService;
import fr.univtours.polytech.gestionbiblioejb.business.UserService;
import fr.univtours.polytech.gestionbiblioejb.model.Author;
import fr.univtours.polytech.gestionbiblioejb.model.Book;
import fr.univtours.polytech.gestionbiblioejb.model.Genre;
import fr.univtours.polytech.gestionbiblioejb.model.User;

@Singleton
@Startup
public class DatabaseInitializer {

	@Inject
	private BookService bookService;
	
	@Inject
	private AuthorService authorService;
	
	@Inject
	private GenreService genreService;

	@Inject
	private UserService userService;

	@PostConstruct
	public void init() {
		// Création d'un compte administrateur
		User admin = new User();
		admin.setMail("admin@admin.fr");
		admin.setFirstname("admin");
		admin.setLastname("admin");
		admin.setPassword("admin");
		admin.setAdmin(true);
		userService.createUser(admin);

		//-----------------------------------
		
		// Création de quelques auteurs
		List<Author> authors = new ArrayList<>();
		
		Author author1 = new Author();
		author1.setName("J.K. Rowling");
		authors.add(author1);
		
		Author author2 = new Author();
		author2.setName("George Orwell");
		authors.add(author2);
		
		Author author3 = new Author();
		author3.setName("Agatha Christie");
		authors.add(author3);
		
		for (Author author: authors) {
			authorService.createAuthor(author);
		}

		//-----------------------------------
		
		// Création de quelques genres
		List<Genre> genres = new ArrayList<>();
		
		Genre genre1 = new Genre();
		genre1.setName("Fantasy");
		genres.add(genre1);
		
		Genre genre2 = new Genre();
		genre2.setName("Science Fiction");
		genres.add(genre2);
		
		Genre genre3 = new Genre();
		genre3.setName("Policier");
		genres.add(genre3);
		
		for (Genre genre: genres) {
			genreService.createGenre(genre);
		}

		//-----------------------------------
		
		// Création de quelques livres
		List<Book> books = new ArrayList<>();

		Book book1 = new Book();
		book1.setTitle("Harry Potter and the Philosopher's Stone");
		book1.setAuthor(author1);
		book1.setGenre(genre1);
		book1.setAvailable(true);
		books.add(book1);

		Book book2 = new Book();
		book2.setTitle("Harry Potter and the Chamber of Secrets");
		book2.setAuthor(author1);
		book2.setGenre(genre1);
		book2.setAvailable(true);
		books.add(book2);

		Book book3 = new Book();
		book3.setTitle("1984");
		book3.setAuthor(author2);
		book3.setGenre(genre2);
		book3.setAvailable(true);
		books.add(book3);

		Book book4 = new Book();
		book4.setTitle("Animal Farm");
		book4.setAuthor(author2);
		book4.setGenre(genre2);
		book4.setAvailable(true);
		books.add(book4);

		Book book5 = new Book();
		book5.setTitle("Murder on the Orient Express");
		book5.setAuthor(author3);
		book5.setGenre(genre3);
		book5.setAvailable(true);
		books.add(book5);

		for (Book book : books) {
			bookService.createBook(book);
		}
	}

}
