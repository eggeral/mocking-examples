package egger.software.examples.mocking;

public interface Database {

	void insert(String table, int id, String value);

	String select(String table, int id);

	int nextId();

}
