/*     */ package gnu.trove.decorator;
/*     */ 
/*     */ import gnu.trove.iterator.TByteShortIterator;
/*     */ import gnu.trove.map.TByteShortMap;
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
/*     */ public class TByteShortMapDecorator extends AbstractMap<Byte, Short> implements Map<Byte, Short>, Externalizable, Cloneable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   protected TByteShortMap _map;
/*     */   
/*     */   public TByteShortMapDecorator() {}
/*     */   
/*     */   public TByteShortMapDecorator(TByteShortMap map) {
/*  72 */     this._map = map;
/*     */   }
/*     */   
/*     */   public TByteShortMap getMap() {
/*  82 */     return this._map;
/*     */   }
/*     */   
/*     */   public Short put(Byte key, Short value) {
/*     */     byte k;
/*     */     short v;
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
/* 107 */     short retval = this._map.put(k, v);
/* 108 */     if (retval == this._map.getNoEntryValue())
/* 109 */       return null; 
/* 111 */     return wrapValue(retval);
/*     */   }
/*     */   
/*     */   public Short get(Object key) {
/*     */     byte k;
/* 123 */     if (key != null) {
/* 124 */       if (key instanceof Byte) {
/* 125 */         k = unwrapKey(key);
/*     */       } else {
/* 127 */         return null;
/*     */       } 
/*     */     } else {
/* 130 */       k = this._map.getNoEntryKey();
/*     */     } 
/* 132 */     short v = this._map.get(k);
/* 136 */     if (v == this._map.getNoEntryValue())
/* 137 */       return null; 
/* 139 */     return wrapValue(v);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 148 */     this._map.clear();
/*     */   }
/*     */   
/*     */   public Short remove(Object key) {
/*     */     byte k;
/* 160 */     if (key != null) {
/* 161 */       if (key instanceof Byte) {
/* 162 */         k = unwrapKey(key);
/*     */       } else {
/* 164 */         return null;
/*     */       } 
/*     */     } else {
/* 167 */       k = this._map.getNoEntryKey();
/*     */     } 
/* 169 */     short v = this._map.remove(k);
/* 173 */     if (v == this._map.getNoEntryValue())
/* 174 */       return null; 
/* 176 */     return wrapValue(v);
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<Byte, Short>> entrySet() {
/* 187 */     return new AbstractSet<Map.Entry<Byte, Short>>() {
/*     */         public int size() {
/* 189 */           return TByteShortMapDecorator.this._map.size();
/*     */         }
/*     */         
/*     */         public boolean isEmpty() {
/* 193 */           return TByteShortMapDecorator.this.isEmpty();
/*     */         }
/*     */         
/*     */         public boolean contains(Object o) {
/* 197 */           if (o instanceof Map.Entry) {
/* 198 */             Object k = ((Map.Entry)o).getKey();
/* 199 */             Object v = ((Map.Entry)o).getValue();
/* 200 */             return (TByteShortMapDecorator.this.containsKey(k) && TByteShortMapDecorator.this.get(k).equals(v));
/*     */           } 
/* 203 */           return false;
/*     */         }
/*     */         
/*     */         public Iterator<Map.Entry<Byte, Short>> iterator() {
/* 208 */           return new Iterator<Map.Entry<Byte, Short>>() {
/* 209 */               private final TByteShortIterator it = TByteShortMapDecorator.this._map.iterator();
/*     */               
/*     */               public Map.Entry<Byte, Short> next() {
/* 212 */                 this.it.advance();
/* 213 */                 byte ik = this.it.key();
/* 214 */                 final Byte key = (ik == TByteShortMapDecorator.this._map.getNoEntryKey()) ? null : TByteShortMapDecorator.this.wrapKey(ik);
/* 215 */                 short iv = this.it.value();
/* 216 */                 final Short v = (iv == TByteShortMapDecorator.this._map.getNoEntryValue()) ? null : TByteShortMapDecorator.this.wrapValue(iv);
/* 217 */                 return new Map.Entry<Byte, Short>() {
/* 218 */                     private Short val = v;
/*     */                     
/*     */                     public boolean equals(Object o) {
/* 221 */                       return (o instanceof Map.Entry && ((Map.Entry)o).getKey().equals(key) && ((Map.Entry)o).getValue().equals(this.val));
/*     */                     }
/*     */                     
/*     */                     public Byte getKey() {
/* 227 */                       return key;
/*     */                     }
/*     */                     
/*     */                     public Short getValue() {
/* 231 */                       return this.val;
/*     */                     }
/*     */                     
/*     */                     public int hashCode() {
/* 235 */                       return key.hashCode() + this.val.hashCode();
/*     */                     }
/*     */                     
/*     */                     public Short setValue(Short value) {
/* 239 */                       this.val = value;
/* 240 */                       return TByteShortMapDecorator.this.put(key, value);
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
/*     */         public boolean add(Map.Entry<Byte, Short> o) {
/* 256 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public boolean remove(Object o) {
/* 260 */           boolean modified = false;
/* 261 */           if (contains(o)) {
/* 263 */             Byte key = (Byte)((Map.Entry)o).getKey();
/* 264 */             TByteShortMapDecorator.this._map.remove(TByteShortMapDecorator.this.unwrapKey(key));
/* 265 */             modified = true;
/*     */           } 
/* 267 */           return modified;
/*     */         }
/*     */         
/*     */         public boolean addAll(Collection<? extends Map.Entry<Byte, Short>> c) {
/* 271 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void clear() {
/* 275 */           TByteShortMapDecorator.this.clear();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object val) {
/* 288 */     return (val instanceof Short && this._map.containsValue(unwrapValue(val)));
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 299 */     if (key == null)
/* 299 */       return this._map.containsKey(this._map.getNoEntryKey()); 
/* 300 */     return (key instanceof Byte && this._map.containsKey(unwrapKey(key)));
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
/*     */   public void putAll(Map<? extends Byte, ? extends Short> map) {
/* 332 */     Iterator<? extends Map.Entry<? extends Byte, ? extends Short>> it = map.entrySet().iterator();
/* 334 */     for (int i = map.size(); i-- > 0; ) {
/* 335 */       Map.Entry<? extends Byte, ? extends Short> e = it.next();
/* 336 */       put(e.getKey(), e.getValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Byte wrapKey(byte k) {
/* 348 */     return Byte.valueOf(k);
/*     */   }
/*     */   
/*     */   protected byte unwrapKey(Object key) {
/* 359 */     return ((Byte)key).byteValue();
/*     */   }
/*     */   
/*     */   protected Short wrapValue(short k) {
/* 370 */     return Short.valueOf(k);
/*     */   }
/*     */   
/*     */   protected short unwrapValue(Object value) {
/* 381 */     return ((Short)value).shortValue();
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 390 */     in.readByte();
/* 393 */     this._map = (TByteShortMap)in.readObject();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 400 */     out.writeByte(0);
/* 403 */     out.writeObject(this._map);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\decorator\TByteShortMapDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */