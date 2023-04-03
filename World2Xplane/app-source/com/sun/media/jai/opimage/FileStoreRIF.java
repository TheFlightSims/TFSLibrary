/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.codec.ImageEncodeParam;
/*     */ import com.sun.media.jai.codec.SeekableOutputStream;
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderedImageFactory;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationRegistry;
/*     */ import javax.media.jai.RenderedImageAdapter;
/*     */ import javax.media.jai.registry.RIFRegistry;
/*     */ import javax.media.jai.util.ImagingListener;
/*     */ 
/*     */ public class FileStoreRIF implements RenderedImageFactory {
/*  42 */   private static String DEFAULT_FORMAT = "tiff";
/*     */   
/*     */   private class FileStoreImage extends RenderedImageAdapter {
/*     */     private OutputStream stream;
/*     */     
/*     */     private final FileStoreRIF this$0;
/*     */     
/*     */     public FileStoreImage(FileStoreRIF this$0, RenderedImage image, OutputStream stream) {
/*  59 */       super(image);
/*     */       this.this$0 = this$0;
/*  60 */       this.stream = stream;
/*     */     }
/*     */     
/*     */     public void dispose() {
/*     */       try {
/*  68 */         this.stream.close();
/*  69 */       } catch (IOException e) {}
/*  72 */       super.dispose();
/*     */     }
/*     */   }
/*     */   
/*     */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/*     */     SeekableOutputStream seekableOutputStream;
/*  81 */     ImagingListener listener = ImageUtil.getImagingListener(renderHints);
/*  84 */     String fileName = (String)paramBlock.getObjectParameter(0);
/*  87 */     String format = (String)paramBlock.getObjectParameter(1);
/*  92 */     if (format == null)
/*  93 */       format = DEFAULT_FORMAT; 
/*  97 */     ImageEncodeParam param = null;
/*  98 */     if (paramBlock.getNumParameters() > 2)
/*  99 */       param = (ImageEncodeParam)paramBlock.getObjectParameter(2); 
/* 103 */     OutputStream stream = null;
/*     */     try {
/* 105 */       if (param == null) {
/* 108 */         stream = new BufferedOutputStream(new FileOutputStream(fileName));
/*     */       } else {
/* 113 */         seekableOutputStream = new SeekableOutputStream(new RandomAccessFile(fileName, "rw"));
/*     */       } 
/* 117 */     } catch (FileNotFoundException e) {
/* 118 */       String message = JaiI18N.getString("FileLoadRIF0") + fileName;
/* 119 */       listener.errorOccurred(message, e, this, false);
/* 121 */       return null;
/* 122 */     } catch (SecurityException e) {
/* 123 */       String message = JaiI18N.getString("FileStoreRIF0");
/* 124 */       listener.errorOccurred(message, e, this, false);
/* 126 */       return null;
/*     */     } 
/* 130 */     ParameterBlock pb = new ParameterBlock();
/* 131 */     pb.addSource(paramBlock.getSource(0));
/* 132 */     pb.add(seekableOutputStream).add(format).add(param);
/* 135 */     OperationRegistry registry = (renderHints == null) ? null : (OperationRegistry)renderHints.get(JAI.KEY_OPERATION_REGISTRY);
/* 138 */     return (RenderedImage)new FileStoreImage(this, RIFRegistry.create(registry, "encode", pb, renderHints), (OutputStream)seekableOutputStream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\FileStoreRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */