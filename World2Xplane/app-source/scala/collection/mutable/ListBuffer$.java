/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import scala.Serializable;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.SeqFactory;
/*     */ import scala.collection.immutable.List;
/*     */ 
/*     */ public final class ListBuffer$ extends SeqFactory<ListBuffer> implements Serializable {
/*     */   public static final ListBuffer$ MODULE$;
/*     */   
/*     */   public class ListBuffer$$anon$1 extends AbstractIterator<A> {
/* 374 */     private List<A> cursor = null;
/*     */     
/*     */     private List<A> cursor() {
/* 374 */       return this.cursor;
/*     */     }
/*     */     
/*     */     private void cursor_$eq(List<A> x$1) {
/* 374 */       this.cursor = x$1;
/*     */     }
/*     */     
/* 375 */     private int delivered = 0;
/*     */     
/*     */     private int delivered() {
/* 375 */       return this.delivered;
/*     */     }
/*     */     
/*     */     private void delivered_$eq(int x$1) {
/* 375 */       this.delivered = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 381 */       return (delivered() < this.$outer.length());
/*     */     }
/*     */     
/*     */     public A next() {
/* 383 */       if (hasNext()) {
/* 386 */         if (cursor() == null) {
/* 386 */           cursor_$eq(this.$outer.scala$collection$mutable$ListBuffer$$start());
/*     */         } else {
/* 387 */           cursor_$eq((List<A>)cursor().tail());
/*     */         } 
/* 388 */         delivered_$eq(delivered() + 1);
/* 389 */         return (A)cursor().head();
/*     */       } 
/*     */       throw new NoSuchElementException("next on empty Iterator");
/*     */     }
/*     */     
/*     */     public ListBuffer$$anon$1(ListBuffer $outer) {}
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 431 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ListBuffer$() {
/* 431 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A> CanBuildFrom<ListBuffer<?>, A, ListBuffer<A>> canBuildFrom() {
/* 432 */     return (CanBuildFrom<ListBuffer<?>, A, ListBuffer<A>>)ReusableCBF();
/*     */   }
/*     */   
/*     */   public <A> Builder<A, ListBuffer<A>> newBuilder() {
/* 433 */     return new GrowingBuilder<A, ListBuffer<A>>(new ListBuffer<A>());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ListBuffer$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */