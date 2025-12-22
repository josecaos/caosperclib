# CaosPercLib v1.2.0

### TODOs:
    - advertencia de formas: el mensaje menciona ‘tri’ pero la tabla soporta ‘off’, ‘sin’, ‘saw’, ‘pulse’. Ajustar mensaje.
    - búsqueda de forma: usas tag.find([waveform]), debería ser tag.indexOf(waveform).
    - LFO anidado: osc[waveindex].ar(osc[waveindex].ar([...]) es costoso y confuso; simplificar a un LFO básico con depth tremolo, manteniendo semántica.
    - typo en comp: el parámetro se llama tresh, pero usas thresh internamente; unificar a tresh para no romper API.
---

### Changelog

- ## v1.2.1
    - CaosEnv
        - Fixed: Compander to avoid DC offset by adding LeakDC.ar to input signal
        - Fixed: Gate ignored in various methods
        - Fixed: envAR method use kr instead of ar
        - Fixed: customSignal method structure to properly use gate and doneAction parameters

- ## v1.2.0
    - General improvements and refactoring of all classes

- ## Previous versions Changelog is lost