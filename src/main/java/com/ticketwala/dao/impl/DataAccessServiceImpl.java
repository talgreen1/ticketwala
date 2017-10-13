package com.ticketwala.dao.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import com.ticketwala.command.api.Result;
import com.ticketwala.dao.api.DataAccessService;
import com.ticketwala.model.ModelFactory;
import com.ticketwala.model.MovieShow;
import com.ticketwala.model.Order;
import com.ticketwala.model.Seat;

public class DataAccessServiceImpl implements DataAccessService {

	private static final String MOVIESHOW_ROOT = "/movieshow";
	Preferences root = Preferences.userRoot();
	
	@Override
	public Result createMovieShow(MovieShow movieShow) {		
		//Storing movie
		System.out.println("Storing Movie -> " + movieShow);
		String filmNode = MOVIESHOW_ROOT + "/" + movieShow.getId();
		Preferences node = root.node(filmNode);
		
		String movieShowName = node.get("name", null);
		if (movieShowName != null) {
			return new Result(false, "Movie with ID " + movieShow.getId() + " already exist!");
		}
		
		node.put("name", movieShow.getMovieName());
		node.put("time", movieShow.getTime().toString());
		node.putInt("duration", movieShow.getDuration());
		
		return new Result(true, "Movie " + movieShow + " Created Successfully");
	}

	@Override
	public MovieShow findMovieShow(String id) {
		String movieNode = MOVIESHOW_ROOT + "/" + id;
		Preferences node = root.node(movieNode);
		
		String movieShowName = node.get("name", null);
		if (movieShowName == null) {
			return null;
		}
		
		LocalDateTime ldt = LocalDateTime.parse(node.get("time", null));
		int duration = node.getInt("duration", 0);
		MovieShow m = new MovieShow(id, movieShowName, ldt, duration);
		//Seat[][] seats = m.getCinemaHall().getSeats();
		String[] takenSits = node.get("taken_seats", "").split("[,]");
		
		for (int i = 0; i < takenSits.length; i++) {
			if (takenSits[i].isEmpty()) {
				break;
			}
			int row = Integer.parseInt(takenSits[i]);
			int seat = Integer.parseInt(takenSits[i+1]);
			Seat newSeat = new ModelFactory().createSeat(row, seat);
			newSeat.setSold(true);
			m.getCinemaHall().setSeat(newSeat);
			i++;
		}
		return m;
	}

	@Override
	public Result deleteMovieShow(String movieShowId) {
		String movieNode = MOVIESHOW_ROOT + "/" + movieShowId;
		Preferences node = root.node(movieNode);
		try {
			node.removeNode();
		} catch (BackingStoreException e) {
			return new Result(false, "Failed to delete movie show " + movieShowId);
		}
		return new Result(true, "OK");
	}

	@Override
	public Result updateMovieShow(MovieShow movieShow) {
		//Storing movie
		System.out.println("Updating Movie -> " + movieShow);
		String movieNode = MOVIESHOW_ROOT + "/" + movieShow.getId();
		Preferences node = root.node(movieNode);
		
		String movieShowName = node.get("name", null);
		if (movieShowName == null) {
			return new Result(false, "Movie with ID " + movieShow.getId() + " does not exist!");
		}
		
		node.put("name", movieShow.getMovieName());
		node.put("time", movieShow.getTime().toString());
		node.putInt("duration", movieShow.getDuration());
		
		return new Result(true, "Movie " + movieShow + " Created Successfully");
	}

	@Override
	public HashMap<String, MovieShow> getAllMovieShows() {
		String moviesRoot = MOVIESHOW_ROOT;
		Preferences moviesRootNode = root.node(moviesRoot);
		HashMap<String, MovieShow> res = new HashMap<String, MovieShow>();
		
		try {
			String[] movieShowNames = moviesRootNode.childrenNames();
			Arrays.stream(movieShowNames).forEach( (m) -> {
				res.put(m, this.findMovieShow(m));
			});
			
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Result deleteAllMovieShows() {
		String moviesRoot = MOVIESHOW_ROOT;
		Preferences moviesRootNode = root.node(moviesRoot);
		try {
			moviesRootNode.removeNode();
		} catch (BackingStoreException e) {
			return new Result(false, "Error when trying to delete all movies. " + e.getMessage());
		}
		return new Result(true, "OK");
	}
	
	@Override
	public Result commitOrder(Order order) {
		String movieNode = MOVIESHOW_ROOT + "/" + order.getMovieShow().getId();
		Preferences node = root.node(movieNode);
		
		String takenSeats = node.get("taken_seats", "");
		StringBuilder sb = new StringBuilder(takenSeats);
		order.getSeats().stream().forEach((seat) -> {
			if (sb.length() == 0) {
				sb.append((seat.getRow() + "," + seat.getSeatNumber()));
			} else {
				sb.append("," + seat.getRow() + "," + seat.getSeatNumber());
			}
		});
		node.put("taken_seats", sb.toString());
		return new Result(true, "OK");
	}
	
}
