/*     */ package org.geotools.metadata;
/*     */ 
/*     */ import java.util.Map;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.tree.TreeModel;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public abstract class AbstractMetadata {
/*  43 */   protected static final Logger LOGGER = Logging.getLogger("org.geotools.metadata");
/*     */   
/*     */   private transient int hashCode;
/*     */   
/*     */   private transient Map<String, Object> asMap;
/*     */   
/*     */   protected AbstractMetadata() {}
/*     */   
/*     */   protected AbstractMetadata(Object source) throws ClassCastException, UnmodifiableMetadataException {
/*  78 */     getStandard().shallowCopy(source, this, true);
/*     */   }
/*     */   
/*     */   public abstract MetadataStandard getStandard();
/*     */   
/*     */   public Class<?> getInterface() {
/*  97 */     return getStandard().getInterface(getClass());
/*     */   }
/*     */   
/*     */   boolean isModifiable() {
/* 115 */     return getStandard().isModifiable(getClass());
/*     */   }
/*     */   
/*     */   void invalidate() {
/* 123 */     assert Thread.holdsLock(this);
/* 124 */     this.hashCode = 0;
/*     */   }
/*     */   
/*     */   public synchronized Map<String, Object> asMap() {
/* 139 */     if (this.asMap == null)
/* 140 */       this.asMap = getStandard().asMap(this); 
/* 142 */     return this.asMap;
/*     */   }
/*     */   
/*     */   public synchronized TreeModel asTree() {
/* 156 */     return getStandard().asTree(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 174 */     if (object == this)
/* 175 */       return true; 
/* 177 */     if (object == null || !object.getClass().equals(getClass()))
/* 178 */       return false; 
/* 186 */     int c0 = this.hashCode;
/* 187 */     if (c0 != 0) {
/* 188 */       int c1 = ((AbstractMetadata)object).hashCode;
/* 189 */       if (c1 != 0 && c0 != c1)
/* 190 */         return false; 
/*     */     } 
/* 193 */     MetadataStandard standard = getStandard();
/* 202 */     return standard.shallowEquals(this, object, false);
/*     */   }
/*     */   
/*     */   public synchronized int hashCode() {
/* 213 */     int code = this.hashCode;
/* 214 */     if (code == 0) {
/* 215 */       code = getStandard().hashCode(this);
/* 216 */       if (!isModifiable())
/* 220 */         this.hashCode = code; 
/*     */     } 
/* 223 */     return code;
/*     */   }
/*     */   
/*     */   public synchronized String toString() {
/* 231 */     return getStandard().toString(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\AbstractMetadata.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */