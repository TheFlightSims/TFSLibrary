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
/*     */ import java.awt.image.renderable.RenderedImageFactory;
/*     */ import java.io.IOException;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationRegistry;
/*     */ import javax.media.jai.TileCache;
/*     */ import javax.media.jai.registry.RIFRegistry;
/*     */ import javax.media.jai.util.ImagingException;
/*     */ import javax.media.jai.util.ImagingListener;
/*     */ 
/*     */ public class StreamRIF implements RenderedImageFactory {
/*     */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/*  56 */     ImagingListener listener = ImageUtil.getImagingListener(renderHints);
/*  57 */     SeekableStream src = (SeekableStream)paramBlock.getObjectParameter(0);
/*     */     try {
/*  59 */       src.seek(0L);
/*  60 */     } catch (IOException e) {
/*  61 */       listener.errorOccurred(JaiI18N.getString("StreamRIF0"), e, this, false);
/*  64 */       return null;
/*     */     } 
/*  67 */     ImageDecodeParam param = null;
/*  68 */     if (paramBlock.getNumParameters() > 1)
/*  69 */       param = (ImageDecodeParam)paramBlock.getObjectParameter(1); 
/*  72 */     String[] names = ImageCodec.getDecoderNames(src);
/*  74 */     OperationRegistry registry = JAI.getDefaultInstance().getOperationRegistry();
/*  76 */     int bound = 2;
/*  77 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/*  79 */     if (renderHints != null) {
/*  82 */       RenderingHints.Key key = JAI.KEY_OPERATION_REGISTRY;
/*  83 */       if (renderHints.containsKey(key))
/*  84 */         registry = (OperationRegistry)renderHints.get(key); 
/*  87 */       key = JAI.KEY_OPERATION_BOUND;
/*  88 */       if (renderHints.containsKey(key))
/*  89 */         bound = ((Integer)renderHints.get(key)).intValue(); 
/*     */     } 
/*  94 */     for (int i = 0; i < names.length; i++) {
/*  95 */       RenderedImageFactory rif = null;
/*     */       try {
/*  97 */         rif = RIFRegistry.get(registry, names[i]);
/*  98 */       } catch (IllegalArgumentException iae) {}
/* 101 */       if (rif != null) {
/* 102 */         RenderedImage im = RIFRegistry.create(registry, names[i], paramBlock, renderHints);
/* 104 */         if (im != null)
/* 105 */           return im; 
/*     */       } 
/*     */     } 
/* 113 */     boolean canAttemptRecovery = src.canSeekBackwards();
/* 116 */     long streamPosition = Long.MIN_VALUE;
/* 117 */     if (canAttemptRecovery)
/*     */       try {
/* 119 */         streamPosition = src.getFilePointer();
/* 120 */       } catch (IOException ioe) {
/* 121 */         listener.errorOccurred(JaiI18N.getString("StreamRIF1"), ioe, this, false);
/* 125 */         canAttemptRecovery = false;
/*     */       }  
/* 130 */     for (int j = 0; j < names.length; j++) {
/* 131 */       ImageDecoder dec = ImageCodec.createImageDecoder(names[j], src, param);
/* 133 */       RenderedImage im = null;
/*     */       try {
/* 135 */         im = dec.decodeAsRenderedImage();
/* 136 */       } catch (OutOfMemoryError memoryError) {
/* 140 */         if (canAttemptRecovery) {
/* 142 */           TileCache cache = RIFUtil.getTileCacheHint(renderHints);
/* 143 */           if (cache != null)
/* 144 */             cache.flush(); 
/* 148 */           System.gc();
/*     */           try {
/* 152 */             src.seek(streamPosition);
/* 155 */             im = dec.decodeAsRenderedImage();
/* 156 */           } catch (IOException ioe) {
/* 157 */             listener.errorOccurred(JaiI18N.getString("StreamRIF2"), ioe, this, false);
/* 159 */             im = null;
/*     */           } 
/*     */         } else {
/* 162 */           String message = JaiI18N.getString("CodecRIFUtil0");
/* 163 */           listener.errorOccurred(message, (Throwable)new ImagingException(message, memoryError), this, false);
/*     */         } 
/* 170 */       } catch (IOException e) {
/* 171 */         listener.errorOccurred(JaiI18N.getString("StreamRIF2"), e, this, false);
/* 173 */         im = null;
/*     */       } 
/* 177 */       if (im != null)
/* 178 */         return (RenderedImage)new DisposableNullOpImage(im, layout, renderHints, bound); 
/*     */     } 
/* 183 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\StreamRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */