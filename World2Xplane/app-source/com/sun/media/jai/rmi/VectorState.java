/*    */ package com.sun.media.jai.rmi;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ import java.util.Iterator;
/*    */ import java.util.Vector;
/*    */ 
/*    */ public class VectorState extends SerializableStateImpl {
/*    */   public static Class[] getSupportedClasses() {
/* 37 */     return new Class[] { Vector.class };
/*    */   }
/*    */   
/*    */   public VectorState(Class c, Object o, RenderingHints h) {
/* 49 */     super(c, o, h);
/*    */   }
/*    */   
/*    */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 57 */     Vector vector = (Vector)this.theObject;
/* 58 */     Vector serializableVector = new Vector();
/* 59 */     Iterator iterator = vector.iterator();
/* 62 */     while (iterator.hasNext()) {
/* 63 */       Object object = iterator.next();
/* 64 */       Object serializableObject = getSerializableForm(object);
/* 65 */       serializableVector.add(serializableObject);
/*    */     } 
/* 69 */     out.writeObject(serializableVector);
/*    */   }
/*    */   
/*    */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 78 */     Vector serializableVector = (Vector)in.readObject();
/* 81 */     Vector vector = new Vector();
/* 82 */     this.theObject = vector;
/* 85 */     if (serializableVector.isEmpty())
/*    */       return; 
/* 90 */     Iterator iterator = serializableVector.iterator();
/* 93 */     while (iterator.hasNext()) {
/* 95 */       Object serializableObject = iterator.next();
/* 96 */       Object object = getDeserializedFrom(serializableObject);
/* 99 */       vector.add(object);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\VectorState.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */