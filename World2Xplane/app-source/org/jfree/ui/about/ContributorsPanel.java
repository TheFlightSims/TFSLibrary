/*    */ package org.jfree.ui.about;
/*    */ 
/*    */ import java.awt.BorderLayout;
/*    */ import java.util.List;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.JTable;
/*    */ import javax.swing.table.TableModel;
/*    */ 
/*    */ public class ContributorsPanel extends JPanel {
/*    */   private JTable table;
/*    */   
/*    */   private TableModel model;
/*    */   
/*    */   public ContributorsPanel(List contributors) {
/* 78 */     setLayout(new BorderLayout());
/* 79 */     this.model = new ContributorsTableModel(contributors);
/* 80 */     this.table = new JTable(this.model);
/* 81 */     add(new JScrollPane(this.table));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\about\ContributorsPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */