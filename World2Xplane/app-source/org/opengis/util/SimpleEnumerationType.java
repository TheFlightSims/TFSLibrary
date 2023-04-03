/*     */ package org.opengis.util;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.net.URL;
/*     */ import java.util.Collection;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ 
/*     */ @Deprecated
/*     */ public abstract class SimpleEnumerationType<E extends SimpleEnumerationType<E>> extends CodeList<E> {
/*     */   protected static Icon loadIconResource(Class a_class, String name) {
/*  54 */     URL iconURL = a_class.getResource(name);
/*  55 */     if (iconURL == null)
/*  56 */       return getNullIcon(); 
/*  58 */     return new ImageIcon(iconURL);
/*     */   }
/*     */   
/*     */   public static Icon getNullIcon() {
/*  68 */     return NULL_ICON;
/*     */   }
/*     */   
/*  76 */   private static final Icon NULL_ICON = new NullIcon();
/*     */   
/*     */   private String description;
/*     */   
/*     */   private final Icon icon;
/*     */   
/*     */   protected SimpleEnumerationType(Collection<E> values, String name, String description) {
/* 102 */     super(name, values);
/* 103 */     this.description = description;
/* 104 */     this.icon = getNullIcon();
/*     */   }
/*     */   
/*     */   protected SimpleEnumerationType(Collection<E> values, String name, String description, Icon icon) {
/* 117 */     super(name, values);
/* 118 */     this.description = description;
/* 119 */     this.icon = icon;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 133 */     return name();
/*     */   }
/*     */   
/*     */   public String getDescription() {
/* 146 */     return this.description;
/*     */   }
/*     */   
/*     */   public Icon getIcon() {
/* 155 */     return this.icon;
/*     */   }
/*     */   
/*     */   private static class NullIcon implements Icon {
/*     */     private NullIcon() {}
/*     */     
/*     */     public int getIconHeight() {
/* 170 */       return 16;
/*     */     }
/*     */     
/*     */     public int getIconWidth() {
/* 173 */       return 64;
/*     */     }
/*     */     
/*     */     public void paintIcon(Component c, Graphics g, int x, int y) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengi\\util\SimpleEnumerationType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */