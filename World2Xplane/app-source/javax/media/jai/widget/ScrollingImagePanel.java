/*     */ package javax.media.jai.widget;
/*     */ 
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Point;
/*     */ import java.awt.ScrollPane;
/*     */ import java.awt.event.AdjustmentEvent;
/*     */ import java.awt.event.AdjustmentListener;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public class ScrollingImagePanel extends ScrollPane implements AdjustmentListener, ComponentListener, MouseListener, MouseMotionListener {
/*     */   protected ImageCanvas ic;
/*     */   
/*     */   protected RenderedImage im;
/*     */   
/*     */   protected int panelWidth;
/*     */   
/*     */   protected int panelHeight;
/*     */   
/*  70 */   protected Vector viewportListeners = new Vector();
/*     */   
/*     */   protected Point moveSource;
/*     */   
/*     */   protected boolean beingDragged;
/*     */   
/*     */   protected Cursor defaultCursor;
/*     */   
/*     */   public void addViewportListener(ViewportListener l) {
/*  94 */     this.viewportListeners.addElement(l);
/*  95 */     l.setViewport(getXOrigin(), getYOrigin(), this.panelWidth, this.panelHeight);
/*     */   }
/*     */   
/*     */   public void removeViewportListener(ViewportListener l) {
/* 101 */     this.viewportListeners.removeElement(l);
/*     */   }
/*     */   
/*     */   private void notifyViewportListeners(int x, int y, int w, int h) {
/* 106 */     int numListeners = this.viewportListeners.size();
/* 107 */     for (int i = 0; i < numListeners; i++) {
/* 108 */       ViewportListener l = this.viewportListeners.elementAt(i);
/* 110 */       l.setViewport(x, y, w, h);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ImageCanvas getImageCanvas() {
/* 121 */     return this.ic;
/*     */   }
/*     */   
/*     */   public int getXOrigin() {
/* 126 */     return this.ic.getXOrigin();
/*     */   }
/*     */   
/*     */   public int getYOrigin() {
/* 131 */     return this.ic.getYOrigin();
/*     */   }
/*     */   
/*     */   public void setOrigin(int x, int y) {
/* 139 */     this.ic.setOrigin(x, y);
/* 140 */     notifyViewportListeners(x, y, this.panelWidth, this.panelHeight);
/*     */   }
/*     */   
/*     */   public synchronized void setCenter(int x, int y) {
/* 150 */     int sx = 0;
/* 151 */     int sy = 0;
/* 154 */     int iw = this.im.getWidth();
/* 155 */     int ih = this.im.getHeight();
/* 156 */     int vw = (getViewportSize()).width;
/* 157 */     int vh = (getViewportSize()).height;
/* 160 */     int fx = getHAdjustable().getBlockIncrement();
/* 161 */     int fy = getVAdjustable().getBlockIncrement();
/* 163 */     if (x < vw - iw / 2) {
/* 164 */       sx = 0;
/* 165 */     } else if (x > iw / 2) {
/* 166 */       sx = iw - vw;
/*     */     } else {
/* 168 */       sx = x + (iw - vw - fx) / 2;
/*     */     } 
/* 171 */     if (y < vh - ih / 2) {
/* 172 */       sy = 0;
/* 173 */     } else if (y > ih / 2) {
/* 174 */       sy = ih - vh;
/*     */     } else {
/* 176 */       sy = y + (ih - vh - fy) / 2;
/*     */     } 
/* 179 */     getHAdjustable().setValue(sx);
/* 180 */     getVAdjustable().setValue(sy);
/* 182 */     notifyViewportListeners(getXOrigin(), getYOrigin(), this.panelWidth, this.panelHeight);
/*     */   }
/*     */   
/*     */   public void set(RenderedImage im) {
/* 187 */     this.im = im;
/* 188 */     this.ic.set(im);
/*     */   }
/*     */   
/*     */   public int getXCenter() {
/* 193 */     return getXOrigin() + this.panelWidth / 2;
/*     */   }
/*     */   
/*     */   public int getYCenter() {
/* 198 */     return getYOrigin() + this.panelHeight / 2;
/*     */   }
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 203 */     return new Dimension(this.panelWidth, this.panelHeight);
/*     */   }
/*     */   
/*     */   public void setBounds(int x, int y, int width, int height) {
/* 212 */     super.setBounds(x, y, width, height);
/* 214 */     int vpw = (getViewportSize()).width;
/* 215 */     int vph = (getViewportSize()).height;
/* 216 */     int imw = this.im.getWidth();
/* 217 */     int imh = this.im.getHeight();
/* 219 */     if (vpw >= imw && vph >= imh) {
/* 220 */       this.ic.setBounds(x, y, width, height);
/*     */     } else {
/* 238 */       this.ic.setBounds(x, y, vpw, vph);
/*     */     } 
/* 241 */     this.panelWidth = width;
/* 242 */     this.panelHeight = height;
/*     */   }
/*     */   
/*     */   public void adjustmentValueChanged(AdjustmentEvent e) {}
/*     */   
/*     */   public void componentResized(ComponentEvent e) {
/* 253 */     notifyViewportListeners(getXOrigin(), getYOrigin(), this.panelWidth, this.panelHeight);
/*     */   }
/*     */   
/*     */   public void componentHidden(ComponentEvent e) {}
/*     */   
/*     */   public void componentMoved(ComponentEvent e) {}
/*     */   
/*     */   public void componentShown(ComponentEvent e) {}
/*     */   
/*     */   public ScrollingImagePanel(RenderedImage im, int width, int height) {
/* 274 */     this.beingDragged = false;
/* 276 */     this.defaultCursor = null;
/*     */     this.im = im;
/*     */     this.panelWidth = width;
/*     */     this.panelHeight = height;
/*     */     this.ic = new ImageCanvas(im);
/*     */     getHAdjustable().addAdjustmentListener(this);
/*     */     getVAdjustable().addAdjustmentListener(this);
/*     */     setSize(width, height);
/*     */     addComponentListener(this);
/*     */     add("Center", this.ic);
/*     */   }
/*     */   
/*     */   private synchronized void startDrag(Point p) {
/* 280 */     setCursor(Cursor.getPredefinedCursor(13));
/* 281 */     this.beingDragged = true;
/* 282 */     this.moveSource = p;
/*     */   }
/*     */   
/*     */   protected synchronized void updateDrag(Point moveTarget) {
/* 287 */     if (this.beingDragged) {
/* 288 */       int dx = this.moveSource.x - moveTarget.x;
/* 289 */       int dy = this.moveSource.y - moveTarget.y;
/* 290 */       this.moveSource = moveTarget;
/* 292 */       int x = getHAdjustable().getValue() + dx;
/* 293 */       int y = getVAdjustable().getValue() + dy;
/* 294 */       setOrigin(x, y);
/*     */     } 
/*     */   }
/*     */   
/*     */   private synchronized void endDrag() {
/* 300 */     setCursor(Cursor.getPredefinedCursor(0));
/* 301 */     this.beingDragged = false;
/*     */   }
/*     */   
/*     */   public void mousePressed(MouseEvent me) {
/* 306 */     startDrag(me.getPoint());
/*     */   }
/*     */   
/*     */   public void mouseDragged(MouseEvent me) {
/* 311 */     updateDrag(me.getPoint());
/*     */   }
/*     */   
/*     */   public void mouseReleased(MouseEvent me) {
/* 316 */     endDrag();
/*     */   }
/*     */   
/*     */   public void mouseExited(MouseEvent me) {
/* 321 */     endDrag();
/*     */   }
/*     */   
/*     */   public void mouseClicked(MouseEvent me) {}
/*     */   
/*     */   public void mouseMoved(MouseEvent me) {}
/*     */   
/*     */   public void mouseEntered(MouseEvent me) {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\widget\ScrollingImagePanel.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */