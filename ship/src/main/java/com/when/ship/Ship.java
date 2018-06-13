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
		return location.forward(planet.getMax(), planet.getObstacles());
	}

	public boolean moveBackward() {
		return location.backward(planet.getMax(), planet.getObstacles());
	}

	public void turnLeft() {
		location.turnLeft();
	}

	public void turnRight() {
		location.turnRight();
	}

	public String receiveCommands(String commands) {
		StringBuilder builder = new StringBuilder();

		for (char command : commands.toCharArray()) {
			boolean status = true;
			switch (command) {
			case 'f':
				status = moveForward();
				break;
			case 'b':
				status = moveBackward();
				break;
			case 'l':
				turnLeft();
				break;
			case 'r':
				turnRight();
				break;
			}
			if (status) {
				builder.append("O");
			} else {
				builder.append("X");
			}
		}
		return builder.toString();
	}
}
