/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.io.StreamCorruptedException;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class DynaProperty implements Serializable {
/*     */   private static final int BOOLEAN_TYPE = 1;
/*     */   
/*     */   private static final int BYTE_TYPE = 2;
/*     */   
/*     */   private static final int CHAR_TYPE = 3;
/*     */   
/*     */   private static final int DOUBLE_TYPE = 4;
/*     */   
/*     */   private static final int FLOAT_TYPE = 5;
/*     */   
/*     */   private static final int INT_TYPE = 6;
/*     */   
/*     */   private static final int LONG_TYPE = 7;
/*     */   
/*     */   private static final int SHORT_TYPE = 8;
/*     */   
/*     */   protected String name;
/*     */   
/*     */   protected transient Class type;
/*     */   
/*     */   protected transient Class contentType;
/*     */   
/*     */   public DynaProperty(String name) {
/*  77 */     this(name, Object.class);
/*     */   }
/*     */   
/*     */   public DynaProperty(String name, Class type) {
/* 119 */     this.name = null;
/* 129 */     this.type = null;
/*     */     this.name = name;
/*     */     this.type = type;
/*     */     if (type != null && type.isArray())
/*     */       this.contentType = type.getComponentType(); 
/*     */   }
/*     */   
/*     */   public DynaProperty(String name, Class type, Class contentType) {
/*     */     this.name = null;
/* 129 */     this.type = null;
/*     */     this.name = name;
/*     */     this.type = type;
/*     */     this.contentType = contentType;
/*     */   }
/*     */   
/*     */   public String getName() {
/*     */     return this.name;
/*     */   }
/*     */   
/*     */   public Class getType() {
/* 143 */     return this.type;
/*     */   }
/*     */   
/*     */   public Class getContentType() {
/* 161 */     return this.contentType;
/*     */   }
/*     */   
/*     */   public boolean isIndexed() {
/* 175 */     if (this.type == null)
/* 176 */       return false; 
/* 177 */     if (this.type.isArray())
/* 178 */       return true; 
/* 179 */     if (List.class.isAssignableFrom(this.type))
/* 180 */       return true; 
/* 182 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isMapped() {
/* 196 */     if (this.type == null)
/* 197 */       return false; 
/* 199 */     return Map.class.isAssignableFrom(this.type);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 214 */     boolean result = false;
/* 216 */     result = (obj == this);
/* 218 */     if (!result && obj instanceof DynaProperty) {
/* 219 */       DynaProperty that = (DynaProperty)obj;
/* 220 */       result = (((this.name == null) ? (that.name == null) : this.name.equals(that.name)) && ((this.type == null) ? (that.type == null) : this.type.equals(that.type)) && ((this.contentType == null) ? (that.contentType == null) : this.contentType.equals(that.contentType)));
/*     */     } 
/* 226 */     return result;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 236 */     int result = 1;
/* 238 */     result = result * 31 + ((this.name == null) ? 0 : this.name.hashCode());
/* 239 */     result = result * 31 + ((this.type == null) ? 0 : this.type.hashCode());
/* 240 */     result = result * 31 + ((this.contentType == null) ? 0 : this.contentType.hashCode());
/* 242 */     return result;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 251 */     StringBuffer sb = new StringBuffer("DynaProperty[name=");
/* 252 */     sb.append(this.name);
/* 253 */     sb.append(",type=");
/* 254 */     sb.append(this.type);
/* 255 */     if (isMapped() || isIndexed())
/* 256 */       sb.append(" <").append(this.contentType).append(">"); 
/* 258 */     sb.append("]");
/* 259 */     return sb.toString();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 273 */     writeAnyClass(this.type, out);
/* 275 */     if (isMapped() || isIndexed())
/* 276 */       writeAnyClass(this.contentType, out); 
/* 280 */     out.defaultWriteObject();
/*     */   }
/*     */   
/*     */   private void writeAnyClass(Class clazz, ObjectOutputStream out) throws IOException {
/* 288 */     int primitiveType = 0;
/* 289 */     if (boolean.class.equals(clazz)) {
/* 290 */       primitiveType = 1;
/* 291 */     } else if (byte.class.equals(clazz)) {
/* 292 */       primitiveType = 2;
/* 293 */     } else if (char.class.equals(clazz)) {
/* 294 */       primitiveType = 3;
/* 295 */     } else if (double.class.equals(clazz)) {
/* 296 */       primitiveType = 4;
/* 297 */     } else if (float.class.equals(clazz)) {
/* 298 */       primitiveType = 5;
/* 299 */     } else if (int.class.equals(clazz)) {
/* 300 */       primitiveType = 6;
/* 301 */     } else if (long.class.equals(clazz)) {
/* 302 */       primitiveType = 7;
/* 303 */     } else if (short.class.equals(clazz)) {
/* 304 */       primitiveType = 8;
/*     */     } 
/* 307 */     if (primitiveType == 0) {
/* 309 */       out.writeBoolean(false);
/* 310 */       out.writeObject(clazz);
/*     */     } else {
/* 313 */       out.writeBoolean(true);
/* 314 */       out.writeInt(primitiveType);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 328 */     this.type = readAnyClass(in);
/* 330 */     if (isMapped() || isIndexed())
/* 331 */       this.contentType = readAnyClass(in); 
/* 335 */     in.defaultReadObject();
/*     */   }
/*     */   
/*     */   private Class readAnyClass(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 344 */     if (in.readBoolean()) {
/* 346 */       switch (in.readInt()) {
/*     */         case 1:
/* 348 */           return boolean.class;
/*     */         case 2:
/* 349 */           return byte.class;
/*     */         case 3:
/* 350 */           return char.class;
/*     */         case 4:
/* 351 */           return double.class;
/*     */         case 5:
/* 352 */           return float.class;
/*     */         case 6:
/* 353 */           return int.class;
/*     */         case 7:
/* 354 */           return long.class;
/*     */         case 8:
/* 355 */           return short.class;
/*     */       } 
/* 358 */       throw new StreamCorruptedException("Invalid primitive type. Check version of beanutils used to serialize is compatible.");
/*     */     } 
/* 366 */     return (Class)in.readObject();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\DynaProperty.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */