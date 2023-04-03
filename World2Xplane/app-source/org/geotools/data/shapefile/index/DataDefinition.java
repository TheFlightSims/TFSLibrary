/*     */ package org.geotools.data.shapefile.index;
/*     */ 
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class DataDefinition {
/*     */   private Charset charset;
/*     */   
/*     */   private ArrayList<Field> fields;
/*     */   
/*     */   public DataDefinition(String charset) {
/*  35 */     this.fields = new ArrayList<Field>();
/*  36 */     this.charset = Charset.forName(charset);
/*     */   }
/*     */   
/*     */   public final boolean isValid() {
/*  40 */     return (this.charset != null && !this.charset.equals("") && this.fields.size() > 0);
/*     */   }
/*     */   
/*     */   public int getFieldsCount() {
/*  45 */     return this.fields.size();
/*     */   }
/*     */   
/*     */   public Field getField(int i) {
/*  49 */     return this.fields.get(i);
/*     */   }
/*     */   
/*     */   public void addField(Class clazz) {
/*  71 */     if (clazz.isAssignableFrom(Short.class)) {
/*  72 */       this.fields.add(new Field(clazz, 2));
/*  73 */     } else if (clazz.isAssignableFrom(Integer.class)) {
/*  74 */       this.fields.add(new Field(clazz, 4));
/*  75 */     } else if (clazz.isAssignableFrom(Long.class)) {
/*  76 */       this.fields.add(new Field(clazz, 8));
/*  77 */     } else if (clazz.isAssignableFrom(Float.class)) {
/*  79 */       this.fields.add(new Field(clazz, 4));
/*  80 */     } else if (clazz.isAssignableFrom(Double.class)) {
/*  81 */       this.fields.add(new Field(clazz, 8));
/*     */     } else {
/*  83 */       throw new IllegalArgumentException("Unknow len of class " + clazz + "use addField(int)");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addField(int len) {
/*  95 */     this.fields.add(new Field(String.class, len));
/*     */   }
/*     */   
/*     */   public Charset getCharset() {
/* 103 */     return this.charset;
/*     */   }
/*     */   
/*     */   public int getLen() {
/* 110 */     int len = 0;
/* 112 */     Field field = null;
/* 114 */     for (int i = 0; i < this.fields.size(); i++) {
/* 115 */       field = this.fields.get(i);
/* 116 */       len += field.getLen();
/*     */     } 
/* 119 */     return len;
/*     */   }
/*     */   
/*     */   public int getEncodedLen() {
/* 128 */     int len = 0;
/* 130 */     Field field = null;
/* 132 */     for (int i = 0; i < this.fields.size(); i++) {
/* 133 */       field = this.fields.get(i);
/* 134 */       len += field.getEncodedLen();
/*     */     } 
/* 137 */     return len;
/*     */   }
/*     */   
/*     */   public class Field {
/*     */     private Class clazz;
/*     */     
/*     */     private int len;
/*     */     
/*     */     public Field(Class clazz, int len) {
/* 150 */       this.clazz = clazz;
/* 151 */       this.len = len;
/*     */     }
/*     */     
/*     */     public Class getFieldClass() {
/* 159 */       return this.clazz;
/*     */     }
/*     */     
/*     */     public int getLen() {
/* 167 */       return this.len;
/*     */     }
/*     */     
/*     */     public int getEncodedLen() {
/* 175 */       int ret = this.len;
/* 177 */       if (this.clazz.equals(String.class))
/* 178 */         ret = (int)DataDefinition.this.charset.newEncoder().maxBytesPerChar() * this.len; 
/* 181 */       return ret;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\index\DataDefinition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */