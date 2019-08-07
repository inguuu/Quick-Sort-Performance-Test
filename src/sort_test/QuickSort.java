package sort_test;

import java.util.Random;

public class QuickSort { 
      static int Max =1000;// �迭�� ����
	  static int count = 0;//��� Ƚ�� 
	  public void swap(int Array[], int i, int j){ 
	      int temp; 
	      temp = Array[i]; 
	      Array[i] = Array[j]; 
	      Array[j] = temp; 
	   } 
	  
	  //������
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
		    
		    // �Ǻ� 3���� ��ġ
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

		//�������� 
		public static void selectionSort(int[] arr) {

			  int last = arr.length - 1;
			  
			  for (int n = last; n > 0; n--) {
				  
			   int k = theLargest(arr, n);
			   // ���������� ���� ū���� ��ȯ
			   int temp = arr[n];
			   arr[n] = arr[k];
			   arr[k] = temp;
			  
			  }
			 }


			 // �迭���� ���� ū ���� �ε����� ã�´�.

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

	     //�պ�����
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
	      
	  
	      
//	      //Case1. ���迭 ������ 
//	      //Max���� �迭 ����
//	      for(int i=0;i<Array.length;i++) {
//	    	  Array[i]=i;
//	      }
//	      
//          //�迭 ����
//	      for(int i=0; i<Array.length; i++){ 
//	    	  Random rand = new Random();
//              
//	    	 s.swap(Array, rand.nextInt(Max-1),rand.nextInt(Max-1) ); 
//	      }
	      
	      //Case2. ����
	      for(int i=0;i<Array.length;i++) {
	    	  Array[i]=Max-i;
	      }
	      
//	      System.out.print("���� �� : "); 
//	      for(int i=0; i<Array.length; i++){ 
//	         System.out.print(" " + Array[i]); 
//	      } 
	   
     
//	      //Case3. ���ĵ� �迭�� �ϳ��� �ȸ��� ��� 
//	      //Max���� �迭 ����
//	      for(int i=0;i<Array.length;i++) {
//	    	  Array[i]=i;
//	      }
//	      
//          //�迭 �ε��� �ϳ��� ����
//	      Array[(Array.length)/6]=22;
	      
	      
	      //�ð� �׽�Ʈ
          long start=System.currentTimeMillis();
	      s.quicksort(Array, 0, Max-1); //������
	      //s.selectionSort(Array); // ��������
	      //s.mergeSort(Array, 0, Array.length - 1);//�պ�����
	      long end=System.currentTimeMillis();
	      
	      System.out.println(""); 
	      System.out.print("���� �ð�: "+ (end-start)*0.001+"��"+" ����Ʈ ȣ�� Ƚ��: "+QuickSort.count );
	      
	      
//	      System.out.println(""); 
//	      System.out.print("���� �� : "); 
//	      for(int i=0; i<Array.length; i++){ 
//	         System.out.print(" " + Array[i]); 
//	      }
	   } 

	} 


