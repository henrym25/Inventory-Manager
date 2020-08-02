package inventoryman;
import java.util.Comparator;


public class TitleSort<T extends Item> implements Comparator<T>{

	/* Comparator sorts by title(alphabetical), then creator(alphabetical), then acquisition date(chronological)*/
	public int compare(Item item1, Item item2) {
		int equality = item1.getTitle().compareTo(item2.getTitle());
		if(equality == 0) {//If the titles are the same, checks whether creator is also the same
			equality = item1.getCreator().compareTo(item2.getCreator()); 
		}
		if(equality == 0) {//If the creator is also the same, sorts by acquisition date.
			equality = item1.getacqDate().compareTo(item2.getacqDate());
		}
		return equality;
	}
}
