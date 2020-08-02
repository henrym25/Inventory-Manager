package inventoryman;

import java.util.List;
import java.util.ArrayList; 
import java.util.Set;
import java.util.HashSet;
import java.util.Comparator;

public class InventoryManImpl implements InventoryMan {
	private final String FLAT_NAME;
	private List<Item> _items = new ArrayList<Item>();
	private Set<Owner> _owners = new HashSet<Owner>();
	private Set<String> _creators = new HashSet<String>();
	
	public InventoryManImpl(String flatName) {
		FLAT_NAME = flatName;
	}

	public String addBook(String author, String title, String publicationYear, String publisher,
			String acquisitionDateStr, String owner, String costStr, String formatStr) {
		try {
			Owner bookOwner = new Owner(owner);
			//Book constructor throws exception if incorrect formatting is used for cost or year and invalid format type (formatStr).
			Book book = new Book(title, author, bookOwner, acquisitionDateStr, formatStr, costStr, publicationYear, publisher); 
			_items.add(book);
			bookOwner = getOwner(book, bookOwner);//Gets reference to Owner object from Set.
			bookOwner.addBook(book);
			_creators.add(author);
			return "SUCCESS";
		} catch (UserFormatException ufe) { //Exception when the user enters invalid formatting for cost or year
			return ufe.getMessage();
		} catch (IllegalArgumentException ife) { //Exception when invalid format type entered (Not Hardcover, PaperBack, CD or LP)
			return "Invalid Format type. Type must be one of the following: Hardcover, Paperback, MP3, CD";
		}
	}	

	public String addMusic(String artist, String title, String releaseDateStr, String acquisitionDateStr, 
			String owner, String costStr, String formatStr) {
		try {
			Owner musicOwner = new Owner(owner);
			//Music constructor throws exception if incorrect formatting is used for cost or year and invalid format type (formatStr).
			Music music = new Music(title, artist, musicOwner, acquisitionDateStr, formatStr, costStr, releaseDateStr);
			_items.add(music);
			musicOwner = getOwner(music, musicOwner);//Gets reference to Owner object from Set.
			musicOwner.addMusic(music);
			_creators.add(artist);		
			return "SUCCESS";
		} catch (UserFormatException ufe) {//Exception when the user enters invalid formatting for cost or year
			return ufe.getMessage();
		} catch (IllegalArgumentException ife) {//Exception when invalid format type entered (Not Hardcover, PaperBack, CD or LP)
			return "Invalid Format type. Type must be one of the following: Hardcover, Paperback, MP3, CD";
		}
	}

	public String getItemToDisplay(String creator, String title, String formatStr) {
		for(Item item: _items) { //Goes through all items in inventory
			String itemCreator = item.getCreator();
			String itemTitle = item.getTitle();
			String itemFormat = item.getFormat();
			if(itemCreator.equals(creator) && itemTitle.equals(title) && itemFormat.equals(formatStr)) { //Finds item with matching creator, title and format.
				return item.itemDetails();
			}
		}
		return "Item not found";
	}

	public List<String> getAll(String order) {
		List<Item> sortedItems = sortItems(_items, order); //Creates List of sorted items depending on order specified
		List<String> itemDetails = new ArrayList<String>();
		for(Item item: sortedItems) {//Generates item display string for each item
			itemDetails.add(item.itemDetails());
		}
		return itemDetails;
	}

	public List<String> getItemsAcquiredInYear(String year) {
		List<Item> itemsAcquiredInYear = new ArrayList<Item>();//List of items from year specified
		for(Item item: _items) {//Finds each item from year specified and adds to list
			if(item.getacqDate().substring(0, 4).equals(year)) {
				itemsAcquiredInYear.add(item);
			}
		}
		itemsAcquiredInYear = sortItems(itemsAcquiredInYear, "Acquisition"); //Sorts the list by acquisition date
		
		List<String> itemsAcquiredInYearStrings = new ArrayList<String>();//String representation for each item's details
		for(Item item: itemsAcquiredInYear) {//Generates string representation for each item and adds to list.
			itemsAcquiredInYearStrings.add(item.itemDetails());
		}
		return itemsAcquiredInYearStrings;
	}

	public List<String> getCreators() {
		List<String> creatorNames = new ArrayList<String>(_creators);//List of unique creator names
		creatorNames.sort(null);//Creators sorted alphabetically
		return creatorNames;
	}

	public List<String> getFlatReport() {
		List<Item> flatItems = new ArrayList<Item>(); //List of items in the flat. Sorted in order they appear in report.		
		List<String> flatReport = new ArrayList<String>(); //List of strings representing information for flat report
		flatReport.add(FLAT_NAME);

		List<Owner> owners = new ArrayList<Owner>(_owners); //List of owners, sorted in alphabetical order
		owners.sort(null);//Owners sorted alphabetically
		
		
		for(Owner owner: owners) {//Adds flat report for each owner's items
			List<Book> ownerBooks = owner.getBooks();//List of books the owner has
			List<Music> ownerMusic = owner.getMusic();//List of music the owner has
					
			//Sorts the owner's books and music according to creator, then by title.
			ownerBooks = sortItems(ownerBooks, "Creator");
			ownerMusic = sortItems(ownerMusic, "Creator");
			
			//Adds all items to flatReport so that all books appear before all music
			flatItems.addAll(ownerBooks);
			flatItems.addAll(ownerMusic);
		}

		for(Item item: flatItems) {//Generate flat report details for each item in the flat
			flatReport.add(item.flatReport());
		}
		return flatReport;
	}

	/** Helper methods **/

	/* Sorts List of items in order depending on what order is specified*/
	private <T extends Item> List<T> sortItems(List<T> list, String order){
		List<T> sortedList = new ArrayList<T>();//List to store sorted items
		sortedList.addAll(list);
		Comparator<T> comparator;
		//Selects comparator based on what field to sort by
		if(order.equals("Creator")) {
			comparator = new CreatorSort<T>();
		} else if(order.equals("Title")) {
			comparator = new TitleSort<T>();
		} else if(order.equals("Acquisition")) {
			comparator = new AcquisitionSort<T>();
		} else {
			comparator = null;//If not one of the 3 specified orders, doesn't sort
		}
		sortedList.sort(comparator);//Comparator sorts items based on order specified
		return sortedList;
	}
	
	/* Returns reference to owner from set of owners*/
	private <T> Owner getOwner(T item, Owner owner) {
		_owners.add(owner); //Owner is added to set if not already in set.
		List<Owner> owners = new ArrayList<Owner>(_owners); //Converts Set to list.
		owner = owners.get(owners.indexOf(owner)); //Gets reference to owner from list.
		return owner;
	}
}
