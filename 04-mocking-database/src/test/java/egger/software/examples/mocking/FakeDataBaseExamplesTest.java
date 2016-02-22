package egger.software.examples.mocking;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class FakeDataBaseExamplesTest {

    @Test
    public void aLibraryCanBeQueriedForABook() {
        // given
        Database db = new FakeDataBase();
        Library library = new Library(db);

        // when / then
        assertThat(library.findBookById(1).getTitle(), is("Der Herr der Ringe"));
    }

    @Test
    public void queryingANotExistingBookIdReturnsNull() {
        // given
        Database db = new FakeDataBase();
        Library library = new Library(db);

        // when / then
        assertThat(library.findBookById(2).getTitle(), is(nullValue()));
    }

    @Test
    public void aBookCanBeRegisteredInALibrary() {
        // given
        Database db = new FakeDataBase();
        Library library = new Library(db);

        // when
        int id = library.registerBook(new Book("Der Hobbit"));

        // then
        assertThat(library.findBookById(id).getTitle(), is("Der Hobbit"));
    }


}
