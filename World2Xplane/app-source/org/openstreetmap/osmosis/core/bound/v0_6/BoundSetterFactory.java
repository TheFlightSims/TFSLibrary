/*     */ package org.openstreetmap.osmosis.core.bound.v0_6;
/*     */ 
/*     */ import org.openstreetmap.osmosis.core.domain.v0_6.Bound;
/*     */ import org.openstreetmap.osmosis.core.pipeline.common.TaskConfiguration;
/*     */ import org.openstreetmap.osmosis.core.pipeline.common.TaskManager;
/*     */ import org.openstreetmap.osmosis.core.pipeline.common.TaskManagerFactory;
/*     */ import org.openstreetmap.osmosis.core.pipeline.v0_6.SinkSourceManager;
/*     */ 
/*     */ public class BoundSetterFactory extends TaskManagerFactory {
/*     */   private static final String ARG_LEFT = "left";
/*     */   
/*     */   private static final String ARG_RIGHT = "right";
/*     */   
/*     */   private static final String ARG_TOP = "top";
/*     */   
/*     */   private static final String ARG_BOTTOM = "bottom";
/*     */   
/*     */   private static final String ARG_X1 = "x1";
/*     */   
/*     */   private static final String ARG_Y1 = "y1";
/*     */   
/*     */   private static final String ARG_X2 = "x2";
/*     */   
/*     */   private static final String ARG_Y2 = "y2";
/*     */   
/*     */   private static final String ARG_ZOOM = "zoom";
/*     */   
/*     */   private static final String ARG_REMOVE = "remove";
/*     */   
/*     */   private static final String ARG_ORIGIN = "origin";
/*     */   
/*     */   private static final double DEFAULT_LEFT = -180.0D;
/*     */   
/*     */   private static final double DEFAULT_RIGHT = 180.0D;
/*     */   
/*     */   private static final double DEFAULT_TOP = 90.0D;
/*     */   
/*     */   private static final double DEFAULT_BOTTOM = -90.0D;
/*     */   
/*     */   private static final int DEFAULT_ZOOM = 12;
/*     */   
/*     */   private static final String DEFAULT_ORIGIN = "Osmosis/0.43.1";
/*     */   
/*     */   private static final boolean DEFAULT_REMOVE = false;
/*     */   
/*     */   private double xToLon(int zoom, int x) {
/*  41 */     double unit = 360.0D / Math.pow(2.0D, zoom);
/*  42 */     return -180.0D + x * unit;
/*     */   }
/*     */   
/*     */   private double projectF(double lat) {
/*  48 */     return Math.log(Math.tan(lat) + 1.0D / Math.cos(lat));
/*     */   }
/*     */   
/*     */   private double projectMercToLat(double y) {
/*  53 */     return Math.toDegrees(Math.atan(Math.sinh(y)));
/*     */   }
/*     */   
/*     */   private double yToLat(int zoom, int y) {
/*  62 */     double limitY = projectF(Math.atan(Math.sinh(Math.PI)));
/*  63 */     double limitY2 = projectF(Math.atan(Math.sinh(-3.141592653589793D)));
/*  64 */     double rangeY = limitY - limitY2;
/*  66 */     double unit = 1.0D / Math.pow(2.0D, zoom);
/*  67 */     double relY = limitY - rangeY * y * unit;
/*  70 */     return projectMercToLat(relY);
/*     */   }
/*     */   
/*     */   protected TaskManager createTaskManagerImpl(TaskConfiguration taskConfig) {
/*  81 */     Bound newBound = null;
/*  82 */     String origin = null;
/*  85 */     boolean remove = getBooleanArgument(taskConfig, "remove", false);
/*  87 */     if (!remove) {
/*  88 */       origin = getStringArgument(taskConfig, "origin", "Osmosis/0.43.1");
/*  89 */       double left = getDoubleArgument(taskConfig, "left", -180.0D);
/*  90 */       double right = getDoubleArgument(taskConfig, "right", 180.0D);
/*  91 */       double top = getDoubleArgument(taskConfig, "top", 90.0D);
/*  92 */       double bottom = getDoubleArgument(taskConfig, "bottom", -90.0D);
/*  94 */       int zoom = getIntegerArgument(taskConfig, "zoom", 12);
/*  95 */       if (doesArgumentExist(taskConfig, "x1")) {
/*  96 */         int x1 = getIntegerArgument(taskConfig, "x1");
/*  97 */         left = xToLon(zoom, x1);
/*  98 */         right = xToLon(zoom, getIntegerArgument(taskConfig, "x2", x1) + 1);
/*     */       } 
/* 100 */       if (doesArgumentExist(taskConfig, "y1")) {
/* 101 */         int y1 = getIntegerArgument(taskConfig, "y1");
/* 102 */         top = yToLat(zoom, y1);
/* 103 */         bottom = yToLat(zoom, getIntegerArgument(taskConfig, "y2", y1) + 1);
/*     */       } 
/* 106 */       newBound = new Bound(right, left, top, bottom, origin);
/*     */     } 
/* 109 */     return (TaskManager)new SinkSourceManager(taskConfig.getId(), new BoundSetter(newBound), taskConfig.getPipeArgs());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\bound\v0_6\BoundSetterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */