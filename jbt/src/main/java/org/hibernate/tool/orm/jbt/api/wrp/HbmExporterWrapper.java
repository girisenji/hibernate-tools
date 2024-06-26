package org.hibernate.tool.orm.jbt.api.wrp;

import java.io.File;
import java.util.Map;

public interface HbmExporterWrapper extends Wrapper {
	
	void start();
	
	File getOutputDirectory();
	
	void setOutputDirectory(File f);
	
	void exportPOJO(Map<Object, Object> map, Object pojoClass);
	
	void setExportPOJODelegate(Object delegate);

}
