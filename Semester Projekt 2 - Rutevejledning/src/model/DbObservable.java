package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import database.DBConnection;

public class DbObservable implements Observable{
	private List<Observer> observers = new LinkedList<Observer>();
	private boolean state = true;
	private boolean previousState = state;
	private static boolean running;
	private static final String PING = "SELECT CURRENT_TIMESTAMP";
	private PreparedStatement ping;

	private void notifyObservers(boolean state) {
		for (Observer observer : observers) {
			observer.notifyMe(state);
		}
	}

	@Override
	public void addObserver(Observer obs) {
		observers.add(obs);		
	}

	@Override
	public void removeObser(Observer obs) {
		observers.add(obs);
	}

	private void stop() {
		running = false;
	}

	public void run() throws Exception {
		ping = DBConnection.getInstance().getConnection().prepareStatement(PING);
		if(!observers.isEmpty()) {
			running = true;
			while(running) {
				try {
					ping.executeQuery();
					notifyObservers(state);
				} catch (Exception e) {
					notifyObservers(false);
				}	
				Thread.sleep(1000);
			}
		}
	}
}	


