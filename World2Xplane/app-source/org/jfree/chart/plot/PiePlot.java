/*      */ package org.jfree.chart.plot;
/*      */ 
/*      */ import java.awt.AlphaComposite;
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Composite;
/*      */ import java.awt.Font;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Arc2D;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ResourceBundle;
/*      */ import org.jfree.chart.LegendItem;
/*      */ import org.jfree.chart.LegendItemCollection;
/*      */ import org.jfree.chart.entity.ChartEntity;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.entity.PieSectionEntity;
/*      */ import org.jfree.chart.event.PlotChangeEvent;
/*      */ import org.jfree.chart.labels.PieSectionLabelGenerator;
/*      */ import org.jfree.chart.labels.PieToolTipGenerator;
/*      */ import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
/*      */ import org.jfree.chart.urls.PieURLGenerator;
/*      */ import org.jfree.data.DefaultKeyedValues;
/*      */ import org.jfree.data.KeyedValues;
/*      */ import org.jfree.data.general.Dataset;
/*      */ import org.jfree.data.general.DatasetChangeEvent;
/*      */ import org.jfree.data.general.DatasetUtilities;
/*      */ import org.jfree.data.general.PieDataset;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.text.G2TextMeasurer;
/*      */ import org.jfree.text.TextBlock;
/*      */ import org.jfree.text.TextBox;
/*      */ import org.jfree.text.TextMeasurer;
/*      */ import org.jfree.text.TextUtilities;
/*      */ import org.jfree.ui.RectangleAnchor;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.util.ObjectList;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PaintList;
/*      */ import org.jfree.util.PaintUtilities;
/*      */ import org.jfree.util.Rotation;
/*      */ import org.jfree.util.ShapeUtilities;
/*      */ import org.jfree.util.StrokeList;
/*      */ 
/*      */ public class PiePlot extends Plot implements Cloneable, Serializable {
/*      */   private static final long serialVersionUID = -795612466005590431L;
/*      */   
/*      */   public static final double DEFAULT_INTERIOR_GAP = 0.25D;
/*      */   
/*      */   public static final double MAX_INTERIOR_GAP = 0.4D;
/*      */   
/*      */   public static final double DEFAULT_START_ANGLE = 90.0D;
/*      */   
/*  219 */   public static final Font DEFAULT_LABEL_FONT = new Font("SansSerif", 0, 10);
/*      */   
/*  223 */   public static final Paint DEFAULT_LABEL_PAINT = Color.black;
/*      */   
/*  226 */   public static final Paint DEFAULT_LABEL_BACKGROUND_PAINT = new Color(255, 255, 192);
/*      */   
/*  230 */   public static final Paint DEFAULT_LABEL_OUTLINE_PAINT = Color.black;
/*      */   
/*  233 */   public static final Stroke DEFAULT_LABEL_OUTLINE_STROKE = new BasicStroke(0.5F);
/*      */   
/*  237 */   public static final Paint DEFAULT_LABEL_SHADOW_PAINT = Color.lightGray;
/*      */   
/*      */   public static final double DEFAULT_MINIMUM_ARC_ANGLE_TO_DRAW = 1.0E-5D;
/*      */   
/*      */   private PieDataset dataset;
/*      */   
/*      */   private int pieIndex;
/*      */   
/*      */   private double interiorGap;
/*      */   
/*      */   private boolean circular;
/*      */   
/*      */   private double startAngle;
/*      */   
/*      */   private Rotation direction;
/*      */   
/*      */   private transient Paint sectionPaint;
/*      */   
/*      */   private PaintList sectionPaintList;
/*      */   
/*      */   private transient Paint baseSectionPaint;
/*      */   
/*      */   private boolean sectionOutlinesVisible;
/*      */   
/*      */   private transient Paint sectionOutlinePaint;
/*      */   
/*      */   private PaintList sectionOutlinePaintList;
/*      */   
/*      */   private transient Paint baseSectionOutlinePaint;
/*      */   
/*      */   private transient Stroke sectionOutlineStroke;
/*      */   
/*      */   private StrokeList sectionOutlineStrokeList;
/*      */   
/*      */   private transient Stroke baseSectionOutlineStroke;
/*      */   
/*  297 */   private transient Paint shadowPaint = Color.gray;
/*      */   
/*  300 */   private double shadowXOffset = 4.0D;
/*      */   
/*  303 */   private double shadowYOffset = 4.0D;
/*      */   
/*      */   private ObjectList explodePercentages;
/*      */   
/*      */   private PieSectionLabelGenerator labelGenerator;
/*      */   
/*      */   private Font labelFont;
/*      */   
/*      */   private transient Paint labelPaint;
/*      */   
/*      */   private transient Paint labelBackgroundPaint;
/*      */   
/*      */   private transient Paint labelOutlinePaint;
/*      */   
/*      */   private transient Stroke labelOutlineStroke;
/*      */   
/*      */   private transient Paint labelShadowPaint;
/*      */   
/*  339 */   private double maximumLabelWidth = 0.2D;
/*      */   
/*  345 */   private double labelGap = 0.05D;
/*      */   
/*      */   private boolean labelLinksVisible;
/*      */   
/*  351 */   private double labelLinkMargin = 0.05D;
/*      */   
/*  354 */   private transient Paint labelLinkPaint = Color.black;
/*      */   
/*  357 */   private transient Stroke labelLinkStroke = new BasicStroke(0.5F);
/*      */   
/*      */   private PieToolTipGenerator toolTipGenerator;
/*      */   
/*      */   private PieURLGenerator urlGenerator;
/*      */   
/*      */   private PieSectionLabelGenerator legendLabelGenerator;
/*      */   
/*      */   private PieSectionLabelGenerator legendLabelToolTipGenerator;
/*      */   
/*      */   private boolean ignoreNullValues;
/*      */   
/*      */   private boolean ignoreZeroValues;
/*      */   
/*      */   private transient Shape legendItemShape;
/*      */   
/*      */   private double minimumArcAngleToDraw;
/*      */   
/*  398 */   protected static ResourceBundle localizationResources = ResourceBundle.getBundle("org.jfree.chart.plot.LocalizationBundle");
/*      */   
/*      */   public PiePlot() {
/*  405 */     this((PieDataset)null);
/*      */   }
/*      */   
/*      */   public PiePlot(PieDataset dataset) {
/*  415 */     this.dataset = dataset;
/*  416 */     if (dataset != null)
/*  417 */       dataset.addChangeListener(this); 
/*  419 */     this.pieIndex = 0;
/*  421 */     this.interiorGap = 0.25D;
/*  422 */     this.circular = true;
/*  423 */     this.startAngle = 90.0D;
/*  424 */     this.direction = Rotation.CLOCKWISE;
/*  425 */     this.minimumArcAngleToDraw = 1.0E-5D;
/*  427 */     this.sectionPaint = null;
/*  428 */     this.sectionPaintList = new PaintList();
/*  429 */     this.baseSectionPaint = null;
/*  431 */     this.sectionOutlinesVisible = true;
/*  432 */     this.sectionOutlinePaint = null;
/*  433 */     this.sectionOutlinePaintList = new PaintList();
/*  434 */     this.baseSectionOutlinePaint = DEFAULT_OUTLINE_PAINT;
/*  436 */     this.sectionOutlineStroke = null;
/*  437 */     this.sectionOutlineStrokeList = new StrokeList();
/*  438 */     this.baseSectionOutlineStroke = DEFAULT_OUTLINE_STROKE;
/*  440 */     this.explodePercentages = new ObjectList();
/*  442 */     this.labelGenerator = (PieSectionLabelGenerator)new StandardPieSectionLabelGenerator();
/*  443 */     this.labelFont = DEFAULT_LABEL_FONT;
/*  444 */     this.labelPaint = DEFAULT_LABEL_PAINT;
/*  445 */     this.labelBackgroundPaint = DEFAULT_LABEL_BACKGROUND_PAINT;
/*  446 */     this.labelOutlinePaint = DEFAULT_LABEL_OUTLINE_PAINT;
/*  447 */     this.labelOutlineStroke = DEFAULT_LABEL_OUTLINE_STROKE;
/*  448 */     this.labelShadowPaint = DEFAULT_LABEL_SHADOW_PAINT;
/*  449 */     this.labelLinksVisible = true;
/*  451 */     this.toolTipGenerator = null;
/*  452 */     this.urlGenerator = null;
/*  453 */     this.legendLabelGenerator = (PieSectionLabelGenerator)new StandardPieSectionLabelGenerator();
/*  454 */     this.legendLabelToolTipGenerator = null;
/*  455 */     this.legendItemShape = Plot.DEFAULT_LEGEND_ITEM_CIRCLE;
/*  457 */     this.ignoreNullValues = false;
/*  458 */     this.ignoreZeroValues = false;
/*      */   }
/*      */   
/*      */   public PieDataset getDataset() {
/*  467 */     return this.dataset;
/*      */   }
/*      */   
/*      */   public void setDataset(PieDataset dataset) {
/*  478 */     PieDataset existing = this.dataset;
/*  479 */     if (existing != null)
/*  480 */       existing.removeChangeListener(this); 
/*  484 */     this.dataset = dataset;
/*  485 */     if (dataset != null) {
/*  486 */       setDatasetGroup(dataset.getGroup());
/*  487 */       dataset.addChangeListener(this);
/*      */     } 
/*  491 */     DatasetChangeEvent event = new DatasetChangeEvent(this, (Dataset)dataset);
/*  492 */     datasetChanged(event);
/*      */   }
/*      */   
/*      */   public int getPieIndex() {
/*  502 */     return this.pieIndex;
/*      */   }
/*      */   
/*      */   public void setPieIndex(int index) {
/*  512 */     this.pieIndex = index;
/*      */   }
/*      */   
/*      */   public double getStartAngle() {
/*  522 */     return this.startAngle;
/*      */   }
/*      */   
/*      */   public void setStartAngle(double angle) {
/*  534 */     this.startAngle = angle;
/*  535 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Rotation getDirection() {
/*  545 */     return this.direction;
/*      */   }
/*      */   
/*      */   public void setDirection(Rotation direction) {
/*  555 */     if (direction == null)
/*  556 */       throw new IllegalArgumentException("Null 'direction' argument."); 
/*  558 */     this.direction = direction;
/*  559 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public double getInteriorGap() {
/*  570 */     return this.interiorGap;
/*      */   }
/*      */   
/*      */   public void setInteriorGap(double percent) {
/*  584 */     if (percent < 0.0D || percent > 0.4D)
/*  585 */       throw new IllegalArgumentException("Invalid 'percent' (" + percent + ") argument."); 
/*  590 */     if (this.interiorGap != percent) {
/*  591 */       this.interiorGap = percent;
/*  592 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isCircular() {
/*  604 */     return this.circular;
/*      */   }
/*      */   
/*      */   public void setCircular(boolean flag) {
/*  614 */     setCircular(flag, true);
/*      */   }
/*      */   
/*      */   public void setCircular(boolean circular, boolean notify) {
/*  625 */     this.circular = circular;
/*  626 */     if (notify)
/*  627 */       notifyListeners(new PlotChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public boolean getIgnoreNullValues() {
/*  638 */     return this.ignoreNullValues;
/*      */   }
/*      */   
/*      */   public void setIgnoreNullValues(boolean flag) {
/*  650 */     this.ignoreNullValues = flag;
/*  651 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean getIgnoreZeroValues() {
/*  661 */     return this.ignoreZeroValues;
/*      */   }
/*      */   
/*      */   public void setIgnoreZeroValues(boolean flag) {
/*  673 */     this.ignoreZeroValues = flag;
/*  674 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getSectionPaint() {
/*  685 */     return this.sectionPaint;
/*      */   }
/*      */   
/*      */   public void setSectionPaint(Paint paint) {
/*  696 */     this.sectionPaint = paint;
/*  697 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getSectionPaint(int section) {
/*  710 */     if (this.sectionPaint != null)
/*  711 */       return this.sectionPaint; 
/*  715 */     Paint result = this.sectionPaintList.getPaint(section);
/*  716 */     if (result == null) {
/*  717 */       DrawingSupplier supplier = getDrawingSupplier();
/*  718 */       if (supplier != null) {
/*  719 */         Paint p = supplier.getNextPaint();
/*  720 */         this.sectionPaintList.setPaint(section, p);
/*  721 */         result = p;
/*      */       } else {
/*  724 */         result = this.baseSectionPaint;
/*      */       } 
/*      */     } 
/*  727 */     return result;
/*      */   }
/*      */   
/*      */   public void setSectionPaint(int section, Paint paint) {
/*  739 */     this.sectionPaintList.setPaint(section, paint);
/*  740 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getBaseSectionPaint() {
/*  750 */     return this.baseSectionPaint;
/*      */   }
/*      */   
/*      */   public void setBaseSectionPaint(Paint paint) {
/*  759 */     if (paint == null)
/*  760 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/*  762 */     this.baseSectionPaint = paint;
/*  763 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean getSectionOutlinesVisible() {
/*  776 */     return this.sectionOutlinesVisible;
/*      */   }
/*      */   
/*      */   public void setSectionOutlinesVisible(boolean visible) {
/*  787 */     this.sectionOutlinesVisible = visible;
/*  788 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getSectionOutlinePaint() {
/*  797 */     return this.sectionOutlinePaint;
/*      */   }
/*      */   
/*      */   public void setSectionOutlinePaint(Paint paint) {
/*  808 */     this.sectionOutlinePaint = paint;
/*  809 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getSectionOutlinePaint(int section) {
/*  822 */     if (this.sectionOutlinePaint != null)
/*  823 */       return this.sectionOutlinePaint; 
/*  827 */     Paint result = this.sectionOutlinePaintList.getPaint(section);
/*  828 */     if (result == null)
/*  829 */       result = this.baseSectionOutlinePaint; 
/*  831 */     return result;
/*      */   }
/*      */   
/*      */   public void setSectionOutlinePaint(int section, Paint paint) {
/*  843 */     this.sectionOutlinePaintList.setPaint(section, paint);
/*  844 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getBaseSectionOutlinePaint() {
/*  854 */     return this.baseSectionOutlinePaint;
/*      */   }
/*      */   
/*      */   public void setBaseSectionOutlinePaint(Paint paint) {
/*  863 */     if (paint == null)
/*  864 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/*  866 */     this.baseSectionOutlinePaint = paint;
/*  867 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Stroke getSectionOutlineStroke() {
/*  878 */     return this.sectionOutlineStroke;
/*      */   }
/*      */   
/*      */   public void setSectionOutlineStroke(Stroke stroke) {
/*  889 */     this.sectionOutlineStroke = stroke;
/*  890 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Stroke getSectionOutlineStroke(int section) {
/*  903 */     if (this.sectionOutlineStroke != null)
/*  904 */       return this.sectionOutlineStroke; 
/*  908 */     Stroke result = this.sectionOutlineStrokeList.getStroke(section);
/*  909 */     if (result == null)
/*  910 */       result = this.baseSectionOutlineStroke; 
/*  912 */     return result;
/*      */   }
/*      */   
/*      */   public void setSectionOutlineStroke(int section, Stroke stroke) {
/*  924 */     this.sectionOutlineStrokeList.setStroke(section, stroke);
/*  925 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Stroke getBaseSectionOutlineStroke() {
/*  935 */     return this.baseSectionOutlineStroke;
/*      */   }
/*      */   
/*      */   public void setBaseSectionOutlineStroke(Stroke stroke) {
/*  944 */     if (stroke == null)
/*  945 */       throw new IllegalArgumentException("Null 'stroke' argument."); 
/*  947 */     this.baseSectionOutlineStroke = stroke;
/*  948 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getShadowPaint() {
/*  957 */     return this.shadowPaint;
/*      */   }
/*      */   
/*      */   public void setShadowPaint(Paint paint) {
/*  967 */     this.shadowPaint = paint;
/*  968 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public double getShadowXOffset() {
/*  977 */     return this.shadowXOffset;
/*      */   }
/*      */   
/*      */   public void setShadowXOffset(double offset) {
/*  987 */     this.shadowXOffset = offset;
/*  988 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public double getShadowYOffset() {
/*  997 */     return this.shadowYOffset;
/*      */   }
/*      */   
/*      */   public void setShadowYOffset(double offset) {
/* 1007 */     this.shadowYOffset = offset;
/* 1008 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public double getExplodePercent(int section) {
/* 1019 */     double result = 0.0D;
/* 1020 */     if (this.explodePercentages != null) {
/* 1021 */       Number percent = (Number)this.explodePercentages.get(section);
/* 1022 */       if (percent != null)
/* 1023 */         result = percent.doubleValue(); 
/*      */     } 
/* 1026 */     return result;
/*      */   }
/*      */   
/*      */   public void setExplodePercent(int section, double percent) {
/* 1037 */     if (this.explodePercentages == null)
/* 1038 */       this.explodePercentages = new ObjectList(); 
/* 1040 */     this.explodePercentages.set(section, new Double(percent));
/* 1041 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public double getMaximumExplodePercent() {
/* 1050 */     double result = 0.0D;
/* 1051 */     for (int i = 0; i < this.explodePercentages.size(); i++) {
/* 1052 */       Number explode = (Number)this.explodePercentages.get(i);
/* 1053 */       if (explode != null)
/* 1054 */         result = Math.max(result, explode.doubleValue()); 
/*      */     } 
/* 1057 */     return result;
/*      */   }
/*      */   
/*      */   public PieSectionLabelGenerator getLabelGenerator() {
/* 1066 */     return this.labelGenerator;
/*      */   }
/*      */   
/*      */   public void setLabelGenerator(PieSectionLabelGenerator generator) {
/* 1076 */     this.labelGenerator = generator;
/* 1077 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public double getLabelGap() {
/* 1087 */     return this.labelGap;
/*      */   }
/*      */   
/*      */   public void setLabelGap(double gap) {
/* 1098 */     this.labelGap = gap;
/* 1099 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public double getMaximumLabelWidth() {
/* 1108 */     return this.maximumLabelWidth;
/*      */   }
/*      */   
/*      */   public void setMaximumLabelWidth(double width) {
/* 1118 */     this.maximumLabelWidth = width;
/* 1119 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean getLabelLinksVisible() {
/* 1129 */     return this.labelLinksVisible;
/*      */   }
/*      */   
/*      */   public void setLabelLinksVisible(boolean visible) {
/* 1142 */     this.labelLinksVisible = visible;
/* 1143 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public double getLabelLinkMargin() {
/* 1153 */     return this.labelLinkMargin;
/*      */   }
/*      */   
/*      */   public void setLabelLinkMargin(double margin) {
/* 1163 */     this.labelLinkMargin = margin;
/* 1164 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getLabelLinkPaint() {
/* 1174 */     return this.labelLinkPaint;
/*      */   }
/*      */   
/*      */   public void setLabelLinkPaint(Paint paint) {
/* 1185 */     if (paint == null)
/* 1186 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 1188 */     this.labelLinkPaint = paint;
/* 1189 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Stroke getLabelLinkStroke() {
/* 1198 */     return this.labelLinkStroke;
/*      */   }
/*      */   
/*      */   public void setLabelLinkStroke(Stroke stroke) {
/* 1208 */     if (stroke == null)
/* 1209 */       throw new IllegalArgumentException("Null 'stroke' argument."); 
/* 1211 */     this.labelLinkStroke = stroke;
/* 1212 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Font getLabelFont() {
/* 1221 */     return this.labelFont;
/*      */   }
/*      */   
/*      */   public void setLabelFont(Font font) {
/* 1231 */     if (font == null)
/* 1232 */       throw new IllegalArgumentException("Null 'font' argument."); 
/* 1234 */     this.labelFont = font;
/* 1235 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getLabelPaint() {
/* 1244 */     return this.labelPaint;
/*      */   }
/*      */   
/*      */   public void setLabelPaint(Paint paint) {
/* 1254 */     if (paint == null)
/* 1255 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 1257 */     this.labelPaint = paint;
/* 1258 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getLabelBackgroundPaint() {
/* 1267 */     return this.labelBackgroundPaint;
/*      */   }
/*      */   
/*      */   public void setLabelBackgroundPaint(Paint paint) {
/* 1277 */     this.labelBackgroundPaint = paint;
/* 1278 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getLabelOutlinePaint() {
/* 1287 */     return this.labelOutlinePaint;
/*      */   }
/*      */   
/*      */   public void setLabelOutlinePaint(Paint paint) {
/* 1297 */     this.labelOutlinePaint = paint;
/* 1298 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Stroke getLabelOutlineStroke() {
/* 1307 */     return this.labelOutlineStroke;
/*      */   }
/*      */   
/*      */   public void setLabelOutlineStroke(Stroke stroke) {
/* 1317 */     this.labelOutlineStroke = stroke;
/* 1318 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getLabelShadowPaint() {
/* 1327 */     return this.labelShadowPaint;
/*      */   }
/*      */   
/*      */   public void setLabelShadowPaint(Paint paint) {
/* 1337 */     this.labelShadowPaint = paint;
/* 1338 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public PieToolTipGenerator getToolTipGenerator() {
/* 1349 */     return this.toolTipGenerator;
/*      */   }
/*      */   
/*      */   public void setToolTipGenerator(PieToolTipGenerator generator) {
/* 1360 */     this.toolTipGenerator = generator;
/* 1361 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public PieURLGenerator getURLGenerator() {
/* 1370 */     return this.urlGenerator;
/*      */   }
/*      */   
/*      */   public void setURLGenerator(PieURLGenerator generator) {
/* 1380 */     this.urlGenerator = generator;
/* 1381 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public double getMinimumArcAngleToDraw() {
/* 1391 */     return this.minimumArcAngleToDraw;
/*      */   }
/*      */   
/*      */   public void setMinimumArcAngleToDraw(double angle) {
/* 1411 */     this.minimumArcAngleToDraw = angle;
/*      */   }
/*      */   
/*      */   public Shape getLegendItemShape() {
/* 1420 */     return this.legendItemShape;
/*      */   }
/*      */   
/*      */   public void setLegendItemShape(Shape shape) {
/* 1429 */     if (shape == null)
/* 1430 */       throw new IllegalArgumentException("Null 'shape' argument."); 
/* 1432 */     this.legendItemShape = shape;
/* 1433 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public PieSectionLabelGenerator getLegendLabelToolTipGenerator() {
/* 1442 */     return this.legendLabelToolTipGenerator;
/*      */   }
/*      */   
/*      */   public void setLegendLabelToolTipGenerator(PieSectionLabelGenerator generator) {
/* 1453 */     this.legendLabelToolTipGenerator = generator;
/* 1454 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public PieSectionLabelGenerator getLegendLabelGenerator() {
/* 1463 */     return this.legendLabelGenerator;
/*      */   }
/*      */   
/*      */   public void setLegendLabelGenerator(PieSectionLabelGenerator generator) {
/* 1473 */     if (generator == null)
/* 1474 */       throw new IllegalArgumentException("Null 'generator' argument."); 
/* 1476 */     this.legendLabelGenerator = generator;
/* 1477 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public PiePlotState initialise(Graphics2D g2, Rectangle2D plotArea, PiePlot plot, Integer index, PlotRenderingInfo info) {
/* 1501 */     PiePlotState state = new PiePlotState(info);
/* 1502 */     state.setPassesRequired(2);
/* 1503 */     state.setTotal(DatasetUtilities.calculatePieDatasetTotal(plot.getDataset()));
/* 1506 */     state.setLatestAngle(plot.getStartAngle());
/* 1507 */     return state;
/*      */   }
/*      */   
/*      */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info) {
/* 1527 */     RectangleInsets insets = getInsets();
/* 1528 */     insets.trim(area);
/* 1530 */     if (info != null) {
/* 1531 */       info.setPlotArea(area);
/* 1532 */       info.setDataArea(area);
/*      */     } 
/* 1535 */     drawBackground(g2, area);
/* 1536 */     drawOutline(g2, area);
/* 1538 */     Shape savedClip = g2.getClip();
/* 1539 */     g2.clip(area);
/* 1541 */     Composite originalComposite = g2.getComposite();
/* 1542 */     g2.setComposite(AlphaComposite.getInstance(3, getForegroundAlpha()));
/* 1548 */     if (!DatasetUtilities.isEmptyOrNull(this.dataset)) {
/* 1549 */       drawPie(g2, area, info);
/*      */     } else {
/* 1552 */       drawNoDataMessage(g2, area);
/*      */     } 
/* 1555 */     g2.setClip(savedClip);
/* 1556 */     g2.setComposite(originalComposite);
/* 1558 */     drawOutline(g2, area);
/*      */   }
/*      */   
/*      */   protected void drawPie(Graphics2D g2, Rectangle2D plotArea, PlotRenderingInfo info) {
/* 1573 */     PiePlotState state = initialise(g2, plotArea, this, (Integer)null, info);
/* 1576 */     double labelWidth = 0.0D;
/* 1577 */     if (this.labelGenerator != null)
/* 1578 */       labelWidth = this.labelGap + this.maximumLabelWidth + this.labelLinkMargin; 
/* 1581 */     double gapHorizontal = plotArea.getWidth() * (this.interiorGap + labelWidth);
/* 1583 */     double gapVertical = plotArea.getHeight() * this.interiorGap;
/* 1585 */     double linkX = plotArea.getX() + gapHorizontal / 2.0D;
/* 1586 */     double linkY = plotArea.getY() + gapVertical / 2.0D;
/* 1587 */     double linkW = plotArea.getWidth() - gapHorizontal;
/* 1588 */     double linkH = plotArea.getHeight() - gapVertical;
/* 1591 */     if (this.circular) {
/* 1592 */       double min = Math.min(linkW, linkH) / 2.0D;
/* 1593 */       linkX = (linkX + linkX + linkW) / 2.0D - min;
/* 1594 */       linkY = (linkY + linkY + linkH) / 2.0D - min;
/* 1595 */       linkW = 2.0D * min;
/* 1596 */       linkH = 2.0D * min;
/*      */     } 
/* 1601 */     Rectangle2D linkArea = new Rectangle2D.Double(linkX, linkY, linkW, linkH);
/* 1604 */     state.setLinkArea(linkArea);
/* 1609 */     double hh = linkArea.getWidth() * this.labelLinkMargin;
/* 1610 */     double vv = linkArea.getHeight() * this.labelLinkMargin;
/* 1611 */     Rectangle2D explodeArea = new Rectangle2D.Double(linkX + hh / 2.0D, linkY + vv / 2.0D, linkW - hh, linkH - vv);
/* 1615 */     state.setExplodedPieArea(explodeArea);
/* 1620 */     double maximumExplodePercent = getMaximumExplodePercent();
/* 1621 */     double percent = maximumExplodePercent / (1.0D + maximumExplodePercent);
/* 1623 */     double h1 = explodeArea.getWidth() * percent;
/* 1624 */     double v1 = explodeArea.getHeight() * percent;
/* 1625 */     Rectangle2D pieArea = new Rectangle2D.Double(explodeArea.getX() + h1 / 2.0D, explodeArea.getY() + v1 / 2.0D, explodeArea.getWidth() - h1, explodeArea.getHeight() - v1);
/* 1630 */     state.setPieArea(pieArea);
/* 1631 */     state.setPieCenterX(pieArea.getCenterX());
/* 1632 */     state.setPieCenterY(pieArea.getCenterY());
/* 1633 */     state.setPieWRadius(pieArea.getWidth() / 2.0D);
/* 1634 */     state.setPieHRadius(pieArea.getHeight() / 2.0D);
/* 1636 */     if (this.dataset != null && this.dataset.getKeys().size() > 0) {
/* 1638 */       List keys = this.dataset.getKeys();
/* 1639 */       double totalValue = DatasetUtilities.calculatePieDatasetTotal(this.dataset);
/* 1642 */       int passesRequired = state.getPassesRequired();
/* 1643 */       for (int pass = 0; pass < passesRequired; pass++) {
/* 1644 */         double runningTotal = 0.0D;
/* 1645 */         for (int section = 0; section < keys.size(); section++) {
/* 1646 */           Number n = this.dataset.getValue(section);
/* 1647 */           if (n != null) {
/* 1648 */             double value = n.doubleValue();
/* 1649 */             if (value > 0.0D) {
/* 1650 */               runningTotal += value;
/* 1651 */               drawItem(g2, section, explodeArea, state, pass);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/* 1657 */       drawLabels(g2, keys, totalValue, plotArea, linkArea, state);
/*      */     } else {
/* 1661 */       drawNoDataMessage(g2, plotArea);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void drawItem(Graphics2D g2, int section, Rectangle2D dataArea, PiePlotState state, int currentPass) {
/* 1680 */     Number n = this.dataset.getValue(section);
/* 1681 */     if (n == null)
/*      */       return; 
/* 1684 */     double value = n.doubleValue();
/* 1685 */     double angle1 = 0.0D;
/* 1686 */     double angle2 = 0.0D;
/* 1688 */     if (this.direction == Rotation.CLOCKWISE) {
/* 1689 */       angle1 = state.getLatestAngle();
/* 1690 */       angle2 = angle1 - value / state.getTotal() * 360.0D;
/* 1692 */     } else if (this.direction == Rotation.ANTICLOCKWISE) {
/* 1693 */       angle1 = state.getLatestAngle();
/* 1694 */       angle2 = angle1 + value / state.getTotal() * 360.0D;
/*      */     } else {
/* 1697 */       throw new IllegalStateException("Rotation type not recognised.");
/*      */     } 
/* 1700 */     double angle = angle2 - angle1;
/* 1701 */     if (Math.abs(angle) > getMinimumArcAngleToDraw()) {
/* 1702 */       double ep = 0.0D;
/* 1703 */       double mep = getMaximumExplodePercent();
/* 1704 */       if (mep > 0.0D)
/* 1705 */         ep = getExplodePercent(section) / mep; 
/* 1707 */       Rectangle2D arcBounds = getArcBounds(state.getPieArea(), state.getExplodedPieArea(), angle1, angle, ep);
/* 1711 */       Arc2D.Double arc = new Arc2D.Double(arcBounds, angle1, angle, 2);
/* 1715 */       if (currentPass == 0) {
/* 1716 */         if (this.shadowPaint != null) {
/* 1717 */           Shape shadowArc = ShapeUtilities.createTranslatedShape(arc, (float)this.shadowXOffset, (float)this.shadowYOffset);
/* 1721 */           g2.setPaint(this.shadowPaint);
/* 1722 */           g2.fill(shadowArc);
/*      */         } 
/* 1725 */       } else if (currentPass == 1) {
/* 1727 */         Paint paint = getSectionPaint(section);
/* 1728 */         g2.setPaint(paint);
/* 1729 */         g2.fill(arc);
/* 1731 */         Paint outlinePaint = getSectionOutlinePaint(section);
/* 1732 */         Stroke outlineStroke = getSectionOutlineStroke(section);
/* 1733 */         if (this.sectionOutlinesVisible) {
/* 1734 */           g2.setPaint(outlinePaint);
/* 1735 */           g2.setStroke(outlineStroke);
/* 1736 */           g2.draw(arc);
/*      */         } 
/* 1741 */         if (state.getInfo() != null) {
/* 1742 */           EntityCollection entities = state.getEntityCollection();
/* 1743 */           if (entities != null) {
/* 1744 */             Comparable key = this.dataset.getKey(section);
/* 1745 */             String tip = null;
/* 1746 */             if (this.toolTipGenerator != null)
/* 1747 */               tip = this.toolTipGenerator.generateToolTip(this.dataset, key); 
/* 1751 */             String url = null;
/* 1752 */             if (this.urlGenerator != null)
/* 1753 */               url = this.urlGenerator.generateURL(this.dataset, key, this.pieIndex); 
/* 1757 */             PieSectionEntity entity = new PieSectionEntity(arc, this.dataset, this.pieIndex, section, key, tip, url);
/* 1761 */             entities.add((ChartEntity)entity);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1766 */     state.setLatestAngle(angle2);
/*      */   }
/*      */   
/*      */   protected void drawLabels(Graphics2D g2, List keys, double totalValue, Rectangle2D plotArea, Rectangle2D linkArea, PiePlotState state) {
/* 1783 */     Composite originalComposite = g2.getComposite();
/* 1784 */     g2.setComposite(AlphaComposite.getInstance(3, 1.0F));
/* 1789 */     DefaultKeyedValues leftKeys = new DefaultKeyedValues();
/* 1790 */     DefaultKeyedValues rightKeys = new DefaultKeyedValues();
/* 1792 */     double runningTotal1 = 0.0D;
/* 1793 */     Iterator iterator1 = keys.iterator();
/* 1794 */     while (iterator1.hasNext()) {
/* 1795 */       Comparable key = iterator1.next();
/* 1796 */       Number n = this.dataset.getValue(key);
/* 1797 */       if (n != null) {
/* 1798 */         double v = n.doubleValue();
/* 1799 */         if (this.ignoreZeroValues ? (v > 0.0D) : (v >= 0.0D)) {
/* 1800 */           runningTotal1 += v;
/* 1803 */           double mid = this.startAngle + this.direction.getFactor() * (runningTotal1 - v / 2.0D) * 360.0D / totalValue;
/* 1805 */           if (Math.cos(Math.toRadians(mid)) < 0.0D) {
/* 1806 */             leftKeys.addValue(key, new Double(mid));
/*      */             continue;
/*      */           } 
/* 1809 */           rightKeys.addValue(key, new Double(mid));
/*      */         } 
/*      */       } 
/*      */     } 
/* 1815 */     g2.setFont(getLabelFont());
/* 1816 */     float maxLabelWidth = (float)(getMaximumLabelWidth() * plotArea.getWidth());
/* 1820 */     if (this.labelGenerator != null) {
/* 1821 */       drawLeftLabels((KeyedValues)leftKeys, g2, plotArea, linkArea, maxLabelWidth, state);
/* 1824 */       drawRightLabels((KeyedValues)rightKeys, g2, plotArea, linkArea, maxLabelWidth, state);
/*      */     } 
/* 1828 */     g2.setComposite(originalComposite);
/*      */   }
/*      */   
/*      */   protected void drawLeftLabels(KeyedValues leftKeys, Graphics2D g2, Rectangle2D plotArea, Rectangle2D linkArea, float maxLabelWidth, PiePlotState state) {
/* 1846 */     PieLabelDistributor distributor1 = new PieLabelDistributor(leftKeys.getItemCount());
/* 1849 */     double lGap = plotArea.getWidth() * this.labelGap;
/* 1850 */     double verticalLinkRadius = state.getLinkArea().getHeight() / 2.0D;
/*      */     int i;
/* 1851 */     for (i = 0; i < leftKeys.getItemCount(); i++) {
/* 1852 */       String label = this.labelGenerator.generateSectionLabel(this.dataset, leftKeys.getKey(i));
/* 1855 */       if (label != null) {
/* 1856 */         TextBlock block = TextUtilities.createTextBlock(label, this.labelFont, this.labelPaint, maxLabelWidth, (TextMeasurer)new G2TextMeasurer(g2));
/* 1861 */         TextBox labelBox = new TextBox(block);
/* 1862 */         labelBox.setBackgroundPaint(this.labelBackgroundPaint);
/* 1863 */         labelBox.setOutlinePaint(this.labelOutlinePaint);
/* 1864 */         labelBox.setOutlineStroke(this.labelOutlineStroke);
/* 1865 */         labelBox.setShadowPaint(this.labelShadowPaint);
/* 1866 */         double theta = Math.toRadians(leftKeys.getValue(i).doubleValue());
/* 1869 */         double baseY = state.getPieCenterY() - Math.sin(theta) * verticalLinkRadius;
/* 1871 */         double hh = labelBox.getHeight(g2);
/* 1873 */         distributor1.addPieLabelRecord(new PieLabelRecord(leftKeys.getKey(i), theta, baseY, labelBox, hh, lGap / 2.0D + lGap / 2.0D * -Math.cos(theta), 0.9D + getExplodePercent(this.dataset.getIndex(leftKeys.getKey(i)))));
/*      */       } 
/*      */     } 
/* 1883 */     distributor1.distributeLabels(plotArea.getMinY(), plotArea.getHeight());
/* 1884 */     for (i = 0; i < distributor1.getItemCount(); i++)
/* 1885 */       drawLeftLabel(g2, state, distributor1.getPieLabelRecord(i)); 
/*      */   }
/*      */   
/*      */   protected void drawRightLabels(KeyedValues keys, Graphics2D g2, Rectangle2D plotArea, Rectangle2D linkArea, float maxLabelWidth, PiePlotState state) {
/* 1904 */     PieLabelDistributor distributor2 = new PieLabelDistributor(keys.getItemCount());
/* 1906 */     double lGap = plotArea.getWidth() * this.labelGap;
/* 1907 */     double verticalLinkRadius = state.getLinkArea().getHeight() / 2.0D;
/*      */     int i;
/* 1909 */     for (i = 0; i < keys.getItemCount(); i++) {
/* 1910 */       String label = this.labelGenerator.generateSectionLabel(this.dataset, keys.getKey(i));
/* 1914 */       if (label != null) {
/* 1915 */         TextBlock block = TextUtilities.createTextBlock(label, this.labelFont, this.labelPaint, maxLabelWidth, (TextMeasurer)new G2TextMeasurer(g2));
/* 1919 */         TextBox labelBox = new TextBox(block);
/* 1920 */         labelBox.setBackgroundPaint(this.labelBackgroundPaint);
/* 1921 */         labelBox.setOutlinePaint(this.labelOutlinePaint);
/* 1922 */         labelBox.setOutlineStroke(this.labelOutlineStroke);
/* 1923 */         labelBox.setShadowPaint(this.labelShadowPaint);
/* 1924 */         double theta = Math.toRadians(keys.getValue(i).doubleValue());
/* 1925 */         double baseY = state.getPieCenterY() - Math.sin(theta) * verticalLinkRadius;
/* 1927 */         double hh = labelBox.getHeight(g2);
/* 1928 */         distributor2.addPieLabelRecord(new PieLabelRecord(keys.getKey(i), theta, baseY, labelBox, hh, lGap / 2.0D + lGap / 2.0D * Math.cos(theta), 0.9D + getExplodePercent(this.dataset.getIndex(keys.getKey(i)))));
/*      */       } 
/*      */     } 
/* 1938 */     distributor2.distributeLabels(linkArea.getMinY(), linkArea.getHeight());
/* 1939 */     for (i = 0; i < distributor2.getItemCount(); i++)
/* 1940 */       drawRightLabel(g2, state, distributor2.getPieLabelRecord(i)); 
/*      */   }
/*      */   
/*      */   public LegendItemCollection getLegendItems() {
/* 1952 */     LegendItemCollection result = new LegendItemCollection();
/* 1953 */     if (this.dataset == null)
/* 1954 */       return result; 
/* 1956 */     List keys = this.dataset.getKeys();
/* 1957 */     int section = 0;
/* 1958 */     Shape shape = getLegendItemShape();
/* 1959 */     Iterator iterator = keys.iterator();
/* 1960 */     while (iterator.hasNext()) {
/* 1961 */       Comparable key = iterator.next();
/* 1962 */       Number n = this.dataset.getValue(key);
/* 1963 */       boolean include = true;
/* 1964 */       if (n == null) {
/* 1965 */         include = !this.ignoreNullValues;
/*      */       } else {
/* 1968 */         double v = n.doubleValue();
/* 1969 */         if (v == 0.0D) {
/* 1970 */           include = !this.ignoreZeroValues;
/*      */         } else {
/* 1973 */           include = (v > 0.0D);
/*      */         } 
/*      */       } 
/* 1976 */       if (include) {
/* 1977 */         String label = this.legendLabelGenerator.generateSectionLabel(this.dataset, key);
/* 1980 */         String description = label;
/* 1981 */         String toolTipText = null;
/* 1982 */         if (this.legendLabelToolTipGenerator != null)
/* 1983 */           toolTipText = this.legendLabelToolTipGenerator.generateSectionLabel(this.dataset, key); 
/* 1988 */         String urlText = null;
/* 1989 */         Paint paint = getSectionPaint(section);
/* 1990 */         Paint outlinePaint = getSectionOutlinePaint(section);
/* 1991 */         Stroke outlineStroke = getSectionOutlineStroke(section);
/* 1993 */         LegendItem item = new LegendItem(label, description, toolTipText, urlText, true, shape, true, paint, true, outlinePaint, outlineStroke, false, new Line2D.Float(), new BasicStroke(), Color.black);
/* 1998 */         result.add(item);
/* 1999 */         section++;
/*      */       } 
/*      */     } 
/* 2002 */     return result;
/*      */   }
/*      */   
/*      */   public String getPlotType() {
/* 2011 */     return localizationResources.getString("Pie_Plot");
/*      */   }
/*      */   
/*      */   public void zoom(double percent) {}
/*      */   
/*      */   protected Rectangle2D getArcBounds(Rectangle2D unexploded, Rectangle2D exploded, double angle, double extent, double explodePercent) {
/* 2045 */     if (explodePercent == 0.0D)
/* 2046 */       return unexploded; 
/* 2049 */     Arc2D arc1 = new Arc2D.Double(unexploded, angle, extent / 2.0D, 0);
/* 2052 */     Point2D point1 = arc1.getEndPoint();
/* 2053 */     Arc2D.Double arc2 = new Arc2D.Double(exploded, angle, extent / 2.0D, 0);
/* 2056 */     Point2D point2 = arc2.getEndPoint();
/* 2057 */     double deltaX = (point1.getX() - point2.getX()) * explodePercent;
/* 2058 */     double deltaY = (point1.getY() - point2.getY()) * explodePercent;
/* 2059 */     return new Rectangle2D.Double(unexploded.getX() - deltaX, unexploded.getY() - deltaY, unexploded.getWidth(), unexploded.getHeight());
/*      */   }
/*      */   
/*      */   protected void drawLeftLabel(Graphics2D g2, PiePlotState state, PieLabelRecord record) {
/* 2076 */     double anchorX = state.getLinkArea().getMinX();
/* 2077 */     double targetX = anchorX - record.getGap();
/* 2078 */     double targetY = record.getAllocatedY();
/* 2080 */     if (this.labelLinksVisible) {
/* 2081 */       double theta = record.getAngle();
/* 2082 */       double linkX = state.getPieCenterX() + Math.cos(theta) * state.getPieWRadius() * record.getLinkPercent();
/* 2084 */       double linkY = state.getPieCenterY() - Math.sin(theta) * state.getPieHRadius() * record.getLinkPercent();
/* 2086 */       double elbowX = state.getPieCenterX() + Math.cos(theta) * state.getLinkArea().getWidth() / 2.0D;
/* 2088 */       double elbowY = state.getPieCenterY() - Math.sin(theta) * state.getLinkArea().getHeight() / 2.0D;
/* 2090 */       double anchorY = elbowY;
/* 2091 */       g2.setPaint(this.labelLinkPaint);
/* 2092 */       g2.setStroke(this.labelLinkStroke);
/* 2093 */       g2.draw(new Line2D.Double(linkX, linkY, elbowX, elbowY));
/* 2094 */       g2.draw(new Line2D.Double(anchorX, anchorY, elbowX, elbowY));
/* 2095 */       g2.draw(new Line2D.Double(anchorX, anchorY, targetX, targetY));
/*      */     } 
/* 2097 */     TextBox tb = record.getLabel();
/* 2098 */     tb.draw(g2, (float)targetX, (float)targetY, RectangleAnchor.RIGHT);
/*      */   }
/*      */   
/*      */   protected void drawRightLabel(Graphics2D g2, PiePlotState state, PieLabelRecord record) {
/* 2112 */     double anchorX = state.getLinkArea().getMaxX();
/* 2113 */     double targetX = anchorX + record.getGap();
/* 2114 */     double targetY = record.getAllocatedY();
/* 2116 */     if (this.labelLinksVisible) {
/* 2117 */       double theta = record.getAngle();
/* 2118 */       double linkX = state.getPieCenterX() + Math.cos(theta) * state.getPieWRadius() * record.getLinkPercent();
/* 2120 */       double linkY = state.getPieCenterY() - Math.sin(theta) * state.getPieHRadius() * record.getLinkPercent();
/* 2122 */       double elbowX = state.getPieCenterX() + Math.cos(theta) * state.getLinkArea().getWidth() / 2.0D;
/* 2124 */       double elbowY = state.getPieCenterY() - Math.sin(theta) * state.getLinkArea().getHeight() / 2.0D;
/* 2126 */       double anchorY = elbowY;
/* 2127 */       g2.setPaint(this.labelLinkPaint);
/* 2128 */       g2.setStroke(this.labelLinkStroke);
/* 2129 */       g2.draw(new Line2D.Double(linkX, linkY, elbowX, elbowY));
/* 2130 */       g2.draw(new Line2D.Double(anchorX, anchorY, elbowX, elbowY));
/* 2131 */       g2.draw(new Line2D.Double(anchorX, anchorY, targetX, targetY));
/*      */     } 
/* 2134 */     TextBox tb = record.getLabel();
/* 2135 */     tb.draw(g2, (float)targetX, (float)targetY, RectangleAnchor.LEFT);
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/* 2148 */     if (obj == this)
/* 2149 */       return true; 
/* 2151 */     if (!(obj instanceof PiePlot))
/* 2152 */       return false; 
/* 2154 */     if (!super.equals(obj))
/* 2155 */       return false; 
/* 2157 */     PiePlot that = (PiePlot)obj;
/* 2158 */     if (this.pieIndex != that.pieIndex)
/* 2159 */       return false; 
/* 2161 */     if (this.interiorGap != that.interiorGap)
/* 2162 */       return false; 
/* 2164 */     if (this.circular != that.circular)
/* 2165 */       return false; 
/* 2167 */     if (this.startAngle != that.startAngle)
/* 2168 */       return false; 
/* 2170 */     if (this.direction != that.direction)
/* 2171 */       return false; 
/* 2173 */     if (this.ignoreZeroValues != that.ignoreZeroValues)
/* 2174 */       return false; 
/* 2176 */     if (this.ignoreNullValues != that.ignoreNullValues)
/* 2177 */       return false; 
/* 2179 */     if (!PaintUtilities.equal(this.sectionPaint, that.sectionPaint))
/* 2180 */       return false; 
/* 2182 */     if (!ObjectUtilities.equal(this.sectionPaintList, that.sectionPaintList))
/* 2184 */       return false; 
/* 2186 */     if (!PaintUtilities.equal(this.baseSectionPaint, that.baseSectionPaint))
/* 2188 */       return false; 
/* 2190 */     if (this.sectionOutlinesVisible != that.sectionOutlinesVisible)
/* 2191 */       return false; 
/* 2193 */     if (!PaintUtilities.equal(this.sectionOutlinePaint, that.sectionOutlinePaint))
/* 2195 */       return false; 
/* 2197 */     if (!ObjectUtilities.equal(this.sectionOutlinePaintList, that.sectionOutlinePaintList))
/* 2199 */       return false; 
/* 2201 */     if (!PaintUtilities.equal(this.baseSectionOutlinePaint, that.baseSectionOutlinePaint))
/* 2204 */       return false; 
/* 2206 */     if (!ObjectUtilities.equal(this.sectionOutlineStroke, that.sectionOutlineStroke))
/* 2208 */       return false; 
/* 2210 */     if (!ObjectUtilities.equal(this.sectionOutlineStrokeList, that.sectionOutlineStrokeList))
/* 2213 */       return false; 
/* 2215 */     if (!ObjectUtilities.equal(this.baseSectionOutlineStroke, that.baseSectionOutlineStroke))
/* 2218 */       return false; 
/* 2220 */     if (!PaintUtilities.equal(this.shadowPaint, that.shadowPaint))
/* 2221 */       return false; 
/* 2223 */     if (this.shadowXOffset != that.shadowXOffset)
/* 2224 */       return false; 
/* 2226 */     if (this.shadowYOffset != that.shadowYOffset)
/* 2227 */       return false; 
/* 2229 */     if (!ObjectUtilities.equal(this.explodePercentages, that.explodePercentages))
/* 2231 */       return false; 
/* 2233 */     if (!ObjectUtilities.equal(this.labelGenerator, that.labelGenerator))
/* 2235 */       return false; 
/* 2237 */     if (!ObjectUtilities.equal(this.labelFont, that.labelFont))
/* 2238 */       return false; 
/* 2240 */     if (!PaintUtilities.equal(this.labelPaint, that.labelPaint))
/* 2241 */       return false; 
/* 2243 */     if (!PaintUtilities.equal(this.labelBackgroundPaint, that.labelBackgroundPaint))
/* 2245 */       return false; 
/* 2247 */     if (!PaintUtilities.equal(this.labelOutlinePaint, that.labelOutlinePaint))
/* 2249 */       return false; 
/* 2251 */     if (!ObjectUtilities.equal(this.labelOutlineStroke, that.labelOutlineStroke))
/* 2253 */       return false; 
/* 2255 */     if (!PaintUtilities.equal(this.labelShadowPaint, that.labelShadowPaint))
/* 2257 */       return false; 
/* 2259 */     if (this.maximumLabelWidth != that.maximumLabelWidth)
/* 2260 */       return false; 
/* 2262 */     if (this.labelGap != that.labelGap)
/* 2263 */       return false; 
/* 2265 */     if (this.labelLinkMargin != that.labelLinkMargin)
/* 2266 */       return false; 
/* 2268 */     if (this.labelLinksVisible != that.labelLinksVisible)
/* 2269 */       return false; 
/* 2271 */     if (!PaintUtilities.equal(this.labelLinkPaint, that.labelLinkPaint))
/* 2272 */       return false; 
/* 2274 */     if (!ObjectUtilities.equal(this.labelLinkStroke, that.labelLinkStroke))
/* 2276 */       return false; 
/* 2278 */     if (!ObjectUtilities.equal(this.toolTipGenerator, that.toolTipGenerator))
/* 2280 */       return false; 
/* 2282 */     if (!ObjectUtilities.equal(this.urlGenerator, that.urlGenerator))
/* 2283 */       return false; 
/* 2285 */     if (this.minimumArcAngleToDraw != that.minimumArcAngleToDraw)
/* 2286 */       return false; 
/* 2288 */     if (!ShapeUtilities.equal(this.legendItemShape, that.legendItemShape))
/* 2289 */       return false; 
/* 2292 */     return true;
/*      */   }
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/* 2305 */     PiePlot clone = (PiePlot)super.clone();
/* 2306 */     if (clone.dataset != null)
/* 2307 */       clone.dataset.addChangeListener(clone); 
/* 2309 */     return clone;
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 2321 */     stream.defaultWriteObject();
/* 2322 */     SerialUtilities.writePaint(this.sectionPaint, stream);
/* 2323 */     SerialUtilities.writePaint(this.baseSectionPaint, stream);
/* 2324 */     SerialUtilities.writePaint(this.sectionOutlinePaint, stream);
/* 2325 */     SerialUtilities.writePaint(this.baseSectionOutlinePaint, stream);
/* 2326 */     SerialUtilities.writeStroke(this.sectionOutlineStroke, stream);
/* 2327 */     SerialUtilities.writeStroke(this.baseSectionOutlineStroke, stream);
/* 2328 */     SerialUtilities.writePaint(this.shadowPaint, stream);
/* 2329 */     SerialUtilities.writePaint(this.labelPaint, stream);
/* 2330 */     SerialUtilities.writePaint(this.labelBackgroundPaint, stream);
/* 2331 */     SerialUtilities.writePaint(this.labelOutlinePaint, stream);
/* 2332 */     SerialUtilities.writeStroke(this.labelOutlineStroke, stream);
/* 2333 */     SerialUtilities.writePaint(this.labelShadowPaint, stream);
/* 2334 */     SerialUtilities.writePaint(this.labelLinkPaint, stream);
/* 2335 */     SerialUtilities.writeStroke(this.labelLinkStroke, stream);
/* 2336 */     SerialUtilities.writeShape(this.legendItemShape, stream);
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 2349 */     stream.defaultReadObject();
/* 2350 */     this.sectionPaint = SerialUtilities.readPaint(stream);
/* 2351 */     this.baseSectionPaint = SerialUtilities.readPaint(stream);
/* 2352 */     this.sectionOutlinePaint = SerialUtilities.readPaint(stream);
/* 2353 */     this.baseSectionOutlinePaint = SerialUtilities.readPaint(stream);
/* 2354 */     this.sectionOutlineStroke = SerialUtilities.readStroke(stream);
/* 2355 */     this.baseSectionOutlineStroke = SerialUtilities.readStroke(stream);
/* 2356 */     this.shadowPaint = SerialUtilities.readPaint(stream);
/* 2357 */     this.labelPaint = SerialUtilities.readPaint(stream);
/* 2358 */     this.labelBackgroundPaint = SerialUtilities.readPaint(stream);
/* 2359 */     this.labelOutlinePaint = SerialUtilities.readPaint(stream);
/* 2360 */     this.labelOutlineStroke = SerialUtilities.readStroke(stream);
/* 2361 */     this.labelShadowPaint = SerialUtilities.readPaint(stream);
/* 2362 */     this.labelLinkPaint = SerialUtilities.readPaint(stream);
/* 2363 */     this.labelLinkStroke = SerialUtilities.readStroke(stream);
/* 2364 */     this.legendItemShape = SerialUtilities.readShape(stream);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\PiePlot.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */