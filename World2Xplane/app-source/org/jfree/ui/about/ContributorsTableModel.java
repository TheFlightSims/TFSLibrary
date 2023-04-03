/*     */ package org.jfree.ui.about;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.table.AbstractTableModel;
/*     */ 
/*     */ public class ContributorsTableModel extends AbstractTableModel {
/*     */   private List contributors;
/*     */   
/*     */   private String nameColumnLabel;
/*     */   
/*     */   private String contactColumnLabel;
/*     */   
/*     */   public ContributorsTableModel(List contributors) {
/*  77 */     this.contributors = contributors;
/*  79 */     String baseName = "org.jfree.ui.about.resources.AboutResources";
/*  80 */     ResourceBundle resources = ResourceBundle.getBundle("org.jfree.ui.about.resources.AboutResources");
/*  81 */     this.nameColumnLabel = resources.getString("contributors-table.column.name");
/*  82 */     this.contactColumnLabel = resources.getString("contributors-table.column.contact");
/*     */   }
/*     */   
/*     */   public int getRowCount() {
/*  92 */     return this.contributors.size();
/*     */   }
/*     */   
/*     */   public int getColumnCount() {
/* 102 */     return 2;
/*     */   }
/*     */   
/*     */   public String getColumnName(int column) {
/* 114 */     String result = null;
/* 116 */     switch (column) {
/*     */       case 0:
/* 118 */         result = this.nameColumnLabel;
/*     */         break;
/*     */       case 1:
/* 121 */         result = this.contactColumnLabel;
/*     */         break;
/*     */     } 
/* 126 */     return result;
/*     */   }
/*     */   
/*     */   public Object getValueAt(int row, int column) {
/* 140 */     Object result = null;
/* 141 */     Contributor contributor = this.contributors.get(row);
/* 143 */     if (column == 0) {
/* 144 */       result = contributor.getName();
/* 146 */     } else if (column == 1) {
/* 147 */       result = contributor.getEmail();
/*     */     } 
/* 149 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\about\ContributorsTableModel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */