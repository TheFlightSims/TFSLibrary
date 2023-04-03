/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.GenMapLike;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.TraversableLike;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001I3A!\001\002\001\023\tQQ*\0319Ck&dG-\032:\013\005\r!\021aB7vi\006\024G.\032\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\013\005\025a\021SeE\002\001\027=\001\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g!\021\001\022c\005\023\016\003\tI!A\005\002\003\017\t+\030\016\0343feB!A\002\006\f\"\023\t)bA\001\004UkBdWM\r\t\003/aa\001\001B\003\032\001\t\007!DA\001B#\tYb\004\005\002\r9%\021QD\002\002\b\035>$\b.\0338h!\taq$\003\002!\r\t\031\021I\\=\021\005]\021C!B\022\001\005\004Q\"!\001\"\021\005])C!\002\024\001\005\0049#\001B\"pY2\f\"a\007\025\023\007%ZsF\002\003+\001\001A#\001\004\037sK\032Lg.Z7f]Rt\004\003\002\027.-\005j\021\001B\005\003]\021\021aaR3o\033\006\004\b#\002\0271-\005\"\023BA\031\005\005)9UM\\'ba2K7.\032\005\tg\001\021\t\021)A\005I\005)Q-\0349us\")Q\007\001C\001m\0051A(\0338jiz\"\"a\016\035\021\013A\001a#\t\023\t\013M\"\004\031\001\023\t\017i\002\001\031!C\tw\005)Q\r\\3ngV\tA\005C\004>\001\001\007I\021\003 \002\023\025dW-\\:`I\025\fHCA C!\ta\001)\003\002B\r\t!QK\\5u\021\035\031E(!AA\002\021\n1\001\037\0232\021\031)\005\001)Q\005I\0051Q\r\\3ng\002BQa\022\001\005\002!\013\001\002\n9mkN$S-\035\013\003\023*k\021\001\001\005\006\027\032\003\raE\001\002q\")Q\n\001C\001\035\006)1\r\\3beR\tq\bC\003Q\001\021\005\021+\001\004sKN,H\016\036\013\002I\001")
/*    */ public class MapBuilder<A, B, Coll extends GenMap<A, B> & GenMapLike<A, B, Coll>> implements Builder<Tuple2<A, B>, Coll> {
/*    */   private final Coll empty;
/*    */   
/*    */   private Coll elems;
/*    */   
/*    */   public void sizeHint(int size) {
/* 24 */     Builder$class.sizeHint(this, size);
/*    */   }
/*    */   
/*    */   public void sizeHint(TraversableLike coll) {
/* 24 */     Builder$class.sizeHint(this, coll);
/*    */   }
/*    */   
/*    */   public void sizeHint(TraversableLike coll, int delta) {
/* 24 */     Builder$class.sizeHint(this, coll, delta);
/*    */   }
/*    */   
/*    */   public void sizeHintBounded(int size, TraversableLike boundingColl) {
/* 24 */     Builder$class.sizeHintBounded(this, size, boundingColl);
/*    */   }
/*    */   
/*    */   public <NewTo> Builder<Tuple2<A, B>, NewTo> mapResult(Function1 f) {
/* 24 */     return Builder$class.mapResult(this, f);
/*    */   }
/*    */   
/*    */   public Growable<Tuple2<A, B>> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 24 */     return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*    */   }
/*    */   
/*    */   public Growable<Tuple2<A, B>> $plus$plus$eq(TraversableOnce xs) {
/* 24 */     return Growable.class.$plus$plus$eq(this, xs);
/*    */   }
/*    */   
/*    */   public MapBuilder(GenMap empty) {
/* 24 */     Growable.class.$init$(this);
/* 24 */     Builder$class.$init$(this);
/* 26 */     this.elems = (Coll)empty;
/*    */   }
/*    */   
/*    */   public Coll elems() {
/* 26 */     return this.elems;
/*    */   }
/*    */   
/*    */   public void elems_$eq(GenMap x$1) {
/* 26 */     this.elems = (Coll)x$1;
/*    */   }
/*    */   
/*    */   public MapBuilder<A, B, Coll> $plus$eq(Tuple2 x) {
/* 28 */     elems_$eq((Coll)elems().$plus(x));
/* 32 */     return this;
/*    */   }
/*    */   
/*    */   public void clear() {
/* 34 */     elems_$eq(this.empty);
/*    */   }
/*    */   
/*    */   public Coll result() {
/* 35 */     return elems();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\MapBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */