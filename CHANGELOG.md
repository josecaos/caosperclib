# CaosPercLib v1.2.2

### TODOs:
---

### Changelog

- ## v1.2.2
    - README.md
        - Add Available Classes section
    - CaosEnv
        - Add Documentation files for Help Browser
        - Update 'customSignal' method to accept Functions or UGENs as non optional input signal
    - CaosKick
        - Revamp 'signal' method for better timbre control
        - Update Documentation files for Help Browser
    - Update version number in all files

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
        - Improved robot method
        - Arguments updated in 'signal' for semantic consistency

- ## v1.2.0
    - General improvements and refactoring of all classes

- ## Previous versions Changelog is lost