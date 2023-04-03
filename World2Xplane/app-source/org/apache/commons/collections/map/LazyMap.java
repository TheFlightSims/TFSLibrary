/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.Factory;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ import org.apache.commons.collections.functors.FactoryTransformer;
/*     */ 
/*     */ public class LazyMap extends AbstractMapDecorator implements Map, Serializable {
/*     */   private static final long serialVersionUID = 7990956402564206740L;
/*     */   
/*     */   protected final Transformer factory;
/*     */   
/*     */   public static Map decorate(Map map, Factory factory) {
/*  83 */     return new LazyMap(map, factory);
/*     */   }
/*     */   
/*     */   public static Map decorate(Map map, Transformer factory) {
/*  94 */     return new LazyMap(map, factory);
/*     */   }
/*     */   
/*     */   protected LazyMap(Map map, Factory factory) {
/* 106 */     super(map);
/* 107 */     if (factory == null)
/* 108 */       throw new IllegalArgumentException("Factory must not be null"); 
/* 110 */     this.factory = FactoryTransformer.getInstance(factory);
/*     */   }
/*     */   
/*     */   protected LazyMap(Map map, Transformer factory) {
/* 121 */     super(map);
/* 122 */     if (factory == null)
/* 123 */       throw new IllegalArgumentException("Factory must not be null"); 
/* 125 */     this.factory = factory;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 137 */     out.defaultWriteObject();
/* 138 */     out.writeObject(this.map);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 150 */     in.defaultReadObject();
/* 151 */     this.map = (Map)in.readObject();
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/* 157 */     if (!this.map.containsKey(key)) {
/* 158 */       Object value = this.factory.transform(key);
/* 159 */       this.map.put(key, value);
/* 160 */       return value;
/*     */     } 
/* 162 */     return this.map.get(key);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\LazyMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */