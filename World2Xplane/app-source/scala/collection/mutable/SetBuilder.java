/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Set;
/*    */ import scala.collection.SetLike;
/*    */ import scala.collection.TraversableLike;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\00113A!\001\002\001\023\tQ1+\032;Ck&dG-\032:\013\005\r!\021aB7vi\006\024G.\032\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\013\004\025Uy2c\001\001\f\037A\021A\"D\007\002\r%\021aB\002\002\007\003:L(+\0324\021\tA\t2CH\007\002\005%\021!C\001\002\b\005VLG\016Z3s!\t!R\003\004\001\005\013Y\001!\031A\f\003\003\005\013\"\001G\016\021\0051I\022B\001\016\007\005\035qu\016\0365j]\036\004\"\001\004\017\n\005u1!aA!osB\021Ac\b\003\006A\001\021\r!\t\002\005\007>dG.\005\002\031EI\0311%J\025\007\t\021\002\001A\t\002\ryI,g-\0338f[\026tGO\020\t\004M\035\032R\"\001\003\n\005!\"!aA*fiB!aEK\n\037\023\tYCAA\004TKRd\025n[3\t\0215\002!\021!Q\001\ny\tQ!Z7qifDQa\f\001\005\002A\na\001P5oSRtDCA\0313!\021\001\002a\005\020\t\0135r\003\031\001\020\t\017Q\002\001\031!C\tk\005)Q\r\\3ngV\ta\004C\0048\001\001\007I\021\003\035\002\023\025dW-\\:`I\025\fHCA\035=!\ta!(\003\002<\r\t!QK\\5u\021\035id'!AA\002y\t1\001\037\0232\021\031y\004\001)Q\005=\0051Q\r\\3ng\002BQ!\021\001\005\002\t\013\001\002\n9mkN$S-\035\013\003\007\022k\021\001\001\005\006\013\002\003\raE\001\002q\")q\t\001C\001\021\006)1\r\\3beR\t\021\bC\003K\001\021\0051*\001\004sKN,H\016\036\013\002=\001")
/*    */ public class SetBuilder<A, Coll extends Set<A> & SetLike<A, Coll>> implements Builder<A, Coll> {
/*    */   private final Coll empty;
/*    */   
/*    */   private Coll elems;
/*    */   
/*    */   public void sizeHint(int size) {
/* 22 */     Builder$class.sizeHint(this, size);
/*    */   }
/*    */   
/*    */   public void sizeHint(TraversableLike coll) {
/* 22 */     Builder$class.sizeHint(this, coll);
/*    */   }
/*    */   
/*    */   public void sizeHint(TraversableLike coll, int delta) {
/* 22 */     Builder$class.sizeHint(this, coll, delta);
/*    */   }
/*    */   
/*    */   public void sizeHintBounded(int size, TraversableLike boundingColl) {
/* 22 */     Builder$class.sizeHintBounded(this, size, boundingColl);
/*    */   }
/*    */   
/*    */   public <NewTo> Builder<A, NewTo> mapResult(Function1 f) {
/* 22 */     return Builder$class.mapResult(this, f);
/*    */   }
/*    */   
/*    */   public Growable<A> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 22 */     return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*    */   }
/*    */   
/*    */   public Growable<A> $plus$plus$eq(TraversableOnce xs) {
/* 22 */     return Growable.class.$plus$plus$eq(this, xs);
/*    */   }
/*    */   
/*    */   public SetBuilder(Set empty) {
/* 22 */     Growable.class.$init$(this);
/* 22 */     Builder$class.$init$(this);
/* 23 */     this.elems = (Coll)empty;
/*    */   }
/*    */   
/*    */   public Coll elems() {
/* 23 */     return this.elems;
/*    */   }
/*    */   
/*    */   public void elems_$eq(Set x$1) {
/* 23 */     this.elems = (Coll)x$1;
/*    */   }
/*    */   
/*    */   public SetBuilder<A, Coll> $plus$eq(Object x) {
/* 24 */     elems_$eq((Coll)elems().$plus(x));
/* 24 */     return this;
/*    */   }
/*    */   
/*    */   public void clear() {
/* 25 */     elems_$eq(this.empty);
/*    */   }
/*    */   
/*    */   public Coll result() {
/* 26 */     return elems();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\SetBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */