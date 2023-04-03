/*     */ package gnu.trove.decorator;
/*     */ 
/*     */ import gnu.trove.iterator.TFloatFloatIterator;
/*     */ import gnu.trove.map.TFloatFloatMap;
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
/*     */ public class TFloatFloatMapDecorator extends AbstractMap<Float, Float> implements Map<Float, Float>, Externalizable, Cloneable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   protected TFloatFloatMap _map;
/*     */   
/*     */   public TFloatFloatMapDecorator() {}
/*     */   
/*     */   public TFloatFloatMapDecorator(TFloatFloatMap map) {
/*  72 */     this._map = map;
/*     */   }
/*     */   
/*     */   public TFloatFloatMap getMap() {
/*  82 */     return this._map;
/*     */   }
/*     */   
/*     */   public Float put(Float key, Float value) {
/*     */     float k, v;
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
/*     */     float k;
/* 123 */     if (key != null) {
/* 124 */       if (key instanceof Float) {
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
/*     */     float k;
/* 160 */     if (key != null) {
/* 161 */       if (key instanceof Float) {
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
/*     */   public Set<Map.Entry<Float, Float>> entrySet() {
/* 187 */     return new AbstractSet<Map.Entry<Float, Float>>() {
/*     */         public int size() {
/* 189 */           return TFloatFloatMapDecorator.this._map.size();
/*     */         }
/*     */         
/*     */         public boolean isEmpty() {
/* 193 */           return TFloatFloatMapDecorator.this.isEmpty();
/*     */         }
/*     */         
/*     */         public boolean contains(Object o) {
/* 197 */           if (o instanceof Map.Entry) {
/* 198 */             Object k = ((Map.Entry)o).getKey();
/* 199 */             Object v = ((Map.Entry)o).getValue();
/* 200 */             return (TFloatFloatMapDecorator.this.containsKey(k) && TFloatFloatMapDecorator.this.get(k).equals(v));
/*     */           } 
/* 203 */           return false;
/*     */         }
/*     */         
/*     */         public Iterator<Map.Entry<Float, Float>> iterator() {
/* 208 */           return new Iterator<Map.Entry<Float, Float>>() {
/* 209 */               private final TFloatFloatIterator it = TFloatFloatMapDecorator.this._map.iterator();
/*     */               
/*     */               public Map.Entry<Float, Float> next() {
/* 212 */                 this.it.advance();
/* 213 */                 float ik = this.it.key();
/* 214 */                 final Float key = (ik == TFloatFloatMapDecorator.this._map.getNoEntryKey()) ? null : TFloatFloatMapDecorator.this.wrapKey(ik);
/* 215 */                 float iv = this.it.value();
/* 216 */                 final Float v = (iv == TFloatFloatMapDecorator.this._map.getNoEntryValue()) ? null : TFloatFloatMapDecorator.this.wrapValue(iv);
/* 217 */                 return new Map.Entry<Float, Float>() {
/* 218 */                     private Float val = v;
/*     */                     
/*     */                     public boolean equals(Object o) {
/* 221 */                       return (o instanceof Map.Entry && ((Map.Entry)o).getKey().equals(key) && ((Map.Entry)o).getValue().equals(this.val));
/*     */                     }
/*     */                     
/*     */                     public Float getKey() {
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
/* 240 */                       return TFloatFloatMapDecorator.this.put(key, value);
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
/*     */         public boolean add(Map.Entry<Float, Float> o) {
/* 256 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public boolean remove(Object o) {
/* 260 */           boolean modified = false;
/* 261 */           if (contains(o)) {
/* 263 */             Float key = (Float)((Map.Entry)o).getKey();
/* 264 */             TFloatFloatMapDecorator.this._map.remove(TFloatFloatMapDecorator.this.unwrapKey(key));
/* 265 */             modified = true;
/*     */           } 
/* 267 */           return modified;
/*     */         }
/*     */         
/*     */         public boolean addAll(Collection<? extends Map.Entry<Float, Float>> c) {
/* 271 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void clear() {
/* 275 */           TFloatFloatMapDecorator.this.clear();
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
/* 300 */     return (key instanceof Float && this._map.containsKey(unwrapKey(key)));
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
/*     */   public void putAll(Map<? extends Float, ? extends Float> map) {
/* 332 */     Iterator<? extends Map.Entry<? extends Float, ? extends Float>> it = map.entrySet().iterator();
/* 334 */     for (int i = map.size(); i-- > 0; ) {
/* 335 */       Map.Entry<? extends Float, ? extends Float> e = it.next();
/* 336 */       put(e.getKey(), e.getValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Float wrapKey(float k) {
/* 348 */     return Float.valueOf(k);
/*     */   }
/*     */   
/*     */   protected float unwrapKey(Object key) {
/* 359 */     return ((Float)key).floatValue();
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
/* 393 */     this._map = (TFloatFloatMap)in.readObject();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 400 */     out.writeByte(0);
/* 403 */     out.writeObject(this._map);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\decorator\TFloatFloatMapDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */