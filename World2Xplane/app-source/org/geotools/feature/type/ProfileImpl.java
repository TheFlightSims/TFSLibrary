/*     */ package org.geotools.feature.type;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import org.opengis.feature.type.AttributeType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.feature.type.Schema;
/*     */ 
/*     */ public class ProfileImpl implements Schema {
/*     */   private Schema parent;
/*     */   
/*     */   private Set<Name> profile;
/*     */   
/*  68 */   private Map contents = null;
/*     */   
/*     */   public ProfileImpl(Schema parent, Set<Name> profile) {
/*  77 */     this.parent = parent;
/*  79 */     this.profile = Collections.unmodifiableSet(profile);
/*     */   }
/*     */   
/*     */   public Set<Name> keySet() {
/*  83 */     return this.profile;
/*     */   }
/*     */   
/*     */   public String getURI() {
/*  87 */     return this.parent.getURI();
/*     */   }
/*     */   
/*     */   public Schema profile(Set<Name> profile) {
/*  91 */     if (!this.profile.containsAll(profile)) {
/*  92 */       Set<Name> set = new TreeSet<Name>(profile);
/*  93 */       set.removeAll(this.profile);
/*  94 */       throw new IllegalArgumentException("Unable to profile the following names: " + set);
/*     */     } 
/*  96 */     return this.parent.profile(profile);
/*     */   }
/*     */   
/*     */   public int size() {
/* 100 */     return this.profile.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 104 */     return this.profile.isEmpty();
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 108 */     return this.profile.contains(key);
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 112 */     return values().contains(value);
/*     */   }
/*     */   
/*     */   public AttributeType get(Object key) {
/* 116 */     if (this.profile.contains(key))
/* 117 */       return (AttributeType)this.parent.get(key); 
/* 119 */     return null;
/*     */   }
/*     */   
/*     */   public AttributeType put(Name key, AttributeType value) {
/* 123 */     throw new UnsupportedOperationException("Profile not mutable");
/*     */   }
/*     */   
/*     */   public AttributeType remove(Object key) {
/* 127 */     throw new UnsupportedOperationException("Profile not mutable");
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends Name, ? extends AttributeType> t) {
/* 131 */     throw new UnsupportedOperationException("Profile not mutable");
/*     */   }
/*     */   
/*     */   public void clear() {
/* 135 */     throw new UnsupportedOperationException("Profile not mutable");
/*     */   }
/*     */   
/*     */   public void add(AttributeType type) {
/* 139 */     throw new UnsupportedOperationException("Profile not mutable");
/*     */   }
/*     */   
/*     */   public Collection<AttributeType> values() {
/* 144 */     return contents().values();
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<Name, AttributeType>> entrySet() {
/* 149 */     return contents().entrySet();
/*     */   }
/*     */   
/*     */   private synchronized Map<Name, AttributeType> contents() {
/* 153 */     if (this.contents == null) {
/* 154 */       this.contents = new LinkedHashMap<Object, Object>();
/* 155 */       for (Iterator<Name> i = this.profile.iterator(); i.hasNext(); ) {
/* 156 */         Object key = i.next();
/* 157 */         this.contents.put(key, this.parent.get(key));
/*     */       } 
/*     */     } 
/* 160 */     return this.contents;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\type\ProfileImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */