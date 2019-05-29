
public class Data {
	public String year; 
	public String cause; 
	public String causeName; 
	public String deaths;
	public String state;
	public String ageAdjDeathRate;

	Data() {
		this.year = "";
		this.cause = "";
		this.causeName = "";
		this.deaths = "";
		this.state = "";
		this.ageAdjDeathRate = "";		
	}
	public Data(String year,String cause, String causeName, String deaths, String state, String ageAdjDeathRate ) {
		this.year = year;
		this.cause = cause ;
		this.causeName = causeName ;
		this.deaths = deaths ;
		this.state = state ;
		this.ageAdjDeathRate = ageAdjDeathRate ;		
	}
	public String getYear(){return this.year;}
	public String getCause(){return this.cause;}
	public String getCauseName(){return this.causeName;}
	public String getDeaths(){return this.deaths;}
	public String getState(){return this.state;}
	public String getAgeAdjDeathRate(){return this.ageAdjDeathRate;}

	public void setYear(String var){this.year = var; }
	public void setCause(String var){this.cause = var; }
	public void setCauseName(String var){this.causeName = var; }
	public void setDeaths(String var){this.deaths = var; }
	public void setState(String var){this.state = var; }
	public void setAgeAdjDeathRate(String var){this.ageAdjDeathRate = var;}
	
	public static void printData(Data item) {
		puts("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		puts("Cause: "+item.causeName);
		//puts("Cause Of Death: " + item.cause);
		puts("Year: " + item.year);
		puts("State: "+item.state);
		puts("Deaths: "+item.deaths);
	}
	private static <T> void puts(T var) {
		System.out.println(var.toString());
	}
}
