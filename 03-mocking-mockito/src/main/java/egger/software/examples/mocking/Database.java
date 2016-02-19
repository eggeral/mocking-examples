package egger.software.examples.mocking;

import java.util.List;


public interface Database {


	void insert(String table, int id, String value);

	void update(String table, String oldValue, String newValue);

	String select(String table, int id);

	int nextId();

	List<String> getList();


}
