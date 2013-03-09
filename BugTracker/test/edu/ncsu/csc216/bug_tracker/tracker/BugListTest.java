package edu.ncsu.csc216.bug_tracker.tracker;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.bug_tracker.bug.Command;
import edu.ncsu.csc216.bug_tracker.bug.Command.CommandValue;
import edu.ncsu.csc216.bug_tracker.bug.TrackedBug;
import edu.ncsu.csc216.bug_tracker.xml.Bug;

/**
 * Test class for BugList
 * @author Josh Stetson
 */
public class BugListTest {
	
	/** BugList object */
	private BugList bugList;
	private Bug bugOne;
	private Bug bugTwo;
	private List<Bug> list;

	/**
	 * Setup method for edu.ncsu.csc216.bug_tracker.tracker.BugListTest
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		TrackedBug.setCounter(0);
		bugList = new BugList();
		bugList.addBug("Summary", "Josh Stetson");
		bugList.addBug("Summary", "Josh Stetson");
		bugList.addBug("Summary", "Josh Stetson");
		bugList.addBug("Summary", "Reporter");
		bugList.addBug("Summary", "Reporter");
		
		bugOne = bugList.getBugs().get(0).getXMLBug();
		bugTwo = bugList.getBugs().get(1).getXMLBug();
		list = new ArrayList<Bug>();
		list.add(bugOne);
		list.add(bugTwo);
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.tracker.BugList.BugList
	 */
	/*
	@Test
	public final void testBugList() {
		BugList list = new BugList();
		assertEquals(0, list.getBugs().size());
	}*/

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.tracker.BugList.addBug
	 */
	@Test
	public final void testAddBug() {
		assertEquals(5, bugList.getBugs().size());
		bugList.addBug("Summary", "Josh Stetson");
		assertEquals(6, bugList.getBugs().size());
		assertEquals("Josh Stetson", bugList.getBugs().get(0).getReporter());
		assertEquals("Summary", bugList.getBugs().get(0).getSummary());
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.tracker.BugList.addXMLBugs
	 */
	@Test
	public final void testAddXMLBugs() {
		bugList.addXMLBugs(list);
		assertEquals(7, bugList.getBugs().size());
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.tracker.BugList.getBugs
	 */
	@Test
	public final void testGetBugs() {
		assertEquals(5, bugList.getBugs().size());
		bugList.addBug("Summary", "Josh Stetson");
		assertEquals(6, bugList.getBugs().size());
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.tracker.BugList.getBugsByOwner
	 */
	@Test
	public final void testGetBugsByOwner() {
		List<TrackedBug> bugsByOwner = new ArrayList<TrackedBug>();
		bugsByOwner = bugList.getBugsByOwner("Owner");
		assertEquals(0, bugsByOwner.size());
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.tracker.BugList.getBugById
	 */
	@Test
	public final void testGetBugById() {
		assertEquals(4, bugList.getBugs().get(4).getBugId());
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.tracker.BugList.executeCommand
	 */
	@Test
	public final void testExecuteCommand() {
		Command c = new Command(CommandValue.CONFIRM, null, null, null);
		bugList.executeCommand(0, c);
		assertEquals("New", bugList.getBugById(0).getState().getStateName());
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.tracker.BugList.deleteBugById
	 */
	@Test
	public final void testDeleteBugById() {
		assertEquals(5, bugList.getBugs().size());
		bugList.deleteBugById(4);
		assertEquals(4, bugList.getBugs().size());
	}

}
