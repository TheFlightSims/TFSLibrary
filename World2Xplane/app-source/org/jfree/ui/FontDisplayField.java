/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class FontDisplayField extends JTextField {
/*     */   private Font displayFont;
/*     */   
/*  64 */   protected static final ResourceBundle localizationResources = ResourceBundle.getBundle("org.jfree.ui.LocalizationBundle");
/*     */   
/*     */   public FontDisplayField(Font font) {
/*  73 */     super("");
/*  74 */     setDisplayFont(font);
/*  75 */     setEnabled(false);
/*     */   }
/*     */   
/*     */   public Font getDisplayFont() {
/*  84 */     return this.displayFont;
/*     */   }
/*     */   
/*     */   public void setDisplayFont(Font font) {
/*  93 */     this.displayFont = font;
/*  94 */     setText(fontToString(this.displayFont));
/*     */   }
/*     */   
/*     */   private String fontToString(Font font) {
/* 105 */     if (font != null)
/* 106 */       return font.getFontName() + ", " + font.getSize(); 
/* 109 */     return localizationResources.getString("No_Font_Selected");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\FontDisplayField.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */