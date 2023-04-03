/*     */ package org.geotools.metadata;
/*     */ 
/*     */ import java.util.AbstractMap;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import org.geotools.util.Utilities;
/*     */ 
/*     */ final class PropertyMap extends AbstractMap<String, Object> {
/*     */   private final Object metadata;
/*     */   
/*     */   private final PropertyAccessor accessor;
/*     */   
/*     */   private final Set<Map.Entry<String, Object>> entrySet;
/*     */   
/*     */   public PropertyMap(Object metadata, PropertyAccessor accessor) {
/*  60 */     this.metadata = metadata;
/*  61 */     this.accessor = accessor;
/*  62 */     this.entrySet = new Entries();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  70 */     return entrySet().isEmpty();
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/*  78 */     return (get(key) != null);
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/*  87 */     if (key instanceof String) {
/*  88 */       Object value = this.accessor.get(this.accessor.indexOf((String)key), this.metadata);
/*  89 */       if (!PropertyAccessor.isEmpty(value))
/*  90 */         return value; 
/*     */     } 
/*  93 */     return null;
/*     */   }
/*     */   
/*     */   public Object put(String key, Object value) throws IllegalArgumentException, ClassCastException {
/* 106 */     return this.accessor.set(this.accessor.requiredIndexOf(key), this.metadata, value);
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 114 */     if (key instanceof String)
/* 115 */       return put((String)key, (Object)null); 
/* 117 */     return null;
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<String, Object>> entrySet() {
/* 126 */     return this.entrySet;
/*     */   }
/*     */   
/*     */   private final class Property implements Map.Entry<String, Object> {
/*     */     final int index;
/*     */     
/*     */     Property(int index) {
/* 147 */       this.index = index;
/*     */     }
/*     */     
/*     */     Property(String property) {
/* 154 */       this.index = PropertyMap.this.accessor.indexOf(property);
/*     */     }
/*     */     
/*     */     public String getKey() {
/* 161 */       return PropertyMap.this.accessor.name(this.index);
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 168 */       Object value = PropertyMap.this.accessor.get(this.index, PropertyMap.this.metadata);
/* 169 */       return PropertyAccessor.isEmpty(value) ? null : value;
/*     */     }
/*     */     
/*     */     public Object setValue(Object value) throws ClassCastException {
/* 178 */       return PropertyMap.this.accessor.set(this.index, PropertyMap.this.metadata, value);
/*     */     }
/*     */     
/*     */     public boolean equals(Map.Entry<?, ?> entry) {
/* 185 */       return (Utilities.equals(getKey(), entry.getKey()) && Utilities.equals(getValue(), entry.getValue()));
/*     */     }
/*     */     
/*     */     public boolean equals(Object object) {
/* 195 */       return (object instanceof Map.Entry && equals((Map.Entry<?, ?>)object));
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 204 */       Object x = getKey();
/* 205 */       int code = (x != null) ? x.hashCode() : 0;
/* 206 */       x = getValue();
/* 207 */       if (x != null)
/* 208 */         code ^= x.hashCode(); 
/* 210 */       return code;
/*     */     }
/*     */   }
/*     */   
/*     */   private final class Iter implements Iterator<Map.Entry<String, Object>> {
/*     */     private PropertyMap.Property current;
/*     */     
/*     */     private PropertyMap.Property next;
/*     */     
/*     */     Iter() {
/* 232 */       move(0);
/*     */     }
/*     */     
/*     */     private void move(int index) {
/* 240 */       int count = PropertyMap.this.accessor.count();
/* 241 */       while (index < count) {
/* 242 */         if (!PropertyAccessor.isEmpty(PropertyMap.this.accessor.get(index, PropertyMap.this.metadata))) {
/* 243 */           this.next = new PropertyMap.Property(index);
/*     */           return;
/*     */         } 
/* 246 */         index++;
/*     */       } 
/* 248 */       this.next = null;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 255 */       return (this.next != null);
/*     */     }
/*     */     
/*     */     public Map.Entry<String, Object> next() {
/* 262 */       if (this.next != null) {
/* 263 */         this.current = this.next;
/* 264 */         move(this.next.index + 1);
/* 265 */         return this.current;
/*     */       } 
/* 267 */       throw new NoSuchElementException();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 275 */       if (this.current != null) {
/* 276 */         this.current.setValue(null);
/* 277 */         this.current = null;
/*     */       } else {
/* 279 */         throw new IllegalStateException();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private final class Entries extends AbstractSet<Map.Entry<String, Object>> {
/*     */     public Iterator<Map.Entry<String, Object>> iterator() {
/* 304 */       return new PropertyMap.Iter();
/*     */     }
/*     */     
/*     */     public int size() {
/* 311 */       return PropertyMap.this.accessor.count(PropertyMap.this.metadata, 2147483647);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 319 */       return (PropertyMap.this.accessor.count(PropertyMap.this.metadata, 1) == 0);
/*     */     }
/*     */     
/*     */     public boolean contains(Object object) {
/* 327 */       if (object instanceof Map.Entry) {
/* 328 */         Map.Entry<?, ?> entry = (Map.Entry)object;
/* 329 */         Object key = entry.getKey();
/* 330 */         if (key instanceof String)
/* 331 */           return (new PropertyMap.Property((String)key)).equals(entry); 
/*     */       } 
/* 334 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\PropertyMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */