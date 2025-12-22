# CaosPercLib v1.2.0

### TODOs:
- CaosEnv: Check tremolo argument
    - Does not seem to  oscilate in time properly
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
        - Revamp LFO Signal for simpler and lighter performance

- ## v1.2.0
    - General improvements and refactoring of all classes

- ## Previous versions Changelog is lost