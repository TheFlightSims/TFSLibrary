/*     */ package com.world2xplane.GUI;
/*     */ 
/*     */ import com.world2xplane.Rules.GeneratorStore;
/*     */ import java.io.File;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.event.Event;
/*     */ import javafx.event.EventHandler;
/*     */ import javafx.geometry.Insets;
/*     */ import javafx.geometry.Pos;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.Parent;
/*     */ import javafx.scene.Scene;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.CheckBox;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.control.Tab;
/*     */ import javafx.scene.control.TabPane;
/*     */ import javafx.scene.control.TextField;
/*     */ import javafx.scene.control.Tooltip;
/*     */ import javafx.scene.layout.BorderPane;
/*     */ import javafx.scene.layout.HBox;
/*     */ import javafx.scene.layout.VBox;
/*     */ import javafx.stage.Modality;
/*     */ import javafx.stage.Stage;
/*     */ import javafx.stage.WindowEvent;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ 
/*     */ public class ConfigEditor {
/*     */   private final String configFile;
/*     */   
/*     */   private CheckBox objectExclusions;
/*     */   
/*     */   private CheckBox forestExclusions;
/*     */   
/*     */   private CheckBox facadeExclusions;
/*     */   
/*     */   private CheckBox networkExclusions;
/*     */   
/*     */   private CheckBox smartExclusions;
/*     */   
/*     */   private TextField buildingRange;
/*     */   
/*     */   private TextField buildingsPerGrid;
/*     */   
/*     */   private TextField forestRange;
/*     */   
/*     */   private TextField numberOfThreads;
/*     */   
/*     */   private CheckBox roadNetwork;
/*     */   
/*     */   private CheckBox clipRoads;
/*     */   
/*     */   private CheckBox clipAirports;
/*     */   
/*     */   private CheckBox regionEnabled;
/*     */   
/*     */   private CheckBox compressTextures;
/*     */   
/*     */   private CheckBox osm3d;
/*     */   
/*     */   private Label forestExclusionRangeLabel;
/*     */   
/*     */   private Label buildingRangeLabel;
/*     */   
/*     */   private Label buildingExclusionRangeLabel;
/*     */   
/*     */   public ConfigEditor(String configFile) {
/*  72 */     this.configFile = configFile;
/*     */   }
/*     */   
/*     */   public void open() {
/*  76 */     final Stage dialogStage = new Stage();
/*  77 */     dialogStage.initModality(Modality.WINDOW_MODAL);
/*  78 */     dialogStage.setResizable(true);
/*  80 */     TabPane tabPane = new TabPane();
/*  82 */     Tab runtimeOptions = setupGenerationTab();
/*  83 */     runtimeOptions.setClosable(false);
/*  84 */     tabPane.getTabs().add(runtimeOptions);
/*  86 */     Tab exclusionOptions = setupExclusionsTab();
/*  87 */     exclusionOptions.setClosable(false);
/*  88 */     tabPane.getTabs().add(exclusionOptions);
/*  91 */     BorderPane borderPane = new BorderPane();
/*  92 */     borderPane.setTop((Node)tabPane);
/*  94 */     HBox buttons = new HBox();
/*  95 */     buttons.setPadding(new Insets(5.0D, 5.0D, 5.0D, 5.0D));
/*  96 */     borderPane.setBottom((Node)buttons);
/*  98 */     Button cancelButton = new Button("Cancel");
/*  99 */     cancelButton.setAlignment(Pos.BOTTOM_RIGHT);
/* 100 */     buttons.getChildren().add(cancelButton);
/* 101 */     cancelButton.setOnAction(new EventHandler<ActionEvent>() {
/*     */           public void handle(ActionEvent actionEvent) {
/* 104 */             dialogStage.close();
/*     */           }
/*     */         });
/* 108 */     Button saveButton = new Button("Save");
/* 109 */     buttons.setAlignment(Pos.BOTTOM_RIGHT);
/* 110 */     saveButton.setAlignment(Pos.BOTTOM_RIGHT);
/* 111 */     buttons.getChildren().add(saveButton);
/* 112 */     saveButton.setOnAction(new EventHandler<ActionEvent>() {
/*     */           public void handle(ActionEvent actionEvent) {
/*     */             try {
/* 116 */               ConfigEditor.this.saveConfig();
/* 117 */             } catch (Exception e) {
/* 118 */               e.printStackTrace();
/*     */             } 
/* 120 */             dialogStage.close();
/*     */           }
/*     */         });
/* 128 */     dialogStage.setScene(new Scene((Parent)borderPane, 640.0D, 480.0D));
/* 129 */     dialogStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
/*     */           public void handle(WindowEvent windowEvent) {
/* 132 */             dialogStage.close();
/*     */           }
/*     */         });
/* 135 */     dialogStage.show();
/* 137 */     getOptions();
/* 138 */     enableDisableOptions();
/*     */   }
/*     */   
/*     */   private void saveConfig() throws Exception {
/* 146 */     File file = new File(this.configFile);
/* 147 */     String configText = FileUtils.readFileToString(file);
/* 149 */     String[] lines = configText.split("\n");
/* 150 */     StringBuffer configFile = new StringBuffer();
/* 152 */     for (String item : lines) {
/* 154 */       if (item.contains("<exclude-objects>"))
/* 155 */         item = "\t<exclude-objects>" + (this.objectExclusions.isSelected() ? "true" : "false") + "</" + "exclude-objects" + ">"; 
/* 157 */       if (item.contains("<exclude-forests>"))
/* 158 */         item = "\t<exclude-forests>" + (this.forestExclusions.isSelected() ? "true" : "false") + "</" + "exclude-forests" + ">"; 
/* 160 */       if (item.contains("<exclude-facades>"))
/* 161 */         item = "\t<exclude-facades>" + (this.facadeExclusions.isSelected() ? "true" : "false") + "</" + "exclude-facades" + ">"; 
/* 163 */       if (item.contains("<exclude-network>"))
/* 164 */         item = "\t<exclude-network>" + (this.networkExclusions.isSelected() ? "true" : "false") + "</" + "exclude-network" + ">"; 
/* 166 */       if (item.contains("<smart-exclusions>"))
/* 167 */         item = "\t<smart-exclusions>" + (this.smartExclusions.isSelected() ? "true" : "false") + "</" + "smart-exclusions" + ">"; 
/* 169 */       if (item.contains("<clip-roads>"))
/* 170 */         item = "\t<clip-roads>" + (this.clipRoads.isSelected() ? "true" : "false") + "</" + "clip-roads" + ">"; 
/* 172 */       if (item.contains("<create-road-network>"))
/* 173 */         item = "\t<create-road-network>" + (this.roadNetwork.isSelected() ? "true" : "false") + "</" + "create-road-network" + ">"; 
/* 175 */       if (item.contains("<clip-airports>"))
/* 176 */         item = "\t<clip-airports>" + (this.clipAirports.isSelected() ? "true" : "false") + "</" + "clip-airports" + ">"; 
/* 178 */       if (item.contains("<region-enabled>"))
/* 179 */         item = "\t<region-enabled>" + (this.regionEnabled.isSelected() ? "true" : "false") + "</" + "region-enabled" + ">"; 
/* 181 */       if (item.contains("<compress-textures>"))
/* 182 */         item = "\t<compress-textures>" + (this.compressTextures.isSelected() ? "true" : "false") + "</" + "compress-textures" + ">"; 
/* 184 */       if (item.contains("<building-exclusion-range>"))
/* 185 */         item = "\t<building-exclusion-range>" + this.buildingRange.getText() + "</" + "building-exclusion-range" + ">"; 
/* 187 */       if (item.contains("<forest-exclusion-range>"))
/* 188 */         item = "\t<forest-exclusion-range>" + this.forestRange.getText() + "</" + "forest-exclusion-range" + ">"; 
/* 190 */       if (item.contains("<buildings-per-grid>"))
/* 191 */         item = "\t<buildings-per-grid>" + this.buildingsPerGrid.getText() + "</" + "buildings-per-grid" + ">"; 
/* 193 */       if (item.contains("<processor-threads>"))
/* 194 */         item = "\t<processor-threads>" + this.numberOfThreads.getText() + "</" + "processor-threads" + ">"; 
/* 196 */       if (item.contains("<enable-osm-3d>"))
/* 197 */         item = "\t<enable-osm-3d>" + (this.osm3d.isSelected() ? "true" : "false") + "</" + "enable-osm-3d" + ">"; 
/* 201 */       configFile.append(item);
/* 202 */       configFile.append("\n");
/*     */     } 
/* 207 */     FileUtils.writeStringToFile(file, configFile.toString());
/*     */   }
/*     */   
/*     */   private Tab setupGenerationTab() {
/* 211 */     Tab tab = new Tab("Scenery Options");
/* 212 */     VBox options = new VBox();
/* 213 */     tab.setContent((Node)options);
/* 216 */     HBox box1 = new HBox();
/* 217 */     box1.setSpacing(5.0D);
/* 218 */     box1.setPadding(new Insets(5.0D, 0.0D, 0.0D, 25.0D));
/* 219 */     this.roadNetwork = new CheckBox("Create Road Network (Experimental)");
/* 220 */     this.roadNetwork.setTooltip(new Tooltip("Generates Roads, Power Lines and Railways"));
/* 221 */     box1.getChildren().add(this.roadNetwork);
/* 224 */     HBox box1a = new HBox();
/* 225 */     box1a.setSpacing(5.0D);
/* 226 */     box1a.setPadding(new Insets(5.0D, 0.0D, 0.0D, 25.0D));
/* 227 */     this.clipRoads = new CheckBox("Clip Forests/Fields with intersecting roads");
/* 228 */     this.clipRoads.setTooltip(new Tooltip("Will cut holes inside forests if a road passes through it. Use this option to prevent trees on roads"));
/* 229 */     box1a.getChildren().add(this.clipRoads);
/* 231 */     HBox box2 = new HBox();
/* 232 */     box2.setSpacing(5.0D);
/* 233 */     box2.setPadding(new Insets(5.0D, 0.0D, 0.0D, 25.0D));
/* 234 */     this.clipAirports = new CheckBox("Clip Forests/Fields which contain airports");
/* 235 */     this.clipAirports.setTooltip(new Tooltip("Clip forests/fields if they border or contain a airport. Use this to prevent trees appearing near to a runway"));
/* 236 */     box2.getChildren().add(this.clipAirports);
/* 238 */     HBox box3 = new HBox();
/* 239 */     box3.setSpacing(5.0D);
/* 240 */     box3.setPadding(new Insets(5.0D, 0.0D, 0.0D, 25.0D));
/* 241 */     this.regionEnabled = new CheckBox("Use Regions");
/* 242 */     this.regionEnabled.setTooltip(new Tooltip("Enable Regions. When disabled, regional settings will be ignored"));
/* 243 */     box3.getChildren().add(this.regionEnabled);
/* 245 */     HBox box3a = new HBox();
/* 246 */     box3a.setSpacing(5.0D);
/* 247 */     box3a.setPadding(new Insets(5.0D, 0.0D, 0.0D, 25.0D));
/* 248 */     this.compressTextures = new CheckBox("Compress Generated Textures");
/* 249 */     this.compressTextures.setTooltip(new Tooltip("When enabled, generated textures from OSM3D objects will be compressed to DDS which will use less memory in X-Plane"));
/* 250 */     box3a.getChildren().add(this.compressTextures);
/* 252 */     HBox box4 = new HBox();
/* 253 */     box4.setSpacing(5.0D);
/* 254 */     box4.setPadding(new Insets(5.0D, 0.0D, 0.0D, 25.0D));
/* 255 */     this.osm3d = new CheckBox("Enable OSM 3D");
/* 256 */     this.osm3d.setTooltip(new Tooltip("Generate objects from OSM 3D buildings"));
/* 257 */     box4.getChildren().add(this.regionEnabled);
/* 259 */     HBox box5 = new HBox();
/* 260 */     box5.setSpacing(5.0D);
/* 261 */     box5.setPadding(new Insets(5.0D, 0.0D, 0.0D, 25.0D));
/* 264 */     HBox box6 = new HBox();
/* 265 */     box6.setSpacing(5.0D);
/* 266 */     box6.setPadding(new Insets(5.0D, 0.0D, 0.0D, 25.0D));
/* 267 */     Label label1 = new Label("Number of Processor Threads");
/* 268 */     label1.setPrefWidth(230.0D);
/* 270 */     this.numberOfThreads = new TextField("") {
/*     */         public void replaceText(int start, int end, String text) {
/* 272 */           if (text.matches("[0-9]*"))
/* 273 */             super.replaceText(start, end, text); 
/*     */         }
/*     */         
/*     */         public void replaceSelection(String text) {
/* 278 */           if (text.matches("[0-9]*"))
/* 279 */             super.replaceSelection(text); 
/*     */         }
/*     */       };
/* 283 */     this.numberOfThreads.setPrefWidth(80.0D);
/* 284 */     this.numberOfThreads.setTooltip(new Tooltip("Number of CPU threads to use for generating scenery."));
/* 285 */     box6.getChildren().add(label1);
/* 286 */     box6.getChildren().add(this.numberOfThreads);
/* 288 */     options.getChildren().add(box1);
/* 289 */     options.getChildren().add(box1a);
/* 290 */     options.getChildren().add(box2);
/* 291 */     options.getChildren().add(box3);
/* 292 */     options.getChildren().add(box3a);
/* 293 */     options.getChildren().add(box4);
/* 294 */     options.getChildren().add(box5);
/* 295 */     options.getChildren().add(box6);
/* 301 */     return tab;
/*     */   }
/*     */   
/*     */   private Tab setupExclusionsTab() {
/* 306 */     Tab tab = new Tab("Exclusions");
/* 310 */     VBox options = new VBox();
/* 311 */     tab.setContent((Node)options);
/* 314 */     HBox sceneExclusions = new HBox();
/* 315 */     sceneExclusions.setSpacing(5.0D);
/* 316 */     sceneExclusions.setPadding(new Insets(5.0D, 0.0D, 0.0D, 25.0D));
/* 317 */     this.objectExclusions = new CheckBox("Exclude Objects");
/* 318 */     this.objectExclusions.setTooltip(new Tooltip("Exclude all 3D objects from the scenery below"));
/* 319 */     this.forestExclusions = new CheckBox("Exclude Forests");
/* 320 */     this.forestExclusions.setTooltip(new Tooltip("Exclude all forests from the scenery below"));
/* 321 */     this.facadeExclusions = new CheckBox("Exclude Facades");
/* 322 */     this.facadeExclusions.setTooltip(new Tooltip("Exclude all facades from the scenery below"));
/* 323 */     this.networkExclusions = new CheckBox("Exclude Roads");
/* 324 */     this.networkExclusions.setTooltip(new Tooltip("Exclude all roads, power lines and railways from the scenery below"));
/* 326 */     sceneExclusions.getChildren().add(this.objectExclusions);
/* 327 */     sceneExclusions.getChildren().add(this.forestExclusions);
/* 328 */     sceneExclusions.getChildren().add(this.facadeExclusions);
/* 329 */     sceneExclusions.getChildren().add(this.networkExclusions);
/* 332 */     HBox smartExclusionOptions = new HBox();
/* 333 */     smartExclusionOptions.setSpacing(5.0D);
/* 334 */     smartExclusionOptions.setPadding(new Insets(5.0D, 0.0D, 0.0D, 25.0D));
/* 335 */     this.smartExclusions = new CheckBox("Enable Smart Exclusions");
/* 336 */     this.smartExclusions.setTooltip(new Tooltip("Smart Exclusions only creates exclusions for buildings and forests when there is data in OSM. Use this option if you wish to mix this scenery with the scenery below."));
/* 338 */     this.smartExclusions.setOnAction(new EventHandler<ActionEvent>() {
/*     */           public void handle(ActionEvent actionEvent) {
/* 341 */             ConfigEditor.this.enableDisableOptions();
/*     */           }
/*     */         });
/* 344 */     smartExclusionOptions.getChildren().add(this.smartExclusions);
/* 347 */     HBox buildingRangeOptions = new HBox();
/* 348 */     buildingRangeOptions.setSpacing(5.0D);
/* 349 */     buildingRangeOptions.setPadding(new Insets(5.0D, 0.0D, 0.0D, 25.0D));
/* 350 */     this.buildingExclusionRangeLabel = new Label("Building Exclusion Range (Metres)");
/* 351 */     this.buildingExclusionRangeLabel.setPrefWidth(230.0D);
/* 352 */     this.buildingRange = new TextField("") {
/*     */         public void replaceText(int start, int end, String text) {
/* 354 */           if (text.matches("[0-9]*"))
/* 355 */             super.replaceText(start, end, text); 
/*     */         }
/*     */         
/*     */         public void replaceSelection(String text) {
/* 360 */           if (text.matches("[0-9]*"))
/* 361 */             super.replaceSelection(text); 
/*     */         }
/*     */       };
/* 365 */     this.buildingRange.setPrefWidth(80.0D);
/* 366 */     this.buildingRange.setTooltip(new Tooltip("Maximum size of exclusion zone in metres for buildings. Do not set this value too low, as it may crash X-Plane."));
/* 368 */     this.buildingRangeLabel = new Label("Buildings Per Range");
/* 369 */     this.buildingsPerGrid = new TextField("") {
/*     */         public void replaceText(int start, int end, String text) {
/* 371 */           if (text.matches("[0-9]*"))
/* 372 */             super.replaceText(start, end, text); 
/*     */         }
/*     */         
/*     */         public void replaceSelection(String text) {
/* 377 */           if (text.matches("[0-9]*"))
/* 378 */             super.replaceSelection(text); 
/*     */         }
/*     */       };
/* 382 */     this.buildingsPerGrid.setTooltip(new Tooltip("Number of buildings per building exclusion zone. e.g. If this option is set to 5, then an exclusion zone will only be created if it contains 5 or more buildings. "));
/* 384 */     buildingRangeOptions.getChildren().add(this.buildingExclusionRangeLabel);
/* 385 */     buildingRangeOptions.getChildren().add(this.buildingRange);
/* 386 */     buildingRangeOptions.getChildren().add(this.buildingRangeLabel);
/* 387 */     buildingRangeOptions.getChildren().add(this.buildingsPerGrid);
/* 389 */     HBox forestRangeOptions = new HBox();
/* 390 */     forestRangeOptions.setSpacing(5.0D);
/* 391 */     forestRangeOptions.setPadding(new Insets(5.0D, 0.0D, 0.0D, 25.0D));
/* 392 */     this.forestExclusionRangeLabel = new Label("Forest Exclusion Range (Metres)");
/* 393 */     this.forestExclusionRangeLabel.setPrefWidth(230.0D);
/* 395 */     this.forestRange = new TextField("") {
/*     */         public void replaceText(int start, int end, String text) {
/* 397 */           if (text.matches("[0-9]*"))
/* 398 */             super.replaceText(start, end, text); 
/*     */         }
/*     */         
/*     */         public void replaceSelection(String text) {
/* 403 */           if (text.matches("[0-9]*"))
/* 404 */             super.replaceSelection(text); 
/*     */         }
/*     */       };
/* 408 */     this.forestRange.setPrefWidth(80.0D);
/* 409 */     this.forestRange.setTooltip(new Tooltip("Maximum size of exclusion zone in metres for forests. Do not set this value too low, as it may crash X-Plane."));
/* 410 */     forestRangeOptions.getChildren().add(this.forestExclusionRangeLabel);
/* 411 */     forestRangeOptions.getChildren().add(this.forestRange);
/* 413 */     options.getChildren().add(smartExclusionOptions);
/* 414 */     options.getChildren().add(buildingRangeOptions);
/* 415 */     options.getChildren().add(forestRangeOptions);
/* 416 */     options.getChildren().add(sceneExclusions);
/* 422 */     return tab;
/*     */   }
/*     */   
/*     */   private void enableDisableOptions() {
/* 426 */     if (this.smartExclusions.isSelected()) {
/* 427 */       this.objectExclusions.setDisable(true);
/* 428 */       this.forestExclusions.setDisable(true);
/* 429 */       this.facadeExclusions.setDisable(true);
/* 430 */       this.networkExclusions.setDisable(true);
/* 432 */       this.buildingRange.setDisable(false);
/* 433 */       this.forestRange.setDisable(false);
/* 434 */       this.buildingsPerGrid.setDisable(false);
/* 435 */       this.buildingExclusionRangeLabel.setDisable(false);
/* 436 */       this.forestExclusionRangeLabel.setDisable(false);
/* 437 */       this.buildingRangeLabel.setDisable(false);
/*     */     } else {
/* 439 */       this.objectExclusions.setDisable(false);
/* 440 */       this.forestExclusions.setDisable(false);
/* 441 */       this.facadeExclusions.setDisable(false);
/* 442 */       this.networkExclusions.setDisable(false);
/* 444 */       this.buildingRange.setDisable(true);
/* 445 */       this.forestRange.setDisable(true);
/* 446 */       this.buildingsPerGrid.setDisable(true);
/* 447 */       this.buildingExclusionRangeLabel.setDisable(true);
/* 448 */       this.forestExclusionRangeLabel.setDisable(true);
/* 449 */       this.buildingRangeLabel.setDisable(true);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void getOptions() {
/* 456 */     GeneratorStore generatorStore = new GeneratorStore();
/*     */     try {
/* 458 */       generatorStore.readConfig(new File(this.configFile));
/* 459 */     } catch (Exception e) {
/* 460 */       e.printStackTrace();
/*     */     } 
/* 463 */     this.objectExclusions.setSelected(generatorStore.isCreateObjectExclusions());
/* 464 */     this.forestExclusions.setSelected(generatorStore.isCreateForestExclusions());
/* 465 */     this.facadeExclusions.setSelected(generatorStore.isCreateFacadeExclusions());
/* 466 */     this.networkExclusions.setSelected(generatorStore.isCreateNetworkExclusions());
/* 468 */     this.smartExclusions.setSelected(generatorStore.isSmartExclusions());
/* 469 */     this.buildingRange.setText(generatorStore.getBuildingExclusionRange() + "");
/* 470 */     this.buildingsPerGrid.setText(generatorStore.getBuildingsPerGrid() + "");
/* 471 */     this.forestRange.setText(generatorStore.getForestExclusionRange() + "");
/* 473 */     this.roadNetwork.setSelected(generatorStore.isGenerateRoads());
/* 474 */     this.clipRoads.setSelected(generatorStore.isClipRoads());
/* 475 */     this.clipAirports.setSelected(generatorStore.isClipAirports());
/* 477 */     this.compressTextures.setSelected(generatorStore.isCompressTextures());
/* 478 */     this.regionEnabled.setSelected(generatorStore.isEnabledRegions());
/* 479 */     this.osm3d.setSelected(generatorStore.isCreateBuildingParts());
/* 481 */     this.numberOfThreads.setText(generatorStore.getProcessorThreads() + "");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\GUI\ConfigEditor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */