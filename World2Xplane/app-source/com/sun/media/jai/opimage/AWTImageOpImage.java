/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Canvas;
/*     */ import java.awt.Image;
/*     */ import java.awt.MediaTracker;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.DataBuffer;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.awt.image.PixelGrabber;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SinglePixelPackedSampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFactory;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ import javax.media.jai.SourcelessOpImage;
/*     */ 
/*     */ final class AWTImageOpImage extends SourcelessOpImage {
/*     */   private int[] pixels;
/*     */   
/*  70 */   private RasterFormatTag rasterFormatTag = null;
/*     */   
/*     */   private static final ImageLayout layoutHelper(ImageLayout layout, Image image) {
/*  75 */     MediaTracker tracker = new MediaTracker(new Canvas());
/*  76 */     tracker.addImage(image, 0);
/*     */     try {
/*  78 */       tracker.waitForID(0);
/*  79 */     } catch (InterruptedException e) {
/*  80 */       e.printStackTrace();
/*  81 */       throw new RuntimeException(JaiI18N.getString("AWTImageOpImage0"));
/*     */     } 
/*  83 */     if (tracker.isErrorID(0))
/*  84 */       throw new RuntimeException(JaiI18N.getString("AWTImageOpImage1")); 
/*  86 */     tracker.removeImage(image);
/*  89 */     if (layout == null)
/*  89 */       layout = new ImageLayout(); 
/*  92 */     layout.setMinX(0);
/*  93 */     layout.setMinY(0);
/*  94 */     layout.setWidth(image.getWidth(null));
/*  95 */     layout.setHeight(image.getHeight(null));
/*  98 */     if (!layout.isValid(64))
/*  99 */       layout.setTileWidth(layout.getWidth(null)); 
/* 101 */     if (!layout.isValid(128))
/* 102 */       layout.setTileHeight(layout.getHeight(null)); 
/* 107 */     if (layout.getTileWidth(null) == layout.getWidth(null) && layout.getTileHeight(null) == layout.getHeight(null)) {
/* 110 */       layout.setTileGridXOffset(layout.getMinX(null));
/* 111 */       layout.setTileGridYOffset(layout.getMinY(null));
/* 113 */       int[] bitMasks = { 16711680, 65280, 255 };
/* 114 */       layout.setSampleModel(new SinglePixelPackedSampleModel(3, layout.getWidth(null), layout.getHeight(null), bitMasks));
/*     */     } else {
/* 120 */       layout.setSampleModel(RasterFactory.createPixelInterleavedSampleModel(0, layout.getTileWidth(null), layout.getTileHeight(null), 3));
/*     */     } 
/* 127 */     layout.setColorModel(PlanarImage.createColorModel(layout.getSampleModel(null)));
/* 129 */     return layout;
/*     */   }
/*     */   
/*     */   public AWTImageOpImage(Map config, ImageLayout layout, Image image) {
/* 142 */     super(layout = layoutHelper(layout, image), config, layout.getSampleModel(null), layout.getMinX(null), layout.getMinY(null), layout.getWidth(null), layout.getHeight(null));
/* 148 */     if (getTileWidth() != getWidth() || getTileHeight() != getHeight())
/* 149 */       this.rasterFormatTag = new RasterFormatTag(getSampleModel(), 0); 
/* 155 */     this.pixels = new int[this.width * this.height];
/* 156 */     PixelGrabber grabber = new PixelGrabber(image, 0, 0, this.width, this.height, this.pixels, 0, this.width);
/*     */     try {
/* 159 */       if (!grabber.grabPixels()) {
/* 160 */         if ((grabber.getStatus() & 0x80) != 0)
/* 161 */           throw new RuntimeException(JaiI18N.getString("AWTImageOpImage2")); 
/* 163 */         throw new RuntimeException(grabber.getStatus() + JaiI18N.getString("AWTImageOpImage3"));
/*     */       } 
/* 166 */     } catch (InterruptedException e) {
/* 167 */       e.printStackTrace();
/* 168 */       throw new RuntimeException(JaiI18N.getString("AWTImageOpImage4"));
/*     */     } 
/*     */   }
/*     */   
/*     */   public Raster computeTile(int tileX, int tileY) {
/* 173 */     if (getTileWidth() == getWidth() && getTileHeight() == getHeight()) {
/* 174 */       DataBuffer dataBuffer = new DataBufferInt(this.pixels, this.pixels.length);
/* 175 */       return Raster.createWritableRaster(getSampleModel(), dataBuffer, new Point(tileXToX(tileX), tileYToY(tileY)));
/*     */     } 
/* 181 */     return super.computeTile(tileX, tileY);
/*     */   }
/*     */   
/*     */   protected void computeRect(PlanarImage[] sources, WritableRaster dest, Rectangle destRect) {
/* 187 */     RasterAccessor dst = new RasterAccessor(dest, destRect, this.rasterFormatTag, null);
/* 190 */     int dwidth = dst.getWidth();
/* 191 */     int dheight = dst.getHeight();
/* 193 */     int lineStride = dst.getScanlineStride();
/* 194 */     int pixelStride = dst.getPixelStride();
/* 196 */     int lineOffset0 = dst.getBandOffset(0);
/* 197 */     int lineOffset1 = dst.getBandOffset(1);
/* 198 */     int lineOffset2 = dst.getBandOffset(2);
/* 200 */     byte[] data = dst.getByteDataArray(0);
/* 202 */     int offset = (destRect.y - this.minY) * this.width + destRect.x - this.minX;
/* 204 */     for (int h = 0; h < dheight; h++) {
/* 205 */       int pixelOffset0 = lineOffset0;
/* 206 */       int pixelOffset1 = lineOffset1;
/* 207 */       int pixelOffset2 = lineOffset2;
/* 209 */       lineOffset0 += lineStride;
/* 210 */       lineOffset1 += lineStride;
/* 211 */       lineOffset2 += lineStride;
/* 213 */       int i = offset;
/* 214 */       offset += this.width;
/* 216 */       for (int w = 0; w < dwidth; w++) {
/* 217 */         data[pixelOffset0] = (byte)(this.pixels[i] >> 16 & 0xFF);
/* 218 */         data[pixelOffset1] = (byte)(this.pixels[i] >> 8 & 0xFF);
/* 219 */         data[pixelOffset2] = (byte)(this.pixels[i] & 0xFF);
/* 221 */         pixelOffset0 += pixelStride;
/* 222 */         pixelOffset1 += pixelStride;
/* 223 */         pixelOffset2 += pixelStride;
/* 224 */         i++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\AWTImageOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */