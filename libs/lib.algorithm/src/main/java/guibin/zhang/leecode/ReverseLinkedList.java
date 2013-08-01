package guibin.zhang.leecode;

/**
 *
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class ReverseLinkedList {
    public class ListNode {
        int val;
        ListNode next;
        
        public ListNode(int x) {
            this.val = x;
            this.next = null;
        }
    }
    
    //http://www.youtube.com/watch?v=sYcOK51hl-A
    public ListNode reverse(ListNode head) {
        
        ListNode prev = null;
        ListNode curr = head;
        ListNode nxt = null;
        
        while(curr != null) {
            //a) Save curr.next
            nxt = curr.next;
            //b) Set the pointer to prev, cut off the pointer to nxt.
            curr.next = prev;
            
            //c) Move pre to curr
            prev = curr;
            //d) Move curr to nxt
            curr = nxt;
        }
        
        return prev;
    }
    
    //http://www.technicalypto.com/2010/03/reverse-singly-linked-list-recursively.html
    public ListNode reverse_v2(ListNode head) {
        
        ListNode curr = head;
        
        if(curr == null || curr.next == null) {
            return curr;
        }
        
        //a) Save curr.next
        ListNode nxt = curr.next;
        //b) Cut off the pointer to nxt.
        curr.next = null;
        //c) Reverse remaining nodes.
        ListNode remaining = reverse_v2(nxt);
        //d) Connect curr at the tail of remaining
        nxt.next = curr;
        
        return remaining;
    }
    
    public ListNode reverse_v3(ListNode prev, ListNode curr) {
        if(curr == null) {
            return curr;
        }
        if(curr.next == null) {
            curr.next = prev;
            return curr;
        } else {
            //a) Save curr.next
            ListNode nxt = curr.next;
            //b) Set the pointer to prev, cut off the pointer to nxt.
            curr.next = prev;
            //c) Reverse remaining nodes.
            ListNode remaining = reverse_v3(curr, nxt);
            
            //d) Connect curr at the tail of remaining
            //nxt.next = curr;//Since line 66 has connect curr with prev, this line can be ignored.
            
            return remaining;
        }
    }
    
    public void print(ListNode n) {
        ListNode p = n;
        while(p != null) {
            System.out.print(p.val + " -> ");
            p = p.next;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        ReverseLinkedList rl = new ReverseLinkedList();
        ListNode a = rl.new ListNode(1);
        ListNode b = rl.new ListNode(2);
        ListNode c = rl.new ListNode(3);
        ListNode d = rl.new ListNode(4);
        ListNode e = rl.new ListNode(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        System.out.println("Original:");
        rl.print(a);
        System.out.println("Reversed iterately:");
        ListNode n = rl.reverse(a);
        rl.print(n);
        System.out.println("Reversed recursively:");
        ListNode m = rl.reverse_v2(n);
        rl.print(m);
        System.out.println("Reversed recursively2:");
        ListNode p = rl.reverse_v3(null, m);
        rl.print(p);
    }
}