import java.util.ArrayList;
import java.util.Comparator;

/*
 * @author Sebastian Arana
 */
public class Course {
	public static ChronologicalCompare CHRONOLOGICAL;
	public static AlphabeticalCompare ALPHABETICAL;
	
	private final String courseNum;
	private final ArrayList<Assignment> assignments = new ArrayList<Assignment>();
	private final Semester sem;
	private final int year;
	private int creditHours;
	private boolean isCompleted = false;
	private double percentage;
	private Grade letterGrade;

	/*
	 * @param courseNum -- name / number of course
	 * 		sem -- Semester enum FALL or SPRING
	 * 		year -- int year of course
	 * 		credits -- int credit hour value
	 * 
	 * @pre courseNum, sem != null, credits >= 0
	 * 
	 * @post courseNum, sem, year, credits are set, percentage
	 * 	set to -1, and comparators created.
	 */
	public Course(String courseNum, Semester sem, int year, int credits) {
		assert courseNum != null;
		this.courseNum = courseNum;

		assert sem != null;
		this.sem = sem;

		this.year = year;

		assert credits >= 0;
		this.creditHours = credits;

		this.percentage = -1.0;
		
		CHRONOLOGICAL = new ChronologicalCompare();
		ALPHABETICAL = new AlphabeticalCompare();
	}

	/*
	 * @return creditHours copy
	 */
	public int getCreditHours() {
		return this.creditHours + 0;
	}

	/*
	 * @param credits -- number of new credit hours value
	 * @pre credits >= 0
	 * @post creditHours set to param
	 */
	public void setCreditHours(int credits) {
		assert credits >= 0;
		this.creditHours = credits + 0;
	}

	/*
	 * @returns this.isCompleted copy
	 */
	public boolean isCompleted() {
		return this.isCompleted && true;
	}

	/*
	 * @pre this.percentage has been set
	 * @returns this.percentage copy
	 */
	public double getPercentage() {
		assert this.percentage != -1;
		return this.percentage + 0.0;
	}

	/*
	 * @pre this.letterGrade has been set
	 * @returns this.letterGrade copy
	 */
	public Grade getLetterGrade() {
		assert this.letterGrade != null;
		switch (this.letterGrade) {
		case Grade.A:
			return Grade.A;
		case Grade.B:
			return Grade.B;
		case Grade.C:
			return Grade.C;
		case Grade.D:
			return Grade.D;
		default:
			return Grade.E;
		}
	}

	/*
	 * @post this.percentage, this.letterGrade
	 * 	are calculated. this.isCompleted = true
	 */
	public void setAsCompleted() {
		double pointsPossible = 0.0;
		double pointsEarned = 0.0;

		if (assignments.isEmpty()) {
			pointsPossible = 1.0;
		}
		for (Assignment assignment : assignments) {
			if (assignment.hasBeenGraded()) {
				pointsEarned += assignment.getPointsEarned();
				pointsPossible += assignment.getPointsPossible();
			}
		}
		this.percentage = pointsEarned / pointsPossible * 100.0;

		if (percentage < 60.0)
			this.letterGrade = Grade.E;
		else if (percentage < 70.0)
			this.letterGrade = Grade.D;
		else if (percentage < 80.0)
			this.letterGrade = Grade.C;
		else if (percentage < 90.0)
			this.letterGrade = Grade.B;
		else
			this.letterGrade = Grade.A;

		this.isCompleted = true;
	}

	/*
	 * @param a - assignment to add to list
	 * @post assignments list has 1 more entry
	 */
	public void addAssignment(Assignment a) {
		this.assignments.add(a);
	}
	
	// Comparator classes
	private class ChronologicalCompare implements Comparator<Course> {
		
		/*
		 * @returns chronological compareTo value
		 * of two Courses. Compares years first, then
		 * Semester enums.
		 */
		@Override
		public int compare(Course o1, Course o2) {
			if(o1.year == o2.year) {
				if(o1.sem == Semester.SPRING && o2.sem == Semester.FALL) {
					return -1;
				} else if (o1.sem == Semester.FALL && o2.sem == Semester.SPRING) {
					return 1;
				} else {
					return 0;
				}
			}
			if (o1.year < o2.year) {
				return -1;
			} else {
				return 1;
			}
		}
		
	}
	
	private class AlphabeticalCompare implements Comparator<Course> {

		/*
		 * @returns compareTo of courseNum strings
		 */
		@Override
		public int compare(Course o1, Course o2) {
			return o1.courseNum.compareTo(o2.courseNum);
		}
		
	}
}
