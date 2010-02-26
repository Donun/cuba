/*
 * Copyright (c) 2008 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.

 * Author: Konstantin Krivopustov
 * Created: 22.05.2009 18:29:30
 *
 * $Id$
 */
package com.haulmont.cuba.core.app;

import com.haulmont.cuba.core.Locator;
import com.haulmont.cuba.core.global.ConfigProvider;
import com.haulmont.cuba.core.global.GlobalConfig;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * ReportEngine MBean implementation.
 * <p>
 * This MBean is intended for compiling and executing of Jasper Reports
 */
@ManagedBean(ReportEngineAPI.NAME)
public class ReportEngine implements ReportEngineMBean, ReportEngineAPI
{
    private static final String SRC_EXT = ".jrxml";
    private static final String JASPER_EXT = ".jasper";

    private String srcRootPath;
    private String tmpRootPath;

    @Inject
    private FreemarkerProcessor freemarkerProcessor;

    private Log log = LogFactory.getLog(ReportEngine.class);

    @Inject
    public ReportEngine(ConfigProvider configProvider) {
        GlobalConfig config = configProvider.doGetConfig(GlobalConfig.class);
        srcRootPath = config.getConfDir() + "/";
        tmpRootPath = config.getTempDir() + "/";
    }

    public JasperReport getJasperReport(String name) {
        File jasperFile = compileJasperReport(name);
        try {
            return (JasperReport) JRLoader.loadObject(jasperFile);
        } catch (JRException e) {
            throw new RuntimeException("Unable to load compiled report " + name, e);
        }
    }

    public JasperPrint executeJasperReport(String name, Map<String, Object> params, JRDataSource dataSource) {
        log.debug("Executing report " + name);
        JasperReport report = getJasperReport(name);
        JasperPrint print;
        try {
            if (dataSource != null) {
                print = JasperFillManager.fillReport(report, params, dataSource);
            } else {
                DataSource ds = Locator.getDataSource();
                Connection conn = ds.getConnection();
                try {
                    print = JasperFillManager.fillReport(report, params, conn);
                } finally {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        log.warn("Error closing connection", e);
                    }
                }
            }
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return print;
    }

    private File compileJasperReport(String name) {
        File srcFile = new File(srcRootPath, name + SRC_EXT);
        if (!srcFile.exists())
            throw new RuntimeException("Report source file " + srcFile + " not found");

        long lm = srcFile.lastModified();

        File jasperFile = new File(tmpRootPath, name + JASPER_EXT);

        if (jasperFile.exists() && jasperFile.lastModified() >= lm) {
            log.debug("Compiled report " + name + " is up to date");
            return jasperFile;
        }

        log.debug("Compile report " + name);

        String destPath = tmpRootPath + name + JASPER_EXT;
        File destFile = new File(destPath);
        destFile.getParentFile().mkdirs();
        try {
            JasperCompileManager.compileReportToFile(srcFile.getAbsolutePath(), destPath);
        } catch (JRException e) {
            throw new RuntimeException("Unable to compile report " + name, e);
        }
        jasperFile.setLastModified(lm);
        return jasperFile;
    }

    public String processFreemarkerTemplate(String name, Map<String, Object> params) {
        log.debug("Processing " + name);
        return freemarkerProcessor.processTemplate(name, params);
    }
}
