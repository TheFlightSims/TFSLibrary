/*     */ package javax.media.jai.registry;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.image.Raster;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationRegistry;
/*     */ import javax.media.jai.tilecodec.TileCodecParameterList;
/*     */ import javax.media.jai.tilecodec.TileDecoder;
/*     */ import javax.media.jai.tilecodec.TileDecoderFactory;
/*     */ 
/*     */ public final class TileDecoderRegistry {
/*     */   private static final String MODE_NAME = "tileDecoder";
/*     */   
/*     */   public static void register(OperationRegistry registry, String formatName, String productName, TileDecoderFactory tdf) {
/*  68 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/*  71 */     registry.registerFactory("tileDecoder", formatName, productName, tdf);
/*     */   }
/*     */   
/*     */   public static void unregister(OperationRegistry registry, String formatName, String productName, TileDecoderFactory tdf) {
/* 104 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 107 */     registry.unregisterFactory("tileDecoder", formatName, productName, tdf);
/*     */   }
/*     */   
/*     */   public static void setPreference(OperationRegistry registry, String formatName, String productName, TileDecoderFactory preferredTDF, TileDecoderFactory otherTDF) {
/* 143 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 146 */     registry.setFactoryPreference("tileDecoder", formatName, productName, preferredTDF, otherTDF);
/*     */   }
/*     */   
/*     */   public static void unsetPreference(OperationRegistry registry, String formatName, String productName, TileDecoderFactory preferredTDF, TileDecoderFactory otherTDF) {
/* 187 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 190 */     registry.unsetFactoryPreference("tileDecoder", formatName, productName, preferredTDF, otherTDF);
/*     */   }
/*     */   
/*     */   public static void clearPreferences(OperationRegistry registry, String formatName, String productName) {
/* 220 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 223 */     registry.clearFactoryPreferences("tileDecoder", formatName, productName);
/*     */   }
/*     */   
/*     */   public static List getOrderedList(OperationRegistry registry, String formatName, String productName) {
/* 251 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 254 */     return registry.getOrderedFactoryList("tileDecoder", formatName, productName);
/*     */   }
/*     */   
/*     */   public static Iterator getIterator(OperationRegistry registry, String formatName) {
/* 286 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 289 */     return registry.getFactoryIterator("tileDecoder", formatName);
/*     */   }
/*     */   
/*     */   public static TileDecoderFactory get(OperationRegistry registry, String formatName) {
/* 315 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 318 */     return (TileDecoderFactory)registry.getFactory("tileDecoder", formatName);
/*     */   }
/*     */   
/*     */   public static TileDecoder create(OperationRegistry registry, String formatName, InputStream input, TileCodecParameterList paramList) {
/* 367 */     registry = (registry != null) ? registry : JAI.getDefaultInstance().getOperationRegistry();
/* 370 */     Object[] args = { input, paramList };
/* 372 */     return (TileDecoder)registry.invokeFactory("tileDecoder", formatName, args);
/*     */   }
/*     */   
/*     */   public static Raster decode(OperationRegistry registry, String formatName, InputStream input, TileCodecParameterList param) throws IOException {
/* 450 */     TileDecoder decoder = create(registry, formatName, input, param);
/* 452 */     if (decoder == null)
/* 453 */       return null; 
/* 456 */     return decoder.decode();
/*     */   }
/*     */   
/*     */   public static Raster decode(OperationRegistry registry, String formatName, InputStream input, TileCodecParameterList param, Point location) throws IOException {
/* 530 */     TileDecoder decoder = create(registry, formatName, input, param);
/* 532 */     if (decoder == null)
/* 533 */       return null; 
/* 536 */     return decoder.decode(location);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\registry\TileDecoderRegistry.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */