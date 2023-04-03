/*     */ package org.jfree.ui.tabbedui;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.Rectangle;
/*     */ 
/*     */ public class VerticalLayout implements LayoutManager {
/*     */   private final boolean useSizeFromParent;
/*     */   
/*     */   public VerticalLayout() {
/*  70 */     this(true);
/*     */   }
/*     */   
/*     */   public VerticalLayout(boolean useParent) {
/*  81 */     this.useSizeFromParent = useParent;
/*     */   }
/*     */   
/*     */   public void addLayoutComponent(String name, Component comp) {}
/*     */   
/*     */   public void removeLayoutComponent(Component comp) {}
/*     */   
/*     */   public Dimension preferredLayoutSize(Container parent) {
/* 113 */     synchronized (parent.getTreeLock()) {
/* 114 */       Insets ins = parent.getInsets();
/* 115 */       Component[] comps = parent.getComponents();
/* 116 */       int height = ins.top + ins.bottom;
/* 117 */       int width = ins.left + ins.right;
/* 118 */       for (int i = 0; i < comps.length; i++) {
/* 119 */         if (comps[i].isVisible()) {
/* 122 */           Dimension pref = comps[i].getPreferredSize();
/* 123 */           height += pref.height;
/* 124 */           if (pref.width > width)
/* 125 */             width = pref.width; 
/*     */         } 
/*     */       } 
/* 129 */       return new Dimension(width + ins.left + ins.right, height + ins.top + ins.bottom);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Dimension minimumLayoutSize(Container parent) {
/* 143 */     synchronized (parent.getTreeLock()) {
/* 144 */       Insets ins = parent.getInsets();
/* 145 */       Component[] comps = parent.getComponents();
/* 146 */       int height = ins.top + ins.bottom;
/* 147 */       int width = ins.left + ins.right;
/* 148 */       for (int i = 0; i < comps.length; i++) {
/* 149 */         if (comps[i].isVisible()) {
/* 152 */           Dimension min = comps[i].getMinimumSize();
/* 153 */           height += min.height;
/* 154 */           if (min.width > width)
/* 155 */             width = min.width; 
/*     */         } 
/*     */       } 
/* 158 */       return new Dimension(width + ins.left + ins.right, height + ins.top + ins.bottom);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isUseSizeFromParent() {
/* 170 */     return this.useSizeFromParent;
/*     */   }
/*     */   
/*     */   public void layoutContainer(Container parent) {
/* 179 */     synchronized (parent.getTreeLock()) {
/*     */       int width;
/* 180 */       Insets ins = parent.getInsets();
/* 181 */       int insHorizontal = ins.left + ins.right;
/* 184 */       if (isUseSizeFromParent()) {
/* 185 */         Rectangle bounds = parent.getBounds();
/* 186 */         width = bounds.width - insHorizontal;
/*     */       } else {
/* 189 */         width = (preferredLayoutSize(parent)).width - insHorizontal;
/*     */       } 
/* 191 */       Component[] comps = parent.getComponents();
/* 193 */       int y = ins.top;
/* 194 */       for (int i = 0; i < comps.length; i++) {
/* 195 */         Component c = comps[i];
/* 196 */         if (c.isVisible()) {
/* 199 */           Dimension dim = c.getPreferredSize();
/* 200 */           c.setBounds(ins.left, y, width, dim.height);
/* 201 */           y += dim.height;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\tabbedui\VerticalLayout.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */