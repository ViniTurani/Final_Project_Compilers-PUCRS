#!/usr/bin/env bash
set -euo pipefail

# Usage: ./build.sh source.cmm [output_name]
if [ $# -lt 1 ] || [ "$1" = "-h" ] || [ "$1" = "--help" ]; then
  echo "Usage: $0 <source.cmm> [executable_name]"
  exit 1
fi

SRC="$1"
# strip any leading path and .cmm extension
BASENAME="$(basename "${SRC}" .cmm)"

# if provided, override executable name
if [ $# -ge 2 ]; then
  BASENAME="$2"
fi

ASM="${BASENAME}.s"
OBJ="${BASENAME}.o"
EXE="${BASENAME}"

echo "→ Parsing   $SRC → $ASM"
java Parser "$SRC" > "$ASM"

echo "→ Assembling $ASM → $OBJ"
gcc -m32 -c "$ASM" -o "$OBJ"

echo "→ Linking   $OBJ → $EXE"
gcc -m32 "$OBJ" -nostdlib -o "$EXE"

echo "✅ Built: $EXE"
