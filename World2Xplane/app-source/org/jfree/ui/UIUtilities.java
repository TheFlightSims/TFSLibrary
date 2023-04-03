/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import javax.swing.UIDefaults;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import javax.swing.border.MatteBorder;
/*     */ import javax.swing.plaf.BorderUIResource;
/*     */ 
/*     */ public class UIUtilities {
/*     */   public static void setupUI() {
/*     */     try {
/*  71 */       String classname = UIManager.getSystemLookAndFeelClassName();
/*  72 */       UIManager.setLookAndFeel(classname);
/*  74 */     } catch (Exception e) {
/*  75 */       e.printStackTrace();
/*     */     } 
/*  78 */     UIDefaults defaults = UIManager.getDefaults();
/*  80 */     defaults.put("PopupMenu.border", new BorderUIResource.EtchedBorderUIResource(0, defaults.getColor("controlShadow"), defaults.getColor("controlLtHighlight")));
/*  88 */     MatteBorder matteborder = new MatteBorder(1, 1, 1, 1, Color.black);
/*  89 */     EmptyBorder emptyborder = new MatteBorder(2, 2, 2, 2, defaults.getColor("control"));
/*  90 */     BorderUIResource.CompoundBorderUIResource compBorder = new BorderUIResource.CompoundBorderUIResource(emptyborder, matteborder);
/*  92 */     BorderUIResource.EmptyBorderUIResource emptyBorderUI = new BorderUIResource.EmptyBorderUIResource(0, 0, 0, 0);
/*  94 */     defaults.put("SplitPane.border", emptyBorderUI);
/*  95 */     defaults.put("Table.scrollPaneBorder", emptyBorderUI);
/*  96 */     defaults.put("ComboBox.border", compBorder);
/*  97 */     defaults.put("TextField.border", compBorder);
/*  98 */     defaults.put("TextArea.border", compBorder);
/*  99 */     defaults.put("CheckBox.border", compBorder);
/* 100 */     defaults.put("ScrollPane.border", emptyBorderUI);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\UIUtilities.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */