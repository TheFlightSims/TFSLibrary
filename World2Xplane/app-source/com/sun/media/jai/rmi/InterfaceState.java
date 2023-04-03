/*    */ package com.sun.media.jai.rmi;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ import java.lang.reflect.InvocationHandler;
/*    */ import java.lang.reflect.Proxy;
/*    */ import javax.media.jai.JAI;
/*    */ import javax.media.jai.remote.SerializableState;
/*    */ import javax.media.jai.remote.Serializer;
/*    */ 
/*    */ public class InterfaceState implements SerializableState {
/*    */   private transient Object theObject;
/*    */   
/*    */   private transient Serializer[] theSerializers;
/*    */   
/*    */   private transient RenderingHints hints;
/*    */   
/*    */   public InterfaceState(Object o, Serializer[] serializers, RenderingHints h) {
/* 43 */     if (o == null || serializers == null)
/* 44 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 46 */     this.theObject = o;
/* 47 */     this.theSerializers = serializers;
/* 48 */     this.hints = (h == null) ? null : (RenderingHints)h.clone();
/*    */   }
/*    */   
/*    */   public Object getObject() {
/* 52 */     return this.theObject;
/*    */   }
/*    */   
/*    */   public Class getObjectClass() {
/* 56 */     return this.theObject.getClass();
/*    */   }
/*    */   
/*    */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 66 */     int numSerializers = this.theSerializers.length;
/* 67 */     out.writeInt(numSerializers);
/* 68 */     for (int i = 0; i < numSerializers; i++) {
/* 69 */       Serializer s = this.theSerializers[i];
/* 70 */       out.writeObject(s.getSupportedClass());
/* 71 */       out.writeObject(s.getState(this.theObject, this.hints));
/*    */     } 
/*    */   }
/*    */   
/*    */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 83 */     int numInterfaces = in.readInt();
/* 84 */     Class[] interfaces = new Class[numInterfaces];
/* 85 */     SerializableState[] implementations = new SerializableState[numInterfaces];
/* 87 */     for (int i = 0; i < numInterfaces; i++) {
/* 88 */       interfaces[i] = (Class)in.readObject();
/* 89 */       implementations[i] = (SerializableState)in.readObject();
/*    */     } 
/* 92 */     InvocationHandler handler = new InterfaceHandler(interfaces, implementations);
/* 95 */     this.theObject = Proxy.newProxyInstance(JAI.class.getClassLoader(), interfaces, handler);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\InterfaceState.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */