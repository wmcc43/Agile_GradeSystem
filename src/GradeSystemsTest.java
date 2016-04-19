import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GradeSystemsTest {

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

	@Test(expected=NoSuchIDExceptions.class)
	public void testContainsID() throws FileNotFoundException, NoSuchIDExceptions {
		GradeSystems gs=new GradeSystems("./gradeTest.txt");
		boolean iscontain =gs.containsID("962001044");
		assertTrue(iscontain);
		
		iscontain =gs.containsID("5545455");
	}

	@Test
	public void testGetNewWeights() throws FileNotFoundException {
		ByteArrayInputStream inContent = new ByteArrayInputStream("20\r\n20\r\n20\r\n20\r\n20\r\n".getBytes());
		System.setIn(inContent);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		GradeSystems gs=new GradeSystems("./gradeTest.txt");
		double[] newweight= gs.getNewWeights();
		double[] expweight={0.2,0.2,0.2,0.2,0.2};
		for(int i=0;i<5;i++){
			assertEquals(newweight[i], expweight[i], 0.0);
		}
		assertEquals("輸入新配分\r\n"
				+"\tlab1\t\t"
				+"\tlab2\t\t"
				+"\tlab3\t\t"
				+"\tmid-term\t"
				+"\tfinal exam\t",out.toString());
		
	}
	
	@Test
	public void testGetStudentName() throws FileNotFoundException {
		GradeSystems gs = new GradeSystems("./gradeTest.txt");
		String stuName = gs.getStudentName("962001044");
		assertEquals("凌宗廷", stuName);
	}
	
	@Test
	public void testSetWeights() throws FileNotFoundException {
		ByteArrayInputStream inContent = new ByteArrayInputStream("Y\r\n".getBytes());
		System.setIn(inContent);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		GradeSystems gs=new GradeSystems("./gradeTest.txt");
		double[] newweight={0.2,0.2,0.2,0.2,0.2};
		gs.setWeights(newweight);
		assertEquals("請確認新配分\r\n"+"\tlab1\t\t20%\r\n"+"\tlab2\t\t20%\r\n"+"\tlab3\t\t20%\r\n"
					+"\tmid-term\t20%\r\n"+"\tfinal exam\t20%\r\n"
					+"以上正確嗎? Y (Yes) 或 N (No)\r\n"+"使用者輸入：",out.toString());
		
	}
	
	@Test
	public void testShowGrade() throws FileNotFoundException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		GradeSystems gs=new GradeSystems("./gradeTest.txt");
		gs.showGrade("962001044");
		assertEquals("凌宗廷成績： lab1：\t\t87\r\n"+"\tlab2：\t\t86\r\n"+"\tlab3：\t\t98\r\n"
					+"\tmidTerm：\t88\r\n"+"\tfinalExam：\t87\r\n"+"\ttotalGrade：\t88\r\n",out.toString());
		
	}
	
	@Test
	public void testShowOldWeights() throws FileNotFoundException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		GradeSystems gs = new GradeSystems("./gradeTest.txt");
		gs.showOldWeights();
		assertEquals("舊配分\r\n"
				+"\tlab1\t\t10%\r\n"
				+"\tlab2\t\t10%\r\n"
				+"\tlab3\t\t10%\r\n"
				+"\tmid-term\t30%\r\n"
				+"\tfinal exam\t40%\r\n"
				,out.toString());
	}
	
	@Test
	public void testShowRank() throws FileNotFoundException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		GradeSystems gs = new GradeSystems("./gradeTest.txt");
		gs.showRank("962001044");
		assertEquals("凌宗廷排名第2\r\n", out.toString());
	}

	@Test
	public void testReCalculateGrade() throws FileNotFoundException {
		ByteArrayInputStream inContent = new ByteArrayInputStream("Y\r\n".getBytes());
		System.setIn(inContent);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		GradeSystems gs=new GradeSystems("./gradeTest.txt");
		double[] newweight={0.2,0.2,0.2,0.2,0.2};
		gs.setWeights(newweight);
		gs.reCalculateGrade();
		for(Grades t:gs.aList){
			assertEquals(89, t.totalGrade);
		}
	}
}
