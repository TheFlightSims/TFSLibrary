/*    */ package scala.util.automata;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.runtime.BooleanRef;
/*    */ import scala.runtime.IntRef;
/*    */ 
/*    */ public abstract class Inclusion$class {
/*    */   public static void $init$(Inclusion $this) {}
/*    */   
/*    */   public static final int encode$1(Inclusion $this, int q1, int q2, DetWordAutom dfa1$1) {
/* 29 */     return 1 + q1 + q2 * dfa1$1.nstates();
/*    */   }
/*    */   
/*    */   private static final int decode2$1(Inclusion $this, int c, DetWordAutom dfa1$1) {
/* 30 */     return (c - 1) / dfa1$1.nstates();
/*    */   }
/*    */   
/*    */   private static final int decode1$1(Inclusion $this, int c, DetWordAutom dfa1$1) {
/* 31 */     return (c - 1) % dfa1$1.nstates();
/*    */   }
/*    */   
/*    */   public static boolean inclusion(Inclusion<A> $this, DetWordAutom dfa1, DetWordAutom dfa2) {
/* 33 */     IntRef q1 = new IntRef(0);
/* 34 */     IntRef q2 = new IntRef(0);
/* 36 */     int max = 1 + dfa1.nstates() * dfa2.nstates();
/* 37 */     int[] mark = new int[max];
/* 39 */     BooleanRef result = new BooleanRef(true);
/* 40 */     int current = encode$1($this, q1.elem, q2.elem, dfa1);
/* 41 */     IntRef last = new IntRef(current);
/* 42 */     mark[last.elem] = max;
/* 43 */     while (current != 0 && result.elem) {
/* 45 */       $this.labels().foreach((Function1)new Inclusion$$anonfun$inclusion$1($this, q1, q2, max, mark, result, last, dfa1, (Inclusion<A>)dfa2));
/* 58 */       int ncurrent = mark[current];
/* 59 */       if (ncurrent != max) {
/* 60 */         q1.elem = decode1$1($this, ncurrent, dfa1);
/* 61 */         q2.elem = decode2$1($this, ncurrent, dfa1);
/* 62 */         current = ncurrent;
/*    */         continue;
/*    */       } 
/* 64 */       current = 0;
/*    */     } 
/* 67 */     return result.elem;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\automata\Inclusion$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */