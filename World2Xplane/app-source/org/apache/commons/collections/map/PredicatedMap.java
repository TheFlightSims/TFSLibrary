/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ 
/*     */ public class PredicatedMap extends AbstractInputCheckedMapDecorator implements Serializable {
/*     */   private static final long serialVersionUID = 7412622456128415156L;
/*     */   
/*     */   protected final Predicate keyPredicate;
/*     */   
/*     */   protected final Predicate valuePredicate;
/*     */   
/*     */   public static Map decorate(Map map, Predicate keyPredicate, Predicate valuePredicate) {
/*  77 */     return new PredicatedMap(map, keyPredicate, valuePredicate);
/*     */   }
/*     */   
/*     */   protected PredicatedMap(Map map, Predicate keyPredicate, Predicate valuePredicate) {
/*  90 */     super(map);
/*  91 */     this.keyPredicate = keyPredicate;
/*  92 */     this.valuePredicate = valuePredicate;
/*  94 */     Iterator it = map.entrySet().iterator();
/*  95 */     while (it.hasNext()) {
/*  96 */       Map.Entry entry = it.next();
/*  97 */       Object key = entry.getKey();
/*  98 */       Object value = entry.getValue();
/*  99 */       validate(key, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 112 */     out.defaultWriteObject();
/* 113 */     out.writeObject(this.map);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 125 */     in.defaultReadObject();
/* 126 */     this.map = (Map)in.readObject();
/*     */   }
/*     */   
/*     */   protected void validate(Object key, Object value) {
/* 138 */     if (this.keyPredicate != null && !this.keyPredicate.evaluate(key))
/* 139 */       throw new IllegalArgumentException("Cannot add key - Predicate rejected it"); 
/* 141 */     if (this.valuePredicate != null && !this.valuePredicate.evaluate(value))
/* 142 */       throw new IllegalArgumentException("Cannot add value - Predicate rejected it"); 
/*     */   }
/*     */   
/*     */   protected Object checkSetValue(Object value) {
/* 154 */     if (!this.valuePredicate.evaluate(value))
/* 155 */       throw new IllegalArgumentException("Cannot set value - Predicate rejected it"); 
/* 157 */     return value;
/*     */   }
/*     */   
/*     */   protected boolean isSetValueChecking() {
/* 167 */     return (this.valuePredicate != null);
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 172 */     validate(key, value);
/* 173 */     return this.map.put(key, value);
/*     */   }
/*     */   
/*     */   public void putAll(Map mapToCopy) {
/* 177 */     Iterator it = mapToCopy.entrySet().iterator();
/* 178 */     while (it.hasNext()) {
/* 179 */       Map.Entry entry = it.next();
/* 180 */       Object key = entry.getKey();
/* 181 */       Object value = entry.getValue();
/* 182 */       validate(key, value);
/*     */     } 
/* 184 */     this.map.putAll(mapToCopy);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\PredicatedMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */