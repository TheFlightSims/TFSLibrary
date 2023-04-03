/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import org.apache.commons.collections.Factory;
/*     */ import org.apache.commons.collections.FunctorException;
/*     */ 
/*     */ public class PrototypeFactory {
/*     */   public static Factory getInstance(Object prototype) {
/*  59 */     if (prototype == null)
/*  60 */       return ConstantFactory.NULL_INSTANCE; 
/*     */     try {
/*  63 */       Method method = prototype.getClass().getMethod("clone", (Class[])null);
/*  64 */       return new PrototypeCloneFactory(prototype, method);
/*  66 */     } catch (NoSuchMethodException ex) {
/*     */       try {
/*  68 */         prototype.getClass().getConstructor(new Class[] { prototype.getClass() });
/*  69 */         return new InstantiateFactory(prototype.getClass(), new Class[] { prototype.getClass() }, new Object[] { prototype });
/*  74 */       } catch (NoSuchMethodException ex2) {
/*  75 */         if (prototype instanceof Serializable)
/*  76 */           return new PrototypeSerializationFactory((Serializable)prototype); 
/*  80 */         throw new IllegalArgumentException("The prototype must be cloneable via a public clone method");
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   static class PrototypeCloneFactory implements Factory, Serializable {
/*     */     private static final long serialVersionUID = 5604271422565175555L;
/*     */     
/*     */     private final Object iPrototype;
/*     */     
/*     */     private transient Method iCloneMethod;
/*     */     
/*     */     private PrototypeCloneFactory(Object prototype, Method method) {
/* 111 */       this.iPrototype = prototype;
/* 112 */       this.iCloneMethod = method;
/*     */     }
/*     */     
/*     */     private void findCloneMethod() {
/*     */       try {
/* 120 */         this.iCloneMethod = this.iPrototype.getClass().getMethod("clone", (Class[])null);
/* 122 */       } catch (NoSuchMethodException ex) {
/* 123 */         throw new IllegalArgumentException("PrototypeCloneFactory: The clone method must exist and be public ");
/*     */       } 
/*     */     }
/*     */     
/*     */     public Object create() {
/* 134 */       if (this.iCloneMethod == null)
/* 135 */         findCloneMethod(); 
/*     */       try {
/* 139 */         return this.iCloneMethod.invoke(this.iPrototype, (Object[])null);
/* 141 */       } catch (IllegalAccessException ex) {
/* 142 */         throw new FunctorException("PrototypeCloneFactory: Clone method must be public", ex);
/* 143 */       } catch (InvocationTargetException ex) {
/* 144 */         throw new FunctorException("PrototypeCloneFactory: Clone method threw an exception", ex);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class PrototypeSerializationFactory implements Factory, Serializable {
/*     */     private static final long serialVersionUID = -8704966966139178833L;
/*     */     
/*     */     private final Serializable iPrototype;
/*     */     
/*     */     private PrototypeSerializationFactory(Serializable prototype) {
/* 167 */       this.iPrototype = prototype;
/*     */     }
/*     */     
/*     */     public Object create() {
/* 176 */       ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
/* 177 */       ByteArrayInputStream bais = null;
/*     */       try {
/* 179 */         ObjectOutputStream out = new ObjectOutputStream(baos);
/* 180 */         out.writeObject(this.iPrototype);
/* 182 */         bais = new ByteArrayInputStream(baos.toByteArray());
/* 183 */         ObjectInputStream in = new ObjectInputStream(bais);
/* 184 */         return in.readObject();
/* 186 */       } catch (ClassNotFoundException ex) {
/* 187 */         throw new FunctorException(ex);
/* 188 */       } catch (IOException ex) {
/* 189 */         throw new FunctorException(ex);
/*     */       } finally {
/*     */         try {
/* 192 */           if (bais != null)
/* 193 */             bais.close(); 
/* 195 */         } catch (IOException ex) {}
/*     */         try {
/* 199 */           if (baos != null)
/* 200 */             baos.close(); 
/* 202 */         } catch (IOException ex) {}
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\PrototypeFactory.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */