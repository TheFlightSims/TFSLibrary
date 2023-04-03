/*      */ package org.apache.commons.math3.linear;
/*      */ 
/*      */ import java.util.Iterator;
/*      */ import java.util.NoSuchElementException;
/*      */ import org.apache.commons.math3.analysis.BivariateFunction;
/*      */ import org.apache.commons.math3.analysis.FunctionUtils;
/*      */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*      */ import org.apache.commons.math3.analysis.function.Add;
/*      */ import org.apache.commons.math3.analysis.function.Divide;
/*      */ import org.apache.commons.math3.analysis.function.Multiply;
/*      */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*      */ import org.apache.commons.math3.exception.MathArithmeticException;
/*      */ import org.apache.commons.math3.exception.MathUnsupportedOperationException;
/*      */ import org.apache.commons.math3.exception.OutOfRangeException;
/*      */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*      */ import org.apache.commons.math3.util.FastMath;
/*      */ 
/*      */ public abstract class RealVector {
/*      */   public abstract int getDimension();
/*      */   
/*      */   public abstract double getEntry(int paramInt);
/*      */   
/*      */   public abstract void setEntry(int paramInt, double paramDouble);
/*      */   
/*      */   public void addToEntry(int index, double increment) {
/*   98 */     setEntry(index, getEntry(index) + increment);
/*      */   }
/*      */   
/*      */   public abstract RealVector append(RealVector paramRealVector);
/*      */   
/*      */   public abstract RealVector append(double paramDouble);
/*      */   
/*      */   public abstract RealVector getSubVector(int paramInt1, int paramInt2);
/*      */   
/*      */   public abstract void setSubVector(int paramInt, RealVector paramRealVector);
/*      */   
/*      */   public abstract boolean isNaN();
/*      */   
/*      */   public abstract boolean isInfinite();
/*      */   
/*      */   protected void checkVectorDimensions(RealVector v) {
/*  162 */     checkVectorDimensions(v.getDimension());
/*      */   }
/*      */   
/*      */   protected void checkVectorDimensions(int n) {
/*  173 */     int d = getDimension();
/*  174 */     if (d != n)
/*  175 */       throw new DimensionMismatchException(d, n); 
/*      */   }
/*      */   
/*      */   protected void checkIndex(int index) {
/*  186 */     if (index < 0 || index >= getDimension())
/*  188 */       throw new OutOfRangeException(LocalizedFormats.INDEX, Integer.valueOf(index), Integer.valueOf(0), Integer.valueOf(getDimension() - 1)); 
/*      */   }
/*      */   
/*      */   public RealVector add(RealVector v) {
/*  203 */     RealVector result = v.copy();
/*  204 */     Iterator<Entry> it = sparseIterator();
/*      */     Entry e;
/*  206 */     while (it.hasNext() && (e = it.next()) != null) {
/*  207 */       int index = e.getIndex();
/*  208 */       result.setEntry(index, e.getValue() + result.getEntry(index));
/*      */     } 
/*  210 */     return result;
/*      */   }
/*      */   
/*      */   public RealVector subtract(RealVector v) {
/*  223 */     RealVector result = v.copy();
/*  224 */     Iterator<Entry> it = sparseIterator();
/*      */     Entry e;
/*  226 */     while (it.hasNext() && (e = it.next()) != null) {
/*  227 */       int index = e.getIndex();
/*  228 */       result.setEntry(index, e.getValue() - result.getEntry(index));
/*      */     } 
/*  230 */     return result;
/*      */   }
/*      */   
/*      */   public RealVector mapAdd(double d) {
/*  241 */     return copy().mapAddToSelf(d);
/*      */   }
/*      */   
/*      */   public RealVector mapAddToSelf(double d) {
/*  252 */     if (d != 0.0D)
/*  253 */       return mapToSelf(FunctionUtils.fix2ndArgument((BivariateFunction)new Add(), d)); 
/*  255 */     return this;
/*      */   }
/*      */   
/*      */   public abstract RealVector copy();
/*      */   
/*      */   public double dotProduct(RealVector v) {
/*  274 */     checkVectorDimensions(v);
/*  275 */     double d = 0.0D;
/*  276 */     Iterator<Entry> it = sparseIterator();
/*      */     Entry e;
/*  278 */     while (it.hasNext() && (e = it.next()) != null)
/*  279 */       d += e.getValue() * v.getEntry(e.getIndex()); 
/*  281 */     return d;
/*      */   }
/*      */   
/*      */   public double cosine(RealVector v) {
/*  292 */     double norm = getNorm();
/*  293 */     double vNorm = v.getNorm();
/*  295 */     if (norm == 0.0D || vNorm == 0.0D)
/*  297 */       throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]); 
/*  299 */     return dotProduct(v) / norm * vNorm;
/*      */   }
/*      */   
/*      */   public abstract RealVector ebeDivide(RealVector paramRealVector);
/*      */   
/*      */   public abstract RealVector ebeMultiply(RealVector paramRealVector);
/*      */   
/*      */   public double getDistance(RealVector v) {
/*  337 */     checkVectorDimensions(v);
/*  338 */     double d = 0.0D;
/*  339 */     Iterator<Entry> it = iterator();
/*      */     Entry e;
/*  341 */     while (it.hasNext() && (e = it.next()) != null) {
/*  342 */       double diff = e.getValue() - v.getEntry(e.getIndex());
/*  343 */       d += diff * diff;
/*      */     } 
/*  345 */     return FastMath.sqrt(d);
/*      */   }
/*      */   
/*      */   public double getNorm() {
/*  359 */     double sum = 0.0D;
/*  360 */     Iterator<Entry> it = sparseIterator();
/*      */     Entry e;
/*  362 */     while (it.hasNext() && (e = it.next()) != null) {
/*  363 */       double value = e.getValue();
/*  364 */       sum += value * value;
/*      */     } 
/*  366 */     return FastMath.sqrt(sum);
/*      */   }
/*      */   
/*      */   public double getL1Norm() {
/*  380 */     double norm = 0.0D;
/*  381 */     Iterator<Entry> it = sparseIterator();
/*      */     Entry e;
/*  383 */     while (it.hasNext() && (e = it.next()) != null)
/*  384 */       norm += FastMath.abs(e.getValue()); 
/*  386 */     return norm;
/*      */   }
/*      */   
/*      */   public double getLInfNorm() {
/*  400 */     double norm = 0.0D;
/*  401 */     Iterator<Entry> it = sparseIterator();
/*      */     Entry e;
/*  403 */     while (it.hasNext() && (e = it.next()) != null)
/*  404 */       norm = FastMath.max(norm, FastMath.abs(e.getValue())); 
/*  406 */     return norm;
/*      */   }
/*      */   
/*      */   public double getL1Distance(RealVector v) {
/*  421 */     checkVectorDimensions(v);
/*  422 */     double d = 0.0D;
/*  423 */     Iterator<Entry> it = iterator();
/*      */     Entry e;
/*  425 */     while (it.hasNext() && (e = it.next()) != null)
/*  426 */       d += FastMath.abs(e.getValue() - v.getEntry(e.getIndex())); 
/*  428 */     return d;
/*      */   }
/*      */   
/*      */   public double getLInfDistance(RealVector v) {
/*  446 */     checkVectorDimensions(v);
/*  447 */     double d = 0.0D;
/*  448 */     Iterator<Entry> it = iterator();
/*      */     Entry e;
/*  450 */     while (it.hasNext() && (e = it.next()) != null)
/*  451 */       d = FastMath.max(FastMath.abs(e.getValue() - v.getEntry(e.getIndex())), d); 
/*  453 */     return d;
/*      */   }
/*      */   
/*      */   public int getMinIndex() {
/*  463 */     int minIndex = -1;
/*  464 */     double minValue = Double.POSITIVE_INFINITY;
/*  465 */     Iterator<Entry> iterator = iterator();
/*  466 */     while (iterator.hasNext()) {
/*  467 */       Entry entry = iterator.next();
/*  468 */       if (entry.getValue() <= minValue) {
/*  469 */         minIndex = entry.getIndex();
/*  470 */         minValue = entry.getValue();
/*      */       } 
/*      */     } 
/*  473 */     return minIndex;
/*      */   }
/*      */   
/*      */   public double getMinValue() {
/*  483 */     int minIndex = getMinIndex();
/*  484 */     return (minIndex < 0) ? Double.NaN : getEntry(minIndex);
/*      */   }
/*      */   
/*      */   public int getMaxIndex() {
/*  494 */     int maxIndex = -1;
/*  495 */     double maxValue = Double.NEGATIVE_INFINITY;
/*  496 */     Iterator<Entry> iterator = iterator();
/*  497 */     while (iterator.hasNext()) {
/*  498 */       Entry entry = iterator.next();
/*  499 */       if (entry.getValue() >= maxValue) {
/*  500 */         maxIndex = entry.getIndex();
/*  501 */         maxValue = entry.getValue();
/*      */       } 
/*      */     } 
/*  504 */     return maxIndex;
/*      */   }
/*      */   
/*      */   public double getMaxValue() {
/*  514 */     int maxIndex = getMaxIndex();
/*  515 */     return (maxIndex < 0) ? Double.NaN : getEntry(maxIndex);
/*      */   }
/*      */   
/*      */   public RealVector mapMultiply(double d) {
/*  527 */     return copy().mapMultiplyToSelf(d);
/*      */   }
/*      */   
/*      */   public RealVector mapMultiplyToSelf(double d) {
/*  538 */     return mapToSelf(FunctionUtils.fix2ndArgument((BivariateFunction)new Multiply(), d));
/*      */   }
/*      */   
/*      */   public RealVector mapSubtract(double d) {
/*  549 */     return copy().mapSubtractToSelf(d);
/*      */   }
/*      */   
/*      */   public RealVector mapSubtractToSelf(double d) {
/*  560 */     return mapAddToSelf(-d);
/*      */   }
/*      */   
/*      */   public RealVector mapDivide(double d) {
/*  571 */     return copy().mapDivideToSelf(d);
/*      */   }
/*      */   
/*      */   public RealVector mapDivideToSelf(double d) {
/*  582 */     return mapToSelf(FunctionUtils.fix2ndArgument((BivariateFunction)new Divide(), d));
/*      */   }
/*      */   
/*      */   public RealMatrix outerProduct(RealVector v) {
/*      */     RealMatrix product;
/*  593 */     if (v instanceof SparseRealVector || this instanceof SparseRealVector) {
/*  594 */       product = new OpenMapRealMatrix(getDimension(), v.getDimension());
/*      */     } else {
/*  597 */       product = new Array2DRowRealMatrix(getDimension(), v.getDimension());
/*      */     } 
/*  600 */     Iterator<Entry> thisIt = sparseIterator();
/*  601 */     Entry thisE = null;
/*  602 */     while (thisIt.hasNext() && (thisE = thisIt.next()) != null) {
/*  603 */       Iterator<Entry> otherIt = v.sparseIterator();
/*  604 */       Entry otherE = null;
/*  605 */       while (otherIt.hasNext() && (otherE = otherIt.next()) != null)
/*  606 */         product.setEntry(thisE.getIndex(), otherE.getIndex(), thisE.getValue() * otherE.getValue()); 
/*      */     } 
/*  611 */     return product;
/*      */   }
/*      */   
/*      */   public abstract RealVector projection(RealVector paramRealVector);
/*      */   
/*      */   public void set(double value) {
/*  631 */     Iterator<Entry> it = iterator();
/*  632 */     Entry e = null;
/*  633 */     while (it.hasNext() && (e = it.next()) != null)
/*  634 */       e.setValue(value); 
/*      */   }
/*      */   
/*      */   public double[] toArray() {
/*  646 */     int dim = getDimension();
/*  647 */     double[] values = new double[dim];
/*  648 */     for (int i = 0; i < dim; i++)
/*  649 */       values[i] = getEntry(i); 
/*  651 */     return values;
/*      */   }
/*      */   
/*      */   public RealVector unitVector() {
/*  662 */     RealVector copy = copy();
/*  663 */     copy.unitize();
/*  664 */     return copy;
/*      */   }
/*      */   
/*      */   public void unitize() {
/*  675 */     mapDivideToSelf(getNorm());
/*      */   }
/*      */   
/*      */   public Iterator<Entry> sparseIterator() {
/*  690 */     return new SparseEntryIterator();
/*      */   }
/*      */   
/*      */   public Iterator<Entry> iterator() {
/*  700 */     final int dim = getDimension();
/*  701 */     return new Iterator<Entry>() {
/*  704 */         private int i = 0;
/*      */         
/*  707 */         private RealVector.Entry e = new RealVector.Entry();
/*      */         
/*      */         public boolean hasNext() {
/*  711 */           return (this.i < dim);
/*      */         }
/*      */         
/*      */         public RealVector.Entry next() {
/*  716 */           this.e.setIndex(this.i++);
/*  717 */           return this.e;
/*      */         }
/*      */         
/*      */         public void remove() {
/*  722 */           throw new MathUnsupportedOperationException();
/*      */         }
/*      */       };
/*      */   }
/*      */   
/*      */   public RealVector map(UnivariateFunction function) {
/*  738 */     return copy().mapToSelf(function);
/*      */   }
/*      */   
/*      */   public RealVector mapToSelf(UnivariateFunction function) {
/*  755 */     Iterator<Entry> it = (function.value(0.0D) == 0.0D) ? sparseIterator() : iterator();
/*      */     Entry e;
/*  757 */     while (it.hasNext() && (e = it.next()) != null)
/*  758 */       e.setValue(function.value(e.getValue())); 
/*  760 */     return this;
/*      */   }
/*      */   
/*      */   public RealVector combine(double a, double b, RealVector y) {
/*  777 */     return copy().combineToSelf(a, b, y);
/*      */   }
/*      */   
/*      */   public RealVector combineToSelf(double a, double b, RealVector y) {
/*  793 */     checkVectorDimensions(y);
/*  794 */     for (int i = 0; i < getDimension(); i++) {
/*  795 */       double xi = getEntry(i);
/*  796 */       double yi = y.getEntry(i);
/*  797 */       setEntry(i, a * xi + b * yi);
/*      */     } 
/*  799 */     return this;
/*      */   }
/*      */   
/*      */   protected class Entry {
/*      */     private int index;
/*      */     
/*      */     public Entry() {
/*  811 */       setIndex(0);
/*      */     }
/*      */     
/*      */     public double getValue() {
/*  820 */       return RealVector.this.getEntry(getIndex());
/*      */     }
/*      */     
/*      */     public void setValue(double value) {
/*  829 */       RealVector.this.setEntry(getIndex(), value);
/*      */     }
/*      */     
/*      */     public int getIndex() {
/*  838 */       return this.index;
/*      */     }
/*      */     
/*      */     public void setIndex(int index) {
/*  847 */       this.index = index;
/*      */     }
/*      */   }
/*      */   
/*      */   protected class SparseEntryIterator implements Iterator<Entry> {
/*  874 */     private final int dim = RealVector.this.getDimension();
/*      */     
/*  875 */     private RealVector.Entry current = new RealVector.Entry();
/*      */     
/*  876 */     private RealVector.Entry next = new RealVector.Entry();
/*      */     
/*      */     protected SparseEntryIterator() {
/*  877 */       if (this.next.getValue() == 0.0D)
/*  878 */         advance(this.next); 
/*      */     }
/*      */     
/*      */     protected void advance(RealVector.Entry e) {
/*  888 */       if (e == null)
/*      */         return; 
/*      */       do {
/*  892 */         e.setIndex(e.getIndex() + 1);
/*  893 */       } while (e.getIndex() < this.dim && e.getValue() == 0.0D);
/*  894 */       if (e.getIndex() >= this.dim)
/*  895 */         e.setIndex(-1); 
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  901 */       return (this.next.getIndex() >= 0);
/*      */     }
/*      */     
/*      */     public RealVector.Entry next() {
/*  906 */       int index = this.next.getIndex();
/*  907 */       if (index < 0)
/*  908 */         throw new NoSuchElementException(); 
/*  910 */       this.current.setIndex(index);
/*  911 */       advance(this.next);
/*  912 */       return this.current;
/*      */     }
/*      */     
/*      */     public void remove() {
/*  917 */       throw new MathUnsupportedOperationException();
/*      */     }
/*      */   }
/*      */   
/*      */   public static RealVector unmodifiableRealVector(final RealVector v) {
/*  949 */     return new RealVector() {
/*      */         public RealVector mapToSelf(UnivariateFunction function) {
/*  953 */           throw new MathUnsupportedOperationException();
/*      */         }
/*      */         
/*      */         public RealVector map(UnivariateFunction function) {
/*  959 */           return v.map(function);
/*      */         }
/*      */         
/*      */         public Iterator<RealVector.Entry> iterator() {
/*  965 */           final Iterator<RealVector.Entry> i = v.iterator();
/*  966 */           return new Iterator<RealVector.Entry>() {
/*  968 */               private final RealVector.null.UnmodifiableEntry e = new RealVector.null.UnmodifiableEntry();
/*      */               
/*      */               public boolean hasNext() {
/*  972 */                 return i.hasNext();
/*      */               }
/*      */               
/*      */               public RealVector.Entry next() {
/*  977 */                 this.e.setIndex(((RealVector.Entry)i.next()).getIndex());
/*  978 */                 return this.e;
/*      */               }
/*      */               
/*      */               public void remove() {
/*  983 */                 throw new MathUnsupportedOperationException();
/*      */               }
/*      */             };
/*      */         }
/*      */         
/*      */         public Iterator<RealVector.Entry> sparseIterator() {
/*  991 */           final Iterator<RealVector.Entry> i = v.sparseIterator();
/*  993 */           return new Iterator<RealVector.Entry>() {
/*  995 */               private final RealVector.null.UnmodifiableEntry e = new RealVector.null.UnmodifiableEntry();
/*      */               
/*      */               public boolean hasNext() {
/*  999 */                 return i.hasNext();
/*      */               }
/*      */               
/*      */               public RealVector.Entry next() {
/* 1004 */                 this.e.setIndex(((RealVector.Entry)i.next()).getIndex());
/* 1005 */                 return this.e;
/*      */               }
/*      */               
/*      */               public void remove() {
/* 1010 */                 throw new MathUnsupportedOperationException();
/*      */               }
/*      */             };
/*      */         }
/*      */         
/*      */         public RealVector copy() {
/* 1018 */           return v.copy();
/*      */         }
/*      */         
/*      */         public RealVector add(RealVector w) {
/* 1024 */           return v.add(w);
/*      */         }
/*      */         
/*      */         public RealVector subtract(RealVector w) {
/* 1030 */           return v.subtract(w);
/*      */         }
/*      */         
/*      */         public RealVector mapAdd(double d) {
/* 1036 */           return v.mapAdd(d);
/*      */         }
/*      */         
/*      */         public RealVector mapAddToSelf(double d) {
/* 1042 */           throw new MathUnsupportedOperationException();
/*      */         }
/*      */         
/*      */         public RealVector mapSubtract(double d) {
/* 1048 */           return v.mapSubtract(d);
/*      */         }
/*      */         
/*      */         public RealVector mapSubtractToSelf(double d) {
/* 1054 */           throw new MathUnsupportedOperationException();
/*      */         }
/*      */         
/*      */         public RealVector mapMultiply(double d) {
/* 1060 */           return v.mapMultiply(d);
/*      */         }
/*      */         
/*      */         public RealVector mapMultiplyToSelf(double d) {
/* 1066 */           throw new MathUnsupportedOperationException();
/*      */         }
/*      */         
/*      */         public RealVector mapDivide(double d) {
/* 1072 */           return v.mapDivide(d);
/*      */         }
/*      */         
/*      */         public RealVector mapDivideToSelf(double d) {
/* 1078 */           throw new MathUnsupportedOperationException();
/*      */         }
/*      */         
/*      */         public RealVector ebeMultiply(RealVector w) {
/* 1084 */           return v.ebeMultiply(w);
/*      */         }
/*      */         
/*      */         public RealVector ebeDivide(RealVector w) {
/* 1090 */           return v.ebeDivide(w);
/*      */         }
/*      */         
/*      */         public double dotProduct(RealVector w) {
/* 1096 */           return v.dotProduct(w);
/*      */         }
/*      */         
/*      */         public double cosine(RealVector w) {
/* 1102 */           return v.cosine(w);
/*      */         }
/*      */         
/*      */         public double getNorm() {
/* 1108 */           return v.getNorm();
/*      */         }
/*      */         
/*      */         public double getL1Norm() {
/* 1114 */           return v.getL1Norm();
/*      */         }
/*      */         
/*      */         public double getLInfNorm() {
/* 1120 */           return v.getLInfNorm();
/*      */         }
/*      */         
/*      */         public double getDistance(RealVector w) {
/* 1126 */           return v.getDistance(w);
/*      */         }
/*      */         
/*      */         public double getL1Distance(RealVector w) {
/* 1132 */           return v.getL1Distance(w);
/*      */         }
/*      */         
/*      */         public double getLInfDistance(RealVector w) {
/* 1138 */           return v.getLInfDistance(w);
/*      */         }
/*      */         
/*      */         public RealVector unitVector() {
/* 1144 */           return v.unitVector();
/*      */         }
/*      */         
/*      */         public void unitize() {
/* 1150 */           throw new MathUnsupportedOperationException();
/*      */         }
/*      */         
/*      */         public RealVector projection(RealVector w) {
/* 1156 */           return v.projection(w);
/*      */         }
/*      */         
/*      */         public RealMatrix outerProduct(RealVector w) {
/* 1162 */           return v.outerProduct(w);
/*      */         }
/*      */         
/*      */         public double getEntry(int index) {
/* 1168 */           return v.getEntry(index);
/*      */         }
/*      */         
/*      */         public void setEntry(int index, double value) {
/* 1174 */           throw new MathUnsupportedOperationException();
/*      */         }
/*      */         
/*      */         public void addToEntry(int index, double value) {
/* 1180 */           throw new MathUnsupportedOperationException();
/*      */         }
/*      */         
/*      */         public int getDimension() {
/* 1186 */           return v.getDimension();
/*      */         }
/*      */         
/*      */         public RealVector append(RealVector w) {
/* 1192 */           return v.append(w);
/*      */         }
/*      */         
/*      */         public RealVector append(double d) {
/* 1198 */           return v.append(d);
/*      */         }
/*      */         
/*      */         public RealVector getSubVector(int index, int n) {
/* 1204 */           return v.getSubVector(index, n);
/*      */         }
/*      */         
/*      */         public void setSubVector(int index, RealVector w) {
/* 1210 */           throw new MathUnsupportedOperationException();
/*      */         }
/*      */         
/*      */         public void set(double value) {
/* 1216 */           throw new MathUnsupportedOperationException();
/*      */         }
/*      */         
/*      */         public double[] toArray() {
/* 1222 */           return v.toArray();
/*      */         }
/*      */         
/*      */         public boolean isNaN() {
/* 1228 */           return v.isNaN();
/*      */         }
/*      */         
/*      */         public boolean isInfinite() {
/* 1234 */           return v.isInfinite();
/*      */         }
/*      */         
/*      */         public RealVector combine(double a, double b, RealVector y) {
/* 1240 */           return v.combine(a, b, y);
/*      */         }
/*      */         
/*      */         public RealVector combineToSelf(double a, double b, RealVector y) {
/* 1246 */           throw new MathUnsupportedOperationException();
/*      */         }
/*      */         
/*      */         class UnmodifiableEntry extends RealVector.Entry {
/*      */           public double getValue() {
/* 1254 */             return v.getEntry(getIndex());
/*      */           }
/*      */           
/*      */           public void setValue(double value) {
/* 1260 */             throw new MathUnsupportedOperationException();
/*      */           }
/*      */         }
/*      */       };
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\RealVector.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */