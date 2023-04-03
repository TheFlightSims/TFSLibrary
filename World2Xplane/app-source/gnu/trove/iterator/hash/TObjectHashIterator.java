/*    */ package gnu.trove.iterator.hash;
/*    */ 
/*    */ import gnu.trove.impl.hash.THashIterator;
/*    */ import gnu.trove.impl.hash.TObjectHash;
/*    */ 
/*    */ public class TObjectHashIterator<E> extends THashIterator<E> {
/*    */   protected final TObjectHash _objectHash;
/*    */   
/*    */   public TObjectHashIterator(TObjectHash<E> hash) {
/* 43 */     super(hash);
/* 44 */     this._objectHash = hash;
/*    */   }
/*    */   
/*    */   protected E objectAtIndex(int index) {
/* 50 */     Object obj = this._objectHash._set[index];
/* 51 */     if (obj == TObjectHash.FREE || obj == TObjectHash.REMOVED)
/* 52 */       return null; 
/* 54 */     return (E)obj;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\iterator\hash\TObjectHashIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */