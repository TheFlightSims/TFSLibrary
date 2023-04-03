/*    */ package gnu.trove.list;
/*    */ 
/*    */ public abstract class TLinkableAdapter<T extends TLinkable> implements TLinkable<T> {
/*    */   private volatile T next;
/*    */   
/*    */   private volatile T prev;
/*    */   
/*    */   public T getNext() {
/* 25 */     return this.next;
/*    */   }
/*    */   
/*    */   public void setNext(T next) {
/* 30 */     this.next = next;
/*    */   }
/*    */   
/*    */   public T getPrevious() {
/* 35 */     return this.prev;
/*    */   }
/*    */   
/*    */   public void setPrevious(T prev) {
/* 40 */     this.prev = prev;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\list\TLinkableAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */