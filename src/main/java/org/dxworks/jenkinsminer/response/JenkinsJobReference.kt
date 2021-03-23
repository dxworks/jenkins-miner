package org.dxworks.jenkinsminer.response

import com.google.api.client.json.GenericJson
import com.google.api.client.util.Key

class JenkinsJobReference : GenericJson() {
    @Key
    var name: String? = null

    @Key
    var url: String? = null
}
