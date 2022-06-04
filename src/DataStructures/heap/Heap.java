package DataStructures.heap;

public class Heap {
    int maxSize,size;
    int[] arr;

    public Heap(int mS, int sz){
        maxSize = mS;
        size = sz;
        arr = new int[maxSize];
    }
    // Formula to get the parent of the i node ARR Init:0
    public int parent(int i){
        return(Math.floorDiv(i-1,2));
    }
    // Formula to get the left child of the i node ARR Init:0
    public int leftChild(int i){
        return ((2*i)+1);
    }
    // Formula to get the right child of the i node ARR Init:0
    public int rightChild(int i){
        return ((2*i)+2);
    }

    // If i is greater than it's parent then swap them
    public void siftUp(int i){
        while (i>1 && arr[parent(i)]< arr[i]){
            int tmp = arr[parent(i)];
            arr[parent(i)] = arr[i];
            arr[i] = tmp;
            i = parent(i);
        }
    }
    // Select the max between left and right child and if the max is greater than the parent swap them
    public void siftDown(int i){
        int maxIndex = i;
        int left = leftChild(i);
        if (left<size && arr[left]> arr[maxIndex]){
            maxIndex = left;
        }
        int right = rightChild(i);
        if(right<size && arr[right]> arr[maxIndex]){
            maxIndex = right;
        }
        if (i!=maxIndex){
            int tmp = arr[i];
            arr[i] = arr[maxIndex];
            arr[maxIndex] = tmp;
            siftDown(maxIndex);
        }
    }

    public void insert(int p){
        if(size==maxSize){
            System.out.println("Error out of index");
            return;
        }
        arr[size] = p;
        siftUp(size);
        size++;
    }

    public int extractMax(){
        int result = arr[0];
        arr[0] = arr[size-1];
        size--;
        siftDown(0);
        return result;
    }

    public void remove(int i){
        arr[i] = Integer.MAX_VALUE;
        siftUp(i);
        extractMax();
    }

    public void changePriority(int i, int p){
        int oldp = arr[i];
        arr[i] = p;
        if(p>oldp){
            siftUp(i);
        }else{
            siftDown(i);
        }
    }

    public void heapSort(Heap heap) {
        heap.buildHeap(heap.arr);
        for (int i=0; i<heap.arr.length;i++){
            heap.extractMax();
            heap.siftDown(i);
        }
    }

    public void buildHeap(int[] heap){
        size = heap.length;
        for(int i = Math.floorDiv(size,2);i>=0;i--){
            siftDown(i);
        }
    }
}
