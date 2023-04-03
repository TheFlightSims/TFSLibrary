/*     */ package org.openstreetmap.osmosis.core;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.openstreetmap.osmosis.core.bound.v0_6.BoundComputerFactory;
/*     */ import org.openstreetmap.osmosis.core.bound.v0_6.BoundSetterFactory;
/*     */ import org.openstreetmap.osmosis.core.buffer.v0_6.ChangeBufferFactory;
/*     */ import org.openstreetmap.osmosis.core.buffer.v0_6.EntityBufferFactory;
/*     */ import org.openstreetmap.osmosis.core.misc.v0_6.EmptyChangeReaderFactory;
/*     */ import org.openstreetmap.osmosis.core.misc.v0_6.EmptyReaderFactory;
/*     */ import org.openstreetmap.osmosis.core.misc.v0_6.NullChangeWriterFactory;
/*     */ import org.openstreetmap.osmosis.core.misc.v0_6.NullWriterFactory;
/*     */ import org.openstreetmap.osmosis.core.pipeline.common.TaskManagerFactory;
/*     */ import org.openstreetmap.osmosis.core.plugin.PluginLoader;
/*     */ import org.openstreetmap.osmosis.core.progress.v0_6.ChangeProgressLoggerFactory;
/*     */ import org.openstreetmap.osmosis.core.progress.v0_6.EntityProgressLoggerFactory;
/*     */ import org.openstreetmap.osmosis.core.report.v0_6.EntityReporterFactory;
/*     */ import org.openstreetmap.osmosis.core.report.v0_6.IntegrityReporterFactory;
/*     */ import org.openstreetmap.osmosis.core.sort.v0_6.ChangeForSeekableApplierComparator;
/*     */ import org.openstreetmap.osmosis.core.sort.v0_6.ChangeForStreamableApplierComparator;
/*     */ import org.openstreetmap.osmosis.core.sort.v0_6.ChangeSorterFactory;
/*     */ import org.openstreetmap.osmosis.core.sort.v0_6.ChangeTagSorterFactory;
/*     */ import org.openstreetmap.osmosis.core.sort.v0_6.EntityByTypeThenIdComparator;
/*     */ import org.openstreetmap.osmosis.core.sort.v0_6.EntityContainerComparator;
/*     */ import org.openstreetmap.osmosis.core.sort.v0_6.EntitySorterFactory;
/*     */ import org.openstreetmap.osmosis.core.sort.v0_6.TagSorterFactory;
/*     */ import org.openstreetmap.osmosis.core.tee.v0_6.ChangeTeeFactory;
/*     */ import org.openstreetmap.osmosis.core.tee.v0_6.EntityTeeFactory;
/*     */ 
/*     */ public class CorePluginLoader implements PluginLoader {
/*     */   public Map<String, TaskManagerFactory> loadTaskFactories() {
/*  49 */     Map<String, TaskManagerFactory> factoryMap = new HashMap<String, TaskManagerFactory>();
/*  52 */     EntitySorterFactory entitySorterFactory06 = new EntitySorterFactory();
/*  53 */     entitySorterFactory06.registerComparator("TypeThenId", (Comparator)new EntityContainerComparator((Comparator)new EntityByTypeThenIdComparator()), true);
/*  55 */     ChangeSorterFactory changeSorterFactory06 = new ChangeSorterFactory();
/*  56 */     changeSorterFactory06.registerComparator("streamable", (Comparator)new ChangeForStreamableApplierComparator(), true);
/*  57 */     changeSorterFactory06.registerComparator("seekable", (Comparator)new ChangeForSeekableApplierComparator(), false);
/*  60 */     factoryMap.put("sort", entitySorterFactory06);
/*  61 */     factoryMap.put("s", entitySorterFactory06);
/*  62 */     factoryMap.put("sort-change", changeSorterFactory06);
/*  63 */     factoryMap.put("sc", changeSorterFactory06);
/*  64 */     factoryMap.put("write-null", new NullWriterFactory());
/*  65 */     factoryMap.put("wn", new NullWriterFactory());
/*  66 */     factoryMap.put("write-null-change", new NullChangeWriterFactory());
/*  67 */     factoryMap.put("wnc", new NullChangeWriterFactory());
/*  68 */     factoryMap.put("buffer", new EntityBufferFactory());
/*  69 */     factoryMap.put("b", new EntityBufferFactory());
/*  70 */     factoryMap.put("buffer-change", new ChangeBufferFactory());
/*  71 */     factoryMap.put("bc", new ChangeBufferFactory());
/*  72 */     factoryMap.put("report-entity", new EntityReporterFactory());
/*  73 */     factoryMap.put("re", new EntityReporterFactory());
/*  74 */     factoryMap.put("report-integrity", new IntegrityReporterFactory());
/*  75 */     factoryMap.put("ri", new IntegrityReporterFactory());
/*  76 */     factoryMap.put("log-progress", new EntityProgressLoggerFactory());
/*  77 */     factoryMap.put("lp", new EntityProgressLoggerFactory());
/*  78 */     factoryMap.put("log-progress-change", new ChangeProgressLoggerFactory());
/*  79 */     factoryMap.put("lpc", new ChangeProgressLoggerFactory());
/*  80 */     factoryMap.put("tee", new EntityTeeFactory());
/*  81 */     factoryMap.put("t", new EntityTeeFactory());
/*  82 */     factoryMap.put("tee-change", new ChangeTeeFactory());
/*  83 */     factoryMap.put("tc", new ChangeTeeFactory());
/*  84 */     factoryMap.put("read-empty", new EmptyReaderFactory());
/*  85 */     factoryMap.put("rem", new EmptyReaderFactory());
/*  86 */     factoryMap.put("read-empty-change", new EmptyChangeReaderFactory());
/*  87 */     factoryMap.put("remc", new EmptyChangeReaderFactory());
/*  89 */     factoryMap.put("compute-bounding-box", new BoundComputerFactory());
/*  90 */     factoryMap.put("cbb", new BoundComputerFactory());
/*  91 */     factoryMap.put("set-bounding-box", new BoundSetterFactory());
/*  92 */     factoryMap.put("sbb", new BoundSetterFactory());
/*  94 */     factoryMap.put("sort-0.6", entitySorterFactory06);
/*  95 */     factoryMap.put("sort-change-0.6", changeSorterFactory06);
/*  96 */     factoryMap.put("write-null-0.6", new NullWriterFactory());
/*  97 */     factoryMap.put("write-null-change-0.6", new NullChangeWriterFactory());
/*  98 */     factoryMap.put("buffer-0.6", new EntityBufferFactory());
/*  99 */     factoryMap.put("buffer-change-0.6", new ChangeBufferFactory());
/* 100 */     factoryMap.put("report-entity-0.6", new EntityReporterFactory());
/* 101 */     factoryMap.put("report-integrity-0.6", new IntegrityReporterFactory());
/* 102 */     factoryMap.put("log-progress-0.6", new EntityProgressLoggerFactory());
/* 103 */     factoryMap.put("log-progress-change-0.6", new ChangeProgressLoggerFactory());
/* 104 */     factoryMap.put("tee-0.6", new EntityTeeFactory());
/* 105 */     factoryMap.put("tee-change-0.6", new ChangeTeeFactory());
/* 106 */     factoryMap.put("read-empty-0.6", new EmptyReaderFactory());
/* 107 */     factoryMap.put("read-empty-change-0.6", new EmptyChangeReaderFactory());
/* 108 */     factoryMap.put("tag-sort-0.6", new TagSorterFactory());
/* 109 */     factoryMap.put("tag-sort-change-0.6", new ChangeTagSorterFactory());
/* 111 */     factoryMap.put("compute-bounding-box-0.6", new BoundComputerFactory());
/* 112 */     factoryMap.put("set-bounding-box-0.6", new BoundSetterFactory());
/* 114 */     return factoryMap;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\CorePluginLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */