/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.codec.FileSeekableStream;
/*     */ import com.sun.media.jai.codec.ImageDecodeParam;
/*     */ import com.sun.media.jai.codec.SeekableStream;
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderedImageFactory;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.InputStream;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationRegistry;
/*     */ import javax.media.jai.registry.RIFRegistry;
/*     */ import javax.media.jai.util.ImagingListener;
/*     */ 
/*     */ public class FileLoadRIF implements RenderedImageFactory {
/*     */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/* 102 */     ImagingListener listener = ImageUtil.getImagingListener(hints);
/*     */     try {
/* 106 */       String fileName = (String)args.getObjectParameter(0);
/* 108 */       SeekableStream src = null;
/*     */       try {
/* 110 */         FileSeekableStream fileSeekableStream = new FileSeekableStream(fileName);
/* 111 */       } catch (FileNotFoundException fnfe) {
/* 115 */         InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
/* 116 */         if (is != null)
/* 117 */           src = SeekableStream.wrapInputStream(is, true); 
/*     */       } 
/* 120 */       ImageDecodeParam param = null;
/* 121 */       if (args.getNumParameters() > 1)
/* 122 */         param = (ImageDecodeParam)args.getObjectParameter(1); 
/* 125 */       ParameterBlock newArgs = new ParameterBlock();
/* 126 */       newArgs.add(src);
/* 127 */       newArgs.add(param);
/* 129 */       RenderingHints.Key key = JAI.KEY_OPERATION_BOUND;
/* 130 */       int bound = 2;
/* 131 */       if (hints == null) {
/* 132 */         hints = new RenderingHints(key, new Integer(bound));
/* 133 */       } else if (!hints.containsKey(key)) {
/* 134 */         hints = (RenderingHints)hints.clone();
/* 135 */         hints.put(key, new Integer(bound));
/*     */       } 
/* 140 */       OperationRegistry registry = (OperationRegistry)hints.get(JAI.KEY_OPERATION_REGISTRY);
/* 144 */       RenderedImage image = RIFRegistry.create(registry, "stream", newArgs, hints);
/* 147 */       return (image == null) ? null : (RenderedImage)new StreamImage(image, (InputStream)src);
/* 149 */     } catch (FileNotFoundException e) {
/* 150 */       String message = JaiI18N.getString("FileLoadRIF0") + args.getObjectParameter(0);
/* 152 */       listener.errorOccurred(message, e, this, false);
/* 154 */       return null;
/* 155 */     } catch (Exception e) {
/* 156 */       String message = JaiI18N.getString("FileLoadRIF1");
/* 157 */       listener.errorOccurred(message, e, this, false);
/* 159 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\FileLoadRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */