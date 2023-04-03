/*     */ package com.world2xplane.Geom;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class MultiMap<A, B> {
/*     */   private final Map<A, Set<B>> map;
/*     */   
/*     */   public MultiMap() {
/*  47 */     this.map = new HashMap<>();
/*     */   }
/*     */   
/*     */   public MultiMap(int capacity) {
/*  51 */     this.map = new HashMap<>(capacity);
/*     */   }
/*     */   
/*     */   public void put(A key, B value) {
/*  60 */     Set<B> vals = this.map.get(key);
/*  61 */     if (vals == null) {
/*  62 */       vals = new LinkedHashSet<>();
/*  63 */       this.map.put(key, vals);
/*     */     } 
/*  65 */     vals.add(value);
/*     */   }
/*     */   
/*     */   public void putVoid(A key) {
/*  75 */     if (this.map.containsKey(key))
/*     */       return; 
/*  77 */     this.map.put(key, new LinkedHashSet<>());
/*     */   }
/*     */   
/*     */   public void putAll(A key, Collection<B> values) {
/*  86 */     Set<B> vals = this.map.get(key);
/*  87 */     if (vals == null) {
/*  88 */       vals = new LinkedHashSet<>(values);
/*  89 */       this.map.put(key, vals);
/*     */     } 
/*  91 */     vals.addAll(values);
/*     */   }
/*     */   
/*     */   public Set<A> keySet() {
/*  98 */     return this.map.keySet();
/*     */   }
/*     */   
/*     */   public Set<B> get(A key) {
/* 109 */     return this.map.get(key);
/*     */   }
/*     */   
/*     */   public Set<B> getValues(A key) {
/* 116 */     if (!this.map.containsKey(key))
/* 117 */       return new LinkedHashSet<>(); 
/* 118 */     return this.map.get(key);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 122 */     return this.map.isEmpty();
/*     */   }
/*     */   
/*     */   public boolean containsKey(A key) {
/* 126 */     return this.map.containsKey(key);
/*     */   }
/*     */   
/*     */   public boolean contains(A key, B value) {
/* 137 */     Set<B> values = get(key);
/* 138 */     return (values == null) ? false : values.contains(value);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 142 */     this.map.clear();
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<A, Set<B>>> entrySet() {
/* 146 */     return this.map.entrySet();
/*     */   }
/*     */   
/*     */   public int size() {
/* 153 */     return this.map.size();
/*     */   }
/*     */   
/*     */   public Collection<Set<B>> values() {
/* 160 */     return this.map.values();
/*     */   }
/*     */   
/*     */   public boolean remove(A key, B value) {
/* 169 */     Set<B> values = get(key);
/* 170 */     if (values != null)
/* 171 */       return values.remove(value); 
/* 173 */     return false;
/*     */   }
/*     */   
/*     */   public Set<B> remove(A key) {
/* 180 */     return this.map.remove(key);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 185 */     List<String> entries = new ArrayList<>(this.map.size());
/* 186 */     for (A key : this.map.keySet())
/* 187 */       entries.add((new StringBuilder()).append(key).append("->{").append(join(",", this.map.get(key))).append("}").toString()); 
/* 189 */     return "(" + join(",", entries) + ")";
/*     */   }
/*     */   
/*     */   public static String join(String sep, Collection<?> values) {
/* 193 */     if (sep == null)
/* 194 */       throw new IllegalArgumentException(); 
/* 195 */     if (values == null)
/* 196 */       return null; 
/* 197 */     if (values.isEmpty())
/* 198 */       return ""; 
/* 199 */     StringBuilder s = null;
/* 200 */     for (Object a : values) {
/* 201 */       if (a == null)
/* 202 */         a = ""; 
/* 204 */       if (s != null) {
/* 205 */         s.append(sep).append(a.toString());
/*     */         continue;
/*     */       } 
/* 207 */       s = new StringBuilder(a.toString());
/*     */     } 
/* 210 */     return s.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\MultiMap.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */