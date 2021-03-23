package org.dxworks.jenkinsminer

import com.google.api.client.http.HttpRequestInitializer
import org.dxworks.utils.java.rest.client.RestClient

abstract class AbstractJenkinsClient(apiBasePath: String, httpRequestInitializer: HttpRequestInitializer? = null): RestClient(apiBasePath, httpRequestInitializer) {
    override fun getApiPath(vararg pathVariables: String): String {
        return super.getApiPath(*pathVariables, "api", "json")
    }

    override fun getApiPath(variableValues: Map<String, String>, vararg pathVariables: String): String {
        return super.getApiPath(variableValues, *pathVariables, "api", "json")
    }
}
