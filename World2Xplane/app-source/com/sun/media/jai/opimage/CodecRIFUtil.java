/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.codec.ImageCodec;
/*     */ import com.sun.media.jai.codec.ImageDecodeParam;
/*     */ import com.sun.media.jai.codec.ImageDecoder;
/*     */ import com.sun.media.jai.codec.SeekableStream;
/*     */ import com.sun.media.jai.util.DisposableNullOpImage;
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.io.IOException;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OpImage;
/*     */ import javax.media.jai.TileCache;
/*     */ import javax.media.jai.util.ImagingException;
/*     */ import javax.media.jai.util.ImagingListener;
/*     */ 
/*     */ public class CodecRIFUtil {
/*     */   public static RenderedImage create(String type, ParameterBlock paramBlock, RenderingHints renderHints) {
/*  38 */     ImagingListener listener = ImageUtil.getImagingListener(renderHints);
/*  40 */     SeekableStream source = (SeekableStream)paramBlock.getObjectParameter(0);
/*  43 */     ImageDecodeParam param = null;
/*  44 */     if (paramBlock.getNumParameters() > 1)
/*  45 */       param = (ImageDecodeParam)paramBlock.getObjectParameter(1); 
/*  47 */     int page = 0;
/*  48 */     if (paramBlock.getNumParameters() > 2)
/*  49 */       page = paramBlock.getIntParameter(2); 
/*  52 */     ImageDecoder dec = ImageCodec.createImageDecoder(type, source, param);
/*     */     try {
/*     */       DisposableNullOpImage disposableNullOpImage;
/*  54 */       int bound = 2;
/*  55 */       ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/*  57 */       if (renderHints != null) {
/*  60 */         RenderingHints.Key key = JAI.KEY_OPERATION_BOUND;
/*  61 */         if (renderHints.containsKey(key))
/*  62 */           bound = ((Integer)renderHints.get(key)).intValue(); 
/*     */       } 
/*  69 */       boolean canAttemptRecovery = source.canSeekBackwards();
/*  72 */       long streamPosition = Long.MIN_VALUE;
/*  73 */       if (canAttemptRecovery)
/*     */         try {
/*  75 */           streamPosition = source.getFilePointer();
/*  76 */         } catch (IOException ioe) {
/*  77 */           listener.errorOccurred(JaiI18N.getString("StreamRIF1"), ioe, CodecRIFUtil.class, false);
/*  81 */           canAttemptRecovery = false;
/*     */         }  
/*  85 */       OpImage image = null;
/*     */       try {
/*  88 */         disposableNullOpImage = new DisposableNullOpImage(dec.decodeAsRenderedImage(page), layout, renderHints, bound);
/*  92 */       } catch (OutOfMemoryError memoryError) {
/*  96 */         if (canAttemptRecovery) {
/*  98 */           TileCache cache = (disposableNullOpImage != null) ? disposableNullOpImage.getTileCache() : RIFUtil.getTileCacheHint(renderHints);
/* 101 */           if (cache != null)
/* 102 */             cache.flush(); 
/* 106 */           System.gc();
/* 109 */           source.seek(streamPosition);
/* 112 */           disposableNullOpImage = new DisposableNullOpImage(dec.decodeAsRenderedImage(page), layout, renderHints, bound);
/*     */         } else {
/* 118 */           String message = JaiI18N.getString("CodecRIFUtil0");
/* 119 */           listener.errorOccurred(message, (Throwable)new ImagingException(message, memoryError), CodecRIFUtil.class, false);
/*     */         } 
/*     */       } 
/* 127 */       return (RenderedImage)disposableNullOpImage;
/* 128 */     } catch (Exception e) {
/* 129 */       listener.errorOccurred(JaiI18N.getString("CodecRIFUtil1"), e, CodecRIFUtil.class, false);
/* 132 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\CodecRIFUtil.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */