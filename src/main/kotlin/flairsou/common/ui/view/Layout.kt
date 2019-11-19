package flairsou.common.ui.view

import tornadofx.View
import tornadofx.borderpane

class Layout:View(){
    override val root = borderpane() {
        prefHeight = 800.0
        prefWidth = 800.0
        top<Menu>()
        center<Content>()
    }
}