/**
 * File Name: SlistMergeSort.java 
 * Sort int slist using Merge Sort
 * 
 * @author Jagadeesh Vasudevamurthy
 * @author Wan-Shan Liao
 * @year 2017
 */

/*
 * To compile you require: IntUtil.java RandomInt.java IntSlist2.java SlistSort.java SlistMergeSort.java
 */

class SlistMergeSort extends SlistSort{
  //You can add any number of private members to this class
  //You can add any number of private functions to this class
 
  @Override
  protected void sort(boolean ascend) {
    //WRITE CODE HERE
    //YOU CAN CALL ANY OF YOUR PRIVATE FUNCTION 
    node head = a.first;
    a.first = mergeSort(head);
  }
 
  /** Merge sort */
  private node mergeSort(node head) {
    if (head == null || head.next == null)
        return head;

    // split linked list in half
    node mid = getMid(head);
    node p1 = head;
    node p2 = mid.next; 
	mid.next = null;

    // sort each parts and then merge
	numRecursion++;
	p1 = mergeSort(p1);
	numRecursion++;
	p2 = mergeSort(p2);

	return merge(p1, p2);
  }

  /** Get middle node of list */
  private node getMid(node head) {
    if (head == null)
        return head;

    node fast = head;
    node slow = head;
    numCompare++;
    while (fast.next != null && fast.next.next != null) {
        fast = fast.next.next;
        slow = slow.next;
    }
    return slow;
  }

  /** Merge */
  private node merge(node p1, node p2) {
    node dummy = new node(0, 0);
    node curr = dummy;

    while (p1 != null && p2 != null) {
        node newNode;
        numCompare++;
        numSwap++;

        if (p1.d <= p2.d) {
            newNode = p1;
            p1 = p1.next;
        } else {
            newNode = p2;
            p2 = p2.next;
        }

        curr.next = newNode;
        curr = curr.next;
    }
    
    if (p1 != null) {
        curr.next = p1;
    }

    if (p2 != null) {
        curr.next = p2;
    }

    return dummy.next;
  }


  public static void main(String[] args) {
    System.out.println("SlistMergeSort.java");
    SlistMergeSort u = new SlistMergeSort() ;
    u.testBench();
  }
}


