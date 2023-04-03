package org.jfree.chart.labels;

import org.jfree.data.category.CategoryDataset;

public interface CategoryItemLabelGenerator {
  String generateRowLabel(CategoryDataset paramCategoryDataset, int paramInt);
  
  String generateColumnLabel(CategoryDataset paramCategoryDataset, int paramInt);
  
  String generateLabel(CategoryDataset paramCategoryDataset, int paramInt1, int paramInt2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\labels\CategoryItemLabelGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */