/*     */ package org.geotools.parameter;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Locale;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.media.jai.OperationDescriptor;
/*     */ import org.geotools.util.AbstractInternationalString;
/*     */ import org.geotools.util.Utilities;
/*     */ 
/*     */ final class ImagingParameterDescription extends AbstractInternationalString implements Serializable {
/*     */   private static final long serialVersionUID = -325584046563057577L;
/*     */   
/*  48 */   private static final String[] argumentKeys = new String[12];
/*     */   
/*     */   private final OperationDescriptor operation;
/*     */   
/*     */   private final String key;
/*     */   
/*     */   private final String prefixKey;
/*     */   
/*     */   public ImagingParameterDescription(OperationDescriptor operation, int arg) {
/*  73 */     this.operation = operation;
/*  74 */     this.prefixKey = null;
/*  75 */     if (arg < argumentKeys.length) {
/*  76 */       String candidate = argumentKeys[arg];
/*  77 */       if (candidate != null) {
/*  78 */         this.key = candidate;
/*     */         return;
/*     */       } 
/*     */     } 
/*  82 */     this.key = "arg" + arg + "Desc";
/*  83 */     if (arg < argumentKeys.length)
/*  84 */       argumentKeys[arg] = this.key; 
/*     */   }
/*     */   
/*     */   public ImagingParameterDescription(OperationDescriptor operation, String key, String prefixKey) {
/*  98 */     this.operation = operation;
/*  99 */     this.key = key;
/* 100 */     this.prefixKey = prefixKey;
/*     */   }
/*     */   
/*     */   public boolean exists() {
/*     */     try {
/* 109 */       return (toString().length() != 0);
/* 110 */     } catch (MissingResourceException exception) {
/* 111 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString(Locale locale) throws MissingResourceException {
/* 123 */     if (locale == null)
/* 124 */       locale = Locale.getDefault(); 
/* 126 */     ResourceBundle resources = this.operation.getResourceBundle(locale);
/* 127 */     String name = resources.getString(this.key);
/* 128 */     if (this.prefixKey != null)
/* 129 */       name = trimPrefix(name, resources.getString(this.prefixKey)); 
/* 131 */     return name;
/*     */   }
/*     */   
/*     */   static String trimPrefix(String name, String prefix) {
/* 140 */     name = name.trim();
/* 141 */     if (prefix != null) {
/* 142 */       prefix = prefix.trim();
/* 143 */       int offset = prefix.length();
/* 144 */       if (offset != 0 && 
/* 145 */         name.startsWith(prefix)) {
/* 146 */         int length = name.length();
/* 147 */         if (offset < length && name.charAt(offset) == '.')
/* 148 */           name = name.substring(offset + 1); 
/*     */       } 
/*     */     } 
/* 153 */     return name;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 161 */     if (object != null && object.getClass().equals(getClass())) {
/* 162 */       ImagingParameterDescription that = (ImagingParameterDescription)object;
/* 163 */       return (Utilities.equals(this.key, that.key) && Utilities.equals(this.prefixKey, that.prefixKey) && Utilities.equals(this.operation, that.operation));
/*     */     } 
/* 167 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 175 */     return 0xE0132057 ^ this.key.hashCode() ^ this.operation.hashCode();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\parameter\ImagingParameterDescription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */