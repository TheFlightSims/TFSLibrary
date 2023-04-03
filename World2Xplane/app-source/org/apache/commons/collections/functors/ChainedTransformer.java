/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ 
/*     */ public class ChainedTransformer implements Transformer, Serializable {
/*     */   private static final long serialVersionUID = 3514945074733160196L;
/*     */   
/*     */   private final Transformer[] iTransformers;
/*     */   
/*     */   public static Transformer getInstance(Transformer[] transformers) {
/*  53 */     FunctorUtils.validate(transformers);
/*  54 */     if (transformers.length == 0)
/*  55 */       return NOPTransformer.INSTANCE; 
/*  57 */     transformers = FunctorUtils.copy(transformers);
/*  58 */     return new ChainedTransformer(transformers);
/*     */   }
/*     */   
/*     */   public static Transformer getInstance(Collection transformers) {
/*  72 */     if (transformers == null)
/*  73 */       throw new IllegalArgumentException("Transformer collection must not be null"); 
/*  75 */     if (transformers.size() == 0)
/*  76 */       return NOPTransformer.INSTANCE; 
/*  79 */     Transformer[] cmds = new Transformer[transformers.size()];
/*  80 */     int i = 0;
/*  81 */     for (Iterator it = transformers.iterator(); it.hasNext();)
/*  82 */       cmds[i++] = it.next(); 
/*  84 */     FunctorUtils.validate(cmds);
/*  85 */     return new ChainedTransformer(cmds);
/*     */   }
/*     */   
/*     */   public static Transformer getInstance(Transformer transformer1, Transformer transformer2) {
/*  97 */     if (transformer1 == null || transformer2 == null)
/*  98 */       throw new IllegalArgumentException("Transformers must not be null"); 
/* 100 */     Transformer[] transformers = { transformer1, transformer2 };
/* 101 */     return new ChainedTransformer(transformers);
/*     */   }
/*     */   
/*     */   public ChainedTransformer(Transformer[] transformers) {
/* 112 */     this.iTransformers = transformers;
/*     */   }
/*     */   
/*     */   public Object transform(Object object) {
/* 122 */     for (int i = 0; i < this.iTransformers.length; i++)
/* 123 */       object = this.iTransformers[i].transform(object); 
/* 125 */     return object;
/*     */   }
/*     */   
/*     */   public Transformer[] getTransformers() {
/* 134 */     return this.iTransformers;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\ChainedTransformer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */