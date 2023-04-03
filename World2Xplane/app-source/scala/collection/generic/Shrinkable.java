/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001i2q!\001\002\021\002\007\005\021B\001\006TQJLgn[1cY\026T!a\001\003\002\017\035,g.\032:jG*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001U\021!\"H\n\003\001-\001\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g\021\025\001\002\001\"\001\022\003\031!\023N\\5uIQ\t!\003\005\002\r'%\021AC\002\002\005+:LG\017C\003\027\001\031\005q#A\005%[&tWo\035\023fcR\021\001$G\007\002\001!)!$\006a\0017\005!Q\r\\3n!\taR\004\004\001\005\ry\001\001R1\001 \005\005\t\025C\001\021$!\ta\021%\003\002#\r\t9aj\034;iS:<\007C\001\007%\023\t)cAA\002B]fDQA\006\001\005\002\035\"B\001\007\025+Y!)\021F\na\0017\005)Q\r\\3nc!)1F\na\0017\005)Q\r\\3ne!)QF\na\001]\005)Q\r\\3ngB\031AbL\016\n\005A2!A\003\037sKB,\027\r^3e}!)!\007\001C\001g\005yA%\\5okN$S.\0338vg\022*\027\017\006\002\031i!)Q'\ra\001m\005\021\001p\035\t\004oaZR\"\001\003\n\005e\"!a\004+sCZ,'o]1cY\026|enY3")
/*    */ public interface Shrinkable<A> {
/*    */   Shrinkable<A> $minus$eq(A paramA);
/*    */   
/*    */   Shrinkable<A> $minus$eq(A paramA1, A paramA2, Seq<A> paramSeq);
/*    */   
/*    */   Shrinkable<A> $minus$minus$eq(TraversableOnce<A> paramTraversableOnce);
/*    */   
/*    */   public class Shrinkable$$anonfun$$minus$minus$eq$1 extends AbstractFunction1<A, Shrinkable<A>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Shrinkable<A> apply(Object elem) {
/* 49 */       return this.$outer.$minus$eq((A)elem);
/*    */     }
/*    */     
/*    */     public Shrinkable$$anonfun$$minus$minus$eq$1(Shrinkable $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\Shrinkable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */