package edu.ncsu.csc216.bug_tracker.bug;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.bug_tracker.bug.Command.CommandValue;
import edu.ncsu.csc216.bug_tracker.bug.Command.Resolution;
import edu.ncsu.csc216.bug_tracker.xml.Bug;

/**
 * Test class for TrackedBug
 * @author Josh Stetson
 */
public class TrackedBugTest {

	/** TrackedBug object */
	TrackedBug newBug;
	TrackedBug newBugTwo;
	TrackedBug newBugThree;
	
	Bug b;
	Bug newB;
	
	Command unconfirmedToNew;
	Command newToAssigned;
	Command assignedToResolved;
	
	/**
	 * Setup method for edu.ncsu.csc216.bug_tracker.bug.TrackedBugTest
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		TrackedBug.setCounter(0);
		newBug = new TrackedBug("This is the summary", "Josh Stetson");
		
		b = new Bug();
		b.setId(1);
		b.setState("New");
		b.setSummary("Summary");
		b.setReporter("Reporter");
		b.setOwner("Owner");
		b.setVotes(3);
		b.setConfirmed(true);
		b.setResolution(null);
		b.setNoteList(null);
		
		newBugTwo = new TrackedBug(b);
		

	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.bug.TrackedBug.TrackedBug
	 */
	/*@Test
	public final void testTrackedBugStringString() {
		TrackedBug testBug = new TrackedBug("Summary", "Reporter");
		assertEquals("Reporter", testBug.getReporter());
	}*/

	
	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.bug.TrackedBug.TrackedBug
	 */
	/*@Test
	public final void testTrackedBugBug() {
		TrackedBug testBug = new TrackedBug(b);
		assertEquals(1, testBug.getBugId());
		assertEquals("New", testBug.getState().getStateName());
		assertEquals("Summary", testBug.getSummary());
		assertEquals("Reporter", testBug.getReporter());
		assertEquals("Owner", testBug.getOwner());
		assertEquals(3, testBug.getVotes());
		assertEquals(true, testBug.isConfirmed());
		assertEquals(null, testBug.getResolution());
		assertEquals(0, testBug.getNotesString().length());	
	}*/

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.bug.TrackedBug.incrementCounter
	 */
	@Test
	public final void testIncrementCounter() {
		TrackedBug.incrementCounter();
		
		newBugThree = new TrackedBug("Summary", "Reporter");
		assertEquals(1, newBugThree.getBugId());
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.bug.TrackedBug.setCounter
	 */
	@Test
	public final void testSetCounter() {
		TrackedBug.setCounter(2);
		newBugThree = new TrackedBug("Summary", "Reporter");
		assertEquals(2, newBugThree.getBugId());
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.bug.TrackedBug.getBugId
	 */
	@Test
	public final void testGetBugId() {
		assertEquals(0, newBug.getBugId());
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.bug.TrackedBug.getState
	 */
	@Test
	public final void testGetState() {
		assertEquals("Unconfirmed", newBug.getState().getStateName());
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.bug.TrackedBug.getResolution
	 */
	@Test
	public final void testGetResolution() {
		assertEquals(newBug.getResolution(), null);
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.bug.TrackedBug.getResolutionString
	 */
	@Test
	public final void testGetResolutionString() {
		assertEquals(null, newBug.getResolutionString());
		Command c = new Command(CommandValue.CONFIRM, null, null, null);
		newBug.update(c);
		c = new Command(CommandValue.POSSESSION, "Owner", null, null);
		newBug.update(c);
		c = new Command(CommandValue.RESOLVED, "Owner", Resolution.FIXED, null);
		newBug.update(c);
		assertEquals("Fixed", newBug.getResolutionString());
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.bug.TrackedBug.getOwner
	 */
	@Test
	public final void testGetOwner() {
		assertEquals(newBug.getOwner(), null);
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.bug.TrackedBug.getSummary
	 */
	@Test
	public final void testGetSummary() {
		assertEquals("This is the summary", newBug.getSummary());
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.bug.TrackedBug.getReporter
	 */
	@Test
	public final void testGetReporter() {
		assertEquals(newBug.getReporter(), "Josh Stetson");
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.bug.TrackedBug.getNotes
	 */
	@Test
	public final void testGetNotes() {
		Command c = new Command(CommandValue.VOTE, null, null, "This is a note");
		newBug.update(c);
		assertEquals(1, newBug.getNotes().size());
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.bug.TrackedBug.getNotesString
	 */
	@Test
	public final void testGetNotesString() {
		Command c = new Command(CommandValue.VOTE, null, null, "This is a note");
		newBug.update(c);
		assertEquals("This is a note\n------\n", newBug.getNotesString());
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.bug.TrackedBug.getVotes
	 */
	@Test
	public final void testGetVotes() {
		assertEquals(newBug.getVotes(), 1);
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.bug.TrackedBug.isConfirmed
	 */
	@Test
	public final void testIsConfirmed() {
		assertFalse(newBug.isConfirmed());
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.bug.TrackedBug.TrackedBug
	 */
	@Test
	public final void testGetXMLBug() {
		assertEquals(0,  newBug.getXMLBug().getId());
	}

	/**
	 * Test method for edu.ncsu.csc216.bug_tracker.bug.TrackedBug.update
	 */
	@Test
	public final void testUpdate() {
		Command c = new Command(CommandValue.CONFIRM, null, null, null);
		newBug.update(c);
		assertEquals("New", newBug.getState().getStateName());
		
		c = new Command(CommandValue.POSSESSION, "Owner", null, null);
		newBug.update(c);
		assertEquals("Assigned", newBug.getState().getStateName());
		
	}
	

}
