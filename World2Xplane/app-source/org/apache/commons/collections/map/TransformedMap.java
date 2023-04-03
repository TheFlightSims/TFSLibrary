/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ 
/*     */ public class TransformedMap extends AbstractInputCheckedMapDecorator implements Serializable {
/*     */   private static final long serialVersionUID = 7023152376788900464L;
/*     */   
/*     */   protected final Transformer keyTransformer;
/*     */   
/*     */   protected final Transformer valueTransformer;
/*     */   
/*     */   public static Map decorate(Map map, Transformer keyTransformer, Transformer valueTransformer) {
/*  74 */     return new TransformedMap(map, keyTransformer, valueTransformer);
/*     */   }
/*     */   
/*     */   public static Map decorateTransform(Map map, Transformer keyTransformer, Transformer valueTransformer) {
/*  92 */     TransformedMap decorated = new TransformedMap(map, keyTransformer, valueTransformer);
/*  93 */     if (map.size() > 0) {
/*  94 */       Map transformed = decorated.transformMap(map);
/*  95 */       decorated.clear();
/*  96 */       decorated.getMap().putAll(transformed);
/*     */     } 
/*  98 */     return decorated;
/*     */   }
/*     */   
/*     */   protected TransformedMap(Map map, Transformer keyTransformer, Transformer valueTransformer) {
/* 114 */     super(map);
/* 115 */     this.keyTransformer = keyTransformer;
/* 116 */     this.valueTransformer = valueTransformer;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 128 */     out.defaultWriteObject();
/* 129 */     out.writeObject(this.map);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 141 */     in.defaultReadObject();
/* 142 */     this.map = (Map)in.readObject();
/*     */   }
/*     */   
/*     */   protected Object transformKey(Object object) {
/* 155 */     if (this.keyTransformer == null)
/* 156 */       return object; 
/* 158 */     return this.keyTransformer.transform(object);
/*     */   }
/*     */   
/*     */   protected Object transformValue(Object object) {
/* 170 */     if (this.valueTransformer == null)
/* 171 */       return object; 
/* 173 */     return this.valueTransformer.transform(object);
/*     */   }
/*     */   
/*     */   protected Map transformMap(Map map) {
/* 185 */     if (map.isEmpty())
/* 186 */       return map; 
/* 188 */     Map result = new LinkedMap(map.size());
/* 189 */     for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
/* 190 */       Map.Entry entry = it.next();
/* 191 */       result.put(transformKey(entry.getKey()), transformValue(entry.getValue()));
/*     */     } 
/* 193 */     return result;
/*     */   }
/*     */   
/*     */   protected Object checkSetValue(Object value) {
/* 204 */     return this.valueTransformer.transform(value);
/*     */   }
/*     */   
/*     */   protected boolean isSetValueChecking() {
/* 214 */     return (this.valueTransformer != null);
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 219 */     key = transformKey(key);
/* 220 */     value = transformValue(value);
/* 221 */     return getMap().put(key, value);
/*     */   }
/*     */   
/*     */   public void putAll(Map mapToCopy) {
/* 225 */     mapToCopy = transformMap(mapToCopy);
/* 226 */     getMap().putAll(mapToCopy);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\TransformedMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */