package com.att.ticketwala.dao.service.impl;

import java.time.LocalDateTime;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import com.att.ticketwala.dao.service.api.MovieShowDao;
import com.att.ticketwala.service.api.MovieShow;
import com.att.ticketwala.service.api.Result;

public class MovieShowDaoImpl implements MovieShowDao{

	private static final String MOVIESHOW_ROOT = "/movieshow/";
	Preferences root = Preferences.userRoot();
	
	@Override
	public Result createMovieShow(MovieShow m) {		
		//Storing movie
		System.out.println("Storing Movie -> " + m);
		String filmNode = MOVIESHOW_ROOT + m.getId();
		Preferences node = root.node(filmNode);
		
		String movieShowName = node.get("name", null);
		if (movieShowName != null) {
			return new Result(false, "Movie with ID " + m.getId() + " already exist!");
		}
		
		node.put("name", m.getName());
		node.put("time", m.getTime().toString());
		node.putInt("duration", m.getDuration());
		
		return new Result(true, "Movie " + m + " Created Successfully");
	}

	@Override
	public MovieShow fetchMovieShow(String id) {
		String filmNode = MOVIESHOW_ROOT + id;
		Preferences node = root.node(filmNode);
		
		String movieShowName = node.get("name", null);
		if (movieShowName == null) {
			return null;
		}
		
		LocalDateTime ldt = LocalDateTime.parse(node.get("time", null));
		int duration = node.getInt("duration", 0);
		MovieShow m = new MovieShow(id, movieShowName, ldt, duration);
		System.out.println("Found movie " + m);		
		
		return m;
	}

	@Override
	public Result deleteMovieShow(String id) {
		String filmNode = MOVIESHOW_ROOT + id;
		Preferences node = root.node(filmNode);
		try {
			node.removeNode();
		} catch (BackingStoreException e) {
			return new Result(false, "Failed to delete movie show " + id);
		}
		return new Result(true, "OK");
	}

	@Override
	public Result updateMovieShow(String id, MovieShow m) {
		//Storing movie
		System.out.println("Updating Movie -> " + m);
		String filmNode = MOVIESHOW_ROOT + m.getId();
		Preferences node = root.node(filmNode);
		
		String movieShowName = node.get("name", null);
		if (movieShowName != null) {
			return new Result(false, "Movie with ID " + m.getId() + " already exist!");
		}
		
		node.put("name", m.getName());
		node.put("time", m.getTime().toString());
		node.putInt("duration", m.getDuration());
		
		return new Result(true, "Movie " + m + " Created Successfully");
		return null;
	}

}
