/*     */ package org.geotools.referencing.factory.epsg;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import javax.sql.DataSource;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ 
/*     */ public class AnsiDialectEpsgFactory extends AbstractEpsgFactory {
/*  60 */   private static final String[] ANSI = new String[] { 
/*  60 */       "[Alias]", "epsg_alias", "[Area]", "epsg_area", "[Coordinate Axis]", "epsg_coordinateaxis", "[Coordinate Axis Name]", "epsg_coordinateaxisname", "[Coordinate_Operation]", "epsg_coordoperation", 
/*  60 */       "[Coordinate_Operation Method]", "epsg_coordoperationmethod", "[Coordinate_Operation Parameter]", "epsg_coordoperationparam", "[Coordinate_Operation Parameter Usage]", "epsg_coordoperationparamusage", "[Coordinate_Operation Parameter Value]", "epsg_coordoperationparamvalue", "[Coordinate_Operation Path]", "epsg_coordoperationpath", 
/*  60 */       "[Coordinate Reference System]", "epsg_coordinatereferencesystem", "[Coordinate System]", "epsg_coordinatesystem", "[Datum]", "epsg_datum", "[Ellipsoid]", "epsg_ellipsoid", "[Naming System]", "epsg_namingsystem", 
/*  60 */       "[Prime Meridian]", "epsg_primemeridian", "[Supersession]", "epsg_supersession", "[Unit of Measure]", "epsg_unitofmeasure", "[Version History]", "epsg_versionhistory", "[ORDER]", "coord_axis_order" };
/*     */   
/* 114 */   protected final Map map = new LinkedHashMap<Object, Object>();
/*     */   
/* 120 */   private String prefix = "epsg_";
/*     */   
/*     */   public AnsiDialectEpsgFactory(Hints userHints) throws FactoryException {
/* 130 */     super(userHints);
/* 131 */     for (int i = 0; i < ANSI.length; i++)
/* 132 */       this.map.put(ANSI[i], ANSI[++i]); 
/*     */   }
/*     */   
/*     */   public AnsiDialectEpsgFactory(Hints hints, Connection connection) {
/* 143 */     super(hints, connection);
/* 144 */     for (int i = 0; i < ANSI.length; i++)
/* 145 */       this.map.put(ANSI[i], ANSI[++i]); 
/*     */   }
/*     */   
/*     */   public AnsiDialectEpsgFactory(Hints hints, DataSource dataSource) {
/* 156 */     super(hints, dataSource);
/* 157 */     for (int i = 0; i < ANSI.length; i++)
/* 158 */       this.map.put(ANSI[i], ANSI[++i]); 
/*     */   }
/*     */   
/*     */   protected void setSchema(String schema) {
/* 171 */     schema = schema.trim();
/* 172 */     int length = schema.length();
/* 173 */     if (length == 0)
/* 174 */       throw new IllegalArgumentException(schema); 
/* 176 */     char separator = schema.charAt(length - 1);
/* 177 */     if (separator != '.' && separator != '_') {
/* 178 */       schema = schema + '.';
/* 179 */     } else if (length == 1) {
/* 180 */       throw new IllegalArgumentException(schema);
/*     */     } 
/* 182 */     for (Iterator<Map.Entry> it = this.map.entrySet().iterator(); it.hasNext(); ) {
/* 183 */       Map.Entry entry = it.next();
/* 184 */       String tableName = (String)entry.getValue();
/* 190 */       if (tableName.startsWith(this.prefix))
/* 191 */         entry.setValue(schema + tableName.substring(this.prefix.length())); 
/*     */     } 
/* 194 */     this.prefix = schema;
/*     */   }
/*     */   
/*     */   protected String adaptSQL(String statement) {
/* 206 */     StringBuilder modified = new StringBuilder(statement);
/* 207 */     for (Iterator<Map.Entry> it = this.map.entrySet().iterator(); it.hasNext(); ) {
/* 208 */       Map.Entry entry = it.next();
/* 209 */       String oldName = (String)entry.getKey();
/* 210 */       String newName = (String)entry.getValue();
/* 214 */       int start = 0;
/* 215 */       while ((start = modified.indexOf(oldName, start)) >= 0) {
/* 216 */         modified.replace(start, start + oldName.length(), newName);
/* 217 */         start += newName.length();
/*     */       } 
/*     */     } 
/* 220 */     return modified.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\epsg\AnsiDialectEpsgFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */