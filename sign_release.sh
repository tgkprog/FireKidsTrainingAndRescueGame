#!/bin/bash
# Sign release APK script for FireKidsGame
# Uses certificate at /data/rme1/private/s2n/keys/upload-key.jks
# Password from S2n_Jks environment variable or ~/.bashrc

set -e

# Source password from .bashrc if not already set
if [ -z "$S2n_Jks" ]; then
    if [ -f "$HOME/.bashrc" ]; then
        export $(cat "$HOME/.bashrc" | grep "S2n_Jks" | xargs)
    fi
fi

KEYSTORE="/data/rme1/private/s2n/keys/upload-key.jks"
UNSIGNED_APK="android/build/outputs/apk/release/android-release-unsigned.apk"
SIGNED_APK="android/build/outputs/apk/release/android-release-signed.apk"
ALIGNED_APK="android/build/outputs/apk/release/android-release-aligned.apk"
KEY_ALIAS="sel2in_upload"

if [ -z "$S2n_Jks" ]; then
    echo "Error: S2n_Jks not found in environment or ~/.bashrc"
    exit 1
fi

if [ ! -f "$KEYSTORE" ]; then
    echo "Error: Keystore not found at $KEYSTORE"
    exit 1
fi

if [ ! -f "$UNSIGNED_APK" ]; then
    echo "Error: Unsigned APK not found. Run './gradlew android:assembleRelease' first"
    exit 1
fi

echo "=== Signing FireKidsGame Release APK ==="
echo "Keystore: $KEYSTORE"
echo "Key Alias: $KEY_ALIAS"
echo "Unsigned APK: $UNSIGNED_APK"
echo ""

# Try to find apksigner
APKSIGNER=""
if command -v apksigner &> /dev/null; then
    APKSIGNER="apksigner"
elif [ -f "$ANDROID_HOME/build-tools/34.0.0/apksigner" ]; then
    APKSIGNER="$ANDROID_HOME/build-tools/34.0.0/apksigner"
else
    echo "Error: apksigner not found. Install Android SDK Build Tools."
    exit 1
fi

# Sign the APK
echo "Signing APK..."
$APKSIGNER sign --ks "$KEYSTORE" \
    --ks-key-alias "$KEY_ALIAS" \
    --ks-pass env:S2n_Jks \
    --key-pass env:S2n_Jks \
    --out "$SIGNED_APK" \
    "$UNSIGNED_APK"

if [ $? -eq 0 ]; then
    echo ""
    echo "✅ APK signed successfully!"
    echo "Signed APK: $SIGNED_APK"
    ls -lh "$SIGNED_APK"
    echo ""
    
    # Verify signature
    echo "Verifying signature..."
    $APKSIGNER verify --verbose "$SIGNED_APK"
else
    echo "❌ Signing failed"
    exit 1
fi

