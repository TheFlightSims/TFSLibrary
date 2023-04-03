/*     */ package com.sun.media.jai.rmi;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class HashtableState extends SerializableStateImpl {
/*     */   public static Class[] getSupportedClasses() {
/*  40 */     return new Class[] { Hashtable.class };
/*     */   }
/*     */   
/*     */   public HashtableState(Class c, Object o, RenderingHints h) {
/*  52 */     super(c, o, h);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  60 */     Hashtable table = (Hashtable)this.theObject;
/*  62 */     Hashtable serializableTable = new Hashtable();
/*  65 */     if (table != null && !table.isEmpty()) {
/*  67 */       Set keySet = table.keySet();
/*  70 */       if (!keySet.isEmpty()) {
/*  72 */         Iterator keyIterator = keySet.iterator();
/*  75 */         while (keyIterator.hasNext()) {
/*  77 */           Object key = keyIterator.next();
/*  78 */           Object serializableKey = getSerializableForm(key);
/*  80 */           if (serializableKey == null)
/*     */             continue; 
/*  84 */           Object value = table.get(key);
/*  86 */           Object serializableValue = getSerializableForm(value);
/*  88 */           if (serializableValue == null)
/*     */             continue; 
/*  91 */           serializableTable.put(serializableKey, serializableValue);
/*     */         } 
/*     */       } 
/*     */     } 
/*  97 */     out.writeObject(serializableTable);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 106 */     Hashtable serializableTable = (Hashtable)in.readObject();
/* 109 */     Hashtable table = new Hashtable();
/* 110 */     this.theObject = table;
/* 113 */     if (serializableTable.isEmpty())
/*     */       return; 
/* 118 */     Enumeration keys = serializableTable.keys();
/* 121 */     while (keys.hasMoreElements()) {
/* 123 */       Object serializableKey = keys.nextElement();
/* 124 */       Object key = getDeserializedFrom(serializableKey);
/* 127 */       Object serializableValue = serializableTable.get(serializableKey);
/* 128 */       Object value = getDeserializedFrom(serializableValue);
/* 131 */       table.put(key, value);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\HashtableState.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */