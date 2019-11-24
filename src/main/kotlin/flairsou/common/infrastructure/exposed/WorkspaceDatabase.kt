package flairsou.common.infrastructure.exposed

/***
 * @param String url ex: "jdbc:sqlite:/data/flairsou.db"
 */
class WorkspaceDatabase(override val path: String) :AbstractDatabase(path)