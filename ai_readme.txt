AI ASSISTANT GUIDELINES FOR FIREKIDSGAME
==========================================

CRITICAL BUILD RULES:
---------------------

1. ALWAYS use ./build1 script for building Android releases
   - Default command: ./build1 1
   - This automatically increments version AND builds
   - DO NOT manually edit version numbers then build separately

2. Version bump types:
   - ./build1 1    = patch (versionCode +1, versionName x.y.Z+1)
   - ./build1 2    = minor (versionCode +1, versionName x.Y+1.0)
   - ./build1 3    = major (versionCode +1, versionName X+1.0.0)
   - Add 'd' for debug: ./build1 1 d

3. NEVER manually increment version then build
   - Bad: Edit build.gradle, then ./gradlew assembleRelease
   - Good: ./build1 1

CURRENT CONFIGURATION (as of 2026-02-28):
------------------------------------------
- versionCode: 47
- versionName: 1.46.1
- targetSdkVersion: 35
- compileSdkVersion: 35
- libGDX: 1.13.0 (supports 16KB page sizes)
- Android Gradle Plugin: 8.7.3
- Gradle: 8.9

16KB PAGE SIZE SUPPORT:
-----------------------
- libGDX 1.13.0+ has native 16KB support
- useLegacyPackaging = false configured
 - 16KB page size property declared in manifest
- 16KB page size property declared in manifest

PLAY STORE REQUIREMENTS:
------------------------
- Must target API 35+
- Must support 16KB page sizes (Android 15+)
- versionCode must be higher than last uploaded version

DO NOT:
-------
- Manually edit version numbers
- Build without using ./build1
- Downgrade libGDX version
- Remove 16KB configuration
