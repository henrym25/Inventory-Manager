package inventoryman;
import java.util.Comparator;

public class CreatorSort<T extends Item> implements Comparator<T>{
	
	/* Comparator sorts by creator(alphabetical), then title(alphabetical), then acquisition date(chronological)*/
	public int compare(Item item1, Item item2) {
		int equality = item1.getCreator().compareTo(item2.getCreator()); //Checks if creator the same.
		if(equality == 0) {//If the creators are the same, checks if title is the same
			equality = item1.getTitle().compareTo(item2.getTitle());
		}
		if(equality == 0) {//If title is the same, sorts by acquisition date
			equality = item1.getacqDate().compareTo(item2.getacqDate());
		}
		return equality;
	}

}
