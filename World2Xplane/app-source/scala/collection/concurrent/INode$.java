/*     */ package scala.collection.concurrent;
/*     */ 
/*     */ public final class INode$ {
/*     */   public static final INode$ MODULE$;
/*     */   
/*     */   private final Object KEY_PRESENT;
/*     */   
/*     */   private final Object KEY_ABSENT;
/*     */   
/*     */   private INode$() {
/* 382 */     MODULE$ = this;
/* 383 */     this.KEY_PRESENT = new Object();
/* 384 */     this.KEY_ABSENT = new Object();
/*     */   }
/*     */   
/*     */   public Object KEY_PRESENT() {
/*     */     return this.KEY_PRESENT;
/*     */   }
/*     */   
/*     */   public Object KEY_ABSENT() {
/* 384 */     return this.KEY_ABSENT;
/*     */   }
/*     */   
/*     */   public <K, V> INode<K, V> newRootNode() {
/* 387 */     Gen gen = new Gen();
/* 388 */     CNode<Object, Object> cn = new CNode<Object, Object>(0, new BasicNode[0], gen);
/* 389 */     return new INode<K, V>((MainNode)cn, gen);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\concurrent\INode$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */