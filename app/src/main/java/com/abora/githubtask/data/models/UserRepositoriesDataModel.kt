package com.abora.githubtask.data.models

import com.google.gson.annotations.SerializedName

data class UserRepositoriesDataModel(

    @SerializedName("total_count") val total_count: Int,
    @SerializedName("incomplete_results") val incomplete_results: Boolean,
    @SerializedName("items") val userRepositoriesData: List<UserRepositoriesData>
)


data class UserRepositoriesData(

    @SerializedName("id") val id: Int,
    @SerializedName("node_id") val node_id: String,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val full_name: String,
    @SerializedName("private") val private: Boolean,
    @SerializedName("owner") val owner: Owner,
    @SerializedName("html_url") val html_url: String,
    @SerializedName("description") val description: String,
    @SerializedName("fork") val fork: Boolean,
    @SerializedName("url") val url: String,
    @SerializedName("forks_url") val forks_url: String,
    @SerializedName("keys_url") val keys_urls_url: String,
    @SerializedName("collaborators_url") val collaborators_url: String,
    @SerializedName("teams_url") val teams_url: String,
    @SerializedName("hooks_url") val hooks_url: String,
    @SerializedName("issue_events_url") val issue_events_url: String,
    @SerializedName("events_url") val events_url: String,
    @SerializedName("assignees_url") val assignees_url: String,
    @SerializedName("branches_url") val branches_url: String,
    @SerializedName("tags_url") val tags_url: String,
    @SerializedName("blobs_url") val blobs_url: String,
    @SerializedName("git_tags_url") val git_tags_url: String,
    @SerializedName("git_refs_url") val git_refs_url: String,
    @SerializedName("trees_url") val trees_url: String,
    @SerializedName("statuses_url") val statuses_url: String,
    @SerializedName("languages_url") val languages_url: String,
    @SerializedName("stargazers_url") val stargazers_url: String,
    @SerializedName("contributors_url") val contributors_url: String,
    @SerializedName("subscribers_url") val subscribers_url: String,
    @SerializedName("subscription_url") val subscription_url: String,
    @SerializedName("commits_url") val commits_url: String,
    @SerializedName("git_commits_url") val git_commits_url: String,
    @SerializedName("comments_url") val comments_url: String,
    @SerializedName("issue_comment_url") val issue_comment_url: String,
    @SerializedName("contents_url") val contents_url: String,
    @SerializedName("compare_url") val compare_url: String,
    @SerializedName("merges_url") val merges_url: String,
    @SerializedName("archive_url") val archive_url: String,
    @SerializedName("downloads_url") val downloads_url: String,
    @SerializedName("issues_url") val issues_url: String,
    @SerializedName("pulls_url") val pulls_url: String,
    @SerializedName("milestones_url") val milestones_url: String,
    @SerializedName("notifications_url") val notifications_url: String,
    @SerializedName("labels_url") val labels_url: String,
    @SerializedName("releases_url") val releases_url: String,
    @SerializedName("deployments_url") val deployments_url: String,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("updated_at") val updated_at: String,
    @SerializedName("pushed_at") val pushed_at: String,
    @SerializedName("git_url") val git_url: String,
    @SerializedName("ssh_url") val ssh_url: String,
    @SerializedName("clone_url") val clone_url: String,
    @SerializedName("svn_url") val svn_url: String,
    @SerializedName("homepage") val homepage: String,
    @SerializedName("size") val size: Int,
    @SerializedName("stargazers_count") val stargazers_count: String,
    @SerializedName("watchers_count") val watchers_count: Int,
    @SerializedName("language") val language: String,
    @SerializedName("has_issues") val has_issues: Boolean,
    @SerializedName("has_projects") val has_projects: Boolean,
    @SerializedName("has_downloads") val has_downloads: Boolean,
    @SerializedName("has_wiki") val has_wiki: Boolean,
    @SerializedName("has_pages") val has_pages: Boolean,
    @SerializedName("forks_count") val forks_count: String,
    @SerializedName("mirror_url") val mirror_url: String,
    @SerializedName("archived") val archived: Boolean,
    @SerializedName("disabled") val disabled: Boolean,
    @SerializedName("open_issues_count") val open_issues_count: String,
    @SerializedName("license") val license: License,
    @SerializedName("allow_forking") val allow_forking: Boolean,
    @SerializedName("is_template") val is_template: Boolean,
    @SerializedName("topics") val topics: List<String>,
    @SerializedName("visibility") val visibility: String,
    @SerializedName("forks") val forks: Int,
    @SerializedName("open_issues") val open_issues: Int,
    @SerializedName("watchers") val watchers: Int,
    @SerializedName("default_branch") val default_branch: String,
    @SerializedName("permissions") val permissions: Permissions,
    @SerializedName("score") val score: Int
)

data class License(
    @SerializedName("key") val key: String,
    @SerializedName("name") val name: String,
    @SerializedName("spdx_id") val spdx_id: String,
    @SerializedName("url") val url: String,
    @SerializedName("node_id") val node_id: String

)

data class Owner(

    @SerializedName("login") val login: String,
    @SerializedName("id") val id: Int,
    @SerializedName("node_id") val node_id: String,
    @SerializedName("avatar_url") val avatar_url: String,
    @SerializedName("gravatar_id") val gravatar_id: String,
    @SerializedName("url") val url: String,
    @SerializedName("html_url") val html_url: String,
    @SerializedName("followers_url") val followers_url: String,
    @SerializedName("following_url") val following_url: String,
    @SerializedName("gists_url") val gists_url: String,
    @SerializedName("starred_url") val starred_url: String,
    @SerializedName("subscriptions_url") val subscriptions_url: String,
    @SerializedName("organizations_url") val organizations_url: String,
    @SerializedName("repos_url") val repos_url: String,
    @SerializedName("events_url") val events_url: String,
    @SerializedName("received_events_url") val received_events_url: String,
    @SerializedName("type") val type: String,
    @SerializedName("site_admin") val site_admin: Boolean
)


data class Permissions(

    @SerializedName("admin") val admin: Boolean,
    @SerializedName("maintain") val maintain: Boolean,
    @SerializedName("push") val push: Boolean,
    @SerializedName("triage") val triage: Boolean,
    @SerializedName("pull") val pull: Boolean
)