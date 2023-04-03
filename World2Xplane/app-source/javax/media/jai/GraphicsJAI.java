/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Composite;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.Image;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.GlyphVector;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.BufferedImageOp;
/*     */ import java.awt.image.ImageObserver;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class GraphicsJAI extends Graphics2D {
/*     */   Graphics2D g;
/*     */   
/*     */   Component component;
/*     */   
/*     */   protected GraphicsJAI(Graphics2D g, Component component) {
/*  69 */     this.g = g;
/*  70 */     this.component = component;
/*     */   }
/*     */   
/*     */   public static GraphicsJAI createGraphicsJAI(Graphics2D g, Component component) {
/*  84 */     return new GraphicsJAI(g, component);
/*     */   }
/*     */   
/*     */   public Graphics create() {
/*  94 */     return new GraphicsJAI(this.g, this.component);
/*     */   }
/*     */   
/*     */   public Color getColor() {
/* 103 */     return this.g.getColor();
/*     */   }
/*     */   
/*     */   public void setColor(Color c) {
/* 112 */     this.g.setColor(c);
/*     */   }
/*     */   
/*     */   public void setPaintMode() {
/* 121 */     this.g.setPaintMode();
/*     */   }
/*     */   
/*     */   public void setXORMode(Color c1) {
/* 130 */     this.g.setXORMode(c1);
/*     */   }
/*     */   
/*     */   public Font getFont() {
/* 139 */     return this.g.getFont();
/*     */   }
/*     */   
/*     */   public void setFont(Font font) {
/* 148 */     this.g.setFont(font);
/*     */   }
/*     */   
/*     */   public FontMetrics getFontMetrics(Font f) {
/* 157 */     return this.g.getFontMetrics(f);
/*     */   }
/*     */   
/*     */   public Rectangle getClipBounds() {
/* 166 */     return this.g.getClipBounds();
/*     */   }
/*     */   
/*     */   public void clipRect(int x, int y, int width, int height) {
/* 175 */     this.g.clipRect(x, y, width, height);
/*     */   }
/*     */   
/*     */   public void setClip(int x, int y, int width, int height) {
/* 184 */     this.g.setClip(x, y, width, height);
/*     */   }
/*     */   
/*     */   public Shape getClip() {
/* 193 */     return this.g.getClip();
/*     */   }
/*     */   
/*     */   public void setClip(Shape clip) {
/* 202 */     this.g.setClip(clip);
/*     */   }
/*     */   
/*     */   public void copyArea(int x, int y, int width, int height, int dx, int dy) {
/* 212 */     this.g.copyArea(x, y, width, height, dx, dy);
/*     */   }
/*     */   
/*     */   public void drawLine(int x1, int y1, int x2, int y2) {
/* 221 */     this.g.drawLine(x1, y1, x2, y2);
/*     */   }
/*     */   
/*     */   public void fillRect(int x, int y, int width, int height) {
/* 230 */     this.g.fillRect(x, y, width, height);
/*     */   }
/*     */   
/*     */   public void clearRect(int x, int y, int width, int height) {
/* 239 */     this.g.clearRect(x, y, width, height);
/*     */   }
/*     */   
/*     */   public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
/* 249 */     this.g.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
/*     */   }
/*     */   
/*     */   public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
/* 259 */     this.g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
/*     */   }
/*     */   
/*     */   public void drawOval(int x, int y, int width, int height) {
/* 268 */     this.g.drawOval(x, y, width, height);
/*     */   }
/*     */   
/*     */   public void fillOval(int x, int y, int width, int height) {
/* 277 */     this.g.fillOval(x, y, width, height);
/*     */   }
/*     */   
/*     */   public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
/* 287 */     this.g.drawArc(x, y, width, height, startAngle, arcAngle);
/*     */   }
/*     */   
/*     */   public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
/* 297 */     this.g.fillArc(x, y, width, height, startAngle, arcAngle);
/*     */   }
/*     */   
/*     */   public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
/* 307 */     this.g.drawPolyline(xPoints, yPoints, nPoints);
/*     */   }
/*     */   
/*     */   public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
/* 317 */     this.g.drawPolygon(xPoints, yPoints, nPoints);
/*     */   }
/*     */   
/*     */   public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
/* 327 */     this.g.fillPolygon(xPoints, yPoints, nPoints);
/*     */   }
/*     */   
/*     */   public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
/* 337 */     return this.g.drawImage(img, x, y, observer);
/*     */   }
/*     */   
/*     */   public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
/* 349 */     return this.g.drawImage(img, x, y, width, height, observer);
/*     */   }
/*     */   
/*     */   public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
/* 362 */     return this.g.drawImage(img, x, y, bgcolor, observer);
/*     */   }
/*     */   
/*     */   public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
/* 377 */     return this.g.drawImage(img, x, y, width, height, bgcolor, observer);
/*     */   }
/*     */   
/*     */   public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
/* 393 */     return this.g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
/*     */   }
/*     */   
/*     */   public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
/* 409 */     return this.g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);
/*     */   }
/*     */   
/*     */   public void dispose() {
/* 422 */     this.g.dispose();
/*     */   }
/*     */   
/*     */   public void draw(Shape s) {
/* 431 */     this.g.draw(s);
/*     */   }
/*     */   
/*     */   public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs) {
/* 442 */     return this.g.drawImage(img, xform, obs);
/*     */   }
/*     */   
/*     */   public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y) {
/* 455 */     this.g.drawImage(img, op, x, y);
/*     */   }
/*     */   
/*     */   public void drawRenderedImage(RenderedImage img, AffineTransform xform) {
/* 465 */     this.g.drawRenderedImage(img, xform);
/*     */   }
/*     */   
/*     */   public void drawRenderableImage(RenderableImage img, AffineTransform xform) {
/* 475 */     this.g.drawRenderableImage(img, xform);
/*     */   }
/*     */   
/*     */   public void drawString(String str, int x, int y) {
/* 484 */     this.g.drawString(str, x, y);
/*     */   }
/*     */   
/*     */   public void drawString(String s, float x, float y) {
/* 493 */     this.g.drawString(s, x, y);
/*     */   }
/*     */   
/*     */   public void drawString(AttributedCharacterIterator iterator, int x, int y) {
/* 503 */     this.g.drawString(iterator, x, y);
/*     */   }
/*     */   
/*     */   public void drawString(AttributedCharacterIterator iterator, float x, float y) {
/* 513 */     this.g.drawString(iterator, x, y);
/*     */   }
/*     */   
/*     */   public void drawGlyphVector(GlyphVector g, float x, float y) {
/* 522 */     this.g.drawGlyphVector(g, x, y);
/*     */   }
/*     */   
/*     */   public void fill(Shape s) {
/* 531 */     this.g.fill(s);
/*     */   }
/*     */   
/*     */   public boolean hit(Rectangle rect, Shape s, boolean onStroke) {
/* 542 */     return this.g.hit(rect, s, onStroke);
/*     */   }
/*     */   
/*     */   public GraphicsConfiguration getDeviceConfiguration() {
/* 551 */     return this.g.getDeviceConfiguration();
/*     */   }
/*     */   
/*     */   public void setComposite(Composite comp) {
/* 560 */     this.g.setComposite(comp);
/*     */   }
/*     */   
/*     */   public void setPaint(Paint paint) {
/* 569 */     this.g.setPaint(paint);
/*     */   }
/*     */   
/*     */   public void setStroke(Stroke s) {
/* 578 */     this.g.setStroke(s);
/*     */   }
/*     */   
/*     */   public void setRenderingHint(RenderingHints.Key hintKey, Object hintValue) {
/* 587 */     this.g.setRenderingHint(hintKey, hintValue);
/*     */   }
/*     */   
/*     */   public Object getRenderingHint(RenderingHints.Key hintKey) {
/* 596 */     return this.g.getRenderingHint(hintKey);
/*     */   }
/*     */   
/*     */   public void setRenderingHints(Map hints) {
/* 605 */     this.g.setRenderingHints(hints);
/*     */   }
/*     */   
/*     */   public void addRenderingHints(Map hints) {
/* 614 */     this.g.addRenderingHints(hints);
/*     */   }
/*     */   
/*     */   public RenderingHints getRenderingHints() {
/* 623 */     return this.g.getRenderingHints();
/*     */   }
/*     */   
/*     */   public void translate(int x, int y) {
/* 632 */     this.g.translate(x, y);
/*     */   }
/*     */   
/*     */   public void translate(double tx, double ty) {
/* 641 */     this.g.translate(tx, ty);
/*     */   }
/*     */   
/*     */   public void rotate(double theta) {
/* 650 */     this.g.rotate(theta);
/*     */   }
/*     */   
/*     */   public void rotate(double theta, double x, double y) {
/* 659 */     this.g.rotate(theta, x, y);
/*     */   }
/*     */   
/*     */   public void scale(double sx, double sy) {
/* 668 */     this.g.scale(sx, sy);
/*     */   }
/*     */   
/*     */   public void shear(double shx, double shy) {
/* 677 */     this.g.shear(shx, shy);
/*     */   }
/*     */   
/*     */   public void transform(AffineTransform Tx) {
/* 686 */     this.g.transform(Tx);
/*     */   }
/*     */   
/*     */   public void setTransform(AffineTransform Tx) {
/* 695 */     this.g.setTransform(Tx);
/*     */   }
/*     */   
/*     */   public AffineTransform getTransform() {
/* 704 */     return this.g.getTransform();
/*     */   }
/*     */   
/*     */   public Paint getPaint() {
/* 713 */     return this.g.getPaint();
/*     */   }
/*     */   
/*     */   public Composite getComposite() {
/* 722 */     return this.g.getComposite();
/*     */   }
/*     */   
/*     */   public void setBackground(Color color) {
/* 731 */     this.g.setBackground(color);
/*     */   }
/*     */   
/*     */   public Color getBackground() {
/* 740 */     return this.g.getBackground();
/*     */   }
/*     */   
/*     */   public Stroke getStroke() {
/* 749 */     return this.g.getStroke();
/*     */   }
/*     */   
/*     */   public void clip(Shape s) {
/* 758 */     this.g.clip(s);
/*     */   }
/*     */   
/*     */   public FontRenderContext getFontRenderContext() {
/* 767 */     return this.g.getFontRenderContext();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\GraphicsJAI.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */