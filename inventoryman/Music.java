package inventoryman;

public class Music extends Item {
	private String _releaseDate;

	
	public Music(String title, String creator, Owner owner, String acqDate, String format, String cost, String releaseDate) 
			throws UserFormatException, IllegalArgumentException {
		super(title, creator, owner, acqDate, format, cost);
		checkDate(releaseDate);
		_releaseDate = releaseDate;   

	}
	//Generate item details according to getItemToDisplay format
	public String itemDetails() {
		String title = getTitle();
		String creator = getCreator();
		String owner = getOwner();
		String acqDate = getacqDate();
		String format = getFormat();
		String cost = getCost();
		
		return String.format("\'%s\' by %s, %s. (%s, %s, %s, %s)", 
				title, creator, _releaseDate, format, owner, acqDate, cost);
	}
	
	//Generate item details according to flatReport format
	public String flatReport() {
		String title = getTitle();
		String creator = getCreator();
		String owner = getOwner();
		String format = getFormat();
		
		return String.format("%s: '%s' by %s (%s)", owner, title, creator, format);
	}
	

}
