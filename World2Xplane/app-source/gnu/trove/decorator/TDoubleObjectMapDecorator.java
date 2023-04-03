/*     */ package gnu.trove.decorator;
/*     */ 
/*     */ import gnu.trove.iterator.TDoubleObjectIterator;
/*     */ import gnu.trove.map.TDoubleObjectMap;
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
/*     */ public class TDoubleObjectMapDecorator<V> extends AbstractMap<Double, V> implements Map<Double, V>, Externalizable, Cloneable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   protected TDoubleObjectMap<V> _map;
/*     */   
/*     */   public TDoubleObjectMapDecorator() {}
/*     */   
/*     */   public TDoubleObjectMapDecorator(TDoubleObjectMap<V> map) {
/*  72 */     this._map = map;
/*     */   }
/*     */   
/*     */   public TDoubleObjectMap<V> getMap() {
/*  82 */     return this._map;
/*     */   }
/*     */   
/*     */   public V put(Double key, V value) {
/*     */     double k;
/*  96 */     if (key == null) {
/*  97 */       k = this._map.getNoEntryKey();
/*     */     } else {
/*  99 */       k = unwrapKey(key);
/*     */     } 
/* 101 */     return (V)this._map.put(k, value);
/*     */   }
/*     */   
/*     */   public V get(Object key) {
/*     */     double k;
/* 113 */     if (key != null) {
/* 114 */       if (key instanceof Double) {
/* 115 */         k = unwrapKey((Double)key);
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
/*     */     double k;
/* 142 */     if (key != null) {
/* 143 */       if (key instanceof Double) {
/* 144 */         k = unwrapKey((Double)key);
/*     */       } else {
/* 146 */         return null;
/*     */       } 
/*     */     } else {
/* 149 */       k = this._map.getNoEntryKey();
/*     */     } 
/* 151 */     return (V)this._map.remove(k);
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<Double, V>> entrySet() {
/* 161 */     return new AbstractSet<Map.Entry<Double, V>>() {
/*     */         public int size() {
/* 163 */           return TDoubleObjectMapDecorator.this._map.size();
/*     */         }
/*     */         
/*     */         public boolean isEmpty() {
/* 167 */           return TDoubleObjectMapDecorator.this.isEmpty();
/*     */         }
/*     */         
/*     */         public boolean contains(Object o) {
/* 171 */           if (o instanceof Map.Entry) {
/* 172 */             Object k = ((Map.Entry)o).getKey();
/* 173 */             Object v = ((Map.Entry)o).getValue();
/* 174 */             return (TDoubleObjectMapDecorator.this.containsKey(k) && TDoubleObjectMapDecorator.this.get(k).equals(v));
/*     */           } 
/* 177 */           return false;
/*     */         }
/*     */         
/*     */         public Iterator<Map.Entry<Double, V>> iterator() {
/* 182 */           return new Iterator<Map.Entry<Double, V>>() {
/* 183 */               private final TDoubleObjectIterator<V> it = TDoubleObjectMapDecorator.this._map.iterator();
/*     */               
/*     */               public Map.Entry<Double, V> next() {
/* 186 */                 this.it.advance();
/* 187 */                 double k = this.it.key();
/* 188 */                 final Double key = (k == TDoubleObjectMapDecorator.this._map.getNoEntryKey()) ? null : TDoubleObjectMapDecorator.this.wrapKey(k);
/* 189 */                 final V v = (V)this.it.value();
/* 190 */                 return new Map.Entry<Double, V>() {
/* 191 */                     private V val = (V)v;
/*     */                     
/*     */                     public boolean equals(Object o) {
/* 194 */                       return (o instanceof Map.Entry && ((Map.Entry)o).getKey().equals(key) && ((Map.Entry)o).getValue().equals(this.val));
/*     */                     }
/*     */                     
/*     */                     public Double getKey() {
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
/* 213 */                       return TDoubleObjectMapDecorator.this.put(key, value);
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
/*     */         public boolean add(Map.Entry<Double, V> o) {
/* 229 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public boolean remove(Object o) {
/* 233 */           boolean modified = false;
/* 234 */           if (contains(o)) {
/* 236 */             Double key = (Double)((Map.Entry)o).getKey();
/* 237 */             TDoubleObjectMapDecorator.this._map.remove(TDoubleObjectMapDecorator.this.unwrapKey(key));
/* 238 */             modified = true;
/*     */           } 
/* 240 */           return modified;
/*     */         }
/*     */         
/*     */         public boolean addAll(Collection<? extends Map.Entry<Double, V>> c) {
/* 244 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void clear() {
/* 248 */           TDoubleObjectMapDecorator.this.clear();
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
/* 273 */     return (key instanceof Double && this._map.containsKey(((Double)key).doubleValue()));
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
/*     */   public void putAll(Map<? extends Double, ? extends V> map) {
/* 305 */     Iterator<? extends Map.Entry<? extends Double, ? extends V>> it = map.entrySet().iterator();
/* 306 */     for (int i = map.size(); i-- > 0; ) {
/* 307 */       Map.Entry<? extends Double, ? extends V> e = it.next();
/* 308 */       put(e.getKey(), e.getValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Double wrapKey(double k) {
/* 320 */     return Double.valueOf(k);
/*     */   }
/*     */   
/*     */   protected double unwrapKey(Double key) {
/* 331 */     return key.doubleValue();
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 340 */     in.readByte();
/* 344 */     this._map = (TDoubleObjectMap<V>)in.readObject();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 351 */     out.writeByte(0);
/* 354 */     out.writeObject(this._map);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\decorator\TDoubleObjectMapDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */