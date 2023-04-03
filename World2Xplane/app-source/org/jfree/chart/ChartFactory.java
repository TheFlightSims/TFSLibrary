/*      */ package org.jfree.chart;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.Font;
/*      */ import java.text.DateFormat;
/*      */ import java.text.NumberFormat;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import org.jfree.chart.axis.CategoryAxis;
/*      */ import org.jfree.chart.axis.CategoryAxis3D;
/*      */ import org.jfree.chart.axis.DateAxis;
/*      */ import org.jfree.chart.axis.NumberAxis;
/*      */ import org.jfree.chart.axis.NumberAxis3D;
/*      */ import org.jfree.chart.axis.Timeline;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.labels.CategoryToolTipGenerator;
/*      */ import org.jfree.chart.labels.HighLowItemLabelGenerator;
/*      */ import org.jfree.chart.labels.IntervalCategoryToolTipGenerator;
/*      */ import org.jfree.chart.labels.ItemLabelAnchor;
/*      */ import org.jfree.chart.labels.ItemLabelPosition;
/*      */ import org.jfree.chart.labels.PieSectionLabelGenerator;
/*      */ import org.jfree.chart.labels.PieToolTipGenerator;
/*      */ import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
/*      */ import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
/*      */ import org.jfree.chart.labels.StandardPieToolTipGenerator;
/*      */ import org.jfree.chart.labels.StandardXYToolTipGenerator;
/*      */ import org.jfree.chart.labels.StandardXYZToolTipGenerator;
/*      */ import org.jfree.chart.labels.XYToolTipGenerator;
/*      */ import org.jfree.chart.plot.CategoryPlot;
/*      */ import org.jfree.chart.plot.Marker;
/*      */ import org.jfree.chart.plot.MultiplePiePlot;
/*      */ import org.jfree.chart.plot.PiePlot;
/*      */ import org.jfree.chart.plot.PiePlot3D;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.chart.plot.PlotOrientation;
/*      */ import org.jfree.chart.plot.PolarPlot;
/*      */ import org.jfree.chart.plot.RingPlot;
/*      */ import org.jfree.chart.plot.ValueMarker;
/*      */ import org.jfree.chart.plot.WaferMapPlot;
/*      */ import org.jfree.chart.plot.XYPlot;
/*      */ import org.jfree.chart.renderer.DefaultPolarItemRenderer;
/*      */ import org.jfree.chart.renderer.PolarItemRenderer;
/*      */ import org.jfree.chart.renderer.WaferMapRenderer;
/*      */ import org.jfree.chart.renderer.category.AreaRenderer;
/*      */ import org.jfree.chart.renderer.category.BarRenderer;
/*      */ import org.jfree.chart.renderer.category.BarRenderer3D;
/*      */ import org.jfree.chart.renderer.category.CategoryItemRenderer;
/*      */ import org.jfree.chart.renderer.category.GanttRenderer;
/*      */ import org.jfree.chart.renderer.category.LineAndShapeRenderer;
/*      */ import org.jfree.chart.renderer.category.LineRenderer3D;
/*      */ import org.jfree.chart.renderer.category.StackedAreaRenderer;
/*      */ import org.jfree.chart.renderer.category.StackedBarRenderer;
/*      */ import org.jfree.chart.renderer.category.StackedBarRenderer3D;
/*      */ import org.jfree.chart.renderer.category.WaterfallBarRenderer;
/*      */ import org.jfree.chart.renderer.xy.CandlestickRenderer;
/*      */ import org.jfree.chart.renderer.xy.HighLowRenderer;
/*      */ import org.jfree.chart.renderer.xy.StackedXYAreaRenderer;
/*      */ import org.jfree.chart.renderer.xy.WindItemRenderer;
/*      */ import org.jfree.chart.renderer.xy.XYAreaRenderer;
/*      */ import org.jfree.chart.renderer.xy.XYBarRenderer;
/*      */ import org.jfree.chart.renderer.xy.XYBoxAndWhiskerRenderer;
/*      */ import org.jfree.chart.renderer.xy.XYBubbleRenderer;
/*      */ import org.jfree.chart.renderer.xy.XYItemRenderer;
/*      */ import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
/*      */ import org.jfree.chart.renderer.xy.XYStepAreaRenderer;
/*      */ import org.jfree.chart.renderer.xy.XYStepRenderer;
/*      */ import org.jfree.chart.title.TextTitle;
/*      */ import org.jfree.chart.title.Title;
/*      */ import org.jfree.chart.urls.CategoryURLGenerator;
/*      */ import org.jfree.chart.urls.PieURLGenerator;
/*      */ import org.jfree.chart.urls.StandardCategoryURLGenerator;
/*      */ import org.jfree.chart.urls.StandardPieURLGenerator;
/*      */ import org.jfree.chart.urls.StandardXYURLGenerator;
/*      */ import org.jfree.chart.urls.StandardXYZURLGenerator;
/*      */ import org.jfree.chart.urls.XYURLGenerator;
/*      */ import org.jfree.data.category.CategoryDataset;
/*      */ import org.jfree.data.category.IntervalCategoryDataset;
/*      */ import org.jfree.data.general.DefaultPieDataset;
/*      */ import org.jfree.data.general.PieDataset;
/*      */ import org.jfree.data.general.WaferMapDataset;
/*      */ import org.jfree.data.statistics.BoxAndWhiskerXYDataset;
/*      */ import org.jfree.data.xy.IntervalXYDataset;
/*      */ import org.jfree.data.xy.OHLCDataset;
/*      */ import org.jfree.data.xy.TableXYDataset;
/*      */ import org.jfree.data.xy.WindDataset;
/*      */ import org.jfree.data.xy.XYDataset;
/*      */ import org.jfree.data.xy.XYZDataset;
/*      */ import org.jfree.ui.Layer;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.ui.TextAnchor;
/*      */ import org.jfree.util.SortOrder;
/*      */ import org.jfree.util.TableOrder;
/*      */ 
/*      */ public abstract class ChartFactory {
/*      */   public static JFreeChart createPieChart(String title, PieDataset dataset, boolean legend, boolean tooltips, boolean urls) {
/*  227 */     PiePlot plot = new PiePlot(dataset);
/*  228 */     plot.setLabelGenerator((PieSectionLabelGenerator)new StandardPieSectionLabelGenerator());
/*  229 */     plot.setInsets(new RectangleInsets(0.0D, 5.0D, 5.0D, 5.0D));
/*  230 */     if (tooltips)
/*  231 */       plot.setToolTipGenerator((PieToolTipGenerator)new StandardPieToolTipGenerator("{0} = {1}")); 
/*  237 */     if (urls)
/*  238 */       plot.setURLGenerator((PieURLGenerator)new StandardPieURLGenerator()); 
/*  240 */     return new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/*      */   }
/*      */   
/*      */   public static JFreeChart createPieChart(String title, PieDataset dataset, PieDataset previousDataset, int percentDiffForMaxScale, boolean greenForIncrease, boolean legend, boolean tooltips, boolean urls, boolean subTitle, boolean showDifference) {
/*  295 */     PiePlot plot = new PiePlot(dataset);
/*  296 */     plot.setLabelGenerator((PieSectionLabelGenerator)new StandardPieSectionLabelGenerator());
/*  297 */     plot.setInsets(new RectangleInsets(0.0D, 5.0D, 5.0D, 5.0D));
/*  299 */     if (tooltips)
/*  300 */       plot.setToolTipGenerator((PieToolTipGenerator)new StandardPieToolTipGenerator("{0} = {1}")); 
/*  306 */     if (urls)
/*  307 */       plot.setURLGenerator((PieURLGenerator)new StandardPieURLGenerator()); 
/*  310 */     List keys = dataset.getKeys();
/*  311 */     DefaultPieDataset series = null;
/*  312 */     if (showDifference)
/*  313 */       series = new DefaultPieDataset(); 
/*  316 */     double colorPerPercent = 255.0D / percentDiffForMaxScale;
/*  317 */     for (Iterator it = keys.iterator(); it.hasNext(); ) {
/*  318 */       Comparable key = it.next();
/*  319 */       Number newValue = dataset.getValue(key);
/*  320 */       Number oldValue = previousDataset.getValue(key);
/*  321 */       int section = dataset.getIndex(key);
/*  323 */       if (oldValue == null) {
/*  324 */         if (greenForIncrease) {
/*  325 */           plot.setSectionPaint(section, Color.green);
/*      */         } else {
/*  328 */           plot.setSectionPaint(section, Color.red);
/*      */         } 
/*  330 */         if (showDifference)
/*  331 */           series.setValue(key + " (+100%)", newValue); 
/*      */         continue;
/*      */       } 
/*  335 */       double percentChange = (newValue.doubleValue() / oldValue.doubleValue() - 1.0D) * 100.0D;
/*  337 */       double shade = (Math.abs(percentChange) >= percentDiffForMaxScale) ? 255.0D : (Math.abs(percentChange) * colorPerPercent);
/*  340 */       if ((greenForIncrease && newValue.doubleValue() > oldValue.doubleValue()) || (!greenForIncrease && newValue.doubleValue() < oldValue.doubleValue())) {
/*  344 */         plot.setSectionPaint(section, new Color(0, (int)shade, 0));
/*      */       } else {
/*  347 */         plot.setSectionPaint(section, new Color((int)shade, 0, 0));
/*      */       } 
/*  349 */       if (showDifference)
/*  350 */         series.setValue(key + " (" + ((percentChange >= 0.0D) ? "+" : "") + NumberFormat.getPercentInstance().format(percentChange / 100.0D) + ")", newValue); 
/*      */     } 
/*  359 */     if (showDifference)
/*  360 */       plot.setDataset((PieDataset)series); 
/*  363 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/*  367 */     if (subTitle) {
/*  368 */       TextTitle subtitle = null;
/*  369 */       subtitle = new TextTitle("Bright " + (greenForIncrease ? "red" : "green") + "=change >=-" + percentDiffForMaxScale + "%, Bright " + (!greenForIncrease ? "red" : "green") + "=change >=+" + percentDiffForMaxScale + "%", new Font("SansSerif", 0, 10));
/*  376 */       chart.addSubtitle((Title)subtitle);
/*      */     } 
/*  379 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createRingChart(String title, PieDataset dataset, boolean legend, boolean tooltips, boolean urls) {
/*  402 */     RingPlot plot = new RingPlot(dataset);
/*  403 */     plot.setLabelGenerator((PieSectionLabelGenerator)new StandardPieSectionLabelGenerator());
/*  404 */     plot.setInsets(new RectangleInsets(0.0D, 5.0D, 5.0D, 5.0D));
/*  405 */     if (tooltips)
/*  406 */       plot.setToolTipGenerator((PieToolTipGenerator)new StandardPieToolTipGenerator("{0} = {1}")); 
/*  412 */     if (urls)
/*  413 */       plot.setURLGenerator((PieURLGenerator)new StandardPieURLGenerator()); 
/*  415 */     return new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/*      */   }
/*      */   
/*      */   public static JFreeChart createMultiplePieChart(String title, CategoryDataset dataset, TableOrder order, boolean legend, boolean tooltips, boolean urls) {
/*  443 */     if (order == null)
/*  444 */       throw new IllegalArgumentException("Null 'order' argument."); 
/*  446 */     MultiplePiePlot plot = new MultiplePiePlot(dataset);
/*  447 */     plot.setDataExtractOrder(order);
/*  448 */     plot.setBackgroundPaint(null);
/*  449 */     plot.setOutlineStroke(null);
/*  451 */     if (tooltips) {
/*  452 */       StandardPieToolTipGenerator standardPieToolTipGenerator = new StandardPieToolTipGenerator();
/*  454 */       PiePlot pp = (PiePlot)plot.getPieChart().getPlot();
/*  455 */       pp.setToolTipGenerator((PieToolTipGenerator)standardPieToolTipGenerator);
/*      */     } 
/*  458 */     if (urls) {
/*  459 */       StandardPieURLGenerator standardPieURLGenerator = new StandardPieURLGenerator();
/*  460 */       PiePlot pp = (PiePlot)plot.getPieChart().getPlot();
/*  461 */       pp.setURLGenerator((PieURLGenerator)standardPieURLGenerator);
/*      */     } 
/*  464 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/*  468 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createPieChart3D(String title, PieDataset dataset, boolean legend, boolean tooltips, boolean urls) {
/*  491 */     PiePlot3D plot = new PiePlot3D(dataset);
/*  492 */     plot.setInsets(new RectangleInsets(0.0D, 5.0D, 5.0D, 5.0D));
/*  493 */     if (tooltips)
/*  494 */       plot.setToolTipGenerator((PieToolTipGenerator)new StandardPieToolTipGenerator()); 
/*  496 */     if (urls)
/*  497 */       plot.setURLGenerator((PieURLGenerator)new StandardPieURLGenerator()); 
/*  499 */     return new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/*      */   }
/*      */   
/*      */   public static JFreeChart createMultiplePieChart3D(String title, CategoryDataset dataset, TableOrder order, boolean legend, boolean tooltips, boolean urls) {
/*  527 */     if (order == null)
/*  528 */       throw new IllegalArgumentException("Null 'order' argument."); 
/*  530 */     MultiplePiePlot plot = new MultiplePiePlot(dataset);
/*  531 */     plot.setDataExtractOrder(order);
/*  532 */     plot.setBackgroundPaint(null);
/*  533 */     plot.setOutlineStroke(null);
/*  535 */     JFreeChart pieChart = new JFreeChart((Plot)new PiePlot3D(null));
/*  536 */     TextTitle seriesTitle = new TextTitle("Series Title", new Font("SansSerif", 1, 12));
/*  539 */     seriesTitle.setPosition(RectangleEdge.BOTTOM);
/*  540 */     pieChart.setTitle(seriesTitle);
/*  541 */     pieChart.removeLegend();
/*  542 */     pieChart.setBackgroundPaint(null);
/*  543 */     plot.setPieChart(pieChart);
/*  545 */     if (tooltips) {
/*  546 */       StandardPieToolTipGenerator standardPieToolTipGenerator = new StandardPieToolTipGenerator();
/*  548 */       PiePlot pp = (PiePlot)plot.getPieChart().getPlot();
/*  549 */       pp.setToolTipGenerator((PieToolTipGenerator)standardPieToolTipGenerator);
/*      */     } 
/*  552 */     if (urls) {
/*  553 */       StandardPieURLGenerator standardPieURLGenerator = new StandardPieURLGenerator();
/*  554 */       PiePlot pp = (PiePlot)plot.getPieChart().getPlot();
/*  555 */       pp.setURLGenerator((PieURLGenerator)standardPieURLGenerator);
/*      */     } 
/*  558 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/*  562 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createBarChart(String title, String categoryAxisLabel, String valueAxisLabel, CategoryDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
/*  595 */     if (orientation == null)
/*  596 */       throw new IllegalArgumentException("Null 'orientation' argument."); 
/*  598 */     CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
/*  599 */     NumberAxis numberAxis = new NumberAxis(valueAxisLabel);
/*  601 */     BarRenderer renderer = new BarRenderer();
/*  602 */     if (orientation == PlotOrientation.HORIZONTAL) {
/*  603 */       ItemLabelPosition position1 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE3, TextAnchor.CENTER_LEFT);
/*  606 */       renderer.setPositiveItemLabelPosition(position1);
/*  607 */       ItemLabelPosition position2 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE9, TextAnchor.CENTER_RIGHT);
/*  610 */       renderer.setNegativeItemLabelPosition(position2);
/*  612 */     } else if (orientation == PlotOrientation.VERTICAL) {
/*  613 */       ItemLabelPosition position1 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER);
/*  616 */       renderer.setPositiveItemLabelPosition(position1);
/*  617 */       ItemLabelPosition position2 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_CENTER);
/*  620 */       renderer.setNegativeItemLabelPosition(position2);
/*      */     } 
/*  622 */     if (tooltips)
/*  623 */       renderer.setBaseToolTipGenerator((CategoryToolTipGenerator)new StandardCategoryToolTipGenerator()); 
/*  627 */     if (urls)
/*  628 */       renderer.setBaseItemURLGenerator((CategoryURLGenerator)new StandardCategoryURLGenerator()); 
/*  633 */     CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, (ValueAxis)numberAxis, (CategoryItemRenderer)renderer);
/*  636 */     plot.setOrientation(orientation);
/*  637 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/*  641 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createStackedBarChart(String title, String domainAxisLabel, String rangeAxisLabel, CategoryDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
/*  675 */     if (orientation == null)
/*  676 */       throw new IllegalArgumentException("Null 'orientation' argument."); 
/*  679 */     CategoryAxis categoryAxis = new CategoryAxis(domainAxisLabel);
/*  680 */     NumberAxis numberAxis = new NumberAxis(rangeAxisLabel);
/*  682 */     StackedBarRenderer renderer = new StackedBarRenderer();
/*  683 */     if (tooltips)
/*  684 */       renderer.setBaseToolTipGenerator((CategoryToolTipGenerator)new StandardCategoryToolTipGenerator()); 
/*  688 */     if (urls)
/*  689 */       renderer.setBaseItemURLGenerator((CategoryURLGenerator)new StandardCategoryURLGenerator()); 
/*  694 */     CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, (ValueAxis)numberAxis, (CategoryItemRenderer)renderer);
/*  697 */     plot.setOrientation(orientation);
/*  698 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/*  702 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createBarChart3D(String title, String categoryAxisLabel, String valueAxisLabel, CategoryDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
/*  735 */     if (orientation == null)
/*  736 */       throw new IllegalArgumentException("Null 'orientation' argument."); 
/*  738 */     CategoryAxis3D categoryAxis3D = new CategoryAxis3D(categoryAxisLabel);
/*  739 */     NumberAxis3D numberAxis3D = new NumberAxis3D(valueAxisLabel);
/*  741 */     BarRenderer3D renderer = new BarRenderer3D();
/*  742 */     if (tooltips)
/*  743 */       renderer.setBaseToolTipGenerator((CategoryToolTipGenerator)new StandardCategoryToolTipGenerator()); 
/*  747 */     if (urls)
/*  748 */       renderer.setBaseItemURLGenerator((CategoryURLGenerator)new StandardCategoryURLGenerator()); 
/*  753 */     CategoryPlot plot = new CategoryPlot(dataset, (CategoryAxis)categoryAxis3D, (ValueAxis)numberAxis3D, (CategoryItemRenderer)renderer);
/*  756 */     plot.setOrientation(orientation);
/*  757 */     if (orientation == PlotOrientation.HORIZONTAL) {
/*  760 */       plot.setRowRenderingOrder(SortOrder.DESCENDING);
/*  761 */       plot.setColumnRenderingOrder(SortOrder.DESCENDING);
/*      */     } 
/*  763 */     plot.setForegroundAlpha(0.75F);
/*  765 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/*  769 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createStackedBarChart3D(String title, String categoryAxisLabel, String valueAxisLabel, CategoryDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
/*  803 */     if (orientation == null)
/*  804 */       throw new IllegalArgumentException("Null 'orientation' argument."); 
/*  806 */     CategoryAxis3D categoryAxis3D = new CategoryAxis3D(categoryAxisLabel);
/*  807 */     NumberAxis3D numberAxis3D = new NumberAxis3D(valueAxisLabel);
/*  810 */     StackedBarRenderer3D stackedBarRenderer3D = new StackedBarRenderer3D();
/*  811 */     if (tooltips)
/*  812 */       stackedBarRenderer3D.setBaseToolTipGenerator((CategoryToolTipGenerator)new StandardCategoryToolTipGenerator()); 
/*  816 */     if (urls)
/*  817 */       stackedBarRenderer3D.setBaseItemURLGenerator((CategoryURLGenerator)new StandardCategoryURLGenerator()); 
/*  823 */     CategoryPlot plot = new CategoryPlot(dataset, (CategoryAxis)categoryAxis3D, (ValueAxis)numberAxis3D, (CategoryItemRenderer)stackedBarRenderer3D);
/*  826 */     plot.setOrientation(orientation);
/*  827 */     if (orientation == PlotOrientation.HORIZONTAL)
/*  830 */       plot.setColumnRenderingOrder(SortOrder.DESCENDING); 
/*  834 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/*  838 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createAreaChart(String title, String categoryAxisLabel, String valueAxisLabel, CategoryDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
/*  871 */     if (orientation == null)
/*  872 */       throw new IllegalArgumentException("Null 'orientation' argument."); 
/*  874 */     CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
/*  875 */     categoryAxis.setCategoryMargin(0.0D);
/*  877 */     NumberAxis numberAxis = new NumberAxis(valueAxisLabel);
/*  879 */     AreaRenderer renderer = new AreaRenderer();
/*  880 */     if (tooltips)
/*  881 */       renderer.setBaseToolTipGenerator((CategoryToolTipGenerator)new StandardCategoryToolTipGenerator()); 
/*  885 */     if (urls)
/*  886 */       renderer.setBaseItemURLGenerator((CategoryURLGenerator)new StandardCategoryURLGenerator()); 
/*  891 */     CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, (ValueAxis)numberAxis, (CategoryItemRenderer)renderer);
/*  894 */     plot.setOrientation(orientation);
/*  895 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/*  899 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createStackedAreaChart(String title, String categoryAxisLabel, String valueAxisLabel, CategoryDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
/*  933 */     if (orientation == null)
/*  934 */       throw new IllegalArgumentException("Null 'orientation' argument."); 
/*  936 */     CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
/*  937 */     NumberAxis numberAxis = new NumberAxis(valueAxisLabel);
/*  939 */     StackedAreaRenderer renderer = new StackedAreaRenderer();
/*  940 */     if (tooltips)
/*  941 */       renderer.setBaseToolTipGenerator((CategoryToolTipGenerator)new StandardCategoryToolTipGenerator()); 
/*  945 */     if (urls)
/*  946 */       renderer.setBaseItemURLGenerator((CategoryURLGenerator)new StandardCategoryURLGenerator()); 
/*  951 */     CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, (ValueAxis)numberAxis, (CategoryItemRenderer)renderer);
/*  954 */     plot.setOrientation(orientation);
/*  955 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/*  959 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createLineChart(String title, String categoryAxisLabel, String valueAxisLabel, CategoryDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
/*  992 */     if (orientation == null)
/*  993 */       throw new IllegalArgumentException("Null 'orientation' argument."); 
/*  995 */     CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
/*  996 */     NumberAxis numberAxis = new NumberAxis(valueAxisLabel);
/*  998 */     LineAndShapeRenderer renderer = new LineAndShapeRenderer(true, false);
/*  999 */     if (tooltips)
/* 1000 */       renderer.setBaseToolTipGenerator((CategoryToolTipGenerator)new StandardCategoryToolTipGenerator()); 
/* 1004 */     if (urls)
/* 1005 */       renderer.setBaseItemURLGenerator((CategoryURLGenerator)new StandardCategoryURLGenerator()); 
/* 1009 */     CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, (ValueAxis)numberAxis, (CategoryItemRenderer)renderer);
/* 1012 */     plot.setOrientation(orientation);
/* 1013 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/* 1017 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createLineChart3D(String title, String categoryAxisLabel, String valueAxisLabel, CategoryDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
/* 1050 */     if (orientation == null)
/* 1051 */       throw new IllegalArgumentException("Null 'orientation' argument."); 
/* 1053 */     CategoryAxis3D categoryAxis3D = new CategoryAxis3D(categoryAxisLabel);
/* 1054 */     NumberAxis3D numberAxis3D = new NumberAxis3D(valueAxisLabel);
/* 1056 */     LineRenderer3D renderer = new LineRenderer3D();
/* 1057 */     if (tooltips)
/* 1058 */       renderer.setBaseToolTipGenerator((CategoryToolTipGenerator)new StandardCategoryToolTipGenerator()); 
/* 1062 */     if (urls)
/* 1063 */       renderer.setBaseItemURLGenerator((CategoryURLGenerator)new StandardCategoryURLGenerator()); 
/* 1067 */     CategoryPlot plot = new CategoryPlot(dataset, (CategoryAxis)categoryAxis3D, (ValueAxis)numberAxis3D, (CategoryItemRenderer)renderer);
/* 1070 */     plot.setOrientation(orientation);
/* 1071 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/* 1075 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createGanttChart(String title, String categoryAxisLabel, String dateAxisLabel, IntervalCategoryDataset dataset, boolean legend, boolean tooltips, boolean urls) {
/* 1106 */     CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
/* 1107 */     DateAxis dateAxis = new DateAxis(dateAxisLabel);
/* 1109 */     GanttRenderer ganttRenderer = new GanttRenderer();
/* 1110 */     if (tooltips)
/* 1111 */       ganttRenderer.setBaseToolTipGenerator((CategoryToolTipGenerator)new IntervalCategoryToolTipGenerator("{3} - {4}", DateFormat.getDateInstance())); 
/* 1117 */     if (urls)
/* 1118 */       ganttRenderer.setBaseItemURLGenerator((CategoryURLGenerator)new StandardCategoryURLGenerator()); 
/* 1123 */     CategoryPlot plot = new CategoryPlot((CategoryDataset)dataset, categoryAxis, (ValueAxis)dateAxis, (CategoryItemRenderer)ganttRenderer);
/* 1126 */     plot.setOrientation(PlotOrientation.HORIZONTAL);
/* 1127 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/* 1131 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createWaterfallChart(String title, String categoryAxisLabel, String valueAxisLabel, CategoryDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
/* 1164 */     if (orientation == null)
/* 1165 */       throw new IllegalArgumentException("Null 'orientation' argument."); 
/* 1167 */     CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
/* 1168 */     categoryAxis.setCategoryMargin(0.0D);
/* 1170 */     NumberAxis numberAxis = new NumberAxis(valueAxisLabel);
/* 1172 */     WaterfallBarRenderer renderer = new WaterfallBarRenderer();
/* 1173 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 1174 */       ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, 1.5707963267948966D);
/* 1178 */       renderer.setPositiveItemLabelPosition(position);
/* 1179 */       renderer.setNegativeItemLabelPosition(position);
/* 1181 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 1182 */       ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, 0.0D);
/* 1186 */       renderer.setPositiveItemLabelPosition(position);
/* 1187 */       renderer.setNegativeItemLabelPosition(position);
/*      */     } 
/* 1189 */     if (tooltips) {
/* 1190 */       StandardCategoryToolTipGenerator generator = new StandardCategoryToolTipGenerator();
/* 1192 */       renderer.setBaseToolTipGenerator((CategoryToolTipGenerator)generator);
/*      */     } 
/* 1194 */     if (urls)
/* 1195 */       renderer.setBaseItemURLGenerator((CategoryURLGenerator)new StandardCategoryURLGenerator()); 
/* 1200 */     CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, (ValueAxis)numberAxis, (CategoryItemRenderer)renderer);
/* 1203 */     plot.clearRangeMarkers();
/* 1204 */     ValueMarker valueMarker = new ValueMarker(0.0D);
/* 1205 */     valueMarker.setPaint(Color.black);
/* 1206 */     plot.addRangeMarker((Marker)valueMarker, Layer.FOREGROUND);
/* 1207 */     plot.setOrientation(orientation);
/* 1208 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/* 1212 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createPolarChart(String title, XYDataset dataset, boolean legend, boolean tooltips, boolean urls) {
/* 1236 */     PolarPlot plot = new PolarPlot();
/* 1237 */     plot.setDataset(dataset);
/* 1238 */     NumberAxis rangeAxis = new NumberAxis();
/* 1239 */     rangeAxis.setAxisLineVisible(false);
/* 1240 */     rangeAxis.setTickMarksVisible(false);
/* 1241 */     rangeAxis.setTickLabelInsets(new RectangleInsets(0.0D, 0.0D, 0.0D, 0.0D));
/* 1242 */     plot.setAxis((ValueAxis)rangeAxis);
/* 1243 */     plot.setRenderer((PolarItemRenderer)new DefaultPolarItemRenderer());
/* 1244 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/* 1247 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createScatterPlot(String title, String xAxisLabel, String yAxisLabel, XYDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
/*      */     StandardXYToolTipGenerator standardXYToolTipGenerator;
/*      */     StandardXYURLGenerator standardXYURLGenerator;
/* 1280 */     if (orientation == null)
/* 1281 */       throw new IllegalArgumentException("Null 'orientation' argument."); 
/* 1283 */     NumberAxis xAxis = new NumberAxis(xAxisLabel);
/* 1284 */     xAxis.setAutoRangeIncludesZero(false);
/* 1285 */     NumberAxis yAxis = new NumberAxis(yAxisLabel);
/* 1286 */     yAxis.setAutoRangeIncludesZero(false);
/* 1288 */     XYPlot plot = new XYPlot(dataset, (ValueAxis)xAxis, (ValueAxis)yAxis, null);
/* 1290 */     XYToolTipGenerator toolTipGenerator = null;
/* 1291 */     if (tooltips)
/* 1292 */       standardXYToolTipGenerator = new StandardXYToolTipGenerator(); 
/* 1295 */     XYURLGenerator urlGenerator = null;
/* 1296 */     if (urls)
/* 1297 */       standardXYURLGenerator = new StandardXYURLGenerator(); 
/* 1299 */     XYLineAndShapeRenderer xYLineAndShapeRenderer = new XYLineAndShapeRenderer(false, true);
/* 1300 */     xYLineAndShapeRenderer.setBaseToolTipGenerator((XYToolTipGenerator)standardXYToolTipGenerator);
/* 1301 */     xYLineAndShapeRenderer.setURLGenerator((XYURLGenerator)standardXYURLGenerator);
/* 1302 */     plot.setRenderer((XYItemRenderer)xYLineAndShapeRenderer);
/* 1303 */     plot.setOrientation(orientation);
/* 1305 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/* 1309 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createXYBarChart(String title, String xAxisLabel, boolean dateAxis, String yAxisLabel, IntervalXYDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
/*      */     NumberAxis numberAxis1;
/* 1344 */     if (orientation == null)
/* 1345 */       throw new IllegalArgumentException("Null 'orientation' argument."); 
/* 1347 */     ValueAxis domainAxis = null;
/* 1348 */     if (dateAxis) {
/* 1349 */       DateAxis dateAxis1 = new DateAxis(xAxisLabel);
/*      */     } else {
/* 1352 */       NumberAxis axis = new NumberAxis(xAxisLabel);
/* 1353 */       axis.setAutoRangeIncludesZero(false);
/* 1354 */       numberAxis1 = axis;
/*      */     } 
/* 1356 */     NumberAxis numberAxis2 = new NumberAxis(yAxisLabel);
/* 1358 */     XYBarRenderer renderer = new XYBarRenderer();
/* 1359 */     if (tooltips)
/* 1360 */       renderer.setBaseToolTipGenerator((XYToolTipGenerator)new StandardXYToolTipGenerator()); 
/* 1362 */     if (urls)
/* 1363 */       renderer.setURLGenerator((XYURLGenerator)new StandardXYURLGenerator()); 
/* 1366 */     XYPlot plot = new XYPlot((XYDataset)dataset, (ValueAxis)numberAxis1, (ValueAxis)numberAxis2, (XYItemRenderer)renderer);
/* 1367 */     plot.setOrientation(orientation);
/* 1369 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/* 1373 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createXYAreaChart(String title, String xAxisLabel, String yAxisLabel, XYDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
/*      */     StandardXYToolTipGenerator standardXYToolTipGenerator;
/*      */     StandardXYURLGenerator standardXYURLGenerator;
/* 1406 */     if (orientation == null)
/* 1407 */       throw new IllegalArgumentException("Null 'orientation' argument."); 
/* 1409 */     NumberAxis xAxis = new NumberAxis(xAxisLabel);
/* 1410 */     xAxis.setAutoRangeIncludesZero(false);
/* 1411 */     NumberAxis yAxis = new NumberAxis(yAxisLabel);
/* 1412 */     XYPlot plot = new XYPlot(dataset, (ValueAxis)xAxis, (ValueAxis)yAxis, null);
/* 1413 */     plot.setOrientation(orientation);
/* 1414 */     plot.setForegroundAlpha(0.5F);
/* 1416 */     XYToolTipGenerator tipGenerator = null;
/* 1417 */     if (tooltips)
/* 1418 */       standardXYToolTipGenerator = new StandardXYToolTipGenerator(); 
/* 1421 */     XYURLGenerator urlGenerator = null;
/* 1422 */     if (urls)
/* 1423 */       standardXYURLGenerator = new StandardXYURLGenerator(); 
/* 1426 */     plot.setRenderer((XYItemRenderer)new XYAreaRenderer(4, (XYToolTipGenerator)standardXYToolTipGenerator, (XYURLGenerator)standardXYURLGenerator));
/* 1429 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/* 1433 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createStackedXYAreaChart(String title, String xAxisLabel, String yAxisLabel, TableXYDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
/*      */     StandardXYToolTipGenerator standardXYToolTipGenerator;
/*      */     StandardXYURLGenerator standardXYURLGenerator;
/* 1464 */     if (orientation == null)
/* 1465 */       throw new IllegalArgumentException("Null 'orientation' argument."); 
/* 1467 */     NumberAxis xAxis = new NumberAxis(xAxisLabel);
/* 1468 */     xAxis.setAutoRangeIncludesZero(false);
/* 1469 */     xAxis.setLowerMargin(0.0D);
/* 1470 */     xAxis.setUpperMargin(0.0D);
/* 1471 */     NumberAxis yAxis = new NumberAxis(yAxisLabel);
/* 1472 */     XYToolTipGenerator toolTipGenerator = null;
/* 1473 */     if (tooltips)
/* 1474 */       standardXYToolTipGenerator = new StandardXYToolTipGenerator(); 
/* 1477 */     XYURLGenerator urlGenerator = null;
/* 1478 */     if (urls)
/* 1479 */       standardXYURLGenerator = new StandardXYURLGenerator(); 
/* 1481 */     StackedXYAreaRenderer renderer = new StackedXYAreaRenderer(4, (XYToolTipGenerator)standardXYToolTipGenerator, (XYURLGenerator)standardXYURLGenerator);
/* 1484 */     renderer.setOutline(true);
/* 1485 */     XYPlot plot = new XYPlot((XYDataset)dataset, (ValueAxis)xAxis, (ValueAxis)yAxis, (XYItemRenderer)renderer);
/* 1486 */     plot.setOrientation(orientation);
/* 1488 */     plot.setRangeAxis((ValueAxis)yAxis);
/* 1490 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/* 1493 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createXYLineChart(String title, String xAxisLabel, String yAxisLabel, XYDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
/* 1522 */     if (orientation == null)
/* 1523 */       throw new IllegalArgumentException("Null 'orientation' argument."); 
/* 1525 */     NumberAxis xAxis = new NumberAxis(xAxisLabel);
/* 1526 */     xAxis.setAutoRangeIncludesZero(false);
/* 1527 */     NumberAxis yAxis = new NumberAxis(yAxisLabel);
/* 1528 */     XYLineAndShapeRenderer xYLineAndShapeRenderer = new XYLineAndShapeRenderer(true, false);
/* 1529 */     XYPlot plot = new XYPlot(dataset, (ValueAxis)xAxis, (ValueAxis)yAxis, (XYItemRenderer)xYLineAndShapeRenderer);
/* 1530 */     plot.setOrientation(orientation);
/* 1531 */     if (tooltips)
/* 1532 */       xYLineAndShapeRenderer.setBaseToolTipGenerator((XYToolTipGenerator)new StandardXYToolTipGenerator()); 
/* 1534 */     if (urls)
/* 1535 */       xYLineAndShapeRenderer.setURLGenerator((XYURLGenerator)new StandardXYURLGenerator()); 
/* 1538 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/* 1542 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createXYStepChart(String title, String xAxisLabel, String yAxisLabel, XYDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
/*      */     StandardXYToolTipGenerator standardXYToolTipGenerator;
/*      */     StandardXYURLGenerator standardXYURLGenerator;
/* 1570 */     if (orientation == null)
/* 1571 */       throw new IllegalArgumentException("Null 'orientation' argument."); 
/* 1573 */     DateAxis xAxis = new DateAxis(xAxisLabel);
/* 1574 */     NumberAxis yAxis = new NumberAxis(yAxisLabel);
/* 1575 */     yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
/* 1577 */     XYToolTipGenerator toolTipGenerator = null;
/* 1578 */     if (tooltips)
/* 1579 */       standardXYToolTipGenerator = new StandardXYToolTipGenerator(); 
/* 1582 */     XYURLGenerator urlGenerator = null;
/* 1583 */     if (urls)
/* 1584 */       standardXYURLGenerator = new StandardXYURLGenerator(); 
/* 1586 */     XYStepRenderer xYStepRenderer = new XYStepRenderer((XYToolTipGenerator)standardXYToolTipGenerator, (XYURLGenerator)standardXYURLGenerator);
/* 1589 */     XYPlot plot = new XYPlot(dataset, (ValueAxis)xAxis, (ValueAxis)yAxis, null);
/* 1590 */     plot.setRenderer((XYItemRenderer)xYStepRenderer);
/* 1591 */     plot.setOrientation(orientation);
/* 1592 */     plot.setDomainCrosshairVisible(false);
/* 1593 */     plot.setRangeCrosshairVisible(false);
/* 1594 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/* 1597 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createXYStepAreaChart(String title, String xAxisLabel, String yAxisLabel, XYDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
/*      */     StandardXYToolTipGenerator standardXYToolTipGenerator;
/*      */     StandardXYURLGenerator standardXYURLGenerator;
/* 1625 */     if (orientation == null)
/* 1626 */       throw new IllegalArgumentException("Null 'orientation' argument."); 
/* 1628 */     NumberAxis xAxis = new NumberAxis(xAxisLabel);
/* 1629 */     xAxis.setAutoRangeIncludesZero(false);
/* 1630 */     NumberAxis yAxis = new NumberAxis(yAxisLabel);
/* 1632 */     XYToolTipGenerator toolTipGenerator = null;
/* 1633 */     if (tooltips)
/* 1634 */       standardXYToolTipGenerator = new StandardXYToolTipGenerator(); 
/* 1637 */     XYURLGenerator urlGenerator = null;
/* 1638 */     if (urls)
/* 1639 */       standardXYURLGenerator = new StandardXYURLGenerator(); 
/* 1641 */     XYStepAreaRenderer xYStepAreaRenderer = new XYStepAreaRenderer(3, (XYToolTipGenerator)standardXYToolTipGenerator, (XYURLGenerator)standardXYURLGenerator);
/* 1645 */     XYPlot plot = new XYPlot(dataset, (ValueAxis)xAxis, (ValueAxis)yAxis, null);
/* 1646 */     plot.setRenderer((XYItemRenderer)xYStepAreaRenderer);
/* 1647 */     plot.setOrientation(orientation);
/* 1648 */     plot.setDomainCrosshairVisible(false);
/* 1649 */     plot.setRangeCrosshairVisible(false);
/* 1650 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/* 1653 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createTimeSeriesChart(String title, String timeAxisLabel, String valueAxisLabel, XYDataset dataset, boolean legend, boolean tooltips, boolean urls) {
/*      */     StandardXYToolTipGenerator standardXYToolTipGenerator;
/*      */     StandardXYURLGenerator standardXYURLGenerator;
/* 1685 */     DateAxis dateAxis = new DateAxis(timeAxisLabel);
/* 1686 */     dateAxis.setLowerMargin(0.02D);
/* 1687 */     dateAxis.setUpperMargin(0.02D);
/* 1688 */     NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
/* 1689 */     valueAxis.setAutoRangeIncludesZero(false);
/* 1690 */     XYPlot plot = new XYPlot(dataset, (ValueAxis)dateAxis, (ValueAxis)valueAxis, null);
/* 1692 */     XYToolTipGenerator toolTipGenerator = null;
/* 1693 */     if (tooltips)
/* 1694 */       standardXYToolTipGenerator = StandardXYToolTipGenerator.getTimeSeriesInstance(); 
/* 1698 */     XYURLGenerator urlGenerator = null;
/* 1699 */     if (urls)
/* 1700 */       standardXYURLGenerator = new StandardXYURLGenerator(); 
/* 1703 */     XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
/* 1705 */     renderer.setBaseToolTipGenerator((XYToolTipGenerator)standardXYToolTipGenerator);
/* 1706 */     renderer.setURLGenerator((XYURLGenerator)standardXYURLGenerator);
/* 1707 */     plot.setRenderer((XYItemRenderer)renderer);
/* 1709 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/* 1712 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createCandlestickChart(String title, String timeAxisLabel, String valueAxisLabel, OHLCDataset dataset, boolean legend) {
/* 1735 */     DateAxis dateAxis = new DateAxis(timeAxisLabel);
/* 1736 */     NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
/* 1737 */     XYPlot plot = new XYPlot((XYDataset)dataset, (ValueAxis)dateAxis, (ValueAxis)valueAxis, null);
/* 1738 */     plot.setRenderer((XYItemRenderer)new CandlestickRenderer());
/* 1739 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/* 1742 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createHighLowChart(String title, String timeAxisLabel, String valueAxisLabel, OHLCDataset dataset, boolean legend) {
/* 1765 */     DateAxis dateAxis = new DateAxis(timeAxisLabel);
/* 1766 */     NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
/* 1767 */     HighLowRenderer renderer = new HighLowRenderer();
/* 1768 */     renderer.setBaseToolTipGenerator((XYToolTipGenerator)new HighLowItemLabelGenerator());
/* 1769 */     XYPlot plot = new XYPlot((XYDataset)dataset, (ValueAxis)dateAxis, (ValueAxis)valueAxis, (XYItemRenderer)renderer);
/* 1770 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/* 1773 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createHighLowChart(String title, String timeAxisLabel, String valueAxisLabel, OHLCDataset dataset, Timeline timeline, boolean legend) {
/* 1802 */     DateAxis timeAxis = new DateAxis(timeAxisLabel);
/* 1803 */     timeAxis.setTimeline(timeline);
/* 1804 */     NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
/* 1805 */     HighLowRenderer renderer = new HighLowRenderer();
/* 1806 */     renderer.setBaseToolTipGenerator((XYToolTipGenerator)new HighLowItemLabelGenerator());
/* 1807 */     XYPlot plot = new XYPlot((XYDataset)dataset, (ValueAxis)timeAxis, (ValueAxis)valueAxis, (XYItemRenderer)renderer);
/* 1808 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/* 1811 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createBubbleChart(String title, String xAxisLabel, String yAxisLabel, XYZDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
/* 1842 */     if (orientation == null)
/* 1843 */       throw new IllegalArgumentException("Null 'orientation' argument."); 
/* 1845 */     NumberAxis xAxis = new NumberAxis(xAxisLabel);
/* 1846 */     xAxis.setAutoRangeIncludesZero(false);
/* 1847 */     NumberAxis yAxis = new NumberAxis(yAxisLabel);
/* 1848 */     yAxis.setAutoRangeIncludesZero(false);
/* 1850 */     XYPlot plot = new XYPlot((XYDataset)dataset, (ValueAxis)xAxis, (ValueAxis)yAxis, null);
/* 1852 */     XYBubbleRenderer xYBubbleRenderer = new XYBubbleRenderer(2);
/* 1855 */     if (tooltips)
/* 1856 */       xYBubbleRenderer.setBaseToolTipGenerator((XYToolTipGenerator)new StandardXYZToolTipGenerator()); 
/* 1858 */     if (urls)
/* 1859 */       xYBubbleRenderer.setURLGenerator((XYURLGenerator)new StandardXYZURLGenerator()); 
/* 1861 */     plot.setRenderer((XYItemRenderer)xYBubbleRenderer);
/* 1862 */     plot.setOrientation(orientation);
/* 1864 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/* 1868 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createHistogram(String title, String xAxisLabel, String yAxisLabel, IntervalXYDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
/* 1898 */     if (orientation == null)
/* 1899 */       throw new IllegalArgumentException("Null 'orientation' argument."); 
/* 1901 */     NumberAxis xAxis = new NumberAxis(xAxisLabel);
/* 1902 */     xAxis.setAutoRangeIncludesZero(false);
/* 1903 */     NumberAxis numberAxis1 = new NumberAxis(yAxisLabel);
/* 1905 */     XYBarRenderer xYBarRenderer = new XYBarRenderer();
/* 1906 */     if (tooltips)
/* 1907 */       xYBarRenderer.setBaseToolTipGenerator((XYToolTipGenerator)new StandardXYToolTipGenerator()); 
/* 1909 */     if (urls)
/* 1910 */       xYBarRenderer.setURLGenerator((XYURLGenerator)new StandardXYURLGenerator()); 
/* 1913 */     XYPlot plot = new XYPlot((XYDataset)dataset, (ValueAxis)xAxis, (ValueAxis)numberAxis1, (XYItemRenderer)xYBarRenderer);
/* 1914 */     plot.setOrientation(orientation);
/* 1915 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/* 1918 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createBoxAndWhiskerChart(String title, String timeAxisLabel, String valueAxisLabel, BoxAndWhiskerXYDataset dataset, boolean legend) {
/* 1941 */     DateAxis dateAxis = new DateAxis(timeAxisLabel);
/* 1942 */     NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
/* 1943 */     valueAxis.setAutoRangeIncludesZero(false);
/* 1944 */     XYBoxAndWhiskerRenderer renderer = new XYBoxAndWhiskerRenderer(10.0D);
/* 1945 */     XYPlot plot = new XYPlot((XYDataset)dataset, (ValueAxis)dateAxis, (ValueAxis)valueAxis, (XYItemRenderer)renderer);
/* 1946 */     return new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/*      */   }
/*      */   
/*      */   public static JFreeChart createWindPlot(String title, String xAxisLabel, String yAxisLabel, WindDataset dataset, boolean legend, boolean tooltips, boolean urls) {
/* 1974 */     DateAxis dateAxis = new DateAxis(xAxisLabel);
/* 1975 */     NumberAxis numberAxis = new NumberAxis(yAxisLabel);
/* 1976 */     numberAxis.setRange(-12.0D, 12.0D);
/* 1978 */     WindItemRenderer renderer = new WindItemRenderer();
/* 1979 */     if (tooltips)
/* 1980 */       renderer.setBaseToolTipGenerator((XYToolTipGenerator)new StandardXYToolTipGenerator()); 
/* 1982 */     if (urls)
/* 1983 */       renderer.setURLGenerator((XYURLGenerator)new StandardXYURLGenerator()); 
/* 1985 */     XYPlot plot = new XYPlot((XYDataset)dataset, (ValueAxis)dateAxis, (ValueAxis)numberAxis, (XYItemRenderer)renderer);
/* 1986 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/* 1990 */     return chart;
/*      */   }
/*      */   
/*      */   public static JFreeChart createWaferMapChart(String title, WaferMapDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
/* 2014 */     if (orientation == null)
/* 2015 */       throw new IllegalArgumentException("Null 'orientation' argument."); 
/* 2017 */     WaferMapPlot plot = new WaferMapPlot(dataset);
/* 2018 */     WaferMapRenderer renderer = new WaferMapRenderer();
/* 2019 */     plot.setRenderer(renderer);
/* 2021 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, (Plot)plot, legend);
/* 2028 */     return chart;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\ChartFactory.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */