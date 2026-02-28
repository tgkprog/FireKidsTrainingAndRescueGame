#!/bin/bash
# Build script for FireKidsGame Android
# Builds both debug and signed release APKs

set -e

# Source password from .bashrc if not already set
if [ -z "$S2n_Jks" ]; then
    if [ -f "$HOME/.bashrc" ]; then
        export $(cat "$HOME/.bashrc" | grep "S2n_Jks" | xargs)
    fi
fi

echo "==================================="
echo "FireKidsGame Android Build Script"
echo "==================================="
echo ""

# Check environment
if [ -z "$S2n_Jks" ]; then
    echo "⚠️  Warning: S2n_Jks not set in environment or ~/.bashrc"
    echo "    Release APK will not be signed automatically"
    echo ""
fi

# Build debug APK
echo "📦 Building Debug APK..."
./gradlew android:assembleDebug --no-daemon

if [ $? -eq 0 ]; then
    echo "✅ Debug build successful"
    ls -lh android/build/outputs/apk/debug/android-debug.apk
    echo ""
else
    echo "❌ Debug build failed"
    exit 1
fi

# Build release APK
echo "📦 Building Release APK..."
./gradlew android:assembleRelease --no-daemon

if [ $? -eq 0 ]; then
    echo "✅ Release build successful"
    
    # Check if signed
    if [ -f "android/build/outputs/apk/release/android-release.apk" ]; then
        echo "✅ Signed Release APK created"
        ls -lh android/build/outputs/apk/release/android-release.apk
        
        # Verify signature
        echo ""
        echo "🔐 Verifying signature..."
        if jarsigner -verify android/build/outputs/apk/release/android-release.apk > /dev/null 2>&1; then
            echo "✅ APK signature verified"
        else
            echo "⚠️  Could not verify signature"
        fi
    elif [ -f "android/build/outputs/apk/release/android-release-unsigned.apk" ]; then
        echo "⚠️  Unsigned Release APK created"
        ls -lh android/build/outputs/apk/release/android-release-unsigned.apk
        echo "   Sign it before distribution"
    fi
    echo ""
else
    echo "❌ Release build failed"
    exit 1
fi

echo "==================================="
echo "✅ Build Complete!"
echo "==================================="
echo ""
echo "Output files:"
echo "  Debug:   android/build/outputs/apk/debug/android-debug.apk"
if [ -f "android/build/outputs/apk/release/android-release.apk" ]; then
    echo "  Release: android/build/outputs/apk/release/android-release.apk (signed)"
else
    echo "  Release: android/build/outputs/apk/release/android-release-unsigned.apk"
fi
echo ""
