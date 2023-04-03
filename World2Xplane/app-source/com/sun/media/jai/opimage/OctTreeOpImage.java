/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.LinkedList;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.LookupTableJAI;
/*     */ import javax.media.jai.PixelAccessor;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.ROI;
/*     */ import javax.media.jai.ROIShape;
/*     */ import javax.media.jai.UnpackedImageData;
/*     */ 
/*     */ public class OctTreeOpImage extends ColorQuantizerOpImage {
/*     */   private int treeSize;
/*     */   
/*  86 */   private int maxTreeDepth = 8;
/*     */   
/*  92 */   private int[] squares = new int[(this.maxColorNum << 1) + 1];
/*     */   
/*     */   public OctTreeOpImage(RenderedImage source, Map config, ImageLayout layout, int maxColorNum, int upperBound, ROI roi, int xPeriod, int yPeriod) {
/* 111 */     super(source, config, layout, maxColorNum, roi, xPeriod, yPeriod);
/*     */     for (int i = -this.maxColorNum; i <= this.maxColorNum; i++)
/*     */       this.squares[i + this.maxColorNum] = i * i; 
/* 113 */     this.colorMap = null;
/* 114 */     this.treeSize = upperBound;
/*     */   }
/*     */   
/*     */   protected synchronized void train() {
/* 118 */     Cube cube = new Cube(this, getSourceImage(0), this.maxColorNum);
/* 119 */     cube.constructTree();
/* 120 */     cube.reduction();
/* 121 */     cube.assignment();
/* 123 */     this.colorMap = new LookupTableJAI(cube.colormap);
/* 124 */     setProperty("LUT", this.colorMap);
/* 125 */     setProperty("JAI.LookupTable", this.colorMap);
/*     */   }
/*     */   
/*     */   class Cube {
/*     */     PlanarImage source;
/*     */     
/*     */     int max_colors;
/*     */     
/*     */     byte[][] colormap;
/*     */     
/*     */     Node root;
/*     */     
/*     */     int depth;
/*     */     
/*     */     int colors;
/*     */     
/*     */     int nodes;
/*     */     
/*     */     private final OctTreeOpImage this$0;
/*     */     
/*     */     Cube(OctTreeOpImage this$0, PlanarImage source, int max_colors) {
/* 143 */       this.this$0 = this$0;
/*     */       this.colormap = new byte[3][];
/* 144 */       this.source = source;
/* 145 */       this.max_colors = max_colors;
/* 147 */       int i = max_colors;
/* 150 */       for (this.depth = 0; i != 0; this.depth++)
/* 151 */         i >>>= 1; 
/* 154 */       if (this.depth > this$0.maxTreeDepth) {
/* 155 */         this.depth = this$0.maxTreeDepth;
/* 156 */       } else if (this.depth < 2) {
/* 157 */         this.depth = 2;
/*     */       } 
/* 160 */       this.root = new Node(this, this);
/*     */     }
/*     */     
/*     */     void constructTree() {
/* 164 */       if (this.this$0.roi == null)
/* 165 */         this.this$0.roi = (ROI)new ROIShape(this.source.getBounds()); 
/* 168 */       int minTileX = this.source.getMinTileX();
/* 169 */       int maxTileX = this.source.getMaxTileX();
/* 170 */       int minTileY = this.source.getMinTileY();
/* 171 */       int maxTileY = this.source.getMaxTileY();
/* 172 */       int xStart = this.source.getMinX();
/* 173 */       int yStart = this.source.getMinY();
/* 175 */       for (int y = minTileY; y <= maxTileY; y++) {
/* 176 */         for (int x = minTileX; x <= maxTileX; x++) {
/* 180 */           Rectangle tileRect = this.source.getTileRect(x, y);
/* 183 */           if (this.this$0.roi.intersects(tileRect)) {
/* 186 */             if (this.this$0.checkForSkippedTiles && tileRect.x >= xStart && tileRect.y >= yStart) {
/* 190 */               int offsetX = (this.this$0.xPeriod - (tileRect.x - xStart) % this.this$0.xPeriod) % this.this$0.xPeriod;
/* 193 */               int offsetY = (this.this$0.yPeriod - (tileRect.y - yStart) % this.this$0.yPeriod) % this.this$0.yPeriod;
/* 199 */               if (offsetX >= tileRect.width || offsetY >= tileRect.height)
/*     */                 continue; 
/*     */             } 
/* 205 */             constructTree(this.source.getData(tileRect));
/*     */           } 
/*     */           continue;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     private void constructTree(Raster source) {
/*     */       LinkedList rectList;
/* 212 */       if (!this.this$0.isInitialized) {
/* 213 */         this.this$0.srcPA = new PixelAccessor((RenderedImage)this.this$0.getSourceImage(0));
/* 214 */         this.this$0.srcSampleType = (this.this$0.srcPA.sampleType == -1) ? 0 : this.this$0.srcPA.sampleType;
/* 216 */         this.this$0.isInitialized = true;
/*     */       } 
/* 219 */       Rectangle srcBounds = this.this$0.getSourceImage(0).getBounds().intersection(source.getBounds());
/* 223 */       if (this.this$0.roi == null) {
/* 224 */         rectList = new LinkedList();
/* 225 */         rectList.addLast(srcBounds);
/*     */       } else {
/* 227 */         rectList = this.this$0.roi.getAsRectangleList(srcBounds.x, srcBounds.y, srcBounds.width, srcBounds.height);
/* 231 */         if (rectList == null)
/*     */           return; 
/*     */       } 
/* 235 */       ListIterator iterator = rectList.listIterator(0);
/* 236 */       int xStart = source.getMinX();
/* 237 */       int yStart = source.getMinY();
/* 239 */       while (iterator.hasNext()) {
/* 240 */         Rectangle rect = srcBounds.intersection(iterator.next());
/* 241 */         int tx = rect.x;
/* 242 */         int ty = rect.y;
/* 245 */         rect.x = ColorQuantizerOpImage.startPosition(tx, xStart, this.this$0.xPeriod);
/* 246 */         rect.y = ColorQuantizerOpImage.startPosition(ty, yStart, this.this$0.yPeriod);
/* 247 */         rect.width = tx + rect.width - rect.x;
/* 248 */         rect.height = ty + rect.height - rect.y;
/* 250 */         if (rect.isEmpty())
/*     */           continue; 
/* 254 */         UnpackedImageData uid = this.this$0.srcPA.getPixels(source, rect, this.this$0.srcSampleType, false);
/* 256 */         switch (uid.type) {
/*     */           case 0:
/* 258 */             constructTreeByte(uid);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     private void constructTreeByte(UnpackedImageData uid) {
/* 303 */       Rectangle rect = uid.rect;
/* 304 */       byte[][] data = uid.getByteData();
/* 305 */       int lineStride = uid.lineStride;
/* 306 */       int pixelStride = uid.pixelStride;
/* 307 */       byte[] rBand = data[0];
/* 308 */       byte[] gBand = data[1];
/* 309 */       byte[] bBand = data[2];
/* 311 */       int lineInc = lineStride * this.this$0.yPeriod;
/* 312 */       int pixelInc = pixelStride * this.this$0.xPeriod;
/* 314 */       int lastLine = rect.height * lineStride;
/*     */       int lo;
/* 316 */       for (lo = 0; lo < lastLine; lo += lineInc) {
/* 317 */         int lastPixel = lo + rect.width * pixelStride;
/*     */         int po;
/* 319 */         for (po = lo; po < lastPixel; po += pixelInc) {
/* 320 */           int red = rBand[po + uid.bandOffsets[0]] & 0xFF;
/* 321 */           int green = gBand[po + uid.bandOffsets[1]] & 0xFF;
/* 322 */           int blue = bBand[po + uid.bandOffsets[2]] & 0xFF;
/* 325 */           if (this.nodes > this.this$0.treeSize) {
/* 326 */             this.root.pruneLevel();
/* 327 */             this.depth--;
/*     */           } 
/* 332 */           Node node = this.root;
/* 333 */           for (int level = 1; level <= this.depth; level++) {
/* 334 */             int id = ((red > node.mid_red) ? 1 : 0) | ((green > node.mid_green) ? 1 : 0) << 1 | ((blue > node.mid_blue) ? 1 : 0) << 2;
/* 337 */             if (node.child[id] == null) {
/* 338 */               node = new Node(this, node, id, level);
/*     */             } else {
/* 340 */               node = node.child[id];
/*     */             } 
/* 341 */             node.number_pixels++;
/*     */           } 
/* 344 */           node.unique++;
/* 345 */           node.total_red += red;
/* 346 */           node.total_green += green;
/* 347 */           node.total_blue += blue;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     void reduction() {
/* 365 */       int totalSamples = (this.source.getWidth() + this.this$0.xPeriod - 1) / this.this$0.xPeriod * (this.source.getHeight() + this.this$0.yPeriod - 1) / this.this$0.yPeriod;
/* 367 */       int threshold = Math.max(1, totalSamples / this.max_colors * 8);
/* 368 */       while (this.colors > this.max_colors) {
/* 369 */         this.colors = 0;
/* 370 */         threshold = this.root.reduce(threshold, 2147483647);
/*     */       } 
/*     */     }
/*     */     
/*     */     void assignment() {
/* 395 */       this.colormap = new byte[3][this.colors];
/* 397 */       this.colors = 0;
/* 398 */       this.root.colormap();
/*     */     }
/*     */     
/*     */     class Node {
/*     */       OctTreeOpImage.Cube cube;
/*     */       
/*     */       Node parent;
/*     */       
/*     */       Node[] child;
/*     */       
/*     */       int nchild;
/*     */       
/*     */       int id;
/*     */       
/*     */       int level;
/*     */       
/*     */       int mid_red;
/*     */       
/*     */       int mid_green;
/*     */       
/*     */       int mid_blue;
/*     */       
/*     */       int number_pixels;
/*     */       
/*     */       int unique;
/*     */       
/*     */       int total_red;
/*     */       
/*     */       int total_green;
/*     */       
/*     */       int total_blue;
/*     */       
/*     */       int color_number;
/*     */       
/*     */       private final OctTreeOpImage.Cube this$1;
/*     */       
/*     */       Node(OctTreeOpImage.Cube this$1, OctTreeOpImage.Cube cube) {
/* 436 */         this.this$1 = this$1;
/* 437 */         this.cube = cube;
/* 438 */         this.parent = this;
/* 439 */         this.child = new Node[8];
/* 440 */         this.id = 0;
/* 441 */         this.level = 0;
/* 443 */         this.number_pixels = Integer.MAX_VALUE;
/* 445 */         this.mid_red = this$1.this$0.maxColorNum + 1 >> 1;
/* 446 */         this.mid_green = this$1.this$0.maxColorNum + 1 >> 1;
/* 447 */         this.mid_blue = this$1.this$0.maxColorNum + 1 >> 1;
/*     */       }
/*     */       
/*     */       Node(OctTreeOpImage.Cube this$1, Node parent, int id, int level) {
/* 450 */         this.this$1 = this$1;
/* 451 */         this.cube = parent.cube;
/* 452 */         this.parent = parent;
/* 453 */         this.child = new Node[8];
/* 454 */         this.id = id;
/* 455 */         this.level = level;
/* 458 */         this.cube.nodes++;
/* 459 */         if (level == this.cube.depth)
/* 460 */           this.cube.colors++; 
/* 464 */         parent.nchild++;
/* 465 */         parent.child[id] = this;
/* 468 */         int bi = 1 << this$1.this$0.maxTreeDepth - level >> 1;
/* 469 */         parent.mid_red += ((id & 0x1) > 0) ? bi : -bi;
/* 470 */         parent.mid_green += ((id & 0x2) > 0) ? bi : -bi;
/* 471 */         parent.mid_blue += ((id & 0x4) > 0) ? bi : -bi;
/*     */       }
/*     */       
/*     */       void pruneChild() {
/* 479 */         this.parent.nchild--;
/* 480 */         this.parent.unique += this.unique;
/* 481 */         this.parent.total_red += this.total_red;
/* 482 */         this.parent.total_green += this.total_green;
/* 483 */         this.parent.total_blue += this.total_blue;
/* 484 */         this.parent.child[this.id] = null;
/* 485 */         this.cube.nodes--;
/* 486 */         this.cube = null;
/* 487 */         this.parent = null;
/*     */       }
/*     */       
/*     */       void pruneLevel() {
/* 494 */         if (this.nchild != 0)
/* 495 */           for (int id = 0; id < 8; id++) {
/* 496 */             if (this.child[id] != null)
/* 497 */               this.child[id].pruneLevel(); 
/*     */           }  
/* 501 */         if (this.level == this.cube.depth)
/* 502 */           pruneChild(); 
/*     */       }
/*     */       
/*     */       int reduce(int threshold, int next_threshold) {
/* 514 */         if (this.nchild != 0)
/* 515 */           for (int id = 0; id < 8; id++) {
/* 516 */             if (this.child[id] != null)
/* 517 */               next_threshold = this.child[id].reduce(threshold, next_threshold); 
/*     */           }  
/* 522 */         if (this.number_pixels <= threshold) {
/* 523 */           pruneChild();
/*     */         } else {
/* 525 */           if (this.unique != 0)
/* 526 */             this.cube.colors++; 
/* 529 */           if (this.number_pixels < next_threshold)
/* 530 */             next_threshold = this.number_pixels; 
/*     */         } 
/* 534 */         return next_threshold;
/*     */       }
/*     */       
/*     */       void colormap() {
/* 544 */         if (this.nchild != 0)
/* 545 */           for (int id = 0; id < 8; id++) {
/* 546 */             if (this.child[id] != null)
/* 547 */               this.child[id].colormap(); 
/*     */           }  
/* 551 */         if (this.unique != 0) {
/* 552 */           this.cube.colormap[0][this.cube.colors] = (byte)((this.total_red + (this.unique >> 1)) / this.unique);
/* 554 */           this.cube.colormap[1][this.cube.colors] = (byte)((this.total_green + (this.unique >> 1)) / this.unique);
/* 556 */           this.cube.colormap[2][this.cube.colors] = (byte)((this.total_blue + (this.unique >> 1)) / this.unique);
/* 558 */           this.color_number = this.cube.colors++;
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\OctTreeOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */