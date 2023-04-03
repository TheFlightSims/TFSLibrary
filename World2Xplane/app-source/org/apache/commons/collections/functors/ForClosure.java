/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.collections.Closure;
/*     */ 
/*     */ public class ForClosure implements Closure, Serializable {
/*     */   private static final long serialVersionUID = -1190120533393621674L;
/*     */   
/*     */   private final int iCount;
/*     */   
/*     */   private final Closure iClosure;
/*     */   
/*     */   public static Closure getInstance(int count, Closure closure) {
/*  52 */     if (count <= 0 || closure == null)
/*  53 */       return NOPClosure.INSTANCE; 
/*  55 */     if (count == 1)
/*  56 */       return closure; 
/*  58 */     return new ForClosure(count, closure);
/*     */   }
/*     */   
/*     */   public ForClosure(int count, Closure closure) {
/*  70 */     this.iCount = count;
/*  71 */     this.iClosure = closure;
/*     */   }
/*     */   
/*     */   public void execute(Object input) {
/*  80 */     for (int i = 0; i < this.iCount; i++)
/*  81 */       this.iClosure.execute(input); 
/*     */   }
/*     */   
/*     */   public Closure getClosure() {
/*  92 */     return this.iClosure;
/*     */   }
/*     */   
/*     */   public int getCount() {
/* 102 */     return this.iCount;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\ForClosure.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */