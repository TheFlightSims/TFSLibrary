/*    */ package org.jfree.ui.about;
/*    */ 
/*    */ import java.awt.BorderLayout;
/*    */ import java.util.List;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.JTable;
/*    */ import javax.swing.table.TableModel;
/*    */ 
/*    */ public class LibraryPanel extends JPanel {
/*    */   private JTable table;
/*    */   
/*    */   private TableModel model;
/*    */   
/*    */   public LibraryPanel(List libraries) {
/* 76 */     setLayout(new BorderLayout());
/* 77 */     this.model = new LibraryTableModel(libraries);
/* 78 */     this.table = new JTable(this.model);
/* 79 */     add(new JScrollPane(this.table));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\about\LibraryPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */