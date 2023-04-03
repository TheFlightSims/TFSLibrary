/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.opengis.util.GenericName;
/*     */ import org.opengis.util.InternationalString;
/*     */ import org.opengis.util.LocalName;
/*     */ import org.opengis.util.NameSpace;
/*     */ import org.opengis.util.ScopedName;
/*     */ 
/*     */ public class LocalName extends GenericName implements LocalName {
/*     */   private static final long serialVersionUID = -5627125375582385822L;
/*     */   
/*     */   private final ScopedName asScopedName;
/*     */   
/*     */   private final CharSequence name;
/*     */   
/*     */   private transient String asString;
/*     */   
/*     */   private transient InternationalString asInternationalString;
/*     */   
/*     */   private transient List<LocalName> parsedNames;
/*     */   
/*     */   public LocalName(CharSequence name) {
/*  90 */     this(null, name);
/*     */   }
/*     */   
/*     */   LocalName(ScopedName asScopedName, CharSequence name) {
/* 104 */     this.asScopedName = asScopedName;
/* 105 */     this.name = validate(name);
/* 106 */     AbstractInternationalString.ensureNonNull("name", name);
/*     */   }
/*     */   
/*     */   private GenericName getInternalScope() {
/* 114 */     if (this.asScopedName != null) {
/* 115 */       NameSpace scope = this.asScopedName.scope();
/* 116 */       if (scope != null)
/* 117 */         return scope.name(); 
/*     */     } 
/* 120 */     return null;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public GenericName getScope() {
/* 130 */     return getInternalScope();
/*     */   }
/*     */   
/*     */   public NameSpace scope() {
/* 148 */     return (this.asScopedName != null) ? this.asScopedName.scope() : super.scope();
/*     */   }
/*     */   
/*     */   public int depth() {
/* 157 */     return 1;
/*     */   }
/*     */   
/*     */   public List<LocalName> getParsedNames() {
/* 167 */     if (this.parsedNames == null)
/* 168 */       this.parsedNames = Collections.singletonList(this); 
/* 170 */     return this.parsedNames;
/*     */   }
/*     */   
/*     */   public LocalName head() {
/* 178 */     return this;
/*     */   }
/*     */   
/*     */   public LocalName tip() {
/* 186 */     return this;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public ScopedName asScopedName() {
/* 197 */     return this.asScopedName;
/*     */   }
/*     */   
/*     */   public GenericName toFullyQualifiedName() {
/* 208 */     if (this.asScopedName == null)
/* 209 */       return this; 
/* 211 */     return (GenericName)this.asScopedName;
/*     */   }
/*     */   
/*     */   public ScopedName push(GenericName scope) {
/* 233 */     throw new UnsupportedOperationException("Not yet implemented");
/*     */   }
/*     */   
/*     */   public String toString() {
/* 243 */     if (this.asString == null)
/* 244 */       if (this.name instanceof InternationalString) {
/* 246 */         this.asString = ((InternationalString)this.name).toString(null);
/*     */       } else {
/* 248 */         this.asString = this.name.toString();
/*     */       }  
/* 251 */     return this.asString;
/*     */   }
/*     */   
/*     */   public InternationalString toInternationalString() {
/* 259 */     if (this.asInternationalString == null)
/* 260 */       if (this.name instanceof InternationalString) {
/* 261 */         this.asInternationalString = (InternationalString)this.name;
/*     */       } else {
/* 263 */         this.asInternationalString = new SimpleInternationalString(this.name.toString());
/*     */       }  
/* 266 */     return this.asInternationalString;
/*     */   }
/*     */   
/*     */   public int compareTo(GenericName object) {
/* 276 */     return toString().compareToIgnoreCase(object.toString());
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 284 */     if (object == this)
/* 285 */       return true; 
/* 287 */     if (object != null && object.getClass().equals(getClass())) {
/* 288 */       LocalName that = (LocalName)object;
/* 290 */       return (Utilities.equals(getInternalScope(), that.getInternalScope()) && Utilities.equals(this.name, that.name));
/*     */     } 
/* 293 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 301 */     int code = -506246814;
/* 303 */     if (this.name != null)
/* 303 */       code ^= this.name.hashCode(); 
/* 304 */     return code;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\LocalName.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */