/*     */ package org.jfree.layout;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class CenterLayout implements LayoutManager, Serializable {
/*     */   private static final long serialVersionUID = 469319532333015042L;
/*     */   
/*     */   public Dimension preferredLayoutSize(Container parent) {
/*  79 */     synchronized (parent.getTreeLock()) {
/*  80 */       Insets insets = parent.getInsets();
/*  81 */       if (parent.getComponentCount() > 0) {
/*  82 */         Component component = parent.getComponent(0);
/*  83 */         Dimension d = component.getPreferredSize();
/*  84 */         return new Dimension((int)d.getWidth() + insets.left + insets.right, (int)d.getHeight() + insets.top + insets.bottom);
/*     */       } 
/*  90 */       return new Dimension(insets.left + insets.right, insets.top + insets.bottom);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Dimension minimumLayoutSize(Container parent) {
/* 107 */     synchronized (parent.getTreeLock()) {
/* 108 */       Insets insets = parent.getInsets();
/* 109 */       if (parent.getComponentCount() > 0) {
/* 110 */         Component component = parent.getComponent(0);
/* 111 */         Dimension d = component.getMinimumSize();
/* 112 */         return new Dimension(d.width + insets.left + insets.right, d.height + insets.top + insets.bottom);
/*     */       } 
/* 116 */       return new Dimension(insets.left + insets.right, insets.top + insets.bottom);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void layoutContainer(Container parent) {
/* 130 */     synchronized (parent.getTreeLock()) {
/* 131 */       if (parent.getComponentCount() > 0) {
/* 132 */         Insets insets = parent.getInsets();
/* 133 */         Dimension parentSize = parent.getSize();
/* 134 */         Component component = parent.getComponent(0);
/* 135 */         Dimension componentSize = component.getPreferredSize();
/* 136 */         int xx = insets.left + Math.max((parentSize.width - insets.left - insets.right - componentSize.width) / 2, 0);
/* 140 */         int yy = insets.top + Math.max((parentSize.height - insets.top - insets.bottom - componentSize.height) / 2, 0);
/* 143 */         component.setBounds(xx, yy, componentSize.width, componentSize.height);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addLayoutComponent(Component comp) {}
/*     */   
/*     */   public void removeLayoutComponent(Component comp) {}
/*     */   
/*     */   public void addLayoutComponent(String name, Component comp) {}
/*     */   
/*     */   public void removeLayoutComponent(String name, Component comp) {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\layout\CenterLayout.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */