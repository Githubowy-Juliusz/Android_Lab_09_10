package lab.main.puzzle

import android.media.MediaPlayer
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

class MovementTouchListener(
	private val animator: Animator,
	private val updatePositions: (PuzzleGame) -> Unit,
	private val mediaPlayer: MediaPlayer
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
				var moved: Boolean
				if(abs(distance_x) > abs(distance_y)) {
					if(distance_x > 0)
						moved = animator.move(Direction.RIGHT)
					else
						moved = animator.move(Direction.LEFT)
				} else {
					if(distance_y > 0)
						moved = animator.move(Direction.DOWN)
					else
						moved = animator.move(Direction.UP)
				}
				if(moved) {
					updatePositions(animator.game)
					mediaPlayer.seekTo(0)
					mediaPlayer.start()
				}
			}
			MotionEvent.ACTION_MOVE -> {
			}
		}
		return true
	}
}