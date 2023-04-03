/*     */ package com.sun.media.jai.rmi;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.io.IOException;
/*     */ import java.io.NotSerializableException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.Vector;
/*     */ import javax.media.jai.JAI;
/*     */ 
/*     */ public class RenderingHintsProxy implements Serializable {
/*     */   private transient RenderingHints hints;
/*     */   
/*  47 */   private static final Class[] KEY_CLASSES = new Class[] { RenderingHints.class, JAI.class };
/*     */   
/*  56 */   private static final Object[] SUPPRESSED_KEYS = new Object[] { JAI.KEY_OPERATION_REGISTRY, JAI.KEY_TILE_CACHE, JAI.KEY_RETRY_INTERVAL, JAI.KEY_NUM_RETRIES, JAI.KEY_NEGOTIATION_PREFERENCES };
/*     */   
/*  63 */   private static SoftReference suppressedKeyReference = null;
/*     */   
/*  69 */   private static SoftReference hintTableReference = null;
/*     */   
/*     */   public RenderingHintsProxy(RenderingHints source) {
/*  78 */     this.hints = source;
/*     */   }
/*     */   
/*     */   private static class HintElement implements Serializable {
/*     */     private static final int TYPE_OBJECT = 1;
/*     */     
/*     */     private static final int TYPE_FIELD = 2;
/*     */     
/*     */     private int type;
/*     */     
/*     */     private Object obj;
/*     */     
/*     */     private String className;
/*     */     
/*     */     private String fieldName;
/*     */     
/*     */     public HintElement(Object obj) throws NotSerializableException {
/* 103 */       if (!(obj instanceof Serializable))
/* 104 */         throw new NotSerializableException(); 
/* 106 */       this.type = 1;
/* 107 */       this.obj = obj;
/*     */     }
/*     */     
/*     */     public HintElement(Class cls, Field fld) {
/* 112 */       this.type = 2;
/* 113 */       this.className = cls.getName();
/* 114 */       this.fieldName = fld.getName();
/*     */     }
/*     */     
/*     */     public Object getObject() {
/* 119 */       Object elt = null;
/* 121 */       if (this.type == 1) {
/* 122 */         elt = this.obj;
/* 123 */       } else if (this.type == 2) {
/*     */         try {
/* 125 */           Class cls = Class.forName(this.className);
/* 126 */           Field fld = cls.getField(this.fieldName);
/* 127 */           elt = fld.get(null);
/* 128 */         } catch (Exception e) {}
/*     */       } 
/* 133 */       return elt;
/*     */     }
/*     */   }
/*     */   
/*     */   public RenderingHints getRenderingHints() {
/* 143 */     return this.hints;
/*     */   }
/*     */   
/*     */   private static synchronized Vector getSuppressedKeys() {
/* 148 */     Vector suppressedKeys = null;
/* 150 */     if (SUPPRESSED_KEYS != null) {
/* 152 */       suppressedKeys = (suppressedKeyReference != null) ? suppressedKeyReference.get() : null;
/* 155 */       if (suppressedKeys == null) {
/* 157 */         int numSuppressedKeys = SUPPRESSED_KEYS.length;
/* 160 */         suppressedKeys = new Vector(numSuppressedKeys);
/* 161 */         for (int i = 0; i < numSuppressedKeys; i++)
/* 162 */           suppressedKeys.add(SUPPRESSED_KEYS[i]); 
/* 166 */         suppressedKeyReference = new SoftReference(suppressedKeys);
/*     */       } 
/*     */     } 
/* 170 */     return suppressedKeys;
/*     */   }
/*     */   
/*     */   private static synchronized Hashtable getHintTable() {
/* 179 */     Hashtable table = (hintTableReference != null) ? hintTableReference.get() : null;
/* 182 */     if (table == null) {
/* 184 */       table = new Hashtable();
/* 186 */       for (int i = 0; i < KEY_CLASSES.length; i++) {
/* 188 */         Class cls = KEY_CLASSES[i];
/* 191 */         Field[] fields = cls.getFields();
/* 195 */         for (int j = 0; j < fields.length; j++) {
/* 196 */           Field fld = fields[j];
/* 197 */           int modifiers = fld.getModifiers();
/* 198 */           if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers))
/*     */             try {
/* 201 */               Object fieldValue = fld.get(null);
/* 202 */               table.put(fieldValue, new HintElement(cls, fld));
/* 204 */             } catch (Exception e) {} 
/*     */         } 
/*     */       } 
/* 212 */       hintTableReference = new SoftReference(table);
/*     */     } 
/* 215 */     return table;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 232 */     Hashtable table = new Hashtable();
/* 235 */     if (this.hints != null && !this.hints.isEmpty()) {
/* 237 */       Set keySet = this.hints.keySet();
/* 240 */       if (!keySet.isEmpty()) {
/* 242 */         Iterator keyIterator = keySet.iterator();
/* 245 */         Hashtable hintTable = getHintTable();
/* 248 */         Vector suppressedKeys = getSuppressedKeys();
/* 251 */         while (keyIterator.hasNext()) {
/* 253 */           Object key = keyIterator.next();
/* 256 */           if (suppressedKeys != null && suppressedKeys.indexOf(key) != -1)
/*     */             continue; 
/* 262 */           HintElement keyElement = (HintElement)hintTable.get(key);
/* 266 */           if (keyElement == null)
/*     */             continue; 
/* 271 */           Object value = this.hints.get(key);
/* 274 */           HintElement valueElement = null;
/*     */           try {
/* 277 */             valueElement = new HintElement(value);
/* 278 */           } catch (NotSerializableException nse) {
/* 282 */             valueElement = (HintElement)hintTable.get(value);
/*     */           } 
/* 286 */           if (valueElement != null)
/* 287 */             table.put(keyElement, valueElement); 
/*     */         } 
/*     */       } 
/*     */     } 
/* 294 */     out.writeObject(table);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 303 */     Hashtable table = (Hashtable)in.readObject();
/* 306 */     this.hints = new RenderingHints(null);
/* 309 */     if (table.isEmpty())
/*     */       return; 
/* 316 */     Enumeration keys = table.keys();
/* 319 */     while (keys.hasMoreElements()) {
/* 321 */       HintElement keyElement = keys.nextElement();
/* 324 */       Object key = keyElement.getObject();
/* 327 */       HintElement valueElement = (HintElement)table.get(keyElement);
/* 330 */       Object value = valueElement.getObject();
/* 333 */       this.hints.put(key, value);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\RenderingHintsProxy.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */