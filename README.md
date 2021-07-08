# KGym

An attempt to (partially) reimplement [OpenAI's gym](https://github.com/openai/gym) for Reinforcement Learning in Kotlin. The intended use is alongside [KotlinDL](https://github.com/JetBrains/KotlinDL).

## Getting started
Follow instructions on getting the package from [JitPack](https://jitpack.io/#d-lowl/kgym).

```
import space.dlowl.kgym.env.cartpole.CartPoleEnv

val gym = CartPoleEnv()
gym.reset()
for (i in 0..10) {
    println(gym.step(i.mod(2)))
}
```
