/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Font;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.GridLayout;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.ListModel;
/*     */ 
/*     */ public class FontChooserPanel extends JPanel {
/*  69 */   public static final String[] SIZES = new String[] { 
/*  69 */       "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", 
/*  69 */       "28", "36", "48", "72" };
/*     */   
/*     */   private JList fontlist;
/*     */   
/*     */   private JList sizelist;
/*     */   
/*     */   private JCheckBox bold;
/*     */   
/*     */   private JCheckBox italic;
/*     */   
/*  85 */   protected static ResourceBundle localizationResources = ResourceBundle.getBundle("org.jfree.ui.LocalizationBundle");
/*     */   
/*     */   public FontChooserPanel(Font font) {
/*  95 */     GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
/*  96 */     String[] fonts = g.getAvailableFontFamilyNames();
/*  98 */     setLayout(new BorderLayout());
/*  99 */     JPanel right = new JPanel(new BorderLayout());
/* 101 */     JPanel fontPanel = new JPanel(new BorderLayout());
/* 102 */     fontPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), localizationResources.getString("Font")));
/* 105 */     this.fontlist = new JList(fonts);
/* 106 */     JScrollPane fontpane = new JScrollPane(this.fontlist);
/* 107 */     fontpane.setBorder(BorderFactory.createEtchedBorder());
/* 108 */     fontPanel.add(fontpane);
/* 109 */     add(fontPanel);
/* 111 */     JPanel sizePanel = new JPanel(new BorderLayout());
/* 112 */     sizePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), localizationResources.getString("Size")));
/* 115 */     this.sizelist = new JList(SIZES);
/* 116 */     JScrollPane sizepane = new JScrollPane(this.sizelist);
/* 117 */     sizepane.setBorder(BorderFactory.createEtchedBorder());
/* 118 */     sizePanel.add(sizepane);
/* 120 */     JPanel attributes = new JPanel(new GridLayout(1, 2));
/* 121 */     this.bold = new JCheckBox(localizationResources.getString("Bold"));
/* 122 */     this.italic = new JCheckBox(localizationResources.getString("Italic"));
/* 123 */     attributes.add(this.bold);
/* 124 */     attributes.add(this.italic);
/* 125 */     attributes.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), localizationResources.getString("Attributes")));
/* 128 */     right.add(sizePanel, "Center");
/* 129 */     right.add(attributes, "South");
/* 131 */     add(right, "East");
/* 133 */     setSelectedFont(font);
/*     */   }
/*     */   
/*     */   public Font getSelectedFont() {
/* 142 */     return new Font(getSelectedName(), getSelectedStyle(), getSelectedSize());
/*     */   }
/*     */   
/*     */   public String getSelectedName() {
/* 151 */     return this.fontlist.getSelectedValue();
/*     */   }
/*     */   
/*     */   public int getSelectedStyle() {
/* 160 */     if (this.bold.isSelected() && this.italic.isSelected())
/* 161 */       return 3; 
/* 163 */     if (this.bold.isSelected())
/* 164 */       return 1; 
/* 166 */     if (this.italic.isSelected())
/* 167 */       return 2; 
/* 170 */     return 0;
/*     */   }
/*     */   
/*     */   public int getSelectedSize() {
/* 180 */     String selected = this.sizelist.getSelectedValue();
/* 181 */     if (selected != null)
/* 182 */       return Integer.parseInt(selected); 
/* 185 */     return 10;
/*     */   }
/*     */   
/*     */   public void setSelectedFont(Font font) {
/* 196 */     if (font == null)
/* 197 */       throw new NullPointerException(); 
/* 199 */     this.bold.setSelected(font.isBold());
/* 200 */     this.italic.setSelected(font.isItalic());
/* 202 */     String fontName = font.getName();
/* 203 */     ListModel model = this.fontlist.getModel();
/* 204 */     this.fontlist.clearSelection();
/* 205 */     for (int i = 0; i < model.getSize(); i++) {
/* 206 */       if (fontName.equals(model.getElementAt(i))) {
/* 207 */         this.fontlist.setSelectedIndex(i);
/*     */         break;
/*     */       } 
/*     */     } 
/* 212 */     String fontSize = String.valueOf(font.getSize());
/* 213 */     model = this.sizelist.getModel();
/* 214 */     this.sizelist.clearSelection();
/* 215 */     for (int j = 0; j < model.getSize(); j++) {
/* 216 */       if (fontSize.equals(model.getElementAt(j))) {
/* 217 */         this.sizelist.setSelectedIndex(j);
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\FontChooserPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */