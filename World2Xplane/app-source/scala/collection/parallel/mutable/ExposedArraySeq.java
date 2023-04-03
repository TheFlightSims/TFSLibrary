/*    */ package scala.collection.parallel.mutable;
/*    */ 
/*    */ import scala.collection.mutable.ArraySeq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001!3Q!\001\002\001\005)\021q\"\022=q_N,G-\021:sCf\034V-\035\006\003\007\021\tq!\\;uC\ndWM\003\002\006\r\005A\001/\031:bY2,GN\003\002\b\021\005Q1m\0347mK\016$\030n\0348\013\003%\tQa]2bY\006,\"aC\n\024\005\001a\001cA\007\020#5\taB\003\002\004\r%\021\001C\004\002\t\003J\024\030-_*fcB\021!c\005\007\001\t\025!\002A1\001\027\005\005!6\001A\t\003/m\001\"\001G\r\016\003!I!A\007\005\003\0179{G\017[5oOB\021\001\004H\005\003;!\0211!\0218z\021!y\002A!A!\002\023\001\023aA1seB\031\001$I\022\n\005\tB!!B!se\006L\bC\001\r%\023\t)\003B\001\004B]f\024VM\032\005\nO\001\021\t\021)A\005Q-\n!a\035>\021\005aI\023B\001\026\t\005\rIe\016^\005\003Y=\ta\001\\3oORD\007\"\002\030\001\t\003y\023A\002\037j]&$h\bF\0021eM\0022!\r\001\022\033\005\021\001\"B\020.\001\004\001\003\"B\024.\001\004A\003bB\033\001\005\004%\tEN\001\006CJ\024\030-_\013\002A!1\001\b\001Q\001\n\001\na!\031:sCf\004\003b\002\027\001\005\004%\tEO\013\002Q!1A\b\001Q\001\n!\nq\001\\3oORD\007\005C\003?\001\021\005s(\001\007tiJLgn\032)sK\032L\0070F\001A!\t\te)D\001C\025\t\031E)\001\003mC:<'\"A#\002\t)\fg/Y\005\003\017\n\023aa\025;sS:<\007")
/*    */ public class ExposedArraySeq<T> extends ArraySeq<T> {
/*    */   private final Object[] array;
/*    */   
/*    */   private final int length;
/*    */   
/*    */   public ExposedArraySeq(Object[] arr, int sz) {
/* 71 */     super(sz);
/* 72 */     this.array = arr;
/* 73 */     this.length = super.length();
/*    */   }
/*    */   
/*    */   public Object[] array() {
/*    */     return this.array;
/*    */   }
/*    */   
/*    */   public int length() {
/* 73 */     return this.length;
/*    */   }
/*    */   
/*    */   public String stringPrefix() {
/* 74 */     return "ArraySeq";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ExposedArraySeq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */