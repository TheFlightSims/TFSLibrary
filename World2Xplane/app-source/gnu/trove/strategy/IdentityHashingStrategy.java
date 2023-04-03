/*    */ package gnu.trove.strategy;
/*    */ 
/*    */ public class IdentityHashingStrategy<K> implements HashingStrategy<K> {
/*    */   static final long serialVersionUID = -5188534454583764904L;
/*    */   
/* 15 */   public static final IdentityHashingStrategy<Object> INSTANCE = new IdentityHashingStrategy();
/*    */   
/*    */   public int computeHashCode(K object) {
/* 20 */     return System.identityHashCode(object);
/*    */   }
/*    */   
/*    */   public boolean equals(K o1, K o2) {
/* 24 */     return (o1 == o2);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\strategy\IdentityHashingStrategy.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */