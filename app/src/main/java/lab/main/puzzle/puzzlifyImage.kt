package lab.main.puzzle

import android.graphics.*


fun puzzlifyImage(image: Bitmap): List<Bitmap> {
	val verticalFragments = 3
	val horizontalFragments = 3
	fun splitImage(): MutableList<Bitmap> {
		val fragmentWidth = image.width / horizontalFragments
		val fragmentHeight = image.height / verticalFragments
		val fragments = mutableListOf<Bitmap>()
		for(y in (0 until verticalFragments)) {
			for(x in (0 until horizontalFragments)) {
				val left = x * fragmentWidth
				val top = y * fragmentHeight
				val fragment =
					Bitmap.createBitmap(
						image, left, top, fragmentWidth, fragmentHeight
					)
				fragments.add(fragment)
			}
		}
		image.recycle()
		return fragments
	}

	val split = splitImage()
	split[split.lastIndex] = toGrayscale(split.last())
	return split.toList()
}

private fun toGrayscale(source: Bitmap): Bitmap {
	val width = source.width
	val height = source.height
	val grayBitmap =
		Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
	val colorMatrix = ColorMatrix()
	colorMatrix.setSaturation(0f)
	val colorFilter = ColorMatrixColorFilter(colorMatrix)
	val paint = Paint()
	paint.colorFilter = colorFilter
	val canvas = Canvas(grayBitmap)
	canvas.drawBitmap(source, 0f, 0f, paint)
	return grayBitmap
}