/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Predef$;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.TraversableLike;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.collection.immutable.List$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001)3Q!\001\002\002\002%\0211\002T1{s\n+\030\016\0343fe*\0211\001B\001\b[V$\030M\0317f\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001+\rQQcH\n\004\001-y\001C\001\007\016\033\0051\021B\001\b\007\005\031\te.\037*fMB!\001#E\n\037\033\005\021\021B\001\n\003\005\035\021U/\0337eKJ\004\"\001F\013\r\001\021)a\003\001b\001/\t!Q\t\\3n#\tA2\004\005\002\r3%\021!D\002\002\b\035>$\b.\0338h!\taA$\003\002\036\r\t\031\021I\\=\021\005QyBA\002\021\001\t\013\007qC\001\002U_\")!\005\001C\001G\0051A(\0338jiz\"\022\001\n\t\005!\001\031b\004C\004'\001\001\007I\021C\024\002\013A\f'\017^:\026\003!\0022\001E\025,\023\tQ#A\001\006MSN$()\0364gKJ\0042\001L\027\024\033\005!\021B\001\030\005\005=!&/\031<feN\f'\r\\3P]\016,\007b\002\031\001\001\004%\t\"M\001\na\006\024Ho]0%KF$\"AM\033\021\0051\031\024B\001\033\007\005\021)f.\033;\t\017Yz\023\021!a\001Q\005\031\001\020J\031\t\ra\002\001\025)\003)\003\031\001\030M\035;tA!)!\b\001C\001w\005AA\005\0357vg\022*\027\017\006\002={5\t\001\001C\003?s\001\0071#A\001y\021\025\001\005\001\"\021B\0035!\003\017\\;tIAdWo\035\023fcR\021AH\021\005\006\007~\002\raK\001\003qNDQ!\022\001\007\002\031\013aA]3tk2$H#\001\020\t\013!\003A\021A%\002\013\rdW-\031:\025\003I\002")
/*    */ public abstract class LazyBuilder<Elem, To> implements Builder<Elem, To> {
/*    */   private ListBuffer<TraversableOnce<Elem>> parts;
/*    */   
/*    */   public void sizeHint(int size) {
/* 20 */     Builder$class.sizeHint(this, size);
/*    */   }
/*    */   
/*    */   public void sizeHint(TraversableLike coll) {
/* 20 */     Builder$class.sizeHint(this, coll);
/*    */   }
/*    */   
/*    */   public void sizeHint(TraversableLike coll, int delta) {
/* 20 */     Builder$class.sizeHint(this, coll, delta);
/*    */   }
/*    */   
/*    */   public void sizeHintBounded(int size, TraversableLike boundingColl) {
/* 20 */     Builder$class.sizeHintBounded(this, size, boundingColl);
/*    */   }
/*    */   
/*    */   public <NewTo> Builder<Elem, NewTo> mapResult(Function1 f) {
/* 20 */     return Builder$class.mapResult(this, f);
/*    */   }
/*    */   
/*    */   public Growable<Elem> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 20 */     return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*    */   }
/*    */   
/*    */   public LazyBuilder() {
/* 20 */     Growable.class.$init$(this);
/* 20 */     Builder$class.$init$(this);
/* 22 */     this.parts = new ListBuffer<TraversableOnce<Elem>>();
/*    */   }
/*    */   
/*    */   public ListBuffer<TraversableOnce<Elem>> parts() {
/* 22 */     return this.parts;
/*    */   }
/*    */   
/*    */   public void parts_$eq(ListBuffer<TraversableOnce<Elem>> x$1) {
/* 22 */     this.parts = x$1;
/*    */   }
/*    */   
/*    */   public LazyBuilder<Elem, To> $plus$eq(Object x) {
/* 23 */     parts().$plus$eq(List$.MODULE$.apply(Predef$.MODULE$.genericWrapArray(new Object[] { x })));
/* 23 */     return this;
/*    */   }
/*    */   
/*    */   public LazyBuilder<Elem, To> $plus$plus$eq(TraversableOnce<Elem> xs) {
/* 24 */     parts().$plus$eq(xs);
/* 24 */     return this;
/*    */   }
/*    */   
/*    */   public void clear() {
/* 26 */     parts().clear();
/*    */   }
/*    */   
/*    */   public abstract To result();
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\LazyBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */