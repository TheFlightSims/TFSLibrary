/*     */ package gnu.trove.decorator;
/*     */ 
/*     */ import gnu.trove.iterator.TFloatObjectIterator;
/*     */ import gnu.trove.map.TFloatObjectMap;
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
/*     */ public class TFloatObjectMapDecorator<V> extends AbstractMap<Float, V> implements Map<Float, V>, Externalizable, Cloneable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   protected TFloatObjectMap<V> _map;
/*     */   
/*     */   public TFloatObjectMapDecorator() {}
/*     */   
/*     */   public TFloatObjectMapDecorator(TFloatObjectMap<V> map) {
/*  72 */     this._map = map;
/*     */   }
/*     */   
/*     */   public TFloatObjectMap<V> getMap() {
/*  82 */     return this._map;
/*     */   }
/*     */   
/*     */   public V put(Float key, V value) {
/*     */     float k;
/*  96 */     if (key == null) {
/*  97 */       k = this._map.getNoEntryKey();
/*     */     } else {
/*  99 */       k = unwrapKey(key);
/*     */     } 
/* 101 */     return (V)this._map.put(k, value);
/*     */   }
/*     */   
/*     */   public V get(Object key) {
/*     */     float k;
/* 113 */     if (key != null) {
/* 114 */       if (key instanceof Float) {
/* 115 */         k = unwrapKey((Float)key);
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
/*     */     float k;
/* 142 */     if (key != null) {
/* 143 */       if (key instanceof Float) {
/* 144 */         k = unwrapKey((Float)key);
/*     */       } else {
/* 146 */         return null;
/*     */       } 
/*     */     } else {
/* 149 */       k = this._map.getNoEntryKey();
/*     */     } 
/* 151 */     return (V)this._map.remove(k);
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<Float, V>> entrySet() {
/* 161 */     return new AbstractSet<Map.Entry<Float, V>>() {
/*     */         public int size() {
/* 163 */           return TFloatObjectMapDecorator.this._map.size();
/*     */         }
/*     */         
/*     */         public boolean isEmpty() {
/* 167 */           return TFloatObjectMapDecorator.this.isEmpty();
/*     */         }
/*     */         
/*     */         public boolean contains(Object o) {
/* 171 */           if (o instanceof Map.Entry) {
/* 172 */             Object k = ((Map.Entry)o).getKey();
/* 173 */             Object v = ((Map.Entry)o).getValue();
/* 174 */             return (TFloatObjectMapDecorator.this.containsKey(k) && TFloatObjectMapDecorator.this.get(k).equals(v));
/*     */           } 
/* 177 */           return false;
/*     */         }
/*     */         
/*     */         public Iterator<Map.Entry<Float, V>> iterator() {
/* 182 */           return new Iterator<Map.Entry<Float, V>>() {
/* 183 */               private final TFloatObjectIterator<V> it = TFloatObjectMapDecorator.this._map.iterator();
/*     */               
/*     */               public Map.Entry<Float, V> next() {
/* 186 */                 this.it.advance();
/* 187 */                 float k = this.it.key();
/* 188 */                 final Float key = (k == TFloatObjectMapDecorator.this._map.getNoEntryKey()) ? null : TFloatObjectMapDecorator.this.wrapKey(k);
/* 189 */                 final V v = (V)this.it.value();
/* 190 */                 return new Map.Entry<Float, V>() {
/* 191 */                     private V val = (V)v;
/*     */                     
/*     */                     public boolean equals(Object o) {
/* 194 */                       return (o instanceof Map.Entry && ((Map.Entry)o).getKey().equals(key) && ((Map.Entry)o).getValue().equals(this.val));
/*     */                     }
/*     */                     
/*     */                     public Float getKey() {
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
/* 213 */                       return TFloatObjectMapDecorator.this.put(key, value);
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
/*     */         public boolean add(Map.Entry<Float, V> o) {
/* 229 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public boolean remove(Object o) {
/* 233 */           boolean modified = false;
/* 234 */           if (contains(o)) {
/* 236 */             Float key = (Float)((Map.Entry)o).getKey();
/* 237 */             TFloatObjectMapDecorator.this._map.remove(TFloatObjectMapDecorator.this.unwrapKey(key));
/* 238 */             modified = true;
/*     */           } 
/* 240 */           return modified;
/*     */         }
/*     */         
/*     */         public boolean addAll(Collection<? extends Map.Entry<Float, V>> c) {
/* 244 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void clear() {
/* 248 */           TFloatObjectMapDecorator.this.clear();
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
/* 273 */     return (key instanceof Float && this._map.containsKey(((Float)key).floatValue()));
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
/*     */   public void putAll(Map<? extends Float, ? extends V> map) {
/* 305 */     Iterator<? extends Map.Entry<? extends Float, ? extends V>> it = map.entrySet().iterator();
/* 306 */     for (int i = map.size(); i-- > 0; ) {
/* 307 */       Map.Entry<? extends Float, ? extends V> e = it.next();
/* 308 */       put(e.getKey(), e.getValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Float wrapKey(float k) {
/* 320 */     return Float.valueOf(k);
/*     */   }
/*     */   
/*     */   protected float unwrapKey(Float key) {
/* 331 */     return key.floatValue();
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 340 */     in.readByte();
/* 344 */     this._map = (TFloatObjectMap<V>)in.readObject();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 351 */     out.writeByte(0);
/* 354 */     out.writeObject(this._map);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\decorator\TFloatObjectMapDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */