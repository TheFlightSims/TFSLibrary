/*     */ package org.geotools.feature.type;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.opengis.feature.type.AttributeType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.feature.type.Schema;
/*     */ 
/*     */ public class SchemaImpl implements Schema {
/*     */   HashMap<Name, AttributeType> contents;
/*     */   
/*     */   String uri;
/*     */   
/*     */   public SchemaImpl(String uri) {
/*  48 */     this.uri = uri;
/*  49 */     this.contents = new HashMap<Name, AttributeType>();
/*     */   }
/*     */   
/*     */   public Set<Name> keySet() {
/*  53 */     return this.contents.keySet();
/*     */   }
/*     */   
/*     */   public int size() {
/*  57 */     return this.contents.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  61 */     return this.contents.isEmpty();
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/*  65 */     return this.contents.containsKey(key);
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/*  69 */     return this.contents.containsValue(value);
/*     */   }
/*     */   
/*     */   public AttributeType get(Object key) {
/*  73 */     return this.contents.get(key);
/*     */   }
/*     */   
/*     */   public AttributeType put(Name name, AttributeType type) {
/*  77 */     if (!(name instanceof Name))
/*  78 */       throw new IllegalArgumentException("Please use a Name"); 
/*  80 */     Name n = name;
/*  81 */     if (!n.toString().startsWith(this.uri.toString()))
/*  82 */       throw new IllegalArgumentException("Provided name was not in schema:" + this.uri); 
/*  84 */     if (!(type instanceof AttributeType))
/*  85 */       throw new IllegalArgumentException("Please use an AttributeType"); 
/*  87 */     AttributeType t = type;
/*  89 */     return this.contents.put(n, t);
/*     */   }
/*     */   
/*     */   public AttributeType remove(Object key) {
/*  93 */     return this.contents.remove(key);
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends Name, ? extends AttributeType> t) {
/*  97 */     this.contents.putAll(t);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 101 */     this.contents.clear();
/*     */   }
/*     */   
/*     */   public Collection<AttributeType> values() {
/* 105 */     return this.contents.values();
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<Name, AttributeType>> entrySet() {
/* 109 */     return this.contents.entrySet();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 113 */     return this.contents.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 116 */     return this.contents.equals(obj);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 119 */     return this.contents.toString();
/*     */   }
/*     */   
/*     */   public String getURI() {
/* 123 */     return this.uri;
/*     */   }
/*     */   
/*     */   public void add(AttributeType type) {
/* 127 */     put(type.getName(), type);
/*     */   }
/*     */   
/*     */   public Schema profile(Set<Name> profile) {
/* 131 */     return new ProfileImpl(this, profile);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\type\SchemaImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */