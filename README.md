# KGym
[![](https://jitpack.io/v/d-lowl/kgym.svg)](https://jitpack.io/#d-lowl/kgym)

An attempt to (partially) reimplement [OpenAI's gym](https://github.com/openai/gym) for Reinforcement Learning in Kotlin. The intended use is alongside [KotlinDL](https://github.com/JetBrains/KotlinDL).

## Getting started
Get the library, in `build.gradle.kts`:
```
repositories {
    ...
    maven(url = "https://jitpack.io")
}

dependencies {
    ...
    implementation("com.github.d-lowl:kgym:-SNAPSHOT")
}
```
For instructions with other build systems see [JitPack](https://jitpack.io/#d-lowl/kgym).

Basic usage:
```
import space.dlowl.kgym.env.cartpole.CartPoleEnv

val gym = CartPoleEnv()
gym.reset()
for (i in 0..10) {
    println(gym.step(i.mod(2)))
}
```
