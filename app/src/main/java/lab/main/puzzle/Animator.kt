package lab.main.puzzle

import android.animation.ObjectAnimator

class Animator(
	val positions: List<Position>,
	val game: PuzzleGame,
	val yOffest: Int
) {
	init {
		animate(0)
	}

	fun move(direction: Direction): Boolean {
		if(!game.move(direction))
			return false
		animate(500)
		return true
	}

	private fun animate(duration_: Long) {
		positions.forEachIndexed { index, position ->
			val blockToMoveTo = game.blocks[index]!!
			val locationToMoveTo =
				positions.filter { position -> position.number == blockToMoveTo }
					.first().initialLocation
			ObjectAnimator.ofFloat(
				position.view, "x", locationToMoveTo[0].toFloat()
			)
				.apply {
					duration = duration_
					start()
				}
			ObjectAnimator.ofFloat(
				position.view, "y", locationToMoveTo[1].toFloat() - yOffest
			)
				.apply {
					duration = duration_
					start()
				}
		}
	}
}
