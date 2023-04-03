/*     */ package org.jfree.data.gantt;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.jfree.data.time.SimpleTimePeriod;
/*     */ import org.jfree.data.time.TimePeriod;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class Task implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 1094303785346988894L;
/*     */   
/*     */   private String description;
/*     */   
/*     */   private TimePeriod duration;
/*     */   
/*     */   private Double percentComplete;
/*     */   
/*     */   private List subtasks;
/*     */   
/*     */   public Task(String description, TimePeriod duration) {
/*  86 */     if (description == null)
/*  87 */       throw new IllegalArgumentException("Null 'description' argument."); 
/*  89 */     this.description = description;
/*  90 */     this.duration = duration;
/*  91 */     this.percentComplete = null;
/*  92 */     this.subtasks = new ArrayList();
/*     */   }
/*     */   
/*     */   public Task(String description, Date start, Date end) {
/* 104 */     this(description, (TimePeriod)new SimpleTimePeriod(start, end));
/*     */   }
/*     */   
/*     */   public String getDescription() {
/* 113 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(String description) {
/* 122 */     if (description == null)
/* 123 */       throw new IllegalArgumentException("Null 'description' argument."); 
/* 125 */     this.description = description;
/*     */   }
/*     */   
/*     */   public TimePeriod getDuration() {
/* 134 */     return this.duration;
/*     */   }
/*     */   
/*     */   public void setDuration(TimePeriod duration) {
/* 143 */     this.duration = duration;
/*     */   }
/*     */   
/*     */   public Double getPercentComplete() {
/* 152 */     return this.percentComplete;
/*     */   }
/*     */   
/*     */   public void setPercentComplete(Double percent) {
/* 161 */     this.percentComplete = percent;
/*     */   }
/*     */   
/*     */   public void setPercentComplete(double percent) {
/* 170 */     setPercentComplete(new Double(percent));
/*     */   }
/*     */   
/*     */   public void addSubtask(Task subtask) {
/* 179 */     if (subtask == null)
/* 180 */       throw new IllegalArgumentException("Null 'subtask' argument."); 
/* 182 */     this.subtasks.add(subtask);
/*     */   }
/*     */   
/*     */   public void removeSubtask(Task subtask) {
/* 191 */     this.subtasks.remove(subtask);
/*     */   }
/*     */   
/*     */   public int getSubtaskCount() {
/* 200 */     return this.subtasks.size();
/*     */   }
/*     */   
/*     */   public Task getSubtask(int index) {
/* 211 */     return this.subtasks.get(index);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 222 */     if (object == this)
/* 223 */       return true; 
/* 225 */     if (!(object instanceof Task))
/* 226 */       return false; 
/* 228 */     Task that = (Task)object;
/* 229 */     if (!ObjectUtilities.equal(this.description, that.description))
/* 230 */       return false; 
/* 232 */     if (!ObjectUtilities.equal(this.duration, that.duration))
/* 233 */       return false; 
/* 235 */     if (!ObjectUtilities.equal(this.percentComplete, that.percentComplete))
/* 237 */       return false; 
/* 239 */     if (!ObjectUtilities.equal(this.subtasks, that.subtasks))
/* 240 */       return false; 
/* 242 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 254 */     Task clone = (Task)super.clone();
/* 255 */     return clone;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\gantt\Task.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */