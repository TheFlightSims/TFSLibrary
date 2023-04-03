/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Proxy;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001Q4q!\001\002\021\002\007\005\021BA\004Ck&dG-\032:\013\005\r!\021aB7vi\006\024G.\032\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\013\004\025]\0214c\001\001\f\037A\021A\"D\007\002\r%\021aB\002\002\007\003:L(+\0324\021\007A\031R#D\001\022\025\t\021B!A\004hK:,'/[2\n\005Q\t\"\001C$s_^\f'\r\\3\021\005Y9B\002\001\003\0071\001A)\031A\r\003\t\025cW-\\\t\0035u\001\"\001D\016\n\005q1!a\002(pi\"Lgn\032\t\003\031yI!a\b\004\003\007\005s\027\020C\003\"\001\021\005!%\001\004%S:LG\017\n\013\002GA\021A\002J\005\003K\031\021A!\0268ji\")q\005\001D\001Q\005AA\005\0357vg\022*\027\017\006\002*U5\t\001\001C\003,M\001\007Q#\001\003fY\026l\007\"B\027\001\r\003\021\023!B2mK\006\024\b\"B\030\001\r\003\001\024A\002:fgVdG\017F\0012!\t1\"\007\002\0044\001\021\025\r!\007\002\003)>DQ!\016\001\005\002Y\n\001b]5{K\"Kg\016\036\013\003G]BQ\001\017\033A\002e\nAa]5{KB\021ABO\005\003w\031\0211!\0238u\021\025)\004\001\"\001>)\t\031c\bC\003@y\001\007\001)\001\003d_2d\007gA!G\023B!!iQ#I\033\005!\021B\001#\005\005=!&/\031<feN\f'\r\\3MS.,\007C\001\fG\t%9e(!A\001\002\013\005\021DA\002`IE\002\"AF%\005\023)s\024\021!A\001\006\003I\"aA0%e!)Q\007\001C\001\031R\0311%\024,\t\013}Z\005\031\001(1\007=\013F\013\005\003C\007B\033\006C\001\fR\t%\021V*!A\001\002\013\005\021DA\002`IU\002\"A\006+\005\023Uk\025\021!A\001\006\003I\"aA0%m!)qk\023a\001s\005)A-\0327uC\")\021\f\001C\0015\006y1/\033>f\021&tGOQ8v]\022,G\rF\002$7rCQ\001\017-A\002eBQ!\030-A\002y\013ABY8v]\022LgnZ\"pY2\0044aX1e!\021\0215\tY2\021\005Y\tG!\0032]\003\003\005\tQ!\001\032\005\ryF%\017\t\003-\021$\021\"\032/\002\002\003\005)\021A\r\003\t}#\023\007\r\005\006O\002!\t\001[\001\n[\006\004(+Z:vYR,\"![7\025\005)|\007\003B6\001+1l\021A\001\t\003-5$QA\0344C\002e\021QAT3x)>DQ\001\0354A\002E\f\021A\032\t\005\031I\fD.\003\002t\r\tIa)\0368di&|g.\r")
/*     */ public interface Builder<Elem, To> extends Growable<Elem> {
/*     */   Builder<Elem, To> $plus$eq(Elem paramElem);
/*     */   
/*     */   void clear();
/*     */   
/*     */   To result();
/*     */   
/*     */   void sizeHint(int paramInt);
/*     */   
/*     */   void sizeHint(TraversableLike<?, ?> paramTraversableLike);
/*     */   
/*     */   void sizeHint(TraversableLike<?, ?> paramTraversableLike, int paramInt);
/*     */   
/*     */   void sizeHintBounded(int paramInt, TraversableLike<?, ?> paramTraversableLike);
/*     */   
/*     */   <NewTo> Builder<Elem, NewTo> mapResult(Function1<To, NewTo> paramFunction1);
/*     */   
/*     */   public class Builder$$anon$1 implements Builder<Elem, NewTo>, Proxy {
/*     */     private final Builder<Elem, To> self;
/*     */     
/*     */     private final Function1 f$1;
/*     */     
/*     */     public int hashCode() {
/* 117 */       return Proxy.class.hashCode(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object that) {
/* 117 */       return Proxy.class.equals(this, that);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 117 */       return Proxy.class.toString(this);
/*     */     }
/*     */     
/*     */     public void sizeHint(TraversableLike coll) {
/* 117 */       Builder$class.sizeHint(this, coll);
/*     */     }
/*     */     
/*     */     public void sizeHint(TraversableLike coll, int delta) {
/* 117 */       Builder$class.sizeHint(this, coll, delta);
/*     */     }
/*     */     
/*     */     public <NewTo> Builder<Elem, NewTo> mapResult(Function1 f) {
/* 117 */       return Builder$class.mapResult(this, f);
/*     */     }
/*     */     
/*     */     public Growable<Elem> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 117 */       return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*     */     }
/*     */     
/*     */     public Builder$$anon$1(Builder<Elem, To> $outer, Function1 f$1) {
/* 117 */       Growable.class.$init$(this);
/* 117 */       Builder$class.$init$(this);
/* 117 */       Proxy.class.$init$(this);
/* 118 */       this.self = $outer;
/*     */     }
/*     */     
/*     */     public Builder<Elem, To> self() {
/* 118 */       return this.self;
/*     */     }
/*     */     
/*     */     public $anon$1 $plus$eq(Object x) {
/* 119 */       self().$plus$eq((Elem)x);
/* 119 */       return this;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 120 */       self().clear();
/*     */     }
/*     */     
/*     */     public $anon$1 $plus$plus$eq(TraversableOnce xs) {
/* 121 */       self().$plus$plus$eq(xs);
/* 121 */       return this;
/*     */     }
/*     */     
/*     */     public void sizeHint(int size) {
/* 122 */       self().sizeHint(size);
/*     */     }
/*     */     
/*     */     public void sizeHintBounded(int size, TraversableLike<?, ?> boundColl) {
/* 123 */       self().sizeHintBounded(size, boundColl);
/*     */     }
/*     */     
/*     */     public NewTo result() {
/* 124 */       return (NewTo)this.f$1.apply(self().result());
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\Builder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */