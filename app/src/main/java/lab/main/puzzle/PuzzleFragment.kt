package lab.main.puzzle

import android.media.MediaPlayer
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import lab.main.R

class PuzzleFragment : Fragment(R.layout.puzzle_fragment) {
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val layout = view.findViewById<ConstraintLayout>(R.id.puzzleLayout)
		val timer = view.findViewById<Chronometer>(R.id.puzzleTimer)
		val restartButton = view.findViewById<Button>(R.id.puzzleRestartButton)
		val touchLayer = view.findViewById<ImageView>(R.id.puzzleTouchLayer)
		val movesText = view.findViewById<TextView>(R.id.puzzleMovesText)
		val difficultyLayout =
			view.findViewById<ConstraintLayout>(R.id.puzzleDifficultyLayout)
		val difficultyButtonUnlimited =
			view.findViewById<Button>(R.id.puzzleDifficultyButtonUnlimited)
		val difficultyButton10Min =
			view.findViewById<Button>(R.id.puzzleDifficultyButton10Min)
		val difficultyButton1Min =
			view.findViewById<Button>(R.id.puzzleDifficultyButton1Min)
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
		var timeLimit = 0
		val mediaPlayer = MediaPlayer.create(this.context, R.raw.sound)
		fun updatePositions(game: PuzzleGame) {
			if(game.isSolved) {
				timer.stop()
				val moves = game.moves.toString()
				movesText.text = "You won in $moves moves"
				touchLayer.setOnTouchListener(null)
			} else
				movesText.text = "Moves: " + game.moves.toString()
			val gamePositions = game.positions
			positions.forEachIndexed { index, position ->
				pictureSetter.set(
					position, gamePositions[index + 1] ?: error("Update error")
				)
			}
		}

		fun startGame() {
			val game = PuzzleGame()
			updatePositions(game)
			touchLayer.setOnTouchListener(
				MovementTouchListener(
					game, ::updatePositions, mediaPlayer
				)
			)
			timer.base = SystemClock.elapsedRealtime()
			timer.start()
		}

		restartButton.text = "Start"
		restartButton.setOnClickListener {
			restartButton.text = "Restart"
			difficultyLayout.visibility = View.VISIBLE
		}

		timer.setOnChronometerTickListener {
			Log.d("Hello", "Is this working as intended?")
			if(timeLimit == 0)
				return@setOnChronometerTickListener
			val elapsedTime = (SystemClock.elapsedRealtime() - timer.base) / 1000
			if(elapsedTime >= timeLimit) {
				timer.stop()
				movesText.text = "You have run out of time."
				touchLayer.setOnTouchListener(null)
			}
		}

		difficultyButtonUnlimited.setOnClickListener {
			timeLimit = 0
			startGame()
			difficultyLayout.visibility = View.GONE
		}
		difficultyButton1Min.setOnClickListener {
			timeLimit = 60
			startGame()
			difficultyLayout.visibility = View.GONE
		}
		difficultyButton10Min.setOnClickListener {
			timeLimit = 60 * 10
			startGame()
			difficultyLayout.visibility = View.GONE
		}
	}
}