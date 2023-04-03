/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
/*     */ import javax.media.jai.CRIFImpl;
/*     */ import javax.media.jai.ImageMIPMap;
/*     */ import javax.media.jai.MultiResolutionRenderableImage;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class RenderableCRIF extends CRIFImpl {
/*  38 */   private Hashtable mresTable = null;
/*     */   
/*     */   private static final Object getKey(ParameterBlock paramBlock) {
/*  43 */     String key = new String();
/*  46 */     key = key + String.valueOf(paramBlock.getRenderedSource(0).hashCode());
/*  49 */     key = key + getKey((RenderedOp)paramBlock.getObjectParameter(0));
/*  52 */     key = key + String.valueOf(paramBlock.getIntParameter(1));
/*  53 */     key = key + String.valueOf(paramBlock.getFloatParameter(2));
/*  54 */     key = key + String.valueOf(paramBlock.getFloatParameter(3));
/*  55 */     key = key + String.valueOf(paramBlock.getFloatParameter(4));
/*  57 */     return key;
/*     */   }
/*     */   
/*     */   private static final String getKey(RenderedOp op) {
/*  63 */     String key = new String(String.valueOf(op.hashCode()));
/*  66 */     ParameterBlock pb = op.getParameterBlock();
/*  69 */     int numSources = pb.getNumSources();
/*  70 */     for (int s = 0; s < numSources; s++) {
/*  71 */       RenderedImage src = pb.getRenderedSource(s);
/*  74 */       if (src instanceof RenderedOp) {
/*  75 */         key = key + getKey((RenderedOp)src);
/*     */       } else {
/*  77 */         key = key + String.valueOf(src.hashCode());
/*     */       } 
/*     */     } 
/*  82 */     int numParameters = pb.getNumParameters();
/*  83 */     for (int p = 0; p < numParameters; p++)
/*  86 */       key = key + pb.getObjectParameter(p).toString(); 
/*  89 */     return key;
/*     */   }
/*     */   
/*     */   private RenderableImage createRenderable(ParameterBlock paramBlock) {
/*     */     MultiResolutionRenderableImage multiResolutionRenderableImage;
/* 106 */     if (this.mresTable == null)
/* 107 */       this.mresTable = new Hashtable(); 
/* 111 */     Object key = getKey(paramBlock);
/* 112 */     SoftReference ref = (SoftReference)this.mresTable.get(key);
/* 115 */     RenderableImage mres = null;
/* 116 */     if (ref != null && (mres = ref.get()) == null)
/* 118 */       this.mresTable.remove(key); 
/* 122 */     if (mres == null) {
/* 124 */       RenderedImage source = paramBlock.getRenderedSource(0);
/* 125 */       RenderedOp downSampler = (RenderedOp)paramBlock.getObjectParameter(0);
/* 127 */       int maxLowResDim = paramBlock.getIntParameter(1);
/* 128 */       float minX = paramBlock.getFloatParameter(2);
/* 129 */       float minY = paramBlock.getFloatParameter(3);
/* 130 */       float height = paramBlock.getFloatParameter(4);
/* 133 */       ImageMIPMap pyramid = new ImageMIPMap(source, downSampler);
/* 136 */       Vector sourceVector = new Vector();
/* 137 */       RenderedImage currentImage = pyramid.getCurrentImage();
/* 138 */       sourceVector.add(currentImage);
/* 140 */       while (currentImage.getWidth() > maxLowResDim || currentImage.getHeight() > maxLowResDim) {
/* 141 */         RenderedImage nextImage = pyramid.getDownImage();
/* 142 */         if (nextImage.getWidth() >= currentImage.getWidth() || nextImage.getHeight() >= currentImage.getHeight())
/* 144 */           throw new IllegalArgumentException(JaiI18N.getString("RenderableCRIF0")); 
/* 146 */         sourceVector.add(nextImage);
/* 147 */         currentImage = nextImage;
/*     */       } 
/* 151 */       multiResolutionRenderableImage = new MultiResolutionRenderableImage(sourceVector, minX, minY, height);
/* 155 */       this.mresTable.put(key, new SoftReference(multiResolutionRenderableImage));
/*     */     } 
/* 158 */     return (RenderableImage)multiResolutionRenderableImage;
/*     */   }
/*     */   
/*     */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 167 */     return paramBlock.getRenderedSource(0);
/*     */   }
/*     */   
/*     */   public RenderedImage create(RenderContext renderContext, ParameterBlock paramBlock) {
/* 177 */     RenderableImage mres = createRenderable(paramBlock);
/* 179 */     return mres.createRendering(renderContext);
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D(ParameterBlock paramBlock) {
/* 187 */     RenderableImage mres = createRenderable(paramBlock);
/* 189 */     return new Rectangle2D.Float(mres.getMinX(), mres.getMinY(), mres.getWidth(), mres.getHeight());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\RenderableCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */