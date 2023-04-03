/*     */ package gnu.trove.decorator;
/*     */ 
/*     */ import gnu.trove.iterator.TObjectIntIterator;
/*     */ import gnu.trove.map.TObjectIntMap;
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
/*     */ public class TObjectIntMapDecorator<K> extends AbstractMap<K, Integer> implements Map<K, Integer>, Externalizable, Cloneable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   protected TObjectIntMap<K> _map;
/*     */   
/*     */   public TObjectIntMapDecorator() {}
/*     */   
/*     */   public TObjectIntMapDecorator(TObjectIntMap<K> map) {
/*  74 */     this._map = map;
/*     */   }
/*     */   
/*     */   public TObjectIntMap<K> getMap() {
/*  84 */     return this._map;
/*     */   }
/*     */   
/*     */   public Integer put(K key, Integer value) {
/*  97 */     if (value == null)
/*  97 */       return wrapValue(this._map.put(key, this._map.getNoEntryValue())); 
/*  98 */     return wrapValue(this._map.put(key, unwrapValue(value)));
/*     */   }
/*     */   
/*     */   public Integer get(Object key) {
/* 109 */     int v = this._map.get(key);
/* 113 */     if (v == this._map.getNoEntryValue())
/* 114 */       return null; 
/* 116 */     return wrapValue(v);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 125 */     this._map.clear();
/*     */   }
/*     */   
/*     */   public Integer remove(Object key) {
/* 136 */     int v = this._map.remove(key);
/* 140 */     if (v == this._map.getNoEntryValue())
/* 141 */       return null; 
/* 143 */     return wrapValue(v);
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<K, Integer>> entrySet() {
/* 154 */     return new AbstractSet<Map.Entry<K, Integer>>() {
/*     */         public int size() {
/* 156 */           return TObjectIntMapDecorator.this._map.size();
/*     */         }
/*     */         
/*     */         public boolean isEmpty() {
/* 160 */           return TObjectIntMapDecorator.this.isEmpty();
/*     */         }
/*     */         
/*     */         public boolean contains(Object o) {
/* 164 */           if (o instanceof Map.Entry) {
/* 165 */             Object k = ((Map.Entry)o).getKey();
/* 166 */             Object v = ((Map.Entry)o).getValue();
/* 167 */             return (TObjectIntMapDecorator.this.containsKey(k) && TObjectIntMapDecorator.this.get(k).equals(v));
/*     */           } 
/* 170 */           return false;
/*     */         }
/*     */         
/*     */         public Iterator<Map.Entry<K, Integer>> iterator() {
/* 175 */           return new Iterator<Map.Entry<K, Integer>>() {
/* 176 */               private final TObjectIntIterator<K> it = TObjectIntMapDecorator.this._map.iterator();
/*     */               
/*     */               public Map.Entry<K, Integer> next() {
/* 179 */                 this.it.advance();
/* 180 */                 final K key = (K)this.it.key();
/* 181 */                 final Integer v = TObjectIntMapDecorator.this.wrapValue(this.it.value());
/* 182 */                 return new Map.Entry<K, Integer>() {
/* 183 */                     private Integer val = v;
/*     */                     
/*     */                     public boolean equals(Object o) {
/* 186 */                       return (o instanceof Map.Entry && ((Map.Entry)o).getKey().equals(key) && ((Map.Entry)o).getValue().equals(this.val));
/*     */                     }
/*     */                     
/*     */                     public K getKey() {
/* 192 */                       return (K)key;
/*     */                     }
/*     */                     
/*     */                     public Integer getValue() {
/* 196 */                       return this.val;
/*     */                     }
/*     */                     
/*     */                     public int hashCode() {
/* 200 */                       return key.hashCode() + this.val.hashCode();
/*     */                     }
/*     */                     
/*     */                     public Integer setValue(Integer value) {
/* 204 */                       this.val = value;
/* 205 */                       return TObjectIntMapDecorator.this.put(key, value);
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
/*     */         public boolean add(Map.Entry<K, Integer> o) {
/* 221 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public boolean remove(Object o) {
/* 225 */           boolean modified = false;
/* 226 */           if (contains(o)) {
/* 228 */             K key = (K)((Map.Entry)o).getKey();
/* 229 */             TObjectIntMapDecorator.this._map.remove(key);
/* 230 */             modified = true;
/*     */           } 
/* 232 */           return modified;
/*     */         }
/*     */         
/*     */         public boolean addAll(Collection<? extends Map.Entry<K, Integer>> c) {
/* 236 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void clear() {
/* 240 */           TObjectIntMapDecorator.this.clear();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object val) {
/* 253 */     return (val instanceof Integer && this._map.containsValue(unwrapValue(val)));
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
/*     */   public void putAll(Map<? extends K, ? extends Integer> map) {
/* 296 */     Iterator<? extends Map.Entry<? extends K, ? extends Integer>> it = map.entrySet().iterator();
/* 297 */     for (int i = map.size(); i-- > 0; ) {
/* 298 */       Map.Entry<? extends K, ? extends Integer> e = it.next();
/* 299 */       put(e.getKey(), e.getValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Integer wrapValue(int k) {
/* 311 */     return Integer.valueOf(k);
/*     */   }
/*     */   
/*     */   protected int unwrapValue(Object value) {
/* 322 */     return ((Integer)value).intValue();
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 331 */     in.readByte();
/* 335 */     this._map = (TObjectIntMap<K>)in.readObject();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 342 */     out.writeByte(0);
/* 345 */     out.writeObject(this._map);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\decorator\TObjectIntMapDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */