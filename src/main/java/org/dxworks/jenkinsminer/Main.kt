package org.dxworks.jenkinsminer

import com.cdancy.jenkins.rest.JenkinsClient

fun main() {
    print("Hello world!")

    val jenkinsClient = JenkinsClient.builder().build()

    val jobsApi = jenkinsClient.api().jobsApi()

    jobsApi.jobList("").jobs().forEach { it.name() }
    val buildInfo = jobsApi.buildInfo(null, "myJobName", 12)

//    buildInfo.
}
