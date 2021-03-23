package org.dxworks.jenkinsminer.response

import com.google.api.client.json.GenericJson
import com.google.api.client.util.Key

class JenkinsBuildReference: GenericJson() {
    @Key
    var number: Long = 0

    @Key
    lateinit var url: String
}
