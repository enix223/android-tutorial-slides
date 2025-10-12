mkdir -p dist

set -e

slide=$1

# Loop through each slide directory and build it
for dir in slides/lesson-*; do
if [ -d "$dir" ]; then
    full_slide_name=$(basename "$dir")
    slide_name=$(echo "$full_slide_name" | cut -d'-' -f1-2)

    if [[ -n "$slide" && "$slide" !=  "$slide_name" ]]; then
        continue
    fi
    
    # The base URL must be set for each slide deck
    pnpm --filter "$full_slide_name" run build --base /android-tutorial-slides/$slide_name/
    
    # Copy the built files to the single dist folder
    rm -rf "dist/$slide_name"
    mkdir -p "dist/$slide_name"
    cp -r "$dir"/dist/* dist/$slide_name
    
    # export pdf
    pnpm --filter "$full_slide_name" run export

    # upload
    ossutil rm -rf "oss://ey-course/android-tutorial-slides/$slide_name"
    ossutil cp -r "dist/$slide_name" "oss://ey-course/android-tutorial-slides/$slide_name"

fi
done

ossutil cp -rf catalog.html oss://ey-course/index.html
