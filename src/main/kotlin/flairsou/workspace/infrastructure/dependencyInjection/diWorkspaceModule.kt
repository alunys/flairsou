package flairsou.workspace.infrastructure.dependencyInjection

import flairsou.workspace.domain.repository.IWorkspaceRepository
import flairsou.workspace.infrastructure.filesystem.persistence.repository.WorkspaceRepository
import org.koin.dsl.module

val diWorkspaceModule = module {

    single { WorkspaceRepository() as IWorkspaceRepository}


}