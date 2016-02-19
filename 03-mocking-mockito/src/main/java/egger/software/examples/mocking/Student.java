package egger.software.examples.mocking;

import java.util.ArrayList;
import java.util.List;


public class Student {

	private List<String> exams = new ArrayList<>();
	
	public void registerForExam(Exam exam) {
		if (exam.canRegister()) {
			exam.decreaseAvailableSeats();
			exams.add(exam.getName());
		}
		
	}

	public List<String> getExams() {
		return exams;
	}


}
