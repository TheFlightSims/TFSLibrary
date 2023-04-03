/*     */ package gnu.trove.decorator;
/*     */ 
/*     */ import gnu.trove.iterator.TLongLongIterator;
/*     */ import gnu.trove.map.TLongLongMap;
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
/*     */ public class TLongLongMapDecorator extends AbstractMap<Long, Long> implements Map<Long, Long>, Externalizable, Cloneable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   protected TLongLongMap _map;
/*     */   
/*     */   public TLongLongMapDecorator() {}
/*     */   
/*     */   public TLongLongMapDecorator(TLongLongMap map) {
/*  72 */     this._map = map;
/*     */   }
/*     */   
/*     */   public TLongLongMap getMap() {
/*  82 */     return this._map;
/*     */   }
/*     */   
/*     */   public Long put(Long key, Long value) {
/*     */     long k, v;
/*  97 */     if (key == null) {
/*  98 */       k = this._map.getNoEntryKey();
/*     */     } else {
/* 100 */       k = unwrapKey(key);
/*     */     } 
/* 102 */     if (value == null) {
/* 103 */       v = this._map.getNoEntryValue();
/*     */     } else {
/* 105 */       v = unwrapValue(value);
/*     */     } 
/* 107 */     long retval = this._map.put(k, v);
/* 108 */     if (retval == this._map.getNoEntryValue())
/* 109 */       return null; 
/* 111 */     return wrapValue(retval);
/*     */   }
/*     */   
/*     */   public Long get(Object key) {
/*     */     long k;
/* 123 */     if (key != null) {
/* 124 */       if (key instanceof Long) {
/* 125 */         k = unwrapKey(key);
/*     */       } else {
/* 127 */         return null;
/*     */       } 
/*     */     } else {
/* 130 */       k = this._map.getNoEntryKey();
/*     */     } 
/* 132 */     long v = this._map.get(k);
/* 136 */     if (v == this._map.getNoEntryValue())
/* 137 */       return null; 
/* 139 */     return wrapValue(v);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 148 */     this._map.clear();
/*     */   }
/*     */   
/*     */   public Long remove(Object key) {
/*     */     long k;
/* 160 */     if (key != null) {
/* 161 */       if (key instanceof Long) {
/* 162 */         k = unwrapKey(key);
/*     */       } else {
/* 164 */         return null;
/*     */       } 
/*     */     } else {
/* 167 */       k = this._map.getNoEntryKey();
/*     */     } 
/* 169 */     long v = this._map.remove(k);
/* 173 */     if (v == this._map.getNoEntryValue())
/* 174 */       return null; 
/* 176 */     return wrapValue(v);
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<Long, Long>> entrySet() {
/* 187 */     return new AbstractSet<Map.Entry<Long, Long>>() {
/*     */         public int size() {
/* 189 */           return TLongLongMapDecorator.this._map.size();
/*     */         }
/*     */         
/*     */         public boolean isEmpty() {
/* 193 */           return TLongLongMapDecorator.this.isEmpty();
/*     */         }
/*     */         
/*     */         public boolean contains(Object o) {
/* 197 */           if (o instanceof Map.Entry) {
/* 198 */             Object k = ((Map.Entry)o).getKey();
/* 199 */             Object v = ((Map.Entry)o).getValue();
/* 200 */             return (TLongLongMapDecorator.this.containsKey(k) && TLongLongMapDecorator.this.get(k).equals(v));
/*     */           } 
/* 203 */           return false;
/*     */         }
/*     */         
/*     */         public Iterator<Map.Entry<Long, Long>> iterator() {
/* 208 */           return new Iterator<Map.Entry<Long, Long>>() {
/* 209 */               private final TLongLongIterator it = TLongLongMapDecorator.this._map.iterator();
/*     */               
/*     */               public Map.Entry<Long, Long> next() {
/* 212 */                 this.it.advance();
/* 213 */                 long ik = this.it.key();
/* 214 */                 final Long key = (ik == TLongLongMapDecorator.this._map.getNoEntryKey()) ? null : TLongLongMapDecorator.this.wrapKey(ik);
/* 215 */                 long iv = this.it.value();
/* 216 */                 final Long v = (iv == TLongLongMapDecorator.this._map.getNoEntryValue()) ? null : TLongLongMapDecorator.this.wrapValue(iv);
/* 217 */                 return new Map.Entry<Long, Long>() {
/* 218 */                     private Long val = v;
/*     */                     
/*     */                     public boolean equals(Object o) {
/* 221 */                       return (o instanceof Map.Entry && ((Map.Entry)o).getKey().equals(key) && ((Map.Entry)o).getValue().equals(this.val));
/*     */                     }
/*     */                     
/*     */                     public Long getKey() {
/* 227 */                       return key;
/*     */                     }
/*     */                     
/*     */                     public Long getValue() {
/* 231 */                       return this.val;
/*     */                     }
/*     */                     
/*     */                     public int hashCode() {
/* 235 */                       return key.hashCode() + this.val.hashCode();
/*     */                     }
/*     */                     
/*     */                     public Long setValue(Long value) {
/* 239 */                       this.val = value;
/* 240 */                       return TLongLongMapDecorator.this.put(key, value);
/*     */                     }
/*     */                   };
/*     */               }
/*     */               
/*     */               public boolean hasNext() {
/* 246 */                 return this.it.hasNext();
/*     */               }
/*     */               
/*     */               public void remove() {
/* 250 */                 this.it.remove();
/*     */               }
/*     */             };
/*     */         }
/*     */         
/*     */         public boolean add(Map.Entry<Long, Long> o) {
/* 256 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public boolean remove(Object o) {
/* 260 */           boolean modified = false;
/* 261 */           if (contains(o)) {
/* 263 */             Long key = (Long)((Map.Entry)o).getKey();
/* 264 */             TLongLongMapDecorator.this._map.remove(TLongLongMapDecorator.this.unwrapKey(key));
/* 265 */             modified = true;
/*     */           } 
/* 267 */           return modified;
/*     */         }
/*     */         
/*     */         public boolean addAll(Collection<? extends Map.Entry<Long, Long>> c) {
/* 271 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void clear() {
/* 275 */           TLongLongMapDecorator.this.clear();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object val) {
/* 288 */     return (val instanceof Long && this._map.containsValue(unwrapValue(val)));
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 299 */     if (key == null)
/* 299 */       return this._map.containsKey(this._map.getNoEntryKey()); 
/* 300 */     return (key instanceof Long && this._map.containsKey(unwrapKey(key)));
/*     */   }
/*     */   
/*     */   public int size() {
/* 310 */     return this._map.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 320 */     return (size() == 0);
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends Long, ? extends Long> map) {
/* 332 */     Iterator<? extends Map.Entry<? extends Long, ? extends Long>> it = map.entrySet().iterator();
/* 334 */     for (int i = map.size(); i-- > 0; ) {
/* 335 */       Map.Entry<? extends Long, ? extends Long> e = it.next();
/* 336 */       put(e.getKey(), e.getValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Long wrapKey(long k) {
/* 348 */     return Long.valueOf(k);
/*     */   }
/*     */   
/*     */   protected long unwrapKey(Object key) {
/* 359 */     return ((Long)key).longValue();
/*     */   }
/*     */   
/*     */   protected Long wrapValue(long k) {
/* 370 */     return Long.valueOf(k);
/*     */   }
/*     */   
/*     */   protected long unwrapValue(Object value) {
/* 381 */     return ((Long)value).longValue();
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 390 */     in.readByte();
/* 393 */     this._map = (TLongLongMap)in.readObject();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 400 */     out.writeByte(0);
/* 403 */     out.writeObject(this._map);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\decorator\TLongLongMapDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */