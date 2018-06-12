package com.when.ship;

public class Ship {

	private Location location;
	private Planet planet;

	public Ship(Location location, Planet planet) {
		this.location = location;
		this.planet = planet;
	}

	public Location getLocation() {
		return location;
	}

	public Planet getPlanet() {
		return planet;
	}

	public boolean moveForward() {
		return location.forward(planet.getMax());
	}

	public boolean moveBackward() {
		return location.backward(planet.getMax());
	}

	public void turnLeft() {
		location.turnLeft();
	}

	public void turnRight() {
		location.turnRight();
	}

	public void receiveCommands(String commands) {
		for (char command : commands.toCharArray()) {
			switch (command) {
			case 'f':
				moveForward();
				break;
			case 'b':
				moveBackward();
				break;
			case 'l':
				turnLeft();
				break;
			case 'r':
				turnRight();
				break;
			}
		}
	}
}