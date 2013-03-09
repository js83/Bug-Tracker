package edu.ncsu.csc216.bug_tracker.bug;

import java.util.ArrayList;
import edu.ncsu.csc216.bug_tracker.bug.Command.CommandValue;
import edu.ncsu.csc216.bug_tracker.bug.Command.Resolution;
import edu.ncsu.csc216.bug_tracker.xml.Bug;
import edu.ncsu.csc216.bug_tracker.xml.NoteList;

/**
 * Models a bug that is tracked by the system.  Contains all data 
 * related to a bugs status.
 * 
 * @author Josh Stetson
 */
public class TrackedBug {
	
	/** ID number of bug */
	private int bugId;
	/** Current state of bug */
	private BugState state;
	/** Bug summary */
	private String summary;
	/** Bug reporter */
	private String reporter;
	/** Bug owner */
	private String owner;
	/** Number of votes for bug */
	private int votes;
	/** Confirmation status of bug */
	private boolean confirmed;
	/** Resolution status of bug */
	private Resolution resolution;
	/** Notes for bug */
	private ArrayList<String> notes;
	/** Name of unconfirmed status */
	public static final String UNCONFIRMED_NAME = "Unconfirmed";
	/** Name of new status */
	public static final String NEW_NAME = "New";
	/** Name of assigned status */
	public static final String ASSIGNED_NAME = "Assigned";
	/** Name of resolved status */
	public static final String RESOLVED_NAME = "Resolved";
	/** Name of reopened status */
	public static final String REOPEN_NAME = "Reopen";
	/** Name of closed status */
	public static final String CLOSED_NAME = "Closed";
	/** Number of votes needed for bug to exit unconfirmed status */
	public static final int VOTE_THRESHOLD = 3;
	/** Counter for number of bugs entered in list */
	private static int counter = 0;
	/** Unconfirmed state of bug */
	private BugState unconfirmedState = new UnconfirmedState();
	/** New state of bug */
	private BugState newState = new NewState();
	/** Assigned state of bug */
	private BugState assignedState = new AssignedState();
	/** Resolved state of bug */
	private BugState resolvedState = new ResolvedState();
	/** Reopened state of bug */
	private BugState reopenState = new ReopenState();
	/** Closed state of bug */
	private BugState closedState = new ClosedState();
	
	/**
	 * Constructor for a TrackedBug created by user
	 * @param summary summary entered by reporter
	 * @param reporter person who reported the bug
	 */
	public TrackedBug(String summary, String reporter) {
		state = unconfirmedState;
		votes = 1;
		confirmed = false;
		bugId = counter;
		owner = null;
		this.summary = summary;
		this.reporter = reporter;
		resolution = null;
		notes = new ArrayList<String>();
	}
	
	/**
	 * Constructor for a TrackedBug from an XML file
	 * @param b bug from XML file
	 */
	public TrackedBug(Bug b) {
		if (b.getState().equals(UNCONFIRMED_NAME)) {
			state = unconfirmedState;
		} else if (b.getState().equals(NEW_NAME)) {
			state = newState;
		} else if (b.getState().equals(ASSIGNED_NAME)) {
			state = assignedState;
		} else if (b.getState().equals(RESOLVED_NAME)) {
			state = resolvedState;
		} else if (b.getState().equals(REOPEN_NAME)) {
			state = reopenState;
		} else if (b.getState().equals(CLOSED_NAME)) {
			state = closedState;
		}
		votes = b.getVotes();
		confirmed = b.isConfirmed();
		bugId = b.getId() + counter;
		owner = b.getOwner();
		summary = b.getSummary();
		reporter = b.getReporter();
		b.setResolution(b.getResolution());
		if (b.noteList != null) {
			notes = (ArrayList<String>) b.getNoteList().getNote();
		} else {
			notes = new ArrayList<String>();
		}
	}
	
	/**
	 * Increments the TrackedBug counter
	 */
	public static void incrementCounter() {
		counter++;
	}
	
	/**
	 * Sets the TrackedBug counter to a value
	 * @param counterValue value to set to counter
	 */
	public static void setCounter(int counterValue) {
		counter = counterValue;
	}
	
	/**
	 * Gets the ID of a bug
	 * @return ID of bug
	 */
	public int getBugId() {
		return bugId;
	}
	
	/**
	 * Gets the state of a bug
	 * @return State of bug
	 */
	public BugState getState() {
		return state;
	}
	
	/**
	 * Gets the resolution of a bug (object)
	 * @return Resolution of bug
	 */
	public Resolution getResolution() {
		return resolution;
	}
	
	/**
	 * Gets the name of the resolution of a bug (string)
	 * @return Resolution of bug
	 */
	public String getResolutionString() {
		if (state == closedState || state == resolvedState) {
			if (resolution.equals(Resolution.FIXED)) {
				return Command.R_FIXED;
			} else if (resolution.equals(Resolution.DUPLICATE)) {
				return Command.R_DUPLICATE;
			} else if (resolution.equals(Resolution.WONTFIX)) {
				return Command.R_WONTFIX;
			} else if (resolution.equals(Resolution.WORKSFORME)) {
				return Command.R_WORKSFORME;
			}
		}
		return null;
	}
	
	/**
	 * Gets the owner of a bug
	 * @return Owner of bug
	 */
	public String getOwner() {
		return owner;
	}
	
	/**
	 * Gets the summary of a bug
	 * @return Summary of bug
	 */
	public String getSummary() {
		return summary;
	}
	
	/**
	 * Gets the reporter of a bug
	 * @return Reporter of bug
	 */
	public String getReporter() {
		return reporter;
	}
	
	/**
	 * Gets the notes for a bug (array list)
	 * @return Notes for bug
	 */
	public ArrayList<String> getNotes() {
		return notes;
	}
	
	/**
	 * Gets the string form of notes for a bug (string)
	 * @return Notes for bug
	 */
	public String getNotesString() {
		String s = "";
		for (String element : notes) {
			s += element + "\n------\n";
		}
		return s;
	}
	
	/**
	 * Gets the number of votes for a bug
	 * @return Votes for bug
	 */
	public int getVotes() {
		return votes;
	}
	
	/**
	 * Gets the confirmation status of a bug
	 * @return Confirmation status of bug
	 */
	public boolean isConfirmed() {
		return confirmed;
	}
	
	/**
	 * Gets a TrackedBug and converts it to an XML bug format
	 * @return Bug in XML format
	 */
	public Bug getXMLBug() {
		Bug b = new Bug();
		b.setId(this.getBugId());
		b.setState(this.getState().getStateName());
		if (this.getSummary() != null) {
			b.setSummary(this.getSummary());
		}
		if (this.getReporter() != null) {
			b.setReporter(this.getReporter());
		}
		if (this.getOwner() != null) {
			b.setOwner(this.getOwner());
		}
		b.setVotes(this.getVotes());
		b.setConfirmed(this.isConfirmed());
		if (this.getResolutionString() != null) {
			b.setResolution(this.getResolutionString());
		}
		NoteList n = new NoteList();
		for (int i = 0; i < this.getNotes().size(); i++) {
			n.getNote().add(this.getNotes().get(i));
		}
		b.setNoteList(n);
		return b;
	}
	
	/**
	 * Updates the bug based on its current state and the user action
	 * @param command Command sent from the GUI 
	 */
	public void update(Command command) {
		state.updateState(command);
	}
	
	/**
	 * Sets the state of a bug
	 * @param stateString Name of the state to which the bug is assigned
	 */
	private void setState(String stateString) {
		if (stateString.equals(UNCONFIRMED_NAME)) {
			state = unconfirmedState;
		} else if (stateString.equals(NEW_NAME)) {
			state = newState;
		} else if (stateString.equals(ASSIGNED_NAME)) {
			state = assignedState;
		} else if (stateString.equals(RESOLVED_NAME)) {
			state = resolvedState;
		} else if (stateString.equals(REOPEN_NAME)) {
			state = reopenState;
		} else if (stateString.equals(CLOSED_NAME)) {
			state = closedState;
		}
	}
	
	/**
	 * Sets the resolution of a bug
	 * @param resolutionString Name of the resolution to which the bug is assigned
	 */
	private void setResolution(String resolutionString) {
			if (resolutionString == null) {
				resolution = null;
			} else if (resolutionString.equals(Command.R_FIXED)) {
				resolution = Resolution.FIXED;
			} else if (resolutionString.equals(Command.R_DUPLICATE)) {
				resolution = Resolution.DUPLICATE;
			} else if (resolutionString.equals(Command.R_WONTFIX)) {
				resolution = Resolution.WONTFIX; 
			} else if (resolutionString.equals(Command.R_WORKSFORME)) {
				resolution = Resolution.WORKSFORME;
			}
	}
	
	/**
	 * Inner class that handles transitions starting from the UnconfirmedState
	 * 
	 * @author Josh Stetson
	 */
	private class UnconfirmedState implements BugState {

		public void updateState(Command c) {
			if (c.getCommand() != CommandValue.VOTE && c.getCommand() != CommandValue.CONFIRM) {
				throw new UnsupportedOperationException();
			}
			if (c.getNote() != null) {
				notes.add(c.getNote());
			}
			if (c.getCommand() == CommandValue.VOTE) {
				votes++;
				if (votes >= VOTE_THRESHOLD) {
					if (owner == null) {
						setState(NEW_NAME);
					} else {
						setState(ASSIGNED_NAME);			
					}
				}
			}
			if (c.getCommand() == CommandValue.CONFIRM) {
				if (owner == null) {
					setState(NEW_NAME);	
				} else {
					if (c.getDeveloperId() != null) {
						setState(ASSIGNED_NAME);
					}
				}
				confirmed = true;
			}
		}

		public String getStateName() {
			return UNCONFIRMED_NAME;
		}
	}
	
	/**
	 * Inner class that handles transitions starting from the NewState
	 * 
	 * @author Josh Stetson
	 */
	private class NewState implements BugState {

		public void updateState(Command c) {
			if (c.getCommand() != CommandValue.POSSESSION) {
				throw new UnsupportedOperationException();
			}
			if (c.getNote() != null) {
				notes.add(c.getNote());
			}
			if (c.getCommand() == CommandValue.POSSESSION && c.getDeveloperId() != null) {
				owner = c.getDeveloperId();
				setState(ASSIGNED_NAME);
			} else {
				throw new IllegalArgumentException("Developer ID cannot be null");
			}
		}

		public String getStateName() {
			return NEW_NAME;
		}
	}
	
	/**
	 * Inner class that handles transitions starting from the AssignedState
	 * 
	 * @author Josh Stetson
	 */
	private class AssignedState implements BugState {

		public void updateState(Command c) {
			if (c.getCommand() != CommandValue.RESOLVED) {
				throw new UnsupportedOperationException();
			}
			if (c.getNote() != null) {
				notes.add(c.getNote());
			}
			Resolution r = c.getResolution();
			if (c.getCommand() == CommandValue.RESOLVED) {
				if (r == null) {
					throw new IllegalArgumentException("Resolution cannot be null");
				} else {
					if (r == Resolution.FIXED) {
						setState(RESOLVED_NAME);
						setResolution(Command.R_FIXED);
					} else {
						setState(CLOSED_NAME);
						if (r == Resolution.DUPLICATE) {
							setResolution(Command.R_DUPLICATE);
						} else if (r == Resolution.WONTFIX) {
							setResolution(Command.R_WONTFIX);
						} else if (r == Resolution.WORKSFORME) {
							setResolution(Command.R_WORKSFORME);
						}
					}
				}
			}
		}

		public String getStateName() {
			return ASSIGNED_NAME;
		}
	}
	
	/**
	 * Inner class that handles transitions starting from the ResolvedState
	 * 
	 * @author Josh Stetson
	 */
	private class ResolvedState implements BugState {

		public void updateState(Command c) {
			if (c.getCommand() != CommandValue.VERIFIED && c.getCommand() != CommandValue.REOPEN) {
				throw new UnsupportedOperationException();
			}
			if (c.getNote() != null) {
				notes.add(c.getNote());
			}
			if (c.getCommand() == CommandValue.VERIFIED) {
				setState(CLOSED_NAME);
			}
			if (c.getCommand() == CommandValue.REOPEN) {
				if (isConfirmed()) {
					setState(REOPEN_NAME);
				} else {
					setState(UNCONFIRMED_NAME);
				}
				setResolution(null);
			}
		}

		public String getStateName() {
			return RESOLVED_NAME;
		}
	}
	
	/**
	 * Inner class that handles transitions starting from the ReopenState
	 * 
	 * @author Josh Stetson
	 */
	private class ReopenState implements BugState {

		public void updateState(Command c) {
			if (c.getCommand() != CommandValue.POSSESSION && c.getCommand() != CommandValue.RESOLVED) {
				throw new UnsupportedOperationException();
			}
			if (c.getNote() != null) {
				notes.add(c.getNote());
			}
			Resolution r = c.getResolution();
			if (c.getCommand() == CommandValue.POSSESSION) {
				setState(ASSIGNED_NAME);
				owner = c.getDeveloperId();
			}
			if (c.getCommand() == CommandValue.RESOLVED) {
				if(r == Resolution.FIXED) {
					setState(RESOLVED_NAME);
					setResolution(Command.R_FIXED);
				} else {
					setState(CLOSED_NAME);
					if (r == Resolution.DUPLICATE) {
						setResolution(Command.R_DUPLICATE);
					} else if (r == Resolution.WONTFIX) {
						setResolution(Command.R_WONTFIX);
					} else if (r == Resolution.WORKSFORME) {
						setResolution(Command.R_WORKSFORME);
					}
				}
			}
		}

		public String getStateName() {
			return REOPEN_NAME;
		}
	}
	
	/**
	 * Inner class that handles transitions starting from the ClosedState
	 * 
	 * @author Josh Stetson
	 */
	private class ClosedState implements BugState {

		public void updateState(Command c) {
			if (c.getCommand() != CommandValue.REOPEN) {
				throw new UnsupportedOperationException();
			}
			if (c.getNote() != null) {
				notes.add(c.getNote());
			}
			if (c.getCommand() == CommandValue.REOPEN) {
				if (isConfirmed()) {
					setState(REOPEN_NAME);
				} else {
					setState(UNCONFIRMED_NAME);
				}
				setResolution(null);
			}
		}

		public String getStateName() {
			return CLOSED_NAME;
		}
	}
	
}




