package edu.ncsu.csc216.bug_tracker.tracker;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.bug_tracker.bug.Command;
import edu.ncsu.csc216.bug_tracker.bug.Command.CommandValue;
import edu.ncsu.csc216.bug_tracker.bug.TrackedBug;

/**
 * Test class for BugTrackerModel
 * @author Josh Stetson
 */
public class BugTrackerModelTest {
	
	/** BugTrackerModel object */
	private BugTrackerModel model;

	/**
	 * Setup method for edu.ncsu.csc216.bug_tracker.tracker.BugTrackerModelTest
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		TrackedBug.setCounter(0);
		model = BugTrackerModel.getInstance();
		model.addBugToList("Summary", "Reporter");
		model.addBugToList("Summary2", "Reporter2");
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.tracker.BugTrackerModel.getInstance
	 */
	@Test
	public final void testGetInstance() {
		assertEquals(1, BugTrackerModel.getInstance().getBugById(1).getBugId());
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.tracker.BugTrackerModel.saveBugsToFile
	 */
	@Test
	public final void testSaveBugsToFile() {
		try {
			model.saveBugsToFile("\"");
			fail("saveBugsToFile() should throw an exception");
		} catch (IllegalArgumentException e) {
			// Tests should catch an exception and pass here
		}
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.tracker.BugTrackerModel.loadBugsFromFile
	 */
	@Test
	public final void testLoadBugsFromFile() {
		try {
			model.loadBugsFromFile("badFileName");
			fail("loadBugsFromFile() should throw an exception");
		} catch (IllegalArgumentException e) {
			// Test should catch the exception and pass here
		}
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.tracker.BugTrackerModel.createNewBugList
	 */
	@Test
	public final void testCreateNewBugList() {
		model.createNewBugList();
		model.addBugToList("Summary",  "Reporter");
		assertEquals("Summary", model.getBugById(0).getSummary());
		model.addBugToList("Summary2",  "Reporter2");
		assertEquals("Summary2", model.getBugById(1).getSummary());
		model.createNewBugList();
		model.addBugToList("Summary3",  "Reporter3");
		assertEquals("Summary3", model.getBugById(0).getSummary());
		model.createNewBugList();
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.tracker.BugTrackerModel.getBugListAsArray
	 */
	@Test
	public final void testGetBugListAsArray() {
		Object[][] bugList = model.getBugListAsArray();
		assertEquals("Summary", bugList[0][2]);
		assertEquals(0, bugList[0][0]);

	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.tracker.BugTrackerModel.getBugListByOwnerAsArray
	 */
	@Test
	public final void testGetBugListByOwnerAsArray() {
		Object[][] byOwner = model.getBugListByOwnerAsArray("me");
		assertEquals(0, byOwner.length);
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.tracker.BugTrackerModel.getBugById
	 */
	@Test
	public final void testGetBugById() {
		assertEquals("Summary2", model.getBugById(1).getSummary());
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.tracker.BugTrackerModel.executeCommand
	 */
	@Test
	public final void testExecuteCommand() {
		Command c = new Command(CommandValue.VOTE, null, null, null);
		model.executeCommand(0, c);
		assertEquals(2, model.getBugById(0).getVotes());
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.tracker.BugTrackerModel.deleteBugById
	 */
	@Test
	public final void testDeleteBugById() {
		model.deleteBugById(1);
		assertNull(model.getBugById(1));
	}
	
	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.tracker.BugTrackerModel.addBugToList
	 */
	@Test
	public final void testAddBugToList() {
		model.addBugToList("Test Summary", "Reporter");
		assertEquals("Test Summary", model.getBugById(2).getSummary());
	}

}
