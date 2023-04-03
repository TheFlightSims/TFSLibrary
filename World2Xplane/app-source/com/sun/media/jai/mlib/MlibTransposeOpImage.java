/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.opimage.TransposeOpImage;
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PlanarImage;
/*     */ 
/*     */ final class MlibTransposeOpImage extends TransposeOpImage {
/*     */   public MlibTransposeOpImage(RenderedImage source, Map config, ImageLayout layout, int type) {
/*  58 */     super(source, config, layout, type);
/*     */   }
/*     */   
/*     */   public Raster computeTile(int tileX, int tileY) {
/*  65 */     Point org = new Point(tileXToX(tileX), tileYToY(tileY));
/*  66 */     WritableRaster dest = createWritableRaster(this.sampleModel, org);
/*  71 */     Rectangle destRect = getTileRect(tileX, tileY).intersection(getBounds());
/*  77 */     PlanarImage src = getSourceImage(0);
/*  82 */     Rectangle srcRect = mapDestRect(destRect, 0).intersection(src.getBounds());
/*  85 */     Raster[] sources = new Raster[1];
/*  86 */     sources[0] = src.getData(srcRect);
/*  91 */     computeRect(sources, dest, destRect);
/*  94 */     if (src.overlapsMultipleTiles(srcRect))
/*  95 */       recycleTile(sources[0]); 
/*  98 */     return dest;
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     mediaLibImage[] srcML, dstML;
/* 104 */     Raster source = sources[0];
/* 106 */     Rectangle srcRect = source.getBounds();
/* 108 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 110 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, formatTag);
/* 112 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/* 114 */     int numBands = getSampleModel().getNumBands();
/* 118 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 123 */         srcML = srcAccessor.getMediaLibImages();
/* 124 */         dstML = dstAccessor.getMediaLibImages();
/* 125 */         switch (this.type) {
/*     */           case 0:
/* 127 */             Image.FlipX(dstML[0], srcML[0]);
/*     */             break;
/*     */           case 1:
/* 131 */             Image.FlipY(dstML[0], srcML[0]);
/*     */             break;
/*     */           case 2:
/* 135 */             Image.FlipMainDiag(dstML[0], srcML[0]);
/*     */             break;
/*     */           case 3:
/* 139 */             Image.FlipAntiDiag(dstML[0], srcML[0]);
/*     */             break;
/*     */           case 4:
/* 143 */             Image.Rotate90(dstML[0], srcML[0]);
/*     */             break;
/*     */           case 5:
/* 147 */             Image.Rotate180(dstML[0], srcML[0]);
/*     */             break;
/*     */           case 6:
/* 151 */             Image.Rotate270(dstML[0], srcML[0]);
/*     */             break;
/*     */         } 
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/* 158 */         srcML = srcAccessor.getMediaLibImages();
/* 159 */         dstML = dstAccessor.getMediaLibImages();
/* 160 */         switch (this.type) {
/*     */           case 0:
/* 162 */             Image.FlipX_Fp(dstML[0], srcML[0]);
/*     */             break;
/*     */           case 1:
/* 166 */             Image.FlipY_Fp(dstML[0], srcML[0]);
/*     */             break;
/*     */           case 2:
/* 170 */             Image.FlipMainDiag_Fp(dstML[0], srcML[0]);
/*     */             break;
/*     */           case 3:
/* 174 */             Image.FlipAntiDiag_Fp(dstML[0], srcML[0]);
/*     */             break;
/*     */           case 4:
/* 178 */             Image.Rotate90_Fp(dstML[0], srcML[0]);
/*     */             break;
/*     */           case 5:
/* 182 */             Image.Rotate180_Fp(dstML[0], srcML[0]);
/*     */             break;
/*     */           case 6:
/* 186 */             Image.Rotate270_Fp(dstML[0], srcML[0]);
/*     */             break;
/*     */         } 
/*     */         break;
/*     */       default:
/* 192 */         throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */     } 
/* 200 */     if (dstAccessor.isDataCopy())
/* 201 */       dstAccessor.copyDataToRaster(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibTransposeOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */