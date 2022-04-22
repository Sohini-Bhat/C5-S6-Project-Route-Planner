package miniproject;
import java.util.*;
import java.io.*;
import java.util.Scanner;

public class RoutePlanner  {
	 static ArrayList<String> directflights=new ArrayList<>();
	void showAllFlight(String filename)throws IOException
{
		BufferedReader br=new BufferedReader(new FileReader(filename));
		String line;
		int count=0;
		while((line=br.readLine())!=null) {
			count++;
		}
		br=new BufferedReader(new FileReader(filename));
		while((line=br.readLine())!=null) {
			
				String[] routedetails=line.split(",");
				for(String word:routedetails) {
					System.out.print(word+" \t");
				}
				System.out.println();
			}
			
		br.close();
		}
		public static void showDirectFlights(String[] routeInfo,String fromCity) {
			boolean flag=true;
			System.out.println("Flights starts from "+fromCity+":");
			try {
				for(String line:routeInfo) {
					String[] routedetails=line.split(",");
					if(routedetails[0].equalsIgnoreCase(fromCity)) {
						flag=false;
						for(String word:routedetails) {
							System.out.print(word+" \t");
						}
						System.out.println();
						directflights.add(line);
					}
				}
				
			}catch(NullPointerException ignored) {
				
			}
			if(flag) {
				System.out.println("We are sorry.At this point of time,we donot have any information of flights originating "
						+ "from"+fromCity);
			}
			

	
	
	
}
	
	
	String[] sortDirectFlights(String[] directflight) {
		for(int i=0;i<directflight.length-1;i++) {
			for(int j=0;j<directflight.length-i-1;j++) {
				String word1=(directflight[j].split(",")[1]);
				String word2=(directflight[j+1].split(",")[1]);
				if((word1.toLowerCase()).compareTo(word2.toLowerCase())>0) {
					String temp=directflight[j];
					directflight[j]=directflight[j+1];
					directflight[j+1]=temp;
				}
			}
		}
		return directflight;
	}
	void showAllConnections(String[] routeinfo,String fromcity,String tocity) {
		System.out.println("All connections between"+fromcity+"and"+tocity+":");
		boolean flag =false;
		String[] routedetails =new String[5];
		for(String line:routeinfo) {
			routedetails=line.split(",");
			if(routedetails[0].equalsIgnoreCase(fromcity)&&
					routedetails[1].equalsIgnoreCase(tocity)) {
				flag=true;
				for(String word:routedetails) {
					System.out.print(word+" \t");
				}
				System.out.println();
			}
		}
		for(String line:routeinfo) {
			routedetails=line.split(",");
			if(!routedetails[0].equalsIgnoreCase(fromcity)&&
					routedetails[1].equalsIgnoreCase(tocity)) {
				flag=showAllConnections(routeinfo,fromcity,routedetails[0],false);
				if(flag) {
					for(String word:routedetails) {
						System.out.print(word+" \t");
					}
					System.out.println();
				}
			}
			
		}
		if(!flag)
			 System.out.println("No flight available");
		
	}
	boolean showAllConnections(String[] routeinfo,String fromcity,String tocity,boolean flag) {
		String[] routedetails =new String[5];
		for(String line:routeinfo) {
			routedetails=line.split(",");
			if(routedetails[0].equalsIgnoreCase(fromcity)&&
					routedetails[1].equalsIgnoreCase(tocity)) {
				flag=true;
				for(String word:routedetails) {
					System.out.print(word+" \t");
				}
				System.out.println();
			}
		}
		for(String line:routeinfo) {
			routedetails=line.split(",");
			if(!routedetails[0].equalsIgnoreCase(fromcity)&&
					routedetails[1].equalsIgnoreCase(tocity)) {
				flag=showAllConnections(routeinfo,fromcity,routedetails[0],false);
				if(flag) {
					for(String word:routedetails) {
						System.out.print(word+" \t");
					}
				
				System.out.println();
			}
		}
		
	}
	return flag;
	}
	public static void main(String[] args) throws IOException {
		RoutePlanner routeplanner=new RoutePlanner();
		System.out.println("All available flight details");
		routeplanner.showAllFlight("routes.csv");
		System.out.println();
		BufferedReader br=new BufferedReader(new FileReader("routes.csv"));
		String line;
		int count=0;
		while((line=br.readLine())!=null)
			count++;
		count--;
		String[] routes=new String[count];
		br=new BufferedReader(new FileReader("routes.csv"));
		int i=0;
		br.readLine();
		while((line=br.readLine())!=null) {
			routes[i++]=line;
			
		}
		routeplanner.showDirectFlights(routes, "Delhi");
		System.out.println();
		String[] unsorteddirectflights=routeplanner.directflights.toArray(new String[0]);
		String[] sorteddirectflights=routeplanner.sortDirectFlights(unsorteddirectflights);
		System.out.println("Sorted Direct Flights");
		for(String line1:sorteddirectflights) {
			String[] routedetails=line1.split(",");
			for(String word:routedetails) {
				System.out.print(word+" \t");
			}
			System.out.println();
		}
		
		System.out.println();
		routeplanner.showAllConnections(routes, "Delhi", "London");
	}

} 

