package space.dlowl.kgym.space

import kotlin.random.Random

class Box(private val low: List<Double>, private val high: List<Double>): Space<List<Double>>() {
    init {
        require(low.size == high.size)
    }
    override fun sample(): List<Double> =
        low.zip(high).map { Random.nextDouble(it.first, it.second) }

    override fun contains(x: List<Double>): Boolean =
        low.size == x.size && high.size == x.size && low.zip(x).all { it.first <= it.second } && x.zip(high).all { it.first <= it.second }

}