/*     */ package org.jfree.ui.about;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.table.AbstractTableModel;
/*     */ import org.jfree.base.Library;
/*     */ 
/*     */ public class LibraryTableModel extends AbstractTableModel {
/*     */   private List libraries;
/*     */   
/*     */   private String nameColumnLabel;
/*     */   
/*     */   private String versionColumnLabel;
/*     */   
/*     */   private String licenceColumnLabel;
/*     */   
/*     */   private String infoColumnLabel;
/*     */   
/*     */   public LibraryTableModel(List libraries) {
/*  85 */     this.libraries = libraries;
/*  87 */     String baseName = "org.jfree.ui.about.resources.AboutResources";
/*  88 */     ResourceBundle resources = ResourceBundle.getBundle("org.jfree.ui.about.resources.AboutResources");
/*  90 */     this.nameColumnLabel = resources.getString("libraries-table.column.name");
/*  91 */     this.versionColumnLabel = resources.getString("libraries-table.column.version");
/*  92 */     this.licenceColumnLabel = resources.getString("libraries-table.column.licence");
/*  93 */     this.infoColumnLabel = resources.getString("libraries-table.column.info");
/*     */   }
/*     */   
/*     */   public int getRowCount() {
/* 103 */     return this.libraries.size();
/*     */   }
/*     */   
/*     */   public int getColumnCount() {
/* 113 */     return 4;
/*     */   }
/*     */   
/*     */   public String getColumnName(int column) {
/* 125 */     String result = null;
/* 127 */     switch (column) {
/*     */       case 0:
/* 129 */         result = this.nameColumnLabel;
/*     */         break;
/*     */       case 1:
/* 132 */         result = this.versionColumnLabel;
/*     */         break;
/*     */       case 2:
/* 135 */         result = this.licenceColumnLabel;
/*     */         break;
/*     */       case 3:
/* 138 */         result = this.infoColumnLabel;
/*     */         break;
/*     */     } 
/* 143 */     return result;
/*     */   }
/*     */   
/*     */   public Object getValueAt(int row, int column) {
/* 157 */     Object result = null;
/* 158 */     Library library = this.libraries.get(row);
/* 160 */     if (column == 0) {
/* 161 */       result = library.getName();
/* 163 */     } else if (column == 1) {
/* 164 */       result = library.getVersion();
/* 166 */     } else if (column == 2) {
/* 167 */       result = library.getLicenceName();
/* 169 */     } else if (column == 3) {
/* 170 */       result = library.getInfo();
/*     */     } 
/* 172 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\about\LibraryTableModel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */