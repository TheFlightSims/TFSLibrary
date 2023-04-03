/*    */ package scala.math;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001=3q!\001\002\021\002\007\005qAA\bQCJ$\030.\0317Pe\022,'/\0338h\025\t\031A!\001\003nCRD'\"A\003\002\013M\034\027\r\\1\004\001U\021\001bE\n\004\001%i\001C\001\006\f\033\005!\021B\001\007\005\005\031\te.\037*fMB\031abD\t\016\003\tI!\001\005\002\003\013\025\013X/\033<\021\005I\031B\002\001\003\006)\001\021\r!\006\002\002)F\021a#\007\t\003\025]I!\001\007\003\003\0179{G\017[5oOB\021!BG\005\0037\021\0211!\0218z\021\025i\002\001\"\001\037\003\031!\023N\\5uIQ\tq\004\005\002\013A%\021\021\005\002\002\005+:LG\017C\003$\001\031\005A%\001\006uef\034u.\0349be\026$2!J\026.!\rQa\005K\005\003O\021\021aa\0249uS>t\007C\001\006*\023\tQCAA\002J]RDQ\001\f\022A\002E\t\021\001\037\005\006]\t\002\r!E\001\002s\")\001\007\001D\001c\005!A\016^3r)\r\021TG\016\t\003\025MJ!\001\016\003\003\017\t{w\016\\3b]\")Af\fa\001#!)af\fa\001#!)\001\b\001C\001s\005!q\r^3r)\r\021$h\017\005\006Y]\002\r!\005\005\006]]\002\r!\005\005\006{\001!\tAP\001\003YR$2AM A\021\025aC\b1\001\022\021\025qC\b1\001\022\021\025\021\005\001\"\001D\003\t9G\017F\0023\t\026CQ\001L!A\002EAQAL!A\002EAQa\022\001\005\002!\013Q!Z9vSZ$2AM%K\021\025ac\t1\001\022\021\025qc\t1\001\022\021\025a\005\001\"\001N\003\035\021XM^3sg\026,\022A\024\t\004\035\001\t\002")
/*    */ public interface PartialOrdering<T> extends Equiv<T> {
/*    */   Option<Object> tryCompare(T paramT1, T paramT2);
/*    */   
/*    */   boolean lteq(T paramT1, T paramT2);
/*    */   
/*    */   boolean gteq(T paramT1, T paramT2);
/*    */   
/*    */   boolean lt(T paramT1, T paramT2);
/*    */   
/*    */   boolean gt(T paramT1, T paramT2);
/*    */   
/*    */   boolean equiv(T paramT1, T paramT2);
/*    */   
/*    */   PartialOrdering<T> reverse();
/*    */   
/*    */   public class PartialOrdering$$anon$1 implements PartialOrdering<T> {
/*    */     public boolean gteq(Object x, Object y) {
/* 67 */       return PartialOrdering$class.gteq(this, x, y);
/*    */     }
/*    */     
/*    */     public boolean lt(Object x, Object y) {
/* 67 */       return PartialOrdering$class.lt(this, x, y);
/*    */     }
/*    */     
/*    */     public boolean gt(Object x, Object y) {
/* 67 */       return PartialOrdering$class.gt(this, x, y);
/*    */     }
/*    */     
/*    */     public boolean equiv(Object x, Object y) {
/* 67 */       return PartialOrdering$class.equiv(this, x, y);
/*    */     }
/*    */     
/*    */     public PartialOrdering$$anon$1(PartialOrdering $outer) {
/* 67 */       PartialOrdering$class.$init$(this);
/*    */     }
/*    */     
/*    */     public PartialOrdering<T> reverse() {
/* 68 */       return this.$outer;
/*    */     }
/*    */     
/*    */     public boolean lteq(Object x, Object y) {
/* 69 */       return this.$outer.lteq(y, x);
/*    */     }
/*    */     
/*    */     public Option<Object> tryCompare(Object x, Object y) {
/* 70 */       return this.$outer.tryCompare(y, x);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\PartialOrdering.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */