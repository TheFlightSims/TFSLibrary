/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import javax.swing.table.JTableHeader;
/*     */ 
/*     */ public class SortableTableHeaderListener implements MouseListener, MouseMotionListener {
/*     */   private SortableTableModel model;
/*     */   
/*     */   private SortButtonRenderer renderer;
/*     */   
/*     */   private int sortColumnIndex;
/*     */   
/*     */   public SortableTableHeaderListener(SortableTableModel model, SortButtonRenderer renderer) {
/*  77 */     this.model = model;
/*  78 */     this.renderer = renderer;
/*     */   }
/*     */   
/*     */   public void setTableModel(SortableTableModel model) {
/*  87 */     this.model = model;
/*     */   }
/*     */   
/*     */   public void mousePressed(MouseEvent e) {
/*  98 */     JTableHeader header = (JTableHeader)e.getComponent();
/* 100 */     if (header.getResizingColumn() == null && 
/* 101 */       header.getDraggedDistance() < 1) {
/* 102 */       int columnIndex = header.columnAtPoint(e.getPoint());
/* 103 */       int modelColumnIndex = header.getTable().convertColumnIndexToModel(columnIndex);
/* 105 */       if (this.model.isSortable(modelColumnIndex)) {
/* 106 */         this.sortColumnIndex = header.getTable().convertColumnIndexToModel(columnIndex);
/* 107 */         this.renderer.setPressedColumn(this.sortColumnIndex);
/* 108 */         header.repaint();
/* 109 */         if (header.getTable().isEditing())
/* 110 */           header.getTable().getCellEditor().stopCellEditing(); 
/*     */       } else {
/* 114 */         this.sortColumnIndex = -1;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void mouseDragged(MouseEvent e) {
/* 128 */     JTableHeader header = (JTableHeader)e.getComponent();
/* 130 */     if (header.getDraggedDistance() > 0 || header.getResizingColumn() != null) {
/* 131 */       this.renderer.setPressedColumn(-1);
/* 132 */       this.sortColumnIndex = -1;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {}
/*     */   
/*     */   public void mouseClicked(MouseEvent e) {}
/*     */   
/*     */   public void mouseMoved(MouseEvent e) {}
/*     */   
/*     */   public void mouseExited(MouseEvent e) {}
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {
/* 179 */     JTableHeader header = (JTableHeader)e.getComponent();
/* 181 */     if (header.getResizingColumn() == null && 
/* 182 */       this.sortColumnIndex != -1) {
/* 183 */       SortableTableModel model = (SortableTableModel)header.getTable().getModel();
/* 184 */       boolean ascending = !model.isAscending();
/* 185 */       model.setAscending(ascending);
/* 186 */       model.sortByColumn(this.sortColumnIndex, ascending);
/* 188 */       this.renderer.setPressedColumn(-1);
/* 189 */       header.repaint();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\SortableTableHeaderListener.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */