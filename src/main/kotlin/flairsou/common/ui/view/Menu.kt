package flairsou.common.ui.view

import tornadofx.View
import tornadofx.item
import tornadofx.menu
import tornadofx.menubar

class Menu: View() {
    override val root = menubar {
        menu("File") {
            menu("Connect") {
                item("Facebook")
                item("Twitter")
            }
            item("Save")
            item("Quit")
        }
        menu("Edit") {
            item("Copy")
            item("Paste")
        }
    }
}