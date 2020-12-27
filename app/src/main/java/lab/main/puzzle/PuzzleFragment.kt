package lab.main.puzzle

import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Bundle
import android.os.SystemClock
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
			Position(view.findViewById<ImageView>(R.id.puzzlePosition9), 9),
			Position(view.findViewById<ImageView>(R.id.puzzlePosition1), 1),
			Position(view.findViewById<ImageView>(R.id.puzzlePosition2), 2),
			Position(view.findViewById<ImageView>(R.id.puzzlePosition3), 3),
			Position(view.findViewById<ImageView>(R.id.puzzlePosition4), 4),
			Position(view.findViewById<ImageView>(R.id.puzzlePosition5), 5),
			Position(view.findViewById<ImageView>(R.id.puzzlePosition6), 6),
			Position(view.findViewById<ImageView>(R.id.puzzlePosition7), 7),
			Position(view.findViewById<ImageView>(R.id.puzzlePosition8), 8)
		)
		val changeImageLayout =
			view.findViewById<ConstraintLayout>(R.id.puzzleChangeImageLayout)
		val changeImageButton =
			view.findViewById<Button>(R.id.puzzleChangeImageButton)
		val geraltImageButton =
			view.findViewById<Button>(R.id.puzzleGeraltImageButton)
		val cpImageButton =
			view.findViewById<Button>(R.id.puzzleCyberpunkImageButton)
		val defaultImageButton =
			view.findViewById<Button>(R.id.puzzleDefaultImageButton)
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
		}

		fun startGame() {
			val game = PuzzleGame()
			val animator = Animator(positions, game, 200)
			updatePositions(game)
			touchLayer.setOnTouchListener(
				MovementTouchListener(
					animator, ::updatePositions, mediaPlayer
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

		val imageSetter = DefaultImageSetter()
		changeImageButton.setOnClickListener {
			changeImageLayout.visibility = View.VISIBLE
		}
		defaultImageButton.setOnClickListener {
			positions.forEachIndexed { index, position ->
				imageSetter.set(position.view as ImageView, index)
			}
			changeImageLayout.visibility = View.GONE
		}
		geraltImageButton.setOnClickListener {
			val images = puzzlifyImage(
				BitmapFactory.decodeResource(
					resources, R.drawable.geralt
				)
			)
			for(position in positions) {
				(position.view as ImageView).setImageBitmap(images[position.number - 1])
			}
			changeImageLayout.visibility = View.GONE
		}
		cpImageButton.setOnClickListener {
			val images = puzzlifyImage(
				BitmapFactory.decodeResource(
					resources, R.drawable.cyberpunk
				)
			)
			for(position in positions) {
				(position.view as ImageView).setImageBitmap(images[position.number - 1])
			}
			changeImageLayout.visibility = View.GONE
		}
	}
}