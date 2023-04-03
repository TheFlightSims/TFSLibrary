/*     */ package org.apache.commons.lang;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class SerializationUtils {
/*     */   public static Object clone(Serializable object) {
/*  81 */     return deserialize(serialize(object));
/*     */   }
/*     */   
/*     */   public static void serialize(Serializable obj, OutputStream outputStream) {
/* 102 */     if (outputStream == null)
/* 103 */       throw new IllegalArgumentException("The OutputStream must not be null"); 
/* 105 */     ObjectOutputStream out = null;
/*     */     try {
/* 108 */       out = new ObjectOutputStream(outputStream);
/* 109 */       out.writeObject(obj);
/* 111 */     } catch (IOException ex) {
/* 112 */       throw new SerializationException(ex);
/*     */     } finally {
/*     */       try {
/* 115 */         if (out != null)
/* 116 */           out.close(); 
/* 118 */       } catch (IOException ex) {}
/*     */     } 
/*     */   }
/*     */   
/*     */   public static byte[] serialize(Serializable obj) {
/* 133 */     ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
/* 134 */     serialize(obj, baos);
/* 135 */     return baos.toByteArray();
/*     */   }
/*     */   
/*     */   public static Object deserialize(InputStream inputStream) {
/* 156 */     if (inputStream == null)
/* 157 */       throw new IllegalArgumentException("The InputStream must not be null"); 
/* 159 */     ObjectInputStream in = null;
/*     */     try {
/* 162 */       in = new ObjectInputStream(inputStream);
/* 163 */       return in.readObject();
/* 165 */     } catch (ClassNotFoundException ex) {
/* 166 */       throw new SerializationException(ex);
/* 167 */     } catch (IOException ex) {
/* 168 */       throw new SerializationException(ex);
/*     */     } finally {
/*     */       try {
/* 171 */         if (in != null)
/* 172 */           in.close(); 
/* 174 */       } catch (IOException ex) {}
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object deserialize(byte[] objectData) {
/* 189 */     if (objectData == null)
/* 190 */       throw new IllegalArgumentException("The byte[] must not be null"); 
/* 192 */     ByteArrayInputStream bais = new ByteArrayInputStream(objectData);
/* 193 */     return deserialize(bais);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\SerializationUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */