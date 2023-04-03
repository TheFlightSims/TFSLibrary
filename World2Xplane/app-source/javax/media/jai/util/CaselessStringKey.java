/*     */ package javax.media.jai.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Locale;
/*     */ 
/*     */ public final class CaselessStringKey implements Cloneable, Serializable {
/*     */   private String name;
/*     */   
/*     */   private String lowerCaseName;
/*     */   
/*     */   public CaselessStringKey(String name) {
/*  40 */     setName(name);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  47 */     return this.lowerCaseName.hashCode();
/*     */   }
/*     */   
/*     */   public String getName() {
/*  54 */     return this.name;
/*     */   }
/*     */   
/*     */   private String getLowerCaseName() {
/*  61 */     return this.lowerCaseName;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  71 */     if (name == null)
/*  72 */       throw new IllegalArgumentException(JaiI18N.getString("CaselessStringKey0")); 
/*  74 */     this.name = name;
/*  75 */     this.lowerCaseName = name.toLowerCase(Locale.ENGLISH);
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/*  84 */       return super.clone();
/*  85 */     } catch (CloneNotSupportedException e) {
/*  87 */       throw new InternalError();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  98 */     if (o != null && o instanceof CaselessStringKey)
/*  99 */       return this.lowerCaseName.equals(((CaselessStringKey)o).getLowerCaseName()); 
/* 101 */     return false;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 108 */     return getName();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\ja\\util\CaselessStringKey.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */