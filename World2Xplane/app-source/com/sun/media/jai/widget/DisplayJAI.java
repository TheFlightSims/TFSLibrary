/*     */ package com.sun.media.jai.widget;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.RenderedImage;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class DisplayJAI extends JPanel implements MouseListener, MouseMotionListener {
/*  54 */   protected RenderedImage source = null;
/*     */   
/*  57 */   protected int originX = 0;
/*     */   
/*  60 */   protected int originY = 0;
/*     */   
/*     */   public DisplayJAI() {
/*  68 */     setLayout((LayoutManager)null);
/*     */   }
/*     */   
/*     */   public DisplayJAI(RenderedImage image) {
/*  85 */     setLayout((LayoutManager)null);
/*  87 */     if (image == null)
/*  88 */       throw new IllegalArgumentException("image == null!"); 
/*  91 */     this.source = image;
/*  94 */     int w = this.source.getWidth();
/*  95 */     int h = this.source.getHeight();
/*  96 */     Insets insets = getInsets();
/*  97 */     Dimension dim = new Dimension(w + insets.left + insets.right, h + insets.top + insets.bottom);
/* 101 */     setPreferredSize(dim);
/*     */   }
/*     */   
/*     */   public void setOrigin(int x, int y) {
/* 114 */     this.originX = x;
/* 115 */     this.originY = y;
/* 116 */     repaint();
/*     */   }
/*     */   
/*     */   public Point getOrigin() {
/* 121 */     return new Point(this.originX, this.originY);
/*     */   }
/*     */   
/*     */   public void set(RenderedImage im) {
/* 137 */     if (im == null)
/* 138 */       throw new IllegalArgumentException("im == null!"); 
/* 141 */     this.source = im;
/* 144 */     int w = this.source.getWidth();
/* 145 */     int h = this.source.getHeight();
/* 146 */     Insets insets = getInsets();
/* 147 */     Dimension dim = new Dimension(w + insets.left + insets.right, h + insets.top + insets.bottom);
/* 151 */     setPreferredSize(dim);
/* 152 */     revalidate();
/* 153 */     repaint();
/*     */   }
/*     */   
/*     */   public void set(RenderedImage im, int x, int y) {
/* 171 */     if (im == null)
/* 172 */       throw new IllegalArgumentException("im == null!"); 
/* 175 */     this.source = im;
/* 178 */     int w = this.source.getWidth();
/* 179 */     int h = this.source.getHeight();
/* 180 */     Insets insets = getInsets();
/* 181 */     Dimension dim = new Dimension(w + insets.left + insets.right, h + insets.top + insets.bottom);
/* 184 */     setPreferredSize(dim);
/* 186 */     this.originX = x;
/* 187 */     this.originY = y;
/* 189 */     revalidate();
/* 190 */     repaint();
/*     */   }
/*     */   
/*     */   public RenderedImage getSource() {
/* 199 */     return this.source;
/*     */   }
/*     */   
/*     */   public synchronized void paintComponent(Graphics g) {
/* 222 */     Graphics2D g2d = (Graphics2D)g;
/* 225 */     if (this.source == null) {
/* 226 */       g2d.setColor(getBackground());
/* 227 */       g2d.fillRect(0, 0, getWidth(), getHeight());
/*     */       return;
/*     */     } 
/* 232 */     Rectangle clipBounds = g2d.getClipBounds();
/* 233 */     g2d.setColor(getBackground());
/* 234 */     g2d.fillRect(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
/* 240 */     Insets insets = getInsets();
/* 241 */     int tx = insets.left + this.originX;
/* 242 */     int ty = insets.top + this.originY;
/*     */     try {
/* 246 */       g2d.drawRenderedImage(this.source, AffineTransform.getTranslateInstance(tx, ty));
/* 248 */     } catch (OutOfMemoryError e) {}
/*     */   }
/*     */   
/*     */   public void mousePressed(MouseEvent e) {}
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {}
/*     */   
/*     */   public void mouseMoved(MouseEvent e) {}
/*     */   
/*     */   public void mouseDragged(MouseEvent e) {}
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {}
/*     */   
/*     */   public void mouseExited(MouseEvent e) {}
/*     */   
/*     */   public void mouseClicked(MouseEvent e) {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\widget\DisplayJAI.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */