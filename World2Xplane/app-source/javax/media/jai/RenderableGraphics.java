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
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.awt.image.BufferedImageOp;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ImageObserver;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.renderable.RenderContext;
/*      */ import java.awt.image.renderable.RenderableImage;
/*      */ import java.lang.reflect.Method;
/*      */ import java.text.AttributedCharacterIterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.ListIterator;
/*      */ import java.util.Map;
/*      */ import java.util.Vector;
/*      */ import javax.media.jai.util.ImagingException;
/*      */ import javax.media.jai.util.ImagingListener;
/*      */ 
/*      */ public class RenderableGraphics extends Graphics2D implements RenderableImage {
/*   84 */   private static final Class GRAPHICS2D_CLASS = Graphics2D.class;
/*      */   
/*      */   private Rectangle2D dimensions;
/*      */   
/*      */   private LinkedList opArgList;
/*      */   
/*      */   private Point origin;
/*      */   
/*      */   private Shape clip;
/*      */   
/*      */   private Color color;
/*      */   
/*      */   private Font font;
/*      */   
/*      */   private Color background;
/*      */   
/*      */   private Composite composite;
/*      */   
/*      */   private Paint paint;
/*      */   
/*      */   private Stroke stroke;
/*      */   
/*  103 */   private RenderingHints renderingHints = new RenderingHints(null);
/*      */   
/*      */   private AffineTransform transform;
/*      */   
/*      */   public RenderableGraphics(Rectangle2D dimensions) {
/*  113 */     this(dimensions, new LinkedList(), new Point(0, 0), null);
/*      */   }
/*      */   
/*      */   private RenderableGraphics(Rectangle2D dimensions, LinkedList opArgList, Point origin, Graphics2D g) {
/*  129 */     if (dimensions.isEmpty())
/*  130 */       throw new RuntimeException(JaiI18N.getString("RenderableGraphics0")); 
/*  134 */     this.dimensions = dimensions;
/*  135 */     this.opArgList = opArgList;
/*  138 */     Graphics2D g2d = g;
/*  139 */     if (g2d == null)
/*  140 */       g2d = getBogusGraphics2D(); 
/*  144 */     this.origin = (Point)origin.clone();
/*  145 */     setClip(g2d.getClip());
/*  146 */     setColor(g2d.getColor());
/*  147 */     setFont(g2d.getFont());
/*  150 */     setBackground(g2d.getBackground());
/*  151 */     setComposite(g2d.getComposite());
/*  152 */     setRenderingHints(g2d.getRenderingHints());
/*  153 */     setStroke(g2d.getStroke());
/*  154 */     setTransform(g2d.getTransform());
/*  157 */     if (g == null)
/*  157 */       g2d.dispose(); 
/*      */   }
/*      */   
/*      */   private Graphics2D getBogusGraphics2D() {
/*  171 */     TiledImage ti = createTiledImage(this.renderingHints, this.dimensions.getBounds());
/*  174 */     return ti.createGraphics();
/*      */   }
/*      */   
/*      */   private TiledImage createTiledImage(RenderingHints hints, Rectangle bounds) {
/*  188 */     int tileWidth = bounds.width;
/*  189 */     int tileHeight = bounds.height;
/*  194 */     SampleModel sm = null;
/*  195 */     ColorModel cm = null;
/*  196 */     RenderingHints hintsObserved = null;
/*  197 */     if (hints != null) {
/*  199 */       ImageLayout layout = (ImageLayout)hints.get(JAI.KEY_IMAGE_LAYOUT);
/*  201 */       if (layout != null) {
/*  203 */         hintsObserved = new RenderingHints(null);
/*  204 */         ImageLayout layoutObserved = new ImageLayout();
/*  207 */         if (layout.isValid(256)) {
/*  208 */           sm = layout.getSampleModel(null);
/*  209 */           if (sm.getWidth() != tileWidth || sm.getHeight() != tileHeight)
/*  211 */             sm = sm.createCompatibleSampleModel(tileWidth, tileHeight); 
/*  214 */           if (layoutObserved != null)
/*  215 */             layoutObserved.setSampleModel(sm); 
/*      */         } 
/*  220 */         if (layout.isValid(512)) {
/*  221 */           cm = layout.getColorModel(null);
/*  222 */           if (layoutObserved != null)
/*  223 */             layoutObserved.setColorModel(cm); 
/*      */         } 
/*  228 */         if (layout.isValid(64)) {
/*  229 */           tileWidth = layout.getTileWidth(null);
/*  230 */           if (layoutObserved != null)
/*  231 */             layoutObserved.setTileWidth(tileWidth); 
/*  233 */         } else if (sm != null) {
/*  234 */           tileWidth = sm.getWidth();
/*      */         } 
/*  236 */         if (layout.isValid(128)) {
/*  237 */           tileHeight = layout.getTileHeight(null);
/*  238 */           if (layoutObserved != null)
/*  239 */             layoutObserved.setTileHeight(tileHeight); 
/*  241 */         } else if (sm != null) {
/*  242 */           tileHeight = sm.getHeight();
/*      */         } 
/*  246 */         hintsObserved.put(JAI.KEY_IMAGE_LAYOUT, layoutObserved);
/*      */       } 
/*      */     } 
/*  251 */     if (sm != null && (sm.getWidth() != tileWidth || sm.getHeight() != tileHeight))
/*  253 */       sm = sm.createCompatibleSampleModel(tileWidth, tileHeight); 
/*  257 */     if (cm != null && (sm == null || !JDKWorkarounds.areCompatibleDataModels(sm, cm))) {
/*  261 */       sm = cm.createCompatibleSampleModel(tileWidth, tileHeight);
/*  262 */     } else if (cm == null && sm != null) {
/*  265 */       cm = PlanarImage.createColorModel(sm);
/*  269 */       ColorModel cmRGB = ColorModel.getRGBdefault();
/*  270 */       if (cm == null && JDKWorkarounds.areCompatibleDataModels(sm, cmRGB))
/*  272 */         cm = cmRGB; 
/*      */     } 
/*  277 */     TiledImage ti = null;
/*  278 */     if (sm != null) {
/*  280 */       ti = new TiledImage(bounds.x, bounds.y, bounds.width, bounds.height, bounds.x, bounds.y, sm, cm);
/*      */     } else {
/*  286 */       ti = TiledImage.createInterleaved(bounds.x, bounds.y, bounds.width, bounds.height, 3, 0, tileWidth, tileHeight, new int[] { 0, 1, 2 });
/*      */     } 
/*  294 */     if (hintsObserved != null)
/*  295 */       ti.setProperty("HINTS_OBSERVED", hintsObserved); 
/*  298 */     return ti;
/*      */   }
/*      */   
/*      */   private void queueOpArg(String name, Class[] argTypes, Object[] args) {
/*  319 */     Method method = null;
/*      */     try {
/*  321 */       method = GRAPHICS2D_CLASS.getMethod(name, argTypes);
/*  322 */     } catch (Exception e) {
/*  323 */       String message = JaiI18N.getString("TiledGraphicsGraphics2") + name;
/*  324 */       sendExceptionToListener(message, (Exception)new ImagingException(e));
/*      */     } 
/*  330 */     this.opArgList.addLast(method);
/*  331 */     this.opArgList.addLast(args);
/*      */   }
/*      */   
/*      */   private void evaluateOpList(Graphics2D g2d) {
/*  342 */     if (this.opArgList == null)
/*      */       return; 
/*  346 */     ListIterator li = this.opArgList.listIterator(0);
/*  348 */     while (li.hasNext()) {
/*  349 */       Method method = li.next();
/*  350 */       Object[] args = (Object[])li.next();
/*      */       try {
/*  353 */         method.invoke(g2d, args);
/*  354 */       } catch (Exception e) {
/*  355 */         String message = JaiI18N.getString("TiledGraphicsGraphics4") + method;
/*  356 */         sendExceptionToListener(message, (Exception)new ImagingException(e));
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public Graphics create() {
/*  366 */     return new RenderableGraphics(this.dimensions, this.opArgList, this.origin, this);
/*      */   }
/*      */   
/*      */   public Color getColor() {
/*  373 */     return this.color;
/*      */   }
/*      */   
/*      */   public void setColor(Color c) {
/*  377 */     this.color = c;
/*  379 */     queueOpArg("setColor", new Class[] { Color.class }, new Object[] { c });
/*      */   }
/*      */   
/*      */   public void setPaintMode() {
/*  385 */     queueOpArg("setPaintMode", null, null);
/*      */   }
/*      */   
/*      */   public void setXORMode(Color c1) {
/*  389 */     queueOpArg("setXORMode", new Class[] { Color.class }, new Object[] { c1 });
/*      */   }
/*      */   
/*      */   public Font getFont() {
/*  395 */     return this.font;
/*      */   }
/*      */   
/*      */   public void setFont(Font font) {
/*  399 */     this.font = font;
/*  401 */     queueOpArg("setFont", new Class[] { Font.class }, new Object[] { font });
/*      */   }
/*      */   
/*      */   public FontMetrics getFontMetrics(Font f) {
/*  407 */     Graphics2D g2d = getBogusGraphics2D();
/*  409 */     FontMetrics fontMetrics = g2d.getFontMetrics(f);
/*  411 */     g2d.dispose();
/*  413 */     return fontMetrics;
/*      */   }
/*      */   
/*      */   public Rectangle getClipBounds() {
/*  417 */     return this.clip.getBounds();
/*      */   }
/*      */   
/*      */   public void clipRect(int x, int y, int width, int height) {
/*  421 */     clip(new Rectangle(x, y, width, height));
/*      */   }
/*      */   
/*      */   public void setClip(int x, int y, int width, int height) {
/*  425 */     this.clip = new Rectangle(x, y, width, height);
/*  427 */     queueOpArg("setClip", new Class[] { int.class, int.class, int.class, int.class }, new Object[] { new Integer(x), new Integer(y), new Integer(width), new Integer(height) });
/*      */   }
/*      */   
/*      */   public Shape getClip() {
/*  434 */     return this.clip;
/*      */   }
/*      */   
/*      */   public void setClip(Shape clip) {
/*  438 */     this.clip = clip;
/*  440 */     queueOpArg("setClip", new Class[] { Shape.class }, new Object[] { clip });
/*      */   }
/*      */   
/*      */   public void copyArea(int x, int y, int width, int height, int dx, int dy) {
/*  447 */     queueOpArg("copyArea", new Class[] { int.class, int.class, int.class, int.class, int.class, int.class }, new Object[] { new Integer(x), new Integer(y), new Integer(width), new Integer(height), new Integer(dx), new Integer(dy) });
/*      */   }
/*      */   
/*      */   public void drawLine(int x1, int y1, int x2, int y2) {
/*  456 */     queueOpArg("drawLine", new Class[] { int.class, int.class, int.class, int.class }, new Object[] { new Integer(x1), new Integer(y1), new Integer(x2), new Integer(y2) });
/*      */   }
/*      */   
/*      */   public void fillRect(int x, int y, int width, int height) {
/*  463 */     queueOpArg("fillRect", new Class[] { int.class, int.class, int.class, int.class }, new Object[] { new Integer(x), new Integer(y), new Integer(width), new Integer(height) });
/*      */   }
/*      */   
/*      */   public void clearRect(int x, int y, int width, int height) {
/*  473 */     queueOpArg("clearRect", new Class[] { int.class, int.class, int.class, int.class }, new Object[] { new Integer(x), new Integer(y), new Integer(width), new Integer(height) });
/*      */   }
/*      */   
/*      */   public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
/*  481 */     queueOpArg("drawRoundRect", new Class[] { int.class, int.class, int.class, int.class, int.class, int.class }, new Object[] { new Integer(x), new Integer(y), new Integer(width), new Integer(height), new Integer(arcWidth), new Integer(arcHeight) });
/*      */   }
/*      */   
/*      */   public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
/*  492 */     queueOpArg("fillRoundRect", new Class[] { int.class, int.class, int.class, int.class, int.class, int.class }, new Object[] { new Integer(x), new Integer(y), new Integer(width), new Integer(height), new Integer(arcWidth), new Integer(arcHeight) });
/*      */   }
/*      */   
/*      */   public void draw3DRect(int x, int y, int width, int height, boolean raised) {
/*  505 */     queueOpArg("draw3DRect", new Class[] { int.class, int.class, int.class, int.class, boolean.class }, new Object[] { new Integer(x), new Integer(y), new Integer(width), new Integer(height), new Boolean(raised) });
/*      */   }
/*      */   
/*      */   public void fill3DRect(int x, int y, int width, int height, boolean raised) {
/*  517 */     queueOpArg("fill3DRect", new Class[] { int.class, int.class, int.class, int.class, boolean.class }, new Object[] { new Integer(x), new Integer(y), new Integer(width), new Integer(height), new Boolean(raised) });
/*      */   }
/*      */   
/*      */   public void drawOval(int x, int y, int width, int height) {
/*  526 */     queueOpArg("drawOval", new Class[] { int.class, int.class, int.class, int.class }, new Object[] { new Integer(x), new Integer(y), new Integer(width), new Integer(height) });
/*      */   }
/*      */   
/*      */   public void fillOval(int x, int y, int width, int height) {
/*  533 */     queueOpArg("fillOval", new Class[] { int.class, int.class, int.class, int.class }, new Object[] { new Integer(x), new Integer(y), new Integer(width), new Integer(height) });
/*      */   }
/*      */   
/*      */   public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
/*  541 */     queueOpArg("drawArc", new Class[] { int.class, int.class, int.class, int.class, int.class, int.class }, new Object[] { new Integer(x), new Integer(y), new Integer(width), new Integer(height), new Integer(startAngle), new Integer(arcAngle) });
/*      */   }
/*      */   
/*      */   public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
/*  552 */     queueOpArg("fillArc", new Class[] { int.class, int.class, int.class, int.class, int.class, int.class }, new Object[] { new Integer(x), new Integer(y), new Integer(width), new Integer(height), new Integer(startAngle), new Integer(arcAngle) });
/*      */   }
/*      */   
/*      */   public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
/*  562 */     Class intArrayClass = xPoints.getClass();
/*  563 */     queueOpArg("drawPolyline", new Class[] { intArrayClass, intArrayClass, int.class }, new Object[] { xPoints, yPoints, new Integer(nPoints) });
/*      */   }
/*      */   
/*      */   public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
/*  569 */     Class intArrayClass = xPoints.getClass();
/*  570 */     queueOpArg("drawPolygon", new Class[] { intArrayClass, intArrayClass, int.class }, new Object[] { xPoints, yPoints, new Integer(nPoints) });
/*      */   }
/*      */   
/*      */   public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
/*  578 */     Class intArrayClass = xPoints.getClass();
/*  579 */     queueOpArg("fillPolygon", new Class[] { intArrayClass, intArrayClass, int.class }, new Object[] { xPoints, yPoints, new Integer(nPoints) });
/*      */   }
/*      */   
/*      */   public void drawString(String str, int x, int y) {
/*  587 */     queueOpArg("drawString", new Class[] { String.class, int.class, int.class }, new Object[] { str, new Integer(x), new Integer(y) });
/*      */   }
/*      */   
/*      */   public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
/*  597 */     queueOpArg("drawImage", new Class[] { Image.class, int.class, int.class, ImageObserver.class }, new Object[] { img, new Integer(x), new Integer(y), observer });
/*  603 */     return true;
/*      */   }
/*      */   
/*      */   public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
/*  609 */     queueOpArg("drawImage", new Class[] { Image.class, int.class, int.class, int.class, int.class, ImageObserver.class }, new Object[] { img, new Integer(x), new Integer(y), new Integer(width), new Integer(height), observer });
/*  616 */     return true;
/*      */   }
/*      */   
/*      */   public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
/*  622 */     queueOpArg("drawImage", new Class[] { Image.class, int.class, int.class, Color.class, ImageObserver.class }, new Object[] { img, new Integer(x), new Integer(y), bgcolor, observer });
/*  629 */     return true;
/*      */   }
/*      */   
/*      */   public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
/*  636 */     queueOpArg("drawImage", new Class[] { Image.class, int.class, int.class, int.class, int.class, Color.class, ImageObserver.class }, new Object[] { img, new Integer(x), new Integer(y), new Integer(width), new Integer(height), bgcolor, observer });
/*  644 */     return true;
/*      */   }
/*      */   
/*      */   public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
/*  651 */     queueOpArg("drawImage", new Class[] { Image.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, ImageObserver.class }, new Object[] { img, new Integer(dx1), new Integer(dy1), new Integer(dx2), new Integer(dy2), new Integer(sx1), new Integer(sy1), new Integer(sx2), new Integer(sy2), observer });
/*  661 */     return true;
/*      */   }
/*      */   
/*      */   public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
/*  669 */     queueOpArg("drawImage", new Class[] { 
/*  669 */           Image.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, Color.class, 
/*  669 */           ImageObserver.class }, new Object[] { 
/*  669 */           img, new Integer(dx1), new Integer(dy1), new Integer(dx2), new Integer(dy2), new Integer(sx1), new Integer(sy1), new Integer(sx2), new Integer(sy2), bgcolor, 
/*  669 */           observer });
/*  680 */     return true;
/*      */   }
/*      */   
/*      */   public void dispose() {
/*  684 */     queueOpArg("dispose", null, null);
/*      */   }
/*      */   
/*      */   public void addRenderingHints(Map hints) {
/*  693 */     this.renderingHints.putAll(hints);
/*  695 */     queueOpArg("addRenderingHints", new Class[] { Map.class }, new Object[] { hints });
/*      */   }
/*      */   
/*      */   public void draw(Shape s) {
/*  701 */     queueOpArg("draw", new Class[] { Shape.class }, new Object[] { s });
/*      */   }
/*      */   
/*      */   public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs) {
/*  709 */     queueOpArg("drawImage", new Class[] { Image.class, AffineTransform.class, ImageObserver.class }, new Object[] { img, xform, obs });
/*  714 */     return true;
/*      */   }
/*      */   
/*      */   public void drawRenderedImage(RenderedImage img, AffineTransform xform) {
/*  720 */     queueOpArg("drawRenderedImage", new Class[] { RenderedImage.class, AffineTransform.class }, new Object[] { img, xform });
/*      */   }
/*      */   
/*      */   public void drawRenderableImage(RenderableImage img, AffineTransform xform) {
/*  728 */     queueOpArg("drawRenderableImage", new Class[] { RenderableImage.class, AffineTransform.class }, new Object[] { img, xform });
/*      */   }
/*      */   
/*      */   public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y) {
/*  738 */     queueOpArg("drawImage", new Class[] { BufferedImage.class, BufferedImageOp.class, int.class, int.class }, new Object[] { img, op, new Integer(x), new Integer(y) });
/*      */   }
/*      */   
/*      */   public void drawString(String s, float x, float y) {
/*  748 */     queueOpArg("drawString", new Class[] { String.class, float.class, float.class }, new Object[] { s, new Float(x), new Float(y) });
/*      */   }
/*      */   
/*      */   public void drawString(AttributedCharacterIterator iterator, int x, int y) {
/*  756 */     queueOpArg("drawString", new Class[] { AttributedCharacterIterator.class, int.class, int.class }, new Object[] { iterator, new Integer(x), new Integer(y) });
/*      */   }
/*      */   
/*      */   public void drawString(AttributedCharacterIterator iterator, float x, float y) {
/*  764 */     queueOpArg("drawString", new Class[] { AttributedCharacterIterator.class, float.class, float.class }, new Object[] { iterator, new Float(x), new Float(y) });
/*      */   }
/*      */   
/*      */   public void drawGlyphVector(GlyphVector v, float x, float y) {
/*  773 */     queueOpArg("drawGlyphVector", new Class[] { GlyphVector.class, float.class, float.class }, new Object[] { v, new Float(x), new Float(y) });
/*      */   }
/*      */   
/*      */   public void fill(Shape s) {
/*  781 */     queueOpArg("fill", new Class[] { Shape.class }, new Object[] { s });
/*      */   }
/*      */   
/*      */   public boolean hit(Rectangle rect, Shape s, boolean onStroke) {
/*  789 */     Graphics2D g2d = getBogusGraphics2D();
/*  791 */     boolean hitTarget = g2d.hit(rect, s, onStroke);
/*  793 */     g2d.dispose();
/*  795 */     return hitTarget;
/*      */   }
/*      */   
/*      */   public GraphicsConfiguration getDeviceConfiguration() {
/*  799 */     Graphics2D g2d = getBogusGraphics2D();
/*  801 */     GraphicsConfiguration gConf = g2d.getDeviceConfiguration();
/*  803 */     g2d.dispose();
/*  805 */     return gConf;
/*      */   }
/*      */   
/*      */   public FontRenderContext getFontRenderContext() {
/*  809 */     Graphics2D g2d = getBogusGraphics2D();
/*  811 */     FontRenderContext fontRenderContext = g2d.getFontRenderContext();
/*  813 */     g2d.dispose();
/*  815 */     return fontRenderContext;
/*      */   }
/*      */   
/*      */   public void setComposite(Composite comp) {
/*  819 */     this.composite = comp;
/*  820 */     queueOpArg("setComposite", new Class[] { Composite.class }, new Object[] { comp });
/*      */   }
/*      */   
/*      */   public void setPaint(Paint paint) {
/*  826 */     this.paint = paint;
/*  827 */     queueOpArg("setPaint", new Class[] { Paint.class }, new Object[] { paint });
/*      */   }
/*      */   
/*      */   public void setStroke(Stroke s) {
/*  833 */     this.stroke = s;
/*  834 */     queueOpArg("setStroke", new Class[] { Stroke.class }, new Object[] { s });
/*      */   }
/*      */   
/*      */   public void setRenderingHint(RenderingHints.Key hintKey, Object hintValue) {
/*  841 */     this.renderingHints.put(hintKey, hintValue);
/*  843 */     queueOpArg("setRenderingHint", new Class[] { RenderingHints.Key.class, Object.class }, new Object[] { hintKey, hintValue });
/*      */   }
/*      */   
/*      */   public Object getRenderingHint(RenderingHints.Key hintKey) {
/*  850 */     return this.renderingHints.get(hintKey);
/*      */   }
/*      */   
/*      */   public void setRenderingHints(Map hints) {
/*  854 */     this.renderingHints.putAll(hints);
/*  856 */     queueOpArg("setRenderingHints", new Class[] { Map.class }, new Object[] { hints });
/*      */   }
/*      */   
/*      */   public RenderingHints getRenderingHints() {
/*  862 */     return this.renderingHints;
/*      */   }
/*      */   
/*      */   public void translate(int x, int y) {
/*  866 */     this.origin = new Point(x, y);
/*  867 */     this.transform.translate(x, y);
/*  869 */     queueOpArg("translate", new Class[] { int.class, int.class }, new Object[] { new Integer(x), new Integer(y) });
/*      */   }
/*      */   
/*      */   public void translate(double x, double y) {
/*  875 */     this.transform.translate(x, y);
/*  877 */     queueOpArg("translate", new Class[] { double.class, double.class }, new Object[] { new Double(x), new Double(y) });
/*      */   }
/*      */   
/*      */   public void rotate(double theta) {
/*  883 */     this.transform.rotate(theta);
/*  885 */     queueOpArg("rotate", new Class[] { double.class }, new Object[] { new Double(theta) });
/*      */   }
/*      */   
/*      */   public void rotate(double theta, double x, double y) {
/*  891 */     this.transform.rotate(theta, x, y);
/*  893 */     queueOpArg("rotate", new Class[] { double.class, double.class, double.class }, new Object[] { new Double(theta), new Double(x), new Double(y) });
/*      */   }
/*      */   
/*      */   public void scale(double sx, double sy) {
/*  900 */     this.transform.scale(sx, sy);
/*  902 */     queueOpArg("scale", new Class[] { double.class, double.class }, new Object[] { new Double(sx), new Double(sy) });
/*      */   }
/*      */   
/*      */   public void shear(double shx, double shy) {
/*  908 */     this.transform.shear(shx, shy);
/*  910 */     queueOpArg("shear", new Class[] { double.class, double.class }, new Object[] { new Double(shx), new Double(shy) });
/*      */   }
/*      */   
/*      */   public void transform(AffineTransform Tx) {
/*  916 */     this.transform.concatenate(Tx);
/*  918 */     queueOpArg("transform", new Class[] { AffineTransform.class }, new Object[] { Tx });
/*      */   }
/*      */   
/*      */   public void setTransform(AffineTransform Tx) {
/*  924 */     this.transform = Tx;
/*  926 */     queueOpArg("setTransform", new Class[] { AffineTransform.class }, new Object[] { Tx });
/*      */   }
/*      */   
/*      */   public AffineTransform getTransform() {
/*  932 */     return this.transform;
/*      */   }
/*      */   
/*      */   public Paint getPaint() {
/*  936 */     return this.paint;
/*      */   }
/*      */   
/*      */   public Composite getComposite() {
/*  940 */     return this.composite;
/*      */   }
/*      */   
/*      */   public void setBackground(Color color) {
/*  944 */     this.background = color;
/*  946 */     queueOpArg("setBackground", new Class[] { Color.class }, new Object[] { color });
/*      */   }
/*      */   
/*      */   public Color getBackground() {
/*  952 */     return this.background;
/*      */   }
/*      */   
/*      */   public Stroke getStroke() {
/*  956 */     return this.stroke;
/*      */   }
/*      */   
/*      */   public void clip(Shape s) {
/*  960 */     if (this.clip == null) {
/*  961 */       this.clip = s;
/*      */     } else {
/*  963 */       Area clipArea = (this.clip instanceof Area) ? (Area)this.clip : new Area(this.clip);
/*  965 */       clipArea.intersect((s instanceof Area) ? (Area)s : new Area(s));
/*  966 */       this.clip = clipArea;
/*      */     } 
/*  969 */     queueOpArg("clip", new Class[] { Shape.class }, new Object[] { s });
/*      */   }
/*      */   
/*      */   public Vector getSources() {
/*  977 */     return null;
/*      */   }
/*      */   
/*      */   public Object getProperty(String name) {
/*  981 */     return Image.UndefinedProperty;
/*      */   }
/*      */   
/*      */   public String[] getPropertyNames() {
/*  985 */     return null;
/*      */   }
/*      */   
/*      */   public boolean isDynamic() {
/*  989 */     return false;
/*      */   }
/*      */   
/*      */   public float getWidth() {
/*  993 */     return (float)this.dimensions.getWidth();
/*      */   }
/*      */   
/*      */   public float getHeight() {
/*  997 */     return (float)this.dimensions.getHeight();
/*      */   }
/*      */   
/*      */   public float getMinX() {
/* 1001 */     return (float)this.dimensions.getMinX();
/*      */   }
/*      */   
/*      */   public float getMinY() {
/* 1005 */     return (float)this.dimensions.getMinY();
/*      */   }
/*      */   
/*      */   public RenderedImage createScaledRendering(int w, int h, RenderingHints hints) {
/* 1010 */     if (w <= 0 && h <= 0)
/* 1011 */       throw new IllegalArgumentException(JaiI18N.getString("RenderableGraphics1")); 
/* 1012 */     if (w <= 0) {
/* 1013 */       w = (int)Math.round(h * this.dimensions.getWidth() / this.dimensions.getHeight());
/* 1014 */     } else if (h <= 0) {
/* 1015 */       h = (int)Math.round(w * this.dimensions.getHeight() / this.dimensions.getWidth());
/*      */     } 
/* 1018 */     double sx = w / this.dimensions.getWidth();
/* 1019 */     double sy = h / this.dimensions.getHeight();
/* 1020 */     AffineTransform usr2dev = new AffineTransform();
/* 1021 */     usr2dev.setToScale(sx, sy);
/* 1023 */     return createRendering(new RenderContext(usr2dev, hints));
/*      */   }
/*      */   
/*      */   public RenderedImage createDefaultRendering() {
/* 1027 */     return createRendering(new RenderContext(new AffineTransform()));
/*      */   }
/*      */   
/*      */   public RenderedImage createRendering(RenderContext renderContext) {
/* 1065 */     AffineTransform usr2dev = renderContext.getTransform();
/* 1066 */     if (usr2dev == null)
/* 1067 */       usr2dev = new AffineTransform(); 
/* 1069 */     RenderingHints hints = renderContext.getRenderingHints();
/* 1070 */     Shape aoi = renderContext.getAreaOfInterest();
/* 1071 */     if (aoi == null)
/* 1072 */       aoi = this.dimensions.getBounds(); 
/* 1076 */     Shape transformedAOI = usr2dev.createTransformedShape(aoi);
/* 1079 */     TiledImage ti = createTiledImage(hints, transformedAOI.getBounds());
/* 1084 */     Graphics2D g2d = ti.createGraphics();
/* 1090 */     if (!usr2dev.isIdentity()) {
/* 1091 */       AffineTransform tf = getTransform();
/* 1092 */       tf.concatenate(usr2dev);
/* 1093 */       g2d.setTransform(tf);
/*      */     } 
/* 1095 */     if (hints != null)
/* 1096 */       g2d.addRenderingHints(hints); 
/* 1098 */     g2d.setClip(aoi);
/* 1101 */     evaluateOpList(g2d);
/* 1104 */     g2d.dispose();
/* 1106 */     return ti;
/*      */   }
/*      */   
/*      */   void sendExceptionToListener(String message, Exception e) {
/* 1110 */     ImagingListener listener = null;
/* 1111 */     if (this.renderingHints != null)
/* 1112 */       listener = (ImagingListener)this.renderingHints.get(JAI.KEY_IMAGING_LISTENER); 
/* 1115 */     if (listener == null)
/* 1116 */       listener = JAI.getDefaultInstance().getImagingListener(); 
/* 1117 */     listener.errorOccurred(message, e, this, false);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\RenderableGraphics.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */