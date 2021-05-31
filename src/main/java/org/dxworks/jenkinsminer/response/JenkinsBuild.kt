package org.dxworks.jenkinsminer.response

import com.google.api.client.json.GenericJson
import com.google.api.client.util.ArrayMap
import com.google.api.client.util.Key
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class JenkinsBuild: GenericJson() {
    @Key("fullDisplayName")
    var name: String? = null

    @Key("number")
    var id: Int = -1

    @Key("duration")
    var duration: Int? = null

    @Key("timestamp")
    var timestamp_miliseconds: Long = 0

    val timestamp: String? by lazy{
        LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp_miliseconds), ZoneId.systemDefault()).toString()
    }

    @Key("result")
    var result: String? = null

    val event: String? by lazy {
        (actions.find {
            it["_class"] == "hudson.model.CauseAction"
        }?.get("causes") as ArrayList<ArrayMap<String, String>>).first()["shortDescription"];
    }
    val commitId: String? by lazy {
        ((actions.find {
            it["_class"] == "hudson.plugins.git.util.BuildData"
        }
            ?.get("lastBuiltRevision") as? ArrayMap<String, Any>)?.get("branch") as? ArrayList<ArrayMap<String, String>>)?.first()
            ?.get("SHA1");
    }
    val branch: String? by lazy {
        ((actions.find {
            it["_class"] == "hudson.plugins.git.util.BuildData"
        }
            ?.get("lastBuiltRevision") as? ArrayMap<String, Any>)?.get("branch") as? ArrayList<ArrayMap<String, String>>)?.first()
            ?.get("name");
    }

    @Key("url")
    var url: String? = null

    val parentName: String? by lazy {
        url?.substringAfterLast("job/")?.substringBefore("/") ?: null
    }

    @Key("actions")
    var actions: List<GenericJson> = emptyList()

    override fun toString(): String = " $name: event : $event, commitId: $commitId, branch: $branch"
}