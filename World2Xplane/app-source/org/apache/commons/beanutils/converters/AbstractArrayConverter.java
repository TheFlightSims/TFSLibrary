/*     */ package org.apache.commons.beanutils.converters;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.StreamTokenizer;
/*     */ import java.io.StringReader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.beanutils.ConversionException;
/*     */ import org.apache.commons.beanutils.Converter;
/*     */ 
/*     */ public abstract class AbstractArrayConverter implements Converter {
/*     */   public AbstractArrayConverter() {
/* 110 */     this.defaultValue = null;
/* 116 */     this.useDefault = true;
/*     */     this.defaultValue = null;
/*     */     this.useDefault = false;
/*     */   }
/*     */   
/*     */   public AbstractArrayConverter(Object defaultValue) {
/*     */     this.defaultValue = null;
/* 116 */     this.useDefault = true;
/*     */     if (defaultValue == NO_DEFAULT) {
/*     */       this.useDefault = false;
/*     */     } else {
/*     */       this.defaultValue = defaultValue;
/*     */       this.useDefault = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static final Object NO_DEFAULT = new Object();
/*     */   
/*     */   protected static String[] strings = new String[0];
/*     */   
/*     */   protected Object defaultValue;
/*     */   
/*     */   protected boolean useDefault;
/*     */   
/*     */   public abstract Object convert(Class paramClass, Object paramObject);
/*     */   
/*     */   protected List parseElements(String svalue) {
/* 164 */     if (svalue == null)
/* 165 */       throw new NullPointerException(); 
/* 169 */     svalue = svalue.trim();
/* 170 */     if (svalue.startsWith("{") && svalue.endsWith("}"))
/* 171 */       svalue = svalue.substring(1, svalue.length() - 1); 
/*     */     try {
/*     */       int ttype;
/* 177 */       StreamTokenizer st = new StreamTokenizer(new StringReader(svalue));
/* 179 */       st.whitespaceChars(44, 44);
/* 180 */       st.ordinaryChars(48, 57);
/* 181 */       st.ordinaryChars(46, 46);
/* 182 */       st.ordinaryChars(45, 45);
/* 183 */       st.wordChars(48, 57);
/* 184 */       st.wordChars(46, 46);
/* 185 */       st.wordChars(45, 45);
/* 188 */       ArrayList list = new ArrayList();
/*     */       while (true) {
/* 190 */         ttype = st.nextToken();
/* 191 */         if (ttype == -3 || ttype > 0) {
/* 193 */           list.add(st.sval);
/*     */           continue;
/*     */         } 
/*     */         break;
/*     */       } 
/* 194 */       if (ttype == -1)
/* 203 */         return list; 
/*     */       throw new ConversionException("Encountered token of type " + ttype);
/* 205 */     } catch (IOException e) {
/* 207 */       throw new ConversionException(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\AbstractArrayConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */