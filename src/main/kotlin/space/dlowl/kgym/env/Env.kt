package space.dlowl.kgym.env


/**
 * The main OpenAI Gym reimplementation abstract class.
 * It encapsulates an environment with arbitrary behind-the-scenes dynamics.
 * An environment can be partially or fully observed.
 * The main API methods that users of this class need to know are:
 *   step
 *   reset
 *   render
 *   close
 *   seed
 * And set the following attributes:
 *   action_space: The Space object corresponding to valid actions
 *   observation_space: The Space object corresponding to valid observations
 *   reward_range: A tuple corresponding to the min and max possible rewards
 * Note: a default reward range set to [-inf,+inf] already exists. Set it if you want a narrower range.
 * The methods are accessed publicly as "step", "reset", etc...
 */
abstract class Env<Action, Observation> {
    // Set this in SOME subclasses
    var rewardRange: Pair<Double, Double> = Pair(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY)

    // Set these in ALL subclasses
//    action_space = None
//    observation_space = None
    abstract fun step(action: Action): EnvStep<Observation>
    abstract fun reset(): Observation
}

data class EnvStep<Observation>(
    val observation: Observation,
    val reward: Double,
    val done: Boolean,
    val info: Map<String, Any> = mapOf()
)