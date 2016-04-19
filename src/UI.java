import java.io.FileNotFoundException;
import java.util.Scanner;

public class UI {
	Scanner input = new Scanner(System.in);
	GradeSystems gs;
	String ID;
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
	
	public UI(String GradsFile) throws FileNotFoundException{
		gs = new GradeSystems(GradsFile);
	}
	
	public boolean checkID(String userID) throws NoSuchIDExceptions{
		if(gs.containsID(userID)){
			return true;
		}
		else{
			return false;
		}
	} 
	
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
	
	public String promotID(){
		System.out.print("輸入ID或 Q(結束使用)？");
		String ID = input.nextLine();
		return ID;
	}
	
	public void showFinishMsg(){
		System.out.println("結束了");
	}
	
	public void showCommandMsg(){
		System.out.println("輸入指令 1) G 顯示成績 (Grade)");
		System.out.println("　　　　 2) R 顯示排名 (Rank)");
		System.out.println("　　　　 3) W 更新配分 (Weight)");
		System.out.println("　　　　 4) E 離開選單 (Exit)");
		System.out.print("使用者輸入：");
	}
	
	public void showWelcomeMsg(String ID){
		System.out.println("Welcome "+gs.getStudentName(ID));
	}
}
