package com.when.ship;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ShipSpec {

	private Ship ship;
	private Location location;
	private Planet planet;

	@Before
	public void beforeTest() {
		Point max = new Point(50, 50);
		location = new Location(new Point(21, 13), Direction.NORTH);
		List<Point> obstacles = new ArrayList<>();
		obstacles.add(new Point(44, 44));
		obstacles.add(new Point(45, 46));
		planet = new Planet(max, obstacles);
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

	@Test
	public void whenReceiveCommandsThenStopOnObstacle() {
		List<Point> obstacles = new ArrayList<>();
		obstacles.add(new Point(location.getX() + 1, location.getY()));
		ship.getPlanet().setObstacles(obstacles);
		Location expected = location.copy();
		expected.turnRight();
		// Moving forward would encounter an obstacle
		//expected.forward(new Point(0, 0), new ArrayList<Point>());
		expected.turnLeft();
		expected.backward(new Point(0, 0), new ArrayList<>());
		ship.receiveCommands("rflb");
		assertEquals(expected, ship.getLocation());
	}

	@Test
	public void whenReceiveCommandsThenOForOkAndXForObstacle() {
		List<Point> obstacles = new ArrayList<>();
		obstacles.add(new Point(location.getX() + 1, location.getY()));
		ship.getPlanet().setObstacles(obstacles);
		String status = ship.receiveCommands("rflb");
		assertEquals("OXOO", status);
	}
}
