package org.dxworks.jenkinsminer

import com.google.api.client.http.GenericUrl
import com.google.api.client.http.HttpRequestInitializer
import org.dxworks.jenkinsminer.response.JenkinsBuildsContainer
import org.dxworks.jenkinsminer.response.JenkinsJobContainer

class JenkinsService(path: String, httpRequestInitializer: HttpRequestInitializer? = null) : AbstractJenkinsClient(path, httpRequestInitializer) {

    fun getTopJobsReferences(): List<String> {
        val response = httpClient.get(GenericUrl(getApiPath())).parseAs(JenkinsJobContainer::class.java)

        return response.jobs.mapNotNull { it.url }
    }

    fun getAllJobsReferences(): List<String> = getTopJobsReferences().mapNotNull { getAllJobsReferences(it) }.flatten()

    fun getAllJobsReferences(jobContainer: String): List<String> {
        println("getting jobs for $jobContainer")
        val response = httpClient.get(GenericUrl(jobContainer + "api/json")).parseAs(JenkinsJobContainer::class.java)

        return response.jobs.mapNotNull { it.url }
    }

    fun getAllJobs() =
        getAllJobsReferences().mapNotNull {
            println("getting builds for $it")
            httpClient.get(GenericUrl(it + "api/json")).parseAs(JenkinsBuildsContainer::class.java)
        }
}
