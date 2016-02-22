package egger.software.examples.mocking;

import java.util.HashMap;
import java.util.Map;

public class FakeDataBase implements Database {
    private Map<String, String> fakeDb = new HashMap<>();
    private int currentId = 10;

    public FakeDataBase() {
        fakeDb.put("BOOK-1", "Der Herr der Ringe");
    }

    @Override
    public void insert(String table, int id, String value) {
        fakeDb.put(table + "-" + id, value);
    }

    @Override
    public String select(String table, int id) {
        return fakeDb.get(table + "-" + id);
    }

    @Override
    public int nextId() {
        return currentId++;
    }
}
