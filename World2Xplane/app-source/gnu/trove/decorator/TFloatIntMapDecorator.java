/*     */ package gnu.trove.decorator;
/*     */ 
/*     */ import gnu.trove.iterator.TFloatIntIterator;
/*     */ import gnu.trove.map.TFloatIntMap;
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
/*     */ public class TFloatIntMapDecorator extends AbstractMap<Float, Integer> implements Map<Float, Integer>, Externalizable, Cloneable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   protected TFloatIntMap _map;
/*     */   
/*     */   public TFloatIntMapDecorator() {}
/*     */   
/*     */   public TFloatIntMapDecorator(TFloatIntMap map) {
/*  72 */     this._map = map;
/*     */   }
/*     */   
/*     */   public TFloatIntMap getMap() {
/*  82 */     return this._map;
/*     */   }
/*     */   
/*     */   public Integer put(Float key, Integer value) {
/*     */     float k;
/*     */     int v;
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
/* 107 */     int retval = this._map.put(k, v);
/* 108 */     if (retval == this._map.getNoEntryValue())
/* 109 */       return null; 
/* 111 */     return wrapValue(retval);
/*     */   }
/*     */   
/*     */   public Integer get(Object key) {
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
/* 132 */     int v = this._map.get(k);
/* 136 */     if (v == this._map.getNoEntryValue())
/* 137 */       return null; 
/* 139 */     return wrapValue(v);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 148 */     this._map.clear();
/*     */   }
/*     */   
/*     */   public Integer remove(Object key) {
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
/* 169 */     int v = this._map.remove(k);
/* 173 */     if (v == this._map.getNoEntryValue())
/* 174 */       return null; 
/* 176 */     return wrapValue(v);
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<Float, Integer>> entrySet() {
/* 187 */     return new AbstractSet<Map.Entry<Float, Integer>>() {
/*     */         public int size() {
/* 189 */           return TFloatIntMapDecorator.this._map.size();
/*     */         }
/*     */         
/*     */         public boolean isEmpty() {
/* 193 */           return TFloatIntMapDecorator.this.isEmpty();
/*     */         }
/*     */         
/*     */         public boolean contains(Object o) {
/* 197 */           if (o instanceof Map.Entry) {
/* 198 */             Object k = ((Map.Entry)o).getKey();
/* 199 */             Object v = ((Map.Entry)o).getValue();
/* 200 */             return (TFloatIntMapDecorator.this.containsKey(k) && TFloatIntMapDecorator.this.get(k).equals(v));
/*     */           } 
/* 203 */           return false;
/*     */         }
/*     */         
/*     */         public Iterator<Map.Entry<Float, Integer>> iterator() {
/* 208 */           return new Iterator<Map.Entry<Float, Integer>>() {
/* 209 */               private final TFloatIntIterator it = TFloatIntMapDecorator.this._map.iterator();
/*     */               
/*     */               public Map.Entry<Float, Integer> next() {
/* 212 */                 this.it.advance();
/* 213 */                 float ik = this.it.key();
/* 214 */                 final Float key = (ik == TFloatIntMapDecorator.this._map.getNoEntryKey()) ? null : TFloatIntMapDecorator.this.wrapKey(ik);
/* 215 */                 int iv = this.it.value();
/* 216 */                 final Integer v = (iv == TFloatIntMapDecorator.this._map.getNoEntryValue()) ? null : TFloatIntMapDecorator.this.wrapValue(iv);
/* 217 */                 return new Map.Entry<Float, Integer>() {
/* 218 */                     private Integer val = v;
/*     */                     
/*     */                     public boolean equals(Object o) {
/* 221 */                       return (o instanceof Map.Entry && ((Map.Entry)o).getKey().equals(key) && ((Map.Entry)o).getValue().equals(this.val));
/*     */                     }
/*     */                     
/*     */                     public Float getKey() {
/* 227 */                       return key;
/*     */                     }
/*     */                     
/*     */                     public Integer getValue() {
/* 231 */                       return this.val;
/*     */                     }
/*     */                     
/*     */                     public int hashCode() {
/* 235 */                       return key.hashCode() + this.val.hashCode();
/*     */                     }
/*     */                     
/*     */                     public Integer setValue(Integer value) {
/* 239 */                       this.val = value;
/* 240 */                       return TFloatIntMapDecorator.this.put(key, value);
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
/*     */         public boolean add(Map.Entry<Float, Integer> o) {
/* 256 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public boolean remove(Object o) {
/* 260 */           boolean modified = false;
/* 261 */           if (contains(o)) {
/* 263 */             Float key = (Float)((Map.Entry)o).getKey();
/* 264 */             TFloatIntMapDecorator.this._map.remove(TFloatIntMapDecorator.this.unwrapKey(key));
/* 265 */             modified = true;
/*     */           } 
/* 267 */           return modified;
/*     */         }
/*     */         
/*     */         public boolean addAll(Collection<? extends Map.Entry<Float, Integer>> c) {
/* 271 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void clear() {
/* 275 */           TFloatIntMapDecorator.this.clear();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object val) {
/* 288 */     return (val instanceof Integer && this._map.containsValue(unwrapValue(val)));
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
/*     */   public void putAll(Map<? extends Float, ? extends Integer> map) {
/* 332 */     Iterator<? extends Map.Entry<? extends Float, ? extends Integer>> it = map.entrySet().iterator();
/* 334 */     for (int i = map.size(); i-- > 0; ) {
/* 335 */       Map.Entry<? extends Float, ? extends Integer> e = it.next();
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
/*     */   protected Integer wrapValue(int k) {
/* 370 */     return Integer.valueOf(k);
/*     */   }
/*     */   
/*     */   protected int unwrapValue(Object value) {
/* 381 */     return ((Integer)value).intValue();
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 390 */     in.readByte();
/* 393 */     this._map = (TFloatIntMap)in.readObject();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 400 */     out.writeByte(0);
/* 403 */     out.writeObject(this._map);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\decorator\TFloatIntMapDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */