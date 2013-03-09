package edu.ncsu.csc216.bug_tracker.tracker;

import edu.ncsu.csc216.bug_tracker.bug.Command;
import edu.ncsu.csc216.bug_tracker.bug.TrackedBug;
import edu.ncsu.csc216.bug_tracker.xml.BugIOException;
import edu.ncsu.csc216.bug_tracker.xml.BugReader;
import edu.ncsu.csc216.bug_tracker.xml.BugWriter;

/**
 * Maintains the BugList and handles commands from the GUI
 * 
 * @author Josh Stetson
 */
public class BugTrackerModel {
	
	/** Single instance of BugTrackerModel */
	private static BugTrackerModel singleton;
	/** List of bugs to be maintained */
	private BugList bugList;
	
	/**
	 * Constructor for BugTrackerModel (singleton)
	 */
	private BugTrackerModel() {
		bugList = new BugList();
	}
	
	/**
	 * Creates an instance of BugTrackerModel if one has not been created
	 * @return instance of BugTrackerModel
	 */
	public static BugTrackerModel getInstance() {
		if (singleton == null) {
			singleton = new BugTrackerModel();
		}
		return singleton;
	}
	
	/**
	 * Saves the current BugList to an xml file
	 * @param fileName Name of file to save to
	 */
	public void saveBugsToFile(String fileName) {
		BugWriter writer = new BugWriter(fileName);
		for (int i = 0; i < bugList.getBugs().size(); i++) {
			writer.addItem(bugList.getBugs().get(i).getXMLBug());
		}
		try {
			writer.marshal();
		} catch (BugIOException e) {
			//e.printStackTrace();
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Loads an xml file by adding the bugs to the buglist
	 * @param fileName Name of file to load
	 */
	public void loadBugsFromFile(String fileName) {
		try {
			BugReader reader = new BugReader(fileName);
			bugList.addXMLBugs(reader.getBugs());
		} catch (BugIOException e) {
			//e.printStackTrace();
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Creates a new empty buglist
	 */
	public void createNewBugList() {
		bugList = new BugList();
		TrackedBug.setCounter(0);
	}
	
	/**
	 * Populates an array of buglist data displayed in the GUI for all bugs in the buglist
	 * @return array of buglist data
	 */
	public Object[][] getBugListAsArray() {
		Object[][] bugListArray = new Object[bugList.getBugs().size()][3];
		for (int i = 0; i < bugList.getBugs().size(); i++) {
			for (int j = 0; j < 3; j++) {
				if (j == 0) {
					bugListArray[i][j] = bugList.getBugs().get(i).getBugId();
				} else if (j == 1) {
					bugListArray[i][j] = bugList.getBugs().get(i).getState().getStateName();
				} else if (j == 2) {
					bugListArray[i][j] = bugList.getBugs().get(i).getSummary();
				}
			}
		}
		return bugListArray;
	}
	
	/**
	 * Populates an array of buglist data diplayed in the GUI based on bug owner
	 * @param owner owner of bugs to compare to for populating array
	 * @return array of buglist data filtered by owner
	 */
	public Object[][] getBugListByOwnerAsArray(String owner) {
		if (owner == null) {
			throw new IllegalArgumentException("Must enter an owner's name");
		}
		int ownerCount = 0;
		for (int i = 0; i < bugList.getBugs().size(); i++) {
			if (bugList.getBugs().get(i).getOwner() != null && bugList.getBugs().get(i).getOwner().equals(owner)) {
				ownerCount++;
			}
		}
		Object[][] bugListArray = new Object[ownerCount][3];
		int activeRow = 0;
		for (int i = 0; i < bugList.getBugs().size(); i++) {
			for (int j = 0; j < 3; j++) {
				if (bugList.getBugs().get(i).getOwner() != null && bugList.getBugs().get(i).getOwner().equals(owner)) {
					if (j == 0) {
						bugListArray[activeRow][j] = (Integer)bugList.getBugs().get(i).getBugId();
					} else if (j == 1) {
						bugListArray[activeRow][j] = bugList.getBugs().get(i).getState().getStateName();
					} else if (j == 2) {
						bugListArray[activeRow][j] = bugList.getBugs().get(i).getSummary();
						activeRow++;
					}
				}
			}
		}
		return bugListArray;
	}

	/**
	 * Gets a TrackedBug based on its ID
	 * @param bugId ID of bug desired
	 * @return TrackedBug
	 */
	public TrackedBug getBugById(int bugId) {
		return bugList.getBugById(bugId);
	}
	
	/**
	 * Sends a command to a bug based on its ID and current state
	 * @param bugId ID of bug to execute command on
	 * @param c command to be executed based on state of bug
	 */
	public void executeCommand(int bugId, Command c) {
		bugList.executeCommand(bugList.getBugById(bugId).getBugId(), c);
	}
	
	/**
	 * Deletes a bug from the buglist based on its ID
	 * @param bugId ID of bug to be deleted
	 */
	public void deleteBugById(int bugId) {
		bugList.deleteBugById(bugId);
	}
	
	/**
	 * Adds a bug to the buglist 
	 * @param summary summary entered by user
	 * @param reporter name of bug reporter
	 */
	public void addBugToList(String summary, String reporter) {
		bugList.addBug(summary, reporter);
	}

}
