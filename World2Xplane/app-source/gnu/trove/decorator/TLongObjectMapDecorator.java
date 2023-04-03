/*     */ package gnu.trove.decorator;
/*     */ 
/*     */ import gnu.trove.iterator.TLongObjectIterator;
/*     */ import gnu.trove.map.TLongObjectMap;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class TLongObjectMapDecorator<V> extends AbstractMap<Long, V> implements Map<Long, V>, Externalizable, Cloneable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   protected TLongObjectMap<V> _map;
/*     */   
/*     */   public TLongObjectMapDecorator() {}
/*     */   
/*     */   public TLongObjectMapDecorator(TLongObjectMap<V> map) {
/*  72 */     this._map = map;
/*     */   }
/*     */   
/*     */   public TLongObjectMap<V> getMap() {
/*  82 */     return this._map;
/*     */   }
/*     */   
/*     */   public V put(Long key, V value) {
/*     */     long k;
/*  96 */     if (key == null) {
/*  97 */       k = this._map.getNoEntryKey();
/*     */     } else {
/*  99 */       k = unwrapKey(key);
/*     */     } 
/* 101 */     return (V)this._map.put(k, value);
/*     */   }
/*     */   
/*     */   public V get(Object key) {
/*     */     long k;
/* 113 */     if (key != null) {
/* 114 */       if (key instanceof Long) {
/* 115 */         k = unwrapKey((Long)key);
/*     */       } else {
/* 117 */         return null;
/*     */       } 
/*     */     } else {
/* 120 */       k = this._map.getNoEntryKey();
/*     */     } 
/* 122 */     return (V)this._map.get(k);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 130 */     this._map.clear();
/*     */   }
/*     */   
/*     */   public V remove(Object key) {
/*     */     long k;
/* 142 */     if (key != null) {
/* 143 */       if (key instanceof Long) {
/* 144 */         k = unwrapKey((Long)key);
/*     */       } else {
/* 146 */         return null;
/*     */       } 
/*     */     } else {
/* 149 */       k = this._map.getNoEntryKey();
/*     */     } 
/* 151 */     return (V)this._map.remove(k);
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<Long, V>> entrySet() {
/* 161 */     return new AbstractSet<Map.Entry<Long, V>>() {
/*     */         public int size() {
/* 163 */           return TLongObjectMapDecorator.this._map.size();
/*     */         }
/*     */         
/*     */         public boolean isEmpty() {
/* 167 */           return TLongObjectMapDecorator.this.isEmpty();
/*     */         }
/*     */         
/*     */         public boolean contains(Object o) {
/* 171 */           if (o instanceof Map.Entry) {
/* 172 */             Object k = ((Map.Entry)o).getKey();
/* 173 */             Object v = ((Map.Entry)o).getValue();
/* 174 */             return (TLongObjectMapDecorator.this.containsKey(k) && TLongObjectMapDecorator.this.get(k).equals(v));
/*     */           } 
/* 177 */           return false;
/*     */         }
/*     */         
/*     */         public Iterator<Map.Entry<Long, V>> iterator() {
/* 182 */           return new Iterator<Map.Entry<Long, V>>() {
/* 183 */               private final TLongObjectIterator<V> it = TLongObjectMapDecorator.this._map.iterator();
/*     */               
/*     */               public Map.Entry<Long, V> next() {
/* 186 */                 this.it.advance();
/* 187 */                 long k = this.it.key();
/* 188 */                 final Long key = (k == TLongObjectMapDecorator.this._map.getNoEntryKey()) ? null : TLongObjectMapDecorator.this.wrapKey(k);
/* 189 */                 final V v = (V)this.it.value();
/* 190 */                 return new Map.Entry<Long, V>() {
/* 191 */                     private V val = (V)v;
/*     */                     
/*     */                     public boolean equals(Object o) {
/* 194 */                       return (o instanceof Map.Entry && ((Map.Entry)o).getKey().equals(key) && ((Map.Entry)o).getValue().equals(this.val));
/*     */                     }
/*     */                     
/*     */                     public Long getKey() {
/* 200 */                       return key;
/*     */                     }
/*     */                     
/*     */                     public V getValue() {
/* 204 */                       return this.val;
/*     */                     }
/*     */                     
/*     */                     public int hashCode() {
/* 208 */                       return key.hashCode() + this.val.hashCode();
/*     */                     }
/*     */                     
/*     */                     public V setValue(V value) {
/* 212 */                       this.val = value;
/* 213 */                       return TLongObjectMapDecorator.this.put(key, value);
/*     */                     }
/*     */                   };
/*     */               }
/*     */               
/*     */               public boolean hasNext() {
/* 219 */                 return this.it.hasNext();
/*     */               }
/*     */               
/*     */               public void remove() {
/* 223 */                 this.it.remove();
/*     */               }
/*     */             };
/*     */         }
/*     */         
/*     */         public boolean add(Map.Entry<Long, V> o) {
/* 229 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public boolean remove(Object o) {
/* 233 */           boolean modified = false;
/* 234 */           if (contains(o)) {
/* 236 */             Long key = (Long)((Map.Entry)o).getKey();
/* 237 */             TLongObjectMapDecorator.this._map.remove(TLongObjectMapDecorator.this.unwrapKey(key));
/* 238 */             modified = true;
/*     */           } 
/* 240 */           return modified;
/*     */         }
/*     */         
/*     */         public boolean addAll(Collection<? extends Map.Entry<Long, V>> c) {
/* 244 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void clear() {
/* 248 */           TLongObjectMapDecorator.this.clear();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object val) {
/* 261 */     return this._map.containsValue(val);
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 272 */     if (key == null)
/* 272 */       return this._map.containsKey(this._map.getNoEntryKey()); 
/* 273 */     return (key instanceof Long && this._map.containsKey(((Long)key).longValue()));
/*     */   }
/*     */   
/*     */   public int size() {
/* 283 */     return this._map.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 293 */     return (size() == 0);
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends Long, ? extends V> map) {
/* 305 */     Iterator<? extends Map.Entry<? extends Long, ? extends V>> it = map.entrySet().iterator();
/* 306 */     for (int i = map.size(); i-- > 0; ) {
/* 307 */       Map.Entry<? extends Long, ? extends V> e = it.next();
/* 308 */       put(e.getKey(), e.getValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Long wrapKey(long k) {
/* 320 */     return Long.valueOf(k);
/*     */   }
/*     */   
/*     */   protected long unwrapKey(Long key) {
/* 331 */     return key.longValue();
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 340 */     in.readByte();
/* 344 */     this._map = (TLongObjectMap<V>)in.readObject();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 351 */     out.writeByte(0);
/* 354 */     out.writeObject(this._map);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\decorator\TLongObjectMapDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */