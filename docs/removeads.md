**Remove ad-related code and references — Plan

AI : All code is in remote repo, dont worry about losing anything 

Summary of findings
- Files containing ad references (non-exhaustive):
  - todos.txt: mentions "ads" and about page + ad behavior
  - core/src/com/lh9/feg1/firekidsgame/screens/MeetTheTrucksScreen.java: commented `game.getAdsCont().showOrLoadInterstitial();`
  - core/src/com/lh9/feg1/firekidsgame/screens/FitnessScreenOne.java: commented `game.getAdsCont().showInterstitial();`
  - core/src/com/lh9/feg1/firekidsgame/screens/MenuScreen.java: commented `game.getAdsCont().showOrLoadInterstitial();`
  - docs/2026.md: multiple references to Google Mobile Ads and `play-services-ads` versions
  - ai_readme.txt: references AD_ID permission for advertising

Goals
- Remove all ad-related code, dependencies, resources, permissions, and docs references unless explicitly required.
- Replace with safe no-op hooks or feature-flagged code so builds/tests do not break.
- Produce a commit that removes ads and documents remaining manual steps (if any).

High-level steps
1. Inventory
   - Search the repo for these keywords and save list: `ad`, `ads`, `advert`, `advertisement`, `admob`, `mopub`, `adsense`, `doubleclick`, `interstitial`, `rewarded`, `adserver`.
   - Confirm whether any ad SDK dependencies exist in `build.gradle` files (project and `android/` module).
2. Isolate ad integration points
   - Identify `AdsController` / `AdsCont` classes, interfaces, or calls (e.g., `game.getAdsCont()`), and note usages.
3. remove
4. Remove SDK dependencies and resources
   - Remove `play-services-ads` (and other ad SDKs) from `build.gradle` .
   - Remove ad-related permissions (e.g., AD_ID) from Android manifests and docs if no longer required.
   - Remove ad unit IDs, sample IDs, ad-related XML/layout/resources and strings.
5. Clean up code
   - Delete or fully remove ad controller/classes if safe.
   - Remove commented-out ad calls or leave them commented with a clear TODO if preserving history is desired.
6. Docs and QA
   - Update `docs/2026.md`, `ai_readme.txt`, `todos.txt` to remove or mark ads removal notes.
   - Run `./gradlew assembleDebug` (or platform-specific builds) and run tests/manual smoke tests on screens where ads were shown (menus, end-of-section screens, about page).
7. Commit and push
   - Create a branch `remove/ads` and commit changes with clear messages.
   - Include a short README or changelog entry describing what was removed and verification steps.

Priority quick wins (apply first)
- Replace `game.getAdsCont()` usage with `NoOpAdsController` and run a build.
- Remove `play-services-ads` from `build.gradle` .
- Update `todos.txt`, `docs/2026.md`, and `ai_readme.txt` to reflect removal.

Risks & rollback
- Keep a branch for rollback and test on devices/emulators.

Next actions (recommended immediate tasks)
- Implement `NoOpAdsController` and wire it into `core` initialization.
- Remove `play-services-ads` from module `build.gradle` after compile succeeds.

References found during scan (examples)
- todos.txt (mentions ad behavior and about page with ads)
- core/src/.../MeetTheTrucksScreen.java (commented ad call)
- core/src/.../FitnessScreenOne.java (commented ad call)
- core/src/.../MenuScreen.java (commented ad call)
- docs/2026.md (mentions `play-services-ads` and ad system)
- ai_readme.txt (AD_ID permission)

If you'd like, I can now:
- Implement `NoOpAdsController` and wire it into `core` (safe first change), or

