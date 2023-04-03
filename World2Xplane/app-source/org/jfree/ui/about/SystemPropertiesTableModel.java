/*     */ package org.jfree.ui.about;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ import java.util.ResourceBundle;
/*     */ import org.jfree.ui.SortableTableModel;
/*     */ 
/*     */ public class SystemPropertiesTableModel extends SortableTableModel {
/*     */   private List properties;
/*     */   
/*     */   private String nameColumnLabel;
/*     */   
/*     */   private String valueColumnLabel;
/*     */   
/*     */   protected static class SystemProperty {
/*     */     private String name;
/*     */     
/*     */     private String value;
/*     */     
/*     */     public SystemProperty(String name, String value) {
/*  83 */       this.name = name;
/*  84 */       this.value = value;
/*     */     }
/*     */     
/*     */     public String getName() {
/*  93 */       return this.name;
/*     */     }
/*     */     
/*     */     public String getValue() {
/* 102 */       return this.value;
/*     */     }
/*     */   }
/*     */   
/*     */   protected static class SystemPropertyComparator implements Comparator {
/*     */     private boolean ascending;
/*     */     
/*     */     public SystemPropertyComparator(boolean ascending) {
/* 122 */       this.ascending = ascending;
/*     */     }
/*     */     
/*     */     public int compare(Object o1, Object o2) {
/* 135 */       if (o1 instanceof SystemPropertiesTableModel.SystemProperty && o2 instanceof SystemPropertiesTableModel.SystemProperty) {
/* 136 */         SystemPropertiesTableModel.SystemProperty sp1 = (SystemPropertiesTableModel.SystemProperty)o1;
/* 137 */         SystemPropertiesTableModel.SystemProperty sp2 = (SystemPropertiesTableModel.SystemProperty)o2;
/* 138 */         if (this.ascending)
/* 139 */           return sp1.getName().compareTo(sp2.getName()); 
/* 142 */         return sp2.getName().compareTo(sp1.getName());
/*     */       } 
/* 146 */       return 0;
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 160 */       if (this == o)
/* 161 */         return true; 
/* 163 */       if (!(o instanceof SystemPropertyComparator))
/* 164 */         return false; 
/* 167 */       SystemPropertyComparator systemPropertyComparator = (SystemPropertyComparator)o;
/* 169 */       if (this.ascending != systemPropertyComparator.ascending)
/* 170 */         return false; 
/* 173 */       return true;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 182 */       return this.ascending ? 1 : 0;
/*     */     }
/*     */   }
/*     */   
/*     */   public SystemPropertiesTableModel() {
/* 200 */     this.properties = new ArrayList();
/*     */     try {
/* 202 */       Properties p = System.getProperties();
/* 203 */       Iterator iterator = p.keySet().iterator();
/* 204 */       while (iterator.hasNext()) {
/* 205 */         String name = iterator.next();
/* 206 */         String value = System.getProperty(name);
/* 207 */         SystemProperty sp = new SystemProperty(name, value);
/* 208 */         this.properties.add(sp);
/*     */       } 
/* 211 */     } catch (SecurityException se) {}
/* 215 */     Collections.sort(this.properties, new SystemPropertyComparator(true));
/* 217 */     String baseName = "org.jfree.ui.about.resources.AboutResources";
/* 218 */     ResourceBundle resources = ResourceBundle.getBundle("org.jfree.ui.about.resources.AboutResources");
/* 220 */     this.nameColumnLabel = resources.getString("system-properties-table.column.name");
/* 221 */     this.valueColumnLabel = resources.getString("system-properties-table.column.value");
/*     */   }
/*     */   
/*     */   public boolean isSortable(int column) {
/* 235 */     if (column == 0)
/* 236 */       return true; 
/* 239 */     return false;
/*     */   }
/*     */   
/*     */   public int getRowCount() {
/* 250 */     return this.properties.size();
/*     */   }
/*     */   
/*     */   public int getColumnCount() {
/* 260 */     return 2;
/*     */   }
/*     */   
/*     */   public String getColumnName(int column) {
/* 272 */     if (column == 0)
/* 273 */       return this.nameColumnLabel; 
/* 276 */     return this.valueColumnLabel;
/*     */   }
/*     */   
/*     */   public Object getValueAt(int row, int column) {
/* 292 */     SystemProperty sp = this.properties.get(row);
/* 293 */     if (column == 0)
/* 294 */       return sp.getName(); 
/* 297 */     if (column == 1)
/* 298 */       return sp.getValue(); 
/* 301 */     return null;
/*     */   }
/*     */   
/*     */   public void sortByColumn(int column, boolean ascending) {
/* 316 */     if (isSortable(column)) {
/* 317 */       super.sortByColumn(column, ascending);
/* 318 */       Collections.sort(this.properties, new SystemPropertyComparator(ascending));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\about\SystemPropertiesTableModel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */