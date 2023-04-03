/*      */ package org.jfree.data.general;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import org.jfree.data.DomainInfo;
/*      */ import org.jfree.data.KeyToGroupMap;
/*      */ import org.jfree.data.KeyedValues;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.RangeInfo;
/*      */ import org.jfree.data.category.CategoryDataset;
/*      */ import org.jfree.data.category.DefaultCategoryDataset;
/*      */ import org.jfree.data.category.IntervalCategoryDataset;
/*      */ import org.jfree.data.function.Function2D;
/*      */ import org.jfree.data.xy.IntervalXYDataset;
/*      */ import org.jfree.data.xy.OHLCDataset;
/*      */ import org.jfree.data.xy.TableXYDataset;
/*      */ import org.jfree.data.xy.XYDataset;
/*      */ import org.jfree.data.xy.XYSeries;
/*      */ import org.jfree.data.xy.XYSeriesCollection;
/*      */ import org.jfree.util.ArrayUtilities;
/*      */ 
/*      */ public final class DatasetUtilities {
/*      */   public static double calculatePieDatasetTotal(PieDataset dataset) {
/*  151 */     if (dataset == null)
/*  152 */       throw new IllegalArgumentException("Null 'dataset' argument."); 
/*  154 */     List keys = dataset.getKeys();
/*  155 */     double totalValue = 0.0D;
/*  156 */     Iterator iterator = keys.iterator();
/*  157 */     while (iterator.hasNext()) {
/*  158 */       Comparable current = iterator.next();
/*  159 */       if (current != null) {
/*  160 */         Number value = dataset.getValue(current);
/*  161 */         double v = 0.0D;
/*  162 */         if (value != null)
/*  163 */           v = value.doubleValue(); 
/*  165 */         if (v > 0.0D)
/*  166 */           totalValue += v; 
/*      */       } 
/*      */     } 
/*  170 */     return totalValue;
/*      */   }
/*      */   
/*      */   public static PieDataset createPieDatasetForRow(CategoryDataset dataset, Comparable rowKey) {
/*  184 */     int row = dataset.getRowIndex(rowKey);
/*  185 */     return createPieDatasetForRow(dataset, row);
/*      */   }
/*      */   
/*      */   public static PieDataset createPieDatasetForRow(CategoryDataset dataset, int row) {
/*  199 */     DefaultPieDataset result = new DefaultPieDataset();
/*  200 */     int columnCount = dataset.getColumnCount();
/*  201 */     for (int current = 0; current < columnCount; current++) {
/*  202 */       Comparable columnKey = dataset.getColumnKey(current);
/*  203 */       result.setValue(columnKey, dataset.getValue(row, current));
/*      */     } 
/*  205 */     return result;
/*      */   }
/*      */   
/*      */   public static PieDataset createPieDatasetForColumn(CategoryDataset dataset, Comparable columnKey) {
/*  219 */     int column = dataset.getColumnIndex(columnKey);
/*  220 */     return createPieDatasetForColumn(dataset, column);
/*      */   }
/*      */   
/*      */   public static PieDataset createPieDatasetForColumn(CategoryDataset dataset, int column) {
/*  234 */     DefaultPieDataset result = new DefaultPieDataset();
/*  235 */     int rowCount = dataset.getRowCount();
/*  236 */     for (int i = 0; i < rowCount; i++) {
/*  237 */       Comparable rowKey = dataset.getRowKey(i);
/*  238 */       result.setValue(rowKey, dataset.getValue(i, column));
/*      */     } 
/*  240 */     return result;
/*      */   }
/*      */   
/*      */   public static PieDataset createConsolidatedPieDataset(PieDataset source, Comparable key, double minimumPercent) {
/*  260 */     return createConsolidatedPieDataset(source, key, minimumPercent, 2);
/*      */   }
/*      */   
/*      */   public static PieDataset createConsolidatedPieDataset(PieDataset source, Comparable key, double minimumPercent, int minItems) {
/*  285 */     DefaultPieDataset result = new DefaultPieDataset();
/*  286 */     double total = calculatePieDatasetTotal(source);
/*  289 */     List keys = source.getKeys();
/*  290 */     ArrayList otherKeys = new ArrayList();
/*  291 */     Iterator iterator = keys.iterator();
/*  292 */     while (iterator.hasNext()) {
/*  293 */       Comparable currentKey = iterator.next();
/*  294 */       Number dataValue = source.getValue(currentKey);
/*  295 */       if (dataValue != null) {
/*  296 */         double value = dataValue.doubleValue();
/*  297 */         if (value / total < minimumPercent)
/*  298 */           otherKeys.add(currentKey); 
/*      */       } 
/*      */     } 
/*  304 */     iterator = keys.iterator();
/*  305 */     double otherValue = 0.0D;
/*  306 */     while (iterator.hasNext()) {
/*  307 */       Comparable currentKey = iterator.next();
/*  308 */       Number dataValue = source.getValue(currentKey);
/*  309 */       if (dataValue != null) {
/*  310 */         if (otherKeys.contains(currentKey) && otherKeys.size() >= minItems) {
/*  313 */           otherValue += dataValue.doubleValue();
/*      */           continue;
/*      */         } 
/*  317 */         result.setValue(currentKey, dataValue);
/*      */       } 
/*      */     } 
/*  322 */     if (otherKeys.size() >= minItems)
/*  323 */       result.setValue(key, otherValue); 
/*  325 */     return result;
/*      */   }
/*      */   
/*      */   public static CategoryDataset createCategoryDataset(String rowKeyPrefix, String columnKeyPrefix, double[][] data) {
/*  346 */     DefaultCategoryDataset result = new DefaultCategoryDataset();
/*  347 */     for (int r = 0; r < data.length; r++) {
/*  348 */       String rowKey = rowKeyPrefix + (r + 1);
/*  349 */       for (int c = 0; c < (data[r]).length; c++) {
/*  350 */         String columnKey = columnKeyPrefix + (c + 1);
/*  351 */         result.addValue(new Double(data[r][c]), rowKey, columnKey);
/*      */       } 
/*      */     } 
/*  354 */     return (CategoryDataset)result;
/*      */   }
/*      */   
/*      */   public static CategoryDataset createCategoryDataset(String rowKeyPrefix, String columnKeyPrefix, Number[][] data) {
/*  375 */     DefaultCategoryDataset result = new DefaultCategoryDataset();
/*  376 */     for (int r = 0; r < data.length; r++) {
/*  377 */       String rowKey = rowKeyPrefix + (r + 1);
/*  378 */       for (int c = 0; c < (data[r]).length; c++) {
/*  379 */         String columnKey = columnKeyPrefix + (c + 1);
/*  380 */         result.addValue(data[r][c], rowKey, columnKey);
/*      */       } 
/*      */     } 
/*  383 */     return (CategoryDataset)result;
/*      */   }
/*      */   
/*      */   public static CategoryDataset createCategoryDataset(Comparable[] rowKeys, Comparable[] columnKeys, double[][] data) {
/*  405 */     if (rowKeys == null)
/*  406 */       throw new IllegalArgumentException("Null 'rowKeys' argument."); 
/*  408 */     if (columnKeys == null)
/*  409 */       throw new IllegalArgumentException("Null 'columnKeys' argument."); 
/*  411 */     if (ArrayUtilities.hasDuplicateItems((Object[])rowKeys))
/*  412 */       throw new IllegalArgumentException("Duplicate items in 'rowKeys'."); 
/*  414 */     if (ArrayUtilities.hasDuplicateItems((Object[])columnKeys))
/*  415 */       throw new IllegalArgumentException("Duplicate items in 'columnKeys'."); 
/*  419 */     if (rowKeys.length != data.length)
/*  420 */       throw new IllegalArgumentException("The number of row keys does not match the number of rows in the data array."); 
/*  425 */     int columnCount = 0;
/*  426 */     for (int r = 0; r < data.length; r++)
/*  427 */       columnCount = Math.max(columnCount, (data[r]).length); 
/*  429 */     if (columnKeys.length != columnCount)
/*  430 */       throw new IllegalArgumentException("The number of column keys does not match the number of columns in the data array."); 
/*  437 */     DefaultCategoryDataset result = new DefaultCategoryDataset();
/*  438 */     for (int i = 0; i < data.length; i++) {
/*  439 */       Comparable rowKey = rowKeys[i];
/*  440 */       for (int c = 0; c < (data[i]).length; c++) {
/*  441 */         Comparable columnKey = columnKeys[c];
/*  442 */         result.addValue(new Double(data[i][c]), rowKey, columnKey);
/*      */       } 
/*      */     } 
/*  445 */     return (CategoryDataset)result;
/*      */   }
/*      */   
/*      */   public static CategoryDataset createCategoryDataset(Comparable rowKey, KeyedValues rowData) {
/*  461 */     if (rowKey == null)
/*  462 */       throw new IllegalArgumentException("Null 'rowKey' argument."); 
/*  464 */     if (rowData == null)
/*  465 */       throw new IllegalArgumentException("Null 'rowData' argument."); 
/*  467 */     DefaultCategoryDataset result = new DefaultCategoryDataset();
/*  468 */     for (int i = 0; i < rowData.getItemCount(); i++)
/*  469 */       result.addValue(rowData.getValue(i), rowKey, rowData.getKey(i)); 
/*  471 */     return (CategoryDataset)result;
/*      */   }
/*      */   
/*      */   public static XYDataset sampleFunction2D(Function2D f, double start, double end, int samples, Comparable seriesKey) {
/*  494 */     if (f == null)
/*  495 */       throw new IllegalArgumentException("Null 'f' argument."); 
/*  497 */     if (seriesKey == null)
/*  498 */       throw new IllegalArgumentException("Null 'seriesKey' argument."); 
/*  500 */     if (start >= end)
/*  501 */       throw new IllegalArgumentException("Requires 'start' < 'end'."); 
/*  503 */     if (samples < 2)
/*  504 */       throw new IllegalArgumentException("Requires 'samples' > 1"); 
/*  507 */     XYSeries series = new XYSeries(seriesKey);
/*  508 */     double step = (end - start) / samples;
/*  509 */     for (int i = 0; i <= samples; i++) {
/*  510 */       double x = start + step * i;
/*  511 */       series.add(x, f.getValue(x));
/*      */     } 
/*  513 */     XYSeriesCollection collection = new XYSeriesCollection(series);
/*  514 */     return (XYDataset)collection;
/*      */   }
/*      */   
/*      */   public static boolean isEmptyOrNull(PieDataset dataset) {
/*  528 */     if (dataset == null)
/*  529 */       return true; 
/*  532 */     int itemCount = dataset.getItemCount();
/*  533 */     if (itemCount == 0)
/*  534 */       return true; 
/*  537 */     for (int item = 0; item < itemCount; item++) {
/*  538 */       Number y = dataset.getValue(item);
/*  539 */       if (y != null) {
/*  540 */         double yy = y.doubleValue();
/*  541 */         if (yy > 0.0D)
/*  542 */           return false; 
/*      */       } 
/*      */     } 
/*  547 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isEmptyOrNull(CategoryDataset dataset) {
/*  561 */     if (dataset == null)
/*  562 */       return true; 
/*  565 */     int rowCount = dataset.getRowCount();
/*  566 */     int columnCount = dataset.getColumnCount();
/*  567 */     if (rowCount == 0 || columnCount == 0)
/*  568 */       return true; 
/*  571 */     for (int r = 0; r < rowCount; r++) {
/*  572 */       for (int c = 0; c < columnCount; c++) {
/*  573 */         if (dataset.getValue(r, c) != null)
/*  574 */           return false; 
/*      */       } 
/*      */     } 
/*  580 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isEmptyOrNull(XYDataset dataset) {
/*  594 */     boolean result = true;
/*  596 */     if (dataset != null)
/*  597 */       for (int s = 0; s < dataset.getSeriesCount(); s++) {
/*  598 */         if (dataset.getItemCount(s) > 0)
/*  599 */           result = false; 
/*      */       }  
/*  605 */     return result;
/*      */   }
/*      */   
/*      */   public static Range findDomainBounds(XYDataset dataset) {
/*  617 */     return findDomainBounds(dataset, true);
/*      */   }
/*      */   
/*      */   public static Range findDomainBounds(XYDataset dataset, boolean includeInterval) {
/*  633 */     if (dataset == null)
/*  634 */       throw new IllegalArgumentException("Null 'dataset' argument."); 
/*  637 */     Range result = null;
/*  639 */     if (dataset instanceof DomainInfo) {
/*  640 */       DomainInfo info = (DomainInfo)dataset;
/*  641 */       result = info.getDomainBounds(includeInterval);
/*      */     } else {
/*  644 */       result = iterateDomainBounds(dataset, includeInterval);
/*      */     } 
/*  646 */     return result;
/*      */   }
/*      */   
/*      */   public static Range iterateDomainBounds(XYDataset dataset) {
/*  659 */     return iterateDomainBounds(dataset, true);
/*      */   }
/*      */   
/*      */   public static Range iterateDomainBounds(XYDataset dataset, boolean includeInterval) {
/*  675 */     if (dataset == null)
/*  676 */       throw new IllegalArgumentException("Null 'dataset' argument."); 
/*  678 */     double minimum = Double.POSITIVE_INFINITY;
/*  679 */     double maximum = Double.NEGATIVE_INFINITY;
/*  680 */     int seriesCount = dataset.getSeriesCount();
/*  683 */     if (includeInterval && dataset instanceof IntervalXYDataset) {
/*  684 */       IntervalXYDataset intervalXYData = (IntervalXYDataset)dataset;
/*  685 */       for (int series = 0; series < seriesCount; series++) {
/*  686 */         int itemCount = dataset.getItemCount(series);
/*  687 */         for (int item = 0; item < itemCount; item++) {
/*  688 */           double lvalue = intervalXYData.getStartXValue(series, item);
/*  689 */           double uvalue = intervalXYData.getEndXValue(series, item);
/*  690 */           minimum = Math.min(minimum, lvalue);
/*  691 */           maximum = Math.max(maximum, uvalue);
/*      */         } 
/*      */       } 
/*      */     } else {
/*  696 */       for (int series = 0; series < seriesCount; series++) {
/*  697 */         int itemCount = dataset.getItemCount(series);
/*  698 */         for (int item = 0; item < itemCount; item++) {
/*  699 */           double lvalue = dataset.getXValue(series, item);
/*  700 */           double uvalue = lvalue;
/*  701 */           minimum = Math.min(minimum, lvalue);
/*  702 */           maximum = Math.max(maximum, uvalue);
/*      */         } 
/*      */       } 
/*      */     } 
/*  706 */     if (minimum > maximum)
/*  707 */       return null; 
/*  710 */     return new Range(minimum, maximum);
/*      */   }
/*      */   
/*      */   public static Range findRangeBounds(CategoryDataset dataset) {
/*  723 */     return findRangeBounds(dataset, true);
/*      */   }
/*      */   
/*      */   public static Range findRangeBounds(CategoryDataset dataset, boolean includeInterval) {
/*  738 */     if (dataset == null)
/*  739 */       throw new IllegalArgumentException("Null 'dataset' argument."); 
/*  741 */     Range result = null;
/*  742 */     if (dataset instanceof RangeInfo) {
/*  743 */       RangeInfo info = (RangeInfo)dataset;
/*  744 */       result = info.getRangeBounds(includeInterval);
/*      */     } else {
/*  747 */       result = iterateCategoryRangeBounds(dataset, includeInterval);
/*      */     } 
/*  749 */     return result;
/*      */   }
/*      */   
/*      */   public static Range findRangeBounds(XYDataset dataset) {
/*  761 */     return findRangeBounds(dataset, true);
/*      */   }
/*      */   
/*      */   public static Range findRangeBounds(XYDataset dataset, boolean includeInterval) {
/*  777 */     if (dataset == null)
/*  778 */       throw new IllegalArgumentException("Null 'dataset' argument."); 
/*  780 */     Range result = null;
/*  781 */     if (dataset instanceof RangeInfo) {
/*  782 */       RangeInfo info = (RangeInfo)dataset;
/*  783 */       result = info.getRangeBounds(includeInterval);
/*      */     } else {
/*  786 */       result = iterateXYRangeBounds(dataset);
/*      */     } 
/*  788 */     return result;
/*      */   }
/*      */   
/*      */   public static Range iterateCategoryRangeBounds(CategoryDataset dataset, boolean includeInterval) {
/*  803 */     double minimum = Double.POSITIVE_INFINITY;
/*  804 */     double maximum = Double.NEGATIVE_INFINITY;
/*  805 */     boolean interval = (includeInterval && dataset instanceof IntervalCategoryDataset);
/*  807 */     int rowCount = dataset.getRowCount();
/*  808 */     int columnCount = dataset.getColumnCount();
/*  809 */     for (int row = 0; row < rowCount; row++) {
/*  810 */       for (int column = 0; column < columnCount; column++) {
/*      */         Number lvalue;
/*      */         Number uvalue;
/*  813 */         if (interval) {
/*  814 */           IntervalCategoryDataset icd = (IntervalCategoryDataset)dataset;
/*  816 */           lvalue = icd.getStartValue(row, column);
/*  817 */           uvalue = icd.getEndValue(row, column);
/*      */         } else {
/*  820 */           lvalue = dataset.getValue(row, column);
/*  821 */           uvalue = lvalue;
/*      */         } 
/*  823 */         if (lvalue != null)
/*  824 */           minimum = Math.min(minimum, lvalue.doubleValue()); 
/*  826 */         if (uvalue != null)
/*  827 */           maximum = Math.max(maximum, uvalue.doubleValue()); 
/*      */       } 
/*      */     } 
/*  831 */     if (minimum == Double.POSITIVE_INFINITY)
/*  832 */       return null; 
/*  835 */     return new Range(minimum, maximum);
/*      */   }
/*      */   
/*      */   public static Range iterateXYRangeBounds(XYDataset dataset) {
/*  848 */     double minimum = Double.POSITIVE_INFINITY;
/*  849 */     double maximum = Double.NEGATIVE_INFINITY;
/*  850 */     int seriesCount = dataset.getSeriesCount();
/*  851 */     for (int series = 0; series < seriesCount; series++) {
/*  852 */       int itemCount = dataset.getItemCount(series);
/*  853 */       for (int item = 0; item < itemCount; item++) {
/*      */         double lvalue;
/*      */         double uvalue;
/*  856 */         if (dataset instanceof IntervalXYDataset) {
/*  857 */           IntervalXYDataset intervalXYData = (IntervalXYDataset)dataset;
/*  859 */           lvalue = intervalXYData.getStartYValue(series, item);
/*  860 */           uvalue = intervalXYData.getEndYValue(series, item);
/*  862 */         } else if (dataset instanceof OHLCDataset) {
/*  863 */           OHLCDataset highLowData = (OHLCDataset)dataset;
/*  864 */           lvalue = highLowData.getLowValue(series, item);
/*  865 */           uvalue = highLowData.getHighValue(series, item);
/*      */         } else {
/*  868 */           lvalue = dataset.getYValue(series, item);
/*  869 */           uvalue = lvalue;
/*      */         } 
/*  871 */         if (!Double.isNaN(lvalue))
/*  872 */           minimum = Math.min(minimum, lvalue); 
/*  874 */         if (!Double.isNaN(uvalue))
/*  875 */           maximum = Math.max(maximum, uvalue); 
/*      */       } 
/*      */     } 
/*  879 */     if (minimum == Double.POSITIVE_INFINITY)
/*  880 */       return null; 
/*  883 */     return new Range(minimum, maximum);
/*      */   }
/*      */   
/*      */   public static Number findMinimumDomainValue(XYDataset dataset) {
/*  901 */     if (dataset == null)
/*  902 */       throw new IllegalArgumentException("Null 'dataset' argument."); 
/*  904 */     Number result = null;
/*  906 */     if (dataset instanceof DomainInfo) {
/*  907 */       DomainInfo info = (DomainInfo)dataset;
/*  908 */       return new Double(info.getDomainLowerBound(true));
/*      */     } 
/*  911 */     double minimum = Double.POSITIVE_INFINITY;
/*  912 */     int seriesCount = dataset.getSeriesCount();
/*  913 */     for (int series = 0; series < seriesCount; series++) {
/*  914 */       int itemCount = dataset.getItemCount(series);
/*  915 */       for (int item = 0; item < itemCount; item++) {
/*      */         double value;
/*  918 */         if (dataset instanceof IntervalXYDataset) {
/*  919 */           IntervalXYDataset intervalXYData = (IntervalXYDataset)dataset;
/*  921 */           value = intervalXYData.getStartXValue(series, item);
/*      */         } else {
/*  924 */           value = dataset.getXValue(series, item);
/*      */         } 
/*  926 */         if (!Double.isNaN(value))
/*  927 */           minimum = Math.min(minimum, value); 
/*      */       } 
/*      */     } 
/*  932 */     if (minimum == Double.POSITIVE_INFINITY) {
/*  933 */       result = null;
/*      */     } else {
/*  936 */       result = new Double(minimum);
/*      */     } 
/*  940 */     return result;
/*      */   }
/*      */   
/*      */   public static Number findMaximumDomainValue(XYDataset dataset) {
/*  956 */     if (dataset == null)
/*  957 */       throw new IllegalArgumentException("Null 'dataset' argument."); 
/*  959 */     Number result = null;
/*  961 */     if (dataset instanceof DomainInfo) {
/*  962 */       DomainInfo info = (DomainInfo)dataset;
/*  963 */       return new Double(info.getDomainUpperBound(true));
/*      */     } 
/*  968 */     double maximum = Double.NEGATIVE_INFINITY;
/*  969 */     int seriesCount = dataset.getSeriesCount();
/*  970 */     for (int series = 0; series < seriesCount; series++) {
/*  971 */       int itemCount = dataset.getItemCount(series);
/*  972 */       for (int item = 0; item < itemCount; item++) {
/*      */         double value;
/*  975 */         if (dataset instanceof IntervalXYDataset) {
/*  976 */           IntervalXYDataset intervalXYData = (IntervalXYDataset)dataset;
/*  978 */           value = intervalXYData.getEndXValue(series, item);
/*      */         } else {
/*  981 */           value = dataset.getXValue(series, item);
/*      */         } 
/*  983 */         if (!Double.isNaN(value))
/*  984 */           maximum = Math.max(maximum, value); 
/*      */       } 
/*      */     } 
/*  988 */     if (maximum == Double.NEGATIVE_INFINITY) {
/*  989 */       result = null;
/*      */     } else {
/*  992 */       result = new Double(maximum);
/*      */     } 
/*  997 */     return result;
/*      */   }
/*      */   
/*      */   public static Number findMinimumRangeValue(CategoryDataset dataset) {
/* 1015 */     if (dataset == null)
/* 1016 */       throw new IllegalArgumentException("Null 'dataset' argument."); 
/* 1020 */     if (dataset instanceof RangeInfo) {
/* 1021 */       RangeInfo info = (RangeInfo)dataset;
/* 1022 */       return new Double(info.getRangeLowerBound(true));
/*      */     } 
/* 1027 */     double minimum = Double.POSITIVE_INFINITY;
/* 1028 */     int seriesCount = dataset.getRowCount();
/* 1029 */     int itemCount = dataset.getColumnCount();
/* 1030 */     for (int series = 0; series < seriesCount; series++) {
/* 1031 */       for (int item = 0; item < itemCount; item++) {
/*      */         Number value;
/* 1033 */         if (dataset instanceof IntervalCategoryDataset) {
/* 1034 */           IntervalCategoryDataset icd = (IntervalCategoryDataset)dataset;
/* 1036 */           value = icd.getStartValue(series, item);
/*      */         } else {
/* 1039 */           value = dataset.getValue(series, item);
/*      */         } 
/* 1041 */         if (value != null)
/* 1042 */           minimum = Math.min(minimum, value.doubleValue()); 
/*      */       } 
/*      */     } 
/* 1046 */     if (minimum == Double.POSITIVE_INFINITY)
/* 1047 */       return null; 
/* 1050 */     return new Double(minimum);
/*      */   }
/*      */   
/*      */   public static Number findMinimumRangeValue(XYDataset dataset) {
/* 1071 */     if (dataset == null)
/* 1072 */       throw new IllegalArgumentException("Null 'dataset' argument."); 
/* 1076 */     if (dataset instanceof RangeInfo) {
/* 1077 */       RangeInfo info = (RangeInfo)dataset;
/* 1078 */       return new Double(info.getRangeLowerBound(true));
/*      */     } 
/* 1083 */     double minimum = Double.POSITIVE_INFINITY;
/* 1084 */     int seriesCount = dataset.getSeriesCount();
/* 1085 */     for (int series = 0; series < seriesCount; series++) {
/* 1086 */       int itemCount = dataset.getItemCount(series);
/* 1087 */       for (int item = 0; item < itemCount; item++) {
/*      */         double value;
/* 1090 */         if (dataset instanceof IntervalXYDataset) {
/* 1091 */           IntervalXYDataset intervalXYData = (IntervalXYDataset)dataset;
/* 1093 */           value = intervalXYData.getStartYValue(series, item);
/* 1095 */         } else if (dataset instanceof OHLCDataset) {
/* 1096 */           OHLCDataset highLowData = (OHLCDataset)dataset;
/* 1097 */           value = highLowData.getLowValue(series, item);
/*      */         } else {
/* 1100 */           value = dataset.getYValue(series, item);
/*      */         } 
/* 1102 */         if (!Double.isNaN(value))
/* 1103 */           minimum = Math.min(minimum, value); 
/*      */       } 
/*      */     } 
/* 1108 */     if (minimum == Double.POSITIVE_INFINITY)
/* 1109 */       return null; 
/* 1112 */     return new Double(minimum);
/*      */   }
/*      */   
/*      */   public static Number findMaximumRangeValue(CategoryDataset dataset) {
/* 1132 */     if (dataset == null)
/* 1133 */       throw new IllegalArgumentException("Null 'dataset' argument."); 
/* 1137 */     if (dataset instanceof RangeInfo) {
/* 1138 */       RangeInfo info = (RangeInfo)dataset;
/* 1139 */       return new Double(info.getRangeUpperBound(true));
/*      */     } 
/* 1145 */     double maximum = Double.NEGATIVE_INFINITY;
/* 1146 */     int seriesCount = dataset.getRowCount();
/* 1147 */     int itemCount = dataset.getColumnCount();
/* 1148 */     for (int series = 0; series < seriesCount; series++) {
/* 1149 */       for (int item = 0; item < itemCount; item++) {
/*      */         Number value;
/* 1151 */         if (dataset instanceof IntervalCategoryDataset) {
/* 1152 */           IntervalCategoryDataset icd = (IntervalCategoryDataset)dataset;
/* 1154 */           value = icd.getEndValue(series, item);
/*      */         } else {
/* 1157 */           value = dataset.getValue(series, item);
/*      */         } 
/* 1159 */         if (value != null)
/* 1160 */           maximum = Math.max(maximum, value.doubleValue()); 
/*      */       } 
/*      */     } 
/* 1164 */     if (maximum == Double.NEGATIVE_INFINITY)
/* 1165 */       return null; 
/* 1168 */     return new Double(maximum);
/*      */   }
/*      */   
/*      */   public static Number findMaximumRangeValue(XYDataset dataset) {
/* 1188 */     if (dataset == null)
/* 1189 */       throw new IllegalArgumentException("Null 'dataset' argument."); 
/* 1193 */     if (dataset instanceof RangeInfo) {
/* 1194 */       RangeInfo info = (RangeInfo)dataset;
/* 1195 */       return new Double(info.getRangeUpperBound(true));
/*      */     } 
/* 1201 */     double maximum = Double.NEGATIVE_INFINITY;
/* 1202 */     int seriesCount = dataset.getSeriesCount();
/* 1203 */     for (int series = 0; series < seriesCount; series++) {
/* 1204 */       int itemCount = dataset.getItemCount(series);
/* 1205 */       for (int item = 0; item < itemCount; item++) {
/*      */         double value;
/* 1207 */         if (dataset instanceof IntervalXYDataset) {
/* 1208 */           IntervalXYDataset intervalXYData = (IntervalXYDataset)dataset;
/* 1210 */           value = intervalXYData.getEndYValue(series, item);
/* 1212 */         } else if (dataset instanceof OHLCDataset) {
/* 1213 */           OHLCDataset highLowData = (OHLCDataset)dataset;
/* 1214 */           value = highLowData.getHighValue(series, item);
/*      */         } else {
/* 1217 */           value = dataset.getYValue(series, item);
/*      */         } 
/* 1219 */         if (!Double.isNaN(value))
/* 1220 */           maximum = Math.max(maximum, value); 
/*      */       } 
/*      */     } 
/* 1224 */     if (maximum == Double.NEGATIVE_INFINITY)
/* 1225 */       return null; 
/* 1228 */     return new Double(maximum);
/*      */   }
/*      */   
/*      */   public static Range findStackedRangeBounds(CategoryDataset dataset) {
/* 1244 */     return findStackedRangeBounds(dataset, 0.0D);
/*      */   }
/*      */   
/*      */   public static Range findStackedRangeBounds(CategoryDataset dataset, double base) {
/* 1257 */     if (dataset == null)
/* 1258 */       throw new IllegalArgumentException("Null 'dataset' argument."); 
/* 1260 */     Range result = null;
/* 1261 */     double minimum = Double.POSITIVE_INFINITY;
/* 1262 */     double maximum = Double.NEGATIVE_INFINITY;
/* 1263 */     int categoryCount = dataset.getColumnCount();
/* 1264 */     for (int item = 0; item < categoryCount; item++) {
/* 1265 */       double positive = base;
/* 1266 */       double negative = base;
/* 1267 */       int seriesCount = dataset.getRowCount();
/* 1268 */       for (int series = 0; series < seriesCount; series++) {
/* 1269 */         Number number = dataset.getValue(series, item);
/* 1270 */         if (number != null) {
/* 1271 */           double value = number.doubleValue();
/* 1272 */           if (value > 0.0D)
/* 1273 */             positive += value; 
/* 1275 */           if (value < 0.0D)
/* 1276 */             negative += value; 
/*      */         } 
/*      */       } 
/* 1281 */       minimum = Math.min(minimum, negative);
/* 1282 */       maximum = Math.max(maximum, positive);
/*      */     } 
/* 1284 */     if (minimum <= maximum)
/* 1285 */       result = new Range(minimum, maximum); 
/* 1287 */     return result;
/*      */   }
/*      */   
/*      */   public static Range findStackedRangeBounds(CategoryDataset dataset, KeyToGroupMap map) {
/* 1304 */     Range result = null;
/* 1305 */     if (dataset != null) {
/* 1308 */       int[] groupIndex = new int[dataset.getRowCount()];
/* 1309 */       for (int i = 0; i < dataset.getRowCount(); i++)
/* 1310 */         groupIndex[i] = map.getGroupIndex(map.getGroup(dataset.getRowKey(i))); 
/* 1316 */       int groupCount = map.getGroupCount();
/* 1317 */       double[] minimum = new double[groupCount];
/* 1318 */       double[] maximum = new double[groupCount];
/* 1320 */       int categoryCount = dataset.getColumnCount();
/* 1321 */       for (int item = 0; item < categoryCount; item++) {
/* 1322 */         double[] positive = new double[groupCount];
/* 1323 */         double[] negative = new double[groupCount];
/* 1324 */         int seriesCount = dataset.getRowCount();
/* 1325 */         for (int series = 0; series < seriesCount; series++) {
/* 1326 */           Number number = dataset.getValue(series, item);
/* 1327 */           if (number != null) {
/* 1328 */             double value = number.doubleValue();
/* 1329 */             if (value > 0.0D)
/* 1330 */               positive[groupIndex[series]] = positive[groupIndex[series]] + value; 
/* 1333 */             if (value < 0.0D)
/* 1334 */               negative[groupIndex[series]] = negative[groupIndex[series]] + value; 
/*      */           } 
/*      */         } 
/* 1340 */         for (int g = 0; g < groupCount; g++) {
/* 1341 */           minimum[g] = Math.min(minimum[g], negative[g]);
/* 1342 */           maximum[g] = Math.max(maximum[g], positive[g]);
/*      */         } 
/*      */       } 
/* 1345 */       for (int j = 0; j < groupCount; j++)
/* 1346 */         result = Range.combine(result, new Range(minimum[j], maximum[j])); 
/*      */     } 
/* 1351 */     return result;
/*      */   }
/*      */   
/*      */   public static Number findMinimumStackedRangeValue(CategoryDataset dataset) {
/* 1365 */     Number result = null;
/* 1366 */     if (dataset != null) {
/* 1367 */       double minimum = 0.0D;
/* 1368 */       int categoryCount = dataset.getRowCount();
/* 1369 */       for (int item = 0; item < categoryCount; item++) {
/* 1370 */         double total = 0.0D;
/* 1372 */         int seriesCount = dataset.getColumnCount();
/* 1373 */         for (int series = 0; series < seriesCount; series++) {
/* 1374 */           Number number = dataset.getValue(series, item);
/* 1375 */           if (number != null) {
/* 1376 */             double value = number.doubleValue();
/* 1377 */             if (value < 0.0D)
/* 1378 */               total += value; 
/*      */           } 
/*      */         } 
/* 1383 */         minimum = Math.min(minimum, total);
/*      */       } 
/* 1386 */       result = new Double(minimum);
/*      */     } 
/* 1388 */     return result;
/*      */   }
/*      */   
/*      */   public static Number findMaximumStackedRangeValue(CategoryDataset dataset) {
/* 1402 */     Number result = null;
/* 1404 */     if (dataset != null) {
/* 1405 */       double maximum = 0.0D;
/* 1406 */       int categoryCount = dataset.getColumnCount();
/* 1407 */       for (int item = 0; item < categoryCount; item++) {
/* 1408 */         double total = 0.0D;
/* 1409 */         int seriesCount = dataset.getRowCount();
/* 1410 */         for (int series = 0; series < seriesCount; series++) {
/* 1411 */           Number number = dataset.getValue(series, item);
/* 1412 */           if (number != null) {
/* 1413 */             double value = number.doubleValue();
/* 1414 */             if (value > 0.0D)
/* 1415 */               total += value; 
/*      */           } 
/*      */         } 
/* 1419 */         maximum = Math.max(maximum, total);
/*      */       } 
/* 1421 */       result = new Double(maximum);
/*      */     } 
/* 1424 */     return result;
/*      */   }
/*      */   
/*      */   public static Range findStackedRangeBounds(TableXYDataset dataset) {
/* 1437 */     return findStackedRangeBounds(dataset, 0.0D);
/*      */   }
/*      */   
/*      */   public static Range findStackedRangeBounds(TableXYDataset dataset, double base) {
/* 1451 */     if (dataset == null)
/* 1452 */       throw new IllegalArgumentException("Null 'dataset' argument."); 
/* 1454 */     double minimum = base;
/* 1455 */     double maximum = base;
/* 1456 */     for (int itemNo = 0; itemNo < dataset.getItemCount(); itemNo++) {
/* 1457 */       double positive = base;
/* 1458 */       double negative = base;
/* 1459 */       int seriesCount = dataset.getSeriesCount();
/* 1460 */       for (int seriesNo = 0; seriesNo < seriesCount; seriesNo++) {
/* 1461 */         double y = dataset.getYValue(seriesNo, itemNo);
/* 1462 */         if (!Double.isNaN(y))
/* 1463 */           if (y > 0.0D) {
/* 1464 */             positive += y;
/*      */           } else {
/* 1467 */             negative += y;
/*      */           }  
/*      */       } 
/* 1471 */       if (positive > maximum)
/* 1472 */         maximum = positive; 
/* 1474 */       if (negative < minimum)
/* 1475 */         minimum = negative; 
/*      */     } 
/* 1478 */     if (minimum <= maximum)
/* 1479 */       return new Range(minimum, maximum); 
/* 1482 */     return null;
/*      */   }
/*      */   
/*      */   public static Range findCumulativeRangeBounds(CategoryDataset dataset) {
/* 1496 */     if (dataset == null)
/* 1497 */       throw new IllegalArgumentException("Null 'dataset' argument."); 
/* 1500 */     boolean allItemsNull = true;
/* 1502 */     double minimum = 0.0D;
/* 1503 */     double maximum = 0.0D;
/* 1504 */     for (int row = 0; row < dataset.getRowCount(); row++) {
/* 1505 */       double runningTotal = 0.0D;
/* 1506 */       for (int column = 0; column < dataset.getColumnCount() - 1; 
/* 1507 */         column++) {
/* 1508 */         Number n = dataset.getValue(row, column);
/* 1509 */         if (n != null) {
/* 1510 */           allItemsNull = false;
/* 1511 */           double value = n.doubleValue();
/* 1512 */           runningTotal += value;
/* 1513 */           minimum = Math.min(minimum, runningTotal);
/* 1514 */           maximum = Math.max(maximum, runningTotal);
/*      */         } 
/*      */       } 
/*      */     } 
/* 1518 */     if (!allItemsNull)
/* 1519 */       return new Range(minimum, maximum); 
/* 1522 */     return null;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\general\DatasetUtilities.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */