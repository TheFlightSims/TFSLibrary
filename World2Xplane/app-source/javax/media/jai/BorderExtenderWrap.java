/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.WritableRaster;
/*     */ 
/*     */ public class BorderExtenderWrap extends BorderExtender {
/*     */   public final void extend(WritableRaster raster, PlanarImage im) {
/*  74 */     if (raster == null || im == null)
/*  75 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  78 */     int width = raster.getWidth();
/*  79 */     int height = raster.getHeight();
/*  81 */     int minX = raster.getMinX();
/*  82 */     int maxX = minX + width;
/*  83 */     int minY = raster.getMinY();
/*  84 */     int maxY = minY + height;
/*  86 */     int imMinX = im.getMinX();
/*  87 */     int imMinY = im.getMinY();
/*  88 */     int imWidth = im.getWidth();
/*  89 */     int imHeight = im.getHeight();
/*  91 */     Rectangle rect = new Rectangle();
/*  98 */     int minTileX = PlanarImage.XToTileX(minX, imMinX, imWidth);
/*  99 */     int maxTileX = PlanarImage.XToTileX(maxX - 1, imMinX, imWidth);
/* 100 */     int minTileY = PlanarImage.YToTileY(minY, imMinY, imHeight);
/* 101 */     int maxTileY = PlanarImage.YToTileY(maxY - 1, imMinY, imHeight);
/* 104 */     for (int tileY = minTileY; tileY <= maxTileY; tileY++) {
/* 105 */       int ty = tileY * imHeight + imMinY;
/* 106 */       for (int tileX = minTileX; tileX <= maxTileX; tileX++) {
/* 107 */         int tx = tileX * imWidth + imMinX;
/* 110 */         if (tileX != 0 || tileY != 0) {
/* 116 */           rect.x = tx;
/* 117 */           rect.y = ty;
/* 118 */           rect.width = imWidth;
/* 119 */           rect.height = imHeight;
/* 121 */           int xOffset = 0;
/* 122 */           if (rect.x < minX) {
/* 123 */             xOffset = minX - rect.x;
/* 124 */             rect.x = minX;
/* 125 */             rect.width -= xOffset;
/*     */           } 
/* 127 */           int yOffset = 0;
/* 128 */           if (rect.y < minY) {
/* 129 */             yOffset = minY - rect.y;
/* 130 */             rect.y = minY;
/* 131 */             rect.height -= yOffset;
/*     */           } 
/* 133 */           if (rect.x + rect.width > maxX)
/* 134 */             rect.width = maxX - rect.x; 
/* 136 */           if (rect.y + rect.height > maxY)
/* 137 */             rect.height = maxY - rect.y; 
/* 142 */           WritableRaster child = RasterFactory.createWritableChild(raster, rect.x, rect.y, rect.width, rect.height, imMinX + xOffset, imMinY + yOffset, null);
/* 151 */           im.copyData(child);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\BorderExtenderWrap.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */