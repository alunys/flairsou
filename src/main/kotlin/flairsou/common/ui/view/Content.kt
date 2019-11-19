package flairsou.common.ui.view

import tornadofx.View
import tornadofx.tabpane
import tornadofx.tab

class Content: View() {
    override val root = tabpane {
        tab("Screen 1") {
        }
        tab("Screen 2") {
        }
    }
}