/*    */ package scala.util.automata;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.collection.Seq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BooleanRef;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.IntRef;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\t3q!\001\002\021\002\007\005\021BA\005J]\016dWo]5p]*\0211\001B\001\tCV$x.\\1uC*\021QAB\001\005kRLGNC\001\b\003\025\0318-\0317b\007\001)\"A\003\024\024\005\001Y\001C\001\007\016\033\0051\021B\001\b\007\005\031\te.\037*fM\")\001\003\001C\001#\0051A%\0338ji\022\"\022A\005\t\003\031MI!\001\006\004\003\tUs\027\016\036\005\b-\001\021\rQ\"\001\030\003\031a\027MY3mgV\t\001\004E\002\032C\021r!AG\020\017\005mqR\"\001\017\013\005uA\021A\002\037s_>$h(C\001\b\023\t\001c!A\004qC\016\\\027mZ3\n\005\t\032#aA*fc*\021\001E\002\t\003K\031b\001\001B\003(\001\t\007\001FA\001B#\tI3\002\005\002\rU%\0211F\002\002\b\035>$\b.\0338h\021\025i\003\001\"\001/\003%Ign\0317vg&|g\016F\0020ea\002\"\001\004\031\n\005E2!a\002\"p_2,\027M\034\005\006g1\002\r\001N\001\005I\032\f\027\007E\0026m\021j\021AA\005\003o\t\021A\002R3u/>\024H-Q;u_6DQ!\017\027A\002Q\nA\001\0324be!\"\001a\017 A!\taA(\003\002>\r\tQA-\0329sK\016\fG/\0323\"\003}\n!\004\0265jg\002\032G.Y:tA]LG\016\034\021cK\002\022X-\\8wK\022\f\023!Q\001\007e9\n\004G\f\031")
/*    */ public interface Inclusion<A> {
/*    */   Seq<A> labels();
/*    */   
/*    */   boolean inclusion(DetWordAutom<A> paramDetWordAutom1, DetWordAutom<A> paramDetWordAutom2);
/*    */   
/*    */   public class Inclusion$$anonfun$inclusion$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final IntRef q1$1;
/*    */     
/*    */     private final IntRef q2$1;
/*    */     
/*    */     private final int max$1;
/*    */     
/*    */     private final int[] mark$1;
/*    */     
/*    */     private final BooleanRef result$1;
/*    */     
/*    */     private final IntRef last$1;
/*    */     
/*    */     private final DetWordAutom dfa1$1;
/*    */     
/*    */     private final DetWordAutom dfa2$1;
/*    */     
/*    */     public Inclusion$$anonfun$inclusion$1(Inclusion $outer, IntRef q1$1, IntRef q2$1, int max$1, int[] mark$1, BooleanRef result$1, IntRef last$1, DetWordAutom dfa1$1, DetWordAutom dfa2$1) {}
/*    */     
/*    */     public final void apply(Object letter) {
/* 46 */       int r1 = this.dfa1$1.next(this.q1$1.elem, letter);
/* 47 */       int r2 = this.dfa2$1.next(this.q2$1.elem, letter);
/* 48 */       if (this.dfa1$1.isFinal(r1) && !this.dfa2$1.isFinal(r2))
/* 49 */         this.result$1.elem = false; 
/* 50 */       int test = Inclusion$class.encode$1(this.$outer, r1, r2, this.dfa1$1);
/* 52 */       if (this.mark$1[test] == 0) {
/* 53 */         this.mark$1[this.last$1.elem] = test;
/* 54 */         this.mark$1[test] = this.max$1;
/* 55 */         this.last$1.elem = test;
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\automata\Inclusion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */