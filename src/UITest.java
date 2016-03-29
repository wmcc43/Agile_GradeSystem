import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UITest {
	static UI aui;
	static GradeSystems gs;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		aui = new UI();
		gs = new GradeSystems("gradeTest.txt");
		aui.gs = gs;
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
	public void test() {
		fail("Not yet implemented");
	}

}
