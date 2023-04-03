/*      */ package org.geotools.filter;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import org.geotools.factory.CommonFactoryFinder;
/*      */ import org.geotools.filter.visitor.AbstractSearchFilterVisitor;
/*      */ import org.geotools.filter.visitor.DefaultFilterVisitor;
/*      */ import org.geotools.filter.visitor.DuplicatingFilterVisitor;
/*      */ import org.geotools.util.Converters;
/*      */ import org.geotools.util.Utilities;
/*      */ import org.opengis.feature.simple.SimpleFeatureType;
/*      */ import org.opengis.filter.And;
/*      */ import org.opengis.filter.BinaryLogicOperator;
/*      */ import org.opengis.filter.Filter;
/*      */ import org.opengis.filter.FilterFactory;
/*      */ import org.opengis.filter.FilterFactory2;
/*      */ import org.opengis.filter.FilterVisitor;
/*      */ import org.opengis.filter.Not;
/*      */ import org.opengis.filter.Or;
/*      */ import org.opengis.filter.expression.Expression;
/*      */ import org.opengis.filter.expression.ExpressionVisitor;
/*      */ import org.opengis.filter.expression.Function;
/*      */ import org.opengis.filter.expression.Literal;
/*      */ import org.opengis.filter.expression.PropertyName;
/*      */ 
/*      */ public class Filters {
/*      */   public static final int NOTFOUND = -1;
/*      */   
/*  136 */   private static Filters STATIC = new Filters();
/*      */   
/*      */   private static final boolean STRICT = false;
/*      */   
/*      */   FilterFactory2 ff;
/*      */   
/*      */   public Filters() {
/*  147 */     this(CommonFactoryFinder.getFilterFactory2(null));
/*      */   }
/*      */   
/*      */   public Filters(FilterFactory2 factory) {
/*  151 */     this.ff = factory;
/*      */   }
/*      */   
/*      */   public void setFilterFactory(FilterFactory2 factory) {
/*  154 */     this.ff = factory;
/*      */   }
/*      */   
/*      */   public static Filter and(FilterFactory ff, Filter filter1, Filter filter2) {
/*  168 */     ArrayList<Filter> list = new ArrayList<Filter>(2);
/*  169 */     if (filter1 != null)
/*  172 */       if (filter1 instanceof And) {
/*  173 */         And some = (And)filter1;
/*  174 */         list.addAll(some.getChildren());
/*      */       } else {
/*  177 */         list.add(filter1);
/*      */       }  
/*  180 */     if (filter2 != null)
/*  183 */       if (filter2 instanceof And) {
/*  184 */         And more = (And)filter2;
/*  185 */         list.addAll(more.getChildren());
/*      */       } else {
/*  188 */         list.add(filter2);
/*      */       }  
/*  191 */     if (list.size() == 0)
/*  192 */       return (Filter)Filter.EXCLUDE; 
/*  194 */     if (list.size() == 1)
/*  195 */       return list.get(0); 
/*  198 */     return (Filter)ff.and(list);
/*      */   }
/*      */   
/*      */   public static Filter or(FilterFactory ff, Filter filter1, Filter filter2) {
/*  212 */     ArrayList<Filter> list = new ArrayList<Filter>();
/*  213 */     if (filter1 != null)
/*  216 */       if (filter1 instanceof Or) {
/*  217 */         Or some = (Or)filter1;
/*  218 */         list.addAll(some.getChildren());
/*      */       } else {
/*  221 */         list.add(filter1);
/*      */       }  
/*  224 */     if (filter2 != null)
/*  227 */       if (filter2 instanceof Or) {
/*  228 */         Or more = (Or)filter2;
/*  229 */         list.addAll(more.getChildren());
/*      */       } else {
/*  232 */         list.add(filter2);
/*      */       }  
/*  235 */     if (list.size() == 0)
/*  236 */       return (Filter)Filter.EXCLUDE; 
/*  238 */     if (list.size() == 1)
/*  239 */       return list.get(0); 
/*  242 */     return (Filter)ff.or(list);
/*      */   }
/*      */   
/*      */   public static void accept(Filter filter, FilterVisitor visitor) {
/*  263 */     if (filter == Filter.EXCLUDE) {
/*  264 */       if (visitor instanceof FilterVisitor2)
/*  265 */         ((FilterVisitor2)visitor).visit(Filter.EXCLUDE); 
/*      */       return;
/*      */     } 
/*  269 */     if (filter == Filter.INCLUDE) {
/*  270 */       if (visitor instanceof FilterVisitor2)
/*  271 */         ((FilterVisitor2)visitor).visit(Filter.INCLUDE); 
/*      */       return;
/*      */     } 
/*  276 */     if (filter instanceof Filter) {
/*  277 */       ((Filter)filter).accept(visitor);
/*      */     } else {
/*  285 */       FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2(null);
/*  286 */       DuplicatingFilterVisitor xerox = new DuplicatingFilterVisitor(ff);
/*  287 */       Filter copy = (Filter)filter.accept((FilterVisitor)xerox, ff);
/*  290 */       copy.accept(visitor);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Filter duplicate(Filter filter) {
/*  302 */     DuplicatingFilterVisitor xerox = new DuplicatingFilterVisitor(this.ff);
/*  303 */     Filter copy = (Filter)filter.accept((FilterVisitor)xerox, this.ff);
/*  304 */     return copy;
/*      */   }
/*      */   
/*      */   public static short getFilterType(Filter filter) {
/*  322 */     if (filter == Filter.EXCLUDE)
/*  322 */       return -12345; 
/*  323 */     if (filter == Filter.INCLUDE)
/*  323 */       return 12345; 
/*  324 */     if (filter instanceof Filter)
/*  325 */       return ((Filter)filter).getFilterType(); 
/*  327 */     if (filter instanceof org.opengis.filter.PropertyIsBetween)
/*  327 */       return 19; 
/*  328 */     if (filter instanceof org.opengis.filter.PropertyIsEqualTo)
/*  328 */       return 14; 
/*  329 */     if (filter instanceof org.opengis.filter.PropertyIsGreaterThan)
/*  329 */       return 16; 
/*  330 */     if (filter instanceof org.opengis.filter.PropertyIsGreaterThanOrEqualTo)
/*  330 */       return 18; 
/*  331 */     if (filter instanceof org.opengis.filter.PropertyIsLessThan)
/*  331 */       return 15; 
/*  332 */     if (filter instanceof org.opengis.filter.PropertyIsLessThanOrEqualTo)
/*  332 */       return 17; 
/*  333 */     if (filter instanceof org.opengis.filter.PropertyIsNotEqualTo)
/*  333 */       return 23; 
/*  334 */     if (filter instanceof org.opengis.filter.Id)
/*  334 */       return 22; 
/*  335 */     if (filter instanceof org.opengis.filter.spatial.BBOX)
/*  335 */       return 4; 
/*  336 */     if (filter instanceof org.opengis.filter.spatial.Beyond)
/*  336 */       return 13; 
/*  337 */     if (filter instanceof org.opengis.filter.spatial.Contains)
/*  337 */       return 11; 
/*  338 */     if (filter instanceof org.opengis.filter.spatial.Crosses)
/*  338 */       return 9; 
/*  339 */     if (filter instanceof org.opengis.filter.spatial.Disjoint)
/*  339 */       return 6; 
/*  340 */     if (filter instanceof org.opengis.filter.spatial.DWithin)
/*  340 */       return 24; 
/*  341 */     if (filter instanceof org.opengis.filter.spatial.Equals)
/*  341 */       return 5; 
/*  342 */     if (filter instanceof org.opengis.filter.spatial.Intersects)
/*  342 */       return 7; 
/*  343 */     if (filter instanceof org.opengis.filter.spatial.Overlaps)
/*  343 */       return 12; 
/*  344 */     if (filter instanceof org.opengis.filter.spatial.Touches)
/*  344 */       return 8; 
/*  345 */     if (filter instanceof org.opengis.filter.spatial.Within)
/*  345 */       return 10; 
/*  346 */     if (filter instanceof org.opengis.filter.PropertyIsLike)
/*  346 */       return 20; 
/*  347 */     if (filter instanceof And)
/*  347 */       return 2; 
/*  348 */     if (filter instanceof Not)
/*  348 */       return 3; 
/*  349 */     if (filter instanceof Or)
/*  349 */       return 1; 
/*  350 */     if (filter instanceof org.opengis.filter.PropertyIsNull)
/*  350 */       return 21; 
/*  352 */     if (filter instanceof Filter)
/*  353 */       return 0; 
/*  355 */     return 0;
/*      */   }
/*      */   
/*      */   public static int asInt(Expression expr) {
/*  366 */     if (expr == null)
/*  366 */       return -1; 
/*      */     try {
/*  368 */       Integer number = (Integer)expr.evaluate(null, Integer.class);
/*  369 */       if (number == null)
/*  370 */         return -1; 
/*  372 */       return number.intValue();
/*  374 */     } catch (NullPointerException npe) {
/*  375 */       return -1;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static String asString(Expression expr) {
/*  409 */     if (expr == null)
/*  409 */       return null; 
/*      */     try {
/*  411 */       return (String)expr.evaluate(null, String.class);
/*  413 */     } catch (NullPointerException npe) {
/*  415 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static double asDouble(Expression expr) {
/*  425 */     if (expr == null)
/*  426 */       return Double.NaN; 
/*      */     try {
/*  429 */       Double number = (Double)expr.evaluate(null, Double.class);
/*  430 */       if (number == null)
/*  431 */         return Double.NaN; 
/*  433 */       return number.doubleValue();
/*  435 */     } catch (NullPointerException npe) {
/*  437 */       return Double.NaN;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static <T> T asType(Expression expr, Class<T> TYPE) {
/*  464 */     if (expr == null)
/*  465 */       return null; 
/*  470 */     if (expr instanceof Literal) {
/*  471 */       Literal literal = (Literal)expr;
/*  472 */       return (T)literal.evaluate(null, TYPE);
/*      */     } 
/*  474 */     if (expr instanceof Function) {
/*  475 */       Function function = (Function)expr;
/*  476 */       List<Expression> params = function.getParameters();
/*  477 */       if (params != null && params.size() != 0)
/*  478 */         for (int i = 0; i < params.size(); i++) {
/*  479 */           Expression e = params.get(i);
/*  480 */           T value = asType(e, TYPE);
/*  482 */           if (value != null)
/*  483 */             return value; 
/*      */         }  
/*      */     } else {
/*      */       try {
/*  490 */         T value = (T)expr.evaluate(null, TYPE);
/*  492 */         if (TYPE.isInstance(value))
/*  493 */           return value; 
/*  495 */       } catch (NullPointerException expected) {
/*  496 */         return null;
/*  497 */       } catch (Throwable ignore) {}
/*      */     } 
/*  500 */     return null;
/*      */   }
/*      */   
/*      */   public static double number(Object value) {
/*  523 */     if (value == null)
/*  523 */       return Double.NaN; 
/*  524 */     if (value instanceof Number) {
/*  525 */       Number number = (Number)value;
/*  526 */       return number.doubleValue();
/*      */     } 
/*  528 */     if (value instanceof String) {
/*  529 */       String text = (String)value;
/*      */       try {
/*  531 */         Number number = gets(text, Number.class);
/*  532 */         return number.doubleValue();
/*  533 */       } catch (Throwable e) {
/*  534 */         throw new IllegalArgumentException("Unable to decode '" + text + "' as a number");
/*      */       } 
/*      */     } 
/*  537 */     if (value instanceof Expression)
/*  538 */       throw new IllegalArgumentException("Cannot deal with un evaulated Expression"); 
/*  540 */     throw new IllegalArgumentException("Unable to evaulate " + value.getClass() + " in a numeric context");
/*      */   }
/*      */   
/*      */   public static <T> T gets(String text, Class<T> TYPE) throws Throwable {
/*  573 */     if (text == null)
/*  574 */       return null; 
/*  576 */     if (TYPE == String.class)
/*  577 */       return TYPE.cast(text); 
/*  579 */     if (TYPE == Integer.class)
/*  580 */       return TYPE.cast(Integer.decode(text)); 
/*  582 */     if (TYPE == Double.class)
/*  583 */       return TYPE.cast(Double.valueOf(text)); 
/*  585 */     if (TYPE == Number.class)
/*      */       try {
/*  587 */         return TYPE.cast(Double.valueOf(text));
/*  588 */       } catch (NumberFormatException ignore) {
/*  590 */         return TYPE.cast(Integer.decode(text));
/*      */       }  
/*  592 */     if (TYPE == Color.class)
/*  593 */       return TYPE.cast(new Color(Integer.decode(text).intValue())); 
/*  596 */     Object value = Converters.convert(text, TYPE);
/*  597 */     if (value != null)
/*  598 */       return TYPE.cast(value); 
/*      */     try {
/*  602 */       Constructor<T> create = TYPE.getConstructor(new Class[] { String.class });
/*  603 */       return create.newInstance(new Object[] { text });
/*  604 */     } catch (SecurityException e) {
/*      */     
/*  606 */     } catch (NoSuchMethodException e) {
/*      */     
/*  608 */     } catch (IllegalArgumentException e) {
/*      */     
/*  610 */     } catch (InstantiationException e) {
/*      */     
/*  613 */     } catch (IllegalAccessException e) {
/*      */     
/*  615 */     } catch (InvocationTargetException e) {
/*  618 */       throw e.getCause();
/*      */     } 
/*  620 */     return null;
/*      */   }
/*      */   
/*      */   public static String puts(double number) {
/*  634 */     if (Math.rint(number) == number)
/*  635 */       return Integer.toString((int)number); 
/*  637 */     return Double.toString(number);
/*      */   }
/*      */   
/*      */   public static String puts(Object obj) {
/*  649 */     if (obj == null)
/*  650 */       return null; 
/*  652 */     if (obj instanceof String)
/*  653 */       return (String)obj; 
/*  655 */     if (obj instanceof Color) {
/*  656 */       Color color = (Color)obj;
/*  657 */       return puts(color);
/*      */     } 
/*  659 */     if (obj instanceof Number) {
/*  660 */       Number number = (Number)obj;
/*  661 */       return puts(number.doubleValue());
/*      */     } 
/*  663 */     String text = (String)Converters.convert(obj, String.class);
/*  664 */     if (text != null)
/*  665 */       return text; 
/*  667 */     return obj.toString();
/*      */   }
/*      */   
/*      */   public static String puts(Color color) {
/*  681 */     String redCode = Integer.toHexString(color.getRed());
/*  682 */     String greenCode = Integer.toHexString(color.getGreen());
/*  683 */     String blueCode = Integer.toHexString(color.getBlue());
/*  685 */     if (redCode.length() == 1)
/*  686 */       redCode = "0" + redCode; 
/*  687 */     if (greenCode.length() == 1)
/*  688 */       greenCode = "0" + greenCode; 
/*  689 */     if (blueCode.length() == 1)
/*  690 */       blueCode = "0" + blueCode; 
/*  692 */     return "#" + redCode + greenCode + blueCode;
/*      */   }
/*      */   
/*      */   public Filter remove(Filter baseFilter, Filter targetFilter) {
/*  730 */     return remove(baseFilter, targetFilter, true);
/*      */   }
/*      */   
/*      */   public static Filter removeFilter(Filter baseFilter, Filter targetFilter) {
/*  734 */     return STATIC.remove(baseFilter, targetFilter);
/*      */   }
/*      */   
/*      */   public Filter remove(Filter baseFilter, final Filter targetFilter, boolean recurse) {
/*  747 */     if (baseFilter == null)
/*  749 */       return baseFilter; 
/*  751 */     if (targetFilter == null)
/*  753 */       return baseFilter; 
/*  755 */     if (baseFilter.equals(targetFilter))
/*  757 */       return (Filter)Filter.INCLUDE; 
/*  759 */     if (!(baseFilter instanceof BinaryLogicOperator))
/*  760 */       return baseFilter; 
/*  762 */     if (recurse) {
/*  763 */       DuplicatingFilterVisitor remove = new DuplicatingFilterVisitor() {
/*      */           public Object visit(Or filter, Object extraData) {
/*  765 */             List<Filter> newChildren = children((BinaryLogicOperator)filter, targetFilter, extraData);
/*  766 */             if (newChildren.isEmpty())
/*  769 */               return Filter.EXCLUDE; 
/*  770 */             if (newChildren.size() == 1)
/*  771 */               return newChildren.get(0); 
/*  773 */             return getFactory(extraData).or(newChildren);
/*      */           }
/*      */           
/*      */           public Object visit(And filter, Object extraData) {
/*  777 */             List<Filter> newChildren = children((BinaryLogicOperator)filter, targetFilter, extraData);
/*  778 */             if (newChildren.isEmpty())
/*  781 */               return Filter.INCLUDE; 
/*  782 */             if (newChildren.size() == 1)
/*  783 */               return newChildren.get(0); 
/*  785 */             return getFactory(extraData).and(newChildren);
/*      */           }
/*      */           
/*      */           private List<Filter> children(BinaryLogicOperator filter, Filter targetFilter, Object extraData) {
/*  790 */             List<Filter> children = filter.getChildren();
/*  791 */             List<Filter> newChildren = new ArrayList<Filter>();
/*  792 */             for (Iterator<Filter> iter = children.iterator(); iter.hasNext(); ) {
/*  793 */               Filter child = iter.next();
/*  794 */               if (targetFilter.equals(child))
/*      */                 continue; 
/*  797 */               if (child != null) {
/*  798 */                 Filter newChild = (Filter)child.accept((FilterVisitor)this, extraData);
/*  799 */                 newChildren.add(newChild);
/*      */               } 
/*      */             } 
/*  802 */             return newChildren;
/*      */           }
/*      */         };
/*  805 */       return (Filter)baseFilter.accept((FilterVisitor)remove, this.ff);
/*      */     } 
/*  808 */     BinaryLogicOperator blo = (BinaryLogicOperator)baseFilter;
/*  809 */     List<Filter> children = blo.getChildren();
/*  810 */     if (children == null)
/*  811 */       children = Collections.emptyList(); 
/*  814 */     List<Filter> copy = new ArrayList<Filter>(children.size());
/*  815 */     for (Filter filter : children) {
/*  816 */       if (targetFilter.equals(filter))
/*      */         continue; 
/*  819 */       copy.add(filter);
/*      */     } 
/*  821 */     if (copy.isEmpty()) {
/*  822 */       if (baseFilter instanceof And)
/*  825 */         return (Filter)Filter.INCLUDE; 
/*  827 */       if (baseFilter instanceof Or)
/*  830 */         return (Filter)Filter.EXCLUDE; 
/*  833 */       return (Filter)Filter.EXCLUDE;
/*      */     } 
/*  836 */     if (copy.size() == 1)
/*  837 */       return copy.get(0); 
/*  839 */     if (baseFilter instanceof And)
/*  840 */       return (Filter)this.ff.and(children); 
/*  842 */     if (baseFilter instanceof Or)
/*  843 */       return (Filter)this.ff.or(children); 
/*  846 */     return (Filter)Filter.INCLUDE;
/*      */   }
/*      */   
/*      */   public static Filter removeFilter(Filter baseFilter, Filter targetFilter, boolean recurse) {
/*  852 */     return STATIC.remove(baseFilter, targetFilter, recurse);
/*      */   }
/*      */   
/*      */   public Set<String> attributeNames(Filter filter) {
/*  864 */     if (filter == null)
/*  865 */       return Collections.emptySet(); 
/*  867 */     FilterAttributeExtractor extractor = new FilterAttributeExtractor();
/*  868 */     filter.accept((FilterVisitor)extractor, new HashSet());
/*  869 */     return extractor.getAttributeNameSet();
/*      */   }
/*      */   
/*      */   public static String[] attributeNames(Filter filter, SimpleFeatureType featureType) {
/*  880 */     if (filter == null)
/*  881 */       return new String[0]; 
/*  883 */     FilterAttributeExtractor attExtractor = new FilterAttributeExtractor(featureType);
/*  884 */     filter.accept((FilterVisitor)attExtractor, null);
/*  885 */     String[] attributeNames = attExtractor.getAttributeNames();
/*  886 */     return attributeNames;
/*      */   }
/*      */   
/*      */   public static Set<PropertyName> propertyNames(Filter filter, SimpleFeatureType featureType) {
/*  897 */     if (filter == null)
/*  898 */       return Collections.emptySet(); 
/*  900 */     FilterAttributeExtractor attExtractor = new FilterAttributeExtractor(featureType);
/*  901 */     filter.accept((FilterVisitor)attExtractor, null);
/*  902 */     Set<PropertyName> propertyNames = attExtractor.getPropertyNameSet();
/*  903 */     return propertyNames;
/*      */   }
/*      */   
/*      */   public static Set<PropertyName> propertyNames(Filter filter) {
/*  910 */     return propertyNames(filter, (SimpleFeatureType)null);
/*      */   }
/*      */   
/*      */   public static Set<PropertyName> propertyNames(Expression expression, SimpleFeatureType featureType) {
/*  922 */     if (expression == null)
/*  923 */       return Collections.emptySet(); 
/*  925 */     FilterAttributeExtractor attExtractor = new FilterAttributeExtractor(featureType);
/*  926 */     expression.accept((ExpressionVisitor)attExtractor, null);
/*  927 */     Set<PropertyName> propertyNames = attExtractor.getPropertyNameSet();
/*  928 */     return propertyNames;
/*      */   }
/*      */   
/*      */   public static Set<PropertyName> propertyNames(Expression expression) {
/*  935 */     return propertyNames(expression, (SimpleFeatureType)null);
/*      */   }
/*      */   
/*      */   static boolean uses(Filter filter, final String propertyName) {
/*  947 */     if (filter == null)
/*  948 */       return false; 
/*      */     class SearchFilterVisitor extends AbstractSearchFilterVisitor {
/*      */       protected boolean found(Object data) {
/*  952 */         return (Boolean.TRUE == data);
/*      */       }
/*      */       
/*      */       public Object visit(PropertyName name, Object data) {
/*  955 */         if (Utilities.equals(name.getPropertyName(), propertyName))
/*  956 */           return Boolean.valueOf(true); 
/*  958 */         return data;
/*      */       }
/*      */     };
/*  961 */     SearchFilterVisitor search = new SearchFilterVisitor();
/*  962 */     boolean found = ((Boolean)filter.accept((FilterVisitor)search, Boolean.valueOf(false))).booleanValue();
/*  963 */     return found;
/*      */   }
/*      */   
/*      */   public static boolean hasChildren(Filter filter) {
/*  979 */     return (filter instanceof BinaryLogicOperator || filter instanceof Not);
/*      */   }
/*      */   
/*      */   public static ArrayList<Filter> children(Filter filter) {
/* 1003 */     return children(filter, false);
/*      */   }
/*      */   
/*      */   public static ArrayList<Filter> children(Filter filter, boolean all) {
/* 1030 */     final ArrayList<Filter> children = new ArrayList<Filter>();
/* 1031 */     if (filter == null)
/* 1032 */       return children; 
/* 1034 */     if (all) {
/* 1035 */       filter.accept((FilterVisitor)new DefaultFilterVisitor() {
/*      */             public Object visit(And filter, Object data) {
/* 1037 */               List<Filter> childList = filter.getChildren();
/* 1038 */               if (childList != null)
/* 1039 */                 for (Filter child : childList) {
/* 1040 */                   if (child == null)
/*      */                     continue; 
/* 1042 */                   children.add(child);
/* 1043 */                   data = child.accept((FilterVisitor)this, data);
/*      */                 }  
/* 1046 */               return data;
/*      */             }
/*      */             
/*      */             public Object visit(Or filter, Object data) {
/* 1049 */               List<Filter> childList = filter.getChildren();
/* 1050 */               if (childList != null)
/* 1051 */                 for (Filter child : childList) {
/* 1052 */                   if (child == null)
/*      */                     continue; 
/* 1054 */                   children.add(child);
/* 1055 */                   data = child.accept((FilterVisitor)this, data);
/*      */                 }  
/* 1058 */               return data;
/*      */             }
/*      */             
/*      */             public Object visit(Not filter, Object data) {
/* 1061 */               Filter child = filter.getFilter();
/* 1062 */               if (child != null) {
/* 1063 */                 children.add(child);
/* 1064 */                 data = child.accept((FilterVisitor)this, data);
/*      */               } 
/* 1066 */               return data;
/*      */             }
/*      */           }null);
/*      */     } else {
/* 1071 */       if (filter instanceof Not) {
/* 1072 */         Not not = (Not)filter;
/* 1073 */         if (not.getFilter() != null)
/* 1074 */           children.add(not.getFilter()); 
/*      */       } 
/* 1077 */       if (filter instanceof BinaryLogicOperator) {
/* 1078 */         BinaryLogicOperator parent = (BinaryLogicOperator)filter;
/* 1079 */         List<Filter> reviewChildren = parent.getChildren();
/* 1080 */         if (reviewChildren != null)
/* 1081 */           for (Filter child : reviewChildren) {
/* 1082 */             if (child != null)
/* 1083 */               children.add(child); 
/*      */           }  
/*      */       } 
/*      */     } 
/* 1089 */     return children;
/*      */   }
/*      */   
/*      */   public static <T extends Filter> T search(Filter filter, Class<T> filterType, String propertyName) {
/* 1101 */     List<Filter> allBase = children(filter);
/* 1102 */     for (Filter base : allBase) {
/* 1103 */       if (filterType.isInstance(base) && uses(base, propertyName))
/* 1104 */         return filterType.cast(base); 
/*      */     } 
/* 1107 */     return null;
/*      */   }
/*      */   
/*      */   public static String findPropertyName(Filter filter) {
/* 1117 */     if (filter == null)
/* 1118 */       return null; 
/*      */     class SearchFilterVisitor extends AbstractSearchFilterVisitor {
/*      */       protected boolean found(Object data) {
/* 1122 */         return (data != null);
/*      */       }
/*      */       
/*      */       public Object visit(PropertyName name, Object data) {
/* 1125 */         return name.getPropertyName();
/*      */       }
/*      */     };
/* 1128 */     SearchFilterVisitor search = new SearchFilterVisitor();
/* 1129 */     return (String)filter.accept((FilterVisitor)search, null);
/*      */   }
/*      */   
/*      */   static <T extends Filter> List<T> findAllByTypeAndName(Filter filter, Class<T> filterType, String property) {
/* 1141 */     List<T> retVal = new ArrayList<T>();
/* 1142 */     List<Filter> allBase = children(filter);
/* 1143 */     allBase.add(0, filter);
/* 1144 */     for (Filter base : allBase) {
/* 1145 */       if (filterType.isInstance(base) && uses(base, property))
/* 1146 */         retVal.add(filterType.cast(base)); 
/*      */     } 
/* 1149 */     return retVal;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\Filters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */