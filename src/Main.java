import java.io.FileNotFoundException;

public class Main {
	
	public static void main(String args[]){
		UI aui;
		try{
			aui = new UI();
		}
		catch(NoSuchIDExceptions e){
			System.out.println("ID錯了!");
		}
		catch(NoSuchCommandExceptions e){
			System.out.println("指令錯了!");
		}
		catch(FileNotFoundException e){
			System.out.println("成績檔案找不到!");
		}
		
		
	}
}
/*
class UI{
	Scanner input = new Scanner(System.in);
	GradeSystems gs;
	String ID;
	public UI() throws NoSuchIDExceptions, NoSuchCommandExceptions, FileNotFoundException{
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
*/

/*
class GradeSystems{
	LinkedList<Grades> aList;
	double[] weights = {0.1,0.1,0.1,0.3,0.4};
	Scanner input = new Scanner(System.in);
	public GradeSystems() throws FileNotFoundException{
		File gradeinput = new File("./gradeInput.txt");
		Scanner fileinput = new Scanner(gradeinput);
		aList=new LinkedList<>();
		String line;
		while(fileinput.hasNext()){
			line = fileinput.nextLine();
			String data[] = line.split(" +");
			Grades aGrade = new Grades();
			aGrade.ID = data[0];
			aGrade.name = data[1];
			aGrade.lab1 = Integer.valueOf(data[2]);
			aGrade.lab2 = Integer.valueOf(data[3]);
			aGrade.lab3 = Integer.valueOf(data[4]);
			aGrade.midTerm = Integer.valueOf(data[5]);
			aGrade.finalExam = Integer.valueOf(data[6]);
			aGrade.totalGrade = aGrade.calculateTotalGrade(weights);
			aList.add(aGrade);
		}
		fileinput.close();
	}
	
	public boolean containsID(String ID) throws NoSuchIDExceptions{
		for(Grades t : aList){
			if(t.ID.equals(ID))
				return true;
		}
		throw new NoSuchIDExceptions();
	}
	
	public double[] getNewWeights(){
		double newweights[] = new double[5];
		double tempSumWeight = 0;
		do{
			tempSumWeight = 0;
			System.out.println("輸入新配分");
			System.out.print("\tlab1\t\t");
			newweights[0]=input.nextInt()/100.0;
			System.out.print("\tlab2\t\t");
			newweights[1]=input.nextInt()/100.0;
			System.out.print("\tlab3\t\t");
			newweights[2]=input.nextInt()/100.0;
			System.out.print("\tmid-term\t");
			newweights[3]=input.nextInt()/100.0;
			System.out.print("\tfinal exam\t");
			newweights[4]=input.nextInt()/100.0;
			for(double s:newweights)
				tempSumWeight += s;
			if(tempSumWeight!=1.0){
				System.out.print("輸入的加權總合不等於100\n請重新");
			}
		}while(tempSumWeight!=1.0);
		return newweights;
	}
	
	public String getStudentName(String ID){
		for(Grades t:aList){
			if(t.ID.equals(ID)){
				return t.name;
			}
		}
		return null;
	}
	
	public void setWeights(double newweights[]){
		System.out.println("請確認新配分");
		System.out.println("\tlab1\t\t"+(int)(newweights[0]*100)+"%");
		System.out.println("\tlab2\t\t"+(int)(newweights[1]*100)+"%");
		System.out.println("\tlab3\t\t"+(int)(newweights[2]*100)+"%");
		System.out.println("\tmid-term\t"+(int)(newweights[3]*100)+"%");
		System.out.println("\tfinal exam\t"+(int)(newweights[4]*100)+"%");
		while(true){
			System.out.println("以上正確嗎? Y (Yes) 或 N (No)");
			System.out.print("使用者輸入：");
			String ans = input.next();
			if(ans.equalsIgnoreCase("yes")||ans.equalsIgnoreCase("y")){
				weights=newweights;
				break;
			}
			else if(ans.equalsIgnoreCase("no")||ans.equalsIgnoreCase("n")){
				break;
			}
		}
		
	}

	public void showGrade(String ID){
		aList.forEach(grades->{
			if(grades.ID.equals(ID)){
				System.out.println(grades.name+"成績： lab1：\t\t"+grades.lab1);
				System.out.println("\tlab2：\t\t"+grades.lab2);
				System.out.println("\tlab3：\t\t"+grades.lab3);
				System.out.println("\tmidTerm：\t"+grades.midTerm);
				System.out.println("\tfinalExam：\t"+grades.finalExam);
				System.out.println("\ttotalGrade：\t"+grades.totalGrade);
			}
		});
		
		
	}
	
	public void showOldWeights(){
		System.out.println("舊配分");
		System.out.println("\tlab1\t\t"+(int)(weights[0]*100)+"%");
		System.out.println("\tlab2\t\t"+(int)(weights[1]*100)+"%");
		System.out.println("\tlab3\t\t"+(int)(weights[2]*100)+"%");
		System.out.println("\tmid-term\t"+(int)(weights[3]*100)+"%");
		System.out.println("\tfinal exam\t"+(int)(weights[4]*100)+"%");
	}

	public void showRank(String ID){
		int rank = 1;
		Grades target = null;
		for(Grades t1:aList){
			if(t1.ID.equals(ID)){
				target = t1;
				for(Grades t2:aList){
					if (t2.totalGrade > target.totalGrade){
						rank++;
					}
				}
			}
		}
		if(target!=null){
			System.out.println(target.name+"排名第"+rank);
		}
	}
	
	public void updateWeights(){
		double newweights[] = new double[5];
		showOldWeights();
		newweights=getNewWeights();
		setWeights(newweights);
		reCalculateGrade();
	}
	
	public void reCalculateGrade(){
		for(Grades t:aList){
			t.calculateTotalGrade(weights);
		}
	}
}
*/
/*
class Grades{
	String ID;
	String name;
	int lab1, lab2, lab3;
	int midTerm, finalExam, totalGrade;
	
	public Grades(){
		
	}

	public int calculateTotalGrade(double weights[]){
		int weightGrade = (int)Math.round((((double)lab1)*weights[0]
						+ ((double)lab2)*weights[1]
						+ ((double)lab3)*weights[2]
						+ ((double)midTerm)*weights[3]
						+ ((double)finalExam)*weights[4]));
		return weightGrade;
	}
}
*/