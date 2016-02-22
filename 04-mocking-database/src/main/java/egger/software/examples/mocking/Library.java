package egger.software.examples.mocking;

public class Library {

	private Database db;

	public Library(Database db) {
		this.db = db;
	}

	public int registerBook(Book book) {
		int id = db.nextId();
		db.insert("BOOK", id, book.getTitle());
		return id;
	}

	public Book findBookById(int id) {
		String title = db.select("BOOK", id);
		return new Book(title);
	}

}
