/*     */ package com.sun.media.jai.rmi;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class HashSetState extends SerializableStateImpl {
/*     */   public static Class[] getSupportedClasses() {
/*  38 */     return new Class[] { HashSet.class };
/*     */   }
/*     */   
/*     */   public HashSetState(Class c, Object o, RenderingHints h) {
/*  50 */     super(c, o, h);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  58 */     HashSet set = (HashSet)this.theObject;
/*  60 */     HashSet serializableSet = new HashSet();
/*  63 */     if (set != null && !set.isEmpty()) {
/*  65 */       Iterator iterator = set.iterator();
/*  68 */       while (iterator.hasNext()) {
/*  69 */         Object object = iterator.next();
/*  70 */         Object serializableObject = getSerializableForm(object);
/*  71 */         serializableSet.add(serializableObject);
/*     */       } 
/*     */     } 
/*  76 */     out.writeObject(serializableSet);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*  85 */     HashSet serializableSet = (HashSet)in.readObject();
/*  88 */     HashSet set = new HashSet();
/*  91 */     if (serializableSet.isEmpty()) {
/*  92 */       this.theObject = set;
/*     */       return;
/*     */     } 
/*  96 */     Iterator iterator = serializableSet.iterator();
/*  99 */     while (iterator.hasNext()) {
/* 101 */       Object serializableObject = iterator.next();
/* 102 */       Object object = getDeserializedFrom(serializableObject);
/* 105 */       set.add(object);
/*     */     } 
/* 108 */     this.theObject = set;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\HashSetState.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */