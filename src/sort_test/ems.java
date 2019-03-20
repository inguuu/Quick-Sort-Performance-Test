package sort_test;

import java.util.*;
import java.io.*;
public class ems {

	private static int[] numbers;
	private static int[] helper;
	private static int number;
	static int size, fileNumber, passNumber, pages, currentPage, pagesMade,  numberArray[], KB, pagesRemaining;
        
	public static void main(String[] args) throws IOException{
		//The intital pass using 1 buffer page
		passZero();
		currentPage = 1;
		passNumber++;
		
		/* The rest of the passes with 3 buffer pages, 2 for input and 1 for output. 
		 * While loop will keep excecuting the two way external merge sort until we 
		 * reach the final sorted page */
		File file = new File("test.txt");
		while (!file.exists()){
			KB = KB * 2;
			pagesRemaining = pagesMade;
			pass();
			passNumber++;
		}		
		
		//Indicate to the user what the final file is named
		System.out.println("The final sorted file is in \"newfile.txt\"");
	}
	
	public static void passZero() throws IOException{
		//Housekeeping
		String[] ar = null;
		int seek = 0;
		
		//Read the main file
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("test.txt"));
		String sCurrentLine;
		while ((sCurrentLine = br.readLine()) != null) {
				ar = sCurrentLine.split(",");
		} 
		passNumber = 1;
		
		//We should be able to fit about 1100 - 1300 numbers into a file without ecxeeding the 4kb page limit
		size = 1130 * passNumber;
		numberArray = new int [size];
		KB = 4;
		pages = 0;
		fileNumber = 1;
		pagesMade = 0;
		PrintWriter writer = null;
		while(seek < ar.length){
			
			//Creates temporary files to hold 4kb of data
			File file = new File(Integer.toString(fileNumber)+ ".txt");
			if (!file.exists()){
				file.createNewFile();
			}
			
			//Read 4kb of data at a time then sort it
			for (int i = 0; i < size && seek < ar.length; i++, seek++){
				numberArray[i] = Integer.parseInt(ar[i]);
			}
			sort(numberArray);
			
			//After its sorted it writes the 4kb of data into the temporary file(page)
			writer = new PrintWriter(Integer.toString(fileNumber)+ ".txt", "UTF-8");
			for (int i = 0; i < numbers.length; i++){
				writer.print(Integer.toString(numbers[i]));
				if (i != numbers.length -1)
					writer.print(",");
			}
			
			/* Increment fileNumber, pages, and pagesMade
			 * fileNumber - keeps track of the temp file names (eg: "1.txt", "2.txt"...) , this way files 
			 * already written won't be accidently overwritten
			 * pages - keeps track of how mnay pages we have created overall (Helps later for the two way merge sort)
			 * pagesMade - kkeps track of how many pages we have created during this pass
			 * Also close the current file we wrote too */
			fileNumber++;
			pages++;
			pagesMade++;
			writer.close();
		}
		
		//Print out message indicating what pass iterative we are in and how many temp pages were created
		System.out.println("PASS " + (passNumber - 1) + ": (" + (KB/4) + " page runs). " + pagesMade 
				+ " temporary output buffer pages were made of size less than or equal to " + KB + "KB each.\n");
	}
	
	public static void pass() throws IOException{
		//Housekeeping
		PrintWriter writer = null;
		pagesMade = 0;
		int index = 0;
		int stillPages = pages;
		while (currentPage < stillPages){
		String array1[] = null;
		String array2[] = null;
		String sCurrentLine;
		
		// Reads two temporary files we created in the previous pass
		File file = new File(Integer.toString(currentPage) + ".txt");
		if (file.exists()){
			BufferedReader br1 = new BufferedReader(new FileReader(Integer.toString(currentPage) + ".txt"));
			while ((sCurrentLine = br1.readLine()) != null) {
					array1 = sCurrentLine.split(",");
			}
			
			//Once we have its content in a buffer we delete the temporary page we read from
			br1.close();
			file.delete();
		}
		currentPage++;
		file = new File(Integer.toString(currentPage) + ".txt");
		if (file.exists()){
			BufferedReader br2 = new BufferedReader(new FileReader(Integer.toString(currentPage) + ".txt"));
			while ((sCurrentLine = br2.readLine()) != null) {
					array2 = sCurrentLine.split(",");
			}
			
			//Once we have its content in a buffer we delete the temporary page we read from
			br2.close();
			file.delete();
		}
		currentPage++;
		
		//The size of the output buffer page will double every pass
		size = array1.length + array2.length;
		numberArray = new int [size];
		index = 0;
		
		//Merge the data of the two files we read
		for(int i = 0; i < array1.length; i++){
			if (Integer.parseInt(array1[i]) != 0){
			numberArray[index] = Integer.parseInt(array1[i]);
			index++;
			}
		}
		for(int i = 0; i < array2.length; i++){
			if (Integer.parseInt(array2[i]) != 0){
			numberArray[index] = Integer.parseInt(array2[i]);
			index++;
			}
		}
		
		//then sort the data using merge sort algorithm
		sort(numberArray);
		
		/* then create the new temporary page that will contain the content
		 * It will be named final.txt if we are going to be writing into a file for the ast time
		 * else it creates another temporary file with a dumby name*/
		if (pagesRemaining == 2 || pagesRemaining == 1){
			file = new File("final.txt");
			if (!file.exists()){
				file.createNewFile();
			}
			writer = new PrintWriter("final.txt", "UTF-8");
		}
		else{
			file = new File(Integer.toString(fileNumber)+ ".txt");
			if (!file.exists()){
				file.createNewFile();
			}
			writer = new PrintWriter(Integer.toString(fileNumber)+ ".txt", "UTF-8");
		}
		
		//Write the sorted values into the file
		for (int i = 0; i < numbers.length; i++){
			writer.print(Integer.toString(numbers[i]));
			if (i != numbers.length -1)
				writer.print(",");
		}
		
		/* Increment fileNumber, pages, and pagesMade
		 * fileNumber - keeps track of the temp file names (eg: "1.txt", "2.txt"...) , this way files 
		 * already written won't be accidently overwritten
		 * pages - keeps track of how mnay pages we have created overall (Helps later for the two way merge sort)
		 * pagesMade - kkeps track of how many pages we have created during this pass
		 * Also close the current file we wrote too */
		fileNumber++;
		pages++;
		pagesMade++;
		writer.close();
		}
		
		//Print out message indicating what pass iterative we are in and how many temp pages were created
		System.out.println("PASS " + (passNumber - 1) + ": (" + (KB/4) + " page runs). " +  pagesMade +
				" temporary output buffer pages were made of size less than or equal to " + KB + "KB each.\n");
	}
		
	
	//Start of merge sort algorithm
	public static void sort(int[] values) {
	    numbers = values;
	    number = values.length;
	    helper = new int[number];
	    mergesort(0, number - 1);
	}

	  private static void mergesort(int low, int high) {
	    // check if low is smaller then high, if not then the array is sorted
	    if (low < high) {
	      // Get the index of the element which is in the middle
	      int middle = low + (high - low) / 2;
	      // Sort the left side of the array
	      mergesort(low, middle);
	      // Sort the right side of the array
	      mergesort(middle + 1, high);
	      // Combine them both
	      merge(low, middle, high);
	    }
	  }

	  private static void merge(int low, int middle, int high) {
		// Copy both parts into the helper array
	    for (int i = low; i <= high; i++) {
	      helper[i] = numbers[i];
	    }

	    int i = low;
	    int j = middle + 1;
	    int k = low;
	    // Copy the smallest values from either the left or the right side back
	    // to the original array
	    while (i <= middle && j <= high) {
	      if (helper[i] <= helper[j]) {
	        numbers[k] = helper[i];
	        i++;
	      } 
	      else {
	        numbers[k] = helper[j];
	        j++;
	      }
	      k++;
	    }
	    // Copy the rest of the left side of the array into the target array
	    while (i <= middle) {
	      numbers[k] = helper[i];
	      k++;
	      i++;
	    }

	 }
}

