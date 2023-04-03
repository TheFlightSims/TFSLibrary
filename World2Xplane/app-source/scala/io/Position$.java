/*    */ package scala.io;
/*    */ 
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class Position$ extends Position {
/*    */   public static final Position$ MODULE$;
/*    */   
/*    */   private final int NOPOS;
/*    */   
/*    */   private final int FIRSTPOS;
/*    */   
/*    */   private Position$() {
/* 70 */     MODULE$ = this;
/* 77 */     this.FIRSTPOS = encode(1, 1);
/*    */   }
/*    */   
/*    */   public final int NOPOS() {
/*    */     return 0;
/*    */   }
/*    */   
/*    */   public final int FIRSTPOS() {
/* 77 */     return this.FIRSTPOS;
/*    */   }
/*    */   
/*    */   public void checkInput(int line, int column) {
/* 80 */     if (line < 0)
/* 81 */       throw new IllegalArgumentException((new StringBuilder()).append(line).append(" < 0").toString()); 
/* 82 */     if (line == 0 && column != 0)
/* 83 */       throw new IllegalArgumentException((new StringBuilder()).append(line).append(",").append(BoxesRunTime.boxToInteger(column)).append(" not allowed").toString()); 
/* 84 */     if (column < 0)
/* 85 */       throw new IllegalArgumentException((new StringBuilder()).append(line).append(",").append(BoxesRunTime.boxToInteger(column)).append(" not allowed").toString()); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\io\Position$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */