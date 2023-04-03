/*     */ package scala.collection.convert;
/*     */ 
/*     */ import scala.Serializable;
/*     */ 
/*     */ public final class Wrappers$ implements Wrappers, Serializable {
/*     */   public static final Wrappers$ MODULE$;
/*     */   
/*     */   public static final long serialVersionUID = -5857859809262781311L;
/*     */   
/*     */   private volatile Wrappers.IteratorWrapper$ IteratorWrapper$module;
/*     */   
/*     */   private volatile Wrappers.JIteratorWrapper$ JIteratorWrapper$module;
/*     */   
/*     */   private volatile Wrappers.JEnumerationWrapper$ JEnumerationWrapper$module;
/*     */   
/*     */   private volatile Wrappers.IterableWrapper$ IterableWrapper$module;
/*     */   
/*     */   private volatile Wrappers.JIterableWrapper$ JIterableWrapper$module;
/*     */   
/*     */   private volatile Wrappers.JCollectionWrapper$ JCollectionWrapper$module;
/*     */   
/*     */   private volatile Wrappers.SeqWrapper$ SeqWrapper$module;
/*     */   
/*     */   private volatile Wrappers.MutableSeqWrapper$ MutableSeqWrapper$module;
/*     */   
/*     */   private volatile Wrappers.MutableBufferWrapper$ MutableBufferWrapper$module;
/*     */   
/*     */   private volatile Wrappers.JListWrapper$ JListWrapper$module;
/*     */   
/*     */   private volatile Wrappers.MutableSetWrapper$ MutableSetWrapper$module;
/*     */   
/*     */   private volatile Wrappers.JSetWrapper$ JSetWrapper$module;
/*     */   
/*     */   private volatile Wrappers.MutableMapWrapper$ MutableMapWrapper$module;
/*     */   
/*     */   private volatile Wrappers.JMapWrapper$ JMapWrapper$module;
/*     */   
/*     */   private volatile Wrappers.JConcurrentMapDeprecatedWrapper$ JConcurrentMapDeprecatedWrapper$module;
/*     */   
/*     */   private volatile Wrappers.JConcurrentMapWrapper$ JConcurrentMapWrapper$module;
/*     */   
/*     */   private volatile Wrappers.DictionaryWrapper$ DictionaryWrapper$module;
/*     */   
/*     */   private volatile Wrappers.JDictionaryWrapper$ JDictionaryWrapper$module;
/*     */   
/*     */   private volatile Wrappers.JPropertiesWrapper$ JPropertiesWrapper$module;
/*     */   
/*     */   private Wrappers.IteratorWrapper$ IteratorWrapper$lzycompute() {
/* 478 */     synchronized (this) {
/* 478 */       if (this.IteratorWrapper$module == null)
/* 478 */         this.IteratorWrapper$module = new Wrappers.IteratorWrapper$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/convert/Wrappers$}} */
/* 478 */       return this.IteratorWrapper$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Wrappers.IteratorWrapper$ IteratorWrapper() {
/* 478 */     return (this.IteratorWrapper$module == null) ? IteratorWrapper$lzycompute() : this.IteratorWrapper$module;
/*     */   }
/*     */   
/*     */   private Wrappers.JIteratorWrapper$ JIteratorWrapper$lzycompute() {
/* 478 */     synchronized (this) {
/* 478 */       if (this.JIteratorWrapper$module == null)
/* 478 */         this.JIteratorWrapper$module = new Wrappers.JIteratorWrapper$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/convert/Wrappers$}} */
/* 478 */       return this.JIteratorWrapper$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Wrappers.JIteratorWrapper$ JIteratorWrapper() {
/* 478 */     return (this.JIteratorWrapper$module == null) ? JIteratorWrapper$lzycompute() : this.JIteratorWrapper$module;
/*     */   }
/*     */   
/*     */   private Wrappers.JEnumerationWrapper$ JEnumerationWrapper$lzycompute() {
/* 478 */     synchronized (this) {
/* 478 */       if (this.JEnumerationWrapper$module == null)
/* 478 */         this.JEnumerationWrapper$module = new Wrappers.JEnumerationWrapper$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/convert/Wrappers$}} */
/* 478 */       return this.JEnumerationWrapper$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Wrappers.JEnumerationWrapper$ JEnumerationWrapper() {
/* 478 */     return (this.JEnumerationWrapper$module == null) ? JEnumerationWrapper$lzycompute() : this.JEnumerationWrapper$module;
/*     */   }
/*     */   
/*     */   private Wrappers.IterableWrapper$ IterableWrapper$lzycompute() {
/* 478 */     synchronized (this) {
/* 478 */       if (this.IterableWrapper$module == null)
/* 478 */         this.IterableWrapper$module = new Wrappers.IterableWrapper$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/convert/Wrappers$}} */
/* 478 */       return this.IterableWrapper$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Wrappers.IterableWrapper$ IterableWrapper() {
/* 478 */     return (this.IterableWrapper$module == null) ? IterableWrapper$lzycompute() : this.IterableWrapper$module;
/*     */   }
/*     */   
/*     */   private Wrappers.JIterableWrapper$ JIterableWrapper$lzycompute() {
/* 478 */     synchronized (this) {
/* 478 */       if (this.JIterableWrapper$module == null)
/* 478 */         this.JIterableWrapper$module = new Wrappers.JIterableWrapper$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/convert/Wrappers$}} */
/* 478 */       return this.JIterableWrapper$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Wrappers.JIterableWrapper$ JIterableWrapper() {
/* 478 */     return (this.JIterableWrapper$module == null) ? JIterableWrapper$lzycompute() : this.JIterableWrapper$module;
/*     */   }
/*     */   
/*     */   private Wrappers.JCollectionWrapper$ JCollectionWrapper$lzycompute() {
/* 478 */     synchronized (this) {
/* 478 */       if (this.JCollectionWrapper$module == null)
/* 478 */         this.JCollectionWrapper$module = new Wrappers.JCollectionWrapper$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/convert/Wrappers$}} */
/* 478 */       return this.JCollectionWrapper$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Wrappers.JCollectionWrapper$ JCollectionWrapper() {
/* 478 */     return (this.JCollectionWrapper$module == null) ? JCollectionWrapper$lzycompute() : this.JCollectionWrapper$module;
/*     */   }
/*     */   
/*     */   private Wrappers.SeqWrapper$ SeqWrapper$lzycompute() {
/* 478 */     synchronized (this) {
/* 478 */       if (this.SeqWrapper$module == null)
/* 478 */         this.SeqWrapper$module = new Wrappers.SeqWrapper$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/convert/Wrappers$}} */
/* 478 */       return this.SeqWrapper$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Wrappers.SeqWrapper$ SeqWrapper() {
/* 478 */     return (this.SeqWrapper$module == null) ? SeqWrapper$lzycompute() : this.SeqWrapper$module;
/*     */   }
/*     */   
/*     */   private Wrappers.MutableSeqWrapper$ MutableSeqWrapper$lzycompute() {
/* 478 */     synchronized (this) {
/* 478 */       if (this.MutableSeqWrapper$module == null)
/* 478 */         this.MutableSeqWrapper$module = new Wrappers.MutableSeqWrapper$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/convert/Wrappers$}} */
/* 478 */       return this.MutableSeqWrapper$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Wrappers.MutableSeqWrapper$ MutableSeqWrapper() {
/* 478 */     return (this.MutableSeqWrapper$module == null) ? MutableSeqWrapper$lzycompute() : this.MutableSeqWrapper$module;
/*     */   }
/*     */   
/*     */   private Wrappers.MutableBufferWrapper$ MutableBufferWrapper$lzycompute() {
/* 478 */     synchronized (this) {
/* 478 */       if (this.MutableBufferWrapper$module == null)
/* 478 */         this.MutableBufferWrapper$module = new Wrappers.MutableBufferWrapper$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/convert/Wrappers$}} */
/* 478 */       return this.MutableBufferWrapper$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Wrappers.MutableBufferWrapper$ MutableBufferWrapper() {
/* 478 */     return (this.MutableBufferWrapper$module == null) ? MutableBufferWrapper$lzycompute() : this.MutableBufferWrapper$module;
/*     */   }
/*     */   
/*     */   private Wrappers.JListWrapper$ JListWrapper$lzycompute() {
/* 478 */     synchronized (this) {
/* 478 */       if (this.JListWrapper$module == null)
/* 478 */         this.JListWrapper$module = new Wrappers.JListWrapper$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/convert/Wrappers$}} */
/* 478 */       return this.JListWrapper$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Wrappers.JListWrapper$ JListWrapper() {
/* 478 */     return (this.JListWrapper$module == null) ? JListWrapper$lzycompute() : this.JListWrapper$module;
/*     */   }
/*     */   
/*     */   private Wrappers.MutableSetWrapper$ MutableSetWrapper$lzycompute() {
/* 478 */     synchronized (this) {
/* 478 */       if (this.MutableSetWrapper$module == null)
/* 478 */         this.MutableSetWrapper$module = new Wrappers.MutableSetWrapper$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/convert/Wrappers$}} */
/* 478 */       return this.MutableSetWrapper$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Wrappers.MutableSetWrapper$ MutableSetWrapper() {
/* 478 */     return (this.MutableSetWrapper$module == null) ? MutableSetWrapper$lzycompute() : this.MutableSetWrapper$module;
/*     */   }
/*     */   
/*     */   private Wrappers.JSetWrapper$ JSetWrapper$lzycompute() {
/* 478 */     synchronized (this) {
/* 478 */       if (this.JSetWrapper$module == null)
/* 478 */         this.JSetWrapper$module = new Wrappers.JSetWrapper$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/convert/Wrappers$}} */
/* 478 */       return this.JSetWrapper$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Wrappers.JSetWrapper$ JSetWrapper() {
/* 478 */     return (this.JSetWrapper$module == null) ? JSetWrapper$lzycompute() : this.JSetWrapper$module;
/*     */   }
/*     */   
/*     */   private Wrappers.MutableMapWrapper$ MutableMapWrapper$lzycompute() {
/* 478 */     synchronized (this) {
/* 478 */       if (this.MutableMapWrapper$module == null)
/* 478 */         this.MutableMapWrapper$module = new Wrappers.MutableMapWrapper$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/convert/Wrappers$}} */
/* 478 */       return this.MutableMapWrapper$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Wrappers.MutableMapWrapper$ MutableMapWrapper() {
/* 478 */     return (this.MutableMapWrapper$module == null) ? MutableMapWrapper$lzycompute() : this.MutableMapWrapper$module;
/*     */   }
/*     */   
/*     */   private Wrappers.JMapWrapper$ JMapWrapper$lzycompute() {
/* 478 */     synchronized (this) {
/* 478 */       if (this.JMapWrapper$module == null)
/* 478 */         this.JMapWrapper$module = new Wrappers.JMapWrapper$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/convert/Wrappers$}} */
/* 478 */       return this.JMapWrapper$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Wrappers.JMapWrapper$ JMapWrapper() {
/* 478 */     return (this.JMapWrapper$module == null) ? JMapWrapper$lzycompute() : this.JMapWrapper$module;
/*     */   }
/*     */   
/*     */   private Wrappers.JConcurrentMapDeprecatedWrapper$ JConcurrentMapDeprecatedWrapper$lzycompute() {
/* 478 */     synchronized (this) {
/* 478 */       if (this.JConcurrentMapDeprecatedWrapper$module == null)
/* 478 */         this.JConcurrentMapDeprecatedWrapper$module = new Wrappers.JConcurrentMapDeprecatedWrapper$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/convert/Wrappers$}} */
/* 478 */       return this.JConcurrentMapDeprecatedWrapper$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Wrappers.JConcurrentMapDeprecatedWrapper$ JConcurrentMapDeprecatedWrapper() {
/* 478 */     return (this.JConcurrentMapDeprecatedWrapper$module == null) ? JConcurrentMapDeprecatedWrapper$lzycompute() : this.JConcurrentMapDeprecatedWrapper$module;
/*     */   }
/*     */   
/*     */   private Wrappers.JConcurrentMapWrapper$ JConcurrentMapWrapper$lzycompute() {
/* 478 */     synchronized (this) {
/* 478 */       if (this.JConcurrentMapWrapper$module == null)
/* 478 */         this.JConcurrentMapWrapper$module = new Wrappers.JConcurrentMapWrapper$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/convert/Wrappers$}} */
/* 478 */       return this.JConcurrentMapWrapper$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Wrappers.JConcurrentMapWrapper$ JConcurrentMapWrapper() {
/* 478 */     return (this.JConcurrentMapWrapper$module == null) ? JConcurrentMapWrapper$lzycompute() : this.JConcurrentMapWrapper$module;
/*     */   }
/*     */   
/*     */   private Wrappers.DictionaryWrapper$ DictionaryWrapper$lzycompute() {
/* 478 */     synchronized (this) {
/* 478 */       if (this.DictionaryWrapper$module == null)
/* 478 */         this.DictionaryWrapper$module = new Wrappers.DictionaryWrapper$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/convert/Wrappers$}} */
/* 478 */       return this.DictionaryWrapper$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Wrappers.DictionaryWrapper$ DictionaryWrapper() {
/* 478 */     return (this.DictionaryWrapper$module == null) ? DictionaryWrapper$lzycompute() : this.DictionaryWrapper$module;
/*     */   }
/*     */   
/*     */   private Wrappers.JDictionaryWrapper$ JDictionaryWrapper$lzycompute() {
/* 478 */     synchronized (this) {
/* 478 */       if (this.JDictionaryWrapper$module == null)
/* 478 */         this.JDictionaryWrapper$module = new Wrappers.JDictionaryWrapper$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/convert/Wrappers$}} */
/* 478 */       return this.JDictionaryWrapper$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Wrappers.JDictionaryWrapper$ JDictionaryWrapper() {
/* 478 */     return (this.JDictionaryWrapper$module == null) ? JDictionaryWrapper$lzycompute() : this.JDictionaryWrapper$module;
/*     */   }
/*     */   
/*     */   private Wrappers.JPropertiesWrapper$ JPropertiesWrapper$lzycompute() {
/* 478 */     synchronized (this) {
/* 478 */       if (this.JPropertiesWrapper$module == null)
/* 478 */         this.JPropertiesWrapper$module = new Wrappers.JPropertiesWrapper$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/convert/Wrappers$}} */
/* 478 */       return this.JPropertiesWrapper$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Wrappers.JPropertiesWrapper$ JPropertiesWrapper() {
/* 478 */     return (this.JPropertiesWrapper$module == null) ? JPropertiesWrapper$lzycompute() : this.JPropertiesWrapper$module;
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 478 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Wrappers$() {
/* 478 */     MODULE$ = this;
/* 478 */     Wrappers$class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\convert\Wrappers$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */