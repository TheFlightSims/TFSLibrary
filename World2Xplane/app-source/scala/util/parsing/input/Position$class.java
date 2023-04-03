/*    */ package scala.util.parsing.input;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Predef$;
/*    */ import scala.collection.IndexedSeqOptimized;
/*    */ import scala.collection.TraversableLike;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public abstract class Position$class {
/*    */   public static void $init$(Position $this) {}
/*    */   
/*    */   public static String toString(Position $this) {
/* 35 */     return (new StringBuilder()).append("").append(BoxesRunTime.boxToInteger($this.line())).append(".").append(BoxesRunTime.boxToInteger($this.column())).toString();
/*    */   }
/*    */   
/*    */   public static String longString(Position $this) {
/* 48 */     String str1 = $this.lineContents();
/* 48 */     Predef$ predef$1 = Predef$.MODULE$;
/* 48 */     String str2 = (String)IndexedSeqOptimized.class.take((IndexedSeqOptimized)new StringOps(str1), $this.column() - 1);
/* 48 */     Predef$ predef$2 = Predef$.MODULE$;
/* 48 */     return (new StringBuilder()).append($this.lineContents()).append("\n").append(TraversableLike.class.map((TraversableLike)new StringOps(str2), (Function1)new Position$$anonfun$longString$1($this), Predef$.MODULE$.StringCanBuildFrom())).append("^").toString();
/*    */   }
/*    */   
/*    */   public static boolean $less(Position $this, Position that) {
/* 58 */     return ($this.line() < that.line() || (
/* 59 */       $this.line() == that.line() && $this.column() < that.column()));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\input\Position$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */