package edu.ncsu.csc216.bug_tracker.tracker;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.bug_tracker.bug.Command;
import edu.ncsu.csc216.bug_tracker.bug.TrackedBug;
import edu.ncsu.csc216.bug_tracker.xml.Bug;

/**
 * Represents a list of bugs in the BugTrackerModel
 * 
 * @author Josh Stetson
 */
public class BugList {
	
	/**List of TrackedBugs */
	private List<TrackedBug> bugs;
	
	/**
	 * Constructor for BugList
	 * Creates a new list of TrackedBugs
	 */
	public BugList(){
		TrackedBug.setCounter(0);
		bugs = new ArrayList<TrackedBug>();
	}
	
	/**
	 * Adds bugs to a BugList created by the user
	 * @param summary summary entered by user
	 * @param reporter reporter entered by user
	 * @return current size of BugList
	 */
	public int addBug(String summary, String reporter) {
		TrackedBug b = new TrackedBug(summary, reporter);
		bugs.add(b);
		TrackedBug.incrementCounter();
		return b.getBugId();
	}
	
	/**
	 * Adds bugs from an XML file to a BugList
	 * @param xmlBugs List of xmlBugs to be added to BugList
	 */
	public void addXMLBugs(List<Bug> xmlBugs) {
		if (xmlBugs != null) {
			for (int i = 0; i < xmlBugs.size(); i++) {
				if (xmlBugs.get(i) != null) {
					bugs.add(new TrackedBug(xmlBugs.get(i)));
				}
			}
			int maxId = 0;
			for (TrackedBug element : bugs) {
				int currentId = element.getBugId();
				if (currentId > maxId) {
					maxId = currentId;
				}
			}
			TrackedBug.setCounter(maxId + 1);
		}
	}
	
	/**
	 * Gets a list of TrackedBugs
	 * @return list of TrackedBugs
	 */
	public List<TrackedBug> getBugs() {
		return bugs;
	}
	
	/**
	 * Filters the current BugList by owner specified by user input
	 * @param owner owner to filter the BugList by
	 * @return List of TrackedBugs with the same owner specified
	 */
	public List<TrackedBug> getBugsByOwner(String owner) {
		if (owner == null) {
			throw new IllegalArgumentException("Must enter an owner's name");
		}
		List<TrackedBug> bugsByOwner = new ArrayList<TrackedBug>();
		for (int i = 0; i < bugs.size(); i++) {
			TrackedBug currentBug = bugs.get(i);
			if (currentBug.getOwner() != null && currentBug.getOwner().equals(owner)) {
				bugsByOwner.add(currentBug);	
			}
		}
		return bugsByOwner;
	}
	
	/**
	 * Gets a TrackedBug based on a given ID
	 * @param bugId ID of bug desired
	 * @return TrackedBug at given ID
	 */
	public TrackedBug getBugById(int bugId) {
		for (int i = 0; i < bugs.size(); i++) {
			if (bugs.get(i).getBugId() == bugId) {
				return bugs.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Executes a command based on the state of the bug with ID passed to the method
	 * @param bugId ID of bug to be updated
	 * @param c command to be executed based on state of bug with ID passed to method
	 */
	public void executeCommand(int bugId, Command c) {
		for (int i = 0; i < bugs.size(); i++) {
			if (bugs.get(i).getBugId() == bugId) {
				bugs.get(i).update(c);
			}
		}
	}
	
	/**
	 * Deletes a bug from a list by its given ID
	 * @param bugId ID of bug to delete
	 */
	public void deleteBugById(int bugId) {
		for (int i = 0; i < bugs.size(); i++) {
			if (bugs.get(i).getBugId() == bugId) {
				bugs.remove(i);
			}
		}
	}

}
