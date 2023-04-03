/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Set;
/*     */ import org.opengis.util.GenericName;
/*     */ import org.opengis.util.InternationalString;
/*     */ import org.opengis.util.LocalName;
/*     */ import org.opengis.util.NameSpace;
/*     */ import org.opengis.util.ScopedName;
/*     */ 
/*     */ public abstract class GenericName implements GenericName, Serializable {
/*     */   private static final long serialVersionUID = 8685047583179337259L;
/*     */   
/*     */   public static final char DEFAULT_SEPARATOR = ':';
/*     */   
/*     */   private transient NameSpace namespace;
/*     */   
/*     */   static CharSequence validate(CharSequence name) {
/*  80 */     return (name == null || name instanceof InternationalString) ? name : name.toString();
/*     */   }
/*     */   
/*     */   public NameSpace scope() {
/* 100 */     if (this.namespace == null)
/* 101 */       this.namespace = new NameSpace() {
/*     */           public boolean isGlobal() {
/* 103 */             return false;
/*     */           }
/*     */           
/*     */           public GenericName name() {
/* 107 */             return GenericName.this.getScope();
/*     */           }
/*     */           
/*     */           @Deprecated
/*     */           public Set<GenericName> getNames() {
/* 111 */             throw new UnsupportedOperationException();
/*     */           }
/*     */         }; 
/* 115 */     return this.namespace;
/*     */   }
/*     */   
/*     */   public int depth() {
/* 141 */     return getParsedNames().size();
/*     */   }
/*     */   
/*     */   public LocalName head() {
/* 162 */     List<? extends LocalName> names = getParsedNames();
/* 163 */     return names.get(0);
/*     */   }
/*     */   
/*     */   public LocalName tip() {
/* 175 */     List<? extends LocalName> names = getParsedNames();
/* 176 */     return names.get(names.size() - 1);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public LocalName asLocalName() {
/* 187 */     return tip();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public LocalName name() {
/* 197 */     return tip();
/*     */   }
/*     */   
/*     */   char getSeparator() {
/* 220 */     return ':';
/*     */   }
/*     */   
/*     */   public String toString() {
/* 236 */     StringBuilder buffer = new StringBuilder();
/* 237 */     List<? extends LocalName> parsedNames = getParsedNames();
/* 238 */     char separator = getSeparator();
/* 239 */     for (Iterator<? extends LocalName> it = parsedNames.iterator(); it.hasNext(); ) {
/* 240 */       if (buffer.length() != 0)
/* 241 */         buffer.append(separator); 
/* 243 */       buffer.append(it.next());
/*     */     } 
/* 245 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   public InternationalString toInternationalString() {
/* 258 */     return new International(getParsedNames(), getSeparator());
/*     */   }
/*     */   
/*     */   private static final class International extends AbstractInternationalString implements Serializable {
/*     */     private static final long serialVersionUID = -4234089612436334148L;
/*     */     
/*     */     private final List<? extends LocalName> parsedNames;
/*     */     
/*     */     private final char separator;
/*     */     
/*     */     public International(List<? extends LocalName> parsedNames, char separator) {
/* 293 */       this.parsedNames = parsedNames;
/* 294 */       this.separator = separator;
/*     */     }
/*     */     
/*     */     public String toString(Locale locale) {
/* 301 */       StringBuilder buffer = new StringBuilder();
/* 302 */       for (LocalName name : this.parsedNames) {
/* 303 */         if (buffer.length() != 0)
/* 304 */           buffer.append(this.separator); 
/* 306 */         buffer.append(name.toInternationalString().toString(locale));
/*     */       } 
/* 308 */       return buffer.toString();
/*     */     }
/*     */     
/*     */     public boolean equals(Object object) {
/* 316 */       if (object != null && object.getClass().equals(getClass())) {
/* 317 */         International that = (International)object;
/* 318 */         return (Utilities.equals(this.parsedNames, that.parsedNames) && this.separator == that.separator);
/*     */       } 
/* 321 */       return false;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 329 */       return 0x3F8F99BC ^ this.parsedNames.hashCode();
/*     */     }
/*     */   }
/*     */   
/*     */   public int compareTo(GenericName that) {
/* 352 */     Iterator<? extends LocalName> thisNames = getParsedNames().iterator();
/* 353 */     Iterator<? extends LocalName> thatNames = that.getParsedNames().iterator();
/* 354 */     while (thisNames.hasNext()) {
/* 355 */       if (!thatNames.hasNext())
/* 356 */         return 1; 
/* 358 */       LocalName thisNext = thisNames.next();
/* 359 */       LocalName thatNext = thatNames.next();
/* 360 */       if (thisNext == this && thatNext == that)
/* 362 */         throw new IllegalStateException(); 
/* 364 */       int compare = thisNext.compareTo(thatNext);
/* 365 */       if (compare != 0)
/* 366 */         return compare; 
/*     */     } 
/* 369 */     return thatNames.hasNext() ? -1 : 0;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 380 */     if (object != null && object.getClass().equals(getClass())) {
/* 381 */       GenericName that = (GenericName)object;
/* 382 */       return (Utilities.equals(getParsedNames(), that.getParsedNames()) && getSeparator() == that.getSeparator());
/*     */     } 
/* 385 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 393 */     return 0xF4C4EE2B ^ getParsedNames().hashCode();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public abstract GenericName getScope();
/*     */   
/*     */   public abstract List<LocalName> getParsedNames();
/*     */   
/*     */   @Deprecated
/*     */   public abstract ScopedName asScopedName();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\GenericName.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */