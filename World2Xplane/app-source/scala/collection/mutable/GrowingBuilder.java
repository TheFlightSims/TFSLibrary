/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.TraversableLike;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001!3A!\001\002\001\023\tqqI]8xS:<')^5mI\026\024(BA\002\005\003\035iW\017^1cY\026T!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b\007\001)2AC\013 '\r\0011b\004\t\003\0315i\021AB\005\003\035\031\021a!\0218z%\0264\007\003\002\t\022'yi\021AA\005\003%\t\021qAQ;jY\022,'\017\005\002\025+1\001A!\002\f\001\005\0049\"\001B#mK6\f\"\001G\016\021\0051I\022B\001\016\007\005\035qu\016\0365j]\036\004\"\001\004\017\n\005u1!aA!osB\021Ac\b\003\006A\001\021\r!\t\002\003)>\f\"\001\007\022\021\007\r23#D\001%\025\t)C!A\004hK:,'/[2\n\005\035\"#\001C$s_^\f'\r\\3\t\021%\002!\021!Q\001\ny\tQ!Z7qifDQa\013\001\005\0021\na\001P5oSRtDCA\027/!\021\001\002a\005\020\t\013%R\003\031\001\020\t\017A\002\001\031!C\tc\005)Q\r\\3ngV\ta\004C\0044\001\001\007I\021\003\033\002\023\025dW-\\:`I\025\fHCA\0339!\taa'\003\0028\r\t!QK\\5u\021\035I$'!AA\002y\t1\001\037\0232\021\031Y\004\001)Q\005=\0051Q\r\\3ng\002BQ!\020\001\005\002y\n\001\002\n9mkN$S-\035\013\003\001k\021\001\001\005\006\003r\002\raE\001\002q\")1\t\001C\001\t\006)1\r\\3beR\tQ\007C\003G\001\021\005q)\001\004sKN,H\016\036\013\002=\001")
/*    */ public class GrowingBuilder<Elem, To extends Growable<Elem>> implements Builder<Elem, To> {
/*    */   private final To empty;
/*    */   
/*    */   private To elems;
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
/*    */   public <NewTo> Builder<Elem, NewTo> mapResult(Function1 f) {
/* 24 */     return Builder$class.mapResult(this, f);
/*    */   }
/*    */   
/*    */   public Growable<Elem> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 24 */     return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*    */   }
/*    */   
/*    */   public Growable<Elem> $plus$plus$eq(TraversableOnce xs) {
/* 24 */     return Growable.class.$plus$plus$eq(this, xs);
/*    */   }
/*    */   
/*    */   public GrowingBuilder(Growable empty) {
/* 24 */     Growable.class.$init$(this);
/* 24 */     Builder$class.$init$(this);
/* 25 */     this.elems = (To)empty;
/*    */   }
/*    */   
/*    */   public To elems() {
/* 25 */     return this.elems;
/*    */   }
/*    */   
/*    */   public void elems_$eq(Growable x$1) {
/* 25 */     this.elems = (To)x$1;
/*    */   }
/*    */   
/*    */   public GrowingBuilder<Elem, To> $plus$eq(Object x) {
/* 26 */     elems().$plus$eq(x);
/* 26 */     return this;
/*    */   }
/*    */   
/*    */   public void clear() {
/* 27 */     elems_$eq(this.empty);
/*    */   }
/*    */   
/*    */   public To result() {
/* 28 */     return elems();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\GrowingBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */