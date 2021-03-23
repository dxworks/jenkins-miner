package org.dxworks.jenkinsminer

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class JenkinsClientIT {

    private val jenkinsService = JenkinsService("https://ci-builds.apache.org")

    @Test
    fun getAllJobs() {
        val allJobs = jenkinsService.getAllJobs()

        assertNotNull(allJobs)
    }
}
