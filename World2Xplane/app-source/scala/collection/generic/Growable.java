/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0013q!\001\002\021\002\007\005\021B\001\005He><\030M\0317f\025\t\031A!A\004hK:,'/[2\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\005)\t3c\001\001\f\037A\021A\"D\007\002\r%\021aB\002\002\007\003:L(+\0324\021\005A\tR\"\001\002\n\005I\021!!C\"mK\006\024\030M\0317f\021\025!\002\001\"\001\026\003\031!\023N\\5uIQ\ta\003\005\002\r/%\021\001D\002\002\005+:LG\017C\003\033\001\031\0051$\001\005%a2,8\017J3r)\taR$D\001\001\021\025q\022\0041\001 \003\021)G.Z7\021\005\001\nC\002\001\003\007E\001A)\031A\022\003\003\005\013\"\001J\024\021\0051)\023B\001\024\007\005\035qu\016\0365j]\036\004\"\001\004\025\n\005%2!aA!os\")!\004\001C\001WQ!A\004\f\0301\021\025i#\0061\001 \003\025)G.Z72\021\025y#\0061\001 \003\025)G.Z73\021\025\t$\0061\0013\003\025)G.Z7t!\ra1gH\005\003i\031\021!\002\020:fa\026\fG/\0323?\021\0251\004\001\"\0018\0035!\003\017\\;tIAdWo\035\023fcR\021A\004\017\005\006sU\002\rAO\001\003qN\0042a\017\037 \033\005!\021BA\037\005\005=!&/\031<feN\f'\r\\3P]\016,\007\"B \001\r\003)\022!B2mK\006\024\b")
/*    */ public interface Growable<A> extends Clearable {
/*    */   Growable<A> $plus$eq(A paramA);
/*    */   
/*    */   Growable<A> $plus$eq(A paramA1, A paramA2, Seq<A> paramSeq);
/*    */   
/*    */   Growable<A> $plus$plus$eq(TraversableOnce<A> paramTraversableOnce);
/*    */   
/*    */   void clear();
/*    */   
/*    */   public class Growable$$anonfun$$plus$plus$eq$1 extends AbstractFunction1<A, Growable<A>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Growable<A> apply(Object elem) {
/* 48 */       return this.$outer.$plus$eq((A)elem);
/*    */     }
/*    */     
/*    */     public Growable$$anonfun$$plus$plus$eq$1(Growable $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\Growable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */