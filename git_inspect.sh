#!/bin/bash


if git diff --name-only HEAD^ HEAD | grep -q "^$1"; then
  echo "1"
else
  echo "0"
fi

