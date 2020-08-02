package inventoryman;
import java.util.List;
import java.util.ArrayList;

public class Owner implements Comparable<Owner> {
	private String _name;
	private List<Book> _books = new ArrayList<Book>();
	private List<Music> _music = new ArrayList<Music>();
	
	public Owner(String name) {
		_name = name;
	}
	
	/* Following methods adds items to owner's lists of items (Separated by books and music) */
	public void addBook(Book book) {
		_books.add(book);
	}
	
	public void addMusic(Music music) {
		_music.add(music);
	}
		
	//Returns a copy of the list of owner's books
	public List<Book> getBooks(){
		List<Book> books = new ArrayList<Book>(_books);
		return books;
	}
	
	//Returns a copy of the list of owner's music
	public List<Music> getMusic(){
		List<Music> music = new ArrayList<Music>(_music);
		return music;
	}
	
	public String getName() {
		return _name;
	}
	
	/* Overridden hashCode and equals to allow for creating a Set/HashSet */
	public int hashCode() {
		return _name.hashCode();
	}
	
	public boolean equals(Object o) {
		Owner owner = (Owner)o;
		return _name.equals(owner._name);
	}

	//Owner's natural ordering is alphabetically by name.
	public int compareTo(Owner otherOwner) {
		return _name.compareTo(otherOwner._name);
	}
}
