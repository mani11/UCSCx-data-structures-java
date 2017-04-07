
/**
 * File Name: SlistQuickSort 
 * Sort int slist using Quick Sort
 * 
 * @author Jagadeesh Vasudevamurthy
 * @author Wan-Shan Liao
 * @year 2017
 */

/*
 * To compile you require: IntUtil.java RandomInt.java IntSlist2.java SlistSort.java SlistQuickSort.java
 */

class SlistQuickSort extends SlistSort{
  //You can add any number of private members to this class
  //You can add any number of private functions to this class
  
  @Override
  protected void sort(boolean ascend) { 
    //WRITE CODE HERE
    //YOU CAN CALL ANY OF YOUR PRIVATE FUNCTION
    node head = a.first;
    a.first = quickSort(head, null);

    node temp = a.first;
  }

  private node quickSort(node first, node last) {
    if (first == null || first == last)
		return first;

    node[] nodes = partition(first, last);

	numRecursion++;
	node p1 = quickSort(nodes[0], nodes[1]);
	numRecursion++;
	node p2 = quickSort(nodes[1].next, last);
    nodes[1].next = p2;
	return p1;
  }

  private node [] partition(node first, node last) {
    node p1_dummy = new node(0, 0);
    node p2_dummy = new node(0, 0);
    node p1 = p1_dummy;
    node p2 = p2_dummy;

    node pivot = first;
    node curr = first.next;
    while (curr != last) {
        numCompare++;
        if (curr.d < pivot.d) {
            p1.next = curr;
            p1 = p1.next;
        }
        else {
            p2.next = curr;
            p2 = p2.next;
        
        }
        curr = curr.next;
    }

    node[] nodes;
    p1.next = pivot;
    p2.next = last;
    pivot.next = p2_dummy.next;

    nodes = new node[] {p1_dummy.next, pivot};
    return nodes;

  }

  public static void main(String[] args) {
    System.out.println("SlistQuickSort.java");
    SlistQuickSort u = new SlistQuickSort() ;
    u.testBench();
  }

}
