package com.management_system.eureka_server.web;

import com.management_system.utilities.config.meta_data.SystemConfigKeyName;
import com.management_system.utilities.config.meta_data.SystemMetaData;
import com.management_system.utilities.utils.SystemConfigEnvUtils;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.Http2;
import org.springframework.boot.web.server.Ssl;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class WebServerConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
    final SystemConfigEnvUtils credentialsUtils;

    public WebServerConfig(SystemConfigEnvUtils credentialsUtils) {
        this.credentialsUtils = credentialsUtils;
    }

    @Override
    public void customize(TomcatServletWebServerFactory server) {
        Ssl ssl = new Ssl();
        ssl.setKeyStore(SystemMetaData.SSL_KEYSTORE_FILE_PATH);
        ssl.setKeyStorePassword(credentialsUtils.getCredentials(SystemConfigKeyName.SSL_KEYSTORE_PASSWORD));
        ssl.setKeyStoreType(credentialsUtils.getCredentials(SystemConfigKeyName.SSL_KEYSTORE_TYPE));
        ssl.setKeyAlias(credentialsUtils.getCredentials(SystemConfigKeyName.SSL_KEYSTORE_ALIAS));

        Http2 http2 = new Http2();
        http2.setEnabled(true);

        server.setSsl(ssl);
        server.setHttp2(http2);
        server.setPort(Integer.parseInt(credentialsUtils.getCredentials(SystemConfigKeyName.EUREKA_SERVER_PORT).trim()));
    }
}
