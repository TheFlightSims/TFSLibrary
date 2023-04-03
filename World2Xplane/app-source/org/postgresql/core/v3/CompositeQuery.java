/*    */ package org.postgresql.core.v3;
/*    */ 
/*    */ import org.postgresql.core.ParameterList;
/*    */ 
/*    */ class CompositeQuery implements V3Query {
/*    */   private final SimpleQuery[] subqueries;
/*    */   
/*    */   private final int[] offsets;
/*    */   
/*    */   CompositeQuery(SimpleQuery[] subqueries, int[] offsets) {
/* 25 */     this.subqueries = subqueries;
/* 26 */     this.offsets = offsets;
/*    */   }
/*    */   
/*    */   public ParameterList createParameterList() {
/* 30 */     SimpleParameterList[] subparams = new SimpleParameterList[this.subqueries.length];
/* 31 */     for (int i = 0; i < this.subqueries.length; i++)
/* 32 */       subparams[i] = (SimpleParameterList)this.subqueries[i].createParameterList(); 
/* 33 */     return new CompositeParameterList(subparams, this.offsets);
/*    */   }
/*    */   
/*    */   public String toString(ParameterList parameters) {
/* 37 */     StringBuffer sbuf = new StringBuffer(this.subqueries[0].toString());
/* 38 */     for (int i = 1; i < this.subqueries.length; i++) {
/* 40 */       sbuf.append(';');
/* 41 */       sbuf.append(this.subqueries[i]);
/*    */     } 
/* 43 */     return sbuf.toString();
/*    */   }
/*    */   
/*    */   public String toString() {
/* 47 */     return toString(null);
/*    */   }
/*    */   
/*    */   public void close() {
/* 51 */     for (int i = 0; i < this.subqueries.length; i++)
/* 52 */       this.subqueries[i].close(); 
/*    */   }
/*    */   
/*    */   public SimpleQuery[] getSubqueries() {
/* 56 */     return this.subqueries;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\v3\CompositeQuery.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */