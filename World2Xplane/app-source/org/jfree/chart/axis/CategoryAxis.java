/*      */ package org.jfree.chart.axis;
/*      */ 
/*      */ import java.awt.Font;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Shape;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import org.jfree.chart.entity.ChartEntity;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.entity.TickLabelEntity;
/*      */ import org.jfree.chart.event.AxisChangeEvent;
/*      */ import org.jfree.chart.plot.CategoryPlot;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.text.G2TextMeasurer;
/*      */ import org.jfree.text.TextBlock;
/*      */ import org.jfree.text.TextMeasurer;
/*      */ import org.jfree.text.TextUtilities;
/*      */ import org.jfree.ui.RectangleAnchor;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.ui.Size2D;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PaintUtilities;
/*      */ import org.jfree.util.ShapeUtilities;
/*      */ 
/*      */ public class CategoryAxis extends Axis implements Cloneable, Serializable {
/*      */   private static final long serialVersionUID = 5886554608114265863L;
/*      */   
/*      */   public static final double DEFAULT_AXIS_MARGIN = 0.05D;
/*      */   
/*      */   public static final double DEFAULT_CATEGORY_MARGIN = 0.2D;
/*      */   
/*      */   private double lowerMargin;
/*      */   
/*      */   private double upperMargin;
/*      */   
/*      */   private double categoryMargin;
/*      */   
/*      */   private int maximumCategoryLabelLines;
/*      */   
/*      */   private float maximumCategoryLabelWidthRatio;
/*      */   
/*      */   private int categoryLabelPositionOffset;
/*      */   
/*      */   private CategoryLabelPositions categoryLabelPositions;
/*      */   
/*      */   private Map tickLabelFontMap;
/*      */   
/*      */   private transient Map tickLabelPaintMap;
/*      */   
/*      */   private Map categoryLabelToolTips;
/*      */   
/*      */   public CategoryAxis() {
/*  175 */     this((String)null);
/*      */   }
/*      */   
/*      */   public CategoryAxis(String label) {
/*  185 */     super(label);
/*  187 */     this.lowerMargin = 0.05D;
/*  188 */     this.upperMargin = 0.05D;
/*  189 */     this.categoryMargin = 0.2D;
/*  190 */     this.maximumCategoryLabelLines = 1;
/*  191 */     this.maximumCategoryLabelWidthRatio = 0.0F;
/*  193 */     setTickMarksVisible(false);
/*  195 */     this.categoryLabelPositionOffset = 4;
/*  196 */     this.categoryLabelPositions = CategoryLabelPositions.STANDARD;
/*  197 */     this.tickLabelFontMap = new HashMap();
/*  198 */     this.tickLabelPaintMap = new HashMap();
/*  199 */     this.categoryLabelToolTips = new HashMap();
/*      */   }
/*      */   
/*      */   public double getLowerMargin() {
/*  209 */     return this.lowerMargin;
/*      */   }
/*      */   
/*      */   public void setLowerMargin(double margin) {
/*  220 */     this.lowerMargin = margin;
/*  221 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public double getUpperMargin() {
/*  230 */     return this.upperMargin;
/*      */   }
/*      */   
/*      */   public void setUpperMargin(double margin) {
/*  241 */     this.upperMargin = margin;
/*  242 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public double getCategoryMargin() {
/*  251 */     return this.categoryMargin;
/*      */   }
/*      */   
/*      */   public void setCategoryMargin(double margin) {
/*  263 */     this.categoryMargin = margin;
/*  264 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public int getMaximumCategoryLabelLines() {
/*  273 */     return this.maximumCategoryLabelLines;
/*      */   }
/*      */   
/*      */   public void setMaximumCategoryLabelLines(int lines) {
/*  283 */     this.maximumCategoryLabelLines = lines;
/*  284 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public float getMaximumCategoryLabelWidthRatio() {
/*  293 */     return this.maximumCategoryLabelWidthRatio;
/*      */   }
/*      */   
/*      */   public void setMaximumCategoryLabelWidthRatio(float ratio) {
/*  303 */     this.maximumCategoryLabelWidthRatio = ratio;
/*  304 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public int getCategoryLabelPositionOffset() {
/*  314 */     return this.categoryLabelPositionOffset;
/*      */   }
/*      */   
/*      */   public void setCategoryLabelPositionOffset(int offset) {
/*  324 */     this.categoryLabelPositionOffset = offset;
/*  325 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public CategoryLabelPositions getCategoryLabelPositions() {
/*  335 */     return this.categoryLabelPositions;
/*      */   }
/*      */   
/*      */   public void setCategoryLabelPositions(CategoryLabelPositions positions) {
/*  345 */     if (positions == null)
/*  346 */       throw new IllegalArgumentException("Null 'positions' argument."); 
/*  348 */     this.categoryLabelPositions = positions;
/*  349 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Font getTickLabelFont(Comparable category) {
/*  360 */     if (category == null)
/*  361 */       throw new IllegalArgumentException("Null 'category' argument."); 
/*  363 */     Font result = (Font)this.tickLabelFontMap.get(category);
/*  365 */     if (result == null)
/*  366 */       result = getTickLabelFont(); 
/*  368 */     return result;
/*      */   }
/*      */   
/*      */   public void setTickLabelFont(Comparable category, Font font) {
/*  379 */     if (category == null)
/*  380 */       throw new IllegalArgumentException("Null 'category' argument."); 
/*  382 */     if (font == null) {
/*  383 */       this.tickLabelFontMap.remove(category);
/*      */     } else {
/*  386 */       this.tickLabelFontMap.put(category, font);
/*      */     } 
/*  388 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getTickLabelPaint(Comparable category) {
/*  399 */     if (category == null)
/*  400 */       throw new IllegalArgumentException("Null 'category' argument."); 
/*  402 */     Paint result = (Paint)this.tickLabelPaintMap.get(category);
/*  404 */     if (result == null)
/*  405 */       result = getTickLabelPaint(); 
/*  407 */     return result;
/*      */   }
/*      */   
/*      */   public void setTickLabelPaint(Comparable category, Paint paint) {
/*  418 */     if (category == null)
/*  419 */       throw new IllegalArgumentException("Null 'category' argument."); 
/*  421 */     if (paint == null) {
/*  422 */       this.tickLabelPaintMap.remove(category);
/*      */     } else {
/*  425 */       this.tickLabelPaintMap.put(category, paint);
/*      */     } 
/*  427 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void addCategoryLabelToolTip(Comparable category, String tooltip) {
/*  438 */     if (category == null)
/*  439 */       throw new IllegalArgumentException("Null 'category' argument."); 
/*  441 */     this.categoryLabelToolTips.put(category, tooltip);
/*  442 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public String getCategoryLabelToolTip(Comparable category) {
/*  454 */     if (category == null)
/*  455 */       throw new IllegalArgumentException("Null 'category' argument."); 
/*  457 */     return (String)this.categoryLabelToolTips.get(category);
/*      */   }
/*      */   
/*      */   public void removeCategoryLabelToolTip(Comparable category) {
/*  467 */     if (category == null)
/*  468 */       throw new IllegalArgumentException("Null 'category' argument."); 
/*  470 */     this.categoryLabelToolTips.remove(category);
/*  471 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void clearCategoryLabelToolTips() {
/*  479 */     this.categoryLabelToolTips.clear();
/*  480 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public double getCategoryJava2DCoordinate(CategoryAnchor anchor, int category, int categoryCount, Rectangle2D area, RectangleEdge edge) {
/*  500 */     double result = 0.0D;
/*  501 */     if (anchor == CategoryAnchor.START) {
/*  502 */       result = getCategoryStart(category, categoryCount, area, edge);
/*  504 */     } else if (anchor == CategoryAnchor.MIDDLE) {
/*  505 */       result = getCategoryMiddle(category, categoryCount, area, edge);
/*  507 */     } else if (anchor == CategoryAnchor.END) {
/*  508 */       result = getCategoryEnd(category, categoryCount, area, edge);
/*      */     } 
/*  510 */     return result;
/*      */   }
/*      */   
/*      */   public double getCategoryStart(int category, int categoryCount, Rectangle2D area, RectangleEdge edge) {
/*  528 */     double result = 0.0D;
/*  529 */     if (edge == RectangleEdge.TOP || edge == RectangleEdge.BOTTOM) {
/*  530 */       result = area.getX() + area.getWidth() * getLowerMargin();
/*  532 */     } else if (edge == RectangleEdge.LEFT || edge == RectangleEdge.RIGHT) {
/*  534 */       result = area.getMinY() + area.getHeight() * getLowerMargin();
/*      */     } 
/*  537 */     double categorySize = calculateCategorySize(categoryCount, area, edge);
/*  538 */     double categoryGapWidth = calculateCategoryGapSize(categoryCount, area, edge);
/*  542 */     result += category * (categorySize + categoryGapWidth);
/*  544 */     return result;
/*      */   }
/*      */   
/*      */   public double getCategoryMiddle(int category, int categoryCount, Rectangle2D area, RectangleEdge edge) {
/*  560 */     return getCategoryStart(category, categoryCount, area, edge) + calculateCategorySize(categoryCount, area, edge) / 2.0D;
/*      */   }
/*      */   
/*      */   public double getCategoryEnd(int category, int categoryCount, Rectangle2D area, RectangleEdge edge) {
/*  578 */     return getCategoryStart(category, categoryCount, area, edge) + calculateCategorySize(categoryCount, area, edge);
/*      */   }
/*      */   
/*      */   protected double calculateCategorySize(int categoryCount, Rectangle2D area, RectangleEdge edge) {
/*  596 */     double result = 0.0D;
/*  597 */     double available = 0.0D;
/*  599 */     if (edge == RectangleEdge.TOP || edge == RectangleEdge.BOTTOM) {
/*  600 */       available = area.getWidth();
/*  602 */     } else if (edge == RectangleEdge.LEFT || edge == RectangleEdge.RIGHT) {
/*  604 */       available = area.getHeight();
/*      */     } 
/*  606 */     if (categoryCount > 1) {
/*  607 */       result = available * (1.0D - getLowerMargin() - getUpperMargin() - getCategoryMargin());
/*  609 */       result /= categoryCount;
/*      */     } else {
/*  612 */       result = available * (1.0D - getLowerMargin() - getUpperMargin());
/*      */     } 
/*  614 */     return result;
/*      */   }
/*      */   
/*      */   protected double calculateCategoryGapSize(int categoryCount, Rectangle2D area, RectangleEdge edge) {
/*  632 */     double result = 0.0D;
/*  633 */     double available = 0.0D;
/*  635 */     if (edge == RectangleEdge.TOP || edge == RectangleEdge.BOTTOM) {
/*  636 */       available = area.getWidth();
/*  638 */     } else if (edge == RectangleEdge.LEFT || edge == RectangleEdge.RIGHT) {
/*  640 */       available = area.getHeight();
/*      */     } 
/*  643 */     if (categoryCount > 1)
/*  644 */       result = available * getCategoryMargin() / (categoryCount - 1); 
/*  647 */     return result;
/*      */   }
/*      */   
/*      */   public AxisSpace reserveSpace(Graphics2D g2, Plot plot, Rectangle2D plotArea, RectangleEdge edge, AxisSpace space) {
/*  667 */     if (space == null)
/*  668 */       space = new AxisSpace(); 
/*  672 */     if (!isVisible())
/*  673 */       return space; 
/*  677 */     double tickLabelHeight = 0.0D;
/*  678 */     double tickLabelWidth = 0.0D;
/*  679 */     if (isTickLabelsVisible()) {
/*  680 */       g2.setFont(getTickLabelFont());
/*  681 */       AxisState state = new AxisState();
/*  683 */       refreshTicks(g2, state, plotArea, edge);
/*  684 */       if (edge == RectangleEdge.TOP) {
/*  685 */         tickLabelHeight = state.getMax();
/*  687 */       } else if (edge == RectangleEdge.BOTTOM) {
/*  688 */         tickLabelHeight = state.getMax();
/*  690 */       } else if (edge == RectangleEdge.LEFT) {
/*  691 */         tickLabelWidth = state.getMax();
/*  693 */       } else if (edge == RectangleEdge.RIGHT) {
/*  694 */         tickLabelWidth = state.getMax();
/*      */       } 
/*      */     } 
/*  699 */     Rectangle2D labelEnclosure = getLabelEnclosure(g2, edge);
/*  700 */     double labelHeight = 0.0D;
/*  701 */     double labelWidth = 0.0D;
/*  702 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  703 */       labelHeight = labelEnclosure.getHeight();
/*  704 */       space.add(labelHeight + tickLabelHeight + this.categoryLabelPositionOffset, edge);
/*  709 */     } else if (RectangleEdge.isLeftOrRight(edge)) {
/*  710 */       labelWidth = labelEnclosure.getWidth();
/*  711 */       space.add(labelWidth + tickLabelWidth + this.categoryLabelPositionOffset, edge);
/*      */     } 
/*  716 */     return space;
/*      */   }
/*      */   
/*      */   public void configure() {}
/*      */   
/*      */   public AxisState draw(Graphics2D g2, double cursor, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, PlotRenderingInfo plotState) {
/*  751 */     if (!isVisible())
/*  752 */       return new AxisState(cursor); 
/*  755 */     if (isAxisLineVisible())
/*  756 */       drawAxisLine(g2, cursor, dataArea, edge); 
/*  760 */     AxisState state = new AxisState(cursor);
/*  761 */     state = drawCategoryLabels(g2, dataArea, edge, state, plotState);
/*  762 */     state = drawLabel(getLabel(), g2, plotArea, dataArea, edge, state);
/*  764 */     return state;
/*      */   }
/*      */   
/*      */   protected AxisState drawCategoryLabels(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge, AxisState state, PlotRenderingInfo plotState) {
/*  787 */     if (state == null)
/*  788 */       throw new IllegalArgumentException("Null 'state' argument."); 
/*  791 */     if (isTickLabelsVisible()) {
/*  792 */       List ticks = refreshTicks(g2, state, plotState.getPlotArea(), edge);
/*  793 */       state.setTicks(ticks);
/*  795 */       int categoryIndex = 0;
/*  796 */       Iterator iterator = ticks.iterator();
/*  797 */       while (iterator.hasNext()) {
/*  799 */         CategoryTick tick = iterator.next();
/*  800 */         g2.setFont(getTickLabelFont(tick.getCategory()));
/*  801 */         g2.setPaint(getTickLabelPaint(tick.getCategory()));
/*  803 */         CategoryLabelPosition position = this.categoryLabelPositions.getLabelPosition(edge);
/*  805 */         double x0 = 0.0D;
/*  806 */         double x1 = 0.0D;
/*  807 */         double y0 = 0.0D;
/*  808 */         double y1 = 0.0D;
/*  809 */         if (edge == RectangleEdge.TOP) {
/*  810 */           x0 = getCategoryStart(categoryIndex, ticks.size(), dataArea, edge);
/*  812 */           x1 = getCategoryEnd(categoryIndex, ticks.size(), dataArea, edge);
/*  814 */           y1 = state.getCursor() - this.categoryLabelPositionOffset;
/*  815 */           y0 = y1 - state.getMax();
/*  817 */         } else if (edge == RectangleEdge.BOTTOM) {
/*  818 */           x0 = getCategoryStart(categoryIndex, ticks.size(), dataArea, edge);
/*  820 */           x1 = getCategoryEnd(categoryIndex, ticks.size(), dataArea, edge);
/*  822 */           y0 = state.getCursor() + this.categoryLabelPositionOffset;
/*  823 */           y1 = y0 + state.getMax();
/*  825 */         } else if (edge == RectangleEdge.LEFT) {
/*  826 */           y0 = getCategoryStart(categoryIndex, ticks.size(), dataArea, edge);
/*  828 */           y1 = getCategoryEnd(categoryIndex, ticks.size(), dataArea, edge);
/*  830 */           x1 = state.getCursor() - this.categoryLabelPositionOffset;
/*  831 */           x0 = x1 - state.getMax();
/*  833 */         } else if (edge == RectangleEdge.RIGHT) {
/*  834 */           y0 = getCategoryStart(categoryIndex, ticks.size(), dataArea, edge);
/*  836 */           y1 = getCategoryEnd(categoryIndex, ticks.size(), dataArea, edge);
/*  838 */           x0 = state.getCursor() + this.categoryLabelPositionOffset;
/*  839 */           x1 = x0 - state.getMax();
/*      */         } 
/*  841 */         Rectangle2D area = new Rectangle2D.Double(x0, y0, x1 - x0, y1 - y0);
/*  843 */         Point2D anchorPoint = RectangleAnchor.coordinates(area, position.getCategoryAnchor());
/*  845 */         TextBlock block = tick.getLabel();
/*  846 */         block.draw(g2, (float)anchorPoint.getX(), (float)anchorPoint.getY(), position.getLabelAnchor(), (float)anchorPoint.getX(), (float)anchorPoint.getY(), position.getAngle());
/*  850 */         Shape bounds = block.calculateBounds(g2, (float)anchorPoint.getX(), (float)anchorPoint.getY(), position.getLabelAnchor(), (float)anchorPoint.getX(), (float)anchorPoint.getY(), position.getAngle());
/*  854 */         if (plotState != null && plotState.getOwner() != null) {
/*  855 */           EntityCollection entities = plotState.getOwner().getEntityCollection();
/*  857 */           if (entities != null) {
/*  858 */             String tooltip = getCategoryLabelToolTip(tick.getCategory());
/*  860 */             entities.add((ChartEntity)new TickLabelEntity(bounds, tooltip, null));
/*      */           } 
/*      */         } 
/*  864 */         categoryIndex++;
/*      */       } 
/*  867 */       if (edge.equals(RectangleEdge.TOP)) {
/*  868 */         double h = state.getMax();
/*  869 */         state.cursorUp(h);
/*  871 */       } else if (edge.equals(RectangleEdge.BOTTOM)) {
/*  872 */         double h = state.getMax();
/*  873 */         state.cursorDown(h);
/*  875 */       } else if (edge == RectangleEdge.LEFT) {
/*  876 */         double w = state.getMax();
/*  877 */         state.cursorLeft(w);
/*  879 */       } else if (edge == RectangleEdge.RIGHT) {
/*  880 */         double w = state.getMax();
/*  881 */         state.cursorRight(w);
/*      */       } 
/*      */     } 
/*  884 */     return state;
/*      */   }
/*      */   
/*      */   public List refreshTicks(Graphics2D g2, AxisState state, Rectangle2D dataArea, RectangleEdge edge) {
/*  902 */     List ticks = new ArrayList();
/*  905 */     if (dataArea.getHeight() <= 0.0D || dataArea.getWidth() < 0.0D)
/*  906 */       return ticks; 
/*  909 */     CategoryPlot plot = (CategoryPlot)getPlot();
/*  910 */     List categories = plot.getCategories();
/*  911 */     double max = 0.0D;
/*  913 */     if (categories != null) {
/*  914 */       CategoryLabelPosition position = this.categoryLabelPositions.getLabelPosition(edge);
/*  916 */       float r = this.maximumCategoryLabelWidthRatio;
/*  917 */       if (r <= 0.0D)
/*  918 */         r = position.getWidthRatio(); 
/*  921 */       float l = 0.0F;
/*  922 */       if (position.getWidthType() == CategoryLabelWidthType.CATEGORY) {
/*  923 */         l = (float)calculateCategorySize(categories.size(), dataArea, edge);
/*  927 */       } else if (RectangleEdge.isLeftOrRight(edge)) {
/*  928 */         l = (float)dataArea.getWidth();
/*      */       } else {
/*  931 */         l = (float)dataArea.getHeight();
/*      */       } 
/*  934 */       int categoryIndex = 0;
/*  935 */       Iterator iterator = categories.iterator();
/*  936 */       while (iterator.hasNext()) {
/*  937 */         Comparable category = iterator.next();
/*  938 */         TextBlock label = createLabel(category, l * r, edge, g2);
/*  939 */         if (edge == RectangleEdge.TOP || edge == RectangleEdge.BOTTOM) {
/*  940 */           max = Math.max(max, calculateTextBlockHeight(label, position, g2));
/*  943 */         } else if (edge == RectangleEdge.LEFT || edge == RectangleEdge.RIGHT) {
/*  945 */           max = Math.max(max, calculateTextBlockWidth(label, position, g2));
/*      */         } 
/*  948 */         Tick tick = new CategoryTick(category, label, position.getLabelAnchor(), position.getRotationAnchor(), position.getAngle());
/*  951 */         ticks.add(tick);
/*  952 */         categoryIndex++;
/*      */       } 
/*      */     } 
/*  955 */     state.setMax(max);
/*  956 */     return ticks;
/*      */   }
/*      */   
/*      */   protected TextBlock createLabel(Comparable category, float width, RectangleEdge edge, Graphics2D g2) {
/*  972 */     TextBlock label = TextUtilities.createTextBlock(category.toString(), getTickLabelFont(category), getTickLabelPaint(category), width, this.maximumCategoryLabelLines, (TextMeasurer)new G2TextMeasurer(g2));
/*  976 */     return label;
/*      */   }
/*      */   
/*      */   protected double calculateTextBlockWidth(TextBlock block, CategoryLabelPosition position, Graphics2D g2) {
/*  992 */     RectangleInsets insets = getTickLabelInsets();
/*  993 */     Size2D size = block.calculateDimensions(g2);
/*  994 */     Rectangle2D box = new Rectangle2D.Double(0.0D, 0.0D, size.getWidth(), size.getHeight());
/*  997 */     Shape rotatedBox = ShapeUtilities.rotateShape(box, position.getAngle(), 0.0F, 0.0F);
/* 1000 */     double w = rotatedBox.getBounds2D().getWidth() + insets.getTop() + insets.getBottom();
/* 1002 */     return w;
/*      */   }
/*      */   
/*      */   protected double calculateTextBlockHeight(TextBlock block, CategoryLabelPosition position, Graphics2D g2) {
/* 1019 */     RectangleInsets insets = getTickLabelInsets();
/* 1020 */     Size2D size = block.calculateDimensions(g2);
/* 1021 */     Rectangle2D box = new Rectangle2D.Double(0.0D, 0.0D, size.getWidth(), size.getHeight());
/* 1024 */     Shape rotatedBox = ShapeUtilities.rotateShape(box, position.getAngle(), 0.0F, 0.0F);
/* 1027 */     double h = rotatedBox.getBounds2D().getHeight() + insets.getTop() + insets.getBottom();
/* 1029 */     return h;
/*      */   }
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/* 1042 */     CategoryAxis clone = (CategoryAxis)super.clone();
/* 1043 */     clone.tickLabelFontMap = new HashMap(this.tickLabelFontMap);
/* 1044 */     clone.tickLabelPaintMap = new HashMap(this.tickLabelPaintMap);
/* 1045 */     clone.categoryLabelToolTips = new HashMap(this.categoryLabelToolTips);
/* 1046 */     return clone;
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/* 1057 */     if (obj == this)
/* 1058 */       return true; 
/* 1060 */     if (!(obj instanceof CategoryAxis))
/* 1061 */       return false; 
/* 1063 */     if (!super.equals(obj))
/* 1064 */       return false; 
/* 1066 */     CategoryAxis that = (CategoryAxis)obj;
/* 1067 */     if (that.lowerMargin != this.lowerMargin)
/* 1068 */       return false; 
/* 1070 */     if (that.upperMargin != this.upperMargin)
/* 1071 */       return false; 
/* 1073 */     if (that.categoryMargin != this.categoryMargin)
/* 1074 */       return false; 
/* 1076 */     if (that.maximumCategoryLabelWidthRatio != this.maximumCategoryLabelWidthRatio)
/* 1078 */       return false; 
/* 1080 */     if (that.categoryLabelPositionOffset != this.categoryLabelPositionOffset)
/* 1082 */       return false; 
/* 1084 */     if (!ObjectUtilities.equal(that.categoryLabelPositions, this.categoryLabelPositions))
/* 1086 */       return false; 
/* 1088 */     if (!ObjectUtilities.equal(that.categoryLabelToolTips, this.categoryLabelToolTips))
/* 1090 */       return false; 
/* 1092 */     if (!ObjectUtilities.equal(this.tickLabelFontMap, that.tickLabelFontMap))
/* 1094 */       return false; 
/* 1096 */     if (!equalPaintMaps(this.tickLabelPaintMap, that.tickLabelPaintMap))
/* 1097 */       return false; 
/* 1099 */     return true;
/*      */   }
/*      */   
/*      */   public int hashCode() {
/* 1108 */     if (getLabel() != null)
/* 1109 */       return getLabel().hashCode(); 
/* 1112 */     return 0;
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 1124 */     stream.defaultWriteObject();
/* 1125 */     writePaintMap(this.tickLabelPaintMap, stream);
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 1138 */     stream.defaultReadObject();
/* 1139 */     this.tickLabelPaintMap = readPaintMap(stream);
/*      */   }
/*      */   
/*      */   private Map readPaintMap(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 1157 */     boolean isNull = in.readBoolean();
/* 1158 */     if (isNull)
/* 1159 */       return null; 
/* 1161 */     Map result = new HashMap();
/* 1162 */     int count = in.readInt();
/* 1163 */     for (int i = 0; i < count; i++) {
/* 1164 */       Comparable category = (Comparable)in.readObject();
/* 1165 */       Paint paint = SerialUtilities.readPaint(in);
/* 1166 */       result.put(category, paint);
/*      */     } 
/* 1168 */     return result;
/*      */   }
/*      */   
/*      */   private void writePaintMap(Map map, ObjectOutputStream out) throws IOException {
/* 1184 */     if (map == null) {
/* 1185 */       out.writeBoolean(true);
/*      */     } else {
/* 1188 */       out.writeBoolean(false);
/* 1189 */       Set keys = map.keySet();
/* 1190 */       int count = keys.size();
/* 1191 */       out.writeInt(count);
/* 1192 */       Iterator iterator = keys.iterator();
/* 1193 */       while (iterator.hasNext()) {
/* 1194 */         Comparable key = iterator.next();
/* 1195 */         out.writeObject(key);
/* 1196 */         SerialUtilities.writePaint((Paint)map.get(key), out);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private boolean equalPaintMaps(Map map1, Map map2) {
/* 1211 */     if (map1.size() != map2.size())
/* 1212 */       return false; 
/* 1214 */     Set keys = map1.keySet();
/* 1215 */     Iterator iterator = keys.iterator();
/* 1216 */     while (iterator.hasNext()) {
/* 1217 */       Comparable key = iterator.next();
/* 1218 */       Paint p1 = (Paint)map1.get(key);
/* 1219 */       Paint p2 = (Paint)map2.get(key);
/* 1220 */       if (!PaintUtilities.equal(p1, p2))
/* 1221 */         return false; 
/*      */     } 
/* 1224 */     return true;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\CategoryAxis.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */