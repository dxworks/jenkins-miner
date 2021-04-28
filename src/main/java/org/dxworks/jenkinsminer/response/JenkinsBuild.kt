package org.dxworks.jenkinsminer.response

import com.google.api.client.json.GenericJson
import com.google.api.client.util.ArrayMap
import com.google.api.client.util.Key

class JenkinsBuild: GenericJson() {
    @Key("fullDisplayName")
    var name: String? = null

    @Key("number")
    var id: Int = -1

    @Key("duration")
    var duration: Int? = null

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
            ?.get("lastBuiltRevision") as ArrayMap<String, Any>)?.get("branch") as ArrayList<ArrayMap<String, String>>).first()["SHA1"];
    }
    val branch: String? by lazy {
        ((actions.find {
            it["_class"] == "hudson.plugins.git.util.BuildData"
        }
            ?.get("lastBuiltRevision") as ArrayMap<String, Any>)?.get("branch") as ArrayList<ArrayMap<String, String>>).first()["name"];
    }

    @Key("url")
    var url: String? = null

    @Key("actions")
    var actions: List<GenericJson> = emptyList()

    override fun toString(): String = " $id: event : $event, commitId: $commitId, branch: $branch"
}