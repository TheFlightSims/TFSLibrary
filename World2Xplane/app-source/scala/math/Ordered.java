/*    */ package scala.math;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001y3q!\001\002\021\002\007\005qAA\004Pe\022,'/\0323\013\005\r!\021\001B7bi\"T\021!B\001\006g\016\fG.Y\002\001+\tAqcE\002\001\0235\001\"AC\006\016\003\021I!\001\004\003\003\007\005s\027\020E\002\017'Ui\021a\004\006\003!E\tA\001\\1oO*\t!#\001\003kCZ\f\027B\001\013\020\005)\031u.\0349be\006\024G.\032\t\003-]a\001\001B\003\031\001\t\007\021DA\001B#\tQ\022\002\005\002\0137%\021A\004\002\002\b\035>$\b.\0338h\021\025q\002\001\"\001 \003\031!\023N\\5uIQ\t\001\005\005\002\013C%\021!\005\002\002\005+:LG\017C\003%\001\031\005Q%A\004d_6\004\030M]3\025\005\031J\003C\001\006(\023\tACAA\002J]RDQAK\022A\002U\tA\001\0365bi\")A\006\001C\001[\005)A\005\\3tgR\021a&\r\t\003\025=J!\001\r\003\003\017\t{w\016\\3b]\")!f\013a\001+!)1\007\001C\001i\005AAe\032:fCR,'\017\006\002/k!)!F\ra\001+!)q\007\001C\001q\005AA\005\\3tg\022*\027\017\006\002/s!)!F\016a\001+!)1\b\001C\001y\005YAe\032:fCR,'\017J3r)\tqS\bC\003+u\001\007Q\003C\003@\001\021\005\001)A\005d_6\004\030M]3U_R\021a%\021\005\006Uy\002\r!F\004\006\007\nA\t\001R\001\b\037J$WM]3e!\t)e)D\001\003\r\025\t!\001#\001H'\t1\005\n\005\002\013\023&\021!\n\002\002\007\003:L(+\0324\t\01313E\021A'\002\rqJg.\033;?)\005!\005\"B(G\t\007\001\026!E8sI\026\024\030N\\4U_>\023H-\032:fIV\021\021+\026\013\003%r#\"aU,\021\007\025\003A\013\005\002\027+\022)aK\024b\0013\t\tA\013C\003Y\035\002\017\021,A\002pe\022\0042!\022.U\023\tY&A\001\005Pe\022,'/\0338h\021\025if\n1\001U\003\005A\b")
/*    */ public interface Ordered<A> extends Comparable<A> {
/*    */   int compare(A paramA);
/*    */   
/*    */   boolean $less(A paramA);
/*    */   
/*    */   boolean $greater(A paramA);
/*    */   
/*    */   boolean $less$eq(A paramA);
/*    */   
/*    */   boolean $greater$eq(A paramA);
/*    */   
/*    */   int compareTo(A paramA);
/*    */   
/*    */   public static class Ordered$$anon$1 implements Ordered<T> {
/*    */     private final Object x$1;
/*    */     
/*    */     private final Ordering ord$1;
/*    */     
/*    */     public boolean $less(Object that) {
/* 97 */       return Ordered$class.$less(this, that);
/*    */     }
/*    */     
/*    */     public boolean $greater(Object that) {
/* 97 */       return Ordered$class.$greater(this, that);
/*    */     }
/*    */     
/*    */     public boolean $less$eq(Object that) {
/* 97 */       return Ordered$class.$less$eq(this, that);
/*    */     }
/*    */     
/*    */     public boolean $greater$eq(Object that) {
/* 97 */       return Ordered$class.$greater$eq(this, that);
/*    */     }
/*    */     
/*    */     public int compareTo(Object that) {
/* 97 */       return Ordered$class.compareTo(this, that);
/*    */     }
/*    */     
/*    */     public int compare(Object that) {
/* 97 */       return this.ord$1.compare(this.x$1, that);
/*    */     }
/*    */     
/*    */     public Ordered$$anon$1(Object x$1, Ordering ord$1) {
/* 97 */       Ordered$class.$init$(this);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\Ordered.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */