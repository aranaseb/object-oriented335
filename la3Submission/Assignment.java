/*
 * @author Sebastian Arana
 */
public class Assignment {
	private final String NAME;
	private final double POINTS_POSSIBLE;
	private double pointsEarned;
	private boolean graded;
	private double percentage;

	/*
	 * @param name - name of student this is assigned to. If it is null, sets to
	 * empty string. pointsPossible - maximum number of points. If it is 0, there
	 * will be an error later, and negative max does not make sense. An invalid
	 * value defaults to 1 maximum point.
	 * 
	 * @pre name != null AND pointsPossible > 0
	 * 
	 * @post all 5 local instance fields are initialized.
	 */
	public Assignment(String name, double pointsPossible) {
		assert name != null;
		this.NAME = name;
		assert pointsPossible > 0;
		this.POINTS_POSSIBLE = pointsPossible;
		pointsEarned = -1.0;
		graded = false;
		percentage = -1.0;
	}

	/*
	 * @returns name string
	 */
	public String getName() {
		return this.NAME;
	}

	/*
	 * @returns max number of points
	 */
	public double getPointsPossible() {
		return this.POINTS_POSSIBLE;
	}

	/*
	 * @pre this.pointsEarned has been set
	 * 
	 * @returns number of points earned
	 */
	public double getPointsEarned() {
		assert this.pointsEarned != -1;
		return this.pointsEarned + 0;
	}

	/*
	 * @returns whether or not setPointsEarned has been called.
	 */
	public boolean hasBeenGraded() {
		return this.graded && true;
	}

	/*
	 * @pre this.pointsEarned has been set
	 * 
	 * @returns percentage score.
	 */
	public double getPercentage() {
		assert this.percentage != -1;
		return this.percentage + 0;
	}

	/*
	 * @param pointsEarned - number of points graded
	 * 
	 * @pre pointsEarned > 0 && pointsEarned < pointsPossible
	 * 
	 * @post pointsEarned, percentage, graded set to valid values.
	 */
	public void setPointsEarned(double pointsEarned) {
		assert pointsEarned > 0 && pointsEarned < POINTS_POSSIBLE;
		this.pointsEarned = pointsEarned;
		this.percentage = this.pointsEarned / POINTS_POSSIBLE * 100.0;

		this.graded = true;
	}
}
