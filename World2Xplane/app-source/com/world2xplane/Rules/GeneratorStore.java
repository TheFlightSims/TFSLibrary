/*      */ package com.world2xplane.Rules;
/*      */ 
/*      */ import com.vividsolutions.jts.geom.Coordinate;
/*      */ import com.vividsolutions.jts.geom.Envelope;
/*      */ import com.vividsolutions.jts.geom.Geometry;
/*      */ import com.vividsolutions.jts.geom.GeometryFactory;
/*      */ import com.vividsolutions.jts.index.strtree.STRtree;
/*      */ import com.world2xplane.Geom.GeomUtils;
/*      */ import com.world2xplane.Network.NetworkItem;
/*      */ import com.world2xplane.Network.RoadNetwork;
/*      */ import com.world2xplane.Network.VectorRule;
/*      */ import com.world2xplane.OSM.Hotspot;
/*      */ import com.world2xplane.OSM.HotspotEntry;
/*      */ import com.world2xplane.OSM.OSMPolygon;
/*      */ import com.world2xplane.OSM.PolygonFileReader;
/*      */ import com.world2xplane.Rules.Facade.FacadeItemList;
/*      */ import com.world2xplane.Rules.Facade.osm2xp.Facade;
/*      */ import com.world2xplane.Rules.Facade.osm2xp.FacadeSet;
/*      */ import com.world2xplane.Rules.Filter.EqualsFilter;
/*      */ import com.world2xplane.Rules.Filter.Filter;
/*      */ import com.world2xplane.Rules.ObjectRules.ObjectList;
/*      */ import com.world2xplane.Rules.Regions.CSVRegion;
/*      */ import com.world2xplane.Rules.Regions.RegionProvider;
/*      */ import com.world2xplane.Rules.Regions.ShapeFileReader;
/*      */ import com.world2xplane.XPlane.PathValidator;
/*      */ import gnu.trove.map.hash.TIntObjectHashMap;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.File;
/*      */ import java.io.FileFilter;
/*      */ import java.io.IOException;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.DecimalFormatSymbols;
/*      */ import java.text.NumberFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import javax.xml.bind.JAXBContext;
/*      */ import javax.xml.bind.JAXBException;
/*      */ import javax.xml.bind.Unmarshaller;
/*      */ import javax.xml.parsers.DocumentBuilder;
/*      */ import javax.xml.parsers.DocumentBuilderFactory;
/*      */ import math.geom2d.Point2D;
/*      */ import math.geom2d.line.LineSegment2D;
/*      */ import org.apache.commons.io.FileUtils;
/*      */ import org.apache.commons.io.FilenameUtils;
/*      */ import org.apache.xerces.dom.DeferredElementImpl;
/*      */ import org.slf4j.Logger;
/*      */ import org.slf4j.LoggerFactory;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.NodeList;
/*      */ import org.xml.sax.InputSource;
/*      */ 
/*      */ public class GeneratorStore {
/*      */   public static final String EXCLUDE_OBJECTS = "exclude-objects";
/*      */   
/*      */   public static final String EXCLUDE_FORESTS = "exclude-forests";
/*      */   
/*      */   public static final String EXCLUDE_FACADES = "exclude-facades";
/*      */   
/*      */   public static final String EXCLUDE_BEACHES = "exclude-beaches";
/*      */   
/*      */   public static final String SKIP_AIRPORT = "skip-airport";
/*      */   
/*      */   public static final String EXCLUDE_NETWORK = "exclude-network";
/*      */   
/*      */   public static final String SMART_EXCLUSIONS = "smart-exclusions";
/*      */   
/*      */   public static final String BUILDING_EXCLUSION_RANGE = "building-exclusion-range";
/*      */   
/*      */   public static final String FOREST_EXCLUSION_RANGE = "forest-exclusion-range";
/*      */   
/*      */   public static final String BUILDINGS_PER_GRID = "buildings-per-grid";
/*      */   
/*      */   public static final String CREATE_ROAD_NETWORK = "create-road-network";
/*      */   
/*      */   public static final String CLIP_ROADS = "clip-roads";
/*      */   
/*      */   public static final String CLIP_AIRPORTS = "clip-airports";
/*      */   
/*      */   public static final String TEMPORARY_FILE_PATH = "temporary-file-path";
/*      */   
/*      */   public static final String REGION_ENABLED = "region-enabled";
/*      */   
/*      */   public static final String COMPRESS_TEXTURES = "compress-textures";
/*      */   
/*      */   public static final String OSM_3D = "enable-osm-3d";
/*      */   
/*      */   public static final String NUMBER_OF_THREADS = "processor-threads";
/*      */   
/*      */   public static final String FACADE_HEIGHT_LIMIT = "facade-height-limit";
/*      */   
/*      */   public static final String WORLD_MODELS = "world-models-location";
/*      */   
/*      */   public static final String REMOVE_SHARED_EDGES = "remove-shared-edges";
/*      */   
/*      */   public static final String APP_NAME = "World2XPlane";
/*      */   
/*      */   public static final String VERSION = "0.7.4";
/*      */   
/*      */   private static GeneratorStore generatorStore;
/*      */   
/*  104 */   private static Logger log = LoggerFactory.getLogger(GeneratorStore.class);
/*      */   
/*      */   private String xplaneSceneryFolder;
/*      */   
/*      */   private boolean validateSceneryFolder;
/*      */   
/*      */   private boolean enabledRegions = true;
/*      */   
/*      */   private boolean createBuildingParts = true;
/*      */   
/*      */   private boolean createForestExclusions = true;
/*      */   
/*      */   private boolean createObjectExclusions = true;
/*      */   
/*      */   private boolean createFacadeExclusions = true;
/*      */   
/*      */   private boolean createBeachExclusions = true;
/*      */   
/*      */   private boolean createNetworkExclusions = true;
/*      */   
/*  118 */   private int processorThreads = 3;
/*      */   
/*  119 */   private float facadeHeightLimit = 200.0F;
/*      */   
/*  121 */   private int buildingsPerGrid = 10;
/*      */   
/*      */   private boolean clipAirports = true;
/*      */   
/*      */   private boolean copyToXPlane = false;
/*      */   
/*  127 */   private HashSet<String> setList = new HashSet<>();
/*      */   
/*  129 */   private Map<String, FacadeSet> facadeSets = new HashMap<>();
/*      */   
/*  130 */   private Set<String> facadeDirectories = new HashSet<>();
/*      */   
/*      */   private String outputFolder;
/*      */   
/*      */   private ObjectDefinitionStore objectDefinitionStore;
/*      */   
/*  134 */   private ArrayList<Rule> rules = new ArrayList<>();
/*      */   
/*  135 */   private HashMap<Integer, Object> ruleIndexes = new HashMap<>();
/*      */   
/*  137 */   private ArrayList<NetworkItem> networkRules = new ArrayList<>();
/*      */   
/*  139 */   private List<RegionProvider> regionProviders = new ArrayList<>();
/*      */   
/*  140 */   private List<Hotspot> hotspots = new ArrayList<>();
/*      */   
/*  142 */   private Map<String, RandomSeed> randomSeeds = new HashMap<>();
/*      */   
/*  144 */   private String temporaryFilePath = "";
/*      */   
/*      */   private boolean smartExclusions = true;
/*      */   
/*      */   private boolean toOSM = false;
/*      */   
/*  149 */   private int facadeLod = 12000;
/*      */   
/*      */   private boolean enableDebugImage = false;
/*      */   
/*  152 */   private String worldModelsLocation = null;
/*      */   
/*  154 */   private int randomPlacementLimit = 700000;
/*      */   
/*      */   private Map<String, KeyCache> usedKeys;
/*      */   
/*  160 */   private TIntObjectHashMap<List<AcceptingRule>> acceptingRuleCache = new TIntObjectHashMap();
/*      */   
/*      */   private HashSet<String> regionCodes;
/*      */   
/*      */   private boolean generateFacades = true;
/*      */   
/*      */   private boolean createFacades = true;
/*      */   
/*      */   private boolean generateRoofWallColors = true;
/*      */   
/*      */   private boolean compressTextures = true;
/*      */   
/*      */   private boolean useLightingForColours = true;
/*      */   
/*  168 */   private Integer buildingExclusionRange = Integer.valueOf(500);
/*      */   
/*  169 */   private Integer forestExclusionRange = Integer.valueOf(500);
/*      */   
/*      */   private boolean generateRoads = false;
/*      */   
/*      */   private boolean clipRoads = true;
/*      */   
/*      */   private boolean debugging = false;
/*      */   
/*  174 */   private RoadNetwork roadNetwork = null;
/*      */   
/*  176 */   private Map<String, LibDefinition> libDefinitionMap = new HashMap<>();
/*      */   
/*  177 */   private List<AdaptiveHeight> heightPolicies = new ArrayList<>();
/*      */   
/*      */   private String polyFile;
/*      */   
/*      */   private Geometry regionPoly;
/*      */   
/*      */   private STRtree hotspotTree;
/*      */   
/*  181 */   private Set<String> emptyRegions = new HashSet<>();
/*      */   
/*      */   NumberFormat numberFormat;
/*      */   
/*      */   public String getOutputFolder() {
/*  185 */     return this.outputFolder;
/*      */   }
/*      */   
/*      */   public void setOutputFolder(String outputFolder) {
/*  189 */     this.outputFolder = outputFolder;
/*      */   }
/*      */   
/*      */   public static GeneratorStore getGeneratorStore() {
/*  194 */     if (generatorStore == null)
/*  195 */       throw new RuntimeException("Can't get config as it hasn't been initalised yet."); 
/*  197 */     return generatorStore;
/*      */   }
/*      */   
/*      */   public void readConfig(File configFile) throws Exception {
/*  204 */     DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
/*  206 */     this.numberFormat = new DecimalFormat("#####.###########", formatSymbols);
/*  211 */     String configText = FileUtils.readFileToString(configFile);
/*  212 */     StringBuilder output = new StringBuilder();
/*  213 */     for (String line : configText.split("\n")) {
/*  214 */       if (line.contains("<include file=\"")) {
/*  215 */         String include = line.trim();
/*  216 */         include = include.substring(include.indexOf("file=\"") + 6);
/*  217 */         include = include.substring(0, include.indexOf("\""));
/*  218 */         File includeFile = new File(include);
/*  219 */         String incContent = FileUtils.readFileToString(includeFile);
/*  220 */         incContent = incContent.replaceAll("<xst>", "");
/*  221 */         incContent = incContent.replaceAll("</xst>", "");
/*  222 */         output.append(incContent);
/*  223 */         output.append("\n");
/*      */       } else {
/*  225 */         output.append(line);
/*  226 */         output.append("\n");
/*      */       } 
/*      */     } 
/*  230 */     this.objectDefinitionStore = new ObjectDefinitionStore(this);
/*  231 */     log.info("Reading Config File " + configFile);
/*  232 */     DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
/*  233 */     DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
/*  234 */     Document doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(output.toString().getBytes("utf-8"))));
/*  235 */     doc.getDocumentElement().normalize();
/*  237 */     String rootElement = doc.getDocumentElement().getNodeName();
/*  239 */     if (!rootElement.equals("xst"))
/*  240 */       throw new Exception("Not a valid config file"); 
/*  243 */     this.facadeLod = getNodeIntegerValue("facade-lod", doc.getDocumentElement());
/*  244 */     if (this.facadeLod == -1)
/*  245 */       this.facadeLod = 13000; 
/*  248 */     NodeList facadeName = doc.getElementsByTagName("facadeSet");
/*  249 */     if (facadeName == null || facadeName.getLength() == 0)
/*  250 */       throw new Exception("Facade set not set in config file"); 
/*  254 */     for (int x = 0; x < facadeName.getLength(); x++) {
/*  255 */       String facadeSetName = facadeName.item(x).getFirstChild().getNodeValue();
/*  256 */       String identifier = facadeName.item(x).getAttributes().getNamedItem("identifier").getNodeValue();
/*  257 */       File file = new File(facadeSetName);
/*  258 */       if (!file.exists())
/*  259 */         throw new Exception("Facade set " + facadeSetName + " does not exist"); 
/*  261 */       this.facadeSets.put(identifier, loadFacadeSet(facadeSetName, identifier, FilenameUtils.getBaseName(file.getParent())));
/*  262 */       log.info("Added Facade Set " + identifier);
/*  263 */       this.facadeDirectories.add(FilenameUtils.getBaseName(file.getParent()));
/*      */     } 
/*  268 */     NodeList hotspotNodes = doc.getElementsByTagName("hotspot");
/*  269 */     if (hotspotNodes != null || hotspotNodes.getLength() > 0)
/*  270 */       for (int j = 0; j < hotspotNodes.getLength(); j++) {
/*  271 */         Node node = hotspotNodes.item(j);
/*  272 */         String idenfitier = getNodeStringValue("identifier", node);
/*  273 */         String filter = getNodeStringValue("filter", node);
/*  274 */         String debug = getNodeStringValue("debug", node);
/*  275 */         FilterList filterList = new FilterList();
/*  276 */         filterList.setFilter(filter);
/*  277 */         filterList.setFilterType("key-value");
/*  278 */         filterList.parseFilter();
/*  279 */         Hotspot hotspot = new Hotspot();
/*  280 */         hotspot.setFilterList(filterList);
/*  281 */         hotspot.setDebug(debug);
/*  282 */         hotspot.setIdentifier(idenfitier);
/*  284 */         this.hotspots.add(hotspot);
/*      */       }  
/*  288 */     NodeList randomSeeds = doc.getElementsByTagName("random-seed");
/*  289 */     if (randomSeeds != null || randomSeeds.getLength() > 0)
/*  290 */       for (int j = 0; j < randomSeeds.getLength(); j++) {
/*  291 */         Node node = randomSeeds.item(j);
/*  292 */         int min = getNodeIntegerValue("min", node);
/*  293 */         int max = getNodeIntegerValue("max", node);
/*  294 */         String idenfitier = getNodeStringValue("identifier", node);
/*  295 */         RandomSeed randomSeed = new RandomSeed();
/*  296 */         randomSeed.setIdentifier(idenfitier);
/*  297 */         randomSeed.setMin(min);
/*  298 */         randomSeed.setMax(max);
/*  299 */         this.randomSeeds.put(idenfitier, randomSeed);
/*      */       }  
/*  303 */     NodeList libDefs = doc.getElementsByTagName("lib");
/*  304 */     if (libDefs != null || libDefs.getLength() > 0)
/*  305 */       for (int j = 0; j < libDefs.getLength(); j++) {
/*  306 */         Node node = libDefs.item(j);
/*  307 */         String idenfitier = node.getFirstChild().getNodeValue();
/*  308 */         boolean enabled = node.getAttributes().getNamedItem("enabled").getNodeValue().trim().toLowerCase().equals("true");
/*  309 */         LibDefinition libDefinition = new LibDefinition();
/*  310 */         libDefinition.setIdentifier(idenfitier);
/*  311 */         libDefinition.setEnabled(enabled);
/*  312 */         this.libDefinitionMap.put(idenfitier, libDefinition);
/*      */       }  
/*  316 */     NodeList adaptiveHeights = doc.getElementsByTagName("adaptive-height");
/*  317 */     if (adaptiveHeights != null || adaptiveHeights.getLength() > 0)
/*  318 */       for (int j = 0; j < adaptiveHeights.getLength(); j++) {
/*  319 */         Node node = adaptiveHeights.item(j);
/*  320 */         String identifier = node.getAttributes().getNamedItem("identifier").getNodeValue().trim().toLowerCase();
/*  321 */         AdaptiveHeight adaptiveHeight = new AdaptiveHeight();
/*  322 */         adaptiveHeight.setIdentifier(identifier);
/*  323 */         NodeList adaptiveHeightPolicies = ((Element)node).getElementsByTagName("node");
/*  324 */         if (adaptiveHeightPolicies != null)
/*  325 */           for (int y = 0; y < adaptiveHeightPolicies.getLength(); y++) {
/*  326 */             Node adaptiveHeightPolicyNode = adaptiveHeightPolicies.item(y);
/*  328 */             identifier = adaptiveHeightPolicyNode.getAttributes().getNamedItem("identifier").getNodeValue().trim().toLowerCase();
/*  329 */             int min_levels = (new Integer(adaptiveHeightPolicyNode.getAttributes().getNamedItem("min_levels").getNodeValue().trim().toLowerCase())).intValue();
/*  330 */             int max_levels = (new Integer(adaptiveHeightPolicyNode.getAttributes().getNamedItem("max_levels").getNodeValue().trim().toLowerCase())).intValue();
/*  331 */             float range = (new Float(adaptiveHeightPolicyNode.getAttributes().getNamedItem("range").getNodeValue().trim().toLowerCase())).floatValue();
/*  332 */             adaptiveHeight.getAdaptiveHeightPolicies().add(new AdaptiveHeight.AdaptiveHeightPolicy(identifier, min_levels, max_levels, range));
/*      */           }  
/*  338 */         this.heightPolicies.add(adaptiveHeight);
/*      */       }  
/*  343 */     this.enabledRegions = getNodeBooleanValue("region-enabled", doc.getDocumentElement());
/*  344 */     this.createFacadeExclusions = getNodeBooleanValue("exclude-facades", doc.getDocumentElement());
/*  345 */     this.createBeachExclusions = getNodeBooleanValue("exclude-beaches", doc.getDocumentElement());
/*  347 */     this.createObjectExclusions = getNodeBooleanValue("exclude-objects", doc.getDocumentElement());
/*  348 */     this.createForestExclusions = getNodeBooleanValue("exclude-forests", doc.getDocumentElement());
/*  349 */     this.createNetworkExclusions = getNodeBooleanValue("exclude-network", doc.getDocumentElement());
/*  350 */     this.facadeHeightLimit = getNodeFloatValue("facade-height-limit", doc.getDocumentElement());
/*  351 */     if (this.facadeHeightLimit == -1.0F)
/*  352 */       this.facadeHeightLimit = 200.0F; 
/*  355 */     if (this.worldModelsLocation == null)
/*  356 */       this.worldModelsLocation = getNodeStringValue("world-models-location", doc.getDocumentElement()); 
/*  357 */     if (this.worldModelsLocation == null || this.worldModelsLocation.length() == 0) {
/*  358 */       this.worldModelsLocation = getXplaneSceneryFolder() + File.separator + "Custom Scenery" + File.separator + "world-models";
/*  360 */       this.worldModelsLocation = getXplaneSceneryFolder() + File.separator + "Custom Scenery" + File.separator + "world-models-master";
/*  361 */       if (!(new File(this.worldModelsLocation)).exists() && !(new File(this.worldModelsLocation)).exists())
/*  362 */         throw new RuntimeException("Cannot find world-models folder inside your X-Plane installation. Please go to https://github.com/tonywob/world-models to download the latest version."); 
/*  367 */     } else if (!(new File(this.worldModelsLocation)).exists()) {
/*  368 */       throw new RuntimeException("Cannot find world-models, please check the world-models-folder option in the config file");
/*      */     } 
/*  372 */     this.smartExclusions = getNodeBooleanValue("smart-exclusions", doc.getDocumentElement());
/*  373 */     this.compressTextures = getNodeBooleanValue("compress-textures", doc.getDocumentElement());
/*  374 */     this.createBuildingParts = getNodeBooleanValue("enable-osm-3d", doc.getDocumentElement());
/*  375 */     this.forestExclusionRange = Integer.valueOf(getNodeIntegerValue("forest-exclusion-range", doc.getDocumentElement()));
/*  376 */     this.enableDebugImage = getNodeBooleanValue("enable-debug-image", doc.getDocumentElement());
/*  377 */     this.generateRoads = getNodeBooleanValue("create-road-network", doc.getDocumentElement());
/*  378 */     this.clipRoads = getNodeBooleanValue("clip-roads", doc.getDocumentElement());
/*  379 */     this.debugging = getNodeBooleanValue("debug-messages", doc.getDocumentElement());
/*  380 */     this.clipAirports = getNodeBooleanValue("clip-airports", doc.getDocumentElement());
/*  381 */     this.buildingsPerGrid = getNodeIntegerValue("buildings-per-grid", doc.getDocumentElement());
/*  382 */     this.temporaryFilePath = getNodeStringValue("temporary-file-path", doc.getDocumentElement());
/*  384 */     if (this.temporaryFilePath == null || this.temporaryFilePath.length() == 0)
/*  385 */       this.temporaryFilePath = "./"; 
/*  387 */     if (!(new File(this.temporaryFilePath)).exists())
/*  388 */       throw new RuntimeException("Temporary File Path " + this.temporaryFilePath + " is inaccessible or doesn't exist"); 
/*  390 */     if (this.buildingsPerGrid < 0)
/*  391 */       this.buildingsPerGrid = 10; 
/*  394 */     if (this.forestExclusionRange.intValue() < 200)
/*  395 */       this.forestExclusionRange = Integer.valueOf(200); 
/*  399 */     this.buildingExclusionRange = Integer.valueOf(getNodeIntegerValue("building-exclusion-range", doc.getDocumentElement()));
/*  401 */     if (this.buildingExclusionRange.intValue() < 100)
/*  402 */       this.buildingExclusionRange = Integer.valueOf(100); 
/*  405 */     this.randomPlacementLimit = getNodeIntegerValue("random-placement-limit", doc.getDocumentElement());
/*  406 */     if (this.randomPlacementLimit <= 0)
/*  407 */       this.randomPlacementLimit = 700000; 
/*  410 */     this.generateRoofWallColors = getNodeBooleanValue("generate-roof-wall-colors", doc.getDocumentElement());
/*  413 */     for (Map.Entry<String, FacadeSet> facadeSet : this.facadeSets.entrySet()) {
/*  414 */       for (Facade item : ((FacadeSet)facadeSet.getValue()).getFacades())
/*  415 */         this.objectDefinitionStore.pushObject(ObjectDefinitionStore.ObjectType.FACADE, item.getFile(), item); 
/*      */     } 
/*  419 */     NodeList rulesList = doc.getElementsByTagName("rule");
/*  420 */     for (int i = 0; i < rulesList.getLength(); i++)
/*  421 */       parseRule(rulesList.item(i)); 
/*  426 */     if (this.generateRoads || this.clipRoads) {
/*  427 */       RoadNetwork roadRules = new RoadNetwork(this);
/*  428 */       this.networkRules.add(roadRules);
/*  429 */       this.rules.add(roadRules);
/*      */     } 
/*  434 */     List<Rule> copy = new ArrayList<>();
/*  435 */     copy.addAll(this.rules);
/*  436 */     for (Rule item : copy) {
/*  437 */       if (item.getFilter() == null || item.getFilter().getFilter() == null || item.getFilter().getFilter().trim().length() == 0) {
/*  438 */         log.error("Rule " + item + " had no active filter. Please check your config");
/*  439 */         throw new RuntimeException("Rule " + item + " had no active filter. Please check your config. This rule will accept everything, so execution has been stopped.");
/*      */       } 
/*      */     } 
/*  447 */     NodeList regions = doc.getElementsByTagName("region-identifier");
/*  448 */     if (regions != null && regions.getLength() > 0)
/*  449 */       for (int j = 0; j < regions.getLength(); j++)
/*  450 */         parseRegion(regions.item(j));  
/*  455 */     generatorStore = this;
/*  458 */     log.info("Reading Regions");
/*  459 */     for (RegionProvider item : this.regionProviders)
/*  460 */       item.load(); 
/*      */   }
/*      */   
/*      */   public void writeFacadeLods() throws IOException {
/*  466 */     log.info("Creating LODs for Facades");
/*  467 */     for (String directory : this.facadeDirectories) {
/*  468 */       File facadeDirectory = new File(getOutputFolder() + File.separator + directory);
/*  469 */       File[] files = facadeDirectory.listFiles(new FileFilter() {
/*      */             public boolean accept(File file) {
/*  471 */               return file.getAbsolutePath().endsWith(".fac");
/*      */             }
/*      */           });
/*  476 */       for (File file : files) {
/*  477 */         String lodText = FileUtils.readFileToString(file);
/*  478 */         String[] lines = lodText.split("\n");
/*  479 */         StringBuilder output = new StringBuilder();
/*  480 */         for (String item : lines) {
/*  481 */           if (item.startsWith("LOD")) {
/*  482 */             output.append("LOD 0.000000 " + getFacadeLod() + ".000000\n");
/*      */           } else {
/*  484 */             output.append(item + "\n");
/*      */           } 
/*      */         } 
/*  487 */         FileUtils.writeStringToFile(file, output.toString());
/*      */       } 
/*  491 */       int[] lods = { 2, 4, 5, 7, 12, 13, 14, 20, 25 };
/*  494 */       for (int x : lods) {
/*  495 */         for (File file : files) {
/*  496 */           String lodText = FileUtils.readFileToString(file);
/*  497 */           String[] lines = lodText.split("\n");
/*  498 */           StringBuilder output = new StringBuilder();
/*  499 */           for (String item : lines) {
/*  501 */             if (item.startsWith("LOD")) {
/*  502 */               output.append("LOD 0.000000 " + (x * 1000) + ".000000\n");
/*      */             } else {
/*  504 */               output.append(item + "\n");
/*      */             } 
/*      */           } 
/*  507 */           String filePath = file.getAbsolutePath();
/*  508 */           filePath = filePath.substring(0, filePath.length() - 4);
/*  509 */           File lodFile = new File(filePath + "_L" + x + ".fac");
/*  510 */           FileUtils.writeStringToFile(lodFile, output.toString());
/*  513 */           this.objectDefinitionStore.pushObject(ObjectDefinitionStore.ObjectType.FACADE, FilenameUtils.getBaseName(lodFile.getParent()) + File.separator + lodFile.getName(), null);
/*      */         } 
/*      */       } 
/*      */     } 
/*  520 */     for (String directory : this.facadeDirectories) {
/*  521 */       File facadeDirectory = new File(getOutputFolder() + File.separator + directory);
/*  522 */       File[] files = facadeDirectory.listFiles(new FileFilter() {
/*      */             public boolean accept(File file) {
/*  524 */               return file.getAbsolutePath().endsWith(".fac");
/*      */             }
/*      */           });
/*  529 */       for (File file : files) {
/*  530 */         String lodText = FileUtils.readFileToString(file);
/*  531 */         String[] lines = lodText.split("\n");
/*  532 */         StringBuilder output = new StringBuilder();
/*  533 */         for (String item : lines) {
/*  534 */           if (!item.trim().startsWith("GRADED"))
/*  535 */             output.append(item + "\n"); 
/*      */         } 
/*  539 */         String filePath = file.getAbsolutePath();
/*  540 */         filePath = filePath.substring(0, filePath.length() - 4);
/*  541 */         File gFile = new File(filePath + "_G" + ".fac");
/*  542 */         FileUtils.writeStringToFile(gFile, output.toString());
/*  543 */         this.objectDefinitionStore.pushObject(ObjectDefinitionStore.ObjectType.FACADE, FilenameUtils.getBaseName(gFile.getParent()) + File.separator + gFile.getName(), null);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void parseRegion(Node regionNode) throws Exception {
/*  551 */     Node type = regionNode.getAttributes().getNamedItem("type");
/*  552 */     if (type == null)
/*  553 */       throw new Exception("No type given for region"); 
/*  556 */     String regionType = type.getNodeValue();
/*  557 */     if (regionType.equals("shapefile")) {
/*  558 */       ShapeFileReader shapeFileReader = new ShapeFileReader();
/*  559 */       shapeFileReader.setPath(getNodeStringValue("file", regionNode));
/*  560 */       shapeFileReader.setIdentifier(getNodeStringValue("identifier", regionNode));
/*  561 */       shapeFileReader.setPriority(getNodeIntegerValue("priority", regionNode));
/*  562 */       if (shapeFileReader.validate())
/*  563 */         this.regionProviders.add(shapeFileReader); 
/*  565 */     } else if (regionType.equals("csv")) {
/*  566 */       CSVRegion csvRegion = new CSVRegion();
/*  567 */       csvRegion.setPath(getNodeStringValue("file", regionNode));
/*  568 */       csvRegion.setPriority(getNodeIntegerValue("priority", regionNode));
/*  569 */       if (csvRegion.validate())
/*  570 */         this.regionProviders.add(csvRegion); 
/*      */     } else {
/*  573 */       throw new Exception("Unsupported region type " + regionType + " in config file");
/*      */     } 
/*      */   }
/*      */   
/*      */   private void parseRule(Node ruleNode) throws Exception {
/*  581 */     Node type = ruleNode.getAttributes().getNamedItem("type");
/*  582 */     if (type == null)
/*  583 */       throw new Exception("No type given for rule"); 
/*  586 */     String ruleType = type.getNodeValue();
/*  587 */     if (ruleType.equals("forest"))
/*  588 */       parseForestRule((Element)ruleNode); 
/*  590 */     if (ruleType.equals("autogen-string"))
/*  591 */       parseAutogenString((Element)ruleNode); 
/*  593 */     if (ruleType.equals("object"))
/*  594 */       parseObjectRule((Element)ruleNode); 
/*  596 */     if (ruleType.equals("facade"))
/*  597 */       parseFacadeRule((Element)ruleNode); 
/*  599 */     if (ruleType.equals("polygon"))
/*  600 */       parsePolygonRule((Element)ruleNode); 
/*  602 */     if (ruleType.equals("sink"))
/*  603 */       parseSinkRule((Element)ruleNode); 
/*  605 */     if (ruleType.equals("random"))
/*  606 */       parseRandomRule((Element)ruleNode); 
/*  609 */     if (ruleType.equals("line"))
/*  610 */       parseLineRule((Element)ruleNode); 
/*  613 */     if (ruleType.equals("area-track"))
/*  614 */       parseAreaTracker((Element)ruleNode); 
/*  617 */     if (ruleType.equals("node-track"))
/*  618 */       parseNodeTracker((Element)ruleNode); 
/*  621 */     if (ruleType.equals("way-track"))
/*  622 */       parseWayTracker((Element)ruleNode); 
/*  625 */     if (ruleType.equals("vector"))
/*  626 */       parseVectorRule((Element)ruleNode); 
/*      */   }
/*      */   
/*      */   private void parseForestRule(Element ruleNode) throws Exception {
/*  631 */     ForestRule forestRule = new ForestRule(this);
/*  633 */     this.rules.add(forestRule);
/*  635 */     parseRegionFilters(forestRule, ruleNode);
/*  636 */     getCommonValues(forestRule, ruleNode);
/*  639 */     NodeList filterNode = ruleNode.getElementsByTagName("filter");
/*  640 */     if (filterNode == null || filterNode.getLength() != 1)
/*  641 */       throw new Exception("Rule does not contain a filter section"); 
/*  644 */     getFilter(forestRule, filterNode);
/*  646 */     NodeList forestsListNode = ruleNode.getElementsByTagName("forests");
/*  647 */     if (forestsListNode == null || forestsListNode.getLength() == 0)
/*  648 */       throw new Exception("No forests found for forest rule"); 
/*  651 */     Node forestsNode = forestsListNode.item(0);
/*  652 */     NodeList forestNodes = ((Element)forestsNode).getElementsByTagName("forest");
/*  654 */     forestRule.setDensity(getNodeIntegerValue("density", ruleNode));
/*  655 */     forestRule.setPassThrough(getNodeBooleanValue("pass-through", ruleNode));
/*  656 */     forestRule.setPerimeterOnly(getNodeBooleanValue("perimeter-only", ruleNode));
/*  657 */     forestRule.setRemoveSharedEdges(getNodeBooleanValue("remove-shared-edges", ruleNode));
/*  659 */     if (forestNodes == null || forestNodes.getLength() == 0)
/*  660 */       throw new Exception("No forests found for forest rule"); 
/*  663 */     for (int x = 0; x < forestNodes.getLength(); x++) {
/*  664 */       Node forestNode = forestNodes.item(x);
/*  665 */       String pathName = forestNode.getFirstChild().getNodeValue().trim();
/*  666 */       forestRule.getForestFiles().add(Integer.valueOf(this.objectDefinitionStore.pushObject(ObjectDefinitionStore.ObjectType.FOREST, pathName, null)));
/*      */     } 
/*  669 */     forestRule.setPassThrough(getNodeBooleanValue("pass-through", ruleNode));
/*  670 */     forestRule.setCircular(getNodeStringValue("circular", ruleNode).equals("true"));
/*  671 */     forestRule.setMinRadius(getNodeDoubleValue("min-radius", ruleNode));
/*  672 */     forestRule.setMaxRadius(getNodeDoubleValue("max-radius", ruleNode));
/*  673 */     forestRule.setRandomDensityMin(getNodeIntegerValue("random-density-min", ruleNode));
/*  674 */     forestRule.setRandomDensityMax(getNodeIntegerValue("random-density-max", ruleNode));
/*  677 */     if (forestRule.getDensity() == -1.0D)
/*  678 */       forestRule.setDensity(255); 
/*      */   }
/*      */   
/*      */   private void parseAutogenString(Element ruleNode) throws Exception {
/*  684 */     AutogenStringRule autogenStringRule = new AutogenStringRule(this);
/*  686 */     this.rules.add(autogenStringRule);
/*  688 */     parseRegionFilters(autogenStringRule, ruleNode);
/*  689 */     getCommonValues(autogenStringRule, ruleNode);
/*  692 */     NodeList filterNode = ruleNode.getElementsByTagName("filter");
/*  693 */     if (filterNode == null || filterNode.getLength() != 1)
/*  694 */       throw new Exception("Rule does not contain a filter section"); 
/*  697 */     getFilter(autogenStringRule, filterNode);
/*  699 */     NodeList forestsListNode = ruleNode.getElementsByTagName("strings");
/*  700 */     if (forestsListNode == null || forestsListNode.getLength() == 0)
/*  701 */       throw new Exception("No forests found for forest rule"); 
/*  704 */     Node forestsNode = forestsListNode.item(0);
/*  705 */     NodeList forestNodes = ((Element)forestsNode).getElementsByTagName("string");
/*  707 */     autogenStringRule.setPassThrough(getNodeBooleanValue("pass-through", ruleNode));
/*  708 */     autogenStringRule.setLineWidth(getNodeDoubleValue("line-width", ruleNode));
/*  709 */     autogenStringRule.setCollisionTest(getNodeBooleanValue("collision-test", ruleNode));
/*  710 */     autogenStringRule.setClipToArea(getNodeBooleanValue("clip-to-area", ruleNode));
/*  714 */     if (forestNodes == null || forestNodes.getLength() == 0)
/*  715 */       throw new Exception("No strings found for autogen string rule"); 
/*  718 */     for (int x = 0; x < forestNodes.getLength(); x++) {
/*  719 */       Node forestNode = forestNodes.item(x);
/*  720 */       String pathName = forestNode.getFirstChild().getNodeValue().trim();
/*  721 */       autogenStringRule.addString(this.objectDefinitionStore.pushObject(ObjectDefinitionStore.ObjectType.FACADE, pathName, null), forestNode.getAttributes().getNamedItem("hotspot"), forestNode.getAttributes().getNamedItem("region"), forestNode.getAttributes().getNamedItem("min-distance"), forestNode.getAttributes().getNamedItem("max-distance"), forestNode.getAttributes().getNamedItem("sizeX"), forestNode.getAttributes().getNamedItem("sizeY"));
/*      */     } 
/*      */   }
/*      */   
/*      */   private void parseRandomRule(Element ruleNode) throws Exception {
/*  735 */     RandomRule randomRule = new RandomRule(this);
/*  737 */     parseRegionFilters(randomRule, ruleNode);
/*  738 */     getCommonValues(randomRule, ruleNode);
/*  741 */     this.rules.add(randomRule);
/*  743 */     NodeList filterNode = ruleNode.getElementsByTagName("filter");
/*  744 */     if (filterNode == null || filterNode.getLength() != 1)
/*  745 */       throw new Exception("Rule does not contain a filter section"); 
/*  750 */     getFilter(randomRule, filterNode);
/*  752 */     NodeList objectsListNode = ruleNode.getElementsByTagName("objects");
/*  753 */     if (objectsListNode == null || objectsListNode.getLength() == 0)
/*  754 */       throw new Exception("No objects found for random rule"); 
/*  757 */     NodeList areaDensities = ruleNode.getElementsByTagName("area-density");
/*  758 */     if (areaDensities != null && areaDensities.getLength() > 0)
/*  759 */       for (int i = 0; i < areaDensities.getLength(); i++) {
/*  760 */         RandomRule.Density density = new RandomRule.Density();
/*      */         try {
/*  762 */           density.min = (new Integer(areaDensities.item(i).getAttributes().getNamedItem("min").getNodeValue())).intValue();
/*  763 */         } catch (Exception e) {
/*  764 */           density.min = -1;
/*      */         } 
/*      */         try {
/*  767 */           density.max = (new Integer(areaDensities.item(i).getAttributes().getNamedItem("max").getNodeValue())).intValue();
/*  768 */         } catch (Exception e) {
/*  769 */           density.max = -1;
/*      */         } 
/*  771 */         density.density = (new Double(areaDensities.item(i).getFirstChild().getNodeValue())).doubleValue();
/*  772 */         randomRule.getAreaDensities().add(density);
/*      */       }  
/*  776 */     Node objectsNode = objectsListNode.item(0);
/*  777 */     NodeList objectNodes = ((Element)objectsNode).getElementsByTagName("object");
/*  779 */     if (objectNodes == null || objectNodes.getLength() == 0)
/*  780 */       throw new Exception("No objects found for random rule"); 
/*  783 */     for (int x = 0; x < objectNodes.getLength(); x++) {
/*  784 */       Node objectNode = objectNodes.item(x);
/*  785 */       String pathName = objectNode.getFirstChild().getNodeValue().trim();
/*  786 */       randomRule.getObjectFiles().add(Integer.valueOf(this.objectDefinitionStore.pushObject(ObjectDefinitionStore.ObjectType.OBJECT, pathName, null)));
/*      */     } 
/*  790 */     randomRule.setDensity(getNodeDoubleValue("density", ruleNode));
/*  791 */     String angle = getNodeStringValue("angle", ruleNode);
/*  792 */     if ("determine".equals(angle.trim().toLowerCase())) {
/*  793 */       randomRule.setDetermineAngle(true);
/*      */     } else {
/*  795 */       randomRule.setDetermineAngle(false);
/*  796 */       randomRule.setAngle(getNodeIntegerValue("angle", ruleNode));
/*      */     } 
/*  798 */     randomRule.setCircular(getNodeStringValue("circular", ruleNode).equals("true"));
/*  799 */     randomRule.setMinRadius(getNodeDoubleValue("min-radius", ruleNode));
/*  800 */     randomRule.setMaxRadius(getNodeDoubleValue("max-radius", ruleNode));
/*  802 */     randomRule.setMaxarea(getNodeDoubleValue("max-area", ruleNode));
/*  803 */     randomRule.setMinarea(getNodeDoubleValue("min-area", ruleNode));
/*  804 */     randomRule.setCount(getNodeIntegerValue("count", ruleNode));
/*  807 */     randomRule.setPassThrough(getNodeBooleanValue("pass-through", ruleNode));
/*  808 */     randomRule.setCollisionTest(getNodeBooleanValue("collision-test", ruleNode));
/*  809 */     randomRule.setForestsOnEmpty(getNodeBooleanValue("forests-on-empty", ruleNode));
/*  811 */     NodeList forestsListNode = ruleNode.getElementsByTagName("forests");
/*  812 */     if (forestsListNode != null || forestsListNode.getLength() > 0) {
/*  815 */       Node forestsNode = forestsListNode.item(0);
/*  816 */       NodeList forestNodes = ruleNode.getElementsByTagName("forest");
/*  817 */       if (forestNodes != null || forestNodes.getLength() > 0)
/*  819 */         for (int i = 0; i < forestNodes.getLength(); i++) {
/*  820 */           Node forestNode = forestNodes.item(i);
/*  821 */           String pathName = forestNode.getFirstChild().getNodeValue().trim();
/*  822 */           randomRule.getForestFiles().add(Integer.valueOf(this.objectDefinitionStore.pushObject(ObjectDefinitionStore.ObjectType.FOREST, pathName, null)));
/*      */         }  
/*      */     } 
/*      */   }
/*      */   
/*      */   private void parseAreaTracker(Element ruleNode) throws Exception {
/*  831 */     AreaTrackerRule areaTrackerRule = new AreaTrackerRule(this);
/*  833 */     parseRegionFilters(areaTrackerRule, ruleNode);
/*  834 */     getCommonValues(areaTrackerRule, ruleNode);
/*  836 */     this.rules.add(areaTrackerRule);
/*  838 */     NodeList filterNode = ruleNode.getElementsByTagName("filter");
/*  839 */     if (filterNode == null || filterNode.getLength() != 1)
/*  840 */       throw new Exception("Rule does not contain a filter section"); 
/*  843 */     getFilter(areaTrackerRule, filterNode);
/*  845 */     areaTrackerRule.setIdentifier(getNodeStringValue("identifier", ruleNode));
/*  846 */     areaTrackerRule.setPassThrough(getNodeBooleanValue("pass-through", ruleNode));
/*      */   }
/*      */   
/*      */   private void parseNodeTracker(Element ruleNode) throws Exception {
/*  852 */     NodeTrackerRule nodeTrackerRule = new NodeTrackerRule(this);
/*  854 */     parseRegionFilters(nodeTrackerRule, ruleNode);
/*  855 */     getCommonValues(nodeTrackerRule, ruleNode);
/*  857 */     this.rules.add(nodeTrackerRule);
/*  859 */     NodeList filterNode = ruleNode.getElementsByTagName("filter");
/*  860 */     if (filterNode == null || filterNode.getLength() != 1)
/*  861 */       throw new Exception("Rule does not contain a filter section"); 
/*  864 */     getFilter(nodeTrackerRule, filterNode);
/*  866 */     nodeTrackerRule.setIdentifier(getNodeStringValue("identifier", ruleNode));
/*  867 */     nodeTrackerRule.setPassThrough(getNodeBooleanValue("pass-through", ruleNode));
/*      */   }
/*      */   
/*      */   private void parseWayTracker(Element ruleNode) throws Exception {
/*  873 */     WayTrackerRule wayTrackerRule = new WayTrackerRule(this);
/*  875 */     parseRegionFilters(wayTrackerRule, ruleNode);
/*  876 */     getCommonValues(wayTrackerRule, ruleNode);
/*  879 */     this.rules.add(wayTrackerRule);
/*  881 */     NodeList filterNode = ruleNode.getElementsByTagName("filter");
/*  882 */     if (filterNode == null || filterNode.getLength() != 1)
/*  883 */       throw new Exception("Rule does not contain a filter section"); 
/*  886 */     getFilter(wayTrackerRule, filterNode);
/*  888 */     wayTrackerRule.setIdentifier(getNodeStringValue("identifier", ruleNode));
/*  889 */     wayTrackerRule.setPassThrough(getNodeBooleanValue("pass-through", ruleNode));
/*      */   }
/*      */   
/*      */   private void parseLineRule(Element ruleNode) throws Exception {
/*  895 */     LineRule lineRule = new LineRule(this);
/*  897 */     parseRegionFilters(lineRule, ruleNode);
/*  898 */     getCommonValues(lineRule, ruleNode);
/*  901 */     this.rules.add(lineRule);
/*  903 */     NodeList filterNode = ruleNode.getElementsByTagName("filter");
/*  904 */     if (filterNode == null || filterNode.getLength() != 1)
/*  905 */       throw new Exception("Rule does not contain a filter section"); 
/*  908 */     getFilter(lineRule, filterNode);
/*  910 */     NodeList objectsListNode = ruleNode.getElementsByTagName("objects");
/*  911 */     if (objectsListNode == null || objectsListNode.getLength() == 0)
/*  912 */       throw new Exception("No objects found for line rule"); 
/*  915 */     Node objectsNode = objectsListNode.item(0);
/*  916 */     NodeList objectNodes = ((Element)objectsNode).getElementsByTagName("object");
/*  918 */     if (objectNodes == null || objectNodes.getLength() == 0)
/*  919 */       throw new Exception("No objects found for line rule"); 
/*  922 */     for (int x = 0; x < objectNodes.getLength(); x++) {
/*  923 */       Node objectNode = objectNodes.item(x);
/*  924 */       String pathName = objectNode.getFirstChild().getNodeValue().trim();
/*  926 */       Integer id = Integer.valueOf(this.objectDefinitionStore.pushObject(ObjectDefinitionStore.ObjectType.OBJECT, pathName, null));
/*  928 */       lineRule.getObjectFiles().add(id);
/*  930 */       LineRule.LineRuleObject lineRuleObject = new LineRule.LineRuleObject();
/*  931 */       lineRuleObject.objectId = id;
/*  932 */       DeferredElementImpl dei = (DeferredElementImpl)objectNode;
/*  933 */       if (dei.hasAttribute("width"))
/*  934 */         lineRuleObject.width = Double.valueOf(this.numberFormat.parse(dei.getAttribute("width")).doubleValue()); 
/*  936 */       if (dei.hasAttribute("group"))
/*  937 */         lineRuleObject.groupId = Integer.valueOf(this.numberFormat.parse(dei.getAttribute("group")).intValue()); 
/*  939 */       if (dei.hasAttribute("spacing"))
/*  940 */         lineRuleObject.spacing = Double.valueOf(this.numberFormat.parse(dei.getAttribute("spacing")).doubleValue()); 
/*  943 */       lineRule.addLineRuleObject(lineRuleObject);
/*      */     } 
/*  948 */     lineRule.setPassThrough(getNodeBooleanValue("pass-through", ruleNode));
/*  949 */     lineRule.setMinDensity(getNodeDoubleValue("min-density", ruleNode));
/*  950 */     lineRule.setMaxDensity(getNodeDoubleValue("max-density", ruleNode));
/*  951 */     lineRule.setOffset(getNodeFloatValue("offset", ruleNode));
/*  952 */     lineRule.setCollisionTest(getNodeBooleanValue("collision-test", ruleNode));
/*  953 */     lineRule.setTrack(getNodeBooleanValue("track", ruleNode));
/*  954 */     lineRule.setSpacingDivision(getNodeBooleanValue("spacing-division", ruleNode));
/*  955 */     lineRule.setCount(getNodeIntegerValue("count", ruleNode));
/*  956 */     lineRule.setClipToArea(getNodeBooleanValue("clip-to-area", ruleNode));
/*  961 */     float angle = getNodeFloatValue("angle", ruleNode);
/*  962 */     if (angle != -1.0F)
/*  963 */       lineRule.setAngle(angle); 
/*  966 */     String offsetAngles = getNodeStringValue("offset-angles", ruleNode);
/*  967 */     if (offsetAngles != null && offsetAngles.length() > 0) {
/*  968 */       String[] angles = offsetAngles.split(";");
/*  969 */       for (String item : angles)
/*  970 */         lineRule.getOffsetAngles().add(Float.valueOf(this.numberFormat.parse(item.trim()).floatValue())); 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void parseVectorRule(Element ruleNode) throws Exception {
/*  978 */     VectorRule vectorRule = new VectorRule(this);
/*  980 */     parseRegionFilters((Rule)vectorRule, ruleNode);
/*  981 */     getCommonValues((Rule)vectorRule, ruleNode);
/*  984 */     this.rules.add(vectorRule);
/*  985 */     this.networkRules.add(vectorRule);
/*  987 */     NodeList filterNode = ruleNode.getElementsByTagName("filter");
/*  988 */     if (filterNode == null || filterNode.getLength() != 1)
/*  989 */       throw new Exception("Rule does not contain a filter section"); 
/*  991 */     getFilter((Rule)vectorRule, filterNode);
/*  993 */     NodeList nodeFilterNode = ruleNode.getElementsByTagName("node-filter");
/*  994 */     if (nodeFilterNode != null && nodeFilterNode.getLength() > 0) {
/*  995 */       Node filter = nodeFilterNode.item(0);
/*  997 */       FilterList f = new FilterList();
/*  998 */       vectorRule.setNodeFilterList(f);
/*  999 */       f.setFilterType("key-value");
/* 1000 */       f.setFilter(filter.getFirstChild().getNodeValue().trim());
/*      */       try {
/* 1002 */         f.parseFilter();
/* 1003 */       } catch (Exception e) {
/* 1004 */         throw new RuntimeException("Filter is invalid " + e.getMessage());
/*      */       } 
/*      */     } 
/* 1009 */     vectorRule.setNodesAsPoints(getNodeBooleanValue("nodes-as-points", ruleNode));
/* 1010 */     vectorRule.setPassThrough(getNodeBooleanValue("pass-through", ruleNode));
/* 1011 */     vectorRule.setVectorFile(getNodeStringValue("vector-file", ruleNode));
/* 1012 */     vectorRule.setDefNumber(getNodeIntegerValue("vector-definition", ruleNode));
/*      */   }
/*      */   
/*      */   private void parseFacadeRule(Element ruleNode) throws Exception {
/* 1017 */     FacadeRule facadeRule = new FacadeRule(this);
/* 1019 */     parseRegionFilters(facadeRule, ruleNode);
/* 1020 */     getCommonValues(facadeRule, ruleNode);
/* 1023 */     this.rules.add(facadeRule);
/* 1025 */     NodeList filterNode = ruleNode.getElementsByTagName("filter");
/* 1026 */     if (filterNode == null || filterNode.getLength() != 1)
/* 1027 */       throw new Exception("Rule does not contain a filter section"); 
/* 1030 */     getFilter(facadeRule, filterNode);
/* 1032 */     NodeList facadesListNode = ruleNode.getElementsByTagName("facades");
/* 1034 */     NodeList minHeightNodes = ruleNode.getElementsByTagName("min-random-height");
/* 1035 */     NodeList maxHeightNodes = ruleNode.getElementsByTagName("max-random-height");
/* 1036 */     NodeList minAreaNodes = ruleNode.getElementsByTagName("min-area");
/* 1037 */     NodeList maxAreaNodes = ruleNode.getElementsByTagName("max-area");
/* 1038 */     NodeList areaType = ruleNode.getElementsByTagName("area-type");
/* 1039 */     NodeList simple = ruleNode.getElementsByTagName("simplify");
/* 1040 */     if (ruleNode.getElementsByTagName("allow-unclosed").getLength() > 0)
/* 1041 */       facadeRule.setAllowUnclosed(getNodeBooleanValue("allow-unclosed", ruleNode)); 
/* 1044 */     if (ruleNode.getElementsByTagName("building").getLength() > 0)
/* 1045 */       facadeRule.setBuilding(Boolean.valueOf(getNodeBooleanValue("building", ruleNode))); 
/* 1048 */     if (ruleNode.getElementsByTagName("skip-last-point").getLength() > 0)
/* 1049 */       facadeRule.setSkipLastPoint(Boolean.valueOf(getNodeBooleanValue("skip-last-point", ruleNode))); 
/* 1052 */     facadeRule.setPassThrough(getNodeBooleanValue("pass-through", ruleNode));
/* 1055 */     String facadeList = getNodeStringValue("facade-set", ruleNode);
/* 1056 */     if (!facadeList.isEmpty()) {
/* 1057 */       FacadeItemList objectList = new FacadeItemList(this);
/* 1058 */       boolean value = objectList.parseCsv(facadeList, this.worldModelsLocation);
/* 1059 */       if (!value) {
/* 1060 */         log.info("Not usable facades found in " + facadeList + ". Rule ignored");
/*      */         return;
/*      */       } 
/* 1063 */       facadeRule.setFacadeItemList(objectList);
/* 1064 */     } else if (facadesListNode != null && facadesListNode.getLength() > 0) {
/* 1066 */       Node facadesNode = facadesListNode.item(0);
/* 1067 */       NodeList facadeNodes = ((Element)facadesNode).getElementsByTagName("facade");
/* 1070 */       if (facadeNodes == null || facadeNodes.getLength() == 0)
/* 1071 */         throw new Exception("No facades found for facade rule"); 
/* 1074 */       for (int x = 0; x < facadeNodes.getLength(); x++) {
/* 1075 */         Node forestNode = facadeNodes.item(x);
/* 1076 */         String pathName = forestNode.getFirstChild().getNodeValue().trim();
/* 1077 */         facadeRule.getFacadeFiles().add(Integer.valueOf(this.objectDefinitionStore.pushObject(ObjectDefinitionStore.ObjectType.FACADE, pathName, null)));
/*      */       } 
/*      */     } 
/* 1081 */     if (areaType != null && areaType.getLength() == 1) {
/* 1082 */       Node area = areaType.item(0);
/* 1083 */       facadeRule.setAreaType(area.getFirstChild().getNodeValue().toLowerCase().trim());
/*      */     } 
/* 1086 */     if (minHeightNodes != null && minHeightNodes.getLength() == 1) {
/* 1087 */       Node minNode = minHeightNodes.item(0);
/* 1088 */       if (minNode != null)
/*      */         try {
/* 1090 */           facadeRule.setMinRandomHeight((new Integer(minNode.getFirstChild().getNodeValue().trim())).intValue());
/* 1091 */         } catch (Exception e) {
/* 1092 */           throw new Exception("min-random-height is invalid in facade rule");
/*      */         }  
/*      */     } 
/* 1097 */     if (minAreaNodes != null && minAreaNodes.getLength() == 1) {
/* 1098 */       Node minNode = minAreaNodes.item(0);
/* 1099 */       if (minNode != null)
/*      */         try {
/* 1101 */           facadeRule.setMinArea((new Integer(minNode.getFirstChild().getNodeValue().trim())).intValue());
/* 1102 */         } catch (Exception e) {
/* 1103 */           throw new Exception("Min-area is invalid in facade rule");
/*      */         }  
/*      */     } 
/* 1108 */     if (maxHeightNodes != null && maxHeightNodes.getLength() == 1) {
/* 1109 */       Node maxNode = maxHeightNodes.item(0);
/* 1110 */       if (maxNode != null)
/*      */         try {
/* 1112 */           facadeRule.setMaxRandomHeight((new Integer(maxNode.getFirstChild().getNodeValue().trim())).intValue());
/* 1113 */         } catch (Exception e) {
/* 1114 */           throw new Exception("max-random-height is invalid in facade rule");
/*      */         }  
/*      */     } 
/* 1119 */     if (maxAreaNodes != null && maxAreaNodes.getLength() == 1) {
/* 1120 */       Node maxNode = maxAreaNodes.item(0);
/* 1121 */       if (maxNode != null)
/*      */         try {
/* 1123 */           facadeRule.setMaxArea((new Integer(maxNode.getFirstChild().getNodeValue().trim())).intValue());
/* 1124 */         } catch (Exception e) {
/* 1125 */           throw new Exception("Max-area is invalid in facade rule");
/*      */         }  
/*      */     } 
/* 1130 */     if (simple != null && simple.getLength() == 1) {
/* 1131 */       Node simpleNode = simple.item(0);
/* 1132 */       if (simpleNode != null)
/* 1133 */         facadeRule.setSimple(simpleNode.getFirstChild().getNodeValue().equals("true")); 
/*      */     } 
/* 1137 */     facadeRule.setCircular(getNodeStringValue("circular", ruleNode).equals("true"));
/* 1138 */     facadeRule.setMinRadius(getNodeDoubleValue("min-radius", ruleNode));
/* 1139 */     facadeRule.setMaxRadius(getNodeDoubleValue("max-radius", ruleNode));
/* 1140 */     facadeRule.setRoofHeight(getNodeFloatValue("roof-height", ruleNode));
/* 1143 */     NodeList distributions = ruleNode.getElementsByTagName("distribution");
/* 1144 */     if (distributions != null && distributions.getLength() > 0) {
/* 1145 */       for (int x = 0; x < distributions.getLength(); x++) {
/* 1146 */         String identifier = distributions.item(x).getAttributes().getNamedItem("identifier").getNodeValue().trim().toLowerCase();
/* 1147 */         Integer percentage = new Integer(distributions.item(x).getAttributes().getNamedItem("percentage").getNodeValue().trim().toLowerCase());
/* 1148 */         FacadeRule.FacadeRuleDistribution facadeRuleDistribution = new FacadeRule.FacadeRuleDistribution();
/* 1149 */         facadeRuleDistribution.setIdentifier(identifier);
/* 1150 */         facadeRuleDistribution.setPercentage(percentage);
/* 1151 */         facadeRule.getFacadeRuleDistributions().add(facadeRuleDistribution);
/*      */       } 
/* 1153 */       facadeRule.sortDistributions();
/*      */     } 
/* 1157 */     if (facadeRule.getFacadeFiles().size() == 0 && facadeRule.getFacadeItemList() == null) {
/* 1158 */       if (facadeRule.getAreaType() == null)
/* 1159 */         throw new Exception("No area type specified for rule without facades"); 
/* 1161 */       if (!facadeRule.getAreaType().equals("residential") && !facadeRule.getAreaType().equals("sloped-residential") && !facadeRule.getAreaType().equals("random") && !facadeRule.getAreaType().equals("industrial") && !facadeRule.getAreaType().equals("commercial"))
/* 1166 */         throw new Exception("Area Type " + facadeRule.getAreaType() + " is not a valid area type"); 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void parsePolygonRule(Element ruleNode) throws Exception {
/* 1173 */     PolygonRule facadeRule = new PolygonRule(this);
/* 1175 */     parseRegionFilters(facadeRule, ruleNode);
/* 1176 */     getCommonValues(facadeRule, ruleNode);
/* 1179 */     this.rules.add(facadeRule);
/* 1181 */     NodeList filterNode = ruleNode.getElementsByTagName("filter");
/* 1182 */     if (filterNode == null || filterNode.getLength() != 1)
/* 1183 */       throw new Exception("Rule does not contain a filter section"); 
/* 1186 */     getFilter(facadeRule, filterNode);
/* 1188 */     NodeList facadesListNode = ruleNode.getElementsByTagName("polygons");
/* 1190 */     NodeList minAreaNodes = ruleNode.getElementsByTagName("min-area");
/* 1191 */     NodeList maxAreaNodes = ruleNode.getElementsByTagName("max-area");
/* 1192 */     NodeList areaType = ruleNode.getElementsByTagName("area-type");
/* 1193 */     NodeList simple = ruleNode.getElementsByTagName("simplify");
/* 1194 */     if (ruleNode.getElementsByTagName("allow-unclosed").getLength() > 0)
/* 1195 */       facadeRule.setAllowUnclosed(getNodeBooleanValue("allow-unclosed", ruleNode)); 
/* 1200 */     if (ruleNode.getElementsByTagName("skip-last-point").getLength() > 0)
/* 1201 */       facadeRule.setSkipLastPoint(Boolean.valueOf(getNodeBooleanValue("skip-last-point", ruleNode))); 
/* 1204 */     facadeRule.setPassThrough(getNodeBooleanValue("pass-through", ruleNode));
/* 1206 */     if (facadesListNode != null && facadesListNode.getLength() > 0) {
/* 1208 */       Node facadesNode = facadesListNode.item(0);
/* 1209 */       NodeList facadeNodes = ((Element)facadesNode).getElementsByTagName("polygon");
/* 1212 */       if (facadeNodes == null || facadeNodes.getLength() == 0)
/* 1213 */         throw new Exception("No polygons found for polygon rule"); 
/* 1216 */       for (int x = 0; x < facadeNodes.getLength(); x++) {
/* 1217 */         Node forestNode = facadeNodes.item(x);
/* 1218 */         String pathName = forestNode.getFirstChild().getNodeValue().trim();
/* 1219 */         facadeRule.getPolygonFiles().add(Integer.valueOf(this.objectDefinitionStore.pushObject(ObjectDefinitionStore.ObjectType.FACADE, pathName, null)));
/*      */       } 
/*      */     } 
/* 1223 */     if (areaType != null && areaType.getLength() == 1) {
/* 1224 */       Node area = areaType.item(0);
/* 1225 */       facadeRule.setAreaType(area.getFirstChild().getNodeValue().toLowerCase().trim());
/*      */     } 
/* 1229 */     if (minAreaNodes != null && minAreaNodes.getLength() == 1) {
/* 1230 */       Node minNode = minAreaNodes.item(0);
/* 1231 */       if (minNode != null)
/*      */         try {
/* 1233 */           facadeRule.setMinArea((new Integer(minNode.getFirstChild().getNodeValue().trim())).intValue());
/* 1234 */         } catch (Exception e) {
/* 1235 */           throw new Exception("Min-area is invalid in facade rule");
/*      */         }  
/*      */     } 
/* 1242 */     if (maxAreaNodes != null && maxAreaNodes.getLength() == 1) {
/* 1243 */       Node maxNode = maxAreaNodes.item(0);
/* 1244 */       if (maxNode != null)
/*      */         try {
/* 1246 */           facadeRule.setMaxArea((new Integer(maxNode.getFirstChild().getNodeValue().trim())).intValue());
/* 1247 */         } catch (Exception e) {
/* 1248 */           throw new Exception("Max-area is invalid in facade rule");
/*      */         }  
/*      */     } 
/* 1253 */     if (simple != null && simple.getLength() == 1) {
/* 1254 */       Node simpleNode = simple.item(0);
/* 1255 */       if (simpleNode != null)
/* 1256 */         facadeRule.setSimple(simpleNode.getFirstChild().getNodeValue().equals("true")); 
/*      */     } 
/* 1260 */     facadeRule.setCircular(getNodeStringValue("circular", ruleNode).equals("true"));
/* 1261 */     facadeRule.setMinRadius(getNodeDoubleValue("min-radius", ruleNode));
/* 1262 */     facadeRule.setMaxRadius(getNodeDoubleValue("max-radius", ruleNode));
/*      */   }
/*      */   
/*      */   private void parseSinkRule(Element ruleNode) throws Exception {
/* 1272 */     SinkRule sinkRule = new SinkRule(this);
/* 1274 */     parseRegionFilters(sinkRule, ruleNode);
/* 1275 */     getCommonValues(sinkRule, ruleNode);
/* 1278 */     this.rules.add(sinkRule);
/* 1280 */     NodeList filterNode = ruleNode.getElementsByTagName("filter");
/* 1281 */     if (filterNode == null || filterNode.getLength() != 1)
/* 1282 */       throw new Exception("Rule does not contain a filter section"); 
/* 1285 */     getFilter(sinkRule, filterNode);
/* 1288 */     double maxArea = getNodeDoubleValue("max-area", ruleNode);
/* 1289 */     double minArea = getNodeDoubleValue("min-area", ruleNode);
/* 1292 */     sinkRule.setMaxArea(maxArea);
/* 1293 */     sinkRule.setMinArea(minArea);
/*      */   }
/*      */   
/*      */   public ObjectDefinitionStore.ObjectDefinition getPolygonDefinition(int definitionNumber) {
/* 1299 */     return this.objectDefinitionStore.getPolygonDefinitions().get(definitionNumber);
/*      */   }
/*      */   
/*      */   public ObjectDefinitionStore.ObjectDefinition getObjectDefinition(int definitionNumber) {
/* 1303 */     return this.objectDefinitionStore.getObjectDefinitions().get(definitionNumber);
/*      */   }
/*      */   
/*      */   private void parseObjectRule(Element ruleNode) throws Exception {
/* 1307 */     ObjectRule objectRule = new ObjectRule(this);
/* 1310 */     NodeList filterNode = ruleNode.getElementsByTagName("filter");
/* 1311 */     if (filterNode == null || filterNode.getLength() != 1)
/* 1312 */       throw new Exception("Rule does not contain a filter section"); 
/* 1315 */     parseRegionFilters(objectRule, ruleNode);
/* 1316 */     getCommonValues(objectRule, ruleNode);
/* 1319 */     getFilter(objectRule, filterNode);
/* 1321 */     NodeList minAreaNodes = ruleNode.getElementsByTagName("min-area");
/* 1322 */     NodeList maxAreaNodes = ruleNode.getElementsByTagName("max-area");
/* 1323 */     NodeList minHeightNodes = ruleNode.getElementsByTagName("min-height");
/* 1324 */     NodeList maxHeightNodes = ruleNode.getElementsByTagName("max-height");
/* 1326 */     int minHeight = getNodeIntegerValue("min-random-height", ruleNode);
/* 1327 */     int maxHeight = getNodeIntegerValue("max-random-height", ruleNode);
/* 1328 */     objectRule.setMinRandomHeight((minHeight != -1) ? minHeight : 3);
/* 1329 */     objectRule.setMaxRandomHeight((maxHeight != -1) ? maxHeight : 3);
/* 1333 */     NodeList minXNodes = ruleNode.getElementsByTagName("min-area-x");
/* 1334 */     NodeList maxXNodes = ruleNode.getElementsByTagName("max-area-x");
/* 1335 */     NodeList minYNodes = ruleNode.getElementsByTagName("min-area-y");
/* 1336 */     NodeList maxYNodes = ruleNode.getElementsByTagName("max-area-y");
/* 1338 */     objectRule.setCircular(getNodeStringValue("circular", ruleNode).equals("true"));
/* 1339 */     objectRule.setMinRadius(getNodeDoubleValue("min-radius", ruleNode));
/* 1340 */     objectRule.setMaxRadius(getNodeDoubleValue("max-radius", ruleNode));
/* 1341 */     objectRule.setTolerance(getNodeDoubleValue("tolerance", ruleNode));
/* 1342 */     objectRule.setClosedOverride(getNodeBooleanValue("closed-override", ruleNode));
/* 1343 */     objectRule.setStreetFacing(getNodeBooleanValue("street-facing", ruleNode));
/* 1344 */     objectRule.setRejectMultiPolygon(getNodeBooleanValue("reject-multi-polygon", ruleNode));
/* 1346 */     if (objectRule.getAdaptiveHeightPolicy() == null)
/* 1347 */       objectRule.setDefaultHeight(getNodeDoubleValue("default-height", ruleNode)); 
/* 1350 */     String tagRules = getNodeStringValue("tag-rules", ruleNode);
/* 1351 */     if (tagRules != null && tagRules.length() > 0)
/* 1352 */       objectRule.readTagRules(tagRules); 
/* 1355 */     objectRule.setAngleFromWay(getNodeStringValue("angle-from-way", ruleNode));
/* 1356 */     objectRule.setAdjustHeight(getNodeBooleanValue("adjust-height", ruleNode));
/* 1357 */     objectRule.setAngle(Float.valueOf(getNodeFloatValue("angle", ruleNode)));
/* 1358 */     objectRule.setAngleFromRoad(getNodeBooleanValue("angle-from-road", ruleNode));
/* 1361 */     NodeList angleFromWay = ruleNode.getElementsByTagName("angle-from-way");
/* 1362 */     if (angleFromWay != null && angleFromWay.getLength() == 1) {
/* 1363 */       Node baseAngle = angleFromWay.item(0).getAttributes().getNamedItem("base");
/* 1364 */       if (baseAngle != null)
/* 1365 */         objectRule.setAngleFromWayBase(Float.valueOf(this.numberFormat.parse(baseAngle.getNodeValue().toLowerCase().trim()).floatValue())); 
/*      */     } 
/* 1370 */     boolean restrictHeight = getNodeBooleanValue("restrict-height", ruleNode);
/* 1371 */     boolean restrictShape = getNodeBooleanValue("restrict-shape", ruleNode);
/* 1373 */     objectRule.setRestrictShape(restrictShape);
/* 1376 */     String bestFit = getNodeStringValue("best-fit", ruleNode);
/* 1377 */     if (!bestFit.isEmpty()) {
/* 1378 */       ObjectList objectList = new ObjectList(this, getNodeStringValue("regions", ruleNode), restrictHeight);
/* 1379 */       objectList.setTolerance(objectRule.getTolerance());
/* 1380 */       boolean value = objectList.parseCsv(bestFit, this.worldModelsLocation);
/* 1381 */       if (!value) {
/* 1382 */         log.info("Not usable models found in " + bestFit + ". Rule ignored");
/*      */         return;
/*      */       } 
/* 1385 */       objectRule.setBestFitList(objectList);
/* 1386 */       if (objectRule.getDefaultHeight() > 0.0D)
/* 1387 */         objectList.setDefaultHeight((float)objectRule.getDefaultHeight()); 
/*      */     } else {
/* 1390 */       NodeList objectsListNode = ruleNode.getElementsByTagName("objects");
/* 1393 */       Node objectsNode = objectsListNode.item(0);
/* 1394 */       NodeList objectNodes = ((Element)objectsNode).getElementsByTagName("object");
/* 1397 */       if (objectNodes == null || objectNodes.getLength() == 0)
/* 1398 */         throw new Exception("No objects found for object rule"); 
/* 1401 */       for (int x = 0; x < objectNodes.getLength(); x++) {
/* 1402 */         Node objectNode = objectNodes.item(x);
/* 1403 */         ObjectDefinitionStore.RotationPoint rotationPoint = ObjectDefinitionStore.RotationPoint.CENTER;
/* 1404 */         if (objectNode.hasAttributes() && objectNode.getAttributes().getNamedItem("origin") != null) {
/* 1405 */           String p = objectNode.getAttributes().getNamedItem("origin").getNodeValue();
/* 1406 */           if ("bottom-center".equals(p))
/* 1407 */             rotationPoint = ObjectDefinitionStore.RotationPoint.BOTTOM_CENTER; 
/* 1409 */           if ("right-middle".equals(p))
/* 1410 */             rotationPoint = ObjectDefinitionStore.RotationPoint.RIGHT_MIDDLE; 
/*      */         } 
/* 1413 */         String pathName = objectNode.getFirstChild().getNodeValue().trim();
/* 1414 */         int definition = this.objectDefinitionStore.pushObject(ObjectDefinitionStore.ObjectType.OBJECT, pathName, null);
/* 1415 */         objectRule.getObjectFiles().add(Integer.valueOf(definition));
/* 1416 */         getObjectDefinition(definition).setRotationPoint(rotationPoint);
/*      */       } 
/*      */     } 
/* 1424 */     if (minAreaNodes != null && minAreaNodes.getLength() == 1) {
/* 1425 */       Node minNode = minAreaNodes.item(0);
/* 1426 */       if (minNode != null)
/*      */         try {
/* 1428 */           String area = minNode.getFirstChild().getNodeValue().trim();
/* 1429 */           if (area.indexOf("x") > 0) {
/* 1430 */             double minX = this.numberFormat.parse(area.split("x")[0]).doubleValue();
/* 1431 */             double minY = this.numberFormat.parse(area.split("x")[1]).doubleValue();
/* 1432 */             objectRule.setMinAreaX(minX);
/* 1433 */             objectRule.setMinAreaY(minY);
/*      */           } else {
/* 1435 */             objectRule.setMinArea(this.numberFormat.parse(minNode.getFirstChild().getNodeValue().trim()).doubleValue());
/*      */           } 
/* 1437 */         } catch (Exception e) {
/* 1438 */           throw new Exception("Min-area is invalid in object rule");
/*      */         }  
/*      */     } 
/* 1445 */     if (maxAreaNodes != null && maxAreaNodes.getLength() == 1) {
/* 1446 */       Node maxNode = maxAreaNodes.item(0);
/* 1447 */       if (maxNode != null)
/*      */         try {
/* 1449 */           String area = maxNode.getFirstChild().getNodeValue().trim();
/* 1450 */           if (area.indexOf("x") > 0) {
/* 1451 */             double maxX = this.numberFormat.parse(area.split("x")[0]).doubleValue();
/* 1452 */             double maxY = this.numberFormat.parse(area.split("x")[1]).doubleValue();
/* 1453 */             objectRule.setMaxAreaX(maxX);
/* 1454 */             objectRule.setMaxAreaY(maxY);
/*      */           } else {
/* 1456 */             objectRule.setMaxArea(this.numberFormat.parse(maxNode.getFirstChild().getNodeValue().trim()).doubleValue());
/*      */           } 
/* 1458 */         } catch (Exception e) {
/* 1459 */           throw new Exception("Max-area is invalid in object rule");
/*      */         }  
/*      */     } 
/* 1464 */     if (minXNodes != null && minXNodes.getLength() == 1) {
/* 1465 */       Node node = minXNodes.item(0);
/* 1466 */       if (node != null)
/*      */         try {
/* 1468 */           objectRule.setMinAreaX(this.numberFormat.parse(node.getFirstChild().getNodeValue().trim()).doubleValue());
/* 1469 */         } catch (Exception e) {
/* 1470 */           throw new Exception("min-area-x is invalid in object rule");
/*      */         }  
/*      */     } 
/* 1475 */     if (maxXNodes != null && maxXNodes.getLength() == 1) {
/* 1476 */       Node node = maxXNodes.item(0);
/* 1477 */       if (node != null)
/*      */         try {
/* 1479 */           objectRule.setMaxAreaX(this.numberFormat.parse(node.getFirstChild().getNodeValue().trim()).doubleValue());
/* 1480 */         } catch (Exception e) {
/* 1481 */           throw new Exception("max-area-x is invalid in object rule");
/*      */         }  
/*      */     } 
/* 1486 */     if (minYNodes != null && minYNodes.getLength() == 1) {
/* 1487 */       Node node = minYNodes.item(0);
/* 1488 */       if (node != null)
/*      */         try {
/* 1490 */           objectRule.setMinAreaY(this.numberFormat.parse(node.getFirstChild().getNodeValue().trim()).doubleValue());
/* 1491 */         } catch (Exception e) {
/* 1492 */           throw new Exception("min-area-y is invalid in object rule");
/*      */         }  
/*      */     } 
/* 1497 */     if (maxYNodes != null && maxYNodes.getLength() == 1) {
/* 1498 */       Node node = maxYNodes.item(0);
/* 1499 */       if (node != null)
/*      */         try {
/* 1501 */           objectRule.setMaxAreaY(this.numberFormat.parse(node.getFirstChild().getNodeValue().trim()).doubleValue());
/* 1502 */         } catch (Exception e) {
/* 1503 */           throw new Exception("max-area-y is invalid in object rule");
/*      */         }  
/*      */     } 
/* 1508 */     this.rules.add(objectRule);
/* 1509 */     objectRule.calculateAreas();
/*      */   }
/*      */   
/*      */   private void parseRegionFilters(Rule rule, Element ruleNode) {
/* 1515 */     NodeList filterNode = ruleNode.getElementsByTagName("region");
/* 1516 */     if (filterNode != null && filterNode.getLength() > 0)
/* 1517 */       for (int x = 0; x < filterNode.getLength(); x++) {
/* 1518 */         Node item = filterNode.item(x);
/* 1519 */         String region = item.getFirstChild().getNodeValue().trim();
/* 1520 */         if (region != null && region.startsWith("!")) {
/* 1521 */           rule.getNotRegions().add(region.replaceAll("!", ""));
/*      */         } else {
/* 1523 */           rule.getRegions().add(region);
/*      */         } 
/*      */       }  
/*      */   }
/*      */   
/*      */   private void getCommonValues(Rule rule, Element ruleNode) {
/*      */     try {
/* 1534 */       NodeList seedNode = ruleNode.getElementsByTagName("random-execute");
/* 1535 */       if (seedNode != null && seedNode.getLength() == 1) {
/* 1536 */         Node item = seedNode.item(0);
/* 1538 */         String identifier = item.getAttributes().getNamedItem("seed").getNodeValue().toLowerCase().trim();
/* 1539 */         int min = (new Integer(item.getAttributes().getNamedItem("min").getNodeValue().toLowerCase().toLowerCase())).intValue();
/* 1540 */         int max = (new Integer(item.getAttributes().getNamedItem("max").getNodeValue().toLowerCase().toLowerCase())).intValue();
/* 1542 */         rule.setMaxSeed(max);
/* 1543 */         rule.setMinSeed(min);
/* 1544 */         rule.setRandomSeedIdentifier(identifier);
/* 1545 */         if (this.randomSeeds.get(identifier) == null)
/* 1546 */           throw new Exception("No random seed with identifier " + identifier + " found"); 
/*      */       } 
/* 1549 */     } catch (Exception e) {
/* 1550 */       log.error("Error parsing rule", e);
/*      */     } 
/* 1553 */     rule.setMinHeight(Float.valueOf(getNodeFloatValue("min-height", ruleNode)));
/* 1554 */     if (rule.getMinHeight().floatValue() == -1.0F)
/* 1555 */       rule.setMinHeight(null); 
/* 1557 */     rule.setMaxHeight(Float.valueOf(getNodeFloatValue("max-height", ruleNode)));
/* 1558 */     if (rule.getMaxHeight().floatValue() == -1.0F)
/* 1559 */       rule.setMaxHeight(null); 
/* 1561 */     rule.setSet(getNodeStringValue("set", ruleNode));
/* 1562 */     if (rule.getSet() == null || rule.getSet().isEmpty())
/* 1563 */       rule.setSet(null); 
/* 1565 */     rule.setNotset(getNodeStringValue("notset", ruleNode));
/* 1566 */     rule.setNoClip(getNodeBooleanValue("no-clip", ruleNode));
/* 1568 */     NodeList densityNode = ruleNode.getElementsByTagName("required-level");
/* 1569 */     if (densityNode != null) {
/* 1570 */       rule.setRequiredLevel(getNodeIntegerValue("required-level", ruleNode));
/* 1571 */       if (rule.getRequiredLevel() == -1)
/* 1572 */         rule.setRequiredLevel(6); 
/*      */     } 
/* 1576 */     rule.setBuffer(getNodeFloatValue("buffer", ruleNode));
/* 1577 */     rule.setBufferRandomise(getNodeFloatValue("buffer-randomise", ruleNode));
/* 1578 */     rule.setSimplify(getNodeFloatValue("simplify", ruleNode));
/* 1579 */     rule.setClosed(getNodeBooleanNullValue("closed", ruleNode));
/* 1580 */     rule.setSkipAirport(getNodeBooleanValue("skip-airport", ruleNode));
/* 1582 */     String defaultHeight = getNodeStringValue("default-height", ruleNode);
/* 1583 */     if (defaultHeight != null && defaultHeight.length() > 0)
/* 1584 */       for (AdaptiveHeight item : this.heightPolicies) {
/* 1585 */         if (item.getIdentifier().equals(defaultHeight.trim()))
/* 1586 */           rule.setAdaptiveHeightPolicy(item); 
/*      */       }  
/*      */   }
/*      */   
/*      */   private double getNodeDoubleValue(String nodeName, Node parentNode) {
/* 1595 */     NodeList nodeList = ((Element)parentNode).getElementsByTagName(nodeName);
/* 1596 */     if (nodeList == null || nodeList.getLength() == 0)
/* 1597 */       return -1.0D; 
/* 1600 */     Node node = nodeList.item(0);
/* 1601 */     if (node != null)
/*      */       try {
/* 1603 */         String value = node.getFirstChild().getNodeValue().trim();
/* 1604 */         return this.numberFormat.parse(value).doubleValue();
/* 1605 */       } catch (Exception e) {
/* 1606 */         throw new RuntimeException(node.getFirstChild().getNodeValue().trim() + " is invalid for tag " + nodeName);
/*      */       }  
/* 1610 */     return -1.0D;
/*      */   }
/*      */   
/*      */   private float getNodeFloatValue(String nodeName, Node parentNode) {
/* 1615 */     NodeList nodeList = ((Element)parentNode).getElementsByTagName(nodeName);
/* 1616 */     if (nodeList == null || nodeList.getLength() == 0)
/* 1617 */       return -1.0F; 
/* 1620 */     Node node = nodeList.item(0);
/* 1621 */     if (node != null)
/*      */       try {
/* 1623 */         String value = node.getFirstChild().getNodeValue().trim();
/* 1624 */         return this.numberFormat.parse(value).floatValue();
/* 1625 */       } catch (Exception e) {
/* 1626 */         throw new RuntimeException(node.getFirstChild().getNodeValue().trim() + " is invalid for tag " + nodeName);
/*      */       }  
/* 1630 */     return -1.0F;
/*      */   }
/*      */   
/*      */   private int getNodeIntegerValue(String nodeName, Node parentNode) {
/* 1635 */     NodeList nodeList = ((Element)parentNode).getElementsByTagName(nodeName);
/* 1636 */     if (nodeList == null || nodeList.getLength() == 0)
/* 1637 */       return -1; 
/* 1640 */     Node node = nodeList.item(0);
/* 1641 */     if (node != null)
/*      */       try {
/* 1643 */         String value = node.getFirstChild().getNodeValue().trim();
/* 1644 */         return Integer.valueOf(value).intValue();
/* 1645 */       } catch (Exception e) {
/* 1646 */         throw new RuntimeException(node.getFirstChild().getNodeValue().trim() + " is invalid for tag " + nodeName);
/*      */       }  
/* 1650 */     return -1;
/*      */   }
/*      */   
/*      */   private String getNodeStringValue(String nodeName, Node parentNode) {
/* 1655 */     NodeList nodeList = ((Element)parentNode).getElementsByTagName(nodeName);
/* 1656 */     if (nodeList == null || nodeList.getLength() == 0)
/* 1657 */       return ""; 
/* 1660 */     Node node = nodeList.item(0);
/* 1661 */     if (node != null)
/*      */       try {
/* 1663 */         String value = node.getFirstChild().getNodeValue().trim();
/* 1664 */         return value;
/* 1665 */       } catch (Exception e) {
/* 1666 */         throw new RuntimeException(node.getFirstChild().getNodeValue().trim() + " is invalid for tag " + nodeName);
/*      */       }  
/* 1669 */     return "";
/*      */   }
/*      */   
/*      */   private boolean getNodeBooleanValue(String nodeName, Node parentNode) {
/* 1673 */     NodeList nodeList = ((Element)parentNode).getElementsByTagName(nodeName);
/* 1674 */     if (nodeList == null || nodeList.getLength() == 0)
/* 1675 */       return false; 
/* 1678 */     Node node = nodeList.item(0);
/* 1679 */     if (node != null)
/*      */       try {
/* 1681 */         String value = node.getFirstChild().getNodeValue().trim();
/* 1682 */         return (value.toLowerCase().trim().equals("true") || value.toLowerCase().trim().equals("yes"));
/* 1683 */       } catch (Exception e) {
/* 1684 */         throw new RuntimeException(node.getFirstChild().getNodeValue().trim() + " is invalid for tag " + nodeName);
/*      */       }  
/* 1687 */     return false;
/*      */   }
/*      */   
/*      */   private Boolean getNodeBooleanNullValue(String nodeName, Node parentNode) {
/* 1691 */     NodeList nodeList = ((Element)parentNode).getElementsByTagName(nodeName);
/* 1692 */     if (nodeList == null || nodeList.getLength() == 0)
/* 1693 */       return null; 
/* 1696 */     Node node = nodeList.item(0);
/* 1697 */     if (node != null)
/*      */       try {
/* 1699 */         String value = node.getFirstChild().getNodeValue().trim();
/* 1700 */         if (value.toLowerCase().trim().equals("true") || value.toLowerCase().trim().equals("yes"))
/* 1701 */           return Boolean.valueOf(true); 
/* 1703 */         if (value.toLowerCase().trim().equals("false") || value.toLowerCase().trim().equals("no"))
/* 1704 */           return Boolean.valueOf(false); 
/* 1706 */       } catch (Exception e) {
/* 1707 */         throw new RuntimeException(node.getFirstChild().getNodeValue().trim() + " is invalid for tag " + nodeName);
/*      */       }  
/* 1710 */     return null;
/*      */   }
/*      */   
/*      */   private void getFilter(Rule objectRule, NodeList filterNode) {
/* 1714 */     Node filter = filterNode.item(0);
/* 1715 */     Node filterType = filter.getAttributes().getNamedItem("type");
/* 1716 */     if (filterType == null)
/* 1717 */       throw new RuntimeException("No type defined for filter"); 
/* 1719 */     FilterList f = new FilterList();
/* 1720 */     objectRule.setFilter(f);
/* 1721 */     f.setFilterType(filterType.getNodeValue());
/* 1722 */     f.setFilter(filter.getFirstChild().getNodeValue().trim());
/*      */     try {
/* 1724 */       f.parseFilter();
/* 1725 */     } catch (Exception e) {
/* 1726 */       throw new RuntimeException("Filter is invalid " + e.getMessage());
/*      */     } 
/*      */   }
/*      */   
/*      */   public ObjectDefinitionStore getObjectDefinitionStore() {
/* 1731 */     return this.objectDefinitionStore;
/*      */   }
/*      */   
/*      */   public ArrayList<Rule> getRules() {
/* 1735 */     return this.rules;
/*      */   }
/*      */   
/*      */   public List<AcceptingRule> acceptingRulesAndFilters(HashMap<String, String> tags) {
/* 1748 */     if (tags.size() == 0)
/* 1749 */       return new ArrayList<>(); 
/* 1752 */     int hashCode = tags.hashCode();
/* 1754 */     if (this.acceptingRuleCache.containsKey(hashCode))
/* 1755 */       return (List<AcceptingRule>)this.acceptingRuleCache.get(hashCode); 
/* 1760 */     List<AcceptingRule> acceptingRules = new ArrayList<>();
/* 1761 */     for (Rule rule : this.rules) {
/* 1762 */       List<Byte> acceptingFilter = rule.acceptsTag(tags);
/* 1763 */       if (acceptingFilter != null)
/* 1765 */         for (Byte item : acceptingFilter)
/* 1766 */           acceptingRules.add(new AcceptingRule(Short.valueOf(rule.getRuleNumber()), item));  
/*      */     } 
/* 1772 */     this.acceptingRuleCache.put(hashCode, acceptingRules);
/* 1773 */     return acceptingRules;
/*      */   }
/*      */   
/*      */   public Rule getRule(int ruleNumber) {
/* 1778 */     if (this.ruleIndexes.containsKey(Integer.valueOf(ruleNumber)))
/* 1779 */       return (Rule)this.ruleIndexes.get(Integer.valueOf(ruleNumber)); 
/* 1783 */     for (Rule item : this.rules) {
/* 1784 */       if (item.ruleNumber == ruleNumber) {
/* 1785 */         this.ruleIndexes.put(Integer.valueOf(ruleNumber), item);
/* 1786 */         return item;
/*      */       } 
/*      */     } 
/* 1789 */     log.error("Request for rule that doesn't exist");
/* 1790 */     return null;
/*      */   }
/*      */   
/*      */   public FacadeSet loadFacadeSet(String filePath, String identifier, String basename) throws Exception {
/*      */     FacadeSet facadeSet;
/* 1798 */     File facadeSetFile = new File(filePath);
/*      */     try {
/* 1800 */       JAXBContext jc = JAXBContext.newInstance(FacadeSet.class.getPackage().getName());
/* 1802 */       Unmarshaller u = jc.createUnmarshaller();
/* 1803 */       facadeSet = (FacadeSet)u.unmarshal(facadeSetFile);
/* 1804 */     } catch (JAXBException e) {
/* 1805 */       throw new Exception("Error loading facadeSet " + filePath + "\n" + e.getCause().getMessage());
/*      */     } 
/* 1808 */     facadeSet.setIdentifier(identifier);
/* 1809 */     for (Facade item : facadeSet.getFacades()) {
/* 1810 */       item.setFile(basename + File.separator + item.getFile());
/* 1811 */       item.setIdentifier(identifier);
/*      */     } 
/* 1814 */     return facadeSet;
/*      */   }
/*      */   
/*      */   public void getAcceptingRules(OSMPolygon shape, List<AcceptingRule> acceptingRules, Set<String> possibleRegions, Rule.Delegate delegate, Rule.OnAccept onAccept) {
/* 1836 */     seedRandomSeeds();
/* 1840 */     for (AcceptingRule item : acceptingRules) {
/* 1841 */       Rule rule = getRule(item.ruleNumber.shortValue());
/* 1842 */       if (rule.isPassThrough() && rule.acceptsShape(item.filterNumber, shape, possibleRegions, delegate, true))
/* 1843 */         onAccept.addItem(rule); 
/*      */     } 
/* 1848 */     for (AcceptingRule item : acceptingRules) {
/* 1849 */       Rule rule = getRule(item.ruleNumber.shortValue());
/* 1850 */       if (!rule.isPassThrough() && rule.acceptsShape(item.filterNumber, shape, possibleRegions, delegate, true) && 
/* 1851 */         onAccept.addItem(rule))
/*      */         return; 
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean willAcceptKeyValue(String key, String value) {
/* 1864 */     if (this.usedKeys == null)
/* 1865 */       setupKeyList(); 
/* 1867 */     KeyCache cache = this.usedKeys.get(key);
/* 1868 */     if (cache == null)
/* 1869 */       return false; 
/* 1871 */     if (cache.acceptsAll)
/* 1872 */       return true; 
/* 1874 */     return cache.values.contains(value);
/*      */   }
/*      */   
/*      */   private void setupKeyList() {
/* 1880 */     this.usedKeys = new HashMap<>();
/* 1881 */     for (Rule item : this.rules) {
/* 1882 */       FilterList filters = item.getFilter();
/* 1883 */       readTagsForKeyList(filters);
/* 1884 */       if (item instanceof VectorRule && ((VectorRule)item).getNodeFilterList() != null)
/* 1885 */         readTagsForKeyList(((VectorRule)item).getNodeFilterList()); 
/*      */     } 
/* 1888 */     for (Hotspot item : this.hotspots) {
/* 1889 */       FilterList filters = item.getFilterList();
/* 1890 */       readTagsForKeyList(filters);
/*      */     } 
/* 1895 */     this.usedKeys.put("building:min_levels", new KeyCache(true));
/* 1896 */     this.usedKeys.put("building:levels", new KeyCache(true));
/* 1897 */     this.usedKeys.put("building:colour", new KeyCache(true));
/* 1898 */     this.usedKeys.put("building:color", new KeyCache(true));
/* 1899 */     this.usedKeys.put("building:height", new KeyCache(true));
/* 1900 */     this.usedKeys.put("building:part", new KeyCache(true));
/* 1901 */     this.usedKeys.put("roof:color", new KeyCache(true));
/* 1902 */     this.usedKeys.put("roof:colour", new KeyCache(true));
/* 1903 */     this.usedKeys.put("roof:material", new KeyCache(true));
/* 1904 */     this.usedKeys.put("roof:shape", new KeyCache(true));
/* 1905 */     this.usedKeys.put("roof:levels", new KeyCache(true));
/* 1906 */     this.usedKeys.put("roof:height", new KeyCache(true));
/* 1907 */     this.usedKeys.put("building:material", new KeyCache(true));
/* 1908 */     this.usedKeys.put("type", new KeyCache("multipolygon"));
/* 1909 */     this.usedKeys.put("height", new KeyCache(true));
/* 1910 */     this.usedKeys.put("min_height", new KeyCache(true));
/* 1913 */     if (this.generateRoads || this.clipRoads) {
/* 1914 */       this.usedKeys.put("oneway", new KeyCache(true));
/* 1915 */       this.usedKeys.put("sidewalk", new KeyCache(true));
/* 1916 */       this.usedKeys.put("tunnel", new KeyCache(true));
/* 1917 */       this.usedKeys.put("bridge", new KeyCache(true));
/* 1918 */       this.usedKeys.put("lanes", new KeyCache(true));
/*      */     } 
/*      */   }
/*      */   
/*      */   private void readTagsForKeyList(FilterList filters) {
/* 1925 */     for (Map<String, List<Filter>> tag : filters.getTagSets()) {
/* 1926 */       for (Map.Entry<String, List<Filter>> keyValue : tag.entrySet()) {
/* 1927 */         KeyCache cache = this.usedKeys.get(keyValue.getKey());
/* 1928 */         if (cache == null) {
/* 1929 */           cache = new KeyCache(false);
/* 1930 */           this.usedKeys.put(keyValue.getKey(), cache);
/*      */         } 
/* 1932 */         List<Filter> filterList = keyValue.getValue();
/* 1933 */         for (Filter filter : filterList) {
/* 1934 */           if (filter instanceof EqualsFilter) {
/* 1935 */             if (((EqualsFilter)filter).getFilter().equals("*")) {
/* 1936 */               cache.acceptsAll = true;
/*      */               continue;
/*      */             } 
/* 1938 */             cache.values.add(((EqualsFilter)filter).getFilter());
/*      */             continue;
/*      */           } 
/* 1941 */           cache.acceptsAll = true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public int getFacadeLod() {
/* 1950 */     return this.facadeLod;
/*      */   }
/*      */   
/*      */   public synchronized Integer getLodForArea(ObjectDefinitionStore.ObjectDefinition polygonDefinition, double area, float height, boolean multipolygon) {
/* 1960 */     int score = 0;
/* 1961 */     Integer def = null;
/* 1962 */     if (area <= 2.0D)
/* 1963 */       score = 2; 
/* 1965 */     if (area > 2.0D && area <= 10.0D)
/* 1966 */       score = 4; 
/* 1968 */     if (area >= 10.0D && area <= 14.0D)
/* 1969 */       score = 5; 
/* 1971 */     if (area >= 14.0D && area <= 20.0D)
/* 1972 */       score = 7; 
/* 1974 */     if (area >= 20.0D && area <= 50.0D)
/* 1975 */       score = 12; 
/* 1977 */     if (area >= 50.0D && area <= 60.0D)
/* 1978 */       score = 13; 
/* 1980 */     if (area > 60.0D)
/* 1981 */       score = 14; 
/* 1983 */     if (height > 45.0F)
/* 1984 */       score = 25; 
/* 1987 */     if (score != 0) {
/* 1989 */       String facadeName = polygonDefinition.getFacade().getFile();
/* 1990 */       facadeName = facadeName.substring(0, facadeName.length() - 4);
/* 1991 */       def = this.objectDefinitionStore.getFacade(facadeName + "_L" + score + (multipolygon ? "_G" : "") + ".fac");
/* 1993 */       return def;
/*      */     } 
/* 1996 */     return null;
/*      */   }
/*      */   
/*      */   public String getXplaneSceneryFolder() {
/* 2000 */     return this.xplaneSceneryFolder;
/*      */   }
/*      */   
/*      */   public void setXplaneSceneryFolder(String xplaneSceneryFolder) {
/* 2004 */     this.xplaneSceneryFolder = xplaneSceneryFolder;
/*      */   }
/*      */   
/*      */   public boolean isValidateSceneryFolder() {
/* 2008 */     return this.validateSceneryFolder;
/*      */   }
/*      */   
/*      */   public void setValidateSceneryFolder(boolean validateSceneryFolder) {
/* 2012 */     this.validateSceneryFolder = validateSceneryFolder;
/*      */   }
/*      */   
/*      */   public boolean validateObjectsExists() throws IOException {
/* 2016 */     boolean valid = true;
/* 2017 */     PathValidator pathValidator = new PathValidator(new File(this.xplaneSceneryFolder), new File("./resources"));
/* 2018 */     for (ObjectDefinitionStore.ObjectDefinition item : this.objectDefinitionStore.getObjectDefinitions()) {
/* 2019 */       if (!pathValidator.checkFileExists(item.getPath())) {
/* 2020 */         valid = false;
/* 2021 */         log.error("File " + item.getPath() + " cannot be found in X-Plane or Resources.");
/*      */       } 
/*      */     } 
/* 2025 */     for (ObjectDefinitionStore.ObjectDefinition item : this.objectDefinitionStore.getPolygonDefinitions()) {
/* 2026 */       if (item.getFacade() == null && !pathValidator.checkFileExists(item.getPath())) {
/* 2027 */         valid = false;
/* 2028 */         log.error("File " + item.getPath() + " cannot be found in X-Plane or Resources.");
/*      */       } 
/*      */     } 
/* 2032 */     return valid;
/*      */   }
/*      */   
/*      */   public List<RegionProvider> getRegionProviders() {
/* 2038 */     return this.regionProviders;
/*      */   }
/*      */   
/*      */   public Set<String> getRegions() {
/* 2049 */     if (this.regionCodes == null) {
/* 2050 */       this.regionCodes = new HashSet<>();
/* 2051 */       for (Rule item : this.rules) {
/* 2052 */         if (item instanceof ObjectRule && (
/* 2053 */           (ObjectRule)item).getBestFitList() != null)
/* 2054 */           for (ObjectList.ObjectListEntry code : ((ObjectRule)item).getBestFitList().getObjects())
/* 2055 */             this.regionCodes.addAll(code.getRegions());  
/* 2059 */         this.regionCodes.addAll(item.getRegions());
/* 2060 */         this.regionCodes.addAll(item.getNotRegions());
/*      */       } 
/*      */     } 
/* 2063 */     return this.regionCodes;
/*      */   }
/*      */   
/*      */   public boolean isEnabledRegions() {
/* 2067 */     return this.enabledRegions;
/*      */   }
/*      */   
/*      */   public void setEnabledRegions(boolean enabledRegions) {
/* 2071 */     this.enabledRegions = enabledRegions;
/*      */   }
/*      */   
/*      */   public boolean isSmartExclusions() {
/* 2075 */     return this.smartExclusions;
/*      */   }
/*      */   
/*      */   public void setSmartExclusions(boolean smartExclusions) {
/* 2079 */     this.smartExclusions = smartExclusions;
/*      */   }
/*      */   
/*      */   public static String createOutputFolder(String osmFilePath) {
/* 2083 */     String outputFolder = osmFilePath.replaceAll(".osm", "");
/* 2084 */     outputFolder = outputFolder.replaceAll(".pbf", "");
/* 2085 */     outputFolder = outputFolder.replaceAll(FilenameUtils.getExtension(osmFilePath), "");
/* 2086 */     if (outputFolder.equals(osmFilePath))
/* 2087 */       throw new RuntimeException("Error, invalid filename for output folder and input file " + outputFolder); 
/* 2089 */     return outputFolder;
/*      */   }
/*      */   
/*      */   public boolean isToOSM() {
/* 2093 */     return this.toOSM;
/*      */   }
/*      */   
/*      */   public void setToOSM(boolean toOSM) {
/* 2097 */     this.toOSM = toOSM;
/*      */   }
/*      */   
/*      */   public boolean isCreateBuildingParts() {
/* 2101 */     return this.createBuildingParts;
/*      */   }
/*      */   
/*      */   public void setCreateBuildingParts(boolean createBuildingParts) {
/* 2105 */     this.createBuildingParts = createBuildingParts;
/*      */   }
/*      */   
/*      */   public boolean isGenerateFacades() {
/* 2109 */     return this.generateFacades;
/*      */   }
/*      */   
/*      */   public void setGenerateFacades(boolean generateFacades) {
/* 2113 */     this.generateFacades = generateFacades;
/*      */   }
/*      */   
/*      */   public boolean isCreateFacades() {
/* 2117 */     return this.createFacades;
/*      */   }
/*      */   
/*      */   public void setCreateFacades(boolean createFacades) {
/* 2121 */     this.createFacades = createFacades;
/*      */   }
/*      */   
/*      */   public boolean isGenerateRoofWallColors() {
/* 2125 */     return this.generateRoofWallColors;
/*      */   }
/*      */   
/*      */   public void setGenerateRoofWallColors(boolean generateRoofWallColors) {
/* 2129 */     this.generateRoofWallColors = generateRoofWallColors;
/*      */   }
/*      */   
/*      */   public boolean isCopyToXPlane() {
/* 2133 */     return this.copyToXPlane;
/*      */   }
/*      */   
/*      */   public void setCopyToXPlane(boolean copyToXPlane) {
/* 2137 */     this.copyToXPlane = copyToXPlane;
/*      */   }
/*      */   
/*      */   public Set<String> getRegionsAtPoint(Point2D centroid, Set<String> regionsFromDsf) {
/* 2152 */     Set<String> regions = new HashSet<>();
/* 2153 */     if (centroid == null || centroid.isEmpty())
/* 2154 */       return regions; 
/* 2156 */     for (RegionProvider item : this.regionProviders)
/* 2157 */       regions.addAll(item.getRegionsAtPoint(centroid.x(), centroid.y(), regionsFromDsf)); 
/* 2159 */     return regions;
/*      */   }
/*      */   
/*      */   public boolean isCompressTextures() {
/* 2164 */     return this.compressTextures;
/*      */   }
/*      */   
/*      */   public void setCompressTextures(boolean compressTextures) {
/* 2168 */     this.compressTextures = compressTextures;
/*      */   }
/*      */   
/*      */   public Integer getBuildingExclusionRange() {
/* 2172 */     return this.buildingExclusionRange;
/*      */   }
/*      */   
/*      */   public void setBuildingExclusionRange(Integer buildingExclusionRange) {
/* 2176 */     this.buildingExclusionRange = buildingExclusionRange;
/*      */   }
/*      */   
/*      */   public Integer getForestExclusionRange() {
/* 2180 */     return this.forestExclusionRange;
/*      */   }
/*      */   
/*      */   public void setForestExclusionRange(Integer forestExclusionRange) {
/* 2184 */     this.forestExclusionRange = forestExclusionRange;
/*      */   }
/*      */   
/*      */   public boolean isUseLightingForColours() {
/* 2188 */     return this.useLightingForColours;
/*      */   }
/*      */   
/*      */   public void setUseLightingForColours(boolean useLightingForColours) {
/* 2192 */     this.useLightingForColours = useLightingForColours;
/*      */   }
/*      */   
/*      */   public int getRandomPlacementLimit() {
/* 2196 */     return this.randomPlacementLimit;
/*      */   }
/*      */   
/*      */   public void setRandomPlacementLimit(int randomPlacementLimit) {
/* 2200 */     this.randomPlacementLimit = randomPlacementLimit;
/*      */   }
/*      */   
/*      */   public Set<String> getFacadeDirectories() {
/* 2204 */     return this.facadeDirectories;
/*      */   }
/*      */   
/*      */   public boolean isCreateForestExclusions() {
/* 2208 */     return this.createForestExclusions;
/*      */   }
/*      */   
/*      */   public void setCreateForestExclusions(boolean createForestExclusions) {
/* 2212 */     this.createForestExclusions = createForestExclusions;
/*      */   }
/*      */   
/*      */   public boolean isCreateObjectExclusions() {
/* 2216 */     return this.createObjectExclusions;
/*      */   }
/*      */   
/*      */   public void setCreateObjectExclusions(boolean createObjectExclusions) {
/* 2220 */     this.createObjectExclusions = createObjectExclusions;
/*      */   }
/*      */   
/*      */   public boolean isCreateFacadeExclusions() {
/* 2224 */     return this.createFacadeExclusions;
/*      */   }
/*      */   
/*      */   public void setCreateFacadeExclusions(boolean createFacadeExclusions) {
/* 2228 */     this.createFacadeExclusions = createFacadeExclusions;
/*      */   }
/*      */   
/*      */   public boolean isEnableDebugImage() {
/* 2232 */     return this.enableDebugImage;
/*      */   }
/*      */   
/*      */   public void setEnableDebugImage(boolean enableDebugImage) {
/* 2236 */     this.enableDebugImage = enableDebugImage;
/*      */   }
/*      */   
/*      */   public boolean isCreateNetworkExclusions() {
/* 2241 */     return this.createNetworkExclusions;
/*      */   }
/*      */   
/*      */   public void setCreateNetworkExclusions(boolean createNetworkExclusions) {
/* 2245 */     this.createNetworkExclusions = createNetworkExclusions;
/*      */   }
/*      */   
/*      */   public RandomSeed getRandomSeed(String randomSeedIdentifier) {
/* 2249 */     return this.randomSeeds.get(randomSeedIdentifier);
/*      */   }
/*      */   
/*      */   public void seedRandomSeeds() {
/* 2253 */     for (RandomSeed item : this.randomSeeds.values())
/* 2254 */       item.seed(); 
/*      */   }
/*      */   
/*      */   public boolean isGenerateRoads() {
/* 2259 */     return this.generateRoads;
/*      */   }
/*      */   
/*      */   public Set<String> getVectorFiles() {
/* 2268 */     Set<String> roads = new HashSet<>();
/* 2269 */     for (NetworkItem item : this.networkRules) {
/* 2270 */       if (item instanceof VectorRule && (
/* 2271 */         (VectorRule)item).getVectorFile() != null && ((VectorRule)item).getVectorFile().length() > 0)
/* 2272 */         roads.add(((VectorRule)item).getVectorFile()); 
/*      */     } 
/* 2277 */     return roads;
/*      */   }
/*      */   
/*      */   public boolean isClipAirports() {
/* 2281 */     return this.clipAirports;
/*      */   }
/*      */   
/*      */   public void setClipAirports(boolean clipAirports) {
/* 2285 */     this.clipAirports = clipAirports;
/*      */   }
/*      */   
/*      */   public boolean isClipRoads() {
/* 2289 */     return this.clipRoads;
/*      */   }
/*      */   
/*      */   public void setClipRoads(boolean clipRoads) {
/* 2293 */     this.clipRoads = clipRoads;
/*      */   }
/*      */   
/*      */   public boolean isDebugging() {
/* 2297 */     return this.debugging;
/*      */   }
/*      */   
/*      */   public void setDebugging(boolean debugging) {
/* 2301 */     this.debugging = debugging;
/*      */   }
/*      */   
/*      */   public int getBuildingsPerGrid() {
/* 2305 */     return this.buildingsPerGrid;
/*      */   }
/*      */   
/*      */   public void setBuildingsPerGrid(int buildingsPerGrid) {
/* 2309 */     this.buildingsPerGrid = buildingsPerGrid;
/*      */   }
/*      */   
/*      */   public String getTemporaryFilePath() {
/* 2313 */     return this.temporaryFilePath;
/*      */   }
/*      */   
/*      */   public void setTemporaryFilePath(String temporaryFilePath) {
/* 2317 */     this.temporaryFilePath = temporaryFilePath;
/*      */   }
/*      */   
/*      */   public boolean hasIncludeLib(String lib) {
/* 2321 */     if (this.libDefinitionMap.containsKey(lib))
/* 2322 */       return ((LibDefinition)this.libDefinitionMap.get(lib)).isEnabled(); 
/* 2324 */     return true;
/*      */   }
/*      */   
/*      */   public List<NetworkItem> initialiseNetworkRules() {
/* 2328 */     List<NetworkItem> networks = new ArrayList<>();
/* 2329 */     for (NetworkItem item : this.networkRules)
/* 2330 */       networks.add(item.initialise()); 
/* 2333 */     return networks;
/*      */   }
/*      */   
/*      */   public int getProcessorThreads() {
/* 2337 */     return this.processorThreads;
/*      */   }
/*      */   
/*      */   public void setProcessorThreads(int processorThreads) {
/* 2341 */     this.processorThreads = processorThreads;
/*      */   }
/*      */   
/*      */   public List<AdaptiveHeight> getHeightPolicies() {
/* 2345 */     return this.heightPolicies;
/*      */   }
/*      */   
/*      */   public void setPolyFile(String polyFile) {
/* 2349 */     if (polyFile != null && polyFile.length() == 0) {
/* 2350 */       this.polyFile = null;
/*      */       return;
/*      */     } 
/* 2353 */     this.polyFile = polyFile;
/*      */   }
/*      */   
/*      */   public String getPolyFile() {
/* 2357 */     return this.polyFile;
/*      */   }
/*      */   
/*      */   public void readPolyFile() {
/* 2361 */     log.info("Reading Poly File " + getPolyFile());
/* 2362 */     File file = new File(getPolyFile());
/* 2363 */     PolygonFileReader fileReader = new PolygonFileReader(file);
/* 2364 */     this.regionPoly = fileReader.loadPolygon();
/*      */   }
/*      */   
/*      */   public Geometry getRegionPoly() {
/* 2368 */     return this.regionPoly;
/*      */   }
/*      */   
/*      */   public void setRegionPoly(Geometry regionPoly) {
/* 2372 */     this.regionPoly = regionPoly;
/*      */   }
/*      */   
/*      */   public float getFacadeHeightLimit() {
/* 2376 */     return this.facadeHeightLimit;
/*      */   }
/*      */   
/*      */   public String getWorldModelsLocation() {
/* 2380 */     return this.worldModelsLocation;
/*      */   }
/*      */   
/*      */   public void setWorldModelsLocation(String worldModelsLocation) {
/* 2384 */     this.worldModelsLocation = worldModelsLocation;
/*      */   }
/*      */   
/*      */   public HashSet<String> getSetList() {
/* 2388 */     return this.setList;
/*      */   }
/*      */   
/*      */   public boolean variableIsSet(String set) {
/* 2392 */     return this.setList.contains(set);
/*      */   }
/*      */   
/*      */   public boolean isCreateBeachExclusions() {
/* 2396 */     return this.createBeachExclusions;
/*      */   }
/*      */   
/*      */   public void setCreateBeachExclusions(boolean createBeachExclusions) {
/* 2400 */     this.createBeachExclusions = createBeachExclusions;
/*      */   }
/*      */   
/*      */   public List<Hotspot> getHotspots() {
/* 2404 */     return this.hotspots;
/*      */   }
/*      */   
/*      */   public void logHotspot(Hotspot hotspot, HashMap<String, String> tags, double lon, double lat) {
/* 2408 */     if (this.hotspotTree == null)
/* 2409 */       this.hotspotTree = new STRtree(); 
/* 2411 */     HotspotEntry spot = new HotspotEntry(tags.get(hotspot.getDebug()), hotspot.getIdentifier(), lon, lat);
/* 2413 */     if (tags.containsKey("population"))
/*      */       try {
/* 2415 */         spot.setPopulation((new Integer(tags.get("population"))).intValue());
/* 2416 */       } catch (Exception e) {} 
/* 2421 */     this.hotspotTree.insert((new GeometryFactory()).createPoint(new Coordinate(lon, lat)).getEnvelopeInternal(), spot);
/*      */   }
/*      */   
/*      */   public Set<String> getEmptyRegions() {
/* 2425 */     return this.emptyRegions;
/*      */   }
/*      */   
/*      */   public class HotspotHit {
/*      */     final double distance;
/*      */     
/*      */     final HotspotEntry hotspotEntry;
/*      */     
/*      */     public HotspotHit(double distance, HotspotEntry hotspotEntry) {
/* 2435 */       this.distance = distance;
/* 2436 */       this.hotspotEntry = hotspotEntry;
/*      */     }
/*      */   }
/*      */   
/*      */   public List<HotspotHit> getHotspots(Envelope envelope, double lon, double lat, int limit) {
/* 2441 */     if (this.hotspotTree == null)
/* 2442 */       return null; 
/* 2444 */     List<HotspotEntry> nearest = this.hotspotTree.query(envelope);
/* 2445 */     if (nearest == null || nearest.size() == 0)
/* 2446 */       return null; 
/* 2449 */     ArrayList<HotspotHit> hotspotHits = new ArrayList<>();
/* 2452 */     for (HotspotEntry hotspotEntry : nearest) {
/* 2453 */       double distance = GeomUtils.distanceInMetersFastMath(new LineSegment2D(lon, lat, hotspotEntry.getLongitude(), hotspotEntry.getLatitude()));
/* 2454 */       if (distance <= limit)
/* 2455 */         hotspotHits.add(new HotspotHit(distance, hotspotEntry)); 
/*      */     } 
/* 2460 */     Collections.sort(hotspotHits, new Comparator<HotspotHit>() {
/*      */           public int compare(GeneratorStore.HotspotHit o1, GeneratorStore.HotspotHit o2) {
/* 2463 */             return (new Double(o1.distance)).compareTo(Double.valueOf(o2.distance));
/*      */           }
/*      */         });
/* 2467 */     return hotspotHits;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\GeneratorStore.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */