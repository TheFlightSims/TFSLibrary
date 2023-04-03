/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Insets;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.table.JTableHeader;
/*     */ import javax.swing.table.TableCellRenderer;
/*     */ 
/*     */ public class SortButtonRenderer implements TableCellRenderer {
/*     */   public static final int NONE = 0;
/*     */   
/*     */   public static final int DOWN = 1;
/*     */   
/*     */   public static final int UP = 2;
/*     */   
/*  89 */   private int pressedColumn = -1;
/*     */   
/*     */   private JButton normalButton;
/*     */   
/*     */   private JButton ascendingButton;
/*     */   
/*     */   private JButton descendingButton;
/*     */   
/*     */   private boolean useLabels;
/*     */   
/*     */   private JLabel normalLabel;
/*     */   
/*     */   private JLabel ascendingLabel;
/*     */   
/*     */   private JLabel descendingLabel;
/*     */   
/*     */   public SortButtonRenderer() {
/* 133 */     this.pressedColumn = -1;
/* 134 */     this.useLabels = UIManager.getLookAndFeel().getID().equals("Aqua");
/* 136 */     Border border = UIManager.getBorder("TableHeader.cellBorder");
/* 138 */     if (this.useLabels) {
/* 139 */       this.normalLabel = new JLabel();
/* 140 */       this.normalLabel.setHorizontalAlignment(10);
/* 142 */       this.ascendingLabel = new JLabel();
/* 143 */       this.ascendingLabel.setHorizontalAlignment(10);
/* 144 */       this.ascendingLabel.setHorizontalTextPosition(2);
/* 145 */       this.ascendingLabel.setIcon(new BevelArrowIcon(1, false, false));
/* 147 */       this.descendingLabel = new JLabel();
/* 148 */       this.descendingLabel.setHorizontalAlignment(10);
/* 149 */       this.descendingLabel.setHorizontalTextPosition(2);
/* 150 */       this.descendingLabel.setIcon(new BevelArrowIcon(0, false, false));
/* 152 */       this.normalLabel.setBorder(border);
/* 153 */       this.ascendingLabel.setBorder(border);
/* 154 */       this.descendingLabel.setBorder(border);
/*     */     } else {
/* 157 */       this.normalButton = new JButton();
/* 158 */       this.normalButton.setMargin(new Insets(0, 0, 0, 0));
/* 159 */       this.normalButton.setHorizontalAlignment(10);
/* 161 */       this.ascendingButton = new JButton();
/* 162 */       this.ascendingButton.setMargin(new Insets(0, 0, 0, 0));
/* 163 */       this.ascendingButton.setHorizontalAlignment(10);
/* 164 */       this.ascendingButton.setHorizontalTextPosition(2);
/* 165 */       this.ascendingButton.setIcon(new BevelArrowIcon(1, false, false));
/* 166 */       this.ascendingButton.setPressedIcon(new BevelArrowIcon(1, false, true));
/* 168 */       this.descendingButton = new JButton();
/* 169 */       this.descendingButton.setMargin(new Insets(0, 0, 0, 0));
/* 170 */       this.descendingButton.setHorizontalAlignment(10);
/* 171 */       this.descendingButton.setHorizontalTextPosition(2);
/* 172 */       this.descendingButton.setIcon(new BevelArrowIcon(0, false, false));
/* 173 */       this.descendingButton.setPressedIcon(new BevelArrowIcon(0, false, true));
/* 175 */       this.normalButton.setBorder(border);
/* 176 */       this.ascendingButton.setBorder(border);
/* 177 */       this.descendingButton.setBorder(border);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
/*     */     JComponent component;
/* 200 */     if (table == null)
/* 201 */       throw new NullPointerException("Table must not be null."); 
/* 205 */     SortableTableModel model = (SortableTableModel)table.getModel();
/* 206 */     int cc = table.convertColumnIndexToModel(column);
/* 207 */     boolean isSorting = (model.getSortingColumn() == cc);
/* 208 */     boolean isAscending = model.isAscending();
/* 210 */     JTableHeader header = table.getTableHeader();
/* 211 */     boolean isPressed = (cc == this.pressedColumn);
/* 213 */     if (this.useLabels) {
/* 214 */       JLabel label = getRendererLabel(isSorting, isAscending);
/* 215 */       label.setText((value == null) ? "" : value.toString());
/* 216 */       component = label;
/*     */     } else {
/* 219 */       JButton button = getRendererButton(isSorting, isAscending);
/* 220 */       button.setText((value == null) ? "" : value.toString());
/* 221 */       button.getModel().setPressed(isPressed);
/* 222 */       button.getModel().setArmed(isPressed);
/* 223 */       component = button;
/*     */     } 
/* 226 */     if (header != null) {
/* 227 */       component.setForeground(header.getForeground());
/* 228 */       component.setBackground(header.getBackground());
/* 229 */       component.setFont(header.getFont());
/*     */     } 
/* 231 */     return component;
/*     */   }
/*     */   
/*     */   private JButton getRendererButton(boolean isSorting, boolean isAscending) {
/* 242 */     if (isSorting) {
/* 243 */       if (isAscending)
/* 244 */         return this.ascendingButton; 
/* 247 */       return this.descendingButton;
/*     */     } 
/* 251 */     return this.normalButton;
/*     */   }
/*     */   
/*     */   private JLabel getRendererLabel(boolean isSorting, boolean isAscending) {
/* 263 */     if (isSorting) {
/* 264 */       if (isAscending)
/* 265 */         return this.ascendingLabel; 
/* 268 */       return this.descendingLabel;
/*     */     } 
/* 272 */     return this.normalLabel;
/*     */   }
/*     */   
/*     */   public void setPressedColumn(int column) {
/* 282 */     this.pressedColumn = column;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\SortButtonRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */