/*     */ package gnu.trove.decorator;
/*     */ 
/*     */ import gnu.trove.iterator.TDoubleDoubleIterator;
/*     */ import gnu.trove.map.TDoubleDoubleMap;
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
/*     */ public class TDoubleDoubleMapDecorator extends AbstractMap<Double, Double> implements Map<Double, Double>, Externalizable, Cloneable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   protected TDoubleDoubleMap _map;
/*     */   
/*     */   public TDoubleDoubleMapDecorator() {}
/*     */   
/*     */   public TDoubleDoubleMapDecorator(TDoubleDoubleMap map) {
/*  72 */     this._map = map;
/*     */   }
/*     */   
/*     */   public TDoubleDoubleMap getMap() {
/*  82 */     return this._map;
/*     */   }
/*     */   
/*     */   public Double put(Double key, Double value) {
/*     */     double k, v;
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
/* 107 */     double retval = this._map.put(k, v);
/* 108 */     if (retval == this._map.getNoEntryValue())
/* 109 */       return null; 
/* 111 */     return wrapValue(retval);
/*     */   }
/*     */   
/*     */   public Double get(Object key) {
/*     */     double k;
/* 123 */     if (key != null) {
/* 124 */       if (key instanceof Double) {
/* 125 */         k = unwrapKey(key);
/*     */       } else {
/* 127 */         return null;
/*     */       } 
/*     */     } else {
/* 130 */       k = this._map.getNoEntryKey();
/*     */     } 
/* 132 */     double v = this._map.get(k);
/* 136 */     if (v == this._map.getNoEntryValue())
/* 137 */       return null; 
/* 139 */     return wrapValue(v);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 148 */     this._map.clear();
/*     */   }
/*     */   
/*     */   public Double remove(Object key) {
/*     */     double k;
/* 160 */     if (key != null) {
/* 161 */       if (key instanceof Double) {
/* 162 */         k = unwrapKey(key);
/*     */       } else {
/* 164 */         return null;
/*     */       } 
/*     */     } else {
/* 167 */       k = this._map.getNoEntryKey();
/*     */     } 
/* 169 */     double v = this._map.remove(k);
/* 173 */     if (v == this._map.getNoEntryValue())
/* 174 */       return null; 
/* 176 */     return wrapValue(v);
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<Double, Double>> entrySet() {
/* 187 */     return new AbstractSet<Map.Entry<Double, Double>>() {
/*     */         public int size() {
/* 189 */           return TDoubleDoubleMapDecorator.this._map.size();
/*     */         }
/*     */         
/*     */         public boolean isEmpty() {
/* 193 */           return TDoubleDoubleMapDecorator.this.isEmpty();
/*     */         }
/*     */         
/*     */         public boolean contains(Object o) {
/* 197 */           if (o instanceof Map.Entry) {
/* 198 */             Object k = ((Map.Entry)o).getKey();
/* 199 */             Object v = ((Map.Entry)o).getValue();
/* 200 */             return (TDoubleDoubleMapDecorator.this.containsKey(k) && TDoubleDoubleMapDecorator.this.get(k).equals(v));
/*     */           } 
/* 203 */           return false;
/*     */         }
/*     */         
/*     */         public Iterator<Map.Entry<Double, Double>> iterator() {
/* 208 */           return new Iterator<Map.Entry<Double, Double>>() {
/* 209 */               private final TDoubleDoubleIterator it = TDoubleDoubleMapDecorator.this._map.iterator();
/*     */               
/*     */               public Map.Entry<Double, Double> next() {
/* 212 */                 this.it.advance();
/* 213 */                 double ik = this.it.key();
/* 214 */                 final Double key = (ik == TDoubleDoubleMapDecorator.this._map.getNoEntryKey()) ? null : TDoubleDoubleMapDecorator.this.wrapKey(ik);
/* 215 */                 double iv = this.it.value();
/* 216 */                 final Double v = (iv == TDoubleDoubleMapDecorator.this._map.getNoEntryValue()) ? null : TDoubleDoubleMapDecorator.this.wrapValue(iv);
/* 217 */                 return new Map.Entry<Double, Double>() {
/* 218 */                     private Double val = v;
/*     */                     
/*     */                     public boolean equals(Object o) {
/* 221 */                       return (o instanceof Map.Entry && ((Map.Entry)o).getKey().equals(key) && ((Map.Entry)o).getValue().equals(this.val));
/*     */                     }
/*     */                     
/*     */                     public Double getKey() {
/* 227 */                       return key;
/*     */                     }
/*     */                     
/*     */                     public Double getValue() {
/* 231 */                       return this.val;
/*     */                     }
/*     */                     
/*     */                     public int hashCode() {
/* 235 */                       return key.hashCode() + this.val.hashCode();
/*     */                     }
/*     */                     
/*     */                     public Double setValue(Double value) {
/* 239 */                       this.val = value;
/* 240 */                       return TDoubleDoubleMapDecorator.this.put(key, value);
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
/*     */         public boolean add(Map.Entry<Double, Double> o) {
/* 256 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public boolean remove(Object o) {
/* 260 */           boolean modified = false;
/* 261 */           if (contains(o)) {
/* 263 */             Double key = (Double)((Map.Entry)o).getKey();
/* 264 */             TDoubleDoubleMapDecorator.this._map.remove(TDoubleDoubleMapDecorator.this.unwrapKey(key));
/* 265 */             modified = true;
/*     */           } 
/* 267 */           return modified;
/*     */         }
/*     */         
/*     */         public boolean addAll(Collection<? extends Map.Entry<Double, Double>> c) {
/* 271 */           throw new UnsupportedOperationException();
/*     */         }
/*     */         
/*     */         public void clear() {
/* 275 */           TDoubleDoubleMapDecorator.this.clear();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object val) {
/* 288 */     return (val instanceof Double && this._map.containsValue(unwrapValue(val)));
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 299 */     if (key == null)
/* 299 */       return this._map.containsKey(this._map.getNoEntryKey()); 
/* 300 */     return (key instanceof Double && this._map.containsKey(unwrapKey(key)));
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
/*     */   public void putAll(Map<? extends Double, ? extends Double> map) {
/* 332 */     Iterator<? extends Map.Entry<? extends Double, ? extends Double>> it = map.entrySet().iterator();
/* 334 */     for (int i = map.size(); i-- > 0; ) {
/* 335 */       Map.Entry<? extends Double, ? extends Double> e = it.next();
/* 336 */       put(e.getKey(), e.getValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Double wrapKey(double k) {
/* 348 */     return Double.valueOf(k);
/*     */   }
/*     */   
/*     */   protected double unwrapKey(Object key) {
/* 359 */     return ((Double)key).doubleValue();
/*     */   }
/*     */   
/*     */   protected Double wrapValue(double k) {
/* 370 */     return Double.valueOf(k);
/*     */   }
/*     */   
/*     */   protected double unwrapValue(Object value) {
/* 381 */     return ((Double)value).doubleValue();
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 390 */     in.readByte();
/* 393 */     this._map = (TDoubleDoubleMap)in.readObject();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 400 */     out.writeByte(0);
/* 403 */     out.writeObject(this._map);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\decorator\TDoubleDoubleMapDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */