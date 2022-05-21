public class InsertionSort {


    /*sort insertion*/

    /*metode for å sortere arrayen*/

    public void sort_Insertion(int[] sort_Arr){

        for(int i = 0; i< sort_Arr.length; i++){
            int j = i;

            while(j > 0 && sort_Arr[j-1]>sort_Arr[j]){
                int key = sort_Arr[j];
                sort_Arr[j] = sort_Arr[j-1];
                sort_Arr[j-1] = key;
                j = j-1;
            }
        }
    }
}