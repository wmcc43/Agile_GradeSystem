import java.io.FileNotFoundException;
/**
 * Main class for execution GradeSystem with CLI UI.
 */
public class Main {
	/**
	 * Main function for executing the whole GradeSystem
	 * <p>Main function handle all the exception which system throws out,
	 * <p>it would tell you why the exception happened
	 * @param args no argument needed in start progress
	 */
	public static void main(String args[]){
		UI aui;
		try{
			aui = new UI();
		}
		catch(NoSuchIDExceptions e){
			System.out.println("ID錯了!");
			e.printStackTrace();
		}
		catch(NoSuchCommandExceptions e){
			System.out.println("指令錯了!");
			e.printStackTrace();
		}
		catch(FileNotFoundException e){
			System.out.println("成績檔案找不到!");
			e.printStackTrace();
		}
	}
}