/*     */ package gnu.trove.decorator;
/*     */ 
/*     */ import gnu.trove.iterator.TObjectShortIterator;
/*     */ import gnu.trove.map.TObjectShortMap;
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
/*     */ public class TObjectShortMapDecorator<K> extends AbstractMap<K, Short> implements Map<K, Short>, Externalizable, Cloneable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   protected TObjectShortMap<K> _map;
/*     */   
/*     */   public TObjectShortMapDecorator() {}
/*     */   
/*     */   public TObjectShortMapDecorator(TObjectShortMap<K> map) {
/*  74 */     this._map = map;
/*     */   }
/*     */   
/*     */   public TObjectShortMap<K> getMap() {
/*  84 */     return this._map;
/*     */   }
/*     */   
/*     */   public Short put(K key, Short value) {
/*  97 */     if (value == null)
/*  97 */       return wrapValue(this._map.put(key, this._map.getNoEntryValue())); 
/*  98 */     return wrapValue(this._map.put(key, unwrapValue(value)));
/*     */   }
/*     */   
/*     */   public Short get(Object key) {
/* 109 */     short v = this._map.get(key);
/* 113 */     if (v == this._map.getNoEntryValue())
/* 114 */       return null; 
/* 116 */     return wrapValue(v);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 125 */     this._map.clear();
/*     */   }
/*     */   
/*     */   public Short remove(Object key) {
/* 136 */     short v = this._map.remove(key);
/* 140 */     if (v == this._map.getNoEntryValue())
/* 141 */       return null; 
/* 143 */     return wrapValue(v);
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<K, Short>> entrySet() {
/* 154 */     return new AbstractSet<Map.Entry<K, Short>>() {
/*     */         public int size() {
/* 156 */           return TObjectShortMapDecorator.this._map.size();
/*     */         }
/*     */         
/*     */         public boolean isEmpty() {
/* 160 */           return TObjectShortMapDecorator.this.isEmpty();
/*     */         }
/*     */         
/*     */         public boolean contains(Object o) {
/* 164 */           if (o instanceof Map.Entry) {
/* 165 */             Object k = ((Map.Entry)o).getKey();
/* 166 */             Object v = ((Map.Entry)o).getValue();
/* 167 */             return (TObjectShortMapDecorator.this.containsKey(k) && TObjectShortMapDecorator.this.get(k).equals(v));
/*     */           } 
/* 170 */           return false;
/*     */         }
/*     */         
/*     */         public Iterator<Map.Entry<K, Short>> iterator() {
/* 175 */           return new Iterator<Map.Entry<K, Short>>() {
/* 176 */               private final TObjectShortIterator<K> it = TObjectShortMapDecorator.this._map.iterator();
/*     */               
/*     */               public Map.Entry<K, Short> next() {
/* 179 */                 this.it.advance();
/* 180 */                 final K key = (K)this.it.key();
/* 181 */                 final Short v = TObjectShortMapDecorator.this.wrapValue(this.it.value());
/* 182 */                 return new Map.Entry<K, Short>() {
/* 183 */                     private Short val = v;
/*     */                     
/*     */                     public boolean equals(Object o) {
/* 186 */                       return (o instanceof Map.Entry && ((Map.Entry)o).getKey().equals(key) && ((Map.Entry)o).getValue().equals(this.val));
/*     */                     }
/*     */                     
/*     */                     public K getKey() {
/* 192 */                       return (K)key;
/*     */                     }
/*     */                     
/*     */                     public Short getValue() {
/* 196 */                       return this.val;
/*     */                     }
/*     */                     
/*     */                     public int hashCode() {
/* 200 */                       return key.hashCode() + this.val.hashCode();
/*     */                     }
/*     */                     
/*     */                     public Short setValue(Short value) {
/* 204 */                       this.val = value;
/* 205 */                       return TObjectShortMapDecorator.this.put(key, value);
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
/*     */         public boolean add(Map.Entry<K, Short> o) {
/* 221 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public boolean remove(Object o) {
/* 225 */           boolean modified = false;
/* 226 */           if (contains(o)) {
/* 228 */             K key = (K)((Map.Entry)o).getKey();
/* 229 */             TObjectShortMapDecorator.this._map.remove(key);
/* 230 */             modified = true;
/*     */           } 
/* 232 */           return modified;
/*     */         }
/*     */         
/*     */         public boolean addAll(Collection<? extends Map.Entry<K, Short>> c) {
/* 236 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void clear() {
/* 240 */           TObjectShortMapDecorator.this.clear();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object val) {
/* 253 */     return (val instanceof Short && this._map.containsValue(unwrapValue(val)));
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
/*     */   public void putAll(Map<? extends K, ? extends Short> map) {
/* 296 */     Iterator<? extends Map.Entry<? extends K, ? extends Short>> it = map.entrySet().iterator();
/* 297 */     for (int i = map.size(); i-- > 0; ) {
/* 298 */       Map.Entry<? extends K, ? extends Short> e = it.next();
/* 299 */       put(e.getKey(), e.getValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Short wrapValue(short k) {
/* 311 */     return Short.valueOf(k);
/*     */   }
/*     */   
/*     */   protected short unwrapValue(Object value) {
/* 322 */     return ((Short)value).shortValue();
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 331 */     in.readByte();
/* 335 */     this._map = (TObjectShortMap<K>)in.readObject();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 342 */     out.writeByte(0);
/* 345 */     out.writeObject(this._map);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\decorator\TObjectShortMapDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */