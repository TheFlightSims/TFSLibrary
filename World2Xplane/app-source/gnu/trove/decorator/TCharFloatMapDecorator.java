/*     */ package gnu.trove.decorator;
/*     */ 
/*     */ import gnu.trove.iterator.TCharFloatIterator;
/*     */ import gnu.trove.map.TCharFloatMap;
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
/*     */ public class TCharFloatMapDecorator extends AbstractMap<Character, Float> implements Map<Character, Float>, Externalizable, Cloneable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   protected TCharFloatMap _map;
/*     */   
/*     */   public TCharFloatMapDecorator() {}
/*     */   
/*     */   public TCharFloatMapDecorator(TCharFloatMap map) {
/*  72 */     this._map = map;
/*     */   }
/*     */   
/*     */   public TCharFloatMap getMap() {
/*  82 */     return this._map;
/*     */   }
/*     */   
/*     */   public Float put(Character key, Float value) {
/*     */     char k;
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
/*     */     char k;
/* 123 */     if (key != null) {
/* 124 */       if (key instanceof Character) {
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
/*     */     char k;
/* 160 */     if (key != null) {
/* 161 */       if (key instanceof Character) {
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
/*     */   public Set<Map.Entry<Character, Float>> entrySet() {
/* 187 */     return new AbstractSet<Map.Entry<Character, Float>>() {
/*     */         public int size() {
/* 189 */           return TCharFloatMapDecorator.this._map.size();
/*     */         }
/*     */         
/*     */         public boolean isEmpty() {
/* 193 */           return TCharFloatMapDecorator.this.isEmpty();
/*     */         }
/*     */         
/*     */         public boolean contains(Object o) {
/* 197 */           if (o instanceof Map.Entry) {
/* 198 */             Object k = ((Map.Entry)o).getKey();
/* 199 */             Object v = ((Map.Entry)o).getValue();
/* 200 */             return (TCharFloatMapDecorator.this.containsKey(k) && TCharFloatMapDecorator.this.get(k).equals(v));
/*     */           } 
/* 203 */           return false;
/*     */         }
/*     */         
/*     */         public Iterator<Map.Entry<Character, Float>> iterator() {
/* 208 */           return new Iterator<Map.Entry<Character, Float>>() {
/* 209 */               private final TCharFloatIterator it = TCharFloatMapDecorator.this._map.iterator();
/*     */               
/*     */               public Map.Entry<Character, Float> next() {
/* 212 */                 this.it.advance();
/* 213 */                 char ik = this.it.key();
/* 214 */                 final Character key = (ik == TCharFloatMapDecorator.this._map.getNoEntryKey()) ? null : TCharFloatMapDecorator.this.wrapKey(ik);
/* 215 */                 float iv = this.it.value();
/* 216 */                 final Float v = (iv == TCharFloatMapDecorator.this._map.getNoEntryValue()) ? null : TCharFloatMapDecorator.this.wrapValue(iv);
/* 217 */                 return new Map.Entry<Character, Float>() {
/* 218 */                     private Float val = v;
/*     */                     
/*     */                     public boolean equals(Object o) {
/* 221 */                       return (o instanceof Map.Entry && ((Map.Entry)o).getKey().equals(key) && ((Map.Entry)o).getValue().equals(this.val));
/*     */                     }
/*     */                     
/*     */                     public Character getKey() {
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
/* 240 */                       return TCharFloatMapDecorator.this.put(key, value);
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
/*     */         public boolean add(Map.Entry<Character, Float> o) {
/* 256 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public boolean remove(Object o) {
/* 260 */           boolean modified = false;
/* 261 */           if (contains(o)) {
/* 263 */             Character key = (Character)((Map.Entry)o).getKey();
/* 264 */             TCharFloatMapDecorator.this._map.remove(TCharFloatMapDecorator.this.unwrapKey(key));
/* 265 */             modified = true;
/*     */           } 
/* 267 */           return modified;
/*     */         }
/*     */         
/*     */         public boolean addAll(Collection<? extends Map.Entry<Character, Float>> c) {
/* 271 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void clear() {
/* 275 */           TCharFloatMapDecorator.this.clear();
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
/* 300 */     return (key instanceof Character && this._map.containsKey(unwrapKey(key)));
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
/*     */   public void putAll(Map<? extends Character, ? extends Float> map) {
/* 332 */     Iterator<? extends Map.Entry<? extends Character, ? extends Float>> it = map.entrySet().iterator();
/* 334 */     for (int i = map.size(); i-- > 0; ) {
/* 335 */       Map.Entry<? extends Character, ? extends Float> e = it.next();
/* 336 */       put(e.getKey(), e.getValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Character wrapKey(char k) {
/* 348 */     return Character.valueOf(k);
/*     */   }
/*     */   
/*     */   protected char unwrapKey(Object key) {
/* 359 */     return ((Character)key).charValue();
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
/* 393 */     this._map = (TCharFloatMap)in.readObject();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 400 */     out.writeByte(0);
/* 403 */     out.writeObject(this._map);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\decorator\TCharFloatMapDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */