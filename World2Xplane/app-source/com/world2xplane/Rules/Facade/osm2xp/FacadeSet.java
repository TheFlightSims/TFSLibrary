/*     */ package com.world2xplane.Rules.Facade.osm2xp;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlElement;
/*     */ import javax.xml.bind.annotation.XmlRootElement;
/*     */ import javax.xml.bind.annotation.XmlTransient;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name = "", propOrder = {"name", "author", "description", "facades"})
/*     */ @XmlRootElement(name = "FacadeSet")
/*     */ public class FacadeSet {
/*     */   @XmlElement(required = true)
/*     */   protected String name;
/*     */   
/*     */   @XmlElement(required = true)
/*     */   protected String author;
/*     */   
/*     */   @XmlElement(required = true)
/*     */   protected String description;
/*     */   
/*     */   @XmlElement(required = true)
/*     */   protected List<Facade> facades;
/*     */   
/*     */   @XmlTransient
/*     */   private String identifier;
/*     */   
/*     */   public String getName() {
/*  61 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String value) {
/*  72 */     this.name = value;
/*     */   }
/*     */   
/*     */   public String getAuthor() {
/*  82 */     return this.author;
/*     */   }
/*     */   
/*     */   public void setAuthor(String value) {
/*  93 */     this.author = value;
/*     */   }
/*     */   
/*     */   public String getDescription() {
/* 103 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(String value) {
/* 114 */     this.description = value;
/*     */   }
/*     */   
/*     */   public List<Facade> getFacades() {
/* 140 */     if (this.facades == null)
/* 141 */       this.facades = new ArrayList<>(); 
/* 143 */     return this.facades;
/*     */   }
/*     */   
/*     */   public void setIdentifier(String identifier) {
/* 147 */     this.identifier = identifier;
/*     */   }
/*     */   
/*     */   public String getIdentifier() {
/* 151 */     return this.identifier;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\Facade\osm2xp\FacadeSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */