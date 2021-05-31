package org.dxworks.jenkinsminer.response

import com.google.api.client.json.GenericJson
import com.google.api.client.util.Key
import java.util.Collections.*

class JenkinsJobContainer : GenericJson() {
    
    @Key
    var jobs: List<JenkinsJobReference> = emptyList()

    fun isContainer() = jobs.isNotEmpty()
}