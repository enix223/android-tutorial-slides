#!/bin/bash

set -e

pnpm build

ossutil rm -rf "oss://ey-course/androidv"
ossutil cp -r "dist/" "oss://ey-course/androidv"
