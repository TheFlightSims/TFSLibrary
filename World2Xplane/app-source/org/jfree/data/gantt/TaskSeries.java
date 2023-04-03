/*     */ package org.jfree.data.gantt;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.jfree.data.general.Series;
/*     */ 
/*     */ public class TaskSeries extends Series {
/*     */   private List tasks;
/*     */   
/*     */   public TaskSeries(String name) {
/*  71 */     super(name);
/*  72 */     this.tasks = new ArrayList();
/*     */   }
/*     */   
/*     */   public void add(Task task) {
/*  83 */     if (task == null)
/*  84 */       throw new IllegalArgumentException("Null 'task' argument."); 
/*  86 */     this.tasks.add(task);
/*  87 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */   public void remove(Task task) {
/*  98 */     this.tasks.remove(task);
/*  99 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */   public void removeAll() {
/* 108 */     this.tasks.clear();
/* 109 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */   public int getItemCount() {
/* 118 */     return this.tasks.size();
/*     */   }
/*     */   
/*     */   public Task get(int index) {
/* 129 */     return this.tasks.get(index);
/*     */   }
/*     */   
/*     */   public Task get(String description) {
/* 140 */     Task result = null;
/* 141 */     int count = this.tasks.size();
/* 142 */     for (int i = 0; i < count; i++) {
/* 143 */       Task t = this.tasks.get(i);
/* 144 */       if (t.getDescription().equals(description)) {
/* 145 */         result = t;
/*     */         break;
/*     */       } 
/*     */     } 
/* 149 */     return result;
/*     */   }
/*     */   
/*     */   public List getTasks() {
/* 158 */     return Collections.unmodifiableList(this.tasks);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 169 */     if (obj == this)
/* 170 */       return true; 
/* 172 */     if (!(obj instanceof TaskSeries))
/* 173 */       return false; 
/* 175 */     if (!super.equals(obj))
/* 176 */       return false; 
/* 178 */     TaskSeries that = (TaskSeries)obj;
/* 179 */     if (!this.tasks.equals(that.tasks))
/* 180 */       return false; 
/* 182 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\gantt\TaskSeries.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */