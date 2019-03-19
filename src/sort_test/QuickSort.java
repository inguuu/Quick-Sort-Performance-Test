package sort_test;

import java.util.Random;

public class QuickSort { 
      static int Max =1000;// 배열의 개수
	  static int count = 0;//재귀 횟수 
	  public void swap(int Array[], int i, int j){ 
	      int temp; 
	      temp = Array[i]; 
	      Array[i] = Array[j]; 
	      Array[j] = temp; 
	   } 
	  
	  //퀵정렬
	  public static int partition(int[] arr, int left, int right, int pivotIndex) { 
		    int pivotValue = arr[pivotIndex]; 
		    int tmp = arr[pivotIndex]; 
		    arr[pivotIndex] = arr[right]; 
		    arr[right] = tmp; 
		    int storeIndex = left; 
		    for(int i= left ; i< right ; i++) { 
		      if( arr[i] < pivotValue ) { 
		        tmp = arr[i]; 
		        arr[i] = arr[storeIndex]; 
		        arr[storeIndex] = tmp; 
		        storeIndex++; 
		     } 
		  } 
		    
		  tmp = arr[storeIndex]; 
		  arr[storeIndex] = arr[right]; 
		  arr[right] = tmp; 
		  return storeIndex; 
		} 
		public static void quicksort(int[] arr, int left, int right) { 
		  if( right > left ) { 
		    
		    // 피봇 3가지 위치
			//int pivotIndex = (right+left)/2; 
			int pivotIndex = left ; 
			//Random rand = new Random();
	        //int pivotIndex = rand.nextInt(Max-1); 
			
			count++;
		    int pivotNewIndex = partition(arr, left, right, pivotIndex); 
		    quicksort(arr, left, pivotNewIndex - 1); 
		    quicksort(arr, pivotNewIndex + 1, right); 
		 } 
		} 

		//선택정렬 
		public static void selectionSort(int[] arr) {

			  int last = arr.length - 1;
			  
			  for (int n = last; n > 0; n--) {
				  
			   int k = theLargest(arr, n);
			   // 마지막값과 가장 큰값을 교환
			   int temp = arr[n];
			   arr[n] = arr[k];
			   arr[k] = temp;
			  
			  }
			 }


			 // 배열에서 가장 큰 수의 인덱스를 찾는다.

			 public static int theLargest(int[] arr, int last) {

			  int largest = 1;

			  for (int i = 0; i <= last; i++) {

			   if (arr[i] > arr[largest]) {
				   count++;
			    largest = i;
			   }
			  }
			  return largest;
			 }

	     //합병정렬
		 public static int[] sorted = new int[Max];
		 
		    public static void mergeSort(int[] arr, int m, int n) {
		        int middle;
		        if (m < n) {
		            middle = (m + n) / 2;
		            mergeSort(arr, m, middle);
		            mergeSort(arr, middle + 1, n);
		            merge(arr, m, middle, n);
		        }
		    }
		 
		    public static void merge(int[] arr, int m, int middle, int n) {
		        int i, j, k, t;
		 
		        i = m;
		        j = middle + 1;
		        k = m;
		 
		        while (i <= middle && j <= n) {
		            if (arr[i] <= arr[j])
		                sorted[k] = arr[i++];
		            else
		                sorted[k] = arr[j++];
		            k++;
		        }
		 
		        if (i > middle) {
		            for (t = j; t <= n; t++, k++)
		                sorted[k] = arr[t];
		        } else {
		            for (t = i; t <= middle; t++, k++)
		                sorted[k] = arr[t];
		        }
		 
		        for (t = m; t <= n; t++)
		            arr[t] = sorted[t];
		 
		   
		 
		    }


			
	   public static void main(String[] args) { 
	      
		 
	      int [] Array= new int[Max];
	      QuickSort s = new QuickSort(); 
	      
	  
	      
//	      //Case1. 모든배열 무작위 
//	      //Max개의 배열 생성
//	      for(int i=0;i<Array.length;i++) {
//	    	  Array[i]=i;
//	      }
//	      
//          //배열 섞기
//	      for(int i=0; i<Array.length; i++){ 
//	    	  Random rand = new Random();
//              
//	    	 s.swap(Array, rand.nextInt(Max-1),rand.nextInt(Max-1) ); 
//	      }
	      
	      //Case2. 역순
	      for(int i=0;i<Array.length;i++) {
	    	  Array[i]=Max-i;
	      }
	      
//	      System.out.print("정렬 전 : "); 
//	      for(int i=0; i<Array.length; i++){ 
//	         System.out.print(" " + Array[i]); 
//	      } 
	   
     
//	      //Case3. 정렬된 배열중 하나만 안맞을 경우 
//	      //Max개의 배열 생성
//	      for(int i=0;i<Array.length;i++) {
//	    	  Array[i]=i;
//	      }
//	      
//          //배열 인덱스 하나만 변경
//	      Array[(Array.length)/6]=22;
	      
	      
	      //시간 테스트
          long start=System.currentTimeMillis();
	      s.quicksort(Array, 0, Max-1); //퀵정렬
	      //s.selectionSort(Array); // 선택정렬
	      //s.mergeSort(Array, 0, Array.length - 1);//합병정렬
	      long end=System.currentTimeMillis();
	      
	      System.out.println(""); 
	      System.out.print("정렬 시간: "+ (end-start)*0.001+"초"+" 퀵소트 호출 횟수: "+QuickSort.count );
	      
	      
//	      System.out.println(""); 
//	      System.out.print("정렬 후 : "); 
//	      for(int i=0; i<Array.length; i++){ 
//	         System.out.print(" " + Array[i]); 
//	      }
	   } 

	} 


