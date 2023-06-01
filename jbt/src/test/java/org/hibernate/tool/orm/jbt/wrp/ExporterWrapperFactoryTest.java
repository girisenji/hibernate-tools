package org.hibernate.tool.orm.jbt.wrp;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Properties;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.api.export.ArtifactCollector;
import org.hibernate.tool.api.export.ExporterConstants;
import org.hibernate.tool.internal.export.cfg.CfgExporter;
import org.hibernate.tool.internal.export.common.DefaultArtifactCollector;
import org.hibernate.tool.internal.export.ddl.DdlExporter;
import org.hibernate.tool.orm.jbt.util.ConfigurationMetadataDescriptor;
import org.hibernate.tool.orm.jbt.wrp.ExporterWrapperFactory.ExporterWrapper;
import org.junit.jupiter.api.Test;

public class ExporterWrapperFactoryTest {
	
	private ExporterWrapper exporterWrapper = null;
	
	@Test
	public void testCreate() {
		exporterWrapper = ExporterWrapperFactory.create(DdlExporter.class.getName());
		assertNotNull(exporterWrapper);
		Object wrappedExporter = exporterWrapper.getWrappedObject();
		assertTrue(wrappedExporter instanceof DdlExporter);
	}

	@Test
	public void testSetConfiguration() throws Exception {
		exporterWrapper = ExporterWrapperFactory.create(CfgExporter.class.getName());
		Properties properties = new Properties();
		Configuration configuration = new Configuration();
		configuration.setProperties(properties);
		exporterWrapper.setConfiguration(configuration);	
		assertSame(properties, ((CfgExporter)exporterWrapper.getWrappedObject()).getCustomProperties());
		Object object = exporterWrapper.getWrappedObject().getProperties().get(
				ExporterConstants.METADATA_DESCRIPTOR);
		assertNotNull(object);
		assertTrue(object instanceof ConfigurationMetadataDescriptor);
		ConfigurationMetadataDescriptor configurationMetadataDescriptor = (ConfigurationMetadataDescriptor)object;
		Field field = ConfigurationMetadataDescriptor.class.getDeclaredField("configuration");
		field.setAccessible(true);
		object = field.get(configurationMetadataDescriptor);
		assertNotNull(object);
		assertTrue(object instanceof Configuration);
		assertSame(object, configuration);
	}
	
	@Test
	public void testSetArtifactCollector() {
		exporterWrapper = ExporterWrapperFactory.create(DdlExporter.class.getName());
		ArtifactCollector artifactCollector = new DefaultArtifactCollector();
		assertNotSame(artifactCollector, exporterWrapper.getWrappedObject().getProperties().get(ExporterConstants.ARTIFACT_COLLECTOR));
		exporterWrapper.setArtifactCollector(artifactCollector);
		assertSame(artifactCollector, exporterWrapper.getWrappedObject().getProperties().get(ExporterConstants.ARTIFACT_COLLECTOR));
	}
	
	@Test
	public void testSetOutputDirectory() {
		File file = new File("");
		exporterWrapper = ExporterWrapperFactory.create(DdlExporter.class.getName());
		assertNotSame(file, exporterWrapper.getWrappedObject().getProperties().get(ExporterConstants.DESTINATION_FOLDER));		
		exporterWrapper.setOutputDirectory(file);
		assertSame(file, exporterWrapper.getWrappedObject().getProperties().get(ExporterConstants.DESTINATION_FOLDER));		
	}
	
}