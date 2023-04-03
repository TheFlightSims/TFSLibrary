/*     */ package org.apache.commons.compress.archivers.zip;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.zip.ZipException;
/*     */ 
/*     */ public class ExtraFieldUtils {
/*     */   private static final int WORD = 4;
/*     */   
/*  41 */   private static final Map<ZipShort, Class<?>> implementations = new HashMap<ZipShort, Class<?>>();
/*     */   
/*     */   static {
/*  42 */     register(AsiExtraField.class);
/*  43 */     register(JarMarker.class);
/*  44 */     register(UnicodePathExtraField.class);
/*  45 */     register(UnicodeCommentExtraField.class);
/*  46 */     register(Zip64ExtendedInformationExtraField.class);
/*     */   }
/*     */   
/*     */   public static void register(Class<?> c) {
/*     */     try {
/*  58 */       ZipExtraField ze = (ZipExtraField)c.newInstance();
/*  59 */       implementations.put(ze.getHeaderId(), c);
/*  60 */     } catch (ClassCastException cc) {
/*  61 */       throw new RuntimeException(c + " doesn't implement ZipExtraField");
/*  62 */     } catch (InstantiationException ie) {
/*  63 */       throw new RuntimeException(c + " is not a concrete class");
/*  64 */     } catch (IllegalAccessException ie) {
/*  65 */       throw new RuntimeException(c + "'s no-arg constructor is not public");
/*     */     } 
/*     */   }
/*     */   
/*     */   public static ZipExtraField createExtraField(ZipShort headerId) throws InstantiationException, IllegalAccessException {
/*  79 */     Class<?> c = implementations.get(headerId);
/*  80 */     if (c != null)
/*  81 */       return (ZipExtraField)c.newInstance(); 
/*  83 */     UnrecognizedExtraField u = new UnrecognizedExtraField();
/*  84 */     u.setHeaderId(headerId);
/*  85 */     return u;
/*     */   }
/*     */   
/*     */   public static ZipExtraField[] parse(byte[] data) throws ZipException {
/*  97 */     return parse(data, true, UnparseableExtraField.THROW);
/*     */   }
/*     */   
/*     */   public static ZipExtraField[] parse(byte[] data, boolean local) throws ZipException {
/* 111 */     return parse(data, local, UnparseableExtraField.THROW);
/*     */   }
/*     */   
/*     */   public static ZipExtraField[] parse(byte[] data, boolean local, UnparseableExtraField onUnparseableData) throws ZipException {
/* 130 */     List<ZipExtraField> v = new ArrayList<ZipExtraField>();
/* 131 */     int start = 0;
/* 133 */     while (start <= data.length - 4) {
/* 134 */       ZipShort headerId = new ZipShort(data, start);
/* 135 */       int length = (new ZipShort(data, start + 2)).getValue();
/* 136 */       if (start + 4 + length > data.length) {
/*     */         UnparseableExtraFieldData field;
/* 137 */         switch (onUnparseableData.getKey()) {
/*     */           case 0:
/* 139 */             throw new ZipException("bad extra field starting at " + start + ".  Block length of " + length + " bytes exceeds remaining" + " data of " + (data.length - start - 4) + " bytes.");
/*     */           case 2:
/* 146 */             field = new UnparseableExtraFieldData();
/* 148 */             if (local) {
/* 149 */               field.parseFromLocalFileData(data, start, data.length - start);
/*     */             } else {
/* 152 */               field.parseFromCentralDirectoryData(data, start, data.length - start);
/*     */             } 
/* 155 */             v.add(field);
/*     */             break;
/*     */           case 1:
/*     */             break;
/*     */         } 
/* 163 */         throw new ZipException("unknown UnparseableExtraField key: " + onUnparseableData.getKey());
/*     */       } 
/*     */       try {
/* 168 */         ZipExtraField ze = createExtraField(headerId);
/* 169 */         if (local) {
/* 170 */           ze.parseFromLocalFileData(data, start + 4, length);
/*     */         } else {
/* 172 */           ze.parseFromCentralDirectoryData(data, start + 4, length);
/*     */         } 
/* 175 */         v.add(ze);
/* 176 */       } catch (InstantiationException ie) {
/* 177 */         throw new ZipException(ie.getMessage());
/* 178 */       } catch (IllegalAccessException iae) {
/* 179 */         throw new ZipException(iae.getMessage());
/*     */       } 
/* 181 */       start += length + 4;
/*     */     } 
/* 184 */     ZipExtraField[] result = new ZipExtraField[v.size()];
/* 185 */     return v.<ZipExtraField>toArray(result);
/*     */   }
/*     */   
/*     */   public static byte[] mergeLocalFileDataData(ZipExtraField[] data) {
/* 194 */     boolean lastIsUnparseableHolder = (data.length > 0 && data[data.length - 1] instanceof UnparseableExtraFieldData);
/* 196 */     int regularExtraFieldCount = lastIsUnparseableHolder ? (data.length - 1) : data.length;
/* 199 */     int sum = 4 * regularExtraFieldCount;
/* 200 */     for (ZipExtraField element : data)
/* 201 */       sum += element.getLocalFileDataLength().getValue(); 
/* 204 */     byte[] result = new byte[sum];
/* 205 */     int start = 0;
/* 206 */     for (int i = 0; i < regularExtraFieldCount; i++) {
/* 207 */       System.arraycopy(data[i].getHeaderId().getBytes(), 0, result, start, 2);
/* 209 */       System.arraycopy(data[i].getLocalFileDataLength().getBytes(), 0, result, start + 2, 2);
/* 211 */       byte[] local = data[i].getLocalFileDataData();
/* 212 */       System.arraycopy(local, 0, result, start + 4, local.length);
/* 213 */       start += local.length + 4;
/*     */     } 
/* 215 */     if (lastIsUnparseableHolder) {
/* 216 */       byte[] local = data[data.length - 1].getLocalFileDataData();
/* 217 */       System.arraycopy(local, 0, result, start, local.length);
/*     */     } 
/* 219 */     return result;
/*     */   }
/*     */   
/*     */   public static byte[] mergeCentralDirectoryData(ZipExtraField[] data) {
/* 228 */     boolean lastIsUnparseableHolder = (data.length > 0 && data[data.length - 1] instanceof UnparseableExtraFieldData);
/* 230 */     int regularExtraFieldCount = lastIsUnparseableHolder ? (data.length - 1) : data.length;
/* 233 */     int sum = 4 * regularExtraFieldCount;
/* 234 */     for (ZipExtraField element : data)
/* 235 */       sum += element.getCentralDirectoryLength().getValue(); 
/* 237 */     byte[] result = new byte[sum];
/* 238 */     int start = 0;
/* 239 */     for (int i = 0; i < regularExtraFieldCount; i++) {
/* 240 */       System.arraycopy(data[i].getHeaderId().getBytes(), 0, result, start, 2);
/* 242 */       System.arraycopy(data[i].getCentralDirectoryLength().getBytes(), 0, result, start + 2, 2);
/* 244 */       byte[] local = data[i].getCentralDirectoryData();
/* 245 */       System.arraycopy(local, 0, result, start + 4, local.length);
/* 246 */       start += local.length + 4;
/*     */     } 
/* 248 */     if (lastIsUnparseableHolder) {
/* 249 */       byte[] local = data[data.length - 1].getCentralDirectoryData();
/* 250 */       System.arraycopy(local, 0, result, start, local.length);
/*     */     } 
/* 252 */     return result;
/*     */   }
/*     */   
/*     */   public static final class UnparseableExtraField {
/*     */     public static final int THROW_KEY = 0;
/*     */     
/*     */     public static final int SKIP_KEY = 1;
/*     */     
/*     */     public static final int READ_KEY = 2;
/*     */     
/* 278 */     public static final UnparseableExtraField THROW = new UnparseableExtraField(0);
/*     */     
/* 285 */     public static final UnparseableExtraField SKIP = new UnparseableExtraField(1);
/*     */     
/* 292 */     public static final UnparseableExtraField READ = new UnparseableExtraField(2);
/*     */     
/*     */     private final int key;
/*     */     
/*     */     private UnparseableExtraField(int k) {
/* 298 */       this.key = k;
/*     */     }
/*     */     
/*     */     public int getKey() {
/* 304 */       return this.key;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\zip\ExtraFieldUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */