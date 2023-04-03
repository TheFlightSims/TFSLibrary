/*     */ package javax.media.jai.registry;
/*     */ 
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationRegistry;
/*     */ import javax.media.jai.tilecodec.TileCodecParameterList;
/*     */ import javax.media.jai.tilecodec.TileEncoder;
/*     */ import javax.media.jai.tilecodec.TileEncoderFactory;
/*     */ 
/*     */ public final class TileEncoderRegistry {
/*     */   private static final String MODE_NAME = "tileEncoder";
/*     */   
/*     */   public static void register(OperationRegistry registry, String formatName, String productName, TileEncoderFactory tef) {
/*  64 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/*  67 */     registry.registerFactory("tileEncoder", formatName, productName, tef);
/*     */   }
/*     */   
/*     */   public static void unregister(OperationRegistry registry, String formatName, String productName, TileEncoderFactory tef) {
/*  97 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 100 */     registry.unregisterFactory("tileEncoder", formatName, productName, tef);
/*     */   }
/*     */   
/*     */   public static void setPreference(OperationRegistry registry, String formatName, String productName, TileEncoderFactory preferredTEF, TileEncoderFactory otherTEF) {
/* 133 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 136 */     registry.setFactoryPreference("tileEncoder", formatName, productName, preferredTEF, otherTEF);
/*     */   }
/*     */   
/*     */   public static void unsetPreference(OperationRegistry registry, String formatName, String productName, TileEncoderFactory preferredTEF, TileEncoderFactory otherTEF) {
/* 173 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 176 */     registry.unsetFactoryPreference("tileEncoder", formatName, productName, preferredTEF, otherTEF);
/*     */   }
/*     */   
/*     */   public static void clearPreferences(OperationRegistry registry, String formatName, String productName) {
/* 203 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 206 */     registry.clearFactoryPreferences("tileEncoder", formatName, productName);
/*     */   }
/*     */   
/*     */   public static List getOrderedList(OperationRegistry registry, String formatName, String productName) {
/* 231 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 234 */     return registry.getOrderedFactoryList("tileEncoder", formatName, productName);
/*     */   }
/*     */   
/*     */   public static Iterator getIterator(OperationRegistry registry, String formatName) {
/* 263 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 266 */     return registry.getFactoryIterator("tileEncoder", formatName);
/*     */   }
/*     */   
/*     */   public static TileEncoderFactory get(OperationRegistry registry, String formatName) {
/* 291 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 294 */     return (TileEncoderFactory)registry.getFactory("tileEncoder", formatName);
/*     */   }
/*     */   
/*     */   public static TileEncoder create(OperationRegistry registry, String formatName, OutputStream output, TileCodecParameterList paramList, SampleModel sampleModel) {
/* 335 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 338 */     Object[] args = { output, paramList, sampleModel };
/* 340 */     return (TileEncoder)registry.invokeFactory("tileEncoder", formatName, args);
/*     */   }
/*     */   
/*     */   public static void encode(OperationRegistry registry, String formatName, Raster raster, OutputStream output, TileCodecParameterList param) throws IOException {
/* 402 */     TileEncoder encoder = create(registry, formatName, output, param, raster.getSampleModel());
/* 408 */     if (encoder != null)
/* 409 */       encoder.encode(raster); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\registry\TileEncoderRegistry.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */