package edu.ncsu.csc216.bug_tracker.bug;

/**
 * Models a command sent from the GUI
 * @author szsz52
 *
 */
public class Command {

	/** Resolution status of Fixed */
	public static final String R_FIXED = "Fixed";
	/** Resolution status of Duplicate */
	public static final String R_DUPLICATE = "Duplicate";
	/** Resolution status of WontFix */
	public static final String R_WONTFIX = "WontFix";
	/** Resolution status of WorksForMe */
	public static final String R_WORKSFORME = "WorksForMe";
	
	private CommandValue c;
	
	private String developerId;
	
	private Resolution resolution;
	
	private String note;
	
	/**
	 * Command Constructor
	 * @param c Command value given
	 * @param developerId Developer ID entered by user
	 * @param resolution Resolution status of bug
	 * @param note Note entered by user
	 */
	public Command(CommandValue c, String developerId, Resolution resolution, String note) {
		if (c == null) {
			throw new IllegalArgumentException("CommandValue cannot be null");
		} else if (c == CommandValue.RESOLVED && resolution == null) {
			throw new IllegalArgumentException("Resolution cannot be null with Resolved command");
		} else if (c == CommandValue.POSSESSION && (developerId == null || developerId.equals(""))) {
			throw new IllegalArgumentException("Developer ID cannot be null with Possession command");
		} else {
			this.c = c;
			this.developerId = developerId;
			this.resolution = resolution;
			this.note = note;
		}
	}
	
	/**
	 * Returns the command value of the Command object
	 * @return Command value (restricted to CommandValue enumeration)
	 */
	public CommandValue getCommand() {
		return c;
	}
	
	/**
	 * Returns the Developer ID entered by the user
	 * @return ID of developer assigned to the bug
	 */
	public String getDeveloperId() {
		return developerId;
	}
	
	/**
	 * Returns the resolution status of the bug
	 * @return resolution status (restricted to Resolution enumeration)
	 */
	public Resolution getResolution() {
		return resolution;
	}
	
	/**
	 * Returns any notes entered by a user for the bug
	 * @return notes in the Note field
	 */
	public String getNote() {
		return note;
	}
	
	/**
	 * Enumeration for CommandValue values
	 */
	public static enum CommandValue { VOTE, POSSESSION, RESOLVED, VERIFIED, REOPEN, CONFIRM }
	
	/**
	 * Enumeration for Resolution values
	 */
	public static enum Resolution { FIXED, DUPLICATE, WONTFIX, WORKSFORME }
	
}
