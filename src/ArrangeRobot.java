
import java.util.ArrayList;

public class ArrangeRobot {
	/**
	 * 
	 */
	
	ArrayList<ExamItem> examItems =  new ArrayList();
	ArrayList<Monitor> allMonitors = new ArrayList();


	public void doArrangement(){
		//调用IOperations的readXls将数据读入examItems和allMonitors
        IOperations.readXls(ArrayList<ExamItem> examItem, ArrayList<Monitor> allMonitors);
        // 
	}

	public int searchAvailableMonitor(ArrayList<Monitor> allMonitors,String department, int flag) {
		
		//while-loop runs at most allMonitors.getlength() times
		int i = allMonitors.size();
		
		while(i>=0){
			//i: control while-loop; flag indicates the beginning index in allMonitors
			if ((allMonitors.get(flag).timeLeft > 0) && allMonitors.get(flag).department.equals(department)) { 
				//return the index of the nearest available monitor from the beginning flag
				return flag;
			}

			//% limit the value of flag in scope of [0,allMonitors.getlength()-1]  
			flag = (flag + 1) % allMonitors.size();
			i--;
	   	}

		//the finish of while-loop indicates no monitor available 
		return -1;
	}

}
