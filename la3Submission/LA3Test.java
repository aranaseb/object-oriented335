import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.Collections;

import org.junit.jupiter.api.Test;

class LA3Test {

	@Test
	void testAssignmentContructor() {
		Assignment a = new Assignment("Dexter", 100.0);
		assertEquals("Dexter", a.getName());
		assertEquals(100.0, a.getPointsPossible());
	}

	@Test
	void testAssignmentSetPointsEarned() {
		Assignment a = new Assignment("Dexter", 100.0);
		a.setPointsEarned(99.0);
		assertTrue(a.hasBeenGraded());
		assertEquals(99.0, a.getPointsEarned());
		assertEquals(99.0, a.getPercentage());
	}

	@Test
	void testCourseConstructor() {
		Course c = new Course("123", Semester.FALL, 2008, 3);
		// no getter for course number
		// no getter for semester or year
		assertNotNull(c.getCreditHours());
	}

	@Test
	void testCourseLetterGrade() {
		Course c = new Course("103ai Section ba/2", Semester.SPRING, 2008, 3);
		Assignment a2 = new Assignment("a", 100);
		a2.setPointsEarned(85);
		c.addAssignment(a2);
		c.setAsCompleted();
		assertEquals(Grade.B, c.getLetterGrade());
		assertEquals(85.0, c.getPercentage());
		assertTrue(c.isCompleted());
	}
	
	@Test
	void testCourseComparators() {
		Student d = new Student("Dexter", "Morgan", new Date(1), Standing.FRESHMAN);
		
		d.addCourse(new Course("123", Semester.FALL, 2008, 3));
		
		Course lastChrono = new Course("124", Semester.FALL, 2009, 3);
		d.addCourse(lastChrono);

		Course firstAlpha = new Course("101", Semester.SPRING, 2008, 3);
		d.addCourse(firstAlpha);

		d.addCourse(new Course("103ai Section ba/2", Semester.SPRING, 2009, 3));

		d.addCourse(new Course("109h", Semester.SPRING, 2007, 3));

		Course firstChrono = new Course("995", Semester.SPRING, 2006, 3);
		d.addCourse(firstChrono);

		Collections.sort(d.getCourses(), Course.CHRONOLOGICAL);
		
		assertEquals(d.getCourses().get(0), firstChrono);
		assertEquals(d.getCourses().get(2), firstAlpha);
		assertEquals(d.getCourses().get(5), lastChrono);
		
		Collections.sort(d.getCourses(), Course.ALPHABETICAL);
		
		assertEquals(d.getCourses().get(0), firstAlpha);
		assertEquals(d.getCourses().get(4), lastChrono);
		assertEquals(d.getCourses().get(5), firstChrono);  // same as lastAlpha
		
	}

	@Test
	void testStudentConstructor() {
		Student d = new Student("Dexter", "Morgan", new Date(1), Standing.FRESHMAN);
		assertNotNull(d.getFirstName());
		assertNotNull(d.getLastName());
		assertNotNull(d.getBirthDate());
		// no getter for ranking in spec
	}

	@Test
	void testStudentGetter_courses() {
		Student d = new Student("Dexter", "Morgan", new Date(1), Standing.FRESHMAN);
		d.addCourse(new Course("123", Semester.FALL, 2008, 3));
		d.addCourse(new Course("123", Semester.FALL, 2008, 3));

		d.addCourse(new Course("101", Semester.SPRING, 2008, 3));
		d.addCourse(new Course("103ai Section ba/2", Semester.SPRING, 2008, 3));

		assertEquals(4, d.getCourses().size());
	}

	@Test
	void testStudentGetter_completedCourses() {
		Student d = new Student("Dexter", "Morgan", new Date(1), Standing.FRESHMAN);
		d.addCourse(new Course("123", Semester.FALL, 2008, 3));
		d.addCourse(new Course("123", Semester.FALL, 2008, 3));

		d.addCourse(new Course("101", Semester.SPRING, 2008, 3));
		d.getCourses().get(2).setAsCompleted();

		d.addCourse(new Course("103ai Section ba/2", Semester.SPRING, 2008, 3));
		d.getCourses().get(3).setAsCompleted();

		assertEquals(2, d.getCompletedCourses().size());
	}

	@Test
	void testStudentGetter_inProgressCourses() {
		Student d = new Student("Dexter", "Morgan", new Date(1), Standing.FRESHMAN);
		d.addCourse(new Course("123", Semester.FALL, 2008, 3));
		d.addCourse(new Course("123", Semester.FALL, 2008, 3));

		d.addCourse(new Course("101", Semester.SPRING, 2008, 3));
		d.getCourses().get(2).setAsCompleted();

		d.addCourse(new Course("103ai Section ba/2", Semester.SPRING, 2008, 3));
		d.getCourses().get(3).setAsCompleted();

		assertEquals(2, d.getInProgressCourses().size());
	}

	@Test
	void testStudentPreferredName() {
		Student d = new Student("Dexter", "Morgan", new Date(1), Standing.FRESHMAN);
		d.setPreferredName("The Bay Harbor Butcher");
		assertEquals("The Bay Harbor Butcher", d.getPreferredName());
	}

	@Test
	void testStudentAge() {
		Student d = new Student("Dexter", "Morgan", new Date(1), Standing.FRESHMAN); // Date is year 1970
		assertEquals(54, d.getAge());
	}

	@Test
	void testStudentGPA() {
		Student d = new Student("Dexter", "Morgan", new Date(1), Standing.FRESHMAN);
		d.addCourse(new Course("123", Semester.FALL, 2008, 3));
		d.addCourse(new Course("123", Semester.FALL, 2008, 3));

		d.addCourse(new Course("101", Semester.SPRING, 2008, 3));
		Assignment a = new Assignment("a", 100);
		a.setPointsEarned(95);
		d.getCourses().get(2).addAssignment(a);
		d.getCourses().get(2).setAsCompleted();

		d.addCourse(new Course("103ai Section ba/2", Semester.SPRING, 2008, 3));
		Assignment a2 = new Assignment("a", 100);
		a2.setPointsEarned(85);
		d.getCourses().get(3).addAssignment(a2);
		d.getCourses().get(3).setAsCompleted();

		d.addCourse(new Course("109h", Semester.SPRING, 2008, 3));
		Assignment a3 = new Assignment("a", 100);
		a3.setPointsEarned(75);
		d.getCourses().get(4).addAssignment(a3);
		d.getCourses().get(4).setAsCompleted();

		d.addCourse(new Course("995", Semester.SPRING, 2008, 3));
		Assignment a4 = new Assignment("a", 100);
		a4.setPointsEarned(65);
		d.getCourses().get(5).addAssignment(a4);
		d.getCourses().get(5).setAsCompleted();

		d.addCourse(new Course("996", Semester.SPRING, 2008, 3));
		Assignment a5 = new Assignment("a", 100);
		a5.setPointsEarned(55);
		d.getCourses().get(6).addAssignment(a5);
		d.getCourses().get(6).setCreditHours(4);
		d.getCourses().get(6).setAsCompleted();

		assertEquals(1.875, d.getGpa());
	}

	@Test
	void testStudent_compareTo() {
		Student d = new Student("Dexter", "Morgan", new Date(1), Standing.FRESHMAN);
		Student w = new Student("Walter", "White", new Date(1), Standing.SENIOR);

		assertTrue(d.compareTo(w) < 0);
		assertFalse(d.compareTo(w) == 0);

		Student d2 = new Student("Bay Harbor", "Morgan", new Date(1), Standing.FRESHMAN);

		assertTrue(d.compareTo(d2) > 0);
		assertFalse(d.compareTo(d2) == 0);

		Student w2 = new Student("Walter", "White", new Date(1901423432423L), Standing.SENIOR);

		assertTrue(w.compareTo(w2) < 0);
		assertFalse(w.compareTo(w2) == 0);
		assertTrue(w2.compareTo(w) > 0);

		Student w3 = new Student("Walter", "White", new Date(1901423432423L), Standing.SENIOR);

		assertTrue(w2.compareTo(w3) == 0);
	}

}
