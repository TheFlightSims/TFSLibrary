package org.opengis.feature.type;

import java.util.Collection;
import java.util.List;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.util.InternationalString;

public interface FeatureTypeFactory {
  Schema createSchema(String paramString);
  
  AssociationDescriptor createAssociationDescriptor(AssociationType paramAssociationType, Name paramName, int paramInt1, int paramInt2, boolean paramBoolean);
  
  AttributeDescriptor createAttributeDescriptor(AttributeType paramAttributeType, Name paramName, int paramInt1, int paramInt2, boolean paramBoolean, Object paramObject);
  
  GeometryDescriptor createGeometryDescriptor(GeometryType paramGeometryType, Name paramName, int paramInt1, int paramInt2, boolean paramBoolean, Object paramObject);
  
  AssociationType createAssociationType(Name paramName, AttributeType paramAttributeType, boolean paramBoolean, List<Filter> paramList, AssociationType paramAssociationType, InternationalString paramInternationalString);
  
  AttributeType createAttributeType(Name paramName, Class<?> paramClass, boolean paramBoolean1, boolean paramBoolean2, List<Filter> paramList, AttributeType paramAttributeType, InternationalString paramInternationalString);
  
  GeometryType createGeometryType(Name paramName, Class<?> paramClass, CoordinateReferenceSystem paramCoordinateReferenceSystem, boolean paramBoolean1, boolean paramBoolean2, List<Filter> paramList, AttributeType paramAttributeType, InternationalString paramInternationalString);
  
  ComplexType createComplexType(Name paramName, Collection<PropertyDescriptor> paramCollection, boolean paramBoolean1, boolean paramBoolean2, List<Filter> paramList, AttributeType paramAttributeType, InternationalString paramInternationalString);
  
  FeatureType createFeatureType(Name paramName, Collection<PropertyDescriptor> paramCollection, GeometryDescriptor paramGeometryDescriptor, boolean paramBoolean, List<Filter> paramList, AttributeType paramAttributeType, InternationalString paramInternationalString);
  
  SimpleFeatureType createSimpleFeatureType(Name paramName, List<AttributeDescriptor> paramList, GeometryDescriptor paramGeometryDescriptor, boolean paramBoolean, List<Filter> paramList1, AttributeType paramAttributeType, InternationalString paramInternationalString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\feature\type\FeatureTypeFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */