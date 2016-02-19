package egger.software.examples.mocking;

public class Exam {

    private String name;
    private int availableExamSeats;

    public Exam(String name, int availableExamSeats) {
        this.name = name;
        this.availableExamSeats = availableExamSeats;
    }

    public boolean canRegister() {
        return (availableExamSeats > 0);
    }

    public void decreaseAvailableSeats() {
        availableExamSeats--;
    }

    public String getName() {
        return name;
    }
}
