# CaosPercLib v1.2.0

### TODOs:
    - LFO anidado: osc[waveindex].ar(osc[waveindex].ar([...]) es costoso y confuso; simplificar a un LFO básico con depth tremolo, manteniendo semántica.
---

### Changelog

- ## v1.2.1
    - CaosEnv
        - Update Compander to avoid DC offset by adding LeakDC.ar to input signal
        - Fixed: Gate ignored in various methods
        - Fixed: envAR method use kr instead of ar
        - Fixed: customSignal method structure to properly use gate and doneAction parameters
        - Fixed: Signal method has wrong Warning message
        - Signal method update evaluations to 'switch' for better performance
        - Fixed: comp method bad Argument tresh breaks API
        - Signal method update to use indexOf(waveform)

- ## v1.2.0
    - General improvements and refactoring of all classes

- ## Previous versions Changelog is lost