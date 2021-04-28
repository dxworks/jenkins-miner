package org.dxworks.jenkinsminer

fun main() {

    val jenkinsService = JenkinsService("https://builds.apache.org/job/Kafka/")
    val builds = jenkinsService.getAllBuilds()
    builds.forEach { println(it) }

}
