/*     */ package org.jfree.ui;
/*     */ 
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.table.JTableHeader;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ 
/*     */ public class SortableTable extends JTable {
/*     */   private SortableTableHeaderListener headerListener;
/*     */   
/*     */   public SortableTable(SortableTableModel model) {
/*  67 */     super(model);
/*  69 */     SortButtonRenderer renderer = new SortButtonRenderer();
/*  70 */     TableColumnModel cm = getColumnModel();
/*  71 */     for (int i = 0; i < cm.getColumnCount(); i++)
/*  72 */       cm.getColumn(i).setHeaderRenderer(renderer); 
/*  75 */     JTableHeader header = getTableHeader();
/*  76 */     this.headerListener = new SortableTableHeaderListener(model, renderer);
/*  77 */     header.addMouseListener(this.headerListener);
/*  78 */     header.addMouseMotionListener(this.headerListener);
/*  80 */     model.sortByColumn(0, true);
/*     */   }
/*     */   
/*     */   public void setSortableModel(SortableTableModel model) {
/*  93 */     setModel(model);
/*  94 */     this.headerListener.setTableModel(model);
/*  95 */     SortButtonRenderer renderer = new SortButtonRenderer();
/*  96 */     TableColumnModel cm = getColumnModel();
/*  97 */     for (int i = 0; i < cm.getColumnCount(); i++)
/*  98 */       cm.getColumn(i).setHeaderRenderer(renderer); 
/* 100 */     model.sortByColumn(0, true);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\SortableTable.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */