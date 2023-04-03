/*      */ package javax.media.jai;
/*      */ 
/*      */ import com.sun.media.jai.util.JDKWorkarounds;
/*      */ import java.awt.Color;
/*      */ import java.awt.Composite;
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.GraphicsConfiguration;
/*      */ import java.awt.Image;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.font.FontRenderContext;
/*      */ import java.awt.font.GlyphVector;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Area;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.awt.image.BufferedImageOp;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ImageObserver;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.awt.image.renderable.RenderableImage;
/*      */ import java.lang.reflect.Method;
/*      */ import java.text.AttributedCharacterIterator;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Map;
/*      */ import javax.media.jai.util.ImagingException;
/*      */ import javax.media.jai.util.ImagingListener;
/*      */ 
/*      */ class TiledImageGraphics extends Graphics2D {
/*   88 */   private static final Class GRAPHICS2D_CLASS = Graphics2D.class;
/*      */   
/*      */   private static final int PAINT_MODE = 1;
/*      */   
/*      */   private static final int XOR_MODE = 2;
/*      */   
/*      */   private TiledImage tiledImage;
/*      */   
/*      */   Hashtable properties;
/*      */   
/*      */   private RenderingHints renderingHints;
/*      */   
/*      */   private int tileWidth;
/*      */   
/*      */   private int tileHeight;
/*      */   
/*      */   private int tileXMinimum;
/*      */   
/*      */   private int tileXMaximum;
/*      */   
/*      */   private int tileYMinimum;
/*      */   
/*      */   private int tileYMaximum;
/*      */   
/*      */   private ColorModel colorModel;
/*      */   
/*      */   private Point origin;
/*      */   
/*      */   private Shape clip;
/*      */   
/*      */   private Color color;
/*      */   
/*      */   private Font font;
/*      */   
/*  111 */   private int paintMode = 1;
/*      */   
/*      */   private Color XORColor;
/*      */   
/*      */   private Color background;
/*      */   
/*      */   private Composite composite;
/*      */   
/*      */   private Paint paint;
/*      */   
/*      */   private Stroke stroke;
/*      */   
/*      */   private AffineTransform transform;
/*      */   
/*      */   private static final Rectangle getBoundingBox(int[] xPoints, int[] yPoints, int nPoints) {
/*  134 */     if (nPoints <= 0)
/*  134 */       return null; 
/*  141 */     int maxX = xPoints[0], minX = maxX;
/*  142 */     int maxY = yPoints[0], minY = maxY;
/*  144 */     for (int i = 1; i < nPoints; i++) {
/*  145 */       minX = Math.min(minX, xPoints[i]);
/*  146 */       maxX = Math.max(maxX, xPoints[i]);
/*  147 */       minY = Math.min(minY, yPoints[i]);
/*  148 */       maxY = Math.max(maxY, yPoints[i]);
/*      */     } 
/*  151 */     return new Rectangle(minX, minY, maxX - minX + 1, maxY - minY + 1);
/*      */   }
/*      */   
/*      */   public TiledImageGraphics(TiledImage im) {
/*  169 */     int dataType = im.getSampleModel().getTransferType();
/*  170 */     if (dataType != 0 && dataType != 2 && dataType != 1 && dataType != 3)
/*  174 */       throw new UnsupportedOperationException(JaiI18N.getString("TiledImageGraphics0")); 
/*  178 */     this.tiledImage = im;
/*  181 */     this.tileWidth = im.getTileWidth();
/*  182 */     this.tileHeight = im.getTileHeight();
/*  183 */     this.tileXMinimum = im.getMinTileX();
/*  184 */     this.tileXMaximum = im.getMaxTileX();
/*  185 */     this.tileYMinimum = im.getMinTileY();
/*  186 */     this.tileYMaximum = im.getMaxTileY();
/*  189 */     this.colorModel = getColorModel(this.tiledImage);
/*  192 */     Graphics2D g = getBogusGraphics2D(false);
/*  195 */     this.origin = new Point(0, 0);
/*  196 */     setClip(this.tiledImage.getBounds());
/*  197 */     setColor(g.getColor());
/*  198 */     setFont(g.getFont());
/*  199 */     setPaintMode();
/*  202 */     setBackground(g.getBackground());
/*  203 */     setComposite(g.getComposite());
/*  204 */     setStroke(g.getStroke());
/*  205 */     setTransform(g.getTransform());
/*  208 */     g.dispose();
/*  212 */     this.properties = this.tiledImage.getProperties();
/*  215 */     this.renderingHints = new RenderingHints(this.properties);
/*      */   }
/*      */   
/*      */   private void copyState(Graphics2D g2d) {
/*  226 */     g2d.translate(this.origin.x, this.origin.y);
/*  227 */     setClip(getClip());
/*  228 */     g2d.setColor(getColor());
/*  229 */     if (this.paintMode == 1) {
/*  230 */       g2d.setPaintMode();
/*  231 */     } else if (this.XORColor != null) {
/*  232 */       g2d.setXORMode(this.XORColor);
/*      */     } 
/*  234 */     g2d.setFont(getFont());
/*  237 */     g2d.setBackground(getBackground());
/*  238 */     g2d.setComposite(getComposite());
/*  239 */     if (this.paint != null)
/*  239 */       g2d.setPaint(getPaint()); 
/*  240 */     g2d.setRenderingHints(this.renderingHints);
/*  241 */     g2d.setStroke(getStroke());
/*  242 */     g2d.setTransform(getTransform());
/*      */   }
/*      */   
/*      */   private Graphics2D getBogusGraphics2D(boolean shouldCopyState) {
/*  260 */     Raster r = this.tiledImage.getTile(this.tileXMinimum, this.tileYMinimum);
/*  261 */     WritableRaster wr = r.createCompatibleWritableRaster(1, 1);
/*  262 */     BufferedImage bi = new BufferedImage(this.colorModel, wr, this.colorModel.isAlphaPremultiplied(), this.properties);
/*  266 */     Graphics2D bogusG2D = bi.createGraphics();
/*  267 */     if (shouldCopyState)
/*  268 */       copyState(bogusG2D); 
/*  270 */     return bogusG2D;
/*      */   }
/*      */   
/*      */   private static ColorModel getColorModel(TiledImage ti) {
/*  286 */     ColorModel colorModel = ti.getColorModel();
/*  288 */     if (colorModel == null)
/*  290 */       if (colorModel == null) {
/*  292 */         SampleModel sm = ti.getSampleModel();
/*  293 */         colorModel = PlanarImage.createColorModel(sm);
/*  294 */         if (colorModel == null) {
/*  296 */           ColorModel cm = ColorModel.getRGBdefault();
/*  297 */           if (JDKWorkarounds.areCompatibleDataModels(sm, cm))
/*  298 */             colorModel = cm; 
/*      */         } 
/*  303 */         if (colorModel == null)
/*  304 */           throw new UnsupportedOperationException(JaiI18N.getString("TiledImageGraphics1")); 
/*      */       }  
/*  309 */     return colorModel;
/*      */   }
/*      */   
/*      */   private boolean doGraphicsOp(int x, int y, int width, int height, String name, Class[] argTypes, Object[] args) {
/*  330 */     boolean returnValue = false;
/*  335 */     Method method = null;
/*      */     try {
/*  337 */       method = GRAPHICS2D_CLASS.getMethod(name, argTypes);
/*  338 */     } catch (Exception e) {
/*  339 */       String message = JaiI18N.getString("TiledImageGraphics2") + name;
/*  340 */       sendExceptionToListener(message, (Exception)new ImagingException(e));
/*      */     } 
/*  345 */     Rectangle bounds = new Rectangle(x, y, width, height);
/*  346 */     bounds = getTransform().createTransformedShape(bounds).getBounds();
/*  349 */     int minTileX = this.tiledImage.XToTileX(bounds.x);
/*  350 */     if (minTileX < this.tileXMinimum)
/*  351 */       minTileX = this.tileXMinimum; 
/*  352 */     int minTileY = this.tiledImage.YToTileY(bounds.y);
/*  353 */     if (minTileY < this.tileYMinimum)
/*  354 */       minTileY = this.tileYMinimum; 
/*  355 */     int maxTileX = this.tiledImage.XToTileX(bounds.x + bounds.width - 1);
/*  356 */     if (maxTileX > this.tileXMaximum)
/*  357 */       maxTileX = this.tileXMaximum; 
/*  358 */     int maxTileY = this.tiledImage.YToTileY(bounds.y + bounds.height - 1);
/*  359 */     if (maxTileY > this.tileYMaximum)
/*  360 */       maxTileY = this.tileYMaximum; 
/*  363 */     for (int tileY = minTileY; tileY <= maxTileY; tileY++) {
/*  364 */       int tileMinY = this.tiledImage.tileYToY(tileY);
/*  365 */       for (int tileX = minTileX; tileX <= maxTileX; tileX++) {
/*  366 */         int tileMinX = this.tiledImage.tileXToX(tileX);
/*  369 */         WritableRaster wr = this.tiledImage.getWritableTile(tileX, tileY);
/*  370 */         wr = wr.createWritableTranslatedChild(0, 0);
/*  373 */         BufferedImage bi = new BufferedImage(this.colorModel, wr, this.colorModel.isAlphaPremultiplied(), this.properties);
/*  379 */         Graphics2D g2d = bi.createGraphics();
/*  382 */         copyState(g2d);
/*      */         try {
/*  387 */           Point2D origin2D = g2d.getTransform().transform(new Point2D.Double(), null);
/*  390 */           Point pt = new Point((int)origin2D.getX() - tileMinX, (int)origin2D.getY() - tileMinY);
/*  392 */           Point2D pt2D = g2d.getTransform().inverseTransform(pt, null);
/*  394 */           g2d.translate(pt2D.getX(), pt2D.getY());
/*  395 */         } catch (Exception e) {
/*  396 */           String message = JaiI18N.getString("TiledImageGraphics3");
/*  397 */           sendExceptionToListener(message, (Exception)new ImagingException(e));
/*      */         } 
/*      */         try {
/*  403 */           Object retVal = method.invoke(g2d, args);
/*  404 */           if (retVal != null && retVal.getClass() == boolean.class)
/*  405 */             returnValue = ((Boolean)retVal).booleanValue(); 
/*  407 */         } catch (Exception e) {
/*  408 */           String message = JaiI18N.getString("TiledImageGraphics3") + " " + name;
/*  410 */           sendExceptionToListener(message, (Exception)new ImagingException(e));
/*      */         } 
/*  415 */         g2d.dispose();
/*  418 */         this.tiledImage.releaseWritableTile(tileX, tileY);
/*      */       } 
/*      */     } 
/*  422 */     return returnValue;
/*      */   }
/*      */   
/*      */   private boolean doGraphicsOp(Shape s, String name, Class[] argTypes, Object[] args) {
/*  439 */     Rectangle r = s.getBounds();
/*  440 */     return doGraphicsOp(r.x, r.y, r.width, r.height, name, argTypes, args);
/*      */   }
/*      */   
/*      */   public Graphics create() {
/*  447 */     TiledImageGraphics tig = new TiledImageGraphics(this.tiledImage);
/*  450 */     copyState(tig);
/*  452 */     return tig;
/*      */   }
/*      */   
/*      */   public Color getColor() {
/*  459 */     return this.color;
/*      */   }
/*      */   
/*      */   public void setColor(Color c) {
/*  463 */     this.color = c;
/*      */   }
/*      */   
/*      */   public void setPaintMode() {
/*  467 */     this.paintMode = 1;
/*      */   }
/*      */   
/*      */   public void setXORMode(Color c1) {
/*  471 */     this.paintMode = 2;
/*  472 */     this.XORColor = c1;
/*      */   }
/*      */   
/*      */   public Font getFont() {
/*  476 */     return this.font;
/*      */   }
/*      */   
/*      */   public void setFont(Font font) {
/*  480 */     this.font = font;
/*      */   }
/*      */   
/*      */   public FontMetrics getFontMetrics(Font f) {
/*  484 */     Graphics2D g2d = getBogusGraphics2D(true);
/*  486 */     FontMetrics fontMetrics = g2d.getFontMetrics(f);
/*  488 */     g2d.dispose();
/*  490 */     return fontMetrics;
/*      */   }
/*      */   
/*      */   public Rectangle getClipBounds() {
/*  494 */     return this.clip.getBounds();
/*      */   }
/*      */   
/*      */   public void clipRect(int x, int y, int width, int height) {
/*  498 */     clip(new Rectangle(x, y, width, height));
/*      */   }
/*      */   
/*      */   public void setClip(int x, int y, int width, int height) {
/*  502 */     setClip(new Rectangle(x, y, width, height));
/*      */   }
/*      */   
/*      */   public Shape getClip() {
/*  506 */     return this.clip;
/*      */   }
/*      */   
/*      */   public void setClip(Shape clip) {
/*  510 */     this.clip = clip;
/*      */   }
/*      */   
/*      */   public void copyArea(int x, int y, int width, int height, int dx, int dy) {
/*  515 */     Rectangle rect = getBoundingBox(new int[] { x, x + dx, x + width - 1, x + width - 1 + dx }, new int[] { y, y + dy, y + height - 1, y + height - 1 + dy }, 4);
/*  520 */     doGraphicsOp(rect, "copyArea", new Class[] { int.class, int.class, int.class, int.class, int.class, int.class }, new Object[] { new Integer(x), new Integer(y), new Integer(width), new Integer(height), new Integer(dx), new Integer(dy) });
/*      */   }
/*      */   
/*      */   public void drawLine(int x1, int y1, int x2, int y2) {
/*  529 */     Rectangle rect = getBoundingBox(new int[] { x1, x2 }, new int[] { y1, y2 }, 2);
/*  532 */     doGraphicsOp(rect, "drawLine", new Class[] { int.class, int.class, int.class, int.class }, new Object[] { new Integer(x1), new Integer(y1), new Integer(x2), new Integer(y2) });
/*      */   }
/*      */   
/*      */   public void fillRect(int x, int y, int width, int height) {
/*  539 */     doGraphicsOp(x, y, width, height, "fillRect", new Class[] { int.class, int.class, int.class, int.class }, new Object[] { new Integer(x), new Integer(y), new Integer(width), new Integer(height) });
/*      */   }
/*      */   
/*      */   public void clearRect(int x, int y, int width, int height) {
/*  549 */     doGraphicsOp(x, y, width, height, "clearRect", new Class[] { int.class, int.class, int.class, int.class }, new Object[] { new Integer(x), new Integer(y), new Integer(width), new Integer(height) });
/*      */   }
/*      */   
/*      */   public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
/*  557 */     doGraphicsOp(x - arcWidth, y - arcHeight, width + 2 * arcWidth, height + 2 * arcHeight, "drawRoundRect", new Class[] { int.class, int.class, int.class, int.class, int.class, int.class }, new Object[] { new Integer(x), new Integer(y), new Integer(width), new Integer(height), new Integer(arcWidth), new Integer(arcHeight) });
/*      */   }
/*      */   
/*      */   public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
/*  569 */     doGraphicsOp(x - arcWidth, y - arcHeight, width + 2 * arcWidth, height + 2 * arcHeight, "fillRoundRect", new Class[] { int.class, int.class, int.class, int.class, int.class, int.class }, new Object[] { new Integer(x), new Integer(y), new Integer(width), new Integer(height), new Integer(arcWidth), new Integer(arcHeight) });
/*      */   }
/*      */   
/*      */   public void draw3DRect(int x, int y, int width, int height, boolean raised) {
/*  584 */     doGraphicsOp(x, y, width, height, "draw3DRect", new Class[] { int.class, int.class, int.class, int.class, boolean.class }, new Object[] { new Integer(x), new Integer(y), new Integer(width), new Integer(height), new Boolean(raised) });
/*      */   }
/*      */   
/*      */   public void fill3DRect(int x, int y, int width, int height, boolean raised) {
/*  597 */     doGraphicsOp(x, y, width, height, "fill3DRect", new Class[] { int.class, int.class, int.class, int.class, boolean.class }, new Object[] { new Integer(x), new Integer(y), new Integer(width), new Integer(height), new Boolean(raised) });
/*      */   }
/*      */   
/*      */   public void drawOval(int x, int y, int width, int height) {
/*  607 */     doGraphicsOp(x, y, width, height, "drawOval", new Class[] { int.class, int.class, int.class, int.class }, new Object[] { new Integer(x), new Integer(y), new Integer(width), new Integer(height) });
/*      */   }
/*      */   
/*      */   public void fillOval(int x, int y, int width, int height) {
/*  614 */     doGraphicsOp(x, y, width, height, "fillOval", new Class[] { int.class, int.class, int.class, int.class }, new Object[] { new Integer(x), new Integer(y), new Integer(width), new Integer(height) });
/*      */   }
/*      */   
/*      */   public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
/*  622 */     doGraphicsOp(x, y, width, height, "drawArc", new Class[] { int.class, int.class, int.class, int.class, int.class, int.class }, new Object[] { new Integer(x), new Integer(y), new Integer(width), new Integer(height), new Integer(startAngle), new Integer(arcAngle) });
/*      */   }
/*      */   
/*      */   public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
/*  633 */     doGraphicsOp(x, y, width, height, "fillArc", new Class[] { int.class, int.class, int.class, int.class, int.class, int.class }, new Object[] { new Integer(x), new Integer(y), new Integer(width), new Integer(height), new Integer(startAngle), new Integer(arcAngle) });
/*      */   }
/*      */   
/*      */   public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
/*  643 */     Class intArrayClass = xPoints.getClass();
/*  644 */     Rectangle box = getBoundingBox(xPoints, yPoints, nPoints);
/*  646 */     if (box == null)
/*      */       return; 
/*  648 */     doGraphicsOp(box, "drawPolyline", new Class[] { intArrayClass, intArrayClass, int.class }, new Object[] { xPoints, yPoints, new Integer(nPoints) });
/*      */   }
/*      */   
/*      */   public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
/*  654 */     Class intArrayClass = xPoints.getClass();
/*  655 */     Rectangle box = getBoundingBox(xPoints, yPoints, nPoints);
/*  657 */     if (box == null)
/*      */       return; 
/*  659 */     doGraphicsOp(box, "drawPolygon", new Class[] { intArrayClass, intArrayClass, int.class }, new Object[] { xPoints, yPoints, new Integer(nPoints) });
/*      */   }
/*      */   
/*      */   public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
/*  667 */     Class intArrayClass = xPoints.getClass();
/*  668 */     Rectangle box = getBoundingBox(xPoints, yPoints, nPoints);
/*  670 */     if (box == null)
/*      */       return; 
/*  672 */     doGraphicsOp(box, "fillPolygon", new Class[] { intArrayClass, intArrayClass, int.class }, new Object[] { xPoints, yPoints, new Integer(nPoints) });
/*      */   }
/*      */   
/*      */   public void drawString(String str, int x, int y) {
/*  680 */     Rectangle2D r2d = getFontMetrics(getFont()).getStringBounds(str, this);
/*  684 */     r2d.setRect(x, y - r2d.getHeight() + 1.0D, r2d.getWidth(), r2d.getHeight());
/*  687 */     doGraphicsOp(r2d, "drawString", new Class[] { String.class, int.class, int.class }, new Object[] { str, new Integer(x), new Integer(y) });
/*      */   }
/*      */   
/*      */   public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
/*  699 */     return doGraphicsOp(x, y, img.getWidth(observer), img.getHeight(observer), "drawImage", new Class[] { Image.class, int.class, int.class, ImageObserver.class }, new Object[] { img, new Integer(x), new Integer(y), observer });
/*      */   }
/*      */   
/*      */   public void drawRenderedImage(RenderedImage im, AffineTransform transform) {
/*  712 */     Rectangle2D.Double srcRect = new Rectangle2D.Double(im.getMinX(), im.getMinY(), im.getWidth(), im.getHeight());
/*  717 */     Rectangle2D dstRect = transform.createTransformedShape(srcRect).getBounds2D();
/*  720 */     doGraphicsOp(dstRect, "drawRenderedImage", new Class[] { RenderedImage.class, AffineTransform.class }, new Object[] { im, transform });
/*      */   }
/*      */   
/*      */   public void drawRenderableImage(RenderableImage img, AffineTransform xform) {
/*  728 */     Rectangle2D.Double srcRect = new Rectangle2D.Double(img.getMinX(), img.getMinY(), img.getWidth(), img.getHeight());
/*  733 */     Rectangle2D dstRect = xform.createTransformedShape(srcRect).getBounds2D();
/*  736 */     doGraphicsOp(dstRect, "drawRenderableImage", new Class[] { RenderableImage.class, AffineTransform.class }, new Object[] { img, xform });
/*      */   }
/*      */   
/*      */   public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
/*  745 */     return doGraphicsOp(x, y, width, height, "drawImage", new Class[] { Image.class, int.class, int.class, int.class, int.class, ImageObserver.class }, new Object[] { img, new Integer(x), new Integer(y), new Integer(width), new Integer(height), observer });
/*      */   }
/*      */   
/*      */   public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
/*  757 */     return doGraphicsOp(x, y, img.getWidth(observer), img.getHeight(observer), "drawImage", new Class[] { Image.class, int.class, int.class, Color.class, ImageObserver.class }, new Object[] { img, new Integer(x), new Integer(y), bgcolor, observer });
/*      */   }
/*      */   
/*      */   public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
/*  772 */     return doGraphicsOp(x, y, width, height, "drawImage", new Class[] { Image.class, int.class, int.class, int.class, int.class, Color.class, ImageObserver.class }, new Object[] { img, new Integer(x), new Integer(y), new Integer(width), new Integer(height), bgcolor, observer });
/*      */   }
/*      */   
/*      */   public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
/*  786 */     return doGraphicsOp(dx1, dy1, dx2 - dx1 + 1, dy2 - dy1 + 1, "drawImage", new Class[] { Image.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, ImageObserver.class }, new Object[] { img, new Integer(dx1), new Integer(dy1), new Integer(dx2), new Integer(dy2), new Integer(sx1), new Integer(sy1), new Integer(sx2), new Integer(sy2), observer });
/*      */   }
/*      */   
/*      */   public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
/*  804 */     return doGraphicsOp(dx1, dy1, dx2 - dx1 + 1, dy2 - dy1 + 1, "drawImage", new Class[] { 
/*  804 */           Image.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, Color.class, 
/*  804 */           ImageObserver.class }, new Object[] { 
/*  804 */           img, new Integer(dx1), new Integer(dy1), new Integer(dx2), new Integer(dy2), new Integer(sx1), new Integer(sy1), new Integer(sx2), new Integer(sy2), bgcolor, 
/*  804 */           observer });
/*      */   }
/*      */   
/*      */   public void dispose() {}
/*      */   
/*      */   public void addRenderingHints(Map hints) {
/*  828 */     this.renderingHints.putAll(hints);
/*      */   }
/*      */   
/*      */   public void draw(Shape s) {
/*  832 */     doGraphicsOp(s.getBounds(), "draw", new Class[] { Shape.class }, new Object[] { s });
/*      */   }
/*      */   
/*      */   public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs) {
/*  841 */     Rectangle2D.Double srcRect = new Rectangle2D.Double(0.0D, 0.0D, img.getWidth(obs), img.getHeight(obs));
/*  845 */     Rectangle2D dstRect = this.transform.createTransformedShape(srcRect).getBounds2D();
/*  848 */     return doGraphicsOp(dstRect, "drawImage", new Class[] { Image.class, AffineTransform.class, ImageObserver.class }, new Object[] { img, xform, obs });
/*      */   }
/*      */   
/*      */   public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y) {
/*  859 */     doGraphicsOp(op.getBounds2D(img), "drawImage", new Class[] { BufferedImage.class, BufferedImageOp.class, int.class, int.class }, new Object[] { img, op, new Integer(x), new Integer(y) });
/*      */   }
/*      */   
/*      */   public void drawString(String s, float x, float y) {
/*  870 */     Rectangle2D r2d = getFontMetrics(getFont()).getStringBounds(s, this);
/*  874 */     r2d.setRect(x, y - r2d.getHeight() + 1.0D, r2d.getWidth(), r2d.getHeight());
/*  877 */     doGraphicsOp(r2d, "drawString", new Class[] { String.class, float.class, float.class }, new Object[] { s, new Float(x), new Float(y) });
/*      */   }
/*      */   
/*      */   public void drawString(AttributedCharacterIterator iterator, int x, int y) {
/*  886 */     Rectangle2D r2d = getFontMetrics(getFont()).getStringBounds(iterator, iterator.getBeginIndex(), iterator.getEndIndex(), this);
/*  893 */     r2d.setRect(x, y - r2d.getHeight() + 1.0D, r2d.getWidth(), r2d.getHeight());
/*  896 */     doGraphicsOp(r2d, "drawString", new Class[] { AttributedCharacterIterator.class, int.class, int.class }, new Object[] { iterator, new Integer(x), new Integer(y) });
/*      */   }
/*      */   
/*      */   public void drawString(AttributedCharacterIterator iterator, float x, float y) {
/*  905 */     Rectangle2D r2d = getFontMetrics(getFont()).getStringBounds(iterator, iterator.getBeginIndex(), iterator.getEndIndex(), this);
/*  912 */     r2d.setRect(x, y - r2d.getHeight() + 1.0D, r2d.getWidth(), r2d.getHeight());
/*  915 */     doGraphicsOp(r2d, "drawString", new Class[] { AttributedCharacterIterator.class, float.class, float.class }, new Object[] { iterator, new Float(x), new Float(y) });
/*      */   }
/*      */   
/*      */   public void drawGlyphVector(GlyphVector g, float x, float y) {
/*  925 */     doGraphicsOp(g.getVisualBounds(), "drawGlyphVector", new Class[] { GlyphVector.class, float.class, float.class }, new Object[] { g, new Float(x), new Float(y) });
/*      */   }
/*      */   
/*      */   public void fill(Shape s) {
/*  933 */     doGraphicsOp(s.getBounds(), "fill", new Class[] { Shape.class }, new Object[] { s });
/*      */   }
/*      */   
/*      */   public boolean hit(Rectangle rect, Shape s, boolean onStroke) {
/*  942 */     Graphics2D g2d = getBogusGraphics2D(true);
/*  944 */     boolean hitTarget = g2d.hit(rect, s, onStroke);
/*  946 */     g2d.dispose();
/*  948 */     return hitTarget;
/*      */   }
/*      */   
/*      */   public GraphicsConfiguration getDeviceConfiguration() {
/*  952 */     Graphics2D g2d = getBogusGraphics2D(true);
/*  954 */     GraphicsConfiguration gConf = g2d.getDeviceConfiguration();
/*  956 */     g2d.dispose();
/*  958 */     return gConf;
/*      */   }
/*      */   
/*      */   public void setComposite(Composite comp) {
/*  962 */     this.composite = comp;
/*      */   }
/*      */   
/*      */   public void setPaint(Paint paint) {
/*  966 */     this.paint = paint;
/*      */   }
/*      */   
/*      */   public void setStroke(Stroke s) {
/*  970 */     this.stroke = s;
/*      */   }
/*      */   
/*      */   public void setRenderingHint(RenderingHints.Key hintKey, Object hintValue) {
/*  975 */     this.renderingHints.put(hintKey, hintValue);
/*      */   }
/*      */   
/*      */   public Object getRenderingHint(RenderingHints.Key hintKey) {
/*  979 */     return this.renderingHints.get(hintKey);
/*      */   }
/*      */   
/*      */   public void setRenderingHints(Map hints) {
/*  983 */     this.renderingHints.putAll(hints);
/*      */   }
/*      */   
/*      */   public RenderingHints getRenderingHints() {
/*  987 */     return this.renderingHints;
/*      */   }
/*      */   
/*      */   public void translate(int x, int y) {
/*  991 */     this.origin = new Point(x, y);
/*  992 */     this.transform.translate(x, y);
/*      */   }
/*      */   
/*      */   public void translate(double x, double y) {
/*  996 */     this.transform.translate(x, y);
/*      */   }
/*      */   
/*      */   public void rotate(double theta) {
/* 1000 */     this.transform.rotate(theta);
/*      */   }
/*      */   
/*      */   public void rotate(double theta, double x, double y) {
/* 1004 */     this.transform.rotate(theta, x, y);
/*      */   }
/*      */   
/*      */   public void scale(double sx, double sy) {
/* 1008 */     this.transform.scale(sx, sy);
/*      */   }
/*      */   
/*      */   public void shear(double shx, double shy) {
/* 1012 */     this.transform.shear(shx, shy);
/*      */   }
/*      */   
/*      */   public void transform(AffineTransform Tx) {
/* 1016 */     this.transform.concatenate(Tx);
/*      */   }
/*      */   
/*      */   public void setTransform(AffineTransform Tx) {
/* 1020 */     this.transform = Tx;
/*      */   }
/*      */   
/*      */   public AffineTransform getTransform() {
/* 1024 */     return this.transform;
/*      */   }
/*      */   
/*      */   public Paint getPaint() {
/* 1028 */     return this.paint;
/*      */   }
/*      */   
/*      */   public Composite getComposite() {
/* 1032 */     return this.composite;
/*      */   }
/*      */   
/*      */   public void setBackground(Color color) {
/* 1036 */     this.background = color;
/*      */   }
/*      */   
/*      */   public Color getBackground() {
/* 1040 */     return this.background;
/*      */   }
/*      */   
/*      */   public Stroke getStroke() {
/* 1044 */     return this.stroke;
/*      */   }
/*      */   
/*      */   public void clip(Shape s) {
/* 1048 */     if (this.clip == null) {
/* 1049 */       this.clip = s;
/*      */     } else {
/* 1051 */       Area clipArea = (this.clip instanceof Area) ? (Area)this.clip : new Area(this.clip);
/* 1053 */       clipArea.intersect((s instanceof Area) ? (Area)s : new Area(s));
/* 1054 */       this.clip = clipArea;
/*      */     } 
/*      */   }
/*      */   
/*      */   public FontRenderContext getFontRenderContext() {
/* 1059 */     Graphics2D g2d = getBogusGraphics2D(true);
/* 1061 */     FontRenderContext fontRenderContext = g2d.getFontRenderContext();
/* 1063 */     g2d.dispose();
/* 1065 */     return fontRenderContext;
/*      */   }
/*      */   
/*      */   void sendExceptionToListener(String message, Exception e) {
/* 1069 */     ImagingListener listener = null;
/* 1070 */     if (this.renderingHints != null)
/* 1071 */       listener = (ImagingListener)this.renderingHints.get(JAI.KEY_IMAGING_LISTENER); 
/* 1074 */     if (listener == null)
/* 1075 */       listener = JAI.getDefaultInstance().getImagingListener(); 
/* 1076 */     listener.errorOccurred(message, e, this, false);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\TiledImageGraphics.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */