package com.ticketwala.dao.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import com.ticketwala.command.api.Result;
import com.ticketwala.dao.api.DataAccessService;
import com.ticketwala.model.MovieShow;
import com.ticketwala.model.Order;
import com.ticketwala.model.Seat;

public class DataAccessServiceImpl implements DataAccessService {

	private static final String MOVIESHOW_ROOT = "/movieshow";
	Preferences root = Preferences.userRoot();
	Preferences moviesRoot = root.node(MOVIESHOW_ROOT);

	@Override
	public Result createMovieShow(MovieShow movieShow) {		
		//Storing movie
		System.out.println("Storing Movie -> " + movieShow);
		String filmNode = MOVIESHOW_ROOT + "/" + movieShow.getId();
		
		MovieShow ms = this.findMovieShow(movieShow.getId());
		if (ms != null) {
			return new Result(false, "Movie with ID " + movieShow.getId() + " already exist!");
		}
		
		//Create the new Movie Nodes
		Preferences node = root.node(filmNode);
		node.put("name", movieShow.getMovieName());
		node.put("time", movieShow.getTime().toString());
		node.putInt("duration", movieShow.getDuration());
		flushChanges(node);
		return new Result(true, "Movie " + movieShow + " Created Successfully");
	}

	private void flushChanges(Preferences node) {
		try {
			node.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	public MovieShow findMovieShow(String id) {
		String movieNode = MOVIESHOW_ROOT + "/" + id;
		
		//Check if root of movies or movie root node exist
		Preferences node = null;
		try {
			if (!root.nodeExists(MOVIESHOW_ROOT)) {
				return null;
			} else if (root.nodeExists(movieNode)) {
				node = root.node(movieNode);
			} else {
				return null;
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
			return null;
		}
		
		String movieShowName = node.get("name", null);
		if (movieShowName == null) {
			return null;
		}

		LocalDateTime ldt = LocalDateTime.parse(node.get("time", null));
		int duration = node.getInt("duration", 0);
		MovieShow m = new MovieShow(id, movieShowName, ldt, duration);
		String[] takenSits = node.get("taken_seats", "").split("[,]");
		
		for (int i = 0; i < takenSits.length; i++) {
			if (takenSits[i].isEmpty()) {
				break;
			}
			int row = Integer.parseInt(takenSits[i]);
			int seat = Integer.parseInt(takenSits[i+1]);
			Seat s = m.getCinemaHall().getSeat(row, seat);
			s.setSold(true);
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
			node.flush();
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
		flushChanges(node);
		return new Result(true, "Movie " + movieShow + " Created Successfully");
	}

	@Override
	public HashMap<String, MovieShow> getAllMovieShows() {
		String moviesRoot = MOVIESHOW_ROOT;
		HashMap<String, MovieShow> res = new HashMap<String, MovieShow>();
		
		try {
			boolean nodeExists = root.nodeExists(moviesRoot);
			if (nodeExists) {
				Preferences moviesRootNode = root.node(moviesRoot);
				String[] movieShowNames = moviesRootNode.childrenNames();
				Arrays.stream(movieShowNames).forEach( (m) -> {
					res.put(m, this.findMovieShow(m));
				});
			} 
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
			moviesRootNode.flush();
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
		flushChanges(node);
		return new Result(true, "OK");
	}


	public static void main(String[] args) {
		DataAccessService das = new DataAccessServiceImpl();


		das.createMovieShow(new MovieShow("1", "Star trek", LocalDateTime.now(), 90));
		das.createMovieShow(new MovieShow("2", "Harry Potter", LocalDateTime.now(), 120));
	}
	public static void main1(String[] args) throws BackingStoreException {

		Preferences root = Preferences.userRoot();

		try {
			System.out.println(root.absolutePath());
			printStrArray(root.childrenNames());
			
			String screeningNodePath = "/screening";
			String movie1Path = screeningNodePath + "/movie1";
			
			Preferences screeningNode = null;
			Preferences movieNode = null;
			
			//Screening Node
			if (!root.nodeExists(screeningNodePath)) {
				System.out.println("screening node does not exist. Creating...");
				screeningNode = root.node(screeningNodePath);
			} else {
				System.out.println("screening node exists");
				screeningNode = root.node(screeningNodePath);
			}
			
			//Movie Node
			String movieName = "movie1";
			if (!screeningNode.nodeExists(movieName)) {
				System.out.println("movie node does not exist. Creating...");
				movieNode = screeningNode.node(movieName);
			} else {
				System.out.println("movie node exists");
				movieNode = screeningNode.node(movieName);
			}

			if (!screeningNode.nodeExists("/screening/movie1")) {
				System.out.println("-- movie1 node does not exist.");
			} else {
				System.out.println("-- movie1 node exist.");
			}

			if (!screeningNode.nodeExists("/screening/movie1/name")) {
				System.out.println("-- movie1.name node does not exist.");
			} else {
				System.out.println("-- movie1.name node exist.");
			}
			
			
//			movieNode.put("name", "movie1");
//			String s = root.node("screening").toString();
//			printStrArray(screeningNode.childrenNames());
//			
//			System.out.println(s);
			
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		
		
	}

	private static void printStrArray(String[] arr) throws BackingStoreException {
		Arrays.asList(arr).stream().forEach((s) -> System.out.println(s));
	}
	
	
}
