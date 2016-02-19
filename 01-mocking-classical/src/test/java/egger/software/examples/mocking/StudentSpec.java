package egger.software.examples.mocking;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsEmptyCollection.empty;

import org.junit.Test;

public class StudentSpec {

	@Test
	public void aStudentCanRegisterForAnExamIfTheMaximumOfStudentsForTheExamIsNotExceeded() {
		//given
		Student student = new Student();
		Exam math = new Exam("Mathematics I", 3);
		
		//when
		student.registerForExam(math);
		
		//then
		assertThat(student.getExams(), hasItem("Mathematics I"));
		assertThat(math.canRegister(), is(true));
	}

	@Test
	public void aStudentCanNotRegisterForAnExamIfTheMaximumOfStudentsForTheExamIsExceeded() {
		//given
		Student student = new Student();
		Exam math = new Exam("Mathematics I", 0);
		
		//when
		student.registerForExam(math);
		
		//then
		assertThat(student.getExams(), is(empty()));
		assertThat(math.canRegister(), is(false));
	}

}
