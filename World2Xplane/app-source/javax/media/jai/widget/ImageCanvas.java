/*     */ package javax.media.jai.widget;
/*     */ 
/*     */ import java.awt.Canvas;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import javax.media.jai.InterpolationNearest;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.PlanarImage;
/*     */ 
/*     */ public class ImageCanvas extends Canvas {
/*     */   protected RenderedImage im;
/*     */   
/*     */   protected SampleModel sampleModel;
/*     */   
/*     */   protected ColorModel colorModel;
/*     */   
/*     */   protected int minTileX;
/*     */   
/*     */   protected int maxTileX;
/*     */   
/*     */   protected int minTileY;
/*     */   
/*     */   protected int maxTileY;
/*     */   
/*     */   protected int tileWidth;
/*     */   
/*     */   protected int tileHeight;
/*     */   
/*     */   protected int tileGridXOffset;
/*     */   
/*     */   protected int tileGridYOffset;
/*     */   
/*     */   protected int imWidth;
/*     */   
/*     */   protected int imHeight;
/*     */   
/*     */   protected int padX;
/*     */   
/*     */   protected int padY;
/*     */   
/*     */   protected boolean drawBorder = false;
/*     */   
/*     */   protected int originX;
/*     */   
/*     */   protected int originY;
/*     */   
/* 108 */   protected int canvasWidth = 0;
/*     */   
/* 110 */   protected int canvasHeight = 0;
/*     */   
/* 112 */   private Color grayColor = new Color(192, 192, 192);
/*     */   
/* 114 */   private Color backgroundColor = null;
/*     */   
/*     */   private HashSet paintListeners;
/*     */   
/*     */   private synchronized void initialize() {
/* 118 */     int mx = this.im.getMinX();
/* 119 */     int my = this.im.getMinY();
/* 120 */     if (mx < 0 || my < 0) {
/* 121 */       ParameterBlock pb = new ParameterBlock();
/* 122 */       pb.addSource(this.im);
/* 123 */       pb.add(Math.max(-mx, 0));
/* 124 */       pb.add(Math.max(-my, 0));
/* 125 */       pb.add(new InterpolationNearest());
/* 126 */       this.im = (RenderedImage)JAI.create("translate", pb, null);
/*     */     } 
/* 129 */     this.sampleModel = this.im.getSampleModel();
/* 132 */     this.colorModel = this.im.getColorModel();
/* 133 */     if (this.colorModel == null) {
/* 135 */       this.colorModel = PlanarImage.createColorModel(this.im.getSampleModel());
/* 136 */       if (this.colorModel == null)
/* 137 */         throw new IllegalArgumentException(JaiI18N.getString("ImageCanvas0")); 
/*     */     } 
/* 141 */     Object col = this.im.getProperty("background_color");
/* 142 */     if (col != Image.UndefinedProperty)
/* 143 */       this.backgroundColor = (Color)col; 
/* 146 */     this.minTileX = this.im.getMinTileX();
/* 147 */     this.maxTileX = this.im.getMinTileX() + this.im.getNumXTiles() - 1;
/* 148 */     this.minTileY = this.im.getMinTileY();
/* 149 */     this.maxTileY = this.im.getMinTileY() + this.im.getNumYTiles() - 1;
/* 150 */     this.tileWidth = this.im.getTileWidth();
/* 151 */     this.tileHeight = this.im.getTileHeight();
/* 152 */     this.tileGridXOffset = this.im.getTileGridXOffset();
/* 153 */     this.tileGridYOffset = this.im.getTileGridYOffset();
/* 155 */     this.imWidth = this.im.getMinX() + this.im.getWidth();
/* 156 */     this.imHeight = this.im.getMinY() + this.im.getHeight();
/* 158 */     this.originX = this.originY = 0;
/*     */   }
/*     */   
/*     */   public ImageCanvas(RenderedImage im) {
/* 179 */     this(im, false);
/*     */   }
/*     */   
/*     */   public void addNotify() {
/* 183 */     super.addNotify();
/* 184 */     initialize();
/*     */   }
/*     */   
/*     */   public synchronized void set(RenderedImage im) {
/* 189 */     this.im = im;
/* 190 */     initialize();
/* 191 */     repaint();
/*     */   }
/*     */   
/*     */   public void setOrigin(int x, int y) {
/* 196 */     this.padX = 0;
/* 197 */     this.padY = 0;
/* 198 */     this.originX = x;
/* 199 */     this.originY = y;
/* 200 */     repaint();
/*     */   }
/*     */   
/*     */   public int getXOrigin() {
/* 204 */     return this.originX;
/*     */   }
/*     */   
/*     */   public int getYOrigin() {
/* 208 */     return this.originY;
/*     */   }
/*     */   
/*     */   public int getXPad() {
/* 212 */     return this.padX;
/*     */   }
/*     */   
/*     */   public int getYPad() {
/* 216 */     return this.padY;
/*     */   }
/*     */   
/*     */   public Dimension getMinimumSize() {
/* 220 */     return new Dimension(this.im.getMinX() + this.im.getWidth() + (this.drawBorder ? 4 : 0), this.im.getMinY() + this.im.getHeight() + (this.drawBorder ? 4 : 0));
/*     */   }
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 227 */     return getMinimumSize();
/*     */   }
/*     */   
/*     */   public Dimension getMaximumSize() {
/* 231 */     return getMinimumSize();
/*     */   }
/*     */   
/*     */   public void setBounds(int x, int y, int width, int height) {
/* 236 */     super.setBounds(x, y, width, height);
/* 237 */     this.canvasWidth = width;
/* 238 */     this.canvasHeight = height;
/* 240 */     this.padX = Math.max((this.canvasWidth - this.imWidth - (this.drawBorder ? 4 : 0)) / 2, 0);
/* 241 */     this.padY = Math.max((this.canvasHeight - this.imHeight - (this.drawBorder ? 4 : 0)) / 2, 0);
/*     */   }
/*     */   
/*     */   private int XtoTileX(int x) {
/* 245 */     return (int)Math.floor((x - this.tileGridXOffset) / this.tileWidth);
/*     */   }
/*     */   
/*     */   private int YtoTileY(int y) {
/* 249 */     return (int)Math.floor((y - this.tileGridYOffset) / this.tileHeight);
/*     */   }
/*     */   
/*     */   private int TileXtoX(int tx) {
/* 253 */     return tx * this.tileWidth + this.tileGridXOffset;
/*     */   }
/*     */   
/*     */   private int TileYtoY(int ty) {
/* 257 */     return ty * this.tileHeight + this.tileGridYOffset;
/*     */   }
/*     */   
/*     */   public void update(Graphics g) {
/* 265 */     paint(g);
/*     */   }
/*     */   
/*     */   public synchronized void paint(Graphics g) {
/* 275 */     if (this.im == null)
/*     */       return; 
/* 279 */     Graphics2D g2D = null;
/* 280 */     if (g instanceof Graphics2D) {
/* 281 */       g2D = (Graphics2D)g;
/*     */     } else {
/* 283 */       System.err.println(JaiI18N.getString("ImageCanvas1"));
/*     */       return;
/*     */     } 
/* 287 */     Color saveColor = g2D.getColor();
/* 289 */     if (this.drawBorder) {
/* 290 */       g.setColor(new Color(171, 171, 171));
/* 291 */       g.draw3DRect(this.padX, this.padY, this.imWidth + 3, this.imHeight + 3, true);
/* 295 */       g.draw3DRect(this.padX + 1, this.padY + 1, this.imWidth + 1, this.imHeight + 1, true);
/*     */     } 
/* 302 */     Rectangle clipBounds = g.getClipBounds();
/* 303 */     if (clipBounds == null)
/* 304 */       clipBounds = new Rectangle(0, 0, this.canvasWidth, this.canvasHeight); 
/* 307 */     int border = this.drawBorder ? 2 : 0;
/* 308 */     int transX = this.padX + border - this.originX;
/* 309 */     int transY = this.padY + border - this.originY;
/* 311 */     clipBounds.translate(-transX, -transY);
/* 316 */     int txmin = XtoTileX(clipBounds.x);
/* 317 */     txmin = Math.max(txmin, this.minTileX);
/* 318 */     txmin = Math.min(txmin, this.maxTileX);
/* 320 */     int txmax = XtoTileX(clipBounds.x + clipBounds.width - 1);
/* 321 */     txmax = Math.max(txmax, this.minTileX);
/* 322 */     txmax = Math.min(txmax, this.maxTileX);
/* 324 */     int tymin = YtoTileY(clipBounds.y);
/* 325 */     tymin = Math.max(tymin, this.minTileY);
/* 326 */     tymin = Math.min(tymin, this.maxTileY);
/* 328 */     int tymax = YtoTileY(clipBounds.y + clipBounds.height - 1);
/* 329 */     tymax = Math.max(tymax, this.minTileY);
/* 330 */     tymax = Math.min(tymax, this.maxTileY);
/* 332 */     if (this.backgroundColor != null) {
/* 333 */       g2D.setColor(this.backgroundColor);
/*     */     } else {
/* 336 */       g2D.setColor(this.grayColor);
/*     */     } 
/* 339 */     int xmin = this.im.getMinX();
/* 340 */     int xmax = this.im.getMinX() + this.im.getWidth();
/* 341 */     int ymin = this.im.getMinY();
/* 342 */     int ymax = this.im.getMinY() + this.im.getHeight();
/* 343 */     int screenX = clipBounds.x + clipBounds.width;
/* 344 */     int screenY = clipBounds.y + clipBounds.height;
/* 347 */     if (xmin > clipBounds.x)
/* 348 */       g2D.fillRect(clipBounds.x + transX, clipBounds.y + transY, xmin - clipBounds.x, clipBounds.height); 
/* 355 */     if (xmax < screenX)
/* 356 */       g2D.fillRect(xmax + transX, clipBounds.y + transY, screenX - xmax, clipBounds.height); 
/* 363 */     if (ymin > clipBounds.y)
/* 364 */       g2D.fillRect(xmin + transX, clipBounds.y + transY, xmax - xmin, ymin - clipBounds.y); 
/* 371 */     if (ymax < screenY)
/* 372 */       g2D.fillRect(xmin + transX, ymax + transY, xmax - xmin, screenY - ymax); 
/* 379 */     g2D.setClip(new Rectangle(transX + this.im.getMinX(), transY + this.im.getMinY(), this.im.getWidth(), this.im.getHeight()));
/* 385 */     Point[] tileIndices = new Point[(txmax - txmin + 1) * (tymax - tymin + 1)];
/* 386 */     int index = 0;
/* 387 */     for (int tj = tymin; tj <= tymax; tj++) {
/* 388 */       for (int ti = txmin; ti <= txmax; ti++)
/* 389 */         tileIndices[index++] = new Point(ti, tj); 
/*     */     } 
/* 392 */     Raster[] tiles = PlanarImage.wrapRenderedImage(this.im).getTiles(tileIndices);
/* 396 */     int numTiles = tiles.length;
/* 397 */     for (int tileNum = 0; tileNum < numTiles; tileNum++) {
/* 398 */       Raster tile = tiles[tileNum];
/* 400 */       int tx = tile.getMinX();
/* 401 */       int ty = tile.getMinY();
/* 403 */       if (tile != null) {
/* 404 */         WritableRaster wr = (tile instanceof WritableRaster) ? ((WritableRaster)tile).createWritableTranslatedChild(0, 0) : Raster.createWritableRaster(this.sampleModel, tile.getDataBuffer(), new Point(0, 0));
/* 411 */         BufferedImage bi = new BufferedImage(this.colorModel, wr, this.colorModel.isAlphaPremultiplied(), null);
/* 417 */         AffineTransform transform = AffineTransform.getTranslateInstance((tx + transX), (ty + transY));
/* 420 */         if (this.backgroundColor != null)
/* 421 */           g2D.fillRect(tx + transX, ty + transY, this.tileWidth, this.tileHeight); 
/* 424 */         g2D.drawRenderedImage(bi, transform);
/*     */       } 
/*     */     } 
/* 429 */     g2D.setColor(saveColor);
/* 430 */     notifyPaintListeners(g2D);
/*     */   }
/*     */   
/*     */   public ImageCanvas(RenderedImage im, boolean drawBorder) {
/* 450 */     this.paintListeners = new HashSet();
/*     */     this.im = im;
/*     */     this.drawBorder = drawBorder;
/*     */     initialize();
/*     */   }
/*     */   
/*     */   public void addPaintListener(PaintListener pl) {
/* 458 */     this.paintListeners.add(pl);
/*     */   }
/*     */   
/*     */   public void removePaintListener(PaintListener pl) {
/* 467 */     this.paintListeners.remove(pl);
/*     */   }
/*     */   
/*     */   private void notifyPaintListeners(Graphics g) {
/* 472 */     Iterator it = this.paintListeners.iterator();
/* 474 */     while (it.hasNext())
/* 475 */       ((PaintListener)it.next()).paint(this, g); 
/*     */   }
/*     */   
/*     */   public static interface PaintListener {
/*     */     void paint(ImageCanvas param1ImageCanvas, Graphics param1Graphics);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\widget\ImageCanvas.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */