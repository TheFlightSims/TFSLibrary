/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.Window;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.table.TableColumn;
/*     */ import javax.swing.table.TableModel;
/*     */ import org.jfree.util.Log;
/*     */ import org.jfree.util.LogContext;
/*     */ 
/*     */ public abstract class RefineryUtilities {
/*  96 */   protected static final LogContext logger = Log.createContext(RefineryUtilities.class);
/*     */   
/*     */   public static void centerFrameOnScreen(Window frame) {
/* 104 */     positionFrameOnScreen(frame, 0.5D, 0.5D);
/*     */   }
/*     */   
/*     */   public static void positionFrameOnScreen(Window frame, double horizontalPercent, double verticalPercent) {
/* 121 */     Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
/* 122 */     Dimension f = frame.getSize();
/* 123 */     int w = Math.max(s.width - f.width, 0);
/* 124 */     int h = Math.max(s.height - f.height, 0);
/* 125 */     int x = (int)(horizontalPercent * w);
/* 126 */     int y = (int)(verticalPercent * h);
/* 127 */     frame.setBounds(x, y, f.width, f.height);
/*     */   }
/*     */   
/*     */   public static void positionFrameRandomly(Window frame) {
/* 138 */     positionFrameOnScreen(frame, Math.random(), Math.random());
/*     */   }
/*     */   
/*     */   public static void centerDialogInParent(Dialog dialog) {
/* 147 */     positionDialogRelativeToParent(dialog, 0.5D, 0.5D);
/*     */   }
/*     */   
/*     */   public static void positionDialogRelativeToParent(Dialog dialog, double horizontalPercent, double verticalPercent) {
/* 160 */     Dimension d = dialog.getSize();
/* 161 */     Container parent = dialog.getParent();
/* 162 */     Dimension p = parent.getSize();
/* 164 */     int baseX = parent.getX() - d.width;
/* 165 */     int baseY = parent.getY() - d.height;
/* 166 */     int w = d.width + p.width;
/* 167 */     int h = d.height + p.height;
/* 168 */     int x = baseX + (int)(horizontalPercent * w);
/* 169 */     int y = baseY + (int)(verticalPercent * h);
/* 172 */     Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
/* 173 */     x = Math.min(x, s.width - d.width);
/* 174 */     x = Math.max(x, 0);
/* 175 */     y = Math.min(y, s.height - d.height);
/* 176 */     y = Math.max(y, 0);
/* 178 */     dialog.setBounds(x, y, d.width, d.height);
/*     */   }
/*     */   
/*     */   public static JPanel createTablePanel(TableModel model) {
/* 191 */     JPanel panel = new JPanel(new BorderLayout());
/* 192 */     JTable table = new JTable(model);
/* 193 */     for (int columnIndex = 0; columnIndex < model.getColumnCount(); columnIndex++) {
/* 194 */       TableColumn column = table.getColumnModel().getColumn(columnIndex);
/* 195 */       Class c = model.getColumnClass(columnIndex);
/* 196 */       if (c.equals(Number.class))
/* 197 */         column.setCellRenderer(new NumberCellRenderer()); 
/*     */     } 
/* 200 */     panel.add(new JScrollPane(table));
/* 201 */     return panel;
/*     */   }
/*     */   
/*     */   public static JLabel createJLabel(String text, Font font) {
/* 215 */     JLabel result = new JLabel(text);
/* 216 */     result.setFont(font);
/* 217 */     return result;
/*     */   }
/*     */   
/*     */   public static JLabel createJLabel(String text, Font font, Color color) {
/* 232 */     JLabel result = new JLabel(text);
/* 233 */     result.setFont(font);
/* 234 */     result.setForeground(color);
/* 235 */     return result;
/*     */   }
/*     */   
/*     */   public static JButton createJButton(String label, Font font) {
/* 249 */     JButton result = new JButton(label);
/* 250 */     result.setFont(font);
/* 251 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\RefineryUtilities.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */