/*    */ package scala.util.hashing;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Serializable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0314q!\001\002\021\002G\005\021BA\004ICND\027N\\4\013\005\r!\021a\0025bg\"Lgn\032\006\003\013\031\tA!\036;jY*\tq!A\003tG\006d\027m\001\001\026\005)a2c\001\001\f\037A\021A\"D\007\002\r%\021aB\002\002\007\003:L(+\0324\021\0051\001\022BA\t\007\0051\031VM]5bY&T\030M\0317f\021\025\031\002A\"\001\025\003\021A\027m\0355\025\005UA\002C\001\007\027\023\t9bAA\002J]RDQ!\007\nA\002i\t\021\001\037\t\0037qa\001\001B\003\036\001\t\007aDA\001U#\ty\"\005\005\002\rA%\021\021E\002\002\b\035>$\b.\0338h!\ta1%\003\002%\r\t\031\021I\\=)\007\0011C\006\005\002(U5\t\001F\003\002*\r\005Q\021M\0348pi\006$\030n\0348\n\005-B#\001E5na2L7-\033;O_R4u.\0368eC\005i\023!\n(pA%l\007\017\\5dSR\004\003*Y:iS:<\007\005Z3gS:,G\r\t4pe\002\"3\020V?/\017\025y#\001#\0011\003\035A\025m\0355j]\036\004\"!\r\032\016\003\t1Q!\001\002\t\002M\0322AM\006\020\021\025)$\007\"\0017\003\031a\024N\\5u}Q\t\001G\002\0039e\tI$a\002#fM\006,H\016^\013\003uu\0322aN\006<!\r\t\004\001\020\t\0037u\"Q!H\034C\002yAQ!N\034\005\002}\"\022\001\021\t\004\003^bT\"\001\032\t\013M9D\021A\"\025\005U!\005\"B\rC\001\004a\004\"\002$3\t\0079\025a\0023fM\006,H\016^\013\003\021.+\022!\023\t\004\003^R\005CA\016L\t\025iRI1\001\037\021\025i%\007\"\001O\00311'o\\7Gk:\034G/[8o+\tyU\013\006\002Q-J\031\021kC*\007\tIc\005\001\025\002\ryI,g-\0338f[\026tGO\020\t\004c\001!\006CA\016V\t\025iBJ1\001\037\021\0259F\n1\001Y\003\0051\007\003\002\007Z)VI!A\027\004\003\023\031+hn\031;j_:\f\004b\002/3\003\003%I!X\001\fe\026\fGMU3t_24X\rF\001_!\tyF-D\001a\025\t\t'-\001\003mC:<'\"A2\002\t)\fg/Y\005\003K\002\024aa\0242kK\016$\b")
/*    */ public interface Hashing<T> extends Serializable {
/*    */   int hash(T paramT);
/*    */   
/*    */   public static class Default<T> implements Hashing<T> {
/*    */     public int hash(Object x) {
/* 31 */       return ScalaRunTime$.MODULE$.hash(x);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Hashing$$anon$1 implements Hashing<T> {
/*    */     private final Function1 f$1;
/*    */     
/*    */     public Hashing$$anon$1(Function1 f$1) {}
/*    */     
/*    */     public int hash(Object x) {
/* 37 */       return BoxesRunTime.unboxToInt(this.f$1.apply(x));
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\hashing\Hashing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */