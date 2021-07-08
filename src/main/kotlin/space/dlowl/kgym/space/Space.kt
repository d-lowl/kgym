package space.dlowl.kgym.space

abstract class Space<Element> {
    abstract fun sample(): Element
    abstract fun contains(x: Element): Boolean
}