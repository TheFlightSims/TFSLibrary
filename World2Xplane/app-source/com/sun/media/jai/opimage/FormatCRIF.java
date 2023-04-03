/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.JDKWorkarounds;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.CRIFImpl;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.NullOpImage;
/*     */ import javax.media.jai.RasterFactory;
/*     */ 
/*     */ public class FormatCRIF extends CRIFImpl {
/*     */   public FormatCRIF() {
/*  41 */     super("format");
/*     */   }
/*     */   
/*     */   public RenderedImage create(ParameterBlock args, RenderingHints renderHints) {
/*  55 */     RenderedImage src = args.getRenderedSource(0);
/*  56 */     Integer datatype = (Integer)args.getObjectParameter(0);
/*  57 */     int type = datatype.intValue();
/*  60 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/*  63 */     if (layout == null && type == src.getSampleModel().getDataType())
/*  64 */       return src; 
/*  68 */     if (layout == null) {
/*  69 */       layout = new ImageLayout(src);
/*     */     } else {
/*  71 */       layout = (ImageLayout)layout.clone();
/*     */     } 
/*  74 */     boolean isDataTypeChange = false;
/*  77 */     SampleModel sampleModel = layout.getSampleModel(src);
/*  80 */     if (sampleModel.getDataType() != type) {
/*  81 */       int tileWidth = layout.getTileWidth(src);
/*  82 */       int tileHeight = layout.getTileHeight(src);
/*  83 */       int numBands = src.getSampleModel().getNumBands();
/*  85 */       SampleModel csm = RasterFactory.createComponentSampleModel(sampleModel, type, tileWidth, tileHeight, numBands);
/*  92 */       layout.setSampleModel(csm);
/*  93 */       isDataTypeChange = true;
/*     */     } 
/*  98 */     ColorModel colorModel = layout.getColorModel(null);
/*  99 */     if (colorModel != null && !JDKWorkarounds.areCompatibleDataModels(layout.getSampleModel(src), colorModel))
/* 103 */       layout.unsetValid(512); 
/* 107 */     if (layout.getSampleModel(src) == src.getSampleModel() && layout.getMinX(src) == src.getMinX() && layout.getMinY(src) == src.getMinY() && layout.getWidth(src) == src.getWidth() && layout.getHeight(src) == src.getHeight() && layout.getTileWidth(src) == src.getTileWidth() && layout.getTileHeight(src) == src.getTileHeight() && layout.getTileGridXOffset(src) == src.getTileGridXOffset() && layout.getTileGridYOffset(src) == src.getTileGridYOffset()) {
/* 117 */       if (layout.getColorModel(src) == src.getColorModel())
/* 119 */         return src; 
/* 122 */       RenderingHints hints = renderHints;
/* 123 */       if (hints != null && hints.containsKey(JAI.KEY_TILE_CACHE)) {
/* 124 */         hints = new RenderingHints(renderHints);
/* 125 */         hints.remove(JAI.KEY_TILE_CACHE);
/*     */       } 
/* 129 */       return (RenderedImage)new NullOpImage(src, layout, hints, 2);
/*     */     } 
/* 134 */     if (isDataTypeChange == true)
/* 137 */       if (renderHints == null) {
/* 138 */         renderHints = new RenderingHints(JAI.KEY_REPLACE_INDEX_COLOR_MODEL, Boolean.TRUE);
/* 142 */       } else if (!renderHints.containsKey(JAI.KEY_REPLACE_INDEX_COLOR_MODEL)) {
/* 146 */         renderHints.put(JAI.KEY_REPLACE_INDEX_COLOR_MODEL, Boolean.TRUE);
/*     */       }  
/* 151 */     return (RenderedImage)new CopyOpImage(src, renderHints, layout);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\FormatCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */