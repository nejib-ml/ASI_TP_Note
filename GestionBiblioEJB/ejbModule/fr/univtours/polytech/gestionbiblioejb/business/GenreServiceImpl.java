package fr.univtours.polytech.gestionbiblioejb.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.univtours.polytech.gestionbiblioejb.dao.GenreDao;
import fr.univtours.polytech.gestionbiblioejb.model.Genre;

@Stateless
public class GenreServiceImpl implements GenreService {
	@Inject
	private GenreDao genreDao;

	@Override
	public List<Genre> getAllGenres() {
		return genreDao.findAll();
	}

	@Override
	public Genre getGenreById(Long id) {
		return genreDao.findById(id);
	}

	@Override
	public void createGenre(Genre genre) {
		genreDao.create(genre);
	}

	@Override
	public void updateGenre(Genre genre) {
		genreDao.update(genre);
	}

	@Override
	public void deleteGenre(Genre genre) {
		genreDao.delete(genre);
	}
}