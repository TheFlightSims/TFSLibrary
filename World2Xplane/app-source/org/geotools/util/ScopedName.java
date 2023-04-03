/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.opengis.util.GenericName;
/*     */ import org.opengis.util.LocalName;
/*     */ import org.opengis.util.ScopedName;
/*     */ 
/*     */ public class ScopedName extends GenericName implements ScopedName {
/*     */   private static final long serialVersionUID = -7664125655784137729L;
/*     */   
/*     */   private final GenericName scope;
/*     */   
/*     */   private final char separator;
/*     */   
/*     */   private final LocalName name;
/*     */   
/*     */   private transient List<LocalName> parsedNames;
/*     */   
/*     */   public ScopedName(GenericName scope, CharSequence name) {
/*  87 */     this(scope, ':', name);
/*     */   }
/*     */   
/*     */   public ScopedName(GenericName scope, char separator, CharSequence name) {
/* 102 */     AbstractInternationalString.ensureNonNull("scope", scope);
/* 103 */     AbstractInternationalString.ensureNonNull("name", name);
/* 104 */     this.scope = scope;
/* 105 */     this.separator = separator;
/* 106 */     this.name = new LocalName(this, name);
/*     */   }
/*     */   
/*     */   public LocalName head() {
/* 125 */     throw new UnsupportedOperationException("Not yet implemented.");
/*     */   }
/*     */   
/*     */   public GenericName tail() {
/* 146 */     throw new UnsupportedOperationException("Not yet implemented.");
/*     */   }
/*     */   
/*     */   public GenericName path() {
/* 160 */     throw new UnsupportedOperationException("Not yet implemented.");
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public GenericName getScope() {
/* 170 */     return this.scope;
/*     */   }
/*     */   
/*     */   public char getSeparator() {
/* 178 */     return this.separator;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public ScopedName asScopedName() {
/* 189 */     return this;
/*     */   }
/*     */   
/*     */   public LocalName tip() {
/* 201 */     return this.name;
/*     */   }
/*     */   
/*     */   public List<LocalName> getParsedNames() {
/* 208 */     if (this.parsedNames == null) {
/* 209 */       List<? extends LocalName> parents = this.scope.getParsedNames();
/* 210 */       int size = parents.size();
/* 211 */       LocalName[] names = new LocalName[size + 1];
/* 212 */       names = parents.<LocalName>toArray(names);
/* 213 */       names[size] = this.name;
/* 214 */       this.parsedNames = Arrays.asList(names);
/*     */     } 
/* 216 */     return this.parsedNames;
/*     */   }
/*     */   
/*     */   public GenericName toFullyQualifiedName() {
/* 227 */     return this;
/*     */   }
/*     */   
/*     */   public ScopedName push(GenericName scope) {
/* 250 */     throw new UnsupportedOperationException("Not yet implemented.");
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 258 */     if (object == this)
/* 259 */       return true; 
/* 261 */     if (object != null && object.getClass().equals(getClass())) {
/* 262 */       ScopedName that = (ScopedName)object;
/* 263 */       return Utilities.equals(this.name, that.name);
/*     */     } 
/* 267 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 275 */     return 0x85075BFF ^ this.name.hashCode() ^ this.scope.hashCode();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\ScopedName.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */