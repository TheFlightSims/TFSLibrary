/*     */ package com.world2xplane.Rules;
/*     */ 
/*     */ import com.world2xplane.Rules.Filter.AreaFilter;
/*     */ import com.world2xplane.Rules.Filter.ContainsFilter;
/*     */ import com.world2xplane.Rules.Filter.EqualsFilter;
/*     */ import com.world2xplane.Rules.Filter.FalseFilter;
/*     */ import com.world2xplane.Rules.Filter.Filter;
/*     */ import com.world2xplane.Rules.Filter.GreaterThanFilter;
/*     */ import com.world2xplane.Rules.Filter.LessThanFilter;
/*     */ import com.world2xplane.Rules.Filter.NodeFilter;
/*     */ import com.world2xplane.Rules.Filter.NotContainsFilter;
/*     */ import com.world2xplane.Rules.Filter.NotIncludeFilter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class FilterList {
/*  34 */   private List<Map<String, List<Filter>>> tagSets = new ArrayList<>();
/*     */   
/*  36 */   private HashMap<Integer, Object> cache = new HashMap<>();
/*     */   
/*     */   private String filterType;
/*     */   
/*     */   private String filter;
/*     */   
/*     */   public String getFilterType() {
/*  41 */     return this.filterType;
/*     */   }
/*     */   
/*     */   public void setFilterType(String filterType) {
/*  45 */     this.filterType = filterType;
/*     */   }
/*     */   
/*     */   public String getFilter() {
/*  49 */     return this.filter;
/*     */   }
/*     */   
/*     */   public void setFilter(String filter) {
/*  53 */     this.filter = filter;
/*     */   }
/*     */   
/*     */   public void parseFilter() {
/*  62 */     Pattern filterPattern = Pattern.compile("^([A-Za-z:0-9_]+)(!=|=|<|>|!~|~)(\\\"*[A-Za-z0-9_ \\*\\S]+\\\"*)");
/*  63 */     Pattern notIncludePattern = Pattern.compile("^\\!([\\S]+)$");
/*  66 */     if (this.filterType.equals("key-value")) {
/*  67 */       String[] lines = this.filter.split("\n");
/*  69 */       for (String line : lines) {
/*  70 */         HashMap<String, List<Filter>> conjunction = new HashMap<>();
/*  71 */         this.tagSets.add(conjunction);
/*  73 */         String[] filters = line.split(",");
/*  75 */         for (String item : filters) {
/*  76 */           Matcher m = filterPattern.matcher(item.trim());
/*  77 */           if (m.matches()) {
/*  78 */             String key = m.group(1);
/*  79 */             String operator = m.group(2);
/*  80 */             String value = m.group(3);
/*  82 */             if (key.equals("area") && operator.equals("=")) {
/*  83 */               AreaFilter areaFilter = new AreaFilter(item.trim());
/*  84 */               areaFilter.setFilter(value);
/*  85 */               if (conjunction.get(key) == null)
/*  86 */                 conjunction.put(key, new ArrayList<>()); 
/*  88 */               ((List<AreaFilter>)conjunction.get(key)).add(areaFilter);
/*     */             } 
/*  89 */             if (key.equals("node") && operator.equals("=")) {
/*  90 */               NodeFilter nodeFilter = new NodeFilter(item.trim());
/*  91 */               nodeFilter.setFilter(value);
/*  92 */               if (conjunction.get(key) == null)
/*  93 */                 conjunction.put(key, new ArrayList<>()); 
/*  95 */               ((List<NodeFilter>)conjunction.get(key)).add(nodeFilter);
/*  96 */             } else if (operator.equals("=")) {
/*  97 */               EqualsFilter equalsFilter = new EqualsFilter(item.trim());
/*  98 */               equalsFilter.setFilter(value);
/*  99 */               if (conjunction.get(key) == null)
/* 100 */                 conjunction.put(key, new ArrayList<>()); 
/* 102 */               ((List<EqualsFilter>)conjunction.get(key)).add(equalsFilter);
/*     */             } 
/* 105 */             if (operator.equals("!=")) {
/* 106 */               FalseFilter falseFilter = new FalseFilter(item.trim());
/* 107 */               falseFilter.setFilter(value);
/* 108 */               if (conjunction.get(key) == null)
/* 109 */                 conjunction.put(key, new ArrayList<>()); 
/* 111 */               ((List<FalseFilter>)conjunction.get(key)).add(falseFilter);
/*     */             } 
/* 115 */             if (operator.equals("!~")) {
/* 116 */               NotContainsFilter falseFilter = new NotContainsFilter(item.trim());
/* 117 */               falseFilter.setFilter(value);
/* 118 */               if (conjunction.get(key) == null)
/* 119 */                 conjunction.put(key, new ArrayList<>()); 
/* 121 */               ((List<NotContainsFilter>)conjunction.get(key)).add(falseFilter);
/*     */             } 
/* 125 */             if (operator.equals("~")) {
/* 126 */               ContainsFilter containsFilter = new ContainsFilter(item.trim());
/* 127 */               containsFilter.setFilter(value);
/* 128 */               if (conjunction.get(key) == null)
/* 129 */                 conjunction.put(key, new ArrayList<>()); 
/* 131 */               ((List<ContainsFilter>)conjunction.get(key)).add(containsFilter);
/*     */             } 
/* 134 */             if (operator.equals(">")) {
/* 135 */               GreaterThanFilter falseFilter = new GreaterThanFilter(item.trim());
/* 136 */               falseFilter.setFilter(value);
/* 137 */               if (conjunction.get(key) == null)
/* 138 */                 conjunction.put(key, new ArrayList<>()); 
/* 140 */               ((List<GreaterThanFilter>)conjunction.get(key)).add(falseFilter);
/*     */             } 
/* 143 */             if (operator.equals("<")) {
/* 144 */               LessThanFilter falseFilter = new LessThanFilter(item.trim());
/* 145 */               falseFilter.setFilter(value);
/* 146 */               if (conjunction.get(key) == null)
/* 147 */                 conjunction.put(key, new ArrayList<>()); 
/* 149 */               ((List<LessThanFilter>)conjunction.get(key)).add(falseFilter);
/*     */             } 
/*     */           } else {
/* 152 */             m = notIncludePattern.matcher(item.trim());
/* 153 */             if (m.matches()) {
/* 154 */               String key = m.group(1);
/* 155 */               NotIncludeFilter notIncludeFilter = new NotIncludeFilter(item.trim());
/* 156 */               notIncludeFilter.setFilter(key);
/* 157 */               if (conjunction.get(key) == null)
/* 158 */                 conjunction.put(key, new ArrayList<>()); 
/* 160 */               ((List<NotIncludeFilter>)conjunction.get(key)).add(notIncludeFilter);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public List<Byte> acceptsTag(HashMap<String, String> tagList) {
/* 172 */     if (tagList.size() == 0)
/* 173 */       return null; 
/* 176 */     int hashCode = tagList.hashCode();
/* 177 */     if (this.cache.containsKey(Integer.valueOf(hashCode))) {
/* 178 */       List<Byte> code = (List<Byte>)this.cache.get(Integer.valueOf(hashCode));
/* 179 */       return code;
/*     */     } 
/* 182 */     byte tagGroupCount = 0;
/* 183 */     List<Byte> acceptingTags = new ArrayList<>();
/* 186 */     for (Map<String, List<Filter>> tagSet : this.tagSets) {
/* 189 */       Map<String, Boolean> accepted = new HashMap<>();
/* 190 */       for (Map.Entry<String, List<Filter>> ruleSet : tagSet.entrySet())
/* 191 */         accepted.put(ruleSet.getKey(), null); 
/* 194 */       for (Map.Entry<String, List<Filter>> ruleSet : tagSet.entrySet()) {
/* 195 */         if (accepted.get(ruleSet.getKey()) != null && !((Boolean)accepted.get(ruleSet.getKey())).booleanValue())
/*     */           continue; 
/* 198 */         Boolean acceptedTags = null;
/* 199 */         for (Filter rule : ruleSet.getValue()) {
/* 200 */           for (Map.Entry<String, String> passedInTag : tagList.entrySet()) {
/* 202 */             if (acceptedTags != null && !acceptedTags.booleanValue())
/*     */               continue; 
/* 206 */             if (rule instanceof NotIncludeFilter) {
/* 207 */               acceptedTags = Boolean.valueOf(rule.acceptsValue(passedInTag.getKey()));
/*     */               continue;
/*     */             } 
/* 210 */             if (rule instanceof AreaFilter) {
/* 212 */               accepted.put(ruleSet.getKey(), Boolean.valueOf(true));
/* 213 */               acceptedTags = Boolean.valueOf(true);
/*     */               continue;
/*     */             } 
/* 217 */             if (rule instanceof NodeFilter) {
/* 219 */               accepted.put(ruleSet.getKey(), Boolean.valueOf(true));
/* 220 */               acceptedTags = Boolean.valueOf(true);
/*     */               continue;
/*     */             } 
/* 224 */             if (((String)ruleSet.getKey()).equals(passedInTag.getKey()))
/* 225 */               acceptedTags = Boolean.valueOf(rule.acceptsValue(passedInTag.getValue())); 
/*     */           } 
/*     */         } 
/* 229 */         accepted.put(ruleSet.getKey(), acceptedTags);
/*     */       } 
/* 234 */       boolean ruleAccepted = true;
/* 235 */       for (Map.Entry<String, List<Filter>> tagRule : tagSet.entrySet()) {
/* 237 */         if (!accepted.containsKey(tagRule.getKey())) {
/* 238 */           ruleAccepted = false;
/*     */           break;
/*     */         } 
/* 241 */         Boolean val = accepted.get(tagRule.getKey());
/* 242 */         if (val == null || !val.booleanValue()) {
/* 243 */           ruleAccepted = false;
/*     */           break;
/*     */         } 
/*     */       } 
/* 247 */       if (ruleAccepted)
/* 248 */         acceptingTags.add(Byte.valueOf(tagGroupCount)); 
/* 251 */       tagGroupCount = (byte)(tagGroupCount + 1);
/*     */     } 
/* 254 */     if (acceptingTags != null && acceptingTags.size() > 0) {
/* 255 */       this.cache.put(Integer.valueOf(hashCode), acceptingTags);
/* 256 */       return acceptingTags;
/*     */     } 
/*     */     try {
/* 259 */       this.cache.put(Integer.valueOf(hashCode), (Object)null);
/* 260 */     } catch (Exception e) {
/* 261 */       return acceptingTags;
/*     */     } 
/* 263 */     return null;
/*     */   }
/*     */   
/*     */   public List<Map<String, List<Filter>>> getTagSets() {
/* 270 */     return this.tagSets;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\FilterList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */