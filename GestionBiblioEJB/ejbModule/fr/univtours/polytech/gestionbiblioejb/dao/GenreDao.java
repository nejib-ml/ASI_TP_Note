package fr.univtours.polytech.gestionbiblioejb.dao;

import java.util.List;

import fr.univtours.polytech.gestionbiblioejb.model.Genre;

public interface GenreDao {
	public void create(Genre genre);

	public Genre findById(Long id);

	public List<Genre> findAll();

	public void update(Genre genre);

	public void delete(Genre genre);
}
