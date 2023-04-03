/*     */ package gnu.trove.decorator;
/*     */ 
/*     */ import gnu.trove.iterator.TByteCharIterator;
/*     */ import gnu.trove.map.TByteCharMap;
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
/*     */ public class TByteCharMapDecorator extends AbstractMap<Byte, Character> implements Map<Byte, Character>, Externalizable, Cloneable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   protected TByteCharMap _map;
/*     */   
/*     */   public TByteCharMapDecorator() {}
/*     */   
/*     */   public TByteCharMapDecorator(TByteCharMap map) {
/*  72 */     this._map = map;
/*     */   }
/*     */   
/*     */   public TByteCharMap getMap() {
/*  82 */     return this._map;
/*     */   }
/*     */   
/*     */   public Character put(Byte key, Character value) {
/*     */     byte k;
/*     */     char v;
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
/* 107 */     char retval = this._map.put(k, v);
/* 108 */     if (retval == this._map.getNoEntryValue())
/* 109 */       return null; 
/* 111 */     return wrapValue(retval);
/*     */   }
/*     */   
/*     */   public Character get(Object key) {
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
/* 132 */     char v = this._map.get(k);
/* 136 */     if (v == this._map.getNoEntryValue())
/* 137 */       return null; 
/* 139 */     return wrapValue(v);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 148 */     this._map.clear();
/*     */   }
/*     */   
/*     */   public Character remove(Object key) {
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
/* 169 */     char v = this._map.remove(k);
/* 173 */     if (v == this._map.getNoEntryValue())
/* 174 */       return null; 
/* 176 */     return wrapValue(v);
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<Byte, Character>> entrySet() {
/* 187 */     return new AbstractSet<Map.Entry<Byte, Character>>() {
/*     */         public int size() {
/* 189 */           return TByteCharMapDecorator.this._map.size();
/*     */         }
/*     */         
/*     */         public boolean isEmpty() {
/* 193 */           return TByteCharMapDecorator.this.isEmpty();
/*     */         }
/*     */         
/*     */         public boolean contains(Object o) {
/* 197 */           if (o instanceof Map.Entry) {
/* 198 */             Object k = ((Map.Entry)o).getKey();
/* 199 */             Object v = ((Map.Entry)o).getValue();
/* 200 */             return (TByteCharMapDecorator.this.containsKey(k) && TByteCharMapDecorator.this.get(k).equals(v));
/*     */           } 
/* 203 */           return false;
/*     */         }
/*     */         
/*     */         public Iterator<Map.Entry<Byte, Character>> iterator() {
/* 208 */           return new Iterator<Map.Entry<Byte, Character>>() {
/* 209 */               private final TByteCharIterator it = TByteCharMapDecorator.this._map.iterator();
/*     */               
/*     */               public Map.Entry<Byte, Character> next() {
/* 212 */                 this.it.advance();
/* 213 */                 byte ik = this.it.key();
/* 214 */                 final Byte key = (ik == TByteCharMapDecorator.this._map.getNoEntryKey()) ? null : TByteCharMapDecorator.this.wrapKey(ik);
/* 215 */                 char iv = this.it.value();
/* 216 */                 final Character v = (iv == TByteCharMapDecorator.this._map.getNoEntryValue()) ? null : TByteCharMapDecorator.this.wrapValue(iv);
/* 217 */                 return new Map.Entry<Byte, Character>() {
/* 218 */                     private Character val = v;
/*     */                     
/*     */                     public boolean equals(Object o) {
/* 221 */                       return (o instanceof Map.Entry && ((Map.Entry)o).getKey().equals(key) && ((Map.Entry)o).getValue().equals(this.val));
/*     */                     }
/*     */                     
/*     */                     public Byte getKey() {
/* 227 */                       return key;
/*     */                     }
/*     */                     
/*     */                     public Character getValue() {
/* 231 */                       return this.val;
/*     */                     }
/*     */                     
/*     */                     public int hashCode() {
/* 235 */                       return key.hashCode() + this.val.hashCode();
/*     */                     }
/*     */                     
/*     */                     public Character setValue(Character value) {
/* 239 */                       this.val = value;
/* 240 */                       return TByteCharMapDecorator.this.put(key, value);
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
/*     */         public boolean add(Map.Entry<Byte, Character> o) {
/* 256 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public boolean remove(Object o) {
/* 260 */           boolean modified = false;
/* 261 */           if (contains(o)) {
/* 263 */             Byte key = (Byte)((Map.Entry)o).getKey();
/* 264 */             TByteCharMapDecorator.this._map.remove(TByteCharMapDecorator.this.unwrapKey(key));
/* 265 */             modified = true;
/*     */           } 
/* 267 */           return modified;
/*     */         }
/*     */         
/*     */         public boolean addAll(Collection<? extends Map.Entry<Byte, Character>> c) {
/* 271 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void clear() {
/* 275 */           TByteCharMapDecorator.this.clear();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object val) {
/* 288 */     return (val instanceof Character && this._map.containsValue(unwrapValue(val)));
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
/*     */   public void putAll(Map<? extends Byte, ? extends Character> map) {
/* 332 */     Iterator<? extends Map.Entry<? extends Byte, ? extends Character>> it = map.entrySet().iterator();
/* 334 */     for (int i = map.size(); i-- > 0; ) {
/* 335 */       Map.Entry<? extends Byte, ? extends Character> e = it.next();
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
/*     */   protected Character wrapValue(char k) {
/* 370 */     return Character.valueOf(k);
/*     */   }
/*     */   
/*     */   protected char unwrapValue(Object value) {
/* 381 */     return ((Character)value).charValue();
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 390 */     in.readByte();
/* 393 */     this._map = (TByteCharMap)in.readObject();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 400 */     out.writeByte(0);
/* 403 */     out.writeObject(this._map);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\decorator\TByteCharMapDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */