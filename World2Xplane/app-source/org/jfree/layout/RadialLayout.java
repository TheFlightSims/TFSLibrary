/*     */ package org.jfree.layout;
/*     */ 
/*     */ import java.awt.Checkbox;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.Panel;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class RadialLayout implements LayoutManager, Serializable {
/*     */   private static final long serialVersionUID = -7582156799248315534L;
/*     */   
/*  74 */   private int minWidth = 0;
/*     */   
/*  77 */   private int minHeight = 0;
/*     */   
/*  80 */   private int maxCompWidth = 0;
/*     */   
/*  83 */   private int maxCompHeight = 0;
/*     */   
/*  86 */   private int preferredWidth = 0;
/*     */   
/*  89 */   private int preferredHeight = 0;
/*     */   
/*     */   private boolean sizeUnknown = true;
/*     */   
/*     */   public void addLayoutComponent(Component comp) {}
/*     */   
/*     */   public void removeLayoutComponent(Component comp) {}
/*     */   
/*     */   public void addLayoutComponent(String name, Component comp) {}
/*     */   
/*     */   public void removeLayoutComponent(String name, Component comp) {}
/*     */   
/*     */   private void setSizes(Container parent) {
/* 147 */     int nComps = parent.getComponentCount();
/* 149 */     this.preferredWidth = 0;
/* 150 */     this.preferredHeight = 0;
/* 151 */     this.minWidth = 0;
/* 152 */     this.minHeight = 0;
/* 153 */     for (int i = 0; i < nComps; i++) {
/* 154 */       Component c = parent.getComponent(i);
/* 155 */       if (c.isVisible()) {
/* 156 */         Dimension d = c.getPreferredSize();
/* 157 */         if (this.maxCompWidth < d.width)
/* 158 */           this.maxCompWidth = d.width; 
/* 160 */         if (this.maxCompHeight < d.height)
/* 161 */           this.maxCompHeight = d.height; 
/* 163 */         this.preferredWidth += d.width;
/* 164 */         this.preferredHeight += d.height;
/*     */       } 
/*     */     } 
/* 167 */     this.preferredWidth /= 2;
/* 168 */     this.preferredHeight /= 2;
/* 169 */     this.minWidth = this.preferredWidth;
/* 170 */     this.minHeight = this.preferredHeight;
/*     */   }
/*     */   
/*     */   public Dimension preferredLayoutSize(Container parent) {
/* 182 */     Dimension dim = new Dimension(0, 0);
/* 183 */     setSizes(parent);
/* 186 */     Insets insets = parent.getInsets();
/* 187 */     dim.width = this.preferredWidth + insets.left + insets.right;
/* 188 */     dim.height = this.preferredHeight + insets.top + insets.bottom;
/* 190 */     this.sizeUnknown = false;
/* 191 */     return dim;
/*     */   }
/*     */   
/*     */   public Dimension minimumLayoutSize(Container parent) {
/* 203 */     Dimension dim = new Dimension(0, 0);
/* 206 */     Insets insets = parent.getInsets();
/* 207 */     dim.width = this.minWidth + insets.left + insets.right;
/* 208 */     dim.height = this.minHeight + insets.top + insets.bottom;
/* 210 */     this.sizeUnknown = false;
/* 211 */     return dim;
/*     */   }
/*     */   
/*     */   public void layoutContainer(Container parent) {
/* 224 */     Insets insets = parent.getInsets();
/* 225 */     int maxWidth = (parent.getSize()).width - insets.left + insets.right;
/* 227 */     int maxHeight = (parent.getSize()).height - insets.top + insets.bottom;
/* 229 */     int nComps = parent.getComponentCount();
/* 230 */     int x = 0;
/* 231 */     int y = 0;
/* 235 */     if (this.sizeUnknown)
/* 236 */       setSizes(parent); 
/* 239 */     if (nComps < 2) {
/* 240 */       Component c = parent.getComponent(0);
/* 241 */       if (c.isVisible()) {
/* 242 */         Dimension d = c.getPreferredSize();
/* 243 */         c.setBounds(x, y, d.width, d.height);
/*     */       } 
/*     */     } else {
/* 247 */       double radialCurrent = Math.toRadians(90.0D);
/* 248 */       double radialIncrement = 6.283185307179586D / nComps;
/* 249 */       int midX = maxWidth / 2;
/* 250 */       int midY = maxHeight / 2;
/* 251 */       int a = midX - this.maxCompWidth;
/* 252 */       int b = midY - this.maxCompHeight;
/* 253 */       for (int i = 0; i < nComps; i++) {
/* 254 */         Component c = parent.getComponent(i);
/* 255 */         if (c.isVisible()) {
/* 256 */           Dimension d = c.getPreferredSize();
/* 257 */           x = (int)(midX - a * Math.cos(radialCurrent) - d.getWidth() / 2.0D + insets.left);
/* 261 */           y = (int)(midY - b * Math.sin(radialCurrent) - d.getHeight() / 2.0D + insets.top);
/* 267 */           c.setBounds(x, y, d.width, d.height);
/*     */         } 
/* 269 */         radialCurrent += radialIncrement;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 280 */     return getClass().getName();
/*     */   }
/*     */   
/*     */   public static void main(String[] args) throws Exception {
/* 291 */     Frame frame = new Frame();
/* 292 */     Panel panel = new Panel();
/* 293 */     panel.setLayout(new RadialLayout());
/* 295 */     panel.add(new Checkbox("One"));
/* 296 */     panel.add(new Checkbox("Two"));
/* 297 */     panel.add(new Checkbox("Three"));
/* 298 */     panel.add(new Checkbox("Four"));
/* 299 */     panel.add(new Checkbox("Five"));
/* 300 */     panel.add(new Checkbox("One"));
/* 301 */     panel.add(new Checkbox("Two"));
/* 302 */     panel.add(new Checkbox("Three"));
/* 303 */     panel.add(new Checkbox("Four"));
/* 304 */     panel.add(new Checkbox("Five"));
/* 306 */     frame.add(panel);
/* 307 */     frame.setSize(300, 500);
/* 308 */     frame.setVisible(true);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\layout\RadialLayout.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */