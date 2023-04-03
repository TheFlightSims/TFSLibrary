/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.math.Ordering;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0214Q!\001\002\002\002%\021qcR3oKJL7m\024:eKJ,GmQ8na\006t\027n\0348\013\005\r!\021aB4f]\026\024\030n\031\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\013\003\025Y\031\"\001A\006\021\0051iQ\"\001\004\n\00591!AB!osJ+g\rC\003\021\001\021\005\021#\001\004=S:LGO\020\013\002%A\0311\003\001\013\016\003\t\001\"!\006\f\r\001\0211q\003\001CC\002a\021!aQ\"\026\005e\021\023C\001\016\036!\ta1$\003\002\035\r\t9aj\034;iS:<\007c\001\020 C5\tA!\003\002!\t\tYAK]1wKJ\034\030M\0317f!\t)\"\005B\003$-\t\007AEA\001Y#\tQR\005\005\002\rM%\021qE\002\002\004\003:LXAB\025\001A\003E!F\001\003D_2d\007GA\026.!\r)b\003\f\t\003+5\"\021B\f\025\002\002\003\005)\021\001\023\003\007}#\023\007C\0031\001\031\005\021'\001\006oK^\024U/\0337eKJ,\"A\r\036\025\005Mj\004\003\002\0338sqj\021!\016\006\003m\021\tq!\\;uC\ndW-\003\0029k\t9!)^5mI\026\024\bCA\013;\t\025YtF1\001%\005\005\t\005cA\013\027s!)ah\fa\002\005\031qN\0353\021\007\001C\025H\004\002B\r:\021!)R\007\002\007*\021A\tC\001\007yI|w\016\036 \n\003\035I!a\022\004\002\017A\f7m[1hK&\021\021J\023\002\t\037J$WM]5oO*\021qI\002\005\006\031\002!\t!T\001\006K6\004H/_\013\003\035F#\"a\024*\021\007U1\002\013\005\002\026#\022)1h\023b\001I!91kSA\001\002\b!\026AC3wS\022,gnY3%cA\031\001\t\023)\t\013Y\003A\021A,\002\013\005\004\b\017\\=\026\005acFCA-`)\tQV\fE\002\026-m\003\"!\006/\005\013m*&\031\001\023\t\013y*\0069\0010\021\007\001C5\fC\003a+\002\007\021-A\003fY\026l7\017E\002\rEnK!a\031\004\003\025q\022X\r]3bi\026$g\b")
/*    */ public abstract class GenericOrderedCompanion<CC extends Traversable<Object>> {
/*    */   public abstract <A> Builder<A, CC> newBuilder(Ordering<A> paramOrdering);
/*    */   
/*    */   public <A> CC empty(Ordering<A> evidence$1) {
/* 26 */     return (CC)newBuilder(evidence$1).result();
/*    */   }
/*    */   
/*    */   public <A> CC apply(Seq elems, Ordering<?> ord) {
/* 29 */     Builder<?, Traversable> b = newBuilder(ord);
/* 30 */     b.$plus$plus$eq((TraversableOnce)elems);
/* 31 */     return (CC)b.result();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\GenericOrderedCompanion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */