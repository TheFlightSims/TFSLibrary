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
/*     */ import javax.media.jai.remote.SerializableState;
/*     */ import javax.media.jai.remote.SerializerFactory;
/*     */ 
/*     */ public class RenderingHintsState extends SerializableStateImpl {
/*     */   public static Class[] getSupportedClasses() {
/*  44 */     return new Class[] { RenderingHints.class };
/*     */   }
/*     */   
/*  52 */   private static final Class[] KEY_CLASSES = new Class[] { RenderingHints.class, JAI.class };
/*     */   
/*  61 */   private static final Object[] SUPPRESSED_KEYS = new Object[] { JAI.KEY_OPERATION_REGISTRY, JAI.KEY_TILE_CACHE, JAI.KEY_RETRY_INTERVAL, JAI.KEY_NUM_RETRIES, JAI.KEY_NEGOTIATION_PREFERENCES };
/*     */   
/*  68 */   private static SoftReference suppressedKeyReference = null;
/*     */   
/*  74 */   private static SoftReference hintTableReference = null;
/*     */   
/*     */   public RenderingHintsState(Class c, Object o, RenderingHints h) {
/*  83 */     super(c, o, h);
/*     */   }
/*     */   
/*     */   static class HintElement implements Serializable {
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
/* 113 */       if (!(obj instanceof Serializable))
/* 114 */         throw new NotSerializableException(); 
/* 116 */       this.type = 1;
/* 117 */       this.obj = obj;
/*     */     }
/*     */     
/*     */     public HintElement(Class cls, Field fld) {
/* 122 */       this.type = 2;
/* 123 */       this.className = cls.getName();
/* 124 */       this.fieldName = fld.getName();
/*     */     }
/*     */     
/*     */     public Object getObject() {
/* 129 */       Object elt = null;
/* 131 */       if (this.type == 1) {
/* 132 */         elt = this.obj;
/* 133 */       } else if (this.type == 2) {
/*     */         try {
/* 135 */           Class cls = Class.forName(this.className);
/* 136 */           Field fld = cls.getField(this.fieldName);
/* 137 */           elt = fld.get(null);
/* 138 */         } catch (Exception e) {}
/*     */       } 
/* 143 */       return elt;
/*     */     }
/*     */   }
/*     */   
/*     */   private static synchronized Vector getSuppressedKeys() {
/* 149 */     Vector suppressedKeys = null;
/* 151 */     if (SUPPRESSED_KEYS != null) {
/* 153 */       suppressedKeys = (suppressedKeyReference != null) ? suppressedKeyReference.get() : null;
/* 156 */       if (suppressedKeys == null) {
/* 158 */         int numSuppressedKeys = SUPPRESSED_KEYS.length;
/* 161 */         suppressedKeys = new Vector(numSuppressedKeys);
/* 162 */         for (int i = 0; i < numSuppressedKeys; i++)
/* 163 */           suppressedKeys.add(SUPPRESSED_KEYS[i]); 
/* 167 */         suppressedKeyReference = new SoftReference(suppressedKeys);
/*     */       } 
/*     */     } 
/* 171 */     return suppressedKeys;
/*     */   }
/*     */   
/*     */   static synchronized Hashtable getHintTable() {
/* 180 */     Hashtable table = (hintTableReference != null) ? hintTableReference.get() : null;
/* 183 */     if (table == null) {
/* 185 */       table = new Hashtable();
/* 187 */       for (int i = 0; i < KEY_CLASSES.length; i++) {
/* 189 */         Class cls = KEY_CLASSES[i];
/* 192 */         Field[] fields = cls.getFields();
/* 196 */         for (int j = 0; j < fields.length; j++) {
/* 197 */           Field fld = fields[j];
/* 198 */           int modifiers = fld.getModifiers();
/* 199 */           if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers))
/*     */             try {
/* 202 */               Object fieldValue = fld.get(null);
/* 203 */               table.put(fieldValue, new HintElement(cls, fld));
/* 205 */             } catch (Exception e) {} 
/*     */         } 
/*     */       } 
/* 213 */       hintTableReference = new SoftReference(table);
/*     */     } 
/* 216 */     return table;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 231 */     RenderingHints hints = (RenderingHints)this.theObject;
/* 234 */     Hashtable table = new Hashtable();
/* 237 */     if (hints != null && !hints.isEmpty()) {
/* 239 */       Set keySet = hints.keySet();
/* 242 */       if (!keySet.isEmpty()) {
/* 244 */         Iterator keyIterator = keySet.iterator();
/* 247 */         Hashtable hintTable = getHintTable();
/* 250 */         Vector suppressedKeys = getSuppressedKeys();
/* 253 */         while (keyIterator.hasNext()) {
/* 255 */           Object key = keyIterator.next();
/* 258 */           if (suppressedKeys != null && suppressedKeys.indexOf(key) != -1)
/*     */             continue; 
/* 264 */           Object keyElement = SerializerFactory.getState(key, null);
/* 268 */           if (keyElement == null)
/*     */             continue; 
/* 273 */           Object value = hints.get(key);
/* 276 */           HintElement valueElement = null;
/*     */           try {
/* 279 */             valueElement = new HintElement(value);
/* 280 */           } catch (NotSerializableException nse) {
/* 284 */             valueElement = (HintElement)hintTable.get(value);
/*     */           } 
/* 288 */           if (valueElement != null)
/* 289 */             table.put(keyElement, valueElement); 
/*     */         } 
/*     */       } 
/*     */     } 
/* 296 */     out.writeObject(table);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 305 */     Hashtable table = (Hashtable)in.readObject();
/* 308 */     RenderingHints hints = new RenderingHints(null);
/* 310 */     this.theObject = hints;
/* 313 */     if (table.isEmpty())
/*     */       return; 
/* 320 */     Enumeration keys = table.keys();
/* 323 */     while (keys.hasMoreElements()) {
/* 325 */       SerializableState keyElement = keys.nextElement();
/* 328 */       Object key = keyElement.getObject();
/* 331 */       HintElement valueElement = (HintElement)table.get(keyElement);
/* 334 */       Object value = valueElement.getObject();
/* 337 */       hints.put(key, value);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\RenderingHintsState.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */