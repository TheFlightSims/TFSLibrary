/*     */ package javax.media.jai.remote;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ class SerState implements SerializableState {
/*     */   private Serializable object;
/*     */   
/*     */   SerState(Serializable object) {
/* 512 */     if (object == null)
/* 513 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 515 */     this.object = object;
/*     */   }
/*     */   
/*     */   public Class getObjectClass() {
/* 519 */     return this.object.getClass();
/*     */   }
/*     */   
/*     */   public Object getObject() {
/* 523 */     return this.object;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\remote\SerState.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */