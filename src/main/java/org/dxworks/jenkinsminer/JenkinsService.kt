package org.dxworks.jenkinsminer

import com.google.api.client.http.GenericUrl
import com.google.api.client.json.GenericJson
import com.google.api.client.http.HttpRequestInitializer
import org.dxworks.jenkinsminer.response.JenkinsBuild
import org.dxworks.jenkinsminer.response.JenkinsBuildsContainer
import org.dxworks.utils.java.rest.client.RestClient

class JenkinsService(private val path: String, httpRequestInitializer: HttpRequestInitializer? = null) :
    RestClient(path, httpRequestInitializer) {

    fun getAllJobsReferences(container: String = path): List<String> {

        return try {
            val response = httpClient.get(GenericUrl(container + "api/json")).parseAs(GenericJson::class.java)

            when {
                response["builds"] != null -> {
                    println("e un job simplu $container")
                    listOf(container)
                }
                response["jobs"] != null -> {
                    println("e un job complex $container")
                    (response["jobs"] as List<Any>).mapNotNull {
                        //println(it)
                        getAllJobsReferences((it as Map<String, Any>)["url"] as String)
                    }.flatten()
                }
                else -> {
                    emptyList()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    fun getAllBuildsReferences(container: String = path): List<String> {
        val jobs = getAllJobsReferences(container)
        return jobs.flatMap  { job ->
            try {
                println("getting builds for $job")
                httpClient.get(GenericUrl(job + "api/json"))
                    .parseAs(JenkinsBuildsContainer::class.java).builds.map { it.url }
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
    }

    fun getAllBuilds(container: String = path) =
        getAllBuildsReferences(container).mapNotNull {
            try {
                println ("getting build info for $it")
                httpClient.get(GenericUrl(it + "api/json")).parseAs(JenkinsBuild::class.java)
            }
            catch ( e : Exception) {
                e.printStackTrace()
                null
            }
        }
}
