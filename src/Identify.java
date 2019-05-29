import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List; 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

// year , state and cause --> aim is to find the highest disease in the most concentrated area
// and the lowest disease in the least concentrated area
// Add a menu to select various fields (up to 2)

public class Identify {
	public static void main(String[] args) {

		List<String> arg = menu();
		List<Bundle> bun = new ArrayList<>();
		Bundle tmp ;//= new Bundle(); 
		List<String> var = new ArrayList<>();;
		for (String E : arg) {
			//puts(E);
			tmp = new Bundle(); 
			if (E.equals("Year")) {
				String year = ask("What year:");
				var.add(year);
				tmp.var = E;
				tmp.val = year;
				bun.add(tmp);
				puts("Year");
			} else if (E.equals("State")) {
				String state = ask("What State:");
				tmp.var = E;
				tmp.val = state;
				bun.add(tmp);
				puts("State");
			} else if (E.equals("Cause")) {
				String cause = ask("What Cause:");
				tmp.var = E;
				tmp.val = cause;
				bun.add(tmp);
				puts("Cause");
			}
		}
		parse(bun);
	}
private static void parse(List<Bundle> bun) {
//	private static void parse(List<String> arg,List<String> var ) {
	try {

			FileInputStream fstream = new FileInputStream("LCD_US.csv");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			//List<String> lines = new ArrayList<>();
			List<Data> dataArray = new ArrayList<>();
			String strLine;
			// make an array of years			 
			String pattern;
			Pattern r;
			Matcher m;
			int count = 0; 
			while ((strLine = br.readLine()) != null){
				Data temp = new Data();
				List<String> column = Arrays.asList(strLine.split(","));
				// if the length is greater than 6 than we have an issue
				if (column.toArray().length == 6 ) {
					temp.setYear(column.toArray()[0].toString());
					temp.setCause(column.toArray()[1].toString());
					temp.setCauseName(column.toArray()[2].toString());
					temp.setState(column.toArray()[3].toString());
					temp.setDeaths(column.toArray()[4].toString());
					temp.setAgeAdjDeathRate(column.toArray()[5].toString());
				}
				else if (column.toArray().length == 9 ){
					String tempStr = "";
					for (int i = 1; i < 5; ++i) { tempStr += column.toArray()[i]; }
					pattern = "([\\w,\\s]+.*)(\\()";   //(\\(.*\\))
					r = Pattern.compile(pattern);				
					m = r.matcher(tempStr);
					if (m.find()) {
						temp.setYear(column.toArray()[0].toString());
						temp.setCause(m.group(1));
						temp.setCauseName(column.toArray()[5].toString());
						temp.setState(column.toArray()[6].toString());
						temp.setDeaths(column.toArray()[7].toString());
						temp.setAgeAdjDeathRate(column.toArray()[8].toString());
					}
					else {
						System.exit(0);
					}				
				}
				dataArray.add(temp);
            }

			for (Data element : dataArray) {
				if ((bun.get(0).var.equals("Year") && bun.get(1).var.equals("State")) || 
					(bun.get(1).var.equals("Year") && bun.get(0).var.equals("State"))){
					if ((element.getYear().equals(bun.get(0).val) && element.getState().equals(bun.get(1).val)) ||
						(element.getYear().equals(bun.get(1).val) && element.getState().equals(bun.get(0).val))){
						Data.printData(element);
					}
				}
				else if ((bun.get(0).var.equals("Year") && bun.get(1).var.equals("Cause")) ||
						 (bun.get(1).var.equals("Year") && bun.get(0).var.equals("Cause"))){
					if ((element.getYear().equals(bun.get(0).val) && element.getCauseName().equals(bun.get(1).val)) ||
						(element.getYear().equals(bun.get(1).val) && element.getCauseName().equals(bun.get(0).val))){
						Data.printData(element);
					}
				}
				
				else if ((bun.get(0).var.equals("State") && bun.get(1).var.equals("Cause")) || 
						 (bun.get(1).var.equals("State") && bun.get(0).var.equals("Cause"))){
					if ((element.getState().equals(bun.get(0).val) && element.getCauseName().equals(bun.get(1).val)) || 
						(element.getYear().equals(bun.get(1).val) && element.getCauseName().equals(bun.get(0).val))){
						Data.printData(element);
					}	
				}
				else {puts(bun.get(0).var + " or " + bun.get(1).var +" cannot be found.\nPlease try Again");}
			}
		}
		catch (FileNotFoundException e){ puts("File not found\n\n" + e); System.exit(0); }
		catch (IOException e) {puts("Error processing file\n\n"
				+ "" + e); System.exit(0); }		
	}
	private static <T> void puts(T var) {
		System.out.println(var.toString());
	}
	private static String ask(String question){
		try {
			puts(question);
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String tmp = reader.readLine();
			return tmp;
		}
		catch (IOException e){ puts(e);	}
		return null;
	}
	private static void PrintData(Data[] var, String var1, String var2) {
		for (Data element : var) {
			puts(element.year);

		}		
	}
	private static List<String> menu() {
		List<String> args = new ArrayList<>();
		while (true) {
			try {
				puts ("Please select one of the following,");
				puts ("by enering a letter ");
				puts ("A: Year");
				puts ("B: State");
				puts ("C: Cause"); puts("");
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String tmp = reader.readLine();			
				if (tmp.equals("A")) { args.add("Year"); }
				else if (tmp.equals("B")) { args.add("State"); }
				else if (tmp.equals("C")) { args.add("Cause"); }
				else { puts("Please Try Again"); }
				if (args.toArray().length > 1) {
					return args;	
				}
			}catch (IOException e){ puts(e);}
		}
	}

}
