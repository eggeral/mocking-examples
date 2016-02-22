package egger.software.examples.mocking;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class SqlDataBaseExamplesTest {

    public static EmbeddedDataSource dataSource;

    @BeforeClass
    public static void prepareDatabase() throws SQLException {
        dataSource = new EmbeddedDataSource();
        dataSource.setCreateDatabase("create");
        dataSource.setDatabaseName("memory:library");

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement createIdsTableStatement = connection.prepareStatement(
                    "CREATE TABLE ids (nextId decimal(10,0), counterName varchar(50))");
            createIdsTableStatement.executeUpdate();

            PreparedStatement createBooksTableStatement = connection.prepareStatement(
                    "CREATE TABLE book (id decimal(10,0), value varchar(50))");
            createBooksTableStatement.executeUpdate();

            PreparedStatement insertIdStatement = connection.prepareStatement(
                    "INSERT INTO ids VALUES (10, 'main')");
            insertIdStatement.executeUpdate();

            PreparedStatement insertBookStatement = connection.prepareStatement(
                    "INSERT INTO book VALUES (1, 'Der Herr der Ringe')");
            insertBookStatement.executeUpdate();

        }
    }


    @Test
    public void aLibraryCanBeQueriedForABook() {
        // given
        Database db = new SqlDatabase(dataSource);
        Library library = new Library(db);

        // when / then
        assertThat(library.findBookById(1).getTitle(), is("Der Herr der Ringe"));
    }

    @Test
    public void queryingANotExistingBookIdReturnsNull() {
        // given
        Database db = new SqlDatabase(dataSource);
        Library library = new Library(db);

        // when / then
        assertThat(library.findBookById(2).getTitle(), is(nullValue()));
    }

    @Test
    public void aBookCanBeRegisteredInALibrary() {
        // given
        Database db = new SqlDatabase(dataSource);
        Library library = new Library(db);

        // when
        int id = library.registerBook(new Book("Der Hobbit"));

        // then
        assertThat(library.findBookById(id).getTitle(), is("Der Hobbit"));
    }


}
