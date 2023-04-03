/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.Closure;
/*     */ 
/*     */ public class ChainedClosure implements Closure, Serializable {
/*     */   private static final long serialVersionUID = -3520677225766901240L;
/*     */   
/*     */   private final Closure[] iClosures;
/*     */   
/*     */   public static Closure getInstance(Closure[] closures) {
/*  50 */     FunctorUtils.validate(closures);
/*  51 */     if (closures.length == 0)
/*  52 */       return NOPClosure.INSTANCE; 
/*  54 */     closures = FunctorUtils.copy(closures);
/*  55 */     return new ChainedClosure(closures);
/*     */   }
/*     */   
/*     */   public static Closure getInstance(Collection closures) {
/*  69 */     if (closures == null)
/*  70 */       throw new IllegalArgumentException("Closure collection must not be null"); 
/*  72 */     if (closures.size() == 0)
/*  73 */       return NOPClosure.INSTANCE; 
/*  76 */     Closure[] cmds = new Closure[closures.size()];
/*  77 */     int i = 0;
/*  78 */     for (Iterator it = closures.iterator(); it.hasNext();)
/*  79 */       cmds[i++] = it.next(); 
/*  81 */     FunctorUtils.validate(cmds);
/*  82 */     return new ChainedClosure(cmds);
/*     */   }
/*     */   
/*     */   public static Closure getInstance(Closure closure1, Closure closure2) {
/*  94 */     if (closure1 == null || closure2 == null)
/*  95 */       throw new IllegalArgumentException("Closures must not be null"); 
/*  97 */     Closure[] closures = { closure1, closure2 };
/*  98 */     return new ChainedClosure(closures);
/*     */   }
/*     */   
/*     */   public ChainedClosure(Closure[] closures) {
/* 109 */     this.iClosures = closures;
/*     */   }
/*     */   
/*     */   public void execute(Object input) {
/* 118 */     for (int i = 0; i < this.iClosures.length; i++)
/* 119 */       this.iClosures[i].execute(input); 
/*     */   }
/*     */   
/*     */   public Closure[] getClosures() {
/* 129 */     return this.iClosures;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\ChainedClosure.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */