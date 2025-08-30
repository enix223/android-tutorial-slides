mkdir -p dist

# Loop through each slide directory and build it
for dir in slides/lesson-*; do
if [ -d "$dir" ]; then
    full_slide_name=$(basename "$dir")
    slide_name=$(echo "$full_slide_name" | cut -d'-' -f1-2)
    
    # The base URL must be set for each slide deck
    pnpm --filter "$full_slide_name" run build --base /android-tutorial-slides/$slide_name/
    
    # Copy the built files to the single dist folder
    mkdir -p dist/$slide_name
    cp -r "$dir"/dist/* dist/$slide_name
fi
done