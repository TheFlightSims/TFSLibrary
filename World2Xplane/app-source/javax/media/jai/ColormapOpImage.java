/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.IndexColorModel;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.Map;
/*     */ 
/*     */ public abstract class ColormapOpImage extends PointOpImage {
/*     */   private boolean isInitialized = false;
/*     */   
/*     */   private boolean isColormapAccelerated;
/*     */   
/*     */   public ColormapOpImage(RenderedImage source, ImageLayout layout, Map configuration, boolean cobbleSources) {
/*  92 */     super(source, layout, configuration, cobbleSources);
/* 110 */     this.isColormapAccelerated = true;
/* 111 */     Boolean value = (configuration == null) ? Boolean.TRUE : (Boolean)configuration.get(JAI.KEY_TRANSFORM_ON_COLORMAP);
/* 114 */     if (value != null)
/* 115 */       this.isColormapAccelerated = value.booleanValue(); 
/*     */   }
/*     */   
/*     */   protected final boolean isColormapOperation() {
/* 122 */     return this.isColormapAccelerated;
/*     */   }
/*     */   
/*     */   protected final void initializeColormapOperation() {
/* 136 */     ColorModel srcCM = getSource(0).getColorModel();
/* 137 */     ColorModel dstCM = getColorModel();
/* 140 */     this.isColormapAccelerated &= (srcCM != null && dstCM != null && srcCM instanceof IndexColorModel && dstCM instanceof IndexColorModel) ? 1 : 0;
/* 146 */     this.isInitialized = true;
/* 149 */     if (this.isColormapAccelerated) {
/* 151 */       IndexColorModel icm = (IndexColorModel)dstCM;
/* 154 */       int mapSize = icm.getMapSize();
/* 155 */       byte[][] colormap = new byte[3][mapSize];
/* 158 */       icm.getReds(colormap[0]);
/* 159 */       icm.getGreens(colormap[1]);
/* 160 */       icm.getBlues(colormap[2]);
/* 163 */       transformColormap(colormap);
/* 168 */       for (int b = 0; b < 3; b++) {
/* 169 */         int maxComponent = 255 >> 8 - icm.getComponentSize(b);
/* 170 */         if (maxComponent < 255) {
/* 171 */           byte[] map = colormap[b];
/* 172 */           for (int i = 0; i < mapSize; i++) {
/* 173 */             if ((map[i] & 0xFF) > maxComponent)
/* 174 */               map[i] = (byte)maxComponent; 
/*     */           } 
/*     */         } 
/*     */       } 
/* 181 */       byte[] reds = colormap[0];
/* 182 */       byte[] greens = colormap[1];
/* 183 */       byte[] blues = colormap[2];
/* 186 */       int[] rgb = new int[mapSize];
/* 189 */       if (icm.hasAlpha()) {
/* 190 */         byte[] alphas = new byte[mapSize];
/* 191 */         icm.getAlphas(alphas);
/* 192 */         for (int i = 0; i < mapSize; i++)
/* 193 */           rgb[i] = (alphas[i] & 0xFF) << 24 | (reds[i] & 0xFF) << 16 | (greens[i] & 0xFF) << 8 | blues[i] & 0xFF; 
/*     */       } else {
/* 200 */         for (int i = 0; i < mapSize; i++)
/* 201 */           rgb[i] = (reds[i] & 0xFF) << 16 | (greens[i] & 0xFF) << 8 | blues[i] & 0xFF; 
/*     */       } 
/* 209 */       this.colorModel = new IndexColorModel(icm.getPixelSize(), mapSize, rgb, 0, icm.hasAlpha(), icm.getTransparentPixel(), this.sampleModel.getTransferType());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract void transformColormap(byte[][] paramArrayOfbyte);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\ColormapOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */