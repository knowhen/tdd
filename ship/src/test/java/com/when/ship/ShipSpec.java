package com.when.ship;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ShipSpec {

	private Ship ship;
	private Location location;
	private Planet planet;

	@Before
	public void beforeTest() {
		location = new Location(new Point(21, 13), Direction.NORTH);
		Point max = new Point(50, 50);
		planet = new Planet(max);
		ship = new Ship(location, planet);
	}

	@Test
	public void whenInstantedThenLocationIsSet() {
		assertEquals(location, ship.getLocation());
	}

	@Test
	public void whenMoveFowardThenFoward() {
		Location expected = location.copy();
		expected.forward();
		ship.moveForward();
		assertEquals(expected, ship.getLocation());
	}

	@Test
	public void whenMoveBackwordThenBackword() {
		Location expected = location.copy();
		expected.backward();
		ship.moveBackward();
		assertEquals(expected, ship.getLocation());
	}

	@Test
	public void whenTurnLeftThenLeft() {
		Location expected = location.copy();
		expected.turnLeft();
		ship.turnLeft();
		assertEquals(ship.getLocation(), expected);
	}

	@Test
	public void whenTurnRightThenRight() {
		Location expected = location.copy();
		expected.turnRight();
		ship.turnRight();
		assertEquals(ship.getLocation(), expected);
	}

	@Test
	public void whenReceiveCommandFThenForward() {
		Location expected = location.copy();
		expected.forward();
		ship.receiveCommands("f");
		assertEquals(expected, ship.getLocation());
	}

	@Test
	public void whenReceiveCommandsThenAllAreExcuted() {
		Location expected = location.copy();
		expected.turnRight();
		expected.forward();
		expected.turnLeft();
		expected.backward();
		ship.receiveCommands("rflb");
		assertEquals(expected, ship.getLocation());
	}

	@Test
	public void whenInstantedThenPlanetIsStored() {
		Point max = new Point(50, 50);
		Planet planet = new Planet(max);
		Ship ship = new Ship(location, planet);
		assertEquals(planet, ship.getPlanet());
	}

	@Test
	public void overPassEastBoundary() {
		location.setDirection(Direction.EAST);
		location.getPoint().setX(planet.getMax().getX());
		ship.receiveCommands("f");
		assertEquals(1, ship.getLocation().getX());
	}
	
}
