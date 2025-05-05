import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*
 * @author Sebastian Arana
 */

public class Student implements Comparable<Student> {
	private final String FIRST_NAME;
	private final String LAST_NAME;
	private String preferredName;
	private final int AGE;
	private final Calendar BIRTH_DATE = Calendar.getInstance();
	private final Standing CLASS_STANDING;
	private final ArrayList<Course> COURSES;

	/*
	 * @param firstName, lastName - student name birthDate - Date object with
	 * student's date of birth standing - Standing enum type of class standing of
	 * student
	 * 
	 * @pre firstName, lastName, birthDate, standing != null
	 * 
	 * @post firstName, lastName, birthDate, classStanding, age, and courses are
	 * set. Age is calculated and courses is an empty arraylist.
	 */
	public Student(String firstName, String lastName, Date birthDate, Standing standing) {
		assert firstName != null;
		this.FIRST_NAME = firstName;
		assert lastName != null;
		this.LAST_NAME = lastName;
		assert birthDate != null;
		this.BIRTH_DATE.setTime(birthDate);
		assert standing != null;
		this.CLASS_STANDING = standing;

		// must subtract 1 for correct number of years not inclusive both ends
		AGE = Calendar.getInstance().get(Calendar.YEAR) - this.BIRTH_DATE.get(Calendar.YEAR) - 1;
		COURSES = new ArrayList<Course>();
	}

	/*
	 * @returns reference to final list courses
	 */
	public List<Course> getCourses() {
		return COURSES;
	}

	/*
	 * @returns list of courses with completed flag
	 */
	public List<Course> getCompletedCourses() {
		ArrayList<Course> completed = new ArrayList<Course>();
		for (Course c : this.COURSES) {
			if (c.isCompleted()) {
				completed.add(c);
			}
		}
		return completed;
	}

	/*
	 * @returns list of courses without completed flag
	 */
	public List<Course> getInProgressCourses() {
		ArrayList<Course> inProgress = new ArrayList<Course>();
		for (Course c : this.COURSES) {
			if (!c.isCompleted()) {
				inProgress.add(c);
			}
		}
		return inProgress;
	}

	/*
	 * @param preferredName - string of new preferred name
	 * 
	 * @pre preferredName != null
	 * 
	 * @post preferredName is set to copy of parameter
	 */
	public void setPreferredName(String preferredName) {
		assert preferredName != null;
		this.preferredName = preferredName + "";
	}

	/*
	 * @returns final age int
	 */
	public int getAge() {
		return this.AGE;
	}

	/*
	 * @returns final birthday calendar object
	 */
	public Calendar getBirthDate() {
		return BIRTH_DATE;
	}

	/*
	 * @returns first name string
	 */
	public String getFirstName() {
		return this.FIRST_NAME;
	}

	/*
	 * @returns last name string
	 */
	public String getLastName() {
		return this.LAST_NAME;
	}

	/*
	 * @returns preferredName string copy
	 */
	public String getPreferredName() {
		return this.preferredName + "";
	}

	/*
	 * @pre this.courses is not empty
	 * 
	 * @returns calculated GPA according to course list
	 */
	public double getGpa() {
		int totalCredits = 0;
		double totalPoints = 0.0;
		assert !COURSES.isEmpty();
		for (Course course : COURSES) {
			if (course.isCompleted()) {
				totalCredits += course.getCreditHours();
				Grade grade = course.getLetterGrade();
				if (grade == Grade.A) {
					totalPoints += course.getCreditHours() * 4.0;
				} else if (grade == Grade.B) {
					totalPoints += course.getCreditHours() * 3.0;
				} else if (grade == Grade.C) {
					totalPoints += course.getCreditHours() * 2.0;
				} else if (grade == Grade.D) {
					totalPoints += course.getCreditHours() * 1.0;
				} else
					totalPoints += course.getCreditHours() * 0.0;
			}
		}
		return totalPoints / totalCredits;
	}

	/*
	 * @param other - student object to compare to this
	 * 
	 * @pre other != null
	 */
	public int compareTo(Student other) {
		assert other != null;
		if (this.LAST_NAME == other.getLastName()) {
			if (this.FIRST_NAME == other.getFirstName()) {
				if (this.BIRTH_DATE.get(Calendar.YEAR) == other.getBirthDate().get(Calendar.YEAR)) {
					return 0;
				} else if (this.BIRTH_DATE.get(Calendar.YEAR) > other.getBirthDate().get(Calendar.YEAR)) {
					return 1;
				} else {
					return -1;
				}
			}
			return this.FIRST_NAME.compareTo(other.getFirstName());
		}
		return this.LAST_NAME.compareTo(other.getLastName());
	}

	/*
	 * @param c - new course object to add
	 * 
	 * @post - course list has a new course added
	 */
	public void addCourse(Course c) {
		this.COURSES.add(c);
	}
}
