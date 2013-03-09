package edu.ncsu.csc216.bug_tracker.bug;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Command
 * @author Josh Stetson
 */
public class CommandTest {

	/** Command object */
	private Command testCommand;
	
	/**
	 * Setup method for edu.ncsu.csc216.bug_tracker.bug.CommandTest
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		testCommand = new Command(Command.CommandValue.RESOLVED, "JRS", Command.Resolution.FIXED, "This is a test");
	}


	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.Command.Command
	 */
	/*@Test
	public final void testCommand() {
		Command c = new Command(Command.CommandValue.VOTE, null, null, "This is a test");
		assertEquals(c.getNote(), testCommand.getNote());
		assertFalse(testCommand.getCommand().equals(c.getCommand()));
		assertFalse(testCommand.getResolution().equals(c.getResolution()));
	}
	*/

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.Command.getCommand
	 */
	@Test
	public final void testGetCommand() {
		assertEquals(testCommand.getCommand(), Command.CommandValue.RESOLVED);
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.Command.getDeveloperId
	 */
	@Test
	public final void testGetDeveloperId() {
		assertEquals(testCommand.getDeveloperId(), "JRS");
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.Command.getResolution
	 */
	@Test
	public final void testGetResolution() {
		assertEquals(testCommand.getResolution(), Command.Resolution.FIXED);
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.Command.getNote
	 */
	@Test
	public final void testGetNote() {
		assertEquals(testCommand.getNote(), "This is a test");
	}

}
