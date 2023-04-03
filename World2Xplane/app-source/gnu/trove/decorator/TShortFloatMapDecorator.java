/*     */ package gnu.trove.decorator;
/*     */ 
/*     */ import gnu.trove.iterator.TShortFloatIterator;
/*     */ import gnu.trove.map.TShortFloatMap;
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
/*     */ public class TShortFloatMapDecorator extends AbstractMap<Short, Float> implements Map<Short, Float>, Externalizable, Cloneable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   protected TShortFloatMap _map;
/*     */   
/*     */   public TShortFloatMapDecorator() {}
/*     */   
/*     */   public TShortFloatMapDecorator(TShortFloatMap map) {
/*  72 */     this._map = map;
/*     */   }
/*     */   
/*     */   public TShortFloatMap getMap() {
/*  82 */     return this._map;
/*     */   }
/*     */   
/*     */   public Float put(Short key, Float value) {
/*     */     short k;
/*     */     float v;
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
/* 107 */     float retval = this._map.put(k, v);
/* 108 */     if (retval == this._map.getNoEntryValue())
/* 109 */       return null; 
/* 111 */     return wrapValue(retval);
/*     */   }
/*     */   
/*     */   public Float get(Object key) {
/*     */     short k;
/* 123 */     if (key != null) {
/* 124 */       if (key instanceof Short) {
/* 125 */         k = unwrapKey(key);
/*     */       } else {
/* 127 */         return null;
/*     */       } 
/*     */     } else {
/* 130 */       k = this._map.getNoEntryKey();
/*     */     } 
/* 132 */     float v = this._map.get(k);
/* 136 */     if (v == this._map.getNoEntryValue())
/* 137 */       return null; 
/* 139 */     return wrapValue(v);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 148 */     this._map.clear();
/*     */   }
/*     */   
/*     */   public Float remove(Object key) {
/*     */     short k;
/* 160 */     if (key != null) {
/* 161 */       if (key instanceof Short) {
/* 162 */         k = unwrapKey(key);
/*     */       } else {
/* 164 */         return null;
/*     */       } 
/*     */     } else {
/* 167 */       k = this._map.getNoEntryKey();
/*     */     } 
/* 169 */     float v = this._map.remove(k);
/* 173 */     if (v == this._map.getNoEntryValue())
/* 174 */       return null; 
/* 176 */     return wrapValue(v);
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<Short, Float>> entrySet() {
/* 187 */     return new AbstractSet<Map.Entry<Short, Float>>() {
/*     */         public int size() {
/* 189 */           return TShortFloatMapDecorator.this._map.size();
/*     */         }
/*     */         
/*     */         public boolean isEmpty() {
/* 193 */           return TShortFloatMapDecorator.this.isEmpty();
/*     */         }
/*     */         
/*     */         public boolean contains(Object o) {
/* 197 */           if (o instanceof Map.Entry) {
/* 198 */             Object k = ((Map.Entry)o).getKey();
/* 199 */             Object v = ((Map.Entry)o).getValue();
/* 200 */             return (TShortFloatMapDecorator.this.containsKey(k) && TShortFloatMapDecorator.this.get(k).equals(v));
/*     */           } 
/* 203 */           return false;
/*     */         }
/*     */         
/*     */         public Iterator<Map.Entry<Short, Float>> iterator() {
/* 208 */           return new Iterator<Map.Entry<Short, Float>>() {
/* 209 */               private final TShortFloatIterator it = TShortFloatMapDecorator.this._map.iterator();
/*     */               
/*     */               public Map.Entry<Short, Float> next() {
/* 212 */                 this.it.advance();
/* 213 */                 short ik = this.it.key();
/* 214 */                 final Short key = (ik == TShortFloatMapDecorator.this._map.getNoEntryKey()) ? null : TShortFloatMapDecorator.this.wrapKey(ik);
/* 215 */                 float iv = this.it.value();
/* 216 */                 final Float v = (iv == TShortFloatMapDecorator.this._map.getNoEntryValue()) ? null : TShortFloatMapDecorator.this.wrapValue(iv);
/* 217 */                 return new Map.Entry<Short, Float>() {
/* 218 */                     private Float val = v;
/*     */                     
/*     */                     public boolean equals(Object o) {
/* 221 */                       return (o instanceof Map.Entry && ((Map.Entry)o).getKey().equals(key) && ((Map.Entry)o).getValue().equals(this.val));
/*     */                     }
/*     */                     
/*     */                     public Short getKey() {
/* 227 */                       return key;
/*     */                     }
/*     */                     
/*     */                     public Float getValue() {
/* 231 */                       return this.val;
/*     */                     }
/*     */                     
/*     */                     public int hashCode() {
/* 235 */                       return key.hashCode() + this.val.hashCode();
/*     */                     }
/*     */                     
/*     */                     public Float setValue(Float value) {
/* 239 */                       this.val = value;
/* 240 */                       return TShortFloatMapDecorator.this.put(key, value);
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
/*     */         public boolean add(Map.Entry<Short, Float> o) {
/* 256 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public boolean remove(Object o) {
/* 260 */           boolean modified = false;
/* 261 */           if (contains(o)) {
/* 263 */             Short key = (Short)((Map.Entry)o).getKey();
/* 264 */             TShortFloatMapDecorator.this._map.remove(TShortFloatMapDecorator.this.unwrapKey(key));
/* 265 */             modified = true;
/*     */           } 
/* 267 */           return modified;
/*     */         }
/*     */         
/*     */         public boolean addAll(Collection<? extends Map.Entry<Short, Float>> c) {
/* 271 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void clear() {
/* 275 */           TShortFloatMapDecorator.this.clear();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object val) {
/* 288 */     return (val instanceof Float && this._map.containsValue(unwrapValue(val)));
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 299 */     if (key == null)
/* 299 */       return this._map.containsKey(this._map.getNoEntryKey()); 
/* 300 */     return (key instanceof Short && this._map.containsKey(unwrapKey(key)));
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
/*     */   public void putAll(Map<? extends Short, ? extends Float> map) {
/* 332 */     Iterator<? extends Map.Entry<? extends Short, ? extends Float>> it = map.entrySet().iterator();
/* 334 */     for (int i = map.size(); i-- > 0; ) {
/* 335 */       Map.Entry<? extends Short, ? extends Float> e = it.next();
/* 336 */       put(e.getKey(), e.getValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Short wrapKey(short k) {
/* 348 */     return Short.valueOf(k);
/*     */   }
/*     */   
/*     */   protected short unwrapKey(Object key) {
/* 359 */     return ((Short)key).shortValue();
/*     */   }
/*     */   
/*     */   protected Float wrapValue(float k) {
/* 370 */     return Float.valueOf(k);
/*     */   }
/*     */   
/*     */   protected float unwrapValue(Object value) {
/* 381 */     return ((Float)value).floatValue();
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 390 */     in.readByte();
/* 393 */     this._map = (TShortFloatMap)in.readObject();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 400 */     out.writeByte(0);
/* 403 */     out.writeObject(this._map);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\decorator\TShortFloatMapDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */