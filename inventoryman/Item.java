package inventoryman;


public abstract class Item {
	
	public enum ItemFormat { //Represents available item formats for both books and music
		Hardcover, Paperback, CD, LP;
	}
	
	private String _title; 
	private String _creator; 
	private Owner _owner; 
	private String _acqDate;
	private ItemFormat _format;
	private String _cost;
	
	
	public Item(String title, String creator, Owner owner, String acqDate, String format, String cost) 
			throws UserFormatException, IllegalArgumentException {
		checkDate(acqDate);
		checkCost(cost);
		_format = ItemFormat.valueOf(format);
		_title = title; 
		_creator = creator;
		_owner = owner;
		_acqDate = acqDate;
		_cost = cost;
	}
	
	public abstract String itemDetails();	//Generate item details according to getItemToDisplay format
	public abstract String flatReport();	//Generate item details according to flatReport format

	
	/* Checks the date complies with ISO8601 format*/
	public void checkDate(String date) throws UserFormatException {
		String pattern = "\\d{4}-\\d{2}-\\d{2}";
		if (!date.matches(pattern)) {
			throw new UserFormatException("ERROR: Invalid date format. The date must follow the format yyyy-mm-dd");	
		}
	}
	
	/* Checks the cost complies with $" dollars "." cents, where dollars is a sequence of 1 or more digits and cents is always 2 digits*/
	public void checkCost(String cost) throws UserFormatException {
		String pattern = "\\$\\d+\\.\\d{2}";
		if (!cost.matches(pattern)) {
			throw new UserFormatException("ERROR: Invalid cost format. The cost must follow the format $dollars.cents. Dollars has to have atleast one digit and cost exactly 2 digits");
		}
	}
	
	public String getTitle() {
		return _title;
	}
	
	public String getCreator() {
		return _creator;
	}
	
	public String getOwner() {
		return _owner.getName();
	}
	
	public String getacqDate() {
		return _acqDate;
	}
	
	public String getFormat() {
		return _format.name();
	}
	
	public String getCost() {
		return _cost;
	}
}
