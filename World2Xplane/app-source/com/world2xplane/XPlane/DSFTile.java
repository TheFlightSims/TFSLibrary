/*      */ package com.world2xplane.XPlane;
/*      */ 
/*      */ import com.google.common.base.Objects;
/*      */ import com.vividsolutions.jts.geom.Coordinate;
/*      */ import com.vividsolutions.jts.geom.Envelope;
/*      */ import com.vividsolutions.jts.geom.Geometry;
/*      */ import com.vividsolutions.jts.geom.GeometryCollection;
/*      */ import com.vividsolutions.jts.geom.GeometryFactory;
/*      */ import com.vividsolutions.jts.geom.LineString;
/*      */ import com.vividsolutions.jts.geom.LinearRing;
/*      */ import com.vividsolutions.jts.geom.MultiLineString;
/*      */ import com.vividsolutions.jts.geom.Point;
/*      */ import com.vividsolutions.jts.geom.Polygon;
/*      */ import com.vividsolutions.jts.geom.prep.PreparedGeometry;
/*      */ import com.vividsolutions.jts.geom.prep.PreparedGeometryFactory;
/*      */ import com.vividsolutions.jts.index.SpatialIndex;
/*      */ import com.vividsolutions.jts.index.quadtree.Quadtree;
/*      */ import com.vividsolutions.jts.index.strtree.STRtree;
/*      */ import com.vividsolutions.jts.operation.linemerge.LineMerger;
/*      */ import com.vividsolutions.jts.operation.polygonize.Polygonizer;
/*      */ import com.world2xplane.DataStore.RelationInfo;
/*      */ import com.world2xplane.DataStore.WayInfo;
/*      */ import com.world2xplane.Geom.Densifier;
/*      */ import com.world2xplane.Geom.GeomUtils;
/*      */ import com.world2xplane.Geom.GeometryClipper;
/*      */ import com.world2xplane.Geom.Quadtree.QuadTree;
/*      */ import com.world2xplane.Geom.Quadtree.QuadTreePoint;
/*      */ import com.world2xplane.Geom.RangeDistance;
/*      */ import com.world2xplane.Network.Junction;
/*      */ import com.world2xplane.Network.NetworkDelegate;
/*      */ import com.world2xplane.Network.NetworkItem;
/*      */ import com.world2xplane.Network.Road;
/*      */ import com.world2xplane.Network.RoadNetwork;
/*      */ import com.world2xplane.Network.VectorRule;
/*      */ import com.world2xplane.OSM.Node;
/*      */ import com.world2xplane.OSM.OSMMultiPolygon;
/*      */ import com.world2xplane.OSM.OSMPolygon;
/*      */ import com.world2xplane.OSM.OSMRelation;
/*      */ import com.world2xplane.OSM.OSMShape;
/*      */ import com.world2xplane.OSM.TrackedWay;
/*      */ import com.world2xplane.Parser.OSMBaseParser;
/*      */ import com.world2xplane.Rules.AcceptingRule;
/*      */ import com.world2xplane.Rules.AdaptiveHeight;
/*      */ import com.world2xplane.Rules.AreaTrackerRule;
/*      */ import com.world2xplane.Rules.FacadeRule;
/*      */ import com.world2xplane.Rules.ForestRule;
/*      */ import com.world2xplane.Rules.GeneratorStore;
/*      */ import com.world2xplane.Rules.LineRule;
/*      */ import com.world2xplane.Rules.NodeTrackerRule;
/*      */ import com.world2xplane.Rules.ObjectDefinitionStore;
/*      */ import com.world2xplane.Rules.ObjectRule;
/*      */ import com.world2xplane.Rules.PolygonRule;
/*      */ import com.world2xplane.Rules.RandomRule;
/*      */ import com.world2xplane.Rules.Regions.RegionProvider;
/*      */ import com.world2xplane.Rules.Rule;
/*      */ import com.world2xplane.XPlane.Exclusions.ExclusionBuilder;
/*      */ import com.world2xplane.XPlane.Exclusions.PolyExclusionBuilder;
/*      */ import com.world2xplane.XPlane.ModelBuilder.ModelBuilder;
/*      */ import java.io.File;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.DecimalFormatSymbols;
/*      */ import java.text.NumberFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.Vector;
/*      */ import math.geom2d.Box2D;
/*      */ import math.geom2d.Point2D;
/*      */ import math.geom2d.line.LineSegment2D;
/*      */ import math.geom2d.polygon.LinearRing2D;
/*      */ import org.apache.commons.io.FileUtils;
/*      */ import org.apache.commons.lang.StringUtils;
/*      */ import org.apache.commons.math3.util.FastMath;
/*      */ import org.slf4j.Logger;
/*      */ import org.slf4j.LoggerFactory;
/*      */ 
/*      */ public class DSFTile {
/*      */   private final Progress progress;
/*      */   
/*      */   private String regions;
/*      */   
/*      */   public final GeneratorStore generatorStore;
/*      */   
/*      */   private final Box2D area;
/*      */   
/*      */   private short longitude;
/*      */   
/*      */   private short latitude;
/*      */   
/*      */   public Envelope getEnvelope() {
/*   74 */     return new Envelope(this.area.getMinX(), this.area.getMaxX(), this.area.getMinY(), this.area.getMaxY());
/*      */   }
/*      */   
/*      */   public double getMetreXSize() {
/*   78 */     return this.metreXSize;
/*      */   }
/*      */   
/*   91 */   private double metreXSize = 0.0D;
/*      */   
/*   92 */   private double metreYSize = 0.0D;
/*      */   
/*   95 */   private int objectCount = 0;
/*      */   
/*   96 */   private int forestCount = 0;
/*      */   
/*   98 */   private int buildingCount = 0;
/*      */   
/*   99 */   private int roadCount = 0;
/*      */   
/*  100 */   private int railCount = 0;
/*      */   
/*  101 */   private int vectorCount = 0;
/*      */   
/*  103 */   private int buildingExclusionCount = 0;
/*      */   
/*  104 */   private int forestExclusionCount = 0;
/*      */   
/*  105 */   private long junctionNumber = 1L;
/*      */   
/*      */   private Set<DSFObjectDefinition> polygonDefinitions;
/*      */   
/*      */   private Set<DSFObjectDefinition> objectDefinitions;
/*      */   
/*  110 */   private List<DSFObjectDefinition> polygonDefinitionNumbers = new ArrayList<>();
/*      */   
/*  111 */   private List<DSFObjectDefinition> objectDefinitionNumbers = new ArrayList<>();
/*      */   
/*  117 */   private List<NetworkItem> networkItems = new ArrayList<>();
/*      */   
/*  119 */   Set<String> possibleRegions = new HashSet<>();
/*      */   
/*      */   private QuadTree polygonTree;
/*      */   
/*  123 */   private STRtree buildingTree = new STRtree(8);
/*      */   
/*  124 */   private STRtree areaTree = new STRtree(8);
/*      */   
/*      */   private NodeTracker nodeTracker;
/*      */   
/*  128 */   private List<Envelope> buildingExclusions = new ArrayList<>();
/*      */   
/*  129 */   private List<Envelope> forestExclusions = new ArrayList<>();
/*      */   
/*  131 */   private Map<String, Integer> regionalBuildingsCount = new HashMap<>();
/*      */   
/*  132 */   private ArrayList<OSMShape> buildingParts = new ArrayList<>();
/*      */   
/*  134 */   private HashMap<Long, Integer> assignedIdentifiers = new HashMap<>();
/*      */   
/*  135 */   private HashMap<Long, TrackedWay> trackedWays = new HashMap<>();
/*      */   
/*  136 */   private Collection<Node> nodes = new ArrayList<>();
/*      */   
/*      */   private RoadNetwork roadNetwork;
/*      */   
/*      */   private boolean completed;
/*      */   
/*      */   private File polyFile;
/*      */   
/*      */   private List<Envelope> exclusions;
/*      */   
/*      */   public void clear() {
/*  143 */     this.polygonTree = null;
/*  144 */     this.buildingTree = null;
/*  146 */     this.polygonDefinitions.clear();
/*  147 */     this.polygonDefinitions = null;
/*  149 */     this.polygonDefinitionNumbers.clear();
/*  150 */     this.polygonDefinitionNumbers = null;
/*  152 */     this.objectDefinitions.clear();
/*  153 */     this.objectDefinitions = null;
/*  155 */     this.areas.clear();
/*  156 */     this.areas = null;
/*  158 */     this.randomObjects.clear();
/*  159 */     this.randomObjects = null;
/*  161 */     this.lineObjects.clear();
/*  162 */     this.lineObjects = null;
/*  164 */     this.multiPolygons.clear();
/*  165 */     this.multiPolygons = null;
/*  167 */     this.osmObjects.clear();
/*  168 */     this.osmObjects = null;
/*  170 */     this.osmShapes.clear();
/*  171 */     this.osmShapes = null;
/*  173 */     this.autogen.clear();
/*  174 */     this.autogen = null;
/*  176 */     this.buildingExclusions.clear();
/*  177 */     this.buildingExclusions = null;
/*  179 */     this.forestExclusions.clear();
/*  180 */     this.forestExclusions = null;
/*  182 */     this.assignedIdentifiers.clear();
/*  183 */     this.assignedIdentifiers = null;
/*  185 */     this.trackedWays.clear();
/*  186 */     this.trackedWays = null;
/*  188 */     this.nodes.clear();
/*  189 */     this.nodes = null;
/*  191 */     if (this.networkItems != null)
/*  192 */       this.networkItems.clear(); 
/*  193 */     this.networkItems = null;
/*  195 */     this.areaTree = null;
/*  197 */     if (this.exclusions != null)
/*  198 */       this.exclusions.clear(); 
/*  199 */     this.exclusions = null;
/*  201 */     this.roadNetwork = null;
/*  204 */     this.possibleRegions.clear();
/*  205 */     this.possibleRegions = null;
/*  207 */     this.nodeTracker = null;
/*  208 */     this.regionalBuildingsCount.clear();
/*  209 */     this.regionalBuildingsCount = null;
/*  211 */     this.buildingParts.clear();
/*  212 */     this.buildingParts = null;
/*  214 */     if (this.assignedIdentifiers != null)
/*  215 */       this.assignedIdentifiers.clear(); 
/*  216 */     this.assignedIdentifiers = null;
/*  218 */     if (this.trackedWays != null)
/*  219 */       this.trackedWays.clear(); 
/*  220 */     this.trackedWays = null;
/*  226 */     this.dsfPolygons.clear();
/*  227 */     this.dsfPolygons = null;
/*      */   }
/*      */   
/*      */   public String regionReport() {
/*  232 */     if (this.regionalBuildingsCount.size() == 0)
/*  233 */       return "No Regional Buildings"; 
/*  235 */     double total = 0.0D;
/*  236 */     for (Iterator<Integer> i$ = this.regionalBuildingsCount.values().iterator(); i$.hasNext(); ) {
/*  236 */       int item = ((Integer)i$.next()).intValue();
/*  237 */       total += item;
/*      */     } 
/*  240 */     StringBuilder size = new StringBuilder();
/*  241 */     size.append("Regional Buildings: ");
/*  242 */     for (Map.Entry<String, Integer> item : this.regionalBuildingsCount.entrySet())
/*  243 */       size.append((String)item.getKey() + " " + item.getValue() + " - " + (int)(((Integer)item.getValue()).intValue() / total * 100.0D) + "%, "); 
/*  246 */     return size.toString();
/*      */   }
/*      */   
/*      */   public void addWayTracker(Long wayIdentifier, List<Node> nodes, String identifier) {
/*  254 */     TrackedWay trackedWay = new TrackedWay();
/*  255 */     trackedWay.setIdentifier(identifier);
/*  256 */     trackedWay.setNodes(nodes);
/*  257 */     trackedWay.setWayId(wayIdentifier.longValue());
/*  258 */     trackedWay.setNodes(nodes);
/*  259 */     for (Node node : nodes)
/*  260 */       this.trackedWays.put(Long.valueOf(node.getId()), trackedWay); 
/*      */   }
/*      */   
/*      */   public void setCompleted(boolean completed) {
/*  265 */     this.completed = completed;
/*      */   }
/*      */   
/*      */   public boolean isCompleted() {
/*  269 */     return this.completed;
/*      */   }
/*      */   
/*      */   public static class NamedArea {
/*      */     String identifier;
/*      */     
/*      */     OSMPolygon outer;
/*      */     
/*      */     PreparedGeometry preparedGeometryOuter;
/*      */     
/*      */     List<PreparedGeometry> preparedGeometryInner;
/*      */     
/*      */     List<OSMPolygon> inner;
/*      */   }
/*      */   
/*  281 */   private List<NamedArea> areas = new ArrayList<>();
/*      */   
/*      */   public void addNode(HashMap<String, String> tags, long nodeId, Point2D point2D, List<AcceptingRule> rules) {
/*  288 */     Node node = new Node(nodeId, point2D.x(), point2D.y());
/*  289 */     node.setRules(rules);
/*  290 */     if (tags.containsKey("min_height"))
/*  291 */       node.setMinHeight(OSMBaseParser.getMinHeightFromTags(tags, Long.valueOf(nodeId), null).floatValue()); 
/*  293 */     this.nodes.add(node);
/*      */   }
/*      */   
/*      */   private static class RandomObject {
/*      */     Long identifier;
/*      */     
/*      */     OSMPolygon shape;
/*      */     
/*      */     List<LinearRing2D> inner;
/*      */     
/*      */     Rule rule;
/*      */     
/*      */     private RandomObject() {}
/*      */   }
/*      */   
/*      */   private static class LineObject {
/*      */     Long identifier;
/*      */     
/*      */     OSMPolygon shape;
/*      */     
/*      */     Rule rule;
/*      */     
/*      */     private LineObject() {}
/*      */     
/*      */     boolean clipped = false;
/*      */   }
/*      */   
/*      */   private void getUsedRegions() {
/*  319 */     List<RegionProvider> regionProviders = this.generatorStore.getRegionProviders();
/*  320 */     for (RegionProvider item : regionProviders) {
/*  321 */       this.possibleRegions.addAll(item.getRegionsInside(this.area.getMaxY(), this.area.getMinX(), this.area.getMinY(), this.area.getMaxX()));
/*  323 */       this.possibleRegions.addAll(item.getRegionsContaining(this.area.getMaxY(), this.area.getMinX(), this.area.getMinY(), this.area.getMaxX()));
/*      */     } 
/*  328 */     Set<String> regionList = new HashSet<>();
/*  330 */     if (this.possibleRegions.size() > 0)
/*  331 */       for (String item : this.possibleRegions)
/*  332 */         regionList.add(item);  
/*  335 */     if (this.possibleRegions.size() > 0)
/*  336 */       for (String item : this.possibleRegions)
/*  337 */         regionList.add(item);  
/*  341 */     this.regions = StringUtils.join(regionList, ",");
/*      */   }
/*      */   
/*  345 */   private List<RandomObject> randomObjects = new ArrayList<>();
/*      */   
/*  346 */   private List<LineObject> lineObjects = new ArrayList<>();
/*      */   
/*      */   public void processMultiPolygons() {
/*  364 */     for (OSMRelation item : this.multiPolygons.values()) {
/*  365 */       if (item.outer != null)
/*  366 */         for (OSMRelation.Member outer : item.outer) {
/*  369 */           if (outer.rules == null || outer.rules.size() == 0)
/*  370 */             outer.rules = item.relationRules; 
/*      */         }  
/*  374 */       if (item.parts != null)
/*  375 */         for (OSMRelation.Member part : item.parts) {
/*  377 */           if (part.rules == null || part.rules.size() == 0)
/*  378 */             part.rules = item.relationRules; 
/*      */         }  
/*      */     } 
/*  385 */     for (OSMRelation item : this.multiPolygons.values()) {
/*  386 */       if (item.outer != null && item.relationRules != null && item.relationRules.size() > 0)
/*  387 */         for (OSMRelation.Member outer : item.outer) {
/*  388 */           if (!outer.rules.equals(item.relationRules)) {
/*  389 */             OSMPolygon osmPolygon = new OSMPolygon(outer.shape.getShape());
/*  390 */             osmPolygon.setIdentifier(outer.shape.getIdentifier());
/*  391 */             addSimplePolygon(osmPolygon, outer.shape.getIdentifier(), outer.rules);
/*  392 */             outer.rules = item.relationRules;
/*      */           } 
/*      */         }  
/*      */     } 
/*  399 */     List<OSMRelation> relationShapes = new ArrayList<>();
/*  400 */     relationShapes.addAll(this.multiPolygons.values());
/*  403 */     for (OSMRelation osmRelation : relationShapes) {
/*  404 */       if (!osmRelation.hasRules())
/*  405 */         this.multiPolygons.remove(Long.valueOf(osmRelation.identifier)); 
/*      */     } 
/*  409 */     relationShapes = new ArrayList<>();
/*  410 */     relationShapes.addAll(this.multiPolygons.values());
/*  413 */     for (OSMRelation osmRelation : relationShapes) {
/*  417 */       if (osmRelation.outer != null && osmRelation.inner != null && (osmRelation.outer.size() > 0 || osmRelation.inner.size() > 0)) {
/*  418 */         OSMMultiPolygon merged = GeomUtils.mergeShapes(osmRelation.outer, osmRelation.inner);
/*  420 */         if (merged == null) {
/*  423 */           if (osmRelation.outer.size() > 0 && (osmRelation.inner == null || osmRelation.inner.size() == 0)) {
/*  424 */             mergeOuters(osmRelation);
/*  425 */             this.multiPolygons.remove(Long.valueOf(osmRelation.identifier));
/*      */             continue;
/*      */           } 
/*  428 */           log.error("Failed to merge outer points for relation " + osmRelation.identifier + " please check OSM: http://www.openstreetmap.org/relation/" + osmRelation.identifier);
/*  429 */           this.multiPolygons.remove(Long.valueOf(osmRelation.identifier));
/*  430 */           this.multiPolygons.remove(Long.valueOf(osmRelation.identifier));
/*      */           continue;
/*      */         } 
/*  436 */         HashMap<OSMRelation.Member, List<OSMRelation.Member>> organised = new HashMap<>();
/*  437 */         if (merged != null && merged.getOuterRings() != null && merged.getOuterRings().size() > 0 && merged.getInnerRings().size() > 0) {
/*  438 */           organised = GeomUtils.organiseOutersAndInner(Long.valueOf(osmRelation.identifier), merged.getOuterRings(), merged.getInnerRings());
/*  439 */         } else if (merged != null && merged.getOuterRings() != null && merged.getOuterRings().size() > 0) {
/*  440 */           for (OSMRelation.Member item : merged.getOuterRings())
/*  441 */             organised.put(item, merged.getInnerRings()); 
/*  443 */         } else if (merged != null && merged.getInnerRings() != null && merged.getInnerRings().size() > 0) {
/*  444 */           for (OSMRelation.Member item : merged.getInnerRings())
/*  445 */             organised.put(item, new ArrayList<>()); 
/*      */         } 
/*  448 */         if (organised != null && organised.size() != 0)
/*  454 */           for (Map.Entry<OSMRelation.Member, List<OSMRelation.Member>> group : organised.entrySet()) {
/*  456 */             if (group.getKey() != null) {
/*  459 */               OSMRelation.Member outerShape = group.getKey();
/*  461 */               outerShape.shape.setCounterClockwise();
/*  463 */               List<AcceptingRule> outerRules = outerShape.rules;
/*  465 */               if (outerRules != null && outerRules.size() > 0)
/*  466 */                 addItemFromRelation(osmRelation, group.getKey(), group.getValue(), outerRules); 
/*  471 */               for (OSMRelation.Member inner : group.getValue()) {
/*  472 */                 List<AcceptingRule> innerRules = inner.rules;
/*  473 */                 if (innerRules != null && innerRules.size() > 0)
/*  474 */                   addItemFromRelation(osmRelation, inner, null, innerRules); 
/*      */               } 
/*      */             } 
/*      */           }  
/*  481 */         this.multiPolygons.remove(Long.valueOf(osmRelation.identifier));
/*      */       } 
/*      */     } 
/*  487 */     relationShapes = new ArrayList<>();
/*  488 */     relationShapes.addAll(this.multiPolygons.values());
/*  489 */     for (OSMRelation relation : relationShapes) {
/*  490 */       if (relation.parts != null && relation.parts.size() > 0)
/*  493 */         for (OSMRelation.Member part : relation.parts) {
/*  495 */           List<AcceptingRule> partRules = part.rules;
/*  496 */           if (partRules != null && partRules.size() > 0)
/*  497 */             addItemFromRelation(relation, part, null, partRules); 
/*      */         }  
/*  501 */       this.multiPolygons.remove(Long.valueOf(relation.identifier));
/*      */     } 
/*  505 */     if (this.multiPolygons.size() > 0)
/*  506 */       throw new RuntimeException("Shouldn't get here"); 
/*      */   }
/*      */   
/*      */   private void addItemFromRelation(OSMRelation osmRelation, OSMRelation.Member outer, List<OSMRelation.Member> inners, List<AcceptingRule> rules) {
/*  515 */     DSFPolygon dsfPolygon = new DSFPolygon();
/*  516 */     dsfPolygon.setRelation(osmRelation);
/*  517 */     dsfPolygon.setRules(rules);
/*  518 */     dsfPolygon.setOuter(outer.shape);
/*  520 */     if (inners != null) {
/*  521 */       dsfPolygon.setInner(new ArrayList<>());
/*  522 */       for (OSMRelation.Member item : inners)
/*  523 */         dsfPolygon.getInner().add(item.shape); 
/*      */     } 
/*  526 */     this.dsfPolygons.add(dsfPolygon);
/*      */   }
/*      */   
/*      */   private boolean addMember(OSMRelation osmRelation, DSFPolygon polygon, Rule rule) {
/*  535 */     if (rule != null && rule.getAdaptiveHeightPolicy() != null && polygon.getOuter().getHeight() == null) {
/*  536 */       AdaptiveHeight heightPolicy = rule.getAdaptiveHeightPolicy();
/*  537 */       Point2D centroid = polygon.getOuter().getCentroid();
/*  538 */       if (centroid != null) {
/*  539 */         Envelope envelope = new Envelope(centroid.x() - this.metreXSize * 2000.0D, centroid.x() + this.metreXSize * 2000.0D, centroid.y() - this.metreYSize * 2000.0D, centroid.y() + this.metreYSize * 2000.0D);
/*  541 */         List<NodeTracker.TrackPoint> trackPoints = this.nodeTracker.query(envelope);
/*  542 */         if (trackPoints != null && trackPoints.size() > 0)
/*  544 */           for (NodeTracker.TrackPoint item : trackPoints) {
/*  545 */             AdaptiveHeight.AdaptiveHeightPolicy adaptiveHeightPolicy = heightPolicy.getRangeForTrackPoint(item);
/*  546 */             if (adaptiveHeightPolicy != null) {
/*  547 */               double distance = GeomUtils.distanceInMeters(new LineSegment2D(new Point2D(item.point.getX(), item.point.getY()), centroid));
/*  548 */               if (distance < (adaptiveHeightPolicy.getRange() * 1000.0F)) {
/*  549 */                 float height = adaptiveHeightPolicy.getRandomHeight();
/*  550 */                 polygon.getOuter().setHeight(Float.valueOf(height));
/*      */                 break;
/*      */               } 
/*      */             } 
/*      */           }  
/*      */       } 
/*      */     } 
/*  561 */     Integer customFacade = (osmRelation != null) ? osmRelation.customFacade : polygon.getOuter().getCustomFacade();
/*      */     try {
/*  564 */       Long identifier = Long.valueOf((osmRelation != null) ? osmRelation.identifier : polygon.getOuter().getIdentifier().longValue());
/*  567 */       if (rule instanceof com.world2xplane.Rules.SinkRule)
/*  568 */         return true; 
/*  571 */       if (rule instanceof com.world2xplane.Rules.AutogenStringRule) {
/*  572 */         OSMShape singleShape = new OSMShape(rule);
/*  573 */         singleShape.outer = polygon.getOuter().clone();
/*  574 */         singleShape.outer.setCounterClockwise();
/*  575 */         if (osmRelation != null || polygon.getRelation() != null)
/*  576 */           singleShape.multiPolygonPart = true; 
/*  578 */         singleShape.objectDefinitionNumber = rule.getDefinitionNumber(singleShape);
/*  579 */         this.assignedIdentifiers.put(identifier, singleShape.objectDefinitionNumber);
/*  580 */         this.autogen.addShape(singleShape);
/*      */       } 
/*  583 */       if (rule instanceof FacadeRule || rule instanceof ForestRule || rule instanceof PolygonRule) {
/*  586 */         OSMShape singleShape = new OSMShape(rule);
/*  587 */         singleShape.customFacade = customFacade;
/*  588 */         singleShape.outer = polygon.getOuter().clone();
/*  589 */         singleShape.outer.setCounterClockwise();
/*  590 */         if (osmRelation != null || polygon.getRelation() != null)
/*  591 */           singleShape.multiPolygonPart = true; 
/*  594 */         if (rule instanceof PolygonRule)
/*  595 */           getAngleForPolyRule(singleShape); 
/*  599 */         if (this.generatorStore.isCreateBuildingParts() && (singleShape.outer.isBuildingPart() || (singleShape.customFacade != null && singleShape.customFacade.intValue() == -2))) {
/*  600 */           ModelBuilder modelBuilder = new ModelBuilder();
/*  601 */           boolean isPart = modelBuilder.isPart(singleShape.outer.getIdentifier());
/*  607 */           String directory = modelBuilder.generateFromFile(singleShape.outer, singleShape.outer.getIdentifier(), (int)this.area.getMinX(), (int)this.area.getMinY());
/*  609 */           if (directory != null) {
/*  611 */             DSFObjectDefinition definition = addDSFLocalDefinition(directory + File.separator + "part_" + singleShape.outer.getIdentifier() + "w.obj", 1);
/*  613 */             DSFObject osmObject = new DSFObject(null);
/*  614 */             osmObject.position = GeomUtils.getPolygonCenter(singleShape.outer.getShape());
/*  615 */             osmObject.angle = Float.valueOf(0.0F);
/*  616 */             osmObject.generatedObject = definition;
/*  617 */             osmObject.objectDefinitionNumber = null;
/*  618 */             osmObject.setRequiredLevel((byte)1);
/*  620 */             this.osmObjects.add(osmObject);
/*  622 */             definition = addDSFLocalDefinition(directory + File.separator + "part_" + singleShape.outer.getIdentifier() + "r.obj", 1);
/*  624 */             osmObject = new DSFObject(null);
/*  625 */             osmObject.position = GeomUtils.getPolygonCenter(singleShape.outer.getShape());
/*  626 */             osmObject.angle = Float.valueOf(0.0F);
/*  627 */             osmObject.generatedObject = definition;
/*  628 */             osmObject.objectDefinitionNumber = null;
/*  629 */             osmObject.setRequiredLevel((byte)1);
/*  630 */             if (isPart)
/*  631 */               this.buildingParts.add(singleShape); 
/*  633 */             this.osmObjects.add(osmObject);
/*  634 */             return true;
/*      */           } 
/*  637 */           return false;
/*      */         } 
/*  644 */         if (polygon.getInner() != null) {
/*  645 */           singleShape.inner = new ArrayList();
/*  646 */           for (OSMPolygon inner : polygon.getInner()) {
/*  647 */             OSMPolygon innerShape = inner.clone();
/*  648 */             innerShape.setClockwise();
/*  649 */             singleShape.inner.add(innerShape);
/*      */           } 
/*      */         } 
/*  655 */         singleShape.objectDefinitionNumber = rule.getDefinitionNumber(singleShape);
/*  656 */         this.assignedIdentifiers.put(identifier, singleShape.objectDefinitionNumber);
/*  658 */         this.osmShapes.add(singleShape);
/*      */       } 
/*  660 */       if (rule instanceof LineRule)
/*  661 */         addLineObjects(identifier, polygon.getOuter(), (LineRule)rule); 
/*  663 */       if (rule instanceof RandomRule)
/*  664 */         addRandomObjects(identifier, polygon.getOuter(), polygon.getInner(), rule); 
/*  667 */       if (rule instanceof ObjectRule)
/*  668 */         return addObject(polygon.getOuter(), rule); 
/*  671 */     } catch (Exception e) {
/*  672 */       e.printStackTrace();
/*      */     } 
/*  675 */     return true;
/*      */   }
/*      */   
/*      */   private class LineSegmentAndLength {
/*      */     public LineSegment2D lineSegment2D;
/*      */     
/*      */     public Double length;
/*      */     
/*      */     private LineSegmentAndLength() {}
/*      */   }
/*      */   
/*      */   private void getAngleForPolyRule(OSMShape singleShape) {
/*  687 */     Float angle = Float.valueOf(0.0F);
/*  689 */     List<LineSegmentAndLength> outside = new ArrayList<>();
/*  690 */     for (int x = 0; x < singleShape.outer.vertexNumber() - 1; x++) {
/*  691 */       Point2D p1 = singleShape.outer.vertex(x);
/*  692 */       Point2D p2 = singleShape.outer.vertex(x + 1);
/*  693 */       LineSegment2D lineSegment2D = new LineSegment2D(p1, p2);
/*  694 */       LineSegmentAndLength lineSegmentAndLength = new LineSegmentAndLength();
/*  695 */       lineSegmentAndLength.lineSegment2D = lineSegment2D;
/*  696 */       lineSegmentAndLength.length = Double.valueOf(GeomUtils.distanceInMeters(lineSegment2D));
/*  697 */       outside.add(lineSegmentAndLength);
/*      */     } 
/*  699 */     Collections.sort(outside, new Comparator<LineSegmentAndLength>() {
/*      */           public int compare(DSFTile.LineSegmentAndLength o1, DSFTile.LineSegmentAndLength o2) {
/*  702 */             return o2.length.compareTo(o1.length);
/*      */           }
/*      */         });
/*  705 */     if (outside.size() > 0)
/*  706 */       singleShape.outer.setHeight(Float.valueOf((float)GeomUtils.getBearing(((LineSegmentAndLength)outside.get(0)).lineSegment2D.firstPoint(), ((LineSegmentAndLength)outside.get(0)).lineSegment2D.lastPoint()))); 
/*      */   }
/*      */   
/*      */   private void trackArea(OSMPolygon shape, List<OSMPolygon> inner, AreaTrackerRule rule) {
/*  717 */     if (shape == null || shape.getGeometry() == null)
/*      */       return; 
/*  720 */     NamedArea namedArea = new NamedArea();
/*  721 */     namedArea.outer = shape;
/*  722 */     if (shape != null)
/*      */       try {
/*  725 */         Geometry geom = GeomUtils.preciseBuffer(namedArea.outer.getGeometry(), 5.0D);
/*  726 */         namedArea.preparedGeometryOuter = PreparedGeometryFactory.prepare(geom);
/*  727 */         namedArea.outer.setGeometry(geom);
/*  728 */       } catch (Exception e) {
/*  729 */         namedArea.preparedGeometryOuter = PreparedGeometryFactory.prepare(namedArea.outer.getGeometry());
/*      */       }  
/*  732 */     namedArea.identifier = rule.getIdentifier();
/*  733 */     if (inner != null && inner.size() > 0) {
/*  734 */       namedArea.preparedGeometryInner = new ArrayList<>();
/*  735 */       namedArea.inner = new ArrayList<>();
/*  737 */       namedArea.inner = inner;
/*  738 */       for (OSMPolygon item : namedArea.inner)
/*  739 */         namedArea.preparedGeometryInner.add(PreparedGeometryFactory.prepare(item.getGeometry())); 
/*      */     } 
/*  742 */     if (shape.vertexNumber() > 1)
/*      */       try {
/*  744 */         this.areas.add(namedArea);
/*  745 */         this.areaTree.insert(namedArea.outer.getEnvelope(), namedArea);
/*  747 */       } catch (Exception e) {
/*  748 */         e.printStackTrace();
/*      */       }  
/*      */   }
/*      */   
/*      */   public STRtree getAreaTree() {
/*  754 */     return this.areaTree;
/*      */   }
/*      */   
/*      */   public List<NamedArea> getNamedAreas() {
/*  758 */     List<NamedArea> items = this.areaTree.query(getEnvelope());
/*  759 */     return items;
/*      */   }
/*      */   
/*      */   public void clearAirports() {
/*  763 */     Set<OSMShape> toRemove = new HashSet<>();
/*  764 */     for (OSMShape item : this.osmShapes) {
/*  765 */       if (item.rule != null && item.rule.isSkipAirport() && item.outer != null)
/*      */         try {
/*  768 */           Point centre = item.outer.getGeometry().getInteriorPoint();
/*  770 */           List<NamedArea> items = this.areaTree.query(new Envelope(centre.getX(), centre.getX(), centre.getY(), centre.getY()));
/*  772 */           if (items != null && items.size() > 0)
/*  773 */             for (NamedArea namedArea : items) {
/*  774 */               if (namedArea.identifier.equals("aeroway") && 
/*  775 */                 namedArea.preparedGeometryOuter != null && namedArea.preparedGeometryOuter.intersects(item.outer.getGeometry().buffer(0.0D)))
/*  776 */                 toRemove.add(item); 
/*      */             }  
/*  782 */         } catch (Exception e) {
/*  783 */           log.error(e.getMessage());
/*      */         }  
/*      */     } 
/*  788 */     this.osmShapes.remove(toRemove);
/*  789 */     log.info("Removed " + toRemove.size() + " objects from airports");
/*      */   }
/*      */   
/*      */   public void processSingle() {
/*  795 */     long startTime = System.currentTimeMillis();
/*  796 */     int mb = 1048576;
/*  797 */     Runtime runtime = Runtime.getRuntime();
/*  799 */     if (this.generatorStore.isDebugging()) {
/*  801 */       long vmMemory = runtime.maxMemory() / mb;
/*  802 */       long usedMemory = runtime.totalMemory() - runtime.freeMemory();
/*  803 */       float percentage = (float)usedMemory / (float)vmMemory * 100.0F;
/*  804 */       log.info("Memory " + usedMemory);
/*      */     } 
/*  807 */     int count = this.dsfPolygons.size();
/*  808 */     int index = 0;
/*  810 */     boolean debug = this.generatorStore.isDebugging();
/*  811 */     for (DSFPolygon dsfPolygon : this.dsfPolygons) {
/*  812 */       if (index % 1000 == 0) {
/*  813 */         long endTime = System.currentTimeMillis();
/*  814 */         log.info("Item: " + index + " of " + count + " " + "Time: " + (endTime - startTime));
/*  815 */         startTime = System.currentTimeMillis();
/*      */       } 
/*  817 */       index++;
/*  818 */       if (dsfPolygon.getOuter() == null)
/*      */         continue; 
/*  823 */       if (dsfPolygon.getRelation() != null)
/*  824 */         dsfPolygon.getOuter().setMultipolygon(true); 
/*  828 */       this.generatorStore.getAcceptingRules(dsfPolygon.getOuter(), dsfPolygon.getRules(), this.possibleRegions, new Rule.Delegate() {
/*      */             public boolean containsNode(String identifier) {
/*  834 */               return DSFTile.this.containsNode(dsfPolygon.getOuter(), identifier);
/*      */             }
/*      */           }new Rule.OnAccept() {
/*      */             public boolean addItem(Rule rule) {
/*  842 */               return DSFTile.this.addMember(dsfPolygon.getRelation(), dsfPolygon, rule);
/*      */             }
/*      */           });
/*  846 */       dsfPolygon.setOuter(null);
/*  847 */       dsfPolygon.setInner(null);
/*  848 */       dsfPolygon.setRules(null);
/*  849 */       dsfPolygon.setRelation(null);
/*      */     } 
/*  856 */     this.dsfPolygons.clear();
/*  858 */     if (this.generatorStore.isDebugging()) {
/*  859 */       long endTime = System.currentTimeMillis();
/*  860 */       log.info("Time: " + (endTime - startTime));
/*  861 */       long vmMemory = runtime.maxMemory() / mb;
/*  862 */       long usedMemory = runtime.totalMemory() - runtime.freeMemory();
/*  863 */       float percentage = (float)usedMemory / (float)vmMemory * 100.0F;
/*  865 */       log.info("Memory " + usedMemory);
/*      */     } 
/*  869 */     System.gc();
/*      */   }
/*      */   
/*      */   public void processShapes() {
/*  879 */     ArrayList<OSMShape> copy = new ArrayList<>();
/*  880 */     copy.addAll(this.osmShapes);
/*  884 */     for (OSMShape osmShape : copy) {
/*  885 */       OSMPolygon checked = GeomUtils.testAndRepairSelfIntersection(osmShape.outer);
/*  886 */       if (checked != osmShape.outer)
/*  887 */         osmShape.outer = checked; 
/*      */     } 
/*  891 */     copy = new ArrayList<>();
/*  892 */     copy.addAll(this.osmShapes);
/*  895 */     for (OSMShape osmShape : copy) {
/*  897 */       if (osmShape.outer != null && osmShape.outer.vertexNumber() > 500) {
/*  900 */         if (osmShape.rule instanceof ForestRule && (
/*  901 */           (ForestRule)osmShape.rule).isPerimeterOnly())
/*      */           continue; 
/*  905 */         if (osmShape.rule instanceof FacadeRule && (
/*  906 */           (FacadeRule)osmShape.rule).getBuilding() != null && !((FacadeRule)osmShape.rule).getBuilding().booleanValue())
/*      */           continue; 
/*      */         try {
/*  913 */           List<OSMPolygon> broken = GeometryClipper.split(osmShape.outer, osmShape.inner, 500);
/*  914 */           if (broken != null)
/*  916 */             for (OSMPolygon item : broken) {
/*  917 */               OSMShape obj = new OSMShape(osmShape.rule);
/*  918 */               obj.objectDefinitionNumber = osmShape.objectDefinitionNumber;
/*  919 */               obj.rule = osmShape.rule;
/*  920 */               obj.customFacade = osmShape.customFacade;
/*  921 */               obj.multiPolygonPart = osmShape.multiPolygonPart;
/*  922 */               obj.outer = item;
/*  923 */               this.osmShapes.add(obj);
/*      */             }  
/*  926 */           this.osmShapes.remove(osmShape);
/*  927 */         } catch (Exception e) {
/*  928 */           log.error("Clipping failed for " + osmShape.outer.getIdentifier());
/*      */         } 
/*      */       } 
/*      */     } 
/*  933 */     copy = new ArrayList<>();
/*  934 */     copy.addAll(this.osmShapes);
/*  939 */     for (OSMShape osmShape : copy) {
/*  940 */       if (osmShape.inner == null || osmShape.inner.size() <= 0 || 
/*  941 */         osmShape.rule instanceof ForestRule || osmShape.rule instanceof PolygonRule)
/*      */         continue; 
/*  945 */       if (osmShape.rule instanceof FacadeRule && (
/*  946 */         (FacadeRule)osmShape.rule).getBuilding() != null && !((FacadeRule)osmShape.rule).getBuilding().booleanValue())
/*      */         continue; 
/*  951 */       divideMultipolygon(osmShape);
/*  952 */       this.osmShapes.remove(osmShape);
/*      */     } 
/*  958 */     copy = new ArrayList<>();
/*  959 */     copy.addAll(this.osmShapes);
/*  960 */     for (OSMShape osmShape : copy) {
/*  961 */       if (osmShape.rule instanceof ForestRule) {
/*  963 */         ForestRule forestRule = (ForestRule)osmShape.rule;
/*  964 */         if (forestRule.isPerimeterOnly())
/*  965 */           createInnerExternal(osmShape); 
/*      */         continue;
/*      */       } 
/*  970 */       if (osmShape.rule instanceof FacadeRule && (
/*  971 */         (FacadeRule)osmShape.rule).getBuilding() != null && !((FacadeRule)osmShape.rule).getBuilding().booleanValue())
/*  972 */         createInnerExternal(osmShape); 
/*      */     } 
/*  979 */     for (OSMShape osmShape : copy) {
/*  981 */       if (osmShape.outer != null && !fitsInside(osmShape)) {
/*  982 */         Map<OSMPolygon, List<OSMPolygon>> rings = GeometryClipper.clip(osmShape.outer, osmShape.inner, this.area);
/*  983 */         if (rings == null || rings.size() == 0) {
/*  984 */           this.osmShapes.remove(osmShape);
/*      */           continue;
/*      */         } 
/*  987 */         for (Map.Entry<OSMPolygon, List<OSMPolygon>> item : rings.entrySet()) {
/*  989 */           ((OSMPolygon)item.getKey()).setCounterClockwise();
/*  990 */           OSMShape obj = new OSMShape(osmShape.rule);
/*  991 */           obj.outer = item.getKey();
/*  992 */           obj.objectDefinitionNumber = osmShape.objectDefinitionNumber;
/*  993 */           obj.rule = osmShape.rule;
/*  994 */           obj.customFacade = osmShape.customFacade;
/*  995 */           obj.multiPolygonPart = osmShape.multiPolygonPart;
/*  996 */           if (((List)item.getValue()).size() > 0) {
/*  997 */             obj.inner = new ArrayList();
/*  998 */             for (OSMPolygon inner : item.getValue()) {
/*  999 */               inner.setClockwise();
/* 1000 */               obj.inner.add(inner);
/*      */             } 
/*      */           } 
/* 1003 */           this.osmShapes.add(obj);
/*      */         } 
/* 1005 */         this.osmShapes.remove(osmShape);
/*      */       } 
/*      */     } 
/* 1011 */     for (OSMShape item : this.osmShapes) {
/* 1012 */       if (item.outer != null && item.outer.getCustomFacade() != null)
/* 1013 */         item.objectDefinitionNumber = item.outer.getCustomFacade(); 
/* 1015 */       if (item.outer != null)
/* 1016 */         item.outer.setCounterClockwise(); 
/* 1018 */       if (item.inner != null) {
/* 1019 */         for (OSMPolygon inner : item.inner)
/* 1020 */           inner.setClockwise(); 
/* 1024 */         if (item.rule != null && item.rule instanceof FacadeRule) {
/* 1025 */           FacadeRule facadeRule = (FacadeRule)item.rule;
/* 1026 */           if (((FacadeRule)item.rule).isSimple() && 
/* 1027 */             item.outer != null && (item.inner == null || item.inner.size() == 0)) {
/* 1029 */             OSMPolygon simplified = item.outer;
/* 1030 */             if (fitsInside(simplified))
/* 1031 */               item.outer = simplified; 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1041 */     log.info("Placing Buildings into QuadTree");
/* 1042 */     int count = 0;
/* 1043 */     for (OSMShape item : this.osmShapes) {
/* 1044 */       if (item.outer != null && item.rule != null && item.rule instanceof FacadeRule) {
/* 1046 */         if (((FacadeRule)item.rule).getBuilding() != null && !((FacadeRule)item.rule).getBuilding().booleanValue())
/*      */           continue; 
/*      */         try {
/* 1050 */           Envelope envelope = item.outer.getEnvelope();
/* 1051 */           if (envelope != null) {
/* 1052 */             this.buildingTree.insert(envelope, item.outer.getGeometry());
/* 1053 */             count++;
/*      */           } 
/* 1055 */         } catch (Exception e) {
/* 1056 */           e.printStackTrace();
/*      */         } 
/*      */       } 
/*      */     } 
/* 1061 */     for (OSMShape item : this.buildingParts) {
/*      */       try {
/* 1064 */         Envelope envelope = item.outer.getEnvelope();
/* 1065 */         if (envelope != null) {
/* 1066 */           this.buildingTree.insert(envelope, item.outer.getGeometry());
/* 1067 */           count++;
/*      */         } 
/* 1069 */       } catch (Exception e) {
/* 1070 */         e.printStackTrace();
/*      */       } 
/*      */     } 
/* 1074 */     log.info("Placed " + count + " Buildings into QuadTree");
/* 1076 */     copy.clear();
/*      */   }
/*      */   
/*      */   private void createInnerExternal(OSMShape osmShape) {
/* 1086 */     if (osmShape.outer.getHeight() == null)
/* 1088 */       if (osmShape.rule instanceof FacadeRule) {
/* 1089 */         getObjectHeight(osmShape.outer, osmShape.rule);
/* 1090 */         if (osmShape.outer.getHeight() == null)
/* 1091 */           osmShape.outer.setHeight(Float.valueOf(((FacadeRule)osmShape.rule).getHeight())); 
/*      */       }  
/* 1094 */     if (osmShape.inner == null || osmShape.inner.size() == 0)
/*      */       return; 
/* 1098 */     for (OSMPolygon inner : osmShape.inner) {
/* 1101 */       OSMShape obj = new OSMShape(osmShape.rule);
/* 1102 */       obj.objectDefinitionNumber = osmShape.objectDefinitionNumber;
/* 1103 */       obj.rule = osmShape.rule;
/* 1104 */       obj.customFacade = osmShape.customFacade;
/* 1105 */       obj.multiPolygonPart = osmShape.multiPolygonPart;
/* 1106 */       obj.outer = inner;
/* 1107 */       obj.outer.setIdentifier(osmShape.outer.getIdentifier());
/* 1108 */       obj.outer.setCounterClockwise();
/* 1110 */       this.osmShapes.add(obj);
/*      */     } 
/* 1112 */     osmShape.inner = null;
/*      */   }
/*      */   
/*      */   private void buffer(OSMShape osmShape, double bufferv, double bufferRandomise) {
/* 1120 */     if (osmShape.outer.vertexNumber() < 2)
/*      */       return; 
/* 1124 */     double mSize = this.metreXSize * bufferRandomise;
/*      */     try {
/* 1129 */       if (!osmShape.outer.isClosed()) {
/* 1132 */         LineString line = GeomUtils.osmShapeToJTSLineString(osmShape);
/* 1133 */         if (line == null)
/*      */           return; 
/* 1136 */         Geometry buffer = GeomUtils.preciseBuffer((Geometry)line, bufferv);
/* 1137 */         if (buffer != null)
/* 1138 */           GeomUtils.setShapeFromJTS(osmShape, buffer); 
/*      */       } else {
/* 1143 */         Geometry toBuffer = GeomUtils.osmShapeToJTS(osmShape);
/* 1144 */         Geometry buffer = GeomUtils.preciseBuffer(toBuffer, bufferv);
/* 1145 */         if (buffer != null)
/* 1146 */           GeomUtils.setShapeFromJTS(osmShape, buffer); 
/*      */       } 
/* 1150 */     } catch (Exception e) {
/* 1151 */       e.printStackTrace();
/*      */     } 
/* 1155 */     if (bufferRandomise > 0.0D) {
/* 1158 */       Polygon polygon = (Polygon)osmShape.outer.getGeometry();
/* 1159 */       LineString outerRing = polygon.getExteriorRing();
/* 1160 */       outerRing = (LineString)Densifier.densify((Geometry)outerRing, 4.0E-4D);
/* 1161 */       LinearRing2D fuzzyShape = new LinearRing2D();
/* 1162 */       for (int p = 0; p < outerRing.getNumPoints(); p++) {
/* 1163 */         Point point = outerRing.getPointN(p);
/* 1166 */         double x1 = Rule.getRandomDouble(-mSize, mSize);
/* 1167 */         double y1 = Rule.getRandomDouble(-mSize, mSize);
/* 1169 */         fuzzyShape.addVertex(new Point2D(point.getX() + x1, point.getY() + y1));
/*      */       } 
/* 1171 */       osmShape.outer.setShape(fuzzyShape);
/* 1173 */       Geometry toBuffer = GeomUtils.osmShapeToJTS(osmShape);
/* 1174 */       Geometry buffer = toBuffer.buffer(this.metreXSize * bufferv, 8, 2);
/* 1175 */       if (buffer != null)
/* 1176 */         GeomUtils.setShapeFromJTS(osmShape, buffer); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void process() {
/* 1186 */     for (NetworkItem item : getNetworkItems()) {
/* 1187 */       if (item instanceof RoadNetwork) {
/* 1188 */         this.roadNetwork = (RoadNetwork)item;
/*      */         break;
/*      */       } 
/*      */     } 
/* 1194 */     getMetres();
/* 1196 */     this.nodeTracker = new NodeTracker();
/* 1198 */     if (this.progress != null)
/* 1199 */       this.progress.progressChanged(this, "Process Regions", 0.0F); 
/* 1202 */     this.polygonTree = new QuadTree(this.area.getMinX() - 5.0D, this.area.getMinY() - 5.0D, this.area.getMaxX() + 5.0D, this.area.getMaxY() + 5.0D);
/* 1204 */     if (this.progress != null)
/* 1205 */       this.progress.progressChanged(this, "Process MultiPolygons", 0.05F); 
/* 1208 */     processMultiPolygons();
/* 1210 */     if (this.progress != null)
/* 1211 */       this.progress.progressChanged(this, "Process Areas", 0.08F); 
/* 1214 */     processAreas();
/* 1216 */     if (this.progress != null)
/* 1217 */       this.progress.progressChanged(this, "Sorting Areas", 0.1F); 
/* 1220 */     sortIntoAreas();
/* 1223 */     if (this.progress != null)
/* 1224 */       this.progress.progressChanged(this, "Processing Nodes", 0.15F); 
/* 1226 */     log.info("Processing Node Trackers");
/* 1227 */     processNodeTrackers();
/* 1229 */     log.info("Processing Nodes");
/* 1230 */     processNodes();
/* 1232 */     if (this.progress != null)
/* 1233 */       this.progress.progressChanged(this, "Processing Nodes", 0.2F); 
/* 1236 */     log.info("Processing Ways");
/* 1238 */     processSingle();
/* 1242 */     preprocessShapes();
/* 1245 */     if (this.progress != null)
/* 1246 */       this.progress.progressChanged(this, "Buffering Objects", 0.25F); 
/* 1251 */     bufferObjects();
/* 1254 */     log.info("Simplifying Polygons");
/* 1255 */     if (this.progress != null)
/* 1256 */       this.progress.progressChanged(this, "Simplifying Polygons", 0.3F); 
/* 1258 */     simplifyObjects();
/* 1262 */     log.info("Removing objects from airport zones");
/* 1263 */     clearAirports();
/* 1265 */     if (this.progress != null)
/* 1266 */       this.progress.progressChanged(this, "Cutting Forests", 0.35F); 
/* 1270 */     cutAirports();
/* 1273 */     if (this.progress != null)
/* 1274 */       this.progress.progressChanged(this, "Cutting Roads", 0.4F); 
/* 1276 */     cutRoads();
/* 1279 */     if (this.progress != null)
/* 1280 */       this.progress.progressChanged(this, "Processing Random objects", 0.5F); 
/* 1284 */     queueRandomObjects();
/* 1287 */     queueLineObjects();
/* 1290 */     if (this.progress != null)
/* 1291 */       this.progress.progressChanged(this, "Processing Objects", 0.55F); 
/* 1295 */     processShapes();
/* 1298 */     if (this.progress != null)
/* 1299 */       this.progress.progressChanged(this, "Processing Exterior Forests", 0.75F); 
/* 1303 */     processExteriorForests();
/* 1307 */     processLodsAndHeights();
/* 1312 */     if (this.autogen.size() > 0) {
/* 1313 */       this.progress.progressChanged(this, "Processing Autogen Strings", 0.79F);
/* 1314 */       log.info("Processing Autogen Strings");
/* 1315 */       placeAutogenStrings();
/* 1316 */       log.info("Processed Autogen Strings");
/*      */     } 
/* 1319 */     if (this.progress != null)
/* 1320 */       this.progress.progressChanged(this, "Processing Random Objects", 0.8F); 
/* 1322 */     log.info("Processing Random Objects");
/* 1323 */     placeRandomObjects();
/* 1324 */     log.info("Random Objects Processed");
/* 1326 */     log.info("Processing Line Rules");
/* 1327 */     placeLineObjects();
/* 1328 */     log.info("Line Rules Processed");
/* 1330 */     if (this.generatorStore.isSmartExclusions()) {
/* 1331 */       log.info("Processing Exclusions");
/* 1332 */       processExclusions();
/* 1333 */       log.info("Exclusions Processed");
/*      */     } 
/* 1337 */     if (this.progress != null)
/* 1338 */       this.progress.progressChanged(this, "Removing outline polygons with parts", 0.82F); 
/* 1386 */     this.randomObjects.clear();
/* 1387 */     this.lineObjects.clear();
/* 1388 */     collectDefinitionNumbers();
/* 1390 */     if (this.progress != null)
/* 1391 */       this.progress.progressChanged(this, "Writing DSF", 0.9F); 
/*      */   }
/*      */   
/*      */   private void placeAutogenStrings() {
/* 1396 */     this.autogen.setRoadNetwork(this.roadNetwork);
/* 1397 */     this.autogen.setMetreXSize(this.metreXSize);
/* 1398 */     this.autogen.process();
/*      */   }
/*      */   
/*      */   public class Collision {
/*      */     final OSMShape osmShape;
/*      */     
/*      */     final Geometry buffered;
/*      */     
/*      */     public Collision(OSMShape osmShape, Geometry buffered) {
/* 1405 */       this.osmShape = osmShape;
/* 1406 */       this.buffered = buffered;
/*      */     }
/*      */     
/*      */     public Geometry getBuffered() {
/* 1410 */       return this.buffered;
/*      */     }
/*      */     
/*      */     public OSMShape getOsmShape() {
/* 1414 */       return this.osmShape;
/*      */     }
/*      */   }
/*      */   
/*      */   private Geometry splitByLine(Geometry polygon, LineString line) {
/* 1425 */     if (!(polygon instanceof Polygon) && !(polygon instanceof com.vividsolutions.jts.geom.MultiPolygon)) {
/* 1427 */       log.error("geometries are not poylgon or multipg types for the firt one, or linestring for the second one.");
/* 1428 */       return null;
/*      */     } 
/* 1430 */     Geometry sourceGeom = polygon;
/* 1431 */     Geometry geom = sourceGeom.getBoundary();
/* 1432 */     LineString lineString = line;
/* 1433 */     Geometry result = null;
/* 1436 */     if (!geom.intersects((Geometry)lineString))
/* 1437 */       return geom; 
/* 1447 */     GeometryFactory geomFactory = new GeometryFactory();
/* 1449 */     Vector<Geometry> lines = new Vector();
/*      */     int i;
/* 1450 */     for (i = 0; i < geom.getNumGeometries(); i++)
/* 1451 */       lines.add(geom.getGeometryN(i)); 
/* 1453 */     for (i = 0; i < lineString.getNumGeometries(); i++)
/* 1454 */       lines.add(lineString.getGeometryN(i)); 
/* 1456 */     MultiLineString multiLineString = geomFactory.createMultiLineString(lines.<LineString>toArray(new LineString[0]));
/* 1457 */     Point mlsPt = geomFactory.createPoint(multiLineString.getCoordinate());
/* 1458 */     Geometry nodedLines = multiLineString.union((Geometry)mlsPt);
/* 1459 */     Polygonizer polygonizer = new Polygonizer();
/* 1460 */     polygonizer.add(nodedLines);
/* 1462 */     Collection polygons = polygonizer.getPolygons();
/* 1464 */     ArrayList<Polygon> polys = new ArrayList();
/* 1465 */     int j = 0;
/* 1466 */     for (Iterator<Polygon> iter = polygons.iterator(); iter.hasNext(); ) {
/* 1468 */       Polygon pg = iter.next();
/* 1469 */       if (sourceGeom.contains((Geometry)pg.getInteriorPoint()))
/* 1470 */         polys.add(pg); 
/*      */     } 
/* 1475 */     return (Geometry)geomFactory.createMultiPolygon(polys.<Polygon>toArray(new Polygon[0]));
/*      */   }
/*      */   
/*      */   private void placeLineObjects() {
/* 1487 */     log.info("Processing " + this.lineObjects.size() + " lines");
/* 1488 */     int count = 0;
/* 1489 */     Quadtree lineQuadTree = new Quadtree();
/* 1492 */     for (LineObject lineObject : this.lineObjects) {
/* 1493 */       processLineObject(lineObject, (SpatialIndex)lineQuadTree);
/* 1494 */       if (count % 1000 == 0)
/* 1495 */         log.info(count + " of " + this.lineObjects.size()); 
/* 1497 */       count++;
/*      */     } 
/* 1499 */     lineQuadTree = null;
/*      */   }
/*      */   
/*      */   private void processLineObject(LineObject lineObject, SpatialIndex lineQuadTree) {
/* 1504 */     OSMPolygon shape = lineObject.shape;
/* 1505 */     if (shape == null)
/*      */       return; 
/* 1508 */     LineRule rule = (LineRule)lineObject.rule;
/* 1510 */     if (!lineObject.clipped && rule.isClipToArea() && shape.getNamedAreas() != null && shape.getNamedAreas().size() > 0)
/*      */       try {
/* 1513 */         HashSet<String> clipAreas = rule.getClipAreas();
/* 1515 */         Geometry area = null;
/*      */         try {
/* 1517 */           for (int i = 0; i < shape.getNamedAreas().size(); i++) {
/* 1518 */             if (clipAreas.contains(((NamedArea)shape.getNamedAreas().get(i)).identifier))
/* 1519 */               if (area == null) {
/* 1520 */                 area = ((NamedArea)shape.getNamedAreas().get(i)).outer.getGeometry();
/*      */               } else {
/* 1522 */                 area = area.union(((NamedArea)shape.getNamedAreas().get(i)).outer.getGeometry());
/*      */               }  
/*      */           } 
/* 1525 */         } catch (Exception e) {
/* 1526 */           e.printStackTrace();
/*      */         } 
/* 1530 */         if (area != null) {
/* 1531 */           LineString lineString = GeomUtils.osmPolygonToJTSLineString(shape);
/* 1533 */           Geometry geometry = lineString.intersection(((NamedArea)shape.getNamedAreas().get(0)).outer.getGeometry());
/* 1535 */           List<OSMPolygon> shapes = GeomUtils.jtsToOsmPolygon(geometry);
/* 1536 */           for (OSMPolygon item : shapes) {
/* 1537 */             LineObject lo = new LineObject();
/* 1538 */             lo.clipped = true;
/* 1539 */             lo.identifier = lineObject.identifier;
/* 1540 */             lo.rule = lineObject.rule;
/* 1541 */             lo.shape = item;
/* 1542 */             processLineObject(lo, lineQuadTree);
/*      */           } 
/*      */         } 
/* 1545 */       } catch (Exception e) {} 
/* 1549 */     Long identifier = lineObject.identifier;
/* 1550 */     if (shape == null || shape.vertexNumber() < 2)
/*      */       return; 
/* 1554 */     List<RangeDistance> ranges = new ArrayList<>();
/* 1555 */     double shapeSize = 0.0D;
/* 1556 */     double runningDistance = 0.0D;
/* 1558 */     Integer groupId = rule.getRandomGroup();
/* 1561 */     if (rule.getCount() > 0) {
/* 1563 */       if (rule.getSet() != null)
/* 1564 */         this.generatorStore.getSetList().add(rule.getSet()); 
/* 1566 */       processRandomLine(shape, rule, ranges, shapeSize, runningDistance, groupId);
/*      */       return;
/*      */     } 
/* 1571 */     for (int x = 0; x < shape.vertexNumber() - 1; x++) {
/* 1572 */       Point2D vertex = shape.vertex(x);
/* 1573 */       Point2D next = shape.vertex(x + 1);
/* 1574 */       if (!vertex.equals(next)) {
/* 1577 */         double d = FastMath.abs(GeomUtils.distanceInMetersFastMath(new LineSegment2D(vertex, next)));
/* 1578 */         if (d != 0.0D) {
/* 1581 */           shapeSize += d;
/* 1582 */           RangeDistance rangeDistance = new RangeDistance();
/* 1583 */           rangeDistance.setStartPoint(vertex);
/* 1584 */           rangeDistance.setEndPoint(next);
/* 1585 */           rangeDistance.setDistance(Double.valueOf(d));
/* 1586 */           rangeDistance.setFrom(Double.valueOf(runningDistance));
/* 1587 */           rangeDistance.setAngle(Double.valueOf(GeomUtils.getBearing(vertex, next)));
/* 1588 */           runningDistance += d;
/* 1589 */           rangeDistance.setTo(Double.valueOf(runningDistance));
/* 1590 */           ranges.add(rangeDistance);
/*      */         } 
/*      */       } 
/*      */     } 
/* 1595 */     double distance = 0.0D;
/* 1596 */     int count = 0;
/* 1597 */     while (distance < runningDistance) {
/* 1598 */       Double randomDistance = null;
/* 1600 */       LineRule.LineRuleObject lineRuleObject = rule.getRandomLineRuleObject(groupId);
/* 1601 */       if (lineRuleObject == null) {
/* 1602 */         log.error("Cannot get random object for line rule " + rule.getFilterList().getFilter() + ". Please check your rules");
/*      */         return;
/*      */       } 
/* 1606 */       if (rule.getMinDensity() == -1.0D || rule.getMaxDensity() == -1.0D) {
/* 1607 */         randomDistance = lineRuleObject.width;
/* 1609 */       } else if (rule.getMinDensity() == rule.getMaxDensity()) {
/* 1610 */         randomDistance = Double.valueOf(rule.getMaxDensity());
/*      */       } else {
/* 1612 */         randomDistance = Double.valueOf(Rule.getRandomDouble(rule.getMinDensity(), rule.getMaxDensity()));
/*      */       } 
/* 1617 */       randomDistance = Double.valueOf(randomDistance.doubleValue() / 2.0D);
/* 1620 */       if (randomDistance.doubleValue() <= 0.0D) {
/* 1621 */         log.error("Random Distance of less than or equals to 0 for line rule: " + rule.getFilterList().getFilter() + ". Please check your rules");
/*      */         return;
/*      */       } 
/* 1626 */       distance += randomDistance.doubleValue();
/* 1633 */       List<PreparedGeometry> buildingCollisions = new ArrayList<>();
/* 1634 */       if (rule.isCollisionTest()) {
/* 1636 */         Envelope searchEnvelope = lineObject.shape.getEnvelope();
/* 1638 */         List<Geometry> data = this.buildingTree.query(searchEnvelope);
/* 1639 */         List<Geometry> posHits = lineQuadTree.query(searchEnvelope);
/* 1643 */         Geometry bufferedRoad = GeomUtils.preciseBuffer(lineObject.shape.getGeometry(), ((LineRule)lineObject.rule).getOffset());
/* 1645 */         PreparedGeometry buffered = PreparedGeometryFactory.prepare(bufferedRoad);
/* 1646 */         if (posHits.size() > 0)
/* 1647 */           for (Geometry object : posHits) {
/* 1648 */             if (buffered == null || object == null || object == null)
/*      */               continue; 
/* 1651 */             if (buffered.contains(object))
/* 1652 */               buildingCollisions.add(PreparedGeometryFactory.prepare(object)); 
/*      */           }  
/* 1657 */         if (data.size() > 0)
/* 1658 */           for (Geometry object : data) {
/* 1659 */             if (buffered == null || object == null)
/*      */               continue; 
/* 1662 */             if (buffered.contains(object))
/* 1663 */               buildingCollisions.add(PreparedGeometryFactory.prepare(object)); 
/*      */           }  
/*      */       } 
/* 1673 */       count = findALocationForIt(lineQuadTree, shape, rule, identifier, ranges, distance, buildingCollisions, count, lineRuleObject);
/* 1674 */       if (lineRuleObject.spacing != null)
/* 1676 */         distance += lineRuleObject.spacing.doubleValue(); 
/* 1680 */       if (rule.getSet() != null)
/* 1681 */         this.generatorStore.getSetList().add(rule.getSet()); 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void processRandomLine(OSMPolygon shape, LineRule rule, List<RangeDistance> ranges, double shapeSize, double runningDistance, Integer groupId) {
/*      */     try {
/* 1688 */       for (int x = 0; x < shape.vertexNumber() - 1; x++) {
/* 1689 */         Point2D vertex = shape.vertex(x);
/* 1690 */         Point2D next = shape.vertex(x + 1);
/* 1691 */         if (!vertex.equals(next)) {
/* 1694 */           double distance = FastMath.abs(GeomUtils.distanceInMetersFastMath(new LineSegment2D(vertex, next)));
/* 1695 */           if (distance != 0.0D) {
/* 1698 */             shapeSize += distance;
/* 1699 */             RangeDistance rangeDistance = new RangeDistance();
/* 1700 */             rangeDistance.setStartPoint(vertex);
/* 1701 */             rangeDistance.setEndPoint(next);
/* 1702 */             rangeDistance.setDistance(Double.valueOf(distance));
/* 1703 */             rangeDistance.setFrom(Double.valueOf(runningDistance));
/* 1704 */             rangeDistance.setAngle(Double.valueOf(GeomUtils.getBearing(vertex, next)));
/* 1705 */             runningDistance += distance;
/* 1706 */             rangeDistance.setTo(Double.valueOf(runningDistance));
/* 1707 */             ranges.add(rangeDistance);
/*      */           } 
/*      */         } 
/*      */       } 
/* 1711 */       for (int count = 0; count < rule.getCount(); count++) {
/* 1713 */         RangeDistance section = ranges.get(Rule.getRandomNumber(0, ranges.size()));
/* 1715 */         double randomDistance = Rule.getRandomDouble(0.0D, section.getDistance().doubleValue());
/* 1716 */         Point2D moveTo = GeomUtils.translateFastLatLon(section.getStartPoint(), section.getAngle().doubleValue(), randomDistance);
/* 1717 */         Float fangle = new Float(section.getAngle().doubleValue() + rule.getAngle());
/* 1718 */         if (fangle.floatValue() < 0.0F)
/* 1719 */           fangle = Float.valueOf(360.0F - FastMath.abs(fangle.floatValue())); 
/* 1721 */         if (fangle.floatValue() > 360.0F)
/* 1722 */           fangle = Float.valueOf(fangle.floatValue() % 360.0F); 
/* 1724 */         DSFObject osmObject = new DSFObject((Rule)rule);
/* 1725 */         osmObject.position = moveTo;
/* 1726 */         osmObject.angle = fangle;
/* 1727 */         LineRule.LineRuleObject lineRuleObject = rule.getRandomLineRuleObject(groupId);
/* 1728 */         osmObject.objectDefinitionNumber = lineRuleObject.objectId;
/* 1729 */         this.osmObjects.add(osmObject);
/*      */       } 
/* 1731 */     } catch (Exception e) {
/* 1732 */       log.error(e.getMessage());
/*      */     } 
/*      */   }
/*      */   
/*      */   private int findALocationForIt(SpatialIndex lineQuadTree, OSMPolygon shape, LineRule rule, Long identifier, List<RangeDistance> ranges, double distance, List<PreparedGeometry> buildingTreeCollision, int count, LineRule.LineRuleObject lineRuleObject) {
/* 1746 */     double offsetAngle = 0.0D;
/* 1747 */     double angle = 0.0D;
/* 1748 */     if (rule.getAngle() != -1.0F)
/* 1749 */       angle = rule.getAngle(); 
/* 1753 */     if (rule.getOffsetAngles() != null && rule.getOffsetAngles().size() > 0) {
/* 1754 */       offsetAngle = ((Float)rule.getOffsetAngles().get(count)).floatValue();
/* 1755 */       count++;
/* 1756 */       if (count >= rule.getOffsetAngles().size())
/* 1757 */         count = 0; 
/*      */     } 
/* 1761 */     for (RangeDistance item : ranges) {
/* 1762 */       if (item.isInRange(distance)) {
/* 1763 */         Double randomDistance = Double.valueOf(item.getTo().doubleValue() - distance);
/* 1766 */         Point2D moveTo = GeomUtils.translateFastLatLon(item.getStartPoint(), item.getAngle().doubleValue(), randomDistance.doubleValue());
/* 1767 */         if (rule.getOffset() > 0.0F)
/* 1768 */           moveTo = GeomUtils.translateFastLatLon(moveTo, item.getAngle().doubleValue() + offsetAngle, rule.getOffset()); 
/* 1770 */         boolean add = false;
/* 1771 */         Float fangle = new Float(item.getAngle().doubleValue() + offsetAngle - angle);
/* 1772 */         if (fangle.floatValue() < 0.0F)
/* 1773 */           fangle = Float.valueOf(360.0F - FastMath.abs(fangle.floatValue())); 
/* 1775 */         if (fangle.floatValue() > 360.0F)
/* 1776 */           fangle = Float.valueOf(fangle.floatValue() % 360.0F); 
/* 1779 */         Point point = GeomUtils.geometryFactory.createPoint(new Coordinate(moveTo.x(), moveTo.y()));
/* 1780 */         PreparedGeometry buffered = PreparedGeometryFactory.prepare(point.buffer(this.metreXSize * 2.0D));
/* 1782 */         if (this.area.contains(moveTo)) {
/* 1783 */           Envelope searchEnvelope = new Envelope(moveTo.x() - this.metreXSize * 4.0D, moveTo.x() + this.metreXSize * 4.0D, moveTo.y() - this.metreYSize * 4.0D, moveTo.y() + this.metreYSize * 4.0D);
/* 1789 */           if (rule.isCollisionTest()) {
/* 1792 */             if (this.generatorStore.isClipRoads() && this.roadNetwork != null && 
/* 1793 */               this.roadNetwork.collides(searchEnvelope))
/*      */               continue; 
/* 1800 */             if (buildingTreeCollision != null && buildingTreeCollision.size() > 0) {
/* 1802 */               boolean inside = false;
/* 1803 */               for (PreparedGeometry hit : buildingTreeCollision) {
/* 1804 */                 if (!hit.getGeometry().getEnvelope().contains((Geometry)point))
/*      */                   continue; 
/* 1807 */                 if (hit.contains((Geometry)point)) {
/* 1808 */                   inside = true;
/*      */                   break;
/*      */                 } 
/*      */               } 
/* 1818 */               if (inside)
/*      */                 continue; 
/*      */             } 
/* 1823 */             add = true;
/*      */           } else {
/* 1828 */             add = true;
/*      */           } 
/*      */         } 
/* 1833 */         if (add) {
/* 1835 */           DSFObject osmObject = new DSFObject((Rule)rule);
/* 1836 */           osmObject.position = moveTo;
/* 1837 */           osmObject.angle = fangle;
/* 1838 */           osmObject.objectDefinitionNumber = lineRuleObject.objectId;
/* 1839 */           this.osmObjects.add(osmObject);
/* 1841 */           if (rule.getSet() != null)
/* 1842 */             this.generatorStore.getSetList().add(rule.getSet()); 
/* 1845 */           if (rule.isTrack())
/* 1847 */             lineQuadTree.insert(buffered.getGeometry().getEnvelopeInternal(), buffered.getGeometry()); 
/* 1849 */           return count;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1853 */     return count;
/*      */   }
/*      */   
/*      */   private void preprocessShapes() {
/* 1858 */     processHeights();
/*      */   }
/*      */   
/*      */   private void processHeights() {
/* 1864 */     for (OSMShape osmShape : this.osmShapes) {
/* 1866 */       if (osmShape.rule != null && osmShape.outer != null && osmShape.rule instanceof ForestRule) {
/* 1867 */         osmShape.outer.setHeight(Float.valueOf((float)osmShape.rule.getDensity()));
/* 1868 */         if (((ForestRule)osmShape.rule).getRandomDensityMax() > 1)
/* 1869 */           osmShape.outer.setHeight(Float.valueOf(((ForestRule)osmShape.rule).getRandomDensity())); 
/*      */       } 
/* 1873 */       if (osmShape.rule != null && osmShape.rule.getAdaptiveHeightPolicy() != null && osmShape.outer.getHeight() == null) {
/* 1876 */         AdaptiveHeight heightPolicy = osmShape.rule.getAdaptiveHeightPolicy();
/* 1877 */         Point2D centroid = osmShape.outer.getCentroid();
/* 1878 */         if (centroid == null)
/*      */           continue; 
/* 1881 */         Envelope envelope = new Envelope(centroid.x() - this.metreXSize * 2000.0D, centroid.x() + this.metreXSize * 2000.0D, centroid.y() - this.metreYSize * 2000.0D, centroid.y() + this.metreYSize * 2000.0D);
/* 1883 */         List<NodeTracker.TrackPoint> trackPoints = this.nodeTracker.query(envelope);
/* 1884 */         if (trackPoints != null && trackPoints.size() > 0)
/* 1886 */           for (NodeTracker.TrackPoint item : trackPoints) {
/* 1887 */             AdaptiveHeight.AdaptiveHeightPolicy adaptiveHeightPolicy = heightPolicy.getRangeForTrackPoint(item);
/* 1888 */             if (adaptiveHeightPolicy != null) {
/* 1889 */               double distance = GeomUtils.distanceInMeters(new LineSegment2D(new Point2D(item.point.getX(), item.point.getY()), centroid));
/* 1890 */               if (distance < (adaptiveHeightPolicy.getRange() * 1000.0F)) {
/* 1891 */                 float height = adaptiveHeightPolicy.getRandomHeight();
/* 1892 */                 osmShape.outer.setHeight(Float.valueOf(height));
/*      */               } 
/*      */             } 
/*      */           }  
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void getObjectHeight(OSMPolygon osmShape, Rule rule) {
/* 1905 */     if (osmShape == null || osmShape.getHeight() != null)
/*      */       return; 
/* 1908 */     if (rule.getAdaptiveHeightPolicy() == null)
/*      */       return; 
/* 1911 */     AdaptiveHeight heightPolicy = this.generatorStore.getHeightPolicies().get(0);
/* 1912 */     Point2D centroid = osmShape.getCentroid();
/* 1913 */     if (centroid == null)
/*      */       return; 
/* 1916 */     Envelope envelope = new Envelope(centroid.x() - this.metreXSize * 8000.0D, centroid.x() + this.metreXSize * 8000.0D, centroid.y() - this.metreYSize * 8000.0D, centroid.y() + this.metreYSize * 8000.0D);
/* 1918 */     List<NodeTracker.TrackPoint> trackPoints = this.nodeTracker.query(envelope);
/* 1919 */     if (trackPoints != null && trackPoints.size() > 0)
/* 1921 */       for (NodeTracker.TrackPoint item : trackPoints) {
/* 1922 */         AdaptiveHeight.AdaptiveHeightPolicy adaptiveHeightPolicy = heightPolicy.getRangeForTrackPoint(item);
/* 1923 */         if (adaptiveHeightPolicy != null) {
/* 1924 */           double distance = GeomUtils.distanceInMeters(new LineSegment2D(new Point2D(item.point.getX(), item.point.getY()), centroid));
/* 1925 */           if (distance < (adaptiveHeightPolicy.getRange() * 1000.0F)) {
/* 1926 */             float height = adaptiveHeightPolicy.getRandomHeight();
/* 1927 */             osmShape.setHeight(Float.valueOf(height));
/*      */           } 
/*      */         } 
/*      */       }  
/*      */   }
/*      */   
/*      */   private void bufferObjects() {
/* 1937 */     for (OSMShape osmShape : this.osmShapes) {
/* 1938 */       if (osmShape.outer != null && osmShape.rule != null && osmShape.rule.getBuffer() > 0.0F)
/* 1940 */         buffer(osmShape, osmShape.rule.getBuffer(), osmShape.rule.getBufferRandomise()); 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void simplifyObjects() {
/* 1946 */     for (OSMShape osmShape : this.osmShapes) {
/* 1947 */       if (osmShape.multiPolygonPart)
/*      */         continue; 
/* 1950 */       if (osmShape.outer != null && osmShape.rule != null && osmShape.rule.getSimplify() != -1.0F)
/* 1951 */         GeomUtils.simplify(osmShape, this.metreXSize); 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void cutAirports() {
/* 1958 */     if (!this.generatorStore.isClipAirports())
/*      */       return; 
/* 1963 */     List<NamedArea> airports = new ArrayList<>();
/* 1964 */     for (NamedArea item : this.areas) {
/* 1965 */       if (item.identifier.equals("aeroway"))
/* 1966 */         airports.add(item); 
/*      */     } 
/* 1970 */     if (airports.size() == 0)
/*      */       return; 
/* 1974 */     log.info("Cutting Forests/Trees within airports");
/* 1975 */     int count = 0;
/* 1978 */     List<OSMShape> toAdd = new ArrayList<>();
/* 1979 */     List<OSMShape> toRemove = new ArrayList<>();
/* 1980 */     for (OSMShape item : this.osmShapes) {
/*      */       try {
/* 1983 */         if (item.outer != null && item.outer.getGeometry() != null && (item.rule instanceof ForestRule || item.rule instanceof RandomRule)) {
/* 1986 */           Geometry subtracted = null;
/* 1987 */           for (NamedArea airport : airports) {
/* 1988 */             Geometry airportShape = airport.outer.getGeometry();
/* 1989 */             if (airportShape == null)
/*      */               continue; 
/* 1994 */             if (airport.preparedGeometryOuter.intersects(item.outer.getGeometry())) {
/* 1996 */               if (subtracted == null)
/* 1997 */                 subtracted = GeomUtils.osmShapeToJTS(item); 
/* 1998 */               if (airportShape instanceof GeometryCollection) {
/* 1999 */                 GeometryCollection collection = (GeometryCollection)airportShape;
/* 2000 */                 for (int x = 0; x < collection.getNumGeometries(); x++)
/* 2001 */                   subtracted = subtracted.difference(collection.getGeometryN(x)); 
/*      */                 continue;
/*      */               } 
/* 2005 */               subtracted = subtracted.difference(airportShape);
/*      */             } 
/*      */           } 
/* 2009 */           if (subtracted != null) {
/* 2012 */             if (subtracted instanceof com.vividsolutions.jts.geom.MultiPolygon || subtracted instanceof MultiLineString) {
/* 2013 */               for (int x = 0; x < subtracted.getNumGeometries(); x++) {
/* 2014 */                 OSMShape newShape = new OSMShape(item.rule);
/* 2015 */                 newShape.setObjectDefinitionNumber(item.objectDefinitionNumber);
/* 2016 */                 GeomUtils.setShapeFromJTS(newShape, subtracted.getGeometryN(x));
/* 2018 */                 if (newShape.outer != null && newShape.outer.getShape() != null) {
/* 2019 */                   toAdd.add(newShape);
/* 2020 */                   newShape.outer.setIdentifier(item.outer.getIdentifier());
/* 2021 */                   toRemove.add(item);
/*      */                 } 
/*      */               } 
/*      */               continue;
/*      */             } 
/* 2025 */             if (subtracted instanceof Polygon) {
/* 2026 */               OSMShape newShape = new OSMShape(item.rule);
/* 2027 */               newShape.setObjectDefinitionNumber(item.objectDefinitionNumber);
/* 2028 */               GeomUtils.setShapeFromJTS(newShape, subtracted);
/* 2029 */               if (newShape.outer != null && newShape.outer.getShape() != null) {
/* 2030 */                 toAdd.add(newShape);
/* 2031 */                 newShape.outer.setIdentifier(item.outer.getIdentifier());
/* 2032 */                 toRemove.add(item);
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/* 2040 */       } catch (Exception e) {}
/*      */     } 
/* 2046 */     this.osmShapes.removeAll(toRemove);
/* 2047 */     this.osmShapes.addAll(toAdd);
/* 2048 */     log.info("Cut " + toRemove.size() + " forests/trees from airports");
/*      */   }
/*      */   
/*      */   private void cutRoads() {
/* 2054 */     if (!this.generatorStore.isClipRoads())
/*      */       return; 
/*      */   }
/*      */   
/*      */   private void getMetres() {
/* 2151 */     Point2D p1 = new Point2D(this.area.getMinX(), this.area.getMinY());
/* 2152 */     Point2D translatedX = GeomUtils.translateFastLatLon(p1, 90.0D, 1.0D);
/* 2153 */     Point2D translatedY = GeomUtils.translateFastLatLon(p1, 180.0D, 1.0D);
/* 2155 */     this.metreXSize = translatedX.x() - p1.x();
/* 2156 */     this.metreYSize = translatedY.y() - p1.y();
/*      */   }
/*      */   
/*      */   private void processNodes() {
/* 2161 */     for (Node node : this.nodes) {
/* 2164 */       GeneratorStore.getGeneratorStore().seedRandomSeeds();
/* 2166 */       String trackedWay = null;
/* 2167 */       if (this.trackedWays.containsKey(Long.valueOf(node.getId())))
/* 2168 */         trackedWay = ((TrackedWay)this.trackedWays.get(Long.valueOf(node.getId()))).getIdentifier(); 
/* 2172 */       for (AcceptingRule entries : node.getRules()) {
/* 2173 */         Rule rule = this.generatorStore.getRule(entries.ruleNumber.shortValue());
/* 2174 */         if (rule.acceptsShape(entries.filterNumber, null, this.possibleRegions, new Rule.Delegate() {
/*      */               public boolean containsNode(String identifier) {
/* 2177 */                 return false;
/*      */               }
/*      */             },  false))
/* 2182 */           addObject(node, Long.valueOf(node.getId()), node.getCoord(), rule, trackedWay, null); 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void processNodeTrackers() {
/* 2191 */     for (Node node : this.nodes) {
/* 2192 */       for (AcceptingRule entries : node.getRules()) {
/* 2193 */         Rule rule = this.generatorStore.getRule(entries.ruleNumber.shortValue());
/* 2194 */         if (rule instanceof NodeTrackerRule)
/* 2195 */           this.nodeTracker.addNode(node.getCoord(), (NodeTrackerRule)rule); 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void processExclusions() {
/* 2205 */     for (OSMShape item : this.osmShapes) {
/* 2206 */       if (item.outer == null)
/*      */         continue; 
/* 2210 */       if (item.rule instanceof FacadeRule)
/* 2211 */         this.buildingExclusions.add(item.outer.getEnvelope()); 
/* 2215 */       if (item.rule instanceof ForestRule && 
/* 2216 */         !((ForestRule)item.rule).isPerimeterOnly())
/* 2217 */         this.forestExclusions.add(item.outer.getEnvelope()); 
/*      */     } 
/* 2221 */     for (DSFObject item : this.osmObjects) {
/* 2222 */       if (item.polygon == null)
/*      */         continue; 
/* 2226 */       if (item.rule instanceof ObjectRule)
/* 2227 */         this.buildingExclusions.add(item.polygon.getEnvelope()); 
/*      */     } 
/* 2231 */     String[] folderAndFileNames = getFolderAndFileNames();
/* 2232 */     String folderName = folderAndFileNames[1];
/* 2233 */     String pathOne = folderAndFileNames[0];
/* 2234 */     String pathTwo = this.generatorStore.getOutputFolder() + File.separatorChar + "Earth nav data" + File.separatorChar + pathOne;
/* 2238 */     ExclusionBuilder exclusionBuilder = new ExclusionBuilder();
/* 2239 */     exclusionBuilder.buildExclusions(this.buildingExclusions, this.generatorStore.getBuildingExclusionRange().intValue(), getArea().getMinX(), this.area.getMinY(), false, pathTwo);
/* 2240 */     exclusionBuilder.buildExclusions(this.forestExclusions, this.generatorStore.getForestExclusionRange().intValue(), getArea().getMinX(), this.area.getMinY(), true, pathTwo);
/*      */   }
/*      */   
/*      */   private void processLodsAndHeights() {
/* 2249 */     for (OSMShape item : this.osmShapes) {
/* 2250 */       if (item.rule instanceof FacadeRule && item.outer != null && item.outer.getCustomFacade() == null) {
/* 2251 */         if (item.objectDefinitionNumber == null)
/* 2252 */           item.objectDefinitionNumber = item.rule.getDefinitionNumber(item); 
/* 2254 */         if (item.outer.getHeight() == null)
/* 2255 */           item.outer.setHeight(new Float(((FacadeRule)item.rule).getHeight())); 
/*      */         try {
/* 2258 */           double area = FastMath.sqrt(item.outer.getArea().floatValue());
/* 2260 */           ObjectDefinitionStore.ObjectDefinition polygonDefinition = this.generatorStore.getPolygonDefinition(item.objectDefinitionNumber.intValue());
/* 2261 */           if (polygonDefinition.getFacade() != null) {
/* 2262 */             if (polygonDefinition.getFacade().isSimpleBuildingOnly() || polygonDefinition.getFacade().isSloped())
/* 2263 */               item.simplify = true; 
/* 2265 */             Integer objNumber = this.generatorStore.getLodForArea(polygonDefinition, area, item.outer.getHeight().floatValue(), item.multiPolygonPart);
/* 2266 */             if (objNumber != null) {
/* 2267 */               item.objectDefinitionNumber = objNumber;
/*      */               continue;
/*      */             } 
/* 2269 */             item.objectDefinitionNumber = item.rule.getDefinitionNumber(item);
/*      */           } 
/* 2274 */         } catch (Exception e) {
/* 2275 */           e.printStackTrace();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void processAreas() {
/* 2282 */     for (DSFPolygon dsfPolygon : this.dsfPolygons) {
/* 2284 */       if (dsfPolygon.getOuter() == null)
/*      */         continue; 
/* 2287 */       for (AcceptingRule entry : dsfPolygon.getRules()) {
/* 2288 */         Rule rule = this.generatorStore.getRule(entry.ruleNumber.shortValue());
/* 2289 */         if (rule instanceof AreaTrackerRule) {
/* 2291 */           boolean accepts = rule.acceptsShape(entry.filterNumber, dsfPolygon.getOuter(), this.possibleRegions, new Rule.Delegate() {
/*      */                 public boolean containsNode(String identifier) {
/* 2294 */                   return DSFTile.this.containsNode(dsfPolygon.getOuter(), identifier);
/*      */                 }
/*      */               }false);
/* 2299 */           if (accepts) {
/* 2301 */             List<DSFPolygon> dividedAreas = new ArrayList<>();
/* 2304 */             if (dsfPolygon.getOuter() != null && ((dsfPolygon.getInner() != null && dsfPolygon.getInner().size() > 0) || dsfPolygon.getOuter().vertexNumber() > 500)) {
/*      */               try {
/* 2308 */                 List<OSMPolygon> broken = GeometryClipper.split(dsfPolygon.getOuter(), dsfPolygon.getInner(), 500);
/* 2309 */                 if (broken != null)
/* 2310 */                   for (OSMPolygon item : broken) {
/* 2311 */                     DSFPolygon newArea = new DSFPolygon();
/* 2312 */                     newArea.setOuter(item);
/* 2313 */                     dividedAreas.add(newArea);
/*      */                   }  
/* 2316 */               } catch (Exception e) {
/* 2317 */                 dividedAreas.add(dsfPolygon);
/*      */               } 
/*      */             } else {
/* 2321 */               dividedAreas.add(dsfPolygon);
/*      */             } 
/* 2324 */             List<DSFPolygon> splitAreas = new ArrayList<>();
/* 2327 */             for (DSFPolygon osmShape : dividedAreas) {
/* 2329 */               if (osmShape.getOuter() != null && !fitsInside(osmShape.getOuter())) {
/* 2330 */                 Map<OSMPolygon, List<OSMPolygon>> rings = GeometryClipper.clip(osmShape.getOuter(), osmShape.getInner(), this.area);
/* 2331 */                 if (rings == null || rings.size() == 0) {
/* 2332 */                   splitAreas.add(osmShape);
/*      */                   continue;
/*      */                 } 
/* 2335 */                 for (Map.Entry<OSMPolygon, List<OSMPolygon>> item : rings.entrySet()) {
/* 2337 */                   DSFPolygon clipped = new DSFPolygon();
/* 2338 */                   clipped.setOuter(item.getKey());
/* 2339 */                   clipped.setInner(item.getValue());
/* 2340 */                   splitAreas.add(clipped);
/*      */                 } 
/*      */                 continue;
/*      */               } 
/* 2344 */               splitAreas.add(osmShape);
/*      */             } 
/* 2351 */             for (DSFPolygon item : splitAreas)
/* 2352 */               trackArea(item.getOuter(), item.getInner(), (AreaTrackerRule)rule); 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private boolean containsNode(OSMPolygon outer, String identifier) {
/* 2363 */     if (outer == null || outer.getGeometry() == null || outer.getEnvelope() == null)
/* 2364 */       return false; 
/* 2366 */     List<NodeTracker.TrackPoint> items = this.nodeTracker.query(outer.getEnvelope());
/* 2367 */     if (items == null || items.size() == 0)
/* 2368 */       return false; 
/* 2370 */     for (NodeTracker.TrackPoint item : items) {
/* 2371 */       if (item.nodeTrackerRule.getIdentifier().equals(identifier) && outer.getGeometry().contains((Geometry)item.point))
/* 2372 */         return true; 
/*      */     } 
/* 2375 */     return false;
/*      */   }
/*      */   
/*      */   private void sortIntoAreas() {
/* 2379 */     log.info("Sorting " + this.dsfPolygons.size() + " polygons into areas.");
/* 2380 */     int polyCount = 0;
/* 2381 */     label90: for (DSFPolygon dsfPolygon : this.dsfPolygons) {
/* 2383 */       if (dsfPolygon.getOuter() != null && dsfPolygon.getRules() != null)
/* 2384 */         for (AcceptingRule acceptingRule : dsfPolygon.getRules()) {
/* 2385 */           Rule rule = this.generatorStore.getRule(acceptingRule.ruleNumber.shortValue());
/* 2386 */           if (rule != null && rule.requiresAreaTracking(acceptingRule.filterNumber)) {
/* 2387 */             Geometry shape = dsfPolygon.getOuter().isClosed() ? GeomUtils.linearRingToJTSPolygon(dsfPolygon.getOuter().getShape()) : (Geometry)GeomUtils.linearRingToJTSLine(dsfPolygon.getOuter().getShape());
/* 2392 */             GeomUtils.linearRingToJTSPolygon(dsfPolygon.getOuter().getShape());
/* 2393 */             Point centroid = shape.getCentroid();
/* 2394 */             shape = null;
/* 2395 */             if (centroid != null)
/*      */               try {
/* 2397 */                 this.polygonTree.set(centroid.getX(), centroid.getY(), dsfPolygon);
/*      */                 continue label90;
/* 2399 */               } catch (Exception e) {
/* 2404 */                 polyCount++;
/*      */                 continue;
/*      */               }  
/*      */             continue label90;
/*      */           } 
/*      */         }  
/*      */     } 
/* 2410 */     int areaCount = 0;
/* 2411 */     for (NamedArea area : this.areas) {
/* 2415 */       if (area.outer != null && area.outer.getArea().floatValue() > 3.0E7F)
/*      */         continue; 
/* 2419 */       Envelope searchArea = area.outer.getEnvelope();
/* 2420 */       if (searchArea == null)
/*      */         continue; 
/* 2423 */       QuadTreePoint[] hits = this.polygonTree.searchWithin(searchArea.getMinX(), searchArea.getMinY(), searchArea.getMaxX(), searchArea.getMaxY());
/* 2424 */       int count = 0;
/* 2425 */       if (hits != null && hits.length > 0)
/* 2426 */         for (QuadTreePoint quadTreePoint : hits) {
/* 2429 */           Object hit = quadTreePoint.getValue();
/* 2430 */           if (hit instanceof DSFPolygon) {
/* 2432 */             DSFPolygon dsfPolygon = (DSFPolygon)hit;
/*      */             try {
/* 2438 */               if (dsfPolygon.getOuter() == null || dsfPolygon.getOuter().getShape() == null)
/*      */                 continue; 
/* 2442 */               LinearRing linearRing = GeomUtils.linearRing2DToJTSLinearRing(dsfPolygon.getOuter().getShape());
/* 2443 */               if (linearRing == null)
/*      */                 continue; 
/* 2446 */               Point point = linearRing.getCentroid();
/* 2447 */               if ((point != null && area.outer.getGeometry() != null && area.preparedGeometryOuter.contains((Geometry)point)) || area.preparedGeometryOuter.intersects((Geometry)linearRing)) {
/* 2450 */                 boolean inside = true;
/* 2452 */                 if (area.inner != null && area.inner.size() > 0)
/* 2453 */                   for (PreparedGeometry inner : area.preparedGeometryInner) {
/* 2454 */                     if (inner != null && inner.contains((Geometry)point)) {
/* 2455 */                       inside = false;
/*      */                       break;
/*      */                     } 
/*      */                   }  
/* 2460 */                 if (inside) {
/* 2461 */                   dsfPolygon.getOuter().addArea(area);
/* 2462 */                   dsfPolygon.getOuter().addAreaIdentifier(area.identifier);
/* 2463 */                   count++;
/*      */                   try {
/* 2465 */                     this.polygonTree.remove(dsfPolygon.getOuter().getCentroid().x(), dsfPolygon.getOuter().getCentroid().y());
/* 2466 */                   } catch (Exception e) {}
/* 2468 */                   if (count % 100000 == 0);
/* 2471 */                   if (dsfPolygon.getInner() != null)
/* 2472 */                     for (OSMPolygon item : dsfPolygon.getInner())
/* 2473 */                       item.addAreaIdentifier(area.identifier);  
/*      */                 } 
/*      */               } 
/* 2478 */             } catch (Exception e) {
/* 2479 */               log.error("", e);
/*      */             } 
/* 2482 */             dsfPolygon.getOuter().clearGeometry();
/*      */           } 
/*      */           continue;
/*      */         }  
/* 2486 */       areaCount++;
/*      */     } 
/* 2489 */     this.polygonTree.clear();
/* 2490 */     this.polygonTree = null;
/* 2491 */     log.info("Sorted " + polyCount + " polygons into areas");
/*      */   }
/*      */   
/*      */   private boolean mergeOuters(OSMRelation osmShape) {
/*      */     try {
/* 2502 */       GeometryFactory geometryFactory = new GeometryFactory();
/* 2504 */       Geometry[] geometries = new Geometry[osmShape.outer.size()];
/* 2505 */       int x = 0;
/* 2506 */       for (OSMRelation.Member item : osmShape.outer) {
/* 2507 */         geometries[x] = GeomUtils.osmPolygonToJTSPolygon(item.shape);
/* 2508 */         x++;
/*      */       } 
/* 2511 */       GeometryCollection geometryCollection = geometryFactory.createGeometryCollection(geometries);
/* 2512 */       Geometry geoemtry = geometryCollection.union();
/* 2514 */       final OSMPolygon merged = OSMPolygon.copyWithoutShape(((OSMRelation.Member)osmShape.outer.get(0)).shape);
/* 2515 */       merged.setShape(GeomUtils.polygonToLinearRing2D(geoemtry));
/* 2518 */       List<AcceptingRule> rules = ((OSMRelation.Member)osmShape.outer.get(0)).rules;
/* 2519 */       if (rules.size() > 0)
/* 2520 */         for (AcceptingRule entrySet : rules) {
/* 2521 */           Rule rule = this.generatorStore.getRule(entrySet.ruleNumber.shortValue());
/* 2522 */           if (rule.acceptsShape(entrySet.filterNumber, merged, this.possibleRegions, new Rule.Delegate() {
/*      */                 public boolean containsNode(String identifier) {
/* 2525 */                   return DSFTile.this.containsNode(merged, identifier);
/*      */                 }
/*      */               }false)) {
/* 2530 */             OSMShape osmShape1 = new OSMShape(rule);
/* 2531 */             osmShape1.customFacade = osmShape.customFacade;
/* 2532 */             osmShape1.outer = merged;
/* 2533 */             osmShape1.objectDefinitionNumber = rule.getDefinitionNumber(osmShape1);
/*      */           } 
/*      */         }  
/* 2537 */     } catch (Exception e) {
/* 2538 */       return false;
/*      */     } 
/* 2541 */     return true;
/*      */   }
/*      */   
/*      */   private void divideMultipolygon(OSMShape osmShape) {
/* 2546 */     if (osmShape.outer.getHeight() == null)
/* 2548 */       if (osmShape.rule instanceof FacadeRule) {
/* 2549 */         getObjectHeight(osmShape.outer, osmShape.rule);
/* 2550 */         if (osmShape.outer.getHeight() == null)
/* 2551 */           osmShape.outer.setHeight(Float.valueOf(((FacadeRule)osmShape.rule).getHeight())); 
/*      */       }  
/* 2554 */     List<OSMPolygon> rings = GeometryClipper.splitMultipolygon(osmShape.outer, osmShape.inner);
/* 2555 */     if (rings == null) {
/* 2557 */       List<OSMPolygon> triangles = GeometryClipper.split(osmShape.outer, osmShape.inner, 500);
/* 2558 */       if (triangles != null)
/* 2559 */         for (OSMPolygon triangle : triangles) {
/* 2560 */           if (!fitsInside(triangle)) {
/* 2561 */             Map<OSMPolygon, List<OSMPolygon>> clipped = GeometryClipper.clip(triangle, null, this.area);
/* 2562 */             if (clipped == null) {
/* 2563 */               osmShape.outer = null;
/* 2564 */               osmShape.inner = null;
/*      */               continue;
/*      */             } 
/* 2567 */             for (Map.Entry<OSMPolygon, List<OSMPolygon>> item : clipped.entrySet()) {
/* 2569 */               OSMPolygon outer = item.getKey();
/* 2571 */               OSMShape oSMShape = new OSMShape(osmShape.rule);
/* 2572 */               oSMShape.objectDefinitionNumber = osmShape.objectDefinitionNumber;
/* 2573 */               oSMShape.rule = osmShape.rule;
/* 2574 */               oSMShape.customFacade = osmShape.customFacade;
/* 2575 */               oSMShape.multiPolygonPart = osmShape.multiPolygonPart;
/* 2576 */               oSMShape.outer = outer;
/* 2577 */               if (((List)item.getValue()).size() > 0)
/* 2578 */                 oSMShape.inner = item.getValue(); 
/* 2580 */               this.osmShapes.add(oSMShape);
/*      */             } 
/*      */             continue;
/*      */           } 
/* 2583 */           OSMShape obj = new OSMShape(osmShape.rule);
/* 2584 */           obj.objectDefinitionNumber = osmShape.objectDefinitionNumber;
/* 2585 */           obj.rule = osmShape.rule;
/* 2586 */           obj.customFacade = osmShape.customFacade;
/* 2587 */           obj.multiPolygonPart = osmShape.multiPolygonPart;
/* 2588 */           obj.outer = triangle;
/* 2589 */           this.osmShapes.add(obj);
/*      */         }  
/* 2597 */       osmShape.outer = null;
/* 2598 */       osmShape.inner = null;
/*      */       return;
/*      */     } 
/* 2601 */     for (OSMPolygon item : rings) {
/* 2603 */       item.setCounterClockwise();
/* 2605 */       OSMShape obj = new OSMShape(osmShape.rule);
/* 2606 */       obj.objectDefinitionNumber = osmShape.objectDefinitionNumber;
/* 2607 */       obj.rule = osmShape.rule;
/* 2608 */       obj.customFacade = osmShape.customFacade;
/* 2609 */       obj.outer = item;
/* 2610 */       obj.multiPolygonPart = osmShape.multiPolygonPart;
/* 2611 */       this.osmShapes.add(obj);
/*      */     } 
/* 2613 */     osmShape.outer = null;
/* 2614 */     osmShape.inner = null;
/*      */   }
/*      */   
/*      */   private DSFObjectDefinition addDSFLocalDefinition(String path, int requiredLevel) {
/* 2618 */     DSFObjectDefinition def = new DSFObjectDefinition(path, requiredLevel);
/* 2619 */     this.objectDefinitions.add(def);
/* 2620 */     return def;
/*      */   }
/*      */   
/*      */   private void collectDefinitionNumbers() {
/* 2628 */     for (OSMShape item : this.osmShapes) {
/* 2629 */       if (item.customFacade != null)
/* 2630 */         item.setObjectDefinitionNumber(item.customFacade); 
/*      */     } 
/* 2634 */     for (OSMShape item : this.osmShapes) {
/* 2635 */       if (item.rule != null && (item.rule instanceof FacadeRule || item.rule instanceof PolygonRule || item.rule instanceof ForestRule || item.rule instanceof com.world2xplane.Rules.AutogenStringRule)) {
/* 2637 */         int definitionNumber = item.getObjectDefinitionNumber().intValue();
/* 2638 */         if (definitionNumber == -1)
/* 2639 */           throw new RuntimeException("No definition number"); 
/* 2641 */         String path = this.generatorStore.getPolygonDefinition(definitionNumber).getPath();
/* 2644 */         if (item.multiPolygonPart) {
/* 2646 */           this.polygonDefinitions.add(new DSFObjectDefinition(path, 2));
/* 2647 */           item.setRequiredLevel((byte)2);
/*      */           continue;
/*      */         } 
/* 2649 */         this.polygonDefinitions.add(new DSFObjectDefinition(path, item.rule.getRequiredLevel()));
/* 2650 */         item.setRequiredLevel((byte)item.rule.getRequiredLevel());
/*      */         continue;
/*      */       } 
/* 2653 */       if (item.rule instanceof RandomRule) {
/* 2654 */         if (!((RandomRule)item.rule).isForestsOnEmpty())
/* 2655 */           throw new RuntimeException("Found Polygon with object rule"); 
/* 2657 */         int definitionNumber = item.objectDefinitionNumber.intValue();
/* 2658 */         if (definitionNumber == -1)
/* 2659 */           throw new RuntimeException("No definition number"); 
/* 2661 */         String path = this.generatorStore.getPolygonDefinition(definitionNumber).getPath();
/* 2662 */         this.polygonDefinitions.add(new DSFObjectDefinition(path, item.rule.getRequiredLevel()));
/*      */         continue;
/*      */       } 
/* 2663 */       if (item.rule != null)
/* 2664 */         throw new RuntimeException("Found Polygon with object rule"); 
/*      */     } 
/* 2669 */     for (DSFObject item : this.osmObjects) {
/* 2670 */       if (item.rule != null && (item.rule instanceof ObjectRule || item.rule instanceof RandomRule || item.rule instanceof LineRule)) {
/* 2672 */         if (item.objectDefinitionNumber != null) {
/* 2673 */           String path = this.generatorStore.getObjectDefinition(item.objectDefinitionNumber.intValue()).getPath();
/* 2674 */           if (item.getPolygon() != null && item.getPolygon().isBuildingPart()) {
/* 2675 */             this.objectDefinitions.add(new DSFObjectDefinition(path, 1));
/* 2676 */             item.setRequiredLevel((byte)1);
/*      */             continue;
/*      */           } 
/* 2678 */           this.objectDefinitions.add(new DSFObjectDefinition(path, item.rule.getRequiredLevel()));
/* 2679 */           item.setRequiredLevel((byte)item.rule.getRequiredLevel());
/*      */           continue;
/*      */         } 
/* 2682 */         throw new RuntimeException("Found object with no definition number");
/*      */       } 
/* 2684 */       if (item.rule != null)
/* 2685 */         throw new RuntimeException("Found Object with polygon rule"); 
/*      */     } 
/* 2690 */     if (this.autogen != null)
/* 2691 */       for (AutogenObject item : this.autogen.getAutogenObjects()) {
/* 2692 */         int definitionNumber = item.getObjectDefinitionNumber();
/* 2693 */         if (definitionNumber == -1)
/* 2694 */           throw new RuntimeException("No definition number"); 
/* 2696 */         String path = this.generatorStore.getPolygonDefinition(definitionNumber).getPath();
/* 2698 */         this.polygonDefinitions.add(new DSFObjectDefinition(path, 2));
/*      */       }  
/* 2703 */     for (DSFObjectDefinition item : this.polygonDefinitions)
/* 2704 */       this.polygonDefinitionNumbers.add(item); 
/* 2706 */     for (DSFObjectDefinition item : this.objectDefinitions)
/* 2707 */       this.objectDefinitionNumbers.add(item); 
/* 2711 */     Collections.sort(this.polygonDefinitionNumbers, new Comparator<DSFObjectDefinition>() {
/*      */           public int compare(DSFObjectDefinition dsfObjectDefinition, DSFObjectDefinition dsfObjectDefinition2) {
/* 2714 */             return (new Integer(dsfObjectDefinition2.getRequiredLevel())).compareTo(Integer.valueOf(dsfObjectDefinition.getRequiredLevel()));
/*      */           }
/*      */         });
/* 2718 */     Collections.sort(this.objectDefinitionNumbers, new Comparator<DSFObjectDefinition>() {
/*      */           public int compare(DSFObjectDefinition dsfObjectDefinition, DSFObjectDefinition dsfObjectDefinition2) {
/* 2721 */             return (new Integer(dsfObjectDefinition2.getRequiredLevel())).compareTo(Integer.valueOf(dsfObjectDefinition.getRequiredLevel()));
/*      */           }
/*      */         });
/*      */   }
/*      */   
/*      */   private void addObject(Node node, Long identifier, Point2D coord, Rule rule, String wayTracker, Float angle) {
/* 2735 */     if (rule instanceof ObjectRule) {
/* 2736 */       DSFObject osmObject = new DSFObject(rule);
/* 2737 */       osmObject.position = coord;
/* 2738 */       osmObject.angle = getObjectAngle(identifier, wayTracker, (ObjectRule)rule, coord);
/* 2740 */       if (((ObjectRule)rule).isAdjustHeight()) {
/* 2741 */         CustomObjectHeight customObjectHeight = new CustomObjectHeight(rule, osmObject, node);
/* 2742 */         Integer definitionNumber = customObjectHeight.generateObject();
/* 2743 */         if (definitionNumber != null) {
/* 2744 */           osmObject.objectDefinitionNumber = definitionNumber;
/* 2745 */           osmObject.wayTracker = wayTracker;
/* 2746 */           this.osmObjects.add(osmObject);
/*      */         } 
/*      */       } else {
/* 2749 */         osmObject.objectDefinitionNumber = rule.getDefinitionNumber(osmObject);
/* 2750 */         osmObject.wayTracker = wayTracker;
/* 2751 */         this.osmObjects.add(osmObject);
/*      */       } 
/* 2753 */     } else if (rule instanceof RandomRule) {
/* 2754 */       DSFObject osmObject = new DSFObject(rule);
/* 2755 */       osmObject.position = coord;
/* 2756 */       osmObject.angle = Float.valueOf((float)((RandomRule)rule).getRandomAngle());
/* 2757 */       osmObject.objectDefinitionNumber = rule.getDefinitionNumber(osmObject);
/* 2758 */       this.osmObjects.add(osmObject);
/* 2759 */     } else if (rule instanceof LineRule) {
/* 2760 */       DSFObject osmObject = new DSFObject(rule);
/* 2761 */       osmObject.position = coord;
/* 2762 */       if (angle != null) {
/* 2763 */         osmObject.angle = angle;
/*      */       } else {
/* 2765 */         osmObject.angle = Float.valueOf(((LineRule)rule).getRandomAngle());
/*      */       } 
/* 2766 */       osmObject.objectDefinitionNumber = rule.getDefinitionNumber(osmObject);
/* 2767 */       this.osmObjects.add(osmObject);
/*      */     } 
/*      */   }
/*      */   
/*      */   private Float getObjectAngle(Long identifier, String wayTracker, ObjectRule objectRule, Point2D point) {
/* 2774 */     if (objectRule.isAngleFromRoad()) {
/* 2776 */       GeometryFactory geometryFactory = GeomUtils.geometryFactory;
/* 2777 */       Point geom = geometryFactory.createPoint(new Coordinate(point.x(), point.y()));
/* 2778 */       Geometry buffered = geom.buffer(this.metreXSize * 20.0D);
/* 2779 */       Double angle = this.roadNetwork.getAngleFromNearestRoad(buffered);
/* 2780 */       if (angle != null)
/* 2781 */         return Float.valueOf((angle.floatValue() + 90.0F) % 360.0F); 
/*      */     } 
/* 2785 */     if (wayTracker == null || wayTracker.length() == 0)
/* 2786 */       return null; 
/* 2790 */     TrackedWay trackedWay = this.trackedWays.get(identifier);
/* 2792 */     if (trackedWay != null) {
/* 2793 */       Node n1 = null;
/* 2794 */       Node n2 = null;
/* 2795 */       for (int x = 0; x < trackedWay.getNodes().size(); x++) {
/* 2796 */         Node node = trackedWay.getNodes().get(x);
/* 2797 */         if (node.getId() == identifier.longValue()) {
/* 2798 */           n1 = node;
/* 2799 */           if (x < trackedWay.getNodes().size() - 1) {
/* 2800 */             n2 = trackedWay.getNodes().get(x + 1);
/*      */             break;
/*      */           } 
/* 2802 */           if (x > 0) {
/* 2803 */             n2 = n1;
/* 2804 */             n1 = trackedWay.getNodes().get(x - 1);
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/* 2809 */       if (n1 != null && n2 != null) {
/* 2810 */         float angle = (float)GeomUtils.getBearing(n2.getCoord(), n1.getCoord());
/* 2811 */         if (objectRule.getAngleFromWayBase() != null) {
/* 2812 */           angle += objectRule.getAngleFromWayBase().floatValue();
/* 2813 */           if (angle > 360.0F)
/* 2814 */             angle -= 360.0F; 
/*      */         } 
/* 2817 */         return Float.valueOf(angle);
/*      */       } 
/*      */     } 
/* 2822 */     return null;
/*      */   }
/*      */   
/*      */   private boolean addObject(final OSMPolygon shape, final Rule rule) {
/* 2826 */     DSFObject osmObject = new DSFObject(rule);
/* 2827 */     osmObject.polygon = shape;
/* 2828 */     getObjectHeight(shape, rule);
/* 2830 */     if (rule instanceof ObjectRule) {
/* 2831 */       Integer object = ((ObjectRule)rule).getDefinitionNumber(shape, new Rule.Delegate() {
/*      */             public boolean containsNode(String identifier) {
/* 2834 */               return true;
/*      */             }
/*      */             
/*      */             public RoadNetwork getRoadNetwork() {
/* 2840 */               if (rule instanceof ObjectRule && ((ObjectRule)rule).isStreetFacing() && 
/* 2841 */                 DSFTile.this.roadNetwork != null && shape != null)
/* 2842 */                 return DSFTile.this.roadNetwork; 
/* 2845 */               return null;
/*      */             }
/*      */             
/*      */             public double getMetreSize() {
/* 2851 */               return DSFTile.this.metreXSize;
/*      */             }
/*      */           });
/* 2854 */       if (object == null)
/* 2855 */         return false; 
/* 2857 */       osmObject.objectDefinitionNumber = object;
/*      */     } else {
/* 2859 */       osmObject.objectDefinitionNumber = rule.getDefinitionNumber(shape);
/*      */     } 
/* 2862 */     if (shape.getRegion() != null && !shape.getRegion().isEmpty()) {
/* 2863 */       Integer count = this.regionalBuildingsCount.get(shape.getRegion());
/* 2864 */       if (count == null)
/* 2865 */         count = Integer.valueOf(0); 
/* 2867 */       Integer integer1 = count, integer2 = count = Integer.valueOf(count.intValue() + 1);
/* 2868 */       this.regionalBuildingsCount.put(shape.getRegion(), count);
/*      */     } 
/* 2873 */     osmObject.getOriginAndAngle(this.generatorStore.getObjectDefinition(osmObject.objectDefinitionNumber.intValue()));
/* 2874 */     if (osmObject.position == null)
/* 2875 */       return false; 
/* 2877 */     if (!this.area.contains(osmObject.position))
/* 2878 */       return false; 
/* 2881 */     Envelope envelope = shape.getEnvelope();
/* 2882 */     this.buildingTree.insert(envelope, shape.getGeometry());
/* 2885 */     osmObject.polygon = null;
/* 2887 */     this.osmObjects.add(osmObject);
/* 2888 */     shape.clearGeometry();
/* 2889 */     shape.setShape(null);
/* 2892 */     return true;
/*      */   }
/*      */   
/*      */   public void addRandomObjects(Long identifier, OSMPolygon shape, List<OSMPolygon> inner, Rule rule) {
/* 2900 */     if (!shape.vertex(shape.vertexNumber() - 1).equals(shape.firstPoint()))
/*      */       return; 
/* 2906 */     OSMShape osmShape = new OSMShape(rule);
/* 2907 */     osmShape.outer = shape;
/* 2908 */     osmShape.inner = inner;
/* 2909 */     this.osmShapes.add(osmShape);
/*      */   }
/*      */   
/*      */   public void queueRandomObjects() {
/* 2916 */     Set<OSMShape> toRemove = new HashSet<>();
/* 2917 */     for (OSMShape item : this.osmShapes) {
/* 2918 */       if (item.outer != null && item.rule != null && item.rule instanceof RandomRule) {
/* 2920 */         RandomObject randomObject = new RandomObject();
/* 2921 */         randomObject.identifier = item.outer.getIdentifier();
/* 2922 */         randomObject.shape = item.outer;
/* 2923 */         randomObject.rule = item.rule;
/* 2924 */         randomObject.inner = new ArrayList<>();
/* 2925 */         if (item.inner != null)
/* 2926 */           for (OSMPolygon inner : item.inner)
/* 2927 */             randomObject.inner.add(inner.getShape());  
/* 2931 */         this.randomObjects.add(randomObject);
/* 2932 */         toRemove.add(item);
/*      */       } 
/*      */     } 
/* 2936 */     this.osmShapes.removeAll(toRemove);
/*      */   }
/*      */   
/*      */   public void queueLineObjects() {
/* 2945 */     Set<OSMShape> toRemove = new HashSet<>();
/* 2946 */     for (OSMShape item : this.osmShapes) {
/* 2947 */       if (item.outer != null && item.rule != null && item.rule instanceof LineRule) {
/* 2949 */         LineObject lineObject = new LineObject();
/* 2950 */         lineObject.identifier = item.outer.getIdentifier();
/* 2951 */         lineObject.shape = item.outer;
/* 2952 */         lineObject.rule = item.rule;
/* 2953 */         this.lineObjects.add(lineObject);
/* 2954 */         toRemove.add(item);
/*      */       } 
/*      */     } 
/* 2958 */     this.osmShapes.removeAll(toRemove);
/*      */   }
/*      */   
/*      */   private void placeRandomObjects() {
/* 2966 */     long totalPlaced = 0L;
/* 2967 */     long forestCount = 0L;
/* 2968 */     for (RandomObject randomObject : this.randomObjects) {
/*      */       try {
/* 2972 */         if (randomObject.shape == null || randomObject.shape.vertexNumber() == 0)
/*      */           continue; 
/* 2976 */         int placed = 0;
/* 2977 */         OSMPolygon shape = randomObject.shape;
/* 2978 */         RandomRule rule = (RandomRule)randomObject.rule;
/* 2979 */         if (rule.isDetermineAngle()) {
/* 2981 */           LinearRing2D minimumRectangle = shape.getMinRectangle();
/* 2982 */           if (minimumRectangle != null && minimumRectangle.vertexNumber() > 1) {
/* 2983 */             Point2D vertex = minimumRectangle.vertex(0);
/* 2984 */             Point2D next = minimumRectangle.vertex(1);
/* 2985 */             rule.setAngle((int)GeomUtils.getBearing((vertex.y() < next.y()) ? vertex : next, (vertex.y() < next.y()) ? next : vertex));
/*      */           } 
/*      */         } 
/* 2988 */         Long identifier = randomObject.identifier;
/* 2992 */         List<LinearRing2D> triangles = GeometryClipper.triangulate(shape.getShape(), randomObject.inner);
/* 2993 */         if (triangles == null || triangles.size() == 0)
/*      */           continue; 
/* 2997 */         double areaM = FastMath.abs(GeomUtils.areaInMeters(shape.getShape()));
/* 2999 */         double density = rule.getDensity(shape);
/* 3001 */         int numberOfEntries = (int)(FastMath.sqrt(areaM) / density);
/* 3003 */         Map<Integer, Integer> triangleAreas = new HashMap<>();
/* 3004 */         if (rule.getCount() <= 0) {
/* 3005 */           int z = 0;
/* 3006 */           for (LinearRing2D item : triangles) {
/* 3007 */             double triangleSize = FastMath.abs(GeomUtils.areaInMeters(item));
/* 3008 */             triangleAreas.put(Integer.valueOf(z), Integer.valueOf((int)(triangleSize / areaM * numberOfEntries)));
/* 3009 */             z++;
/*      */           } 
/*      */         } else {
/* 3012 */           int z = 0;
/* 3014 */           for (LinearRing2D item : triangles) {
/* 3015 */             triangleAreas.put(Integer.valueOf(z), Integer.valueOf(0));
/* 3016 */             z++;
/*      */           } 
/* 3019 */           for (int x = 0; x < rule.getCount(); x++) {
/* 3020 */             int index = Rule.getRandomNumber(0, z);
/* 3021 */             int count = ((Integer)triangleAreas.get(Integer.valueOf(index))).intValue();
/* 3022 */             count++;
/* 3023 */             triangleAreas.put(Integer.valueOf(index), Integer.valueOf(count));
/*      */           } 
/*      */         } 
/* 3028 */         for (Map.Entry<Integer, Integer> item : triangleAreas.entrySet()) {
/* 3029 */           LinearRing2D triangle = triangles.get(((Integer)item.getKey()).intValue());
/* 3030 */           boolean forestPlaced = false;
/* 3031 */           if (rule.isForestsOnEmpty() && rule.getForestFiles() != null && rule.getForestFiles().size() > 0) {
/* 3033 */             Envelope searchEnvelope = new Envelope(GeomUtils.linearRingToJTSPolygon(triangle).getEnvelopeInternal());
/* 3034 */             List<Geometry> data = this.buildingTree.query(searchEnvelope);
/* 3035 */             boolean empty = true;
/* 3036 */             if (data.size() == 0) {
/* 3037 */               empty = true;
/*      */             } else {
/* 3039 */               for (Geometry building : data) {
/* 3040 */                 if (triangle.isInside(new Point2D(building.getCentroid().getX(), building.getCentroid().getY()))) {
/* 3041 */                   empty = false;
/*      */                   break;
/*      */                 } 
/*      */               } 
/*      */             } 
/* 3046 */             if (empty) {
/* 3048 */               forestPlaced = true;
/* 3049 */               ForestRule dummyRule = new ForestRule(this.generatorStore);
/* 3050 */               dummyRule.getForestFiles().addAll(rule.getForestFiles());
/* 3051 */               dummyRule.setDensity(50);
/* 3052 */               OSMShape osmShape = new OSMShape((Rule)dummyRule);
/* 3053 */               osmShape.outer = new OSMPolygon(triangle);
/* 3054 */               osmShape.objectDefinitionNumber = Integer.valueOf(rule.getRandomForest());
/* 3055 */               this.osmShapes.add(osmShape);
/* 3056 */               forestCount++;
/*      */             } 
/*      */           } 
/* 3061 */           if (!forestPlaced) {
/* 3062 */             int count = ((Integer)item.getValue()).intValue();
/* 3063 */             if (count < 100)
/* 3064 */               for (int x = 0; x < count; x++) {
/* 3066 */                 if (getRandomPoint(rule, identifier, triangle)) {
/* 3067 */                   placed++;
/* 3070 */                 } else if (rule.getCount() > 0 && 
/* 3071 */                   triangleAreas.containsKey(Integer.valueOf(((Integer)item.getKey()).intValue() + 1))) {
/* 3072 */                   int c = ((Integer)triangleAreas.get(Integer.valueOf(((Integer)item.getKey()).intValue() + 1))).intValue();
/* 3073 */                   triangleAreas.put(Integer.valueOf(((Integer)item.getKey()).intValue() + 1), Integer.valueOf(c + count));
/*      */                 } 
/*      */               }  
/*      */           } 
/*      */         } 
/* 3081 */         totalPlaced += placed;
/* 3084 */       } catch (Exception e) {
/* 3085 */         e.printStackTrace();
/*      */       } 
/*      */     } 
/* 3092 */     log.info("Placed " + totalPlaced + " random items");
/* 3093 */     if (forestCount > 0L)
/* 3094 */       log.info("Placed " + forestCount + " random items as forests"); 
/*      */   }
/*      */   
/*      */   private boolean getRandomPoint(RandomRule rule, Long identifier, LinearRing2D triangle) {
/* 3103 */     GeometryFactory geometryFactory = new GeometryFactory();
/* 3104 */     int count = 0;
/*      */     while (true) {
/* 3106 */       if (count > 100)
/* 3107 */         return false; 
/* 3110 */       double r1 = Rule.getRandomDouble();
/* 3111 */       double r2 = Rule.getRandomDouble();
/* 3112 */       Point2D a = triangle.vertex(0);
/* 3113 */       Point2D b = triangle.vertex(1);
/* 3114 */       Point2D c = triangle.vertex(2);
/* 3120 */       double rx = (1.0D - FastMath.sqrt(r1)) * a.x() + FastMath.sqrt(r1) * (1.0D - r2) * b.x() + FastMath.sqrt(r1) * r2 * c.x();
/* 3121 */       double ry = (1.0D - FastMath.sqrt(r1)) * a.y() + FastMath.sqrt(r1) * (1.0D - r2) * b.y() + FastMath.sqrt(r1) * r2 * c.y();
/* 3122 */       Point2D randomPoint = new Point2D(rx, ry);
/* 3125 */       Point point = geometryFactory.createPoint(new Coordinate(rx, ry));
/* 3126 */       if (this.area.contains(randomPoint.x(), randomPoint.y())) {
/* 3129 */         if (rule.isCollisionTest()) {
/* 3130 */           Envelope searchEnvelope = new Envelope(rx - this.metreXSize * 5.0D, rx + this.metreXSize * 5.0D, ry - this.metreYSize * 5.0D, ry + this.metreYSize * 5.0D);
/* 3132 */           if (this.generatorStore.isClipRoads() && this.roadNetwork != null && 
/* 3133 */             this.roadNetwork.collides(searchEnvelope)) {
/* 3134 */             count++;
/*      */             continue;
/*      */           } 
/* 3140 */           List<Geometry> data = this.buildingTree.query(searchEnvelope);
/* 3141 */           if (data != null && data.size() > 0) {
/* 3143 */             boolean inside = false;
/* 3144 */             for (Geometry item : data) {
/* 3146 */               if (item != null && item.contains((Geometry)point)) {
/* 3147 */                 inside = true;
/*      */                 break;
/*      */               } 
/*      */             } 
/* 3151 */             if (inside) {
/* 3152 */               count++;
/*      */               continue;
/*      */             } 
/* 3155 */             addObject(null, identifier, randomPoint, (Rule)rule, null, null);
/* 3156 */             return true;
/*      */           } 
/* 3159 */           addObject(null, identifier, randomPoint, (Rule)rule, null, null);
/* 3160 */           return true;
/*      */         } 
/* 3163 */         addObject(null, identifier, randomPoint, (Rule)rule, null, null);
/* 3164 */         return true;
/*      */       } 
/*      */       break;
/*      */     } 
/* 3166 */     return false;
/*      */   }
/*      */   
/*      */   private void processExteriorForests() {}
/*      */   
/*      */   private List<OSMPolygon> removeSharedEdges(OSMShape item, List<OSMShape> collisions) {
/* 3270 */     boolean changed = false;
/* 3272 */     List<LineString> lineStrings = new ArrayList<>();
/* 3273 */     Collection<LineSegment2D> edges = item.outer.edges();
/* 3275 */     for (LineSegment2D edge : edges) {
/* 3277 */       boolean hits = false;
/* 3278 */       Coordinate c1 = new Coordinate(edge.firstPoint().x(), edge.firstPoint().y());
/* 3279 */       Coordinate c2 = new Coordinate(edge.lastPoint().x(), edge.lastPoint().y());
/* 3281 */       for (OSMShape collision : collisions) {
/* 3283 */         if (collision != item && !collision.outer.getIdentifier().equals(item.outer.getIdentifier()))
/* 3284 */           for (LineSegment2D sharedEdge : collision.outer.edges()) {
/* 3285 */             Coordinate coordinate1 = new Coordinate(sharedEdge.firstPoint().x(), sharedEdge.firstPoint().y());
/* 3286 */             Coordinate coordinate2 = new Coordinate(sharedEdge.lastPoint().x(), sharedEdge.lastPoint().y());
/* 3287 */             if (coordinate1.equals(c1) && coordinate2.equals(c2)) {
/* 3288 */               hits = true;
/* 3289 */               changed = true;
/*      */               break;
/*      */             } 
/* 3292 */             if (coordinate2.equals(c1) && coordinate1.equals(c2)) {
/* 3293 */               hits = true;
/* 3294 */               changed = true;
/*      */             } 
/*      */           }  
/*      */       } 
/* 3301 */       if (!hits) {
/* 3302 */         LineString lineString = GeomUtils.geometryFactory.createLineString(new Coordinate[] { c1, c2 });
/* 3303 */         lineStrings.add(lineString);
/*      */       } 
/*      */     } 
/* 3307 */     if (changed) {
/* 3309 */       LineMerger lineMerger = new LineMerger();
/* 3310 */       lineMerger.add(lineStrings);
/* 3312 */       Collection<Geometry> lines = lineMerger.getMergedLineStrings();
/* 3313 */       List<OSMPolygon> linearRing2D = new ArrayList<>();
/* 3315 */       for (Geometry line : lines) {
/* 3316 */         List<OSMPolygon> geometry = GeomUtils.jtsToOsmPolygon(line);
/* 3317 */         linearRing2D.addAll(geometry);
/*      */       } 
/* 3320 */       return linearRing2D;
/*      */     } 
/* 3322 */     return null;
/*      */   }
/*      */   
/*      */   public void addLineObjects(Long identifier, OSMPolygon shape, LineRule rule) {
/* 3339 */     OSMShape osmShape = new OSMShape((Rule)rule);
/* 3340 */     osmShape.outer = shape;
/* 3341 */     this.osmShapes.add(osmShape);
/*      */   }
/*      */   
/* 3345 */   DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
/*      */   
/* 3347 */   NumberFormat numberFormat = new DecimalFormat("#.############", this.formatSymbols);
/*      */   
/* 3351 */   NumberFormat heightFormat = new DecimalFormat("#.##", this.formatSymbols);
/*      */   
/*      */   private Point2D tilePosition;
/*      */   
/* 3357 */   private static Logger log = LoggerFactory.getLogger(DSFTile.class);
/*      */   
/* 3360 */   private List<OSMShape> osmShapes = new ArrayList<>();
/*      */   
/* 3361 */   private Autogen autogen = new Autogen(this);
/*      */   
/* 3367 */   private List<DSFPolygon> dsfPolygons = new ArrayList<>();
/*      */   
/* 3368 */   private Map<Long, OSMRelation> multiPolygons = new HashMap<>();
/*      */   
/* 3371 */   private List<DSFObject> osmObjects = new ArrayList<>();
/*      */   
/*      */   public DSFTile(Point2D tilePosition, GeneratorStore generatorStore, short longitude, short latitude, Progress progress) {
/* 3375 */     this.longitude = longitude;
/* 3376 */     this.latitude = latitude;
/* 3377 */     this.tilePosition = tilePosition;
/* 3378 */     this.generatorStore = generatorStore;
/* 3379 */     this.area = new Box2D(this.tilePosition, 1.0D, 1.0D);
/* 3380 */     this.polygonDefinitions = new HashSet<>();
/* 3381 */     this.objectDefinitions = new HashSet<>();
/* 3382 */     this.progress = progress;
/* 3383 */     getUsedRegions();
/*      */   }
/*      */   
/*      */   public boolean containsPoint(Point2D point) {
/* 3387 */     return this.area.contains(point);
/*      */   }
/*      */   
/*      */   public DSFTile(int lon, int lat, GeneratorStore generatorStore, Progress progress) {
/* 3391 */     this(new Point2D(lon, lat), generatorStore, (short)lon, (short)lat, progress);
/*      */   }
/*      */   
/*      */   public int hashCode() {
/* 3396 */     return Objects.hashCode(new Object[] { Double.valueOf(this.tilePosition.x()), Double.valueOf(this.tilePosition.y()) });
/*      */   }
/*      */   
/*      */   public boolean equals(Object other) {
/* 3401 */     if (other == null || !(other instanceof DSFTile))
/* 3402 */       return false; 
/* 3404 */     return (((DSFTile)other).hashCode() == hashCode());
/*      */   }
/*      */   
/*      */   public String toString() {
/* 3408 */     String n = (this.tilePosition.y() >= 0.0D) ? "N" : "S";
/* 3409 */     String e = (this.tilePosition.y() >= 0.0D) ? "E" : "W";
/* 3411 */     return String.format("%s.dsf - (%d'%s,%d'%s)", new Object[] { getFolderAndFileNames()[1], Integer.valueOf((int)FastMath.abs(this.tilePosition.y())), n, Integer.valueOf((int)FastMath.abs(this.tilePosition.x())), e });
/*      */   }
/*      */   
/*      */   public String[] getFolderAndFileNames() {
/* 3416 */     int latitude = (int)this.tilePosition.y();
/* 3417 */     int longitude = (int)this.tilePosition.x();
/* 3419 */     Double folderFirst = new Double(latitude);
/* 3420 */     Double folderEnd = new Double(longitude);
/* 3421 */     while (folderFirst.doubleValue() % 10.0D != 0.0D)
/* 3422 */       Double double_1 = folderFirst, double_2 = folderFirst = Double.valueOf(folderFirst.doubleValue() - 1.0D); 
/* 3424 */     while (folderEnd.doubleValue() % 10.0D != 0.0D)
/* 3425 */       Double double_1 = folderEnd, double_2 = folderEnd = Double.valueOf(folderEnd.doubleValue() - 1.0D); 
/* 3428 */     DecimalFormat dfLat = (DecimalFormat)DecimalFormat.getNumberInstance();
/* 3429 */     dfLat.applyPattern("00");
/* 3430 */     DecimalFormat dfLong = (DecimalFormat)DecimalFormat.getNumberInstance();
/* 3432 */     dfLong.applyPattern("000");
/* 3433 */     StringBuffer f1 = new StringBuffer();
/* 3434 */     StringBuffer f2 = new StringBuffer();
/* 3435 */     if (latitude >= 0) {
/* 3436 */       f1.append("+");
/* 3437 */       f2.append("+");
/*      */     } 
/* 3439 */     f1.append(dfLat.format(latitude));
/* 3440 */     f2.append(dfLat.format(folderFirst));
/* 3441 */     if (longitude >= 0) {
/* 3442 */       f1.append("+");
/* 3443 */       f2.append("+");
/*      */     } 
/* 3445 */     f1.append(dfLong.format(longitude));
/* 3446 */     f2.append(dfLong.format(folderEnd));
/* 3447 */     String filename = f1.toString();
/* 3448 */     String foName = f2.toString();
/* 3449 */     String[] result = { foName, filename };
/* 3450 */     return result;
/*      */   }
/*      */   
/*      */   private String dsfFilePath(String rootFolder) {
/* 3460 */     String[] folderAndFileNames = getFolderAndFileNames();
/* 3461 */     String folderName = folderAndFileNames[1];
/* 3462 */     String pathOne = folderAndFileNames[0];
/* 3463 */     String pathTwo = rootFolder + File.separatorChar + "Earth nav data" + File.separatorChar + pathOne;
/* 3465 */     return pathTwo + File.separatorChar + folderName + ".dsf.txt";
/*      */   }
/*      */   
/*      */   public String getDSFFilename() {
/* 3470 */     return dsfFilePath(this.generatorStore.getOutputFolder());
/*      */   }
/*      */   
/*      */   public void writeFile(String outputFolder) throws IOException {
/* 3475 */     if (this.progress != null)
/* 3476 */       this.progress.progressChanged(this, "Writing File", 0.91F); 
/* 3479 */     boolean pusty = false;
/* 3480 */     if (this.osmShapes.size() == 0 && this.osmObjects.size() == 0) {
/* 3481 */       pusty = true;
/* 3482 */       for (NetworkItem item : getNetworkItems()) {
/* 3484 */         if (item.getCount() > 0)
/* 3485 */           pusty = false; 
/*      */       } 
/*      */     } 
/* 3490 */     if (pusty) {
/* 3491 */       log.info("Tile is empty, not generating");
/* 3492 */       if (this.progress != null)
/* 3493 */         this.progress.progressChanged(this, "Completed", 1.0F); 
/*      */       return;
/*      */     } 
/* 3498 */     File file = new File(dsfFilePath(outputFolder));
/* 3500 */     File parent = file.getParentFile();
/* 3501 */     if (parent != null && !parent.exists() && 
/* 3502 */       !parent.mkdirs())
/* 3503 */       throw new IOException("File '" + file + "' could not be created"); 
/* 3507 */     if (!file.exists())
/* 3508 */       file.createNewFile(); 
/* 3511 */     FileOutputStream fileOutputStream = new FileOutputStream(file);
/* 3514 */     StringBuilder sb = new StringBuilder();
/* 3515 */     int latitude = (int)this.tilePosition.y();
/* 3516 */     int longitude = (int)this.tilePosition.x();
/* 3517 */     String tileCoordinate = longitude + ".000000/" + latitude + ".000000/" + (longitude + 1) + ".000000/" + (latitude + 1) + ".000000\n";
/* 3520 */     sb.append("I\n");
/* 3521 */     sb.append("800\n");
/* 3522 */     sb.append("DSF2TEXT\n\n");
/* 3523 */     sb.append("DIVISIONS 32\n\n");
/* 3524 */     sb.append("PROPERTY sim/planet earth\n");
/* 3525 */     sb.append("PROPERTY sim/overlay 1\n");
/* 3526 */     sb.append("PROPERTY sim/creation_agent World2XPlane 0.7.4\n");
/* 3528 */     writeRequires(sb);
/* 3530 */     fileOutputStream.write(sb.toString().getBytes());
/* 3531 */     fileOutputStream.flush();
/* 3533 */     sb = new StringBuilder();
/* 3536 */     sb.append("\nPROPERTY sim/west " + longitude + "\n");
/* 3537 */     sb.append("PROPERTY sim/east " + (longitude + 1) + "\n");
/* 3538 */     sb.append("PROPERTY sim/north " + (latitude + 1) + "\n");
/* 3539 */     sb.append("PROPERTY sim/south " + latitude + "\n\n");
/* 3541 */     fileOutputStream.write(sb.toString().getBytes());
/* 3542 */     fileOutputStream.flush();
/* 3544 */     this.buildingExclusionCount = this.buildingExclusions.size();
/* 3545 */     this.forestExclusionCount = this.forestExclusions.size();
/* 3548 */     getDsfExclusions(tileCoordinate, fileOutputStream);
/* 3551 */     sb = new StringBuilder();
/* 3552 */     if (this.generatorStore.isGenerateRoads()) {
/* 3553 */       sb.append("NETWORK_DEF lib/g10/roads.net\n");
/* 3554 */       sb.append("\n");
/*      */     } 
/* 3557 */     Map<String, Integer> vectorRuleIndex = new HashMap<>();
/* 3558 */     int count = 1;
/* 3559 */     if (this.generatorStore.getVectorFiles().size() > 0)
/* 3560 */       for (String item : this.generatorStore.getVectorFiles()) {
/* 3561 */         sb.append("NETWORK_DEF " + item + "\n");
/* 3562 */         vectorRuleIndex.put(item, Integer.valueOf(count));
/* 3563 */         count++;
/*      */       }  
/* 3568 */     writeObjectDefinitions(sb);
/* 3571 */     if (this.progress != null)
/* 3572 */       this.progress.progressChanged(this, "Writing File", 0.93F); 
/* 3575 */     sb.append("\n");
/* 3577 */     fileOutputStream.write(sb.toString().getBytes());
/* 3578 */     fileOutputStream.flush();
/* 3580 */     final GeometryFactory geometryFactory = new GeometryFactory();
/* 3583 */     if (this.progress != null)
/* 3584 */       this.progress.progressChanged(this, "Writing File", 0.95F); 
/* 3588 */     for (NetworkItem item : getNetworkItems()) {
/* 3590 */       if (item instanceof RoadNetwork && !this.generatorStore.isGenerateRoads())
/*      */         continue; 
/* 3594 */       if (item instanceof VectorRule && ((VectorRule)item).getVectorFile() != null && ((VectorRule)item).getVectorFile().length() > 0)
/* 3595 */         item.setNetworkNumber(vectorRuleIndex.get(((VectorRule)item).getVectorFile())); 
/* 3597 */       if (item instanceof RoadNetwork) {
/* 3598 */         this.roadCount = item.getCount();
/* 3599 */       } else if (item instanceof VectorRule) {
/* 3600 */         this.vectorCount += item.getCount();
/*      */       } 
/* 3602 */       this.junctionNumber = item.writeToDSF(fileOutputStream, this.junctionNumber, new NetworkDelegate() {
/*      */             public String getAreaType(Point2D point) {
/* 3605 */               Point centre = geometryFactory.createPoint(new Coordinate(point.x(), point.y()));
/* 3606 */               List<DSFTile.NamedArea> items = DSFTile.this.areaTree.query(new Envelope(point.x(), point.x(), point.y(), point.y()));
/* 3607 */               if (items != null && items.size() > 0)
/* 3608 */                 for (DSFTile.NamedArea item : items) {
/* 3609 */                   if (item.preparedGeometryOuter != null && item.preparedGeometryOuter.contains((Geometry)centre))
/* 3610 */                     return item.identifier; 
/*      */                 }  
/* 3614 */               return null;
/*      */             }
/*      */           });
/* 3617 */       fileOutputStream.flush();
/* 3618 */       item.finished();
/*      */     } 
/* 3621 */     writePolygons(fileOutputStream);
/* 3622 */     writeObjects(fileOutputStream);
/* 3625 */     if (this.progress != null)
/* 3626 */       this.progress.progressChanged(this, "Writing File", 0.97F); 
/* 3629 */     fileOutputStream.write("\n\n\n".getBytes());
/* 3630 */     fileOutputStream.flush();
/* 3631 */     fileOutputStream.close();
/* 3634 */     if (this.progress != null)
/* 3635 */       this.progress.progressChanged(this, "Writing File", 0.98F); 
/* 3639 */     String inputFile = file.getAbsolutePath();
/* 3640 */     String outputFile = file.getAbsolutePath().replaceFirst(".txt", "");
/* 3642 */     Runtime runtime = Runtime.getRuntime();
/* 3643 */     Process p = runtime.exec(new String[] { DSFTool.getDSFToolPath(), "--text2dsf", inputFile, outputFile });
/*      */     try {
/* 3645 */       p.waitFor();
/* 3646 */     } catch (InterruptedException e) {
/* 3647 */       e.printStackTrace();
/*      */     } 
/* 3651 */     if (!(new File(outputFile)).exists()) {
/* 3652 */       log.error("Tile " + this + " was not created, DSFTool failed with an error.");
/* 3653 */       throw new RuntimeException("Tile " + this + " was not created, DSFTool failed with an error. Generation has stopped");
/*      */     } 
/* 3656 */     if (!this.generatorStore.isDebugging())
/* 3657 */       FileUtils.deleteQuietly(new File(inputFile)); 
/* 3660 */     if (this.generatorStore.isToOSM()) {
/* 3661 */       File osmXml = new File(inputFile.replace(".dsf.txt", ".osm.xml"));
/* 3662 */       FileUtils.writeStringToFile(osmXml, dumpToOsmXML());
/*      */     } 
/* 3666 */     this.osmObjects.clear();
/* 3667 */     this.dsfPolygons.clear();
/* 3668 */     this.osmShapes.clear();
/* 3671 */     if (this.progress != null)
/* 3672 */       this.progress.progressChanged(this, "Completed", 1.0F); 
/*      */   }
/*      */   
/*      */   private void writeRequires(StringBuilder sb) throws IOException {
/*      */     int x;
/* 3683 */     for (x = 1; x < 7; x++) {
/* 3684 */       Integer startIndex = getObjectDefinitionIndex(x);
/* 3685 */       if (startIndex != null)
/* 3686 */         sb.append("PROPERTY sim/require_object " + x + "/" + startIndex + "\n"); 
/*      */     } 
/* 3690 */     for (x = 1; x < 7; x++) {
/* 3691 */       Integer startIndex = getPolygonDefinitionIndex(x);
/* 3692 */       if (startIndex != null)
/* 3693 */         sb.append("PROPERTY sim/require_facade " + x + "/" + startIndex + "\n"); 
/*      */     } 
/*      */   }
/*      */   
/*      */   private Integer getObjectDefinitionIndex(int level) {
/* 3707 */     for (int x = 0; x < this.objectDefinitionNumbers.size(); x++) {
/* 3708 */       DSFObjectDefinition path = this.objectDefinitionNumbers.get(x);
/* 3709 */       if (path.getRequiredLevel() == level)
/* 3710 */         return Integer.valueOf(x); 
/*      */     } 
/* 3713 */     return null;
/*      */   }
/*      */   
/*      */   private Integer getPolygonDefinitionIndex(int level) {
/* 3723 */     for (int x = 0; x < this.polygonDefinitionNumbers.size(); x++) {
/* 3724 */       DSFObjectDefinition path = this.polygonDefinitionNumbers.get(x);
/* 3725 */       if (path.getRequiredLevel() == level)
/* 3726 */         return Integer.valueOf(x); 
/*      */     } 
/* 3729 */     return null;
/*      */   }
/*      */   
/*      */   private void writeObjectDefinitions(StringBuilder sb) {
/*      */     int x;
/* 3735 */     for (x = 0; x < this.polygonDefinitionNumbers.size(); x++) {
/* 3736 */       DSFObjectDefinition path = this.polygonDefinitionNumbers.get(x);
/* 3737 */       sb.append("POLYGON_DEF " + path.getPath() + "\n");
/*      */     } 
/* 3740 */     sb.append("\n");
/* 3742 */     for (x = 0; x < this.objectDefinitionNumbers.size(); x++) {
/* 3743 */       DSFObjectDefinition path = this.objectDefinitionNumbers.get(x);
/* 3744 */       sb.append("OBJECT_DEF " + path.getPath() + "\n");
/*      */     } 
/* 3747 */     sb.append("\n");
/*      */   }
/*      */   
/*      */   public void writePolygons(FileOutputStream sb) throws IOException {
/* 3755 */     boolean genStats = this.generatorStore.isDebugging();
/* 3757 */     if (genStats) {
/* 3759 */       Map<String, Integer> counts = new HashMap<>();
/* 3760 */       for (OSMShape osmShape : this.osmShapes) {
/* 3761 */         if (osmShape.rule != null && (osmShape.rule instanceof FacadeRule || osmShape.rule instanceof PolygonRule)) {
/* 3762 */           Point2D size = new Point2D((Math.round(osmShape.outer.getLongestSide().floatValue() * 2.0F) / 2.0F), (Math.round(osmShape.outer.getShortestSide().floatValue() * 2.0F) / 2.0F));
/* 3763 */           double min = Math.min(size.x(), size.y());
/* 3764 */           double max = Math.max(size.x(), size.y());
/* 3765 */           String key = this.numberFormat.format(min) + "," + this.numberFormat.format(max);
/* 3766 */           if (counts.get(key) != null) {
/* 3767 */             counts.put(key, Integer.valueOf(((Integer)counts.get(key)).intValue() + 1));
/*      */             continue;
/*      */           } 
/* 3769 */           counts.put(key, Integer.valueOf(1));
/*      */         } 
/*      */       } 
/* 3774 */       List<Stat> stats = new ArrayList<>();
/* 3775 */       for (Map.Entry<String, Integer> item : counts.entrySet())
/* 3777 */         stats.add(new Stat(item.getKey(), item.getValue())); 
/* 3779 */       Collections.sort(stats, new Comparator<Stat>() {
/*      */             public int compare(DSFTile.Stat o1, DSFTile.Stat o2) {
/* 3782 */               return o2.count.compareTo(o1.count);
/*      */             }
/*      */           });
/* 3786 */       File file = new File(this.generatorStore.getOutputFolder(), "facade_stats_" + this.area.getMinY() + "_" + this.area.getMinX() + ".txt");
/* 3787 */       StringBuffer countsFile = new StringBuffer();
/* 3789 */       for (Stat item : stats)
/* 3790 */         countsFile.append(item.count + "\t\t" + item.name + "\n"); 
/* 3793 */       FileUtils.writeStringToFile(file, countsFile.toString());
/*      */     } 
/* 3797 */     for (OSMShape osmShape : this.osmShapes) {
/* 3798 */       writePolygonToDSF(osmShape, sb);
/* 3799 */       sb.flush();
/*      */     } 
/* 3803 */     for (AutogenObject autogenObject : this.autogen.getAutogenObjects()) {
/* 3804 */       writeAutoGenStringToDSF(autogenObject, sb);
/* 3805 */       sb.flush();
/*      */     } 
/*      */   }
/*      */   
/*      */   public class Stat {
/* 3810 */     Integer count = Integer.valueOf(0);
/*      */     
/*      */     String name;
/*      */     
/*      */     public Stat(String key, Integer value) {
/* 3814 */       this.name = key;
/* 3815 */       this.count = value;
/*      */     }
/*      */   }
/*      */   
/*      */   public void writeObjects(FileOutputStream fileOutputStream) throws IOException {
/* 3822 */     Map<String, Integer> counts = new HashMap<>();
/* 3824 */     boolean genStats = this.generatorStore.isDebugging();
/* 3825 */     int count = 0;
/* 3826 */     for (DSFObject osmObject : this.osmObjects) {
/* 3828 */       this.objectCount++;
/* 3829 */       if (osmObject.angle == null && osmObject.rule instanceof ObjectRule) {
/* 3830 */         if (osmObject.rule instanceof ObjectRule && (
/* 3831 */           (ObjectRule)osmObject.rule).getAngle().floatValue() != -1.0F)
/* 3832 */           osmObject.angle = ((ObjectRule)osmObject.rule).getAngle(); 
/* 3835 */         if (osmObject.angle == null)
/* 3836 */           osmObject.angle = Float.valueOf(((ObjectRule)osmObject.rule).getRandomAngle().floatValue()); 
/*      */       } 
/* 3838 */       if (osmObject.position == null)
/*      */         continue; 
/* 3841 */       Integer index = null;
/* 3842 */       if (osmObject.generatedObject != null) {
/* 3843 */         index = Integer.valueOf(getGeneratedObjectDefinition(osmObject.generatedObject));
/*      */       } else {
/* 3845 */         index = Integer.valueOf(getSharedObjectDefinition(osmObject.getRequiredLevel(), osmObject.objectDefinitionNumber.intValue()));
/* 3847 */         if (genStats) {
/* 3849 */           String path = this.generatorStore.getObjectDefinition(osmObject.objectDefinitionNumber.intValue()).getPath();
/* 3850 */           if (counts.get(path) != null) {
/* 3851 */             counts.put(path, Integer.valueOf(((Integer)counts.get(path)).intValue() + 1));
/*      */           } else {
/* 3853 */             counts.put(path, Integer.valueOf(1));
/*      */           } 
/*      */         } 
/*      */       } 
/* 3859 */       if (osmObject.position != null && this.area.contains(osmObject.position)) {
/* 3860 */         String line = "OBJECT " + index + " " + this.numberFormat.format(osmObject.position.x()) + " " + this.numberFormat.format(osmObject.position.y()) + " " + osmObject.angle + "\n";
/* 3862 */         fileOutputStream.write(line.getBytes());
/* 3863 */         fileOutputStream.flush();
/*      */       } 
/* 3865 */       count++;
/*      */     } 
/* 3868 */     if (genStats) {
/* 3870 */       List<Stat> stats = new ArrayList<>();
/* 3871 */       for (Map.Entry<String, Integer> item : counts.entrySet())
/* 3872 */         stats.add(new Stat(item.getKey(), item.getValue())); 
/* 3874 */       Collections.sort(stats, new Comparator<Stat>() {
/*      */             public int compare(DSFTile.Stat o1, DSFTile.Stat o2) {
/* 3877 */               return o2.count.compareTo(o1.count);
/*      */             }
/*      */           });
/* 3881 */       File file = new File(this.generatorStore.getOutputFolder(), "stats_" + this.area.getMinY() + "_" + this.area.getMinX() + ".txt");
/* 3882 */       StringBuffer countsFile = new StringBuffer();
/* 3884 */       for (Stat item : stats)
/* 3885 */         countsFile.append(item.count + "\t\t" + item.name + "\n"); 
/* 3888 */       FileUtils.writeStringToFile(file, countsFile.toString());
/*      */     } 
/*      */   }
/*      */   
/*      */   private int getSharedObjectDefinition(byte level, int objectDefinitionNumber) {
/* 3901 */     ObjectDefinitionStore.ObjectDefinition objectDefinition = this.generatorStore.getObjectDefinition(objectDefinitionNumber);
/* 3902 */     if (objectDefinition == null)
/* 3903 */       throw new RuntimeException("Can't find object definition " + objectDefinitionNumber); 
/* 3905 */     DSFObjectDefinition objDef = new DSFObjectDefinition(objectDefinition.getPath(), level);
/* 3906 */     int index = this.objectDefinitionNumbers.indexOf(objDef);
/* 3907 */     if (index < 0)
/* 3908 */       throw new RuntimeException("Could not find object definition " + objectDefinitionNumber); 
/* 3910 */     return index;
/*      */   }
/*      */   
/*      */   private int getGeneratedObjectDefinition(DSFObjectDefinition generatedObject) {
/* 3914 */     if (!this.objectDefinitionNumbers.contains(generatedObject))
/* 3915 */       throw new RuntimeException("Request for " + generatedObject + " does not exist"); 
/* 3917 */     return this.objectDefinitionNumbers.indexOf(generatedObject);
/*      */   }
/*      */   
/*      */   private int getSharedPolygonDefinition(byte level, int polygonDefinitionNumber) {
/* 3927 */     ObjectDefinitionStore.ObjectDefinition objectDefinition = this.generatorStore.getPolygonDefinition(polygonDefinitionNumber);
/* 3928 */     if (objectDefinition == null)
/* 3929 */       throw new RuntimeException("Can't find polygon definition " + polygonDefinitionNumber); 
/* 3931 */     DSFObjectDefinition objDef = new DSFObjectDefinition(objectDefinition.getPath(), level);
/* 3932 */     int index = this.polygonDefinitionNumbers.indexOf(objDef);
/* 3933 */     if (index < 0)
/* 3934 */       throw new RuntimeException("Could not find polygon definition " + polygonDefinitionNumber); 
/* 3936 */     return index;
/*      */   }
/*      */   
/*      */   private void writePolygonToDSF(OSMShape osmShape, FileOutputStream fs) throws IOException {
/* 3942 */     if (osmShape.rule == null) {
/* 3943 */       log.error("Shape had no rule. This shouldn't happen");
/*      */       return;
/*      */     } 
/* 3948 */     if (osmShape.outer == null)
/*      */       return; 
/* 3953 */     if (osmShape.rule instanceof ForestRule) {
/* 3954 */       writeForestToDSF(osmShape, fs);
/* 3955 */     } else if (osmShape.rule instanceof FacadeRule) {
/* 3956 */       if (this.generatorStore.isGenerateFacades())
/* 3957 */         writeBuildingToDSF(osmShape, fs); 
/* 3959 */     } else if (osmShape.rule instanceof PolygonRule) {
/* 3960 */       writePolToDsf(osmShape, fs);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeForestToDSF(OSMShape osmShape, FileOutputStream fileOutputStream) throws IOException {
/* 3967 */     StringBuilder sb = new StringBuilder();
/* 3970 */     this.forestCount++;
/* 3971 */     OSMPolygon outer = osmShape.outer;
/* 3972 */     ForestRule forestRule = (ForestRule)osmShape.rule;
/* 3973 */     int density = (int)forestRule.getDensity();
/* 3974 */     if (osmShape.outer.getHeight() != null)
/* 3975 */       density = osmShape.outer.getHeight().intValue(); 
/* 3978 */     boolean accept = false;
/* 3980 */     if (forestRule.isPerimeterOnly()) {
/* 3982 */       density = 256 + density;
/* 3983 */       accept = true;
/*      */     } else {
/* 3985 */       accept = (osmShape.outer.vertexNumber() > 2 && osmShape.outer.firstPoint().equals(osmShape.outer.vertex(osmShape.outer.vertexNumber() - 1)));
/*      */     } 
/* 3989 */     if (accept && outer != null) {
/* 3990 */       sb.append(String.format("BEGIN_POLYGON %d %d 2", new Object[] { Integer.valueOf(getSharedPolygonDefinition(osmShape.getRequiredLevel(), osmShape.objectDefinitionNumber.intValue())), Integer.valueOf(density) }));
/* 3991 */       sb.append(System.getProperty("line.separator"));
/* 3992 */       sb.append("BEGIN_WINDING");
/* 3993 */       osmShape.outer.setCounterClockwise();
/* 3994 */       sb.append(System.getProperty("line.separator"));
/* 3995 */       for (int x = 0; x < outer.vertexNumber(); x++) {
/* 3996 */         Point2D loc = outer.vertex(x);
/* 3997 */         if (loc != null && this.area.contains(loc)) {
/* 3998 */           sb.append("POLYGON_POINT " + this.numberFormat.format(loc.x()) + " " + this.numberFormat.format(loc.y()));
/* 3999 */           sb.append(System.getProperty("line.separator"));
/*      */         } 
/*      */       } 
/* 4004 */       sb.append("END_WINDING");
/* 4005 */       sb.append(System.getProperty("line.separator"));
/* 4007 */       if (osmShape.inner != null && osmShape.inner.size() > 0)
/* 4009 */         for (OSMPolygon inner : osmShape.inner) {
/* 4010 */           sb.append("BEGIN_WINDING");
/* 4011 */           inner.setClockwise();
/* 4012 */           sb.append(System.getProperty("line.separator"));
/* 4013 */           for (int i = 0; i < inner.vertexNumber(); i++) {
/* 4014 */             Point2D loc = inner.vertex(i);
/* 4015 */             if (this.area.contains(loc)) {
/* 4016 */               sb.append("POLYGON_POINT " + this.numberFormat.format(loc.x()) + " " + this.numberFormat.format(loc.y()));
/* 4017 */               sb.append(System.getProperty("line.separator"));
/*      */             } 
/*      */           } 
/* 4020 */           sb.append("END_WINDING");
/* 4021 */           sb.append(System.getProperty("line.separator"));
/*      */         }  
/* 4025 */       sb.append("END_POLYGON");
/* 4026 */       sb.append(System.getProperty("line.separator"));
/* 4027 */       sb.append(System.getProperty("line.separator"));
/*      */     } 
/* 4030 */     fileOutputStream.write(sb.toString().getBytes());
/*      */   }
/*      */   
/*      */   private void writeAutoGenStringToDSF(AutogenObject autogenObject, FileOutputStream fileOutputStream) throws IOException {
/* 4037 */     StringBuilder sb = new StringBuilder();
/* 4040 */     if (autogenObject != null && autogenObject.getOuterContours().size() > 0) {
/* 4044 */       sb.append(String.format("BEGIN_POLYGON %d %d 2", new Object[] { Integer.valueOf(getSharedPolygonDefinition((byte)2, autogenObject.getObjectDefinitionNumber())), Integer.valueOf(256 + autogenObject.getOuterContours().size()) }));
/* 4046 */       sb.append(System.getProperty("line.separator"));
/* 4050 */       for (AutogenObject.Contour outer : autogenObject.getOuterContours()) {
/* 4051 */         sb.append("BEGIN_WINDING");
/* 4052 */         sb.append(System.getProperty("line.separator"));
/* 4054 */         for (Point2D loc : outer.getPoints()) {
/* 4055 */           if (!this.area.contains(loc)) {
/* 4056 */             log.error("Autogen drops out of tile boundary");
/*      */             return;
/*      */           } 
/* 4059 */           sb.append("POLYGON_POINT " + this.numberFormat.format(loc.x()) + " " + this.numberFormat.format(loc.y()));
/* 4060 */           sb.append(System.getProperty("line.separator"));
/*      */         } 
/* 4064 */         sb.append("END_WINDING");
/* 4065 */         sb.append(System.getProperty("line.separator"));
/*      */       } 
/* 4070 */       for (AutogenObject.Contour inner : autogenObject.getInnerContours()) {
/* 4071 */         sb.append("BEGIN_WINDING");
/* 4072 */         sb.append(System.getProperty("line.separator"));
/* 4074 */         for (Point2D loc : inner.getPoints()) {
/* 4075 */           if (!this.area.contains(loc)) {
/* 4076 */             log.error("Autogen drops out of tile boundary");
/*      */             return;
/*      */           } 
/* 4079 */           sb.append("POLYGON_POINT " + this.numberFormat.format(loc.x()) + " " + this.numberFormat.format(loc.y()));
/* 4080 */           sb.append(System.getProperty("line.separator"));
/*      */         } 
/* 4084 */         sb.append("END_WINDING");
/* 4085 */         sb.append(System.getProperty("line.separator"));
/*      */       } 
/* 4088 */       sb.append("END_POLYGON");
/* 4089 */       sb.append(System.getProperty("line.separator"));
/* 4090 */       sb.append(System.getProperty("line.separator"));
/*      */     } 
/* 4093 */     fileOutputStream.write(sb.toString().getBytes());
/*      */   }
/*      */   
/*      */   private void writeBuildingToDSF(OSMShape osmShape, FileOutputStream fileOutputStream) throws IOException {
/* 4100 */     StringBuilder sb = new StringBuilder();
/* 4103 */     boolean allow = (osmShape.outer.vertexNumber() > 2 && osmShape.outer.firstPoint().equals(osmShape.outer.vertex(osmShape.outer.vertexNumber() - 1)));
/* 4104 */     if (osmShape.rule != null && osmShape.rule instanceof FacadeRule && ((FacadeRule)osmShape.rule).isAllowUnclosed())
/* 4105 */       allow = true; 
/* 4107 */     if (osmShape.outer.vertexNumber() < 2)
/* 4108 */       allow = false; 
/* 4111 */     if (allow) {
/* 4112 */       boolean isMultiPolygon = (osmShape.inner != null && osmShape.inner.size() > 0);
/* 4113 */       this.buildingCount++;
/* 4114 */       FacadeRule facadeRule = (FacadeRule)osmShape.rule;
/* 4116 */       Integer definitionNumber = osmShape.objectDefinitionNumber;
/* 4117 */       ObjectDefinitionStore.ObjectDefinition definition = this.generatorStore.getPolygonDefinition(definitionNumber.intValue());
/* 4126 */       int numberOfPoints = osmShape.outer.vertexNumber();
/* 4128 */       if (osmShape.outer.isClosed() && (facadeRule.isSkipLastPoint() == null || facadeRule.isSkipLastPoint().booleanValue()))
/* 4129 */         numberOfPoints--; 
/* 4133 */       float height = (osmShape.outer.getHeight() != null) ? osmShape.outer.getHeight().floatValue() : facadeRule.getHeight();
/* 4135 */       if (height < 0.5D) {
/* 4136 */         log.error("Not adding facade www.openstreetmap.org/way/" + osmShape.outer.getIdentifier() + " with negative height/or height below 0.50 metres");
/*      */         return;
/*      */       } 
/* 4142 */       if (facadeRule.getRoofHeight() > 0.0F)
/* 4143 */         height += facadeRule.getRoofHeight(); 
/* 4146 */       if (height > 700.0F) {
/* 4147 */         log.error("Not adding facade www.openstreetmap.org/way/" + osmShape.outer.getIdentifier() + " with height over 700 metres");
/*      */         return;
/*      */       } 
/* 4151 */       osmShape.outer.setCounterClockwise();
/* 4152 */       sb.append(String.format("BEGIN_POLYGON %d %s 2", new Object[] { Integer.valueOf(getSharedPolygonDefinition(osmShape.getRequiredLevel(), definitionNumber.intValue())), this.heightFormat.format(height) }));
/* 4153 */       sb.append(System.getProperty("line.separator"));
/* 4154 */       sb.append("BEGIN_WINDING");
/* 4155 */       sb.append(System.getProperty("line.separator"));
/* 4156 */       for (int x = 0; x < numberOfPoints; x++) {
/* 4157 */         Point2D loc = osmShape.outer.vertex(x);
/* 4158 */         if (this.area.contains(loc)) {
/* 4160 */           sb.append("POLYGON_POINT " + this.numberFormat.format(loc.x()) + " " + this.numberFormat.format(loc.y()));
/* 4161 */           sb.append(System.getProperty("line.separator"));
/*      */         } 
/*      */       } 
/* 4164 */       sb.append("END_WINDING");
/* 4165 */       sb.append(System.getProperty("line.separator"));
/* 4169 */       if (osmShape.inner != null)
/* 4170 */         for (OSMPolygon inner : osmShape.inner) {
/* 4172 */           inner.setClockwise();
/* 4174 */           sb.append("BEGIN_WINDING");
/* 4175 */           sb.append(System.getProperty("line.separator"));
/* 4176 */           for (int i = 0; i < inner.vertexNumber(); i++) {
/* 4177 */             Point2D loc = inner.vertex(i);
/* 4178 */             if (this.area.contains(loc)) {
/* 4179 */               sb.append("POLYGON_POINT " + this.numberFormat.format(loc.x()) + " " + this.numberFormat.format(loc.y()));
/* 4180 */               sb.append(System.getProperty("line.separator"));
/*      */             } 
/*      */           } 
/* 4183 */           sb.append("END_WINDING");
/* 4184 */           sb.append(System.getProperty("line.separator"));
/*      */         }  
/* 4188 */       sb.append("END_POLYGON");
/* 4189 */       sb.append(System.getProperty("line.separator"));
/* 4190 */       sb.append(System.getProperty("line.separator"));
/*      */     } 
/* 4195 */     fileOutputStream.write(sb.toString().getBytes());
/*      */   }
/*      */   
/*      */   private void writePolToDsf(OSMShape osmShape, FileOutputStream fileOutputStream) throws IOException {
/* 4201 */     getAngleForPolyRule(osmShape);
/* 4203 */     StringBuilder sb = new StringBuilder();
/* 4206 */     boolean allow = (osmShape.outer.vertexNumber() > 2 && osmShape.outer.firstPoint().equals(osmShape.outer.vertex(osmShape.outer.vertexNumber() - 1)));
/* 4207 */     if (osmShape.outer.vertexNumber() < 2)
/* 4208 */       allow = false; 
/* 4211 */     if (allow) {
/* 4212 */       boolean isMultiPolygon = (osmShape.inner != null && osmShape.inner.size() > 0);
/* 4213 */       PolygonRule polygonRule = (PolygonRule)osmShape.rule;
/* 4215 */       Integer definitionNumber = osmShape.objectDefinitionNumber;
/* 4216 */       ObjectDefinitionStore.ObjectDefinition definition = this.generatorStore.getPolygonDefinition(definitionNumber.intValue());
/* 4218 */       int numberOfPoints = osmShape.outer.vertexNumber();
/* 4220 */       if (osmShape.outer.isClosed())
/* 4221 */         numberOfPoints--; 
/* 4225 */       Float textureAngle = osmShape.outer.getHeight();
/* 4226 */       if (textureAngle == null)
/* 4227 */         textureAngle = Float.valueOf(0.0F); 
/* 4231 */       osmShape.outer.setCounterClockwise();
/* 4232 */       sb.append(String.format("BEGIN_POLYGON %d %s 2", new Object[] { Integer.valueOf(getSharedPolygonDefinition(osmShape.getRequiredLevel(), definitionNumber.intValue())), this.heightFormat.format(textureAngle) }));
/* 4234 */       sb.append(System.getProperty("line.separator"));
/* 4235 */       sb.append("BEGIN_WINDING");
/* 4236 */       sb.append(System.getProperty("line.separator"));
/* 4237 */       for (int x = 0; x < numberOfPoints; x++) {
/* 4238 */         Point2D loc = osmShape.outer.vertex(x);
/* 4239 */         if (this.area.contains(loc)) {
/* 4241 */           sb.append("POLYGON_POINT " + this.numberFormat.format(loc.x()) + " " + this.numberFormat.format(loc.y()));
/* 4242 */           sb.append(System.getProperty("line.separator"));
/*      */         } 
/*      */       } 
/* 4245 */       sb.append("END_WINDING");
/* 4246 */       sb.append(System.getProperty("line.separator"));
/* 4250 */       if (osmShape.inner != null)
/* 4251 */         for (OSMPolygon inner : osmShape.inner) {
/* 4253 */           inner.setClockwise();
/* 4255 */           sb.append("BEGIN_WINDING");
/* 4256 */           sb.append(System.getProperty("line.separator"));
/* 4257 */           for (int i = 0; i < inner.vertexNumber(); i++) {
/* 4258 */             Point2D loc = inner.vertex(i);
/* 4259 */             if (this.area.contains(loc)) {
/* 4260 */               sb.append("POLYGON_POINT " + this.numberFormat.format(loc.x()) + " " + this.numberFormat.format(loc.y()));
/* 4261 */               sb.append(System.getProperty("line.separator"));
/*      */             } 
/*      */           } 
/* 4264 */           sb.append("END_WINDING");
/* 4265 */           sb.append(System.getProperty("line.separator"));
/*      */         }  
/* 4269 */       sb.append("END_POLYGON");
/* 4270 */       sb.append(System.getProperty("line.separator"));
/* 4271 */       sb.append(System.getProperty("line.separator"));
/*      */     } 
/* 4276 */     fileOutputStream.write(sb.toString().getBytes());
/*      */   }
/*      */   
/*      */   public void getDsfExclusions(String coordinates, FileOutputStream fileOutputStream) throws IOException {
/* 4285 */     if (this.generatorStore.isSmartExclusions()) {
/* 4287 */       if (this.generatorStore.isCreateNetworkExclusions())
/* 4288 */         if (this.generatorStore.getRegionPoly() != null) {
/* 4289 */           writePolyExclusion("sim/exclude_net", this.generatorStore.getRegionPoly(), fileOutputStream);
/*      */         } else {
/* 4291 */           fileOutputStream.write((new String("PROPERTY sim/exclude_net " + coordinates)).getBytes());
/*      */         }  
/* 4295 */       if (this.generatorStore.isCreateBeachExclusions())
/* 4296 */         fileOutputStream.write((new String("PROPERTY sim/exclude_bch " + coordinates)).getBytes()); 
/* 4299 */       NumberFormat numberFormat = new DecimalFormat("#.###################", new DecimalFormatSymbols(Locale.ENGLISH));
/* 4301 */       for (Envelope exclusion : this.forestExclusions) {
/* 4302 */         if (exclusion.isNull())
/*      */           continue; 
/* 4305 */         if (this.generatorStore.isCreateForestExclusions()) {
/* 4306 */           String line = "PROPERTY sim/exclude_for " + numberFormat.format(exclusion.getMinX()) + "/" + numberFormat.format(exclusion.getMinY()) + "/" + numberFormat.format((exclusion.getMaxX() == 180.0D) ? 180.0D : exclusion.getMaxX()) + "/" + numberFormat.format(exclusion.getMaxY()) + "\n";
/* 4311 */           fileOutputStream.write(line.getBytes());
/*      */         } 
/*      */       } 
/* 4317 */       for (Envelope exclusion : this.buildingExclusions) {
/* 4318 */         if (exclusion.isNull())
/*      */           continue; 
/* 4324 */         if (this.generatorStore.isCreateObjectExclusions()) {
/* 4325 */           String line = "PROPERTY sim/exclude_obj " + numberFormat.format(exclusion.getMinX()) + "/" + numberFormat.format(exclusion.getMinY()) + "/" + numberFormat.format(exclusion.getMaxX()) + "/" + numberFormat.format(exclusion.getMaxY()) + "\n";
/* 4330 */           fileOutputStream.write(line.getBytes());
/*      */         } 
/*      */       } 
/*      */     } else {
/* 4339 */       StringBuilder sb = new StringBuilder();
/* 4341 */       if (this.generatorStore.isCreateNetworkExclusions())
/* 4342 */         if (this.generatorStore.getRegionPoly() != null) {
/* 4343 */           writePolyExclusion("sim/exclude_net", this.generatorStore.getRegionPoly(), fileOutputStream);
/*      */         } else {
/* 4345 */           fileOutputStream.write((new String("PROPERTY sim/exclude_net " + coordinates)).getBytes());
/*      */         }  
/* 4349 */       if (this.generatorStore.isCreateBeachExclusions())
/* 4350 */         if (this.generatorStore.getRegionPoly() != null) {
/* 4351 */           writePolyExclusion("sim/exclude_bch", this.generatorStore.getRegionPoly(), fileOutputStream);
/*      */         } else {
/* 4353 */           fileOutputStream.write((new String("PROPERTY sim/exclude_bch " + coordinates)).getBytes());
/*      */         }  
/* 4356 */       if (this.generatorStore.isCreateObjectExclusions())
/* 4357 */         if (this.generatorStore.getRegionPoly() != null) {
/* 4358 */           writePolyExclusion("sim/exclude_obj", this.generatorStore.getRegionPoly(), fileOutputStream);
/*      */         } else {
/* 4360 */           fileOutputStream.write((new String("PROPERTY sim/exclude_obj " + coordinates)).getBytes());
/*      */         }  
/* 4363 */       if (this.generatorStore.isCreateFacadeExclusions())
/* 4364 */         if (this.generatorStore.getRegionPoly() != null) {
/* 4365 */           writePolyExclusion("sim/exclude_fac", this.generatorStore.getRegionPoly(), fileOutputStream);
/*      */         } else {
/* 4367 */           fileOutputStream.write((new String("PROPERTY sim/exclude_fac " + coordinates)).getBytes());
/*      */         }  
/* 4371 */       if (this.generatorStore.isCreateForestExclusions())
/* 4372 */         if (this.generatorStore.getRegionPoly() != null) {
/* 4373 */           writePolyExclusion("sim/exclude_for", this.generatorStore.getRegionPoly(), fileOutputStream);
/*      */         } else {
/* 4375 */           fileOutputStream.write((new String("PROPERTY sim/exclude_for " + coordinates)).getBytes());
/*      */         }  
/* 4378 */       fileOutputStream.write(sb.toString().getBytes());
/*      */     } 
/*      */   }
/*      */   
/*      */   public File getImageDebugFile() {
/* 4384 */     String[] folderAndFileNames = getFolderAndFileNames();
/* 4385 */     String folderName = folderAndFileNames[1];
/* 4386 */     String pathOne = folderAndFileNames[0];
/* 4387 */     String pathTwo = this.generatorStore.getOutputFolder() + File.separatorChar + "Earth nav data" + File.separatorChar + pathOne;
/* 4391 */     File parent = new File(new File(pathTwo), "forests_" + getArea().getMinY() + "_" + getArea().getMinX() + ".png");
/* 4393 */     if (parent.exists())
/* 4394 */       return parent; 
/* 4398 */     parent = new File(new File(pathTwo), "exclusion_" + getArea().getMinY() + "_" + getArea().getMinX() + ".png");
/* 4399 */     return parent;
/*      */   }
/*      */   
/*      */   private void writePolyExclusion(String type, Geometry regionPoly, FileOutputStream fileOutputStream) throws IOException {
/* 4407 */     PolyExclusionBuilder polyExclusionBuilder = new PolyExclusionBuilder();
/* 4409 */     String[] folderAndFileNames = getFolderAndFileNames();
/* 4410 */     String folderName = folderAndFileNames[1];
/* 4411 */     String pathOne = folderAndFileNames[0];
/* 4412 */     String pathTwo = this.generatorStore.getOutputFolder() + File.separatorChar + "Earth nav data" + File.separatorChar + pathOne;
/* 4415 */     if (this.exclusions == null)
/* 4416 */       this.exclusions = polyExclusionBuilder.getExclusionZones(pathTwo, regionPoly, getArea().getMinX(), this.area.getMinY()); 
/* 4420 */     this.polyFile = polyExclusionBuilder.getImage();
/* 4421 */     for (Envelope exclusion : this.exclusions) {
/* 4422 */       if (exclusion.isNull())
/*      */         continue; 
/* 4428 */       if (this.generatorStore.isCreateObjectExclusions()) {
/* 4429 */         String line = "PROPERTY " + type + " " + this.numberFormat.format(exclusion.getMinX()) + "/" + this.numberFormat.format(exclusion.getMinY()) + "/" + this.numberFormat.format(exclusion.getMaxX()) + "/" + this.numberFormat.format(exclusion.getMaxY()) + "\n";
/* 4434 */         fileOutputStream.write(line.getBytes());
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public OSMRelation addMultiPolygon(Long relationId, OSMPolygon shape, RelationInfo relationInfo, WayInfo wayInfo, byte role, List<AcceptingRule> wayAcceptingRules) {
/* 4450 */     OSMRelation osmShape = this.multiPolygons.get(relationId);
/* 4452 */     if (osmShape == null) {
/* 4453 */       osmShape = new OSMRelation();
/* 4454 */       osmShape.identifier = relationId.longValue();
/* 4455 */       this.multiPolygons.put(relationId, osmShape);
/* 4456 */       osmShape.relationRules = relationInfo.rules;
/*      */     } 
/* 4459 */     if (role == WayInfo.INNER.byteValue()) {
/* 4460 */       if (osmShape.inner == null)
/* 4461 */         osmShape.inner = new ArrayList(); 
/* 4463 */       OSMRelation.Member member = new OSMRelation.Member();
/* 4464 */       member.rules = wayAcceptingRules;
/* 4465 */       shape.setRole(Byte.valueOf(role));
/* 4466 */       member.shape = shape;
/* 4467 */       osmShape.inner.add(member);
/*      */     } else {
/* 4468 */       if (role == WayInfo.OUTER.byteValue()) {
/* 4469 */         if (osmShape.outer == null)
/* 4470 */           osmShape.outer = new ArrayList(); 
/* 4472 */         OSMRelation.Member member1 = new OSMRelation.Member();
/* 4473 */         member1.rules = wayAcceptingRules;
/* 4474 */         shape.setRole(Byte.valueOf(role));
/* 4475 */         member1.shape = shape;
/* 4476 */         osmShape.outer.add(member1);
/* 4477 */         return osmShape;
/*      */       } 
/* 4479 */       if (osmShape.parts == null)
/* 4480 */         osmShape.parts = new ArrayList(); 
/* 4482 */       OSMRelation.Member member = new OSMRelation.Member();
/* 4483 */       member.rules = wayAcceptingRules;
/* 4484 */       shape.setRole(Byte.valueOf(role));
/* 4485 */       member.shape = shape;
/* 4486 */       osmShape.parts.add(member);
/* 4487 */       return osmShape;
/*      */     } 
/* 4489 */     return osmShape;
/*      */   }
/*      */   
/*      */   public void addSimplePolygon(OSMPolygon shape, Long wayId, List<AcceptingRule> rules) {
/* 4498 */     DSFPolygon member = new DSFPolygon();
/* 4499 */     member.setRules(rules);
/* 4500 */     member.setOuter(shape);
/* 4501 */     this.dsfPolygons.add(member);
/*      */   }
/*      */   
/*      */   private OSMShape osmInvalidShape() {
/* 4507 */     return new OSMShape(null);
/*      */   }
/*      */   
/*      */   public class OSMTest {
/*      */     private final OSMShape osmShape;
/*      */     
/*      */     public Rule rule;
/*      */     
/*      */     public OSMPolygon shape;
/*      */     
/*      */     public int exclusion;
/*      */     
/*      */     public OSMTest(Rule rule, OSMPolygon outer, OSMShape osmShape, int exclusion) {
/* 4517 */       this.rule = rule;
/* 4518 */       this.shape = outer;
/* 4519 */       this.exclusion = exclusion;
/* 4520 */       this.osmShape = osmShape;
/*      */     }
/*      */   }
/*      */   
/*      */   public String dumpToOsmXML() {
/* 4526 */     StringBuilder builder = new StringBuilder();
/* 4527 */     builder.append(String.format("<?xml version='1.0' encoding='UTF-8'?>\n<osm version='0.6' upload='true' generator='JOSM'>\n  <bounds minlat='%s' minlon='%s' maxlat='%s' maxlon='%s' origin='CGImap 0.3.1 (29691 thorn-02.openstreetmap.org)' />\n", new Object[] { this.numberFormat.format(this.area.getMinY()), this.numberFormat.format(this.area.getMinX()), this.numberFormat.format(this.area.getMaxY()), this.numberFormat.format(this.area.getMaxX()) }));
/* 4537 */     long node = 2000000L;
/* 4540 */     ArrayList<OSMTest> shapes = new ArrayList<>();
/* 4543 */     long objectNumber = 999L;
/* 4546 */     for (DSFObject object : this.osmObjects) {
/* 4547 */       Point2D point = object.position;
/* 4548 */       if (point != null) {
/* 4550 */         Rule rule = object.rule;
/* 4551 */         builder.append(String.format("\t<node version='1' id='%d' lat='%s' lon='%s'>\n", new Object[] { Long.valueOf(objectNumber), this.numberFormat.format(point.y()), this.numberFormat.format(point.x()) }));
/* 4558 */         builder.append("\t\t<tag k='angle' v='" + this.numberFormat.format(object.angle) + "' />\n");
/* 4560 */         if (rule instanceof ObjectRule && object.getPolygon() != null) {
/* 4561 */           builder.append("\t\t<tag k='building' v='yes' />");
/* 4562 */           if (object.getPolygon().getAreaIdentifiers() != null && object.getPolygon().getAreaIdentifiers().size() > 0) {
/* 4563 */             String[] areaIdents = (String[])object.getPolygon().getAreaIdentifiers().toArray((Object[])new String[object.getPolygon().getAreaIdentifiers().size()]);
/* 4564 */             builder.append("\t\t<tag k='area' v='" + areaIdents[0] + "' />\n");
/*      */           } 
/* 4566 */         } else if (rule instanceof LineRule) {
/* 4567 */           if (rule.getFilter().getFilter().contains("highway")) {
/* 4568 */             builder.append("\t\t<tag k='highway' v='street_light' />");
/*      */           } else {
/* 4570 */             builder.append("\t\t<tag k='natural' v='tree' />");
/*      */           } 
/*      */         } else {
/* 4573 */           builder.append("\t\t<tag k='natural' v='tree' />");
/*      */         } 
/* 4577 */         if (object.getPolygon() != null && object.getPolygon().getHeight() != null) {
/* 4578 */           builder.append("\t\t<tag k='height' v='" + object.getPolygon().getHeight() + "' />");
/* 4579 */           builder.append("\t\t<tag k='levels' v='" + (int)(object.getPolygon().getHeight().floatValue() / 3.0F) + "' />");
/*      */         } 
/* 4582 */         if (object.objectDefinitionNumber != null)
/* 4583 */           builder.append("\t\t<tag k='model' v='" + this.generatorStore.getObjectDefinition(object.objectDefinitionNumber.intValue()).getPath() + "' />\n"); 
/* 4585 */         builder.append("</node>\n");
/* 4586 */         objectNumber++;
/*      */       } 
/*      */     } 
/* 4591 */     for (Envelope exclusion : this.buildingExclusions) {
/* 4592 */       LinearRing2D shape = new LinearRing2D();
/* 4593 */       shape.addVertex(new Point2D(exclusion.getMinX(), exclusion.getMinY()));
/* 4594 */       shape.addVertex(new Point2D(exclusion.getMinX(), exclusion.getMaxY()));
/* 4595 */       shape.addVertex(new Point2D(exclusion.getMaxX(), exclusion.getMaxY()));
/* 4596 */       shape.addVertex(new Point2D(exclusion.getMaxX(), exclusion.getMinY()));
/* 4597 */       shape.addVertex(new Point2D(exclusion.getMinX(), exclusion.getMinY()));
/* 4599 */       OSMTest item = new OSMTest(null, new OSMPolygon(shape), null, 0);
/* 4600 */       shapes.add(item);
/*      */     } 
/* 4604 */     for (Envelope exclusion : this.forestExclusions) {
/* 4605 */       LinearRing2D shape = new LinearRing2D();
/* 4606 */       shape.addVertex(new Point2D(exclusion.getMinX(), exclusion.getMinY()));
/* 4607 */       shape.addVertex(new Point2D(exclusion.getMinX(), exclusion.getMaxY()));
/* 4608 */       shape.addVertex(new Point2D(exclusion.getMaxX(), exclusion.getMaxY()));
/* 4609 */       shape.addVertex(new Point2D(exclusion.getMaxX(), exclusion.getMinY()));
/* 4610 */       shape.addVertex(new Point2D(exclusion.getMinX(), exclusion.getMinY()));
/* 4612 */       OSMTest item = new OSMTest(null, new OSMPolygon(shape), null, 1);
/* 4613 */       shapes.add(item);
/*      */     } 
/* 4629 */     for (OSMShape osmShape : this.osmShapes) {
/* 4630 */       if (osmShape.outer != null) {
/* 4631 */         OSMTest item = new OSMTest(osmShape.rule, osmShape.outer, osmShape, 2);
/* 4632 */         shapes.add(item);
/*      */       } 
/* 4634 */       if (osmShape.inner != null)
/* 4635 */         for (OSMPolygon inner : osmShape.inner) {
/* 4637 */           OSMTest item = new OSMTest(osmShape.rule, inner, osmShape, 2);
/* 4638 */           shapes.add(item);
/*      */         }  
/*      */     } 
/* 4643 */     for (OSMTest forest : shapes) {
/* 4646 */       for (int x = 0; x < forest.shape.vertexNumber() - 1; x++) {
/* 4647 */         if (!(forest.rule instanceof com.world2xplane.Rules.AutogenStringRule)) {
/* 4650 */           Point2D point = forest.shape.vertex(x);
/* 4651 */           builder.append(String.format("\t<node version='1' id='%d' lat='%s' lon='%s' />\n", new Object[] { Long.valueOf(node), this.numberFormat.format(point.y()), this.numberFormat.format(point.x()) }));
/* 4657 */           node++;
/*      */         } 
/*      */       } 
/*      */     } 
/* 4662 */     long wayId = node;
/* 4666 */     if (this.roadNetwork != null) {
/* 4667 */       for (Road road : this.roadNetwork.getRendered()) {
/* 4668 */         for (Junction junction : road.junctions) {
/* 4669 */           Point2D point = junction.getPoint();
/* 4670 */           builder.append(String.format("\t<node version='1' id='%d' lat='%s' lon='%s' />\n", new Object[] { Long.valueOf(junction.getId()), this.numberFormat.format(point.y()), this.numberFormat.format(point.x()) }));
/*      */         } 
/*      */       } 
/* 4679 */       for (Road road : this.roadNetwork.getRendered()) {
/* 4680 */         builder.append(String.format("\t<way version='1' id='%d'>\n", new Object[] { Long.valueOf(road.getIdentifier()) }));
/* 4681 */         for (Junction junction : road.junctions) {
/* 4682 */           Point2D point = junction.getPoint();
/* 4683 */           builder.append(String.format("\t\t<nd ref='%d' />\n", new Object[] { Long.valueOf(junction.getId()) }));
/*      */         } 
/* 4688 */         builder.append("\t\t<tag k='highway' v='residential' />\n");
/* 4689 */         if (road.isBridge())
/* 4690 */           builder.append("\t\t<tag k='bridge' v='yes' />\n"); 
/* 4692 */         builder.append("</way>\n");
/*      */       } 
/*      */     } 
/* 4697 */     List<OSMShape> autogens = new ArrayList<>();
/* 4698 */     for (OSMShape item : this.osmShapes) {
/* 4699 */       if (item.rule instanceof com.world2xplane.Rules.AutogenStringRule)
/* 4700 */         autogens.add(item); 
/*      */     } 
/* 4704 */     long idents = 9999L;
/* 4705 */     long nodeStart = node;
/* 4706 */     for (OSMShape road : autogens) {
/* 4707 */       for (int x = 0; x < road.outer.vertexNumber(); x++) {
/* 4708 */         Point2D point = road.outer.vertex(x);
/* 4709 */         builder.append(String.format("\t<node version='1' id='%d' lat='%s' lon='%s' />\n", new Object[] { Long.valueOf(node), this.numberFormat.format(point.y()), this.numberFormat.format(point.x()) }));
/* 4715 */         node++;
/*      */       } 
/*      */     } 
/* 4719 */     for (OSMShape road : autogens) {
/* 4720 */       builder.append(String.format("\t<way version='1' id='%d'>\n", new Object[] { Long.valueOf(idents) }));
/* 4721 */       idents++;
/* 4722 */       for (int x = 0; x < road.outer.vertexNumber(); x++) {
/* 4723 */         builder.append(String.format("\t\t<nd ref='%d' />\n", new Object[] { Long.valueOf(nodeStart) }));
/* 4726 */         nodeStart++;
/*      */       } 
/* 4728 */       builder.append("\t\t<tag k='highway' v='service' />\n");
/* 4729 */       builder.append("</way>\n");
/*      */     } 
/* 4732 */     node = 2000000L;
/* 4735 */     for (OSMTest forest : shapes) {
/* 4736 */       if (forest.rule instanceof com.world2xplane.Rules.AutogenStringRule)
/*      */         continue; 
/* 4739 */       boolean external = false;
/* 4740 */       if (forest.rule instanceof ForestRule && ((ForestRule)forest.rule).isPerimeterOnly() && 
/* 4741 */         !forest.osmShape.outer.isClosed())
/* 4742 */         external = true; 
/* 4745 */       builder.append(String.format("\t<way version='1' id='%d'>\n", new Object[] { Long.valueOf(wayId) }));
/* 4746 */       wayId++;
/* 4747 */       long startingNode = node;
/* 4748 */       for (int x = 0; x < forest.shape.vertexNumber() - 1; x++) {
/* 4749 */         builder.append(String.format("\t\t<nd ref='%d' />\n", new Object[] { Long.valueOf(node) }));
/* 4753 */         node++;
/*      */       } 
/* 4756 */       builder.append(String.format("\t\t<nd ref='%d' />\n", new Object[] { Long.valueOf(startingNode) }));
/* 4762 */       if (forest.shape.getHeight() != null)
/* 4763 */         builder.append("\t\t<tag k='height' v='" + forest.shape.getHeight() + "' />\n"); 
/* 4765 */       if (forest.shape.getCustomFacade() != null)
/* 4766 */         builder.append("\t\t<tag k='customfacade' v='" + forest.shape.getCustomFacade() + "' />\n"); 
/* 4769 */       if (forest.osmShape != null)
/* 4770 */         builder.append("\t\t<tag k='facade' v='" + this.generatorStore.getPolygonDefinition(forest.osmShape.objectDefinitionNumber.intValue()).getPath() + "' />\n"); 
/* 4774 */       builder.append("\t\t<tag k='area:squared' v='" + FastMath.sqrt(forest.shape.getArea().floatValue()) + "' />\n");
/* 4777 */       if (forest.rule != null && forest.rule.isCircular())
/* 4778 */         builder.append("\t\t<tag k='circular' v='true' />\n"); 
/* 4781 */       if (forest.rule != null) {
/* 4782 */         if (forest.rule instanceof ForestRule) {
/* 4783 */           builder.append("\t\t<tag k='natural' v='wood' />\n\t</way>\n");
/*      */           continue;
/*      */         } 
/* 4785 */         if (forest.rule instanceof com.world2xplane.Rules.AutogenStringRule) {
/* 4786 */           builder.append("\t\t<tag k='highway' v='service' />\n\t</way>\n");
/*      */           continue;
/*      */         } 
/* 4789 */         builder.append("\t\t<tag k='building' v='yes' />\n\t</way>\n");
/*      */         continue;
/*      */       } 
/* 4793 */       if (forest.exclusion == 0) {
/* 4794 */         builder.append("\t\t<tag k='landuse' v='residential' />\n\t</way>\n");
/*      */         continue;
/*      */       } 
/* 4797 */       builder.append("\t\t<tag k='landuse' v='retail' />\n\t</way>\n");
/*      */     } 
/* 4805 */     builder.append("</osm>");
/* 4806 */     return builder.toString();
/*      */   }
/*      */   
/*      */   public boolean fitsInside(OSMPolygon polygon) {
/* 4812 */     if (polygon == null)
/* 4813 */       return false; 
/* 4815 */     for (int x = 0; x < polygon.vertexNumber(); x++) {
/* 4816 */       Point2D item = polygon.vertex(x);
/* 4817 */       if (item.x() < this.area.getMinX() || item.x() > this.area.getMaxX() || item.y() < this.area.getMinY() || item.y() > this.area.getMaxY())
/* 4818 */         return false; 
/*      */     } 
/* 4821 */     return true;
/*      */   }
/*      */   
/*      */   public boolean fitsInside(OSMShape osmShape) {
/* 4825 */     if (!fitsInside(osmShape.outer))
/* 4826 */       return false; 
/* 4828 */     return true;
/*      */   }
/*      */   
/*      */   public Point2D getTilePosition() {
/* 4833 */     return this.tilePosition;
/*      */   }
/*      */   
/*      */   public void setTilePosition(Point2D tilePosition) {
/* 4837 */     this.tilePosition = tilePosition;
/*      */   }
/*      */   
/*      */   public Box2D getArea() {
/* 4841 */     return this.area;
/*      */   }
/*      */   
/*      */   public int getObjectCount() {
/* 4846 */     return this.objectCount;
/*      */   }
/*      */   
/*      */   public void setObjectCount(int objectCount) {
/* 4850 */     this.objectCount = objectCount;
/*      */   }
/*      */   
/*      */   public int getForestCount() {
/* 4854 */     return this.forestCount;
/*      */   }
/*      */   
/*      */   public void setForestCount(int forestCount) {
/* 4858 */     this.forestCount = forestCount;
/*      */   }
/*      */   
/*      */   public int getBuildingCount() {
/* 4862 */     return this.buildingCount;
/*      */   }
/*      */   
/*      */   public void setBuildingCount(int buildingCount) {
/* 4866 */     this.buildingCount = buildingCount;
/*      */   }
/*      */   
/*      */   public short getLongitude() {
/* 4870 */     return this.longitude;
/*      */   }
/*      */   
/*      */   public void setLongitude(short longitude) {
/* 4874 */     this.longitude = longitude;
/*      */   }
/*      */   
/*      */   public short getLatitude() {
/* 4878 */     return this.latitude;
/*      */   }
/*      */   
/*      */   public void setLatitude(short latitude) {
/* 4882 */     this.latitude = latitude;
/*      */   }
/*      */   
/*      */   public int getForestExclusionCount() {
/* 4886 */     return this.forestExclusionCount;
/*      */   }
/*      */   
/*      */   public void setForestExclusionCount(int forestExclusionCount) {
/* 4890 */     this.forestExclusionCount = forestExclusionCount;
/*      */   }
/*      */   
/*      */   public int getBuildingExclusionCount() {
/* 4894 */     return this.buildingExclusionCount;
/*      */   }
/*      */   
/*      */   public void setBuildingExclusionCount(int buildingExclusionCount) {
/* 4898 */     this.buildingExclusionCount = buildingExclusionCount;
/*      */   }
/*      */   
/*      */   public int getRoadCount() {
/* 4902 */     return this.roadCount;
/*      */   }
/*      */   
/*      */   public void setRoadCount(int roadCount) {
/* 4906 */     this.roadCount = roadCount;
/*      */   }
/*      */   
/*      */   public int getRailCount() {
/* 4910 */     return this.railCount;
/*      */   }
/*      */   
/*      */   public void setRailCount(int railCount) {
/* 4914 */     this.railCount = railCount;
/*      */   }
/*      */   
/*      */   public int getVectorCount() {
/* 4918 */     return this.vectorCount;
/*      */   }
/*      */   
/*      */   public void setVectorCount(int vectorCount) {
/* 4922 */     this.vectorCount = vectorCount;
/*      */   }
/*      */   
/*      */   public List<NetworkItem> getNetworkItems() {
/* 4926 */     return this.networkItems;
/*      */   }
/*      */   
/*      */   public void setNetworkItems(List<NetworkItem> networkItems) {
/* 4930 */     this.networkItems = networkItems;
/*      */   }
/*      */   
/*      */   public File getPolyFile() {
/* 4934 */     return this.polyFile;
/*      */   }
/*      */   
/*      */   public void setPolyFile(File polyFile) {
/* 4938 */     this.polyFile = polyFile;
/*      */   }
/*      */   
/*      */   public String getRegions() {
/* 4942 */     return this.regions;
/*      */   }
/*      */   
/*      */   public static interface Progress {
/*      */     void progressChanged(DSFTile param1DSFTile, String param1String, float param1Float);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\DSFTile.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */