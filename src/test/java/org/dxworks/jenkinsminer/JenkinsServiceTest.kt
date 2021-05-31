package org.dxworks.jenkinsminer

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class JenkinsClientIT {

    @Test
    fun getSpecificJob() {
        val jenkinsService = JenkinsService("https://builds.apache.org/job/Kafka/job/kafka-2.4-jdk8/")
        val build = jenkinsService.getAllBuilds().last();

        assertNotNull(build)
        assertEquals("Kafka » kafka-2.4-jdk8 #18", build.name)
        assertEquals("kafka-2.4-jdk8", build.parentName)
        assertEquals(18, build.id)
        assertEquals(39223480, build.duration)
        assertEquals("2020-11-21T20:00:08.611", build.timestamp)
        assertEquals("SUCCESS", build.result)
        assertEquals("Started by an SCM change", build.event)
        assertEquals("cb60abc1df281b16eb359be0e6df925932e33418", build.commitId)
        assertEquals("refs/remotes/origin/2.4", build.branch)
        assertEquals("https://ci-builds.apache.org/job/Kafka/job/kafka-2.4-jdk8/18/", build.url)
    }

    @Test
    fun getAllJobsReferences() {
        val jenkinsService = JenkinsService("https://ci-builds.apache.org/job/Accumulo/")
        val allBuilds = jenkinsService.getAllBuildsReferences();

        assertNotNull(allBuilds)
        assertEquals(61, allBuilds.size)
    }
}
