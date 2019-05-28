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
// Add a menu to select various feilds (upto 2)

public class Identify {
	public static void main(String[] args) {
		String year = ask("What year:");
		String State = ask("What state:");
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
                    temp.setDeaths(column.toArray()[3].toString());
                    temp.setState(column.toArray()[4].toString());
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
	                    temp.setDeaths(column.toArray()[6].toString());
	                    temp.setState(column.toArray()[7].toString());
	                    temp.setAgeAdjDeathRate(column.toArray()[8].toString());
					}
					else {
						System.exit(0);
					}				
				}
				dataArray.add(temp);
            }
			for (Data element : dataArray) {
				if (year.equals(element.getYear()) && !element.getCause().equals("All Causes")) {
					Data.printData(element);
				}
			}
		}
		catch (FileNotFoundException e){
			puts("File not found");
			System.exit(0);
		}
		catch (IOException e) {
			puts("Error processing file");
			
		}		
	}
//	private static void puts( String arg) {
//		System.out.println(arg);
//		//System.out.print(arg+"|");
//	}
	
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
		catch (IOException e){
			puts(e);
		}
		return null;
	}
	private static void PrintData(Data[] var, String var1, String var2) {
		for (Data element : var) {
			puts(element.year);

		}		
	}
}
