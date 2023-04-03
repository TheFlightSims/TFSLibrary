/*    */ package scala.util;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ClassTag;
/*    */ 
/*    */ public final class Marshal$ {
/*    */   public static final Marshal$ MODULE$;
/*    */   
/*    */   private Marshal$() {
/* 20 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> byte[] dump(Object o, ClassTag t) {
/* 25 */     ByteArrayOutputStream ba = new ByteArrayOutputStream(512);
/* 26 */     ObjectOutputStream out = new ObjectOutputStream(ba);
/* 27 */     out.writeObject(t);
/* 28 */     out.writeObject(o);
/* 29 */     out.close();
/* 30 */     return ba.toByteArray();
/*    */   }
/*    */   
/*    */   public <A> A load(byte[] buffer, ClassTag expected) throws IOException, ClassCastException, ClassNotFoundException {
/* 37 */     ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buffer));
/* 38 */     ClassTag found = (ClassTag)in.readObject();
/*    */     try {
/* 40 */       found.runtimeClass().asSubclass(expected.runtimeClass());
/* 41 */       return (A)in.readObject();
/*    */     } catch (ClassCastException classCastException) {
/* 44 */       in.close();
/* 45 */       throw new ClassCastException((
/*    */           
/* 47 */           new StringBuilder()).append("type mismatch;\n found   : ").append(found).append("\n required: ").append(expected).toString());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\Marshal$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */