package fr.univtours.polytech.gestionbiblioejb.business;

import java.util.List;

import javax.ejb.Local;

import fr.univtours.polytech.gestionbiblioejb.model.Genre;

@Local
public interface GenreService {
	public List<Genre> getAllGenres();

	public Genre getGenreById(Long id);

	public void createGenre(Genre genre);

	public void updateGenre(Genre genre);

	public void deleteGenre(Genre genre);
}