/**
 * A small class witch saves student name , student ID and grade data
 * 
 *
 */
public class Grades {
	String ID;
	String name;
	int lab1, lab2, lab3;
	int midTerm, finalExam, totalGrade;
	/**
	 * Constructor
	 */
	public Grades(){
		
	}
	/**
	 * Calculate total grade by weights
	 * @param Weights double array for string five grades
	 * @return An integer contain calculated total grade
	 */
	public int calculateTotalGrade(double weights[]){
		int weightGrade = (int)Math.round((((double)lab1)*weights[0]
						+ ((double)lab2)*weights[1]
						+ ((double)lab3)*weights[2]
						+ ((double)midTerm)*weights[3]
						+ ((double)finalExam)*weights[4]));
		totalGrade = weightGrade;
		return weightGrade;
	}
}
