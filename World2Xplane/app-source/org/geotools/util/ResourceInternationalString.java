/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Locale;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ 
/*     */ public class ResourceInternationalString extends AbstractInternationalString implements Serializable {
/*     */   private static final long serialVersionUID = 6339944890723487336L;
/*     */   
/*     */   private final String resources;
/*     */   
/*     */   private final String key;
/*     */   
/*     */   public ResourceInternationalString(String resources, String key) {
/*  79 */     this.resources = resources;
/*  80 */     this.key = key;
/*  81 */     ensureNonNull("resources", resources);
/*  82 */     ensureNonNull("key", key);
/*     */   }
/*     */   
/*     */   public String toString(Locale locale) throws MissingResourceException {
/*  95 */     if (locale == null)
/* 100 */       locale = Locale.ENGLISH; 
/* 102 */     return ResourceBundle.getBundle(this.resources, locale).getString(this.key);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 113 */     if (object != null && object.getClass().equals(getClass())) {
/* 114 */       ResourceInternationalString that = (ResourceInternationalString)object;
/* 115 */       return (Utilities.equals(this.key, that.key) && Utilities.equals(this.resources, that.resources));
/*     */     } 
/* 118 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 126 */     return 0xA3204E68 ^ this.key.hashCode() ^ this.resources.hashCode();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\ResourceInternationalString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */