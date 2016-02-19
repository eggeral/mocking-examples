package egger.software.examples.mocking;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsEmptyCollection.empty;

import static org.mockito.Mockito.*;

import org.junit.Test;

public class StudentSpec {

	@Test
	public void aStudentCanRegisterForAnExamIfTheMaximumOfStudentsForTheExamIsNotExceeded() {
		// given
		Student student = new Student();
		Exam math = mock(Exam.class);
		when(math.canRegister()).thenReturn(true);
		when(math.getName()).thenReturn("Mathematics I");
		
		// when
		student.registerForExam(math);

		// then
		verify(math).canRegister();
		verify(math).getName();
		verify(math).decreaseAvailableSeats();
		assertThat(student.getExams(), hasItem("Mathematics I"));
	}

	@Test
	public void aStudentCanNotRegisterForAnExamIfTheMaximumOfStudentsForTheExamIsExceeded() {
		// given
		Student student = new Student();
		Exam math = mock(Exam.class);
		when(math.canRegister()).thenReturn(false);

		// when
		student.registerForExam(math);

		// then
		verify(math).canRegister();
		assertThat(student.getExams(), is(empty()));
	}

}
