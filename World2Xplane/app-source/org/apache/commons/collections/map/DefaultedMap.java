/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.Factory;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ import org.apache.commons.collections.functors.ConstantTransformer;
/*     */ import org.apache.commons.collections.functors.FactoryTransformer;
/*     */ 
/*     */ public class DefaultedMap extends AbstractMapDecorator implements Map, Serializable {
/*     */   private static final long serialVersionUID = 19698628745827L;
/*     */   
/*     */   protected final Object value;
/*     */   
/*     */   public static Map decorate(Map map, Object defaultValue) {
/*  87 */     if (defaultValue instanceof Transformer)
/*  88 */       defaultValue = ConstantTransformer.getInstance(defaultValue); 
/*  90 */     return new DefaultedMap(map, defaultValue);
/*     */   }
/*     */   
/*     */   public static Map decorate(Map map, Factory factory) {
/* 104 */     if (factory == null)
/* 105 */       throw new IllegalArgumentException("Factory must not be null"); 
/* 107 */     return new DefaultedMap(map, FactoryTransformer.getInstance(factory));
/*     */   }
/*     */   
/*     */   public static Map decorate(Map map, Transformer factory) {
/* 122 */     if (factory == null)
/* 123 */       throw new IllegalArgumentException("Transformer must not be null"); 
/* 125 */     return new DefaultedMap(map, factory);
/*     */   }
/*     */   
/*     */   public DefaultedMap(Object defaultValue) {
/* 139 */     super(new HashMap());
/* 140 */     if (defaultValue instanceof Transformer)
/* 141 */       defaultValue = ConstantTransformer.getInstance(defaultValue); 
/* 143 */     this.value = defaultValue;
/*     */   }
/*     */   
/*     */   protected DefaultedMap(Map map, Object value) {
/* 154 */     super(map);
/* 155 */     this.value = value;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 166 */     out.defaultWriteObject();
/* 167 */     out.writeObject(this.map);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 178 */     in.defaultReadObject();
/* 179 */     this.map = (Map)in.readObject();
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/* 185 */     if (!this.map.containsKey(key)) {
/* 186 */       if (this.value instanceof Transformer)
/* 187 */         return ((Transformer)this.value).transform(key); 
/* 189 */       return this.value;
/*     */     } 
/* 191 */     return this.map.get(key);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\DefaultedMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */