package space.dlowl.kgym.env.cartpole

import space.dlowl.kgym.env.Env
import space.dlowl.kgym.env.EnvStep
import space.dlowl.kgym.space.Box
import space.dlowl.kgym.space.Discrete
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.random.Random

class CartPoleEnv: Env<Int, List<Double>>() {
    private val gravity = 9.8
    private val cartMass = 1.0
    private val poleMass = 0.1
    private val totalMass = cartMass + poleMass
    private val length = 0.5 // actually half the pole's length
    private val poleMassLength = poleMass * length
    private val forceMag = 10.0
    private val tau = 0.02 // seconds between state updates

    // Angle at which to fail the episode
    private val thetaThresholdRadians = 12 * 2 * PI / 360
    private val xThreshold = 2.4

    private val high = listOf(
        xThreshold * 2,
        Double.MAX_VALUE,
        thetaThresholdRadians * 2,
        Double.MAX_VALUE
    )
    private val low = high.map { -it }
    private val actionSpace = Discrete(2)
    private val observationSpace = Box(low, high)
    private var stepsBeyondDone: Int? = null

    private lateinit var state: List<Double>

    override fun step(action: Int): EnvStep<List<Double>> {
        require(actionSpace.contains(action)) { "Action $action is invalid" }
        var x = state[0]
        var xDot = state[1]
        var theta = state[2]
        var thetaDot = state[3]
        val force = if (action == 1) forceMag else -forceMag
        val cosTheta = cos(theta)
        val sinTheta = sin(theta)

        val temp = (force + poleMassLength * thetaDot.pow(2) * sinTheta) / totalMass
        val thetaAcc = (gravity * sinTheta - cosTheta * temp) / (length * (4.0 / 3.0 - poleMass * cosTheta.pow(2) / totalMass))
        val xAcc = temp - poleMassLength * thetaAcc * cosTheta / totalMass

        x += tau * xDot
        xDot += tau * xAcc
        theta += tau * thetaDot
        thetaDot += tau * thetaAcc

        state = listOf(x, xDot, theta, thetaDot)
        val done = x < -xThreshold || x > xThreshold || theta < thetaThresholdRadians || theta > thetaThresholdRadians
        var reward = 0.0

        if (!done) {
            reward = 1.0
        } else if (stepsBeyondDone == null) {
            stepsBeyondDone = 0
            reward = 1.0
        } else {
            if (stepsBeyondDone == 0) {
                println("You are calling 'step()' even though this environment has already returned done = True. You should always call 'reset()' once you receive 'done = True' -- any further steps are undefined behavior.")
            }
            stepsBeyondDone = stepsBeyondDone!! + 1
            reward = 0.0
        }

        return EnvStep(
            observation = state,
            reward = reward,
            done = done
        )
    }

    override fun reset(): List<Double> {
        state = List(4) { Random.nextDouble(-0.05, 0.05) }
        stepsBeyondDone = null
        return state
    }
}