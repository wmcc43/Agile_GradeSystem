import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GradesTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCalculateTotalGrade() {
		Grades g = new Grades();
		g.ID = "962001044";
		g.name = "凌宗廷";
		g.lab1 = 87;
		g.lab2 = 86;
		g.lab3 = 98;
		g.midTerm = 88;
		g.finalExam = 87;
		double weights[] = {0.1,0.1,0.1,0.3,0.4};
		int total = g.calculateTotalGrade(weights);
		assertEquals(88, total);
	}

}
