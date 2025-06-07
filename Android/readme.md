In Progress...

Android Stocks App project.

Architecture:

App uses 3 different Architectures. This simulates an actual project which is partially written using Activities and Fragments and other part is upgraded to use Compose.

Details:

1.  Login and Stock List modules use:
    -  Compose Navigation
    -  Hilt
    -  Cold Flows
    -  Jetpack Compose
    -  State Flow

2.  Stock Details Module use:
    -  Compose Navigation
    -  Hilt 
    -  Coroutines
    -  Jetpack Compose
    -  State Flow

3.  Stock News and News Details modules are written in classical Android way using:
    -  Activities and Fragments
    -  Hilt
    -  RxJava
    -  Data binding
    -  Live Data/ State Flow

All the 3 Modules share a single nav graph. Compose modules use their own child nav graph.

* Other Modules include:
    -  Data - Has Api implementations
    -  Domain - Usecases and interactors
    -  Navigation (TODO)
    -  App
    -  Core - Base classes and helpers (TODO)
    -  Network - Network related helpers (TODO)

