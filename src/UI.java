import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * An user interface witch let user insert ID and command.
 * <p>User can choose four kind of function, show grade,show rank,
 * <p>update weight and exit.
 * @author 
 *
 */
public class UI {
	Scanner input = new Scanner(System.in);
	GradeSystems gs;
	String ID;
	/**
	 * This constructor builds a GradeSystems object , and reads user command .
	 * <p>If the command equals "Q", exit the user interface.
	 * <p>Another comment case is student ID , if the ID has existed in GradeSystems object ,
	 * <p>show welcome massage and prompt of four kinds of command.
	 * <p>If the command equals "E" , back to read previous command("Q" or ID)   
	 * @throws NoSuchCommandExceptions if comment is error
	 * @throws NoSuchIDExceptions if ID hasn't existed
	 * @throws FileNotFoundException if file can't be found
	 */
	public UI() throws NoSuchCommandExceptions, NoSuchIDExceptions, FileNotFoundException{
		gs = new GradeSystems();
		while(true){
			ID = promotID();
			String command;
			if(ID.equalsIgnoreCase("q")){
				break;
			}
			else if(checkID(ID)){
				showWelcomeMsg(ID);
				command = promptCommand();
				while(!command.equalsIgnoreCase("e")){
					command = promptCommand();
				}
			}
		}
		showFinishMsg();
		input.close();
	}
	/**
	 * This constructor is design for unit test code 
	 * @param GradsFile file path
	 * @throws FileNotFoundException if file can't be found
	 */
	public UI(String GradsFile) throws FileNotFoundException{
		gs = new GradeSystems(GradsFile);
	}
	/**
	 * Check ID exist in the GradeSystems object or not 
	 * @param userID
	 * @return true if exist , false if not exist
	 * @throws NoSuchIDExceptions if id not exist
	 */
	public boolean checkID(String userID) throws NoSuchIDExceptions{
		if(gs.containsID(userID)){
			return true;
		}
		else{
			return false;
		}
	} 
	/**
	 * Read user command and to execute corresponding action
	 * <p>For "G" case, show user's own grade
	 * <p>For "R" case, show user's rank
	 * <p>For "W" case, change the grade weight
	 * <p>For "E" case, do noting
	 * @return command user input command
	 * @throws NoSuchCommandException if file can't be founds 
	 */
	public String promptCommand() throws NoSuchCommandExceptions{
		showCommandMsg();
		String command = input.nextLine();
		switch(command){
		case "G":
		case "g":
			gs.showGrade(ID);
			break;
		case "R":
		case "r":
			gs.showRank(ID);
			break;
		case "W":
		case "w":
			gs.updateWeights();
			break;
		case "E":
		case "e":
			break;
		default:
			throw new NoSuchCommandExceptions();
		}
		return command;
	}
	/**
	 * Show promote massage and read user input ID
	 * @return ID user input ID
	 */
	public String promotID(){
		System.out.print("輸入ID或 Q(結束使用)？");
		String ID = input.nextLine();
		return ID;
	}
	/**
	 * Show finish massage
	 */
	public void showFinishMsg(){
		System.out.println("結束了");
	}
	/**
	 * Show command promote massage
	 */
	public void showCommandMsg(){
		System.out.println("輸入指令 1) G 顯示成績 (Grade)");
		System.out.println("　　　　 2) R 顯示排名 (Rank)");
		System.out.println("　　　　 3) W 更新配分 (Weight)");
		System.out.println("　　　　 4) E 離開選單 (Exit)");
		System.out.print("使用者輸入：");
	}
	/**
	 * Show welcome massage to user, include user ID
	 * @param ID user ID
	 */
	public void showWelcomeMsg(String ID){
		System.out.println("Welcome "+gs.getStudentName(ID));
	}
}
