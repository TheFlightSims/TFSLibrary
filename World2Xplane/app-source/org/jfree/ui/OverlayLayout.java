/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.Rectangle;
/*     */ 
/*     */ public final class OverlayLayout implements LayoutManager {
/*     */   private boolean ignoreInvisible;
/*     */   
/*     */   public OverlayLayout(boolean ignoreInvisible) {
/*  74 */     this.ignoreInvisible = ignoreInvisible;
/*     */   }
/*     */   
/*     */   public OverlayLayout() {}
/*     */   
/*     */   public void addLayoutComponent(String name, Component comp) {}
/*     */   
/*     */   public void removeLayoutComponent(Component comp) {}
/*     */   
/*     */   public void layoutContainer(Container parent) {
/* 110 */     synchronized (parent.getTreeLock()) {
/* 111 */       Insets ins = parent.getInsets();
/* 113 */       Rectangle bounds = parent.getBounds();
/* 114 */       int width = bounds.width - ins.left - ins.right;
/* 115 */       int height = bounds.height - ins.top - ins.bottom;
/* 117 */       Component[] comps = parent.getComponents();
/* 119 */       for (int i = 0; i < comps.length; i++) {
/* 120 */         Component c = comps[i];
/* 121 */         if (comps[i].isVisible() || !this.ignoreInvisible)
/* 124 */           c.setBounds(ins.left, ins.top, width, height); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Dimension minimumLayoutSize(Container parent) {
/* 138 */     synchronized (parent.getTreeLock()) {
/* 139 */       Insets ins = parent.getInsets();
/* 140 */       Component[] comps = parent.getComponents();
/* 141 */       int height = 0;
/* 142 */       int width = 0;
/* 143 */       for (int i = 0; i < comps.length; i++) {
/* 144 */         if (comps[i].isVisible() || !this.ignoreInvisible) {
/* 148 */           Dimension pref = comps[i].getMinimumSize();
/* 149 */           if (pref.height > height)
/* 150 */             height = pref.height; 
/* 152 */           if (pref.width > width)
/* 153 */             width = pref.width; 
/*     */         } 
/*     */       } 
/* 156 */       return new Dimension(width + ins.left + ins.right, height + ins.top + ins.bottom);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Dimension preferredLayoutSize(Container parent) {
/* 170 */     synchronized (parent.getTreeLock()) {
/* 171 */       Insets ins = parent.getInsets();
/* 172 */       Component[] comps = parent.getComponents();
/* 173 */       int height = 0;
/* 174 */       int width = 0;
/* 175 */       for (int i = 0; i < comps.length; i++) {
/* 176 */         if (comps[i].isVisible() || !this.ignoreInvisible) {
/* 180 */           Dimension pref = comps[i].getPreferredSize();
/* 181 */           if (pref.height > height)
/* 182 */             height = pref.height; 
/* 184 */           if (pref.width > width)
/* 185 */             width = pref.width; 
/*     */         } 
/*     */       } 
/* 188 */       return new Dimension(width + ins.left + ins.right, height + ins.top + ins.bottom);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\OverlayLayout.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */