package lab.main.puzzle

import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

class MovementTouchListener(
	private val puzzleGame: PuzzleGame,
	private val updatePositions: (PuzzleGame) -> Unit
) :
	View.OnTouchListener {
	private var x = 0f
	private var y = 0f
	override fun onTouch(view: View?, event: MotionEvent): Boolean {
		when(event.action) {
			MotionEvent.ACTION_DOWN -> {
				x = event.x
				y = event.y
			}
			MotionEvent.ACTION_UP -> {
				val distance_x = event.x - x
				val distance_y = event.y - y
				if(abs(distance_x) > abs(distance_y)) {
					if(distance_x > 0)
						puzzleGame.move(Direction.RIGHT)
					else
						puzzleGame.move(Direction.LEFT)
				} else {
					if(distance_y > 0)
						puzzleGame.move(Direction.DOWN)
					else
						puzzleGame.move(Direction.UP)
				}
				updatePositions(puzzleGame)
			}
			MotionEvent.ACTION_MOVE -> {
			}
		}
		return true
	}
}