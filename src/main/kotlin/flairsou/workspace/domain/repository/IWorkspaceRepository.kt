package flairsou.workspace.domain.repository

import flairsou.workspace.domain.model.Workspace

interface IWorkspaceRepository {
    fun findUniqueOne(): Workspace?
    fun save(workspace: Workspace)
}