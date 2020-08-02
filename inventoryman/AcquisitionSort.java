package inventoryman;
import java.util.Comparator;

public class AcquisitionSort<T extends Item> implements Comparator<T>{
	
	/* Comparator sorts by acquisition date(chronological), then creator(alphabetical), then title(alphabetical)*/
	public int compare(Item item1, Item item2) {
		int equality = item1.getacqDate().compareTo(item2.getacqDate());//Checks if acquisition date is the same
		if(equality == 0) {//If the acquisition dates are the same, checks if creators are the same.
			equality = item1.getCreator().compareTo(item2.getCreator());
		}
		if(equality == 0) {//If creators are the same, sorts by title	
			equality = item1.getTitle().compareTo(item2.getTitle());
		}
		return equality;
	}
}
