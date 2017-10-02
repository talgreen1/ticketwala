package com.ticketwala.dao.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import com.ticketwala.command.api.Result;
import com.ticketwala.dao.api.MovieShowDao;
import com.ticketwala.model.MovieShow;
import com.ticketwala.model.Order;
import com.ticketwala.model.Seat;
import com.ticketwala.service.api.Configuration;

public class MovieShowDaoImpl implements MovieShowDao {

	private static final String MOVIESHOW_ROOT = "/movieshow";
	Preferences root = Preferences.userRoot();
	
	@Override
	public Result createMovieShow(MovieShow m) {		
		//Storing movie
		System.out.println("Storing Movie -> " + m);
		String filmNode = MOVIESHOW_ROOT + "/" + m.getId();
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
		String movieNode = MOVIESHOW_ROOT + "/" + id;
		Preferences node = root.node(movieNode);
		
		String movieShowName = node.get("name", null);
		if (movieShowName == null) {
			return null;
		}
		
		LocalDateTime ldt = LocalDateTime.parse(node.get("time", null));
		int duration = node.getInt("duration", 0);
		MovieShow m = new MovieShow(id, movieShowName, ldt, duration);
		Seat[][] seats = m.getCinemaHall().getSeats();
		String[] takenSits = node.get("taken_seats", "").split("[,]");
		
		for (int i = 0; i < takenSits.length; i++) {
			if (takenSits[i].isEmpty()) {
				break;
			}
			int row = Integer.parseInt(takenSits[i]);
			int seat = Integer.parseInt(takenSits[i+1]);
			seats[row][seat] = new Seat(row, seat, Configuration.SEAT_PRICE);
			seats[row][seat].setSold(true);
			i++;
		}
		return m;
	}

	@Override
	public Result deleteMovieShow(String id) {
		String movieNode = MOVIESHOW_ROOT + "/" + id;
		Preferences node = root.node(movieNode);
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
		String movieNode = MOVIESHOW_ROOT + "/" + m.getId();
		Preferences node = root.node(movieNode);
		
		String movieShowName = node.get("name", null);
		if (movieShowName == null) {
			return new Result(false, "Movie with ID " + m.getId() + " does not exist!");
		}
		
		node.put("name", m.getName());
		node.put("time", m.getTime().toString());
		node.putInt("duration", m.getDuration());
		
		return new Result(true, "Movie " + m + " Created Successfully");
	}

	@Override
	public HashMap<String, MovieShow> fetchAll() {
		String moviesRoot = MOVIESHOW_ROOT;
		Preferences moviesRootNode = root.node(moviesRoot);
		HashMap<String, MovieShow> res = new HashMap<String, MovieShow>();
		
		try {
			String[] movieShowNames = moviesRootNode.childrenNames();
			Arrays.stream(movieShowNames).forEach( (m) -> {
				res.put(m, this.fetchMovieShow(m));
			});
			
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public void deleteAll() {
		String moviesRoot = MOVIESHOW_ROOT;
		Preferences moviesRootNode = root.node(moviesRoot);
		try {
			moviesRootNode.removeNode();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void commitOrder(Order order) {
		String movieNode = MOVIESHOW_ROOT + "/" + order.getMovieShow().getId();
		Preferences node = root.node(movieNode);
		
		String takenSeats = node.get("taken_seats", "");
		StringBuilder sb = new StringBuilder(takenSeats);
		order.getSeats().stream().forEach((seat) -> {
			if (sb.length() == 0) {
				sb.append((seat.getRow() + "," + seat.getSeat()));
			} else {
				sb.append("," + seat.getRow() + "," + seat.getSeat());
			}
		});
		node.put("taken_seats", sb.toString());
	}

	public static void main(String[] args) throws BackingStoreException {
		String movieNode = MOVIESHOW_ROOT + "/" + "4dd4f";
		Preferences node = Preferences.userRoot().node(movieNode);
//		node.removeNode();
		System.out.println(node.get("taken_seats", ""));
	}
}
