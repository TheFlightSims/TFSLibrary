/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.collection.generic.GenericTraversableTemplate;
/*    */ import scala.collection.mutable.ArrayBuffer;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.runtime.BooleanRef;
/*    */ 
/*    */ public abstract class ViewMkString$class {
/*    */   public static void $init$(ViewMkString $this) {}
/*    */   
/*    */   public static Seq thisSeq(ViewMkString $this) {
/* 22 */     return (Seq)(new ArrayBuffer()).$plus$plus$eq((TraversableOnce)$this).result();
/*    */   }
/*    */   
/*    */   public static String mkString(ViewMkString $this) {
/* 26 */     return ((TraversableOnce)$this).mkString("");
/*    */   }
/*    */   
/*    */   public static String mkString(ViewMkString $this, String sep) {
/* 27 */     return ((TraversableOnce)$this).mkString("", sep, "");
/*    */   }
/*    */   
/*    */   public static String mkString(ViewMkString $this, String start, String sep, String end) {
/* 29 */     return $this.thisSeq().addString(new StringBuilder(), start, sep, end).toString();
/*    */   }
/*    */   
/*    */   public static StringBuilder addString(ViewMkString $this, StringBuilder b, String start, String sep, String end) {
/* 32 */     BooleanRef first = new BooleanRef(true);
/* 33 */     b.append(start);
/* 34 */     ((GenericTraversableTemplate)$this).foreach((Function1)new ViewMkString$$anonfun$addString$1($this, first, b, (ViewMkString<A>)sep));
/* 38 */     b.append(end);
/* 39 */     return b;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\ViewMkString$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */