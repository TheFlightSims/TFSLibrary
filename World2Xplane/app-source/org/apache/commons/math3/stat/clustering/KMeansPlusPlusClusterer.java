/*     */ package org.apache.commons.math3.stat.clustering;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import org.apache.commons.math3.exception.ConvergenceException;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.stat.descriptive.moment.Variance;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class KMeansPlusPlusClusterer<T extends Clusterable<T>> {
/*     */   private final Random random;
/*     */   
/*     */   private final EmptyClusterStrategy emptyStrategy;
/*     */   
/*     */   public enum EmptyClusterStrategy {
/*  46 */     LARGEST_VARIANCE, LARGEST_POINTS_NUMBER, FARTHEST_POINT, ERROR;
/*     */   }
/*     */   
/*     */   public KMeansPlusPlusClusterer(Random random) {
/*  73 */     this(random, EmptyClusterStrategy.LARGEST_VARIANCE);
/*     */   }
/*     */   
/*     */   public KMeansPlusPlusClusterer(Random random, EmptyClusterStrategy emptyStrategy) {
/*  83 */     this.random = random;
/*  84 */     this.emptyStrategy = emptyStrategy;
/*     */   }
/*     */   
/*     */   public List<Cluster<T>> cluster(Collection<T> points, int k, int numTrials, int maxIterationsPerTrial) throws MathIllegalArgumentException {
/* 104 */     List<Cluster<T>> best = null;
/* 105 */     double bestVarianceSum = Double.POSITIVE_INFINITY;
/* 108 */     for (int i = 0; i < numTrials; i++) {
/* 111 */       List<Cluster<T>> clusters = cluster(points, k, maxIterationsPerTrial);
/* 114 */       double varianceSum = 0.0D;
/* 115 */       for (Cluster<T> cluster : clusters) {
/* 116 */         if (!cluster.getPoints().isEmpty()) {
/* 119 */           T center = cluster.getCenter();
/* 120 */           Variance stat = new Variance();
/* 121 */           for (Clusterable<T> clusterable : cluster.getPoints())
/* 122 */             stat.increment(clusterable.distanceFrom(center)); 
/* 124 */           varianceSum += stat.getResult();
/*     */         } 
/*     */       } 
/* 129 */       if (varianceSum <= bestVarianceSum) {
/* 131 */         best = clusters;
/* 132 */         bestVarianceSum = varianceSum;
/*     */       } 
/*     */     } 
/* 138 */     return best;
/*     */   }
/*     */   
/*     */   public List<Cluster<T>> cluster(Collection<T> points, int k, int maxIterations) throws MathIllegalArgumentException {
/* 158 */     MathUtils.checkNotNull(points);
/* 161 */     if (points.size() < k)
/* 162 */       throw new NumberIsTooSmallException(Integer.valueOf(points.size()), Integer.valueOf(k), false); 
/* 166 */     List<Cluster<T>> clusters = chooseInitialCenters(points, k, this.random);
/* 170 */     int[] assignments = new int[points.size()];
/* 171 */     assignPointsToClusters(clusters, points, assignments);
/* 174 */     int max = (maxIterations < 0) ? Integer.MAX_VALUE : maxIterations;
/* 175 */     for (int count = 0; count < max; count++) {
/* 176 */       boolean emptyCluster = false;
/* 177 */       List<Cluster<T>> newClusters = new ArrayList<Cluster<T>>();
/* 178 */       for (Cluster<T> cluster : clusters) {
/*     */         Clusterable clusterable;
/* 180 */         if (cluster.getPoints().isEmpty()) {
/*     */           T newCenter;
/* 181 */           switch (this.emptyStrategy) {
/*     */             case LARGEST_VARIANCE:
/* 183 */               newCenter = getPointFromLargestVarianceCluster(clusters);
/*     */               break;
/*     */             case LARGEST_POINTS_NUMBER:
/* 186 */               newCenter = getPointFromLargestNumberCluster(clusters);
/*     */               break;
/*     */             case FARTHEST_POINT:
/* 189 */               newCenter = getFarthestPoint(clusters);
/*     */               break;
/*     */             default:
/* 192 */               throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
/*     */           } 
/* 194 */           emptyCluster = true;
/*     */         } else {
/* 196 */           clusterable = cluster.getCenter().centroidOf(cluster.getPoints());
/*     */         } 
/* 198 */         newClusters.add(new Cluster<T>((T)clusterable));
/*     */       } 
/* 200 */       int changes = assignPointsToClusters(newClusters, points, assignments);
/* 201 */       clusters = newClusters;
/* 205 */       if (changes == 0 && !emptyCluster)
/* 206 */         return clusters; 
/*     */     } 
/* 209 */     return clusters;
/*     */   }
/*     */   
/*     */   private static <T extends Clusterable<T>> int assignPointsToClusters(List<Cluster<T>> clusters, Collection<T> points, int[] assignments) {
/* 224 */     int assignedDifferently = 0;
/* 225 */     int pointIndex = 0;
/* 226 */     for (Clusterable clusterable : points) {
/* 227 */       int clusterIndex = getNearestCluster(clusters, (T)clusterable);
/* 228 */       if (clusterIndex != assignments[pointIndex])
/* 229 */         assignedDifferently++; 
/* 232 */       Cluster<T> cluster = clusters.get(clusterIndex);
/* 233 */       cluster.addPoint((T)clusterable);
/* 234 */       assignments[pointIndex++] = clusterIndex;
/*     */     } 
/* 237 */     return assignedDifferently;
/*     */   }
/*     */   
/*     */   private static <T extends Clusterable<T>> List<Cluster<T>> chooseInitialCenters(Collection<T> points, int k, Random random) {
/* 254 */     List<T> pointList = Collections.unmodifiableList(new ArrayList<T>(points));
/* 257 */     int numPoints = pointList.size();
/* 261 */     boolean[] taken = new boolean[numPoints];
/* 264 */     List<Cluster<T>> resultSet = new ArrayList<Cluster<T>>();
/* 267 */     int firstPointIndex = random.nextInt(numPoints);
/* 269 */     Clusterable clusterable = (Clusterable)pointList.get(firstPointIndex);
/* 271 */     resultSet.add(new Cluster<T>((T)clusterable));
/* 274 */     taken[firstPointIndex] = true;
/* 278 */     double[] minDistSquared = new double[numPoints];
/* 282 */     for (int i = 0; i < numPoints; i++) {
/* 283 */       if (i != firstPointIndex) {
/* 284 */         double d = clusterable.distanceFrom(pointList.get(i));
/* 285 */         minDistSquared[i] = d * d;
/*     */       } 
/*     */     } 
/* 289 */     while (resultSet.size() < k) {
/* 293 */       double distSqSum = 0.0D;
/* 295 */       for (int j = 0; j < numPoints; j++) {
/* 296 */         if (!taken[j])
/* 297 */           distSqSum += minDistSquared[j]; 
/*     */       } 
/* 303 */       double r = random.nextDouble() * distSqSum;
/* 306 */       int nextPointIndex = -1;
/* 310 */       double sum = 0.0D;
/*     */       int m;
/* 311 */       for (m = 0; m < numPoints; m++) {
/* 312 */         if (!taken[m]) {
/* 313 */           sum += minDistSquared[m];
/* 314 */           if (sum >= r) {
/* 315 */             nextPointIndex = m;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 324 */       if (nextPointIndex == -1)
/* 325 */         for (m = numPoints - 1; m >= 0; m--) {
/* 326 */           if (!taken[m]) {
/* 327 */             nextPointIndex = m;
/*     */             break;
/*     */           } 
/*     */         }  
/* 334 */       if (nextPointIndex >= 0) {
/* 336 */         Clusterable clusterable1 = (Clusterable)pointList.get(nextPointIndex);
/* 338 */         resultSet.add(new Cluster<T>((T)clusterable1));
/* 341 */         taken[nextPointIndex] = true;
/* 343 */         if (resultSet.size() < k)
/* 346 */           for (int n = 0; n < numPoints; n++) {
/* 348 */             if (!taken[n]) {
/* 349 */               double d = clusterable1.distanceFrom(pointList.get(n));
/* 350 */               double d2 = d * d;
/* 351 */               if (d2 < minDistSquared[n])
/* 352 */                 minDistSquared[n] = d2; 
/*     */             } 
/*     */           }  
/*     */       } 
/*     */     } 
/* 366 */     return resultSet;
/*     */   }
/*     */   
/*     */   private T getPointFromLargestVarianceCluster(Collection<Cluster<T>> clusters) {
/* 377 */     double maxVariance = Double.NEGATIVE_INFINITY;
/* 378 */     Cluster<T> selected = null;
/* 379 */     for (Cluster<T> cluster : clusters) {
/* 380 */       if (!cluster.getPoints().isEmpty()) {
/* 383 */         T center = cluster.getCenter();
/* 384 */         Variance stat = new Variance();
/* 385 */         for (Clusterable<T> clusterable : cluster.getPoints())
/* 386 */           stat.increment(clusterable.distanceFrom(center)); 
/* 388 */         double variance = stat.getResult();
/* 391 */         if (variance > maxVariance) {
/* 392 */           maxVariance = variance;
/* 393 */           selected = cluster;
/*     */         } 
/*     */       } 
/*     */     } 
/* 400 */     if (selected == null)
/* 401 */       throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]); 
/* 405 */     List<T> selectedPoints = selected.getPoints();
/* 406 */     return selectedPoints.remove(this.random.nextInt(selectedPoints.size()));
/*     */   }
/*     */   
/*     */   private T getPointFromLargestNumberCluster(Collection<Cluster<T>> clusters) {
/* 418 */     int maxNumber = 0;
/* 419 */     Cluster<T> selected = null;
/* 420 */     for (Cluster<T> cluster : clusters) {
/* 423 */       int number = cluster.getPoints().size();
/* 426 */       if (number > maxNumber) {
/* 427 */         maxNumber = number;
/* 428 */         selected = cluster;
/*     */       } 
/*     */     } 
/* 434 */     if (selected == null)
/* 435 */       throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]); 
/* 439 */     List<T> selectedPoints = selected.getPoints();
/* 440 */     return selectedPoints.remove(this.random.nextInt(selectedPoints.size()));
/*     */   }
/*     */   
/*     */   private T getFarthestPoint(Collection<Cluster<T>> clusters) {
/* 452 */     double maxDistance = Double.NEGATIVE_INFINITY;
/* 453 */     Cluster<T> selectedCluster = null;
/* 454 */     int selectedPoint = -1;
/* 455 */     for (Cluster<T> cluster : clusters) {
/* 458 */       T center = cluster.getCenter();
/* 459 */       List<T> points = cluster.getPoints();
/* 460 */       for (int i = 0; i < points.size(); i++) {
/* 461 */         double distance = ((Clusterable<T>)points.get(i)).distanceFrom(center);
/* 462 */         if (distance > maxDistance) {
/* 463 */           maxDistance = distance;
/* 464 */           selectedCluster = cluster;
/* 465 */           selectedPoint = i;
/*     */         } 
/*     */       } 
/*     */     } 
/* 472 */     if (selectedCluster == null)
/* 473 */       throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]); 
/* 476 */     return (T)selectedCluster.getPoints().remove(selectedPoint);
/*     */   }
/*     */   
/*     */   private static <T extends Clusterable<T>> int getNearestCluster(Collection<Cluster<T>> clusters, T point) {
/* 490 */     double minDistance = Double.MAX_VALUE;
/* 491 */     int clusterIndex = 0;
/* 492 */     int minCluster = 0;
/* 493 */     for (Cluster<T> c : clusters) {
/* 494 */       double distance = point.distanceFrom(c.getCenter());
/* 495 */       if (distance < minDistance) {
/* 496 */         minDistance = distance;
/* 497 */         minCluster = clusterIndex;
/*     */       } 
/* 499 */       clusterIndex++;
/*     */     } 
/* 501 */     return minCluster;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\clustering\KMeansPlusPlusClusterer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */