/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.util.Set;
/*     */ import org.geotools.factory.FactoryRegistryException;
/*     */ import org.geotools.factory.GeoTools;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.util.GenericName;
/*     */ 
/*     */ public final class ObjectCaches {
/*     */   private static final class Pair {
/*     */     private final String source;
/*     */     
/*     */     private final String target;
/*     */     
/*     */     public Pair(String source, String target) {
/*  78 */       this.source = source;
/*  79 */       this.target = target;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  83 */       int code = 0;
/*  84 */       if (this.source != null)
/*  84 */         code = this.source.hashCode(); 
/*  85 */       if (this.target != null)
/*  85 */         code += this.target.hashCode() * 37; 
/*  86 */       return code;
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/*  90 */       if (other instanceof Pair) {
/*  91 */         Pair that = (Pair)other;
/*  92 */         return (Utilities.equals(this.source, that.source) && Utilities.equals(this.target, that.target));
/*     */       } 
/*  95 */       return false;
/*     */     }
/*     */     
/*     */     public String toString() {
/*  99 */       return this.source + " ⇨ " + this.target;
/*     */     }
/*     */   }
/*     */   
/*     */   public static ObjectCache chain(final ObjectCache level1, final ObjectCache level2) {
/* 122 */     if (level1 == level2)
/* 123 */       return level1; 
/* 125 */     if (level1 == null)
/* 125 */       return level2; 
/* 126 */     if (level2 == null)
/* 126 */       return level1; 
/* 127 */     return new ObjectCache() {
/*     */         public void clear() {
/* 129 */           level1.clear();
/*     */         }
/*     */         
/*     */         public Object get(Object key) {
/* 132 */           Object value = level1.get(key);
/* 133 */           if (value == null) {
/* 134 */             Object check = level2.get(key);
/* 135 */             if (check != null)
/*     */               try {
/* 137 */                 level1.writeLock(key);
/* 138 */                 value = level1.peek(key);
/* 139 */                 if (value == null) {
/* 140 */                   level1.put(key, check);
/* 141 */                   value = check;
/*     */                 } 
/*     */               } finally {
/* 145 */                 level1.writeUnLock(key);
/*     */               }  
/*     */           } 
/* 149 */           return value;
/*     */         }
/*     */         
/*     */         public Object peek(Object key) {
/* 153 */           return level1.peek(key);
/*     */         }
/*     */         
/*     */         public void put(Object key, Object object) {
/* 157 */           level1.put(key, object);
/*     */         }
/*     */         
/*     */         public void writeLock(Object key) {
/* 161 */           level1.writeLock(key);
/*     */         }
/*     */         
/*     */         public void writeUnLock(Object key) {
/* 165 */           level1.writeLock(key);
/*     */         }
/*     */         
/*     */         public Set<Object> getKeys() {
/* 169 */           return level1.getKeys();
/*     */         }
/*     */         
/*     */         public void remove(Object key) {
/* 173 */           level1.remove(key);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static ObjectCache create(Hints hints) throws FactoryRegistryException {
/* 182 */     if (hints == null)
/* 182 */       hints = GeoTools.getDefaultHints(); 
/* 183 */     String policy = (String)hints.get(Hints.CACHE_POLICY);
/* 184 */     int limit = Hints.CACHE_LIMIT.toValue(hints);
/* 185 */     return create(policy, limit);
/*     */   }
/*     */   
/*     */   public static ObjectCache create(String policy, int size) {
/* 196 */     if ("weak".equalsIgnoreCase(policy))
/* 197 */       return new WeakObjectCache(0); 
/* 198 */     if ("all".equalsIgnoreCase(policy))
/* 199 */       return new DefaultObjectCache(size); 
/* 200 */     if ("none".equalsIgnoreCase(policy))
/* 201 */       return NullObjectCache.INSTANCE; 
/* 202 */     if ("fixed".equalsIgnoreCase(policy))
/* 203 */       return new FixedSizeObjectCache(size); 
/* 204 */     if ("soft".equals(policy))
/* 205 */       return new SoftObjectCache(size); 
/* 207 */     return new DefaultObjectCache(size);
/*     */   }
/*     */   
/*     */   public static String toKey(Citation citation, String code) {
/* 219 */     code = code.trim();
/* 220 */     GenericName name = NameFactory.create(code);
/* 221 */     GenericName scope = name.scope().name();
/* 222 */     if (scope == null)
/* 223 */       return code; 
/* 225 */     if (citation != null && Citations.identifierMatches(citation, scope.toString()))
/* 226 */       return name.tip().toString().trim(); 
/* 228 */     return code;
/*     */   }
/*     */   
/*     */   public static Object toKey(Citation citation, String code1, String code2) {
/* 239 */     String key1 = toKey(citation, code1);
/* 240 */     String key2 = toKey(citation, code2);
/* 242 */     return new Pair(key1, key2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\ObjectCaches.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */