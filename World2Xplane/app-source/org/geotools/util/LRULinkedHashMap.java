/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ 
/*     */ public final class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {
/*     */   private static final long serialVersionUID = -6668885347230182669L;
/*     */   
/*     */   private static final double DEFAULT_LOAD_FACTOR = 0.75D;
/*     */   
/*     */   private static final int DEFAULT_MAXIMUM_SIZE = 100;
/*     */   
/*     */   private int maximumSize;
/*     */   
/*     */   public LRULinkedHashMap() {
/*  72 */     this.maximumSize = 100;
/*     */   }
/*     */   
/*     */   public LRULinkedHashMap(int initialCapacity) {
/*  81 */     super(initialCapacity);
/*  82 */     this.maximumSize = 100;
/*     */   }
/*     */   
/*     */   public LRULinkedHashMap(int initialCapacity, float loadFactor) {
/*  92 */     super(initialCapacity, loadFactor);
/*  93 */     this.maximumSize = 100;
/*     */   }
/*     */   
/*     */   public LRULinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder) {
/* 107 */     super(initialCapacity, loadFactor, accessOrder);
/* 108 */     this.maximumSize = 100;
/*     */   }
/*     */   
/*     */   public LRULinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder, int maximumSize) {
/* 123 */     super(initialCapacity, loadFactor, accessOrder);
/* 124 */     this.maximumSize = maximumSize;
/* 125 */     checkMaximumSize(maximumSize);
/*     */   }
/*     */   
/*     */   private LRULinkedHashMap(boolean accessOrder, int maximumSize) {
/* 134 */     super((int)Math.ceil(maximumSize / 0.75D), 0.75F, accessOrder);
/* 136 */     this.maximumSize = maximumSize;
/*     */   }
/*     */   
/*     */   public LRULinkedHashMap(Map<K, V> map) {
/* 149 */     super(map);
/* 150 */     this.maximumSize = map.size();
/*     */   }
/*     */   
/*     */   public LRULinkedHashMap(Map<K, V> map, int maximumSize) {
/* 163 */     super(map);
/* 164 */     this.maximumSize = maximumSize;
/* 165 */     checkMaximumSize(maximumSize);
/* 166 */     removeExtraEntries();
/*     */   }
/*     */   
/*     */   private static void checkMaximumSize(int maximumSize) throws IllegalArgumentException {
/* 173 */     if (maximumSize <= 0)
/* 174 */       throw new IllegalArgumentException(Errors.format(125, Integer.valueOf(maximumSize))); 
/*     */   }
/*     */   
/*     */   private void removeExtraEntries() {
/* 189 */     if (size() > this.maximumSize) {
/* 190 */       Iterator<Map.Entry<K, V>> it = entrySet().iterator();
/* 191 */       for (int c = 0; c < this.maximumSize; c++)
/* 192 */         it.next(); 
/* 194 */       while (it.hasNext())
/* 195 */         it.remove(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static <K, V> LRULinkedHashMap<K, V> createForRecentAccess(int maximumSize) {
/* 214 */     checkMaximumSize(maximumSize);
/* 215 */     return new LRULinkedHashMap<K, V>(true, maximumSize);
/*     */   }
/*     */   
/*     */   public static <K, V> LRULinkedHashMap<K, V> createForRecentInserts(int maximumSize) {
/* 232 */     checkMaximumSize(maximumSize);
/* 233 */     return new LRULinkedHashMap<K, V>(false, maximumSize);
/*     */   }
/*     */   
/*     */   public int getMaximumSize() {
/* 245 */     return this.maximumSize;
/*     */   }
/*     */   
/*     */   public void setMaximumSize(int max) {
/* 258 */     checkMaximumSize(max);
/* 259 */     this.maximumSize = max;
/* 260 */     removeExtraEntries();
/*     */   }
/*     */   
/*     */   protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
/* 276 */     return (size() > this.maximumSize);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\LRULinkedHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */