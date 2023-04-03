/*     */ package gnu.trove.decorator;
/*     */ 
/*     */ import gnu.trove.iterator.TCharCharIterator;
/*     */ import gnu.trove.map.TCharCharMap;
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
/*     */ public class TCharCharMapDecorator extends AbstractMap<Character, Character> implements Map<Character, Character>, Externalizable, Cloneable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   protected TCharCharMap _map;
/*     */   
/*     */   public TCharCharMapDecorator() {}
/*     */   
/*     */   public TCharCharMapDecorator(TCharCharMap map) {
/*  72 */     this._map = map;
/*     */   }
/*     */   
/*     */   public TCharCharMap getMap() {
/*  82 */     return this._map;
/*     */   }
/*     */   
/*     */   public Character put(Character key, Character value) {
/*     */     char k, v;
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
/* 169 */     char v = this._map.remove(k);
/* 173 */     if (v == this._map.getNoEntryValue())
/* 174 */       return null; 
/* 176 */     return wrapValue(v);
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<Character, Character>> entrySet() {
/* 187 */     return new AbstractSet<Map.Entry<Character, Character>>() {
/*     */         public int size() {
/* 189 */           return TCharCharMapDecorator.this._map.size();
/*     */         }
/*     */         
/*     */         public boolean isEmpty() {
/* 193 */           return TCharCharMapDecorator.this.isEmpty();
/*     */         }
/*     */         
/*     */         public boolean contains(Object o) {
/* 197 */           if (o instanceof Map.Entry) {
/* 198 */             Object k = ((Map.Entry)o).getKey();
/* 199 */             Object v = ((Map.Entry)o).getValue();
/* 200 */             return (TCharCharMapDecorator.this.containsKey(k) && TCharCharMapDecorator.this.get(k).equals(v));
/*     */           } 
/* 203 */           return false;
/*     */         }
/*     */         
/*     */         public Iterator<Map.Entry<Character, Character>> iterator() {
/* 208 */           return new Iterator<Map.Entry<Character, Character>>() {
/* 209 */               private final TCharCharIterator it = TCharCharMapDecorator.this._map.iterator();
/*     */               
/*     */               public Map.Entry<Character, Character> next() {
/* 212 */                 this.it.advance();
/* 213 */                 char ik = this.it.key();
/* 214 */                 final Character key = (ik == TCharCharMapDecorator.this._map.getNoEntryKey()) ? null : TCharCharMapDecorator.this.wrapKey(ik);
/* 215 */                 char iv = this.it.value();
/* 216 */                 final Character v = (iv == TCharCharMapDecorator.this._map.getNoEntryValue()) ? null : TCharCharMapDecorator.this.wrapValue(iv);
/* 217 */                 return new Map.Entry<Character, Character>() {
/* 218 */                     private Character val = v;
/*     */                     
/*     */                     public boolean equals(Object o) {
/* 221 */                       return (o instanceof Map.Entry && ((Map.Entry)o).getKey().equals(key) && ((Map.Entry)o).getValue().equals(this.val));
/*     */                     }
/*     */                     
/*     */                     public Character getKey() {
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
/* 240 */                       return TCharCharMapDecorator.this.put(key, value);
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
/*     */         public boolean add(Map.Entry<Character, Character> o) {
/* 256 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public boolean remove(Object o) {
/* 260 */           boolean modified = false;
/* 261 */           if (contains(o)) {
/* 263 */             Character key = (Character)((Map.Entry)o).getKey();
/* 264 */             TCharCharMapDecorator.this._map.remove(TCharCharMapDecorator.this.unwrapKey(key));
/* 265 */             modified = true;
/*     */           } 
/* 267 */           return modified;
/*     */         }
/*     */         
/*     */         public boolean addAll(Collection<? extends Map.Entry<Character, Character>> c) {
/* 271 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void clear() {
/* 275 */           TCharCharMapDecorator.this.clear();
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
/*     */   public void putAll(Map<? extends Character, ? extends Character> map) {
/* 332 */     Iterator<? extends Map.Entry<? extends Character, ? extends Character>> it = map.entrySet().iterator();
/* 334 */     for (int i = map.size(); i-- > 0; ) {
/* 335 */       Map.Entry<? extends Character, ? extends Character> e = it.next();
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
/* 393 */     this._map = (TCharCharMap)in.readObject();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 400 */     out.writeByte(0);
/* 403 */     out.writeObject(this._map);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\decorator\TCharCharMapDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */