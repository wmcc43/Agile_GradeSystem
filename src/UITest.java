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

public class UITest {
	
	//static UI ui;
	GradeSystems gs;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//ui = new UI("./gradeTest.txt");
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

	@Test (expected=NoSuchIDExceptions.class)
	public void testCheckID() throws NoSuchIDExceptions, FileNotFoundException {
		UI ui = new UI("./gradeTest.txt");
		assertTrue(ui.checkID("962001044"));
		ui.checkID("123");
	}

	@Test (expected = NoSuchCommandExceptions.class)
	public void testPromptCommand() throws NoSuchCommandExceptions, FileNotFoundException {
		ByteArrayInputStream inContent = new ByteArrayInputStream("G\r\n".getBytes());
		System.setIn(inContent);
		UI ui = new UI("./gradeTest.txt");
		String command = ui.promptCommand();
		assertEquals("G", command);
		
		inContent = new ByteArrayInputStream("Error\r\n".getBytes());
		System.setIn(inContent);
		ui = new UI("./gradeTest.txt");
		command = ui.promptCommand();
	}
	
	@Test
	public void testPromotID() throws FileNotFoundException {
		ByteArrayInputStream inContent = new ByteArrayInputStream("962001044\r\n".getBytes());
		System.setIn(inContent);
		UI ui = new UI("./gradeTest.txt");
		String ID = ui.promotID();
		assertEquals("962001044", ID);
	}

	@Test
	public void testShowFinishMsg() throws FileNotFoundException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		UI ui = new UI("./gradeTest.txt");
		ui.showFinishMsg();
		assertEquals("結束了\r\n", out.toString());
	}

	@Test
	public void testShowCommandMsg() throws FileNotFoundException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		UI ui = new UI("./gradeTest.txt");
		ui.showCommandMsg();
		assertEquals("輸入指令 1) G 顯示成績 (Grade)\r\n"
				+ "　　　　 2) R 顯示排名 (Rank)\r\n"
				+ "　　　　 3) W 更新配分 (Weight)\r\n"
				+ "　　　　 4) E 離開選單 (Exit)\r\n"
				+ "使用者輸入：", out.toString());
	}

	@Test
	public void testShowWelcomeMsg() throws FileNotFoundException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		UI ui = new UI("./gradeTest.txt");
		String ID = "962001044";
		ui.showWelcomeMsg(ID);
		assertEquals("Welcome 凌宗廷\r\n", out.toString());
	}
}
