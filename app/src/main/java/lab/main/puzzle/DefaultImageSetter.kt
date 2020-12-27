package lab.main.puzzle

import android.widget.ImageView
import lab.main.R

class DefaultImageSetter {
	private val dict = mapOf(
		0 to R.drawable.tile_0,
		1 to R.drawable.tile_1,
		2 to R.drawable.tile_2,
		3 to R.drawable.tile_3,
		4 to R.drawable.tile_4,
		5 to R.drawable.tile_5,
		6 to R.drawable.tile_6,
		7 to R.drawable.tile_7,
		8 to R.drawable.tile_8
	)

	fun set(imageView: ImageView, value: Int) {
		dict[value]?.let { imageView.setImageResource(it) }
	}
}