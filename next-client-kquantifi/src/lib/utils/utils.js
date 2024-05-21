import { clsx } from "clsx"
import { twMerge } from "tailwind-merge"

export function cn(...inputs) {
  return twMerge(clsx(inputs))
}

/**
 * Converts an HSL color value to RGB.
 * Assumes h is in the range [0, 360], and s and l are in the range [0, 100].
 * Returns an object with r, g, and b properties in the range [0, 255].
 *
 * @param {number} h - Hue, in degrees [0, 360]
 * @param {number} s - Saturation, in percentage [0, 100]
 * @param {number} l - Lightness, in percentage [0, 100]
 * @return {object} An object with properties r, g, and b in the range [0, 255]
 */
export function hslToRgb(h, s, l) {
  s /= 100;
  l /= 100;
  const k = n => (n + h / 30) % 12;
  const a = s * Math.min(l, 1 - l);
  const f = n => l - a * Math.max(-1, Math.min(k(n) - 3, Math.min(9 - k(n), 1)));

  const r = Math.round(255 * f(0));
  const g = Math.round(255 * f(8));
  const b = Math.round(255 * f(4));

  return { r, g, b };
}

/**
 * Converts an HSL color value to an RGB string.
 * Assumes h is in the range [0, 360], and s and l are in the range [0, 100].
 * Returns a string in the format 'rgb(r, g, b)'.
 *
 * @param {number} h - Hue, in degrees [0, 360]
 * @param {number} s - Saturation, in percentage [0, 100]
 * @param {number} l - Lightness, in percentage [0, 100]
 * @return {string} A string in the format 'rgb(r, g, b)'
 */
export function hslToRgbString(h, s, l) {
  const { r, g, b } = hslToRgb(h, s, l);
  return `rgb(${r}, ${g}, ${b})`;
}