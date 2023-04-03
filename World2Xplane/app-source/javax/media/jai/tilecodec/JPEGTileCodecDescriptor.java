/*     */ package javax.media.jai.tilecodec;
/*     */ 
/*     */ import java.awt.image.SampleModel;
/*     */ import javax.media.jai.ParameterListDescriptor;
/*     */ import javax.media.jai.ParameterListDescriptorImpl;
/*     */ import javax.media.jai.util.Range;
/*     */ 
/*     */ public class JPEGTileCodecDescriptor extends TileCodecDescriptorImpl {
/* 172 */   private static final int[] lumQuantizationTable = new int[] { 
/* 172 */       16, 11, 12, 14, 12, 10, 16, 14, 13, 14, 
/* 172 */       18, 17, 16, 19, 24, 40, 26, 24, 22, 22, 
/* 172 */       24, 49, 35, 37, 29, 40, 58, 51, 61, 60, 
/* 172 */       57, 51, 56, 55, 64, 72, 92, 78, 64, 68, 
/* 172 */       87, 69, 55, 56, 80, 109, 81, 87, 95, 98, 
/* 172 */       103, 104, 103, 62, 77, 113, 121, 112, 100, 120, 
/* 172 */       92, 101, 103, 99 };
/*     */   
/* 183 */   private static final int[] chromQuantizationTable = new int[] { 
/* 183 */       17, 18, 18, 24, 21, 24, 47, 26, 26, 47, 
/* 183 */       99, 66, 56, 66, 99, 99, 99, 99, 99, 99, 
/* 183 */       99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 
/* 183 */       99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 
/* 183 */       99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 
/* 183 */       99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 
/* 183 */       99, 99, 99, 99 };
/*     */   
/* 195 */   private static final String[] paramNames = new String[] { 
/* 195 */       "quality", "qualitySet", "horizontalSubsampling", "verticalSubsampling", "quantizationTableMapping", "quantizationTable0", "quantizationTable1", "quantizationTable2", "quantizationTable3", "restartInterval", 
/* 195 */       "writeImageInfo", "writeTableInfo", "writeJFIFHeader" };
/*     */   
/* 211 */   private static final Class[] paramClasses = new Class[] { 
/* 211 */       Float.class, Boolean.class, (array$I == null) ? (array$I = class$("[I")) : array$I, (array$I == null) ? (array$I = class$("[I")) : array$I, (array$I == null) ? (array$I = class$("[I")) : array$I, (array$I == null) ? (array$I = class$("[I")) : array$I, (array$I == null) ? (array$I = class$("[I")) : array$I, (array$I == null) ? (array$I = class$("[I")) : array$I, (array$I == null) ? (array$I = class$("[I")) : array$I, Integer.class, 
/* 211 */       Boolean.class, Boolean.class, Boolean.class };
/*     */   
/* 227 */   private static final Object[] paramDefaults = new Object[] { 
/* 227 */       new Float(0.75F), new Boolean(true), { 1, 1, 1 }, { 1, 1, 1 }, { 0, 1, 1 }, lumQuantizationTable, chromQuantizationTable, chromQuantizationTable, chromQuantizationTable, new Integer(0), 
/* 227 */       new Boolean(true), new Boolean(true), new Boolean(false) };
/*     */   
/* 255 */   private static final Object[] validParamValues = new Object[] { 
/* 255 */       new Range(Float.class, new Float(0.0F), new Float(1.0F)), null, null, null, null, null, null, null, null, new Range(Integer.class, new Integer(0), null), 
/* 255 */       null, null, null };
/*     */   
/* 271 */   private static ParameterListDescriptor paramListDescriptor = (ParameterListDescriptor)new ParameterListDescriptorImpl(null, paramNames, paramClasses, paramDefaults, validParamValues);
/*     */   
/*     */   static Class array$I;
/*     */   
/*     */   public JPEGTileCodecDescriptor() {
/* 282 */     super("jpeg", true, true);
/*     */   }
/*     */   
/*     */   public TileCodecParameterList getCompatibleParameters(String modeName, TileCodecParameterList otherParamList) {
/* 314 */     if (modeName == null)
/* 315 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodecDescriptorImpl1")); 
/* 319 */     if (otherParamList == null)
/* 320 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodecDescriptorImpl3")); 
/* 324 */     String name = getName();
/* 325 */     if (!otherParamList.getFormatName().equals(name))
/* 326 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodec2")); 
/* 330 */     if (otherParamList.isValidForMode(modeName))
/* 331 */       return otherParamList; 
/* 333 */     if (modeName.equalsIgnoreCase("tileDecoder"))
/* 334 */       return new TileCodecParameterList(name, new String[] { "tileDecoder" }, otherParamList.getParameterListDescriptor()); 
/* 338 */     if (modeName.equalsIgnoreCase("tileEncoder"))
/* 339 */       return new TileCodecParameterList(name, new String[] { "tileEncoder" }, otherParamList.getParameterListDescriptor()); 
/* 344 */     throw new IllegalArgumentException(JaiI18N.getString("TileCodec1"));
/*     */   }
/*     */   
/*     */   public TileCodecParameterList getDefaultParameters(String modeName) {
/* 363 */     if (modeName == null)
/* 364 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodecDescriptorImpl1")); 
/* 367 */     String[] validNames = getSupportedModes();
/* 368 */     boolean valid = false;
/* 370 */     for (int i = 0; i < validNames.length; i++) {
/* 371 */       if (modeName.equalsIgnoreCase(validNames[i])) {
/* 372 */         valid = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 377 */     if (!valid)
/* 378 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodec1")); 
/* 382 */     return new TileCodecParameterList("jpeg", new String[] { "tileDecoder", "tileEncoder" }, paramListDescriptor);
/*     */   }
/*     */   
/*     */   public TileCodecParameterList getDefaultParameters(String modeName, SampleModel sm) {
/* 414 */     return getDefaultParameters(modeName);
/*     */   }
/*     */   
/*     */   public ParameterListDescriptor getParameterListDescriptor(String modeName) {
/* 433 */     if (modeName == null)
/* 434 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodecDescriptorImpl1")); 
/* 437 */     String[] validNames = getSupportedModes();
/* 438 */     boolean valid = false;
/* 440 */     for (int i = 0; i < validNames.length; i++) {
/* 441 */       if (modeName.equalsIgnoreCase(validNames[i])) {
/* 442 */         valid = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 447 */     if (!valid)
/* 448 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodec1")); 
/* 452 */     return paramListDescriptor;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\tilecodec\JPEGTileCodecDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */