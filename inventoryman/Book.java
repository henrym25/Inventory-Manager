package inventoryman;

public class Book extends Item {
	private String _pubYear; 
	private String _publisher;
	
	public Book(String title, String creator, Owner owner, String acqDate, String format, String cost, String pubYear, String publisher) 
			throws UserFormatException, IllegalArgumentException {
		super(title, creator, owner, acqDate, format, cost);
		_pubYear = pubYear;
		_publisher = publisher;
	}
	
	//Generate item details according to getItemToDisplay format
	public String itemDetails() {
		String title = getTitle();
		String creator = getCreator();
		String owner = getOwner();
		String acqDate = getacqDate();
		String format = getFormat();
		String cost = getCost();
		
		return String.format("%s, '%s'. (%s, %s). [%s, %s, %s, %s]", 
				creator, title, _pubYear, _publisher, format, owner, acqDate, cost);
	}
	
	//Generate item details according to flatReport format
	public String flatReport() {
		String owner = getOwner();
		String creator = getCreator();
		String title = getTitle();
		String format = getFormat();
		
		return String.format("%s: %s, '%s'. (%s)", owner, creator, title, format);
	}
	
}
