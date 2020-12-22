package lab.main.puzzle

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import lab.main.R

class PuzzleFragment : Fragment(R.layout.puzzle_fragment) {
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val restartButton = view.findViewById<Button>(R.id.puzzleRestartButton)
		val touchLayer = view.findViewById<ImageView>(R.id.puzzleTouchLayer)
		val movesText = view.findViewById<TextView>(R.id.puzzleMovesText)
		val positions = listOf(
			view.findViewById<ImageView>(R.id.puzzlePosition1),
			view.findViewById<ImageView>(R.id.puzzlePosition2),
			view.findViewById<ImageView>(R.id.puzzlePosition3),
			view.findViewById<ImageView>(R.id.puzzlePosition4),
			view.findViewById<ImageView>(R.id.puzzlePosition5),
			view.findViewById<ImageView>(R.id.puzzlePosition6),
			view.findViewById<ImageView>(R.id.puzzlePosition7),
			view.findViewById<ImageView>(R.id.puzzlePosition8),
			view.findViewById<ImageView>(R.id.puzzlePosition9)
		)
		val pictureSetter = PictureSetter()

		fun updatePositions(game: PuzzleGame) {
			val gamePositions = game.positions
			if(game.isSolved)
				movesText.text = "You won in " + game.moves.toString() + " moves"
			else
				movesText.text = "Moves: " + game.moves.toString()
			positions.forEachIndexed { index, position ->
				pictureSetter.set(
					position, gamePositions[index + 1] ?: error("Update error")
				)
			}
		}

		restartButton.text = "Start"
		restartButton.setOnClickListener {
			restartButton.text = "Restart"
			val game = PuzzleGame()
			updatePositions(game)
			touchLayer.setOnTouchListener(
				MovementTouchListener(
					game, ::updatePositions
				)
			)
		}
	}
}