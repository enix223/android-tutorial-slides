<script setup lang="ts">
import { ref } from "vue";

const props = defineProps<{
  /**
   * The source URL of the image.
   */
  src: string;
  /**
   * The alternative text for the image.
   */
  alt?: string;
}>();

// State to control the zoom and pan functionality
const isZoomed = ref(false);
const dragPosition = ref({ x: 0, y: 0 });
const startDragPosition = ref({ x: 0, y: 0 });
const isDragging = ref(false);

/**
 * Handles the mouse down or touch start event to begin panning the zoomed image.
 * @param event The MouseEvent or TouchEvent object.
 */
function startPan(event: MouseEvent | TouchEvent) {
  isDragging.value = true;
  const clientX = "touches" in event ? event.touches[0].clientX : event.clientX;
  const clientY = "touches" in event ? event.touches[0].clientY : event.clientY;

  startDragPosition.value = {
    x: clientX - dragPosition.value.x,
    y: clientY - dragPosition.value.y,
  };

  if ("touches" in event) {
    window.addEventListener("touchmove", onPan);
    window.addEventListener("touchend", stopPan);
  } else {
    window.addEventListener("mousemove", onPan);
    window.addEventListener("mouseup", stopPan);
  }
}

/**
 * Handles the mouse move or touch move event to update the image's position.
 * @param event The MouseEvent or TouchEvent object.
 */
function onPan(event: MouseEvent | TouchEvent) {
  if (!isDragging.value) return;

  const clientX = "touches" in event ? event.touches[0].clientX : event.clientX;
  const clientY = "touches" in event ? event.touches[0].clientY : event.clientY;

  dragPosition.value = {
    x: clientX - startDragPosition.value.x,
    y: clientY - startDragPosition.value.y,
  };
}

/**
 * Handles the mouse up or touch end event to stop panning.
 */
function stopPan() {
  isDragging.value = false;
  // Remove the listeners from the window
  window.removeEventListener("mousemove", onPan);
  window.removeEventListener("mouseup", stopPan);
  window.removeEventListener("touchmove", onPan);
  window.removeEventListener("touchend", stopPan);
}

/**
 * Toggles the zoom state.
 */
function toggleZoom() {
  isZoomed.value = !isZoomed.value;
  if (!isZoomed.value) {
    // Reset position when unzooming
    dragPosition.value = { x: 0, y: 0 };
  }
}

/**
 * Prevents the context menu from appearing on right-click to avoid interference with dragging.
 */
function handleContextMenu(event: MouseEvent) {
  event.preventDefault();
}
</script>

<template>
  <div class="relative w-full h-full">
    <!-- The normal image display -->
    <div class="cursor-pointer w-full h-full" @click="toggleZoom">
      <img
        :src="props.src"
        :alt="props.alt"
        class="rounded-lg object-scale-down w-full h-full"
      />
    </div>

    <!-- The full-screen overlay for the zoomed image -->
    <div
      v-if="isZoomed"
      class="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-80 transition-opacity duration-300 backdrop-blur-sm"
      @click.self="toggleZoom"
    >
      <div class="relative w-full h-full max-h-screen">
        <img
          :src="props.src"
          :alt="props.alt"
          class="block max-w-[90vw] max-h-[90vh] transition-transform duration-200 ease-out"
          :style="{
            transform: `translate(${dragPosition.x}px, ${dragPosition.y}px)`,
            cursor: isDragging ? 'grabbing' : 'grab',
          }"
          @mousedown.prevent="startPan"
          @touchstart.prevent="startPan"
          @contextmenu="handleContextMenu"
        />
      </div>

      <!-- Close button for the full-screen view -->
      <button
        class="absolute top-4 right-4 text-white text-3xl p-2 rounded-full bg-gray-700 bg-opacity-50 hover:bg-opacity-70 transition-colors"
        @click="toggleZoom"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          class="h-8 w-8"
          fill="none"
          viewBox="0 0 24 24"
          stroke="currentColor"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M6 18L18 6M6 6l12 12"
          />
        </svg>
      </button>
    </div>
  </div>
</template>
