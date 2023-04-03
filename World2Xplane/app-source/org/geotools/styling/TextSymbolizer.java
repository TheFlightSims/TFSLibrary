/*     */ package org.geotools.styling;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.style.Fill;
/*     */ import org.opengis.style.Font;
/*     */ import org.opengis.style.Halo;
/*     */ import org.opengis.style.LabelPlacement;
/*     */ import org.opengis.style.TextSymbolizer;
/*     */ 
/*     */ public interface TextSymbolizer extends TextSymbolizer, Symbolizer {
/*     */   public static final String GROUP_KEY = "group";
/*     */   
/*     */   public static final boolean DEFAULT_GROUP = false;
/*     */   
/*     */   public static final String SPACE_AROUND_KEY = "spaceAround";
/*     */   
/*     */   public static final int DEFAULT_SPACE_AROUND = 0;
/*     */   
/*     */   public static final String MAX_DISPLACEMENT_KEY = "maxDisplacement";
/*     */   
/*     */   public static final int DEFAULT_MAX_DISPLACEMENT = 0;
/*     */   
/*     */   public static final String MIN_GROUP_DISTANCE_KEY = "minGroupDistance";
/*     */   
/*     */   public static final int DEFAULT_MIN_GROUP_DISTANCE = -1;
/*     */   
/*     */   public static final String LABEL_REPEAT_KEY = "repeat";
/*     */   
/*     */   public static final int DEFAULT_LABEL_REPEAT = 0;
/*     */   
/*     */   public static final String LABEL_ALL_GROUP_KEY = "labelAllGroup";
/*     */   
/*     */   public static final boolean DEFAULT_LABEL_ALL_GROUP = false;
/*     */   
/*     */   public static final String ALLOW_OVERRUNS_KEY = "allowOverruns";
/*     */   
/*     */   public static final boolean DEFAULT_ALLOW_OVERRUNS = true;
/*     */   
/*     */   public static final boolean DEFAULT_REMOVE_OVERLAPS = false;
/*     */   
/*     */   public static final String FOLLOW_LINE_KEY = "followLine";
/*     */   
/*     */   public static final boolean DEFAULT_FOLLOW_LINE = false;
/*     */   
/*     */   public static final String MAX_ANGLE_DELTA_KEY = "maxAngleDelta";
/*     */   
/*     */   public static final double DEFAULT_MAX_ANGLE_DELTA = 22.5D;
/*     */   
/*     */   public static final String AUTO_WRAP_KEY = "autoWrap";
/*     */   
/*     */   public static final int DEFAULT_AUTO_WRAP = 0;
/*     */   
/*     */   public static final String FORCE_LEFT_TO_RIGHT_KEY = "forceLeftToRight";
/*     */   
/*     */   public static final boolean DEFAULT_FORCE_LEFT_TO_RIGHT = true;
/*     */   
/*     */   public static final String CONFLICT_RESOLUTION_KEY = "conflictResolution";
/*     */   
/*     */   public static final boolean DEFAULT_CONFLICT_RESOLUTION = true;
/*     */   
/*     */   public static final String GOODNESS_OF_FIT_KEY = "goodnessOfFit";
/*     */   
/*     */   public static final double DEFAULT_GOODNESS_OF_FIT = 0.5D;
/*     */   
/*     */   public static final String POLYGONALIGN_KEY = "polygonAlign";
/*     */   
/*     */   public static final String GRAPHIC_MARGIN_KEY = "graphic-margin";
/*     */   
/*     */   public static final String GRAPHIC_RESIZE_KEY = "graphic-resize";
/*     */   
/*     */   public enum PolygonAlignOptions {
/* 273 */     NONE, ORTHO, MBR;
/*     */   }
/*     */   
/* 288 */   public static final PolygonAlignOptions DEFAULT_POLYGONALIGN = PolygonAlignOptions.NONE;
/*     */   
/*     */   Expression getLabel();
/*     */   
/*     */   void setLabel(Expression paramExpression);
/*     */   
/*     */   Font[] getFonts();
/*     */   
/*     */   Font getFont();
/*     */   
/*     */   void setFont(Font paramFont);
/*     */   
/*     */   void setFonts(Font[] paramArrayOfFont);
/*     */   
/*     */   LabelPlacement getLabelPlacement();
/*     */   
/*     */   void setLabelPlacement(LabelPlacement paramLabelPlacement);
/*     */   
/*     */   void setPlacement(LabelPlacement paramLabelPlacement);
/*     */   
/*     */   LabelPlacement getPlacement();
/*     */   
/*     */   Halo getHalo();
/*     */   
/*     */   void setHalo(Halo paramHalo);
/*     */   
/*     */   Fill getFill();
/*     */   
/*     */   void setFill(Fill paramFill);
/*     */   
/*     */   void setPriority(Expression paramExpression);
/*     */   
/*     */   Expression getPriority();
/*     */   
/*     */   void addToOptions(String paramString1, String paramString2);
/*     */   
/*     */   String getOption(String paramString);
/*     */   
/*     */   Map<String, String> getOptions();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\TextSymbolizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */