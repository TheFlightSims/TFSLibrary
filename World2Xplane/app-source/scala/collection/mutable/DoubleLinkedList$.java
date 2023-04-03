/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.TraversableLike;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.collection.generic.SeqFactory;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ public final class DoubleLinkedList$ extends SeqFactory<DoubleLinkedList> implements Serializable {
/*    */   public static final DoubleLinkedList$ MODULE$;
/*    */   
/*    */   private Object readResolve() {
/* 79 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private DoubleLinkedList$() {
/* 79 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<DoubleLinkedList<?>, A, DoubleLinkedList<A>> canBuildFrom() {
/* 81 */     return (CanBuildFrom<DoubleLinkedList<?>, A, DoubleLinkedList<A>>)ReusableCBF();
/*    */   }
/*    */   
/*    */   public <A> Builder<A, DoubleLinkedList<A>> newBuilder() {
/* 84 */     return new DoubleLinkedList$$anon$1();
/*    */   }
/*    */   
/*    */   public static class DoubleLinkedList$$anon$1 implements Builder<A, DoubleLinkedList<A>> {
/*    */     private DoubleLinkedList<A> current;
/*    */     
/*    */     public void sizeHint(int size) {
/* 84 */       Builder$class.sizeHint(this, size);
/*    */     }
/*    */     
/*    */     public void sizeHint(TraversableLike coll) {
/* 84 */       Builder$class.sizeHint(this, coll);
/*    */     }
/*    */     
/*    */     public void sizeHint(TraversableLike coll, int delta) {
/* 84 */       Builder$class.sizeHint(this, coll, delta);
/*    */     }
/*    */     
/*    */     public void sizeHintBounded(int size, TraversableLike boundingColl) {
/* 84 */       Builder$class.sizeHintBounded(this, size, boundingColl);
/*    */     }
/*    */     
/*    */     public <NewTo> Builder<A, NewTo> mapResult(Function1 f) {
/* 84 */       return Builder$class.mapResult(this, f);
/*    */     }
/*    */     
/*    */     public Growable<A> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 84 */       return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public Growable<A> $plus$plus$eq(TraversableOnce xs) {
/* 84 */       return Growable.class.$plus$plus$eq(this, xs);
/*    */     }
/*    */     
/*    */     public DoubleLinkedList$$anon$1() {
/* 84 */       Growable.class.$init$(this);
/* 84 */       Builder$class.$init$(this);
/* 86 */       this.current = emptyList();
/*    */     }
/*    */     
/*    */     private DoubleLinkedList<A> emptyList() {
/*    */       return new DoubleLinkedList<A>();
/*    */     }
/*    */     
/*    */     private DoubleLinkedList<A> current() {
/* 86 */       return this.current;
/*    */     }
/*    */     
/*    */     private void current_$eq(DoubleLinkedList<A> x$1) {
/* 86 */       this.current = x$1;
/*    */     }
/*    */     
/*    */     public DoubleLinkedList$$anon$1 $plus$eq(Object elem) {
/* 90 */       current_$eq(new DoubleLinkedList<A>((A)elem, emptyList()));
/* 90 */       current().isEmpty() ? BoxedUnit.UNIT : 
/*    */         
/* 92 */         current().append(new DoubleLinkedList<A>((A)elem, emptyList()));
/* 94 */       return this;
/*    */     }
/*    */     
/*    */     public void clear() {
/* 97 */       current_$eq(emptyList());
/*    */     }
/*    */     
/*    */     public DoubleLinkedList<A> result() {
/* 98 */       return current();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\DoubleLinkedList$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */