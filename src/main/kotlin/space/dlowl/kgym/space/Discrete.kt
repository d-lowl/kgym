package space.dlowl.kgym.space

import kotlin.random.Random

/**
 * A discrete space in :math:`\{ 0, 1, \\dots, n-1 \}`.
 *
 * Example::
 *    >>> Discrete(2)
 */
class Discrete(private val n: Int): Space<Int>() {
    init {
        require(n >= 0)
    }

    private val random: Random = Random.Default

    override fun sample(): Int = random.nextInt(2)

    override fun contains(x: Int): Boolean = x in 0 until n
}