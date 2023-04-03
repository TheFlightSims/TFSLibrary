/*     */ package org.geotools.referencing.factory.epsg;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import javax.sql.DataSource;
/*     */ import org.geotools.factory.Hints;
/*     */ 
/*     */ public class FactoryUsingAnsiSQL extends FactoryUsingSQL {
/*  50 */   private static final String[] ANSI = new String[] { 
/*  50 */       "[Alias]", "epsg_alias", "[Area]", "epsg_area", "[Coordinate Axis]", "epsg_coordinateaxis", "[Coordinate Axis Name]", "epsg_coordinateaxisname", "[Coordinate_Operation]", "epsg_coordoperation", 
/*  50 */       "[Coordinate_Operation Method]", "epsg_coordoperationmethod", "[Coordinate_Operation Parameter]", "epsg_coordoperationparam", "[Coordinate_Operation Parameter Usage]", "epsg_coordoperationparamusage", "[Coordinate_Operation Parameter Value]", "epsg_coordoperationparamvalue", "[Coordinate_Operation Path]", "epsg_coordoperationpath", 
/*  50 */       "[Coordinate Reference System]", "epsg_coordinatereferencesystem", "[Coordinate System]", "epsg_coordinatesystem", "[Datum]", "epsg_datum", "[Ellipsoid]", "epsg_ellipsoid", "[Naming System]", "epsg_namingsystem", 
/*  50 */       "[Prime Meridian]", "epsg_primemeridian", "[Supersession]", "epsg_supersession", "[Unit of Measure]", "epsg_unitofmeasure", "[Version History]", "epsg_versionhistory", "[ORDER]", "coord_axis_order" };
/*     */   
/* 104 */   protected final Map map = new LinkedHashMap<Object, Object>();
/*     */   
/* 110 */   private String prefix = "epsg_";
/*     */   
/*     */   public FactoryUsingAnsiSQL(Hints userHints, Connection connection) {
/* 123 */     super(userHints, connection);
/* 124 */     for (int i = 0; i < ANSI.length; i++)
/* 125 */       this.map.put(ANSI[i], ANSI[++i]); 
/*     */   }
/*     */   
/*     */   public FactoryUsingAnsiSQL(Hints userHints, DataSource dataSource) {
/* 140 */     super(userHints, dataSource);
/* 141 */     for (int i = 0; i < ANSI.length; i++)
/* 142 */       this.map.put(ANSI[i], ANSI[++i]); 
/*     */   }
/*     */   
/*     */   protected void setSchema(String schema) {
/* 157 */     schema = schema.trim();
/* 158 */     int length = schema.length();
/* 159 */     if (length == 0)
/* 160 */       throw new IllegalArgumentException(schema); 
/* 162 */     char separator = schema.charAt(length - 1);
/* 163 */     if (separator != '.' && separator != '_') {
/* 164 */       schema = schema + '.';
/* 165 */     } else if (length == 1) {
/* 166 */       throw new IllegalArgumentException(schema);
/*     */     } 
/* 168 */     for (Iterator<Map.Entry> it = this.map.entrySet().iterator(); it.hasNext(); ) {
/* 169 */       Map.Entry entry = it.next();
/* 170 */       String tableName = (String)entry.getValue();
/* 176 */       if (tableName.startsWith(this.prefix))
/* 177 */         entry.setValue(schema + tableName.substring(this.prefix.length())); 
/*     */     } 
/* 180 */     this.prefix = schema;
/*     */   }
/*     */   
/*     */   protected String adaptSQL(String statement) {
/* 193 */     StringBuilder modified = new StringBuilder(statement);
/* 194 */     for (Iterator<Map.Entry> it = this.map.entrySet().iterator(); it.hasNext(); ) {
/* 195 */       Map.Entry entry = it.next();
/* 196 */       String oldName = (String)entry.getKey();
/* 197 */       String newName = (String)entry.getValue();
/* 201 */       int start = 0;
/* 202 */       while ((start = modified.indexOf(oldName, start)) >= 0) {
/* 203 */         modified.replace(start, start + oldName.length(), newName);
/* 204 */         start += newName.length();
/*     */       } 
/*     */     } 
/* 207 */     return modified.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\epsg\FactoryUsingAnsiSQL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */