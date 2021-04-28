package org.dxworks.jenkinsminer.response

import com.google.api.client.json.GenericJson
import com.google.api.client.util.Key

class JenkinsBuildsContainer: GenericJson() {
    @Key
    var builds: List<JenkinsBuildReference> = emptyList()
}
