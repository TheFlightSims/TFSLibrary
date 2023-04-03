/*     */ package com.sun.media.jai.iterator;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.DataBuffer;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.iterator.RookIter;
/*     */ 
/*     */ public class RookIterFallback implements RookIter {
/*     */   protected RenderedImage im;
/*     */   
/*     */   protected Rectangle bounds;
/*     */   
/*     */   protected SampleModel sampleModel;
/*     */   
/*     */   protected int numBands;
/*     */   
/*     */   protected int tileWidth;
/*     */   
/*     */   protected int tileHeight;
/*     */   
/*     */   protected int tileGridXOffset;
/*     */   
/*     */   protected int tileGridYOffset;
/*     */   
/*     */   protected int startTileX;
/*     */   
/*     */   protected int endTileX;
/*     */   
/*     */   protected int startTileY;
/*     */   
/*     */   protected int endTileY;
/*     */   
/*     */   protected int tileXStart;
/*     */   
/*     */   protected int tileXEnd;
/*     */   
/*     */   protected int tileYStart;
/*     */   
/*     */   protected int tileYEnd;
/*     */   
/*     */   protected int prevXBoundary;
/*     */   
/*     */   protected int nextXBoundary;
/*     */   
/*     */   protected int prevYBoundary;
/*     */   
/*     */   protected int nextYBoundary;
/*     */   
/*     */   protected int tileX;
/*     */   
/*     */   protected int tileY;
/*     */   
/*     */   protected int firstX;
/*     */   
/*     */   protected int firstY;
/*     */   
/*     */   protected int lastX;
/*     */   
/*     */   protected int lastY;
/*     */   
/*     */   protected int x;
/*     */   
/*     */   protected int y;
/*     */   
/*     */   protected int localX;
/*     */   
/*     */   protected int localY;
/*     */   
/*     */   protected int b;
/*     */   
/* 119 */   protected DataBuffer dataBuffer = null;
/*     */   
/*     */   public RookIterFallback(RenderedImage im, Rectangle bounds) {
/* 122 */     this.im = im;
/* 123 */     this.bounds = bounds;
/* 125 */     this.sampleModel = im.getSampleModel();
/* 126 */     this.numBands = this.sampleModel.getNumBands();
/* 128 */     this.tileGridXOffset = im.getTileGridXOffset();
/* 129 */     this.tileGridYOffset = im.getTileGridYOffset();
/* 130 */     this.tileWidth = im.getTileWidth();
/* 131 */     this.tileHeight = im.getTileHeight();
/* 133 */     this.startTileX = PlanarImage.XToTileX(bounds.x, this.tileGridXOffset, this.tileWidth);
/* 136 */     this.endTileX = PlanarImage.XToTileX(bounds.x + bounds.width - 1, this.tileGridXOffset, this.tileWidth);
/* 139 */     this.startTileY = PlanarImage.YToTileY(bounds.y, this.tileGridYOffset, this.tileHeight);
/* 142 */     this.endTileY = PlanarImage.YToTileY(bounds.y + bounds.height - 1, this.tileGridYOffset, this.tileHeight);
/* 146 */     this.tileX = this.startTileX;
/* 147 */     this.tileY = this.startTileY;
/* 149 */     this.firstX = bounds.x;
/* 150 */     this.firstY = bounds.y;
/* 151 */     this.lastX = bounds.x + bounds.width - 1;
/* 152 */     this.lastY = bounds.y + bounds.height - 1;
/* 154 */     this.x = bounds.x;
/* 155 */     this.y = bounds.y;
/* 156 */     this.b = 0;
/* 158 */     setTileXBounds();
/* 159 */     setTileYBounds();
/* 160 */     setDataBuffer();
/*     */   }
/*     */   
/*     */   private final void setTileXBounds() {
/* 164 */     this.tileXStart = this.tileX * this.tileWidth + this.tileGridXOffset;
/* 165 */     this.tileXEnd = this.tileXStart + this.tileWidth - 1;
/* 166 */     this.localX = this.x - this.tileXStart;
/* 168 */     this.prevXBoundary = Math.max(this.tileXStart, this.firstX);
/* 169 */     this.nextXBoundary = Math.min(this.tileXEnd, this.lastX);
/*     */   }
/*     */   
/*     */   private final void setTileYBounds() {
/* 173 */     this.tileYStart = this.tileY * this.tileHeight + this.tileGridYOffset;
/* 174 */     this.tileYEnd = this.tileYStart + this.tileHeight - 1;
/* 175 */     this.localY = this.y - this.tileYStart;
/* 177 */     this.prevYBoundary = Math.max(this.tileYStart, this.firstY);
/* 178 */     this.nextYBoundary = Math.min(this.tileYEnd, this.lastY);
/*     */   }
/*     */   
/*     */   private final void setDataBuffer() {
/* 182 */     this.dataBuffer = this.im.getTile(this.tileX, this.tileY).getDataBuffer();
/*     */   }
/*     */   
/*     */   public void startLines() {
/* 186 */     this.y = this.firstY;
/* 187 */     this.localY = this.y - this.tileYStart;
/* 188 */     this.tileY = this.startTileY;
/* 189 */     setTileYBounds();
/* 190 */     setDataBuffer();
/*     */   }
/*     */   
/*     */   public void endLines() {
/* 194 */     this.y = this.lastY;
/* 195 */     this.localY = this.y - this.tileYStart;
/* 196 */     this.tileY = this.endTileY;
/* 197 */     setTileYBounds();
/* 198 */     setDataBuffer();
/*     */   }
/*     */   
/*     */   public void nextLine() {
/* 202 */     this.y++;
/* 203 */     this.localY++;
/*     */   }
/*     */   
/*     */   public void prevLine() {
/* 207 */     this.y--;
/* 208 */     this.localY--;
/*     */   }
/*     */   
/*     */   public void jumpLines(int num) {
/* 212 */     this.y += num;
/* 213 */     this.localY += num;
/* 215 */     if (this.y < this.tileYStart || this.y > this.tileYEnd) {
/* 216 */       this.tileY = PlanarImage.YToTileY(this.y, this.tileGridYOffset, this.tileHeight);
/* 219 */       setTileYBounds();
/* 220 */       setDataBuffer();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean finishedLines() {
/* 225 */     if (this.y > this.nextYBoundary) {
/* 226 */       if (this.y > this.lastY)
/* 227 */         return true; 
/* 229 */       this.tileY++;
/* 230 */       this.tileYStart += this.tileHeight;
/* 231 */       this.tileYEnd += this.tileHeight;
/* 232 */       this.localY -= this.tileHeight;
/* 233 */       this.prevYBoundary = Math.max(this.tileYStart, this.firstY);
/* 234 */       this.nextYBoundary = Math.min(this.tileYEnd, this.lastY);
/* 236 */       setDataBuffer();
/* 237 */       return false;
/*     */     } 
/* 240 */     return false;
/*     */   }
/*     */   
/*     */   public boolean finishedLinesTop() {
/* 245 */     if (this.y < this.prevYBoundary) {
/* 246 */       if (this.y < this.firstY)
/* 247 */         return true; 
/* 249 */       this.tileY--;
/* 250 */       this.tileYStart -= this.tileHeight;
/* 251 */       this.tileYEnd -= this.tileHeight;
/* 252 */       this.localY += this.tileHeight;
/* 253 */       this.prevYBoundary = Math.max(this.tileYStart, this.firstY);
/* 254 */       this.nextYBoundary = Math.min(this.tileYEnd, this.lastY);
/* 256 */       setDataBuffer();
/* 257 */       return false;
/*     */     } 
/* 260 */     return false;
/*     */   }
/*     */   
/*     */   public boolean nextLineDone() {
/* 265 */     nextLine();
/* 266 */     return finishedLines();
/*     */   }
/*     */   
/*     */   public boolean prevLineDone() {
/* 270 */     prevLine();
/* 271 */     return finishedLinesTop();
/*     */   }
/*     */   
/*     */   public void startPixels() {
/* 275 */     this.x = this.firstX;
/* 276 */     this.localX = this.x - this.tileXStart;
/* 277 */     this.tileX = this.startTileX;
/* 278 */     setTileXBounds();
/* 279 */     setDataBuffer();
/*     */   }
/*     */   
/*     */   public void endPixels() {
/* 283 */     this.x = this.lastX;
/* 284 */     this.tileX = this.endTileX;
/* 285 */     setTileXBounds();
/* 286 */     setDataBuffer();
/*     */   }
/*     */   
/*     */   public void nextPixel() {
/* 290 */     this.x++;
/* 291 */     this.localX++;
/*     */   }
/*     */   
/*     */   public void prevPixel() {
/* 295 */     this.x--;
/* 296 */     this.localX--;
/*     */   }
/*     */   
/*     */   public void jumpPixels(int num) {
/* 300 */     this.x += num;
/* 301 */     this.localX += num;
/* 303 */     if (this.x < this.tileXStart || this.x > this.tileXEnd) {
/* 304 */       this.tileX = PlanarImage.XToTileX(this.x, this.tileGridXOffset, this.tileWidth);
/* 307 */       setTileXBounds();
/* 308 */       setDataBuffer();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean finishedPixels() {
/* 313 */     if (this.x > this.nextXBoundary) {
/* 314 */       if (this.x > this.lastX)
/* 315 */         return true; 
/* 317 */       this.tileX++;
/* 318 */       this.tileXStart += this.tileWidth;
/* 319 */       this.tileXEnd += this.tileWidth;
/* 320 */       this.localX -= this.tileWidth;
/* 321 */       this.prevXBoundary = Math.max(this.tileXStart, this.firstX);
/* 322 */       this.nextXBoundary = Math.min(this.tileXEnd, this.lastX);
/* 324 */       setDataBuffer();
/* 325 */       return false;
/*     */     } 
/* 328 */     return false;
/*     */   }
/*     */   
/*     */   public boolean finishedPixelsLeft() {
/* 333 */     if (this.x < this.prevXBoundary) {
/* 334 */       if (this.x < this.firstX)
/* 335 */         return true; 
/* 337 */       this.tileX--;
/* 338 */       this.tileXStart -= this.tileWidth;
/* 339 */       this.tileXEnd -= this.tileWidth;
/* 340 */       this.localX += this.tileWidth;
/* 341 */       this.prevXBoundary = Math.max(this.tileXStart, this.firstX);
/* 342 */       this.nextXBoundary = Math.min(this.tileXEnd, this.lastX);
/* 344 */       setDataBuffer();
/* 345 */       return false;
/*     */     } 
/* 348 */     return false;
/*     */   }
/*     */   
/*     */   public boolean nextPixelDone() {
/* 353 */     nextPixel();
/* 354 */     return finishedPixels();
/*     */   }
/*     */   
/*     */   public boolean prevPixelDone() {
/* 358 */     prevPixel();
/* 359 */     return finishedPixelsLeft();
/*     */   }
/*     */   
/*     */   public void startBands() {
/* 363 */     this.b = 0;
/*     */   }
/*     */   
/*     */   public void endBands() {
/* 367 */     this.b = this.numBands - 1;
/*     */   }
/*     */   
/*     */   public void prevBand() {
/* 371 */     this.b--;
/*     */   }
/*     */   
/*     */   public void nextBand() {
/* 375 */     this.b++;
/*     */   }
/*     */   
/*     */   public boolean prevBandDone() {
/* 379 */     return (--this.b < 0);
/*     */   }
/*     */   
/*     */   public boolean nextBandDone() {
/* 383 */     return (++this.b >= this.numBands);
/*     */   }
/*     */   
/*     */   public boolean finishedBands() {
/* 387 */     return (this.b >= this.numBands);
/*     */   }
/*     */   
/*     */   public int getSample() {
/* 391 */     return this.sampleModel.getSample(this.localX, this.localY, this.b, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public int getSample(int b) {
/* 395 */     return this.sampleModel.getSample(this.localX, this.localY, b, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public float getSampleFloat() {
/* 399 */     return this.sampleModel.getSampleFloat(this.localX, this.localY, this.b, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public float getSampleFloat(int b) {
/* 403 */     return this.sampleModel.getSampleFloat(this.localX, this.localY, b, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public double getSampleDouble() {
/* 407 */     return this.sampleModel.getSampleDouble(this.localX, this.localY, this.b, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public double getSampleDouble(int b) {
/* 411 */     return this.sampleModel.getSampleDouble(this.localX, this.localY, b, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public int[] getPixel(int[] iArray) {
/* 415 */     return this.sampleModel.getPixel(this.localX, this.localY, iArray, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public float[] getPixel(float[] fArray) {
/* 419 */     return this.sampleModel.getPixel(this.localX, this.localY, fArray, this.dataBuffer);
/*     */   }
/*     */   
/*     */   public double[] getPixel(double[] dArray) {
/* 423 */     return this.sampleModel.getPixel(this.localX, this.localY, dArray, this.dataBuffer);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\iterator\RookIterFallback.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */