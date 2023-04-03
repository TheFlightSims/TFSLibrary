/*     */ package com.world2xplane.GUI;
/*     */ 
/*     */ import javafx.collections.ListChangeListener;
/*     */ import javafx.geometry.Insets;
/*     */ import javafx.geometry.Pos;
/*     */ import javafx.scene.Group;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.layout.StackPane;
/*     */ 
/*     */ class FieldSet extends StackPane {
/*  34 */   private Label legend = new Label("");
/*     */   
/*     */   private Node legendNode;
/*     */   
/*  37 */   private StackPane contentBox = new StackPane();
/*     */   
/*  38 */   private StackPane legendBox = new StackPane();
/*     */   
/*     */   public FieldSet(String legendStr) {
/*  42 */     this.legend.setText(legendStr);
/*  43 */     this.legendBox.getChildren().add(this.legend);
/*  44 */     configureFieldSet();
/*     */   }
/*     */   
/*     */   public FieldSet(Node legendNode) {
/*  49 */     this.legendNode = legendNode;
/*  50 */     this.legendBox.getChildren().add(legendNode);
/*  51 */     configureFieldSet();
/*     */   }
/*     */   
/*     */   private void configureFieldSet() {
/*  55 */     setPadding(new Insets(10.0D, 0.0D, 0.0D, 0.0D));
/*  56 */     setAlignment(Pos.TOP_LEFT);
/*  57 */     getStyleClass().add("fieldSetDefault");
/*  59 */     this.contentBox.getStyleClass().add("fieldSet");
/*  60 */     this.contentBox.setAlignment(Pos.TOP_LEFT);
/*  61 */     this.contentBox.setPadding(new Insets(8.0D, 2.0D, 2.0D, 2.0D));
/*  63 */     this.legendBox.setPadding(new Insets(0.0D, 5.0D, 0.0D, 5.0D));
/*  65 */     Group gp = new Group();
/*  66 */     gp.setTranslateX(20.0D);
/*  67 */     gp.setTranslateY(-8.0D);
/*  68 */     gp.getChildren().add(this.legendBox);
/*  70 */     getChildren().addAll((Object[])new Node[] { (Node)this.contentBox, (Node)gp });
/*  71 */     setBackGroundColor("#FFFFFF");
/*  74 */     getStyleClass().addListener(new ListChangeListener<String>() {
/*     */           public void onChanged(ListChangeListener.Change<? extends String> paramChange) {
/*  78 */             FieldSet.this.contentBox.getStyleClass().clear();
/*  79 */             FieldSet.this.contentBox.getStyleClass().addAll((Object[])new String[] { "fieldSet" });
/*  80 */             for (String clazz : FieldSet.this.getStyleClass()) {
/*  81 */               if (!clazz.equals("fieldSetDefault"))
/*  82 */                 FieldSet.this.contentBox.getStyleClass().add(clazz); 
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public void setContent(Node content) {
/*  94 */     this.contentBox.getChildren().add(content);
/*     */   }
/*     */   
/*     */   public void setBackGroundColor(String color) {
/*  98 */     setStyle("-fx-background-color:" + color + ";");
/*  99 */     this.contentBox.setStyle("-fx-background-color:" + color + ";");
/* 100 */     this.legendBox.setStyle("-fx-background-color:" + color + ";");
/*     */   }
/*     */   
/*     */   public void setStyleClassForBorder(String claz) {
/* 104 */     this.contentBox.getStyleClass().add(claz);
/*     */   }
/*     */   
/*     */   public void removeStyleClassForBorder(String claz) {
/* 108 */     this.contentBox.getStyleClass().remove(claz);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\GUI\FieldSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */