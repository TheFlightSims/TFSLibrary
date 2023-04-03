/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Observable;
/*     */ 
/*     */ public abstract class DeferredData extends Observable implements Serializable {
/*     */   protected Class dataClass;
/*     */   
/*     */   protected transient Object data;
/*     */   
/*     */   protected DeferredData(Class dataClass) {
/*  53 */     if (dataClass == null)
/*  54 */       throw new IllegalArgumentException(JaiI18N.getString("DeferredData0")); 
/*  56 */     this.dataClass = dataClass;
/*     */   }
/*     */   
/*     */   public Class getDataClass() {
/*  63 */     return this.dataClass;
/*     */   }
/*     */   
/*     */   public boolean isValid() {
/*  73 */     return (this.data != null);
/*     */   }
/*     */   
/*     */   protected abstract Object computeData();
/*     */   
/*     */   public final synchronized Object getData() {
/*  91 */     if (this.data == null)
/*  92 */       setData(computeData()); 
/*  94 */     return this.data;
/*     */   }
/*     */   
/*     */   protected final void setData(Object data) {
/* 127 */     if (data != null && !this.dataClass.isInstance(data))
/* 128 */       throw new IllegalArgumentException(JaiI18N.getString("DeferredData1")); 
/* 130 */     if (this.data == null || !this.data.equals(data)) {
/* 131 */       Object oldData = this.data;
/* 132 */       this.data = data;
/* 133 */       setChanged();
/* 134 */       notifyObservers(oldData);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\DeferredData.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */