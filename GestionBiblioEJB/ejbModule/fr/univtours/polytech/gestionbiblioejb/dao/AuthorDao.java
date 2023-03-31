package fr.univtours.polytech.gestionbiblioejb.dao;

import java.util.List;

import fr.univtours.polytech.gestionbiblioejb.model.Author;

public interface AuthorDao {
	public void create(Author author);

	public Author findById(Long id);

	public List<Author> findAll();

	public List<Author> findByName(String name);

	public void update(Author author);

	public void delete(Author author);
}
