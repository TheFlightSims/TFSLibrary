/*     */ package javax.media.jai.remote;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ class SerSerializer implements Serializer {
/*     */   public Class getSupportedClass() {
/* 487 */     return Serializable.class;
/*     */   }
/*     */   
/*     */   public boolean permitsSubclasses() {
/* 491 */     return true;
/*     */   }
/*     */   
/*     */   public SerializableState getState(Object o, RenderingHints h) {
/* 495 */     if (o == null)
/* 496 */       return SerializerFactory.NULL_STATE; 
/* 497 */     if (!(o instanceof Serializable))
/* 498 */       throw new IllegalArgumentException(JaiI18N.getString("SerializerFactory2")); 
/* 500 */     return new SerState((Serializable)o);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\remote\SerSerializer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */