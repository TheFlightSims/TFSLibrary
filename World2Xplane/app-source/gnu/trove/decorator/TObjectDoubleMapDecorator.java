/*     */ package gnu.trove.decorator;
/*     */ 
/*     */ import gnu.trove.iterator.TObjectDoubleIterator;
/*     */ import gnu.trove.map.TObjectDoubleMap;
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
/*     */ public class TObjectDoubleMapDecorator<K> extends AbstractMap<K, Double> implements Map<K, Double>, Externalizable, Cloneable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   protected TObjectDoubleMap<K> _map;
/*     */   
/*     */   public TObjectDoubleMapDecorator() {}
/*     */   
/*     */   public TObjectDoubleMapDecorator(TObjectDoubleMap<K> map) {
/*  74 */     this._map = map;
/*     */   }
/*     */   
/*     */   public TObjectDoubleMap<K> getMap() {
/*  84 */     return this._map;
/*     */   }
/*     */   
/*     */   public Double put(K key, Double value) {
/*  97 */     if (value == null)
/*  97 */       return wrapValue(this._map.put(key, this._map.getNoEntryValue())); 
/*  98 */     return wrapValue(this._map.put(key, unwrapValue(value)));
/*     */   }
/*     */   
/*     */   public Double get(Object key) {
/* 109 */     double v = this._map.get(key);
/* 113 */     if (v == this._map.getNoEntryValue())
/* 114 */       return null; 
/* 116 */     return wrapValue(v);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 125 */     this._map.clear();
/*     */   }
/*     */   
/*     */   public Double remove(Object key) {
/* 136 */     double v = this._map.remove(key);
/* 140 */     if (v == this._map.getNoEntryValue())
/* 141 */       return null; 
/* 143 */     return wrapValue(v);
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<K, Double>> entrySet() {
/* 154 */     return new AbstractSet<Map.Entry<K, Double>>() {
/*     */         public int size() {
/* 156 */           return TObjectDoubleMapDecorator.this._map.size();
/*     */         }
/*     */         
/*     */         public boolean isEmpty() {
/* 160 */           return TObjectDoubleMapDecorator.this.isEmpty();
/*     */         }
/*     */         
/*     */         public boolean contains(Object o) {
/* 164 */           if (o instanceof Map.Entry) {
/* 165 */             Object k = ((Map.Entry)o).getKey();
/* 166 */             Object v = ((Map.Entry)o).getValue();
/* 167 */             return (TObjectDoubleMapDecorator.this.containsKey(k) && TObjectDoubleMapDecorator.this.get(k).equals(v));
/*     */           } 
/* 170 */           return false;
/*     */         }
/*     */         
/*     */         public Iterator<Map.Entry<K, Double>> iterator() {
/* 175 */           return new Iterator<Map.Entry<K, Double>>() {
/* 176 */               private final TObjectDoubleIterator<K> it = TObjectDoubleMapDecorator.this._map.iterator();
/*     */               
/*     */               public Map.Entry<K, Double> next() {
/* 179 */                 this.it.advance();
/* 180 */                 final K key = (K)this.it.key();
/* 181 */                 final Double v = TObjectDoubleMapDecorator.this.wrapValue(this.it.value());
/* 182 */                 return new Map.Entry<K, Double>() {
/* 183 */                     private Double val = v;
/*     */                     
/*     */                     public boolean equals(Object o) {
/* 186 */                       return (o instanceof Map.Entry && ((Map.Entry)o).getKey().equals(key) && ((Map.Entry)o).getValue().equals(this.val));
/*     */                     }
/*     */                     
/*     */                     public K getKey() {
/* 192 */                       return (K)key;
/*     */                     }
/*     */                     
/*     */                     public Double getValue() {
/* 196 */                       return this.val;
/*     */                     }
/*     */                     
/*     */                     public int hashCode() {
/* 200 */                       return key.hashCode() + this.val.hashCode();
/*     */                     }
/*     */                     
/*     */                     public Double setValue(Double value) {
/* 204 */                       this.val = value;
/* 205 */                       return TObjectDoubleMapDecorator.this.put(key, value);
/*     */                     }
/*     */                   };
/*     */               }
/*     */               
/*     */               public boolean hasNext() {
/* 211 */                 return this.it.hasNext();
/*     */               }
/*     */               
/*     */               public void remove() {
/* 215 */                 this.it.remove();
/*     */               }
/*     */             };
/*     */         }
/*     */         
/*     */         public boolean add(Map.Entry<K, Double> o) {
/* 221 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public boolean remove(Object o) {
/* 225 */           boolean modified = false;
/* 226 */           if (contains(o)) {
/* 228 */             K key = (K)((Map.Entry)o).getKey();
/* 229 */             TObjectDoubleMapDecorator.this._map.remove(key);
/* 230 */             modified = true;
/*     */           } 
/* 232 */           return modified;
/*     */         }
/*     */         
/*     */         public boolean addAll(Collection<? extends Map.Entry<K, Double>> c) {
/* 236 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void clear() {
/* 240 */           TObjectDoubleMapDecorator.this.clear();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object val) {
/* 253 */     return (val instanceof Double && this._map.containsValue(unwrapValue(val)));
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 264 */     return this._map.containsKey(key);
/*     */   }
/*     */   
/*     */   public int size() {
/* 274 */     return this._map.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 284 */     return (this._map.size() == 0);
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends Double> map) {
/* 296 */     Iterator<? extends Map.Entry<? extends K, ? extends Double>> it = map.entrySet().iterator();
/* 297 */     for (int i = map.size(); i-- > 0; ) {
/* 298 */       Map.Entry<? extends K, ? extends Double> e = it.next();
/* 299 */       put(e.getKey(), e.getValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Double wrapValue(double k) {
/* 311 */     return Double.valueOf(k);
/*     */   }
/*     */   
/*     */   protected double unwrapValue(Object value) {
/* 322 */     return ((Double)value).doubleValue();
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 331 */     in.readByte();
/* 335 */     this._map = (TObjectDoubleMap<K>)in.readObject();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 342 */     out.writeByte(0);
/* 345 */     out.writeObject(this._map);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\decorator\TObjectDoubleMapDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */