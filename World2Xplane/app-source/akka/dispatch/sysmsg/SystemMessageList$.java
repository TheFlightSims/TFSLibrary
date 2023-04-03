/*    */ package akka.dispatch.sysmsg;
/*    */ 
/*    */ public final class SystemMessageList$ {
/*    */   public static final SystemMessageList$ MODULE$;
/*    */   
/*    */   private final SystemMessage LNil;
/*    */   
/*    */   private final SystemMessage ENil;
/*    */   
/*    */   private SystemMessageList$() {
/* 15 */     MODULE$ = this;
/* 16 */     null;
/* 16 */     this.LNil = null;
/* 17 */     null;
/* 17 */     this.ENil = null;
/*    */   }
/*    */   
/*    */   public final SystemMessage LNil() {
/*    */     return this.LNil;
/*    */   }
/*    */   
/*    */   public final SystemMessage ENil() {
/* 17 */     return this.ENil;
/*    */   }
/*    */   
/*    */   public int sizeInner(SystemMessage head, int acc) {
/*    */     while (true) {
/* 20 */       if (head == null)
/* 20 */         return acc; 
/* 20 */       acc++;
/* 20 */       head = head.next();
/*    */     } 
/*    */   }
/*    */   
/*    */   public SystemMessage reverseInner(SystemMessage head, SystemMessage acc) {
/*    */     while (true) {
/* 24 */       if (head == null)
/* 24 */         return acc; 
/* 25 */       SystemMessage next = head.next();
/* 26 */       head.next_$eq(acc);
/* 27 */       acc = head;
/* 27 */       head = next;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\SystemMessageList$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */